package shiver.me.timbers.transform;

import javax.activation.MimeType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.AbstractMap.SimpleEntry;
import static java.util.Arrays.asList;
import static java.util.Map.Entry;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.transform.AbstractTransformer.quietMimeType;
import static shiver.me.timbers.transform.TestUtils.mockMap;

/**
 * Utility methods and constants to help with creating {@code Transformation} tests.
 */
public final class TransformerTestUtils {

    private TransformerTestUtils() {
    }

    public static final MimeType TEXT_X_JAVA_SOURCE = quietMimeType("text", "x-java-source");
    public static final MimeType APPLICATION_JAVA_SCRIPT = quietMimeType("application", "javascript");
    public static final MimeType APPLICATION_X_PYTHON = quietMimeType("application", "x-python");
    public static final MimeType APPLICATION_X_RUBY = quietMimeType("application", "x-ruby");
    public static final MimeType APPLICATION_X_PHP = quietMimeType("application", "x-php");
    public static final MimeType APPLICATION_JSON = quietMimeType("application", "json");
    public static final MimeType TEXT_XML = quietMimeType("text", "xml");
    public static final MimeType TEXT_HTML = quietMimeType("text", "html");
    public static final MimeType TEXT_CSS = quietMimeType("text", "css");
    public static final MimeType TEXT_CSV = quietMimeType("text", "csv");

    public static final List<MimeType> MIME_TYPES = asList(TEXT_X_JAVA_SOURCE, APPLICATION_JAVA_SCRIPT,
            APPLICATION_X_PYTHON, APPLICATION_X_RUBY, APPLICATION_X_PHP, APPLICATION_JSON, TEXT_XML, TEXT_HTML,
            TEXT_CSS, TEXT_CSV);

    public static <I, T extends Transformation> List<Transformer<I, T>> mockTransformerList() {

        return mockTransformerList(MIME_TYPES);
    }

    @SuppressWarnings("unchecked")
    public static <I, T extends Transformation> List<Transformer<I, T>> mockTransformerList(
            Collection<MimeType> mimeTypes) {

        final List<Transformer<I, T>> transformations = new ArrayList<Transformer<I, T>>(mimeTypes.size());

        Transformer<I, T> transformer;
        for (MimeType mimeType : mimeTypes) {

            transformer = mock(Transformer.class);
            when(transformer.getMimeType()).thenReturn(mimeType);

            transformations.add(transformer);
        }

        return transformations;
    }

    @SuppressWarnings("unchecked")
    public static <I, T extends Transformation> Map<MimeType, Transformer<I, T>> mockTransformerMap() {

        return mockMap(new ArrayList<Entry<MimeType, Transformer<I, T>>>() {{

            Entry<MimeType, Transformer<I, T>> entry;

            for (MimeType mimeType : MIME_TYPES) {

                entry = mockTransformerEntry(mimeType);

                add(entry);
            }
        }});
    }

    public static <I, T extends Transformation> Entry<MimeType, Transformer<I, T>> mockTransformerEntry(
            MimeType mimeType) {

        final Transformer<I, T> transformer = mockTransformer(mimeType);

        return new SimpleEntry<MimeType, Transformer<I, T>>(mimeType, transformer);
    }

    public static <I, T extends Transformation> Transformer<I, T> mockTransformer(MimeType mimeType) {

        @SuppressWarnings("unchecked")
        final Transformer<I, T> transformer = mock(Transformer.class);
        when(transformer.getMimeType()).thenReturn(mimeType);

        return transformer;
    }

    public static <T extends Transformer>  void assertNoIterations(Transformers<T> transformers) {

        for (Transformer transformer : transformers) {

            fail("an empty " + Transformers.class.getSimpleName() + " should not iterate. Transformer: " +
                    transformer.getMimeType());
        }
    }

    public static void assertCorrectMimeTypes(Map<MimeType, ?> expected, Container<MimeType, ?> actual) {

        for (MimeType mimeType : MIME_TYPES) {

            assertEquals("element with mimeType " + mimeType + " should be returned correctly.", expected.get(mimeType),
                    actual.get(mimeType));
        }
    }
}
