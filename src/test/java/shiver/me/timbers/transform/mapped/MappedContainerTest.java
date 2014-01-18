package shiver.me.timbers.transform.mapped;

import org.junit.Before;
import org.junit.Test;
import shiver.me.timbers.transform.Container;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static shiver.me.timbers.transform.TestUtils.assertCorrectIndices;
import static shiver.me.timbers.transform.TestUtils.assertIterableEquals;
import static shiver.me.timbers.transform.TestUtils.assertIteratorEquals;
import static shiver.me.timbers.transform.TransformationTestUtils.assertCorrectNames;
import static shiver.me.timbers.transform.TransformationTestUtils.mockNameMap;
import static shiver.me.timbers.transform.mapped.MappedContainer.isValidIndex;

public class MappedContainerTest {

    private static final String NULL_VALUE = "NULL";

    private Map<String, String> map;

    @Before
    public void setUp() {

        map = mockNameMap();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testCreateWithNullValue() {

        new MappedContainer<String, String>(NULL_VALUE);
    }

    @Test(expected = AssertionError.class)
    @SuppressWarnings("unchecked")
    public void testCreateWithNullNullValue() {

        new MappedContainer<String, String>(null);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testCreateWithTransformerMapAndNullValue() {

        new MappedContainer<String, String>(map, NULL_VALUE);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullTransformerMapAndNullValue() {

        new MappedContainer<String, String>(null, NULL_VALUE);
    }

    @Test(expected = AssertionError.class)
    @SuppressWarnings("unchecked")
    public void testCreateWithTransformerMapAndNullNullTransform() {

        new MappedContainer<String, String>(map, null);
    }

    @Test
    public void testIsValidIndexWithValidIndices() throws Exception {

        final List<Integer> list = asList(1, 2, 3, 4, 5);

        assertTrue("index should be valid", isValidIndex(list, 0));
        assertTrue("index should be valid", isValidIndex(list, 1));
        assertTrue("index should be valid", isValidIndex(list, 2));
        assertTrue("index should be valid", isValidIndex(list, 3));
        assertTrue("index should be valid", isValidIndex(list, 4));
    }

    @Test
    public void testIsValidIndexWithInvalidIndices() throws Exception {

        final List<Integer> list = asList(1, 2, 3, 4, 5);

        assertFalse("index should be invalid", isValidIndex(list, -1));
        assertFalse("index should be invalid", isValidIndex(list, list.size()));
        assertFalse("index should be invalid", isValidIndex(list, list.size() + 1));
    }

    @Test(expected = NullPointerException.class)
    public void testIsValidIndexWithNullList() throws Exception {

        isValidIndex(null, 0);
    }

    @Test
    public void testGetWithIndex() {

        final Container<String, String> transformers = new MappedContainer<String, String>(map, NULL_VALUE);

        assertCorrectIndices(new ArrayList<String>(map.values()), transformers);
    }

    @Test
    public void testGetWithInvalidIndex() {

        final Container<String, String> transformers = new MappedContainer<String, String>(map, NULL_VALUE);

        assertEquals("the null transformer should be returned for an invalid index.", NULL_VALUE,
                transformers.get(-1));
        assertEquals("the null transformer should be returned for an invalid index.", NULL_VALUE,
                transformers.get(map.size()));
    }

    @Test
    public void testGetWithKey() {

        final Container<String, String> transformers = new MappedContainer<String, String>(map, NULL_VALUE);

        assertCorrectNames(map, transformers);
    }

    @Test
    public void testGetWithInvalidKey() {

        final Container<String, String> transformers = new MappedContainer<String, String>(map, NULL_VALUE);

        assertEquals("the null transformer should be returned for an invalid key.", NULL_VALUE,
                transformers.get("this key is invalid"));
        assertEquals("the null transformer should be returned for an invalid key.", NULL_VALUE,
                transformers.get(null));
    }

    @Test
    public void testIterator() {

        assertIteratorEquals(map.values().iterator(), new MappedContainer<String, String>(map, NULL_VALUE).iterator());
    }

    @Test
    public void testAsCollection() {

        assertIterableEquals(map.values(), new MappedContainer<String, String>(map, NULL_VALUE).asCollection());
    }
}
