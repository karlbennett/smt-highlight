package shiver.me.timbers.transform;

/**
 * This transformer should be when no other suitable transformer can be found. It simply returns the supplied input.
 */
public class NullCompositeStreamTransformer<T extends Transformation> extends WrappedStreamTransformer<T> {

    public NullCompositeStreamTransformer(Transformations<T> transformations) {
        super(new NullStreamTransformer<T>(), transformations);
    }
}
