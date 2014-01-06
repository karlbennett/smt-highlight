package shiver.me.timbers.transform.file;

import shiver.me.timbers.transform.Transformation;
import shiver.me.timbers.transform.Transformer;

import java.io.File;

/**
 * Implement with the logic that will apply the {@link shiver.me.timbers.transform.Transformations} to the text in the {@code File}.
 */
public interface FileTransformer<T extends Transformation> extends Transformer<File, T> {
}
