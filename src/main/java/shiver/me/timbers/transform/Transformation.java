package shiver.me.timbers.transform;

/**
 * A Transformation is a class that applies a transformation to a supplied {@code String}.
 *
 * @author Karl Bennett
 */
public interface Transformation extends Applyer {

    /**
     * The name of the {@code Transformation} indicates the type of {@code String}s that the transformation should be
     * applied to. This could be anything from the name of a type of token to the actual string it's self.
     *
     * @return the name of this {@code Transformation}.
     */
    public String getName();
}
