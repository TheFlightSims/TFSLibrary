/*     */ package org.postgresql.core;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.postgresql.util.GT;
/*     */ 
/*     */ class UTF8Encoding extends Encoding {
/*     */   private static final int MIN_2_BYTES = 128;
/*     */   
/*     */   private static final int MIN_3_BYTES = 2048;
/*     */   
/*     */   private static final int MIN_4_BYTES = 65536;
/*     */   
/*     */   private static final int MAX_CODE_POINT = 1114111;
/*     */   
/*     */   private char[] decoderArray;
/*     */   
/*     */   UTF8Encoding(String jvmEncoding) {
/*  17 */     super(jvmEncoding);
/*  25 */     this.decoderArray = new char[1024];
/*     */   }
/*     */   
/*     */   private static final void checkByte(int ch, int pos, int len) throws IOException {
/*  29 */     if ((ch & 0xC0) != 128)
/*  30 */       throw new IOException(GT.tr("Illegal UTF-8 sequence: byte {0} of {1} byte sequence is not 10xxxxxx: {2}", new Object[] { new Integer(pos), new Integer(len), new Integer(ch) })); 
/*     */   }
/*     */   
/*     */   private static final void checkMinimal(int ch, int minValue) throws IOException {
/*     */     int actualLen;
/*     */     int expectedLen;
/*  35 */     if (ch >= minValue)
/*     */       return; 
/*  39 */     switch (minValue) {
/*     */       case 128:
/*  41 */         actualLen = 2;
/*     */         break;
/*     */       case 2048:
/*  44 */         actualLen = 3;
/*     */         break;
/*     */       case 65536:
/*  47 */         actualLen = 4;
/*     */         break;
/*     */       default:
/*  50 */         throw new IllegalArgumentException("unexpected minValue passed to checkMinimal: " + minValue);
/*     */     } 
/*  54 */     if (ch < 128) {
/*  55 */       expectedLen = 1;
/*  56 */     } else if (ch < 2048) {
/*  57 */       expectedLen = 2;
/*  58 */     } else if (ch < 65536) {
/*  59 */       expectedLen = 3;
/*     */     } else {
/*  61 */       throw new IllegalArgumentException("unexpected ch passed to checkMinimal: " + ch);
/*     */     } 
/*  63 */     throw new IOException(GT.tr("Illegal UTF-8 sequence: {0} bytes used to encode a {1} byte value: {2}", new Object[] { new Integer(actualLen), new Integer(expectedLen), new Integer(ch) }));
/*     */   }
/*     */   
/*     */   public synchronized String decode(byte[] data, int offset, int length) throws IOException {
/*  81 */     char[] cdata = this.decoderArray;
/*  82 */     if (cdata.length < length)
/*  83 */       cdata = this.decoderArray = new char[length]; 
/*  85 */     int in = offset;
/*  86 */     int out = 0;
/*  87 */     int end = length + offset;
/*     */     try {
/*  91 */       while (in < end) {
/*  93 */         int ch = data[in++] & 0xFF;
/*  96 */         if (ch >= 128) {
/*  98 */           if (ch < 192)
/* 100 */             throw new IOException(GT.tr("Illegal UTF-8 sequence: initial byte is {0}: {1}", new Object[] { "10xxxxxx", new Integer(ch) })); 
/* 102 */           if (ch < 224) {
/* 104 */             ch = (ch & 0x1F) << 6;
/* 105 */             checkByte(data[in], 2, 2);
/* 106 */             ch |= data[in++] & 0x3F;
/* 107 */             checkMinimal(ch, 128);
/* 108 */           } else if (ch < 240) {
/* 110 */             ch = (ch & 0xF) << 12;
/* 111 */             checkByte(data[in], 2, 3);
/* 112 */             ch |= (data[in++] & 0x3F) << 6;
/* 113 */             checkByte(data[in], 3, 3);
/* 114 */             ch |= data[in++] & 0x3F;
/* 115 */             checkMinimal(ch, 2048);
/* 116 */           } else if (ch < 248) {
/* 118 */             ch = (ch & 0x7) << 18;
/* 119 */             checkByte(data[in], 2, 4);
/* 120 */             ch |= (data[in++] & 0x3F) << 12;
/* 121 */             checkByte(data[in], 3, 4);
/* 122 */             ch |= (data[in++] & 0x3F) << 6;
/* 123 */             checkByte(data[in], 4, 4);
/* 124 */             ch |= data[in++] & 0x3F;
/* 125 */             checkMinimal(ch, 65536);
/*     */           } else {
/* 127 */             throw new IOException(GT.tr("Illegal UTF-8 sequence: initial byte is {0}: {1}", new Object[] { "11111xxx", new Integer(ch) }));
/*     */           } 
/*     */         } 
/* 131 */         if (ch > 1114111)
/* 132 */           throw new IOException(GT.tr("Illegal UTF-8 sequence: final value is out of range: {0}", new Integer(ch))); 
/* 140 */         if (ch > 65535) {
/* 142 */           ch -= 65536;
/* 143 */           cdata[out++] = (char)(55296 + (ch >> 10));
/* 144 */           cdata[out++] = (char)(56320 + (ch & 0x3FF));
/*     */           continue;
/*     */         } 
/* 145 */         if (ch >= 55296 && ch < 57344)
/* 147 */           throw new IOException(GT.tr("Illegal UTF-8 sequence: final value is a surrogate value: {0}", new Integer(ch))); 
/* 151 */         cdata[out++] = (char)ch;
/*     */       } 
/* 155 */     } catch (ArrayIndexOutOfBoundsException a) {
/* 157 */       throw new IOException("Illegal UTF-8 sequence: multibyte sequence was truncated");
/*     */     } 
/* 161 */     if (in > end)
/* 162 */       throw new IOException("Illegal UTF-8 sequence: multibyte sequence was truncated"); 
/* 164 */     return new String(cdata, 0, out);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\UTF8Encoding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */