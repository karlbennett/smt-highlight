package shiver.me.timbers.transform;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class WrappedStringStreamTransformerTest {

    private static final String TEXT = "this is a test";

    private StringTransformer<Transformation> mockTransformer;
    private Transformations<Transformation> transformations;
    private InputStream stream;
    private CompositeStreamTransformer<Transformation> streamTransformer;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() throws UnsupportedEncodingException {

        mockTransformer = mock(StringTransformer.class);
        transformations = mock(Transformations.class);
        stream = new ByteArrayInputStream(TEXT.getBytes("UTF-8"));

        streamTransformer = new WrappedStringStreamTransformer<Transformation>(mockTransformer, transformations);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testCreate() {

        new WrappedStringStreamTransformer<Transformation>(mock(StringTransformer.class), transformations);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullTransformation() {

        new WrappedStringStreamTransformer<Transformation>(null, transformations);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullTransformations() {

        new WrappedStringStreamTransformer<Transformation>(mockTransformer, null);
    }

    @Test
    public void testTransformationWithInputStreamAndTransformations() {

        streamTransformer.transform(stream, transformations);

        verify(mockTransformer, times(1)).transform(TEXT, transformations);
    }

    @Test(expected = RuntimeException.class)
    public void testTransformationWithClosedInputStreamAndTransformations() throws IOException {

        final InputStream closedStream = new BufferedInputStream(stream);
        closedStream.close();

        streamTransformer.transform(closedStream, transformations);
    }

    @Test
    public void testTransformationWithNullInputStreamAndTransformations() {

        streamTransformer.transform(null, transformations);

        verify(mockTransformer, times(1)).transform(null, transformations);
    }

    @Test
    public void testTransformationWithInputStreamAndNullTransformations() {

        streamTransformer.transform(stream, transformations);

        verify(mockTransformer, times(1)).transform(TEXT, transformations);
    }

    @Test
    public void testTransformationWithTransformationsAndInputStream() {

        streamTransformer.transform(stream);

        verify(mockTransformer, times(1)).transform(TEXT, transformations);
    }

    @Test
    public void testTransformationWithTransformationsAndNullInputStream() {

        streamTransformer.transform(null);

        verify(mockTransformer, times(1)).transform(null, transformations);
    }
}
