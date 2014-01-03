package shiver.me.timbers.transform;

import java.io.InputStream;

/**
 * This transformer should be implemented with the {@link Transformations} stored internally.
 */
public interface CompositeStreamTransformer<T extends Transformation> extends CompositeTransformer<InputStream, T>,
        StreamTransformer<T> {
}
