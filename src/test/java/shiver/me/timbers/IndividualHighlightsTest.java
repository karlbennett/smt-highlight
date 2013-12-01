package shiver.me.timbers;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.NullHighlight.*;

/**
 * @author Karl Bennett
 */
public class IndividualHighlightsTest {

    private static final String HIGHLIGHT_NAME_ONE = "one";
    private static final String HIGHLIGHT_NAME_TWO = "two";
    private static final String HIGHLIGHT_NAME_THREE = "three";

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
    public void testCreateIndividualHighlights() {

        new IndividualHighlights(highlightIterable);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testCreateIndividualHighlightsWithEmptyIterable() {

        Iterator<Highlight> iterator = mock(Iterator.class);

        Iterable<Highlight> emptyHighlightIterable = mock(Iterable.class);
        when(emptyHighlightIterable.iterator()).thenReturn(iterator);

        new IndividualHighlights(emptyHighlightIterable);
    }

    @Test(expected = AssertionError.class)
    public void testCreateIndividualHighlightsWithNullHighlights() {

        new IndividualHighlights(null);
    }

    @Test
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
    public void testGetWithInvalidIndex() {

        Highlights highlights = new IndividualHighlights(highlightIterable);

        assertEquals("the null highlight should be returned for index 3",
                NULL_HIGHLIGHT, highlights.get(3));
    }

    @Test
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
    public void testGetWithInvalidName() {

        final String INVALID_NAME = "not a highlight";

        Highlights highlights = new IndividualHighlights(highlightIterable);

        assertEquals("he null highlight should be returned for a name \"" + INVALID_NAME + "\"",
                NULL_HIGHLIGHT, highlights.get(INVALID_NAME));
    }

    @Test
    public void testGetWithNullName() {

        Highlights highlights = new IndividualHighlights(highlightIterable);

        assertEquals("he null highlight should be returned for a name null", NULL_HIGHLIGHT, highlights.get(null));
    }

    @Test
    public void testIterator() {

        assertNotNull("an iterator should be returned", new IndividualHighlights(highlightIterable).iterator());
    }
}
