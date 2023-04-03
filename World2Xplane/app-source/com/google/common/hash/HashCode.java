/*     */ package com.google.common.hash;
/*     */ 
/*     */ import com.google.common.annotations.Beta;
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.primitives.Ints;
/*     */ import com.google.common.primitives.UnsignedInts;
/*     */ import java.io.Serializable;
/*     */ import java.security.MessageDigest;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ @Beta
/*     */ public abstract class HashCode {
/*     */   public abstract int bits();
/*     */   
/*     */   public abstract int asInt();
/*     */   
/*     */   public abstract long asLong();
/*     */   
/*     */   public abstract long padToLong();
/*     */   
/*     */   public abstract byte[] asBytes();
/*     */   
/*     */   public int writeBytesTo(byte[] dest, int offset, int maxLength) {
/*  90 */     maxLength = Ints.min(new int[] { maxLength, bits() / 8 });
/*  91 */     Preconditions.checkPositionIndexes(offset, offset + maxLength, dest.length);
/*  92 */     writeBytesToImpl(dest, offset, maxLength);
/*  93 */     return maxLength;
/*     */   }
/*     */   
/*     */   abstract void writeBytesToImpl(byte[] paramArrayOfbyte, int paramInt1, int paramInt2);
/*     */   
/*     */   byte[] getBytesInternal() {
/* 104 */     return asBytes();
/*     */   }
/*     */   
/*     */   public static HashCode fromInt(int hash) {
/* 114 */     return new IntHashCode(hash);
/*     */   }
/*     */   
/*     */   private static final class IntHashCode extends HashCode implements Serializable {
/*     */     final int hash;
/*     */     
/*     */     private static final long serialVersionUID = 0L;
/*     */     
/*     */     IntHashCode(int hash) {
/* 121 */       this.hash = hash;
/*     */     }
/*     */     
/*     */     public int bits() {
/* 126 */       return 32;
/*     */     }
/*     */     
/*     */     public byte[] asBytes() {
/* 131 */       return new byte[] { (byte)this.hash, (byte)(this.hash >> 8), (byte)(this.hash >> 16), (byte)(this.hash >> 24) };
/*     */     }
/*     */     
/*     */     public int asInt() {
/* 140 */       return this.hash;
/*     */     }
/*     */     
/*     */     public long asLong() {
/* 145 */       throw new IllegalStateException("this HashCode only has 32 bits; cannot create a long");
/*     */     }
/*     */     
/*     */     public long padToLong() {
/* 150 */       return UnsignedInts.toLong(this.hash);
/*     */     }
/*     */     
/*     */     void writeBytesToImpl(byte[] dest, int offset, int maxLength) {
/* 155 */       for (int i = 0; i < maxLength; i++)
/* 156 */         dest[offset + i] = (byte)(this.hash >> i * 8); 
/*     */     }
/*     */   }
/*     */   
/*     */   public static HashCode fromLong(long hash) {
/* 170 */     return new LongHashCode(hash);
/*     */   }
/*     */   
/*     */   private static final class LongHashCode extends HashCode implements Serializable {
/*     */     final long hash;
/*     */     
/*     */     private static final long serialVersionUID = 0L;
/*     */     
/*     */     LongHashCode(long hash) {
/* 177 */       this.hash = hash;
/*     */     }
/*     */     
/*     */     public int bits() {
/* 182 */       return 64;
/*     */     }
/*     */     
/*     */     public byte[] asBytes() {
/* 187 */       return new byte[] { (byte)(int)this.hash, (byte)(int)(this.hash >> 8L), (byte)(int)(this.hash >> 16L), (byte)(int)(this.hash >> 24L), (byte)(int)(this.hash >> 32L), (byte)(int)(this.hash >> 40L), (byte)(int)(this.hash >> 48L), (byte)(int)(this.hash >> 56L) };
/*     */     }
/*     */     
/*     */     public int asInt() {
/* 200 */       return (int)this.hash;
/*     */     }
/*     */     
/*     */     public long asLong() {
/* 205 */       return this.hash;
/*     */     }
/*     */     
/*     */     public long padToLong() {
/* 210 */       return this.hash;
/*     */     }
/*     */     
/*     */     void writeBytesToImpl(byte[] dest, int offset, int maxLength) {
/* 215 */       for (int i = 0; i < maxLength; i++)
/* 216 */         dest[offset + i] = (byte)(int)(this.hash >> i * 8); 
/*     */     }
/*     */   }
/*     */   
/*     */   public static HashCode fromBytes(byte[] bytes) {
/* 230 */     Preconditions.checkArgument((bytes.length >= 1), "A HashCode must contain at least 1 byte.");
/* 231 */     return fromBytesNoCopy((byte[])bytes.clone());
/*     */   }
/*     */   
/*     */   static HashCode fromBytesNoCopy(byte[] bytes) {
/* 239 */     return new BytesHashCode(bytes);
/*     */   }
/*     */   
/*     */   private static final class BytesHashCode extends HashCode implements Serializable {
/*     */     final byte[] bytes;
/*     */     
/*     */     private static final long serialVersionUID = 0L;
/*     */     
/*     */     BytesHashCode(byte[] bytes) {
/* 246 */       this.bytes = (byte[])Preconditions.checkNotNull(bytes);
/*     */     }
/*     */     
/*     */     public int bits() {
/* 251 */       return this.bytes.length * 8;
/*     */     }
/*     */     
/*     */     public byte[] asBytes() {
/* 256 */       return (byte[])this.bytes.clone();
/*     */     }
/*     */     
/*     */     public int asInt() {
/* 261 */       Preconditions.checkState((this.bytes.length >= 4), "HashCode#asInt() requires >= 4 bytes (it only has %s bytes).", new Object[] { Integer.valueOf(this.bytes.length) });
/* 263 */       return this.bytes[0] & 0xFF | (this.bytes[1] & 0xFF) << 8 | (this.bytes[2] & 0xFF) << 16 | (this.bytes[3] & 0xFF) << 24;
/*     */     }
/*     */     
/*     */     public long asLong() {
/* 271 */       Preconditions.checkState((this.bytes.length >= 8), "HashCode#asLong() requires >= 8 bytes (it only has %s bytes).", new Object[] { Integer.valueOf(this.bytes.length) });
/* 273 */       return padToLong();
/*     */     }
/*     */     
/*     */     public long padToLong() {
/* 278 */       long retVal = (this.bytes[0] & 0xFF);
/* 279 */       for (int i = 1; i < Math.min(this.bytes.length, 8); i++)
/* 280 */         retVal |= (this.bytes[i] & 0xFFL) << i * 8; 
/* 282 */       return retVal;
/*     */     }
/*     */     
/*     */     void writeBytesToImpl(byte[] dest, int offset, int maxLength) {
/* 287 */       System.arraycopy(this.bytes, 0, dest, offset, maxLength);
/*     */     }
/*     */     
/*     */     byte[] getBytesInternal() {
/* 292 */       return this.bytes;
/*     */     }
/*     */   }
/*     */   
/*     */   public static HashCode fromString(String string) {
/* 309 */     Preconditions.checkArgument((string.length() >= 2), "input string (%s) must have at least 2 characters", new Object[] { string });
/* 311 */     Preconditions.checkArgument((string.length() % 2 == 0), "input string (%s) must have an even number of characters", new Object[] { string });
/* 314 */     byte[] bytes = new byte[string.length() / 2];
/* 315 */     for (int i = 0; i < string.length(); i += 2) {
/* 316 */       int ch1 = decode(string.charAt(i)) << 4;
/* 317 */       int ch2 = decode(string.charAt(i + 1));
/* 318 */       bytes[i / 2] = (byte)(ch1 + ch2);
/*     */     } 
/* 320 */     return fromBytesNoCopy(bytes);
/*     */   }
/*     */   
/*     */   private static int decode(char ch) {
/* 324 */     if (ch >= '0' && ch <= '9')
/* 325 */       return ch - 48; 
/* 327 */     if (ch >= 'a' && ch <= 'f')
/* 328 */       return ch - 97 + 10; 
/* 330 */     throw new IllegalArgumentException("Illegal hexadecimal character: " + ch);
/*     */   }
/*     */   
/*     */   public final boolean equals(@Nullable Object object) {
/* 335 */     if (object instanceof HashCode) {
/* 336 */       HashCode that = (HashCode)object;
/* 339 */       return MessageDigest.isEqual(asBytes(), that.asBytes());
/*     */     } 
/* 341 */     return false;
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/* 353 */     if (bits() >= 32)
/* 354 */       return asInt(); 
/* 357 */     byte[] bytes = asBytes();
/* 358 */     int val = bytes[0] & 0xFF;
/* 359 */     for (int i = 1; i < bytes.length; i++)
/* 360 */       val |= (bytes[i] & 0xFF) << i * 8; 
/* 362 */     return val;
/*     */   }
/*     */   
/*     */   public final String toString() {
/* 378 */     byte[] bytes = asBytes();
/* 379 */     StringBuilder sb = new StringBuilder(2 * bytes.length);
/* 380 */     for (byte b : bytes)
/* 381 */       sb.append(hexDigits[b >> 4 & 0xF]).append(hexDigits[b & 0xF]); 
/* 383 */     return sb.toString();
/*     */   }
/*     */   
/* 386 */   private static final char[] hexDigits = "0123456789abcdef".toCharArray();
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\common\hash\HashCode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */