package shiver.me.timbers.transform.iterable;

import shiver.me.timbers.transform.Transformation;
import shiver.me.timbers.transform.Transformations;
import shiver.me.timbers.transform.mapped.MappedContainer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static shiver.me.timbers.asserts.Asserts.argumentIsNullMessage;
import static shiver.me.timbers.asserts.Asserts.assertIsNotNull;

/**
 * A container of individual and possibly unrelated transformations.
 */
public class IterableTransformations<T extends Transformation> extends MappedContainer<String, T>
        implements Transformations<T> {

    public IterableTransformations(T nullTransformation) {

        this(Collections.<T>emptySet(), nullTransformation);
    }

    public IterableTransformations(Iterable<T> transformations, T nullTransformation) {

        super(asMap(transformations), nullTransformation);
    }

    private static <T extends Transformation> Map<String, T> asMap(Iterable<T> transformations) {

        assertIsNotNull(argumentIsNullMessage("transformations"), transformations);

        final Map<String, T> map = new HashMap<String, T>();

        for (T transformation : transformations) {

            map.put(transformation.getName(), transformation);
        }

        return map;
    }
}
