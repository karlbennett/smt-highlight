package shiver.me.timbers.transform;

/**
 * This transformation should be returned when no matching transformation can be found.
 *
 * @author Karl Bennett
 */
public class NullTransformation implements Transformation {

    public static final NullTransformation NULL_TRANSFORMATION = new NullTransformation();

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {

        return getClass().getSimpleName();
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        NullTransformation that = (NullTransformation) o;

        return getName().equals(that.getName());

    }

    @Override
    public int hashCode() {

        return getName().hashCode();
    }
}
