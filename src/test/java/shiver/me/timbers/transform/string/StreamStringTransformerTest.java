package shiver.me.timbers.transform.string;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shiver.me.timbers.transform.Transformation;
import shiver.me.timbers.transform.Transformations;
import shiver.me.timbers.transform.VerifyStream;
import shiver.me.timbers.transform.stream.StreamTransformer;

import javax.activation.MimeType;
import java.io.InputStream;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.transform.FileUtils.testFileInputStream;
import static shiver.me.timbers.transform.FileUtils.testFileText;

public class StreamStringTransformerTest {

    private StreamTransformer<Transformation> mockTransformer;
    private Transformations<Transformation> transformations;
    private InputStream stream;
    private String string;

    private StringTransformer<Transformation> stringTransformer;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {

        mockTransformer = mock(StreamTransformer.class);
        transformations = mock(Transformations.class);
        stream = testFileInputStream();
        string = testFileText();

        stringTransformer = new StreamStringTransformer<Transformation>(mock(MimeType.class), mockTransformer);
    }

    @After
    public void tearDown() throws Exception {

        verifyNoMoreInteractions(mockTransformer);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testCreate() {

        new StreamStringTransformer<Transformation>(mock(MimeType.class), mockTransformer);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullTransformer() {

        new StreamStringTransformer<Transformation>(mock(MimeType.class), null);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullMimeType() {

        new StreamStringTransformer<Transformation>(null, mockTransformer);
    }

    @Test
    public void testTransformationWithStringAndTransformations() {

        when(mockTransformer.transform(any(InputStream.class), eq(transformations))).then(new VerifyStream(stream));

        stringTransformer.transform(string, transformations);

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

        stringTransformer.transform(string, null);

        verify(mockTransformer, times(1)).transform(any(InputStream.class), isNull(Transformations.class));
    }
}
