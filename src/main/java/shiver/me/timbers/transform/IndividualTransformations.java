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

/**
 * A collection of individual and possibly unrelated transformations.
 *
 * @author Karl Bennett
 */
public class IndividualTransformations<T extends Transformation> implements Transformations<T> {

    private final List<T> transformationList;
    private final Map<String, T> transformationMap;
    private final T nullTransformation;

    public IndividualTransformations(T nullTransformation) {

        this(Collections.<T>emptySet(), nullTransformation);
    }

    public IndividualTransformations(Iterable<T> transformations, T nullTransformation) {

        this(notNullSingleton(transformations), nullTransformation);
    }

    public IndividualTransformations(Collection<Iterable<T>> multipleTransformations,
                                     T nullTransformation) {

        assertIsNotNull(argumentIsNullMessage("multipleTransformations"), multipleTransformations);
        assertIsNotNull(argumentIsNullMessage("nullTransformation"), nullTransformation);

        this.transformationList = new ArrayList<T>();
        this.transformationMap = new HashMap<String, T>();

        for (T transformation : new CompactedIterable<T>(multipleTransformations)) {

            this.transformationList.add(transformation);
            this.transformationMap.put(transformation.getName(), transformation);
        }

        this.nullTransformation = nullTransformation;
    }

    private static <T> Collection<Iterable<T>> notNullSingleton(Iterable<T> transformations) {

        assertIsNotNull(argumentIsNullMessage("transformations"), transformations);

        return singleton(transformations);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T get(int index) {

        if (indexIsOutOfBounds(index)) {

            return nullTransformation;
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
    public T get(String name) {

        final T transformation = transformationMap.get(name);

        return isNotNull(transformation) ? transformation : nullTransformation;
    }

    @Override
    public Iterator<T> iterator() {
        // We create a copy of the list so that it can't be mutated with the returned iterator.
        return new LinkedList<T>(transformationList).iterator();
    }
}
