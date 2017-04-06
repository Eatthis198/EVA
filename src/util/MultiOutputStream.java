package util;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by David on 24.03.2017.
 */
public class MultiOutputStream extends OutputStream {

    private OutputStream streams[];

    public MultiOutputStream(OutputStream... streams) {
        this.streams = new OutputStream[streams.length];
        for (int i = 0; i < streams.length; ++i) {
            this.streams[i] = streams[i];
        }
    }

    @Override
    public void write(int b) throws IOException {
        for (int i = 0; i < streams.length; ++i) {
            streams[i].write(b);
        }
    }

    @Override
    public void write(byte[] b) throws IOException {
        for (int i = 0; i < streams.length; ++i) {
            streams[i].write(b);
        }
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        for (int i = 0; i < streams.length; ++i) {
            streams[i].write(b, off, len);
        }
    }

    @Override
    public void flush() throws IOException {
        for (int i = 0; i < streams.length; ++i) {
            streams[i].flush();
        }
    }

    @Override
    public void close() throws IOException {
        for (int i = 0; i < streams.length; ++i) {
            streams[i].close();
        }
    }

}
