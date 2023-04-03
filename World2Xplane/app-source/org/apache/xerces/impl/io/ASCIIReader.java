package org.apache.xerces.impl.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Locale;
import org.apache.xerces.util.MessageFormatter;

public class ASCIIReader extends Reader {
  public static final int DEFAULT_BUFFER_SIZE = 2048;
  
  protected final InputStream fInputStream;
  
  protected final byte[] fBuffer;
  
  private final MessageFormatter fFormatter;
  
  private final Locale fLocale;
  
  public ASCIIReader(InputStream paramInputStream, MessageFormatter paramMessageFormatter, Locale paramLocale) {
    this(paramInputStream, 2048, paramMessageFormatter, paramLocale);
  }
  
  public ASCIIReader(InputStream paramInputStream, int paramInt, MessageFormatter paramMessageFormatter, Locale paramLocale) {
    this(paramInputStream, new byte[paramInt], paramMessageFormatter, paramLocale);
  }
  
  public ASCIIReader(InputStream paramInputStream, byte[] paramArrayOfbyte, MessageFormatter paramMessageFormatter, Locale paramLocale) {
    this.fInputStream = paramInputStream;
    this.fBuffer = paramArrayOfbyte;
    this.fFormatter = paramMessageFormatter;
    this.fLocale = paramLocale;
  }
  
  public int read() throws IOException {
    int i = this.fInputStream.read();
    if (i >= 128)
      throw new MalformedByteSequenceException(this.fFormatter, this.fLocale, "http://www.w3.org/TR/1998/REC-xml-19980210", "InvalidASCII", new Object[] { Integer.toString(i) }); 
    return i;
  }
  
  public int read(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws IOException {
    if (paramInt2 > this.fBuffer.length)
      paramInt2 = this.fBuffer.length; 
    int i = this.fInputStream.read(this.fBuffer, 0, paramInt2);
    for (byte b = 0; b < i; b++) {
      byte b1 = this.fBuffer[b];
      if (b1 < 0)
        throw new MalformedByteSequenceException(this.fFormatter, this.fLocale, "http://www.w3.org/TR/1998/REC-xml-19980210", "InvalidASCII", new Object[] { Integer.toString(b1 & 0xFF) }); 
      paramArrayOfchar[paramInt1 + b] = (char)b1;
    } 
    return i;
  }
  
  public long skip(long paramLong) throws IOException {
    return this.fInputStream.skip(paramLong);
  }
  
  public boolean ready() throws IOException {
    return false;
  }
  
  public boolean markSupported() {
    return this.fInputStream.markSupported();
  }
  
  public void mark(int paramInt) throws IOException {
    this.fInputStream.mark(paramInt);
  }
  
  public void reset() throws IOException {
    this.fInputStream.reset();
  }
  
  public void close() throws IOException {
    this.fInputStream.close();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\io\ASCIIReader.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */