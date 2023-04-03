package org.hsqldb.jdbc;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.IllegalCharsetNameException;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hsqldb.lib.FileUtil;
import org.hsqldb.lib.InOutUtil;
import org.hsqldb.lib.KMPSearchAlgorithm;

public class JDBCClobFile implements Clob {
  public static final String TEMP_FILE_PREFIX = "hsql_jdbc_clob_file_";
  
  public static final String TEMP_FILE_SUFFIX = ".tmp";
  
  private final File m_file;
  
  private boolean m_closed;
  
  private boolean m_deleteOnFree;
  
  private String m_encoding;
  
  private Charset m_charset;
  
  private CharsetEncoder m_encoder;
  
  private boolean m_fixedWidthCharset;
  
  private int m_maxCharWidth;
  
  private List m_streams = new ArrayList();
  
  public long length() throws SQLException {
    checkClosed();
    if (this.m_fixedWidthCharset)
      return this.m_file.length() / this.m_maxCharWidth; 
    ReaderAdapter readerAdapter = null;
    try {
      readerAdapter = new ReaderAdapter(this.m_file, 0L, Long.MAX_VALUE);
      long l = readerAdapter.skip(Long.MAX_VALUE);
      return l;
    } catch (Exception exception) {
      throw JDBCUtil.sqlException(exception);
    } finally {
      if (readerAdapter != null)
        try {
          readerAdapter.close();
        } catch (Exception exception) {} 
    } 
  }
  
  public String getSubString(long paramLong, int paramInt) throws SQLException {
    Reader reader = null;
    CharArrayWriter charArrayWriter = null;
    try {
      int i = Math.min(8192, paramInt);
      reader = getCharacterStream(paramLong, paramInt);
      charArrayWriter = new CharArrayWriter(i);
      InOutUtil.copy(reader, charArrayWriter, paramInt);
    } catch (SQLException sQLException) {
      throw sQLException;
    } catch (Exception exception) {
      throw JDBCUtil.sqlException(exception);
    } finally {
      if (reader != null)
        try {
          reader.close();
        } catch (Exception exception) {} 
    } 
    return charArrayWriter.toString();
  }
  
  public Reader getCharacterStream() throws SQLException {
    return getCharacterStream(1L, Long.MAX_VALUE);
  }
  
  public InputStream getAsciiStream() throws SQLException {
    JDBCBlobFile.InputStreamAdapter inputStreamAdapter;
    try {
      inputStreamAdapter = new JDBCBlobFile.InputStreamAdapter(this.m_file, 0L, Long.MAX_VALUE) {
          public void close() throws IOException {
            try {
              super.close();
            } finally {
              JDBCClobFile.this.m_streams.remove(this);
            } 
          }
        };
    } catch (Exception exception) {
      throw JDBCUtil.sqlException(exception);
    } 
    this.m_streams.add(inputStreamAdapter);
    return inputStreamAdapter;
  }
  
  public long position(char[] paramArrayOfchar, long paramLong) throws SQLException {
    if (paramLong < 1L)
      throw JDBCUtil.outOfRangeArgument("start: " + paramLong); 
    if (paramArrayOfchar == null || paramArrayOfchar.length == 0 || paramLong > length())
      return -1L; 
    Reader reader = null;
    try {
      reader = getCharacterStream(paramLong, Long.MAX_VALUE);
      long l = KMPSearchAlgorithm.search(reader, paramArrayOfchar, KMPSearchAlgorithm.computeTable(paramArrayOfchar));
      return (l == -1L) ? -1L : (paramLong + l);
    } catch (SQLException sQLException) {
      throw sQLException;
    } catch (Exception exception) {
      throw JDBCUtil.sqlException(exception);
    } finally {
      if (reader != null)
        try {
          reader.close();
        } catch (Exception exception) {} 
    } 
  }
  
  public long position(String paramString, long paramLong) throws SQLException {
    return position((paramString == null) ? null : paramString.toCharArray(), paramLong);
  }
  
  public long position(Clob paramClob, long paramLong) throws SQLException {
    char[] arrayOfChar;
    if (paramLong < 1L)
      throw JDBCUtil.outOfRangeArgument("start: " + paramLong); 
    long l;
    if ((l = (paramClob == null) ? 0L : paramClob.length()) == 0L)
      return -1L; 
    if (l > 2147483647L)
      throw JDBCUtil.outOfRangeArgument("pattern.length(): " + l); 
    if (paramClob instanceof JDBCClob) {
      arrayOfChar = ((JDBCClob)paramClob).data().toCharArray();
    } else {
      Reader reader = null;
      CharArrayWriter charArrayWriter = new CharArrayWriter();
      try {
        reader = paramClob.getCharacterStream();
        InOutUtil.copy(reader, charArrayWriter, l);
      } catch (IOException iOException) {
        throw JDBCUtil.sqlException(iOException);
      } finally {
        if (reader != null)
          try {
            reader.close();
          } catch (IOException iOException) {} 
      } 
      arrayOfChar = charArrayWriter.toCharArray();
    } 
    return position(arrayOfChar, paramLong);
  }
  
  public int setString(long paramLong, String paramString) throws SQLException {
    return setString(paramLong, paramString, 0, (paramString == null) ? 0 : paramString.length());
  }
  
  public int setString(long paramLong, String paramString, int paramInt1, int paramInt2) throws SQLException {
    if (paramString == null)
      throw JDBCUtil.nullArgument("str"); 
    Writer writer = null;
    try {
      writer = setCharacterStream(paramLong);
      writer.write(paramString, paramInt1, paramInt2);
    } catch (Exception exception) {
      throw JDBCUtil.sqlException(exception);
    } finally {
      if (writer != null)
        try {
          writer.close();
        } catch (Exception exception) {} 
    } 
    return paramInt2;
  }
  
  public OutputStream setAsciiStream(long paramLong) throws SQLException {
    JDBCBlobFile.OutputStreamAdapter outputStreamAdapter;
    if (paramLong < 1L)
      throw JDBCUtil.invalidArgument("pos: " + paramLong); 
    checkClosed();
    createFile();
    try {
      outputStreamAdapter = new JDBCBlobFile.OutputStreamAdapter(this.m_file, paramLong - 1L) {
          public void close() throws IOException {
            try {
              super.close();
            } finally {
              JDBCClobFile.this.m_streams.remove(this);
            } 
          }
        };
    } catch (Exception exception) {
      throw JDBCUtil.sqlException(exception);
    } 
    this.m_streams.add(outputStreamAdapter);
    return outputStreamAdapter;
  }
  
  public Writer setCharacterStream(long paramLong) throws SQLException {
    BufferedWriter bufferedWriter;
    if (paramLong < 1L)
      throw JDBCUtil.invalidArgument("pos: " + paramLong); 
    checkClosed();
    createFile();
    try {
      WriterAdapter writerAdapter = new WriterAdapter(this.m_file, paramLong - 1L) {
          public void close() throws IOException {
            try {
              super.close();
            } finally {
              JDBCClobFile.this.m_streams.remove(this);
            } 
          }
        };
      bufferedWriter = new BufferedWriter(writerAdapter);
    } catch (Exception exception) {
      throw JDBCUtil.sqlException(exception);
    } 
    this.m_streams.add(bufferedWriter);
    return bufferedWriter;
  }
  
  public void truncate(long paramLong) throws SQLException {
    if (paramLong < 0L)
      throw JDBCUtil.invalidArgument("len: " + paramLong); 
    checkClosed();
    ReaderAdapter readerAdapter = null;
    RandomAccessFile randomAccessFile = null;
    try {
      readerAdapter = new ReaderAdapter(this.m_file, paramLong, Long.MAX_VALUE);
      long l = readerAdapter.getFilePointer();
      readerAdapter.close();
      randomAccessFile = new RandomAccessFile(this.m_file, "rw");
      randomAccessFile.setLength(l);
    } catch (Exception exception) {
      throw JDBCUtil.sqlException(exception);
    } finally {
      if (readerAdapter != null)
        try {
          readerAdapter.close();
        } catch (Exception exception) {} 
      if (randomAccessFile != null)
        try {
          randomAccessFile.close();
        } catch (Exception exception) {} 
    } 
  }
  
  public synchronized void free() throws SQLException {
    if (this.m_closed)
      return; 
    this.m_closed = true;
    ArrayList arrayList = new ArrayList();
    arrayList.addAll(this.m_streams);
    this.m_streams = null;
    for (InputStream inputStream : arrayList) {
      if (inputStream instanceof InputStream) {
        try {
          ((InputStream)inputStream).close();
        } catch (Exception exception) {}
        continue;
      } 
      if (inputStream instanceof OutputStream)
        try {
          ((OutputStream)inputStream).close();
        } catch (Exception exception) {} 
    } 
    if (this.m_deleteOnFree)
      try {
        this.m_file.delete();
      } catch (Exception exception) {} 
  }
  
  public Reader getCharacterStream(long paramLong1, long paramLong2) throws SQLException {
    ReaderAdapter readerAdapter;
    if (paramLong1 < 1L)
      throw JDBCUtil.outOfRangeArgument("pos: " + paramLong1); 
    paramLong1--;
    if (paramLong2 < 0L)
      throw JDBCUtil.outOfRangeArgument("length: " + paramLong2); 
    try {
      readerAdapter = new ReaderAdapter(this.m_file, paramLong1, paramLong2) {
          public void close() throws IOException {
            try {
              super.close();
            } finally {
              JDBCClobFile.this.m_streams.remove(this);
            } 
          }
        };
    } catch (Exception exception) {
      throw JDBCUtil.sqlException(exception);
    } 
    this.m_streams.add(readerAdapter);
    return readerAdapter;
  }
  
  public File getFile() {
    return this.m_file;
  }
  
  public String getEncoding() {
    return this.m_encoding;
  }
  
  public boolean isDeleteOnFree() {
    return this.m_deleteOnFree;
  }
  
  public void setDeleteOnFree(boolean paramBoolean) {
    this.m_deleteOnFree = paramBoolean;
  }
  
  protected void finalize() throws Throwable {
    try {
      super.finalize();
    } finally {
      try {
        free();
      } catch (Throwable throwable) {}
    } 
  }
  
  public JDBCClobFile() throws SQLException {
    this((String)null);
  }
  
  public JDBCClobFile(String paramString) throws SQLException {
    try {
      setEncoding(paramString);
      this.m_file = File.createTempFile("hsql_jdbc_clob_file_", ".tmp");
      this.m_deleteOnFree = true;
    } catch (Exception exception) {
      throw JDBCUtil.sqlException(exception);
    } 
  }
  
  public JDBCClobFile(File paramFile) throws SQLException {
    this(paramFile, null);
  }
  
  public JDBCClobFile(File paramFile, String paramString) throws SQLException {
    if (paramFile == null)
      throw JDBCUtil.nullArgument("file"); 
    try {
      setEncoding(paramString);
      this.m_file = paramFile.getCanonicalFile();
      checkIsFile(false);
      this.m_deleteOnFree = false;
    } catch (Exception exception) {
      throw JDBCUtil.sqlException(exception);
    } 
  }
  
  protected final void setEncoding(String paramString) throws UnsupportedEncodingException {
    Charset charset = charsetForName(paramString);
    CharsetEncoder charsetEncoder = charset.newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
    float f1 = charsetEncoder.maxBytesPerChar();
    float f2 = charsetEncoder.averageBytesPerChar();
    boolean bool = (f1 == Math.round(f1) && f1 == f2) ? true : false;
    this.m_fixedWidthCharset = bool;
    this.m_maxCharWidth = Math.round(f1);
    this.m_charset = charset;
    this.m_encoder = charsetEncoder;
    this.m_encoding = this.m_charset.name();
  }
  
  protected static Charset charsetForName(String paramString) throws UnsupportedEncodingException {
    String str = paramString;
    if (str == null)
      str = Charset.defaultCharset().name(); 
    try {
      if (Charset.isSupported(str))
        return Charset.forName(str); 
    } catch (IllegalCharsetNameException illegalCharsetNameException) {}
    throw new UnsupportedEncodingException(str);
  }
  
  protected final void checkIsFile(boolean paramBoolean) throws SQLException {
    boolean bool1 = false;
    boolean bool2 = false;
    try {
      bool1 = this.m_file.exists();
    } catch (Exception exception) {
      throw JDBCUtil.sqlException(exception);
    } 
    if (bool1)
      try {
        bool2 = this.m_file.isFile();
      } catch (Exception exception) {
        throw JDBCUtil.sqlException(exception);
      }  
    if (bool1) {
      if (!bool2)
        throw JDBCUtil.invalidArgument("Is not a file: " + this.m_file); 
    } else if (paramBoolean) {
      throw JDBCUtil.invalidArgument("Does not exist: " + this.m_file);
    } 
  }
  
  protected void checkClosed() throws SQLException {
    if (this.m_closed)
      throw JDBCUtil.sqlException(1251); 
  }
  
  protected void createFile() throws SQLException {
    try {
      if (!this.m_file.exists()) {
        FileUtil.getFileUtil().makeParentDirectories(this.m_file);
        this.m_file.createNewFile();
      } 
    } catch (Exception exception) {
      throw JDBCUtil.sqlException(exception);
    } 
    checkIsFile(true);
  }
  
  protected class ReaderAdapter extends Reader {
    private static final int CHARBUFFER_CAPACTIY = 128;
    
    private final Reader m_reader;
    
    private long m_remaining = Long.MAX_VALUE;
    
