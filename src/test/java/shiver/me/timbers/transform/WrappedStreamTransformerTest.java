package shiver.me.timbers.transform;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class WrappedStreamTransformerTest {

    private StreamTransformer<Transformation> mockTransformer;
    private Transformations<Transformation> transformations;
    private InputStream stream;
    private WrappedStreamTransformer<Transformation> streamTransformer;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {

        mockTransformer = mock(StreamTransformer.class);
        transformations = mock(Transformations.class);
        stream = mock(InputStream.class);

        streamTransformer = new WrappedStreamTransformer<Transformation>(mockTransformer, transformations);
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
    public void testTransformationWithInputStreamAndTransformations() {

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

        streamTransformer.transform(stream, transformations);

        verify(mockTransformer, times(1)).transform(stream, transformations);
    }

    @Test
    public void testTransformationWithTransformationsAndInputStream() {

        streamTransformer.transform(stream);

        verify(mockTransformer, times(1)).transform(stream, transformations);
    }

    @Test
    public void testTransformationWithTransformationsAndNullInputStream() {

        streamTransformer.transform(null);

        verify(mockTransformer, times(1)).transform(null, transformations);
    }
}
