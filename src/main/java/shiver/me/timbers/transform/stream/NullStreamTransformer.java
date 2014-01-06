package shiver.me.timbers.transform.stream;

import shiver.me.timbers.transform.Transformation;
import shiver.me.timbers.transform.string.NullStringTransformer;

/**
 * This transformer should be when no other suitable transformer can be found. It simply returns the supplied input.
 */
public class NullStreamTransformer<T extends Transformation> extends StringStreamTransformer<T> {

    public NullStreamTransformer() {
        super(new NullStringTransformer<T>());
    }
}
