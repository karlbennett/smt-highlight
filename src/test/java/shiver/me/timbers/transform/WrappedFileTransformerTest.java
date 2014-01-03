package shiver.me.timbers.transform;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class WrappedFileTransformerTest {

    private FileTransformer<Transformation> mockTransformer;
    private Transformations<Transformation> transformations;
    private File file;

    private CompositeFileTransformer<Transformation> fileTransformer;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {

        mockTransformer = mock(FileTransformer.class);
        transformations = mock(Transformations.class);
        file = mock(File.class);

        fileTransformer = new WrappedFileTransformer<Transformation>(mockTransformer, transformations);
    }

    @After
    public void tearDown() throws Exception {

        verifyNoMoreInteractions(mockTransformer);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testCreate() {

        new WrappedFileTransformer<Transformation>(mock(FileTransformer.class), transformations);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullTransformation() {

        new WrappedFileTransformer<Transformation>(null, transformations);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullTransformations() {

        new WrappedFileTransformer<Transformation>(mockTransformer, null);
    }

    @Test
    public void testTransformationWithFileAndTransformations() {

        fileTransformer.transform(file, transformations);

        verify(mockTransformer, times(1)).transform(file, transformations);
    }

    @Test
    public void testTransformationWithNullFileAndTransformations() {

        fileTransformer.transform(null, transformations);

        verify(mockTransformer, times(1)).transform(null, transformations);
    }

    @Test
    public void testTransformationWithFileAndNullTransformations() {

        fileTransformer.transform(file, null);

        verify(mockTransformer, times(1)).transform(file, null);
    }

    @Test
    public void testTransformationWithFile() {

        fileTransformer.transform(file);

        verify(mockTransformer, times(1)).transform(file, transformations);
    }

    @Test
    public void testTransformationWithNullFile() {

        fileTransformer.transform(null);

        verify(mockTransformer, times(1)).transform(null, transformations);
    }
}
