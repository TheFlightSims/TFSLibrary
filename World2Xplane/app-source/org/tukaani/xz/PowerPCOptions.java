package org.tukaani.xz;

import java.io.InputStream;
import org.tukaani.xz.simple.PowerPC;
import org.tukaani.xz.simple.SimpleFilter;

public class PowerPCOptions extends BCJOptions {
  private static final int ALIGNMENT = 4;
  
  public PowerPCOptions() {
    super(4);
  }
  
  public FinishableOutputStream getOutputStream(FinishableOutputStream paramFinishableOutputStream) {
    return new SimpleOutputStream(paramFinishableOutputStream, (SimpleFilter)new PowerPC(true, this.startOffset));
  }
  
  public InputStream getInputStream(InputStream paramInputStream) {
    return new SimpleInputStream(paramInputStream, (SimpleFilter)new PowerPC(false, this.startOffset));
  }
  
  FilterEncoder getFilterEncoder() {
    return new BCJEncoder(this, 5L);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\PowerPCOptions.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */