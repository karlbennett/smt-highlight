package shiver.me.timbers.transform;

import java.io.InputStream;

/**
 * This transformer should be implemented with the {@link Transformations} stored internal.
 */
public interface CompositeStreamTransformer<T extends Transformation> extends StreamTransformer<T> {

    /**
     * Apply the internally stored {@code Transformations} to the supplied {@code InputStream}.
     *
     * @param stream the input stream containing the text to be transformed.
     * @return the transformed text.
     */
    public String transform(InputStream stream);
}
