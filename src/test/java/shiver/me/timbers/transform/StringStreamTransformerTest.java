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

public class StringStreamTransformerTest {

    private StringTransformer<Transformation> mockTransformer;
    private Transformations<Transformation> transformations;
    private InputStream stream;
    private String string;

    private StreamTransformer<Transformation> streamTransformer;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() throws UnsupportedEncodingException {

        mockTransformer = mock(StringTransformer.class);
        transformations = mock(Transformations.class);
        stream = testFileInputStream();
        string = testFileText();

        streamTransformer = new StringStreamTransformer<Transformation>(mockTransformer);
    }

    @After
    public void tearDown() throws Exception {

        verifyNoMoreInteractions(mockTransformer);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testCreate() {

        new StringStreamTransformer<Transformation>(mock(StringTransformer.class));
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullTransformer() {

        new StringStreamTransformer<Transformation>(null);
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
}
