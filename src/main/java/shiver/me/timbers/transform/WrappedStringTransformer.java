package shiver.me.timbers.transform;

import static shiver.me.timbers.asserts.Asserts.argumentIsNullMessage;
import static shiver.me.timbers.asserts.Asserts.assertIsNotNull;

/**
 * This {@link StringTransformer} implementation provides some convenience logic for storing {@link Transformations}
 * against a {@link StringTransformer}.
 */
public class WrappedStringTransformer<T extends Transformation> implements CompositeStringTransformer<T> {

    private final StringTransformer<T> transformer;
    private final Transformations<T> transformations;

    /**
     * Create a new {@code WrappedStringTransformer} that will store a {@code StringTransformer} implementation and
     * {@code Transformations} so that they can be easily reapplied to different text.
     *
     * @param transformer     the transformer to use to apply the transformations.
     * @param transformations the transformations to apply.
     */
    public WrappedStringTransformer(StringTransformer<T> transformer, Transformations<T> transformations) {

        assertIsNotNull(argumentIsNullMessage("transformer"), transformer);
        assertIsNotNull(argumentIsNullMessage("transformations"), transformations);

        this.transformer = transformer;
        this.transformations = transformations;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String transform(String string, Transformations<T> transformations) {

        return transformer.transform(string, transformations);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String transform(String string) {

        return transform(string, transformations);
    }
}