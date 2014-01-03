package shiver.me.timbers.transform;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static shiver.me.timbers.transform.FileUtils.testFileInputStream;
import static shiver.me.timbers.transform.FileUtils.testFileText;

public class WrappedStringStreamTransformerTest {

    private StringTransformer<Transformation> mockTransformer;
    private Transformations<Transformation> transformations;
    private InputStream stream;
    private String string;

    private CompositeStreamTransformer<Transformation> streamTransformer;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() throws UnsupportedEncodingException {

        mockTransformer = mock(StringTransformer.class);
        transformations = mock(Transformations.class);
        stream = testFileInputStream();
        string = testFileText();

        streamTransformer = new WrappedStringStreamTransformer<Transformation>(mockTransformer, transformations);
    }

    @After
    public void tearDown() throws Exception {

        verifyNoMoreInteractions(mockTransformer);
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

        verify(mockTransformer, times(1)).transform(string, transformations);
    }

    @Test(expected = RuntimeException.class)
    public void testTransformationWithClosedInputStreamAndTransformations() throws IOException {

        stream.close();

        streamTransformer.transform(stream, transformations);
    }

    @Test
    public void testTransformationWithNullInputStreamAndTransformations() {

        streamTransformer.transform(null, transformations);

        verify(mockTransformer, times(1)).transform(null, transformations);
    }

    @Test
    public void testTransformationWithInputStreamAndNullTransformations() {

        streamTransformer.transform(stream, null);

        verify(mockTransformer, times(1)).transform(string, null);
    }

    @Test
    public void testTransformationWithInputStream() {

        streamTransformer.transform(stream);

        verify(mockTransformer, times(1)).transform(string, transformations);
    }

    @Test(expected = RuntimeException.class)
    public void testTransformationWithClosedInputStream() throws IOException {

        stream.close();

        streamTransformer.transform(stream);
    }

    @Test
    public void testTransformationWithNullString() {

        streamTransformer.transform(null);

        verify(mockTransformer, times(1)).transform(null, transformations);
    }
}
