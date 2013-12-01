package shiver.me.timbers;

/**
 * This highlight is returned when not matching highlight can be found. It has no name and it's apply method just
 * returns the supplied {@code Sting} without any modification.
 *
 * @author Karl Bennett
 */
public class NullHighlight implements Highlight {

    public static final NullHighlight NULL_HIGHLIGHT = new NullHighlight();

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {

        return NullHighlight.class.getSimpleName();
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

        NullHighlight that = (NullHighlight) o;

        return getName().equals(that.getName());

    }

    @Override
    public int hashCode() {

        return getName().hashCode();
    }
}
