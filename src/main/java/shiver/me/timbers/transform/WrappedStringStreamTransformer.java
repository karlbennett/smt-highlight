package shiver.me.timbers.transform;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;

import static shiver.me.timbers.asserts.Asserts.argumentIsNullMessage;
import static shiver.me.timbers.asserts.Asserts.assertIsNotNull;
import static shiver.me.timbers.checks.Checks.isNotNull;

/**
 * This {@link CompositeStreamTransformer} implementation provides some convenience logic for storing
 * {@link Transformations} against a {@link StringTransformer} that can then be used as a {@link StreamTransformer}.
 */
public class WrappedStringStreamTransformer<T extends Transformation> implements CompositeStreamTransformer<T> {

    private static final int STREAM_COPY_BUFFER_SIZE = 1024 * 4; // This value was taken from commons-io.

    private final StringTransformer<T> transformer;
    private final Transformations<T> transformations;

    /**
     * Create a new {@code WrappedStreamTransformer} that will store a {@code StreamTransformer} implementation and
     * {@code Transformations} so that they can be easily reapplied to different text.
     *
     * @param transformer     the transformer to use to apply the transformations.
     * @param transformations the transformations to apply.
     */
    public WrappedStringStreamTransformer(StringTransformer<T> transformer, Transformations<T> transformations) {

        assertIsNotNull(argumentIsNullMessage("transformer"), transformer);
        assertIsNotNull(argumentIsNullMessage("transformations"), transformations);

        this.transformer = transformer;
        this.transformations = transformations;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String transform(InputStream stream, Transformations<T> transformations) {

        return transformer.transform(isNotNull(stream) ? toString(stream) : null, transformations);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String transform(InputStream stream) {

        return transform(stream, transformations);
    }

    private static String toString(InputStream stream) {

        final Reader reader = new InputStreamReader(stream);

        final StringWriter writer = copyToStringWriter(reader);

        return writer.toString();
    }

    private static StringWriter copyToStringWriter(Reader reader) {

        final StringWriter writer = new StringWriter();

        final char[] buffer = new char[STREAM_COPY_BUFFER_SIZE];

        try {

            for (int charsRead = 0; charsRead != -1; charsRead = reader.read(buffer)) {

                writer.write(buffer, 0, charsRead);
            }
        } catch (IOException e) {

            throw new RuntimeException(e);
        }

        return writer;
    }
}