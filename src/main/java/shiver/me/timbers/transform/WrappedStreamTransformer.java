package shiver.me.timbers.transform;

import java.io.InputStream;

import static shiver.me.timbers.asserts.Asserts.argumentIsNullMessage;
import static shiver.me.timbers.asserts.Asserts.assertIsNotNull;

/**
 * This {@link StreamTransformer} implementation provides some convenience logic for storing {@link Transformations}
 * against a {@link StreamTransformer}.
 *
 * @author Karl Bennett
 */
public class WrappedStreamTransformer<T extends Transformation> implements CompositeStreamTransformer<T> {

    private final StreamTransformer<T> transformer;
    private final Transformations<T> transformations;

    /**
     * Create a new {@code WrappedStreamTransformer} that will store a {@code StreamTransformer} implementation and
     * {@code Transformations} so that they can be easily reapplied to different text.
     *
     * @param transformer     the transformer to use to apply the transformations.
     * @param transformations the transformations to apply.
     */
    public WrappedStreamTransformer(StreamTransformer<T> transformer, Transformations<T> transformations) {

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

        return transformer.transform(stream, transformations);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String transform(InputStream stream) {

        return transform(stream, transformations);
    }
}