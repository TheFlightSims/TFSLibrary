/*     */ package org.geotools.data.shapefile.files;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.FilenameFilter;
/*     */ import java.io.FilterInputStream;
/*     */ import java.io.FilterOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.nio.MappedByteBuffer;
/*     */ import java.nio.channels.Channels;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.nio.channels.ReadableByteChannel;
/*     */ import java.nio.channels.WritableByteChannel;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.locks.ReentrantReadWriteLock;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.data.DataUtilities;
/*     */ import org.geotools.util.logging.Logging;
/*     */ 
/*     */ public class ShpFiles {
/*  78 */   static final Logger LOGGER = Logging.getLogger("org.geotools.data.shapefile");
/*     */   
/*  84 */   private final Map<ShpFileType, URL> urls = new ConcurrentHashMap<ShpFileType, URL>();
/*     */   
/*  89 */   private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
/*     */   
/*  95 */   private final Map<Thread, Collection<ShpFilesLocker>> lockers = new ConcurrentHashMap<Thread, Collection<ShpFilesLocker>>();
/*     */   
/* 100 */   private final MemoryMapCache mapCache = new MemoryMapCache();
/*     */   
/*     */   private boolean memoryMapCacheEnabled;
/*     */   
/*     */   public ShpFiles(String fileName) throws MalformedURLException {
/*     */     try {
/* 114 */       URL url = new URL(fileName);
/* 115 */       init(url);
/* 116 */     } catch (MalformedURLException e) {
/* 117 */       init((new File(fileName)).toURI().toURL());
/*     */     } 
/*     */   }
/*     */   
/*     */   public ShpFiles(File file) throws MalformedURLException {
/* 129 */     init(file.toURI().toURL());
/*     */   }
/*     */   
/*     */   public ShpFiles(URL url) throws IllegalArgumentException {
/* 139 */     init(url);
/*     */   }
/*     */   
/*     */   private void init(URL url) {
/* 143 */     String base = baseName(url);
/* 144 */     if (base == null)
/* 145 */       throw new IllegalArgumentException(url.getPath() + " is not one of the files types that is known to be associated with a shapefile"); 
/* 150 */     String urlString = url.toExternalForm();
/* 151 */     char lastChar = urlString.charAt(urlString.length() - 1);
/* 152 */     boolean upperCase = Character.isUpperCase(lastChar);
/* 154 */     for (ShpFileType type : ShpFileType.values()) {
/*     */       URL newURL;
/* 156 */       String extensionWithPeriod = type.extensionWithPeriod;
/* 157 */       if (upperCase) {
/* 158 */         extensionWithPeriod = extensionWithPeriod.toUpperCase();
/*     */       } else {
/* 160 */         extensionWithPeriod = extensionWithPeriod.toLowerCase();
/*     */       } 
/* 164 */       String string = base + extensionWithPeriod;
/*     */       try {
/* 166 */         newURL = new URL(url, string);
/* 167 */       } catch (MalformedURLException e) {
/* 169 */         throw new RuntimeException(e);
/*     */       } 
/* 171 */       this.urls.put(type, newURL);
/*     */     } 
/* 178 */     if (isLocal()) {
/* 179 */       Set<Map.Entry<ShpFileType, URL>> entries = this.urls.entrySet();
/* 180 */       Map<ShpFileType, URL> toUpdate = new HashMap<ShpFileType, URL>();
/* 181 */       for (Map.Entry<ShpFileType, URL> entry : entries) {
/* 182 */         if (!exists(entry.getKey())) {
/* 183 */           url = findExistingFile(entry.getKey(), entry.getValue());
/* 184 */           if (url != null)
/* 185 */             toUpdate.put(entry.getKey(), url); 
/*     */         } 
/*     */       } 
/* 190 */       this.urls.putAll(toUpdate);
/*     */     } 
/*     */   }
/*     */   
/*     */   private URL findExistingFile(ShpFileType shpFileType, URL value) {
/* 197 */     final File file = DataUtilities.urlToFile(value);
/* 198 */     File directory = file.getParentFile();
/* 199 */     if (directory == null || !directory.exists())
/* 201 */       return null; 
/* 203 */     File[] files = directory.listFiles(new FilenameFilter() {
/*     */           public boolean accept(File dir, String name) {
/* 206 */             return file.getName().equalsIgnoreCase(name);
/*     */           }
/*     */         });
/* 210 */     if (files.length > 0)
/*     */       try {
/* 212 */         return files[0].toURI().toURL();
/* 213 */       } catch (MalformedURLException e) {
/* 214 */         LOGGER.log(Level.SEVERE, "", e);
/*     */       }  
/* 217 */     return null;
/*     */   }
/*     */   
/*     */   protected void finalize() throws Throwable {
/* 225 */     super.finalize();
/* 226 */     dispose();
/*     */   }
/*     */   
/*     */   public void dispose() {
/* 230 */     if (numberOfLocks() != 0) {
/* 231 */       logCurrentLockers(Level.SEVERE);
/* 232 */       this.lockers.clear();
/*     */     } 
/* 234 */     this.mapCache.clean();
/*     */   }
/*     */   
/*     */   public void logCurrentLockers(Level logLevel) {
/* 243 */     for (Collection<ShpFilesLocker> lockerList : this.lockers.values()) {
/* 244 */       for (ShpFilesLocker locker : lockerList) {
/* 245 */         StringBuilder sb = new StringBuilder("The following locker still has a lock: ");
/* 246 */         sb.append(locker);
/* 247 */         LOGGER.log(logLevel, sb.toString(), locker.getTrace());
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private String baseName(Object obj) {
/* 253 */     for (ShpFileType type : ShpFileType.values()) {
/* 254 */       String base = null;
/* 255 */       if (obj instanceof File) {
/* 256 */         File file = (File)obj;
/* 257 */         base = type.toBase(file);
/*     */       } 
/* 259 */       if (obj instanceof URL) {
/* 260 */         URL file = (URL)obj;
/* 261 */         base = type.toBase(file);
/*     */       } 
/* 263 */       if (base != null)
/* 264 */         return base; 
/*     */     } 
/* 267 */     return null;
/*     */   }
/*     */   
/*     */   public Map<ShpFileType, String> getFileNames() {
/* 276 */     Map<ShpFileType, String> result = new HashMap<ShpFileType, String>();
/* 277 */     Set<Map.Entry<ShpFileType, URL>> entries = this.urls.entrySet();
/* 279 */     for (Map.Entry<ShpFileType, URL> entry : entries)
/* 280 */       result.put(entry.getKey(), ((URL)entry.getValue()).toExternalForm()); 
/* 283 */     return result;
/*     */   }
/*     */   
/*     */   public String get(ShpFileType type) {
/* 300 */     return ((URL)this.urls.get(type)).toExternalForm();
/*     */   }
/*     */   
/*     */   public int numberOfLocks() {
/* 310 */     int count = 0;
/* 311 */     for (Collection<ShpFilesLocker> lockerList : this.lockers.values())
/* 312 */       count += lockerList.size(); 
/* 314 */     return count;
/*     */   }
/*     */   
/*     */   public File acquireReadFile(ShpFileType type, FileReader requestor) {
/* 332 */     if (!isLocal())
/* 333 */       throw new IllegalStateException("This method only applies if the files are local"); 
/* 335 */     URL url = acquireRead(type, requestor);
/* 336 */     return DataUtilities.urlToFile(url);
/*     */   }
/*     */   
/*     */   public URL acquireRead(ShpFileType type, FileReader requestor) {
/* 354 */     URL url = this.urls.get(type);
/* 355 */     if (url == null)
/* 356 */       return null; 
/* 358 */     this.readWriteLock.readLock().lock();
/* 359 */     Collection<ShpFilesLocker> threadLockers = getCurrentThreadLockers();
/* 360 */     threadLockers.add(new ShpFilesLocker(url, requestor));
/* 361 */     return url;
/*     */   }
/*     */   
/*     */   public Result<URL, State> tryAcquireRead(ShpFileType type, FileReader requestor) {
/* 382 */     URL url = this.urls.get(type);
/* 383 */     if (url == null)
/* 384 */       return new Result<URL, State>(null, State.NOT_EXIST); 
/* 387 */     boolean locked = this.readWriteLock.readLock().tryLock();
/* 388 */     if (!locked)
/* 389 */       return new Result<URL, State>(null, State.LOCKED); 
/* 392 */     getCurrentThreadLockers().add(new ShpFilesLocker(url, requestor));
/* 394 */     return new Result<URL, State>(url, State.GOOD);
/*     */   }
/*     */   
/*     */   public void unlockRead(File file, FileReader requestor) {
/* 404 */     Collection<URL> allURLS = this.urls.values();
/* 405 */     for (URL url : allURLS) {
/* 406 */       if (DataUtilities.urlToFile(url).equals(file))
/* 407 */         unlockRead(url, requestor); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void unlockRead(URL url, FileReader requestor) {
/* 419 */     if (url == null)
/* 420 */       throw new NullPointerException("url cannot be null"); 
/* 422 */     if (requestor == null)
/* 423 */       throw new NullPointerException("requestor cannot be null"); 
/* 426 */     Collection<ShpFilesLocker> threadLockers = getCurrentThreadLockers();
/* 427 */     boolean removed = threadLockers.remove(new ShpFilesLocker(url, requestor));
/* 428 */     if (!removed)
/* 429 */       throw new IllegalArgumentException("Expected requestor " + requestor + " to have locked the url but it does not hold the lock for the URL"); 
/* 432 */     if (threadLockers.size() == 0)
/* 433 */       this.lockers.remove(Thread.currentThread()); 
/* 434 */     this.readWriteLock.readLock().unlock();
/*     */   }
/*     */   
/*     */   public File acquireWriteFile(ShpFileType type, FileWriter requestor) {
/* 455 */     if (!isLocal())
/* 456 */       throw new IllegalStateException("This method only applies if the files are local"); 
/* 458 */     URL url = acquireWrite(type, requestor);
/* 459 */     return DataUtilities.urlToFile(url);
/*     */   }
/*     */   
/*     */   public URL acquireWrite(ShpFileType type, FileWriter requestor) {
/* 480 */     URL url = this.urls.get(type);
/* 481 */     if (url == null)
/* 482 */       return null; 
/* 486 */     Collection<ShpFilesLocker> threadLockers = getCurrentThreadLockers();
/* 487 */     relinquishReadLocks(threadLockers);
/* 488 */     this.readWriteLock.writeLock().lock();
/* 489 */     threadLockers.add(new ShpFilesLocker(url, requestor));
/* 490 */     this.mapCache.cleanFileCache(url);
/* 491 */     return url;
/*     */   }
/*     */   
/*     */   public Result<URL, State> tryAcquireWrite(ShpFileType type, FileWriter requestor) {
/* 514 */     URL url = this.urls.get(type);
/* 515 */     if (url == null)
/* 516 */       return new Result<URL, State>(null, State.NOT_EXIST); 
/* 519 */     Collection<ShpFilesLocker> threadLockers = getCurrentThreadLockers();
/* 520 */     boolean locked = this.readWriteLock.writeLock().tryLock();
/* 521 */     if (!locked && threadLockers.size() > 1) {
/* 523 */       relinquishReadLocks(threadLockers);
/* 524 */       locked = this.readWriteLock.writeLock().tryLock();
/* 525 */       if (!locked) {
/* 526 */         regainReadLocks(threadLockers);
/* 527 */         return new Result<URL, State>(null, State.LOCKED);
/*     */       } 
/*     */     } 
/* 531 */     threadLockers.add(new ShpFilesLocker(url, requestor));
/* 532 */     return new Result<URL, State>(url, State.GOOD);
/*     */   }
/*     */   
/*     */   public void unlockWrite(File file, FileWriter requestor) {
/* 542 */     Collection<URL> allURLS = this.urls.values();
/* 543 */     for (URL url : allURLS) {
/* 544 */       if (DataUtilities.urlToFile(url).equals(file))
/* 545 */         unlockWrite(url, requestor); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void unlockWrite(URL url, FileWriter requestor) {
/* 558 */     if (url == null)
/* 559 */       throw new NullPointerException("url cannot be null"); 
/* 561 */     if (requestor == null)
/* 562 */       throw new NullPointerException("requestor cannot be null"); 
/* 564 */     Collection<ShpFilesLocker> threadLockers = getCurrentThreadLockers();
/* 565 */     boolean removed = threadLockers.remove(new ShpFilesLocker(url, requestor));
/* 566 */     if (!removed)
/* 567 */       throw new IllegalArgumentException("Expected requestor " + requestor + " to have locked the url but it does not hold the lock for the URL"); 
/* 571 */     if (threadLockers.size() == 0) {
/* 572 */       this.lockers.remove(Thread.currentThread());
/*     */     } else {
/* 575 */       regainReadLocks(threadLockers);
/*     */     } 
/* 577 */     this.readWriteLock.writeLock().unlock();
/*     */   }
/*     */   
/*     */   private Collection<ShpFilesLocker> getCurrentThreadLockers() {
/* 586 */     Collection<ShpFilesLocker> threadLockers = this.lockers.get(Thread.currentThread());
/* 587 */     if (threadLockers == null) {
/* 588 */       threadLockers = new ArrayList<ShpFilesLocker>();
/* 589 */       this.lockers.put(Thread.currentThread(), threadLockers);
/*     */     } 
/* 591 */     return threadLockers;
/*     */   }
/*     */   
/*     */   private void relinquishReadLocks(Collection<ShpFilesLocker> threadLockers) {
/* 600 */     for (ShpFilesLocker shpFilesLocker : threadLockers) {
/* 601 */       if (shpFilesLocker.reader != null && !shpFilesLocker.upgraded) {
/* 602 */         this.readWriteLock.readLock().unlock();
/* 603 */         shpFilesLocker.upgraded = true;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void regainReadLocks(Collection<ShpFilesLocker> threadLockers) {
/* 614 */     for (ShpFilesLocker shpFilesLocker : threadLockers) {
/* 615 */       if (shpFilesLocker.reader != null && shpFilesLocker.upgraded) {
/* 616 */         this.readWriteLock.readLock().lock();
/* 617 */         shpFilesLocker.upgraded = false;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isLocal() {
/* 628 */     return ((URL)this.urls.get(ShpFileType.SHP)).toExternalForm().toLowerCase().startsWith("file:");
/*     */   }
/*     */   
/*     */   public boolean isWritable() {
/* 637 */     if (!isLocal())
/* 638 */       return false; 
/* 640 */     return (DataUtilities.urlToFile(this.urls.get(ShpFileType.SHP)).canWrite() && DataUtilities.urlToFile(this.urls.get(ShpFileType.DBF)).canWrite());
/*     */   }
/*     */   
/*     */   public boolean delete() {
/* 649 */     BasicShpFileWriter requestor = new BasicShpFileWriter("ShpFiles for deleting all files");
/* 650 */     URL writeLockURL = acquireWrite(ShpFileType.SHP, requestor);
/* 651 */     boolean retVal = true;
/*     */     try {
/* 653 */       if (isLocal()) {
/* 654 */         Collection<URL> values = this.urls.values();
/* 655 */         for (URL url : values) {
/* 656 */           File f = DataUtilities.urlToFile(url);
/* 657 */           if (!f.delete())
/* 658 */             retVal = false; 
/*     */         } 
/*     */       } else {
/* 662 */         retVal = false;
/*     */       } 
/*     */     } finally {
/* 665 */       unlockWrite(writeLockURL, requestor);
/*     */     } 
/* 667 */     return retVal;
/*     */   }
/*     */   
/*     */   public InputStream getInputStream(ShpFileType type, final FileReader requestor) throws IOException {
/* 682 */     final URL url = acquireRead(type, requestor);
/*     */     try {
/* 685 */       FilterInputStream input = new FilterInputStream(url.openStream()) {
/*     */           private volatile boolean closed = false;
/*     */           
/*     */           public void close() throws IOException {
/*     */             try {
/* 692 */               super.close();
/*     */             } finally {
/* 694 */               if (!this.closed) {
/* 695 */                 this.closed = true;
/* 696 */                 ShpFiles.this.unlockRead(url, requestor);
/*     */               } 
/*     */             } 
/*     */           }
/*     */         };
/* 702 */       return input;
/* 703 */     } catch (Throwable e) {
/* 704 */       unlockRead(url, requestor);
/* 705 */       if (e instanceof IOException)
/* 706 */         throw (IOException)e; 
/* 707 */       if (e instanceof RuntimeException)
/* 708 */         throw (RuntimeException)e; 
/* 709 */       if (e instanceof Error)
/* 710 */         throw (Error)e; 
/* 712 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public OutputStream getOutputStream(ShpFileType type, final FileWriter requestor) throws IOException {
/* 729 */     final URL url = acquireWrite(type, requestor);
/*     */     try {
/*     */       OutputStream out;
/* 734 */       if (isLocal()) {
/* 735 */         File file = DataUtilities.urlToFile(url);
/* 736 */         out = new FileOutputStream(file);
/*     */       } else {
/* 738 */         URLConnection connection = url.openConnection();
/* 739 */         connection.setDoOutput(true);
/* 740 */         out = connection.getOutputStream();
/*     */       } 
/* 743 */       FilterOutputStream output = new FilterOutputStream(out) {
/*     */           private volatile boolean closed = false;
/*     */           
/*     */           public void close() throws IOException {
/*     */             try {
/* 750 */               super.close();
/*     */             } finally {
/* 752 */               if (!this.closed) {
/* 753 */                 this.closed = true;
/* 754 */                 ShpFiles.this.unlockWrite(url, requestor);
/*     */               } 
/*     */             } 
/*     */           }
/*     */         };
/* 761 */       return output;
/* 762 */     } catch (Throwable e) {
/* 763 */       unlockWrite(url, requestor);
/* 764 */       if (e instanceof IOException)
/* 765 */         throw (IOException)e; 
/* 766 */       if (e instanceof RuntimeException)
/* 767 */         throw (RuntimeException)e; 
/* 768 */       if (e instanceof Error)
/* 769 */         throw (Error)e; 
/* 771 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ReadableByteChannel getReadChannel(ShpFileType type, FileReader requestor) throws IOException {
/* 789 */     URL url = acquireRead(type, requestor);
/* 790 */     ReadableByteChannel channel = null;
/*     */     try {
/* 792 */       if (isLocal()) {
/* 794 */         File file = DataUtilities.urlToFile(url);
/* 796 */         RandomAccessFile raf = new RandomAccessFile(file, "r");
/* 797 */         channel = new FileChannelDecorator(raf.getChannel(), this, url, requestor);
/*     */       } else {
/* 800 */         InputStream in = url.openConnection().getInputStream();
/* 801 */         channel = new ReadableByteChannelDecorator(Channels.newChannel(in), this, url, requestor);
/*     */       } 
/* 804 */     } catch (Throwable e) {
/* 805 */       unlockRead(url, requestor);
/* 806 */       if (e instanceof IOException)
/* 807 */         throw (IOException)e; 
/* 808 */       if (e instanceof RuntimeException)
/* 809 */         throw (RuntimeException)e; 
/* 810 */       if (e instanceof Error)
/* 811 */         throw (Error)e; 
/* 813 */       throw new RuntimeException(e);
/*     */     } 
/* 816 */     return channel;
/*     */   }
/*     */   
/*     */   public WritableByteChannel getWriteChannel(ShpFileType type, FileWriter requestor) throws IOException {
/* 839 */     URL url = acquireWrite(type, requestor);
/*     */     try {
/*     */       WritableByteChannel channel;
/* 843 */       if (isLocal()) {
/* 845 */         File file = DataUtilities.urlToFile(url);
/* 847 */         RandomAccessFile raf = new RandomAccessFile(file, "rw");
/* 848 */         channel = new FileChannelDecorator(raf.getChannel(), this, url, requestor);
/* 850 */         ((FileChannel)channel).lock();
/*     */       } else {
/* 853 */         OutputStream out = url.openConnection().getOutputStream();
/* 854 */         channel = new WritableByteChannelDecorator(Channels.newChannel(out), this, url, requestor);
/*     */       } 
/* 858 */       return channel;
/* 859 */     } catch (Throwable e) {
/* 860 */       unlockWrite(url, requestor);
/* 861 */       if (e instanceof IOException)
/* 862 */         throw (IOException)e; 
/* 863 */       if (e instanceof RuntimeException)
/* 864 */         throw (RuntimeException)e; 
/* 865 */       if (e instanceof Error)
/* 866 */         throw (Error)e; 
/* 868 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public enum State {
/* 877 */     NOT_EXIST, LOCKED, GOOD;
/*     */   }
/*     */   
/*     */   public StorageFile getStorageFile(ShpFileType type) throws IOException {
/* 898 */     String baseName = getTypeName();
/* 899 */     if (baseName.length() < 3)
/* 900 */       baseName = baseName + "___".substring(0, 3 - baseName.length()); 
/* 902 */     File tmp = File.createTempFile(baseName, type.extensionWithPeriod);
/* 903 */     return new StorageFile(this, tmp, type);
/*     */   }
/*     */   
/*     */   public String getTypeName() {
/* 907 */     String path = ShpFileType.SHP.toBase(this.urls.get(ShpFileType.SHP));
/* 908 */     int slash = Math.max(0, path.lastIndexOf('/') + 1);
/* 909 */     return path.substring(slash);
/*     */   }
/*     */   
/*     */   MappedByteBuffer map(FileChannel wrapped, URL url, FileChannel.MapMode mode, long position, long size) throws IOException {
/* 926 */     if (this.memoryMapCacheEnabled)
/* 927 */       return this.mapCache.map(wrapped, url, mode, position, size); 
/* 929 */     return wrapped.map(mode, position, size);
/*     */   }
/*     */   
/*     */   public boolean isMemoryMapCacheEnabled() {
/* 940 */     return this.memoryMapCacheEnabled;
/*     */   }
/*     */   
/*     */   public void setMemoryMapCacheEnabled(boolean memoryMapCacheEnabled) {
/* 950 */     this.memoryMapCacheEnabled = memoryMapCacheEnabled;
/* 951 */     if (!memoryMapCacheEnabled)
/* 952 */       this.mapCache.clean(); 
/*     */   }
/*     */   
/*     */   public boolean exists(ShpFileType fileType) throws IllegalArgumentException {
/* 966 */     if (!isLocal())
/* 967 */       throw new IllegalArgumentException("This method only makes sense if the files are local"); 
/* 970 */     URL url = this.urls.get(fileType);
/* 971 */     if (url == null)
/* 972 */       return false; 
/* 975 */     File file = DataUtilities.urlToFile(url);
/* 976 */     return file.exists();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\files\ShpFiles.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */