package shiver.me.timbers.transform.composite;

import shiver.me.timbers.transform.Transformation;
import shiver.me.timbers.transform.stream.StreamTransformer;

import java.io.InputStream;

/**
 * This transformer should be implemented with the {@link shiver.me.timbers.transform.Transformations} stored internally.
 */
public interface CompositeStreamTransformer<T extends Transformation> extends CompositeTransformer<InputStream, T>,
        StreamTransformer<T> {
}
