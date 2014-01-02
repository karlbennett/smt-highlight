package shiver.me.timbers.transform;

/**
 * Implement with the logic that will apply the {@link Transformations} to the text in the {@code String}.
 */
public interface StringTransformer<T extends Transformation> extends Transformer<String, T> {
}
