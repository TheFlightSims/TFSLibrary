/*     */ package org.mapdb;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.EOFException;
/*     */ import java.io.File;
/*     */ import java.io.IOError;
/*     */ import java.io.IOException;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.lang.reflect.Method;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.MappedByteBuffer;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.util.Arrays;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ 
/*     */ public abstract class Volume {
/*     */   public void ensureAvailable(long offset) {
/*  49 */     if (!tryAvailable(offset))
/*  50 */       throw new IOError(new IOException("no free space to expand Volume")); 
/*     */   }
/*     */   
/*     */   public abstract boolean tryAvailable(long paramLong);
/*     */   
/*     */   public abstract void truncate(long paramLong);
/*     */   
/*     */   public abstract void putLong(long paramLong1, long paramLong2);
/*     */   
/*     */   public abstract void putInt(long paramLong, int paramInt);
/*     */   
/*     */   public abstract void putByte(long paramLong, byte paramByte);
/*     */   
/*     */   public abstract void putData(long paramLong, byte[] paramArrayOfbyte, int paramInt1, int paramInt2);
/*     */   
/*     */   public abstract void putData(long paramLong, ByteBuffer paramByteBuffer);
/*     */   
/*     */   public abstract long getLong(long paramLong);
/*     */   
/*     */   public abstract int getInt(long paramLong);
/*     */   
/*     */   public abstract byte getByte(long paramLong);
/*     */   
/*     */   public abstract DataInput getDataInput(long paramLong, int paramInt);
/*     */   
/*     */   public abstract void close();
/*     */   
/*     */   public abstract void sync();
/*     */   
/*     */   public abstract boolean isEmpty();
/*     */   
/*     */   public abstract void deleteFile();
/*     */   
/*     */   public abstract boolean isSliced();
/*     */   
/*     */   public void putUnsignedShort(long offset, int value) {
/*  86 */     putByte(offset, (byte)(value >> 8));
/*  87 */     putByte(offset + 1L, (byte)value);
/*     */   }
/*     */   
/*     */   public int getUnsignedShort(long offset) {
/*  91 */     return (getByte(offset) & 0xFF) << 8 | getByte(offset + 1L) & 0xFF;
/*     */   }
/*     */   
/*     */   public int getUnsignedByte(long offset) {
/*  96 */     return getByte(offset) & 0xFF;
/*     */   }
/*     */   
/*     */   public void putUnsignedByte(long offset, int b) {
/* 100 */     putByte(offset, (byte)(b & 0xFF));
/*     */   }
/*     */   
/*     */   public long getSixLong(long pos) {
/* 107 */     return (getByte(pos + 0L) & 0xFF) << 40L | (getByte(pos + 1L) & 0xFF) << 32L | (getByte(pos + 2L) & 0xFF) << 24L | (getByte(pos + 3L) & 0xFF) << 16L | (getByte(pos + 4L) & 0xFF) << 8L | (getByte(pos + 5L) & 0xFF) << 0L;
/*     */   }
/*     */   
/*     */   public void putSixLong(long pos, long value) {
/* 120 */     assert value >= 0L && value >>> 48L == 0L : "value does not fit";
/* 122 */     putByte(pos + 0L, (byte)(int)(0xFFL & value >> 40L));
/* 123 */     putByte(pos + 1L, (byte)(int)(0xFFL & value >> 32L));
/* 124 */     putByte(pos + 2L, (byte)(int)(0xFFL & value >> 24L));
/* 125 */     putByte(pos + 3L, (byte)(int)(0xFFL & value >> 16L));
/* 126 */     putByte(pos + 4L, (byte)(int)(0xFFL & value >> 8L));
/* 127 */     putByte(pos + 5L, (byte)(int)(0xFFL & value >> 0L));
/*     */   }
/*     */   
/*     */   public int putPackedLong(long pos, long value) {
/* 135 */     assert value >= 0L : "negative value";
/* 137 */     int ret = 0;
/* 139 */     while ((value & 0xFFFFFFFFFFFFFF80L) != 0L) {
/* 140 */       putUnsignedByte(pos + ret++, (int)value & 0x7F | 0x80);
/* 141 */       value >>>= 7L;
/*     */     } 
/* 143 */     putUnsignedByte(pos + ret++, (byte)(int)value);
/* 144 */     return ret;
/*     */   }
/*     */   
/*     */   public abstract File getFile();
/*     */   
/*     */   public long getPackedLong(long pos) {
/* 154 */     long result = 0L;
/* 155 */     for (int offset = 0; offset < 64; offset += 7) {
/* 156 */       long b = getUnsignedByte(pos++);
/* 157 */       result |= (b & 0x7FL) << offset;
/* 158 */       if ((b & 0x80L) == 0L)
/* 159 */         return result; 
/*     */     } 
/* 162 */     throw new AssertionError("Malformed long.");
/*     */   }
/*     */   
/*     */   public static Volume volumeForFile(File f, boolean useRandomAccessFile, boolean readOnly, long sizeLimit, int chunkShift, int sizeIncrement) {
/* 176 */     return volumeForFile(f, useRandomAccessFile, readOnly, sizeLimit, chunkShift, sizeIncrement, false);
/*     */   }
/*     */   
/*     */   public static Volume volumeForFile(File f, boolean useRandomAccessFile, boolean readOnly, long sizeLimit, int chunkShift, int sizeIncrement, boolean asyncWriteEnabled) {
/* 180 */     return useRandomAccessFile ? new FileChannelVol(f, readOnly, sizeLimit, chunkShift, sizeIncrement) : new MappedFileVol(f, readOnly, sizeLimit, chunkShift, sizeIncrement, asyncWriteEnabled);
/*     */   }
/*     */   
/*     */   public static Factory fileFactory(File indexFile, int rafMode, boolean readOnly, long sizeLimit, int chunkShift, int sizeIncrement) {
/* 188 */     return fileFactory(indexFile, rafMode, readOnly, sizeLimit, chunkShift, sizeIncrement, new File(indexFile.getPath() + ".p"), new File(indexFile.getPath() + ".t"));
/*     */   }
/*     */   
/*     */   public static Factory fileFactory(File indexFile, int rafMode, boolean readOnly, long sizeLimit, int chunkShift, int sizeIncrement, File physFile, File transLogFile) {
/* 204 */     return fileFactory(indexFile, rafMode, readOnly, sizeLimit, chunkShift, sizeIncrement, physFile, transLogFile, false);
/*     */   }
/*     */   
/*     */   public static Factory fileFactory(final File indexFile, final int rafMode, final boolean readOnly, final long sizeLimit, final int chunkShift, final int sizeIncrement, final File physFile, final File transLogFile, final boolean asyncWriteEnabled) {
/* 227 */     return new Factory() {
/*     */         public Volume createIndexVolume() {
/* 230 */           return Volume.volumeForFile(indexFile, (rafMode > 1), readOnly, sizeLimit, chunkShift, sizeIncrement, asyncWriteEnabled);
/*     */         }
/*     */         
/*     */         public Volume createPhysVolume() {
/* 235 */           return Volume.volumeForFile(physFile, (rafMode > 0), readOnly, sizeLimit, chunkShift, sizeIncrement, asyncWriteEnabled);
/*     */         }
/*     */         
/*     */         public Volume createTransLogVolume() {
/* 240 */           return Volume.volumeForFile(transLogFile, (rafMode > 0), readOnly, sizeLimit, chunkShift, sizeIncrement, asyncWriteEnabled);
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static Factory memoryFactory(final boolean useDirectBuffer, final long sizeLimit, final int chunkShift) {
/* 247 */     return new Factory() {
/*     */         public synchronized Volume createIndexVolume() {
/* 250 */           return new Volume.MemoryVol(useDirectBuffer, sizeLimit, chunkShift);
/*     */         }
/*     */         
/*     */         public synchronized Volume createPhysVolume() {
/* 254 */           return new Volume.MemoryVol(useDirectBuffer, sizeLimit, chunkShift);
/*     */         }
/*     */         
/*     */         public synchronized Volume createTransLogVolume() {
/* 258 */           return new Volume.MemoryVol(useDirectBuffer, sizeLimit, chunkShift);
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static interface Factory {
/*     */     Volume createIndexVolume();
/*     */     
/*     */     Volume createPhysVolume();
/*     */     
/*     */     Volume createTransLogVolume();
/*     */   }
/*     */   
/*     */   public static abstract class ByteBufferVol extends Volume {
/* 272 */     protected final ReentrantLock growLock = new ReentrantLock(false);
/*     */     
/*     */     protected final long sizeLimit;
/*     */     
/*     */     protected final boolean hasLimit;
/*     */     
/*     */     protected final int chunkShift;
/*     */     
/*     */     protected final int chunkSizeModMask;
/*     */     
/*     */     protected final int chunkSize;
/*     */     
/* 280 */     protected volatile ByteBuffer[] chunks = new ByteBuffer[0];
/*     */     
/*     */     protected final boolean readOnly;
/*     */     
/*     */     protected final boolean asyncWriteEnabled;
/*     */     
/*     */     protected ByteBufferVol(boolean readOnly, long sizeLimit, int chunkShift) {
/* 290 */       this(readOnly, sizeLimit, chunkShift, false);
/*     */     }
/*     */     
/*     */     protected ByteBufferVol(boolean readOnly, long sizeLimit, int chunkShift, boolean asyncWriteEnabled) {
/* 294 */       this.readOnly = readOnly;
/* 295 */       this.sizeLimit = sizeLimit;
/* 296 */       this.chunkShift = chunkShift;
/* 297 */       this.chunkSize = 1 << chunkShift;
/* 298 */       this.chunkSizeModMask = this.chunkSize - 1;
/* 300 */       this.hasLimit = (sizeLimit > 0L);
/* 301 */       this.asyncWriteEnabled = asyncWriteEnabled;
/*     */     }
/*     */     
/*     */     public final boolean tryAvailable(long offset) {
/* 306 */       if (this.hasLimit && offset > this.sizeLimit)
/* 306 */         return false; 
/* 308 */       int chunkPos = (int)(offset >>> this.chunkShift);
/* 311 */       if (chunkPos < this.chunks.length)
/* 312 */         return true; 
/* 315 */       this.growLock.lock();
/*     */       try {
/* 318 */         if (chunkPos < this.chunks.length)
/* 319 */           return true; 
/* 321 */         int oldSize = this.chunks.length;
/* 322 */         ByteBuffer[] chunks2 = this.chunks;
/* 324 */         chunks2 = Arrays.<ByteBuffer>copyOf(chunks2, Math.max(chunkPos + 1, chunks2.length + chunks2.length / 1000));
/* 326 */         for (int pos = oldSize; pos < chunks2.length; pos++)
/* 327 */           chunks2[pos] = makeNewBuffer(1L * this.chunkSize * pos); 
/* 331 */         this.chunks = chunks2;
/*     */       } finally {
/* 333 */         this.growLock.unlock();
/*     */       } 
/* 335 */       return true;
/*     */     }
/*     */     
/*     */     public final void putLong(long offset, long value) {
/* 341 */       this.chunks[(int)(offset >>> this.chunkShift)].putLong((int)(offset & this.chunkSizeModMask), value);
/*     */     }
/*     */     
/*     */     public final void putInt(long offset, int value) {
/* 345 */       this.chunks[(int)(offset >>> this.chunkShift)].putInt((int)(offset & this.chunkSizeModMask), value);
/*     */     }
/*     */     
/*     */     public final void putByte(long offset, byte value) {
/* 350 */       this.chunks[(int)(offset >>> this.chunkShift)].put((int)(offset & this.chunkSizeModMask), value);
/*     */     }
/*     */     
/*     */     public void putData(long offset, byte[] src, int srcPos, int srcSize) {
/* 356 */       ByteBuffer b1 = this.chunks[(int)(offset >>> this.chunkShift)].duplicate();
/* 357 */       int bufPos = (int)(offset & this.chunkSizeModMask);
/* 359 */       b1.position(bufPos);
/* 360 */       b1.put(src, srcPos, srcSize);
/*     */     }
/*     */     
/*     */     public final void putData(long offset, ByteBuffer buf) {
/* 364 */       ByteBuffer b1 = this.chunks[(int)(offset >>> this.chunkShift)].duplicate();
/* 365 */       int bufPos = (int)(offset & this.chunkSizeModMask);
/* 367 */       b1.position(bufPos);
/* 368 */       b1.put(buf);
/*     */     }
/*     */     
/*     */     public final long getLong(long offset) {
/* 372 */       return this.chunks[(int)(offset >>> this.chunkShift)].getLong((int)(offset & this.chunkSizeModMask));
/*     */     }
/*     */     
/*     */     public final int getInt(long offset) {
/* 376 */       return this.chunks[(int)(offset >>> this.chunkShift)].getInt((int)(offset & this.chunkSizeModMask));
/*     */     }
/*     */     
/*     */     public final byte getByte(long offset) {
/* 381 */       return this.chunks[(int)(offset >>> this.chunkShift)].get((int)(offset & this.chunkSizeModMask));
/*     */     }
/*     */     
/*     */     public final DataInput2 getDataInput(long offset, int size) {
/* 387 */       return new DataInput2(this.chunks[(int)(offset >>> this.chunkShift)], (int)(offset & this.chunkSizeModMask));
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 392 */       return (this.chunks.length == 0);
/*     */     }
/*     */     
/*     */     public boolean isSliced() {
/* 397 */       return true;
/*     */     }
/*     */     
/*     */     protected void unmap(MappedByteBuffer b) {
/*     */       try {
/* 410 */         if (unmapHackSupported && !this.asyncWriteEnabled) {
/* 414 */           Method cleanerMethod = b.getClass().getMethod("cleaner", new Class[0]);
/* 415 */           if (cleanerMethod != null) {
/* 416 */             cleanerMethod.setAccessible(true);
/* 417 */             Object cleaner = cleanerMethod.invoke(b, new Object[0]);
/* 418 */             if (cleaner != null) {
/* 419 */               Method clearMethod = cleaner.getClass().getMethod("clean", new Class[0]);
/* 420 */               if (clearMethod != null)
/* 421 */                 clearMethod.invoke(cleaner, new Object[0]); 
/*     */             } 
/*     */           } 
/*     */         } 
/* 425 */       } catch (Exception e) {
/* 426 */         unmapHackSupported = false;
/*     */       } 
/*     */     }
/*     */     
/*     */     private static boolean unmapHackSupported = true;
/*     */     
/*     */     static {
/*     */       try {
/* 435 */         unmapHackSupported = (SerializerPojo.classForName("sun.nio.ch.DirectBuffer") != null);
/* 437 */       } catch (Exception e) {
/* 438 */         unmapHackSupported = false;
/*     */       } 
/*     */     }
/*     */     
/* 444 */     private static boolean windowsWorkaround = System.getProperty("os.name").toLowerCase().startsWith("win");
/*     */     
/*     */     protected abstract ByteBuffer makeNewBuffer(long param1Long);
/*     */   }
/*     */   
/*     */   public static final class MappedFileVol extends ByteBufferVol {
/*     */     protected final File file;
/*     */     
/*     */     protected final FileChannel fileChannel;
/*     */     
/*     */     protected final FileChannel.MapMode mapMode;
/*     */     
/*     */     protected final RandomAccessFile raf;
/*     */     
/*     */     public MappedFileVol(File file, boolean readOnly, long sizeLimit, int chunkShift, int sizeIncrement) {
/* 456 */       this(file, readOnly, sizeLimit, chunkShift, sizeIncrement, false);
/*     */     }
/*     */     
/*     */     public MappedFileVol(File file, boolean readOnly, long sizeLimit, int chunkShift, int sizeIncrement, boolean asyncWriteEnabled) {
/* 461 */       super(readOnly, sizeLimit, chunkShift, asyncWriteEnabled);
/* 462 */       this.file = file;
/* 463 */       this.mapMode = readOnly ? FileChannel.MapMode.READ_ONLY : FileChannel.MapMode.READ_WRITE;
/*     */       try {
/* 465 */         Volume.FileChannelVol.checkFolder(file, readOnly);
/* 466 */         this.raf = new RandomAccessFile(file, readOnly ? "r" : "rw");
/* 467 */         this.fileChannel = this.raf.getChannel();
/* 469 */         long fileSize = this.fileChannel.size();
/* 470 */         if (fileSize > 0L) {
/* 472 */           this.chunks = new ByteBuffer[(int)(fileSize >>> chunkShift)];
/* 473 */           for (int i = 0; i < this.chunks.length; i++)
/* 474 */             this.chunks[i] = makeNewBuffer(1L * i * this.chunkSize); 
/*     */         } else {
/* 477 */           this.chunks = new ByteBuffer[0];
/*     */         } 
/* 479 */       } catch (IOException e) {
/* 480 */         throw new IOError(e);
/*     */       } 
/*     */     }
/*     */     
/*     */     public void close() {
/* 486 */       this.growLock.lock();
/*     */       try {
/* 488 */         this.fileChannel.close();
/* 489 */         this.raf.close();
/* 495 */         for (ByteBuffer b : this.chunks) {
/* 496 */           if (b != null && b instanceof MappedByteBuffer)
/* 497 */             unmap((MappedByteBuffer)b); 
/*     */         } 
/* 501 */         this.chunks = null;
/* 503 */       } catch (IOException e) {
/* 504 */         throw new IOError(e);
/*     */       } finally {
/* 506 */         this.growLock.unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     public void sync() {
/* 513 */       if (this.readOnly)
/*     */         return; 
/* 514 */       this.growLock.lock();
/*     */       try {
/* 516 */         for (ByteBuffer b : this.chunks) {
/* 517 */           if (b != null && b instanceof MappedByteBuffer) {
/* 518 */             MappedByteBuffer bb = (MappedByteBuffer)b;
/* 519 */             bb.force();
/*     */           } 
/*     */         } 
/*     */       } finally {
/* 524 */         this.growLock.unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     protected ByteBuffer makeNewBuffer(long offset) {
/*     */       try {
/* 532 */         assert (offset & this.chunkSizeModMask) == 0L;
/* 533 */         assert offset >= 0L;
/* 534 */         ByteBuffer ret = this.fileChannel.map(this.mapMode, offset, this.chunkSize);
/* 535 */         if (this.mapMode == FileChannel.MapMode.READ_ONLY)
/* 536 */           ret = ret.asReadOnlyBuffer(); 
/* 538 */         return ret;
/* 539 */       } catch (IOException e) {
/* 540 */         throw new IOError(e);
/*     */       } 
/*     */     }
/*     */     
/*     */     public void deleteFile() {
/* 547 */       this.file.delete();
/*     */     }
/*     */     
/*     */     public File getFile() {
/* 552 */       return this.file;
/*     */     }
/*     */     
/*     */     public void truncate(long size) {
/* 558 */       int maxSize = 1 + (int)(size >>> this.chunkShift);
/* 559 */       if (maxSize == this.chunks.length)
/*     */         return; 
/* 561 */       if (maxSize > this.chunks.length) {
/* 562 */         ensureAvailable(size);
/*     */         return;
/*     */       } 
/* 565 */       this.growLock.lock();
/*     */       try {
/* 567 */         if (maxSize >= this.chunks.length)
/*     */           return; 
/* 569 */         ByteBuffer[] old = this.chunks;
/* 570 */         this.chunks = Arrays.<ByteBuffer>copyOf(this.chunks, maxSize);
/*     */         int i;
/* 573 */         for (i = maxSize; i < old.length; i++) {
/* 574 */           unmap((MappedByteBuffer)old[i]);
/* 575 */           old[i] = null;
/*     */         } 
/* 578 */         if (Volume.ByteBufferVol.windowsWorkaround)
/* 579 */           for (i = 0; i < maxSize; i++) {
/* 580 */             unmap((MappedByteBuffer)old[i]);
/* 581 */             old[i] = null;
/*     */           }  
/*     */         try {
/* 586 */           this.fileChannel.truncate(1L * this.chunkSize * maxSize);
/* 587 */         } catch (IOException e) {
/* 588 */           throw new IOError(e);
/*     */         } 
/* 591 */         if (Volume.ByteBufferVol.windowsWorkaround)
/* 592 */           for (int pos = 0; pos < maxSize; pos++)
/* 593 */             this.chunks[pos] = makeNewBuffer(1L * this.chunkSize * pos);  
/*     */       } finally {
/* 598 */         this.growLock.unlock();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class MemoryVol extends ByteBufferVol {
/*     */     protected final boolean useDirectBuffer;
/*     */     
/*     */     public String toString() {
/* 609 */       return super.toString() + ",direct=" + this.useDirectBuffer;
/*     */     }
/*     */     
/*     */     public MemoryVol(boolean useDirectBuffer, long sizeLimit, int chunkShift) {
/* 613 */       super(false, sizeLimit, chunkShift);
/* 614 */       this.useDirectBuffer = useDirectBuffer;
/*     */     }
/*     */     
/*     */     protected ByteBuffer makeNewBuffer(long offset) {
/* 619 */       return this.useDirectBuffer ? ByteBuffer.allocateDirect(this.chunkSize) : ByteBuffer.allocate(this.chunkSize);
/*     */     }
/*     */     
/*     */     public void truncate(long size) {
/* 627 */       int maxSize = 1 + (int)(size >>> this.chunkShift);
/* 628 */       if (maxSize == this.chunks.length)
/*     */         return; 
/* 630 */       if (maxSize > this.chunks.length) {
/* 631 */         ensureAvailable(size);
/*     */         return;
/*     */       } 
/* 634 */       this.growLock.lock();
/*     */       try {
/* 636 */         if (maxSize >= this.chunks.length)
/*     */           return; 
/* 638 */         ByteBuffer[] old = this.chunks;
/* 639 */         this.chunks = Arrays.<ByteBuffer>copyOf(this.chunks, maxSize);
/* 642 */         for (int i = maxSize; i < old.length; i++) {
/* 643 */           if (old[i] instanceof MappedByteBuffer)
/* 644 */             unmap((MappedByteBuffer)old[i]); 
/* 645 */           old[i] = null;
/*     */         } 
/*     */       } finally {
/* 649 */         this.growLock.unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     public void close() {
/* 654 */       this.growLock.lock();
/*     */       try {
/* 656 */         for (ByteBuffer b : this.chunks) {
/* 657 */           if (b != null && b instanceof MappedByteBuffer)
/* 658 */             unmap((MappedByteBuffer)b); 
/*     */         } 
/* 661 */         this.chunks = null;
/*     */       } finally {
/* 663 */         this.growLock.unlock();
/*     */       } 
/*     */     }
/*     */     
/*     */     public void sync() {}
/*     */     
/*     */     public void deleteFile() {}
/*     */     
/*     */     public File getFile() {
/* 673 */       return null;
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class FileChannelVol extends Volume {
/*     */     protected final File file;
/*     */     
/*     */     protected final int chunkSize;
/*     */     
/*     */     protected RandomAccessFile raf;
/*     */     
/*     */     protected FileChannel channel;
/*     */     
/*     */     protected final boolean readOnly;
/*     */     
/*     */     protected final long sizeLimit;
/*     */     
/*     */     protected final boolean hasLimit;
/*     */     
/*     */     protected volatile long size;
/*     */     
/* 693 */     protected final Object growLock = new Object();
/*     */     
/*     */     public FileChannelVol(File file, boolean readOnly, long sizeLimit, int chunkShift, int sizeIncrement) {
/* 696 */       this.file = file;
/* 697 */       this.readOnly = readOnly;
/* 698 */       this.sizeLimit = sizeLimit;
/* 699 */       this.hasLimit = (sizeLimit > 0L);
/* 700 */       this.chunkSize = 1 << chunkShift;
/*     */       try {
/* 702 */         checkFolder(file, readOnly);
/* 703 */         if (readOnly && !file.exists()) {
/* 704 */           this.raf = null;
/* 705 */           this.channel = null;
/* 706 */           this.size = 0L;
/*     */         } else {
/* 708 */           this.raf = new RandomAccessFile(file, readOnly ? "r" : "rw");
/* 709 */           this.channel = this.raf.getChannel();
/* 710 */           this.size = this.channel.size();
/*     */         } 
/* 712 */       } catch (IOException e) {
/* 713 */         throw new IOError(e);
/*     */       } 
/*     */     }
/*     */     
/*     */     protected static void checkFolder(File file, boolean readOnly) throws IOException {
/* 718 */       File parent = file.getParentFile();
/* 719 */       if (parent == null)
/* 720 */         parent = file.getCanonicalFile().getParentFile(); 
/* 722 */       if (parent == null)
/* 723 */         throw new IOException("Parent folder could not be determined for: " + file); 
/* 725 */       if (!parent.exists() || !parent.isDirectory())
/* 726 */         throw new IOException("Parent folder does not exist: " + file); 
/* 727 */       if (!parent.canRead())
/* 728 */         throw new IOException("Parent folder is not readable: " + file); 
/* 729 */       if (!readOnly && !parent.canWrite())
/* 730 */         throw new IOException("Parent folder is not writable: " + file); 
/*     */     }
/*     */     
/*     */     public boolean tryAvailable(long offset) {
/* 735 */       if (this.hasLimit && offset > this.sizeLimit)
/* 735 */         return false; 
/* 736 */       if (offset % this.chunkSize != 0L)
/* 737 */         offset += this.chunkSize - offset % this.chunkSize; 
/* 739 */       if (offset > this.size)
/* 739 */         synchronized (this.growLock) {
/*     */           try {
/* 741 */             this.channel.truncate(offset);
/* 742 */             this.size = offset;
/* 743 */           } catch (IOException e) {
/* 744 */             throw new IOError(e);
/*     */           } 
/*     */         }  
/* 747 */       return true;
/*     */     }
/*     */     
/*     */     public void truncate(long size) {
/* 752 */       synchronized (this.growLock) {
/*     */         try {
/* 754 */           this.size = size;
/* 755 */           this.channel.truncate(size);
/* 756 */         } catch (IOException e) {
/* 757 */           throw new IOError(e);
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     protected void writeFully(long offset, ByteBuffer buf) throws IOException {
/* 764 */       int remaining = buf.limit() - buf.position();
/* 765 */       while (remaining > 0) {
/* 766 */         int write = this.channel.write(buf, offset);
/* 767 */         if (write < 0)
/* 767 */           throw new EOFException(); 
/* 768 */         remaining -= write;
/*     */       } 
/*     */     }
/*     */     
/*     */     public final void putSixLong(long offset, long value) {
/* 774 */       assert value >= 0L && value >>> 48L == 0L : "value does not fit";
/*     */       try {
/* 778 */         ByteBuffer buf = ByteBuffer.allocate(6);
/* 779 */         buf.put(0, (byte)(int)(0xFFL & value >> 40L));
/* 780 */         buf.put(1, (byte)(int)(0xFFL & value >> 32L));
/* 781 */         buf.put(2, (byte)(int)(0xFFL & value >> 24L));
/* 782 */         buf.put(3, (byte)(int)(0xFFL & value >> 16L));
/* 783 */         buf.put(4, (byte)(int)(0xFFL & value >> 8L));
/* 784 */         buf.put(5, (byte)(int)(0xFFL & value >> 0L));
/* 786 */         writeFully(offset, buf);
/* 787 */       } catch (IOException e) {
/* 788 */         throw new IOError(e);
/*     */       } 
/*     */     }
/*     */     
/*     */     public void putLong(long offset, long value) {
/*     */       try {
/* 795 */         ByteBuffer buf = ByteBuffer.allocate(8);
/* 796 */         buf.putLong(0, value);
/* 797 */         writeFully(offset, buf);
/* 798 */       } catch (IOException e) {
/* 799 */         throw new IOError(e);
/*     */       } 
/*     */     }
/*     */     
/*     */     public void putInt(long offset, int value) {
/*     */       try {
/* 806 */         ByteBuffer buf = ByteBuffer.allocate(4);
/* 807 */         buf.putInt(0, value);
/* 808 */         writeFully(offset, buf);
/* 809 */       } catch (IOException e) {
/* 810 */         throw new IOError(e);
/*     */       } 
/*     */     }
/*     */     
/*     */     public void putByte(long offset, byte value) {
/*     */       try {
/* 817 */         ByteBuffer buf = ByteBuffer.allocate(1);
/* 818 */         buf.put(0, value);
/* 819 */         writeFully(offset, buf);
/* 820 */       } catch (IOException e) {
/* 821 */         throw new IOError(e);
/*     */       } 
/*     */     }
/*     */     
/*     */     public void putData(long offset, byte[] src, int srcPos, int srcSize) {
/*     */       try {
/* 828 */         ByteBuffer buf = ByteBuffer.wrap(src, srcPos, srcSize);
/* 829 */         writeFully(offset, buf);
/* 830 */       } catch (IOException e) {
/* 831 */         throw new IOError(e);
/*     */       } 
/*     */     }
/*     */     
/*     */     public void putData(long offset, ByteBuffer buf) {
/*     */       try {
/* 838 */         writeFully(offset, buf);
/* 839 */       } catch (IOException e) {
/* 840 */         throw new IOError(e);
/*     */       } 
/*     */     }
/*     */     
/*     */     protected void readFully(long offset, ByteBuffer buf) throws IOException {
/* 845 */       int remaining = buf.limit() - buf.position();
/* 846 */       while (remaining > 0) {
/* 847 */         int read = this.channel.read(buf, offset);
/* 848 */         if (read < 0)
/* 848 */           throw new EOFException(); 
/* 849 */         remaining -= read;
/*     */       } 
/*     */     }
/*     */     
/*     */     public final long getSixLong(long offset) {
/*     */       try {
/* 856 */         ByteBuffer buf = ByteBuffer.allocate(6);
/* 857 */         readFully(offset, buf);
/* 858 */         return (buf.get(0) & 0xFF) << 40L | (buf.get(1) & 0xFF) << 32L | (buf.get(2) & 0xFF) << 24L | (buf.get(3) & 0xFF) << 16L | (buf.get(4) & 0xFF) << 8L | (buf.get(5) & 0xFF) << 0L;
/* 865 */       } catch (IOException e) {
/* 866 */         throw new IOError(e);
/*     */       } 
/*     */     }
/*     */     
/*     */     public long getLong(long offset) {
/*     */       try {
/* 874 */         ByteBuffer buf = ByteBuffer.allocate(8);
/* 875 */         readFully(offset, buf);
/* 876 */         return buf.getLong(0);
/* 877 */       } catch (IOException e) {
/* 878 */         throw new IOError(e);
/*     */       } 
/*     */     }
/*     */     
/*     */     public int getInt(long offset) {
/*     */       try {
/* 885 */         ByteBuffer buf = ByteBuffer.allocate(4);
/* 886 */         readFully(offset, buf);
/* 887 */         return buf.getInt(0);
/* 888 */       } catch (IOException e) {
/* 889 */         throw new IOError(e);
/*     */       } 
/*     */     }
/*     */     
/*     */     public byte getByte(long offset) {
/*     */       try {
/* 897 */         ByteBuffer buf = ByteBuffer.allocate(1);
/* 898 */         readFully(offset, buf);
/* 899 */         return buf.get(0);
/* 900 */       } catch (IOException e) {
/* 901 */         throw new IOError(e);
/*     */       } 
/*     */     }
/*     */     
/*     */     public DataInput2 getDataInput(long offset, int size) {
/*     */       try {
/* 908 */         ByteBuffer buf = ByteBuffer.allocate(size);
/* 909 */         readFully(offset, buf);
/* 910 */         return new DataInput2(buf, 0);
/* 911 */       } catch (IOException e) {
/* 912 */         throw new IOError(e);
/*     */       } 
/*     */     }
/*     */     
/*     */     public void close() {
/*     */       try {
/* 919 */         if (this.channel != null)
/* 920 */           this.channel.close(); 
/* 921 */         this.channel = null;
/* 922 */         if (this.raf != null)
/* 923 */           this.raf.close(); 
/* 924 */         this.raf = null;
/* 925 */       } catch (IOException e) {
/* 926 */         throw new IOError(e);
/*     */       } 
/*     */     }
/*     */     
/*     */     public void sync() {
/*     */       try {
/* 933 */         this.channel.force(true);
/* 934 */       } catch (IOException e) {
/* 935 */         throw new IOError(e);
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/*     */       try {
/* 942 */         return (this.channel == null || this.channel.size() == 0L);
/* 943 */       } catch (IOException e) {
/* 944 */         throw new IOError(e);
/*     */       } 
/*     */     }
/*     */     
/*     */     public void deleteFile() {
/* 950 */       this.file.delete();
/*     */     }
/*     */     
/*     */     public boolean isSliced() {
/* 955 */       return false;
/*     */     }
/*     */     
/*     */     public File getFile() {
/* 960 */       return this.file;
/*     */     }
/*     */   }
/*     */   
/*     */   public static void volumeTransfer(long size, Volume from, Volume to) {
/* 966 */     int bufSize = 65536;
/*     */     long offset;
/* 968 */     for (offset = 0L; offset < size; offset += bufSize) {
/* 969 */       int bb = (int)Math.min(bufSize, size - offset);
/* 970 */       DataInput2 input = (DataInput2)from.getDataInput(offset, bb);
/* 971 */       ByteBuffer buf = input.buf.duplicate();
/* 972 */       buf.position(input.pos);
/* 973 */       buf.limit(input.pos + bb);
/* 974 */       to.ensureAvailable(offset + bb);
/* 975 */       to.putData(offset, buf);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\mapdb\Volume.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */