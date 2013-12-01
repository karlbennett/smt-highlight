package shiver.me.timbers;

/**
 * The applying half of the {@link Highlight} interface. Simply provides an {@link #apply(String)} method that can be
 * implemented with logic for transforming a string.
 *
 * @author Karl Bennett
 */
public interface Applyer {

    /**
     * Apply the highlight to the supplied {@code String}.
     *
     * @param string the {@code String} to apply the highlight to.
     * @return the {@code String} with the highlight applied.
     */
    public String apply(String string);
}
