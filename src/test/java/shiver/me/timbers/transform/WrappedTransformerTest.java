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

public class WrappedTransformerTest {

    private static final String TEXT = "this is a test";

    private Transformer<Transformation> mockTransformer;
    private Transformations<Transformation> transformations;
    private InputStream stream;
    private WrappedTransformer<Transformation> wrappedTransformer;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {

        mockTransformer = mock(Transformer.class);
        transformations = mock(Transformations.class);
        stream = mock(InputStream.class);

        wrappedTransformer = new WrappedTransformer<Transformation>(mockTransformer, transformations);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testCreate() {

        new WrappedTransformer<Transformation>(mock(Transformer.class), transformations);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullTransformation() {

        new WrappedTransformer<Transformation>(null, transformations);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullTransformations() {

        new WrappedTransformer<Transformation>(mockTransformer, null);
    }

    @Test
    public void testTransformationWithInputStreamAndTransformations() {

        wrappedTransformer.transform(stream, transformations);

        verify(mockTransformer, times(1)).transform(stream, transformations);
    }

    @Test
    public void testTransformationWithNullInputStreamAndTransformations() {

        wrappedTransformer.transform((InputStream) null, transformations);

        verify(mockTransformer, times(1)).transform(null, transformations);
    }

    @Test
    public void testTransformationWithInputStreamAndNullTransformations() {

        wrappedTransformer.transform(stream, transformations);

        verify(mockTransformer, times(1)).transform(stream, transformations);
    }

    @Test
    public void testTransformationWithStringAndTransformations() {

        when(mockTransformer.transform(any(InputStream.class), eq(transformations))).then(new VerifyString(TEXT));

        wrappedTransformer.transform(TEXT, transformations);

        verify(mockTransformer, times(1)).transform(any(InputStream.class), eq(transformations));
    }

    @Test(expected = NullPointerException.class)
    public void testTransformationWithNullStringAndTransformations() {

        wrappedTransformer.transform((String) null, transformations);
    }

    @Test
    public void testTransformationWithStringAndNullTransformations() {

        wrappedTransformer.transform(TEXT, transformations);

        verify(mockTransformer, times(1)).transform(any(InputStream.class), eq(transformations));
    }

    @Test
    public void testTransformationWithTransformationsAndInputStream() {

        wrappedTransformer.transform(stream);

        verify(mockTransformer, times(1)).transform(stream, transformations);
    }

    @Test
    public void testTransformationWithTransformationsAndNullInputStream() {

        wrappedTransformer.transform((InputStream) null);

        verify(mockTransformer, times(1)).transform(null, transformations);
    }

    @Test
    public void testTransformationWithTransformationsAndString() {

        when(mockTransformer.transform(any(InputStream.class), eq(transformations))).then(new VerifyString(TEXT));

        wrappedTransformer.transform(TEXT);

        verify(mockTransformer, times(1)).transform(any(InputStream.class), eq(transformations));
    }

    @Test(expected = NullPointerException.class)
    public void testTransformationWithTransformationsAndNullString() {

        wrappedTransformer.transform((String) null);
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
