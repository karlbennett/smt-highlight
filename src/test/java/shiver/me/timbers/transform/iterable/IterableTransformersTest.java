package shiver.me.timbers.transform.iterable;

import org.junit.Before;
import org.junit.Test;
import shiver.me.timbers.transform.NullTransformer;
import shiver.me.timbers.transform.Transformation;
import shiver.me.timbers.transform.Transformer;
import shiver.me.timbers.transform.Transformers;

import javax.activation.MimeType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.transform.TestUtils.assertCorrectIndices;
import static shiver.me.timbers.transform.TestUtils.assertIterableEquals;
import static shiver.me.timbers.transform.TestUtils.assertIteratorEquals;
import static shiver.me.timbers.transform.TransformerTestUtils.assertCorrectMimeTypes;
import static shiver.me.timbers.transform.TransformerTestUtils.assertNoIterations;
import static shiver.me.timbers.transform.TransformerTestUtils.mockTransformerList;
import static shiver.me.timbers.transform.TransformerTestUtils.mockTransformerMap;

public class IterableTransformersTest {

    private static final Transformer<Void, Transformation> NULL_TRANSFORMER =
            new NullTransformer<Void, Transformation>();

    private List<Transformer<Void, Transformation>> transformers;

    @Before
    public void setUp() {

        transformers = mockTransformerList();
    }

    @Test
    public void testCreateWithNullTransformer() {

        assertNoIterations(new IterableTransformers<Transformer<Void, Transformation>>(NULL_TRANSFORMER));
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullNullTransformer() {

        new IterableTransformers<Transformer<Void, Transformation>>(null);
    }

    @Test
    public void testCreateWithIterableAndNullTransformation() {

        new IterableTransformers<Transformer<Void, Transformation>>(transformers, NULL_TRANSFORMER);
    }

    @Test
    public void testCreateWithEmptyIterableAndNullTransformation() {

        assertNoIterations(
                new IterableTransformers<Transformer<Void, Transformation>>(
                        Collections.<Transformer<Void, Transformation>>emptySet(), NULL_TRANSFORMER));
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNullIterableAndNullTransformation() {

        new IterableTransformers<Transformer<Void, Transformation>>(null, NULL_TRANSFORMER);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithIterableAndNullNullTransformer() {

        new IterableTransformers<Transformer<Void, Transformation>>(transformers, null);
    }

    @Test
    public void testGetWithIndex() {

        final Transformers<Transformer<Void, Transformation>> transformers =
                new IterableTransformers<Transformer<Void, Transformation>>(this.transformers, NULL_TRANSFORMER);

        assertCorrectIndices(new ArrayList<Transformer<Void, Transformation>>(this.transformers), transformers);
    }

    @Test
    public void testGetWithInvalidIndex() {

        final Transformers<Transformer<Void, Transformation>> transformers =
                new IterableTransformers<Transformer<Void, Transformation>>(this.transformers, NULL_TRANSFORMER);

        assertEquals("the null transformer should be returned for an invalid index.", NULL_TRANSFORMER,
                transformers.get(-1));
        assertEquals("the null transformer should be returned for an invalid index.", NULL_TRANSFORMER,
                transformers.get(this.transformers.size()));
    }

    @Test
    public void testGetWithKey() {

        final Map<MimeType, Transformer<Void, Transformation>> transformerMap = mockTransformerMap();

        final Transformers<Transformer<Void, Transformation>> transformers =
                new IterableTransformers<Transformer<Void, Transformation>>(transformerMap.values(), NULL_TRANSFORMER);

        assertCorrectMimeTypes(transformerMap, transformers);
    }

    @Test
    public void testGetWithInvalidKey() {

        final Transformers<Transformer<Void, Transformation>> transformers =
                new IterableTransformers<Transformer<Void, Transformation>>(this.transformers, NULL_TRANSFORMER);

        assertEquals("the null transformer should be returned for an invalid key.", NULL_TRANSFORMER,
                transformers.get(mock(MimeType.class)));
        assertEquals("the null transformer should be returned for an invalid key.", NULL_TRANSFORMER,
                transformers.get(null));
    }

    @Test
    public void testIterator() {

        assertIteratorEquals(transformers.iterator(),
                new IterableTransformers<Transformer<Void, Transformation>>(transformers, NULL_TRANSFORMER)
                        .iterator());
    }

    @Test
    public void testAsCollection() {

        assertIterableEquals(transformers,
                new IterableTransformers<Transformer<Void, Transformation>>(transformers, NULL_TRANSFORMER)
                        .asCollection());
    }
}
