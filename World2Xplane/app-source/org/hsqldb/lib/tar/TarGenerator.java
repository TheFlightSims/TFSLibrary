package org.hsqldb.lib.tar;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hsqldb.lib.InputStreamInterface;
import org.hsqldb.lib.InputStreamWrapper;
import org.hsqldb.lib.StringUtil;

public class TarGenerator {
  protected TarFileOutputStream archive;
  
  protected List<TarEntrySupplicant> entryQueue = new ArrayList<TarEntrySupplicant>();
  
  protected long paxThreshold = 8589934592L;
  
  public static void main(String[] paramArrayOfString) throws IOException, TarMalformatException {
    if (paramArrayOfString.length < 1) {
      System.out.println(RB.TarGenerator_syntax.getString(new String[] { DbBackup.class.getName() }));
      System.exit(0);
    } 
    TarGenerator tarGenerator = new TarGenerator(new File(paramArrayOfString[0]), true, null);
    if (paramArrayOfString.length == 1) {
      tarGenerator.queueEntry("stdin", System.in, 10240);
    } else {
      for (byte b = 1; b < paramArrayOfString.length; b++)
        tarGenerator.queueEntry(new File(paramArrayOfString[b])); 
    } 
    tarGenerator.write();
  }
  
  public void setPaxThreshold(long paramLong) {
    this.paxThreshold = paramLong;
  }
  
  public long getPaxThreshold() {
    return this.paxThreshold;
  }
  
  public TarGenerator(File paramFile, boolean paramBoolean, Integer paramInteger) throws IOException {
    File file = paramFile.getAbsoluteFile();
    boolean bool = false;
    if (file.getName().endsWith(".tgz") || file.getName().endsWith(".tar.gz")) {
      bool = true;
    } else if (!file.getName().endsWith(".tar")) {
      throw new IllegalArgumentException(RB.unsupported_ext.getString(new String[] { getClass().getName(), file.getPath() }));
    } 
    if (file.exists()) {
      if (!paramBoolean)
        throw new IOException(RB.dest_exists.getString(new String[] { file.getPath() })); 
    } else {
      File file1 = file.getParentFile();
      if (file1.exists()) {
        if (!file1.isDirectory())
          throw new IOException(RB.parent_not_dir.getString(new String[] { file1.getPath() })); 
        if (!file1.canWrite())
          throw new IOException(RB.cant_write_parent.getString(new String[] { file1.getPath() })); 
      } else if (!file1.mkdirs()) {
        throw new IOException(RB.parent_create_fail.getString(new String[] { file1.getPath() }));
      } 
    } 
    this.archive = (paramInteger == null) ? new TarFileOutputStream(file, bool) : new TarFileOutputStream(file, bool, paramInteger.intValue());
    if (paramInteger != null && TarFileOutputStream.debug)
      System.out.println(RB.bpr_write.getString(paramInteger.intValue())); 
  }
  
  public void queueEntry(File paramFile) throws FileNotFoundException, TarMalformatException {
    queueEntry((String)null, paramFile);
  }
  
  public void queueEntry(String paramString, File paramFile) throws FileNotFoundException, TarMalformatException {
    this.entryQueue.add(new TarEntrySupplicant(paramString, paramFile, this.archive, this.paxThreshold));
  }
  
  public void queueEntry(String paramString, InputStreamInterface paramInputStreamInterface) throws FileNotFoundException, TarMalformatException {
    this.entryQueue.add(new TarEntrySupplicant(paramString, paramInputStreamInterface, this.archive, this.paxThreshold));
  }
  
  public void queueEntry(String paramString, InputStream paramInputStream, int paramInt) throws IOException, TarMalformatException {
    this.entryQueue.add(new TarEntrySupplicant(paramString, paramInputStream, paramInt, '0', this.archive));
  }
  
  public void write() throws IOException, TarMalformatException {
    if (TarFileOutputStream.debug)
      System.out.println(RB.write_queue_report.getString(this.entryQueue.size())); 
    try {
      for (byte b = 0; b < this.entryQueue.size(); b++) {
        System.err.print(Integer.toString(b + 1) + " / " + this.entryQueue.size() + ' ');
        TarEntrySupplicant tarEntrySupplicant = this.entryQueue.get(b);
        System.err.print(tarEntrySupplicant.getPath() + "... ");
        tarEntrySupplicant.write();
        this.archive.assertAtBlockBoundary();
        System.err.println();
      } 
      this.archive.finish();
    } catch (IOException iOException) {
      System.err.println();
      try {
        for (TarEntrySupplicant tarEntrySupplicant : this.entryQueue)
          tarEntrySupplicant.close(); 
        this.archive.close();
      } catch (IOException iOException1) {}
      throw iOException;
    } 
  }
  
  protected static class TarEntrySupplicant {
    protected static byte[] HEADER_TEMPLATE = (byte[])TarFileOutputStream.ZERO_BLOCK.clone();
    
    static Character swapOutDelim = null;
    
    protected static final byte[] ustarBytes = new byte[] { 117, 115, 116, 97, 114 };
    
    protected byte[] rawHeader = (byte[])HEADER_TEMPLATE.clone();
    
    protected String fileMode = "600";
    
    protected InputStreamInterface inputStream;
    
    protected String path;
    
    protected long modTime;
    
    protected TarFileOutputStream tarStream;
    
    protected long dataSize;
    
    protected boolean paxSized = false;
    
    protected final long paxThreshold;
    
    public static final String DEFAULT_FILE_MODES = "600";
    
    protected static void writeField(TarHeaderField param1TarHeaderField, String param1String, byte[] param1ArrayOfbyte) throws TarMalformatException {
      byte[] arrayOfByte;
      int i = param1TarHeaderField.getStart();
      int j = param1TarHeaderField.getStop();
      try {
        arrayOfByte = param1String.getBytes("ISO-8859-1");
      } catch (UnsupportedEncodingException unsupportedEncodingException) {
        throw new RuntimeException(unsupportedEncodingException);
      } 
      if (arrayOfByte.length > j - i)
        throw new TarMalformatException(RB.tar_field_toobig.getString(new String[] { param1TarHeaderField.toString(), param1String })); 
      for (byte b = 0; b < arrayOfByte.length; b++)
        param1ArrayOfbyte[i + b] = arrayOfByte[b]; 
    }
    
    protected static void clearField(TarHeaderField param1TarHeaderField, byte[] param1ArrayOfbyte) {
      int i = param1TarHeaderField.getStart();
      int j = param1TarHeaderField.getStop();
      for (int k = i; k < j; k++)
        param1ArrayOfbyte[k] = 0; 
    }
    
    protected static void writeField(TarHeaderField param1TarHeaderField, long param1Long, byte[] param1ArrayOfbyte) throws TarMalformatException {
      writeField(param1TarHeaderField, prePaddedOctalString(param1Long, param1TarHeaderField.getStop() - param1TarHeaderField.getStart()), param1ArrayOfbyte);
    }
    
    public static String prePaddedOctalString(long param1Long, int param1Int) {
      return StringUtil.toPaddedString(Long.toOctalString(param1Long), param1Int, '0', false);
    }
    
    public String getPath() {
      return this.path;
    }
    
    public long getDataSize() {
      return this.dataSize;
    }
    
    protected TarEntrySupplicant(String param1String, char param1Char, TarFileOutputStream param1TarFileOutputStream, long param1Long) throws TarMalformatException {
      this.paxThreshold = param1Long;
      if (param1String == null)
        throw new IllegalArgumentException(RB.missing_supp_path.getString()); 
      this.path = (swapOutDelim == null) ? param1String : param1String.replace(swapOutDelim.charValue(), '/');
      this.tarStream = param1TarFileOutputStream;
      writeField(TarHeaderField.typeflag, param1Char);
      if (param1Char == '\000' || param1Char == ' ') {
        writeField(TarHeaderField.uname, System.getProperty("user.name"), HEADER_TEMPLATE);
        writeField(TarHeaderField.gname, "root", HEADER_TEMPLATE);
      } 
    }
    
    public TarEntrySupplicant makeXentry() throws IOException, TarMalformatException {
      PIFGenerator pIFGenerator = new PIFGenerator(new File(this.path));
      pIFGenerator.addRecord("size", this.dataSize);
      return new TarEntrySupplicant(pIFGenerator.getName(), new ByteArrayInputStream(pIFGenerator.toByteArray()), pIFGenerator.size(), 'x', this.tarStream);
    }
    
    public TarEntrySupplicant(String param1String, File param1File, TarFileOutputStream param1TarFileOutputStream, long param1Long) throws FileNotFoundException, TarMalformatException {
      this((param1String == null) ? param1File.getPath() : param1String, '0', param1TarFileOutputStream, param1Long);
      if (!param1File.isFile())
        throw new IllegalArgumentException(RB.nonfile_entry.getString()); 
      if (!param1File.canRead())
        throw new IllegalArgumentException(RB.read_denied.getString(new String[] { param1File.getAbsolutePath() })); 
      this.modTime = param1File.lastModified() / 1000L;
      this.fileMode = getLameMode(param1File);
      this.dataSize = param1File.length();
      this.inputStream = (InputStreamInterface)new InputStreamWrapper(new FileInputStream(param1File));
    }
    
    public TarEntrySupplicant(String param1String, InputStreamInterface param1InputStreamInterface, TarFileOutputStream param1TarFileOutputStream, long param1Long) throws FileNotFoundException, TarMalformatException {
      this(param1String, '0', param1TarFileOutputStream, param1Long);
      this.modTime = System.currentTimeMillis() / 1000L;
      this.fileMode = "600";
      this.inputStream = param1InputStreamInterface;
    }
    
    public TarEntrySupplicant(String param1String, InputStream param1InputStream, int param1Int, char param1Char, TarFileOutputStream param1TarFileOutputStream) throws IOException, TarMalformatException {
      this(param1String, param1Char, param1TarFileOutputStream, 8589934592L);
      if (param1Int < 1)
        throw new IllegalArgumentException(RB.read_lt_1.getString()); 
      PipedOutputStream pipedOutputStream = new PipedOutputStream();
      try {
        this.inputStream = (InputStreamInterface)new InputStreamWrapper(new PipedInputStream(pipedOutputStream));
        int i;
        while ((i = param1InputStream.read(param1TarFileOutputStream.writeBuffer, 0, param1TarFileOutputStream.writeBuffer.length)) > 0)
          pipedOutputStream.write(param1TarFileOutputStream.writeBuffer, 0, i); 
        pipedOutputStream.flush();
        this.dataSize = this.inputStream.available();
        if (TarFileOutputStream.debug)
          System.out.println(RB.stream_buffer_report.getString(new String[] { Long.toString(this.dataSize) })); 
      } catch (IOException iOException) {
        close();
        throw iOException;
      } finally {
        try {
          pipedOutputStream.close();
        } finally {
          pipedOutputStream = null;
        } 
      } 
      this.modTime = (new Date()).getTime() / 1000L;
    }
    
    public void close() throws IOException {
      if (this.inputStream == null)
        return; 
      try {
        this.inputStream.close();
      } finally {
        this.inputStream = null;
      } 
    }
    
    protected long headerChecksum() {
      long l = 0L;
      for (byte b = 0; b < this.rawHeader.length; b++) {
        boolean bool = (b >= TarHeaderField.checksum.getStart() && b < TarHeaderField.checksum.getStop()) ? true : false;
        l += bool ? 32L : (0xFF & this.rawHeader[b]);
      } 
      return l;
    }
    
    protected void clearField(TarHeaderField param1TarHeaderField) {
      clearField(param1TarHeaderField, this.rawHeader);
    }
    
    protected void writeField(TarHeaderField param1TarHeaderField, String param1String) throws TarMalformatException {
      writeField(param1TarHeaderField, param1String, this.rawHeader);
    }
    
    protected void writeField(TarHeaderField param1TarHeaderField, long param1Long) throws TarMalformatException {
      writeField(param1TarHeaderField, param1Long, this.rawHeader);
    }
    
    protected void writeField(TarHeaderField param1TarHeaderField, char param1Char) throws TarMalformatException {
      writeField(param1TarHeaderField, Character.toString(param1Char), this.rawHeader);
    }
    
    public void write() throws IOException, TarMalformatException {
      try {
        long l1 = this.inputStream.getSizeLimit();
        if (l1 == 0L)
          return; 
        if (l1 > 0L)
          this.dataSize = l1; 
        if (this.dataSize >= this.paxThreshold) {
          this.paxSized = true;
          makeXentry().write();
          System.err.print("x... ");
        } 
        writeField(TarHeaderField.name, this.path);
        writeField(TarHeaderField.mode, this.fileMode);
        if (!this.paxSized)
          writeField(TarHeaderField.size, this.dataSize); 
        writeField(TarHeaderField.mtime, this.modTime);
        writeField(TarHeaderField.checksum, prePaddedOctalString(headerChecksum(), 6) + "\000 ");
        this.tarStream.writeBlock(this.rawHeader);
        long l2 = this.tarStream.getBytesWritten();
        int i;
        while ((i = this.inputStream.read(this.tarStream.writeBuffer)) > 0)
          this.tarStream.write(i); 
        if (l2 + this.dataSize != this.tarStream.getBytesWritten())
          throw new IOException(RB.data_changed.getString(new String[] { Long.toString(this.dataSize), Long.toString(this.tarStream.getBytesWritten() - l2) })); 
        this.tarStream.padCurrentBlock();
      } finally {
        close();
      } 
    }
    
    protected static String getLameMode(File param1File) {
      boolean bool = false;
      if (param1File.canExecute())
        bool = true; 
      if (param1File.canWrite())
        bool += true; 
      if (param1File.canRead())
        bool += true; 
      return "0" + bool + "00";
    }
    
    static {
      char c = System.getProperty("file.separator").charAt(0);
      if (c != '/')
        swapOutDelim = new Character(c); 
      try {
        writeField(TarHeaderField.uid, 0L, HEADER_TEMPLATE);
        writeField(TarHeaderField.gid, 0L, HEADER_TEMPLATE);
      } catch (TarMalformatException tarMalformatException) {
        throw new RuntimeException(tarMalformatException);
      } 
      int i = TarHeaderField.magic.getStart();
      for (byte b = 0; b < ustarBytes.length; b++)
        HEADER_TEMPLATE[i + b] = ustarBytes[b]; 
      HEADER_TEMPLATE[263] = 48;
      HEADER_TEMPLATE[264] = 48;
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\tar\TarGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */