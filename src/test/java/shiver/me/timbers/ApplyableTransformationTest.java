package shiver.me.timbers;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ApplyableTransformationTest {

    private static final String TEST_TEXT = "some test text.";

    private Applyer applyer;

    @Before
    public void setUp() {

        applyer = mock(Applyer.class);
    }

    @Test
    public void testCreate() {

        new TestApplyableTransformation(applyer);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNull() {

        new TestApplyableTransformation(null);
    }

    @Test
    public void testApply() {

        new TestApplyableTransformation(applyer).apply(TEST_TEXT);

        verify(applyer, times(1)).apply(TEST_TEXT);
    }

    @Test
    public void testApplyWithNull() {

        new TestApplyableTransformation(applyer).apply(null);

        verify(applyer, times(1)).apply(null);
    }

    @Test(expected = Exception.class)
    public void testApplyWithException() {

        when(applyer.apply(TEST_TEXT)).thenThrow(new Exception());

        new TestApplyableTransformation(applyer).apply(TEST_TEXT);
    }

    private static class TestApplyableTransformation extends ApplyableTransformation {

        protected TestApplyableTransformation(Applyer applyer) {
            super(applyer);
        }

        @Override
        public String getName() {

            return getClass().getSimpleName();
        }
    }
}
