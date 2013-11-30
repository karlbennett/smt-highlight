package shiver.me.timbers;

/**
 * An iterable collection of {@link Highlight}s.
 *
 * @author Karl Bennett
 */
public interface Highlights extends Iterable<Highlight> {

    /**
     * @return the {@code Highlight} at the supplied index.
     */
    public Highlight get(int index);

    /**
     * @return the {@code Highlight} with the supplied name.
     */
    public Highlight get(String name);
}
