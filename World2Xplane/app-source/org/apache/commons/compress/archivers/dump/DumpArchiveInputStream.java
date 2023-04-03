/*     */ package org.apache.commons.compress.archivers.dump;
/*     */ 
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.PriorityQueue;
/*     */ import java.util.Queue;
/*     */ import java.util.Stack;
/*     */ import org.apache.commons.compress.archivers.ArchiveEntry;
/*     */ import org.apache.commons.compress.archivers.ArchiveException;
/*     */ import org.apache.commons.compress.archivers.ArchiveInputStream;
/*     */ 
/*     */ public class DumpArchiveInputStream extends ArchiveInputStream {
/*     */   private DumpArchiveSummary summary;
/*     */   
/*     */   private DumpArchiveEntry active;
/*     */   
/*     */   private boolean isClosed;
/*     */   
/*     */   private boolean hasHitEOF;
/*     */   
/*     */   private long entrySize;
/*     */   
/*     */   private long entryOffset;
/*     */   
/*     */   private int readIdx;
/*     */   
/*  52 */   private byte[] readBuf = new byte[1024];
/*     */   
/*     */   private byte[] blockBuffer;
/*     */   
/*     */   private int recordOffset;
/*     */   
/*     */   private long filepos;
/*     */   
/*     */   protected TapeInputStream raw;
/*     */   
/*  59 */   private Map<Integer, Dirent> names = new HashMap<Integer, Dirent>();
/*     */   
/*  62 */   private Map<Integer, DumpArchiveEntry> pending = new HashMap<Integer, DumpArchiveEntry>();
/*     */   
/*     */   private Queue<DumpArchiveEntry> queue;
/*     */   
/*     */   public DumpArchiveInputStream(InputStream is) throws ArchiveException {
/*  74 */     this.raw = new TapeInputStream(is);
/*  75 */     this.hasHitEOF = false;
/*     */     try {
/*  79 */       byte[] headerBytes = this.raw.readRecord();
/*  81 */       if (!DumpArchiveUtil.verify(headerBytes))
/*  82 */         throw new UnrecognizedFormatException(); 
/*  86 */       this.summary = new DumpArchiveSummary(headerBytes);
/*  89 */       this.raw.resetBlockSize(this.summary.getNTRec(), this.summary.isCompressed());
/*  92 */       this.blockBuffer = new byte[4096];
/*  95 */       readCLRI();
/*  96 */       readBITS();
/*  97 */     } catch (IOException ex) {
/*  98 */       throw new ArchiveException(ex.getMessage(), ex);
/*     */     } 
/* 102 */     Dirent root = new Dirent(2, 2, 4, ".");
/* 103 */     this.names.put(Integer.valueOf(2), root);
/* 107 */     this.queue = new PriorityQueue<DumpArchiveEntry>(10, new Comparator<DumpArchiveEntry>() {
/*     */           public int compare(DumpArchiveEntry p, DumpArchiveEntry q) {
/* 110 */             if (p.getOriginalName() == null || q.getOriginalName() == null)
/* 111 */               return Integer.MAX_VALUE; 
/* 114 */             return p.getOriginalName().compareTo(q.getOriginalName());
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public int getCount() {
/* 122 */     return (int)getBytesRead();
/*     */   }
/*     */   
/*     */   public long getBytesRead() {
/* 127 */     return this.raw.getBytesRead();
/*     */   }
/*     */   
/*     */   public DumpArchiveSummary getSummary() {
/* 134 */     return this.summary;
/*     */   }
/*     */   
/*     */   private void readCLRI() throws IOException {
/* 141 */     byte[] readBuf = this.raw.readRecord();
/* 143 */     if (!DumpArchiveUtil.verify(readBuf))
/* 144 */       throw new InvalidFormatException(); 
/* 147 */     this.active = DumpArchiveEntry.parse(readBuf);
/* 149 */     if (DumpArchiveConstants.SEGMENT_TYPE.CLRI != this.active.getHeaderType())
/* 150 */       throw new InvalidFormatException(); 
/* 154 */     if (this.raw.skip((1024 * this.active.getHeaderCount())) == -1L)
/* 156 */       throw new EOFException(); 
/* 158 */     this.readIdx = this.active.getHeaderCount();
/*     */   }
/*     */   
/*     */   private void readBITS() throws IOException {
/* 165 */     byte[] readBuf = this.raw.readRecord();
/* 167 */     if (!DumpArchiveUtil.verify(readBuf))
/* 168 */       throw new InvalidFormatException(); 
/* 171 */     this.active = DumpArchiveEntry.parse(readBuf);
/* 173 */     if (DumpArchiveConstants.SEGMENT_TYPE.BITS != this.active.getHeaderType())
/* 174 */       throw new InvalidFormatException(); 
/* 178 */     if (this.raw.skip((1024 * this.active.getHeaderCount())) == -1L)
/* 180 */       throw new EOFException(); 
/* 182 */     this.readIdx = this.active.getHeaderCount();
/*     */   }
/*     */   
/*     */   public DumpArchiveEntry getNextDumpEntry() throws IOException {
/* 189 */     return getNextEntry();
/*     */   }
/*     */   
/*     */   public DumpArchiveEntry getNextEntry() throws IOException {
/* 197 */     DumpArchiveEntry entry = null;
/* 198 */     String path = null;
/* 201 */     if (!this.queue.isEmpty())
/* 202 */       return this.queue.remove(); 
/* 205 */     while (entry == null) {
/* 206 */       if (this.hasHitEOF)
/* 207 */         return null; 
/* 214 */       while (this.readIdx < this.active.getHeaderCount()) {
/* 215 */         if (!this.active.isSparseRecord(this.readIdx++) && this.raw.skip(1024L) == -1L)
/* 217 */           throw new EOFException(); 
/*     */       } 
/* 221 */       this.readIdx = 0;
/* 222 */       this.filepos = this.raw.getBytesRead();
/* 224 */       byte[] headerBytes = this.raw.readRecord();
/* 226 */       if (!DumpArchiveUtil.verify(headerBytes))
/* 227 */         throw new InvalidFormatException(); 
/* 230 */       this.active = DumpArchiveEntry.parse(headerBytes);
/* 233 */       while (DumpArchiveConstants.SEGMENT_TYPE.ADDR == this.active.getHeaderType()) {
/* 234 */         if (this.raw.skip((1024 * (this.active.getHeaderCount() - this.active.getHeaderHoles()))) == -1L)
/* 237 */           throw new EOFException(); 
/* 240 */         this.filepos = this.raw.getBytesRead();
/* 241 */         headerBytes = this.raw.readRecord();
/* 243 */         if (!DumpArchiveUtil.verify(headerBytes))
/* 244 */           throw new InvalidFormatException(); 
/* 247 */         this.active = DumpArchiveEntry.parse(headerBytes);
/*     */       } 
/* 251 */       if (DumpArchiveConstants.SEGMENT_TYPE.END == this.active.getHeaderType()) {
/* 252 */         this.hasHitEOF = true;
/* 253 */         this.isClosed = true;
/* 254 */         this.raw.close();
/* 256 */         return null;
/*     */       } 
/* 259 */       entry = this.active;
/* 261 */       if (entry.isDirectory()) {
/* 262 */         readDirectoryEntry(this.active);
/* 265 */         this.entryOffset = 0L;
/* 266 */         this.entrySize = 0L;
/* 267 */         this.readIdx = this.active.getHeaderCount();
/*     */       } else {
/* 269 */         this.entryOffset = 0L;
/* 270 */         this.entrySize = this.active.getEntrySize();
/* 271 */         this.readIdx = 0;
/*     */       } 
/* 274 */       this.recordOffset = this.readBuf.length;
/* 276 */       path = getPath(entry);
/* 278 */       if (path == null)
/* 279 */         entry = null; 
/*     */     } 
/* 283 */     entry.setName(path);
/* 284 */     entry.setSimpleName(((Dirent)this.names.get(Integer.valueOf(entry.getIno()))).getName());
/* 285 */     entry.setOffset(this.filepos);
/* 287 */     return entry;
/*     */   }
/*     */   
/*     */   private void readDirectoryEntry(DumpArchiveEntry entry) throws IOException {
/* 295 */     long size = entry.getEntrySize();
/* 296 */     boolean first = true;
/* 298 */     while (first || DumpArchiveConstants.SEGMENT_TYPE.ADDR == entry.getHeaderType()) {
/* 301 */       if (!first)
/* 302 */         this.raw.readRecord(); 
/* 305 */       if (!this.names.containsKey(Integer.valueOf(entry.getIno())) && DumpArchiveConstants.SEGMENT_TYPE.INODE == entry.getHeaderType())
/* 307 */         this.pending.put(Integer.valueOf(entry.getIno()), entry); 
/* 310 */       int datalen = 1024 * entry.getHeaderCount();
/* 312 */       if (this.blockBuffer.length < datalen)
/* 313 */         this.blockBuffer = new byte[datalen]; 
/* 316 */       if (this.raw.read(this.blockBuffer, 0, datalen) != datalen)
/* 317 */         throw new EOFException(); 
/* 320 */       int reclen = 0;
/*     */       int i;
/* 322 */       for (i = 0; i < datalen - 8 && i < size - 8L; 
/* 323 */         i += reclen) {
/* 324 */         int ino = DumpArchiveUtil.convert32(this.blockBuffer, i);
/* 325 */         reclen = DumpArchiveUtil.convert16(this.blockBuffer, i + 4);
/* 327 */         byte type = this.blockBuffer[i + 6];
/* 329 */         String name = new String(this.blockBuffer, i + 8, this.blockBuffer[i + 7]);
/* 331 */         if (!".".equals(name) && !"..".equals(name)) {
/* 336 */           Dirent d = new Dirent(ino, entry.getIno(), type, name);
/* 345 */           this.names.put(Integer.valueOf(ino), d);
/* 348 */           for (Map.Entry<Integer, DumpArchiveEntry> e : this.pending.entrySet()) {
/* 349 */             String path = getPath(e.getValue());
/* 351 */             if (path != null) {
/* 352 */               ((DumpArchiveEntry)e.getValue()).setName(path);
/* 353 */               ((DumpArchiveEntry)e.getValue()).setSimpleName(((Dirent)this.names.get(e.getKey())).getName());
/* 355 */               this.queue.add(e.getValue());
/*     */             } 
/*     */           } 
/* 361 */           for (DumpArchiveEntry e : this.queue)
/* 362 */             this.pending.remove(Integer.valueOf(e.getIno())); 
/*     */         } 
/*     */       } 
/* 366 */       byte[] peekBytes = this.raw.peek();
/* 368 */       if (!DumpArchiveUtil.verify(peekBytes))
/* 369 */         throw new InvalidFormatException(); 
/* 372 */       entry = DumpArchiveEntry.parse(peekBytes);
/* 373 */       first = false;
/* 374 */       size -= 1024L;
/*     */     } 
/*     */   }
/*     */   
/*     */   private String getPath(DumpArchiveEntry entry) {
/* 387 */     Stack<String> elements = new Stack<String>();
/* 388 */     Dirent dirent = null;
/*     */     int i;
/* 390 */     for (i = entry.getIno();; i = dirent.getParentIno()) {
/* 391 */       if (!this.names.containsKey(Integer.valueOf(i))) {
/* 392 */         elements.clear();
/*     */         break;
/*     */       } 
/* 396 */       dirent = this.names.get(Integer.valueOf(i));
/* 397 */       elements.push(dirent.getName());
/* 399 */       if (dirent.getIno() == dirent.getParentIno())
/*     */         break; 
/*     */     } 
/* 405 */     if (elements.isEmpty()) {
/* 406 */       this.pending.put(Integer.valueOf(entry.getIno()), entry);
/* 408 */       return null;
/*     */     } 
/* 412 */     StringBuilder sb = new StringBuilder(elements.pop());
/* 414 */     while (!elements.isEmpty()) {
/* 415 */       sb.append('/');
/* 416 */       sb.append(elements.pop());
/*     */     } 
/* 419 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public int read(byte[] buf, int off, int len) throws IOException {
/* 437 */     int totalRead = 0;
/* 439 */     if (this.isClosed || this.entryOffset >= this.entrySize)
/* 440 */       return -1; 
/* 443 */     if (len + this.entryOffset > this.entrySize)
/* 444 */       len = (int)(this.entrySize - this.entryOffset); 
/* 447 */     while (len > 0) {
/* 448 */       int sz = (len > this.readBuf.length - this.recordOffset) ? (this.readBuf.length - this.recordOffset) : len;
/* 452 */       if (this.recordOffset + sz <= this.readBuf.length) {
/* 453 */         System.arraycopy(this.readBuf, this.recordOffset, buf, off, sz);
/* 454 */         totalRead += sz;
/* 455 */         this.recordOffset += sz;
/* 456 */         len -= sz;
/* 457 */         off += sz;
/*     */       } 
/* 461 */       if (len > 0) {
/* 462 */         if (this.readIdx >= 512) {
/* 463 */           byte[] headerBytes = this.raw.readRecord();
/* 465 */           if (!DumpArchiveUtil.verify(headerBytes))
/* 466 */             throw new InvalidFormatException(); 
/* 469 */           this.active = DumpArchiveEntry.parse(headerBytes);
/* 470 */           this.readIdx = 0;
/*     */         } 
/* 473 */         if (!this.active.isSparseRecord(this.readIdx++)) {
/* 474 */           int r = this.raw.read(this.readBuf, 0, this.readBuf.length);
/* 475 */           if (r != this.readBuf.length)
/* 476 */             throw new EOFException(); 
/*     */         } else {
/* 479 */           Arrays.fill(this.readBuf, (byte)0);
/*     */         } 
/* 482 */         this.recordOffset = 0;
/*     */       } 
/*     */     } 
/* 486 */     this.entryOffset += totalRead;
/* 488 */     return totalRead;
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 496 */     if (!this.isClosed) {
/* 497 */       this.isClosed = true;
/* 498 */       this.raw.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean matches(byte[] buffer, int length) {
/* 509 */     if (length < 32)
/* 510 */       return false; 
/* 514 */     if (length >= 1024)
/* 515 */       return DumpArchiveUtil.verify(buffer); 
/* 519 */     return (60012 == DumpArchiveUtil.convert32(buffer, 24));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\archivers\dump\DumpArchiveInputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */