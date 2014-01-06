package shiver.me.timbers.transform.composite;

import shiver.me.timbers.transform.Transformation;
import shiver.me.timbers.transform.Transformer;

/**
 * This transformer should be implemented with the {@link shiver.me.timbers.transform.Transformations} stored internally.
 */
public interface CompositeTransformer<I, T extends Transformation> extends Transformer<I, T> {

    /**
     * Apply the internally stored {@code Transformations} to the supplied {@code input}.
     *
     * @param input the input containing the text to be transformed.
     * @return the transformed text.
     */
    public String transform(I input);
}
