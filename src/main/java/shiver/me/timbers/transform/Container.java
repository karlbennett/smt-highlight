package shiver.me.timbers.transform;

import java.util.Collection;

/**
 * An iterable collection tht can have it's elements retrieved with a index or key.
 *
 * @author Karl Bennett
 */
public interface Container<K, V> extends Iterable<V> {

    /**
     * @return the element at the supplied index.
     */
    public V get(int index);

    /**
     * @return the element with the supplied key.
     */
    public V get(K key);

    /**
     * @return a collection containing all the containrs values.
     */
    public Collection<V> asCollection();
}
