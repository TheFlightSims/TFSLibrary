package org.tukaani.xz;

import java.io.IOException;
import java.io.InputStream;

public abstract class FilterOptions implements Cloneable {
  public static int getEncoderMemoryUsage(FilterOptions[] paramArrayOfFilterOptions) {
    int i = 0;
    for (byte b = 0; b < paramArrayOfFilterOptions.length; b++)
      i += paramArrayOfFilterOptions[b].getEncoderMemoryUsage(); 
    return i;
  }
  
  public static int getDecoderMemoryUsage(FilterOptions[] paramArrayOfFilterOptions) {
    int i = 0;
    for (byte b = 0; b < paramArrayOfFilterOptions.length; b++)
      i += paramArrayOfFilterOptions[b].getDecoderMemoryUsage(); 
    return i;
  }
  
  public abstract int getEncoderMemoryUsage();
  
  public abstract FinishableOutputStream getOutputStream(FinishableOutputStream paramFinishableOutputStream);
  
  public abstract int getDecoderMemoryUsage();
  
  public abstract InputStream getInputStream(InputStream paramInputStream) throws IOException;
  
  abstract FilterEncoder getFilterEncoder();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\FilterOptions.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */