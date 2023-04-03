/*     */ package org.apache.commons.compress.archivers.tar;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.compress.archivers.ArchiveEntry;
/*     */ import org.apache.commons.compress.archivers.ArchiveInputStream;
/*     */ import org.apache.commons.compress.archivers.zip.ZipEncoding;
/*     */ import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
/*     */ import org.apache.commons.compress.utils.ArchiveUtils;
/*     */ 
/*     */ public class TarArchiveInputStream extends ArchiveInputStream {
/*     */   private static final int SMALL_BUFFER_SIZE = 256;
/*     */   
/*     */   private static final int BUFFER_SIZE = 8192;
/*     */   
/*     */   private boolean hasHitEOF;
/*     */   
/*     */   private long entrySize;
/*     */   
/*     */   private long entryOffset;
/*     */   
/*     */   private byte[] readBuf;
/*     */   
/*     */   protected final TarBuffer buffer;
/*     */   
/*     */   private TarArchiveEntry currEntry;
/*     */   
/*     */   private final ZipEncoding encoding;
/*     */   
/*     */   public TarArchiveInputStream(InputStream is) {
/*  64 */     this(is, 10240, 512);
/*     */   }
/*     */   
/*     */   public TarArchiveInputStream(InputStream is, String encoding) {
/*  74 */     this(is, 10240, 512, encoding);
/*     */   }
/*     */   
/*     */   public TarArchiveInputStream(InputStream is, int blockSize) {
/*  83 */     this(is, blockSize, 512);
/*     */   }
/*     */   
/*     */   public TarArchiveInputStream(InputStream is, int blockSize, String encoding) {
/*  95 */     this(is, blockSize, 512, encoding);
/*     */   }
/*     */   
/*     */   public TarArchiveInputStream(InputStream is, int blockSize, int recordSize) {
/* 105 */     this(is, blockSize, recordSize, (String)null);
/*     */   }
/*     */   
/*     */   public TarArchiveInputStream(InputStream is, int blockSize, int recordSize, String encoding) {
/* 118 */     this.buffer = new TarBuffer(is, blockSize, recordSize);
/* 119 */     this.readBuf = null;
/* 120 */     this.hasHitEOF = false;
/* 121 */     this.encoding = ZipEncodingHelper.getZipEncoding(encoding);
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 130 */     this.buffer.close();
/*     */   }
/*     */   
/*     */   public int getRecordSize() {
/* 139 */     return this.buffer.getRecordSize();
/*     */   }
/*     */   
/*     */   public int available() throws IOException {
/* 156 */     if (this.entrySize - this.entryOffset > 2147483647L)
/* 157 */       return Integer.MAX_VALUE; 
/* 159 */     return (int)(this.entrySize - this.entryOffset);
/*     */   }
/*     */   
/*     */   public long skip(long numToSkip) throws IOException {
/* 178 */     byte[] skipBuf = new byte[8192];
/* 179 */     long skip = numToSkip;
/* 180 */     while (skip > 0L) {
/* 181 */       int realSkip = (int)((skip > skipBuf.length) ? skipBuf.length : skip);
/* 182 */       int numRead = read(skipBuf, 0, realSkip);
/* 183 */       if (numRead == -1)
/*     */         break; 
/* 186 */       skip -= numRead;
/*     */     } 
/* 188 */     return numToSkip - skip;
/*     */   }
/*     */   
/*     */   public synchronized void reset() {}
/*     */   
/*     */   public TarArchiveEntry getNextTarEntry() throws IOException {
/* 212 */     if (this.hasHitEOF)
/* 213 */       return null; 
/* 216 */     if (this.currEntry != null) {
/* 217 */       long numToSkip = this.entrySize - this.entryOffset;
/* 219 */       while (numToSkip > 0L) {
/* 220 */         long skipped = skip(numToSkip);
/* 221 */         if (skipped <= 0L)
/* 222 */           throw new RuntimeException("failed to skip current tar entry"); 
/* 224 */         numToSkip -= skipped;
/*     */       } 
/* 227 */       this.readBuf = null;
/*     */     } 
/* 230 */     byte[] headerBuf = getRecord();
/* 232 */     if (this.hasHitEOF) {
/* 233 */       this.currEntry = null;
/* 234 */       return null;
/*     */     } 
/*     */     try {
/* 238 */       this.currEntry = new TarArchiveEntry(headerBuf, this.encoding);
/* 239 */     } catch (IllegalArgumentException e) {
/* 240 */       IOException ioe = new IOException("Error detected parsing the header");
/* 241 */       ioe.initCause(e);
/* 242 */       throw ioe;
/*     */     } 
/* 244 */     this.entryOffset = 0L;
/* 245 */     this.entrySize = this.currEntry.getSize();
/* 247 */     if (this.currEntry.isGNULongNameEntry()) {
/* 249 */       StringBuffer longName = new StringBuffer();
/* 250 */       byte[] buf = new byte[256];
/* 251 */       int length = 0;
/* 252 */       while ((length = read(buf)) >= 0)
/* 253 */         longName.append(new String(buf, 0, length)); 
/* 255 */       getNextEntry();
/* 256 */       if (this.currEntry == null)
/* 259 */         return null; 
/* 262 */       if (longName.length() > 0 && longName.charAt(longName.length() - 1) == '\000')
/* 264 */         longName.deleteCharAt(longName.length() - 1); 
/* 266 */       this.currEntry.setName(longName.toString());
/*     */     } 
/* 269 */     if (this.currEntry.isPaxHeader())
/* 270 */       paxHeaders(); 
/* 273 */     if (this.currEntry.isGNUSparse())
/* 274 */       readGNUSparse(); 
/* 281 */     this.entrySize = this.currEntry.getSize();
/* 282 */     return this.currEntry;
/*     */   }
/*     */   
/*     */   private byte[] getRecord() throws IOException {
/* 298 */     if (this.hasHitEOF)
/* 299 */       return null; 
/* 302 */     byte[] headerBuf = this.buffer.readRecord();
/* 304 */     if (headerBuf == null) {
/* 305 */       this.hasHitEOF = true;
/* 306 */     } else if (this.buffer.isEOFRecord(headerBuf)) {
/* 307 */       this.hasHitEOF = true;
/*     */     } 
/* 310 */     return this.hasHitEOF ? null : headerBuf;
/*     */   }
/*     */   
/*     */   private void paxHeaders() throws IOException {
/* 314 */     Map<String, String> headers = parsePaxHeaders((InputStream)this);
/* 315 */     getNextEntry();
/* 316 */     applyPaxHeadersToCurrentEntry(headers);
/*     */   }
/*     */   
/*     */   Map<String, String> parsePaxHeaders(InputStream i) throws IOException {
/*     */     int ch;
/* 320 */     Map<String, String> headers = new HashMap<String, String>();
/*     */     do {
/* 324 */       int len = 0;
/* 325 */       int read = 0;
/* 326 */       while ((ch = i.read()) != -1) {
/* 327 */         read++;
/* 328 */         if (ch == 32) {
/* 330 */           ByteArrayOutputStream coll = new ByteArrayOutputStream();
/* 331 */           while ((ch = i.read()) != -1) {
/* 332 */             read++;
/* 333 */             if (ch == 61) {
/* 334 */               String keyword = coll.toString("UTF-8");
/* 336 */               byte[] rest = new byte[len - read];
/* 337 */               int got = i.read(rest);
/* 338 */               if (got != len - read)
/* 339 */                 throw new IOException("Failed to read Paxheader. Expected " + (len - read) + " bytes, read " + got); 
/* 346 */               String value = new String(rest, 0, len - read - 1, "UTF-8");
/* 348 */               headers.put(keyword, value);
/*     */               break;
/*     */             } 
/* 351 */             coll.write((byte)ch);
/*     */           } 
/*     */           break;
/*     */         } 
/* 355 */         len *= 10;
/* 356 */         len += ch - 48;
/*     */       } 
/* 358 */     } while (ch != -1);
/* 362 */     return headers;
/*     */   }
/*     */   
/*     */   private void applyPaxHeadersToCurrentEntry(Map<String, String> headers) {
/* 377 */     for (Map.Entry<String, String> ent : headers.entrySet()) {
/* 378 */       String key = ent.getKey();
/* 379 */       String val = ent.getValue();
/* 380 */       if ("path".equals(key)) {
/* 381 */         this.currEntry.setName(val);
/*     */         continue;
/*     */       } 
/* 382 */       if ("linkpath".equals(key)) {
/* 383 */         this.currEntry.setLinkName(val);
/*     */         continue;
/*     */       } 
/* 384 */       if ("gid".equals(key)) {
/* 385 */         this.currEntry.setGroupId(Integer.parseInt(val));
/*     */         continue;
/*     */       } 
/* 386 */       if ("gname".equals(key)) {
/* 387 */         this.currEntry.setGroupName(val);
/*     */         continue;
/*     */       } 
/* 388 */       if ("uid".equals(key)) {
/* 389 */         this.currEntry.setUserId(Integer.parseInt(val));
/*     */         continue;
/*     */       } 
/* 390 */       if ("uname".equals(key)) {
/* 391 */         this.currEntry.setUserName(val);
/*     */         continue;
/*     */       } 
/* 392 */       if ("size".equals(key)) {
/* 393 */         this.currEntry.setSize(Long.parseLong(val));
/*     */         continue;
/*     */       } 
/* 394 */       if ("mtime".equals(key)) {
/* 395 */         this.currEntry.setModTime((long)(Double.parseDouble(val) * 1000.0D));
/*     */         continue;
/*     */       } 
/* 396 */       if ("SCHILY.devminor".equals(key)) {
/* 397 */         this.currEntry.setDevMinor(Integer.parseInt(val));
/*     */         continue;
/*     */       } 
/* 398 */       if ("SCHILY.devmajor".equals(key))
/* 399 */         this.currEntry.setDevMajor(Integer.parseInt(val)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void readGNUSparse() throws IOException {
/* 417 */     if (this.currEntry.isExtended()) {
/*     */       TarArchiveSparseEntry entry;
/*     */       do {
/* 420 */         byte[] headerBuf = getRecord();
/* 421 */         if (this.hasHitEOF) {
/* 422 */           this.currEntry = null;
/*     */           break;
/*     */         } 
/* 425 */         entry = new TarArchiveSparseEntry(headerBuf);
/* 429 */       } while (entry.isExtended());
/*     */     } 
/*     */   }
/*     */   
/*     */   public ArchiveEntry getNextEntry() throws IOException {
/* 435 */     return getNextTarEntry();
/*     */   }
/*     */   
/*     */   public int read(byte[] buf, int offset, int numToRead) throws IOException {
/* 453 */     int totalRead = 0;
/* 455 */     if (this.entryOffset >= this.entrySize)
/* 456 */       return -1; 
/* 459 */     if (numToRead + this.entryOffset > this.entrySize)
/* 460 */       numToRead = (int)(this.entrySize - this.entryOffset); 
/* 463 */     if (this.readBuf != null) {
/* 464 */       int sz = (numToRead > this.readBuf.length) ? this.readBuf.length : numToRead;
/* 467 */       System.arraycopy(this.readBuf, 0, buf, offset, sz);
/* 469 */       if (sz >= this.readBuf.length) {
/* 470 */         this.readBuf = null;
/*     */       } else {
/* 472 */         int newLen = this.readBuf.length - sz;
/* 473 */         byte[] newBuf = new byte[newLen];
/* 475 */         System.arraycopy(this.readBuf, sz, newBuf, 0, newLen);
/* 477 */         this.readBuf = newBuf;
/*     */       } 
/* 480 */       totalRead += sz;
/* 481 */       numToRead -= sz;
/* 482 */       offset += sz;
/*     */     } 
/* 485 */     while (numToRead > 0) {
/* 486 */       byte[] rec = this.buffer.readRecord();
/* 488 */       if (rec == null)
/* 490 */         throw new IOException("unexpected EOF with " + numToRead + " bytes unread. Occured at byte: " + getBytesRead()); 
/* 493 */       count(rec.length);
/* 494 */       int sz = numToRead;
/* 495 */       int recLen = rec.length;
/* 497 */       if (recLen > sz) {
/* 498 */         System.arraycopy(rec, 0, buf, offset, sz);
/* 500 */         this.readBuf = new byte[recLen - sz];
/* 502 */         System.arraycopy(rec, sz, this.readBuf, 0, recLen - sz);
/*     */       } else {
/* 504 */         sz = recLen;
/* 506 */         System.arraycopy(rec, 0, buf, offset, recLen);
/*     */       } 
/* 509 */       totalRead += sz;
/* 510 */       numToRead -= sz;
/* 511 */       offset += sz;
/*     */     } 
/* 514 */     this.entryOffset += totalRead;
/* 516 */     return totalRead;
/*     */   }
/*     */   
/*     */   public boolean canReadEntryData(ArchiveEntry ae) {
/* 526 */     if (ae instanceof TarArchiveEntry) {
/* 527 */       TarArchiveEntry te = (TarArchiveEntry)ae;
/* 528 */       return !te.isGNUSparse();
/*     */     } 
/* 530 */     return false;
/*     */   }
/*     */   
/*     */   protected final TarArchiveEntry getCurrentEntry() {
/* 534 */     return this.currEntry;
/*     */   }
/*     */   
/*     */   protected final void setCurrentEntry(TarArchiveEntry e) {
/* 538 */     this.currEntry = e;
/*     */   }
/*     */   
/*     */   protected final boolean isAtEOF() {
/* 542 */     return this.hasHitEOF;
/*     */   }
/*     */   
/*     */   protected final void setAtEOF(boolean b) {
/* 546 */     this.hasHitEOF = b;
/*     */   }
/*     */   
/*     */   public static boolean matches(byte[] signature, int length) {
/* 559 */     if (length < 265)
/* 560 */       return false; 
/* 563 */     if (ArchiveUtils.matchAsciiBuffer("ustar\000", signature, 257, 6) && ArchiveUtils.matchAsciiBuffer("00", signature, 263, 2))
/* 569 */       return true; 
/* 571 */     if (ArchiveUtils.matchAsciiBuffer("ustar ", signature, 257, 6) && (ArchiveUtils.matchAsciiBuffer(" \000", signature, 263, 2) || ArchiveUtils.matchAsciiBuffer("0\000", signature, 263, 2)))
/* 582 */       return true; 
/* 585 */     if (ArchiveUtils.matchAsciiBuffer("ustar\000", signature, 257, 6) && ArchiveUtils.matchAsciiBuffer("\000\000", signature, 263, 2))
/* 591 */       return true; 
/* 593 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\archivers\tar\TarArchiveInputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */