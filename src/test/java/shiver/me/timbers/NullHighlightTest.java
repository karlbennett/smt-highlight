package shiver.me.timbers;

import org.junit.Test;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.NullHighlight.NULL_HIGHLIGHT;

public class NullHighlightTest {

    @Test
    public void testGetName() {

        assertEquals("the null highlight name should be correct.", NullHighlight.class.getSimpleName(),
                NULL_HIGHLIGHT.getName());
    }

    @Test
    public void testApply() {

        final String TEST_STRING = "test string";

        assertEquals("the null highlight apply should do nothing.", TEST_STRING, NULL_HIGHLIGHT.apply(TEST_STRING));
    }

    @Test
    public void testEquals() {

        assertTrue("the null highlight equals should work.", NULL_HIGHLIGHT.equals(new NullHighlight()));
    }

    @Test
    public void testEqualsWithNonNullHighlight() {

        assertFalse("the null highlight equals should work with a non null highlight.",
                NULL_HIGHLIGHT.equals(mock(Highlight.class)));
    }

    @Test
    public void testEqualsWithNonHighlight() {

        assertFalse("the null highlight equals should work with a non null highlight.",
                NULL_HIGHLIGHT.equals(new Object()));
    }

    @Test
    @SuppressWarnings("ObjectEqualsNull")
    public void testEqualsWithNull() {

        assertFalse("the null highlight equals should work with a non null highlight.", NULL_HIGHLIGHT.equals(null));
    }

    @Test
    public void testHashCode() {

        assertThat("the null highlight hascode should return a non zero value.", NULL_HIGHLIGHT.hashCode(),
                greaterThan(0));
    }
}
