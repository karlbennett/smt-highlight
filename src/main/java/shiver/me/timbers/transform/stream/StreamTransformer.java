package shiver.me.timbers.transform.stream;

import shiver.me.timbers.transform.Transformation;
import shiver.me.timbers.transform.Transformer;

import java.io.InputStream;

/**
 * Implement with the logic that will apply the {@link shiver.me.timbers.transform.Transformations} to the text in the {@code InputStream}.
 */
public interface StreamTransformer<T extends Transformation> extends Transformer<InputStream, T> {
}
