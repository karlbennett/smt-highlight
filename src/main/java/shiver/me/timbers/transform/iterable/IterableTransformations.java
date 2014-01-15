package shiver.me.timbers.transform.iterable;

import shiver.me.timbers.transform.Transformation;
import shiver.me.timbers.transform.Transformations;

/**
 * A container of individual and possibly unrelated transformations.
 */
public class IterableTransformations<T extends Transformation> extends IterableContainer<String, T>
        implements Transformations<T> {

    public IterableTransformations(T nullTransformation) {

        super(new TransformationIdentityReader<T>(), nullTransformation);
    }

    public IterableTransformations(Iterable<T> transformations, T nullTransformation) {

        super(transformations, new TransformationIdentityReader<T>(), nullTransformation);
    }

    private static class TransformationIdentityReader<T extends Transformation> implements IdentifierReader<String, T> {

        @Override
        public String readIdentifier(T transformation) {

            return transformation.getName();
        }
    }
}
