/*     */ package org.apache.commons.compress.archivers.zip;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PushbackInputStream;
/*     */ import java.util.zip.CRC32;
/*     */ import java.util.zip.DataFormatException;
/*     */ import java.util.zip.Inflater;
/*     */ import java.util.zip.ZipException;
/*     */ import org.apache.commons.compress.archivers.ArchiveEntry;
/*     */ import org.apache.commons.compress.archivers.ArchiveInputStream;
/*     */ 
/*     */ public class ZipArchiveInputStream extends ArchiveInputStream {
/*     */   private final ZipEncoding zipEncoding;
/*     */   
/*     */   private final boolean useUnicodeExtraFields;
/*     */   
/*     */   private final InputStream in;
/*     */   
/*  77 */   private final Inflater inf = new Inflater(true);
/*     */   
/*  82 */   private final CRC32 crc = new CRC32();
/*     */   
/*  87 */   private final Buffer buf = new Buffer();
/*     */   
/*  91 */   private CurrentEntry current = null;
/*     */   
/*     */   private boolean closed = false;
/*     */   
/*     */   private boolean hitCentralDirectory = false;
/*     */   
/* 106 */   private ByteArrayInputStream lastStoredEntry = null;
/*     */   
/*     */   private boolean allowStoredEntriesWithDataDescriptor = false;
/*     */   
/*     */   private static final int LFH_LEN = 30;
/*     */   
/*     */   private static final long TWO_EXP_32 = 4294967296L;
/*     */   
/*     */   public ZipArchiveInputStream(InputStream inputStream) {
/* 132 */     this(inputStream, "UTF8", true);
/*     */   }
/*     */   
/*     */   public ZipArchiveInputStream(InputStream inputStream, String encoding, boolean useUnicodeExtraFields) {
/* 144 */     this(inputStream, encoding, useUnicodeExtraFields, false);
/*     */   }
/*     */   
/*     */   public ZipArchiveInputStream(InputStream inputStream, String encoding, boolean useUnicodeExtraFields, boolean allowStoredEntriesWithDataDescriptor) {
/* 160 */     this.zipEncoding = ZipEncodingHelper.getZipEncoding(encoding);
/* 161 */     this.useUnicodeExtraFields = useUnicodeExtraFields;
/* 162 */     this.in = new PushbackInputStream(inputStream, this.buf.buf.length);
/* 163 */     this.allowStoredEntriesWithDataDescriptor = allowStoredEntriesWithDataDescriptor;
/*     */   }
/*     */   
/*     */   public ZipArchiveEntry getNextZipEntry() throws IOException {
/* 168 */     if (this.closed || this.hitCentralDirectory)
/* 169 */       return null; 
/* 171 */     if (this.current != null)
/* 172 */       closeEntry(); 
/* 174 */     byte[] lfh = new byte[30];
/*     */     try {
/* 176 */       readFully(lfh);
/* 177 */     } catch (EOFException e) {
/* 178 */       return null;
/*     */     } 
/* 180 */     ZipLong sig = new ZipLong(lfh);
/* 181 */     if (sig.equals(ZipLong.CFH_SIG)) {
/* 182 */       this.hitCentralDirectory = true;
/* 183 */       return null;
/*     */     } 
/* 185 */     if (!sig.equals(ZipLong.LFH_SIG))
/* 186 */       return null; 
/* 189 */     int off = 4;
/* 190 */     this.current = new CurrentEntry();
/* 192 */     int versionMadeBy = ZipShort.getValue(lfh, off);
/* 193 */     off += 2;
/* 194 */     this.current.entry.setPlatform(versionMadeBy >> 8 & 0xF);
/* 197 */     GeneralPurposeBit gpFlag = GeneralPurposeBit.parse(lfh, off);
/* 198 */     boolean hasUTF8Flag = gpFlag.usesUTF8ForNames();
/* 199 */     ZipEncoding entryEncoding = hasUTF8Flag ? ZipEncodingHelper.UTF8_ZIP_ENCODING : this.zipEncoding;
/* 201 */     this.current.hasDataDescriptor = gpFlag.usesDataDescriptor();
/* 202 */     this.current.entry.setGeneralPurposeBit(gpFlag);
/* 204 */     off += 2;
/* 206 */     this.current.entry.setMethod(ZipShort.getValue(lfh, off));
/* 207 */     off += 2;
/* 209 */     long time = ZipUtil.dosToJavaTime(ZipLong.getValue(lfh, off));
/* 210 */     this.current.entry.setTime(time);
/* 211 */     off += 4;
/* 213 */     ZipLong size = null, cSize = null;
/* 214 */     if (!this.current.hasDataDescriptor) {
/* 215 */       this.current.entry.setCrc(ZipLong.getValue(lfh, off));
/* 216 */       off += 4;
/* 218 */       cSize = new ZipLong(lfh, off);
/* 219 */       off += 4;
/* 221 */       size = new ZipLong(lfh, off);
/* 222 */       off += 4;
/*     */     } else {
/* 224 */       off += 12;
/*     */     } 
/* 227 */     int fileNameLen = ZipShort.getValue(lfh, off);
/* 229 */     off += 2;
/* 231 */     int extraLen = ZipShort.getValue(lfh, off);
/* 232 */     off += 2;
/* 234 */     byte[] fileName = new byte[fileNameLen];
/* 235 */     readFully(fileName);
/* 236 */     this.current.entry.setName(entryEncoding.decode(fileName), fileName);
/* 238 */     byte[] extraData = new byte[extraLen];
/* 239 */     readFully(extraData);
/* 240 */     this.current.entry.setExtra(extraData);
/* 242 */     if (!hasUTF8Flag && this.useUnicodeExtraFields)
/* 243 */       ZipUtil.setNameAndCommentFromExtraFields(this.current.entry, fileName, null); 
/* 247 */     processZip64Extra(size, cSize);
/* 248 */     return this.current.entry;
/*     */   }
/*     */   
/*     */   private void processZip64Extra(ZipLong size, ZipLong cSize) {
/* 257 */     Zip64ExtendedInformationExtraField z64 = (Zip64ExtendedInformationExtraField)this.current.entry.getExtraField(Zip64ExtendedInformationExtraField.HEADER_ID);
/* 261 */     this.current.usesZip64 = (z64 != null);
/* 262 */     if (!this.current.hasDataDescriptor)
/* 263 */       if (this.current.usesZip64 && (cSize.equals(ZipLong.ZIP64_MAGIC) || size.equals(ZipLong.ZIP64_MAGIC))) {
/* 266 */         this.current.entry.setCompressedSize(z64.getCompressedSize().getLongValue());
/* 268 */         this.current.entry.setSize(z64.getSize().getLongValue());
/*     */       } else {
/* 270 */         this.current.entry.setCompressedSize(cSize.getValue());
/* 271 */         this.current.entry.setSize(size.getValue());
/*     */       }  
/*     */   }
/*     */   
/*     */   public ArchiveEntry getNextEntry() throws IOException {
/* 279 */     return getNextZipEntry();
/*     */   }
/*     */   
/*     */   public boolean canReadEntryData(ArchiveEntry ae) {
/* 291 */     if (ae instanceof ZipArchiveEntry) {
/* 292 */       ZipArchiveEntry ze = (ZipArchiveEntry)ae;
/* 293 */       return (ZipUtil.canHandleEntryData(ze) && supportsDataDescriptorFor(ze));
/*     */     } 
/* 297 */     return false;
/*     */   }
/*     */   
/*     */   public int read(byte[] buffer, int start, int length) throws IOException {
/* 302 */     if (this.closed)
/* 303 */       throw new IOException("The stream is closed"); 
/* 305 */     if (this.inf.finished() || this.current == null)
/* 306 */       return -1; 
/* 310 */     if (start <= buffer.length && length >= 0 && start >= 0 && buffer.length - start >= length) {
/* 312 */       ZipUtil.checkRequestedFeatures(this.current.entry);
/* 313 */       if (!supportsDataDescriptorFor(this.current.entry))
/* 314 */         throw new UnsupportedZipFeatureException(UnsupportedZipFeatureException.Feature.DATA_DESCRIPTOR, this.current.entry); 
/* 320 */       if (this.current.entry.getMethod() == 0)
/* 321 */         return readStored(buffer, start, length); 
/* 323 */       return readDeflated(buffer, start, length);
/*     */     } 
/* 325 */     throw new ArrayIndexOutOfBoundsException();
/*     */   }
/*     */   
/*     */   private int readStored(byte[] buffer, int start, int length) throws IOException {
/* 334 */     if (this.current.hasDataDescriptor) {
/* 335 */       if (this.lastStoredEntry == null)
/* 336 */         readStoredEntry(); 
/* 338 */       return this.lastStoredEntry.read(buffer, start, length);
/*     */     } 
/* 341 */     long csize = this.current.entry.getSize();
/* 342 */     if (this.current.bytesRead >= csize)
/* 343 */       return -1; 
/* 346 */     if (this.buf.offsetInBuffer >= this.buf.lengthOfLastRead) {
/* 347 */       this.buf.offsetInBuffer = 0;
/* 348 */       if ((this.buf.lengthOfLastRead = this.in.read(this.buf.buf)) == -1)
/* 349 */         return -1; 
/* 351 */       count(this.buf.lengthOfLastRead);
/* 352 */       this.current.bytesReadFromStream += this.buf.lengthOfLastRead;
/*     */     } 
/* 355 */     int toRead = (length > this.buf.lengthOfLastRead) ? (this.buf.lengthOfLastRead - this.buf.offsetInBuffer) : length;
/* 358 */     if (csize - this.current.bytesRead < toRead)
/* 360 */       toRead = (int)(csize - this.current.bytesRead); 
/* 362 */     System.arraycopy(this.buf.buf, this.buf.offsetInBuffer, buffer, start, toRead);
/* 363 */     this.buf.offsetInBuffer += toRead;
/* 364 */     this.current.bytesRead += toRead;
/* 365 */     this.crc.update(buffer, start, toRead);
/* 366 */     return toRead;
/*     */   }
/*     */   
/*     */   private int readDeflated(byte[] buffer, int start, int length) throws IOException {
/* 374 */     if (this.inf.needsInput()) {
/* 375 */       fill();
/* 376 */       if (this.buf.lengthOfLastRead > 0)
/* 377 */         this.current.bytesReadFromStream += this.buf.lengthOfLastRead; 
/*     */     } 
/* 380 */     int read = 0;
/*     */     try {
/* 382 */       read = this.inf.inflate(buffer, start, length);
/* 383 */     } catch (DataFormatException e) {
/* 384 */       throw new ZipException(e.getMessage());
/*     */     } 
/* 386 */     if (read == 0) {
/* 387 */       if (this.inf.finished())
/* 388 */         return -1; 
/* 389 */       if (this.buf.lengthOfLastRead == -1)
/* 390 */         throw new IOException("Truncated ZIP file"); 
/*     */     } 
/* 393 */     this.crc.update(buffer, start, read);
/* 394 */     return read;
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 399 */     if (!this.closed) {
/* 400 */       this.closed = true;
/* 401 */       this.in.close();
/* 402 */       this.inf.end();
/*     */     } 
/*     */   }
/*     */   
/*     */   public long skip(long value) throws IOException {
/* 423 */     if (value >= 0L) {
/* 424 */       long skipped = 0L;
/* 425 */       byte[] b = new byte[1024];
/* 426 */       while (skipped < value) {
/* 427 */         long rem = value - skipped;
/* 428 */         int x = read(b, 0, (int)((b.length > rem) ? rem : b.length));
/* 429 */         if (x == -1)
/* 430 */           return skipped; 
/* 432 */         skipped += x;
/*     */       } 
/* 434 */       return skipped;
/*     */     } 
/* 436 */     throw new IllegalArgumentException();
/*     */   }
/*     */   
/*     */   public static boolean matches(byte[] signature, int length) {
/* 451 */     if (length < ZipArchiveOutputStream.LFH_SIG.length)
/* 452 */       return false; 
/* 455 */     return (checksig(signature, ZipArchiveOutputStream.LFH_SIG) || checksig(signature, ZipArchiveOutputStream.EOCD_SIG));
/*     */   }
/*     */   
/*     */   private static boolean checksig(byte[] signature, byte[] expected) {
/* 460 */     for (int i = 0; i < expected.length; i++) {
/* 461 */       if (signature[i] != expected[i])
/* 462 */         return false; 
/*     */     } 
/* 465 */     return true;
/*     */   }
/*     */   
/*     */   private void closeEntry() throws IOException {
/* 487 */     if (this.closed)
/* 488 */       throw new IOException("The stream is closed"); 
/* 490 */     if (this.current == null)
/*     */       return; 
/* 495 */     if (this.current.bytesReadFromStream <= this.current.entry.getCompressedSize() && !this.current.hasDataDescriptor) {
/* 497 */       drainCurrentEntryData();
/*     */     } else {
/* 499 */       skip(Long.MAX_VALUE);
/* 501 */       long inB = (this.current.entry.getMethod() == 8) ? getBytesInflated() : this.current.bytesRead;
/* 507 */       int diff = (int)(this.current.bytesReadFromStream - inB);
/* 510 */       if (diff > 0)
/* 511 */         pushback(this.buf.buf, this.buf.lengthOfLastRead - diff, diff); 
/*     */     } 
/* 515 */     if (this.lastStoredEntry == null && this.current.hasDataDescriptor)
/* 516 */       readDataDescriptor(); 
/* 519 */     this.inf.reset();
/* 520 */     this.buf.reset();
/* 521 */     this.crc.reset();
/* 522 */     this.current = null;
/* 523 */     this.lastStoredEntry = null;
/*     */   }
/*     */   
/*     */   private void drainCurrentEntryData() throws IOException {
/* 531 */     long remaining = this.current.entry.getCompressedSize() - this.current.bytesReadFromStream;
/* 533 */     while (remaining > 0L) {
/* 534 */       long n = this.in.read(this.buf.buf, 0, (int)Math.min(this.buf.buf.length, remaining));
/* 536 */       if (n < 0L)
/* 537 */         throw new EOFException("Truncated ZIP entry: " + this.current.entry.getName()); 
/* 540 */       count(n);
/* 541 */       remaining -= n;
/*     */     } 
/*     */   }
/*     */   
/*     */   private long getBytesInflated() {
/* 562 */     long inB = this.inf.getBytesRead();
/* 563 */     if (this.current.bytesReadFromStream >= 4294967296L)
/* 564 */       while (inB + 4294967296L <= this.current.bytesReadFromStream)
/* 565 */         inB += 4294967296L;  
/* 568 */     return inB;
/*     */   }
/*     */   
/*     */   private void fill() throws IOException {
/* 572 */     if (this.closed)
/* 573 */       throw new IOException("The stream is closed"); 
/* 575 */     if ((this.buf.lengthOfLastRead = this.in.read(this.buf.buf)) > 0) {
/* 576 */       count(this.buf.lengthOfLastRead);
/* 577 */       this.inf.setInput(this.buf.buf, 0, this.buf.lengthOfLastRead);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void readFully(byte[] b) throws IOException {
/* 582 */     int count = 0, x = 0;
/* 583 */     while (count != b.length) {
/* 584 */       count += x = this.in.read(b, count, b.length - count);
/* 585 */       if (x == -1)
/* 586 */         throw new EOFException(); 
/* 588 */       count(x);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void readDataDescriptor() throws IOException {
/* 593 */     byte[] b = new byte[4];
/* 594 */     readFully(b);
/* 595 */     ZipLong val = new ZipLong(b);
/* 596 */     if (ZipLong.DD_SIG.equals(val)) {
/* 598 */       readFully(b);
/* 599 */       val = new ZipLong(b);
/*     */     } 
/* 601 */     this.current.entry.setCrc(val.getValue());
/* 614 */     b = new byte[16];
/* 615 */     readFully(b);
/* 616 */     ZipLong potentialSig = new ZipLong(b, 8);
/* 617 */     if (potentialSig.equals(ZipLong.CFH_SIG) || potentialSig.equals(ZipLong.LFH_SIG)) {
/* 619 */       pushback(b, 8, 8);
/* 620 */       this.current.entry.setCompressedSize(ZipLong.getValue(b));
/* 621 */       this.current.entry.setSize(ZipLong.getValue(b, 4));
/*     */     } else {
/* 623 */       this.current.entry.setCompressedSize(ZipEightByteInteger.getLongValue(b));
/* 625 */       this.current.entry.setSize(ZipEightByteInteger.getLongValue(b, 8));
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean supportsDataDescriptorFor(ZipArchiveEntry entry) {
/* 637 */     return (this.allowStoredEntriesWithDataDescriptor || !entry.getGeneralPurposeBit().usesDataDescriptor() || entry.getMethod() == 8);
/*     */   }
/*     */   
/*     */   private void readStoredEntry() throws IOException {
/* 660 */     ByteArrayOutputStream bos = new ByteArrayOutputStream();
/* 661 */     int off = 0;
/* 662 */     boolean done = false;
/* 665 */     int ddLen = this.current.usesZip64 ? 20 : 12;
/* 667 */     while (!done) {
/* 668 */       int r = this.in.read(this.buf.buf, off, 512 - off);
/* 670 */       if (r <= 0)
/* 673 */         throw new IOException("Truncated ZIP file"); 
/* 675 */       if (r + off < 4) {
/* 677 */         off += r;
/*     */         continue;
/*     */       } 
/* 681 */       done = bufferContainsSignature(bos, off, r, ddLen);
/* 682 */       if (!done)
/* 683 */         off = cacheBytesRead(bos, off, r, ddLen); 
/*     */     } 
/* 687 */     byte[] b = bos.toByteArray();
/* 688 */     this.lastStoredEntry = new ByteArrayInputStream(b);
/*     */   }
/*     */   
/* 691 */   private static final byte[] LFH = ZipLong.LFH_SIG.getBytes();
/*     */   
/* 692 */   private static final byte[] CFH = ZipLong.CFH_SIG.getBytes();
/*     */   
/* 693 */   private static final byte[] DD = ZipLong.DD_SIG.getBytes();
/*     */   
/*     */   private boolean bufferContainsSignature(ByteArrayOutputStream bos, int offset, int lastRead, int expectedDDLen) throws IOException {
/* 707 */     boolean done = false;
/* 708 */     int readTooMuch = 0;
/* 709 */     for (int i = 0; !done && i < lastRead - 4; i++) {
/* 710 */       if (this.buf.buf[i] == LFH[0] && this.buf.buf[i + 1] == LFH[1]) {
/* 711 */         if ((this.buf.buf[i + 2] == LFH[2] && this.buf.buf[i + 3] == LFH[3]) || (this.buf.buf[i] == CFH[2] && this.buf.buf[i + 3] == CFH[3])) {
/* 714 */           readTooMuch = offset + lastRead - i - expectedDDLen;
/* 715 */           done = true;
/* 717 */         } else if (this.buf.buf[i + 2] == DD[2] && this.buf.buf[i + 3] == DD[3]) {
/* 719 */           readTooMuch = offset + lastRead - i;
/* 720 */           done = true;
/*     */         } 
/* 722 */         if (done) {
/* 727 */           pushback(this.buf.buf, offset + lastRead - readTooMuch, readTooMuch);
/* 729 */           bos.write(this.buf.buf, 0, i);
/* 730 */           readDataDescriptor();
/*     */         } 
/*     */       } 
/*     */     } 
/* 734 */     return done;
/*     */   }
/*     */   
/*     */   private int cacheBytesRead(ByteArrayOutputStream bos, int offset, int lastRead, int expecteDDLen) {
/* 748 */     int cacheable = offset + lastRead - expecteDDLen - 3;
/* 749 */     if (cacheable > 0) {
/* 750 */       bos.write(this.buf.buf, 0, cacheable);
/* 751 */       System.arraycopy(this.buf.buf, cacheable, this.buf.buf, 0, expecteDDLen + 3);
/* 753 */       offset = expecteDDLen + 3;
/*     */     } else {
/* 755 */       offset += lastRead;
/*     */     } 
/* 757 */     return offset;
/*     */   }
/*     */   
/*     */   private void pushback(byte[] buf, int offset, int length) throws IOException {
/* 762 */     ((PushbackInputStream)this.in).unread(buf, offset, length);
/* 763 */     pushedBackBytes(length);
/*     */   }
/*     */   
/*     */   private static final class CurrentEntry {
/*     */     private CurrentEntry() {}
/*     */     
/* 774 */     private final ZipArchiveEntry entry = new ZipArchiveEntry();
/*     */     
/*     */     private boolean hasDataDescriptor;
/*     */     
/*     */     private boolean usesZip64;
/*     */     
/*     */     private long bytesRead;
/*     */     
/*     */     private long bytesReadFromStream;
/*     */   }
/*     */   
/*     */   private static final class Buffer {
/* 807 */     private final byte[] buf = new byte[512];
/*     */     
/* 812 */     private int offsetInBuffer = 0;
/*     */     
/* 817 */     private int lengthOfLastRead = 0;
/*     */     
/*     */     private void reset() {
/* 822 */       this.offsetInBuffer = this.lengthOfLastRead = 0;
/*     */     }
/*     */     
/*     */     private Buffer() {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\archivers\zip\ZipArchiveInputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */