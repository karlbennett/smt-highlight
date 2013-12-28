package shiver.me.timbers.transform;

import org.junit.Test;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.transform.NullTransformation.NULL_TRANSFORMATION;

public class NullTransformationTest {

    @Test
    public void testGetName() {

        assertEquals("the null Transformation name should be correct.", NullTransformation.class.getSimpleName(),
                NULL_TRANSFORMATION.getName());
    }

    @Test
    public void testEquals() {

        assertTrue("the null Transformation equals should work.", NULL_TRANSFORMATION.equals(new NullTransformation()));
    }

    @Test
    public void testEqualsWithNonNullTransformation() {

        assertFalse("the null Transformation equals should work with a non null Transformation.",
                NULL_TRANSFORMATION.equals(mock(Transformation.class)));
    }

    @Test
    public void testEqualsWithNonTransformation() {

        assertFalse("the null Transformation equals should work with a non null Transformation.",
                NULL_TRANSFORMATION.equals(new Object()));
    }

    @Test
    @SuppressWarnings("ObjectEqualsNull")
    public void testEqualsWithNull() {

        assertFalse("the null Transformation equals should work with a non null Transformation.", NULL_TRANSFORMATION.equals(null));
    }

    @Test
    public void testHashCode() {

        assertThat("the null Transformation hascode should return a non zero value.", NULL_TRANSFORMATION.hashCode(),
                greaterThan(0));
    }
}
