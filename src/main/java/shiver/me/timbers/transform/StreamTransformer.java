package shiver.me.timbers.transform;

import java.io.InputStream;

/**
 * Implement with the logic that will apply the {@link Transformations} to the text in the {@code InputStream}.
 */
public interface StreamTransformer<T extends Transformation> extends Transformer<InputStream, T> {
}
