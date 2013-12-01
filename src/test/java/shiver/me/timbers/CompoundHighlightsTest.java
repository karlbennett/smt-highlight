package shiver.me.timbers;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.TestUtils.HIGHLIGHT_NAME_ONE;
import static shiver.me.timbers.TestUtils.HIGHLIGHT_NAME_THREE;
import static shiver.me.timbers.TestUtils.HIGHLIGHT_NAME_TWO;
import static shiver.me.timbers.TestUtils.assertNullHighlight;
import static shiver.me.timbers.TestUtils.createEmptyIterable;

public class CompoundHighlightsTest implements HighlightsTestTemplate {

    private Iterable<String> iterable;
    private Applyer applyer;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {

        final Iterator<String> iterator = mock(Iterator.class);
        when(iterator.hasNext()).thenReturn(true, true, true, false);
        when(iterator.next()).thenReturn(HIGHLIGHT_NAME_ONE, HIGHLIGHT_NAME_TWO, HIGHLIGHT_NAME_THREE);

        iterable = mock(Iterable.class);
        when(iterable.iterator()).thenReturn(iterator);

        applyer = mock(Applyer.class);
    }

    @Test
    @Override
    public void testCreate() {

        new CompoundHighlights(iterable, mock(Applyer.class));
    }

    @Test
    @SuppressWarnings({"unchecked", "UnusedDeclaration"})
    @Override
    public void testCreateWithEmptyIterable() {

        for (Highlight highlight : new CompoundHighlights(createEmptyIterable(), applyer)) {

            fail("an empty " + Highlights.class.getSimpleName() + " should not iterate.");
        }
    }

    @Test(expected = AssertionError.class)
    @Override
    public void testCreateWithNullIterable() {

        new CompoundHighlights(null, mock(Applyer.class));
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullApplyer() {

        new CompoundHighlights(iterable, null);
    }

    @Test
    @Override
    public void testGetWithIndex() {

        final Highlights highlights = new CompoundHighlights(iterable, applyer);

        assertEquals("a highlight with the name " + HIGHLIGHT_NAME_ONE + " should be returned for index 0",
                HIGHLIGHT_NAME_ONE, highlights.get(0).getName());

        assertEquals("a highlight with the name " + HIGHLIGHT_NAME_TWO + " should be returned for index 1",
                HIGHLIGHT_NAME_TWO, highlights.get(1).getName());

        assertEquals("a highlight with the name " + HIGHLIGHT_NAME_THREE + " should be returned for index 2",
                HIGHLIGHT_NAME_THREE, highlights.get(2).getName());
    }

    @Test
    @Override
    public void testGetWithInvalidIndex() {

        Highlights highlights = new CompoundHighlights(iterable, applyer);

        assertNullHighlight(highlights, 3);
    }

    @Test
    @Override
    public void testGetWithName() {

        final Highlights highlights = new CompoundHighlights(iterable, applyer);

        assertEquals("a highlight with the name " + HIGHLIGHT_NAME_ONE + " should be returned for the name " +
                HIGHLIGHT_NAME_ONE,
                HIGHLIGHT_NAME_ONE, highlights.get(HIGHLIGHT_NAME_ONE).getName());

        assertEquals("a highlight with the name " + HIGHLIGHT_NAME_TWO + " should be returned for the name " +
                HIGHLIGHT_NAME_TWO,
                HIGHLIGHT_NAME_TWO, highlights.get(HIGHLIGHT_NAME_TWO).getName());

        assertEquals("a highlight with the name " + HIGHLIGHT_NAME_THREE + " should be returned for the name " +
                HIGHLIGHT_NAME_THREE,
                HIGHLIGHT_NAME_THREE, highlights.get(HIGHLIGHT_NAME_THREE).getName());
    }

    @Test
    @Override
    public void testGetWithInvalidName() {

        Highlights highlights = new CompoundHighlights(iterable, applyer);

        assertNullHighlight(highlights, "not a highlight");
    }

    @Test
    @Override
    public void testGetWithNullName() {

        Highlights highlights = new CompoundHighlights(iterable, applyer);

        assertNullHighlight(highlights, null);
    }

    @Test
    @Override
    public void testIterator() {

        assertNotNull("an iterator should be returned", new CompoundHighlights(iterable, applyer).iterator());
    }

    @Test
    public void testApplyer() {

        final String TEST_APPLY_STRING = "this is the apply string.";

        when(applyer.apply(anyString())).thenReturn(TEST_APPLY_STRING);

        Highlights highlights = new CompoundHighlights(iterable, applyer);

        for (Highlight highlight : highlights) {

            assertEquals("all highlights should produce the same apply result.", TEST_APPLY_STRING,
                    highlight.apply("input string"));
        }
    }
}
