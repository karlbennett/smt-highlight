package shiver.me.timbers.transform;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static shiver.me.timbers.asserts.Asserts.argumentIsNullMessage;
import static shiver.me.timbers.asserts.Asserts.assertIsNotNull;
import static shiver.me.timbers.checks.Checks.isNotNull;

/**
 * A collection of individual and possibly unrelated transformers.
 */
public class MappedTransformers<K, T extends Transformer> implements Transformers<K, T> {

    private final List<T> transformerList;
    private final Map<K, T> transformerMap;
    private final T nullTransformer;

    public MappedTransformers(Map<K, T> transformerMap, T nullTransformer) {

        assertIsNotNull(argumentIsNullMessage("transformerMap"), transformerMap);
        assertIsNotNull(argumentIsNullMessage("nullTransformer"), nullTransformer);

        this.transformerList = new ArrayList<T>(transformerMap.values());
        this.transformerMap = transformerMap;
        this.nullTransformer = nullTransformer;
    }

    @Override
    public T get(int index) {

        return isValidIndex(index) ? transformerList.get(index) : nullTransformer;
    }

    private boolean isValidIndex(int index) {

        return 0 <= index && transformerList.size() > index;
    }

    @Override
    public T get(K key) {

        final T value = transformerMap.get(key);

        return isNotNull(value) ? value : nullTransformer;
    }

    @Override
    public Iterator<T> iterator() {

        return new LinkedList<T>(transformerList).iterator();
    }

    @Override
    public Collection<T> asCollection() {

        return new HashSet<T>(transformerList);
    }
}
