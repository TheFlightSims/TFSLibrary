package org.hsqldb.lib.tar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import org.hsqldb.lib.InputStreamInterface;
import org.hsqldb.lib.InputStreamWrapper;

public class DbBackup {
  protected File dbDir;
  
  protected File archiveFile;
  
  protected String instanceName;
  
  protected boolean overWrite = false;
  
  protected boolean abortUponModify = true;
  
  File[] componentFiles;
  
  InputStreamInterface[] componentStreams;
  
  boolean[] existList;
  
  boolean[] ignoreList;
  
  public static void main(String[] paramArrayOfString) throws IOException, TarMalformatException {
    try {
      if (paramArrayOfString.length < 1) {
        System.out.println(RB.DbBackup_syntax.getString(new String[] { DbBackup.class.getName() }));
        System.out.println();
        System.out.println(RB.listing_format.getString());
        System.exit(0);
      } 
      if (paramArrayOfString[0].equals("--save")) {
        boolean bool = (paramArrayOfString.length > 1 && paramArrayOfString[1].equals("--overwrite")) ? true : false;
        if (paramArrayOfString.length != (bool ? 4 : 3))
          throw new IllegalArgumentException(); 
        DbBackup dbBackup = new DbBackup(new File(paramArrayOfString[paramArrayOfString.length - 2]), paramArrayOfString[paramArrayOfString.length - 1]);
        dbBackup.setOverWrite(bool);
        dbBackup.write();
      } else if (paramArrayOfString[0].equals("--list")) {
        if (paramArrayOfString.length < 2)
          throw new IllegalArgumentException(); 
        String[] arrayOfString = null;
        if (paramArrayOfString.length > 2) {
          arrayOfString = new String[paramArrayOfString.length - 2];
          for (byte b = 2; b < paramArrayOfString.length; b++)
            arrayOfString[b - 2] = paramArrayOfString[b]; 
        } 
        (new TarReader(new File(paramArrayOfString[1]), 0, arrayOfString, new Integer(generateBufferBlockValue(new File(paramArrayOfString[1]))), null)).read();
      } else if (paramArrayOfString[0].equals("--extract")) {
        boolean bool1 = (paramArrayOfString.length > 1 && paramArrayOfString[1].equals("--overwrite")) ? true : false;
        byte b = bool1 ? 4 : 3;
        if (paramArrayOfString.length < b)
          throw new IllegalArgumentException(); 
        String[] arrayOfString = null;
        if (paramArrayOfString.length > b) {
          arrayOfString = new String[paramArrayOfString.length - b];
          for (byte b1 = b; b1 < paramArrayOfString.length; b1++)
            arrayOfString[b1 - b] = paramArrayOfString[b1]; 
        } 
        File file = new File(paramArrayOfString[bool1 ? 2 : 1]);
        boolean bool2 = bool1 ? true : true;
        (new TarReader(file, bool2, arrayOfString, new Integer(generateBufferBlockValue(file)), new File(paramArrayOfString[b - 1]))).read();
      } else {
        throw new IllegalArgumentException();
      } 
    } catch (IllegalArgumentException illegalArgumentException) {
      System.out.println(RB.DbBackup_syntaxerr.getString(new String[] { DbBackup.class.getName() }));
      System.exit(2);
    } 
  }
  
  public DbBackup(File paramFile, String paramString) {
    this.archiveFile = paramFile;
    File file = new File(paramString);
    this.dbDir = file.getAbsoluteFile().getParentFile();
    this.instanceName = file.getName();
    this.componentFiles = new File[] { new File(this.dbDir, this.instanceName + ".properties"), new File(this.dbDir, this.instanceName + ".script"), new File(this.dbDir, this.instanceName + ".data"), new File(this.dbDir, this.instanceName + ".backup"), new File(this.dbDir, this.instanceName + ".log"), new File(this.dbDir, this.instanceName + ".lobs") };
    this.componentStreams = new InputStreamInterface[this.componentFiles.length];
    this.existList = new boolean[this.componentFiles.length];
    this.ignoreList = new boolean[this.componentFiles.length];
  }
  
  public DbBackup(File paramFile, String paramString, boolean paramBoolean) {
    this.archiveFile = paramFile;
    File file = new File(paramString);
    this.dbDir = file.getAbsoluteFile().getParentFile();
    this.instanceName = file.getName();
    this.componentFiles = new File[] { new File(this.dbDir, this.instanceName + ".script") };
    this.componentStreams = new InputStreamInterface[this.componentFiles.length];
    this.existList = new boolean[this.componentFiles.length];
    this.ignoreList = new boolean[this.componentFiles.length];
    this.abortUponModify = false;
  }
  
  public void setStream(String paramString, InputStreamInterface paramInputStreamInterface) {
    for (byte b = 0; b < this.componentFiles.length; b++) {
      if (this.componentFiles[b].getName().endsWith(paramString)) {
        this.componentStreams[b] = paramInputStreamInterface;
        break;
      } 
    } 
  }
  
  public void setFileIgnore(String paramString) {
    for (byte b = 0; b < this.componentFiles.length; b++) {
      if (this.componentFiles[b].getName().endsWith(paramString)) {
        this.ignoreList[b] = true;
        break;
      } 
    } 
  }
  
  public void setOverWrite(boolean paramBoolean) {
    this.overWrite = paramBoolean;
  }
  
  public void setAbortUponModify(boolean paramBoolean) {
    this.abortUponModify = paramBoolean;
  }
  
  public boolean getOverWrite() {
    return this.overWrite;
  }
  
  public boolean getAbortUponModify() {
    return this.abortUponModify;
  }
  
  public void write() throws IOException, TarMalformatException {
    long l = (new Date()).getTime();
    checkEssentialFiles();
    TarGenerator tarGenerator = new TarGenerator(this.archiveFile, this.overWrite, new Integer(generateBufferBlockValue(this.componentFiles)));
    for (byte b = 0; b < this.componentFiles.length; b++) {
      boolean bool = (this.componentStreams[b] != null || this.componentFiles[b].exists()) ? true : false;
      if (bool && !this.ignoreList[b])
        if (this.componentStreams[b] == null) {
          tarGenerator.queueEntry(this.componentFiles[b].getName(), this.componentFiles[b]);
          this.existList[b] = true;
        } else {
          tarGenerator.queueEntry(this.componentFiles[b].getName(), this.componentStreams[b]);
        }  
    } 
    tarGenerator.write();
    checkFilesNotChanged(l);
  }
  
  public void writeAsFiles() throws IOException {
    int i = 512 * generateBufferBlockValue(this.componentFiles);
    byte[] arrayOfByte = new byte[i];
    checkEssentialFiles();
    for (byte b = 0; b < this.componentFiles.length; b++) {
      if (!this.ignoreList[b] && this.componentFiles[b].exists()) {
        File file = new File(this.archiveFile, this.componentFiles[b].getName());
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        if (this.componentStreams[b] == null)
          this.componentStreams[b] = (InputStreamInterface)new InputStreamWrapper(new FileInputStream(this.componentFiles[b])); 
        InputStreamInterface inputStreamInterface = this.componentStreams[b];
        while (true) {
          int j = inputStreamInterface.read(arrayOfByte, 0, arrayOfByte.length);
          if (j <= 0) {
            inputStreamInterface.close();
            fileOutputStream.flush();
            fileOutputStream.getFD().sync();
            fileOutputStream.close();
            break;
          } 
          fileOutputStream.write(arrayOfByte, 0, j);
        } 
      } 
    } 
  }
  
  void checkEssentialFiles() throws FileNotFoundException, IllegalStateException {
    if (!this.componentFiles[0].getName().endsWith(".properties"))
      return; 
    for (byte b = 0; b < 2; b++) {
      boolean bool = (this.componentStreams[b] != null || this.componentFiles[b].exists()) ? true : false;
      if (!bool)
        throw new FileNotFoundException(RB.file_missing.getString(new String[] { this.componentFiles[b].getAbsolutePath() })); 
    } 
    if (!this.abortUponModify)
      return; 
    Properties properties = new Properties();
    FileInputStream fileInputStream = null;
    try {
      File file = this.componentFiles[0];
      fileInputStream = new FileInputStream(file);
      properties.load(fileInputStream);
    } catch (IOException iOException) {
      try {
        if (fileInputStream != null)
          fileInputStream.close(); 
      } catch (IOException iOException1) {
      
      } finally {
        fileInputStream = null;
      } 
    } finally {
      try {
        if (fileInputStream != null)
          fileInputStream.close(); 
      } catch (IOException iOException) {
      
      } finally {
        fileInputStream = null;
      } 
    } 
    String str = properties.getProperty("modified");
    if (str != null && (str.equalsIgnoreCase("yes") || str.equalsIgnoreCase("true")))
      throw new IllegalStateException(RB.modified_property.getString(new String[] { str })); 
  }
  
  void checkFilesNotChanged(long paramLong) throws FileNotFoundException {
    if (!this.abortUponModify)
      return; 
    try {
      for (byte b = 0; b < this.componentFiles.length; b++) {
        if (this.componentFiles[b].exists()) {
          if (!this.existList[b])
            throw new FileNotFoundException(RB.file_disappeared.getString(new String[] { this.componentFiles[b].getAbsolutePath() })); 
          if (this.componentFiles[b].lastModified() > paramLong)
            throw new FileNotFoundException(RB.file_changed.getString(new String[] { this.componentFiles[b].getAbsolutePath() })); 
        } else if (this.existList[b]) {
          throw new FileNotFoundException(RB.file_appeared.getString(new String[] { this.componentFiles[b].getAbsolutePath() }));
        } 
      } 
    } catch (IllegalStateException illegalStateException) {
      if (!this.archiveFile.delete())
        System.out.println(RB.cleanup_rmfail.getString(new String[] { this.archiveFile.getAbsolutePath() })); 
      throw illegalStateException;
    } 
  }
  
  protected static int generateBufferBlockValue(File[] paramArrayOfFile) {
    long l = 0L;
    int i;
    for (i = 0; i < paramArrayOfFile.length; i++) {
      if (paramArrayOfFile[i] != null && paramArrayOfFile[i].length() > l)
        l = paramArrayOfFile[i].length(); 
    } 
    i = (int)(l / 5120L);
    return (i < 1) ? 1 : ((i > 40960) ? 40960 : i);
  }
  
  protected static int generateBufferBlockValue(File paramFile) {
    return generateBufferBlockValue(new File[] { paramFile });
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\tar\DbBackup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */