package shiver.me.timbers.transform;

import java.io.File;

/**
 * This transformer should be implemented with the {@link Transformations} stored internally.
 */
public interface CompositeFileTransformer<T extends Transformation> extends CompositeTransformer<File, T>,
        FileTransformer<T> {
}
