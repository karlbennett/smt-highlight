package shiver.me.timbers.transform;

import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;

import static shiver.me.timbers.asserts.Asserts.argumentIsNullMessage;
import static shiver.me.timbers.asserts.Asserts.assertIsNotNull;

/**
 * This transformer simply allows the setting of the {@code MimeType} with the constructor.
 */
public abstract class AbstractTransformer<I, T extends Transformation> implements Transformer<I, T> {

    public static MimeType quietMimeType(String primary, String sub) {

        try {

            return new MimeType(primary, sub);

        } catch (MimeTypeParseException e) {

            throw new RuntimeException(e);
        }
    }

    private final MimeType mimeType;

    protected AbstractTransformer(MimeType mimeType) {

        assertIsNotNull(argumentIsNullMessage("mimeType"), mimeType);

        this.mimeType = mimeType;
    }

    @Override
    public MimeType getMimeType() {

        return mimeType;
    }
}
