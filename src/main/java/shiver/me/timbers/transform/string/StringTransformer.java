package shiver.me.timbers.transform.string;

import shiver.me.timbers.transform.Transformation;
import shiver.me.timbers.transform.Transformer;

/**
 * Implement with the logic that will apply the {@link shiver.me.timbers.transform.Transformations} to the text in the {@code String}.
 */
public interface StringTransformer<T extends Transformation> extends Transformer<String, T> {
}
