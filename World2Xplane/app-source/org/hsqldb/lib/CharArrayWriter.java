package org.hsqldb.lib;

import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;

public class CharArrayWriter {
  protected char[] buffer;
  
  protected int count;
  
  public CharArrayWriter(int paramInt) {
    this.buffer = new char[paramInt];
  }
  
  public CharArrayWriter(char[] paramArrayOfchar) {
    this.buffer = paramArrayOfchar;
  }
  
  public CharArrayWriter(Reader paramReader, int paramInt) throws IOException {
    this.buffer = new char[paramInt];
    int i = paramInt;
    while (i > 0) {
      int j = paramReader.read(this.buffer, this.count, i);
      if (j == -1) {
        if (i > 0) {
          paramReader.close();
          throw new EOFException();
        } 
        break;
      } 
      i -= j;
      this.count += j;
    } 
  }
  
  public CharArrayWriter(Reader paramReader) throws IOException {
    this.buffer = new char[128];
    while (true) {
      int i = paramReader.read(this.buffer, this.count, this.buffer.length - this.count);
      if (i == -1)
        break; 
      this.count += i;
      if (this.count == this.buffer.length)
        ensureRoom(this.count * 2); 
    } 
  }
  
  public void write(int paramInt) {
    if (this.count == this.buffer.length)
      ensureRoom(this.count + 1); 
    this.buffer[this.count++] = (char)paramInt;
  }
  
  public int write(Reader paramReader, int paramInt) throws IOException {
    int i = paramInt;
    while (i > 0) {
      int j = paramReader.read(this.buffer, this.count, i);
      if (j == -1)
        break; 
      i -= j;
      this.count += j;
    } 
    return paramInt - i;
  }
  
  void ensureRoom(int paramInt) {
    if (paramInt <= this.buffer.length)
      return; 
    int i;
    for (i = this.buffer.length; i < paramInt; i *= 2);
    char[] arrayOfChar = new char[i];
    System.arraycopy(this.buffer, 0, arrayOfChar, 0, this.count);
    this.buffer = arrayOfChar;
  }
  
  public void write(String paramString, int paramInt1, int paramInt2) {
    ensureRoom(this.count + paramInt2);
    paramString.getChars(paramInt1, paramInt1 + paramInt2, this.buffer, this.count);
    this.count += paramInt2;
  }
  
  public void reset() {
    this.count = 0;
  }
  
  public void reset(char[] paramArrayOfchar) {
    this.count = 0;
    this.buffer = paramArrayOfchar;
  }
  
  public char[] toCharArray() {
    char[] arrayOfChar = new char[this.count];
    System.arraycopy(this.buffer, 0, arrayOfChar, 0, this.count);
    return arrayOfChar;
  }
  
  public char[] getBuffer() {
    return this.buffer;
  }
  
  public int size() {
    return this.count;
  }
  
  public String toString() {
    return new String(this.buffer, 0, this.count);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\CharArrayWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */