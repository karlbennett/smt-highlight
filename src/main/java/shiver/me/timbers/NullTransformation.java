package shiver.me.timbers;

/**
 * This transformation should be returned when no matching transformation can be found. It has no name and it's apply
 * method just returns the supplied {@code Sting} without any modification.
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

        return NullTransformation.class.getSimpleName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String apply(String string) {

        return string;
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
