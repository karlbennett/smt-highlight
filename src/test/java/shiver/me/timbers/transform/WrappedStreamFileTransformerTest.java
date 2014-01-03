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

public class WrappedStreamFileTransformerTest {

    private StreamTransformer<Transformation> mockTransformer;
    private Transformations<Transformation> transformations;
    private File file;
    private InputStream stream;

    private CompositeFileTransformer<Transformation> stringTransformer;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {

        mockTransformer = mock(StreamTransformer.class);
        transformations = mock(Transformations.class);
        file = testFile();
        stream = testFileInputStream();

        stringTransformer = new WrappedStreamFileTransformer<Transformation>(mockTransformer, transformations);
    }

    @After
    public void tearDown() throws Exception {

        verifyNoMoreInteractions(mockTransformer);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testCreate() {

        new WrappedStreamFileTransformer<Transformation>(mock(StreamTransformer.class), transformations);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullTransformation() {

        new WrappedStreamFileTransformer<Transformation>(null, transformations);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullTransformations() {

        new WrappedStreamFileTransformer<Transformation>(mockTransformer, null);
    }

    @Test
    public void testTransformationWithStringAndTransformations() {

        when(mockTransformer.transform(any(InputStream.class), eq(transformations))).then(new VerifyStream(stream));

        stringTransformer.transform(file, transformations);

        verify(mockTransformer, times(1)).transform(any(InputStream.class), eq(transformations));
    }

    @Test
    public void testTransformationWithNullStringAndTransformations() {

        stringTransformer.transform(null, transformations);

        verify(mockTransformer, times(1)).transform(null, transformations);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testTransformationWithStringAndNullTransformations() {

        stringTransformer.transform(file, null);

        verify(mockTransformer, times(1)).transform(any(InputStream.class), isNull(Transformations.class));
    }

    @Test
    public void testTransformationWithString() {

        when(mockTransformer.transform(any(InputStream.class), eq(transformations))).then(new VerifyStream(stream));

        stringTransformer.transform(file);

        verify(mockTransformer, times(1)).transform(any(InputStream.class), eq(transformations));
    }

    @Test
    public void testTransformationWithNullString() {

        stringTransformer.transform(null);

        verify(mockTransformer, times(1)).transform(null, transformations);
    }
}
