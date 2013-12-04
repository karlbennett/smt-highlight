package shiver.me.timbers;

/**
 * The applying half of the {@link Transformation} interface. Simply provides an {@link #apply(String)} method that can
 * be implemented with logic for transforming a string.
 *
 * @author Karl Bennett
 */
public interface Applyer {

    /**
     * Apply the transformation to the supplied {@code String}.
     *
     * @param string the {@code String} to apply the transformation to.
     * @return the {@code String} with the transformation applied.
     */
    public String apply(String string);
}
