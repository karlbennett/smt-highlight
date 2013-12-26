package shiver.me.timbers.transform;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.transform.TestUtils.ONE;
import static shiver.me.timbers.transform.TestUtils.THREE;
import static shiver.me.timbers.transform.TestUtils.TWO;
import static shiver.me.timbers.transform.TestUtils.assertNullTransformation;

public class CompoundTransformationsTest implements TransformationsTestTemplate {

    private Applyer applyer;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {

        applyer = mock(Applyer.class);
    }

    @Test
    @Override
    public void testCreate() {

        new CompoundTransformations(mockIterable(), mock(Applyer.class));
    }

    @Test
    @SuppressWarnings("UnusedDeclaration")
    @Override
    public void testCreateWithEmptyIterable() {

        for (Transformation transformation :
                new CompoundTransformations(Collections.<String>emptySet(), applyer)) {

            fail("an empty " + Transformations.class.getSimpleName() + " should not iterate.");
        }
    }

    @Test(expected = AssertionError.class)
    @Override
    public void testCreateWithNullIterable() {

        new CompoundTransformations(null, mock(Applyer.class));
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullApplyer() {

        new CompoundTransformations(mockIterable(), null);
    }

    @Test
    @Override
    public void testGetWithIndex() {

        final Transformations transformations = new CompoundTransformations(mockIterable(), applyer);

        Assert.assertEquals("a Transformation with the name " + ONE + " should be returned for index 0",
                ONE, transformations.get(0).getName());

        Assert.assertEquals("a Transformation with the name " + TWO + " should be returned for index 1",
                TWO, transformations.get(1).getName());

        Assert.assertEquals("a Transformation with the name " + THREE + " should be returned for index 2",
                THREE, transformations.get(2).getName());
    }

    @Test
    @Override
    public void testGetWithInvalidIndex() {

        Transformations transformations = new CompoundTransformations(mockIterable(), applyer);

        assertNullTransformation(transformations, 3);
        assertNullTransformation(transformations, -1);
    }

    @Test
    @Override
    public void testGetWithName() {

        final Transformations transformations = new CompoundTransformations(mockIterable(), applyer);

        Assert.assertEquals("a Transformation with the name " + ONE + " should be returned for the name " +
                ONE,
                ONE, transformations.get(ONE).getName());

        Assert.assertEquals("a Transformation with the name " + TWO + " should be returned for the name " +
                TWO,
                TWO, transformations.get(TWO).getName());

        Assert.assertEquals("a Transformation with the name " + THREE + " should be returned for the name " +
                THREE,
                THREE, transformations.get(THREE).getName());
    }

    @Test
    @Override
    public void testGetWithInvalidName() {

        Transformations transformations = new CompoundTransformations(mockIterable(), applyer);

        assertNullTransformation(transformations, "not a transformation");
    }

    @Test
    @Override
    public void testGetWithNullName() {

        Transformations transformations = new CompoundTransformations(mockIterable(), applyer);

        assertNullTransformation(transformations, null);
    }

    @Test
    @Override
    public void testIterator() {

        assertNotNull("an iterator should be returned", new CompoundTransformations(mockIterable(), applyer).iterator());
    }

    @Test
    public void testApplyer() {

        final String TEST_APPLY_STRING = "this is the apply string.";

        when(applyer.apply(anyString())).thenReturn(TEST_APPLY_STRING);

        Transformations transformations = new CompoundTransformations(mockIterable(), applyer);

        for (Transformation transformation : transformations) {

            assertEquals("all transformations should produce the same apply result.", TEST_APPLY_STRING,
                    transformation.apply("input string"));
        }
    }

    private Iterable<String> mockIterable() {

        return TestUtils.mockIterable(ONE, TWO, THREE);
    }
}
