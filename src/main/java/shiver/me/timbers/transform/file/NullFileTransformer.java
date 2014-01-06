package shiver.me.timbers.transform.file;

import shiver.me.timbers.transform.Transformation;
import shiver.me.timbers.transform.stream.NullStreamTransformer;

/**
 * This transformer should be when no other suitable transformer can be found. It simply returns the supplied input.
 */
public class NullFileTransformer<T extends Transformation> extends StreamFileTransformer<T> {

    public NullFileTransformer() {
        super(new NullStreamTransformer<T>());
    }
}
