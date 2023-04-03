/*     */ package org.apache.commons.compress.archivers.cpio;
/*     */ 
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import org.apache.commons.compress.archivers.ArchiveEntry;
/*     */ import org.apache.commons.compress.archivers.ArchiveInputStream;
/*     */ import org.apache.commons.compress.utils.ArchiveUtils;
/*     */ 
/*     */ public class CpioArchiveInputStream extends ArchiveInputStream implements CpioConstants {
/*     */   private boolean closed = false;
/*     */   
/*     */   private CpioArchiveEntry entry;
/*     */   
/*  70 */   private long entryBytesRead = 0L;
/*     */   
/*     */   private boolean entryEOF = false;
/*     */   
/*  74 */   private final byte[] tmpbuf = new byte[4096];
/*     */   
/*  76 */   private long crc = 0L;
/*     */   
/*     */   private final InputStream in;
/*     */   
/*     */   public CpioArchiveInputStream(InputStream in) {
/*  87 */     this.in = in;
/*     */   }
/*     */   
/*     */   public int available() throws IOException {
/* 104 */     ensureOpen();
/* 105 */     if (this.entryEOF)
/* 106 */       return 0; 
/* 108 */     return 1;
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 119 */     if (!this.closed) {
/* 120 */       this.in.close();
/* 121 */       this.closed = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void closeEntry() throws IOException {
/* 134 */     ensureOpen();
/* 135 */     while (read(this.tmpbuf, 0, this.tmpbuf.length) != -1);
/* 139 */     this.entryEOF = true;
/*     */   }
/*     */   
/*     */   private void ensureOpen() throws IOException {
/* 149 */     if (this.closed)
/* 150 */       throw new IOException("Stream closed"); 
/*     */   }
/*     */   
/*     */   public CpioArchiveEntry getNextCPIOEntry() throws IOException {
/* 164 */     ensureOpen();
/* 165 */     if (this.entry != null)
/* 166 */       closeEntry(); 
/* 168 */     byte[] magic = new byte[2];
/* 169 */     readFully(magic, 0, magic.length);
/* 170 */     if (CpioUtil.byteArray2long(magic, false) == 29127L) {
/* 171 */       this.entry = readOldBinaryEntry(false);
/* 172 */     } else if (CpioUtil.byteArray2long(magic, true) == 29127L) {
/* 173 */       this.entry = readOldBinaryEntry(true);
/*     */     } else {
/* 175 */       byte[] more_magic = new byte[4];
/* 176 */       readFully(more_magic, 0, more_magic.length);
/* 177 */       byte[] tmp = new byte[6];
/* 178 */       System.arraycopy(magic, 0, tmp, 0, magic.length);
/* 179 */       System.arraycopy(more_magic, 0, tmp, magic.length, more_magic.length);
/* 181 */       String magicString = ArchiveUtils.toAsciiString(tmp);
/* 182 */       if (magicString.equals("070701")) {
/* 183 */         this.entry = readNewEntry(false);
/* 184 */       } else if (magicString.equals("070702")) {
/* 185 */         this.entry = readNewEntry(true);
/* 186 */       } else if (magicString.equals("070707")) {
/* 187 */         this.entry = readOldAsciiEntry();
/*     */       } else {
/* 189 */         throw new IOException("Unknown magic [" + magicString + "]. Occured at byte: " + getBytesRead());
/*     */       } 
/*     */     } 
/* 193 */     this.entryBytesRead = 0L;
/* 194 */     this.entryEOF = false;
/* 195 */     this.crc = 0L;
/* 197 */     if (this.entry.getName().equals("TRAILER!!!")) {
/* 198 */       this.entryEOF = true;
/* 199 */       return null;
/*     */     } 
/* 201 */     return this.entry;
/*     */   }
/*     */   
/*     */   private void skip(int bytes) throws IOException {
/* 205 */     byte[] buff = new byte[4];
/* 206 */     if (bytes > 0)
/* 207 */       readFully(buff, 0, bytes); 
/*     */   }
/*     */   
/*     */   public int read(byte[] b, int off, int len) throws IOException {
/* 230 */     ensureOpen();
/* 231 */     if (off < 0 || len < 0 || off > b.length - len)
/* 232 */       throw new IndexOutOfBoundsException(); 
/* 233 */     if (len == 0)
/* 234 */       return 0; 
/* 237 */     if (this.entry == null || this.entryEOF)
/* 238 */       return -1; 
/* 240 */     if (this.entryBytesRead == this.entry.getSize()) {
/* 241 */       skip(this.entry.getDataPadCount());
/* 242 */       this.entryEOF = true;
/* 243 */       if (this.entry.getFormat() == 2 && this.crc != this.entry.getChksum())
/* 245 */         throw new IOException("CRC Error. Occured at byte: " + getBytesRead()); 
/* 248 */       return -1;
/*     */     } 
/* 250 */     int tmplength = (int)Math.min(len, this.entry.getSize() - this.entryBytesRead);
/* 252 */     if (tmplength < 0)
/* 253 */       return -1; 
/* 256 */     int tmpread = readFully(b, off, tmplength);
/* 257 */     if (this.entry.getFormat() == 2)
/* 258 */       for (int pos = 0; pos < tmpread; pos++)
/* 259 */         this.crc += (b[pos] & 0xFF);  
/* 262 */     this.entryBytesRead += tmpread;
/* 264 */     return tmpread;
/*     */   }
/*     */   
/*     */   private final int readFully(byte[] b, int off, int len) throws IOException {
/* 269 */     if (len < 0)
/* 270 */       throw new IndexOutOfBoundsException(); 
/* 272 */     int n = 0;
/* 273 */     while (n < len) {
/* 274 */       int count = this.in.read(b, off + n, len - n);
/* 275 */       count(count);
/* 276 */       if (count < 0)
/* 277 */         throw new EOFException(); 
/* 279 */       n += count;
/*     */     } 
/* 281 */     return n;
/*     */   }
/*     */   
/*     */   private long readBinaryLong(int length, boolean swapHalfWord) throws IOException {
/* 286 */     byte[] tmp = new byte[length];
/* 287 */     readFully(tmp, 0, tmp.length);
/* 288 */     return CpioUtil.byteArray2long(tmp, swapHalfWord);
/*     */   }
/*     */   
/*     */   private long readAsciiLong(int length, int radix) throws IOException {
/* 293 */     byte[] tmpBuffer = new byte[length];
/* 294 */     readFully(tmpBuffer, 0, tmpBuffer.length);
/* 295 */     return Long.parseLong(ArchiveUtils.toAsciiString(tmpBuffer), radix);
/*     */   }
/*     */   
/*     */   private CpioArchiveEntry readNewEntry(boolean hasCrc) throws IOException {
/*     */     CpioArchiveEntry ret;
/* 301 */     if (hasCrc) {
/* 302 */       ret = new CpioArchiveEntry((short)2);
/*     */     } else {
/* 304 */       ret = new CpioArchiveEntry((short)1);
/*     */     } 
/* 307 */     ret.setInode(readAsciiLong(8, 16));
/* 308 */     long mode = readAsciiLong(8, 16);
/* 309 */     if (mode != 0L)
/* 310 */       ret.setMode(mode); 
/* 312 */     ret.setUID(readAsciiLong(8, 16));
/* 313 */     ret.setGID(readAsciiLong(8, 16));
/* 314 */     ret.setNumberOfLinks(readAsciiLong(8, 16));
/* 315 */     ret.setTime(readAsciiLong(8, 16));
/* 316 */     ret.setSize(readAsciiLong(8, 16));
/* 317 */     ret.setDeviceMaj(readAsciiLong(8, 16));
/* 318 */     ret.setDeviceMin(readAsciiLong(8, 16));
/* 319 */     ret.setRemoteDeviceMaj(readAsciiLong(8, 16));
/* 320 */     ret.setRemoteDeviceMin(readAsciiLong(8, 16));
/* 321 */     long namesize = readAsciiLong(8, 16);
/* 322 */     ret.setChksum(readAsciiLong(8, 16));
/* 323 */     String name = readCString((int)namesize);
/* 324 */     ret.setName(name);
/* 325 */     if (mode == 0L && !name.equals("TRAILER!!!"))
/* 326 */       throw new IOException("Mode 0 only allowed in the trailer. Found entry name: " + name + " Occured at byte: " + getBytesRead()); 
/* 328 */     skip(ret.getHeaderPadCount());
/* 330 */     return ret;
/*     */   }
/*     */   
/*     */   private CpioArchiveEntry readOldAsciiEntry() throws IOException {
/* 334 */     CpioArchiveEntry ret = new CpioArchiveEntry((short)4);
/* 336 */     ret.setDevice(readAsciiLong(6, 8));
/* 337 */     ret.setInode(readAsciiLong(6, 8));
/* 338 */     long mode = readAsciiLong(6, 8);
/* 339 */     if (mode != 0L)
/* 340 */       ret.setMode(mode); 
/* 342 */     ret.setUID(readAsciiLong(6, 8));
/* 343 */     ret.setGID(readAsciiLong(6, 8));
/* 344 */     ret.setNumberOfLinks(readAsciiLong(6, 8));
/* 345 */     ret.setRemoteDevice(readAsciiLong(6, 8));
/* 346 */     ret.setTime(readAsciiLong(11, 8));
/* 347 */     long namesize = readAsciiLong(6, 8);
/* 348 */     ret.setSize(readAsciiLong(11, 8));
/* 349 */     String name = readCString((int)namesize);
/* 350 */     ret.setName(name);
/* 351 */     if (mode == 0L && !name.equals("TRAILER!!!"))
/* 352 */       throw new IOException("Mode 0 only allowed in the trailer. Found entry: " + name + " Occured at byte: " + getBytesRead()); 
/* 355 */     return ret;
/*     */   }
/*     */   
/*     */   private CpioArchiveEntry readOldBinaryEntry(boolean swapHalfWord) throws IOException {
/* 360 */     CpioArchiveEntry ret = new CpioArchiveEntry((short)8);
/* 362 */     ret.setDevice(readBinaryLong(2, swapHalfWord));
/* 363 */     ret.setInode(readBinaryLong(2, swapHalfWord));
/* 364 */     long mode = readBinaryLong(2, swapHalfWord);
/* 365 */     if (mode != 0L)
/* 366 */       ret.setMode(mode); 
/* 368 */     ret.setUID(readBinaryLong(2, swapHalfWord));
/* 369 */     ret.setGID(readBinaryLong(2, swapHalfWord));
/* 370 */     ret.setNumberOfLinks(readBinaryLong(2, swapHalfWord));
/* 371 */     ret.setRemoteDevice(readBinaryLong(2, swapHalfWord));
/* 372 */     ret.setTime(readBinaryLong(4, swapHalfWord));
/* 373 */     long namesize = readBinaryLong(2, swapHalfWord);
/* 374 */     ret.setSize(readBinaryLong(4, swapHalfWord));
/* 375 */     String name = readCString((int)namesize);
/* 376 */     ret.setName(name);
/* 377 */     if (mode == 0L && !name.equals("TRAILER!!!"))
/* 378 */       throw new IOException("Mode 0 only allowed in the trailer. Found entry: " + name + "Occured at byte: " + getBytesRead()); 
/* 380 */     skip(ret.getHeaderPadCount());
/* 382 */     return ret;
/*     */   }
/*     */   
/*     */   private String readCString(int length) throws IOException {
/* 386 */     byte[] tmpBuffer = new byte[length];
/* 387 */     readFully(tmpBuffer, 0, tmpBuffer.length);
/* 388 */     return new String(tmpBuffer, 0, tmpBuffer.length - 1);
/*     */   }
/*     */   
/*     */   public long skip(long n) throws IOException {
/* 404 */     if (n < 0L)
/* 405 */       throw new IllegalArgumentException("negative skip length"); 
/* 407 */     ensureOpen();
/* 408 */     int max = (int)Math.min(n, 2147483647L);
/* 409 */     int total = 0;
/* 411 */     while (total < max) {
/* 412 */       int len = max - total;
/* 413 */       if (len > this.tmpbuf.length)
/* 414 */         len = this.tmpbuf.length; 
/* 416 */       len = read(this.tmpbuf, 0, len);
/* 417 */       if (len == -1) {
/* 418 */         this.entryEOF = true;
/*     */         break;
/*     */       } 
/* 421 */       total += len;
/*     */     } 
/* 423 */     return total;
/*     */   }
/*     */   
/*     */   public ArchiveEntry getNextEntry() throws IOException {
/* 429 */     return getNextCPIOEntry();
/*     */   }
/*     */   
/*     */   public static boolean matches(byte[] signature, int length) {
/* 446 */     if (length < 6)
/* 447 */       return false; 
/* 451 */     if (signature[0] == 113 && (signature[1] & 0xFF) == 199)
/* 452 */       return true; 
/* 454 */     if (signature[1] == 113 && (signature[0] & 0xFF) == 199)
/* 455 */       return true; 
/* 460 */     if (signature[0] != 48)
/* 461 */       return false; 
/* 463 */     if (signature[1] != 55)
/* 464 */       return false; 
/* 466 */     if (signature[2] != 48)
/* 467 */       return false; 
/* 469 */     if (signature[3] != 55)
/* 470 */       return false; 
/* 472 */     if (signature[4] != 48)
/* 473 */       return false; 
/* 476 */     if (signature[5] == 49)
/* 477 */       return true; 
/* 479 */     if (signature[5] == 50)
/* 480 */       return true; 
/* 482 */     if (signature[5] == 55)
/* 483 */       return true; 
/* 486 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\archivers\cpio\CpioArchiveInputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */