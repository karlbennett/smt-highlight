package shiver.me.timbers.transform;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableMap;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shiver.me.timbers.transform.NullTransformation.NULL_TRANSFORMATION;

/**
 * Utility methods and constants to help with creating tests.
 *
 * @author Karl Bennett
 */
public final class TestUtils {

    private TestUtils() {
    }

    public static final String ONE = "one";
    public static final String TWO = "two";
    public static final String THREE = "three";
    public static final String FOUR = "four";
    public static final String FIVE = "five";
    public static final String SIX = "six";
    public static final String SEVEN = "seven";
    public static final String EIGHT = "eight";
    public static final String NINE = "nine";
    public static final String TEN = "ten";

    public static final String[] NAMES = {ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN};

    public static final String TEST_TEXT = ONE + " " + TWO + " " + THREE + " " + FOUR + " " + FIVE + " " + SIX + " " +
            SEVEN + " " + EIGHT + " " + NINE + " " + TEN;
    public static final String TRANSFORMED_SEVEN_TEXT = ONE + " " + TWO + " " + THREE + " " + FOUR + " " + FIVE + " " +
            SIX + " [7][odd][number]" + SEVEN + "[number][odd][7] " + EIGHT + " " + NINE + " " + TEN;

    public static final String TRANSFORMED_TEXT =
            "[1][odd][number]" + ONE + "[number][odd][1] " +
                    "[2][even][number]" + TWO + "[number][even][2] " +
                    "[3][odd][number]" + THREE + "[number][odd][3] " +
                    "[4][even][number]" + FOUR + "[number][even][4] " +
                    "[5][odd][number]" + FIVE + "[number][odd][5] " +
                    "[6][even][number]" + SIX + "[number][even][6] " +
                    "[7][odd][number]" + SEVEN + "[number][odd][7] " +
                    "[8][even][number]" + EIGHT + "[number][even][8] " +
                    "[9][odd][number]" + NINE + "[number][odd][9] " +
                    "[10][even][number]" + TEN + "[number][even][10]";

    public static final int ONE_START_INDEX = 0;
    public static final int ONE_STOP_INDEX = 2;

    public static final int TWO_START_INDEX = 4;
    public static final int TWO_STOP_INDEX = 6;

    public static final int THREE_START_INDEX = 8;
    public static final int THREE_STOP_INDEX = 12;

    public static final int FOUR_START_INDEX = 14;
    public static final int FOUR_STOP_INDEX = 17;

    public static final int FIVE_START_INDEX = 19;
    public static final int FIVE_STOP_INDEX = 22;

    public static final int SIX_START_INDEX = 24;
    public static final int SIX_STOP_INDEX = 26;

    public static final int SEVEN_START_INDEX = 28;
    public static final int SEVEN_STOP_INDEX = 32;

    public static final int EIGHT_START_INDEX = 34;
    public static final int EIGHT_STOP_INDEX = 38;

    public static final int NINE_START_INDEX = 40;
    public static final int NINE_STOP_INDEX = 43;

    public static final int TEN_START_INDEX = 45;
    public static final int TEN_STOP_INDEX = 47;

    public static final Map<String, Transformation> NUMBER_TRANSFORMATION_MAP = unmodifiableMap(
            new HashMap<String, Transformation>() {{
                put(ONE, new OneTransformation());
                put(TWO, new TwoTransformation());
                put(THREE, new ThreeTransformation());
                put(FOUR, new FourTransformation());
                put(FIVE, new FiveTransformation());
                put(SIX, new SixTransformation());
                put(SEVEN, new SevenTransformation());
                put(EIGHT, new EightTransformation());
                put(NINE, new NineTransformation());
                put(TEN, new TenTransformation());
            }}
    );

    public static final Map<String, Transformation> ODD_EVEN_TRANSFORMATION_MAP = unmodifiableMap(
            new HashMap<String, Transformation>() {{
                put(ONE, new OddTransformation());
                put(TWO, new EvenTransformation());
                put(THREE, new OddTransformation());
                put(FOUR, new EvenTransformation());
                put(FIVE, new OddTransformation());
                put(SIX, new EvenTransformation());
                put(SEVEN, new OddTransformation());
                put(EIGHT, new EvenTransformation());
                put(NINE, new OddTransformation());
                put(TEN, new EvenTransformation());
            }}
    );

