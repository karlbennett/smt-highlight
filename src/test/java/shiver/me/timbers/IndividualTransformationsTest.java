package shiver.me.timbers;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.TestUtils.ONE;
import static shiver.me.timbers.TestUtils.THREE;
import static shiver.me.timbers.TestUtils.TWO;
import static shiver.me.timbers.TestUtils.assertNullTransformation;
import static shiver.me.timbers.TestUtils.createEmptyIterable;

public class IndividualTransformationsTest implements TransformationsTestTemplate {

    private Iterable<Transformation> TransformationIterable;

    private Transformation transformationOne;
    private Transformation transformationTwo;
    private Transformation transformationThree;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {

        transformationOne = mock(Transformation.class);
        when(transformationOne.getName()).thenReturn(ONE);

        transformationTwo = mock(Transformation.class);
        when(transformationTwo.getName()).thenReturn(TWO);

        transformationThree = mock(Transformation.class);
        when(transformationThree.getName()).thenReturn(THREE);

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

        for (Transformation transformation : new IndividualTransformations(createEmptyIterable())) {

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

        assertEquals("Transformation " + ONE + " should be returned for index 0",
                transformationOne, transformations.get(0));

        assertEquals("Transformation " + TWO + " should be returned for index 1",
                transformationTwo, transformations.get(1));

        assertEquals("Transformation " + THREE + " should be returned for index 2",
                transformationThree, transformations.get(2));
    }

    @Test
    @Override
    public void testGetWithInvalidIndex() {

        Transformations transformations = new IndividualTransformations(TransformationIterable);

        assertNullTransformation(transformations, 3);
    }

    @Test
    @Override
    public void testGetWithName() {

        Transformations transformations = new IndividualTransformations(TransformationIterable);

        assertEquals("Transformation " + ONE + " should be returned for the name " + ONE,
                transformationOne, transformations.get(ONE));

        assertEquals("Transformation " + TWO + " should be returned for the name " + TWO,
                transformationTwo, transformations.get(TWO));

        assertEquals("Transformation " + THREE + " should be returned for the name " + THREE,
                transformationThree, transformations.get(THREE));
    }

    @Test
    @Override
    public void testGetWithInvalidName() {

        Transformations transformations = new IndividualTransformations(TransformationIterable);

        assertNullTransformation(transformations, "not a Transformation");
    }

    @Test
    @Override
    public void testGetWithNullName() {

        Transformations transformations = new IndividualTransformations(TransformationIterable);

        assertNullTransformation(transformations, null);
    }

    @Test
    @Override
    public void testIterator() {

        assertNotNull("an iterator should be returned", new IndividualTransformations(TransformationIterable).iterator());
    }
}
