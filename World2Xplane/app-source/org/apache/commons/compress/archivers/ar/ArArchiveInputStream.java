/*     */ package org.apache.commons.compress.archivers.ar;
/*     */ 
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import org.apache.commons.compress.archivers.ArchiveEntry;
/*     */ import org.apache.commons.compress.archivers.ArchiveInputStream;
/*     */ import org.apache.commons.compress.utils.ArchiveUtils;
/*     */ 
/*     */ public class ArArchiveInputStream extends ArchiveInputStream {
/*     */   private final InputStream input;
/*     */   
/*  38 */   private long offset = 0L;
/*     */   
/*     */   private boolean closed;
/*     */   
/*  45 */   private ArArchiveEntry currentEntry = null;
/*     */   
/*  48 */   private byte[] namebuffer = null;
/*     */   
/*  54 */   private long entryOffset = -1L;
/*     */   
/*     */   static final String BSD_LONGNAME_PREFIX = "#1/";
/*     */   
/*     */   public ArArchiveInputStream(InputStream pInput) {
/*  63 */     this.input = pInput;
/*  64 */     this.closed = false;
/*     */   }
/*     */   
/*     */   public ArArchiveEntry getNextArEntry() throws IOException {
/*  75 */     if (this.currentEntry != null) {
/*  76 */       long entryEnd = this.entryOffset + this.currentEntry.getLength();
/*  77 */       while (this.offset < entryEnd) {
/*  78 */         int x = read();
/*  79 */         if (x == -1)
/*  82 */           return null; 
/*     */       } 
/*  85 */       this.currentEntry = null;
/*     */     } 
/*  88 */     if (this.offset == 0L) {
/*  89 */       byte[] arrayOfByte1 = ArchiveUtils.toAsciiBytes("!<arch>\n");
/*  90 */       byte[] arrayOfByte2 = new byte[arrayOfByte1.length];
/*  91 */       int j = read(arrayOfByte2);
/*  92 */       if (j != arrayOfByte1.length)
/*  93 */         throw new IOException("failed to read header. Occured at byte: " + getBytesRead()); 
/*  95 */       for (int k = 0; k < arrayOfByte1.length; k++) {
/*  96 */         if (arrayOfByte1[k] != arrayOfByte2[k])
/*  97 */           throw new IOException("invalid header " + ArchiveUtils.toAsciiString(arrayOfByte2)); 
/*     */       } 
/*     */     } 
/* 102 */     if (this.offset % 2L != 0L && read() < 0)
/* 104 */       return null; 
/* 107 */     if (this.input.available() == 0)
/* 108 */       return null; 
/* 111 */     byte[] name = new byte[16];
/* 112 */     byte[] lastmodified = new byte[12];
/* 113 */     byte[] userid = new byte[6];
/* 114 */     byte[] groupid = new byte[6];
/* 115 */     byte[] filemode = new byte[8];
/* 116 */     byte[] length = new byte[10];
/* 118 */     read(name);
/* 119 */     read(lastmodified);
/* 120 */     read(userid);
/* 121 */     read(groupid);
/* 122 */     read(filemode);
/* 123 */     read(length);
/* 126 */     byte[] expected = ArchiveUtils.toAsciiBytes("`\n");
/* 127 */     byte[] realized = new byte[expected.length];
/* 128 */     int read = read(realized);
/* 129 */     if (read != expected.length)
/* 130 */       throw new IOException("failed to read entry trailer. Occured at byte: " + getBytesRead()); 
/* 132 */     for (int i = 0; i < expected.length; i++) {
/* 133 */       if (expected[i] != realized[i])
/* 134 */         throw new IOException("invalid entry trailer. not read the content? Occured at byte: " + getBytesRead()); 
/*     */     } 
/* 139 */     this.entryOffset = this.offset;
/* 144 */     String temp = ArchiveUtils.toAsciiString(name).trim();
/* 145 */     long len = asLong(length);
/* 147 */     if (isGNUStringTable(temp)) {
/* 148 */       this.currentEntry = readGNUStringTable(length);
/* 149 */       return getNextArEntry();
/*     */     } 
/* 150 */     if (temp.endsWith("/")) {
/* 151 */       temp = temp.substring(0, temp.length() - 1);
/* 152 */     } else if (isGNULongName(temp)) {
/* 153 */       int offset = Integer.parseInt(temp.substring(1));
/* 154 */       temp = getExtendedName(offset);
/* 155 */     } else if (isBSDLongName(temp)) {
/* 156 */       temp = getBSDLongName(temp);
/* 160 */       int nameLen = temp.length();
/* 161 */       len -= nameLen;
/* 162 */       this.entryOffset += nameLen;
/*     */     } 
/* 165 */     this.currentEntry = new ArArchiveEntry(temp, len, asInt(userid, true), asInt(groupid, true), asInt(filemode, 8), asLong(lastmodified));
/* 168 */     return this.currentEntry;
/*     */   }
/*     */   
/*     */   private String getExtendedName(int offset) throws IOException {
/* 179 */     if (this.namebuffer == null)
/* 180 */       throw new IOException("Cannot process GNU long filename as no // record was found"); 
/* 182 */     for (int i = offset; i < this.namebuffer.length; i++) {
/* 183 */       if (this.namebuffer[i] == 10) {
/* 184 */         if (this.namebuffer[i - 1] == 47)
/* 185 */           i--; 
/* 187 */         return ArchiveUtils.toAsciiString(this.namebuffer, offset, i - offset);
/*     */       } 
/*     */     } 
/* 190 */     throw new IOException("Failed to read entry: " + offset);
/*     */   }
/*     */   
/*     */   private long asLong(byte[] input) {
/* 193 */     return Long.parseLong(ArchiveUtils.toAsciiString(input).trim());
/*     */   }
/*     */   
/*     */   private int asInt(byte[] input) {
/* 197 */     return asInt(input, 10, false);
/*     */   }
/*     */   
/*     */   private int asInt(byte[] input, boolean treatBlankAsZero) {
/* 201 */     return asInt(input, 10, treatBlankAsZero);
/*     */   }
/*     */   
/*     */   private int asInt(byte[] input, int base) {
/* 205 */     return asInt(input, base, false);
/*     */   }
/*     */   
/*     */   private int asInt(byte[] input, int base, boolean treatBlankAsZero) {
/* 209 */     String string = ArchiveUtils.toAsciiString(input).trim();
/* 210 */     if (string.length() == 0 && treatBlankAsZero)
/* 211 */       return 0; 
/* 213 */     return Integer.parseInt(string, base);
/*     */   }
/*     */   
/*     */   public ArchiveEntry getNextEntry() throws IOException {
/* 224 */     return getNextArEntry();
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 234 */     if (!this.closed) {
/* 235 */       this.closed = true;
/* 236 */       this.input.close();
/*     */     } 
/* 238 */     this.currentEntry = null;
/*     */   }
/*     */   
/*     */   public int read(byte[] b, int off, int len) throws IOException {
/* 248 */     int toRead = len;
/* 249 */     if (this.currentEntry != null) {
/* 250 */       long entryEnd = this.entryOffset + this.currentEntry.getLength();
/* 251 */       if (len > 0 && entryEnd > this.offset) {
/* 252 */         toRead = (int)Math.min(len, entryEnd - this.offset);
/*     */       } else {
/* 254 */         return -1;
/*     */       } 
/*     */     } 
/* 257 */     int ret = this.input.read(b, off, toRead);
/* 258 */     count(ret);
/* 259 */     this.offset += ((ret > 0) ? ret : 0L);
/* 260 */     return ret;
/*     */   }
/*     */   
/*     */   public static boolean matches(byte[] signature, int length) {
/* 276 */     if (length < 8)
/* 277 */       return false; 
/* 279 */     if (signature[0] != 33)
/* 280 */       return false; 
/* 282 */     if (signature[1] != 60)
/* 283 */       return false; 
/* 285 */     if (signature[2] != 97)
/* 286 */       return false; 
/* 288 */     if (signature[3] != 114)
/* 289 */       return false; 
/* 291 */     if (signature[4] != 99)
/* 292 */       return false; 
/* 294 */     if (signature[5] != 104)
/* 295 */       return false; 
/* 297 */     if (signature[6] != 62)
/* 298 */       return false; 
/* 300 */     if (signature[7] != 10)
/* 301 */       return false; 
/* 304 */     return true;
/*     */   }
/*     */   
/* 308 */   private static final int BSD_LONGNAME_PREFIX_LEN = "#1/".length();
/*     */   
/*     */   private static final String BSD_LONGNAME_PATTERN = "^#1/\\d+";
/*     */   
/*     */   private static final String GNU_STRING_TABLE_NAME = "//";
/*     */   
/*     */   private static final String GNU_LONGNAME_PATTERN = "^/\\d+";
/*     */   
/*     */   private static boolean isBSDLongName(String name) {
/* 336 */     return (name != null && name.matches("^#1/\\d+"));
/*     */   }
/*     */   
/*     */   private String getBSDLongName(String bsdLongName) throws IOException {
/* 348 */     int nameLen = Integer.parseInt(bsdLongName.substring(BSD_LONGNAME_PREFIX_LEN));
/* 350 */     byte[] name = new byte[nameLen];
/* 351 */     int read = 0, readNow = 0;
/* 352 */     while ((readNow = this.input.read(name, read, nameLen - read)) >= 0) {
/* 353 */       read += readNow;
/* 354 */       count(readNow);
/* 355 */       if (read == nameLen)
/*     */         break; 
/*     */     } 
/* 359 */     if (read != nameLen)
/* 360 */       throw new EOFException(); 
/* 362 */     return ArchiveUtils.toAsciiString(name);
/*     */   }
/*     */   
/*     */   private static boolean isGNUStringTable(String name) {
/* 385 */     return "//".equals(name);
/*     */   }
/*     */   
/*     */   private ArArchiveEntry readGNUStringTable(byte[] length) throws IOException {
/* 394 */     int bufflen = asInt(length);
/* 395 */     this.namebuffer = new byte[bufflen];
/* 396 */     int read = read(this.namebuffer, 0, bufflen);
/* 397 */     if (read != bufflen)
/* 398 */       throw new IOException("Failed to read complete // record: expected=" + bufflen + " read=" + read); 
/* 401 */     return new ArArchiveEntry("//", bufflen);
/*     */   }
/*     */   
/*     */   private boolean isGNULongName(String name) {
/* 413 */     return (name != null && name.matches("^/\\d+"));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\archivers\ar\ArArchiveInputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */