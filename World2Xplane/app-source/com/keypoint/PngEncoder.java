/*     */ package com.keypoint;
/*     */ 
/*     */ import java.awt.Image;
/*     */ import java.awt.image.PixelGrabber;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.zip.CRC32;
/*     */ import java.util.zip.Deflater;
/*     */ import java.util.zip.DeflaterOutputStream;
/*     */ 
/*     */ public class PngEncoder {
/*     */   public static final boolean ENCODE_ALPHA = true;
/*     */   
/*     */   public static final boolean NO_ALPHA = false;
/*     */   
/*     */   public static final int FILTER_NONE = 0;
/*     */   
/*     */   public static final int FILTER_SUB = 1;
/*     */   
/*     */   public static final int FILTER_UP = 2;
/*     */   
/*     */   public static final int FILTER_LAST = 2;
/*     */   
/*  75 */   protected static final byte[] IHDR = new byte[] { 73, 72, 68, 82 };
/*     */   
/*  78 */   protected static final byte[] IDAT = new byte[] { 73, 68, 65, 84 };
/*     */   
/*  81 */   protected static final byte[] IEND = new byte[] { 73, 69, 78, 68 };
/*     */   
/*  83 */   protected static final byte[] PHYS = new byte[] { 112, 72, 89, 115 };
/*     */   
/*     */   protected byte[] pngBytes;
/*     */   
/*     */   protected byte[] priorRow;
/*     */   
/*     */   protected byte[] leftBytes;
/*     */   
/*     */   protected Image image;
/*     */   
/*     */   protected int width;
/*     */   
/*     */   protected int height;
/*     */   
/*     */   protected int bytePos;
/*     */   
/*     */   protected int maxPos;
/*     */   
/* 110 */   protected CRC32 crc = new CRC32();
/*     */   
/*     */   protected long crcValue;
/*     */   
/*     */   protected boolean encodeAlpha;
/*     */   
/*     */   protected int filter;
/*     */   
/*     */   protected int bytesPerPixel;
/*     */   
/* 125 */   private int xDpi = 0;
/*     */   
/* 128 */   private int yDpi = 0;
/*     */   
/* 131 */   private static float INCH_IN_METER_UNIT = 0.0254F;
/*     */   
/*     */   protected int compressionLevel;
/*     */   
/*     */   public PngEncoder() {
/* 143 */     this(null, false, 0, 0);
/*     */   }
/*     */   
/*     */   public PngEncoder(Image image) {
/* 154 */     this(image, false, 0, 0);
/*     */   }
/*     */   
/*     */   public PngEncoder(Image image, boolean encodeAlpha) {
/* 166 */     this(image, encodeAlpha, 0, 0);
/*     */   }
/*     */   
/*     */   public PngEncoder(Image image, boolean encodeAlpha, int whichFilter) {
/* 179 */     this(image, encodeAlpha, whichFilter, 0);
/*     */   }
/*     */   
/*     */   public PngEncoder(Image image, boolean encodeAlpha, int whichFilter, int compLevel) {
/* 196 */     this.image = image;
/* 197 */     this.encodeAlpha = encodeAlpha;
/* 198 */     setFilter(whichFilter);
/* 199 */     if (compLevel >= 0 && compLevel <= 9)
/* 200 */       this.compressionLevel = compLevel; 
/*     */   }
/*     */   
/*     */   public void setImage(Image image) {
/* 212 */     this.image = image;
/* 213 */     this.pngBytes = null;
/*     */   }
/*     */   
/*     */   public Image getImage() {
/* 220 */     return this.image;
/*     */   }
/*     */   
/*     */   public byte[] pngEncode(boolean encodeAlpha) {
/* 231 */     byte[] pngIdBytes = { -119, 80, 78, 71, 13, 10, 26, 10 };
/* 233 */     if (this.image == null)
/* 234 */       return null; 
/* 236 */     this.width = this.image.getWidth(null);
/* 237 */     this.height = this.image.getHeight(null);
/* 243 */     this.pngBytes = new byte[(this.width + 1) * this.height * 3 + 200];
/* 248 */     this.maxPos = 0;
/* 250 */     this.bytePos = writeBytes(pngIdBytes, 0);
/* 252 */     writeHeader();
/* 253 */     writeResolution();
/* 255 */     if (writeImageData()) {
/* 256 */       writeEnd();
/* 257 */       this.pngBytes = resizeByteArray(this.pngBytes, this.maxPos);
/*     */     } else {
/* 260 */       this.pngBytes = null;
/*     */     } 
/* 262 */     return this.pngBytes;
/*     */   }
/*     */   
/*     */   public byte[] pngEncode() {
/* 272 */     return pngEncode(this.encodeAlpha);
/*     */   }
/*     */   
/*     */   public void setEncodeAlpha(boolean encodeAlpha) {
/* 281 */     this.encodeAlpha = encodeAlpha;
/*     */   }
/*     */   
/*     */   public boolean getEncodeAlpha() {
/* 290 */     return this.encodeAlpha;
/*     */   }
/*     */   
/*     */   public void setFilter(int whichFilter) {
/* 299 */     this.filter = 0;
/* 300 */     if (whichFilter <= 2)
/* 301 */       this.filter = whichFilter; 
/*     */   }
/*     */   
/*     */   public int getFilter() {
/* 311 */     return this.filter;
/*     */   }
/*     */   
/*     */   public void setCompressionLevel(int level) {
/* 321 */     if (level >= 0 && level <= 9)
/* 322 */       this.compressionLevel = level; 
/*     */   }
/*     */   
/*     */   public int getCompressionLevel() {
/* 332 */     return this.compressionLevel;
/*     */   }
/*     */   
/*     */   protected byte[] resizeByteArray(byte[] array, int newLength) {
/* 344 */     byte[] newArray = new byte[newLength];
/* 345 */     int oldLength = array.length;
/* 347 */     System.arraycopy(array, 0, newArray, 0, Math.min(oldLength, newLength));
/* 348 */     return newArray;
/*     */   }
/*     */   
/*     */   protected int writeBytes(byte[] data, int offset) {
/* 363 */     this.maxPos = Math.max(this.maxPos, offset + data.length);
/* 364 */     if (data.length + offset > this.pngBytes.length)
/* 365 */       this.pngBytes = resizeByteArray(this.pngBytes, this.pngBytes.length + Math.max(1000, data.length)); 
/* 368 */     System.arraycopy(data, 0, this.pngBytes, offset, data.length);
/* 369 */     return offset + data.length;
/*     */   }
/*     */   
/*     */   protected int writeBytes(byte[] data, int nBytes, int offset) {
/* 385 */     this.maxPos = Math.max(this.maxPos, offset + nBytes);
/* 386 */     if (nBytes + offset > this.pngBytes.length)
/* 387 */       this.pngBytes = resizeByteArray(this.pngBytes, this.pngBytes.length + Math.max(1000, nBytes)); 
/* 390 */     System.arraycopy(data, 0, this.pngBytes, offset, nBytes);
/* 391 */     return offset + nBytes;
/*     */   }
/*     */   
/*     */   protected int writeInt2(int n, int offset) {
/* 402 */     byte[] temp = { (byte)(n >> 8 & 0xFF), (byte)(n & 0xFF) };
/* 403 */     return writeBytes(temp, offset);
/*     */   }
/*     */   
/*     */   protected int writeInt4(int n, int offset) {
/* 414 */     byte[] temp = { (byte)(n >> 24 & 0xFF), (byte)(n >> 16 & 0xFF), (byte)(n >> 8 & 0xFF), (byte)(n & 0xFF) };
/* 418 */     return writeBytes(temp, offset);
/*     */   }
/*     */   
/*     */   protected int writeByte(int b, int offset) {
/* 429 */     byte[] temp = { (byte)b };
/* 430 */     return writeBytes(temp, offset);
/*     */   }
/*     */   
/*     */   protected void writeHeader() {
/* 438 */     int startPos = this.bytePos = writeInt4(13, this.bytePos);
/* 439 */     this.bytePos = writeBytes(IHDR, this.bytePos);
/* 440 */     this.width = this.image.getWidth(null);
/* 441 */     this.height = this.image.getHeight(null);
/* 442 */     this.bytePos = writeInt4(this.width, this.bytePos);
/* 443 */     this.bytePos = writeInt4(this.height, this.bytePos);
/* 444 */     this.bytePos = writeByte(8, this.bytePos);
/* 445 */     this.bytePos = writeByte(this.encodeAlpha ? 6 : 2, this.bytePos);
/* 447 */     this.bytePos = writeByte(0, this.bytePos);
/* 448 */     this.bytePos = writeByte(0, this.bytePos);
/* 449 */     this.bytePos = writeByte(0, this.bytePos);
/* 450 */     this.crc.reset();
/* 451 */     this.crc.update(this.pngBytes, startPos, this.bytePos - startPos);
/* 452 */     this.crcValue = this.crc.getValue();
/* 453 */     this.bytePos = writeInt4((int)this.crcValue, this.bytePos);
/*     */   }
/*     */   
/*     */   protected void filterSub(byte[] pixels, int startPos, int width) {
/* 467 */     int offset = this.bytesPerPixel;
/* 468 */     int actualStart = startPos + offset;
/* 469 */     int nBytes = width * this.bytesPerPixel;
/* 470 */     int leftInsert = offset;
/* 471 */     int leftExtract = 0;
/* 473 */     for (int i = actualStart; i < startPos + nBytes; i++) {
/* 474 */       this.leftBytes[leftInsert] = pixels[i];
/* 475 */       pixels[i] = (byte)((pixels[i] - this.leftBytes[leftExtract]) % 256);
/* 477 */       leftInsert = (leftInsert + 1) % 15;
/* 478 */       leftExtract = (leftExtract + 1) % 15;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void filterUp(byte[] pixels, int startPos, int width) {
/* 492 */     int nBytes = width * this.bytesPerPixel;
/* 494 */     for (int i = 0; i < nBytes; i++) {
/* 495 */       byte currentByte = pixels[startPos + i];
/* 496 */       pixels[startPos + i] = (byte)((pixels[startPos + i] - this.priorRow[i]) % 256);
/* 498 */       this.priorRow[i] = currentByte;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean writeImageData() {
/* 512 */     int rowsLeft = this.height;
/* 513 */     int startRow = 0;
/* 528 */     this.bytesPerPixel = this.encodeAlpha ? 4 : 3;
/* 530 */     Deflater scrunch = new Deflater(this.compressionLevel);
/* 531 */     ByteArrayOutputStream outBytes = new ByteArrayOutputStream(1024);
/* 533 */     DeflaterOutputStream compBytes = new DeflaterOutputStream(outBytes, scrunch);
/*     */     try {
/* 536 */       while (rowsLeft > 0) {
/* 537 */         int nRows = Math.min(32767 / this.width * (this.bytesPerPixel + 1), rowsLeft);
/* 539 */         nRows = Math.max(nRows, 1);
/* 541 */         int[] pixels = new int[this.width * nRows];
/* 543 */         PixelGrabber pg = new PixelGrabber(this.image, 0, startRow, this.width, nRows, pixels, 0, this.width);
/*     */         try {
/* 546 */           pg.grabPixels();
/* 548 */         } catch (Exception e) {
/* 549 */           System.err.println("interrupted waiting for pixels!");
/* 550 */           return false;
/*     */         } 
/* 552 */         if ((pg.getStatus() & 0x80) != 0) {
/* 553 */           System.err.println("image fetch aborted or errored");
/* 554 */           return false;
/*     */         } 
/* 561 */         byte[] scanLines = new byte[this.width * nRows * this.bytesPerPixel + nRows];
/* 564 */         if (this.filter == 1)
/* 565 */           this.leftBytes = new byte[16]; 
/* 567 */         if (this.filter == 2)
/* 568 */           this.priorRow = new byte[this.width * this.bytesPerPixel]; 
/* 571 */         int scanPos = 0;
/* 572 */         int startPos = 1;
/* 573 */         for (int i = 0; i < this.width * nRows; i++) {
/* 574 */           if (i % this.width == 0) {
/* 575 */             scanLines[scanPos++] = (byte)this.filter;
/* 576 */             startPos = scanPos;
/*     */           } 
/* 578 */           scanLines[scanPos++] = (byte)(pixels[i] >> 16 & 0xFF);
/* 579 */           scanLines[scanPos++] = (byte)(pixels[i] >> 8 & 0xFF);
/* 580 */           scanLines[scanPos++] = (byte)(pixels[i] & 0xFF);
/* 581 */           if (this.encodeAlpha)
/* 582 */             scanLines[scanPos++] = (byte)(pixels[i] >> 24 & 0xFF); 
/* 585 */           if (i % this.width == this.width - 1 && this.filter != 0) {
/* 587 */             if (this.filter == 1)
/* 588 */               filterSub(scanLines, startPos, this.width); 
/* 590 */             if (this.filter == 2)
/* 591 */               filterUp(scanLines, startPos, this.width); 
/*     */           } 
/*     */         } 
/* 599 */         compBytes.write(scanLines, 0, scanPos);
/* 601 */         startRow += nRows;
/* 602 */         rowsLeft -= nRows;
/*     */       } 
/* 604 */       compBytes.close();
/* 609 */       byte[] compressedLines = outBytes.toByteArray();
/* 610 */       int nCompressed = compressedLines.length;
/* 612 */       this.crc.reset();
/* 613 */       this.bytePos = writeInt4(nCompressed, this.bytePos);
/* 614 */       this.bytePos = writeBytes(IDAT, this.bytePos);
/* 615 */       this.crc.update(IDAT);
/* 616 */       this.bytePos = writeBytes(compressedLines, nCompressed, this.bytePos);
/* 618 */       this.crc.update(compressedLines, 0, nCompressed);
/* 620 */       this.crcValue = this.crc.getValue();
/* 621 */       this.bytePos = writeInt4((int)this.crcValue, this.bytePos);
/* 622 */       scrunch.finish();
/* 623 */       return true;
/* 625 */     } catch (IOException e) {
/* 626 */       System.err.println(e.toString());
/* 627 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void writeEnd() {
/* 635 */     this.bytePos = writeInt4(0, this.bytePos);
/* 636 */     this.bytePos = writeBytes(IEND, this.bytePos);
/* 637 */     this.crc.reset();
/* 638 */     this.crc.update(IEND);
/* 639 */     this.crcValue = this.crc.getValue();
/* 640 */     this.bytePos = writeInt4((int)this.crcValue, this.bytePos);
/*     */   }
/*     */   
/*     */   public void setXDpi(int xDpi) {
/* 650 */     this.xDpi = Math.round(xDpi / INCH_IN_METER_UNIT);
/*     */   }
/*     */   
/*     */   public int getXDpi() {
/* 660 */     return Math.round(this.xDpi * INCH_IN_METER_UNIT);
/*     */   }
/*     */   
/*     */   public void setYDpi(int yDpi) {
/* 669 */     this.yDpi = Math.round(yDpi / INCH_IN_METER_UNIT);
/*     */   }
/*     */   
/*     */   public int getYDpi() {
/* 678 */     return Math.round(this.yDpi * INCH_IN_METER_UNIT);
/*     */   }
/*     */   
/*     */   public void setDpi(int xDpi, int yDpi) {
/* 688 */     this.xDpi = Math.round(xDpi / INCH_IN_METER_UNIT);
/* 689 */     this.yDpi = Math.round(yDpi / INCH_IN_METER_UNIT);
/*     */   }
/*     */   
/*     */   protected void writeResolution() {
/* 696 */     if (this.xDpi > 0 && this.yDpi > 0) {
/* 698 */       int startPos = this.bytePos = writeInt4(9, this.bytePos);
/* 699 */       this.bytePos = writeBytes(PHYS, this.bytePos);
/* 700 */       this.bytePos = writeInt4(this.xDpi, this.bytePos);
/* 701 */       this.bytePos = writeInt4(this.yDpi, this.bytePos);
/* 702 */       this.bytePos = writeByte(1, this.bytePos);
/* 704 */       this.crc.reset();
/* 705 */       this.crc.update(this.pngBytes, startPos, this.bytePos - startPos);
/* 706 */       this.crcValue = this.crc.getValue();
/* 707 */       this.bytePos = writeInt4((int)this.crcValue, this.bytePos);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\keypoint\PngEncoder.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */