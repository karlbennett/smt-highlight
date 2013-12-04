package shiver.me.timbers;

/**
 * An iterable collection of {@link Transformation}s.
 *
 * @author Karl Bennett
 */
public interface Transformations extends Iterable<Transformation> {

    /**
     * @return the {@code Transformation} at the supplied index.
     */
    public Transformation get(int index);

    /**
     * @return the {@code Transformation} with the supplied name.
     */
    public Transformation get(String name);
}
