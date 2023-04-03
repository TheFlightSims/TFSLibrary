package org.apache.commons.configuration;

import java.io.IOException;
import java.io.OutputStream;

abstract class VerifiableOutputStream extends OutputStream {
  public abstract void verify() throws IOException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\VerifiableOutputStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */