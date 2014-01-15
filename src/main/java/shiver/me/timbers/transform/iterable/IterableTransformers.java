package shiver.me.timbers.transform.iterable;

import shiver.me.timbers.transform.Transformer;
import shiver.me.timbers.transform.Transformers;

import javax.activation.MimeType;

/**
 * A collection of individual and possibly unrelated transformers.
 */
public class IterableTransformers<T extends Transformer> extends IterableContainer<MimeType, T>
        implements Transformers<T> {

    public IterableTransformers(T nullTransformer) {

        super(new TransformerIdentifierReader<T>(), nullTransformer);
    }

    public IterableTransformers(Iterable<T> transformers, T nullTransformer) {

        super(transformers, new TransformerIdentifierReader<T>(), nullTransformer);
    }

    private static class TransformerIdentifierReader<T extends Transformer> implements IdentifierReader<MimeType, T> {

        @Override
        public MimeType readIdentifier(T transformer) {

            return transformer.getMimeType();
        }
    }
}
