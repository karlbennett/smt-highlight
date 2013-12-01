package shiver.me.timbers;

/**
 * A highlight is a class that caries out the transform to "highlight" a supplied {@code String}.
 *
 * @author Karl Bennett
 */
public interface Highlight extends Applyer {

    /**
     * The name of the {@code Highlight} indicates the type of {@code String}s that the highlight should be applied to.
     * This would usually be something like a "keyword", "typeDeceleration", or any other unique type of string fragment.
     *
     * @return the name of this {@code Highlight}.
     */
    public String getName();
}
