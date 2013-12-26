package shiver.me.timbers.transform;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singleton;
import static shiver.me.timbers.asserts.Asserts.argumentIsNullMessage;
import static shiver.me.timbers.asserts.Asserts.assertIsNotNull;
import static shiver.me.timbers.checks.Checks.isNotNull;
import static shiver.me.timbers.transform.NullTransformation.NULL_TRANSFORMATION;

/**
 * A collection of individual and possibly unrelated transformations.
 *
 * @author Karl Bennett
 */
public class IndividualTransformations implements Transformations {

    private final List<Transformation> transformationList;
    private final Map<String, Transformation> transformationMap;

    public IndividualTransformations() {

        this(Collections.<Transformation>emptySet());
    }

    public IndividualTransformations(Iterable<Transformation> transformations) {

        this(notNullSingleton(transformations));
    }

    public IndividualTransformations(Collection<Iterable<Transformation>> multipleTransformations) {

        assertIsNotNull(argumentIsNullMessage("multipleTransformations"), multipleTransformations);

        this.transformationList = new ArrayList<Transformation>();
        this.transformationMap = new HashMap<String, Transformation>();

        for (Transformation transformation : new CompactedIterable<Transformation>(multipleTransformations)) {

            this.transformationList.add(transformation);
            this.transformationMap.put(transformation.getName(), transformation);
        }
    }

    private static Collection<Iterable<Transformation>> notNullSingleton(Iterable<Transformation> transformations) {

        assertIsNotNull(argumentIsNullMessage("transformations"), transformations);

        return singleton(transformations);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Transformation get(int index) {

        if (indexIsOutOfBounds(index)) {

            return NULL_TRANSFORMATION;
        }

        return transformationList.get(index);
    }

    private boolean indexIsOutOfBounds(int index) {

        return 0 > index || transformationList.size() <= index;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Transformation get(String name) {

        final Transformation transformation = transformationMap.get(name);

        return isNotNull(transformation) ? transformation : NULL_TRANSFORMATION;
    }

    @Override
    public Iterator<Transformation> iterator() {
        // We create a copy of the list so that it can't be mutated with the returned iterator.
        return new LinkedList<Transformation>(transformationList).iterator();
    }
}
