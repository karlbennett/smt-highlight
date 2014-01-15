package shiver.me.timbers.transform.composite;

import shiver.me.timbers.transform.AbstractTransformer;
import shiver.me.timbers.transform.Transformation;
import shiver.me.timbers.transform.Transformer;

import javax.activation.MimeType;

import static shiver.me.timbers.asserts.Asserts.argumentIsNullMessage;
import static shiver.me.timbers.asserts.Asserts.assertIsNotNull;

/**
 * This wrapped transformer simply allows the retrieval of the supplied transformers {@code MimeType} without the risk
 * of a {@link NullPointerException}.
 */
public abstract class AbstractWrappedTansformer<I, T extends Transformation> extends AbstractTransformer<I, T> {

    protected AbstractWrappedTansformer(Transformer<I, T> transformer) {
        super(getMimeType(transformer));
    }

    private static <I, T extends Transformation> MimeType getMimeType(Transformer<I, T> transformer) {

        assertIsNotNull(argumentIsNullMessage("transformer"), transformer);

        return transformer.getMimeType();
    }
}
