package org.hsqldb.lib;

import java.io.IOException;
import java.io.InputStream;

public class StringInputStream extends InputStream {
  protected int strOffset = 0;
  
  protected int charOffset = 0;
  
  protected int available;
  
  protected String str;
  
  public StringInputStream(String paramString) {
    this.str = paramString;
    this.available = paramString.length() * 2;
  }
  
  public int read() throws IOException {
    if (this.available == 0)
      return -1; 
    this.available--;
    char c = this.str.charAt(this.strOffset);
    if (this.charOffset == 0) {
      this.charOffset = 1;
      return (c & 0xFF00) >> 8;
    } 
    this.charOffset = 0;
    this.strOffset++;
    return c & 0xFF;
  }
  
  public int available() throws IOException {
    return this.available;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\StringInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */