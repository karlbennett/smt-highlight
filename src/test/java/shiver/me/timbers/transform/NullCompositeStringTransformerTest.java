package shiver.me.timbers.transform;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static shiver.me.timbers.transform.FileUtils.testFileText;
import static shiver.me.timbers.transform.TestUtils.EMPTY_TRANSFORMATIONS;

public class NullCompositeStringTransformerTest {

    private static final String TEST_STRING = testFileText();

    @Test
    public void testCreate() {

        new NullCompositeStringTransformer<Transformation>(EMPTY_TRANSFORMATIONS);
    }

    @Test(expected = AssertionError.class)
    public void testWithNullTransformations() {

        new NullCompositeStringTransformer<Transformation>(null);
    }

    @Test
    public void testTransformWithStringAndNullTransformations() {

        assertEquals("the input string should be returned.", TEST_STRING,
                new NullCompositeStringTransformer<Transformation>(EMPTY_TRANSFORMATIONS).transform(TEST_STRING, null));
    }

    @Test
    public void testTransformWithNullStringAndTransformations() {

        assertNull("null should be returned.",
                new NullCompositeStringTransformer<Transformation>(EMPTY_TRANSFORMATIONS).transform(null, null));
    }

    @Test
    public void testTransformWithString() {

        assertEquals("the input string should be returned.", TEST_STRING,
                new NullCompositeStringTransformer<Transformation>(EMPTY_TRANSFORMATIONS).transform(TEST_STRING));
    }

    @Test
    public void testTransformWithNullString() {

        assertNull("null should be returned.",
                new NullCompositeStringTransformer<Transformation>(EMPTY_TRANSFORMATIONS).transform(null));
    }
}
