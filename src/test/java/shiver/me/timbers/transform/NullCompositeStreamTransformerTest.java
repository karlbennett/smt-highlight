package shiver.me.timbers.transform;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static shiver.me.timbers.transform.FileUtils.testFileInputStream;
import static shiver.me.timbers.transform.FileUtils.testFileText;
import static shiver.me.timbers.transform.TestUtils.EMPTY_TRANSFORMATIONS;

public class NullCompositeStreamTransformerTest {

    private static final String TEST_STRING = testFileText();

    private InputStream stream;

    @Before
    public void setUp() {

        stream = testFileInputStream();
    }

    @Test
    public void testCreate() {

        new NullCompositeStreamTransformer<Transformation>(EMPTY_TRANSFORMATIONS);
    }

    @Test(expected = AssertionError.class)
    public void testWithNullTransformations() {

        new NullCompositeStreamTransformer<Transformation>(null);
    }

    @Test
    public void testTransformWithStreamAndNullTransformations() {

        assertEquals("the input string should be returned.", TEST_STRING,
                new NullCompositeStreamTransformer<Transformation>(EMPTY_TRANSFORMATIONS).transform(stream, null));
    }

    @Test
    public void testTransformWithNullStreamAndTransformations() {

        assertNull("null should be returned.",
                new NullCompositeStreamTransformer<Transformation>(EMPTY_TRANSFORMATIONS).transform(null, null));
    }

    @Test
    public void testTransformWithStream() {

        assertEquals("the input string should be returned.", TEST_STRING,
                new NullCompositeStreamTransformer<Transformation>(EMPTY_TRANSFORMATIONS).transform(stream));
    }

    @Test
    public void testTransformWithNullStream() {

        assertNull("null should be returned.",
                new NullCompositeStreamTransformer<Transformation>(EMPTY_TRANSFORMATIONS).transform(null));
    }
}
