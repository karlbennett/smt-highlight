package shiver.me.timbers.transform.string;

import shiver.me.timbers.transform.AbstractTransformer;
import shiver.me.timbers.transform.Transformation;
import shiver.me.timbers.transform.Transformations;
import shiver.me.timbers.transform.stream.StreamTransformer;

import javax.activation.MimeType;
import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;

import static shiver.me.timbers.asserts.Asserts.argumentIsNullMessage;
import static shiver.me.timbers.asserts.Asserts.assertIsNotNull;
import static shiver.me.timbers.checks.Checks.isNotNull;

/**
 * This {@link StringTransformer} implementation provides some convenience logic for presenting a
 * {@link shiver.me.timbers.transform.stream.StreamTransformer} as a {@link StringTransformer}.
 */
public class StreamStringTransformer<T extends Transformation> extends AbstractTransformer<String, T>
        implements StringTransformer<T> {

    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private final StreamTransformer<T> transformer;

    public StreamStringTransformer(MimeType mimeType, StreamTransformer<T> transformer) {
        super(mimeType);

        assertIsNotNull(argumentIsNullMessage("transformer"), transformer);

        this.transformer = transformer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String transform(String text, Transformations<T> transformations) {

        return transformer.transform(isNotNull(text) ? new ByteArrayInputStream(text.getBytes(UTF_8)) : null,
                transformations);
    }
}