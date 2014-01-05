package shiver.me.timbers.transform;

/**
 * This transformer should be when no other suitable transformer can be found. It simply returns the supplied input.
 */
public class NullStringTransformer<T extends Transformation> extends NullTransformer<String, T>
        implements StringTransformer<T> {
}
