/*     */ package org.mapdb;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ 
/*     */ public final class EncryptionXTEA {
/*     */   public static final int ALIGN = 16;
/*     */   
/*     */   private static final int DELTA = -1640531527;
/*     */   
/*     */   private final int k0;
/*     */   
/*     */   private final int k1;
/*     */   
/*     */   private final int k2;
/*     */   
/*     */   private final int k3;
/*     */   
/*     */   private final int k4;
/*     */   
/*     */   private final int k5;
/*     */   
/*     */   private final int k6;
/*     */   
/*     */   private final int k7;
/*     */   
/*     */   private final int k8;
/*     */   
/*     */   private final int k9;
/*     */   
/*     */   private final int k10;
/*     */   
/*     */   private final int k11;
/*     */   
/*     */   private final int k12;
/*     */   
/*     */   private final int k13;
/*     */   
/*     */   private final int k14;
/*     */   
/*     */   private final int k15;
/*     */   
/*     */   private final int k16;
/*     */   
/*     */   private final int k17;
/*     */   
/*     */   private final int k18;
/*     */   
/*     */   private final int k19;
/*     */   
/*     */   private final int k20;
/*     */   
/*     */   private final int k21;
/*     */   
/*     */   private final int k22;
/*     */   
/*     */   private final int k23;
/*     */   
/*     */   private final int k24;
/*     */   
/*     */   private final int k25;
/*     */   
/*     */   private final int k26;
/*     */   
/*     */   private final int k27;
/*     */   
/*     */   private final int k28;
/*     */   
/*     */   private final int k29;
/*     */   
/*     */   private final int k30;
/*     */   
/*     */   private final int k31;
/*     */   
/*     */   public EncryptionXTEA(byte[] password) {
/*  46 */     byte[] b = getHash(password);
/*  47 */     int[] key = new int[4];
/*  48 */     for (int i = 0; i < 16;)
/*  49 */       key[i / 4] = (b[i++] << 24) + ((b[i++] & 0xFF) << 16) + ((b[i++] & 0xFF) << 8) + (b[i++] & 0xFF); 
/*  51 */     int[] r = new int[32];
/*  52 */     for (int j = 0, sum = 0; j < 32; ) {
/*  53 */       r[j++] = sum + key[sum & 0x3];
/*  54 */       sum -= 1640531527;
/*  55 */       r[j++] = sum + key[sum >>> 11 & 0x3];
/*     */     } 
/*  57 */     this.k0 = r[0];
/*  57 */     this.k1 = r[1];
/*  57 */     this.k2 = r[2];
/*  57 */     this.k3 = r[3];
/*  57 */     this.k4 = r[4];
/*  57 */     this.k5 = r[5];
/*  57 */     this.k6 = r[6];
/*  57 */     this.k7 = r[7];
/*  58 */     this.k8 = r[8];
/*  58 */     this.k9 = r[9];
/*  58 */     this.k10 = r[10];
/*  58 */     this.k11 = r[11];
/*  58 */     this.k12 = r[12];
/*  58 */     this.k13 = r[13];
/*  58 */     this.k14 = r[14];
/*  58 */     this.k15 = r[15];
/*  59 */     this.k16 = r[16];
/*  59 */     this.k17 = r[17];
/*  59 */     this.k18 = r[18];
/*  59 */     this.k19 = r[19];
/*  59 */     this.k20 = r[20];
/*  59 */     this.k21 = r[21];
/*  59 */     this.k22 = r[22];
/*  59 */     this.k23 = r[23];
/*  60 */     this.k24 = r[24];
/*  60 */     this.k25 = r[25];
/*  60 */     this.k26 = r[26];
/*  60 */     this.k27 = r[27];
/*  60 */     this.k28 = r[28];
/*  60 */     this.k29 = r[29];
/*  60 */     this.k30 = r[30];
/*  60 */     this.k31 = r[31];
/*     */   }
/*     */   
/*     */   public void encrypt(byte[] bytes, int off, int len) {
/*  65 */     assert len % 16 == 0 : "unaligned len " + len;
/*  67 */     for (int i = off; i < off + len; i += 8)
/*  68 */       encryptBlock(bytes, bytes, i); 
/*     */   }
/*     */   
/*     */   public void decrypt(byte[] bytes, int off, int len) {
/*  73 */     assert len % 16 == 0 : "unaligned len " + len;
/*  75 */     for (int i = off; i < off + len; i += 8)
/*  76 */       decryptBlock(bytes, bytes, i); 
/*     */   }
/*     */   
/*     */   private void encryptBlock(byte[] in, byte[] out, int off) {
/*  81 */     int y = in[off] << 24 | (in[off + 1] & 0xFF) << 16 | (in[off + 2] & 0xFF) << 8 | in[off + 3] & 0xFF;
/*  82 */     int z = in[off + 4] << 24 | (in[off + 5] & 0xFF) << 16 | (in[off + 6] & 0xFF) << 8 | in[off + 7] & 0xFF;
/*  83 */     y += (z << 4 ^ z >>> 5) + z ^ this.k0;
/*  83 */     z += (y >>> 5 ^ y << 4) + y ^ this.k1;
/*  84 */     y += (z << 4 ^ z >>> 5) + z ^ this.k2;
/*  84 */     z += (y >>> 5 ^ y << 4) + y ^ this.k3;
/*  85 */     y += (z << 4 ^ z >>> 5) + z ^ this.k4;
/*  85 */     z += (y >>> 5 ^ y << 4) + y ^ this.k5;
/*  86 */     y += (z << 4 ^ z >>> 5) + z ^ this.k6;
/*  86 */     z += (y >>> 5 ^ y << 4) + y ^ this.k7;
/*  87 */     y += (z << 4 ^ z >>> 5) + z ^ this.k8;
/*  87 */     z += (y >>> 5 ^ y << 4) + y ^ this.k9;
/*  88 */     y += (z << 4 ^ z >>> 5) + z ^ this.k10;
/*  88 */     z += (y >>> 5 ^ y << 4) + y ^ this.k11;
/*  89 */     y += (z << 4 ^ z >>> 5) + z ^ this.k12;
/*  89 */     z += (y >>> 5 ^ y << 4) + y ^ this.k13;
/*  90 */     y += (z << 4 ^ z >>> 5) + z ^ this.k14;
/*  90 */     z += (y >>> 5 ^ y << 4) + y ^ this.k15;
/*  91 */     y += (z << 4 ^ z >>> 5) + z ^ this.k16;
/*  91 */     z += (y >>> 5 ^ y << 4) + y ^ this.k17;
/*  92 */     y += (z << 4 ^ z >>> 5) + z ^ this.k18;
/*  92 */     z += (y >>> 5 ^ y << 4) + y ^ this.k19;
/*  93 */     y += (z << 4 ^ z >>> 5) + z ^ this.k20;
/*  93 */     z += (y >>> 5 ^ y << 4) + y ^ this.k21;
/*  94 */     y += (z << 4 ^ z >>> 5) + z ^ this.k22;
/*  94 */     z += (y >>> 5 ^ y << 4) + y ^ this.k23;
/*  95 */     y += (z << 4 ^ z >>> 5) + z ^ this.k24;
/*  95 */     z += (y >>> 5 ^ y << 4) + y ^ this.k25;
/*  96 */     y += (z << 4 ^ z >>> 5) + z ^ this.k26;
/*  96 */     z += (y >>> 5 ^ y << 4) + y ^ this.k27;
/*  97 */     y += (z << 4 ^ z >>> 5) + z ^ this.k28;
/*  97 */     z += (y >>> 5 ^ y << 4) + y ^ this.k29;
/*  98 */     y += (z << 4 ^ z >>> 5) + z ^ this.k30;
/*  98 */     z += (y >>> 5 ^ y << 4) + y ^ this.k31;
/*  99 */     out[off] = (byte)(y >> 24);
/*  99 */     out[off + 1] = (byte)(y >> 16);
/*  99 */     out[off + 2] = (byte)(y >> 8);
/*  99 */     out[off + 3] = (byte)y;
/* 100 */     out[off + 4] = (byte)(z >> 24);
/* 100 */     out[off + 5] = (byte)(z >> 16);
/* 100 */     out[off + 6] = (byte)(z >> 8);
/* 100 */     out[off + 7] = (byte)z;
/*     */   }
/*     */   
/*     */   private void decryptBlock(byte[] in, byte[] out, int off) {
/* 104 */     int y = in[off] << 24 | (in[off + 1] & 0xFF) << 16 | (in[off + 2] & 0xFF) << 8 | in[off + 3] & 0xFF;
/* 105 */     int z = in[off + 4] << 24 | (in[off + 5] & 0xFF) << 16 | (in[off + 6] & 0xFF) << 8 | in[off + 7] & 0xFF;
/* 106 */     z -= (y >>> 5 ^ y << 4) + y ^ this.k31;
/* 106 */     y -= (z << 4 ^ z >>> 5) + z ^ this.k30;
/* 107 */     z -= (y >>> 5 ^ y << 4) + y ^ this.k29;
/* 107 */     y -= (z << 4 ^ z >>> 5) + z ^ this.k28;
/* 108 */     z -= (y >>> 5 ^ y << 4) + y ^ this.k27;
/* 108 */     y -= (z << 4 ^ z >>> 5) + z ^ this.k26;
/* 109 */     z -= (y >>> 5 ^ y << 4) + y ^ this.k25;
/* 109 */     y -= (z << 4 ^ z >>> 5) + z ^ this.k24;
/* 110 */     z -= (y >>> 5 ^ y << 4) + y ^ this.k23;
/* 110 */     y -= (z << 4 ^ z >>> 5) + z ^ this.k22;
/* 111 */     z -= (y >>> 5 ^ y << 4) + y ^ this.k21;
/* 111 */     y -= (z << 4 ^ z >>> 5) + z ^ this.k20;
/* 112 */     z -= (y >>> 5 ^ y << 4) + y ^ this.k19;
/* 112 */     y -= (z << 4 ^ z >>> 5) + z ^ this.k18;
/* 113 */     z -= (y >>> 5 ^ y << 4) + y ^ this.k17;
/* 113 */     y -= (z << 4 ^ z >>> 5) + z ^ this.k16;
/* 114 */     z -= (y >>> 5 ^ y << 4) + y ^ this.k15;
/* 114 */     y -= (z << 4 ^ z >>> 5) + z ^ this.k14;
/* 115 */     z -= (y >>> 5 ^ y << 4) + y ^ this.k13;
/* 115 */     y -= (z << 4 ^ z >>> 5) + z ^ this.k12;
/* 116 */     z -= (y >>> 5 ^ y << 4) + y ^ this.k11;
/* 116 */     y -= (z << 4 ^ z >>> 5) + z ^ this.k10;
/* 117 */     z -= (y >>> 5 ^ y << 4) + y ^ this.k9;
/* 117 */     y -= (z << 4 ^ z >>> 5) + z ^ this.k8;
/* 118 */     z -= (y >>> 5 ^ y << 4) + y ^ this.k7;
/* 118 */     y -= (z << 4 ^ z >>> 5) + z ^ this.k6;
/* 119 */     z -= (y >>> 5 ^ y << 4) + y ^ this.k5;
/* 119 */     y -= (z << 4 ^ z >>> 5) + z ^ this.k4;
/* 120 */     z -= (y >>> 5 ^ y << 4) + y ^ this.k3;
/* 120 */     y -= (z << 4 ^ z >>> 5) + z ^ this.k2;
/* 121 */     z -= (y >>> 5 ^ y << 4) + y ^ this.k1;
/* 121 */     y -= (z << 4 ^ z >>> 5) + z ^ this.k0;
/* 122 */     out[off] = (byte)(y >> 24);
/* 122 */     out[off + 1] = (byte)(y >> 16);
/* 122 */     out[off + 2] = (byte)(y >> 8);
/* 122 */     out[off + 3] = (byte)y;
/* 123 */     out[off + 4] = (byte)(z >> 24);
/* 123 */     out[off + 5] = (byte)(z >> 16);
/* 123 */     out[off + 6] = (byte)(z >> 8);
/* 123 */     out[off + 7] = (byte)z;
/*     */   }
/*     */   
/* 131 */   private static final int[] K = new int[] { 
/* 131 */       1116352408, 1899447441, -1245643825, -373957723, 961987163, 1508970993, -1841331548, -1424204075, -670586216, 310598401, 
/* 131 */       607225278, 1426881987, 1925078388, -2132889090, -1680079193, -1046744716, -459576895, -272742522, 264347078, 604807628, 
/* 131 */       770255983, 1249150122, 1555081692, 1996064986, -1740746414, -1473132947, -1341970488, -1084653625, -958395405, -710438585, 
/* 131 */       113926993, 338241895, 666307205, 773529912, 1294757372, 1396182291, 1695183700, 1986661051, -2117940946, -1838011259, 
/* 131 */       -1564481375, -1474664885, -1035236496, -949202525, -778901479, -694614492, -200395387, 275423344, 430227734, 506948616, 
/* 131 */       659060556, 883997877, 958139571, 1322822218, 1537002063, 1747873779, 1955562222, 2024104815, -2067236844, -1933114872, 
/* 131 */       -1866530822, -1538233109, -1090935817, -965641998 };
/*     */   
/*     */   public static byte[] getHash(byte[] data) {
/* 153 */     int byteLen = data.length;
/* 154 */     int intLen = (byteLen + 9 + 63) / 64 * 16;
/* 155 */     byte[] bytes = new byte[intLen * 4];
/* 156 */     System.arraycopy(data, 0, bytes, 0, byteLen);
/* 158 */     bytes[byteLen] = Byte.MIN_VALUE;
/* 159 */     int[] buff = new int[intLen];
/* 160 */     for (int i = 0, j = 0; j < intLen; i += 4, j++)
/* 161 */       buff[j] = readInt(bytes, i); 
/* 163 */     buff[intLen - 2] = byteLen >>> 29;
/* 164 */     buff[intLen - 1] = byteLen << 3;
/* 165 */     int[] w = new int[64];
/* 166 */     int[] hh = { 1779033703, -1150833019, 1013904242, -1521486534, 1359893119, -1694144372, 528734635, 1541459225 };
/* 168 */     for (int block = 0; block < intLen; block += 16) {
/* 169 */       System.arraycopy(buff, block + 0, w, 0, 16);
/* 170 */       for (int m = 16; m < 64; m++) {
/* 171 */         int x = w[m - 2];
/* 172 */         int theta1 = rot(x, 17) ^ rot(x, 19) ^ x >>> 10;
/* 173 */         x = w[m - 15];
/* 174 */         int theta0 = rot(x, 7) ^ rot(x, 18) ^ x >>> 3;
/* 175 */         w[m] = theta1 + w[m - 7] + theta0 + w[m - 16];
/*     */       } 
/* 178 */       int a = hh[0], b = hh[1], c = hh[2], d = hh[3];
/* 179 */       int e = hh[4], f = hh[5], g = hh[6], h = hh[7];
/* 181 */       for (int n = 0; n < 64; n++) {
/* 182 */         int t1 = h + (rot(e, 6) ^ rot(e, 11) ^ rot(e, 25)) + (e & f ^ (e ^ 0xFFFFFFFF) & g) + K[n] + w[n];
/* 184 */         int t2 = (rot(a, 2) ^ rot(a, 13) ^ rot(a, 22)) + (a & b ^ a & c ^ b & c);
/* 186 */         h = g;
/* 187 */         g = f;
/* 188 */         f = e;
/* 189 */         e = d + t1;
/* 190 */         d = c;
/* 191 */         c = b;
/* 192 */         b = a;
/* 193 */         a = t1 + t2;
/*     */       } 
/* 195 */       hh[0] = hh[0] + a;
/* 196 */       hh[1] = hh[1] + b;
/* 197 */       hh[2] = hh[2] + c;
/* 198 */       hh[3] = hh[3] + d;
/* 199 */       hh[4] = hh[4] + e;
/* 200 */       hh[5] = hh[5] + f;
/* 201 */       hh[6] = hh[6] + g;
/* 202 */       hh[7] = hh[7] + h;
/*     */     } 
/* 204 */     byte[] result = new byte[32];
/* 205 */     for (int k = 0; k < 8; k++)
/* 206 */       writeInt(result, k * 4, hh[k]); 
/* 208 */     Arrays.fill(w, 0);
/* 209 */     Arrays.fill(buff, 0);
/* 210 */     Arrays.fill(hh, 0);
/* 211 */     Arrays.fill(bytes, (byte)0);
/* 212 */     return result;
/*     */   }
/*     */   
/*     */   private static int rot(int i, int count) {
/* 216 */     return i << 32 - count | i >>> count;
/*     */   }
/*     */   
/*     */   private static int readInt(byte[] b, int i) {
/* 220 */     return ((b[i] & 0xFF) << 24) + ((b[i + 1] & 0xFF) << 16) + ((b[i + 2] & 0xFF) << 8) + (b[i + 3] & 0xFF);
/*     */   }
/*     */   
/*     */   private static void writeInt(byte[] b, int i, int value) {
/* 225 */     b[i] = (byte)(value >> 24);
/* 226 */     b[i + 1] = (byte)(value >> 16);
/* 227 */     b[i + 2] = (byte)(value >> 8);
/* 228 */     b[i + 3] = (byte)value;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\mapdb\EncryptionXTEA.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */