package org.tukaani.xz;

import java.io.InputStream;
import org.tukaani.xz.simple.SimpleFilter;
import org.tukaani.xz.simple.X86;

public class X86Options extends BCJOptions {
  private static final int ALIGNMENT = 1;
  
  public X86Options() {
    super(1);
  }
  
  public FinishableOutputStream getOutputStream(FinishableOutputStream paramFinishableOutputStream) {
    return new SimpleOutputStream(paramFinishableOutputStream, (SimpleFilter)new X86(true, this.startOffset));
  }
  
  public InputStream getInputStream(InputStream paramInputStream) {
    return new SimpleInputStream(paramInputStream, (SimpleFilter)new X86(false, this.startOffset));
  }
  
  FilterEncoder getFilterEncoder() {
    return new BCJEncoder(this, 4L);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\X86Options.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */