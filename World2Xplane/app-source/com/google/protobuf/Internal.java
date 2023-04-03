/*     */ package com.google.protobuf;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
/*     */ 
/*     */ public class Internal {
/*     */   public static String stringDefaultValue(String bytes) {
/*     */     try {
/*  74 */       return new String(bytes.getBytes("ISO-8859-1"), "UTF-8");
/*  75 */     } catch (UnsupportedEncodingException e) {
/*  78 */       throw new IllegalStateException("Java VM does not support a standard character set.", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static ByteString bytesDefaultValue(String bytes) {
/*     */     try {
/*  93 */       return ByteString.copyFrom(bytes.getBytes("ISO-8859-1"));
/*  94 */     } catch (UnsupportedEncodingException e) {
/*  97 */       throw new IllegalStateException("Java VM does not support a standard character set.", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean isValidUtf8(ByteString byteString) {
/* 114 */     int index = 0;
/* 115 */     int size = byteString.size();
/* 120 */     while (index < size) {
/* 121 */       int byte1 = byteString.byteAt(index++) & 0xFF;
/* 122 */       if (byte1 < 128)
/*     */         continue; 
/* 127 */       if (byte1 < 194 || byte1 > 244)
/* 129 */         return false; 
/* 131 */       if (index >= size)
/* 133 */         return false; 
/* 135 */       int byte2 = byteString.byteAt(index++) & 0xFF;
/* 136 */       if (byte2 < 128 || byte2 > 191)
/* 138 */         return false; 
/* 140 */       if (byte1 <= 223)
/*     */         continue; 
/* 146 */       if (index >= size)
/* 148 */         return false; 
/* 150 */       int byte3 = byteString.byteAt(index++) & 0xFF;
/* 151 */       if (byte3 < 128 || byte3 > 191)
/* 153 */         return false; 
/* 155 */       if (byte1 <= 239) {
/* 158 */         if ((byte1 == 224 && byte2 < 160) || (byte1 == 237 && byte2 > 159))
/* 161 */           return false; 
/*     */         continue;
/*     */       } 
/* 167 */       if (index >= size)
/* 169 */         return false; 
/* 171 */       int byte4 = byteString.byteAt(index++) & 0xFF;
/* 172 */       if (byte4 < 128 || byte4 > 191)
/* 174 */         return false; 
/* 177 */       if ((byte1 == 240 && byte2 < 144) || (byte1 == 244 && byte2 > 143))
/* 180 */         return false; 
/*     */     } 
/* 184 */     return true;
/*     */   }
/*     */   
/*     */   public static interface EnumLiteMap<T extends EnumLite> {
/*     */     T findValueByNumber(int param1Int);
/*     */   }
/*     */   
/*     */   public static interface EnumLite {
/*     */     int getNumber();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\protobuf\Internal.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */