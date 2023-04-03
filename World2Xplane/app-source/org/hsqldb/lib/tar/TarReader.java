package org.hsqldb.lib.tar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import org.hsqldb.lib.StringUtil;

public class TarReader {
  public static final int LIST_MODE = 0;
  
  public static final int EXTRACT_MODE = 1;
  
  public static final int OVERWRITE_MODE = 2;
  
  protected TarFileInputStream archive;
  
  protected Pattern[] patterns = null;
  
  protected int mode;
  
  protected File extractBaseDir;
  
  public static void main(String[] paramArrayOfString) throws IOException, TarMalformatException {
    if (paramArrayOfString.length < 1) {
      System.out.println(RB.TarReader_syntax.getString(new String[] { TarReader.class.getName() }));
      System.out.println(RB.listing_format.getString());
      System.exit(0);
    } 
    File file = (paramArrayOfString.length > 1 && paramArrayOfString[1].startsWith("--directory=")) ? new File(paramArrayOfString[1].substring("--directory=".length())) : null;
    byte b = (file == null) ? 2 : 3;
    if (paramArrayOfString.length < b || (!paramArrayOfString[0].equals("t") && !paramArrayOfString[0].equals("x")))
      throw new IllegalArgumentException(RB.tarreader_syntaxerr.getString(new String[] { TarReader.class.getName() })); 
    String[] arrayOfString = null;
    if (paramArrayOfString.length > b) {
      arrayOfString = new String[paramArrayOfString.length - b];
      for (byte b1 = b; b1 < paramArrayOfString.length; b1++)
        arrayOfString[b1 - b] = paramArrayOfString[b1]; 
    } 
    if (paramArrayOfString[0].equals("t") && file != null)
      throw new IllegalArgumentException(RB.dir_x_conflict.getString()); 
    boolean bool1 = (file == null) ? true : true;
    boolean bool2 = paramArrayOfString[0].equals("t") ? false : true;
    (new TarReader(new File(paramArrayOfString[bool1]), bool2, arrayOfString, null, file)).read();
  }
  
  public TarReader(File paramFile1, int paramInt, String[] paramArrayOfString, Integer paramInteger, File paramFile2) throws IOException {
    this.mode = paramInt;
    File file = paramFile1.getAbsoluteFile();
    this.extractBaseDir = (paramFile2 == null) ? null : paramFile2.getAbsoluteFile();
    boolean bool = false;
    if (file.getName().endsWith(".tgz") || file.getName().endsWith(".gz"))
      bool = true; 
    if (paramArrayOfString != null) {
      this.patterns = new Pattern[paramArrayOfString.length];
      for (byte b = 0; b < paramArrayOfString.length; b++)
        this.patterns[b] = Pattern.compile(paramArrayOfString[b]); 
    } 
    this.archive = (paramInteger == null) ? new TarFileInputStream(file, bool) : new TarFileInputStream(file, bool, paramInteger.intValue());
  }
  
  public void read() throws IOException, TarMalformatException {
    boolean bool = false;
    Long long_ = null;
    String str = null;
    try {
      while (this.archive.readNextHeaderBlock()) {
        TarEntryHeader tarEntryHeader = new TarEntryHeader(this.archive.readBuffer);
        char c = tarEntryHeader.getEntryType();
        if (c == 'x') {
          long_ = getPifData(tarEntryHeader).getSize();
          str = tarEntryHeader.toString();
          continue;
        } 
        if (long_ != null) {
          tarEntryHeader.setDataSize(long_.longValue());
          long_ = null;
        } 
        if (this.patterns != null) {
          boolean bool1 = false;
          for (byte b = 0; b < this.patterns.length; b++) {
            if (this.patterns[b].matcher(tarEntryHeader.getPath()).matches()) {
              bool1 = true;
              break;
            } 
          } 
          if (!bool1) {
            str = null;
            skipFileData(tarEntryHeader);
            continue;
          } 
        } 
        if (c != '\000' && c != '0' && c != 'x')
          bool = true; 
        switch (this.mode) {
          case 0:
            if (str != null)
              System.out.println(str); 
            System.out.println(tarEntryHeader.toString());
            skipFileData(tarEntryHeader);
            break;
          case 1:
          case 2:
            if (str != null)
              System.out.println(str); 
            System.out.println(tarEntryHeader.toString());
            if (c == '\000' || c == '0' || c == 'x') {
              extractFile(tarEntryHeader);
              break;
            } 
            skipFileData(tarEntryHeader);
            break;
          default:
            throw new IllegalArgumentException(RB.unsupported_mode.getString(this.mode));
        } 
        str = null;
      } 
      if (bool)
        System.out.println(RB.unsupported_entry_present.getString()); 
    } catch (IOException iOException) {
      this.archive.close();
      throw iOException;
    } 
  }
  
  protected PIFData getPifData(TarEntryHeader paramTarEntryHeader) throws IOException, TarMalformatException {
    long l = paramTarEntryHeader.getDataSize();
    if (l < 1L)
      throw new TarMalformatException(RB.pif_unknown_datasize.getString()); 
    if (l > 2147483647L)
      throw new TarMalformatException(RB.pif_data_toobig.getString(Long.toString(l), 2147483647)); 
    int i = (int)(l / 512L);
    int j = (int)(l % 512L);
    PipedInputStream pipedInputStream = null;
    PipedOutputStream pipedOutputStream = new PipedOutputStream();
    try {
      pipedInputStream = new PipedInputStream(pipedOutputStream);
      while (i > 0) {
        int k = (i > this.archive.getReadBufferBlocks()) ? this.archive.getReadBufferBlocks() : i;
        this.archive.readBlocks(k);
        i -= k;
        pipedOutputStream.write(this.archive.readBuffer, 0, k * 512);
      } 
      if (j != 0) {
        this.archive.readBlock();
        pipedOutputStream.write(this.archive.readBuffer, 0, j);
      } 
      pipedOutputStream.flush();
    } catch (IOException iOException) {
      if (pipedInputStream != null)
        pipedInputStream.close(); 
      throw iOException;
    } finally {
      try {
        pipedOutputStream.close();
      } finally {
        pipedOutputStream = null;
      } 
    } 
    return new PIFData(pipedInputStream);
  }
  
  protected void extractFile(TarEntryHeader paramTarEntryHeader) throws IOException, TarMalformatException {
    if (paramTarEntryHeader.getDataSize() < 1L)
      throw new TarMalformatException(RB.data_size_unknown.getString()); 
    int i = (int)(paramTarEntryHeader.getDataSize() / 512L);
    int j = (int)(paramTarEntryHeader.getDataSize() % 512L);
    File file1 = paramTarEntryHeader.generateFile();
    if (!file1.isAbsolute())
      file1 = (this.extractBaseDir == null) ? file1.getAbsoluteFile() : new File(this.extractBaseDir, file1.getPath()); 
    File file2 = file1.getParentFile();
    if (file1.exists()) {
      if (this.mode != 2)
        throw new IOException(RB.extraction_exists.getString(new String[] { file1.getAbsolutePath() })); 
      if (!file1.isFile())
        throw new IOException(RB.extraction_exists_notfile.getString(new String[] { file1.getAbsolutePath() })); 
    } 
    if (file2.exists()) {
      if (!file2.isDirectory())
        throw new IOException(RB.extraction_parent_not_dir.getString(new String[] { file2.getAbsolutePath() })); 
      if (!file2.canWrite())
        throw new IOException(RB.extraction_parent_not_writable.getString(new String[] { file2.getAbsolutePath() })); 
    } else if (!file2.mkdirs()) {
      throw new IOException(RB.extraction_parent_mkfail.getString(new String[] { file2.getAbsolutePath() }));
    } 
    int k = paramTarEntryHeader.getFileMode();
    FileOutputStream fileOutputStream = new FileOutputStream(file1);
    try {
      file1.setExecutable(false, false);
      file1.setReadable(false, false);
      file1.setWritable(false, false);
      file1.setExecutable(((k & 0x40) != 0), true);
      file1.setReadable(((k & 0x100) != 0), true);
      file1.setWritable(((k & 0x80) != 0), true);
      while (i > 0) {
        int m = (i > this.archive.getReadBufferBlocks()) ? this.archive.getReadBufferBlocks() : i;
        this.archive.readBlocks(m);
        i -= m;
        fileOutputStream.write(this.archive.readBuffer, 0, m * 512);
      } 
      if (j != 0) {
        this.archive.readBlock();
        fileOutputStream.write(this.archive.readBuffer, 0, j);
      } 
      fileOutputStream.flush();
    } finally {
      try {
        fileOutputStream.close();
      } finally {
        fileOutputStream = null;
      } 
    } 
    file1.setLastModified(paramTarEntryHeader.getModTime() * 1000L);
    if (file1.length() != paramTarEntryHeader.getDataSize())
      throw new IOException(RB.write_count_mismatch.getString(new String[] { Long.toString(paramTarEntryHeader.getDataSize()), file1.getAbsolutePath(), Long.toString(file1.length()) })); 
  }
  
  protected void skipFileData(TarEntryHeader paramTarEntryHeader) throws IOException, TarMalformatException {
    if (paramTarEntryHeader.getDataSize() == 0L)
      return; 
    if (paramTarEntryHeader.getDataSize() < 0L)
      throw new TarMalformatException(RB.data_size_unknown.getString()); 
    byte b = (paramTarEntryHeader.getDataSize() % 512L == 0L) ? 0 : 1;
    int i;
    for (i = (int)(paramTarEntryHeader.getDataSize() / 512L) + b; i > 0; i -= j) {
      int j = (i > this.archive.getReadBufferBlocks()) ? this.archive.getReadBufferBlocks() : i;
      this.archive.readBlocks(j);
    } 
  }
  
  protected static class TarEntryHeader {
    protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    
    protected byte[] rawHeader;
    
    protected String path;
    
    protected int fileMode;
    
    protected long dataSize = -1L;
    
    protected long modTime;
    
    protected char entryType;
    
    protected String ownerName;
    
    protected boolean ustar;
    
    public TarEntryHeader(byte[] param1ArrayOfbyte) throws TarMalformatException {
      this.rawHeader = param1ArrayOfbyte;
      Long long_ = readInteger(TarHeaderField.checksum);
      try {
        if (long_ == null)
          throw new MissingField(TarHeaderField.checksum); 
        long l = headerChecksum();
        if (long_.longValue() != l)
          throw new TarMalformatException(RB.checksum_mismatch.getString(new String[] { long_.toString(), Long.toString(l) })); 
        this.path = readString(TarHeaderField.name);
        if (this.path == null)
          throw new MissingField(TarHeaderField.name); 
        Long long_1 = readInteger(TarHeaderField.mode);
        if (long_1 == null)
          throw new MissingField(TarHeaderField.mode); 
        this.fileMode = (int)long_1.longValue();
        long_1 = readInteger(TarHeaderField.size);
        if (long_1 != null)
          this.dataSize = long_1.longValue(); 
        long_1 = readInteger(TarHeaderField.mtime);
        if (long_1 == null)
          throw new MissingField(TarHeaderField.mtime); 
        this.modTime = long_1.longValue();
      } catch (MissingField missingField) {
        throw new TarMalformatException(missingField.getMessage());
      } 
      this.entryType = readChar(TarHeaderField.typeflag);
      this.ownerName = readString(TarHeaderField.uname);
      String str = readString(TarHeaderField.prefix);
      if (str != null)
        this.path = str + '/' + this.path; 
      this.ustar = isUstar();
    }
    
