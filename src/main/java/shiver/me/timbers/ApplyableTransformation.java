package shiver.me.timbers;

import static shiver.me.timbers.Asserts.assertIsNotNull;

/**
 * This {@code Transformation} can have it's {@code Applyer} customised.
 *
 * @author Karl Bennett
 */
public abstract class ApplyableTransformation implements Transformation {

    private final Applyer applyer;

    /**
     * @param applyer a custom {@code Applyer} to be run for this Transformation.
     */
    protected ApplyableTransformation(Applyer applyer) {

        assertIsNotNull(ApplyableTransformation.class.getSimpleName() + " applyer argument cannot be null.", applyer);

        this.applyer = applyer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String apply(String string) {

        return applyer.apply(string);
    }
}
