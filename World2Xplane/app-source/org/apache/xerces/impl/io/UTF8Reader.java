package org.apache.xerces.impl.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Locale;
import org.apache.xerces.impl.msg.XMLMessageFormatter;
import org.apache.xerces.util.MessageFormatter;

public class UTF8Reader extends Reader {
  public static final int DEFAULT_BUFFER_SIZE = 2048;
  
  private static final boolean DEBUG_READ = false;
  
  protected final InputStream fInputStream;
  
  protected final byte[] fBuffer;
  
  protected int fOffset;
  
  private int fSurrogate = -1;
  
  private final MessageFormatter fFormatter;
  
  private final Locale fLocale;
  
  public UTF8Reader(InputStream paramInputStream) {
    this(paramInputStream, 2048, (MessageFormatter)new XMLMessageFormatter(), Locale.getDefault());
  }
  
  public UTF8Reader(InputStream paramInputStream, MessageFormatter paramMessageFormatter, Locale paramLocale) {
    this(paramInputStream, 2048, paramMessageFormatter, paramLocale);
  }
  
  public UTF8Reader(InputStream paramInputStream, int paramInt, MessageFormatter paramMessageFormatter, Locale paramLocale) {
    this(paramInputStream, new byte[paramInt], paramMessageFormatter, paramLocale);
  }
  
  public UTF8Reader(InputStream paramInputStream, byte[] paramArrayOfbyte, MessageFormatter paramMessageFormatter, Locale paramLocale) {
    this.fInputStream = paramInputStream;
    this.fBuffer = paramArrayOfbyte;
    this.fFormatter = paramMessageFormatter;
    this.fLocale = paramLocale;
  }
  
  public int read() throws IOException {
    int i = this.fSurrogate;
    if (this.fSurrogate == -1) {
      byte b = 0;
      int j = (b == this.fOffset) ? this.fInputStream.read() : (this.fBuffer[b++] & 0xFF);
      if (j == -1)
        return -1; 
      if (j < 128) {
        i = (char)j;
      } else if ((j & 0xE0) == 192 && (j & 0x1E) != 0) {
        int k = (b == this.fOffset) ? this.fInputStream.read() : (this.fBuffer[b++] & 0xFF);
        if (k == -1)
          expectedByte(2, 2); 
        if ((k & 0xC0) != 128)
          invalidByte(2, 2, k); 
        i = j << 6 & 0x7C0 | k & 0x3F;
      } else if ((j & 0xF0) == 224) {
        int k = (b == this.fOffset) ? this.fInputStream.read() : (this.fBuffer[b++] & 0xFF);
        if (k == -1)
          expectedByte(2, 3); 
        if ((k & 0xC0) != 128 || (j == 237 && k >= 160) || ((j & 0xF) == 0 && (k & 0x20) == 0))
          invalidByte(2, 3, k); 
        int m = (b == this.fOffset) ? this.fInputStream.read() : (this.fBuffer[b++] & 0xFF);
        if (m == -1)
          expectedByte(3, 3); 
        if ((m & 0xC0) != 128)
          invalidByte(3, 3, m); 
        i = j << 12 & 0xF000 | k << 6 & 0xFC0 | m & 0x3F;
      } else if ((j & 0xF8) == 240) {
        int k = (b == this.fOffset) ? this.fInputStream.read() : (this.fBuffer[b++] & 0xFF);
        if (k == -1)
          expectedByte(2, 4); 
        if ((k & 0xC0) != 128 || ((k & 0x30) == 0 && (j & 0x7) == 0))
          invalidByte(2, 3, k); 
        int m = (b == this.fOffset) ? this.fInputStream.read() : (this.fBuffer[b++] & 0xFF);
        if (m == -1)
          expectedByte(3, 4); 
        if ((m & 0xC0) != 128)
          invalidByte(3, 3, m); 
        int n = (b == this.fOffset) ? this.fInputStream.read() : (this.fBuffer[b++] & 0xFF);
        if (n == -1)
          expectedByte(4, 4); 
        if ((n & 0xC0) != 128)
          invalidByte(4, 4, n); 
        int i1 = j << 2 & 0x1C | k >> 4 & 0x3;
        if (i1 > 16)
          invalidSurrogate(i1); 
        int i2 = i1 - 1;
        int i3 = 0xD800 | i2 << 6 & 0x3C0 | k << 2 & 0x3C | m >> 4 & 0x3;
        int i4 = 0xDC00 | m << 6 & 0x3C0 | n & 0x3F;
        i = i3;
        this.fSurrogate = i4;
      } else {
        invalidByte(1, 1, j);
      } 
    } else {
      this.fSurrogate = -1;
    } 
    return i;
  }
  
  public int read(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws IOException {
    int i = paramInt1;
    int j = 0;
    if (this.fOffset == 0) {
      if (paramInt2 > this.fBuffer.length)
        paramInt2 = this.fBuffer.length; 
      if (this.fSurrogate != -1) {
        paramArrayOfchar[i++] = (char)this.fSurrogate;
        this.fSurrogate = -1;
        paramInt2--;
      } 
      j = this.fInputStream.read(this.fBuffer, 0, paramInt2);
      if (j == -1)
        return -1; 
      j += i - paramInt1;
    } else {
      j = this.fOffset;
      this.fOffset = 0;
    } 
    int k = j;
    byte b = 0;
    while (b < k) {
      byte b1 = this.fBuffer[b];
      if (b1 >= 0) {
        paramArrayOfchar[i++] = (char)b1;
        b++;
        continue;
      } 
      break;
    } 
    while (b < k) {
      byte b1 = this.fBuffer[b];
      if (b1 >= 0) {
        paramArrayOfchar[i++] = (char)b1;
      } else {
        int m = b1 & 0xFF;
        if ((m & 0xE0) == 192 && (m & 0x1E) != 0) {
          int n = -1;
          if (++b < k) {
            n = this.fBuffer[b] & 0xFF;
          } else {
            n = this.fInputStream.read();
            if (n == -1) {
              if (i > paramInt1) {
                this.fBuffer[0] = (byte)m;
                this.fOffset = 1;
                return i - paramInt1;
              } 
              expectedByte(2, 2);
            } 
            j++;
          } 
          if ((n & 0xC0) != 128) {
            if (i > paramInt1) {
              this.fBuffer[0] = (byte)m;
              this.fBuffer[1] = (byte)n;
              this.fOffset = 2;
              return i - paramInt1;
            } 
            invalidByte(2, 2, n);
          } 
          int i1 = m << 6 & 0x7C0 | n & 0x3F;
          paramArrayOfchar[i++] = (char)i1;
          j--;
        } else if ((m & 0xF0) == 224) {
          int n = -1;
          if (++b < k) {
            n = this.fBuffer[b] & 0xFF;
          } else {
            n = this.fInputStream.read();
            if (n == -1) {
              if (i > paramInt1) {
                this.fBuffer[0] = (byte)m;
                this.fOffset = 1;
                return i - paramInt1;
              } 
              expectedByte(2, 3);
            } 
            j++;
          } 
          if ((n & 0xC0) != 128 || (m == 237 && n >= 160) || ((m & 0xF) == 0 && (n & 0x20) == 0)) {
            if (i > paramInt1) {
              this.fBuffer[0] = (byte)m;
              this.fBuffer[1] = (byte)n;
              this.fOffset = 2;
              return i - paramInt1;
            } 
            invalidByte(2, 3, n);
          } 
          int i1 = -1;
          if (++b < k) {
            i1 = this.fBuffer[b] & 0xFF;
          } else {
            i1 = this.fInputStream.read();
            if (i1 == -1) {
              if (i > paramInt1) {
                this.fBuffer[0] = (byte)m;
                this.fBuffer[1] = (byte)n;
                this.fOffset = 2;
                return i - paramInt1;
              } 
              expectedByte(3, 3);
            } 
            j++;
          } 
          if ((i1 & 0xC0) != 128) {
            if (i > paramInt1) {
              this.fBuffer[0] = (byte)m;
              this.fBuffer[1] = (byte)n;
              this.fBuffer[2] = (byte)i1;
              this.fOffset = 3;
              return i - paramInt1;
            } 
            invalidByte(3, 3, i1);
          } 
          int i2 = m << 12 & 0xF000 | n << 6 & 0xFC0 | i1 & 0x3F;
          paramArrayOfchar[i++] = (char)i2;
          j -= 2;
        } else if ((m & 0xF8) == 240) {
          int n = -1;
          if (++b < k) {
            n = this.fBuffer[b] & 0xFF;
          } else {
            n = this.fInputStream.read();
            if (n == -1) {
              if (i > paramInt1) {
                this.fBuffer[0] = (byte)m;
                this.fOffset = 1;
                return i - paramInt1;
              } 
              expectedByte(2, 4);
            } 
            j++;
          } 
          if ((n & 0xC0) != 128 || ((n & 0x30) == 0 && (m & 0x7) == 0)) {
            if (i > paramInt1) {
              this.fBuffer[0] = (byte)m;
              this.fBuffer[1] = (byte)n;
              this.fOffset = 2;
              return i - paramInt1;
            } 
            invalidByte(2, 4, n);
          } 
          int i1 = -1;
          if (++b < k) {
            i1 = this.fBuffer[b] & 0xFF;
          } else {
            i1 = this.fInputStream.read();
            if (i1 == -1) {
              if (i > paramInt1) {
                this.fBuffer[0] = (byte)m;
                this.fBuffer[1] = (byte)n;
                this.fOffset = 2;
                return i - paramInt1;
              } 
              expectedByte(3, 4);
            } 
            j++;
          } 
          if ((i1 & 0xC0) != 128) {
            if (i > paramInt1) {
              this.fBuffer[0] = (byte)m;
              this.fBuffer[1] = (byte)n;
              this.fBuffer[2] = (byte)i1;
              this.fOffset = 3;
              return i - paramInt1;
            } 
            invalidByte(3, 4, i1);
          } 
          int i2 = -1;
          if (++b < k) {
            i2 = this.fBuffer[b] & 0xFF;
          } else {
            i2 = this.fInputStream.read();
            if (i2 == -1) {
              if (i > paramInt1) {
                this.fBuffer[0] = (byte)m;
                this.fBuffer[1] = (byte)n;
                this.fBuffer[2] = (byte)i1;
                this.fOffset = 3;
                return i - paramInt1;
              } 
              expectedByte(4, 4);
            } 
            j++;
          } 
          if ((i2 & 0xC0) != 128) {
            if (i > paramInt1) {
              this.fBuffer[0] = (byte)m;
              this.fBuffer[1] = (byte)n;
              this.fBuffer[2] = (byte)i1;
              this.fBuffer[3] = (byte)i2;
              this.fOffset = 4;
              return i - paramInt1;
            } 
            invalidByte(4, 4, i1);
          } 
          int i3 = m << 2 & 0x1C | n >> 4 & 0x3;
          if (i3 > 16)
            invalidSurrogate(i3); 
          int i4 = i3 - 1;
          int i5 = n & 0xF;
          int i6 = i1 & 0x3F;
          int i7 = i2 & 0x3F;
          int i8 = 0xD800 | i4 << 6 & 0x3C0 | i5 << 2 | i6 >> 4;
          int i9 = 0xDC00 | i6 << 6 & 0x3C0 | i7;
          paramArrayOfchar[i++] = (char)i8;
          j -= 2;
          if (j <= paramInt2) {
            paramArrayOfchar[i++] = (char)i9;
          } else {
            this.fSurrogate = i9;
            j--;
          } 
        } else {
          if (i > paramInt1) {
            this.fBuffer[0] = (byte)m;
            this.fOffset = 1;
            return i - paramInt1;
          } 
          invalidByte(1, 1, m);
        } 
      } 
      b++;
    } 
    return j;
  }
  
  public long skip(long paramLong) throws IOException {
    long l = paramLong;
    char[] arrayOfChar = new char[this.fBuffer.length];
    while (true) {
      int i = (arrayOfChar.length < l) ? arrayOfChar.length : (int)l;
      int j = read(arrayOfChar, 0, i);
      if (j > 0) {
        l -= j;
        if (l <= 0L)
          break; 
        continue;
      } 
      break;
    } 
    return paramLong - l;
  }
  
  public boolean ready() throws IOException {
    return false;
  }
  
  public boolean markSupported() {
    return false;
  }
  
  public void mark(int paramInt) throws IOException {
    throw new IOException(this.fFormatter.formatMessage(this.fLocale, "OperationNotSupported", new Object[] { "mark()", "UTF-8" }));
  }
  
  public void reset() throws IOException {
    this.fOffset = 0;
    this.fSurrogate = -1;
  }
  
  public void close() throws IOException {
    this.fInputStream.close();
  }
  
  private void expectedByte(int paramInt1, int paramInt2) throws MalformedByteSequenceException {
    throw new MalformedByteSequenceException(this.fFormatter, this.fLocale, "http://www.w3.org/TR/1998/REC-xml-19980210", "ExpectedByte", new Object[] { Integer.toString(paramInt1), Integer.toString(paramInt2) });
  }
  
  private void invalidByte(int paramInt1, int paramInt2, int paramInt3) throws MalformedByteSequenceException {
    throw new MalformedByteSequenceException(this.fFormatter, this.fLocale, "http://www.w3.org/TR/1998/REC-xml-19980210", "InvalidByte", new Object[] { Integer.toString(paramInt1), Integer.toString(paramInt2) });
  }
  
  private void invalidSurrogate(int paramInt) throws MalformedByteSequenceException {
    throw new MalformedByteSequenceException(this.fFormatter, this.fLocale, "http://www.w3.org/TR/1998/REC-xml-19980210", "InvalidHighSurrogate", new Object[] { Integer.toHexString(paramInt) });
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\io\UTF8Reader.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */