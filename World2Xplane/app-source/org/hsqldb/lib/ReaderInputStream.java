package org.hsqldb.lib;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class ReaderInputStream extends InputStream {
  protected Reader reader;
  
  protected long pos;
  
  int lastChar = -1;
  
  public ReaderInputStream(Reader paramReader) {
    this.reader = paramReader;
    this.pos = 0L;
  }
  
  public int read() throws IOException {
    if (this.lastChar >= 0) {
      int i = this.lastChar & 0xFF;
      this.lastChar = -1;
      this.pos++;
      return i;
    } 
    this.lastChar = this.reader.read();
    if (this.lastChar < 0)
      return this.lastChar; 
    this.pos++;
    return this.lastChar >> 8;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\ReaderInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */