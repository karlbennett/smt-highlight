package shiver.me.timbers;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.TestUtils.HIGHLIGHT_NAME_ONE;
import static shiver.me.timbers.TestUtils.HIGHLIGHT_NAME_THREE;
import static shiver.me.timbers.TestUtils.HIGHLIGHT_NAME_TWO;
import static shiver.me.timbers.TestUtils.assertNullHighlight;
import static shiver.me.timbers.TestUtils.createEmptyIterable;

public class IndividualHighlightsTest implements HighlightsTestTemplate {

    private Iterable<Highlight> highlightIterable;

    private Highlight highlightOne;
    private Highlight highlightTwo;
    private Highlight highlightThree;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {

        highlightOne = mock(Highlight.class);
        when(highlightOne.getName()).thenReturn(HIGHLIGHT_NAME_ONE);

        highlightTwo = mock(Highlight.class);
        when(highlightTwo.getName()).thenReturn(HIGHLIGHT_NAME_TWO);

        highlightThree = mock(Highlight.class);
        when(highlightThree.getName()).thenReturn(HIGHLIGHT_NAME_THREE);

        Iterator<Highlight> iterator = mock(Iterator.class);
        when(iterator.hasNext()).thenReturn(true, true, true, false);
        when(iterator.next()).thenReturn(highlightOne, highlightTwo, highlightThree);

        highlightIterable = mock(Iterable.class);
        when(highlightIterable.iterator()).thenReturn(iterator);
    }

    @Test
    @Override
    public void testCreate() {

        new IndividualHighlights(highlightIterable);
    }

    @Test
    @Override
    @SuppressWarnings({"unchecked", "UnusedDeclaration"})
    public void testCreateWithEmptyIterable() {

        for (Highlight highlight : new IndividualHighlights(createEmptyIterable())) {

            fail("an empty " + Highlights.class.getSimpleName() + " should not iterate.");
        }
    }

    @Test(expected = AssertionError.class)
    @Override
    public void testCreateWithNullIterable() {

        new IndividualHighlights(null);
    }

    @Test
    @Override
    public void testGetWithIndex() {

        Highlights highlights = new IndividualHighlights(highlightIterable);

        assertEquals("highlight " + HIGHLIGHT_NAME_ONE + " should be returned for index 0",
                highlightOne, highlights.get(0));

        assertEquals("highlight " + HIGHLIGHT_NAME_TWO + " should be returned for index 1",
                highlightTwo, highlights.get(1));

        assertEquals("highlight " + HIGHLIGHT_NAME_THREE + " should be returned for index 2",
                highlightThree, highlights.get(2));
    }

    @Test
    @Override
    public void testGetWithInvalidIndex() {

        Highlights highlights = new IndividualHighlights(highlightIterable);

        assertNullHighlight(highlights, 3);
    }

    @Test
    @Override
    public void testGetWithName() {

        Highlights highlights = new IndividualHighlights(highlightIterable);

        assertEquals("highlight " + HIGHLIGHT_NAME_ONE + " should be returned for the name " + HIGHLIGHT_NAME_ONE,
                highlightOne, highlights.get(HIGHLIGHT_NAME_ONE));

        assertEquals("highlight " + HIGHLIGHT_NAME_TWO + " should be returned for the name " + HIGHLIGHT_NAME_TWO,
                highlightTwo, highlights.get(HIGHLIGHT_NAME_TWO));

        assertEquals("highlight " + HIGHLIGHT_NAME_THREE + " should be returned for the name " + HIGHLIGHT_NAME_THREE,
                highlightThree, highlights.get(HIGHLIGHT_NAME_THREE));
    }

    @Test
    @Override
    public void testGetWithInvalidName() {

        Highlights highlights = new IndividualHighlights(highlightIterable);

        assertNullHighlight(highlights, "not a highlight");
    }

    @Test
    @Override
    public void testGetWithNullName() {

        Highlights highlights = new IndividualHighlights(highlightIterable);

        assertNullHighlight(highlights, null);
    }

    @Test
    @Override
    public void testIterator() {

        assertNotNull("an iterator should be returned", new IndividualHighlights(highlightIterable).iterator());
    }
}
