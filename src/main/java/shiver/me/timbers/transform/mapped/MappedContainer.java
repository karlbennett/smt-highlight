package shiver.me.timbers.transform.mapped;

import shiver.me.timbers.transform.Container;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static shiver.me.timbers.asserts.Asserts.argumentIsNullMessage;
import static shiver.me.timbers.asserts.Asserts.assertIsNotNull;
import static shiver.me.timbers.checks.Checks.isNotNull;

/**
 * A container that is populated from a map.
 */
public class MappedContainer<K, V> implements Container<K, V> {

    private final List<V> list;
    private final Map<K, V> map;
    private final V nullTransformer;

    public MappedContainer(V nullValue) {

        this(Collections.<K, V>emptyMap(), nullValue);
    }

    public MappedContainer(Map<K, V> map, V nullValue) {

        assertIsNotNull(argumentIsNullMessage("map"), map);
        assertIsNotNull(argumentIsNullMessage("nullValue"), nullValue);

        this.list = new ArrayList<V>(map.values());
        this.map = map;
        this.nullTransformer = nullValue;
    }

    @Override
    public V get(int index) {

        return isValidIndex(list, index) ? list.get(index) : nullTransformer;
    }

    public static boolean isValidIndex(List list, int index) {

        return 0 <= index && list.size() > index;
    }

    @Override
    public V get(K key) {

        final V value = map.get(key);

        return isNotNull(value) ? value : nullTransformer;
    }

    @Override
    public Iterator<V> iterator() {

        return new LinkedList<V>(list).iterator();
    }

    @Override
    public Collection<V> asCollection() {

        return new HashSet<V>(list);
    }
}
