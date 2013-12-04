package shiver.me.timbers;

import java.util.LinkedList;
import java.util.List;

import static shiver.me.timbers.Asserts.assertIsNotNull;

/**
 * A collection of transformation names that will all match to the same {@link Transformation#apply(String)} logic.
 *
 * @author Karl Bennett
 */
public class CompoundTransformations extends IndividualTransformations {

    public CompoundTransformations(Iterable<String> names, Applyer applyer) {
        super(createTransformations(names, applyer));
    }

    private static Iterable<Transformation> createTransformations(Iterable<String> names, final Applyer applyer) {

        assertIsNotNull(CompoundTransformations.class.getSimpleName() + " names argument cannot be null.", names);
        assertIsNotNull(CompoundTransformations.class.getSimpleName() + " applyer argument cannot be null.", applyer);

        final List<Transformation> transformations = new LinkedList<Transformation>();

        for (final String name : names) {

            transformations.add(new Transformation() {

                @Override
                public String getName() {

                    return name;
                }

                @Override
                public String apply(String string) {

                    return applyer.apply(string);
                }
            });
        }

        return transformations;
    }
}
