package shiver.me.timbers;

import static shiver.me.timbers.Asserts.*;

/**
 * This is a concrete {@code Transformation} that can have it's name and {@link Applyer} set as constructor
 * dependencies.
 *
 * @author Karl Bennett
 */
public class CompositeTransformation implements Transformation {

    private final String name;
    private final Applyer applyer;

    public CompositeTransformation(String name, Applyer applyer) {

        assertIsNotNull(CompositeTransformation.class.getSimpleName() + " name argument cannot be null.", name);
        assertIsNotNull(CompositeTransformation.class.getSimpleName() + " applyer argument cannot be null.", applyer);

        this.name = name;
        this.applyer = applyer;
    }

    @Override
    public String getName() {

        return name;
    }

    @Override
    public String apply(String string) {

        return applyer.apply(string);
    }
}
