package org.hsqldb.persist;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import org.hsqldb.DatabaseManager;
import org.hsqldb.HsqlDateTime;
import org.hsqldb.HsqlException;
import org.hsqldb.error.Error;
import org.hsqldb.lib.FileUtil;
import org.hsqldb.lib.HsqlTimer;
import org.hsqldb.lib.StringConverter;

public class LockFile {
  public static final long HEARTBEAT_INTERVAL = 10000L;
  
  public static final long HEARTBEAT_INTERVAL_PADDED = 10100L;
  
  protected static final byte[] MAGIC = new byte[] { 72, 83, 81, 76, 76, 79, 67, 75 };
  
  public static final int USED_REGION = 16;
  
  public static final int POLL_RETRIES_DEFAULT = 10;
  
  public static final String POLL_RETRIES_PROPERTY = "hsqldb.lockfile.poll.retries";
  
  public static final String POLL_INTERVAL_PROPERTY = "hsqldb.lockfile.poll.interval";
  
  public static final boolean USE_NIO_FILELOCK_DEFAULT = false;
  
  public static final String USE_NIO_FILELOCK_PROPERTY = "hsqldb.lockfile.nio.filelock";
  
  public static final boolean NIO_FILELOCK_AVAILABLE;
  
  public static final Class NIO_LOCKFILE_CLASS;
  
  protected static final HsqlTimer timer = DatabaseManager.getTimer();
  
  protected File file;
  
  private String cpath;
  
  protected volatile RandomAccessFile raf;
  
  protected volatile boolean locked;
  
  private volatile Object timerTask;
  
  private static final LockFile newNIOLockFile() {
    if (NIO_FILELOCK_AVAILABLE && NIO_LOCKFILE_CLASS != null)
      try {
        return NIO_LOCKFILE_CLASS.newInstance();
      } catch (Exception exception) {} 
    return null;
  }
  
  public static final LockFile newLockFile(String paramString) throws FileCanonicalizationException, FileSecurityException {
    LockFile lockFile = newNIOLockFile();
    if (lockFile == null)
      lockFile = new LockFile(); 
    lockFile.setPath(paramString);
    return lockFile;
  }
  
  public static final LockFile newLockFileLock(String paramString) throws HsqlException {
    LockFile lockFile = null;
    try {
      lockFile = newLockFile(paramString + ".lck");
    } catch (BaseException baseException) {
      throw Error.error(451, baseException.getMessage());
    } 
    boolean bool = false;
    try {
      bool = lockFile.tryLock();
    } catch (BaseException baseException) {
      throw Error.error(451, baseException.getMessage());
    } 
    if (!bool)
      throw Error.error(451, lockFile.toString()); 
    return lockFile;
  }
  
  private final void checkHeartbeat(boolean paramBoolean) throws FileSecurityException, LockHeldExternallyException, UnexpectedEndOfFileException, UnexpectedFileIOException, UnexpectedFileNotFoundException, WrongLengthException, WrongMagicException {
    long l3 = 0L;
    try {
      if (paramBoolean)
        try {
          if (this.file.createNewFile())
            return; 
        } catch (IOException iOException) {} 
      if (!this.file.exists())
        return; 
      l3 = this.file.length();
    } catch (SecurityException securityException) {
      throw new FileSecurityException(this, "checkHeartbeat", securityException);
    } 
    if (l3 != 16L) {
      if (l3 == 0L) {
        this.file.delete();
        return;
      } 
      throw new WrongLengthException(this, "checkHeartbeat", l3);
    } 
    long l1 = System.currentTimeMillis();
    long l2 = readHeartbeat();
    if (Math.abs(l1 - l2) <= 10100L)
      throw new LockHeldExternallyException(this, "checkHeartbeat", l1, l2); 
  }
  
  private final void closeRAF() throws UnexpectedFileIOException {
    if (this.raf != null)
      try {
        this.raf.close();
      } catch (IOException iOException) {
        throw new UnexpectedFileIOException(this, "closeRAF", iOException);
      } finally {
        this.raf = null;
      }  
  }
  
