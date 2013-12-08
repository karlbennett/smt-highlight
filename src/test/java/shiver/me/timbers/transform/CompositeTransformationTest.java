package shiver.me.timbers.transform;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CompositeTransformationTest {

    private static final String NAME = "test_name";

    private static final String TEST_TEXT = "some test text.";

    private Applyer applyer;

    @Before
    public void setUp() {

        applyer = mock(Applyer.class);
    }

    @Test
    public void testCreate() {

        new CompositeTransformation(NAME, applyer);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullApplyer() {

        new CompositeTransformation(NAME, null);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullName() {

        new CompositeTransformation(null, applyer);
    }

    @Test
    public void testGetName() {

        assertEquals("the name should be set correctly.", NAME, new CompositeTransformation(NAME, applyer).getName());
    }

    @Test
    public void testApply() {

        new CompositeTransformation(NAME, applyer).apply(TEST_TEXT);

        verify(applyer, times(1)).apply(TEST_TEXT);
    }

    @Test
    public void testApplyWithNull() {

        new CompositeTransformation(NAME, applyer).apply(null);

        verify(applyer, times(1)).apply(null);
    }

    @Test(expected = Exception.class)
    public void testApplyWithException() {

        when(applyer.apply(TEST_TEXT)).thenThrow(new Exception());

        new CompositeTransformation(NAME, applyer).apply(TEST_TEXT);
    }
}
