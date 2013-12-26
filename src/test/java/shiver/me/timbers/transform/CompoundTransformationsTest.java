package shiver.me.timbers.transform;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.transform.TestUtils.NAMES;
import static shiver.me.timbers.transform.TestUtils.assertNoIterations;
import static shiver.me.timbers.transform.TestUtils.assertNullTransformation;
import static shiver.me.timbers.transform.TestUtils.assertTransformationsHaveCorrectNamesForIndices;
import static shiver.me.timbers.transform.TestUtils.assertTransformationsHaveCorrectNamesForNames;
import static shiver.me.timbers.transform.TestUtils.mockIterable;

public class CompoundTransformationsTest implements TransformationsTestTemplate {

    private Applyer applyer;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {

        applyer = mock(Applyer.class);
    }

    @Test
    @Override
    public void testCreate() {

        new CompoundTransformations(mockIterable(NAMES), mock(Applyer.class));
    }

    @Test
    @SuppressWarnings("UnusedDeclaration")
    @Override
    public void testCreateWithEmptyIterable() {

        assertNoIterations(new CompoundTransformations(Collections.<String>emptySet(), applyer));
    }

    @Test(expected = AssertionError.class)
    @Override
    public void testCreateWithNullIterable() {

        new CompoundTransformations(null, mock(Applyer.class));
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullApplyer() {

        new CompoundTransformations(mockIterable(NAMES), null);
    }

    @Test
    @Override
    public void testGetWithIndex() {

        assertTransformationsHaveCorrectNamesForIndices(new CompoundTransformations(mockIterable(NAMES), applyer));
    }

    @Test
    @Override
    public void testGetWithInvalidIndex() {

        Transformations transformations = new CompoundTransformations(mockIterable(NAMES), applyer);

        assertNullTransformation(transformations, -1);
        assertNullTransformation(transformations, NAMES.size());
    }

    @Test
    @Override
    public void testGetWithName() {

        assertTransformationsHaveCorrectNamesForNames(new CompoundTransformations(mockIterable(NAMES), applyer));
    }

    @Test
    @Override
    public void testGetWithInvalidName() {

        Transformations transformations = new CompoundTransformations(mockIterable(NAMES), applyer);

        assertNullTransformation(transformations, "not a transformation");
    }

    @Test
    @Override
    public void testGetWithNullName() {

        Transformations transformations = new CompoundTransformations(mockIterable(NAMES), applyer);

        assertNullTransformation(transformations, null);
    }

    @Test
    @Override
    public void testIteratorIsNotNull() {

        assertNotNull("an iterator should be returned",
                new CompoundTransformations(mockIterable(NAMES), applyer).iterator());
    }

    @Test
    public void testApplyer() {

        final String TEST_APPLY_STRING = "this is the apply string.";

        when(applyer.apply(anyString())).thenReturn(TEST_APPLY_STRING);

        Transformations transformations = new CompoundTransformations(mockIterable(NAMES), applyer);

        for (Transformation transformation : transformations) {

            assertEquals("all transformations should produce the same apply result.", TEST_APPLY_STRING,
                    transformation.apply("input string"));
        }
    }
}
