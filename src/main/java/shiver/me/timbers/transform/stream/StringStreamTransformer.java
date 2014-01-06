package shiver.me.timbers.transform.stream;

import shiver.me.timbers.transform.Transformation;
import shiver.me.timbers.transform.Transformations;
import shiver.me.timbers.transform.string.StringTransformer;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;

import static shiver.me.timbers.asserts.Asserts.argumentIsNullMessage;
import static shiver.me.timbers.asserts.Asserts.assertIsNotNull;
import static shiver.me.timbers.checks.Checks.isNotNull;

/**
 * This {@link StreamTransformer} implementation provides some convenience logic for presenting a
 * {@link shiver.me.timbers.transform.string.StringTransformer} as a {@link StreamTransformer}.
 */
public class StringStreamTransformer<T extends Transformation> implements StreamTransformer<T> {

    private static final int STREAM_COPY_BUFFER_SIZE = 1024 * 4; // This value was taken from commons-io.

    private final StringTransformer<T> transformer;

    public StringStreamTransformer(StringTransformer<T> transformer) {

        assertIsNotNull(argumentIsNullMessage("transformer"), transformer);

        this.transformer = transformer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String transform(InputStream stream, Transformations<T> transformations) {

        return transformer.transform(isNotNull(stream) ? toString(stream) : null, transformations);
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