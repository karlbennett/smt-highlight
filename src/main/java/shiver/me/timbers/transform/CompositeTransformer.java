package shiver.me.timbers.transform;

/**
 * This transformer should be implemented with the {@link Transformations} stored internally.
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
