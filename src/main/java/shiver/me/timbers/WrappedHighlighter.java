package shiver.me.timbers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static shiver.me.timbers.Asserts.assertIsNotNull;

/**
 * This abstract {@link Highlighter} implementation provides some convenience logic for storing {@link Highlight}s.
 *
 * @author Karl Bennett
 */
public class WrappedHighlighter implements Highlighter {

    private final Highlighter highlighter;
    private final Map<String, Highlight> highlights;

    /**
     * Create a new {@code WrappedHighlighter} that will store a {@code Highlighter} implementation and
     * {@code Highlight}s so that they can be easily reapplied to different texts.
     *
     * @param highlighter the highlighter to use to apply the highlights.
     * @param highlights  the highlights to apply.
     */
    public WrappedHighlighter(Highlighter highlighter, Map<String, Highlight> highlights) {

        assertIsNotNull("The WrappedHighlighters highlighter argument cannot be null.", highlighter);
        assertIsNotNull("The WrappedHighlighters highlights argument cannot be null.", highlights);

        this.highlighter = highlighter;
        this.highlights = highlights;
    }

    /**
     * Create a new {@code WrappedHighlighter} that will store a {@code Highlighter} implementation and
     * {@code Highlight}s so that they can be easily reapplied to different texts.
     *
     * @param highlighter the highlighter to use to apply the highlights.
     * @param highlights  the highlights to apply.
     */
    public WrappedHighlighter(Highlighter highlighter, final List<Highlight> highlights) {

        this(highlighter, new HashMap<String, Highlight>() {{

            assertIsNotNull("The WrappedHighlighters highlights argument cannot be null.", highlights);

            for (Highlight highlight : highlights) {

                put(highlight.getName(), highlight);
            }
        }});
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String highlight(InputStream stream, Map<String, Highlight> highlights) {

        return highlighter.highlight(stream, highlights);
    }

    /**
     * Read the text in the supplied {@code String} and highlight using the supplied {@code Highlight}s.
     *
     * @param text       the string containing the text to be highlighted.
     * @param highlights the highlights to apply to the text.
     * @return the highlighted text.
     */
    public String highlight(String text, Map<String, Highlight> highlights) {

        return highlight(new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8)), highlights);
    }

    /**
     * Read the text from the supplied {@code InputStream} and apply the contained highlights.
     *
     * @param stream the input stream containing the text to be highlighted.
     * @return the highlighted text.
     */
    public String highlight(InputStream stream) {

        return highlight(stream, highlights);
    }

    /**
     * Read the text in the supplied {@code String} and apply the contained highlights.
     *
     * @param text the string containing the text to be highlighted.
     * @return the highlighted text.
     */
    public String highlight(String text) {

        return highlight(new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8)));
    }
}