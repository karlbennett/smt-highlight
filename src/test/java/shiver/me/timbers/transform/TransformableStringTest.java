package shiver.me.timbers.transform;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static shiver.me.timbers.transform.TestUtils.EIGHT;
import static shiver.me.timbers.transform.TestUtils.EIGHT_START_INDEX;
import static shiver.me.timbers.transform.TestUtils.EIGHT_STOP_INDEX;
import static shiver.me.timbers.transform.TestUtils.FIVE;
import static shiver.me.timbers.transform.TestUtils.FIVE_START_INDEX;
import static shiver.me.timbers.transform.TestUtils.FIVE_STOP_INDEX;
import static shiver.me.timbers.transform.TestUtils.FOUR;
import static shiver.me.timbers.transform.TestUtils.FOUR_START_INDEX;
import static shiver.me.timbers.transform.TestUtils.FOUR_STOP_INDEX;
import static shiver.me.timbers.transform.TestUtils.NINE;
import static shiver.me.timbers.transform.TestUtils.NINE_START_INDEX;
import static shiver.me.timbers.transform.TestUtils.NINE_STOP_INDEX;
import static shiver.me.timbers.transform.TestUtils.NUMBER_TRANSFORMATION_MAP;
import static shiver.me.timbers.transform.TestUtils.NumberTransformation;
import static shiver.me.timbers.transform.TestUtils.ODD_EVEN_TRANSFORMATION_MAP;
import static shiver.me.timbers.transform.TestUtils.ONE;
import static shiver.me.timbers.transform.TestUtils.ONE_START_INDEX;
import static shiver.me.timbers.transform.TestUtils.ONE_STOP_INDEX;
import static shiver.me.timbers.transform.TestUtils.SEVEN;
import static shiver.me.timbers.transform.TestUtils.SEVEN_START_INDEX;
import static shiver.me.timbers.transform.TestUtils.SEVEN_STOP_INDEX;
import static shiver.me.timbers.transform.TestUtils.SIX;
import static shiver.me.timbers.transform.TestUtils.SIX_START_INDEX;
import static shiver.me.timbers.transform.TestUtils.SIX_STOP_INDEX;
import static shiver.me.timbers.transform.TestUtils.TEN;
import static shiver.me.timbers.transform.TestUtils.TEN_START_INDEX;
import static shiver.me.timbers.transform.TestUtils.TEN_STOP_INDEX;
import static shiver.me.timbers.transform.TestUtils.TEST_TEXT;
import static shiver.me.timbers.transform.TestUtils.THREE;
import static shiver.me.timbers.transform.TestUtils.THREE_START_INDEX;
import static shiver.me.timbers.transform.TestUtils.THREE_STOP_INDEX;
import static shiver.me.timbers.transform.TestUtils.TRANSFORMED_SEVEN_TEXT;
import static shiver.me.timbers.transform.TestUtils.TRANSFORMED_TEXT;
import static shiver.me.timbers.transform.TestUtils.TWO;
import static shiver.me.timbers.transform.TestUtils.TWO_START_INDEX;
import static shiver.me.timbers.transform.TestUtils.TWO_STOP_INDEX;

public class TransformableStringTest {

    private TransformableString transformableString;

    @Before
    public void setUp() {

        transformableString = new TransformableString(TEST_TEXT);
    }

    @Test(expected = AssertionError.class)
    public void testCreateWithNull() {

        new TransformableString(null);
    }

    @Test
    public void testIsNewWordWithOldIndex() {

        assertFalse("initial index of 0 and new index of 0 should produce false.", transformableString.isNewWord(0, 0));
    }

    @Test
    public void testIsNewWordWithNewIndex() {

        assertTrue("initial index of 0 and index of 1 should produce true.", transformableString.isNewWord(0, 1));
    }

    @Test
    public void testIsNewWordWithNegativeIndex() {

        assertFalse("initial index of 0 and negative index should produce false.", transformableString.isNewWord(0, -1));
        assertFalse("initial index of 0 and negative index should produce false.", transformableString.isNewWord(0, -2));
        assertFalse("initial index of 0 and negative index should produce false.", transformableString.isNewWord(0, -3));
        assertFalse("initial index of 0 and negative index should produce false.", transformableString.isNewWord(0, -5));
    }

    @Test
    public void testUpdateCurrentOffsetForLongerWord() {

        final int LENGTH = ONE.length();

        transformableString.updateCurrentOffset(LENGTH, LENGTH + 5);

        assertEquals("offset should increase", 5, transformableString.getCurrentOffset());
    }

    @Test
    public void testUpdateCurrentOffsetForMultipleLongerWords() {

        final int LENGTH = ONE.length();

        transformableString.updateCurrentOffset(LENGTH, LENGTH + 5);
        transformableString.updateCurrentOffset(LENGTH, LENGTH + 4);
        transformableString.updateCurrentOffset(LENGTH, LENGTH + 3);

        assertEquals("offset should increase", 3, transformableString.getCurrentOffset());
    }

    @Test
    public void testUpdateCurrentOffsetForSameWord() {

        final int LENGTH = ONE.length();

        transformableString.updateCurrentOffset(LENGTH, LENGTH);

        assertEquals("offset should stay the same", 0, transformableString.getCurrentOffset());
    }

    @Test
    public void testUpdateCurrentOffsetForMultipleSameWords() {

        final int LENGTH = ONE.length();

        transformableString.updateCurrentOffset(LENGTH, LENGTH);
        transformableString.updateCurrentOffset(LENGTH, LENGTH);
        transformableString.updateCurrentOffset(LENGTH, LENGTH);

        assertEquals("offset should stay the same", 0, transformableString.getCurrentOffset());
    }

    @Test
    public void testUpdateCurrentOffsetForShorterWord() {

        final int LENGTH = ONE.length();

        transformableString.updateCurrentOffset(LENGTH, LENGTH - 3);

        assertEquals("offset should decrease", -3, transformableString.getCurrentOffset());
    }

    @Test
    public void testUpdateCurrentOffsetForMultipleShorterWords() {

        final int LENGTH = ONE.length();

        transformableString.updateCurrentOffset(LENGTH, LENGTH - 1);
        transformableString.updateCurrentOffset(LENGTH, LENGTH - 2);
        transformableString.updateCurrentOffset(LENGTH, LENGTH - 3);

        assertEquals("offset should decrease", -3, transformableString.getCurrentOffset());
    }

