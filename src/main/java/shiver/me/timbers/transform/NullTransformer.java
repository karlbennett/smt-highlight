package shiver.me.timbers.transform;

import static shiver.me.timbers.checks.Checks.isNotNull;

/**
 * This transformer should be used when no other suitable transformer can be found. It simply returns the supplied
 * input. It simply returns the {@link Object#toString()} value for the supplied {@code input}.
 */
public class NullTransformer<I, T extends Transformation> implements Transformer<I, T> {

    @Override
    public String transform(I input, Transformations<T> transformations) {

        return isNotNull(input) ? input.toString() : null;
    }
}
