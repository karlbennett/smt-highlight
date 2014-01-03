package shiver.me.timbers.transform;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static shiver.me.timbers.transform.FileUtils.testFile;
import static shiver.me.timbers.transform.FileUtils.testFileText;

public class NullFileTransformerTest {

    @Test
    public void testCreate() {

        new NullFileTransformer<Transformation>();
    }

    @Test
    public void testTransform() {

        assertEquals("the input string should be returned.", testFileText(),
                new NullFileTransformer<Transformation>().transform(testFile(), null));
    }

    @Test
    public void testTransformWithNullString() {

        assertNull("null should be returned.", new NullFileTransformer<Transformation>().transform(null, null));
    }
}
