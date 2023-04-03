package org.tukaani.xz;

import java.io.InputStream;
import org.tukaani.xz.simple.SPARC;
import org.tukaani.xz.simple.SimpleFilter;

public class SPARCOptions extends BCJOptions {
  private static final int ALIGNMENT = 4;
  
  public SPARCOptions() {
    super(4);
  }
  
  public FinishableOutputStream getOutputStream(FinishableOutputStream paramFinishableOutputStream) {
    return new SimpleOutputStream(paramFinishableOutputStream, (SimpleFilter)new SPARC(true, this.startOffset));
  }
  
  public InputStream getInputStream(InputStream paramInputStream) {
    return new SimpleInputStream(paramInputStream, (SimpleFilter)new SPARC(false, this.startOffset));
  }
  
  FilterEncoder getFilterEncoder() {
    return new BCJEncoder(this, 9L);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\SPARCOptions.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */