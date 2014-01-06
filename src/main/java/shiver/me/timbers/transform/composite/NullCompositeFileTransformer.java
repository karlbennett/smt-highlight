package shiver.me.timbers.transform.composite;

import shiver.me.timbers.transform.Transformation;
import shiver.me.timbers.transform.Transformations;
import shiver.me.timbers.transform.file.NullFileTransformer;

/**
 * This transformer should be when no other suitable transformer can be found. It simply returns the supplied input.
 */
public class NullCompositeFileTransformer<T extends Transformation> extends WrappedFileTransformer<T> {

    public NullCompositeFileTransformer(Transformations<T> transformations) {
        super(new NullFileTransformer<T>(), transformations);
    }
}
