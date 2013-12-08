package shiver.me.timbers;

import static shiver.me.timbers.Asserts.assertIsNotNull;

/**
 * A character sequence that can have
 *
 * @author Karl Bennett
 */
public class TransformableString implements CharSequence {

    private final StringBuilder transformedMainString;

    private String currentSubString;
    private int currentStartIndex;
    private int oldOffset;
    private int currentOffset;
    private int actualStartIndex;
    private int actualLength;

    public TransformableString(String string) {

        assertIsNotNull(TransformableString.class.getSimpleName() + " string argument cannot be null.", string);

        this.transformedMainString = new StringBuilder(string);

        // Set the initial current start index to -1 so that updatePresets(int,int,String) is called for the first word.
        this.currentStartIndex = -1;
    }

    public void transformSubstring(Transformation transformation, int startIndex, String subString) {

        if (isNewWord(getCurrentStartIndex(), startIndex)) {

            updatePresets(startIndex, subString);
        }

        final int length = subString.length();

        updateActualStartIndex(getOldOffset(), startIndex);
        updateActualLength(getCurrentOffset(), length);

        final String transformedSubString = transformation.apply(getCurrentSubString());

        applyTransformationToMainString(getActualStartIndex(), getActualLength(), transformedSubString);

        setCurrentSubString(transformedSubString);

        updateCurrentOffset(subString.length(), transformedSubString.length());
    }

    private void updatePresets(int startIndex, String subString) {

        setCurrentStartIndex(startIndex);

        updateOldOffset(getCurrentOffset());

        setCurrentSubString(subString);

        updateCurrentOffset(0, 0);
    }

    boolean isNewWord(int currentIndex, int startIndex) {

        return currentIndex < startIndex;
    }

    void updateOldOffset(int currentOffset) {

        oldOffset += currentOffset;
    }

    void updateActualStartIndex(final int oldOffset, final int startIndex) {

        actualStartIndex = oldOffset + startIndex;
    }

    void updateActualLength(final int currentOffset, int length) {

        actualLength = currentOffset + length;
    }

    void applyTransformationToMainString(int actualStartIndex, int length, String transformedSubString) {

        transformedMainString.replace(actualStartIndex, actualStartIndex + length, transformedSubString);
    }

    void updateCurrentOffset(int currentLength, int transformedLength) {

        // Add 1 to the current index difference because we want the offset index to be the next character after the
        // end of the new string length.
        currentOffset = transformedLength - currentLength;
    }

    int getCurrentStartIndex() {

        return currentStartIndex;
    }

    void setCurrentStartIndex(int currentStartIndex) {

        this.currentStartIndex = currentStartIndex;
    }

    String getCurrentSubString() {

        return currentSubString;
    }

    void setCurrentSubString(String currentString) {

        this.currentSubString = currentString;
    }

    int getOldOffset() {

        return oldOffset;
    }

    int getCurrentOffset() {

        return currentOffset;
    }

    int getActualStartIndex() {

        return actualStartIndex;
    }

    int getActualLength() {

        return actualLength;
    }

    @Override
    public int length() {

        return transformedMainString.length();
    }

    @Override
    public char charAt(int index) {

        return transformedMainString.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {

        return transformedMainString.subSequence(start, end);
    }

    @Override
    public String toString() {

        return transformedMainString.toString();
    }
}
