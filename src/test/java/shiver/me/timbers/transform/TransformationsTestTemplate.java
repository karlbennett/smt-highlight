package shiver.me.timbers.transform;

/**
 * This is the minimum suite of tests that must be implemented for any new {@link shiver.me.timbers.transform.Transformations} class.
 *
 * @author Karl Bennett
 */
@SuppressWarnings("UnusedDeclaration")
public interface TransformationsTestTemplate {

    public void testCreate();

    public void testCreateWithEmptyIterable();

    public void testCreateWithNullIterable();

    public void testGetWithIndex();

    public void testGetWithInvalidIndex();

    public void testGetWithName();

    public void testGetWithInvalidName();

    public void testGetWithNullName();

    public void testIterator();
}
