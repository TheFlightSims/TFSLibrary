/*     */ package org.apache.commons.compress.archivers.cpio;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.HashMap;
/*     */ import org.apache.commons.compress.archivers.ArchiveEntry;
/*     */ import org.apache.commons.compress.archivers.ArchiveOutputStream;
/*     */ import org.apache.commons.compress.utils.ArchiveUtils;
/*     */ 
/*     */ public class CpioArchiveOutputStream extends ArchiveOutputStream implements CpioConstants {
/*     */   private CpioArchiveEntry entry;
/*     */   
/*     */   private boolean closed = false;
/*     */   
/*     */   private boolean finished;
/*     */   
/*     */   private final short entryFormat;
/*     */   
/*  76 */   private final HashMap<String, CpioArchiveEntry> names = new HashMap<String, CpioArchiveEntry>();
/*     */   
/*  79 */   private long crc = 0L;
/*     */   
/*     */   private long written;
/*     */   
/*     */   private final OutputStream out;
/*     */   
/*     */   private final int blockSize;
/*     */   
/*  87 */   private long nextArtificalDeviceAndInode = 1L;
/*     */   
/*     */   public CpioArchiveOutputStream(OutputStream out, short format) {
/*  99 */     this(out, format, 512);
/*     */   }
/*     */   
/*     */   public CpioArchiveOutputStream(OutputStream out, short format, int blockSize) {
/* 116 */     this.out = out;
/* 117 */     switch (format) {
/*     */       case 1:
/*     */       case 2:
/*     */       case 4:
/*     */       case 8:
/*     */         break;
/*     */       default:
/* 124 */         throw new IllegalArgumentException("Unknown format: " + format);
/*     */     } 
/* 127 */     this.entryFormat = format;
/* 128 */     this.blockSize = blockSize;
/*     */   }
/*     */   
/*     */   public CpioArchiveOutputStream(OutputStream out) {
/* 139 */     this(out, (short)1);
/*     */   }
/*     */   
/*     */   private void ensureOpen() throws IOException {
/* 149 */     if (this.closed)
/* 150 */       throw new IOException("Stream closed"); 
/*     */   }
/*     */   
/*     */   public void putArchiveEntry(ArchiveEntry entry) throws IOException {
/* 170 */     if (this.finished)
/* 171 */       throw new IOException("Stream has already been finished"); 
/* 174 */     CpioArchiveEntry e = (CpioArchiveEntry)entry;
/* 175 */     ensureOpen();
/* 176 */     if (this.entry != null)
/* 177 */       closeArchiveEntry(); 
/* 179 */     if (e.getTime() == -1L)
/* 180 */       e.setTime(System.currentTimeMillis() / 1000L); 
/* 183 */     short format = e.getFormat();
/* 184 */     if (format != this.entryFormat)
/* 185 */       throw new IOException("Header format: " + format + " does not match existing format: " + this.entryFormat); 
/* 188 */     if (this.names.put(e.getName(), e) != null)
/* 189 */       throw new IOException("duplicate entry: " + e.getName()); 
/* 192 */     writeHeader(e);
/* 193 */     this.entry = e;
/* 194 */     this.written = 0L;
/*     */   }
/*     */   
/*     */   private void writeHeader(CpioArchiveEntry e) throws IOException {
/*     */     boolean swapHalfWord;
/* 198 */     switch (e.getFormat()) {
/*     */       case 1:
/* 200 */         this.out.write(ArchiveUtils.toAsciiBytes("070701"));
/* 201 */         count(6);
/* 202 */         writeNewEntry(e);
/*     */         break;
/*     */       case 2:
/* 205 */         this.out.write(ArchiveUtils.toAsciiBytes("070702"));
/* 206 */         count(6);
/* 207 */         writeNewEntry(e);
/*     */         break;
/*     */       case 4:
/* 210 */         this.out.write(ArchiveUtils.toAsciiBytes("070707"));
/* 211 */         count(6);
/* 212 */         writeOldAsciiEntry(e);
/*     */         break;
/*     */       case 8:
/* 215 */         swapHalfWord = true;
/* 216 */         writeBinaryLong(29127L, 2, swapHalfWord);
/* 217 */         writeOldBinaryEntry(e, swapHalfWord);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void writeNewEntry(CpioArchiveEntry entry) throws IOException {
/* 223 */     long inode = entry.getInode();
/* 224 */     long devMin = entry.getDeviceMin();
/* 226 */     inode = devMin = 0L;
/* 229 */     inode = this.nextArtificalDeviceAndInode & 0xFFFFFFFFFFFFFFFFL;
/* 230 */     devMin = this.nextArtificalDeviceAndInode++ >> 32L & 0xFFFFFFFFFFFFFFFFL;
/* 232 */     this.nextArtificalDeviceAndInode = Math.max(this.nextArtificalDeviceAndInode, inode + 4294967296L * devMin) + 1L;
/* 238 */     writeAsciiLong(inode, 8, 16);
/* 239 */     writeAsciiLong(entry.getMode(), 8, 16);
/* 240 */     writeAsciiLong(entry.getUID(), 8, 16);
/* 241 */     writeAsciiLong(entry.getGID(), 8, 16);
/* 242 */     writeAsciiLong(entry.getNumberOfLinks(), 8, 16);
/* 243 */     writeAsciiLong(entry.getTime(), 8, 16);
/* 244 */     writeAsciiLong(entry.getSize(), 8, 16);
/* 245 */     writeAsciiLong(entry.getDeviceMaj(), 8, 16);
/* 246 */     writeAsciiLong(devMin, 8, 16);
/* 247 */     writeAsciiLong(entry.getRemoteDeviceMaj(), 8, 16);
/* 248 */     writeAsciiLong(entry.getRemoteDeviceMin(), 8, 16);
/* 249 */     writeAsciiLong((entry.getName().length() + 1), 8, 16);
/* 250 */     writeAsciiLong(entry.getChksum(), 8, 16);
/* 251 */     writeCString(entry.getName());
/* 252 */     pad(entry.getHeaderPadCount());
/*     */   }
/*     */   
/*     */   private void writeOldAsciiEntry(CpioArchiveEntry entry) throws IOException {
/* 257 */     long inode = entry.getInode();
/* 258 */     long device = entry.getDevice();
/* 260 */     inode = device = 0L;
/* 263 */     inode = this.nextArtificalDeviceAndInode & 0x3FFFFL;
/* 264 */     device = this.nextArtificalDeviceAndInode++ >> 18L & 0x3FFFFL;
/* 266 */     this.nextArtificalDeviceAndInode = Math.max(this.nextArtificalDeviceAndInode, inode + 262144L * device) + 1L;
/* 272 */     writeAsciiLong(device, 6, 8);
/* 273 */     writeAsciiLong(inode, 6, 8);
/* 274 */     writeAsciiLong(entry.getMode(), 6, 8);
/* 275 */     writeAsciiLong(entry.getUID(), 6, 8);
/* 276 */     writeAsciiLong(entry.getGID(), 6, 8);
/* 277 */     writeAsciiLong(entry.getNumberOfLinks(), 6, 8);
/* 278 */     writeAsciiLong(entry.getRemoteDevice(), 6, 8);
/* 279 */     writeAsciiLong(entry.getTime(), 11, 8);
/* 280 */     writeAsciiLong((entry.getName().length() + 1), 6, 8);
/* 281 */     writeAsciiLong(entry.getSize(), 11, 8);
/* 282 */     writeCString(entry.getName());
/*     */   }
/*     */   
/*     */   private void writeOldBinaryEntry(CpioArchiveEntry entry, boolean swapHalfWord) throws IOException {
/* 287 */     long inode = entry.getInode();
/* 288 */     long device = entry.getDevice();
/* 290 */     inode = device = 0L;
/* 293 */     inode = this.nextArtificalDeviceAndInode & 0xFFFFL;
/* 294 */     device = this.nextArtificalDeviceAndInode++ >> 16L & 0xFFFFL;
/* 296 */     this.nextArtificalDeviceAndInode = Math.max(this.nextArtificalDeviceAndInode, inode + 65536L * device) + 1L;
/* 302 */     writeBinaryLong(device, 2, swapHalfWord);
/* 303 */     writeBinaryLong(inode, 2, swapHalfWord);
/* 304 */     writeBinaryLong(entry.getMode(), 2, swapHalfWord);
/* 305 */     writeBinaryLong(entry.getUID(), 2, swapHalfWord);
/* 306 */     writeBinaryLong(entry.getGID(), 2, swapHalfWord);
/* 307 */     writeBinaryLong(entry.getNumberOfLinks(), 2, swapHalfWord);
/* 308 */     writeBinaryLong(entry.getRemoteDevice(), 2, swapHalfWord);
/* 309 */     writeBinaryLong(entry.getTime(), 4, swapHalfWord);
/* 310 */     writeBinaryLong((entry.getName().length() + 1), 2, swapHalfWord);
/* 311 */     writeBinaryLong(entry.getSize(), 4, swapHalfWord);
/* 312 */     writeCString(entry.getName());
/* 313 */     pad(entry.getHeaderPadCount());
/*     */   }
/*     */   
/*     */   public void closeArchiveEntry() throws IOException {
/* 324 */     if (this.finished)
/* 325 */       throw new IOException("Stream has already been finished"); 
/* 328 */     ensureOpen();
/* 330 */     if (this.entry == null)
/* 331 */       throw new IOException("Trying to close non-existent entry"); 
/* 334 */     if (this.entry.getSize() != this.written)
/* 335 */       throw new IOException("invalid entry size (expected " + this.entry.getSize() + " but got " + this.written + " bytes)"); 
/* 339 */     pad(this.entry.getDataPadCount());
/* 340 */     if (this.entry.getFormat() == 2 && this.crc != this.entry.getChksum())
/* 342 */       throw new IOException("CRC Error"); 
/* 344 */     this.entry = null;
/* 345 */     this.crc = 0L;
/* 346 */     this.written = 0L;
/*     */   }
/*     */   
/*     */   public void write(byte[] b, int off, int len) throws IOException {
/* 366 */     ensureOpen();
/* 367 */     if (off < 0 || len < 0 || off > b.length - len)
/* 368 */       throw new IndexOutOfBoundsException(); 
/* 369 */     if (len == 0)
/*     */       return; 
/* 373 */     if (this.entry == null)
/* 374 */       throw new IOException("no current CPIO entry"); 
/* 376 */     if (this.written + len > this.entry.getSize())
/* 377 */       throw new IOException("attempt to write past end of STORED entry"); 
/* 379 */     this.out.write(b, off, len);
/* 380 */     this.written += len;
/* 381 */     if (this.entry.getFormat() == 2)
/* 382 */       for (int pos = 0; pos < len; pos++)
/* 383 */         this.crc += (b[pos] & 0xFF);  
/* 386 */     count(len);
/*     */   }
/*     */   
/*     */   public void finish() throws IOException {
/* 400 */     ensureOpen();
/* 401 */     if (this.finished)
/* 402 */       throw new IOException("This archive has already been finished"); 
/* 405 */     if (this.entry != null)
/* 406 */       throw new IOException("This archive contains unclosed entries."); 
/* 408 */     this.entry = new CpioArchiveEntry(this.entryFormat);
/* 409 */     this.entry.setName("TRAILER!!!");
/* 410 */     this.entry.setNumberOfLinks(1L);
/* 411 */     writeHeader(this.entry);
/* 412 */     closeArchiveEntry();
/* 414 */     int lengthOfLastBlock = (int)(getBytesWritten() % this.blockSize);
/* 415 */     if (lengthOfLastBlock != 0)
/* 416 */       pad(this.blockSize - lengthOfLastBlock); 
/* 419 */     this.finished = true;
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 431 */     if (!this.finished)
/* 432 */       finish(); 
/* 435 */     if (!this.closed) {
/* 436 */       this.out.close();
/* 437 */       this.closed = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void pad(int count) throws IOException {
/* 442 */     if (count > 0) {
/* 443 */       byte[] buff = new byte[count];
/* 444 */       this.out.write(buff);
/* 445 */       count(count);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void writeBinaryLong(long number, int length, boolean swapHalfWord) throws IOException {
/* 451 */     byte[] tmp = CpioUtil.long2byteArray(number, length, swapHalfWord);
/* 452 */     this.out.write(tmp);
/* 453 */     count(tmp.length);
/*     */   }
/*     */   
/*     */   private void writeAsciiLong(long number, int length, int radix) throws IOException {
/*     */     String tmpStr;
/* 458 */     StringBuffer tmp = new StringBuffer();
/* 460 */     if (radix == 16) {
/* 461 */       tmp.append(Long.toHexString(number));
/* 462 */     } else if (radix == 8) {
/* 463 */       tmp.append(Long.toOctalString(number));
/*     */     } else {
/* 465 */       tmp.append(Long.toString(number));
/*     */     } 
/* 468 */     if (tmp.length() <= length) {
/* 469 */       long insertLength = (length - tmp.length());
/* 470 */       for (int pos = 0; pos < insertLength; pos++)
/* 471 */         tmp.insert(0, "0"); 
/* 473 */       tmpStr = tmp.toString();
/*     */     } else {
/* 475 */       tmpStr = tmp.substring(tmp.length() - length);
/*     */     } 
/* 477 */     byte[] b = ArchiveUtils.toAsciiBytes(tmpStr);
/* 478 */     this.out.write(b);
/* 479 */     count(b.length);
/*     */   }
/*     */   
/*     */   private void writeCString(String str) throws IOException {
/* 488 */     byte[] b = ArchiveUtils.toAsciiBytes(str);
/* 489 */     this.out.write(b);
/* 490 */     this.out.write(0);
/* 491 */     count(b.length + 1);
/*     */   }
/*     */   
/*     */   public ArchiveEntry createArchiveEntry(File inputFile, String entryName) throws IOException {
/* 502 */     if (this.finished)
/* 503 */       throw new IOException("Stream has already been finished"); 
/* 505 */     return new CpioArchiveEntry(inputFile, entryName);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\archivers\cpio\CpioArchiveOutputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */