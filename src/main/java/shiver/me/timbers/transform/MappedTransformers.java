package shiver.me.timbers.transform;

import java.util.Map;

/**
 * A collection of individual and possibly unrelated transformers.
 */
public class MappedTransformers<K, T extends Transformer> extends MappedContainer<K, T> implements Transformers<K, T> {

    public MappedTransformers(Map<K, T> transformerMap, T nullTransformer) {

        super(transformerMap, nullTransformer);
    }
}
