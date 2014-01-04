package shiver.me.timbers.transform;

/**
 * This transformer should be when no other suitable transformer can be found. It simply returns the supplied input.
 */
public class NullStringTransformer<T extends Transformation> implements StringTransformer<T> {

    @Override
    public String transform(String input, Transformations<T> transformations) {

        return input;
    }
}
