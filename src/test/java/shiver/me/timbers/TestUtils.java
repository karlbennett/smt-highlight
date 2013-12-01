package shiver.me.timbers;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.NullHighlight.NULL_HIGHLIGHT;

/**
 * Utility methods and constants to help with creating tests.
 *
 * @author Karl Bennett
 */
public final class TestUtils {

    private TestUtils() {
    }

    public static final String HIGHLIGHT_NAME_ONE = "one";
    public static final String HIGHLIGHT_NAME_TWO = "two";
    public static final String HIGHLIGHT_NAME_THREE = "three";


    public static Iterable createEmptyIterable() {

        Iterator iterator = mock(Iterator.class);

        Iterable emptyIterable = mock(Iterable.class);
        when(emptyIterable.iterator()).thenReturn(iterator);

        return emptyIterable;
    }

    /**
     * Assert that the returned {@link Highlight} is the {@link NullHighlight} for the supplied index.
     */
    public static void assertNullHighlight(Highlights highlights, int index) {

        assertEquals("the null highlight should be returned for the index " + index, NULL_HIGHLIGHT,
                highlights.get(index));
    }

    /**
     * Assert that the returned {@link Highlight} is the {@link NullHighlight} for the supplied name.
     */
    public static void assertNullHighlight(Highlights highlights, String name) {

        assertEquals("the null highlight should be returned for the name " + name, NULL_HIGHLIGHT,
                highlights.get(name));
    }
}
