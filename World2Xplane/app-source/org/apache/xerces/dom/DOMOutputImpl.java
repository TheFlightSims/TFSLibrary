package org.apache.xerces.dom;

import java.io.OutputStream;
import java.io.Writer;
import org.w3c.dom.ls.LSOutput;

public class DOMOutputImpl implements LSOutput {
  protected Writer fCharStream = null;
  
  protected OutputStream fByteStream = null;
  
  protected String fSystemId = null;
  
  protected String fEncoding = null;
  
  public Writer getCharacterStream() {
    return this.fCharStream;
  }
  
  public void setCharacterStream(Writer paramWriter) {
    this.fCharStream = paramWriter;
  }
  
  public OutputStream getByteStream() {
    return this.fByteStream;
  }
  
  public void setByteStream(OutputStream paramOutputStream) {
    this.fByteStream = paramOutputStream;
  }
  
  public String getSystemId() {
    return this.fSystemId;
  }
  
  public void setSystemId(String paramString) {
    this.fSystemId = paramString;
  }
  
  public String getEncoding() {
    return this.fEncoding;
  }
  
  public void setEncoding(String paramString) {
    this.fEncoding = paramString;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom\DOMOutputImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */