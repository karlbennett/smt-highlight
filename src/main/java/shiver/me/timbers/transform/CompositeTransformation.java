package shiver.me.timbers.transform;

import static shiver.me.timbers.asserts.Asserts.argumentIsNullMessage;
import static shiver.me.timbers.asserts.Asserts.assertIsNotNull;

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

        assertIsNotNull(argumentIsNullMessage("name"), name);
        assertIsNotNull(argumentIsNullMessage("applyer"), applyer);

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
