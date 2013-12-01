package shiver.me.timbers;

/**
 * This is the minimum suite of tests that must be implemented for any new {@link Highlights} class.
 *
 * @author Karl Bennett
 */
@SuppressWarnings("UnusedDeclaration")
public interface HighlightsTestTemplate {

    public void testCreate();

    public void testGetWithIndex();

    public void testGetWithInvalidIndex();

    public void testGetWithName();

    public void testGetWithInvalidName();

    public void testGetWithNullName();

    public void testIterator();
}