    @Test
    public void testUpdateOriginalSubstring() {

        transformableString.updateOriginalSubString(ONE_START_INDEX, ONE_STOP_INDEX);

        assertEquals("the original substring should be set correctly.", ONE, transformableString.getOriginalSubString());
    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void testUpdateOriginalSubstringWithInverseIndices() {

        transformableString.updateOriginalSubString(ONE_STOP_INDEX, ONE_START_INDEX);
    }

    @Test
    public void testUpdateActualStartIndexWithNoOffset() {

        transformableString.updateActualStartIndex(0, ONE_START_INDEX);

        assertEquals("with no offset the current and actual start indices should be equal.",
                ONE_START_INDEX, transformableString.getActualStartIndex());
    }

    @Test
    public void testUpdateActualStartIndexWithPositiveOffset() {

        final int OLD_OFFSET = 5;

        transformableString.updateActualStartIndex(OLD_OFFSET, TWO_START_INDEX);

        assertEquals("with a positive offset the actual start index should be equal to the current index plus the old offset.",
                TWO_START_INDEX + OLD_OFFSET, transformableString.getActualStartIndex());
    }

    @Test
    public void testUpdateActualStartIndexWithNegativeOffset() {

        final int OLD_OFFSET = -5;

        transformableString.updateActualStartIndex(OLD_OFFSET, TWO_START_INDEX);

        assertEquals("with a negative offset the actual start index should be equal to the current index plus the old offset.",
                TWO_START_INDEX + OLD_OFFSET, transformableString.getActualStartIndex());
    }

    @Test
    public void testUpdateActualStartIndexWithNegativeOffsetAndZeroCurrentIndex() {

        transformableString.updateActualStartIndex(-5, 0);

        assertThat("a negative offset and a current index of 0 should not a negative actual index.",
                transformableString.getActualStartIndex(), lessThan(0));
    }

    @Test
    public void testUpdateActualStartIndexWithNegativeCurrentIndex() {

        transformableString.updateActualStartIndex(0, -5);

        assertThat("a negative current index should produce a negative actual index.",
                transformableString.getActualStartIndex(), lessThan(0));
    }

    @Test
    public void testUpdateActualStopIndexWithNoOffset() {

        final int LENGTH = ONE.length();

        transformableString.updateActualStopIndex(0, 0, LENGTH);

        assertEquals("with no offset the current and actual stop indices should be equal.",
                LENGTH, transformableString.getActualStopIndex());
    }

    @Test
    public void testUpdateActualStopIndexWithPositiveOffset() {

        final int LENGTH = ONE.length();
        final int OLD_OFFSET = 5;
        final int CURRENT_OFFSET = 5;

        transformableString.updateActualStopIndex(OLD_OFFSET, CURRENT_OFFSET, LENGTH);

        assertEquals("with a positive offset the actual stop index should be equal to the current index plus the offset.",
                OLD_OFFSET + CURRENT_OFFSET + LENGTH, transformableString.getActualStopIndex());
    }

    @Test
    public void testUpdateActualStopIndexWithNegativeOffset() {

        final int LENGTH = ONE.length();
        final int OLD_OFFSET = -5;
        final int CURRENT_OFFSET = -5;

        transformableString.updateActualStopIndex(OLD_OFFSET, CURRENT_OFFSET, LENGTH);

        assertEquals("with a negative offset the actual stop index should be equal to the current index plus the offset.",
                OLD_OFFSET + CURRENT_OFFSET + LENGTH, transformableString.getActualStopIndex());
    }

    @Test
    public void testUpdateActualStopIndexWithNegativeOffsetAndZeroCurrentIndex() {

        transformableString.updateActualStopIndex(-5, -5, 0);

        assertThat("a negative offset and a current index of 0 should produce a negative actual index.",
                transformableString.getActualStopIndex(), lessThan(0));
    }

    @Test
    public void testUpdateActualStopIndexWithNegativeCurrentIndex() {

        transformableString.updateActualStopIndex(0, 0, -5);

        assertThat("a negative current index should produce a negative actual index.",
                transformableString.getActualStopIndex(), lessThan(0));
    }

    @Test
    public void testApplyTransformationToMainStringWithNoOffset() {

        transformableString.applyTransformationToMainString(ONE_START_INDEX, ONE_STOP_INDEX, ONE + "12345");

        assertEquals("one12345 two three four five six seven eight nine ten", transformableString.toString());
    }

    @Test
    public void testApplyTransformationToMainStringWithPositiveCurrentOffset() {

        transformableString.applyTransformationToMainString(ONE_START_INDEX, ONE_STOP_INDEX, ONE + "123");
        transformableString.applyTransformationToMainString(ONE_START_INDEX, ONE_STOP_INDEX + 3, ONE + "12345");

        assertEquals("one12345 two three four five six seven eight nine ten", transformableString.toString());
    }

    @Test
    public void testApplyTransformationToMainStringWithPositiveOldOffset() {

        transformableString.applyTransformationToMainString(ONE_START_INDEX, ONE_STOP_INDEX, ONE + "12345");
        transformableString.applyTransformationToMainString(TWO_START_INDEX + 5, TWO_STOP_INDEX + 5, TWO + "123");

        assertEquals("one12345 two123 three four five six seven eight nine ten", transformableString.toString());
    }

    @Test
    public void testApplyTransformationToMainStringWithPositiveOldOffsetAndNegativeCurrentOffset() {

        transformableString.applyTransformationToMainString(ONE_START_INDEX, ONE_STOP_INDEX, ONE + "12345");
        transformableString.applyTransformationToMainString(TWO_START_INDEX + 5, TWO_STOP_INDEX + 5, TWO + "123");
        transformableString.applyTransformationToMainString(TWO_START_INDEX + 5, TWO_STOP_INDEX + 8, TWO + "1");

        assertEquals("one12345 two1 three four five six seven eight nine ten", transformableString.toString());
    }

    @Test
    public void testTransformSubstringWithTransformationsTwo() {

        applyTransformation(SEVEN_START_INDEX, SEVEN_STOP_INDEX, SEVEN);

        assertEquals("transformed string should only contain a modified seven.", TRANSFORMED_SEVEN_TEXT,
                transformableString.toString());
    }

    @Test
    public void testTransformSubstringWithTransformationsOnAllNumbers() {

        applyTransformation(ONE_START_INDEX, ONE_STOP_INDEX, ONE);
        applyTransformation(TWO_START_INDEX, TWO_STOP_INDEX, TWO);
        applyTransformation(THREE_START_INDEX, THREE_STOP_INDEX, THREE);
        applyTransformation(FOUR_START_INDEX, FOUR_STOP_INDEX, FOUR);
        applyTransformation(FIVE_START_INDEX, FIVE_STOP_INDEX, FIVE);
        applyTransformation(SIX_START_INDEX, SIX_STOP_INDEX, SIX);
        applyTransformation(SEVEN_START_INDEX, SEVEN_STOP_INDEX, SEVEN);
        applyTransformation(EIGHT_START_INDEX, EIGHT_STOP_INDEX, EIGHT);
        applyTransformation(NINE_START_INDEX, NINE_STOP_INDEX, NINE);
        applyTransformation(TEN_START_INDEX, TEN_STOP_INDEX, TEN);


        assertEquals("the transformed string should be correct.", TRANSFORMED_TEXT,
                transformableString.toString());
    }

    @Test(expected = NullPointerException.class)
    public void testTransformSubstringWithNullTransformation() {

        transformableString.transformSubstring(null, ONE_START_INDEX, ONE_START_INDEX);
    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void testTransformSubstringWithInverseIndices() {

        transformableString.transformSubstring(new NumberTransformation(), ONE_STOP_INDEX, ONE_START_INDEX);
    }

    @Test
    public void testLength() {

        assertEquals("the initial length should be the same as the wrapped string.", TEST_TEXT.length(),
                transformableString.length());
    }

    @Test
    public void testLengthAfterTransformation() {

        transformableString.transformSubstring(new NumberTransformation(), ONE_START_INDEX, ONE_STOP_INDEX);

        assertThat("the length should be different to the wrapped string after a transformation.", transformableString.length(),
                not(TEST_TEXT.length()));
    }

    @Test
    public void testCharAt() {

        final int INDEX = 20;

        assertEquals("char should be the same as the wrapped string.", TEST_TEXT.charAt(INDEX), transformableString.charAt(INDEX));
    }

    @Test
    public void testCharAtAfterTransformation() {

        transformableString.transformSubstring(new NumberTransformation(), ONE_START_INDEX, ONE_STOP_INDEX);

        final int INDEX = 20;

        assertThat("char should be different to the wrapped string after a transformation.", transformableString.charAt(INDEX),
                not(TEST_TEXT.charAt(INDEX)));
    }

    @Test
    public void testSubString() {

        final int START = 10;
        final int END = 20;

        assertEquals("sub-sequence should be the same as the wrapped string.", TEST_TEXT.subSequence(START, END),
                transformableString.subSequence(START, END));
    }

    @Test
    public void testSubStringAfterTransformation() {

        transformableString.transformSubstring(new NumberTransformation(), ONE_START_INDEX, ONE_STOP_INDEX);

        final int START = 10;
        final int END = 20;

        assertThat("sub-sequence should be different to the wrapped string after a transformation.",
                transformableString.subSequence(START, END), not(TEST_TEXT.subSequence(START, END)));
    }

    private void applyTransformation(int startIndex, int stopIndex, String substring) {

        transformableString.transformSubstring(new NumberTransformation(), startIndex, stopIndex);
        transformableString.transformSubstring(ODD_EVEN_TRANSFORMATION_MAP.get(substring), startIndex, stopIndex);
        transformableString.transformSubstring(NUMBER_TRANSFORMATION_MAP.get(substring), startIndex, stopIndex);
    }
}
