package shiver.me.timbers.transform;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IndividualTransformationsTest implements TransformationsTestTemplate {

    private Iterable<Transformation> TransformationIterable;

    private Transformation transformationOne;
    private Transformation transformationTwo;
    private Transformation transformationThree;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {

        transformationOne = mock(Transformation.class);
        when(transformationOne.getName()).thenReturn(TestUtils.ONE);

        transformationTwo = mock(Transformation.class);
        when(transformationTwo.getName()).thenReturn(TestUtils.TWO);

        transformationThree = mock(Transformation.class);
        when(transformationThree.getName()).thenReturn(TestUtils.THREE);

        Iterator<Transformation> iterator = mock(Iterator.class);
        when(iterator.hasNext()).thenReturn(true, true, true, false);
        when(iterator.next()).thenReturn(transformationOne, transformationTwo, transformationThree);

        TransformationIterable = mock(Iterable.class);
        when(TransformationIterable.iterator()).thenReturn(iterator);
    }

    @Test
    @Override
    public void testCreate() {

        new IndividualTransformations(TransformationIterable);
    }

    @Test
    @Override
    @SuppressWarnings({"unchecked", "UnusedDeclaration"})
    public void testCreateWithEmptyIterable() {

        for (Transformation transformation : new IndividualTransformations(TestUtils.createEmptyIterable())) {

            fail("an empty " + Transformations.class.getSimpleName() + " should not iterate.");
        }
    }

    @Test(expected = AssertionError.class)
    @Override
    public void testCreateWithNullIterable() {

        new IndividualTransformations(null);
    }

    @Test
    @Override
    public void testGetWithIndex() {

        Transformations transformations = new IndividualTransformations(TransformationIterable);

        Assert.assertEquals("Transformation " + TestUtils.ONE + " should be returned for index 0",
                transformationOne, transformations.get(0));

        Assert.assertEquals("Transformation " + TestUtils.TWO + " should be returned for index 1",
                transformationTwo, transformations.get(1));

        Assert.assertEquals("Transformation " + TestUtils.THREE + " should be returned for index 2",
                transformationThree, transformations.get(2));
    }

    @Test
    @Override
    public void testGetWithInvalidIndex() {

        Transformations transformations = new IndividualTransformations(TransformationIterable);

        TestUtils.assertNullTransformation(transformations, 3);
    }

    @Test
    @Override
    public void testGetWithName() {

        Transformations transformations = new IndividualTransformations(TransformationIterable);

        Assert.assertEquals("Transformation " + TestUtils.ONE + " should be returned for the name " + TestUtils.ONE,
                transformationOne, transformations.get(TestUtils.ONE));

        Assert.assertEquals("Transformation " + TestUtils.TWO + " should be returned for the name " + TestUtils.TWO,
                transformationTwo, transformations.get(TestUtils.TWO));

        Assert.assertEquals("Transformation " + TestUtils.THREE + " should be returned for the name " + TestUtils.THREE,
                transformationThree, transformations.get(TestUtils.THREE));
    }

    @Test
    @Override
    public void testGetWithInvalidName() {

        Transformations transformations = new IndividualTransformations(TransformationIterable);

        TestUtils.assertNullTransformation(transformations, "not a Transformation");
    }

    @Test
    @Override
    public void testGetWithNullName() {

        Transformations transformations = new IndividualTransformations(TransformationIterable);

        TestUtils.assertNullTransformation(transformations, null);
    }

    @Test
    @Override
    public void testIterator() {

        assertNotNull("an iterator should be returned", new IndividualTransformations(TransformationIterable).iterator());
    }
}
