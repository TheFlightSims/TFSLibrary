/*      */ package org.mapdb;
/*      */ 
/*      */ import java.io.File;
/*      */ import java.io.IOError;
/*      */ import java.io.IOException;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.util.Arrays;
/*      */ import java.util.Iterator;
/*      */ import java.util.concurrent.locks.Lock;
/*      */ 
/*      */ public class StoreDirect extends Store {
/*      */   protected static final long MASK_OFFSET = 281474976710640L;
/*      */   
/*      */   protected static final long MASK_LINKED = 8L;
/*      */   
/*      */   protected static final long MASK_DISCARD = 4L;
/*      */   
/*      */   protected static final long MASK_ARCHIVE = 2L;
/*      */   
/*      */   protected static final int HEADER = 234243482;
/*      */   
/*      */   protected static final short STORE_VERSION = 10000;
/*      */   
/*      */   protected static final int MAX_REC_SIZE = 65535;
/*      */   
/*      */   protected static final int PHYS_FREE_SLOTS_COUNT = 4096;
/*      */   
/*      */   protected static final int IO_INDEX_SIZE = 8;
/*      */   
/*      */   protected static final int IO_PHYS_SIZE = 16;
/*      */   
/*      */   protected static final int IO_FREE_SIZE = 24;
/*      */   
/*      */   protected static final int IO_INDEX_SUM = 32;
/*      */   
/*      */   protected static final int IO_FREE_RECID = 120;
/*      */   
/*      */   protected static final int IO_USER_START = 32896;
/*      */   
/*      */   public static final String DATA_FILE_EXT = ".p";
/*      */   
/*      */   protected static final int LONG_STACK_PREF_COUNT = 204;
/*      */   
/*      */   protected static final long LONG_STACK_PREF_SIZE = 1232L;
/*      */   
/*      */   protected static final int LONG_STACK_PREF_COUNT_ALTER = 212;
/*      */   
/*      */   protected static final long LONG_STACK_PREF_SIZE_ALTER = 1280L;
/*      */   
/*      */   protected Volume index;
/*      */   
/*      */   protected Volume phys;
/*      */   
/*      */   protected long physSize;
/*      */   
/*      */   protected long indexSize;
/*      */   
/*      */   protected long freeSize;
/*      */   
/*      */   protected final boolean deleteFilesAfterClose;
/*      */   
/*      */   protected final boolean readOnly;
/*      */   
/*      */   protected final boolean syncOnCommitDisabled;
/*      */   
/*      */   protected final boolean spaceReclaimReuse;
/*      */   
/*      */   protected final boolean spaceReclaimSplit;
/*      */   
/*      */   protected final boolean spaceReclaimTrack;
/*      */   
/*      */   protected final long sizeLimit;
/*      */   
/*  182 */   protected long maxUsedIoList = 0L;
/*      */   
/*      */   public StoreDirect(Volume.Factory volFac, boolean readOnly, boolean deleteFilesAfterClose, int spaceReclaimMode, boolean syncOnCommitDisabled, long sizeLimit, boolean checksum, boolean compress, byte[] password, boolean disableLocks, int sizeIncrement) {
/*  189 */     super(checksum, compress, password, disableLocks);
/*  190 */     this.readOnly = readOnly;
/*  191 */     this.deleteFilesAfterClose = deleteFilesAfterClose;
/*  192 */     this.syncOnCommitDisabled = syncOnCommitDisabled;
/*  193 */     this.sizeLimit = sizeLimit;
/*  195 */     this.spaceReclaimSplit = (spaceReclaimMode > 4);
/*  196 */     this.spaceReclaimReuse = (spaceReclaimMode > 2);
/*  197 */     this.spaceReclaimTrack = (spaceReclaimMode > 0);
/*  199 */     boolean allGood = false;
/*      */     try {
/*  202 */       this.index = volFac.createIndexVolume();
/*  203 */       this.phys = volFac.createPhysVolume();
/*  204 */       if (this.index.isEmpty()) {
/*  205 */         createStructure();
/*      */       } else {
/*  207 */         checkHeaders();
/*  208 */         this.indexSize = this.index.getLong(8L);
/*  209 */         this.physSize = this.index.getLong(16L);
/*  210 */         this.freeSize = this.index.getLong(24L);
/*  212 */         this.maxUsedIoList = 32888L;
/*  213 */         while (this.index.getLong(this.maxUsedIoList) != 0L && this.maxUsedIoList > 120L)
/*  214 */           this.maxUsedIoList -= 8L; 
/*      */       } 
/*  216 */       allGood = true;
/*      */     } finally {
/*  218 */       if (!allGood) {
/*  220 */         if (this.index != null) {
/*  221 */           this.index.sync();
/*  222 */           this.index.close();
/*  223 */           this.index = null;
/*      */         } 
/*  225 */         if (this.phys != null) {
/*  226 */           this.phys.sync();
/*  227 */           this.phys.close();
/*  228 */           this.phys = null;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public StoreDirect(Volume.Factory volFac) {
/*  236 */     this(volFac, false, false, 5, false, 0L, false, false, (byte[])null, false, 0);
/*      */   }
/*      */   
/*      */   protected void checkHeaders() {
/*  242 */     if (this.index.getInt(0L) != 234243482 || this.phys.getInt(0L) != 234243482)
/*  243 */       throw new IOError(new IOException("storage has invalid header")); 
/*  245 */     if (this.index.getUnsignedShort(4L) > 10000 || this.phys.getUnsignedShort(4L) > 10000)
/*  246 */       throw new IOError(new IOException("New store format version, please use newer MapDB version")); 
/*  248 */     int masks = this.index.getUnsignedShort(6L);
/*  249 */     if (masks != this.phys.getUnsignedShort(6L))
/*  250 */       throw new IllegalArgumentException("Index and Phys file have different feature masks"); 
/*  252 */     if (masks != expectedMasks())
/*  253 */       throw new IllegalArgumentException("File created with different features. Please check compression, checksum or encryption"); 
/*  256 */     long checksum = this.index.getLong(32L);
/*  257 */     if (checksum != indexHeaderChecksum())
/*  258 */       throw new IOError(new IOException("Wrong index checksum, store was not closed properly and could be corrupted.")); 
/*      */   }
/*      */   
/*      */   protected void createStructure() {
/*  262 */     this.indexSize = 32960L;
/*  263 */     assert this.indexSize > 32896L;
/*  264 */     this.index.ensureAvailable(this.indexSize);
/*  265 */     for (int i = 0; i < this.indexSize; ) {
/*  265 */       this.index.putLong(i, 0L);
/*  265 */       i += 8;
/*      */     } 
/*  266 */     this.index.putInt(0L, 234243482);
/*  267 */     this.index.putUnsignedShort(4L, 10000);
/*  268 */     this.index.putUnsignedShort(6L, expectedMasks());
/*  269 */     this.index.putLong(8L, this.indexSize);
/*  270 */     this.physSize = 16L;
/*  271 */     this.index.putLong(16L, this.physSize);
/*  272 */     this.phys.ensureAvailable(this.physSize);
/*  273 */     this.phys.putInt(0L, 234243482);
/*  274 */     this.phys.putUnsignedShort(4L, 10000);
/*  275 */     this.phys.putUnsignedShort(6L, expectedMasks());
/*  276 */     this.freeSize = 0L;
/*  277 */     this.index.putLong(24L, this.freeSize);
/*  278 */     this.index.putLong(32L, indexHeaderChecksum());
/*      */   }
/*      */   
/*      */   protected long indexHeaderChecksum() {
/*  282 */     long ret = 0L;
/*  283 */     for (long offset = 0L; offset < 32896L; offset += 8L) {
/*  284 */       if (offset != 32L) {
/*  285 */         long indexVal = this.index.getLong(offset);
/*  286 */         ret |= indexVal | LongHashMap.longHash(indexVal | offset);
/*      */       } 
/*      */     } 
/*  288 */     return ret;
/*      */   }
/*      */   
/*      */   public long preallocate() {
/*  293 */     this.newRecidLock.readLock().lock();
/*      */     try {
/*      */       long ioRecid;
/*  295 */       this.structuralLock.lock();
/*      */       try {
/*  298 */         ioRecid = freeIoRecidTake(true);
/*      */       } finally {
/*  300 */         this.structuralLock.unlock();
/*      */       } 
/*  303 */       Lock lock = this.locks[Store.lockPos(ioRecid)].writeLock();
/*  304 */       lock.lock();
/*      */       try {
/*  306 */         this.index.putLong(ioRecid, 4L);
/*      */       } finally {
/*  308 */         lock.unlock();
/*      */       } 
/*  310 */       long recid = (ioRecid - 32896L) / 8L;
/*  311 */       assert recid > 0L;
/*  314 */       return recid;
/*      */     } finally {
/*  316 */       this.newRecidLock.readLock().unlock();
/*      */     } 
/*      */   }
/*      */   
/*      */   public void preallocate(long[] recids) {
/*  322 */     this.newRecidLock.readLock().lock();
/*      */     try {
/*  324 */       this.structuralLock.lock();
/*      */       try {
/*  326 */         for (int j = 0; j < recids.length; j++)
/*  327 */           recids[j] = freeIoRecidTake(true); 
/*      */       } finally {
/*  329 */         this.structuralLock.unlock();
/*      */       } 
/*  331 */       for (int i = 0; i < recids.length; i++) {
/*  332 */         long ioRecid = recids[i];
/*  333 */         Lock lock = this.locks[Store.lockPos(ioRecid)].writeLock();
/*  334 */         lock.lock();
/*      */         try {
/*  336 */           this.index.putLong(ioRecid, 4L);
/*      */         } finally {
/*  338 */           lock.unlock();
/*      */         } 
/*  340 */         recids[i] = (ioRecid - 32896L) / 8L;
/*  341 */         assert recids[i] > 0L;
/*      */       } 
/*      */     } finally {
/*  346 */       this.newRecidLock.readLock().unlock();
/*      */     } 
/*      */   }
/*      */   
/*      */   public <A> long put(A value, Serializer<A> serializer) {
/*      */     long ioRecid;
/*  353 */     assert value != null;
/*  354 */     DataOutput2 out = serialize(value, serializer);
/*  356 */     this.newRecidLock.readLock().lock();
/*      */     try {
/*      */       long[] indexVals;
/*  359 */       this.structuralLock.lock();
/*      */       try {
/*  363 */         ioRecid = freeIoRecidTake(true);
/*  364 */         indexVals = physAllocate(out.pos, true, false);
/*      */       } finally {
/*  366 */         this.structuralLock.unlock();
/*      */       } 
/*  368 */       Lock lock = this.locks[Store.lockPos(ioRecid)].writeLock();
/*  369 */       lock.lock();
/*      */       try {
/*  371 */         put2(out, ioRecid, indexVals);
/*      */       } finally {
/*  373 */         lock.unlock();
/*      */       } 
/*      */     } finally {
/*  376 */       this.newRecidLock.readLock().unlock();
/*      */     } 
/*  379 */     long recid = (ioRecid - 32896L) / 8L;
/*  380 */     assert recid > 0L;
/*  383 */     this.recycledDataOuts.offer(out);
/*  384 */     return recid;
/*      */   }
/*      */   
/*      */   protected void put2(DataOutput2 out, long ioRecid, long[] indexVals) {
/*  388 */     assert this.locks[Store.lockPos(ioRecid)].writeLock().isHeldByCurrentThread();
/*  389 */     this.index.putLong(ioRecid, indexVals[0] | 0x2L);
/*  391 */     if (indexVals.length == 1 || indexVals[1] == 0L) {
/*  394 */       this.phys.putData(indexVals[0] & 0xFFFFFFFFFFF0L, out.buf, 0, out.pos);
/*      */     } else {
/*  397 */       int outPos = 0;
/*  399 */       for (int i = 0; i < indexVals.length; i++) {
/*  400 */         int c = (i == indexVals.length - 1) ? 0 : 8;
/*  401 */         long indexVal = indexVals[i];
/*  402 */         boolean isLast = ((indexVal & 0x8L) == 0L);
/*  403 */         assert isLast == ((i == indexVals.length - 1));
/*  404 */         int size = (int)(indexVal >>> 48L);
/*  405 */         long offset = indexVal & 0xFFFFFFFFFFF0L;
/*  408 */         this.phys.putData(offset + c, out.buf, outPos, size - c);
/*  409 */         outPos += size - c;
/*  411 */         if (c > 0)
/*  413 */           this.phys.putLong(offset, indexVals[i + 1]); 
/*      */       } 
/*  416 */       if (outPos != out.pos)
/*  416 */         throw new AssertionError(); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public <A> A get(long recid, Serializer<A> serializer) {
/*  423 */     assert recid > 0L;
/*  424 */     long ioRecid = 32896L + recid * 8L;
/*  425 */     Lock lock = this.locks[Store.lockPos(ioRecid)].readLock();
/*  426 */     lock.lock();
/*      */     try {
/*  428 */       return (A)get2(ioRecid, (Serializer)serializer);
/*  429 */     } catch (IOException e) {
/*  430 */       throw new IOError(e);
/*      */     } finally {
/*  432 */       lock.unlock();
/*      */     } 
/*      */   }
/*      */   
/*      */   protected <A> A get2(long ioRecid, Serializer<A> serializer) throws IOException {
/*      */     DataInput2 di;
/*  437 */     assert this.locks[Store.lockPos(ioRecid)].getWriteHoldCount() == 0 || this.locks[Store.lockPos(ioRecid)].writeLock().isHeldByCurrentThread();
/*  440 */     long indexVal = this.index.getLong(ioRecid);
/*  441 */     if (indexVal == 4L)
/*  441 */       return null; 
/*  443 */     int size = (int)(indexVal >>> 48L);
/*  445 */     long offset = indexVal & 0xFFFFFFFFFFF0L;
/*  446 */     if ((indexVal & 0x8L) == 0L) {
/*  448 */       di = (DataInput2)this.phys.getDataInput(offset, size);
/*      */     } else {
/*  452 */       int pos = 0;
/*  453 */       int c = 8;
/*  455 */       byte[] buf = new byte[64];
/*      */       while (true) {
/*  458 */         DataInput2 in = (DataInput2)this.phys.getDataInput(offset + c, size - c);
/*  460 */         if (buf.length < pos + size - c)
/*  461 */           buf = Arrays.copyOf(buf, Math.max(pos + size - c, buf.length * 2)); 
/*  462 */         in.readFully(buf, pos, size - c);
/*  463 */         pos += size - c;
/*  464 */         if (c == 0)
/*      */           break; 
/*  466 */         long next = this.phys.getLong(offset);
/*  467 */         offset = next & 0xFFFFFFFFFFF0L;
/*  468 */         size = (int)(next >>> 48L);
/*  470 */         c = ((next & 0x8L) == 0L) ? 0 : 8;
/*      */       } 
/*  472 */       di = new DataInput2(buf);
/*  473 */       size = pos;
/*      */     } 
/*  475 */     return deserialize(serializer, size, di);
/*      */   }
/*      */   
/*      */   public <A> void update(long recid, A value, Serializer<A> serializer) {
/*  482 */     assert value != null;
/*  483 */     assert recid > 0L;
/*  484 */     DataOutput2 out = serialize(value, serializer);
/*  486 */     long ioRecid = 32896L + recid * 8L;
/*  488 */     Lock lock = this.locks[Store.lockPos(ioRecid)].writeLock();
/*  489 */     lock.lock();
/*      */     try {
/*  491 */       update2(out, ioRecid);
/*      */     } finally {
/*  493 */       lock.unlock();
/*      */     } 
/*  498 */     this.recycledDataOuts.offer(out);
/*      */   }
/*      */   
/*      */   protected void update2(DataOutput2 out, long ioRecid) {
/*  502 */     long indexVal = this.index.getLong(ioRecid);
/*  503 */     int size = (int)(indexVal >>> 48L);
/*  504 */     boolean linked = ((indexVal & 0x8L) != 0L);
/*  505 */     assert this.locks[Store.lockPos(ioRecid)].writeLock().isHeldByCurrentThread();
/*  507 */     if (!linked && out.pos > 0 && size > 0 && size2ListIoRecid(size) == size2ListIoRecid(out.pos)) {
/*  509 */       long offset = indexVal & 0xFFFFFFFFFFF0L;
/*  512 */       this.index.putLong(ioRecid, out.pos << 48L | offset | 0x2L);
/*  514 */       this.phys.putData(offset, out.buf, 0, out.pos);
/*      */     } else {
/*  517 */       long[] indexVals = this.spaceReclaimTrack ? getLinkedRecordsIndexVals(indexVal) : null;
/*  519 */       this.structuralLock.lock();
/*      */       try {
/*  522 */         if (this.spaceReclaimTrack) {
/*  524 */           if (size > 0)
/*  525 */             freePhysPut(indexVal, false); 
/*  528 */           if (indexVals != null)
/*  529 */             for (int i = 0; i < indexVals.length && indexVals[i] != 0L; i++)
/*  530 */               freePhysPut(indexVals[i], false);  
/*      */         } 
/*  535 */         indexVals = physAllocate(out.pos, true, false);
/*      */       } finally {
/*  537 */         this.structuralLock.unlock();
/*      */       } 
/*  540 */       put2(out, ioRecid, indexVals);
/*      */     } 
/*  542 */     assert this.locks[Store.lockPos(ioRecid)].writeLock().isHeldByCurrentThread();
/*      */   }
/*      */   
/*      */   public <A> boolean compareAndSwap(long recid, A expectedOldValue, A newValue, Serializer<A> serializer) {
/*      */     DataOutput2 out;
/*  548 */     assert expectedOldValue != null && newValue != null;
/*  549 */     assert recid > 0L;
/*  550 */     long ioRecid = 32896L + recid * 8L;
/*  551 */     Lock lock = this.locks[Store.lockPos(ioRecid)].writeLock();
/*  552 */     lock.lock();
/*      */     try {
/*  560 */       A oldVal = get2(ioRecid, serializer);
/*  565 */       if ((oldVal == null && expectedOldValue != null) || (oldVal != null && !oldVal.equals(expectedOldValue)))
/*  566 */         return false; 
/*  571 */       out = serialize(newValue, serializer);
/*  573 */       update2(out, ioRecid);
/*  575 */     } catch (IOException e) {
/*  576 */       throw new IOError(e);
/*      */     } finally {
/*  578 */       lock.unlock();
/*      */     } 
/*  580 */     this.recycledDataOuts.offer(out);
/*  581 */     return true;
/*      */   }
/*      */   
/*      */   public <A> void delete(long recid, Serializer<A> serializer) {
/*  586 */     assert recid > 0L;
/*  587 */     long ioRecid = 32896L + recid * 8L;
/*  588 */     Lock lock = this.locks[Store.lockPos(ioRecid)].writeLock();
/*  589 */     lock.lock();
/*      */     try {
/*  592 */       long indexVal = this.index.getLong(ioRecid);
/*  593 */       this.index.putLong(ioRecid, 2L);
/*  595 */       if (!this.spaceReclaimTrack)
/*      */         return; 
/*  597 */       long[] linkedRecords = getLinkedRecordsIndexVals(indexVal);
/*  600 */       this.structuralLock.lock();
/*      */       try {
/*  603 */         freeIoRecidPut(ioRecid);
/*  605 */         if (indexVal >>> 48L > 0L)
/*  606 */           freePhysPut(indexVal, false); 
/*  609 */         if (linkedRecords != null)
/*  610 */           for (int i = 0; i < linkedRecords.length && linkedRecords[i] != 0L; i++)
/*  611 */             freePhysPut(linkedRecords[i], false);  
/*      */       } finally {
/*  615 */         this.structuralLock.unlock();
/*      */       } 
/*      */     } finally {
/*  619 */       lock.unlock();
/*      */     } 
/*      */   }
/*      */   
/*      */   protected long[] getLinkedRecordsIndexVals(long indexVal) {
/*  624 */     long[] linkedRecords = null;
/*  626 */     int linkedPos = 0;
/*  627 */     if ((indexVal & 0x8L) != 0L) {
/*  629 */       linkedRecords = new long[2];
/*  632 */       long linkedVal = this.phys.getLong(indexVal & 0xFFFFFFFFFFF0L);
/*      */       while (true) {
/*  634 */         if (linkedPos == linkedRecords.length)
/*  635 */           linkedRecords = Arrays.copyOf(linkedRecords, linkedRecords.length * 2); 
/*  637 */         linkedRecords[linkedPos] = linkedVal;
/*  639 */         if ((linkedVal & 0x8L) == 0L)
/*      */           break; 
/*  643 */         linkedPos++;
/*  644 */         linkedVal = this.phys.getLong(linkedVal & 0xFFFFFFFFFFF0L);
/*      */       } 
/*      */     } 
/*  647 */     return linkedRecords;
/*      */   }
/*      */   
/*      */   protected long[] physAllocate(int size, boolean ensureAvail, boolean recursive) {
/*  651 */     assert this.structuralLock.isHeldByCurrentThread();
/*  652 */     if (size == 0L)
/*  652 */       return new long[] { 0L }; 
/*  654 */     if (size < 65535) {
/*  655 */       long indexVal = freePhysTake(size, ensureAvail, recursive);
/*  656 */       indexVal |= size << 48L;
/*  657 */       return new long[] { indexVal };
/*      */     } 
/*  659 */     long[] ret = new long[2];
/*  660 */     int retPos = 0;
/*  661 */     int c = 8;
/*  663 */     while (size > 0) {
/*  664 */       if (retPos == ret.length)
/*  664 */         ret = Arrays.copyOf(ret, ret.length * 2); 
/*  665 */       int allocSize = Math.min(size, 65535);
/*  666 */       size -= allocSize - c;
/*  669 */       long indexVal = freePhysTake(allocSize, ensureAvail, recursive);
/*  670 */       indexVal |= allocSize << 48L;
/*  671 */       if (c != 0)
/*  671 */         indexVal |= 0x8L; 
/*  672 */       ret[retPos++] = indexVal;
/*  674 */       c = (size <= 65535) ? 0 : 8;
/*      */     } 
/*  676 */     if (size != 0)
/*  676 */       throw new AssertionError(); 
/*  678 */     return Arrays.copyOf(ret, retPos);
/*      */   }
/*      */   
/*      */   protected static long roundTo16(long offset) {
/*  683 */     long rem = offset & 0xFL;
/*  684 */     if (rem != 0L)
/*  684 */       offset += 16L - rem; 
/*  685 */     return offset;
/*      */   }
/*      */   
/*      */   public void close() {
/*  690 */     for (Runnable closeListener : this.closeListeners)
/*  691 */       closeListener.run(); 
/*  694 */     lockAllWrite();
/*      */     try {
/*      */       try {
/*  697 */         if (!this.readOnly) {
/*  698 */           if (this.serializerPojo != null && this.serializerPojo.hasUnsavedChanges())
/*  699 */             this.serializerPojo.save(this); 
/*  702 */           this.index.putLong(16L, this.physSize);
/*  703 */           this.index.putLong(8L, this.indexSize);
/*  704 */           this.index.putLong(24L, this.freeSize);
/*  706 */           this.index.putLong(32L, indexHeaderChecksum());
/*      */         } 
/*  711 */         if (!this.deleteFilesAfterClose) {
/*  712 */           this.index.sync();
/*  713 */           this.phys.sync();
/*      */         } 
/*      */       } finally {
/*      */         try {
/*  717 */           this.index.close();
/*      */         } finally {
/*      */           try {
/*  720 */             this.phys.close();
/*      */           } finally {
/*  722 */             if (this.deleteFilesAfterClose) {
/*  723 */               this.index.deleteFile();
/*  724 */               this.phys.deleteFile();
/*      */             } 
/*  726 */             this.index = null;
/*  727 */             this.phys = null;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } finally {
/*  733 */       unlockAllWrite();
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean isClosed() {
/*  739 */     return (this.index == null);
/*      */   }
/*      */   
/*      */   public void commit() {
/*  744 */     if (!this.readOnly) {
/*  746 */       if (this.serializerPojo != null && this.serializerPojo.hasUnsavedChanges())
/*  747 */         this.serializerPojo.save(this); 
/*  750 */       this.index.putLong(16L, this.physSize);
/*  751 */       this.index.putLong(8L, this.indexSize);
/*  752 */       this.index.putLong(24L, this.freeSize);
/*  754 */       this.index.putLong(32L, indexHeaderChecksum());
/*      */     } 
/*  756 */     if (!this.syncOnCommitDisabled) {
/*  757 */       this.index.sync();
/*  758 */       this.phys.sync();
/*      */     } 
/*      */   }
/*      */   
/*      */   public void rollback() throws UnsupportedOperationException {
/*  764 */     throw new UnsupportedOperationException("rollback not supported with journal disabled");
/*      */   }
/*      */   
/*      */   public boolean isReadOnly() {
/*  769 */     return this.readOnly;
/*      */   }
/*      */   
/*      */   public boolean canRollback() {
/*  774 */     return false;
/*      */   }
/*      */   
/*      */   public void clearCache() {}
/*      */   
/*      */   public void compact() {
/*      */     int rafMode;
/*  784 */     if (this.readOnly)
/*  784 */       throw new IllegalAccessError(); 
/*  786 */     File indexFile = this.index.getFile();
/*  787 */     File physFile = this.phys.getFile();
/*  790 */     if (this.index instanceof Volume.FileChannelVol) {
/*  791 */       rafMode = 2;
/*  792 */     } else if (this.index instanceof Volume.MappedFileVol && this.phys instanceof Volume.FileChannelVol) {
/*  793 */       rafMode = 1;
/*      */     } else {
/*  795 */       rafMode = 0;
/*      */     } 
/*  799 */     lockAllWrite();
/*      */     try {
/*  801 */       File f1del = null;
/*  802 */       File compactedFile = new File(((indexFile != null) ? (String)indexFile : (String)(f1del = File.createTempFile("mapdb", "compact"))) + ".compact");
/*  803 */       boolean asyncWriteEnabled = (this.index instanceof Volume.ByteBufferVol && ((Volume.ByteBufferVol)this.index).asyncWriteEnabled);
/*  804 */       Volume.Factory fab = Volume.fileFactory(compactedFile, rafMode, false, this.sizeLimit, 20, 0, new File(compactedFile.getPath() + ".p"), new File(compactedFile.getPath() + ".t"), asyncWriteEnabled);
/*  808 */       StoreDirect store2 = new StoreDirect(fab, false, false, 5, false, 0L, this.checksum, this.compress, this.password, false, 0);
/*  810 */       compactPreUnderLock();
/*  812 */       this.index.putLong(16L, this.physSize);
/*  813 */       this.index.putLong(8L, this.indexSize);
/*  814 */       this.index.putLong(24L, this.freeSize);
/*  817 */       store2.lockAllWrite();
/*  821 */       long recid = longStackTake(120L, false);
/*  822 */       for (; recid != 0L; recid = longStackTake(120L, false))
/*  823 */         store2.longStackPut(120L, recid, false); 
/*  827 */       store2.index.putLong(8L, this.indexSize);
/*      */       long ioRecid;
/*  829 */       for (ioRecid = 32896L; ioRecid < this.indexSize; ioRecid += 8L) {
/*  830 */         byte[] bb = get2(ioRecid, (Serializer)Serializer.BYTE_ARRAY_NOSIZE);
/*  831 */         store2.index.ensureAvailable(ioRecid + 8L);
/*  832 */         if (bb == null || bb.length == 0) {
/*  833 */           store2.index.putLong(ioRecid, 0L);
/*      */         } else {
/*  835 */           DataOutput2 out = serialize(bb, (Serializer)Serializer.BYTE_ARRAY_NOSIZE);
/*  836 */           long[] indexVals = store2.physAllocate(out.pos, true, false);
/*  837 */           store2.put2(out, ioRecid, indexVals);
/*      */         } 
/*      */       } 
/*  841 */       File indexFile2 = store2.index.getFile();
/*  842 */       File physFile2 = store2.phys.getFile();
/*  843 */       store2.unlockAllWrite();
/*  845 */       boolean useDirectBuffer = (this.index instanceof Volume.MemoryVol && ((Volume.MemoryVol)this.index).useDirectBuffer);
/*  847 */       this.index.sync();
/*  848 */       this.index.close();
/*  849 */       this.index = null;
/*  850 */       this.phys.sync();
/*  851 */       this.phys.close();
/*  852 */       this.phys = null;
/*  854 */       if (indexFile != null) {
/*  855 */         long time = System.currentTimeMillis();
/*  856 */         File indexFile_ = (indexFile != null) ? new File(indexFile.getPath() + "_" + time + "_orig") : null;
/*  857 */         File physFile_ = (physFile != null) ? new File(physFile.getPath() + "_" + time + "_orig") : null;
/*  859 */         store2.close();
/*  861 */         if (!indexFile.renameTo(indexFile_))
/*  862 */           throw new AssertionError("could not rename file"); 
/*  863 */         if (!physFile.renameTo(physFile_))
/*  864 */           throw new AssertionError("could not rename file"); 
/*  866 */         if (!indexFile2.renameTo(indexFile))
/*  867 */           throw new AssertionError("could not rename file"); 
/*  869 */         if (!physFile2.renameTo(physFile))
/*  870 */           throw new AssertionError("could not rename file"); 
/*  872 */         Volume.Factory fac2 = Volume.fileFactory(indexFile, rafMode, false, this.sizeLimit, 20, 0, new File(indexFile.getPath() + ".p"), new File(indexFile.getPath() + ".t"), asyncWriteEnabled);
/*  876 */         this.index = fac2.createIndexVolume();
/*  877 */         this.phys = fac2.createPhysVolume();
/*  879 */         indexFile_.delete();
/*  880 */         physFile_.delete();
/*      */       } else {
/*  883 */         Volume indexVol2 = new Volume.MemoryVol(useDirectBuffer, this.sizeLimit, 20);
/*  884 */         Volume.volumeTransfer(this.indexSize, store2.index, indexVol2);
/*  885 */         Volume physVol2 = new Volume.MemoryVol(useDirectBuffer, this.sizeLimit, 20);
/*  886 */         Volume.volumeTransfer(store2.physSize, store2.phys, physVol2);
/*  888 */         File f1 = store2.index.getFile();
/*  889 */         File f2 = store2.phys.getFile();
/*  891 */         store2.close();
/*  893 */         f1.delete();
/*  894 */         f2.delete();
/*  896 */         this.index = indexVol2;
/*  897 */         this.phys = physVol2;
/*      */       } 
/*  901 */       if (f1del != null)
/*  902 */         f1del.delete(); 
/*  906 */       this.physSize = store2.physSize;
/*  907 */       this.freeSize = store2.freeSize;
/*  908 */       this.index.putLong(16L, this.physSize);
/*  909 */       this.index.putLong(8L, this.indexSize);
/*  910 */       this.index.putLong(24L, this.freeSize);
/*  911 */       this.index.putLong(32L, indexHeaderChecksum());
/*  913 */       this.maxUsedIoList = 32888L;
/*  914 */       while (this.index.getLong(this.maxUsedIoList) != 0L && this.maxUsedIoList > 120L)
/*  915 */         this.maxUsedIoList -= 8L; 
/*  917 */       compactPostUnderLock();
/*  919 */     } catch (IOException e) {
/*  920 */       throw new IOError(e);
/*      */     } finally {
/*  922 */       unlockAllWrite();
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void compactPreUnderLock() {}
/*      */   
/*      */   protected void compactPostUnderLock() {}
/*      */   
/*      */   protected long longStackTake(long ioList, boolean recursive) {
/*  937 */     assert this.structuralLock.isHeldByCurrentThread();
/*  938 */     assert ioList >= 120L && ioList < 32896L : "wrong ioList: " + ioList;
/*  940 */     long dataOffset = this.index.getLong(ioList);
/*  941 */     if (dataOffset == 0L)
/*  941 */       return 0L; 
/*  943 */     long pos = dataOffset >>> 48L;
/*  944 */     dataOffset &= 0xFFFFFFFFFFF0L;
/*  946 */     if (pos < 8L)
/*  946 */       throw new AssertionError(); 
/*  948 */     long ret = this.phys.getSixLong(dataOffset + pos);
/*  951 */     if (pos == 8L) {
/*  953 */       long next = this.phys.getLong(dataOffset);
/*  954 */       long size = next >>> 48L;
/*  955 */       next &= 0xFFFFFFFFFFF0L;
/*  956 */       if (next != 0L) {
/*  958 */         long nextSize = this.phys.getUnsignedShort(next);
/*  959 */         assert (nextSize - 8L) % 6L == 0L;
/*  960 */         this.index.putLong(ioList, nextSize - 6L << 48L | next);
/*      */       } else {
/*  963 */         this.index.putLong(ioList, 0L);
/*  964 */         if (this.maxUsedIoList == ioList)
/*  966 */           while (this.index.getLong(this.maxUsedIoList) == 0L && this.maxUsedIoList > 120L)
/*  967 */             this.maxUsedIoList -= 8L;  
/*      */       } 
/*  972 */       freePhysPut(size << 48L | dataOffset, true);
/*      */     } else {
/*  975 */       pos -= 6L;
/*  976 */       this.index.putLong(ioList, pos << 48L | dataOffset);
/*      */     } 
/*  981 */     return ret;
/*      */   }
/*      */   
/*      */   protected void longStackPut(long ioList, long offset, boolean recursive) {
/*  987 */     assert this.structuralLock.isHeldByCurrentThread();
/*  988 */     assert offset >>> 48L == 0L;
/*  989 */     assert ioList >= 120L && ioList <= 32896L : "wrong ioList: " + ioList;
/*  991 */     long dataOffset = this.index.getLong(ioList);
/*  992 */     long pos = dataOffset >>> 48L;
/*  993 */     dataOffset &= 0xFFFFFFFFFFF0L;
/*  995 */     if (dataOffset == 0L) {
/*  998 */       long listPhysid = freePhysTake(1232, true, true) & 0xFFFFFFFFFFF0L;
/*  999 */       if (listPhysid == 0L)
/*  999 */         throw new AssertionError(); 
/* 1002 */       this.phys.putLong(listPhysid, 346777171307528192L);
/* 1004 */       this.phys.putSixLong(listPhysid + 8L, offset);
/* 1006 */       this.index.putLong(ioList, 0x8000000000000L | listPhysid);
/* 1007 */       if (this.maxUsedIoList <= ioList)
/* 1007 */         this.maxUsedIoList = ioList; 
/*      */     } else {
/* 1009 */       long next = this.phys.getLong(dataOffset);
/* 1010 */       long size = next >>> 48L;
/* 1011 */       next &= 0xFFFFFFFFFFF0L;
/* 1012 */       assert pos + 6L <= size;
/* 1013 */       if (pos + 6L == size) {
/* 1014 */         long newPageSize = 1232L;
/* 1015 */         if (ioList == size2ListIoRecid(1232L))
/* 1017 */           newPageSize = 1280L; 
/* 1020 */         long listPhysid = freePhysTake((int)newPageSize, true, true) & 0xFFFFFFFFFFF0L;
/* 1021 */         if (listPhysid == 0L)
/* 1021 */           throw new AssertionError(); 
/* 1024 */         this.phys.putLong(listPhysid, newPageSize << 48L | dataOffset & 0xFFFFFFFFFFF0L);
/* 1027 */         this.phys.putSixLong(listPhysid + 8L, offset);
/* 1030 */         this.index.putLong(ioList, 0x8000000000000L | listPhysid);
/*      */       } else {
/* 1033 */         pos += 6L;
/* 1034 */         this.phys.putSixLong(dataOffset + pos, offset);
/* 1035 */         this.index.putLong(ioList, pos << 48L | dataOffset);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void freeIoRecidPut(long ioRecid) {
/* 1043 */     assert ioRecid > 32896L;
/* 1044 */     assert this.locks[Store.lockPos(ioRecid)].writeLock().isHeldByCurrentThread();
/* 1045 */     if (this.spaceReclaimTrack)
/* 1046 */       longStackPut(120L, ioRecid, false); 
/*      */   }
/*      */   
/*      */   protected long freeIoRecidTake(boolean ensureAvail) {
/* 1050 */     if (this.spaceReclaimTrack) {
/* 1051 */       long ioRecid = longStackTake(120L, false);
/* 1052 */       if (ioRecid != 0L) {
/* 1053 */         assert ioRecid > 32896L;
/* 1054 */         return ioRecid;
/*      */       } 
/*      */     } 
/* 1057 */     this.indexSize += 8L;
/* 1058 */     if (ensureAvail)
/* 1059 */       this.index.ensureAvailable(this.indexSize); 
/* 1060 */     assert this.indexSize - 8L > 32896L;
/* 1061 */     return this.indexSize - 8L;
/*      */   }
/*      */   
/*      */   protected static long size2ListIoRecid(long size) {
/* 1065 */     return 128L + (size - 1L) / 16L * 8L;
/*      */   }
/*      */   
/*      */   protected void freePhysPut(long indexVal, boolean recursive) {
/* 1068 */     assert this.structuralLock.isHeldByCurrentThread();
/* 1069 */     long size = indexVal >>> 48L;
/* 1070 */     assert size != 0L;
/* 1071 */     indexVal &= 0xFFFFFFFFFFF0L;
/* 1072 */     if (this.physSize == indexVal + roundTo16(size)) {
/* 1074 */       this.physSize = indexVal;
/*      */       return;
/*      */     } 
/* 1078 */     this.freeSize += roundTo16(size);
/* 1079 */     longStackPut(size2ListIoRecid(size), indexVal, recursive);
/*      */   }
/*      */   
/*      */   protected long freePhysTake(int size, boolean ensureAvail, boolean recursive) {
/* 1083 */     assert this.structuralLock.isHeldByCurrentThread();
/* 1084 */     assert size > 0;
/* 1086 */     if (this.spaceReclaimReuse) {
/* 1087 */       long ret = longStackTake(size2ListIoRecid(size), recursive);
/* 1088 */       if (ret != 0L) {
/* 1089 */         this.freeSize -= roundTo16(size);
/* 1090 */         return ret;
/*      */       } 
/*      */     } 
/* 1094 */     if (!recursive && this.spaceReclaimSplit) {
/*      */       long s;
/* 1095 */       for (s = roundTo16(size) + 16L; s < 65535L; s += 16L) {
/* 1096 */         long ioList = size2ListIoRecid(s);
/* 1097 */         if (ioList > this.maxUsedIoList)
/*      */           break; 
/* 1098 */         long ret = longStackTake(ioList, recursive);
/* 1099 */         if (ret != 0L) {
/* 1101 */           long offset = ret & 0xFFFFFFFFFFF0L;
/* 1103 */           long remaining = s - roundTo16(size);
/* 1104 */           long markFree = remaining << 48L | offset + s - remaining;
/* 1105 */           freePhysPut(markFree, recursive);
/* 1107 */           this.freeSize -= roundTo16(s);
/* 1108 */           return size << 48L | offset;
/*      */         } 
/*      */       } 
/*      */     } 
/* 1114 */     if ((this.physSize & 0xFFFFFL) + size > 1048576L)
/* 1115 */       this.physSize += 1048576L - (this.physSize & 0xFFFFFL); 
/* 1116 */     long physSize2 = this.physSize;
/* 1117 */     this.physSize = roundTo16(this.physSize + size);
/* 1118 */     if (ensureAvail)
/* 1119 */       this.phys.ensureAvailable(this.physSize); 
/* 1120 */     return physSize2;
/*      */   }
/*      */   
/*      */   public long getMaxRecid() {
/* 1126 */     return (this.indexSize - 32896L) / 8L;
/*      */   }
/*      */   
/*      */   public ByteBuffer getRaw(long recid) {
/* 1132 */     byte[] bb = get(recid, (Serializer)Serializer.BYTE_ARRAY_NOSIZE);
/* 1133 */     if (bb == null)
/* 1133 */       return null; 
/* 1134 */     return ByteBuffer.wrap(bb);
/*      */   }
/*      */   
/*      */   public Iterator<Long> getFreeRecids() {
/* 1139 */     return Fun.EMPTY_ITERATOR;
/*      */   }
/*      */   
/*      */   public void updateRaw(long recid, ByteBuffer data) {
/* 1144 */     long ioRecid = recid * 8L + 32896L;
/* 1145 */     if (ioRecid >= this.indexSize) {
/* 1146 */       this.indexSize = ioRecid + 8L;
/* 1147 */       this.index.ensureAvailable(this.indexSize);
/*      */     } 
/* 1150 */     byte[] b = null;
/* 1152 */     if (data != null) {
/* 1153 */       data = data.duplicate();
/* 1154 */       b = new byte[data.remaining()];
/* 1155 */       data.get(b);
/*      */     } 
/* 1158 */     update(recid, b, (Serializer)Serializer.BYTE_ARRAY_NOSIZE);
/*      */   }
/*      */   
/*      */   public long getSizeLimit() {
/* 1163 */     return this.sizeLimit;
/*      */   }
/*      */   
/*      */   public long getCurrSize() {
/* 1168 */     return this.physSize;
/*      */   }
/*      */   
/*      */   public long getFreeSize() {
/* 1173 */     return this.freeSize;
/*      */   }
/*      */   
/*      */   public String calculateStatistics() {
/* 1178 */     String s = "";
/* 1179 */     s = s + getClass().getName() + "\n";
/* 1180 */     s = s + "volume: \n";
/* 1181 */     s = s + "  " + this.phys + "\n";
/* 1183 */     s = s + "indexSize=" + this.indexSize + "\n";
/* 1184 */     s = s + "physSize=" + this.physSize + "\n";
/* 1185 */     s = s + "freeSize=" + this.freeSize + "\n";
/* 1187 */     s = s + "num of freeRecids: " + countLongStackItems(120L) + "\n";
/* 1189 */     for (int size = 16; size < 65545; size *= 2) {
/* 1190 */       long sum = 0L;
/* 1191 */       for (int ss = size / 2; ss < size; ss += 16)
/* 1192 */         sum += countLongStackItems(size2ListIoRecid(ss)) * ss; 
/* 1194 */       s = s + "Size occupied by free records (size=" + size + ") = " + sum;
/*      */     } 
/* 1198 */     return s;
/*      */   }
/*      */   
/*      */   protected long countLongStackItems(long ioList) {
/* 1202 */     long ret = 0L;
/* 1203 */     long v = this.index.getLong(ioList);
/*      */     while (true) {
/* 1206 */       long next = v & 0xFFFFFFFFFFF0L;
/* 1207 */       if (next == 0L)
/* 1207 */         return ret; 
/* 1208 */       ret += v >>> 48L;
/* 1209 */       v = this.phys.getLong(next);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\mapdb\StoreDirect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */