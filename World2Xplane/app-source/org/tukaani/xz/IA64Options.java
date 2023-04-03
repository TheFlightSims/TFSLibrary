package org.tukaani.xz;

import java.io.InputStream;
import org.tukaani.xz.simple.IA64;
import org.tukaani.xz.simple.SimpleFilter;

public class IA64Options extends BCJOptions {
  private static final int ALIGNMENT = 16;
  
  public IA64Options() {
    super(16);
  }
  
  public FinishableOutputStream getOutputStream(FinishableOutputStream paramFinishableOutputStream) {
    return new SimpleOutputStream(paramFinishableOutputStream, (SimpleFilter)new IA64(true, this.startOffset));
  }
  
  public InputStream getInputStream(InputStream paramInputStream) {
    return new SimpleInputStream(paramInputStream, (SimpleFilter)new IA64(false, this.startOffset));
  }
  
  FilterEncoder getFilterEncoder() {
    return new BCJEncoder(this, 6L);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\IA64Options.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */