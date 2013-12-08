package shiver.me.timbers.transform;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CompoundTransformationsTest implements TransformationsTestTemplate {

    private Iterable<String> iterable;
    private Applyer applyer;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {

        final Iterator<String> iterator = mock(Iterator.class);
        when(iterator.hasNext()).thenReturn(true, true, true, false);
        when(iterator.next()).thenReturn(TestUtils.ONE, TestUtils.TWO, TestUtils.THREE);

        iterable = mock(Iterable.class);
        when(iterable.iterator()).thenReturn(iterator);

        applyer = mock(Applyer.class);
    }

    @Test
    @Override
    public void testCreate() {

        new CompoundTransformations(iterable, mock(Applyer.class));
    }

    @Test
    @SuppressWarnings({"unchecked", "UnusedDeclaration"})
    @Override
    public void testCreateWithEmptyIterable() {

        for (Transformation transformation : new CompoundTransformations(TestUtils.createEmptyIterable(), applyer)) {

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

        new CompoundTransformations(iterable, null);
    }

    @Test
    @Override
    public void testGetWithIndex() {

        final Transformations transformations = new CompoundTransformations(iterable, applyer);

        Assert.assertEquals("a Transformation with the name " + TestUtils.ONE + " should be returned for index 0",
                TestUtils.ONE, transformations.get(0).getName());

        Assert.assertEquals("a Transformation with the name " + TestUtils.TWO + " should be returned for index 1",
                TestUtils.TWO, transformations.get(1).getName());

        Assert.assertEquals("a Transformation with the name " + TestUtils.THREE + " should be returned for index 2",
                TestUtils.THREE, transformations.get(2).getName());
    }

    @Test
    @Override
    public void testGetWithInvalidIndex() {

        Transformations transformations = new CompoundTransformations(iterable, applyer);

        TestUtils.assertNullTransformation(transformations, 3);
        TestUtils.assertNullTransformation(transformations, -1);
    }

    @Test
    @Override
    public void testGetWithName() {

        final Transformations transformations = new CompoundTransformations(iterable, applyer);

        Assert.assertEquals("a Transformation with the name " + TestUtils.ONE + " should be returned for the name " +
                TestUtils.ONE,
                TestUtils.ONE, transformations.get(TestUtils.ONE).getName());

        Assert.assertEquals("a Transformation with the name " + TestUtils.TWO + " should be returned for the name " +
                TestUtils.TWO,
                TestUtils.TWO, transformations.get(TestUtils.TWO).getName());

        Assert.assertEquals("a Transformation with the name " + TestUtils.THREE + " should be returned for the name " +
                TestUtils.THREE,
                TestUtils.THREE, transformations.get(TestUtils.THREE).getName());
    }

    @Test
    @Override
    public void testGetWithInvalidName() {

        Transformations transformations = new CompoundTransformations(iterable, applyer);

        TestUtils.assertNullTransformation(transformations, "not a transformation");
    }

    @Test
    @Override
    public void testGetWithNullName() {

        Transformations transformations = new CompoundTransformations(iterable, applyer);

        TestUtils.assertNullTransformation(transformations, null);
    }

    @Test
    @Override
    public void testIterator() {

        assertNotNull("an iterator should be returned", new CompoundTransformations(iterable, applyer).iterator());
    }

    @Test
    public void testApplyer() {

        final String TEST_APPLY_STRING = "this is the apply string.";

        when(applyer.apply(anyString())).thenReturn(TEST_APPLY_STRING);

        Transformations transformations = new CompoundTransformations(iterable, applyer);

        for (Transformation transformation : transformations) {

            assertEquals("all transformations should produce the same apply result.", TEST_APPLY_STRING,
                    transformation.apply("input string"));
        }
    }
}
