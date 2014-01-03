package shiver.me.timbers.transform;

import java.io.File;

import static shiver.me.timbers.asserts.Asserts.argumentIsNullMessage;
import static shiver.me.timbers.asserts.Asserts.assertIsNotNull;

/**
 * This {@link CompositeFileTransformer} implementation provides some convenience logic for storing
 * {@link Transformations} against a {@link FileTransformer}.
 *
 * @author Karl Bennett
 */
public class WrappedFileTransformer<T extends Transformation> implements CompositeFileTransformer<T> {

    private final FileTransformer<T> transformer;
    private final Transformations<T> transformations;

    /**
     * Create a new {@code WrappedFileTransformer} that will store a {@code FileTransformer} implementation and
     * {@code Transformations} so that they can be easily reapplied to different text.
     *
     * @param transformer     the transformer to use to apply the transformations.
     * @param transformations the transformations to apply.
     */
    public WrappedFileTransformer(FileTransformer<T> transformer, Transformations<T> transformations) {

        assertIsNotNull(argumentIsNullMessage("transformer"), transformer);
        assertIsNotNull(argumentIsNullMessage("transformations"), transformations);

        this.transformer = transformer;
        this.transformations = transformations;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String transform(File file, Transformations<T> transformations) {

        return transformer.transform(file, transformations);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String transform(File file) {

        return transform(file, transformations);
    }
}