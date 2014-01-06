package shiver.me.timbers.transform.mapped;

import org.junit.Before;
import org.junit.Test;
import shiver.me.timbers.transform.Container;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static shiver.me.timbers.transform.TestUtils.assertCorrectIndices;
import static shiver.me.timbers.transform.TestUtils.assertCorrectNames;
import static shiver.me.timbers.transform.TestUtils.assertIterableEquals;
import static shiver.me.timbers.transform.TestUtils.assertIteratorEquals;
import static shiver.me.timbers.transform.TestUtils.mockNameMap;

public class MappedContainerTest {

    private static final String NULL_VALUE = "NULL";

    private Map<String, String> map;

    @Before
    public void setUp() {

        map = mockNameMap();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testCreate() {

        new MappedContainer<String, String>(map, NULL_VALUE);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullTransformerMap() {

        new MappedContainer<String, String>(null, NULL_VALUE);
    }

    @Test(expected = AssertionError.class)
    @SuppressWarnings("unchecked")
    public void testCreateWithNullNullTransform() {

        new MappedContainer<String, String>(map, null);
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
