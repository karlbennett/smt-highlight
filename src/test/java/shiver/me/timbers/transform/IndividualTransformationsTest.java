package shiver.me.timbers.transform;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.transform.TestUtils.FIVE;
import static shiver.me.timbers.transform.TestUtils.FOUR;
import static shiver.me.timbers.transform.TestUtils.NAMES;
import static shiver.me.timbers.transform.TestUtils.ONE;
import static shiver.me.timbers.transform.TestUtils.SIX;
import static shiver.me.timbers.transform.TestUtils.THREE;
import static shiver.me.timbers.transform.TestUtils.TWO;
import static shiver.me.timbers.transform.TestUtils.assertNullTransformation;
import static shiver.me.timbers.transform.TestUtils.mockIterable;

public class IndividualTransformationsTest implements TransformationsTestTemplate {

    private List<Transformation> transformationList;

    @Test
    @Override
    @SuppressWarnings("unchecked")
    public void testCreate() {

        new IndividualTransformations(givenTheIterableIsMocked());
    }

    @Test
    @Override
    @SuppressWarnings("UnusedDeclaration")
    public void testCreateWithEmptyIterable() {

        for (Transformation transformation :
                new IndividualTransformations(Collections.<Transformation>emptySet())) {

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

        final Iterable<Transformation> iterable = givenTheIterableIsMocked();

        Transformations transformations = new IndividualTransformations(iterable);

        for (int i = 0; i < transformationList.size(); i++) {

            assertEquals("Transformation " + NAMES[i] + " should be returned for index " + i, transformationList.get(i),
                    transformations.get(i));
        }
    }

    @Test
    public void testGetWithIndexWithMultipleIterables() {

        givenTheTransformationsAreMocked();

        final Iterable<Transformation> iterableOne = mockIterable(transformationList.subList(0, 3));
        final Iterable<Transformation> iterableTwo = mockIterable(transformationList.subList(3, 6));

        @SuppressWarnings("unchecked")
        Transformations transformations = new IndividualTransformations(asList(iterableOne, iterableTwo));

        for (int i = 0; i < transformationList.size(); i++) {

            assertEquals("Transformation " + NAMES[i] + " should be returned for index " + i, transformationList.get(i),
                    transformations.get(i));
        }
    }

    @Test
    @Override
    public void testGetWithInvalidIndex() {

        Transformations transformations = new IndividualTransformations(givenTheIterableIsMocked());

        assertNullTransformation(transformations, transformationList.size());
    }

    @Test
    @Override
    public void testGetWithName() {

        final Iterable<Transformation> iterable = givenTheIterableIsMocked();

        Transformations transformations = new IndividualTransformations(iterable);

        for (int i = 0; i < transformationList.size(); i++) {

            assertEquals("Transformation " + NAMES[i] + " should be returned for index " + i, transformationList.get(i),
                    transformations.get(NAMES[i]));
        }
    }

    @Test
    public void testGetWithNameWithMultipleIterables() {

        givenTheTransformationsAreMocked();

        final Iterable<Transformation> iterableOne = mockIterable(transformationList.subList(0, 3));
        final Iterable<Transformation> iterableTwo = mockIterable(transformationList.subList(3, 6));

        @SuppressWarnings("unchecked")
        Transformations transformations = new IndividualTransformations(asList(iterableOne, iterableTwo));

        for (int i = 0; i < transformationList.size(); i++) {

            assertEquals("Transformation " + NAMES[i] + " should be returned for index " + i, transformationList.get(i),
                    transformations.get(NAMES[i]));
        }
    }

    @Test
    @Override
    public void testGetWithInvalidName() {

        Transformations transformations = new IndividualTransformations(givenTheIterableIsMocked());

        assertNullTransformation(transformations, "not a Transformation");
    }

    @Test
    @Override
    public void testGetWithNullName() {

        Transformations transformations = new IndividualTransformations(givenTheIterableIsMocked());

        assertNullTransformation(transformations, null);
    }

    @Test
    @Override
    @SuppressWarnings("unchecked")
    public void testIterator() {

        assertNotNull("an iterator should be returned",
                new IndividualTransformations(givenTheIterableIsMocked()).iterator());
    }

    private Iterable<Transformation> givenTheIterableIsMocked() {

        givenTheTransformationsAreMocked();

        return TestUtils.mockIterable(transformationList);
    }

    private void givenTheTransformationsAreMocked() {

        transformationList = mockTransformations(ONE, TWO, THREE, FOUR, FIVE, SIX);
    }

    private static List<Transformation> mockTransformations(String... names) {

        final List<Transformation> transformations = new ArrayList<Transformation>(names.length);

        Transformation transformation;
        for (String name : names) {

            transformation = mock(Transformation.class);
            when(transformation.getName()).thenReturn(name);

            transformations.add(transformation);
        }

        return transformations;
    }
}
