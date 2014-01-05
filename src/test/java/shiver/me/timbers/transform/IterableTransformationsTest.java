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

        assertTransformationsIndices(transformationList,
                new IterableTransformations<Transformation>(iterable, NULL_TRANSFORMATION));
    }

    @Test
    public void testGetWithName() {

        final List<Transformation> transformationList = mockTransformationList();

        final Iterable<Transformation> iterable = mockIterable(transformationList);

        assertTransformationsNames(transformationList,
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
    public void testIteratorIsNotNull() {

        final List<Transformation> transformationList = mockTransformationList();

        assertNotNull("an iterator should be returned",
                new IterableTransformations<Transformation>(mockIterable(transformationList), NULL_TRANSFORMATION)
                        .iterator());
    }

    @Test
    public void testAsCollection() {

        final List<Transformation> transformationList = mockTransformationList();

        final Iterable<Transformation> iterable = mockIterable(transformationList);

        assertCollection(transformationList,
                new IterableTransformations<Transformation>(iterable, NULL_TRANSFORMATION).asCollection());
    }
}
