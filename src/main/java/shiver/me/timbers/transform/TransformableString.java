package shiver.me.timbers.transform;

import static shiver.me.timbers.Asserts.assertIsNotNull;

/**
 * A character sequence that can have
 *
 * @author Karl Bennett
 */
public class TransformableString implements CharSequence {

    private final StringBuilder transformedMainString;

    private String originalSubString;
    private String currentSubString;
    private int currentStartIndex;
    private int oldOffset;
    private int currentOffset;
    private int actualStartIndex;
    private int actualStopIndex;

    public TransformableString(String string) {

        assertIsNotNull(TransformableString.class.getSimpleName() + " string argument cannot be null.", string);

        this.transformedMainString = new StringBuilder(string);

        // Set the initial current start index to -1 so that updatePresets(int,int,String) is called for the first word.
        this.currentStartIndex = -1;
    }

    public void transformSubstring(Transformation transformation, int startIndex, int stopIndex) {

        if (isNewWord(getCurrentStartIndex(), startIndex)) {

            updatePresets(startIndex, stopIndex);

        } else {

            updateActualIndices(getOldOffset(), getCurrentOffset(), startIndex, stopIndex);
        }

        final String transformedSubString = transformation.apply(getCurrentSubString());

        applyTransformationToMainString(getActualStartIndex(), getActualStopIndex(), transformedSubString);

        updateCurrentOffset(getOriginalSubString().length(), transformedSubString.length());

        setCurrentSubString(transformedSubString);
    }

    private void updatePresets(int startIndex, int stopIndex) {

        setCurrentStartIndex(startIndex);

        updateOldOffset(getCurrentOffset());
        updateCurrentOffset(0, 0);

        updateActualIndices(getOldOffset(), getCurrentOffset(), startIndex, stopIndex);

        updateOriginalSubString(getActualStartIndex(), getActualStopIndex());
        setCurrentSubString(getOriginalSubString());
    }

    private void updateActualIndices(int oldOffset, int currentOffset, int startIndex, int stopIndex) {

        updateActualStartIndex(oldOffset, startIndex);
        updateActualStopIndex(oldOffset, currentOffset, stopIndex);
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

    void updateActualStopIndex(int oldOffset, int currentOffset, int stopIndex) {

        actualStopIndex = oldOffset + currentOffset + stopIndex;
    }

    void applyTransformationToMainString(int actualStartIndex, int actualStopIndex, String transformedSubString) {

        transformedMainString.replace(actualStartIndex, actualStopIndex + 1, transformedSubString);
    }

    void updateCurrentOffset(int currentLength, int transformedLength) {

        // Add 1 to the current index difference because we want the offset index to be the next character after the
        // end of the new string length.
        currentOffset = transformedLength - currentLength;
    }

    void updateOriginalSubString(int startIndex, int stopIndex) {

        this.originalSubString = transformedMainString.substring(startIndex, stopIndex + 1);
    }

    private int getCurrentStartIndex() {

        return currentStartIndex;
    }

    String getOriginalSubString() {

        return originalSubString;
    }

    private void setCurrentStartIndex(int currentStartIndex) {

        this.currentStartIndex = currentStartIndex;
    }

    private String getCurrentSubString() {

        return currentSubString;
    }

    private void setCurrentSubString(String currentString) {

        this.currentSubString = currentString;
    }

    private int getOldOffset() {

        return oldOffset;
    }

    int getCurrentOffset() {

        return currentOffset;
    }

    int getActualStartIndex() {

        return actualStartIndex;
    }

    int getActualStopIndex() {

        return actualStopIndex;
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