    public static <T> Iterable<T> mockIterable(T... elements) {

        return mockIterable(asList(elements));
    }

    @SuppressWarnings("unchecked")
    public static <T> Iterable<T> mockIterable(List<T> elements) {

        Iterator<T> iterator = mock(Iterator.class);

        if (0 >= elements.size()) {

            when(iterator.hasNext()).thenReturn(false);

        } else {

            final Boolean[] returns = new Boolean[elements.size()];
            Arrays.fill(returns, true);
            returns[returns.length - 1] = false;

            when(iterator.hasNext()).thenReturn(true, returns);
        }

        when(iterator.next()).thenReturn(elements.get(0),
                elements.subList(1, elements.size()).toArray((T[]) new Object[elements.size() - 1]));

        final Iterable<T> transformationIterable = mock(Iterable.class);
        when(transformationIterable.iterator()).thenReturn(iterator);

        return transformationIterable;
    }

    /**
     * Assert that the returned {@link Transformation} is the {@link shiver.me.timbers.transform.NullTransformation} for the supplied index.
     */
    public static void assertNullTransformation(Transformations transformations, int index) {

        assertEquals("the null Transformation should be returned for the index " + index, NULL_TRANSFORMATION,
                transformations.get(index));
    }

    /**
     * Assert that the returned {@link Transformation} is the {@link shiver.me.timbers.transform.NullTransformation} for the supplied name.
     */
    public static void assertNullTransformation(Transformations transformations, String name) {

        assertEquals("the null Transformation should be returned for the name " + name, NULL_TRANSFORMATION,
                transformations.get(name));
    }

    public static class Wrappingransformation extends CompositeTransformation {

        public Wrappingransformation(final String name) {
            super(name, new Applyer() {

                @Override
                public String apply(String string) {

                    return '[' + name + ']' + string + '[' + name + ']';
                }
            });
        }
    }

    public static class NumberTransformation extends Wrappingransformation {

        public static final String NAME = "number";

        public NumberTransformation() {
            super(NAME);
        }
    }

    public static class OddTransformation extends Wrappingransformation {

        public static final String NAME = "odd";

        public OddTransformation() {
            super(NAME);
        }
    }

    public static class EvenTransformation extends Wrappingransformation {

        public static final String NAME = "even";

        public EvenTransformation() {
            super(NAME);
        }
    }

    public static class OneTransformation extends Wrappingransformation {

        public static final String NAME = "1";

        public OneTransformation() {
            super(NAME);
        }
    }

    public static class TwoTransformation extends Wrappingransformation {

        public static final String NAME = "2";

        public TwoTransformation() {
            super(NAME);
        }
    }

    public static class ThreeTransformation extends Wrappingransformation {

        public static final String NAME = "3";

        public ThreeTransformation() {
            super(NAME);
        }
    }

    public static class FourTransformation extends Wrappingransformation {

        public static final String NAME = "4";

        public FourTransformation() {
            super(NAME);
        }
    }

    public static class FiveTransformation extends Wrappingransformation {

        public static final String NAME = "5";

        public FiveTransformation() {
            super(NAME);
        }
    }

    public static class SixTransformation extends Wrappingransformation {

        public static final String NAME = "6";

        public SixTransformation() {
            super(NAME);
        }
    }

    public static class SevenTransformation extends Wrappingransformation {

        public static final String NAME = "7";

        public SevenTransformation() {
            super(NAME);
        }
    }

    public static class EightTransformation extends Wrappingransformation {

        public static final String NAME = "8";

        public EightTransformation() {
            super(NAME);
        }
    }

    public static class NineTransformation extends Wrappingransformation {

        public static final String NAME = "9";

        public NineTransformation() {
            super(NAME);
        }
    }

    public static class TenTransformation extends Wrappingransformation {

        public static final String NAME = "10";

        public TenTransformation() {
            super(NAME);
        }
    }
}
