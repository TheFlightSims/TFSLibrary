/*     */ package com.vividsolutions.jts.io;
/*     */ 
/*     */ import java.io.IOException;
/*     */ 
/*     */ public class ByteOrderDataInStream {
/*  44 */   private int byteOrder = 1;
/*     */   
/*     */   private InStream stream;
/*     */   
/*  47 */   private byte[] buf1 = new byte[1];
/*     */   
/*  48 */   private byte[] buf4 = new byte[4];
/*     */   
/*  49 */   private byte[] buf8 = new byte[8];
/*     */   
/*     */   public ByteOrderDataInStream() {
/*  53 */     this.stream = null;
/*     */   }
/*     */   
/*     */   public ByteOrderDataInStream(InStream stream) {
/*  58 */     this.stream = stream;
/*     */   }
/*     */   
/*     */   public void setInStream(InStream stream) {
/*  69 */     this.stream = stream;
/*     */   }
/*     */   
/*     */   public void setOrder(int byteOrder) {
/*  73 */     this.byteOrder = byteOrder;
/*     */   }
/*     */   
/*     */   public byte readByte() throws IOException {
/*  84 */     this.stream.read(this.buf1);
/*  85 */     return this.buf1[0];
/*     */   }
/*     */   
/*     */   public int readInt() throws IOException {
/*  91 */     this.stream.read(this.buf4);
/*  92 */     return ByteOrderValues.getInt(this.buf4, this.byteOrder);
/*     */   }
/*     */   
/*     */   public long readLong() throws IOException {
/*  97 */     this.stream.read(this.buf8);
/*  98 */     return ByteOrderValues.getLong(this.buf8, this.byteOrder);
/*     */   }
/*     */   
/*     */   public double readDouble() throws IOException {
/* 104 */     this.stream.read(this.buf8);
/* 105 */     return ByteOrderValues.getDouble(this.buf8, this.byteOrder);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\io\ByteOrderDataInStream.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */