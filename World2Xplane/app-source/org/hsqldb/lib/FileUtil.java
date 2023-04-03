package org.hsqldb.lib;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Random;
import org.hsqldb.lib.java.JavaSystem;

public class FileUtil implements FileAccess {
  private static FileUtil fileUtil = new FileUtil();
  
  private static FileAccessRes fileAccessRes = new FileAccessRes();
  
  public final boolean fsIsIgnoreCase = (new File("A")).equals(new File("a"));
  
  public final boolean fsNormalizesPosixSeparator = (new File("/")).getPath().endsWith(File.separator);
  
  final Random random = new Random(System.currentTimeMillis());
  
  static int discardSuffixLength = 9;
  
  public static FileUtil getFileUtil() {
    return fileUtil;
  }
  
  public static FileAccess getFileAccess(boolean paramBoolean) {
    return paramBoolean ? fileAccessRes : fileUtil;
  }
  
  public boolean isStreamElement(String paramString) {
    return (new File(paramString)).exists();
  }
  
  public InputStream openInputStreamElement(String paramString) throws IOException {
    try {
      return new FileInputStream(new File(paramString));
    } catch (Throwable throwable) {
      throw JavaSystem.toIOException(throwable);
    } 
  }
  
  public void createParentDirs(String paramString) {
    makeParentDirectories(new File(paramString));
  }
  
  public void removeElement(String paramString) {
    if (isStreamElement(paramString))
      delete(paramString); 
  }
  
  public void renameElement(String paramString1, String paramString2) {
    renameWithOverwrite(paramString1, paramString2);
  }
  
  public OutputStream openOutputStreamElement(String paramString) throws IOException {
    return new FileOutputStream(new File(paramString));
  }
  
  public boolean delete(String paramString) {
    return (new File(paramString)).delete();
  }
  
  public void deleteOnExit(File paramFile) {
    paramFile.deleteOnExit();
  }
  
  public boolean exists(String paramString) {
    return (new File(paramString)).exists();
  }
  
  public boolean exists(String paramString, boolean paramBoolean, Class paramClass) {
    return (paramString == null || paramString.length() == 0) ? false : (paramBoolean ? ((null != paramClass.getResource(paramString))) : getFileUtil().exists(paramString));
  }
  
  private boolean renameWithOverwrite(String paramString1, String paramString2) {
    File file = new File(paramString1);
    delete(paramString2);
    boolean bool = file.renameTo(new File(paramString2));
    if (bool)
      return true; 
    System.gc();
    delete(paramString2);
    if (exists(paramString2))
      (new File(paramString2)).renameTo(new File(newDiscardFileName(paramString2))); 
    return file.renameTo(new File(paramString2));
  }
  
  public String absolutePath(String paramString) {
    return (new File(paramString)).getAbsolutePath();
  }
  
  public File canonicalFile(File paramFile) throws IOException {
    return new File(paramFile.getCanonicalPath());
  }
  
  public File canonicalFile(String paramString) throws IOException {
    return new File((new File(paramString)).getCanonicalPath());
  }
  
  public String canonicalPath(File paramFile) throws IOException {
    return paramFile.getCanonicalPath();
  }
  
  public String canonicalPath(String paramString) throws IOException {
    return (new File(paramString)).getCanonicalPath();
  }
  
  public String canonicalOrAbsolutePath(String paramString) {
    try {
      return canonicalPath(paramString);
    } catch (Exception exception) {
      return absolutePath(paramString);
    } 
  }
  
  public void makeParentDirectories(File paramFile) {
    String str = paramFile.getParent();
    if (str != null) {
      (new File(str)).mkdirs();
    } else {
      str = paramFile.getPath();
      int i = str.lastIndexOf('/');
      if (i > 0) {
        str = str.substring(0, i);
        (new File(str)).mkdirs();
      } 
    } 
  }
  
  public static String makeDirectories(String paramString) {
    try {
      File file = new File(paramString);
      file.mkdirs();
      return file.getCanonicalPath();
    } catch (IOException iOException) {
      return null;
    } 
  }
  
  public FileAccess.FileSync getFileSync(OutputStream paramOutputStream) throws IOException {
    return new FileSync((FileOutputStream)paramOutputStream);
  }
  
  public static boolean deleteOrRenameDatabaseFiles(String paramString) {
    DatabaseFilenameFilter databaseFilenameFilter = new DatabaseFilenameFilter(paramString);
    File[] arrayOfFile = databaseFilenameFilter.getExistingFileListInDirectory();
    for (byte b1 = 0; b1 < arrayOfFile.length; b1++)
      arrayOfFile[b1].delete(); 
    File file = new File(databaseFilenameFilter.canonicalFile.getPath() + ".tmp");
    if (file.isDirectory()) {
      File[] arrayOfFile1 = file.listFiles();
      for (byte b = 0; b < arrayOfFile1.length; b++)
        arrayOfFile1[b].delete(); 
      file.delete();
    } 
    arrayOfFile = databaseFilenameFilter.getExistingMainFileSetList();
    if (arrayOfFile.length == 0)
      return true; 
    System.gc();
    byte b2;
    for (b2 = 0; b2 < arrayOfFile.length; b2++)
      arrayOfFile[b2].delete(); 
    arrayOfFile = databaseFilenameFilter.getExistingMainFileSetList();
    for (b2 = 0; b2 < arrayOfFile.length; b2++)
      arrayOfFile[b2].renameTo(new File(newDiscardFileName(arrayOfFile[b2].getPath()))); 
    return true;
  }
  
