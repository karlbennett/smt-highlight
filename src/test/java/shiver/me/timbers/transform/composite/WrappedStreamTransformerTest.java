package shiver.me.timbers.transform.composite;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shiver.me.timbers.transform.Transformation;
import shiver.me.timbers.transform.Transformations;
import shiver.me.timbers.transform.stream.StreamTransformer;

import javax.activation.MimeType;
import java.io.IOException;
import java.io.InputStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.transform.FileUtils.testFileInputStream;

public class WrappedStreamTransformerTest {

    private StreamTransformer<Transformation> mockTransformer;
    private Transformations<Transformation> transformations;
    private InputStream stream;

    private CompositeStreamTransformer<Transformation> streamTransformer;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {

        final MimeType mimeType = mock(MimeType.class);

        mockTransformer = mock(StreamTransformer.class);
        when(mockTransformer.getMimeType()).thenReturn(mimeType);

        transformations = mock(Transformations.class);
        stream = testFileInputStream();

        streamTransformer = new WrappedStreamTransformer<Transformation>(mockTransformer, transformations);
    }

    @After
    public void tearDown() throws Exception {

        verify(mockTransformer, times(1)).getMimeType();
        verifyNoMoreInteractions(mockTransformer);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testCreate() {

        new WrappedStreamTransformer<Transformation>(streamTransformer, transformations);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullTransformation() {

        new WrappedStreamTransformer<Transformation>(null, transformations);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullTransformations() {

        new WrappedStreamTransformer<Transformation>(streamTransformer, null);
    }

    @Test
    public void testTransformationWithInputStreamAndTransformations() {

        streamTransformer.transform(stream, transformations);

        verify(mockTransformer, times(1)).transform(stream, transformations);
    }

    @Test
    public void testTransformationWithClosedInputStreamAndTransformations() throws IOException {

        stream.close();

        streamTransformer.transform(stream, transformations);

        verify(mockTransformer, times(1)).transform(stream, transformations);
    }

    @Test
    public void testTransformationWithNullInputStreamAndTransformations() {

        streamTransformer.transform(null, transformations);

        verify(mockTransformer, times(1)).transform(null, transformations);
    }

    @Test
    public void testTransformationWithInputStreamAndNullTransformations() {

        streamTransformer.transform(stream, null);

        verify(mockTransformer, times(1)).transform(stream, null);
    }

    @Test
    public void testTransformationWithInputStream() {

        streamTransformer.transform(stream);

        verify(mockTransformer, times(1)).transform(stream, transformations);
    }

    @Test
    public void testTransformationWithClosedInputStream() throws IOException {

        stream.close();

        streamTransformer.transform(stream);

        verify(mockTransformer, times(1)).transform(stream, transformations);
    }

    @Test
    public void testTransformationWithNullInputStream() {

        streamTransformer.transform(null);

        verify(mockTransformer, times(1)).transform(null, transformations);
    }
}
