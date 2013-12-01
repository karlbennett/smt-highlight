package shiver.me.timbers;

import java.util.LinkedList;
import java.util.List;

import static shiver.me.timbers.Asserts.assertIsNotNull;

/**
 * A collection of highlight names that will all match to the same {@link Highlight#apply(String)} logic.
 *
 * @author Karl Bennett
 */
public class CompoundHighlights extends IndividualHighlights {

    public CompoundHighlights(Iterable<String> names, Applyer applyer) {
        super(createHighLights(names, applyer));
    }

    private static Iterable<Highlight> createHighLights(Iterable<String> names, final Applyer applyer) {

        assertIsNotNull(CompoundHighlights.class.getSimpleName() + " names argument cannot be null.", names);
        assertIsNotNull(CompoundHighlights.class.getSimpleName() + " applyer argument cannot be null.", applyer);

        final List<Highlight> highlights = new LinkedList<Highlight>();

        for (final String name : names) {

            highlights.add(new Highlight() {

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

        return highlights;
    }
}
