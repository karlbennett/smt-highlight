package shiver.me.timbers.transform.composite;

import shiver.me.timbers.transform.Transformation;
import shiver.me.timbers.transform.string.StringTransformer;

/**
 * This transformer should be implemented with the {@link shiver.me.timbers.transform.Transformations} stored internally.
 */
public interface CompositeStringTransformer<T extends Transformation> extends CompositeTransformer<String, T>,
        StringTransformer<T> {
}
