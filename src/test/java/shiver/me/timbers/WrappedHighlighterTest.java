package shiver.me.timbers;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyMap;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WrappedHighlighterTest {

    private static final Map<String, Highlight> EMPTY_MAP = emptyMap();
    private static final String TEXT = "this is a test";

    private Highlighter mockHighlighter;
    private Map<String, Highlight> map;
    private List<Highlight> list;
    private InputStream stream;

    @Before
    public void setUp() {

        mockHighlighter = mock(Highlighter.class);
        map = spy(new HashMap<String, Highlight>());
        list = new ArrayList<Highlight>() {{
            add(new TestHighlight("one"));
            add(new TestHighlight("two"));
            add(new TestHighlight("three"));
        }};
        stream = mock(InputStream.class);
    }

    @Test
    public void testCreate() {

        new WrappedHighlighter(mock(Highlighter.class), map);
    }

    @Test
    public void testCreateWithList() {

        new WrappedHighlighter(mock(Highlighter.class), map);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullHighlighter() {

        new WrappedHighlighter(null, map);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullMap() {

        new WrappedHighlighter(mockHighlighter, (Map<String, Highlight>) null);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullList() {

        new WrappedHighlighter(mockHighlighter, (List<Highlight>) null);
    }

    @Test
    public void testHighlightWithInputStreamAndMap() {

        final WrappedHighlighter highlighter = new WrappedHighlighter(mockHighlighter, EMPTY_MAP);

        highlighter.highlight(stream, map);

        verify(mockHighlighter, times(1)).highlight(stream, map);
    }

    @Test
    public void testHighlightWithNullInputStreamAndMap() {

        final WrappedHighlighter highlighter = new WrappedHighlighter(mockHighlighter, EMPTY_MAP);

        highlighter.highlight((InputStream) null, map);

        verify(mockHighlighter, times(1)).highlight(null, map);
    }

    @Test
    public void testHighlightWithInputStreamAndNullMap() {

        final WrappedHighlighter highlighter = new WrappedHighlighter(mockHighlighter, EMPTY_MAP);

        highlighter.highlight(stream, null);

        verify(mockHighlighter, times(1)).highlight(stream, null);
    }

    @Test
    public void testHighlightWithStringAndMap() {

        final WrappedHighlighter highlighter = new WrappedHighlighter(mockHighlighter, EMPTY_MAP);

        when(mockHighlighter.highlight(any(InputStream.class), eq(map))).then(new VerifyString(TEXT));

        highlighter.highlight(TEXT, map);

        verify(mockHighlighter, times(1)).highlight(any(InputStream.class), eq(map));
    }

    @Test(expected = NullPointerException.class)
    public void testHighlightWithNullStringAndMap() {

        final WrappedHighlighter highlighter = new WrappedHighlighter(mockHighlighter, EMPTY_MAP);

        highlighter.highlight((String) null, map);
    }

    @Test
    public void testHighlightWithStringAndNullMap() {

        final WrappedHighlighter highlighter = new WrappedHighlighter(mockHighlighter, map);

        highlighter.highlight(TEXT, null);

        verify(mockHighlighter, times(1)).highlight(any(InputStream.class), eq((Map<String, Highlight>) null));
    }

    @Test
    public void testHighlighterWithMapAndInputStream() {

        final WrappedHighlighter highlighter = new WrappedHighlighter(mockHighlighter, map);

        highlighter.highlight(stream);

        verify(mockHighlighter, times(1)).highlight(stream, map);
    }

    @Test
    public void testHighlighterWithMapAndNullInputStream() {

        final WrappedHighlighter highlighter = new WrappedHighlighter(mockHighlighter, map);

        highlighter.highlight((InputStream) null);

        verify(mockHighlighter, times(1)).highlight(null, map);
    }

    @Test
    public void testHighlighterWithMapAndString() {

        final WrappedHighlighter highlighter = new WrappedHighlighter(mockHighlighter, map);

        when(mockHighlighter.highlight(any(InputStream.class), eq(map))).then(new VerifyString(TEXT));

        highlighter.highlight(TEXT);

        verify(mockHighlighter, times(1)).highlight(any(InputStream.class), eq(map));
    }

    @Test(expected = NullPointerException.class)
    public void testHighlighterWithMapAndNullString() {

        final WrappedHighlighter highlighter = new WrappedHighlighter(mockHighlighter, map);

        highlighter.highlight((String) null);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testHighlighterWithListAndInputStream() {

        final WrappedHighlighter highlighter = new WrappedHighlighter(mockHighlighter, list);

        when(mockHighlighter.highlight(eq(stream), any(Map.class))).then(new Answer<Void>() {

            @Override
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {

                assertThat("the list items are contained in the map.",
                        ((Map<String, Highlight>) invocationOnMock.getArguments()[1]).values(),
                        hasItems(list.toArray(new Highlight[list.size()]))
                );

                return null;
            }
        });

        highlighter.highlight(stream);

        verify(mockHighlighter, times(1)).highlight(any(InputStream.class), any(Map.class));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testHighlighterWithListAndNullInputStream() {

        final WrappedHighlighter highlighter = new WrappedHighlighter(mockHighlighter, list);

        highlighter.highlight((InputStream) null);

        verify(mockHighlighter, times(1)).highlight(eq((InputStream) null), any(Map.class));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testHighlighterWithListAndString() {

        final WrappedHighlighter highlighter = new WrappedHighlighter(mockHighlighter, list);

        when(mockHighlighter.highlight(any(InputStream.class), any(Map.class))).then(new VerifyString(TEXT));

        highlighter.highlight(TEXT);

        verify(mockHighlighter, times(1)).highlight(any(InputStream.class), any(Map.class));
    }

    @Test(expected = NullPointerException.class)
    public void testHighlighterWithListAndNullString() {

        final WrappedHighlighter highlighter = new WrappedHighlighter(mockHighlighter, list);

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

    /**
     * A test implementation of {@code Highlight} that simply populates a name and implements the equality methods to
     * allow it to be placed within a set.
     */
    private static class TestHighlight implements Highlight {

        private final String name;

        private TestHighlight(String name) {

            this.name = name;
        }

        @Override
        public String getName() {

            return name;
        }

        @Override
        public String apply(String string) {

            return null;
        }

        @Override
        public boolean equals(Object o) {

            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            TestHighlight that = (TestHighlight) o;

            return name.equals(that.name);

        }

        @Override
        public int hashCode() {

            return name.hashCode();
        }
    }
}
