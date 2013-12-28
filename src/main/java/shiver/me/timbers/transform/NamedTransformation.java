package shiver.me.timbers.transform;

import static shiver.me.timbers.asserts.Asserts.argumentIsNullMessage;
import static shiver.me.timbers.asserts.Asserts.assertIsNotNull;

/**
 * This is a concrete {@code Transformation} that can have it's name set as constructor dependency.
 *
 * @author Karl Bennett
 */
public class NamedTransformation implements Transformation {

    private final String name;

    public NamedTransformation(String name) {

        assertIsNotNull(argumentIsNullMessage("name"), name);

        this.name = name;
    }

    @Override
    public String getName() {

        return name;
    }
}
