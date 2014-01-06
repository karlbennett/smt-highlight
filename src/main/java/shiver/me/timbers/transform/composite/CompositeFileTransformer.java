package shiver.me.timbers.transform.composite;

import shiver.me.timbers.transform.Transformation;
import shiver.me.timbers.transform.file.FileTransformer;

import java.io.File;

/**
 * This transformer should be implemented with the {@link shiver.me.timbers.transform.Transformations} stored internally.
 */
public interface CompositeFileTransformer<T extends Transformation> extends CompositeTransformer<File, T>,
        FileTransformer<T> {
}
