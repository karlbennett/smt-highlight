package shiver.me.timbers.transform;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.transform.FileUtils.testFile;
import static shiver.me.timbers.transform.FileUtils.testFileInputStream;

public class StreamFileTransformerTest {

    private StreamTransformer<Transformation> mockTransformer;
    private Transformations<Transformation> transformations;
    private File file;
    private InputStream stream;

    private FileTransformer<Transformation> fileTransformer;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {

        mockTransformer = mock(StreamTransformer.class);
        transformations = mock(Transformations.class);
        file = testFile();
        stream = testFileInputStream();

        fileTransformer = new StreamFileTransformer<Transformation>(mockTransformer);
    }

    @After
    public void tearDown() throws Exception {

        verifyNoMoreInteractions(mockTransformer);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testCreate() {

        new StreamFileTransformer<Transformation>(mock(StreamTransformer.class));
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullTransformer() {

        new StreamFileTransformer<Transformation>(null);
    }

    @Test
    public void testTransformationWithFileAndTransformations() {

        when(mockTransformer.transform(any(InputStream.class), eq(transformations))).then(new VerifyStream(stream));

        fileTransformer.transform(file, transformations);

        verify(mockTransformer, times(1)).transform(any(InputStream.class), eq(transformations));
    }

    @Test(expected = RuntimeException.class)
    public void testTransformationWithInvalidFileAndTransformations() {

        fileTransformer.transform(new File("this file should not exist"), transformations);
    }

    @Test
    public void testTransformationWithNullFileAndTransformations() {

        fileTransformer.transform(null, transformations);

        verify(mockTransformer, times(1)).transform(null, transformations);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testTransformationWithFileAndNullTransformations() {

        fileTransformer.transform(file, null);

        verify(mockTransformer, times(1)).transform(any(InputStream.class), isNull(Transformations.class));
    }
}