  protected boolean doOptionalLockActions() {
    return false;
  }
  
  protected boolean doOptionalReleaseActions() {
    return false;
  }
  
  private final void setPath(String paramString) throws FileCanonicalizationException, FileSecurityException {
    paramString = FileUtil.getFileUtil().canonicalOrAbsolutePath(paramString);
    this.file = new File(paramString);
    try {
      FileUtil.getFileUtil().makeParentDirectories(this.file);
    } catch (SecurityException securityException) {
      throw new FileSecurityException(this, "setPath", securityException);
    } 
    try {
      this.file = FileUtil.getFileUtil().canonicalFile(paramString);
    } catch (SecurityException securityException) {
      throw new FileSecurityException(this, "setPath", securityException);
    } catch (IOException iOException) {
      throw new FileCanonicalizationException(this, "setPath", iOException);
    } 
    this.cpath = this.file.getPath();
  }
  
  private final void openRAF() throws UnexpectedFileNotFoundException, FileSecurityException, UnexpectedFileIOException {
    try {
      this.raf = new RandomAccessFile(this.file, "rw");
    } catch (SecurityException securityException) {
      throw new FileSecurityException(this, "openRAF", securityException);
    } catch (FileNotFoundException fileNotFoundException) {
      throw new UnexpectedFileNotFoundException(this, "openRAF", fileNotFoundException);
    } 
  }
  
  private final void checkMagic(DataInputStream paramDataInputStream) throws FileSecurityException, UnexpectedEndOfFileException, UnexpectedFileIOException, WrongMagicException {
    boolean bool = true;
    byte[] arrayOfByte = new byte[MAGIC.length];
    try {
      for (byte b = 0; b < MAGIC.length; b++) {
        arrayOfByte[b] = paramDataInputStream.readByte();
        if (MAGIC[b] != arrayOfByte[b])
          bool = false; 
      } 
    } catch (SecurityException securityException) {
      throw new FileSecurityException(this, "checkMagic", securityException);
    } catch (EOFException eOFException) {
      throw new UnexpectedEndOfFileException(this, "checkMagic", eOFException);
    } catch (IOException iOException) {
      throw new UnexpectedFileIOException(this, "checkMagic", iOException);
    } 
    if (!bool)
      throw new WrongMagicException(this, "checkMagic", arrayOfByte); 
  }
  
  private final long readHeartbeat() throws FileSecurityException, UnexpectedFileNotFoundException, UnexpectedEndOfFileException, UnexpectedFileIOException, WrongMagicException {
    FileInputStream fileInputStream = null;
    DataInputStream dataInputStream = null;
    try {
      if (!this.file.exists())
        return Long.MIN_VALUE; 
      fileInputStream = new FileInputStream(this.file);
      dataInputStream = new DataInputStream(fileInputStream);
      checkMagic(dataInputStream);
      return dataInputStream.readLong();
    } catch (SecurityException securityException) {
      throw new FileSecurityException(this, "readHeartbeat", securityException);
    } catch (FileNotFoundException fileNotFoundException) {
      throw new UnexpectedFileNotFoundException(this, "readHeartbeat", fileNotFoundException);
    } catch (EOFException eOFException) {
      throw new UnexpectedEndOfFileException(this, "readHeartbeat", eOFException);
    } catch (IOException iOException) {
      throw new UnexpectedFileIOException(this, "readHeartbeat", iOException);
    } finally {
      if (fileInputStream != null)
        try {
          fileInputStream.close();
        } catch (IOException iOException) {} 
    } 
  }
  
  private final void startHeartbeat() {
    if (this.timerTask == null || HsqlTimer.isCancelled(this.timerTask)) {
      HeartbeatRunner heartbeatRunner = new HeartbeatRunner();
      this.timerTask = timer.schedulePeriodicallyAfter(0L, 10000L, heartbeatRunner, true);
    } 
  }
  
