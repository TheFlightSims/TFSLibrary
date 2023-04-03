/*      */ package org.mapdb;
/*      */ 
/*      */ import java.io.IOError;
/*      */ import java.io.IOException;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.util.Arrays;
/*      */ import java.util.concurrent.atomic.AtomicInteger;
/*      */ import java.util.concurrent.locks.Lock;
/*      */ import java.util.zip.CRC32;
/*      */ 
/*      */ public class StoreWAL extends StoreDirect {
/*      */   protected static final long LOG_MASK_OFFSET = 281474976710655L;
/*      */   
/*      */   protected static final byte WAL_INDEX_LONG = 101;
/*      */   
/*      */   protected static final byte WAL_LONGSTACK_PAGE = 102;
/*      */   
/*      */   protected static final byte WAL_PHYS_ARRAY_ONE_LONG = 103;
/*      */   
/*      */   protected static final byte WAL_PHYS_ARRAY = 104;
/*      */   
/*      */   protected static final byte WAL_SKIP_REST_OF_BLOCK = 105;
/*      */   
/*      */   protected static final byte WAL_SEAL = 111;
/*      */   
/*      */   protected static final long LOG_SEAL = 4566556446554645L;
/*      */   
/*      */   public static final String TRANS_LOG_FILE_EXT = ".t";
/*      */   
/*   50 */   protected static final long[] TOMBSTONE = new long[0];
/*      */   
/*   51 */   protected static final long[] PREALLOC = new long[0];
/*      */   
/*      */   protected final Volume.Factory volFac;
/*      */   
/*      */   protected Volume log;
/*      */   
/*      */   protected volatile long logSize;
/*      */   
/*   58 */   protected final LongConcurrentHashMap<long[]> modified = (LongConcurrentHashMap)new LongConcurrentHashMap<long>();
/*      */   
/*   59 */   protected final LongMap<byte[]> longStackPages = (LongMap)new LongHashMap<byte>();
/*      */   
/*   60 */   protected final long[] indexVals = new long[4112];
/*      */   
/*   61 */   protected final boolean[] indexValsModified = new boolean[this.indexVals.length];
/*      */   
/*      */   protected boolean replayPending = true;
/*      */   
/*   66 */   protected final AtomicInteger logChecksum = new AtomicInteger();
/*      */   
/*      */   public StoreWAL(Volume.Factory volFac) {
/*   69 */     this(volFac, false, false, 5, false, 0L, false, false, (byte[])null, false, 0);
/*      */   }
/*      */   
/*      */   public StoreWAL(Volume.Factory volFac, boolean readOnly, boolean deleteFilesAfterClose, int spaceReclaimMode, boolean syncOnCommitDisabled, long sizeLimit, boolean checksum, boolean compress, byte[] password, boolean disableLocks, int sizeIncrement) {
/*   74 */     super(volFac, readOnly, deleteFilesAfterClose, spaceReclaimMode, syncOnCommitDisabled, sizeLimit, checksum, compress, password, disableLocks, sizeIncrement);
/*   76 */     this.volFac = volFac;
/*   77 */     this.log = volFac.createTransLogVolume();
/*   79 */     boolean allGood = false;
/*   80 */     this.structuralLock.lock();
/*      */     try {
/*   82 */       reloadIndexFile();
/*   83 */       if (verifyLogFile())
/*   84 */         replayLogFile(); 
/*   86 */       this.replayPending = false;
/*   87 */       checkHeaders();
/*   88 */       if (!readOnly)
/*   89 */         logReset(); 
/*   90 */       allGood = true;
/*      */     } finally {
/*   92 */       if (!allGood) {
/*   94 */         if (this.log != null) {
/*   95 */           this.log.close();
/*   96 */           this.log = null;
/*      */         } 
/*   98 */         if (this.index != null) {
/*   99 */           this.index.close();
/*  100 */           this.index = null;
/*      */         } 
/*  102 */         if (this.phys != null) {
/*  103 */           this.phys.close();
/*  104 */           this.phys = null;
/*      */         } 
/*      */       } 
/*  108 */       this.structuralLock.unlock();
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void checkHeaders() {
/*  114 */     if (this.replayPending)
/*      */       return; 
/*  115 */     super.checkHeaders();
/*      */   }
/*      */   
/*      */   protected void reloadIndexFile() {
/*  119 */     assert this.structuralLock.isHeldByCurrentThread();
/*  120 */     this.logSize = 16L;
/*  121 */     this.modified.clear();
/*  122 */     this.longStackPages.clear();
/*  123 */     this.indexSize = this.index.getLong(8L);
/*  124 */     this.physSize = this.index.getLong(16L);
/*  125 */     this.freeSize = this.index.getLong(24L);
/*  126 */     for (int i = 0; i < 32896; i += 8)
/*  127 */       this.indexVals[i / 8] = this.index.getLong(i); 
/*  129 */     Arrays.fill(this.indexValsModified, false);
/*  131 */     this.logChecksum.set(0);
/*  133 */     this.maxUsedIoList = 32888L;
/*  134 */     while (this.indexVals[(int)(this.maxUsedIoList / 8L)] != 0L && this.maxUsedIoList > 120L)
/*  135 */       this.maxUsedIoList -= 8L; 
/*      */   }
/*      */   
/*      */   protected void logReset() {
/*  139 */     assert this.structuralLock.isHeldByCurrentThread();
/*  140 */     this.log.truncate(16L);
/*  141 */     this.log.ensureAvailable(16L);
/*  142 */     this.log.putInt(0L, 234243482);
/*  143 */     this.log.putUnsignedShort(4L, 10000);
/*  144 */     this.log.putUnsignedShort(6L, expectedMasks());
/*  145 */     this.log.putLong(8L, 0L);
/*  146 */     this.logSize = 16L;
/*      */   }
/*      */   
/*      */   public long preallocate() {
/*      */     long ioRecid;
/*  155 */     this.newRecidLock.readLock().lock();
/*      */     try {
/*      */       long logPos;
/*  157 */       this.structuralLock.lock();
/*      */       try {
/*  159 */         checkLogRounding();
/*  160 */         ioRecid = freeIoRecidTake(false);
/*  161 */         logPos = this.logSize;
/*  163 */         this.logSize += 17L;
/*  164 */         this.log.ensureAvailable(this.logSize);
/*      */       } finally {
/*  167 */         this.structuralLock.unlock();
/*      */       } 
/*  169 */       Lock lock = this.locks[Store.lockPos(ioRecid)].writeLock();
/*  170 */       lock.lock();
/*      */       try {
/*  174 */         walIndexVal(logPos, ioRecid, 4L);
/*  175 */         this.modified.put(ioRecid, PREALLOC);
/*      */       } finally {
/*  177 */         lock.unlock();
/*      */       } 
/*      */     } finally {
/*  180 */       this.newRecidLock.readLock().unlock();
/*      */     } 
/*  183 */     long recid = (ioRecid - 32896L) / 8L;
/*  184 */     assert recid > 0L;
/*  185 */     return recid;
/*      */   }
/*      */   
/*      */   public void preallocate(long[] recids) {
/*  191 */     for (int i = 0; i < recids.length; i++)
/*  192 */       recids[i] = preallocate(); 
/*      */   }
/*      */   
/*      */   public <A> long put(A value, Serializer<A> serializer) {
/*      */     long ioRecid;
/*  199 */     assert value != null;
/*  200 */     DataOutput2 out = serialize(value, serializer);
/*  206 */     this.newRecidLock.readLock().lock();
/*      */     try {
/*      */       long[] physPos, logPos;
/*  209 */       this.structuralLock.lock();
/*      */       try {
/*  211 */         ioRecid = freeIoRecidTake(false);
/*  213 */         physPos = physAllocate(out.pos, false, false);
/*  215 */         logPos = logAllocate(physPos);
/*      */       } finally {
/*  218 */         this.structuralLock.unlock();
/*      */       } 
/*  221 */       Lock lock = this.locks[Store.lockPos(ioRecid)].writeLock();
/*  222 */       lock.lock();
/*      */       try {
/*  225 */         walIndexVal((logPos[0] & 0xFFFFFFFFFFFFL) - 1L - 8L - 8L - 1L - 8L, ioRecid, physPos[0] | 0x2L);
/*  226 */         walPhysArray(out, physPos, logPos);
/*  228 */         this.modified.put(ioRecid, logPos);
/*  229 */         this.recycledDataOuts.offer(out);
/*      */       } finally {
/*  231 */         lock.unlock();
/*      */       } 
/*      */     } finally {
/*  234 */       this.newRecidLock.readLock().unlock();
/*      */     } 
/*  237 */     long recid = (ioRecid - 32896L) / 8L;
/*  238 */     assert recid > 0L;
/*  239 */     return recid;
/*      */   }
/*      */   
/*      */   protected void walPhysArray(DataOutput2 out, long[] physPos, long[] logPos) {
/*  244 */     int outPos = 0;
/*  245 */     int logC = 0;
/*  246 */     CRC32 crc32 = new CRC32();
/*  248 */     for (int i = 0; i < logPos.length; i++) {
/*  249 */       int c = (i == logPos.length - 1) ? 0 : 8;
/*  250 */       long pos = logPos[i] & 0xFFFFFFFFFFFFL;
/*  251 */       int size = (int)(logPos[i] >>> 48L);
/*  253 */       byte header = (c == 0) ? 104 : 103;
/*  254 */       this.log.putByte(pos - 8L - 1L, header);
/*  255 */       this.log.putLong(pos - 8L, physPos[i]);
/*  257 */       if (c > 0)
/*  258 */         this.log.putLong(pos, physPos[i + 1]); 
/*  260 */       this.log.putData(pos + c, out.buf, outPos, size - c);
/*  262 */       crc32.reset();
/*  263 */       crc32.update(out.buf, outPos, size - c);
/*  264 */       logC |= LongHashMap.longHash(pos | header | physPos[i] | ((c > 0) ? physPos[i + 1] : 0L) | crc32.getValue());
/*  266 */       outPos += size - c;
/*  267 */       assert this.logSize >= outPos;
/*      */     } 
/*  269 */     logChecksumAdd(logC);
/*  270 */     assert outPos == out.pos;
/*      */   }
/*      */   
/*      */   protected void walIndexVal(long logPos, long ioRecid, long indexVal) {
/*  275 */     assert this.locks[Store.lockPos(ioRecid)].writeLock().isHeldByCurrentThread();
/*  276 */     assert this.logSize >= logPos + 1L + 8L + 8L;
/*  277 */     this.log.putByte(logPos, (byte)101);
/*  278 */     this.log.putLong(logPos + 1L, ioRecid);
/*  279 */     this.log.putLong(logPos + 9L, indexVal);
/*  281 */     logChecksumAdd(LongHashMap.longHash(logPos | 0x65L | ioRecid | indexVal));
/*      */   }
/*      */   
/*      */   protected long[] logAllocate(long[] physPos) {
/*  286 */     assert this.structuralLock.isHeldByCurrentThread();
/*  287 */     this.logSize += 17L;
/*  289 */     long[] ret = new long[physPos.length];
/*  290 */     for (int i = 0; i < physPos.length; i++) {
/*  291 */       long size = physPos[i] >>> 48L;
/*  293 */       this.logSize += 9L;
/*  294 */       ret[i] = size << 48L | this.logSize;
/*  296 */       this.logSize += size;
/*  297 */       checkLogRounding();
/*      */     } 
/*  299 */     this.log.ensureAvailable(this.logSize);
/*  300 */     return ret;
/*      */   }
/*      */   
/*      */   protected void checkLogRounding() {
/*  304 */     assert this.structuralLock.isHeldByCurrentThread();
/*  305 */     if ((this.logSize & 0xFFFFFL) + 131070L > 1048576L) {
/*  306 */       this.log.ensureAvailable(this.logSize + 1L);
/*  307 */       this.log.putByte(this.logSize, (byte)105);
/*  308 */       this.logSize += 1048576L - (this.logSize & 0xFFFFFL);
/*      */     } 
/*      */   }
/*      */   
/*      */   public <A> A get(long recid, Serializer<A> serializer) {
/*  315 */     assert recid > 0L;
/*  316 */     long ioRecid = 32896L + recid * 8L;
/*  317 */     Lock lock = this.locks[Store.lockPos(ioRecid)].readLock();
/*  318 */     lock.lock();
/*      */     try {
/*  320 */       return (A)get2(ioRecid, (Serializer)serializer);
/*  321 */     } catch (IOException e) {
/*  322 */       throw new IOError(e);
/*      */     } finally {
/*  324 */       lock.unlock();
/*      */     } 
/*      */   }
/*      */   
/*      */   protected <A> A get2(long ioRecid, Serializer<A> serializer) throws IOException {
/*  330 */     assert this.locks[Store.lockPos(ioRecid)].getWriteHoldCount() == 0 || this.locks[Store.lockPos(ioRecid)].writeLock().isHeldByCurrentThread();
/*  334 */     long[] r = this.modified.get(ioRecid);
/*  336 */     if (r == null)
/*  336 */       return super.get2(ioRecid, serializer); 
/*  338 */     if (r == TOMBSTONE || r == PREALLOC || r.length == 0)
/*  338 */       return null; 
/*  341 */     if (r.length == 1) {
/*  343 */       int size = (int)(r[0] >>> 48L);
/*  344 */       DataInput2 in = (DataInput2)this.log.getDataInput(r[0] & 0xFFFFFFFFFFFFL, size);
/*  345 */       return deserialize(serializer, size, in);
/*      */     } 
/*  348 */     int totalSize = 0;
/*  349 */     for (int i = 0; i < r.length; i++) {
/*  350 */       int c = (i == r.length - 1) ? 0 : 8;
/*  351 */       totalSize += (int)(r[i] >>> 48L) - c;
/*      */     } 
/*  353 */     byte[] b = new byte[totalSize];
/*  354 */     int pos = 0;
/*  355 */     for (int j = 0; j < r.length; j++) {
/*  356 */       int c = (j == r.length - 1) ? 0 : 8;
/*  357 */       int size = (int)(r[j] >>> 48L) - c;
/*  358 */       this.log.getDataInput((r[j] & 0xFFFFFFFFFFFFL) + c, size).readFully(b, pos, size);
/*  359 */       pos += size;
/*      */     } 
/*  361 */     if (pos != totalSize)
/*  361 */       throw new AssertionError(); 
/*  363 */     return deserialize(serializer, totalSize, new DataInput2(b));
/*      */   }
/*      */   
/*      */   public <A> void update(long recid, A value, Serializer<A> serializer) {
/*  369 */     assert recid > 0L;
/*  370 */     assert value != null;
/*  371 */     DataOutput2 out = serialize(value, serializer);
/*  372 */     long ioRecid = 32896L + recid * 8L;
/*  373 */     Lock lock = this.locks[Store.lockPos(ioRecid)].writeLock();
/*  374 */     lock.lock();
/*      */     try {
/*  379 */       long physPos[], logPos[], indexVal = 0L;
/*  380 */       long[] linkedRecords = getLinkedRecordsFromLog(ioRecid);
/*  381 */       if (linkedRecords == null) {
/*  382 */         indexVal = this.index.getLong(ioRecid);
/*  383 */         linkedRecords = getLinkedRecordsIndexVals(indexVal);
/*  384 */       } else if (linkedRecords == PREALLOC) {
/*  385 */         linkedRecords = null;
/*      */       } 
/*  388 */       this.structuralLock.lock();
/*      */       try {
/*  392 */         if (indexVal >>> 48L > 0L)
/*  393 */           freePhysPut(indexVal, false); 
/*  396 */         if (linkedRecords != null)
/*  397 */           for (int i = 0; i < linkedRecords.length && linkedRecords[i] != 0L; i++)
/*  398 */             freePhysPut(linkedRecords[i], false);  
/*  404 */         physPos = physAllocate(out.pos, false, false);
/*  406 */         logPos = logAllocate(physPos);
/*      */       } finally {
/*  409 */         this.structuralLock.unlock();
/*      */       } 
/*  413 */       walIndexVal((logPos[0] & 0xFFFFFFFFFFFFL) - 1L - 8L - 8L - 1L - 8L, ioRecid, physPos[0] | 0x2L);
/*  414 */       walPhysArray(out, physPos, logPos);
/*  416 */       this.modified.put(ioRecid, logPos);
/*      */     } finally {
/*  418 */       lock.unlock();
/*      */     } 
/*  420 */     this.recycledDataOuts.offer(out);
/*      */   }
/*      */   
/*      */   public <A> boolean compareAndSwap(long recid, A expectedOldValue, A newValue, Serializer<A> serializer) {
/*      */     DataOutput2 out;
/*  425 */     assert recid > 0L;
/*  426 */     assert expectedOldValue != null && newValue != null;
/*  427 */     long ioRecid = 32896L + recid * 8L;
/*  428 */     Lock lock = this.locks[Store.lockPos(ioRecid)].writeLock();
/*  429 */     lock.lock();
/*      */     try {
/*      */       long[] physPos, logPos;
/*  433 */       A oldVal = get2(ioRecid, serializer);
/*  434 */       if ((oldVal == null && expectedOldValue != null) || (oldVal != null && !oldVal.equals(expectedOldValue)))
/*  435 */         return false; 
/*  437 */       out = serialize(newValue, serializer);
/*  442 */       long indexVal = 0L;
/*  443 */       long[] linkedRecords = getLinkedRecordsFromLog(ioRecid);
/*  444 */       if (linkedRecords == null) {
/*  445 */         indexVal = this.index.getLong(ioRecid);
/*  446 */         linkedRecords = getLinkedRecordsIndexVals(indexVal);
/*      */       } 
/*  449 */       this.structuralLock.lock();
/*      */       try {
/*  453 */         if (indexVal >>> 48L > 0L)
/*  454 */           freePhysPut(indexVal, false); 
/*  457 */         if (linkedRecords != null)
/*  458 */           for (int i = 0; i < linkedRecords.length && linkedRecords[i] != 0L; i++)
/*  459 */             freePhysPut(linkedRecords[i], false);  
/*  465 */         physPos = physAllocate(out.pos, false, false);
/*  467 */         logPos = logAllocate(physPos);
/*      */       } finally {
/*  470 */         this.structuralLock.unlock();
/*      */       } 
/*  474 */       walIndexVal((logPos[0] & 0xFFFFFFFFFFFFL) - 1L - 8L - 8L - 1L - 8L, ioRecid, physPos[0] | 0x2L);
/*  475 */       walPhysArray(out, physPos, logPos);
/*  477 */       this.modified.put(ioRecid, logPos);
/*  479 */     } catch (IOException e) {
/*  480 */       throw new IOError(e);
/*      */     } finally {
/*  482 */       lock.unlock();
/*      */     } 
/*  484 */     this.recycledDataOuts.offer(out);
/*  485 */     return true;
/*      */   }
/*      */   
/*      */   public <A> void delete(long recid, Serializer<A> serializer) {
/*  490 */     assert recid > 0L;
/*  491 */     long ioRecid = 32896L + recid * 8L;
/*  492 */     Lock lock = this.locks[Store.lockPos(ioRecid)].writeLock();
/*  493 */     lock.lock();
/*      */     try {
/*  497 */       long logPos, indexVal = 0L;
/*  498 */       long[] linkedRecords = getLinkedRecordsFromLog(ioRecid);
/*  499 */       if (linkedRecords == null) {
/*  500 */         indexVal = this.index.getLong(ioRecid);
/*  501 */         if (indexVal == 4L)
/*      */           return; 
/*  502 */         linkedRecords = getLinkedRecordsIndexVals(indexVal);
/*      */       } 
/*  504 */       this.structuralLock.lock();
/*      */       try {
/*  506 */         checkLogRounding();
/*  507 */         logPos = this.logSize;
/*  509 */         this.logSize += 17L;
/*  510 */         this.log.ensureAvailable(this.logSize);
/*  511 */         longStackPut(120L, ioRecid, false);
/*  514 */         if (indexVal >>> 48L > 0L)
/*  515 */           freePhysPut(indexVal, false); 
/*  518 */         if (linkedRecords != null)
/*  519 */           for (int i = 0; i < linkedRecords.length && linkedRecords[i] != 0L; i++)
/*  520 */             freePhysPut(linkedRecords[i], false);  
/*      */       } finally {
/*  525 */         this.structuralLock.unlock();
/*      */       } 
/*  527 */       walIndexVal(logPos, ioRecid, 2L);
/*  528 */       this.modified.put(ioRecid, TOMBSTONE);
/*      */     } finally {
/*  530 */       lock.unlock();
/*      */     } 
/*      */   }
/*      */   
/*      */   public void commit() {
/*  536 */     lockAllWrite();
/*      */     try {
/*  538 */       if (this.serializerPojo != null && this.serializerPojo.hasUnsavedChanges())
/*  539 */         this.serializerPojo.save(this); 
/*  542 */       if (!logDirty())
/*      */         return; 
/*  547 */       int crc = 0;
/*  548 */       LongMap.LongMapIterator<byte[]> iter = (LongMap.LongMapIterator)this.longStackPages.longMapIterator();
/*  549 */       while (iter.moveToNext()) {
/*  550 */         assert iter.key() >>> 48L == 0L;
/*  551 */         byte[] array = iter.value();
/*  552 */         long pageSize = ((array[0] & 0xFF) << 8 | array[1] & 0xFF);
/*  553 */         assert array.length == pageSize;
/*  554 */         long firstVal = pageSize << 48L | iter.key();
/*  555 */         this.log.ensureAvailable(this.logSize + 1L + 8L + pageSize);
/*  557 */         crc |= LongHashMap.longHash(this.logSize | 0x66L | firstVal);
/*  559 */         this.log.putByte(this.logSize, (byte)102);
/*  560 */         this.logSize++;
/*  561 */         this.log.putLong(this.logSize, firstVal);
/*  562 */         this.logSize += 8L;
/*  565 */         CRC32 crc32 = new CRC32();
/*  566 */         crc32.update(array);
/*  567 */         crc = (int)(crc | crc32.getValue());
/*  568 */         this.log.putData(this.logSize, array, 0, array.length);
/*  569 */         this.logSize += array.length;
/*  571 */         checkLogRounding();
/*      */       } 
/*  575 */       for (int i = 120; i < 32896; i += 8) {
/*  576 */         if (this.indexValsModified[i / 8]) {
/*  577 */           this.log.ensureAvailable(this.logSize + 17L);
/*  578 */           this.logSize += 17L;
/*  579 */           walIndexVal(this.logSize - 17L, i, this.indexVals[i / 8]);
/*      */         } 
/*      */       } 
/*  584 */       this.log.ensureAvailable(this.logSize + 1L + 18L + 8L + 4L);
/*  585 */       long indexChecksum = indexHeaderChecksumUncommited();
/*  586 */       crc |= LongHashMap.longHash(this.logSize | 0x6FL | this.indexSize | this.physSize | this.freeSize | indexChecksum);
/*  587 */       this.log.putByte(this.logSize, (byte)111);
/*  588 */       this.logSize++;
/*  589 */       this.log.putSixLong(this.logSize, this.indexSize);
/*  590 */       this.logSize += 6L;
/*  591 */       this.log.putSixLong(this.logSize, this.physSize);
/*  592 */       this.logSize += 6L;
/*  593 */       this.log.putSixLong(this.logSize, this.freeSize);
/*  594 */       this.logSize += 6L;
/*  595 */       this.log.putLong(this.logSize, indexChecksum);
/*  596 */       this.logSize += 8L;
/*  597 */       this.log.putInt(this.logSize, crc | this.logChecksum.get());
/*  598 */       this.logSize += 4L;
/*  601 */       this.log.putLong(8L, 4566556446554645L);
/*  604 */       if (!this.syncOnCommitDisabled)
/*  604 */         this.log.sync(); 
/*  606 */       replayLogFile();
/*  607 */       reloadIndexFile();
/*      */     } finally {
/*  609 */       unlockAllWrite();
/*      */     } 
/*      */   }
/*      */   
/*      */   protected boolean logDirty() {
/*  616 */     if (this.logSize != 16L || !this.longStackPages.isEmpty() || !this.modified.isEmpty())
/*  617 */       return true; 
/*  619 */     for (boolean b : this.indexValsModified) {
/*  620 */       if (b)
/*  621 */         return true; 
/*      */     } 
/*  624 */     return false;
/*      */   }
/*      */   
/*      */   protected long indexHeaderChecksumUncommited() {
/*  628 */     long ret = 0L;
/*  630 */     for (int offset = 0; offset < 32896; offset += 8) {
/*  631 */       if (offset != 32) {
/*      */         long indexVal;
/*  634 */         if (offset == 8) {
/*  635 */           indexVal = this.indexSize;
/*  636 */         } else if (offset == 16) {
/*  637 */           indexVal = this.physSize;
/*  638 */         } else if (offset == 24) {
/*  639 */           indexVal = this.freeSize;
/*      */         } else {
/*  641 */           indexVal = this.indexVals[offset / 8];
/*      */         } 
/*  643 */         ret |= indexVal | LongHashMap.longHash(indexVal | offset);
/*      */       } 
/*      */     } 
/*  646 */     return ret;
/*      */   }
/*      */   
/*      */   protected boolean verifyLogFile() {
/*  650 */     assert this.structuralLock.isHeldByCurrentThread();
/*  652 */     if (this.readOnly && this.log == null)
/*  653 */       return false; 
/*  655 */     this.logSize = 0L;
/*  660 */     if (this.log.isEmpty() || (this.log.getFile() != null && this.log.getFile().length() < 16L) || this.log.getInt(0L) != 234243482 || this.log.getLong(8L) != 4566556446554645L)
/*  663 */       return false; 
/*  666 */     if (this.log.getUnsignedShort(4L) > 10000)
/*  667 */       throw new IOError(new IOException("New store format version, please use newer MapDB version")); 
/*  670 */     if (this.log.getUnsignedShort(6L) != expectedMasks())
/*  671 */       throw new IllegalArgumentException("Log file created with different features. Please check compression, checksum or encryption"); 
/*      */     try {
/*  674 */       CRC32 crc32 = new CRC32();
/*  677 */       this.logSize = 16L;
/*  678 */       byte ins = this.log.getByte(this.logSize);
/*  679 */       this.logSize++;
/*  680 */       int crc = 0;
/*  682 */       while (ins != 111) {
/*  683 */         if (ins == 101) {
/*  684 */           long ioRecid = this.log.getLong(this.logSize);
/*  685 */           this.logSize += 8L;
/*  686 */           long indexVal = this.log.getLong(this.logSize);
/*  687 */           this.logSize += 8L;
/*  688 */           crc |= LongHashMap.longHash(this.logSize - 1L - 8L - 8L | 0x65L | ioRecid | indexVal);
/*  689 */         } else if (ins == 104) {
/*  690 */           long offset2 = this.log.getLong(this.logSize);
/*  691 */           this.logSize += 8L;
/*  692 */           int size = (int)(offset2 >>> 48L);
/*  694 */           byte[] b = new byte[size];
/*  695 */           this.log.getDataInput(this.logSize, size).readFully(b);
/*  697 */           crc32.reset();
/*  698 */           crc32.update(b);
/*  700 */           crc |= LongHashMap.longHash(this.logSize | 0x68L | offset2 | crc32.getValue());
/*  702 */           this.logSize += size;
/*  703 */         } else if (ins == 103) {
/*  704 */           long offset2 = this.log.getLong(this.logSize);
/*  705 */           this.logSize += 8L;
/*  706 */           int size = (int)(offset2 >>> 48L) - 8;
/*  708 */           long nextPageLink = this.log.getLong(this.logSize);
/*  709 */           this.logSize += 8L;
/*  711 */           byte[] b = new byte[size];
/*  712 */           this.log.getDataInput(this.logSize, size).readFully(b);
/*  713 */           crc32.reset();
/*  714 */           crc32.update(b);
/*  716 */           crc |= LongHashMap.longHash(this.logSize | 0x67L | offset2 | nextPageLink | crc32.getValue());
/*  718 */           this.logSize += size;
/*  719 */         } else if (ins == 102) {
/*  720 */           long offset = this.log.getLong(this.logSize);
/*  721 */           this.logSize += 8L;
/*  722 */           long origLogSize = this.logSize;
/*  723 */           int size = (int)(offset >>> 48L);
/*  725 */           crc |= LongHashMap.longHash(origLogSize | 0x66L | offset);
/*  727 */           byte[] b = new byte[size];
/*  728 */           this.log.getDataInput(this.logSize, size).readFully(b);
/*  729 */           crc32.reset();
/*  730 */           crc32.update(b);
/*  731 */           crc = (int)(crc | crc32.getValue());
/*  733 */           this.log.getDataInput(this.logSize, size).readFully(b);
/*  734 */           this.logSize += size;
/*  735 */         } else if (ins == 105) {
/*  736 */           this.logSize += 1048576L - (this.logSize & 0xFFFFFL);
/*      */         } else {
/*  738 */           return false;
/*      */         } 
/*  741 */         ins = this.log.getByte(this.logSize);
/*  742 */         this.logSize++;
/*      */       } 
/*  745 */       long indexSize = this.log.getSixLong(this.logSize);
/*  746 */       this.logSize += 6L;
/*  747 */       long physSize = this.log.getSixLong(this.logSize);
/*  748 */       this.logSize += 6L;
/*  749 */       long freeSize = this.log.getSixLong(this.logSize);
/*  750 */       this.logSize += 6L;
/*  751 */       long indexSum = this.log.getLong(this.logSize);
/*  752 */       this.logSize += 8L;
/*  753 */       crc |= LongHashMap.longHash(this.logSize - 1L - 18L - 8L | indexSize | physSize | freeSize | indexSum);
/*  755 */       int realCrc = this.log.getInt(this.logSize);
/*  756 */       this.logSize += 4L;
/*  758 */       this.logSize = 0L;
/*  759 */       assert this.structuralLock.isHeldByCurrentThread();
/*  762 */       return true;
/*  763 */     } catch (IOException e) {
/*  766 */       return false;
/*  767 */     } catch (IOError e) {
/*  770 */       return false;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void replayLogFile() {
/*  777 */     assert this.structuralLock.isHeldByCurrentThread();
/*  779 */     if (this.readOnly && this.log == null)
/*      */       return; 
/*  782 */     this.logSize = 0L;
/*  786 */     if (this.log.isEmpty() || this.log.getInt(0L) != 234243482 || this.log.getUnsignedShort(4L) > 10000 || this.log.getLong(8L) != 4566556446554645L || this.log.getUnsignedShort(6L) != expectedMasks()) {
/*  790 */       logReset();
/*      */       return;
/*      */     } 
/*  796 */     this.logSize = 16L;
/*  797 */     byte ins = this.log.getByte(this.logSize);
/*  798 */     this.logSize++;
/*  800 */     while (ins != 111) {
/*  801 */       if (ins == 101) {
/*  802 */         long ioRecid = this.log.getLong(this.logSize);
/*  803 */         this.logSize += 8L;
/*  804 */         long indexVal = this.log.getLong(this.logSize);
/*  805 */         this.logSize += 8L;
/*  806 */         this.index.ensureAvailable(ioRecid + 8L);
/*  807 */         this.index.putLong(ioRecid, indexVal);
/*  808 */       } else if (ins == 104 || ins == 102 || ins == 103) {
/*  809 */         long offset = this.log.getLong(this.logSize);
/*  810 */         this.logSize += 8L;
/*  811 */         int size = (int)(offset >>> 48L);
/*  812 */         offset &= 0xFFFFFFFFFFF0L;
/*  815 */         DataInput2 input = (DataInput2)this.log.getDataInput(this.logSize, size);
/*  816 */         ByteBuffer buf = input.buf.duplicate();
/*  818 */         buf.position(input.pos);
/*  819 */         buf.limit(input.pos + size);
/*  820 */         this.phys.ensureAvailable(offset + size);
/*  821 */         this.phys.putData(offset, buf);
/*  823 */         this.logSize += size;
/*  824 */       } else if (ins == 105) {
/*  825 */         this.logSize += 1048576L - (this.logSize & 0xFFFFFL);
/*      */       } else {
/*  827 */         throw new AssertionError("unknown trans log instruction '" + ins + "' at log offset: " + (this.logSize - 1L));
/*      */       } 
/*  830 */       ins = this.log.getByte(this.logSize);
/*  831 */       this.logSize++;
/*      */     } 
/*  833 */     this.index.putLong(8L, this.log.getSixLong(this.logSize));
/*  834 */     this.logSize += 6L;
/*  835 */     this.index.putLong(16L, this.log.getSixLong(this.logSize));
/*  836 */     this.logSize += 6L;
/*  837 */     this.index.putLong(24L, this.log.getSixLong(this.logSize));
/*  838 */     this.logSize += 6L;
/*  839 */     this.index.putLong(32L, this.log.getLong(this.logSize));
/*  840 */     this.logSize += 8L;
/*  845 */     if (!this.syncOnCommitDisabled) {
/*  846 */       this.phys.sync();
/*  847 */       this.index.sync();
/*      */     } 
/*  850 */     logReset();
/*  852 */     assert this.structuralLock.isHeldByCurrentThread();
/*      */   }
/*      */   
/*      */   public void rollback() throws UnsupportedOperationException {
/*  859 */     lockAllWrite();
/*      */     try {
/*  862 */       logReset();
/*  864 */       reloadIndexFile();
/*      */     } finally {
/*  866 */       unlockAllWrite();
/*      */     } 
/*      */   }
/*      */   
/*      */   protected long[] getLinkedRecordsFromLog(long ioRecid) {
/*  871 */     assert this.locks[Store.lockPos(ioRecid)].writeLock().isHeldByCurrentThread();
/*  872 */     long[] ret0 = this.modified.get(ioRecid);
/*  873 */     if (ret0 == PREALLOC)
/*  873 */       return ret0; 
/*  875 */     if (ret0 != null && ret0 != TOMBSTONE) {
/*  876 */       long[] ret = new long[ret0.length];
/*  877 */       for (int i = 0; i < ret0.length; i++) {
/*  878 */         long offset = ret0[i] & 0xFFFFFFFFFFFFL;
/*  880 */         ret[i] = this.log.getLong(offset - 8L);
/*      */       } 
/*  882 */       return ret;
/*      */     } 
/*  884 */     return null;
/*      */   }
/*      */   
/*      */   protected long longStackTake(long ioList, boolean recursive) {
/*  889 */     assert this.structuralLock.isHeldByCurrentThread();
/*  890 */     assert ioList >= 120L && ioList < 32896L : "wrong ioList: " + ioList;
/*  893 */     long dataOffset = this.indexVals[(int)ioList / 8];
/*  894 */     if (dataOffset == 0L)
/*  895 */       return 0L; 
/*  897 */     long pos = dataOffset >>> 48L;
/*  898 */     dataOffset &= 0xFFFFFFFFFFF0L;
/*  899 */     byte[] page = longStackGetPage(dataOffset);
/*  901 */     if (pos < 8L)
/*  901 */       throw new AssertionError(); 
/*  903 */     long ret = longStackGetSixLong(page, (int)pos);
/*  906 */     if (pos == 8L) {
/*  908 */       long next = longStackGetSixLong(page, 2);
/*  909 */       long size = ((page[0] & 0xFF) << 8 | page[1] & 0xFF);
/*  910 */       assert size == page.length;
/*  911 */       if (next != 0L) {
/*  913 */         byte[] nextPage = longStackGetPage(next);
/*  914 */         long nextSize = ((nextPage[0] & 0xFF) << 8 | nextPage[1] & 0xFF);
/*  915 */         assert (nextSize - 8L) % 6L == 0L;
/*  916 */         this.indexVals[(int)ioList / 8] = nextSize - 6L << 48L | next;
/*  917 */         this.indexValsModified[(int)ioList / 8] = true;
/*      */       } else {
/*  920 */         this.indexVals[(int)ioList / 8] = 0L;
/*  921 */         this.indexValsModified[(int)ioList / 8] = true;
/*  922 */         if (this.maxUsedIoList == ioList)
/*  924 */           while (this.indexVals[(int)this.maxUsedIoList / 8] == 0L && this.maxUsedIoList > 120L)
/*  925 */             this.maxUsedIoList -= 8L;  
/*      */       } 
/*  930 */       freePhysPut(size << 48L | dataOffset, true);
/*  931 */       assert dataOffset >>> 48L == 0L;
/*  932 */       this.longStackPages.remove(dataOffset);
/*      */     } else {
/*  935 */       pos -= 6L;
/*  936 */       this.indexVals[(int)ioList / 8] = pos << 48L | dataOffset;
/*  937 */       this.indexValsModified[(int)ioList / 8] = true;
/*      */     } 
/*  942 */     return ret;
/*      */   }
/*      */   
/*      */   protected void longStackPut(long ioList, long offset, boolean recursive) {
/*  948 */     assert this.structuralLock.isHeldByCurrentThread();
/*  949 */     assert offset >>> 48L == 0L;
/*  950 */     assert ioList >= 120L && ioList <= 32896L : "wrong ioList: " + ioList;
/*  952 */     long dataOffset = this.indexVals[(int)ioList / 8];
/*  953 */     long pos = dataOffset >>> 48L;
/*  954 */     dataOffset &= 0xFFFFFFFFFFF0L;
/*  956 */     if (dataOffset == 0L) {
/*  958 */       long listPhysid = freePhysTake(1232, true, true) & 0xFFFFFFFFFFF0L;
/*  959 */       if (listPhysid == 0L)
/*  959 */         throw new AssertionError(); 
/*  960 */       assert listPhysid >>> 48L == 0L;
/*  963 */       byte[] page = new byte[1232];
/*  964 */       page[0] = (byte)(0xFF & page.length >>> 8);
/*  965 */       page[1] = (byte)(0xFF & page.length);
/*  966 */       longStackPutSixLong(page, 2, 0L);
/*  968 */       longStackPutSixLong(page, 8, offset);
/*  970 */       this.indexVals[(int)ioList / 8] = 0x8000000000000L | listPhysid;
/*  971 */       this.indexValsModified[(int)ioList / 8] = true;
/*  972 */       if (this.maxUsedIoList <= ioList)
/*  972 */         this.maxUsedIoList = ioList; 
/*  973 */       this.longStackPages.put(listPhysid, page);
/*      */     } else {
/*  975 */       byte[] page = longStackGetPage(dataOffset);
/*  976 */       long size = ((page[0] & 0xFF) << 8 | page[1] & 0xFF);
/*  978 */       assert pos + 6L <= size;
/*  979 */       if (pos + 6L == size) {
/*  980 */         long newPageSize = 1232L;
/*  981 */         if (ioList == size2ListIoRecid(1232L))
/*  983 */           newPageSize = 1280L; 
/*  986 */         long listPhysid = freePhysTake((int)newPageSize, true, true) & 0xFFFFFFFFFFF0L;
/*  987 */         if (listPhysid == 0L)
/*  987 */           throw new AssertionError(); 
/*  989 */         byte[] newPage = new byte[(int)newPageSize];
/*  992 */         newPage[0] = (byte)(int)(0xFFL & newPageSize >>> 8L);
/*  993 */         newPage[1] = (byte)(int)(0xFFL & newPageSize);
/*  995 */         longStackPutSixLong(newPage, 2, dataOffset & 0xFFFFFFFFFFF0L);
/*  999 */         longStackPutSixLong(newPage, 8, offset);
/* 1000 */         assert listPhysid >>> 48L == 0L;
/* 1001 */         this.longStackPages.put(listPhysid, newPage);
/* 1004 */         this.indexVals[(int)ioList / 8] = 0x8000000000000L | listPhysid;
/* 1005 */         this.indexValsModified[(int)ioList / 8] = true;
/*      */       } else {
/* 1008 */         pos += 6L;
/* 1009 */         longStackPutSixLong(page, (int)pos, offset);
/* 1010 */         this.indexVals[(int)ioList / 8] = pos << 48L | dataOffset;
/* 1011 */         this.indexValsModified[(int)ioList / 8] = true;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected static long longStackGetSixLong(byte[] page, int pos) {
/* 1017 */     return (page[pos + 0] & 0xFF) << 40L | (page[pos + 1] & 0xFF) << 32L | (page[pos + 2] & 0xFF) << 24L | (page[pos + 3] & 0xFF) << 16L | (page[pos + 4] & 0xFF) << 8L | (page[pos + 5] & 0xFF) << 0L;
/*      */   }
/*      */   
/*      */   protected static void longStackPutSixLong(byte[] page, int pos, long value) {
/* 1028 */     assert value >= 0L && value >>> 48L == 0L : "value does not fit";
/* 1029 */     page[pos + 0] = (byte)(int)(0xFFL & value >> 40L);
/* 1030 */     page[pos + 1] = (byte)(int)(0xFFL & value >> 32L);
/* 1031 */     page[pos + 2] = (byte)(int)(0xFFL & value >> 24L);
/* 1032 */     page[pos + 3] = (byte)(int)(0xFFL & value >> 16L);
/* 1033 */     page[pos + 4] = (byte)(int)(0xFFL & value >> 8L);
/* 1034 */     page[pos + 5] = (byte)(int)(0xFFL & value >> 0L);
/*      */   }
/*      */   
/*      */   protected byte[] longStackGetPage(long offset) {
/* 1040 */     assert offset >= 16L;
/* 1041 */     assert offset >>> 48L == 0L;
/* 1043 */     byte[] ret = this.longStackPages.get(offset);
/* 1044 */     if (ret == null) {
/* 1046 */       int size = this.phys.getUnsignedShort(offset);
/* 1047 */       assert size >= 14;
/* 1048 */       ret = new byte[size];
/*      */       try {
/* 1050 */         this.phys.getDataInput(offset, size).readFully(ret);
/* 1051 */       } catch (IOException e) {
/* 1052 */         throw new IOError(e);
/*      */       } 
/* 1056 */       this.longStackPages.put(offset, ret);
/*      */     } 
/* 1059 */     return ret;
/*      */   }
/*      */   
/*      */   public void close() {
/* 1064 */     for (Runnable closeListener : this.closeListeners)
/* 1065 */       closeListener.run(); 
/* 1067 */     if (this.serializerPojo != null && this.serializerPojo.hasUnsavedChanges())
/* 1068 */       this.serializerPojo.save(this); 
/* 1071 */     lockAllWrite();
/*      */     try {
/* 1073 */       if (this.log != null) {
/* 1074 */         this.log.sync();
/* 1075 */         this.log.close();
/* 1076 */         if (this.deleteFilesAfterClose)
/* 1077 */           this.log.deleteFile(); 
/*      */       } 
/* 1081 */       this.index.sync();
/* 1082 */       this.phys.sync();
/* 1084 */       this.index.close();
/* 1085 */       this.phys.close();
/* 1086 */       if (this.deleteFilesAfterClose) {
/* 1087 */         this.index.deleteFile();
/* 1088 */         this.phys.deleteFile();
/*      */       } 
/* 1090 */       this.index = null;
/* 1091 */       this.phys = null;
/*      */     } finally {
/* 1093 */       unlockAllWrite();
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void compactPreUnderLock() {
/* 1098 */     assert this.structuralLock.isLocked();
/* 1099 */     if (logDirty())
/* 1100 */       throw new IllegalAccessError("WAL not empty; commit first, than compact"); 
/*      */   }
/*      */   
/*      */   protected void compactPostUnderLock() {
/* 1104 */     assert this.structuralLock.isLocked();
/* 1105 */     reloadIndexFile();
/*      */   }
/*      */   
/*      */   public boolean canRollback() {
/* 1111 */     return true;
/*      */   }
/*      */   
/*      */   protected void logChecksumAdd(int cs) {
/*      */     int old;
/*      */     do {
/* 1116 */       old = this.logChecksum.get();
/* 1117 */     } while (!this.logChecksum.compareAndSet(old, old | cs));
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\mapdb\StoreWAL.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */