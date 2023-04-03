/*     */ package org.apache.commons.compress.compressors.gzip;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.zip.CRC32;
/*     */ import java.util.zip.DataFormatException;
/*     */ import java.util.zip.Inflater;
/*     */ import org.apache.commons.compress.compressors.CompressorInputStream;
/*     */ 
/*     */ public class GzipCompressorInputStream extends CompressorInputStream {
/*     */   private static final int FHCRC = 2;
/*     */   
/*     */   private static final int FEXTRA = 4;
/*     */   
/*     */   private static final int FNAME = 8;
/*     */   
/*     */   private static final int FCOMMENT = 16;
/*     */   
/*     */   private static final int FRESERVED = 224;
/*     */   
/*     */   private final InputStream in;
/*     */   
/*     */   private final boolean decompressConcatenated;
/*     */   
/*  63 */   private final byte[] buf = new byte[8192];
/*     */   
/*  66 */   private int bufUsed = 0;
/*     */   
/*  69 */   private Inflater inf = new Inflater(true);
/*     */   
/*  72 */   private CRC32 crc = new CRC32();
/*     */   
/*     */   private int memberSize;
/*     */   
/*     */   private boolean endReached = false;
/*     */   
/*     */   public GzipCompressorInputStream(InputStream inputStream) throws IOException {
/*  94 */     this(inputStream, false);
/*     */   }
/*     */   
/*     */   public GzipCompressorInputStream(InputStream inputStream, boolean decompressConcatenated) throws IOException {
/* 122 */     if (inputStream.markSupported()) {
/* 123 */       this.in = inputStream;
/*     */     } else {
/* 125 */       this.in = new BufferedInputStream(inputStream);
/*     */     } 
/* 128 */     this.decompressConcatenated = decompressConcatenated;
/* 129 */     init(true);
/*     */   }
/*     */   
/*     */   private boolean init(boolean isFirstMember) throws IOException {
/* 133 */     assert isFirstMember || this.decompressConcatenated;
/* 136 */     int magic0 = this.in.read();
/* 137 */     int magic1 = this.in.read();
/* 141 */     if (magic0 == -1 && !isFirstMember)
/* 142 */       return false; 
/* 145 */     if (magic0 != 31 || magic1 != 139)
/* 146 */       throw new IOException(isFirstMember ? "Input is not in the .gz format" : "Garbage after a valid .gz stream"); 
/* 152 */     DataInputStream inData = new DataInputStream(this.in);
/* 153 */     int method = inData.readUnsignedByte();
/* 154 */     if (method != 8)
/* 155 */       throw new IOException("Unsupported compression method " + method + " in the .gz header"); 
/* 159 */     int flg = inData.readUnsignedByte();
/* 160 */     if ((flg & 0xE0) != 0)
/* 161 */       throw new IOException("Reserved flags are set in the .gz header"); 
/* 165 */     inData.readInt();
/* 166 */     inData.readUnsignedByte();
/* 167 */     inData.readUnsignedByte();
/* 170 */     if ((flg & 0x4) != 0) {
/* 171 */       int xlen = inData.readUnsignedByte();
/* 172 */       xlen |= inData.readUnsignedByte() << 8;
/* 177 */       while (xlen-- > 0)
/* 178 */         inData.readUnsignedByte(); 
/*     */     } 
/* 183 */     if ((flg & 0x8) != 0)
/* 184 */       readToNull(inData); 
/* 188 */     if ((flg & 0x10) != 0)
/* 189 */       readToNull(inData); 
/* 197 */     if ((flg & 0x2) != 0)
/* 198 */       inData.readShort(); 
/* 202 */     this.inf.reset();
/* 203 */     this.crc.reset();
/* 204 */     this.memberSize = 0;
/* 206 */     return true;
/*     */   }
/*     */   
/*     */   private void readToNull(DataInputStream inData) throws IOException {
/* 210 */     while (inData.readUnsignedByte() != 0);
/*     */   }
/*     */   
/*     */   public int read() throws IOException {
/* 216 */     byte[] buf = new byte[1];
/* 217 */     return (read(buf, 0, 1) == -1) ? -1 : (buf[0] & 0xFF);
/*     */   }
/*     */   
/*     */   public int read(byte[] b, int off, int len) throws IOException {
/* 227 */     if (this.endReached)
/* 228 */       return -1; 
/* 231 */     int size = 0;
/* 233 */     while (len > 0) {
/*     */       int ret;
/* 234 */       if (this.inf.needsInput()) {
/* 237 */         this.in.mark(this.buf.length);
/* 239 */         this.bufUsed = this.in.read(this.buf);
/* 240 */         if (this.bufUsed == -1)
/* 241 */           throw new EOFException(); 
/* 244 */         this.inf.setInput(this.buf, 0, this.bufUsed);
/*     */       } 
/*     */       try {
/* 249 */         ret = this.inf.inflate(b, off, len);
/* 250 */       } catch (DataFormatException e) {
/* 251 */         throw new IOException("Gzip-compressed data is corrupt");
/*     */       } 
/* 254 */       this.crc.update(b, off, ret);
/* 255 */       this.memberSize += ret;
/* 256 */       off += ret;
/* 257 */       len -= ret;
/* 258 */       size += ret;
/* 259 */       count(ret);
/* 261 */       if (this.inf.finished()) {
/* 267 */         this.in.reset();
/* 269 */         int skipAmount = this.bufUsed - this.inf.getRemaining();
/* 270 */         if (this.in.skip(skipAmount) != skipAmount)
/* 271 */           throw new IOException(); 
/* 274 */         this.bufUsed = 0;
/* 276 */         DataInputStream inData = new DataInputStream(this.in);
/* 279 */         long crcStored = 0L;
/* 280 */         for (int i = 0; i < 4; i++)
/* 281 */           crcStored |= inData.readUnsignedByte() << i * 8; 
/* 284 */         if (crcStored != this.crc.getValue())
/* 285 */           throw new IOException("Gzip-compressed data is corrupt (CRC32 error)"); 
/* 290 */         int isize = 0;
/* 291 */         for (int j = 0; j < 4; j++)
/* 292 */           isize |= inData.readUnsignedByte() << j * 8; 
/* 295 */         if (isize != this.memberSize)
/* 296 */           throw new IOException("Gzip-compressed data is corrupt(uncompressed size mismatch)"); 
/* 301 */         if (!this.decompressConcatenated || !init(false)) {
/* 302 */           this.inf.end();
/* 303 */           this.inf = null;
/* 304 */           this.endReached = true;
/* 305 */           return (size == 0) ? -1 : size;
/*     */         } 
/*     */       } 
/*     */     } 
/* 310 */     return size;
/*     */   }
/*     */   
/*     */   public static boolean matches(byte[] signature, int length) {
/* 324 */     if (length < 2)
/* 325 */       return false; 
/* 328 */     if (signature[0] != 31)
/* 329 */       return false; 
/* 332 */     if (signature[1] != -117)
/* 333 */       return false; 
/* 336 */     return true;
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 346 */     if (this.inf != null) {
/* 347 */       this.inf.end();
/* 348 */       this.inf = null;
/*     */     } 
/* 351 */     if (this.in != System.in)
/* 352 */       this.in.close(); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\compressors\gzip\GzipCompressorInputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */