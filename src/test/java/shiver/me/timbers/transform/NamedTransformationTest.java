package shiver.me.timbers.transform;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NamedTransformationTest {

    private static final String NAME = "test_name";

    @Test
    public void testCreate() {

        new NamedTransformation(NAME);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullName() {

        new NamedTransformation(null);
    }

    @Test
    public void testGetName() {

        assertEquals("the name should be set correctly.", NAME, new NamedTransformation(NAME).getName());
    }
}
