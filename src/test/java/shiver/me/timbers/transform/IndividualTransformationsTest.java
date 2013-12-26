package shiver.me.timbers.transform;

import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertNotNull;
import static shiver.me.timbers.transform.TestUtils.assertNoIterations;
import static shiver.me.timbers.transform.TestUtils.assertNullTransformation;
import static shiver.me.timbers.transform.TestUtils.assertTransformationsIndices;
import static shiver.me.timbers.transform.TestUtils.assertTransformationsNames;
import static shiver.me.timbers.transform.TestUtils.mockIterable;
import static shiver.me.timbers.transform.TestUtils.mockTransformationList;

public class IndividualTransformationsTest implements TransformationsTestTemplate {

    @Test
    @Override
    @SuppressWarnings("unchecked")
    public void testCreate() {

        final List<Transformation> transformationList = mockTransformationList();

        new IndividualTransformations(mockIterable(transformationList));
    }

    @Test
    @Override
    public void testCreateWithEmptyIterable() {

        assertNoIterations(new IndividualTransformations(Collections.<Transformation>emptySet()));
    }

    @Test
    public void testCreateWithDefaultConstructor() {

        assertNoIterations(new IndividualTransformations());
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

        final List<Transformation> transformationList = mockTransformationList();

        final Iterable<Transformation> iterable = mockIterable(transformationList);

        assertTransformationsIndices(transformationList,
                new IndividualTransformations(iterable));
    }

    @Test
    @Override
    public void testGetWithName() {

        final List<Transformation> transformationList = mockTransformationList();

        final Iterable<Transformation> iterable = mockIterable(transformationList);

        assertTransformationsNames(transformationList,
                new IndividualTransformations(iterable));
    }

    @Test
    @Override
    public void testGetWithInvalidIndex() {

        final List<Transformation> transformationList = mockTransformationList();

        Transformations transformations = new IndividualTransformations(mockIterable(transformationList));

        assertNullTransformation(transformations, -1);
        assertNullTransformation(transformations, transformationList.size());
    }

    @Test
    @Override
    public void testGetWithInvalidName() {

        final List<Transformation> transformationList = mockTransformationList();

        Transformations transformations = new IndividualTransformations(mockIterable(transformationList));

        assertNullTransformation(transformations, "not a Transformation");
    }

    @Test
    @Override
    public void testGetWithNullName() {

        final List<Transformation> transformationList = mockTransformationList();

        Transformations transformations = new IndividualTransformations(mockIterable(transformationList));

        assertNullTransformation(transformations, null);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetWithIndexWithMultipleIterables() {

        final List<Transformation> transformationList = mockTransformationList();

        final Iterable<Transformation> iterableOne = TestUtils.mockIterable(transformationList.subList(0, 5));
        final Iterable<Transformation> iterableTwo = TestUtils.mockIterable(transformationList.subList(5, 10));

        assertTransformationsIndices(transformationList,
                new IndividualTransformations(asList(iterableOne, iterableTwo)));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetWithNameWithMultipleIterables() {

        final List<Transformation> transformationList = mockTransformationList();

        final Iterable<Transformation> iterableOne = TestUtils.mockIterable(transformationList.subList(0, 5));
        final Iterable<Transformation> iterableTwo = TestUtils.mockIterable(transformationList.subList(5, 10));

        assertTransformationsNames(transformationList,
                new IndividualTransformations(asList(iterableOne, iterableTwo)));
    }

    @Test
    @Override
    @SuppressWarnings("unchecked")
    public void testIteratorIsNotNull() {

        final List<Transformation> transformationList = mockTransformationList();

        assertNotNull("an iterator should be returned",
                new IndividualTransformations(mockIterable(transformationList)).iterator());
    }
}
