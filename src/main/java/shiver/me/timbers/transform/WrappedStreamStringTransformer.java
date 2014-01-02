package shiver.me.timbers.transform;

import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;

import static shiver.me.timbers.asserts.Asserts.argumentIsNullMessage;
import static shiver.me.timbers.asserts.Asserts.assertIsNotNull;

/**
 * This {@link StreamTransformer} implementation provides some convenience logic for storing {@link Transformations}
 * against a {@link StreamTransformer} that can be used as a {@link StringTransformer}.
 */
public class WrappedStreamStringTransformer<T extends Transformation> implements CompositeStringTransformer<T> {

    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private final StreamTransformer<T> transformer;
    private final Transformations<T> transformations;

    /**
     * Create a new {@code WrappedStreamTransformer} that will store a {@code StreamTransformer} implementation and
     * {@code Transformations} so that they can be easily reapplied to different text.
     *
     * @param transformer     the transformer to use to apply the transformations.
     * @param transformations the transformations to apply.
     */
    public WrappedStreamStringTransformer(StreamTransformer<T> transformer, Transformations<T> transformations) {

        assertIsNotNull(argumentIsNullMessage("transformer"), transformer);
        assertIsNotNull(argumentIsNullMessage("transformations"), transformations);

        this.transformer = transformer;
        this.transformations = transformations;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String transform(String text, Transformations<T> transformations) {

        return transformer.transform(new ByteArrayInputStream(text.getBytes(UTF_8)), transformations);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String transform(String text) {

        return transformer.transform(new ByteArrayInputStream(text.getBytes(UTF_8)), transformations);
    }
}