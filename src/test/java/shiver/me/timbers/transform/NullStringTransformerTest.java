package shiver.me.timbers.transform;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class NullStringTransformerTest {

    private static final String TEST_STRING = "test_string";

    @Test
    public void testTransform() {

        assertEquals("the input string should be returned.", TEST_STRING,
                new NullStringTransformer<Transformation>().transform(TEST_STRING, null));
    }

    @Test
    public void testTransformWithNullString() {

        assertNull("null should be returned.", new NullStringTransformer<Transformation>().transform(null, null));
    }
}
