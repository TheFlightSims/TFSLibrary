package org.hsqldb.persist;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.hsqldb.error.Error;
import org.hsqldb.lib.HsqlByteArrayOutputStream;
import org.hsqldb.rowio.RowInputInterface;
import org.hsqldb.rowio.RowInputText;

public class TextFileReader {
  private RandomAccessInterface dataFile;
  
  private RowInputInterface rowIn;
  
  private TextFileSettings textFileSettings;
  
  private String header;
  
  private boolean isReadOnly;
  
  private HsqlByteArrayOutputStream buffer;
  
  TextFileReader(RandomAccessInterface paramRandomAccessInterface, TextFileSettings paramTextFileSettings, RowInputInterface paramRowInputInterface, boolean paramBoolean) {
    this.dataFile = paramRandomAccessInterface;
    this.textFileSettings = paramTextFileSettings;
    this.rowIn = paramRowInputInterface;
    this.isReadOnly = paramBoolean;
    this.buffer = new HsqlByteArrayOutputStream(128);
  }
  
  public RowInputInterface readObject(long paramLong) {
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    boolean bool4 = false;
    this.buffer.reset();
    paramLong = findNextUsedLinePos(paramLong);
    if (paramLong == -1L)
      return null; 
    try {
      this.dataFile.seek(paramLong);
      while (!bool2) {
        int i = this.dataFile.read();
        bool4 = false;
        if (i == -1) {
          if (this.buffer.size() == 0)
            return null; 
          bool2 = true;
          if (!bool3 && !this.isReadOnly) {
            this.dataFile.write(TextFileSettings.BYTES_LINE_SEP, 0, TextFileSettings.BYTES_LINE_SEP.length);
            this.buffer.write(TextFileSettings.BYTES_LINE_SEP);
          } 
          break;
        } 
        switch (i) {
          case 34:
            bool4 = true;
            bool2 = bool3;
            bool3 = false;
            if (this.textFileSettings.isQuoted)
              bool1 = !bool1 ? true : false; 
            break;
          case 13:
            bool3 = !bool1 ? true : false;
            break;
          case 10:
            bool2 = !bool1 ? true : false;
            break;
          default:
            bool4 = true;
            bool2 = bool3;
            bool3 = false;
            break;
        } 
        this.buffer.write(i);
      } 
      if (bool2) {
        String str;
        if (bool4)
          this.buffer.setPosition(this.buffer.size() - 1); 
        try {
          str = this.buffer.toString(this.textFileSettings.stringEncoding);
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
          str = this.buffer.toString();
        } 
        ((RowInputText)this.rowIn).setSource(str, paramLong, this.buffer.size());
        return this.rowIn;
      } 
      return null;
    } catch (IOException iOException) {
      throw Error.error(484, iOException);
    } 
  }
  
  public int readHeaderLine() {
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    this.buffer.reset();
    try {
      this.dataFile.seek(0L);
    } catch (IOException iOException) {
      throw Error.error(484, iOException);
    } 
    while (!bool1) {
      int i;
      bool3 = false;
      try {
        i = this.dataFile.read();
        if (i == -1) {
          if (this.buffer.size() == 0)
            return 0; 
          bool1 = true;
          if (!this.isReadOnly) {
            this.dataFile.write(TextFileSettings.BYTES_LINE_SEP, 0, TextFileSettings.BYTES_LINE_SEP.length);
            this.buffer.write(TextFileSettings.BYTES_LINE_SEP);
          } 
          break;
        } 
      } catch (IOException iOException) {
        throw Error.error(483);
      } 
      switch (i) {
        case 13:
          bool2 = true;
          break;
        case 10:
          bool1 = true;
          break;
        default:
          bool3 = true;
          bool1 = bool2;
          bool2 = false;
          break;
      } 
      if (bool2 || bool1)
        continue; 
      this.buffer.write(i);
    } 
    if (bool3)
      this.buffer.setPosition(this.buffer.size() - 1); 
    try {
      this.header = this.buffer.toString(this.textFileSettings.stringEncoding);
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      this.header = this.buffer.toString();
    } 
    return this.buffer.size();
  }
  
  private long findNextUsedLinePos(long paramLong) {
    try {
      long l1 = paramLong;
      long l2 = paramLong;
      boolean bool = false;
      this.dataFile.seek(paramLong);
      while (true) {
        int i = this.dataFile.read();
        l2++;
        switch (i) {
          case 13:
            bool = true;
            continue;
          case 10:
            bool = false;
            ((RowInputText)this.rowIn).skippedLine();
            l1 = l2;
            continue;
          case 32:
            if (bool) {
              bool = false;
              ((RowInputText)this.rowIn).skippedLine();
            } 
            continue;
          case -1:
            return -1L;
        } 
        if (bool) {
          bool = false;
          ((RowInputText)this.rowIn).skippedLine();
        } 
        return l1;
      } 
    } catch (IOException iOException) {
      throw Error.error(484, iOException);
    } 
  }
  
  public String getHeaderLine() {
    return this.header;
  }
  
  public int getLineNumber() {
    return ((RowInputText)this.rowIn).getLineNumber();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\TextFileReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */