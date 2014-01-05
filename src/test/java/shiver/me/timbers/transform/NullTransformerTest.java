package shiver.me.timbers.transform;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class NullTransformerTest {

    @Test
    public void testTransform() {

        final Object object = new Object();

        assertEquals("response should be the inputs toString() value.", object.toString(),
                new NullTransformer<Object, Transformation>().transform(object, null));
    }

    @Test
    public void testTransformWithNullInput() {

        assertNull("response should be null for null input.",
                new NullTransformer<Object, Transformation>().transform(null, null));
    }
}