  public static File[] getDatabaseFileList(String paramString) {
    DatabaseFilenameFilter databaseFilenameFilter = new DatabaseFilenameFilter(paramString);
    return databaseFilenameFilter.getExistingFileListInDirectory();
  }
  
  public static File[] getDatabaseMainFileList(String paramString) {
    DatabaseFilenameFilter databaseFilenameFilter = new DatabaseFilenameFilter(paramString, false);
    return databaseFilenameFilter.getExistingFileListInDirectory();
  }
  
  public static String newDiscardFileName(String paramString) {
    String str = StringUtil.toPaddedString(Integer.toHexString((int)System.currentTimeMillis()), discardSuffixLength - 1, '0', true);
    return paramString + "." + str + ".old";
  }
  
  static class DatabaseFilenameFilter implements FilenameFilter {
    String[] suffixes = new String[] { ".backup", ".properties", ".script", ".data", ".log", ".lobs" };
    
    String[] extraSuffixes = new String[] { ".lck", ".sql.log", ".app.log" };
    
    private String dbName;
    
    private File parent;
    
    private File canonicalFile;
    
    private boolean extraFiles;
    
    DatabaseFilenameFilter(String param1String) {
      this(param1String, true);
    }
    
    DatabaseFilenameFilter(String param1String, boolean param1Boolean) {
      this.canonicalFile = new File(param1String);
      try {
        this.canonicalFile = this.canonicalFile.getCanonicalFile();
      } catch (Exception exception) {}
      this.dbName = this.canonicalFile.getName();
      this.parent = this.canonicalFile.getParentFile();
      this.extraFiles = param1Boolean;
    }
    
    public File[] getCompleteMainFileSetList() {
      File[] arrayOfFile = new File[this.suffixes.length];
      for (byte b = 0; b < this.suffixes.length; b++)
        arrayOfFile[b] = new File(this.canonicalFile.getPath() + this.suffixes[b]); 
      return arrayOfFile;
    }
    
    public File[] getExistingMainFileSetList() {
      File[] arrayOfFile = getCompleteMainFileSetList();
      HsqlArrayList hsqlArrayList = new HsqlArrayList();
      for (byte b = 0; b < arrayOfFile.length; b++) {
        if (arrayOfFile[b].exists())
          hsqlArrayList.add(arrayOfFile[b]); 
      } 
      arrayOfFile = new File[hsqlArrayList.size()];
      hsqlArrayList.toArray(arrayOfFile);
      return arrayOfFile;
    }
    
    public File[] getExistingFileListInDirectory() {
      File[] arrayOfFile = this.parent.listFiles(this);
      return (arrayOfFile == null) ? new File[0] : arrayOfFile;
    }
    
    public boolean accept(File param1File, String param1String) {
      if (this.parent.equals(param1File) && param1String.indexOf(this.dbName) == 0) {
        String str = param1String.substring(this.dbName.length());
        if (this.extraFiles)
          for (byte b1 = 0; b1 < this.extraSuffixes.length; b1++) {
            if (str.equals(this.extraSuffixes[b1]))
              return true; 
          }  
        for (byte b = 0; b < this.suffixes.length; b++) {
          if (str.equals(this.suffixes[b]))
            return true; 
          if (this.extraFiles && str.startsWith(this.suffixes[b]))
            if (param1String.endsWith(".new")) {
              if (str.length() == this.suffixes[b].length() + 4)
                return true; 
            } else if (param1String.endsWith(".old") && str.length() == this.suffixes[b].length() + FileUtil.discardSuffixLength + 4) {
              return true;
            }  
        } 
      } 
      return false;
    }
  }
  
  public static class FileAccessRes implements FileAccess {
    public boolean isStreamElement(String param1String) {
      URL uRL = null;
      try {
        uRL = getClass().getResource(param1String);
        if (uRL == null) {
          ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
          if (classLoader != null)
            uRL = classLoader.getResource(param1String); 
        } 
      } catch (Throwable throwable) {}
      return (uRL != null);
    }
    
    public InputStream openInputStreamElement(String param1String) throws IOException {
      InputStream inputStream = null;
      try {
        inputStream = getClass().getResourceAsStream(param1String);
        if (inputStream == null) {
          ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
          if (classLoader != null)
            inputStream = classLoader.getResourceAsStream(param1String); 
        } 
      } catch (Throwable throwable) {
      
      } finally {
        if (inputStream == null)
          throw new FileNotFoundException(param1String); 
      } 
      return inputStream;
    }
    
    public void createParentDirs(String param1String) {}
    
    public void removeElement(String param1String) {}
    
    public void renameElement(String param1String1, String param1String2) {}
    
    public OutputStream openOutputStreamElement(String param1String) throws IOException {
      throw new IOException();
    }
    
    public FileAccess.FileSync getFileSync(OutputStream param1OutputStream) throws IOException {
      throw new IOException();
    }
  }
  
  public static class FileSync implements FileAccess.FileSync {
    FileDescriptor outDescriptor;
    
    FileSync(FileOutputStream param1FileOutputStream) throws IOException {
      this.outDescriptor = param1FileOutputStream.getFD();
    }
    
    public void sync() throws IOException {
      this.outDescriptor.sync();
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\FileUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */