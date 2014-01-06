package shiver.me.timbers.transform;

import org.junit.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static shiver.me.timbers.transform.NullTransformation.NULL_TRANSFORMATION;
import static shiver.me.timbers.transform.TestUtils.assertCorrectIndices;
import static shiver.me.timbers.transform.TestUtils.assertCorrectNames;
import static shiver.me.timbers.transform.TestUtils.assertIterableEquals;
import static shiver.me.timbers.transform.TestUtils.assertIteratorEquals;
import static shiver.me.timbers.transform.TestUtils.assertNoIterations;
import static shiver.me.timbers.transform.TestUtils.assertNullTransformation;
import static shiver.me.timbers.transform.TestUtils.mockIterable;
import static shiver.me.timbers.transform.TestUtils.mockTransformationList;
import static shiver.me.timbers.transform.TestUtils.mockTransformationMap;

public class IterableTransformationsTest {

    @Test
    public void testCreate() {

        new IterableTransformations<Transformation>(mockIterable(mockTransformationList()), NULL_TRANSFORMATION);
    }

    @Test
    public void testCreateWithEmptyIterable() {

        assertNoIterations(
                new IterableTransformations<Transformation>(Collections.<Transformation>emptySet(), NULL_TRANSFORMATION));
    }

    @Test
    public void testCreateWithNullTransformer() {

        assertNoIterations(new IterableTransformations<Transformation>(NULL_TRANSFORMATION));
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullIterable() {

        new IterableTransformations<Transformation>(null, NULL_TRANSFORMATION);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithIterableAndNullNullTransformer() {

        new IterableTransformations<Transformation>(mockIterable(mockTransformationList()), null);
    }

    @Test
    public void testGetWithIndex() {

        final List<Transformation> transformationList = mockTransformationList();

        final Iterable<Transformation> iterable = mockIterable(transformationList);

        assertCorrectIndices(transformationList,
                new IterableTransformations<Transformation>(iterable, NULL_TRANSFORMATION));
    }

    @Test
    public void testGetWithName() {

        final Map<String, Transformation> transformationMap = mockTransformationMap();

        final Iterable<Transformation> iterable =
                mockIterable(new LinkedList<Transformation>(transformationMap.values()));

        assertCorrectNames(transformationMap,
                new IterableTransformations<Transformation>(iterable, NULL_TRANSFORMATION));
    }

    @Test
    public void testGetWithInvalidIndex() {

        final List<Transformation> transformationList = mockTransformationList();

        Transformations<Transformation> transformations =
                new IterableTransformations<Transformation>(mockIterable(transformationList), NULL_TRANSFORMATION);

        assertNullTransformation(transformations, -1);
        assertNullTransformation(transformations, transformationList.size());
    }

    @Test
    public void testGetWithInvalidName() {

        final List<Transformation> transformationList = mockTransformationList();

        Transformations<Transformation> transformations =
                new IterableTransformations<Transformation>(mockIterable(transformationList), NULL_TRANSFORMATION);

        assertNullTransformation(transformations, "not a Transformation");
    }

    @Test
    public void testGetWithNullName() {

        final List<Transformation> transformationList = mockTransformationList();

        Transformations<Transformation> transformations =
                new IterableTransformations<Transformation>(mockIterable(transformationList), NULL_TRANSFORMATION);

        assertNullTransformation(transformations, null);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testIterator() {

        final List<Transformation> transformationList = mockTransformationList();

        assertIteratorEquals(transformationList.iterator(),
                new IterableTransformations<Transformation>(mockIterable(transformationList), NULL_TRANSFORMATION)
                        .iterator());
    }

    @Test
    public void testAsCollection() {

        final List<Transformation> transformationList = mockTransformationList();

        final Iterable<Transformation> iterable = mockIterable(transformationList);

        assertIterableEquals(transformationList,
                new IterableTransformations<Transformation>(iterable, NULL_TRANSFORMATION).asCollection());
    }
}
