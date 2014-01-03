package shiver.me.timbers.transform;

/**
 * This transformer should be implemented with the {@link Transformations} stored internally.
 */
public interface CompositeStringTransformer<T extends Transformation> extends CompositeTransformer<String, T>,
        StringTransformer<T> {
}
