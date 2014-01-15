package shiver.me.timbers.transform.composite;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shiver.me.timbers.transform.Transformation;
import shiver.me.timbers.transform.Transformations;
import shiver.me.timbers.transform.string.StringTransformer;

import javax.activation.MimeType;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.transform.FileUtils.testFileText;

public class WrappedStringTransformerTest {

    private StringTransformer<Transformation> mockTransformer;
    private Transformations<Transformation> transformations;
    private String string;

    private CompositeStringTransformer<Transformation> stringTransformer;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {

        final MimeType mimeType = mock(MimeType.class);

        mockTransformer = mock(StringTransformer.class);
        when(mockTransformer.getMimeType()).thenReturn(mimeType);

        transformations = mock(Transformations.class);
        string = testFileText();

        stringTransformer = new WrappedStringTransformer<Transformation>(mockTransformer, transformations);
    }

    @After
    public void tearDown() throws Exception {

        verify(mockTransformer, times(1)).getMimeType();
        verifyNoMoreInteractions(mockTransformer);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testCreate() {

        new WrappedStringTransformer<Transformation>(stringTransformer, transformations);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullTransformation() {

        new WrappedStringTransformer<Transformation>(null, transformations);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullTransformations() {

        new WrappedStringTransformer<Transformation>(stringTransformer, null);
    }

    @Test
    public void testTransformationWithStringAndTransformations() {

        stringTransformer.transform(string, transformations);

        verify(mockTransformer, times(1)).transform(string, transformations);
    }

    @Test
    public void testTransformationWithNullStringAndTransformations() {

        stringTransformer.transform(null, transformations);

        verify(mockTransformer, times(1)).transform(null, transformations);
    }

    @Test
    public void testTransformationWithStringAndNullTransformations() {

        stringTransformer.transform(string, null);

        verify(mockTransformer, times(1)).transform(string, null);
    }

    @Test
    public void testTransformationWithString() {

        stringTransformer.transform(string);

        verify(mockTransformer, times(1)).transform(string, transformations);
    }

    @Test
    public void testTransformationWithTransformationsAndNullString() {

        stringTransformer.transform(null);

        verify(mockTransformer, times(1)).transform(null, transformations);
    }
}
