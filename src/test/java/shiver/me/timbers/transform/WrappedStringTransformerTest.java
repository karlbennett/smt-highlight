package shiver.me.timbers.transform;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class WrappedStringTransformerTest {

    private static final String TEXT = "this is a test";

    private StringTransformer<Transformation> mockTransformer;
    private Transformations<Transformation> transformations;
    private CompositeStringTransformer<Transformation> stringTransformer;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {

        mockTransformer = mock(StringTransformer.class);
        transformations = mock(Transformations.class);

        stringTransformer = new WrappedStringTransformer<Transformation>(mockTransformer, transformations);
    }

    @After
    public void tearDown() throws Exception {

        verifyNoMoreInteractions(mockTransformer);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testCreate() {

        new WrappedStringTransformer<Transformation>(mock(StringTransformer.class), transformations);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullTransformation() {

        new WrappedStringTransformer<Transformation>(null, transformations);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullTransformations() {

        new WrappedStringTransformer<Transformation>(mockTransformer, null);
    }

    @Test
    public void testTransformationWithStringAndTransformations() {

        stringTransformer.transform(TEXT, transformations);

        verify(mockTransformer, times(1)).transform(TEXT, transformations);
    }

    @Test
    public void testTransformationWithNullStringAndTransformations() {

        stringTransformer.transform(null, transformations);

        verify(mockTransformer, times(1)).transform(null, transformations);
    }

    @Test
    public void testTransformationWithStringAndNullTransformations() {

        stringTransformer.transform(TEXT, null);

        verify(mockTransformer, times(1)).transform(TEXT, null);
    }

    @Test
    public void testTransformationWithString() {

        stringTransformer.transform(TEXT);

        verify(mockTransformer, times(1)).transform(TEXT, transformations);
    }

    @Test
    public void testTransformationWithTransformationsAndNullString() {

        stringTransformer.transform(null);

        verify(mockTransformer, times(1)).transform(null, transformations);
    }
}
