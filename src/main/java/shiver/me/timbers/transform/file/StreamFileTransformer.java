package shiver.me.timbers.transform.file;

import shiver.me.timbers.transform.AbstractTransformer;
import shiver.me.timbers.transform.Transformation;
import shiver.me.timbers.transform.Transformations;
import shiver.me.timbers.transform.stream.StreamTransformer;

import javax.activation.MimeType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static shiver.me.timbers.asserts.Asserts.argumentIsNullMessage;
import static shiver.me.timbers.asserts.Asserts.assertIsNotNull;
import static shiver.me.timbers.checks.Checks.isNotNull;

/**
 * This {@link FileTransformer} implementation provides some convenience logic for presenting a
 * {@link shiver.me.timbers.transform.stream.StreamTransformer} as a {@link FileTransformer}.
 */
public class StreamFileTransformer<T extends Transformation> extends AbstractTransformer<File, T>
        implements FileTransformer<T> {

    private final StreamTransformer<T> transformer;

    public StreamFileTransformer(MimeType mimeType, StreamTransformer<T> transformer) {
        super(mimeType);

        assertIsNotNull(argumentIsNullMessage("transformer"), transformer);

        this.transformer = transformer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String transform(File file, Transformations<T> transformations) {

        return transformer.transform(isNotNull(file) ? toStream(file) : null, transformations);
    }

    private static InputStream toStream(File file) {

        try {

            return new FileInputStream(file);

        } catch (FileNotFoundException e) {

            throw new RuntimeException(e);
        }
    }
}