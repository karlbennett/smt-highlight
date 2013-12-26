package shiver.me.timbers.transform;

import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class CompactedIterableTest {

    private static final Iterable[] ITERABLE_ARRAY_THAT_ONLY_CONTAINS_NULL = {null};
    @SuppressWarnings("unchecked")
    private static final Collection<Iterable<Object>> ITERABLE_COLLECTION_THAT_ONLY_CONTAINS_NULL =
            asList((Iterable<Object>) ITERABLE_ARRAY_THAT_ONLY_CONTAINS_NULL[0]);

    private static final Integer[] VALUES = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    private static final Integer[] VALUES_FOR_NULL_AT_THE_START = {VALUES[3], VALUES[4], VALUES[5], VALUES[6],
            VALUES[7], VALUES[8]};
    private static final Integer[] VALUES_FOR_NULL_IN_THE_MIDDLE = {VALUES[0], VALUES[1], VALUES[2], VALUES[6],
            VALUES[7], VALUES[8]};
    private static final Integer[] VALUES_FOR_NULL_AT_THE_END = {VALUES[0], VALUES[1], VALUES[2], VALUES[3], VALUES[4],
            VALUES[5]};

    private static final Iterable[] ITERABLE_ARRAY = {
            asList(VALUES[0], VALUES[1], VALUES[2]),
            asList(VALUES[3], VALUES[4], VALUES[5]),
            asList(VALUES[6], VALUES[7], VALUES[8])
    };
    private static final Iterable[] ITERABLE_ARRAY_WITH_NULL_AT_THE_START = {
            null,
            asList(VALUES[3], VALUES[4], VALUES[5]),
            asList(VALUES[6], VALUES[7], VALUES[8])
    };
    private static final Iterable[] ITERABLE_ARRAY_WITH_NULL_IN_THE_MIDDLE = {
            asList(VALUES[0], VALUES[1], VALUES[2]),
            null,
            asList(VALUES[6], VALUES[7], VALUES[8])
    };
    private static final Iterable[] ITERABLE_ARRAY_WITH_NULL_AT_THE_END = {
            asList(VALUES[0], VALUES[1], VALUES[2]),
            asList(VALUES[3], VALUES[4], VALUES[5]),
            null
    };

    @SuppressWarnings("unchecked")
    private static final Collection<Iterable<Integer>> ITERABLE_COLLECTION = asList(
            (Iterable<Integer>) ITERABLE_ARRAY[0],
            (Iterable<Integer>) ITERABLE_ARRAY[1],
            (Iterable<Integer>) ITERABLE_ARRAY[2]
    );
    @SuppressWarnings("unchecked")
    private static final Collection<Iterable<Integer>> ITERABLE_COLLECTION_WITH_NULL_AT_THE_START = asList(
            (Iterable<Integer>) ITERABLE_ARRAY_WITH_NULL_AT_THE_START[0],
            (Iterable<Integer>) ITERABLE_ARRAY_WITH_NULL_AT_THE_START[1],
            (Iterable<Integer>) ITERABLE_ARRAY_WITH_NULL_AT_THE_START[2]
    );
    @SuppressWarnings("unchecked")
    private static final Collection<Iterable<Integer>> ITERABLE_COLLECTION_WITH_NULL_IN_THE_MIDDLE = asList(
            (Iterable<Integer>) ITERABLE_ARRAY_WITH_NULL_IN_THE_MIDDLE[0],
            (Iterable<Integer>) ITERABLE_ARRAY_WITH_NULL_IN_THE_MIDDLE[1],
            (Iterable<Integer>) ITERABLE_ARRAY_WITH_NULL_IN_THE_MIDDLE[2]
    );
    @SuppressWarnings("unchecked")
    private static final Collection<Iterable<Integer>> ITERABLE_COLLECTION_WITH_NULL_AT_THE_END = asList(
            (Iterable<Integer>) ITERABLE_ARRAY_WITH_NULL_AT_THE_END[0],
            (Iterable<Integer>) ITERABLE_ARRAY_WITH_NULL_AT_THE_END[1],
            (Iterable<Integer>) ITERABLE_ARRAY_WITH_NULL_AT_THE_END[2]
    );

    @Test
    @SuppressWarnings("unchecked")
    public void testCreateWithArray() {

        new CompactedIterable(new Iterable[3]);
    }

    @Test(expected = AssertionError.class)
    @SuppressWarnings("unchecked")
    public void testCreateWithNullArray() {

        new CompactedIterable((Iterable[]) null);
    }

    @Test
    public void testCreateWithCollection() {

        new CompactedIterable<Object>(new HashSet<Iterable<Object>>(0));
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullCollection() {

        new CompactedIterable<Object>((Collection<Iterable<Object>>) null);
    }

    @Test
    @SuppressWarnings({"unchecked", "RedundantArrayCreation"})
    public void testIteratorWithEmptyArrayProducesIterator() {

        assertIteratorNotNull(new CompactedIterable(new Iterable[0]));
    }

    @Test
    @SuppressWarnings({"unchecked", "RedundantArrayCreation"})
    public void testIteratorWithArrayThatOnlyContainsNullProducesIterator() {

        assertIteratorNotNull(new CompactedIterable(ITERABLE_ARRAY_THAT_ONLY_CONTAINS_NULL));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testIteratorWithArrayProducesIterator() {

        assertIteratorNotNull(new CompactedIterable<Integer>(ITERABLE_ARRAY));
    }

    @Test
    public void testIteratorWithEmptyCollectionProducesIterator() {

        assertIteratorNotNull(new CompactedIterable<Object>(new HashSet<Iterable<Object>>(0)));
    }

    @Test
    @SuppressWarnings({"unchecked", "RedundantArrayCreation"})
    public void testIteratorWithCollectionThatOnlyContainsNullProducesIterator() {

        assertIteratorNotNull(new CompactedIterable(ITERABLE_COLLECTION_THAT_ONLY_CONTAINS_NULL));
    }

    @Test
    public void testIteratorWithCollectionProducesIterator() {

        assertIteratorNotNull(new CompactedIterable<Integer>(ITERABLE_COLLECTION));
    }

    @Test
    @SuppressWarnings({"unchecked", "RedundantArrayCreation"})
    public void testIteratorWithEmptyArrayDoesNotIterate() {

        assertNoIterations(new CompactedIterable(new Iterable[0]));
    }

    @Test
    @SuppressWarnings({"unchecked", "RedundantArrayCreation"})
    public void testIteratorWithArrayThatOnlyContainsNullDoesNotIterate() {

        assertNoIterations(new CompactedIterable(ITERABLE_ARRAY_THAT_ONLY_CONTAINS_NULL));
    }

    @Test
    public void testIteratorWithEmptyCollectionDoesNotIterate() {

        assertNoIterations(new CompactedIterable<Object>(new HashSet<Iterable<Object>>(0)));
    }

    @Test
    public void testIteratorWithCollectionThatOnlyContainsNullDoesNotIterate() {

        assertNoIterations(new CompactedIterable<Object>(ITERABLE_COLLECTION_THAT_ONLY_CONTAINS_NULL));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testIteratorWithArrayIteratesCorrectly() {

        assertCorrectIterations(VALUES, new CompactedIterable<Integer>(ITERABLE_ARRAY));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testIteratorWithArrayIteratesCorrectlyWithNullIterableAtTheStart() {

        assertCorrectIterations(VALUES_FOR_NULL_AT_THE_START,
                new CompactedIterable<Integer>(ITERABLE_ARRAY_WITH_NULL_AT_THE_START));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testIteratorWithArrayIteratesCorrectlyWithNullIterableInTheMiddle() {

        assertCorrectIterations(VALUES_FOR_NULL_IN_THE_MIDDLE,
                new CompactedIterable<Integer>(ITERABLE_ARRAY_WITH_NULL_IN_THE_MIDDLE));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testIteratorWithArrayIteratesCorrectlyWithNullIterableAtTheEnd() {

        assertCorrectIterations(VALUES_FOR_NULL_AT_THE_END,
                new CompactedIterable<Integer>(ITERABLE_ARRAY_WITH_NULL_AT_THE_END));
    }

    @Test(expected = UnsupportedOperationException.class)
    @SuppressWarnings("unchecked")
    public void testIteratorRemoveIsUnsupportedForArray() {

        new CompactedIterable<Integer>(ITERABLE_ARRAY).iterator().remove();
    }

    @Test(expected = UnsupportedOperationException.class)
    @SuppressWarnings("unchecked")
    public void testIteratorRemoveIsUnsupportedForCollection() {

        new CompactedIterable<Integer>(ITERABLE_COLLECTION).iterator().remove();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testIteratorWithCollectionIteratesCorrectly() {

        assertCorrectIterations(VALUES, new CompactedIterable<Integer>(ITERABLE_ARRAY));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testIteratorWithCollectionIteratesCorrectlyWithNullIterableAtTheStart() {

        assertCorrectIterations(VALUES_FOR_NULL_AT_THE_START,
                new CompactedIterable<Integer>(ITERABLE_COLLECTION_WITH_NULL_AT_THE_START));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testIteratorWithCollectionIteratesCorrectlyWithNullIterableInTheMiddle() {

        assertCorrectIterations(VALUES_FOR_NULL_IN_THE_MIDDLE,
                new CompactedIterable<Integer>(ITERABLE_COLLECTION_WITH_NULL_IN_THE_MIDDLE));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testIteratorWithCollectionIteratesCorrectlyWithNullIterableAtTheEnd() {

        assertCorrectIterations(VALUES_FOR_NULL_AT_THE_END,
                new CompactedIterable<Integer>(ITERABLE_COLLECTION_WITH_NULL_AT_THE_END));
    }

    private static void assertIteratorNotNull(Iterable iterable) {

        assertNotNull("an iterator should be produced.", iterable.iterator());
    }

    @SuppressWarnings("UnusedDeclaration")
    private static void assertNoIterations(Iterable iterable) {

        for (Object object : iterable) {

            fail("there should be no iterations for an empty array.");
        }
    }

    public void assertCorrectIterations(Integer[] expectedValues, Iterable<Integer> iterable) {

        int index = 0;
        for (Integer integer : iterable) {

            assertEquals("the iterated value should be correct for index " + index, expectedValues[index++], integer);
        }

        assertEquals("the correct number of iterations should have occurred.", expectedValues.length, index);
    }
}
