package shiver.me.timbers.transform;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.transform.TestUtils.ONE;
import static shiver.me.timbers.transform.TestUtils.THREE;
import static shiver.me.timbers.transform.TestUtils.TWO;
import static shiver.me.timbers.transform.TestUtils.assertNullTransformation;

public class IndividualTransformationsTest implements TransformationsTestTemplate {

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
    }

    @Test
    @Override
    @SuppressWarnings("unchecked")
    public void testCreate() {

        new IndividualTransformations(mockIterable());
    }

    @Test
    @Override
    @SuppressWarnings("UnusedDeclaration")
    public void testCreateWithEmptyIterable() {

        for (Transformation transformation :
                new IndividualTransformations(TestUtils.<Transformation>createEmptyIterable())) {

            fail("an empty " + Transformations.class.getSimpleName() + " should not iterate.");
        }
    }

    @Test
    @SuppressWarnings("UnusedDeclaration")
    public void testCreateWithDefaultConstructor() {

        for (Transformation transformation : new IndividualTransformations()) {

            fail("a " + Transformations.class.getSimpleName() +
                    " created with the default constructor should not iterate.");
        }
    }

    @Test(expected = AssertionError.class)
    @Override
    public void testCreateWithNullIterable() {

        new IndividualTransformations((Iterable<Transformation>) null);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullIterableCollection() {

        new IndividualTransformations((Collection<Iterable<Transformation>>) null);
    }

    @Test
    @Override
    public void testGetWithIndex() {

        Transformations transformations = new IndividualTransformations(mockIterable());

        assertEquals("Transformation " + ONE + " should be returned for index 0", transformationOne,
                transformations.get(0));

        assertEquals("Transformation " + TWO + " should be returned for index 1", transformationTwo,
                transformations.get(1));

        assertEquals("Transformation " + THREE + " should be returned for index 2", transformationThree,
                transformations.get(2));
    }

    @Test
    @Override
    public void testGetWithInvalidIndex() {

        Transformations transformations = new IndividualTransformations(mockIterable());

        assertNullTransformation(transformations, 3);
    }

    @Test
    @Override
    public void testGetWithName() {

        Transformations transformations = new IndividualTransformations(mockIterable());

        assertEquals("Transformation " + ONE + " should be returned for the name " + ONE, transformationOne,
                transformations.get(ONE));

        assertEquals("Transformation " + TWO + " should be returned for the name " + TWO, transformationTwo,
                transformations.get(TWO));

        assertEquals("Transformation " + THREE + " should be returned for the name " + THREE, transformationThree,
                transformations.get(THREE));
    }

    @Test
    @Override
    public void testGetWithInvalidName() {

        Transformations transformations = new IndividualTransformations(mockIterable());

        assertNullTransformation(transformations, "not a Transformation");
    }

    @Test
    @Override
    public void testGetWithNullName() {

        Transformations transformations = new IndividualTransformations(mockIterable());

        assertNullTransformation(transformations, null);
    }

    @Test
    @Override
    @SuppressWarnings("unchecked")
    public void testIterator() {

        assertNotNull("an iterator should be returned",
                new IndividualTransformations(mockIterable()).iterator());
    }

    private Iterable<Transformation> mockIterable() {

        return TestUtils.mockIterable(transformationOne, transformationTwo, transformationThree);
    }
}
