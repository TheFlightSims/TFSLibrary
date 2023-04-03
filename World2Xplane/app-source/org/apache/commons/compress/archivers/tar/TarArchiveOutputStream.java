/*     */ package org.apache.commons.compress.archivers.tar;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.StringWriter;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.compress.archivers.ArchiveEntry;
/*     */ import org.apache.commons.compress.archivers.ArchiveOutputStream;
/*     */ import org.apache.commons.compress.archivers.zip.ZipEncoding;
/*     */ import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
/*     */ import org.apache.commons.compress.utils.CountingOutputStream;
/*     */ 
/*     */ public class TarArchiveOutputStream extends ArchiveOutputStream {
/*     */   public static final int LONGFILE_ERROR = 0;
/*     */   
/*     */   public static final int LONGFILE_TRUNCATE = 1;
/*     */   
/*     */   public static final int LONGFILE_GNU = 2;
/*     */   
/*     */   public static final int LONGFILE_POSIX = 3;
/*     */   
/*     */   public static final int BIGNUMBER_ERROR = 0;
/*     */   
/*     */   public static final int BIGNUMBER_STAR = 1;
/*     */   
/*     */   public static final int BIGNUMBER_POSIX = 2;
/*     */   
/*     */   private long currSize;
/*     */   
/*     */   private String currName;
/*     */   
/*     */   private long currBytes;
/*     */   
/*     */   private final byte[] recordBuf;
/*     */   
/*     */   private int assemLen;
/*     */   
/*     */   private final byte[] assemBuf;
/*     */   
/*     */   protected final TarBuffer buffer;
/*     */   
/*  69 */   private int longFileMode = 0;
/*     */   
/*  70 */   private int bigNumberMode = 0;
/*     */   
/*     */   private boolean closed = false;
/*     */   
/*     */   private boolean haveUnclosedEntry = false;
/*     */   
/*     */   private boolean finished = false;
/*     */   
/*     */   private final OutputStream out;
/*     */   
/*     */   private final ZipEncoding encoding;
/*     */   
/*     */   private boolean addPaxHeadersForNonAsciiNames = false;
/*     */   
/*  85 */   private static final ZipEncoding ASCII = ZipEncodingHelper.getZipEncoding("ASCII");
/*     */   
/*     */   public TarArchiveOutputStream(OutputStream os) {
/*  93 */     this(os, 10240, 512);
/*     */   }
/*     */   
/*     */   public TarArchiveOutputStream(OutputStream os, String encoding) {
/* 103 */     this(os, 10240, 512, encoding);
/*     */   }
/*     */   
/*     */   public TarArchiveOutputStream(OutputStream os, int blockSize) {
/* 112 */     this(os, blockSize, 512);
/*     */   }
/*     */   
/*     */   public TarArchiveOutputStream(OutputStream os, int blockSize, String encoding) {
/* 124 */     this(os, blockSize, 512, encoding);
/*     */   }
/*     */   
/*     */   public TarArchiveOutputStream(OutputStream os, int blockSize, int recordSize) {
/* 134 */     this(os, blockSize, recordSize, (String)null);
/*     */   }
/*     */   
/*     */   public TarArchiveOutputStream(OutputStream os, int blockSize, int recordSize, String encoding) {
/* 147 */     this.out = (OutputStream)new CountingOutputStream(os);
/* 148 */     this.encoding = ZipEncodingHelper.getZipEncoding(encoding);
/* 150 */     this.buffer = new TarBuffer(this.out, blockSize, recordSize);
/* 151 */     this.assemLen = 0;
/* 152 */     this.assemBuf = new byte[recordSize];
/* 153 */     this.recordBuf = new byte[recordSize];
/*     */   }
/*     */   
/*     */   public void setLongFileMode(int longFileMode) {
/* 164 */     this.longFileMode = longFileMode;
/*     */   }
/*     */   
/*     */   public void setBigNumberMode(int bigNumberMode) {
/* 176 */     this.bigNumberMode = bigNumberMode;
/*     */   }
/*     */   
/*     */   public void setAddPaxHeadersForNonAsciiNames(boolean b) {
/* 184 */     this.addPaxHeadersForNonAsciiNames = b;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public int getCount() {
/* 190 */     return (int)getBytesWritten();
/*     */   }
/*     */   
/*     */   public long getBytesWritten() {
/* 195 */     return ((CountingOutputStream)this.out).getBytesWritten();
/*     */   }
/*     */   
/*     */   public void finish() throws IOException {
/* 209 */     if (this.finished)
/* 210 */       throw new IOException("This archive has already been finished"); 
/* 213 */     if (this.haveUnclosedEntry)
/* 214 */       throw new IOException("This archives contains unclosed entries."); 
/* 216 */     writeEOFRecord();
/* 217 */     writeEOFRecord();
/* 218 */     this.buffer.flushBlock();
/* 219 */     this.finished = true;
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 228 */     if (!this.finished)
/* 229 */       finish(); 
/* 232 */     if (!this.closed) {
/* 233 */       this.buffer.close();
/* 234 */       this.out.close();
/* 235 */       this.closed = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getRecordSize() {
/* 245 */     return this.buffer.getRecordSize();
/*     */   }
/*     */   
/*     */   public void putArchiveEntry(ArchiveEntry archiveEntry) throws IOException {
/* 263 */     if (this.finished)
/* 264 */       throw new IOException("Stream has already been finished"); 
/* 266 */     TarArchiveEntry entry = (TarArchiveEntry)archiveEntry;
/* 267 */     Map<String, String> paxHeaders = new HashMap<String, String>();
/* 268 */     String entryName = entry.getName();
/* 269 */     byte[] nameBytes = this.encoding.encode(entryName).array();
/* 270 */     boolean paxHeaderContainsPath = false;
/* 271 */     if (nameBytes.length >= 100)
/* 273 */       if (this.longFileMode == 3) {
/* 274 */         paxHeaders.put("path", entryName);
/* 275 */         paxHeaderContainsPath = true;
/* 276 */       } else if (this.longFileMode == 2) {
/* 279 */         TarArchiveEntry longLinkEntry = new TarArchiveEntry("././@LongLink", (byte)76);
/* 282 */         longLinkEntry.setSize((nameBytes.length + 1));
/* 283 */         putArchiveEntry(longLinkEntry);
/* 284 */         write(nameBytes);
/* 285 */         write(0);
/* 286 */         closeArchiveEntry();
/* 287 */       } else if (this.longFileMode != 1) {
/* 288 */         throw new RuntimeException("file name '" + entryName + "' is too long ( > " + 'd' + " bytes)");
/*     */       }  
/* 294 */     if (this.bigNumberMode == 2) {
/* 295 */       addPaxHeadersForBigNumbers(paxHeaders, entry);
/* 296 */     } else if (this.bigNumberMode != 1) {
/* 297 */       failForBigNumbers(entry);
/*     */     } 
/* 300 */     if (this.addPaxHeadersForNonAsciiNames && !paxHeaderContainsPath && !ASCII.canEncode(entryName))
/* 302 */       paxHeaders.put("path", entryName); 
/* 305 */     if (this.addPaxHeadersForNonAsciiNames && (entry.isLink() || entry.isSymbolicLink()) && !ASCII.canEncode(entry.getLinkName()))
/* 308 */       paxHeaders.put("linkpath", entry.getLinkName()); 
/* 311 */     if (paxHeaders.size() > 0)
/* 312 */       writePaxHeaders(entryName, paxHeaders); 
/* 315 */     entry.writeEntryHeader(this.recordBuf, this.encoding, (this.bigNumberMode == 1));
/* 317 */     this.buffer.writeRecord(this.recordBuf);
/* 319 */     this.currBytes = 0L;
/* 321 */     if (entry.isDirectory()) {
/* 322 */       this.currSize = 0L;
/*     */     } else {
/* 324 */       this.currSize = entry.getSize();
/*     */     } 
/* 326 */     this.currName = entryName;
/* 327 */     this.haveUnclosedEntry = true;
/*     */   }
/*     */   
/*     */   public void closeArchiveEntry() throws IOException {
/* 342 */     if (this.finished)
/* 343 */       throw new IOException("Stream has already been finished"); 
/* 345 */     if (!this.haveUnclosedEntry)
/* 346 */       throw new IOException("No current entry to close"); 
/* 348 */     if (this.assemLen > 0) {
/* 349 */       for (int i = this.assemLen; i < this.assemBuf.length; i++)
/* 350 */         this.assemBuf[i] = 0; 
/* 353 */       this.buffer.writeRecord(this.assemBuf);
/* 355 */       this.currBytes += this.assemLen;
/* 356 */       this.assemLen = 0;
/*     */     } 
/* 359 */     if (this.currBytes < this.currSize)
/* 360 */       throw new IOException("entry '" + this.currName + "' closed at '" + this.currBytes + "' before the '" + this.currSize + "' bytes specified in the header were written"); 
/* 365 */     this.haveUnclosedEntry = false;
/*     */   }
/*     */   
/*     */   public void write(byte[] wBuf, int wOffset, int numToWrite) throws IOException {
/* 384 */     if (this.currBytes + numToWrite > this.currSize)
/* 385 */       throw new IOException("request to write '" + numToWrite + "' bytes exceeds size in header of '" + this.currSize + "' bytes for entry '" + this.currName + "'"); 
/* 399 */     if (this.assemLen > 0)
/* 400 */       if (this.assemLen + numToWrite >= this.recordBuf.length) {
/* 401 */         int aLen = this.recordBuf.length - this.assemLen;
/* 403 */         System.arraycopy(this.assemBuf, 0, this.recordBuf, 0, this.assemLen);
/* 405 */         System.arraycopy(wBuf, wOffset, this.recordBuf, this.assemLen, aLen);
/* 407 */         this.buffer.writeRecord(this.recordBuf);
/* 409 */         this.currBytes += this.recordBuf.length;
/* 410 */         wOffset += aLen;
/* 411 */         numToWrite -= aLen;
/* 412 */         this.assemLen = 0;
/*     */       } else {
/* 414 */         System.arraycopy(wBuf, wOffset, this.assemBuf, this.assemLen, numToWrite);
/* 417 */         wOffset += numToWrite;
/* 418 */         this.assemLen += numToWrite;
/* 419 */         numToWrite = 0;
/*     */       }  
/* 428 */     while (numToWrite > 0) {
/* 429 */       if (numToWrite < this.recordBuf.length) {
/* 430 */         System.arraycopy(wBuf, wOffset, this.assemBuf, this.assemLen, numToWrite);
/* 433 */         this.assemLen += numToWrite;
/*     */         break;
/*     */       } 
/* 438 */       this.buffer.writeRecord(wBuf, wOffset);
/* 440 */       int num = this.recordBuf.length;
/* 442 */       this.currBytes += num;
/* 443 */       numToWrite -= num;
/* 444 */       wOffset += num;
/*     */     } 
/*     */   }
/*     */   
/*     */   void writePaxHeaders(String entryName, Map<String, String> headers) throws IOException {
/* 454 */     String name = "./PaxHeaders.X/" + stripTo7Bits(entryName);
/* 455 */     if (name.length() >= 100)
/* 456 */       name = name.substring(0, 99); 
/* 458 */     TarArchiveEntry pex = new TarArchiveEntry(name, (byte)120);
/* 461 */     StringWriter w = new StringWriter();
/* 462 */     for (Map.Entry<String, String> h : headers.entrySet()) {
/* 463 */       String key = h.getKey();
/* 464 */       String value = h.getValue();
/* 465 */       int len = key.length() + value.length() + 3 + 2;
/* 468 */       String line = len + " " + key + "=" + value + "\n";
/* 469 */       int actualLength = (line.getBytes("UTF-8")).length;
/* 470 */       while (len != actualLength) {
/* 476 */         len = actualLength;
/* 477 */         line = len + " " + key + "=" + value + "\n";
/* 478 */         actualLength = (line.getBytes("UTF-8")).length;
/*     */       } 
/* 480 */       w.write(line);
/*     */     } 
/* 482 */     byte[] data = w.toString().getBytes("UTF-8");
/* 483 */     pex.setSize(data.length);
/* 484 */     putArchiveEntry(pex);
/* 485 */     write(data);
/* 486 */     closeArchiveEntry();
/*     */   }
/*     */   
/*     */   private String stripTo7Bits(String name) {
/* 490 */     int length = name.length();
/* 491 */     StringBuffer result = new StringBuffer(length);
/* 492 */     for (int i = 0; i < length; i++) {
/* 493 */       char stripped = (char)(name.charAt(i) & 0x7F);
/* 494 */       if (stripped != '\000')
/* 495 */         result.append(stripped); 
/*     */     } 
/* 498 */     return result.toString();
/*     */   }
/*     */   
/*     */   private void writeEOFRecord() throws IOException {
/* 506 */     for (int i = 0; i < this.recordBuf.length; i++)
/* 507 */       this.recordBuf[i] = 0; 
/* 510 */     this.buffer.writeRecord(this.recordBuf);
/*     */   }
/*     */   
/*     */   public void flush() throws IOException {
/* 515 */     this.out.flush();
/*     */   }
/*     */   
/*     */   public ArchiveEntry createArchiveEntry(File inputFile, String entryName) throws IOException {
/* 522 */     if (this.finished)
/* 523 */       throw new IOException("Stream has already been finished"); 
/* 525 */     return new TarArchiveEntry(inputFile, entryName);
/*     */   }
/*     */   
/*     */   private void addPaxHeadersForBigNumbers(Map<String, String> paxHeaders, TarArchiveEntry entry) {
/* 530 */     addPaxHeaderForBigNumber(paxHeaders, "size", entry.getSize(), 8589934591L);
/* 532 */     addPaxHeaderForBigNumber(paxHeaders, "gid", entry.getGroupId(), 2097151L);
/* 534 */     addPaxHeaderForBigNumber(paxHeaders, "mtime", entry.getModTime().getTime() / 1000L, 8589934591L);
/* 537 */     addPaxHeaderForBigNumber(paxHeaders, "uid", entry.getUserId(), 2097151L);
/* 540 */     addPaxHeaderForBigNumber(paxHeaders, "SCHILY.devmajor", entry.getDevMajor(), 2097151L);
/* 542 */     addPaxHeaderForBigNumber(paxHeaders, "SCHILY.devminor", entry.getDevMinor(), 2097151L);
/* 545 */     failForBigNumber("mode", entry.getMode(), 2097151L);
/*     */   }
/*     */   
/*     */   private void addPaxHeaderForBigNumber(Map<String, String> paxHeaders, String header, long value, long maxValue) {
/* 551 */     if (value < 0L || value > maxValue)
/* 552 */       paxHeaders.put(header, String.valueOf(value)); 
/*     */   }
/*     */   
/*     */   private void failForBigNumbers(TarArchiveEntry entry) {
/* 557 */     failForBigNumber("entry size", entry.getSize(), 8589934591L);
/* 558 */     failForBigNumber("group id", entry.getGroupId(), 2097151L);
/* 559 */     failForBigNumber("last modification time", entry.getModTime().getTime() / 1000L, 8589934591L);
/* 562 */     failForBigNumber("user id", entry.getUserId(), 2097151L);
/* 563 */     failForBigNumber("mode", entry.getMode(), 2097151L);
/* 564 */     failForBigNumber("major device number", entry.getDevMajor(), 2097151L);
/* 566 */     failForBigNumber("minor device number", entry.getDevMinor(), 2097151L);
/*     */   }
/*     */   
/*     */   private void failForBigNumber(String field, long value, long maxValue) {
/* 571 */     if (value < 0L || value > maxValue)
/* 572 */       throw new RuntimeException(field + " '" + value + "' is too big ( > " + maxValue + " )"); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\archivers\tar\TarArchiveOutputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */