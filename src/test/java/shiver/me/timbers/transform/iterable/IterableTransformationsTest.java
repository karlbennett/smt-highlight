package shiver.me.timbers.transform.iterable;

import org.junit.Test;
import shiver.me.timbers.transform.Transformation;
import shiver.me.timbers.transform.Transformations;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static shiver.me.timbers.transform.NullTransformation.NULL_TRANSFORMATION;
import static shiver.me.timbers.transform.TestUtils.assertCorrectIndices;
import static shiver.me.timbers.transform.TestUtils.assertIterableEquals;
import static shiver.me.timbers.transform.TestUtils.assertIteratorEquals;
import static shiver.me.timbers.transform.TransformationTestUtils.assertCorrectNames;
import static shiver.me.timbers.transform.TransformationTestUtils.assertNoIterations;
import static shiver.me.timbers.transform.TransformationTestUtils.assertNullTransformation;
import static shiver.me.timbers.transform.TransformationTestUtils.mockTransformationList;
import static shiver.me.timbers.transform.TransformationTestUtils.mockTransformationMap;

public class IterableTransformationsTest {

    @Test
    public void testCreateWithNullTransformer() {

        assertNoIterations(new IterableTransformations<Transformation>(NULL_TRANSFORMATION));
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullNullTransformer() {

        assertNoIterations(new IterableTransformations<Transformation>(null));
    }

    @Test
    public void testCreateWithIterableAndNullTransformation() {

        new IterableTransformations<Transformation>(mockTransformationList(), NULL_TRANSFORMATION);
    }

    @Test
    public void testCreateWithEmptyIterableAndNullTransformation() {

        assertNoIterations(
                new IterableTransformations<Transformation>(Collections.<Transformation>emptySet(), NULL_TRANSFORMATION));
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullIterableAndNullTransformation() {

        new IterableTransformations<Transformation>(null, NULL_TRANSFORMATION);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithIterableAndNullNullTransformer() {

        new IterableTransformations<Transformation>(mockTransformationList(), null);
    }

    @Test
    public void testGetWithIndex() {

        final List<Transformation> transformationList = mockTransformationList();

        assertCorrectIndices(transformationList,
                new IterableTransformations<Transformation>(transformationList, NULL_TRANSFORMATION));
    }

    @Test
    public void testGetWithName() {

        final Map<String, Transformation> transformationMap = mockTransformationMap();

        assertCorrectNames(transformationMap,
                new IterableTransformations<Transformation>(transformationMap.values(), NULL_TRANSFORMATION));
    }

    @Test
    public void testGetWithInvalidIndex() {

        final List<Transformation> transformationList = mockTransformationList();

        Transformations<Transformation> transformations =
                new IterableTransformations<Transformation>(transformationList, NULL_TRANSFORMATION);

        assertNullTransformation(transformations, -1);
        assertNullTransformation(transformations, transformationList.size());
    }

    @Test
    public void testGetWithInvalidName() {

        final List<Transformation> transformationList = mockTransformationList();

        Transformations<Transformation> transformations =
                new IterableTransformations<Transformation>(transformationList, NULL_TRANSFORMATION);

        assertNullTransformation(transformations, "not a Transformation");
    }

    @Test
    public void testGetWithNullName() {

        final List<Transformation> transformationList = mockTransformationList();

        Transformations<Transformation> transformations =
                new IterableTransformations<Transformation>(transformationList, NULL_TRANSFORMATION);

        assertNullTransformation(transformations, null);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testIterator() {

        final List<Transformation> transformationList = mockTransformationList();

        assertIteratorEquals(transformationList.iterator(),
                new IterableTransformations<Transformation>(transformationList, NULL_TRANSFORMATION)
                        .iterator());
    }

    @Test
    public void testAsCollection() {

        final List<Transformation> transformationList = mockTransformationList();

        assertIterableEquals(transformationList,
                new IterableTransformations<Transformation>(transformationList, NULL_TRANSFORMATION).asCollection());
    }
}
