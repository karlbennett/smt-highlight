package shiver.me.timbers.transform;

import org.apache.commons.io.IOUtils;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.InputStream;

import static org.junit.Assert.assertEquals;

/**
 * Verify that the supplied {@code InputStream} is passed into the mock method.
 */
public class VerifyStream implements Answer<Void> {

    private final InputStream stream;

    /**
     * Create a new {@code VerifyStream} with the expected stream.
     *
     * @param stream the stream that is expected to be passed into the mock method as an {@code InputStream}.
     */
    public VerifyStream(InputStream stream) {

        this.stream = stream;
    }

    @Override
    public Void answer(InvocationOnMock invocationOnMock) throws Throwable {

        assertEquals("the stream should be correct.", IOUtils.toString(stream),
                IOUtils.toString((InputStream) invocationOnMock.getArguments()[0]));

        return null;
    }
}