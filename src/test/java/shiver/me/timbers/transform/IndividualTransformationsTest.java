package shiver.me.timbers.transform;

import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.transform.NullTransformation.NULL_TRANSFORMATION;
import static shiver.me.timbers.transform.TestUtils.assertNoIterations;
import static shiver.me.timbers.transform.TestUtils.assertNullTransformation;
import static shiver.me.timbers.transform.TestUtils.assertTransformationsIndices;
import static shiver.me.timbers.transform.TestUtils.assertTransformationsNames;
import static shiver.me.timbers.transform.TestUtils.mockIterable;
import static shiver.me.timbers.transform.TestUtils.mockTransformationList;

public class IndividualTransformationsTest {

    @Test
    public void testCreate() {

        new IndividualTransformations<Transformation>(mockIterable(mockTransformationList()), NULL_TRANSFORMATION);
    }

    @Test
    public void testCreateWithEmptyIterable() {

        assertNoIterations(
                new IndividualTransformations<Transformation>(Collections.<Transformation>emptySet(), NULL_TRANSFORMATION));
    }

    @Test
    public void testCreateWithNullTransformer() {

        assertNoIterations(new IndividualTransformations<Transformation>(NULL_TRANSFORMATION));
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullIterable() {

        new IndividualTransformations<Transformation>((Iterable<Transformation>) null, NULL_TRANSFORMATION);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithIterableAndNullNullTransformer() {

        new IndividualTransformations<Transformation>(mockIterable(mockTransformationList()), null);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testCreateWithCollection() {

        new IndividualTransformations<Transformation>((Collection<Iterable<Transformation>>) mock(Collection.class),
                NULL_TRANSFORMATION);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullCollection() {

        new IndividualTransformations<Transformation>((Collection<Iterable<Transformation>>) null, NULL_TRANSFORMATION);
    }

    @Test(expected = AssertionError.class)
    @SuppressWarnings("unchecked")
    public void testCreateWithCollectionAndNullNullTransformer() {

        new IndividualTransformations<Transformation>((Collection<Iterable<Transformation>>) mock(Collection.class),
                null);
    }

    @Test
    public void testGetWithIndex() {

        final List<Transformation> transformationList = mockTransformationList();

        final Iterable<Transformation> iterable = mockIterable(transformationList);

        assertTransformationsIndices(transformationList,
                new IndividualTransformations<Transformation>(iterable, NULL_TRANSFORMATION));
    }

    @Test
    public void testGetWithName() {

        final List<Transformation> transformationList = mockTransformationList();

        final Iterable<Transformation> iterable = mockIterable(transformationList);

        assertTransformationsNames(transformationList,
                new IndividualTransformations<Transformation>(iterable, NULL_TRANSFORMATION));
    }

    @Test
    public void testGetWithInvalidIndex() {

        final List<Transformation> transformationList = mockTransformationList();

        Transformations<Transformation> transformations =
                new IndividualTransformations<Transformation>(mockIterable(transformationList), NULL_TRANSFORMATION);

        assertNullTransformation(transformations, -1);
        assertNullTransformation(transformations, transformationList.size());
    }

    @Test
    public void testGetWithInvalidName() {

        final List<Transformation> transformationList = mockTransformationList();

        Transformations<Transformation> transformations =
                new IndividualTransformations<Transformation>(mockIterable(transformationList), NULL_TRANSFORMATION);

        assertNullTransformation(transformations, "not a Transformation");
    }

    @Test
    public void testGetWithNullName() {

        final List<Transformation> transformationList = mockTransformationList();

        Transformations<Transformation> transformations =
                new IndividualTransformations<Transformation>(mockIterable(transformationList), NULL_TRANSFORMATION);

        assertNullTransformation(transformations, null);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetWithIndexWithMultipleIterables() {

        final List<Transformation> transformationList = mockTransformationList();

        final Iterable<Transformation> iterableOne = TestUtils.mockIterable(transformationList.subList(0, 5));
        final Iterable<Transformation> iterableTwo = TestUtils.mockIterable(transformationList.subList(5, 10));

        assertTransformationsIndices(transformationList,
                new IndividualTransformations<Transformation>(asList(iterableOne, iterableTwo), NULL_TRANSFORMATION));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetWithNameWithMultipleIterables() {

        final List<Transformation> transformationList = mockTransformationList();

        final Iterable<Transformation> iterableOne = TestUtils.mockIterable(transformationList.subList(0, 5));
        final Iterable<Transformation> iterableTwo = TestUtils.mockIterable(transformationList.subList(5, 10));

        assertTransformationsNames(transformationList,
                new IndividualTransformations<Transformation>(asList(iterableOne, iterableTwo), NULL_TRANSFORMATION));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testIteratorIsNotNull() {

        final List<Transformation> transformationList = mockTransformationList();

        assertNotNull("an iterator should be returned",
                new IndividualTransformations<Transformation>(mockIterable(transformationList), NULL_TRANSFORMATION)
                        .iterator());
    }
}
