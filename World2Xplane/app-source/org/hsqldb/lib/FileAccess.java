package org.hsqldb.lib;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface FileAccess {
  public static final int ELEMENT_READ = 1;
  
  public static final int ELEMENT_SEEKABLEREAD = 3;
  
  public static final int ELEMENT_WRITE = 4;
  
  public static final int ELEMENT_READWRITE = 7;
  
  public static final int ELEMENT_TRUNCATE = 8;
  
  InputStream openInputStreamElement(String paramString) throws IOException;
  
  OutputStream openOutputStreamElement(String paramString) throws IOException;
  
  boolean isStreamElement(String paramString);
  
  void createParentDirs(String paramString);
  
  void removeElement(String paramString);
  
  void renameElement(String paramString1, String paramString2);
  
  FileSync getFileSync(OutputStream paramOutputStream) throws IOException;
  
  public static interface FileSync {
    void sync() throws IOException;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\FileAccess.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */