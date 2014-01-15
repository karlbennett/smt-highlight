package shiver.me.timbers.transform;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static shiver.me.timbers.transform.AbstractTransformer.quietMimeType;
import static shiver.me.timbers.transform.NullTransformer.TEXT_PLAIN;

public class AbstractTransformerTest {

    @Test
    public void testQuietMimeTypeWithValidMimeType() throws Exception {

        assertEquals("the mime type should be test/plain.", TEXT_PLAIN.toString(),
                quietMimeType("text", "plain").toString());
    }

    @Test(expected = RuntimeException.class)
    public void testQuietMimeTypeWithInvalidMimeType() throws Exception {

        quietMimeType("/in", "valid/");
    }

    @Test(expected = RuntimeException.class)
    public void testQuietMimeTypeWithNullPrimary() throws Exception {

        quietMimeType(null, "plain");
    }

    @Test(expected = RuntimeException.class)
    public void testQuietMimeTypeWithNullSub() throws Exception {

        quietMimeType("text", null);
    }
}
