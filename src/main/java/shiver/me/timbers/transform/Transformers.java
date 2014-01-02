package shiver.me.timbers.transform;

/**
 * A container of {@link Transformation}s.
 */
public interface Transformers<K, T extends Transformation> extends Container<K, CompositeTransformer<T>> {
}
