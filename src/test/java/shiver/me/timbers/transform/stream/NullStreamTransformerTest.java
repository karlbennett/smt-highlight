package shiver.me.timbers.transform.stream;

import org.junit.Test;
import shiver.me.timbers.transform.Transformation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static shiver.me.timbers.transform.FileUtils.testFileInputStream;
import static shiver.me.timbers.transform.FileUtils.testFileText;

public class NullStreamTransformerTest {

    @Test
    public void testCreate() {

        new NullStreamTransformer<Transformation>();
    }

    @Test
    public void testTransform() {

        assertEquals("the input string should be returned.", testFileText(),
                new NullStreamTransformer<Transformation>().transform(testFileInputStream(), null));
    }

    @Test
    public void testTransformWithNullString() {

        assertNull("null should be returned.", new NullStreamTransformer<Transformation>().transform(null, null));
    }
}
