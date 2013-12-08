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

    private Transformer mockTransformer;
    private Transformations transformations;
    private InputStream stream;

    @Before
    public void setUp() {

        mockTransformer = mock(Transformer.class);
        transformations = mock(Transformations.class);
        stream = mock(InputStream.class);
    }

    @Test
    public void testCreate() {

        new WrappedTransformer(mock(Transformer.class), transformations);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullTransformation() {

        new WrappedTransformer(null, transformations);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullTransformations() {

        new WrappedTransformer(mockTransformer, null);
    }

    @Test
    public void testTransformationWithInputStreamAndTransformations() {

        final WrappedTransformer Transformation = new WrappedTransformer(mockTransformer, transformations);

        Transformation.transform(stream, transformations);

        verify(mockTransformer, times(1)).transform(stream, transformations);
    }

    @Test
    public void testTransformationWithNullInputStreamAndTransformations() {

        final WrappedTransformer Transformation = new WrappedTransformer(mockTransformer, transformations);

        Transformation.transform((InputStream) null, transformations);

        verify(mockTransformer, times(1)).transform(null, transformations);
    }

    @Test
    public void testTransformationWithInputStreamAndNullTransformations() {

        final WrappedTransformer Transformation = new WrappedTransformer(mockTransformer, transformations);

        Transformation.transform(stream, transformations);

        verify(mockTransformer, times(1)).transform(stream, transformations);
    }

    @Test
    public void testTransformationWithStringAndTransformations() {

        final WrappedTransformer Transformation = new WrappedTransformer(mockTransformer, transformations);

        when(mockTransformer.transform(any(InputStream.class), eq(transformations))).then(new VerifyString(TEXT));

        Transformation.transform(TEXT, transformations);

        verify(mockTransformer, times(1)).transform(any(InputStream.class), eq(transformations));
    }

    @Test(expected = NullPointerException.class)
    public void testTransformationWithNullStringAndTransformations() {

        final WrappedTransformer Transformation = new WrappedTransformer(mockTransformer, transformations);

        Transformation.transform((String) null, transformations);
    }

    @Test
    public void testTransformationWithStringAndNullTransformations() {

        final WrappedTransformer Transformation = new WrappedTransformer(mockTransformer, transformations);

        Transformation.transform(TEXT, transformations);

        verify(mockTransformer, times(1)).transform(any(InputStream.class), eq(transformations));
    }

    @Test
    public void testTransformationWithTransformationsAndInputStream() {

        final WrappedTransformer Transformation = new WrappedTransformer(mockTransformer, transformations);

        Transformation.transform(stream);

        verify(mockTransformer, times(1)).transform(stream, transformations);
    }

    @Test
    public void testTransformationWithTransformationsAndNullInputStream() {

        final WrappedTransformer Transformation = new WrappedTransformer(mockTransformer, transformations);

        Transformation.transform((InputStream) null);

        verify(mockTransformer, times(1)).transform(null, transformations);
    }

    @Test
    public void testTransformationWithTransformationsAndString() {

        final WrappedTransformer Transformation = new WrappedTransformer(mockTransformer, transformations);

        when(mockTransformer.transform(any(InputStream.class), eq(transformations))).then(new VerifyString(TEXT));

        Transformation.transform(TEXT);

        verify(mockTransformer, times(1)).transform(any(InputStream.class), eq(transformations));
    }

    @Test(expected = NullPointerException.class)
    public void testTransformationWithTransformationsAndNullString() {

        final WrappedTransformer Transformation = new WrappedTransformer(mockTransformer, transformations);

        Transformation.transform((String) null);
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