    private long m_filePointer;
    
    private ByteBuffer m_byteBuffer;
    
    private CharBuffer m_charBuffer;
    
    public ReaderAdapter(File param1File, long param1Long1, long param1Long2) throws FileNotFoundException, IOException {
      if (param1File == null)
        throw new NullPointerException("file"); 
      if (param1Long1 < 0L)
        throw new IllegalArgumentException("pos: " + param1Long1); 
      if (param1Long2 < 0L)
        throw new IllegalArgumentException("length: " + param1Long2); 
      if (!JDBCClobFile.this.m_fixedWidthCharset) {
        int i = 128 * JDBCClobFile.this.m_maxCharWidth;
        this.m_charBuffer = CharBuffer.allocate(128);
        this.m_byteBuffer = ByteBuffer.allocate(i);
      } 
      FileInputStream fileInputStream = new FileInputStream(param1File);
      BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
      InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream, JDBCClobFile.this.m_charset);
      this.m_reader = inputStreamReader;
      long l;
      for (l = 0L; l < param1Long1; l++) {
        int i = read();
        if (i == -1)
          break; 
      } 
      this.m_remaining = param1Long2;
    }
    
    public int read(char[] param1ArrayOfchar, int param1Int1, int param1Int2) throws IOException {
      int j;
      long l = this.m_remaining;
      if (l <= 0L)
        return -1; 
      if (l < param1Int2)
        param1Int2 = (int)l; 
      int i = this.m_reader.read(param1ArrayOfchar, param1Int1, param1Int2);
      if (i == -1)
        return -1; 
      if (i > l) {
        i = (int)l;
        this.m_remaining = 0L;
      } else {
        this.m_remaining -= i;
      } 
      if (JDBCClobFile.this.m_fixedWidthCharset) {
        j = JDBCClobFile.this.m_maxCharWidth * i;
      } else {
        boolean bool = (i > this.m_charBuffer.capacity()) ? true : false;
        CharBuffer charBuffer = bool ? CharBuffer.allocate(i) : this.m_charBuffer;
        ByteBuffer byteBuffer = bool ? ByteBuffer.allocate(i * JDBCClobFile.this.m_maxCharWidth) : this.m_byteBuffer;
        charBuffer.clear();
        byteBuffer.clear();
        charBuffer.put(param1ArrayOfchar, param1Int1, i);
        charBuffer.flip();
        JDBCClobFile.this.m_encoder.encode(charBuffer, byteBuffer, true);
        byteBuffer.flip();
        j = byteBuffer.limit();
        if (bool) {
          this.m_byteBuffer = byteBuffer;
          this.m_charBuffer = charBuffer;
        } 
      } 
      this.m_filePointer += j;
      return i;
    }
    
    public void close() throws IOException {
      this.m_reader.close();
    }
    
    public long getFilePointer() {
      return this.m_filePointer;
    }
  }
  
  protected class WriterAdapter extends Writer {
    private final RandomAccessFile m_randomAccessFile;
    
    public WriterAdapter(File param1File, long param1Long) throws FileNotFoundException, IOException {
      long l;
      if (param1File == null)
        throw new NullPointerException("file"); 
      if (param1Long < 0L)
        throw new IllegalArgumentException("pos: " + param1Long); 
      JDBCClobFile.ReaderAdapter readerAdapter = null;
      try {
        readerAdapter = new JDBCClobFile.ReaderAdapter(param1File, param1Long, Long.MAX_VALUE);
        l = readerAdapter.getFilePointer();
      } finally {
        if (readerAdapter != null)
          try {
            readerAdapter.close();
          } catch (Exception exception) {} 
      } 
      this.m_randomAccessFile = new RandomAccessFile(param1File, "rw");
      this.m_randomAccessFile.seek(l);
    }
    
    public void flush() throws IOException {
      this.m_randomAccessFile.getFD().sync();
    }
    
    public void close() throws IOException {
      this.m_randomAccessFile.close();
    }
    
    public void write(char[] param1ArrayOfchar, int param1Int1, int param1Int2) throws IOException {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      OutputStreamWriter outputStreamWriter = (JDBCClobFile.this.m_encoding == null) ? new OutputStreamWriter(byteArrayOutputStream) : new OutputStreamWriter(byteArrayOutputStream, JDBCClobFile.this.m_charset);
      outputStreamWriter.write(param1ArrayOfchar, param1Int1, param1Int2);
      outputStreamWriter.close();
      this.m_randomAccessFile.write(byteArrayOutputStream.toByteArray());
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\jdbc\JDBCClobFile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */