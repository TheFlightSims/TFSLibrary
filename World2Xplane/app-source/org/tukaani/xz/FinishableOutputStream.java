package org.tukaani.xz;

import java.io.IOException;
import java.io.OutputStream;

public abstract class FinishableOutputStream extends OutputStream {
  public void finish() throws IOException {}
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\FinishableOutputStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */