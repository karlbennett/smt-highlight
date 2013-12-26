package shiver.me.timbers.transform;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

import static shiver.me.timbers.asserts.Asserts.argumentIsNullMessage;
import static shiver.me.timbers.asserts.Asserts.assertIsNotNull;

/**
 * This {@link Transformer} implementation provides some convenience logic for storing {@link Transformations} against a
 * {@link Transformer}.
 *
 * @author Karl Bennett
 */
public class WrappedTransformer implements Transformer {

    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private final Transformer transformer;
    private final Transformations transformations;

    /**
     * Create a new {@code WrappedTransformer} that will store a {@code Transformer} implementation and
     * {@code Transformations} so that they can be easily reapplied to different text.
     *
     * @param transformer     the transformer to use to apply the transformations.
     * @param transformations the transformations to apply.
     */
    public WrappedTransformer(Transformer transformer, Transformations transformations) {

        assertIsNotNull(argumentIsNullMessage("transformer"), transformer);
        assertIsNotNull(argumentIsNullMessage("transformations"), transformations);

        this.transformer = transformer;
        this.transformations = transformations;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String transform(InputStream stream, Transformations transformations) {

        return transformer.transform(stream, transformations);
    }

    /**
     * Read the text in the supplied {@code String} and Transformation using the supplied {@code Transformations}.
     *
     * @param text            the string containing the text to be transformed.
     * @param transformations the transformations to apply to the text.
     * @return the transformed text.
     */
    public String transform(String text, Transformations transformations) {

        return transform(new ByteArrayInputStream(text.getBytes(UTF_8)), transformations);
    }

    /**
     * Read the text from the supplied {@code InputStream} and apply the contained transformations.
     *
     * @param stream the input stream containing the text to be transformed.
     * @return the transformed text.
     */
    public String transform(InputStream stream) {

        return transform(stream, transformations);
    }

    /**
     * Read the text in the supplied {@code String} and apply the contained transformations.
     *
     * @param text the string containing the text to be transformed.
     * @return the transformed text.
     */
    public String transform(String text) {

        return transform(new ByteArrayInputStream(text.getBytes(UTF_8)));
    }
}