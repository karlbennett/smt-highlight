package shiver.me.timbers.transform;

/**
 * This transformer should be when no other suitable transformer can be found. It simply returns the supplied input.
 */
public class NullCompositeStringTransformer<T extends Transformation> extends WrappedStringTransformer<T> {

    public NullCompositeStringTransformer(Transformations<T> transformations) {
        super(new NullStringTransformer<T>(), transformations);
    }
}
