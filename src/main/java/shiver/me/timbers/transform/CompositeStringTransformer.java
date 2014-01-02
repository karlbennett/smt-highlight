package shiver.me.timbers.transform;

/**
 * This transformer should be implemented with the {@link Transformations} stored internal.
 */
public interface CompositeStringTransformer<T extends Transformation> extends StringTransformer<T> {

    /**
     * Apply the internally stored {@code Transformations} to the supplied {@code InputStream}.
     *
     * @param stream the input stream containing the text to be transformed.
     * @return the transformed text.
     */
    public String transform(String stream);
}
