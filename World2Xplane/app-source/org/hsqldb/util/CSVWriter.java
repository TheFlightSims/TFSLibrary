package org.hsqldb.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class CSVWriter {
  private String newline = System.getProperty("line.separator");
  
  private OutputStreamWriter writer = null;
  
  private int nbrCols = 0;
  
  private int nbrRows = 0;
  
  public CSVWriter(File paramFile, String paramString) throws IOException {
    if (paramString == null)
      paramString = System.getProperty("file.encoding"); 
    FileOutputStream fileOutputStream = new FileOutputStream(paramFile);
    this.writer = new OutputStreamWriter(fileOutputStream, paramString);
  }
  
  public void writeHeader(String[] paramArrayOfString) throws IOException {
    this.nbrCols = paramArrayOfString.length;
    doWriteData(paramArrayOfString);
  }
  
  public void writeData(String[] paramArrayOfString) throws IOException {
    doWriteData(paramArrayOfString);
  }
  
  public void close() throws IOException {
    this.writer.close();
  }
  
  private void doWriteData(String[] paramArrayOfString) throws IOException {
    for (byte b = 0; b < paramArrayOfString.length; b++) {
      if (b > 0)
        this.writer.write(";"); 
      if (paramArrayOfString[b] != null) {
        this.writer.write("\"");
        this.writer.write(toCsvValue(paramArrayOfString[b]));
        this.writer.write("\"");
      } 
    } 
    this.writer.write(this.newline);
    this.nbrRows++;
  }
  
  private String toCsvValue(String paramString) {
    StringBuffer stringBuffer = new StringBuffer();
    for (byte b = 0; b < paramString.length(); b++) {
      char c = paramString.charAt(b);
      stringBuffer.append(c);
      switch (c) {
        case '"':
          stringBuffer.append('"');
          break;
      } 
    } 
    return stringBuffer.toString();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqld\\util\CSVWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */