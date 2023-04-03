/*     */ package org.mapdb;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOError;
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import java.util.SortedSet;
/*     */ import java.util.TreeSet;
/*     */ import java.util.concurrent.locks.Lock;
/*     */ 
/*     */ @Deprecated
/*     */ class StoreAppend extends Store {
/*     */   protected static final long HEADER = 1239900952130003033L;
/*     */   
/*     */   protected static final int FILE_SHIFT = 24;
/*     */   
/*     */   protected static final long FILE_MASK = 16777215L;
/*     */   
/*     */   protected static final int MAX_FILE_SIZE_SHIFT = 20;
/*     */   
/*     */   protected static final long SIZEP = 2L;
/*     */   
/*     */   protected static final long RECIDP = 3L;
/*     */   
/*     */   protected static final long END = -2L;
/*     */   
/*     */   protected static final long SKIP = -1L;
/*     */   
/*     */   protected final File file;
/*     */   
/*     */   protected final boolean useRandomAccessFile;
/*     */   
/*     */   protected final boolean readOnly;
/*     */   
/*     */   protected final boolean syncOnCommit;
/*     */   
/*     */   protected final boolean deleteFilesAfterClose;
/*     */   
/*     */   protected final boolean tx;
/*     */   
/*     */   protected volatile boolean closed = false;
/*     */   
/*     */   protected volatile boolean modified = false;
/*     */   
/*  73 */   protected final LongConcurrentHashMap<Volume> volumes = new LongConcurrentHashMap<Volume>();
/*     */   
/*     */   protected Volume currVolume;
/*     */   
/*     */   protected long currPos;
/*     */   
/*     */   protected long currFileNum;
/*     */   
/*     */   protected long maxRecid;
/*     */   
/*     */   protected long rollbackCurrPos;
/*     */   
/*     */   protected long rollbackCurrFileNum;
/*     */   
/*     */   protected long rollbackMaxRecid;
/*     */   
/*  92 */   protected Volume index = new Volume.MemoryVol(false, 0L, 20);
/*     */   
/*     */   protected final LongMap<Long> indexInTx;
/*     */   
/*     */   public StoreAppend(File file, boolean useRandomAccessFile, boolean readOnly, boolean transactionDisabled, boolean deleteFilesAfterClose, boolean syncOnCommitDisabled, boolean checksum, boolean compress, byte[] password, boolean disableLocks) {
/* 102 */     super(checksum, compress, password, disableLocks);
/* 103 */     this.file = file;
/* 104 */     this.useRandomAccessFile = useRandomAccessFile;
/* 105 */     this.readOnly = readOnly;
/* 106 */     this.deleteFilesAfterClose = deleteFilesAfterClose;
/* 107 */     this.syncOnCommit = !syncOnCommitDisabled;
/* 108 */     this.tx = !transactionDisabled;
/* 109 */     this.indexInTx = this.tx ? new LongConcurrentHashMap<Long>() : null;
/* 111 */     File parent = file.getAbsoluteFile().getParentFile();
/* 112 */     if (!parent.exists() || !parent.isDirectory())
/* 113 */       throw new IllegalArgumentException("Parent dir does not exist: " + file); 
/* 116 */     SortedSet<Fun.Tuple2<Long, File>> sortedFiles = new TreeSet<Fun.Tuple2<Long, File>>();
/* 117 */     String prefix = file.getName();
/* 118 */     for (File f : parent.listFiles()) {
/* 119 */       String name = f.getName();
/* 120 */       if (name.startsWith(prefix) && name.length() > prefix.length() + 1) {
/* 121 */         String number = name.substring(prefix.length() + 1, name.length());
/* 122 */         if (number.matches("^[0-9]+$"))
/* 123 */           sortedFiles.add(Fun.t2(Long.valueOf(number), f)); 
/*     */       } 
/*     */     } 
/* 127 */     if (sortedFiles.isEmpty()) {
/* 129 */       Volume zero = Volume.volumeForFile(getFileFromNum(0L), useRandomAccessFile, readOnly, 0L, 20, 0);
/* 130 */       zero.ensureAvailable(64L);
/* 131 */       zero.putLong(0L, 1239900952130003033L);
/* 132 */       long pos = 8L;
/*     */       long recid;
/* 134 */       for (recid = 1L; recid <= 7L; recid++) {
/* 135 */         pos += zero.putPackedLong(pos, recid + 3L);
/* 136 */         pos += zero.putPackedLong(pos, 2L);
/*     */       } 
/* 138 */       this.maxRecid = 7L;
/* 139 */       this.index.ensureAvailable(64L);
/* 141 */       this.volumes.put(0L, zero);
/* 143 */       if (this.tx) {
/* 144 */         this.rollbackCurrPos = pos;
/* 145 */         this.rollbackMaxRecid = this.maxRecid;
/* 146 */         this.rollbackCurrFileNum = 0L;
/* 147 */         zero.putUnsignedByte(pos, 1);
/* 148 */         pos++;
/*     */       } 
/* 151 */       this.currVolume = zero;
/* 152 */       this.currPos = pos;
/*     */     } else {
/* 155 */       for (Fun.Tuple2<Long, File> t : sortedFiles) {
/* 156 */         Long num = (Long)t.a;
/* 157 */         File f = (File)t.b;
/* 158 */         Volume vol = Volume.volumeForFile(f, useRandomAccessFile, readOnly, 0L, 20, 0);
/* 159 */         if (vol.isEmpty() || vol.getLong(0L) != 1239900952130003033L) {
/* 160 */           vol.sync();
/* 161 */           vol.close();
/* 162 */           Iterator<Volume> iterator = this.volumes.valuesIterator();
/* 163 */           while (iterator.hasNext()) {
/* 164 */             Volume next = iterator.next();
/* 165 */             next.sync();
/* 166 */             next.close();
/*     */           } 
/* 168 */           throw new IOError(new IOException("File corrupted: " + f));
/*     */         } 
/* 170 */         this.volumes.put(num.longValue(), vol);
/* 172 */         long pos = 8L;
/* 173 */         while (pos <= 16777215L) {
/* 174 */           long recid = vol.getPackedLong(pos);
/* 175 */           pos += packedLongSize(recid);
/* 176 */           recid -= 3L;
/* 177 */           this.maxRecid = Math.max(recid, this.maxRecid);
/* 180 */           if (recid == -2L) {
/* 182 */             this.currVolume = vol;
/* 183 */             this.currPos = pos;
/* 184 */             this.currFileNum = num.longValue();
/* 185 */             this.rollbackCurrFileNum = num.longValue();
/* 186 */             this.rollbackMaxRecid = this.maxRecid;
/* 187 */             this.rollbackCurrPos = pos - 1L;
/*     */             return;
/*     */           } 
/* 191 */           if (recid == -1L)
/*     */             continue; 
/* 194 */           if (recid <= 0L) {
/* 195 */             Iterator<Volume> iterator = this.volumes.valuesIterator();
/* 196 */             while (iterator.hasNext()) {
/* 197 */               Volume next = iterator.next();
/* 198 */               next.sync();
/* 199 */               next.close();
/*     */             } 
/* 201 */             throw new IOError(new IOException("File corrupted: " + f));
/*     */           } 
/* 204 */           this.index.ensureAvailable(recid * 8L + 8L);
/* 205 */           long indexVal = num.longValue() << 24L | pos;
/* 206 */           long size = vol.getPackedLong(pos);
/* 207 */           pos += packedLongSize(size);
/* 208 */           size -= 2L;
/* 210 */           if (size == 0L) {
/* 211 */             this.index.putLong(recid * 8L, 0L);
/*     */             continue;
/*     */           } 
/* 212 */           if (size > 0L) {
/* 213 */             pos += size;
/* 214 */             this.index.putLong(recid * 8L, indexVal);
/*     */             continue;
/*     */           } 
/* 216 */           this.index.putLong(recid * 8L, Long.MIN_VALUE);
/*     */         } 
/*     */       } 
/* 220 */       Iterator<Volume> vols = this.volumes.valuesIterator();
/* 221 */       while (vols.hasNext()) {
/* 222 */         Volume next = vols.next();
/* 223 */         next.sync();
/* 224 */         next.close();
/*     */       } 
/* 226 */       throw new IOError(new IOException("File not sealed, data possibly corrupted"));
/*     */     } 
/*     */   }
/*     */   
/*     */   public StoreAppend(File file) {
/* 231 */     this(file, false, false, false, false, false, false, false, (byte[])null, false);
/*     */   }
/*     */   
/*     */   protected File getFileFromNum(long fileNumber) {
/* 236 */     return new File(this.file.getPath() + "." + fileNumber);
/*     */   }
/*     */   
/*     */   protected void rollover() {
/* 240 */     if (this.currVolume.getLong(0L) != 1239900952130003033L)
/* 240 */       throw new AssertionError(); 
/* 241 */     if (this.currPos <= 16777215L || this.readOnly)
/*     */       return; 
/* 243 */     this.currVolume.sync();
/* 244 */     this.currFileNum++;
/* 245 */     this.currVolume = Volume.volumeForFile(getFileFromNum(this.currFileNum), this.useRandomAccessFile, this.readOnly, 0L, 20, 0);
/* 246 */     this.currVolume.ensureAvailable(8L);
/* 247 */     this.currVolume.putLong(0L, 1239900952130003033L);
/* 248 */     this.currPos = 8L;
/* 249 */     this.volumes.put(this.currFileNum, this.currVolume);
/*     */   }
/*     */   
/*     */   protected long indexVal(long recid) {
/* 255 */     if (this.tx) {
/* 256 */       Long val = this.indexInTx.get(recid);
/* 257 */       if (val != null)
/* 257 */         return val.longValue(); 
/*     */     } 
/* 259 */     return this.index.getLong(recid * 8L);
/*     */   }
/*     */   
/*     */   protected void setIndexVal(long recid, long indexVal) {
/* 263 */     if (this.tx) {
/* 263 */       this.indexInTx.put(recid, Long.valueOf(indexVal));
/*     */     } else {
/* 265 */       this.index.ensureAvailable(recid * 8L + 8L);
/* 266 */       this.index.putLong(recid * 8L, indexVal);
/*     */     } 
/*     */   }
/*     */   
/*     */   public long preallocate() {
/* 272 */     Lock lock = this.locks[(new Random()).nextInt(this.locks.length)].readLock();
/* 273 */     lock.lock();
/*     */     try {
/*     */       long recid;
/* 275 */       this.structuralLock.lock();
/*     */       try {
/* 278 */         recid = ++this.maxRecid;
/* 280 */         this.modified = true;
/*     */       } finally {
/* 282 */         this.structuralLock.unlock();
/*     */       } 
/* 285 */       assert recid > 0L;
/* 286 */       return recid;
/*     */     } finally {
/* 288 */       lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void preallocate(long[] recids) {
/* 296 */     Lock lock = this.locks[(new Random()).nextInt(this.locks.length)].readLock();
/* 297 */     lock.lock();
/*     */     try {
/* 299 */       this.structuralLock.lock();
/*     */       try {
/* 301 */         for (int i = 0; i < recids.length; i++) {
/* 302 */           recids[i] = ++this.maxRecid;
/* 303 */           if (!$assertionsDisabled && recids[i] <= 0L)
/* 303 */             throw new AssertionError(); 
/*     */         } 
/* 306 */         this.modified = true;
/*     */       } finally {
/* 308 */         this.structuralLock.unlock();
/*     */       } 
/*     */     } finally {
/* 311 */       lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public <A> long put(A value, Serializer<A> serializer) {
/* 318 */     assert value != null;
/* 319 */     DataOutput2 out = serialize(value, serializer);
/* 321 */     Lock lock = this.locks[(new Random()).nextInt(this.locks.length)].readLock();
/* 322 */     lock.lock();
/*     */     try {
/*     */       long oldPos, recid, indexVal;
/* 325 */       this.structuralLock.lock();
/*     */       try {
/* 328 */         rollover();
/* 329 */         this.currVolume.ensureAvailable(this.currPos + 6L + 4L + out.pos);
/* 330 */         recid = ++this.maxRecid;
/* 333 */         this.currPos += this.currVolume.putPackedLong(this.currPos, recid + 3L);
/* 334 */         indexVal = this.currFileNum << 24L | this.currPos;
/* 336 */         this.currPos += this.currVolume.putPackedLong(this.currPos, out.pos + 2L);
/* 338 */         oldPos = this.currPos;
/* 339 */         this.currPos += out.pos;
/* 341 */         this.modified = true;
/*     */       } finally {
/* 343 */         this.structuralLock.unlock();
/*     */       } 
/* 347 */       this.currVolume.putData(oldPos, out.buf, 0, out.pos);
/* 349 */       this.recycledDataOuts.offer(out);
/* 350 */       setIndexVal(recid, indexVal);
/* 352 */       assert recid > 0L;
/* 353 */       return recid;
/*     */     } finally {
/* 355 */       lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public <A> A get(long recid, Serializer<A> serializer) {
/* 361 */     assert recid > 0L;
/* 362 */     Lock lock = this.locks[Store.lockPos(recid)].readLock();
/* 363 */     lock.lock();
/*     */     try {
/* 365 */       return (A)getNoLock(recid, (Serializer)serializer);
/* 366 */     } catch (IOException e) {
/* 367 */       throw new IOError(e);
/*     */     } finally {
/* 369 */       lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected <A> A getNoLock(long recid, Serializer<A> serializer) throws IOException {
/* 374 */     long indexVal = indexVal(recid);
/* 376 */     if (indexVal == 0L)
/* 376 */       return null; 
/* 377 */     Volume vol = this.volumes.get(indexVal >>> 24L);
/* 378 */     long fileOffset = indexVal & 0xFFFFFFL;
/* 379 */     long size = vol.getPackedLong(fileOffset);
/* 380 */     fileOffset += packedLongSize(size);
/* 381 */     size -= 2L;
/* 382 */     if (size < 0L)
/* 382 */       return null; 
/* 383 */     if (size == 0L)
/* 383 */       return serializer.deserialize(new DataInput2(new byte[0]), 0); 
/* 384 */     DataInput2 in = (DataInput2)vol.getDataInput(fileOffset, (int)size);
/* 386 */     return deserialize(serializer, (int)size, in);
/*     */   }
/*     */   
/*     */   public <A> void update(long recid, A value, Serializer<A> serializer) {
/* 392 */     assert value != null;
/* 393 */     assert recid > 0L;
/* 394 */     DataOutput2 out = serialize(value, serializer);
/* 396 */     Lock lock = this.locks[Store.lockPos(recid)].writeLock();
/* 397 */     lock.lock();
/*     */     try {
/* 399 */       updateNoLock(recid, out);
/*     */     } finally {
/* 401 */       lock.unlock();
/*     */     } 
/* 403 */     this.recycledDataOuts.offer(out);
/*     */   }
/*     */   
/*     */   protected void updateNoLock(long recid, DataOutput2 out) {
/*     */     long indexVal, oldPos;
/* 409 */     this.structuralLock.lock();
/*     */     try {
/* 411 */       rollover();
/* 412 */       this.currVolume.ensureAvailable(this.currPos + 6L + 4L + out.pos);
/* 414 */       this.currPos += this.currVolume.putPackedLong(this.currPos, recid + 3L);
/* 415 */       indexVal = this.currFileNum << 24L | this.currPos;
/* 417 */       this.currPos += this.currVolume.putPackedLong(this.currPos, out.pos + 2L);
/* 418 */       oldPos = this.currPos;
/* 419 */       this.currPos += out.pos;
/* 420 */       this.modified = true;
/*     */     } finally {
/* 422 */       this.structuralLock.unlock();
/*     */     } 
/* 425 */     this.currVolume.putData(oldPos, out.buf, 0, out.pos);
/* 427 */     setIndexVal(recid, indexVal);
/*     */   }
/*     */   
/*     */   public <A> boolean compareAndSwap(long recid, A expectedOldValue, A newValue, Serializer<A> serializer) {
/*     */     boolean ret;
/* 433 */     assert expectedOldValue != null && newValue != null;
/* 434 */     assert recid > 0L;
/* 435 */     DataOutput2 out = serialize(newValue, serializer);
/* 436 */     Lock lock = this.locks[Store.lockPos(recid)].writeLock();
/* 437 */     lock.lock();
/*     */     try {
/* 440 */       Object old = getNoLock(recid, serializer);
/* 441 */       if (expectedOldValue.equals(old)) {
/* 442 */         updateNoLock(recid, out);
/* 443 */         ret = true;
/*     */       } else {
/* 445 */         ret = false;
/*     */       } 
/* 447 */     } catch (IOException e) {
/* 448 */       throw new IOError(e);
/*     */     } finally {
/* 450 */       lock.unlock();
/*     */     } 
/* 452 */     this.recycledDataOuts.offer(out);
/* 453 */     return ret;
/*     */   }
/*     */   
/*     */   public <A> void delete(long recid, Serializer<A> serializer) {
/* 458 */     assert recid > 0L;
/* 459 */     Lock lock = this.locks[Store.lockPos(recid)].writeLock();
/* 460 */     lock.lock();
/*     */     try {
/* 462 */       this.structuralLock.lock();
/*     */       try {
/* 464 */         rollover();
/* 465 */         this.currVolume.ensureAvailable(this.currPos + 6L + 0L);
/* 466 */         this.currPos += this.currVolume.putPackedLong(this.currPos, recid + 2L);
/* 467 */         setIndexVal(recid, this.currFileNum << 24L | this.currPos);
/* 469 */         this.currPos += this.currVolume.putPackedLong(this.currPos, 1L);
/* 470 */         this.modified = true;
/*     */       } finally {
/* 472 */         this.structuralLock.unlock();
/*     */       } 
/*     */     } finally {
/* 475 */       lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void close() {
/* 481 */     if (this.closed)
/*     */       return; 
/* 483 */     for (Runnable closeListener : this.closeListeners)
/* 484 */       closeListener.run(); 
/* 486 */     if (this.serializerPojo != null && this.serializerPojo.hasUnsavedChanges())
/* 487 */       this.serializerPojo.save(this); 
/* 490 */     Iterator<Volume> iter = this.volumes.valuesIterator();
/* 491 */     if (!this.readOnly && this.modified) {
/* 492 */       rollover();
/* 493 */       this.currVolume.putUnsignedByte(this.currPos, 1);
/*     */     } 
/* 495 */     while (iter.hasNext()) {
/* 496 */       Volume v = iter.next();
/* 497 */       v.sync();
/* 498 */       v.close();
/* 499 */       if (this.deleteFilesAfterClose)
/* 499 */         v.deleteFile(); 
/*     */     } 
/* 501 */     this.volumes.clear();
/* 502 */     this.closed = true;
/*     */   }
/*     */   
/*     */   public boolean isClosed() {
/* 507 */     return this.closed;
/*     */   }
/*     */   
/*     */   public void commit() {
/* 513 */     if (!this.tx) {
/* 514 */       this.currVolume.sync();
/*     */       return;
/*     */     } 
/* 518 */     lockAllWrite();
/*     */     try {
/* 521 */       LongMap.LongMapIterator<Long> iter = this.indexInTx.longMapIterator();
/* 522 */       while (iter.moveToNext()) {
/* 523 */         this.index.ensureAvailable(iter.key() * 8L + 8L);
/* 524 */         this.index.putLong(iter.key() * 8L, ((Long)iter.value()).longValue());
/*     */       } 
/* 526 */       Volume rollbackCurrVolume = this.volumes.get(this.rollbackCurrFileNum);
/* 527 */       rollbackCurrVolume.putUnsignedByte(this.rollbackCurrPos, 2);
/* 528 */       if (this.syncOnCommit)
/* 528 */         rollbackCurrVolume.sync(); 
/* 530 */       this.indexInTx.clear();
/* 532 */       rollover();
/* 533 */       this.rollbackCurrPos = this.currPos;
/* 534 */       this.rollbackMaxRecid = this.maxRecid;
/* 535 */       this.rollbackCurrFileNum = this.currFileNum;
/* 537 */       this.currVolume.putUnsignedByte(this.rollbackCurrPos, 1);
/* 538 */       this.currPos++;
/* 540 */       if (this.serializerPojo != null && this.serializerPojo.hasUnsavedChanges())
/* 541 */         this.serializerPojo.save(this); 
/*     */     } finally {
/* 545 */       unlockAllWrite();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void rollback() throws UnsupportedOperationException {
/* 553 */     if (!this.tx)
/* 553 */       throw new UnsupportedOperationException("Transactions are disabled"); 
/* 555 */     lockAllWrite();
/*     */     try {
/* 558 */       this.indexInTx.clear();
/* 559 */       this.currVolume = this.volumes.get(this.rollbackCurrFileNum);
/* 560 */       this.currPos = this.rollbackCurrPos;
/* 561 */       this.maxRecid = this.rollbackMaxRecid;
/* 562 */       this.currFileNum = this.rollbackCurrFileNum;
/*     */     } finally {
/* 566 */       unlockAllWrite();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean canRollback() {
/* 573 */     return this.tx;
/*     */   }
/*     */   
/*     */   public boolean isReadOnly() {
/* 579 */     return this.readOnly;
/*     */   }
/*     */   
/*     */   public void clearCache() {}
/*     */   
/*     */   public void compact() {
/* 589 */     if (this.readOnly)
/* 589 */       throw new IllegalAccessError("readonly"); 
/* 590 */     lockAllWrite();
/*     */     try {
/* 593 */       if (!this.indexInTx.isEmpty())
/* 593 */         throw new IllegalAccessError("uncommited changes"); 
/* 595 */       LongHashMap<Boolean> ff = new LongHashMap<Boolean>();
/* 596 */       for (long recid = 0L; recid <= this.maxRecid; recid++) {
/* 597 */         long indexVal = this.index.getLong(recid * 8L);
/* 598 */         if (indexVal != 0L) {
/* 599 */           long fileNum = indexVal >>> 24L;
/* 600 */           ff.put(fileNum, Boolean.valueOf(true));
/*     */         } 
/*     */       } 
/* 604 */       LongMap.LongMapIterator<Volume> iter = this.volumes.longMapIterator();
/* 605 */       while (iter.moveToNext()) {
/* 606 */         long fileNum = iter.key();
/* 607 */         if (fileNum == this.currFileNum || ff.get(fileNum) != null)
/*     */           continue; 
/* 608 */         Volume v = iter.value();
/* 609 */         v.sync();
/* 610 */         v.close();
/* 611 */         v.deleteFile();
/* 612 */         iter.remove();
/*     */       } 
/*     */     } finally {
/* 615 */       unlockAllWrite();
/*     */     } 
/*     */   }
/*     */   
/*     */   public long getMaxRecid() {
/* 622 */     return this.maxRecid;
/*     */   }
/*     */   
/*     */   public ByteBuffer getRaw(long recid) {
/* 628 */     byte[] bb = get(recid, (Serializer)Serializer.BYTE_ARRAY_NOSIZE);
/* 629 */     if (bb == null)
/* 629 */       return null; 
/* 630 */     return ByteBuffer.wrap(bb);
/*     */   }
/*     */   
/*     */   public Iterator<Long> getFreeRecids() {
/* 635 */     return Fun.EMPTY_ITERATOR;
/*     */   }
/*     */   
/*     */   public void updateRaw(long recid, ByteBuffer data) {
/* 640 */     rollover();
/* 641 */     byte[] b = null;
/* 642 */     if (data != null) {
/* 643 */       data = data.duplicate();
/* 644 */       b = new byte[data.remaining()];
/* 645 */       data.get(b);
/*     */     } 
/* 648 */     update(recid, b, (Serializer)Serializer.BYTE_ARRAY_NOSIZE);
/* 649 */     this.modified = true;
/*     */   }
/*     */   
/*     */   public long getSizeLimit() {
/* 654 */     return 0L;
/*     */   }
/*     */   
/*     */   public long getCurrSize() {
/* 659 */     return this.currFileNum * 16777215L;
/*     */   }
/*     */   
/*     */   public long getFreeSize() {
/* 664 */     return 0L;
/*     */   }
/*     */   
/*     */   public String calculateStatistics() {
/* 669 */     return null;
/*     */   }
/*     */   
/*     */   protected static int packedLongSize(long value) {
/* 675 */     int ret = 1;
/* 676 */     while ((value & 0xFFFFFFFFFFFFFF80L) != 0L) {
/* 677 */       ret++;
/* 678 */       value >>>= 7L;
/*     */     } 
/* 680 */     return ret;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\mapdb\StoreAppend.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */