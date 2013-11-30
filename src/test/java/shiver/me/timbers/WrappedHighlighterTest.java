package shiver.me.timbers;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WrappedHighlighterTest {

    private static final String TEXT = "this is a test";

    private Highlighter mockHighlighter;
    private Highlights highlights;
    private InputStream stream;

    @Before
    public void setUp() {

        mockHighlighter = mock(Highlighter.class);
        highlights = mock(Highlights.class);
        stream = mock(InputStream.class);
    }

    @Test
    public void testCreate() {

        new WrappedHighlighter(mock(Highlighter.class), highlights);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullHighlighter() {

        new WrappedHighlighter(null, highlights);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullHighlights() {

        new WrappedHighlighter(mockHighlighter, null);
    }

    @Test
    public void testHighlightWithInputStreamAndHighlights() {

        final WrappedHighlighter highlighter = new WrappedHighlighter(mockHighlighter, highlights);

        highlighter.highlight(stream, highlights);

        verify(mockHighlighter, times(1)).highlight(stream, highlights);
    }

    @Test
    public void testHighlightWithNullInputStreamAndHighlights() {

        final WrappedHighlighter highlighter = new WrappedHighlighter(mockHighlighter, highlights);

        highlighter.highlight((InputStream) null, highlights);

        verify(mockHighlighter, times(1)).highlight(null, highlights);
    }

    @Test
    public void testHighlightWithInputStreamAndNullHighlights() {

        final WrappedHighlighter highlighter = new WrappedHighlighter(mockHighlighter, highlights);

        highlighter.highlight(stream, highlights);

        verify(mockHighlighter, times(1)).highlight(stream, highlights);
    }

    @Test
    public void testHighlightWithStringAndHighlights() {

        final WrappedHighlighter highlighter = new WrappedHighlighter(mockHighlighter, highlights);

        when(mockHighlighter.highlight(any(InputStream.class), eq(highlights))).then(new VerifyString(TEXT));

        highlighter.highlight(TEXT, highlights);

        verify(mockHighlighter, times(1)).highlight(any(InputStream.class), eq(highlights));
    }

    @Test(expected = NullPointerException.class)
    public void testHighlightWithNullStringAndHighlights() {

        final WrappedHighlighter highlighter = new WrappedHighlighter(mockHighlighter, highlights);

        highlighter.highlight((String) null, highlights);
    }

    @Test
    public void testHighlightWithStringAndNullHighlights() {

        final WrappedHighlighter highlighter = new WrappedHighlighter(mockHighlighter, highlights);

        highlighter.highlight(TEXT, highlights);

        verify(mockHighlighter, times(1)).highlight(any(InputStream.class), eq(highlights));
    }

    @Test
    public void testHighlighterWithHighlightsAndInputStream() {

        final WrappedHighlighter highlighter = new WrappedHighlighter(mockHighlighter, highlights);

        highlighter.highlight(stream);

        verify(mockHighlighter, times(1)).highlight(stream, highlights);
    }

    @Test
    public void testHighlighterWithHighlightsAndNullInputStream() {

        final WrappedHighlighter highlighter = new WrappedHighlighter(mockHighlighter, highlights);

        highlighter.highlight((InputStream) null);

        verify(mockHighlighter, times(1)).highlight(null, highlights);
    }

    @Test
    public void testHighlighterWithHighlightsAndString() {

        final WrappedHighlighter highlighter = new WrappedHighlighter(mockHighlighter, highlights);

        when(mockHighlighter.highlight(any(InputStream.class), eq(highlights))).then(new VerifyString(TEXT));

        highlighter.highlight(TEXT);

        verify(mockHighlighter, times(1)).highlight(any(InputStream.class), eq(highlights));
    }

    @Test(expected = NullPointerException.class)
    public void testHighlighterWithHighlightsAndNullString() {

        final WrappedHighlighter highlighter = new WrappedHighlighter(mockHighlighter, highlights);

        highlighter.highlight((String) null);
    }

    /**
     * Verify that the string in the supplied {@code InputStream is correct.}
     */
    private static class VerifyString implements Answer<Void> {

        private final String text;

        /**
         * Create a new {@code VerifyString} with the expected text.
         *
         * @param text the text that is expected to be passed into the mock method as an {@code InputStream}.
         */
        private VerifyString(String text) {

            this.text = text;
        }

        @Override
        public Void answer(InvocationOnMock invocationOnMock) throws Throwable {

            assertEquals("the converted text should be correct.", text,
                    IOUtils.toString((InputStream) invocationOnMock.getArguments()[0]));

            return null;
        }
    }
}
