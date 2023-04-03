/*     */ package org.mapdb;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.nio.ByteBuffer;
/*     */ 
/*     */ public final class DataInput2 extends InputStream implements DataInput {
/*     */   public ByteBuffer buf;
/*     */   
/*     */   public int pos;
/*     */   
/*     */   public DataInput2(ByteBuffer buf, int pos) {
/*  35 */     this.buf = buf;
/*  36 */     this.pos = pos;
/*     */   }
/*     */   
/*     */   public DataInput2(byte[] b) {
/*  40 */     this(ByteBuffer.wrap(b), 0);
/*     */   }
/*     */   
/*     */   public void readFully(byte[] b) throws IOException {
/*  45 */     readFully(b, 0, b.length);
/*     */   }
/*     */   
/*     */   public void readFully(byte[] b, int off, int len) throws IOException {
/*  50 */     ByteBuffer clone = this.buf.duplicate();
/*  51 */     clone.position(this.pos);
/*  52 */     this.pos += len;
/*  53 */     clone.get(b, off, len);
/*     */   }
/*     */   
/*     */   public int skipBytes(int n) throws IOException {
/*  58 */     this.pos += n;
/*  59 */     return n;
/*     */   }
/*     */   
/*     */   public boolean readBoolean() throws IOException {
/*  64 */     return (this.buf.get(this.pos++) == 1);
/*     */   }
/*     */   
/*     */   public byte readByte() throws IOException {
/*  69 */     return this.buf.get(this.pos++);
/*     */   }
/*     */   
/*     */   public int readUnsignedByte() throws IOException {
/*  74 */     return this.buf.get(this.pos++) & 0xFF;
/*     */   }
/*     */   
/*     */   public short readShort() throws IOException {
/*  79 */     short ret = this.buf.getShort(this.pos);
/*  80 */     this.pos += 2;
/*  81 */     return ret;
/*     */   }
/*     */   
/*     */   public int readUnsignedShort() throws IOException {
/*  86 */     return (this.buf.get(this.pos++) & 0xFF) << 8 | this.buf.get(this.pos++) & 0xFF;
/*     */   }
/*     */   
/*     */   public char readChar() throws IOException {
/*  92 */     return (char)readInt();
/*     */   }
/*     */   
/*     */   public int readInt() throws IOException {
/*  97 */     int ret = this.buf.getInt(this.pos);
/*  98 */     this.pos += 4;
/*  99 */     return ret;
/*     */   }
/*     */   
/*     */   public long readLong() throws IOException {
/* 104 */     long ret = this.buf.getLong(this.pos);
/* 105 */     this.pos += 8;
/* 106 */     return ret;
/*     */   }
/*     */   
/*     */   public float readFloat() throws IOException {
/* 111 */     float ret = this.buf.getFloat(this.pos);
/* 112 */     this.pos += 4;
/* 113 */     return ret;
/*     */   }
/*     */   
/*     */   public double readDouble() throws IOException {
/* 118 */     double ret = this.buf.getDouble(this.pos);
/* 119 */     this.pos += 8;
/* 120 */     return ret;
/*     */   }
/*     */   
/*     */   public String readLine() throws IOException {
/* 125 */     return readUTF();
/*     */   }
/*     */   
/*     */   public String readUTF() throws IOException {
/* 130 */     int size = unpackInt(this);
/* 131 */     return SerializerBase.deserializeString(this, size);
/*     */   }
/*     */   
/*     */   public int read() throws IOException {
/* 136 */     return readUnsignedByte();
/*     */   }
/*     */   
/*     */   public static int unpackInt(DataInput is) throws IOException {
/* 183 */     for (int offset = 0, result = 0; offset < 32; offset += 7) {
/* 184 */       int b = is.readUnsignedByte();
/* 185 */       result |= (b & 0x7F) << offset;
/* 186 */       if ((b & 0x80) == 0)
/* 187 */         return result; 
/*     */     } 
/* 190 */     throw new AssertionError("Malformed int.");
/*     */   }
/*     */   
/*     */   public static long unpackLong(DataInput in) throws IOException {
/* 206 */     long result = 0L;
/* 207 */     for (int offset = 0; offset < 64; offset += 7) {
/* 208 */       long b = in.readUnsignedByte();
/* 209 */       result |= (b & 0x7FL) << offset;
/* 210 */       if ((b & 0x80L) == 0L)
/* 211 */         return result; 
/*     */     } 
/* 214 */     throw new AssertionError("Malformed long.");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\mapdb\DataInput2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */