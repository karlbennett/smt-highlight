package shiver.me.timbers.transform;

import java.util.Iterator;

import static java.util.Arrays.asList;
import static shiver.me.timbers.asserts.Asserts.argumentIsNullMessage;
import static shiver.me.timbers.asserts.Asserts.assertIsNotNull;
import static shiver.me.timbers.checks.Checks.isNotNull;

/**
 * This iterable can be used to iterate over the elements within an array or collection of {@link Iterable}s.
 */
class CompactedIterable<T> implements Iterable<T> {

    private final Iterable<Iterable<T>> iterables;

    public CompactedIterable(Iterable<T>... iterables) {

        assertIsNotNull(argumentIsNullMessage("iterables"), iterables);

        this.iterables = asList(iterables);
    }

    public CompactedIterable(Iterable<Iterable<T>> iterables) {

        assertIsNotNull(argumentIsNullMessage("iterables"), iterables);

        this.iterables = iterables;
    }

    @Override
    public Iterator<T> iterator() {

        final Iterator<Iterable<T>> globalIterator = iterables.iterator();

        return new CompactedIterator<T>(globalIterator);
    }

    private static class CompactedIterator<T> implements Iterator<T> {

        private final Iterator<Iterable<T>> globalIterator;
        private Iterator<T> currentIterator;

        private CompactedIterator(Iterator<Iterable<T>> globalIterator) {

            this.globalIterator = globalIterator;

            if (globalIterator.hasNext()) {

                this.currentIterator = nextCurrentIterator();
            }
        }

        private Iterator<T> nextCurrentIterator() {

            Iterable<T> iterable = globalIterator.next();

            return isNotNull(iterable) ? iterable.iterator() : null;
        }

        @Override
        public boolean hasNext() {

            if (hasNextCurrentElement()) {

                return true;
            }

            while (globalIterator.hasNext() && noNextCurrentElement()) {

                currentIterator = nextCurrentIterator();
            }

            return hasNextCurrentElement();
        }

        private boolean hasNextCurrentElement() {

            return isNotNull(currentIterator) && currentIterator.hasNext();
        }

        private boolean noNextCurrentElement() {

            return !hasNextCurrentElement();
        }

        @Override
        public T next() {

            return currentIterator.next();
        }

        @Override
        public void remove() {

            throw new UnsupportedOperationException("cannot remove element because there is no way of knowing witch " +
                    "iterator is being mutated or if this is an iterator for an array.");
        }
    }
}
