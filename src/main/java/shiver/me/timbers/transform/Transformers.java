package shiver.me.timbers.transform;

import javax.activation.MimeType;

/**
 * A container of {@link Transformation}s.
 */
public interface Transformers<T extends Transformer> extends Container<MimeType, T> {
}
