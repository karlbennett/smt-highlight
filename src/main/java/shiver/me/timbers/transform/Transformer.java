package shiver.me.timbers.transform;

import java.io.InputStream;

/**
 * Implement with the logic that will apply the {@link Transformations} to the text in the {@code InputStream}.
 *
 * @author Karl Bennett
 */
public interface Transformer<T extends Transformation> {

    /**
     * Read the text from the supplied {@code InputStream} and transform using the supplied {@code Transformations}.
     *
     * @param stream          the input stream containing the text to be transformed.
     * @param transformations the transformations to apply to the text.
     * @return the transformed text.
     */
    public String transform(InputStream stream, Transformations<T> transformations);
}