    public File generateFile() {
      if (this.entryType != '\000' && this.entryType != '0')
        throw new IllegalStateException(RB.create_only_normal.getString()); 
      return new File(this.path);
    }
    
    public char getEntryType() {
      return this.entryType;
    }
    
    public String getPath() {
      return this.path;
    }
    
    public void setDataSize(long param1Long) {
      this.dataSize = param1Long;
    }
    
    public long getDataSize() {
      return this.dataSize;
    }
    
    public long getModTime() {
      return this.modTime;
    }
    
    public int getFileMode() {
      return this.fileMode;
    }
    
    public String toString() {
      StringBuffer stringBuffer = new StringBuffer(this.sdf.format(new Long(this.modTime * 1000L)) + ' ');
      stringBuffer.append((this.entryType == '\000') ? 32 : this.entryType);
      stringBuffer.append(this.ustar ? 42 : 32);
      stringBuffer.append(" " + StringUtil.toPaddedString(Integer.toOctalString(this.fileMode), 4, ' ', false) + ' ' + StringUtil.toPaddedString(Long.toString(this.dataSize), 11, ' ', false) + "  ");
      stringBuffer.append(StringUtil.toPaddedString((this.ownerName == null) ? "-" : this.ownerName, 8, ' ', true));
      stringBuffer.append("  " + this.path);
      return stringBuffer.toString();
    }
    
    public boolean isUstar() throws TarMalformatException {
      String str = readString(TarHeaderField.magic);
      return (str != null && str.startsWith("ustar"));
    }
    
    public static int indexOf(byte[] param1ArrayOfbyte, byte param1Byte, int param1Int1, int param1Int2) {
      for (int i = param1Int1; i < param1Int2; i++) {
        if (param1ArrayOfbyte[i] == param1Byte)
          return i - param1Int1; 
      } 
      return -1;
    }
    
    protected char readChar(TarHeaderField param1TarHeaderField) throws TarMalformatException {
      String str = readString(param1TarHeaderField);
      return (str == null) ? Character.MIN_VALUE : str.charAt(0);
    }
    
    protected String readString(TarHeaderField param1TarHeaderField) throws TarMalformatException {
      int i = param1TarHeaderField.getStart();
      int j = param1TarHeaderField.getStop();
      int k = indexOf(this.rawHeader, (byte)0, i, j);
      switch (k) {
        case 0:
          return null;
        case -1:
          k = j - i;
          break;
      } 
      try {
        return new String(this.rawHeader, i, k);
      } catch (Throwable throwable) {
        throw new TarMalformatException(RB.bad_header_value.getString(new String[] { param1TarHeaderField.toString() }));
      } 
    }
    
    protected Long readInteger(TarHeaderField param1TarHeaderField) throws TarMalformatException {
      String str = readString(param1TarHeaderField);
      if (str == null)
        return null; 
      try {
        return Long.valueOf(str, 8);
      } catch (NumberFormatException numberFormatException) {
        throw new TarMalformatException(RB.bad_numeric_header_value.getString(new String[] { param1TarHeaderField.toString(), numberFormatException.toString() }));
      } 
    }
    
    protected long headerChecksum() {
      long l = 0L;
      for (byte b = 0; b < 'Ȁ'; b++) {
        boolean bool = (b >= TarHeaderField.checksum.getStart() && b < TarHeaderField.checksum.getStop()) ? true : false;
        l += bool ? 32L : (0xFF & this.rawHeader[b]);
      } 
      return l;
    }
    
    protected static class MissingField extends Exception {
      private TarHeaderField field;
      
      public MissingField(TarHeaderField param2TarHeaderField) {
        this.field = param2TarHeaderField;
      }
      
      public String getMessage() {
        return RB.header_field_missing.getString(new String[] { this.field.toString() });
      }
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\tar\TarReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */