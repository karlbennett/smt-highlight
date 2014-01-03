package shiver.me.timbers.transform;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static shiver.me.timbers.asserts.Asserts.argumentIsNullMessage;
import static shiver.me.timbers.asserts.Asserts.assertIsNotNull;
import static shiver.me.timbers.checks.Checks.isNotNull;

/**
 * This {@link CompositeFileTransformer} implementation provides some convenience logic for storing
 * {@link .Transformations} against a {@link StreamTransformer} that can then be used as a {@link FileTransformer}.
 */
public class WrappedStreamFileTransformer<T extends Transformation> implements CompositeFileTransformer<T> {

    private final StreamTransformer<T> transformer;
    private final Transformations<T> transformations;

    /**
     * Create a new {@code WrappedStreamFileTransformer} that will store a {@code StreamTransformer} implementation and
     * {@code Transformations} so that they can be easily reapplied to different text.
     *
     * @param transformer     the transformer to use to apply the transformations.
     * @param transformations the transformations to apply.
     */
    public WrappedStreamFileTransformer(StreamTransformer<T> transformer, Transformations<T> transformations) {

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

        return transformer.transform(isNotNull(file) ? toStream(file) : null, transformations);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String transform(File file) {

        return transform(file, transformations);
    }

    private static InputStream toStream(File file) {

        try {

            return new FileInputStream(file);

        } catch (FileNotFoundException e) {

            throw new RuntimeException(e);
        }
    }
}