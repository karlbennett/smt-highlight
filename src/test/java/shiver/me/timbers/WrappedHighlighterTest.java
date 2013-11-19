package shiver.me.timbers;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class WrappedHighlighterTest {

    private static class TestHighlighter implements Highlighter {

        @Override
        public String highlight(InputStream stream, final Map<String, Highlight> highlights) {

            try {

                String text = IOUtils.toString(stream);

                StringBuilder builder = new StringBuilder();
                Highlight highlight;
                for (String word : text.split(" ")) {

                    highlight = highlights.get(word);

                    builder.append(null == highlight ? word : highlight.apply(word)).append(" ");
                }

                builder.replace(builder.length() - 1, builder.length(), "");

                return builder.toString();

            } catch (IOException e) {

                throw new RuntimeException(e);
            }
        }
    }

    private static class TestHighlight<T> implements Highlight {

        private final String name;
        private final T replacement;

        private TestHighlight(String name, T replacement) {

            this.name = name;
            this.replacement = replacement;
        }

        @Override
        public String getName() {

            return name;
        }

        @Override
        public String apply(String string) {

            return replacement.toString();
        }
    }

    private static final String ONE = "one";
    private static final String TWO = "two";
    private static final String THREE = "three";
    private static final String FOUR = "four";
    private static final String FIVE = "five";

    private static final Map<String, Highlight> HIGHLIGHT_MAP = new HashMap<String, Highlight>() {{
        put(ONE, new TestHighlight<Integer>(ONE, 1));
        put(TWO, new TestHighlight<Integer>(TWO, 2));
        put(THREE, new TestHighlight<Integer>(THREE, 3));
        put(FOUR, new TestHighlight<Integer>(FOUR, 4));
        put(FIVE, new TestHighlight<Integer>(FIVE, 5));
    }};

    private static final List<Highlight> HIGHLIGHTS = new ArrayList<Highlight>(HIGHLIGHT_MAP.values());

    private static final String INPUT_TEXT;
    private static final String HIGHLIGHTED_TEXT;

    static {
        try {

            INPUT_TEXT = IOUtils.toString(WrappedHighlighterTest.class.getResourceAsStream("input.txt"));
            HIGHLIGHTED_TEXT = IOUtils.toString(WrappedHighlighterTest.class.getResourceAsStream("highlighted.txt"));

        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }

    private InputStream inputStream;

    @Before
    public void setUp() {

        inputStream = WrappedHighlighterTest.class.getResourceAsStream("input.txt");
    }

    @Test
    public void testCreate() {

        new WrappedHighlighter(new TestHighlighter(), HIGHLIGHT_MAP);
    }

    @Test
    public void testCreateWithList() {

        new WrappedHighlighter(new TestHighlighter(), HIGHLIGHTS);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullHighlighter() {

        new WrappedHighlighter(null, HIGHLIGHT_MAP);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullMap() {

        new WrappedHighlighter(new TestHighlighter(), (Map<String, Highlight>) null);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullList() {

        new WrappedHighlighter(new TestHighlighter(), (List<Highlight>) null);
    }

    @Test
    public void testHighlightWithInputStreamAndMap() {

        final WrappedHighlighter highlighter = new WrappedHighlighter(new TestHighlighter(),
                new HashMap<String, Highlight>());

        assertEquals("the text from a stream should be highlighted correctly.", HIGHLIGHTED_TEXT,
                highlighter.highlight(inputStream, HIGHLIGHT_MAP));
    }

    @Test(expected = NullPointerException.class)
    public void testHighlightWithNullInputStreamAndMap() {

        final WrappedHighlighter highlighter = new WrappedHighlighter(new TestHighlighter(),
                new HashMap<String, Highlight>());

        highlighter.highlight((InputStream) null, HIGHLIGHT_MAP);
    }

    @Test
    public void testHighlightWithInputStreamAndEmptyMap() {

        final WrappedHighlighter highlighter = new WrappedHighlighter(new TestHighlighter(),
                new HashMap<String, Highlight>());

        assertEquals("the text should not have changed.", INPUT_TEXT,
                highlighter.highlight(inputStream, new HashMap<String, Highlight>()));
    }

    @Test(expected = NullPointerException.class)
    public void testHighlightWithInputStreamAndNullMap() {

        final WrappedHighlighter highlighter = new WrappedHighlighter(new TestHighlighter(),
                new HashMap<String, Highlight>());

        highlighter.highlight(inputStream, null);
    }

    @Test
    public void testHighlightWithStringAndMap() throws IOException {

        final WrappedHighlighter highlighter = new WrappedHighlighter(new TestHighlighter(),
                new HashMap<String, Highlight>());

        assertEquals("the text from a string should be highlighted correctly.", HIGHLIGHTED_TEXT,
                highlighter.highlight(INPUT_TEXT, HIGHLIGHT_MAP));
    }

    @Test
    public void testHighlightWithEmptyStringAndMap() throws IOException {

        final WrappedHighlighter highlighter = new WrappedHighlighter(new TestHighlighter(),
                new HashMap<String, Highlight>());

        assertEquals("empty text should not be modified.", "",
                highlighter.highlight("", HIGHLIGHT_MAP));
    }

    @Test(expected = NullPointerException.class)
    public void testHighlightWithNullStringAndMap() throws IOException {

        final WrappedHighlighter highlighter = new WrappedHighlighter(new TestHighlighter(),
                new HashMap<String, Highlight>());

        highlighter.highlight((String) null, HIGHLIGHT_MAP);
    }

    @Test
    public void testHighlightWithStringAndEmptyMap() {

        final WrappedHighlighter highlighter = new WrappedHighlighter(new TestHighlighter(),
                new HashMap<String, Highlight>());

        assertEquals("the empty string should not have changed.", INPUT_TEXT,
                highlighter.highlight(INPUT_TEXT, new HashMap<String, Highlight>()));
    }

    @Test(expected = NullPointerException.class)
    public void testHighlightWithStringAndNullMap() throws IOException {

        final WrappedHighlighter highlighter = new WrappedHighlighter(new TestHighlighter(),
                new HashMap<String, Highlight>());

        highlighter.highlight(INPUT_TEXT, null);
    }

    @Test
    public void testHighlighterWithMapAndInputStream() {

        final WrappedHighlighter highlighter = new WrappedHighlighter(new TestHighlighter(), HIGHLIGHT_MAP);

        assertEquals("the text from a stream should be highlighted correctly.", HIGHLIGHTED_TEXT,
                highlighter.highlight(inputStream));
    }

    @Test(expected = NullPointerException.class)
    public void testHighlighterWithMapAndNullInputStream() {

        final WrappedHighlighter highlighter = new WrappedHighlighter(new TestHighlighter(), HIGHLIGHT_MAP);

        highlighter.highlight((InputStream) null);
    }

    @Test
    public void testHighlighterWithMapAndString() throws IOException {

        final WrappedHighlighter highlighter = new WrappedHighlighter(new TestHighlighter(), HIGHLIGHT_MAP);

        assertEquals("the text from a stream should be highlighted correctly.", HIGHLIGHTED_TEXT,
                highlighter.highlight(INPUT_TEXT));
    }

    @Test
    public void testHighlighterWithMapAndEmptyString() throws IOException {

        final WrappedHighlighter highlighter = new WrappedHighlighter(new TestHighlighter(), HIGHLIGHT_MAP);

        assertEquals("the empty string should not have changed.", "",
                highlighter.highlight(""));
    }

    @Test(expected = NullPointerException.class)
    public void testHighlighterWithMapAndNullString() throws IOException {

        final WrappedHighlighter highlighter = new WrappedHighlighter(new TestHighlighter(), HIGHLIGHT_MAP);

        highlighter.highlight((String) null);
    }

    @Test
    public void testHighlighterWithListAndInputStream() {

        final WrappedHighlighter highlighter = new WrappedHighlighter(new TestHighlighter(), HIGHLIGHTS);

        assertEquals("the text from a stream should be highlighted correctly.", HIGHLIGHTED_TEXT,
                highlighter.highlight(inputStream));
    }

    @Test(expected = NullPointerException.class)
    public void testHighlighterWithListAndNullInputStream() {

        final WrappedHighlighter highlighter = new WrappedHighlighter(new TestHighlighter(), HIGHLIGHTS);

        highlighter.highlight((InputStream) null);
    }

    @Test
    public void testHighlighterWithListAndString() throws IOException {

        final WrappedHighlighter highlighter = new WrappedHighlighter(new TestHighlighter(), HIGHLIGHTS);

        assertEquals("the text from a stream should be highlighted correctly.", HIGHLIGHTED_TEXT,
                highlighter.highlight(INPUT_TEXT));
    }

    @Test
    public void testHighlighterWithListAndEmptyString() throws IOException {

        final WrappedHighlighter highlighter = new WrappedHighlighter(new TestHighlighter(), HIGHLIGHTS);

        assertEquals("the empty string should not have changed.", "",
                highlighter.highlight(""));
    }

    @Test(expected = NullPointerException.class)
    public void testHighlighterWithListAndNullString() throws IOException {

        final WrappedHighlighter highlighter = new WrappedHighlighter(new TestHighlighter(), HIGHLIGHTS);

        highlighter.highlight((String) null);
    }
}
