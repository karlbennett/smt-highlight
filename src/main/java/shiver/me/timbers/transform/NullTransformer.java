package shiver.me.timbers.transform;

import javax.activation.MimeType;

import static shiver.me.timbers.checks.Checks.isNotNull;

/**
 * This transformer should be used when no other suitable transformer can be found. It simply returns the supplied
 * input. It simply returns the {@link Object#toString()} value for the supplied {@code input}.
 */
public class NullTransformer<I, T extends Transformation> extends AbstractTransformer<I, T> implements Transformer<I, T> {

    public static final MimeType TEXT_PLAIN = quietMimeType("text", "plain");

    public NullTransformer() {
        super(TEXT_PLAIN);
    }

    @Override
    public String transform(I input, Transformations<T> transformations) {

        return isNotNull(input) ? input.toString() : null;
    }
}
