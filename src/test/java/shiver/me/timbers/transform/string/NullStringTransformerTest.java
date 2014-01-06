package shiver.me.timbers.transform.string;

import org.junit.Test;
import shiver.me.timbers.transform.Transformation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static shiver.me.timbers.transform.FileUtils.testFileText;

public class NullStringTransformerTest {

    private static final String TEST_STRING = testFileText();

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
