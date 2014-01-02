package shiver.me.timbers.transform;

import java.io.File;

/**
 * Implement with the logic that will apply the {@link Transformations} to the text in the {@code File}.
 */
public interface FileTransformer<T extends Transformation> extends Transformer<File, T> {
}
