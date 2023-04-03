/*     */ package org.mapdb;
/*     */ 
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ public final class DataOutput2 extends OutputStream implements DataOutput {
/*     */   public byte[] buf;
/*     */   
/*     */   public volatile int pos;
/*     */   
/*     */   public DataOutput2() {
/*  37 */     this.pos = 0;
/*  38 */     this.buf = new byte[16];
/*     */   }
/*     */   
/*     */   public DataOutput2(byte[] buf) {
/*  42 */     this.pos = 0;
/*  43 */     this.buf = buf;
/*     */   }
/*     */   
/*     */   public byte[] copyBytes() {
/*  47 */     return Arrays.copyOf(this.buf, this.pos);
/*     */   }
/*     */   
/*     */   public void ensureAvail(int n) {
/*  54 */     if (this.pos + n >= this.buf.length) {
/*  55 */       int newSize = Math.max(this.pos + n, this.buf.length * 2);
/*  56 */       this.buf = Arrays.copyOf(this.buf, newSize);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void write(int b) throws IOException {
/*  63 */     ensureAvail(1);
/*  64 */     this.buf[this.pos++] = (byte)b;
/*     */   }
/*     */   
/*     */   public void write(byte[] b) throws IOException {
/*  69 */     write(b, 0, b.length);
/*     */   }
/*     */   
/*     */   public void write(byte[] b, int off, int len) throws IOException {
/*  74 */     ensureAvail(len);
/*  75 */     System.arraycopy(b, off, this.buf, this.pos, len);
/*  76 */     this.pos += len;
/*     */   }
/*     */   
/*     */   public void writeBoolean(boolean v) throws IOException {
/*  81 */     ensureAvail(1);
/*  82 */     this.buf[this.pos++] = (byte)(v ? 1 : 0);
/*     */   }
/*     */   
/*     */   public void writeByte(int v) throws IOException {
/*  87 */     ensureAvail(1);
/*  88 */     this.buf[this.pos++] = (byte)v;
/*     */   }
/*     */   
/*     */   public void writeShort(int v) throws IOException {
/*  93 */     ensureAvail(2);
/*  94 */     this.buf[this.pos++] = (byte)(0xFF & v >> 8);
/*  95 */     this.buf[this.pos++] = (byte)(0xFF & v);
/*     */   }
/*     */   
/*     */   public void writeChar(int v) throws IOException {
/* 100 */     writeInt(v);
/*     */   }
/*     */   
/*     */   public void writeInt(int v) throws IOException {
/* 105 */     ensureAvail(4);
/* 106 */     this.buf[this.pos++] = (byte)(0xFF & v >> 24);
/* 107 */     this.buf[this.pos++] = (byte)(0xFF & v >> 16);
/* 108 */     this.buf[this.pos++] = (byte)(0xFF & v >> 8);
/* 109 */     this.buf[this.pos++] = (byte)(0xFF & v);
/*     */   }
/*     */   
/*     */   public void writeLong(long v) throws IOException {
/* 114 */     ensureAvail(8);
/* 115 */     this.buf[this.pos++] = (byte)(int)(0xFFL & v >> 56L);
/* 116 */     this.buf[this.pos++] = (byte)(int)(0xFFL & v >> 48L);
/* 117 */     this.buf[this.pos++] = (byte)(int)(0xFFL & v >> 40L);
/* 118 */     this.buf[this.pos++] = (byte)(int)(0xFFL & v >> 32L);
/* 119 */     this.buf[this.pos++] = (byte)(int)(0xFFL & v >> 24L);
/* 120 */     this.buf[this.pos++] = (byte)(int)(0xFFL & v >> 16L);
/* 121 */     this.buf[this.pos++] = (byte)(int)(0xFFL & v >> 8L);
/* 122 */     this.buf[this.pos++] = (byte)(int)(0xFFL & v);
/*     */   }
/*     */   
/*     */   public void writeFloat(float v) throws IOException {
/* 127 */     writeInt(Float.floatToIntBits(v));
/*     */   }
/*     */   
/*     */   public void writeDouble(double v) throws IOException {
/* 132 */     writeLong(Double.doubleToLongBits(v));
/*     */   }
/*     */   
/*     */   public void writeBytes(String s) throws IOException {
/* 137 */     writeUTF(s);
/*     */   }
/*     */   
/*     */   public void writeChars(String s) throws IOException {
/* 142 */     writeUTF(s);
/*     */   }
/*     */   
/*     */   public void writeUTF(String s) throws IOException {
/* 147 */     int len = s.length();
/* 148 */     packInt(this, len);
/* 149 */     for (int i = 0; i < len; i++) {
/* 150 */       int c = s.charAt(i);
/* 151 */       packInt(this, c);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void packLong(DataOutput out, long value) throws IOException {
/* 199 */     assert value >= 0L : "negative value: " + value;
/* 201 */     while ((value & 0xFFFFFFFFFFFFFF80L) != 0L) {
/* 202 */       out.write((int)value & 0x7F | 0x80);
/* 203 */       value >>>= 7L;
/*     */     } 
/* 205 */     out.write((byte)(int)value);
/*     */   }
/*     */   
/*     */   public static void packInt(DataOutput in, int value) throws IOException {
/* 225 */     assert value >= 0 : "negative value: " + value;
/* 227 */     while ((value & 0xFFFFFF80) != 0) {
/* 228 */       in.write(value & 0x7F | 0x80);
/* 229 */       value >>>= 7;
/*     */     } 
/* 232 */     in.write((byte)value);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\mapdb\DataOutput2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */