package org.tukaani.xz;

import java.io.InputStream;
import org.tukaani.xz.simple.ARMThumb;
import org.tukaani.xz.simple.SimpleFilter;

public class ARMThumbOptions extends BCJOptions {
  private static final int ALIGNMENT = 2;
  
  public ARMThumbOptions() {
    super(2);
  }
  
  public FinishableOutputStream getOutputStream(FinishableOutputStream paramFinishableOutputStream) {
    return new SimpleOutputStream(paramFinishableOutputStream, (SimpleFilter)new ARMThumb(true, this.startOffset));
  }
  
  public InputStream getInputStream(InputStream paramInputStream) {
    return new SimpleInputStream(paramInputStream, (SimpleFilter)new ARMThumb(false, this.startOffset));
  }
  
  FilterEncoder getFilterEncoder() {
    return new BCJEncoder(this, 8L);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\ARMThumbOptions.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */