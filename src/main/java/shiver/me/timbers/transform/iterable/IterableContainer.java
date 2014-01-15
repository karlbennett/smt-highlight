package shiver.me.timbers.transform.iterable;

import shiver.me.timbers.transform.Container;
import shiver.me.timbers.transform.mapped.MappedContainer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static shiver.me.timbers.asserts.Asserts.argumentIsNullMessage;
import static shiver.me.timbers.asserts.Asserts.assertIsNotNull;

/**
 * A container that contains an object that has a unique identifier that can be used for it's key lookup.
 */
public class IterableContainer<K, V> extends MappedContainer<K, V> implements Container<K, V> {

    /**
     * @param identifierReader the reader that will read each values identifier.
     * @param nullValue        the value that will be returned for any failed lookup.
     */
    public IterableContainer(IdentifierReader<K, V> identifierReader, V nullValue) {

        this(Collections.<V>emptySet(), identifierReader, nullValue);
    }

    /**
     * @param values           the values that wll be stored within the container fo later lookup.
     * @param identifierReader the reader that will read each values identifier.
     * @param nullValue        the value that will be returned for any failed lookup.
     */
    public IterableContainer(Iterable<V> values, IdentifierReader<K, V> identifierReader, V nullValue) {

        super(asMap(values, identifierReader), nullValue);
    }

    private static <K, V> Map<K, V> asMap(Iterable<V> values, IdentifierReader<K, V> identifierReader) {

        assertIsNotNull(argumentIsNullMessage("values"), values);

        final Map<K, V> map = new HashMap<K, V>();

        for (V transformation : values) {

            map.put(identifierReader.readIdentifier(transformation), transformation);
        }

        return map;
    }

    /**
     * Implement this interface with the call to the supplied value identifying getter.
     */
    protected static interface IdentifierReader<K, V> {

        /**
         * Implement this method with the logic for reading the supplied values identifier.
         *
         * @param value the value to read the identifier from.
         * @return the values identifier.
         */
        public K readIdentifier(V value);
    }
}
