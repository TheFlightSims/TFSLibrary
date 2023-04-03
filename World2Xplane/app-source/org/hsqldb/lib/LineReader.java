package org.hsqldb.lib;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class LineReader {
  boolean finished = false;
  
  boolean wasCR = false;
  
  boolean wasEOL = false;
  
  HsqlByteArrayOutputStream baOS = new HsqlByteArrayOutputStream(1024);
  
  final InputStream stream;
  
  final Charset charset;
  
  final String charsetName;
  
  public LineReader(InputStream paramInputStream, String paramString) {
    this.stream = paramInputStream;
    this.charsetName = paramString;
    this.charset = Charset.forName(paramString);
  }
  
  public String readLine() throws IOException {
    if (this.finished)
      return null; 
    while (true) {
      int i = this.stream.read();
      if (i == -1) {
        this.finished = true;
        if (this.baOS.size() == 0)
          return null; 
        break;
      } 
      switch (i) {
        case 13:
          this.wasCR = true;
          break;
        case 10:
          if (this.wasCR) {
            this.wasCR = false;
            continue;
          } 
          break;
      } 
      this.baOS.write(i);
      this.wasCR = false;
    } 
    String str = new String(this.baOS.getBuffer(), 0, this.baOS.size(), this.charsetName);
    this.baOS.reset();
    return str;
  }
  
  public void close() throws IOException {
    this.stream.close();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\LineReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */