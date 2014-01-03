package shiver.me.timbers.transform;

/**
 * This transformer should be when no other suitable transformer can be found. It simply returns the supplied input.
 */
public class NullFileTransformer<T extends Transformation> extends StreamFileTransformer<T> {

    public NullFileTransformer() {
        super(new NullStreamTransformer<T>());
    }
}
