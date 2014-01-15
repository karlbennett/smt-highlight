package shiver.me.timbers.transform;

import javax.activation.MimeType;

/**
 * Implement with the logic that will apply the {@link Transformations} to the text in the {@code input}.
 *
 * @author Karl Bennett
 */
public interface Transformer<I, T extends Transformation> {

    /**
     * @return the {@code MimeType} of the text that this transformer supports.
     */
    public MimeType getMimeType();

    /**
     * Read the text from the supplied {@code input} and transform using the supplied {@code Transformations}.
     *
     * @param input           the input containing the text to be transformed.
     * @param transformations the transformations to apply to the text.
     * @return the transformed text.
     */
    public String transform(I input, Transformations<T> transformations);
}