  private final void stopHeartbeat() {
    if (this.timerTask != null && !HsqlTimer.isCancelled(this.timerTask)) {
      HsqlTimer.cancel(this.timerTask);
      this.timerTask = null;
    } 
  }
  
  private final void writeMagic() throws FileSecurityException, UnexpectedEndOfFileException, UnexpectedFileIOException {
    try {
      this.raf.seek(0L);
      this.raf.write(MAGIC);
    } catch (SecurityException securityException) {
      throw new FileSecurityException(this, "writeMagic", securityException);
    } catch (EOFException eOFException) {
      throw new UnexpectedEndOfFileException(this, "writeMagic", eOFException);
    } catch (IOException iOException) {
      throw new UnexpectedFileIOException(this, "writeMagic", iOException);
    } 
  }
  
  private final void writeHeartbeat() throws FileSecurityException, UnexpectedEndOfFileException, UnexpectedFileIOException {
    try {
      this.raf.seek(MAGIC.length);
      this.raf.writeLong(System.currentTimeMillis());
    } catch (SecurityException securityException) {
      throw new FileSecurityException(this, "writeHeartbeat", securityException);
    } catch (EOFException eOFException) {
      throw new UnexpectedEndOfFileException(this, "writeHeartbeat", eOFException);
    } catch (IOException iOException) {
      throw new UnexpectedFileIOException(this, "writeHeartbeat", iOException);
    } 
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject instanceof LockFile) {
      LockFile lockFile = (LockFile)paramObject;
      return (this.file == null) ? ((lockFile.file == null)) : this.file.equals(lockFile.file);
    } 
    return false;
  }
  
  public final String getCanonicalPath() {
    return this.cpath;
  }
  
  public final int hashCode() {
    return (this.file == null) ? 0 : this.file.hashCode();
  }
  
  public final boolean isLocked() {
    return this.locked;
  }
  
  public static final boolean isLocked(String paramString) {
    boolean bool = true;
    try {
      LockFile lockFile = newLockFile(paramString);
      lockFile.checkHeartbeat(false);
      bool = false;
    } catch (Exception exception) {}
    return bool;
  }
  
  public boolean isValid() {
    return (isLocked() && this.file != null && this.file.exists() && this.raf != null);
  }
  
  public String toString() {
    return super.toString() + "[file =" + this.cpath + ", exists=" + this.file.exists() + ", locked=" + isLocked() + ", valid=" + isValid() + ", " + toStringImpl() + "]";
  }
  
  protected String toStringImpl() {
    return "";
  }
  
  public int getPollHeartbeatRetries() {
    int i = 10;
    try {
      i = Integer.getInteger("hsqldb.lockfile_poll_retries", i).intValue();
    } catch (Exception exception) {}
    if (i < 1)
      i = 1; 
    return i;
  }
  
  public long getPollHeartbeatInterval() {
    int i = getPollHeartbeatRetries();
    long l = 10L + 10100L / i;
    try {
      l = Long.getLong("hsqldb.lockfile.poll.interval", l).longValue();
    } catch (Exception exception) {}
    if (l <= 0L)
      l = 10L + 10100L / i; 
    return l;
  }
  
  private final void pollHeartbeat() throws FileSecurityException, LockHeldExternallyException, UnexpectedFileNotFoundException, UnexpectedEndOfFileException, UnexpectedFileIOException, WrongLengthException, WrongMagicException {
    boolean bool = false;
    int i = getPollHeartbeatRetries();
    long l = getPollHeartbeatInterval();
    BaseException baseException = null;
    int j = i;
    while (j > 0) {
      try {
        checkHeartbeat(true);
        bool = true;
        break;
      } catch (BaseException baseException1) {
        baseException = baseException1;
        try {
          Thread.sleep(l);
        } catch (InterruptedException interruptedException) {
          break;
        } 
        j--;
      } 
    } 
    if (!bool) {
      if (baseException instanceof FileSecurityException)
        throw (FileSecurityException)baseException; 
      if (baseException instanceof LockHeldExternallyException)
        throw (LockHeldExternallyException)baseException; 
      if (baseException instanceof UnexpectedFileNotFoundException)
        throw (UnexpectedFileNotFoundException)baseException; 
      if (baseException instanceof UnexpectedEndOfFileException)
        throw (UnexpectedEndOfFileException)baseException; 
      if (baseException instanceof UnexpectedFileIOException)
        throw (UnexpectedFileIOException)baseException; 
      if (baseException instanceof WrongLengthException)
        throw (WrongLengthException)baseException; 
      if (baseException instanceof WrongMagicException)
        throw (WrongMagicException)baseException; 
    } 
  }
  
  public final boolean tryLock() throws FileSecurityException, LockHeldExternallyException, UnexpectedFileNotFoundException, UnexpectedEndOfFileException, UnexpectedFileIOException, WrongLengthException, WrongMagicException {
    if (this.locked)
      return true; 
    try {
      pollHeartbeat();
      openRAF();
      doOptionalLockActions();
      writeMagic();
      writeHeartbeat();
      FileUtil.getFileUtil().deleteOnExit(this.file);
      this.locked = true;
      startHeartbeat();
    } finally {
      if (!this.locked) {
        doOptionalReleaseActions();
        try {
          closeRAF();
        } catch (Exception exception) {}
      } 
    } 
    return this.locked;
  }
  
  public final boolean tryRelease() throws FileSecurityException, UnexpectedFileIOException {
    boolean bool = !this.locked;
    if (bool)
      return true; 
    stopHeartbeat();
    doOptionalReleaseActions();
    UnexpectedFileIOException unexpectedFileIOException = null;
    FileSecurityException fileSecurityException = null;
    try {
      try {
        closeRAF();
      } catch (UnexpectedFileIOException unexpectedFileIOException1) {
        unexpectedFileIOException = unexpectedFileIOException1;
      } 
      try {
        Thread.sleep(100L);
      } catch (Exception exception) {}
      try {
        bool = this.file.delete();
      } catch (SecurityException securityException) {
        fileSecurityException = new FileSecurityException(this, "tryRelease", securityException);
      } 
    } finally {
      this.locked = false;
    } 
    if (unexpectedFileIOException != null)
      throw unexpectedFileIOException; 
    if (fileSecurityException != null)
      throw fileSecurityException; 
    return bool;
  }
  
  protected final void finalize() throws Throwable {
    tryRelease();
  }
  
  static {
    synchronized (LockFile.class) {
      boolean bool = false;
      try {
        bool = "true".equalsIgnoreCase(System.getProperty("hsqldb.lockfile.nio.filelock", bool ? "true" : "false"));
      } catch (Exception exception) {}
      boolean bool1 = false;
      Class<?> clazz = null;
      if (bool)
        try {
          Class.forName("java.nio.channels.FileLock");
          clazz = Class.forName("org.hsqldb.persist.NIOLockFile");
          bool1 = true;
        } catch (Exception exception) {} 
      NIO_FILELOCK_AVAILABLE = bool1;
      NIO_LOCKFILE_CLASS = clazz;
    } 
  }
  
  public static final class WrongMagicException extends BaseException {
    private final byte[] magic;
    
    public WrongMagicException(LockFile param1LockFile, String param1String, byte[] param1ArrayOfbyte) {
      super(param1LockFile, param1String);
      this.magic = param1ArrayOfbyte;
    }
    
    public String getMessage() {
      null = super.getMessage() + " magic: ";
      return null + ((this.magic == null) ? "null" : ("'" + StringConverter.byteArrayToHexString(this.magic) + "'"));
    }
    
    public byte[] getMagic() {
      return (this.magic == null) ? null : (byte[])this.magic.clone();
    }
  }
  
  public static final class WrongLengthException extends BaseException {
    private final long length;
    
    public WrongLengthException(LockFile param1LockFile, String param1String, long param1Long) {
      super(param1LockFile, param1String);
      this.length = param1Long;
    }
    
    public long getLength() {
      return this.length;
    }
    
    public String getMessage() {
      return super.getMessage() + " length: " + this.length;
    }
  }
  
  public static final class UnexpectedFileNotFoundException extends BaseException {
    private final FileNotFoundException reason;
    
    public UnexpectedFileNotFoundException(LockFile param1LockFile, String param1String, FileNotFoundException param1FileNotFoundException) {
      super(param1LockFile, param1String);
      this.reason = param1FileNotFoundException;
    }
    
    public FileNotFoundException getReason() {
      return this.reason;
    }
    
    public String getMessage() {
      return super.getMessage() + " reason: " + this.reason;
    }
  }
  
  public static final class UnexpectedFileIOException extends BaseException {
    private final IOException reason;
    
    public UnexpectedFileIOException(LockFile param1LockFile, String param1String, IOException param1IOException) {
      super(param1LockFile, param1String);
      this.reason = param1IOException;
    }
    
    public IOException getReason() {
      return this.reason;
    }
    
    public String getMessage() {
      return super.getMessage() + " reason: " + this.reason;
    }
  }
  
  public static final class UnexpectedEndOfFileException extends BaseException {
    private final EOFException reason;
    
    public UnexpectedEndOfFileException(LockFile param1LockFile, String param1String, EOFException param1EOFException) {
      super(param1LockFile, param1String);
      this.reason = param1EOFException;
    }
    
    public EOFException getReason() {
      return this.reason;
    }
    
    public String getMessage() {
      return super.getMessage() + " reason: " + this.reason;
    }
  }
  
  public static final class LockHeldExternallyException extends BaseException {
    private final long read;
    
    private final long heartbeat;
    
    public LockHeldExternallyException(LockFile param1LockFile, String param1String, long param1Long1, long param1Long2) {
      super(param1LockFile, param1String);
      this.read = param1Long1;
      this.heartbeat = param1Long2;
    }
    
    public long getHeartbeat() {
      return this.heartbeat;
    }
    
    public long getRead() {
      return this.read;
    }
    
    public String getMessage() {
      return super.getMessage() + " read: " + HsqlDateTime.getTimestampString(this.read) + " heartbeat - read: " + (this.heartbeat - this.read) + " ms.";
    }
  }
  
  public static final class FileSecurityException extends BaseException {
    private final SecurityException reason;
    
    public FileSecurityException(LockFile param1LockFile, String param1String, SecurityException param1SecurityException) {
      super(param1LockFile, param1String);
      this.reason = param1SecurityException;
    }
    
    public SecurityException getReason() {
      return this.reason;
    }
    
    public String getMessage() {
      return super.getMessage() + " reason: " + this.reason;
    }
  }
  
  public static final class FileCanonicalizationException extends BaseException {
    private final IOException reason;
    
    public FileCanonicalizationException(LockFile param1LockFile, String param1String, IOException param1IOException) {
      super(param1LockFile, param1String);
      this.reason = param1IOException;
    }
    
    public IOException getReason() {
      return this.reason;
    }
    
    public String getMessage() {
      return super.getMessage() + " reason: " + this.reason;
    }
  }
  
  public static abstract class BaseException extends Exception {
    private final LockFile lockFile;
    
    private final String inMethod;
    
    public BaseException(LockFile param1LockFile, String param1String) {
      if (param1LockFile == null)
        throw new NullPointerException("lockFile"); 
      if (param1String == null)
        throw new NullPointerException("inMethod"); 
      this.lockFile = param1LockFile;
      this.inMethod = param1String;
    }
    
    public String getMessage() {
      return "lockFile: " + this.lockFile + " method: " + this.inMethod;
    }
    
    public String getInMethod() {
      return this.inMethod;
    }
    
    public LockFile getLockFile() {
      return this.lockFile;
    }
  }
  
  private final class HeartbeatRunner implements Runnable {
    private HeartbeatRunner() {}
    
    public void run() {
      try {
        LockFile.this.writeHeartbeat();
      } catch (Throwable throwable) {
        Error.printSystemOut(throwable.toString());
      } 
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\LockFile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */