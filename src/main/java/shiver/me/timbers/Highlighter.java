package shiver.me.timbers;

import java.io.InputStream;

/**
 * Implement with the logic that will apply the {@link Highlight}s to the text in the {@code InputStream}.
 *
 * @author Karl Bennett
 */
public interface Highlighter {

    /**
     * Read the text from the supplied {@code InputStream} and highlight using the supplied {@code Highlight}s.
     *
     * @param stream     the input stream containing the text to be highlighted.
     * @param highlights the highlights to apply to the text.
     * @return the highlighted text.
     */
    public String highlight(InputStream stream, Highlights highlights);
}
