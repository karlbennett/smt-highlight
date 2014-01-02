package shiver.me.timbers.transform;

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

public class WrappedStreamStringTransformerTest {

    private static final String TEXT = "this is a test";

    private StreamTransformer<Transformation> mockTransformer;
    private Transformations<Transformation> transformations;
    private CompositeStringTransformer<Transformation> stringTransformer;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {

        mockTransformer = mock(StreamTransformer.class);
        transformations = mock(Transformations.class);

        stringTransformer = new WrappedStreamStringTransformer<Transformation>(mockTransformer, transformations);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testCreate() {

        new WrappedStreamTransformer<Transformation>(mock(StreamTransformer.class), transformations);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullTransformation() {

        new WrappedStreamTransformer<Transformation>(null, transformations);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullTransformations() {

        new WrappedStreamTransformer<Transformation>(mockTransformer, null);
    }

    @Test
    public void testTransformationWithStringAndTransformations() {

        when(mockTransformer.transform(any(InputStream.class), eq(transformations))).then(new VerifyString(TEXT));

        stringTransformer.transform(TEXT, transformations);

        verify(mockTransformer, times(1)).transform(any(InputStream.class), eq(transformations));
    }

    @Test(expected = NullPointerException.class)
    public void testTransformationWithNullStringAndTransformations() {

        stringTransformer.transform(null, transformations);
    }

    @Test
    public void testTransformationWithStringAndNullTransformations() {

        stringTransformer.transform(TEXT, transformations);

        verify(mockTransformer, times(1)).transform(any(InputStream.class), eq(transformations));
    }

    @Test
    public void testTransformationWithTransformationsAndString() {

        when(mockTransformer.transform(any(InputStream.class), eq(transformations))).then(new VerifyString(TEXT));

        stringTransformer.transform(TEXT);

        verify(mockTransformer, times(1)).transform(any(InputStream.class), eq(transformations));
    }

    @Test(expected = NullPointerException.class)
    public void testTransformationWithTransformationsAndNullString() {

        stringTransformer.transform(null);
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
