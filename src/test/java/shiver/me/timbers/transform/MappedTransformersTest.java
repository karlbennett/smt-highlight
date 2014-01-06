package shiver.me.timbers.transform;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static shiver.me.timbers.transform.TestUtils.assertCorrectIndices;
import static shiver.me.timbers.transform.TestUtils.assertCorrectNames;
import static shiver.me.timbers.transform.TestUtils.assertIterableEquals;
import static shiver.me.timbers.transform.TestUtils.assertIteratorEquals;
import static shiver.me.timbers.transform.TestUtils.mockTransformerMap;

public class MappedTransformersTest {

    private static final Transformer<Void, Transformation> NULL_TRANSFORMER =
            new NullTransformer<Void, Transformation>();

    private Map<String, Transformer<Void, Transformation>> transformersMap;

    @Before
    public void setUp() {

        transformersMap = mockTransformerMap();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testCreate() {

        new MappedTransformers<String, Transformer<Void, Transformation>>(transformersMap, NULL_TRANSFORMER);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullTransformerMap() {

        new MappedTransformers<String, Transformer<Void, Transformation>>(null, NULL_TRANSFORMER);
    }

    @Test(expected = AssertionError.class)
    @SuppressWarnings("unchecked")
    public void testCreateWithNullNullTransform() {

        new MappedTransformers<String, Transformer<Void, Transformation>>(transformersMap, null);
    }

    @Test
    public void testGetWithIndex() {

        final Transformers<String, Transformer<Void, Transformation>> transformers =
                new MappedTransformers<String, Transformer<Void, Transformation>>(transformersMap, NULL_TRANSFORMER);

        assertCorrectIndices(new ArrayList<Transformer<Void, Transformation>>(transformersMap.values()), transformers);
    }

    @Test
    public void testGetWithInvalidIndex() {

        final Transformers<String, Transformer<Void, Transformation>> transformers =
                new MappedTransformers<String, Transformer<Void, Transformation>>(transformersMap, NULL_TRANSFORMER);

        assertEquals("the null transformer should be returned for an invalid index.", NULL_TRANSFORMER,
                transformers.get(-1));
        assertEquals("the null transformer should be returned for an invalid index.", NULL_TRANSFORMER,
                transformers.get(transformersMap.size()));
    }

    @Test
    public void testGetWithKey() {

        final Transformers<String, Transformer<Void, Transformation>> transformers =
                new MappedTransformers<String, Transformer<Void, Transformation>>(transformersMap, NULL_TRANSFORMER);

        assertCorrectNames(transformersMap, transformers);
    }

    @Test
    public void testGetWithInvalidKey() {

        final Transformers<String, Transformer<Void, Transformation>> transformers =
                new MappedTransformers<String, Transformer<Void, Transformation>>(transformersMap, NULL_TRANSFORMER);

        assertEquals("the null transformer should be returned for an invalid key.", NULL_TRANSFORMER,
                transformers.get("this key is invalid"));
        assertEquals("the null transformer should be returned for an invalid key.", NULL_TRANSFORMER,
                transformers.get(null));
    }

    @Test
    public void testIterator() {

        assertIteratorEquals(transformersMap.values().iterator(),
                new MappedTransformers<String, Transformer<Void, Transformation>>(transformersMap, NULL_TRANSFORMER)
                        .iterator());
    }

    @Test
    public void testAsCollection() {

        assertIterableEquals(transformersMap.values(),
                new MappedTransformers<String, Transformer<Void, Transformation>>(transformersMap, NULL_TRANSFORMER)
                        .asCollection());
    }
}
