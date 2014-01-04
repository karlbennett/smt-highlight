package shiver.me.timbers.transform;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static shiver.me.timbers.transform.NullTransformation.NULL_TRANSFORMATION;
import static shiver.me.timbers.transform.TestUtils.assertCollection;
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

        new IndividualTransformations<Transformation>(null, NULL_TRANSFORMATION);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithIterableAndNullNullTransformer() {

        new IndividualTransformations<Transformation>(mockIterable(mockTransformationList()), null);
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
    public void testIteratorIsNotNull() {

        final List<Transformation> transformationList = mockTransformationList();

        assertNotNull("an iterator should be returned",
                new IndividualTransformations<Transformation>(mockIterable(transformationList), NULL_TRANSFORMATION)
                        .iterator());
    }

    @Test
    public void testAsCollection() {

        final List<Transformation> transformationList = mockTransformationList();

        final Iterable<Transformation> iterable = mockIterable(transformationList);

        assertCollection(transformationList,
                new IndividualTransformations<Transformation>(iterable, NULL_TRANSFORMATION).asCollection());
    }
}
