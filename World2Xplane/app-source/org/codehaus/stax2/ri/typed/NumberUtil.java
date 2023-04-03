/*     */ package org.codehaus.stax2.ri.typed;
/*     */ 
/*     */ public final class NumberUtil {
/*     */   public static final int MAX_INT_CLEN = 11;
/*     */   
/*     */   public static final int MAX_LONG_CLEN = 21;
/*     */   
/*     */   public static final int MAX_DOUBLE_CLEN = 32;
/*     */   
/*     */   public static final int MAX_FLOAT_CLEN = 32;
/*     */   
/*     */   private static final char NULL_CHAR = '\000';
/*     */   
/*     */   private static final int MILLION = 1000000;
/*     */   
/*     */   private static final int BILLION = 1000000000;
/*     */   
/*     */   private static final long TEN_BILLION_L = 10000000000L;
/*     */   
/*     */   private static final long THOUSAND_L = 1000L;
/*     */   
/*     */   private static final byte BYTE_HYPHEN = 45;
/*     */   
/*     */   private static final byte BYTE_1 = 49;
/*     */   
/*     */   private static final byte BYTE_2 = 50;
/*     */   
/*  67 */   private static long MIN_INT_AS_LONG = -2147483647L;
/*     */   
/*  69 */   private static long MAX_INT_AS_LONG = 2147483647L;
/*     */   
/*  71 */   static final char[] LEADING_TRIPLETS = new char[4000];
/*     */   
/*  72 */   static final char[] FULL_TRIPLETS = new char[4000];
/*     */   
/*     */   static {
/*  77 */     int ix = 0;
/*  78 */     for (int i1 = 0; i1 < 10; i1++) {
/*  79 */       char f1 = (char)(48 + i1);
/*  80 */       char l1 = (i1 == 0) ? Character.MIN_VALUE : f1;
/*  81 */       for (int i2 = 0; i2 < 10; i2++) {
/*  82 */         char f2 = (char)(48 + i2);
/*  83 */         char l2 = (i1 == 0 && i2 == 0) ? Character.MIN_VALUE : f2;
/*  84 */         for (int i3 = 0; i3 < 10; i3++) {
/*  86 */           char f3 = (char)(48 + i3);
/*  87 */           LEADING_TRIPLETS[ix] = l1;
/*  88 */           LEADING_TRIPLETS[ix + 1] = l2;
/*  89 */           LEADING_TRIPLETS[ix + 2] = f3;
/*  90 */           FULL_TRIPLETS[ix] = f1;
/*  91 */           FULL_TRIPLETS[ix + 1] = f2;
/*  92 */           FULL_TRIPLETS[ix + 2] = f3;
/*  93 */           ix += 4;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static int writeInt(int value, char[] buffer, int offset) {
/* 114 */     if (value < 0) {
/* 116 */       if (value == Integer.MIN_VALUE)
/* 120 */         return writeLong(value, buffer, offset); 
/* 122 */       buffer[offset++] = '-';
/* 123 */       value = -value;
/*     */     } 
/* 126 */     if (value < 1000000) {
/* 127 */       if (value < 1000) {
/* 128 */         if (value < 10) {
/* 129 */           buffer[offset++] = (char)(48 + value);
/*     */         } else {
/* 131 */           offset = writeLeadingTriplet(value, buffer, offset);
/*     */         } 
/*     */       } else {
/* 134 */         int i = value / 1000;
/* 135 */         value -= i * 1000;
/* 136 */         offset = writeLeadingTriplet(i, buffer, offset);
/* 137 */         offset = writeFullTriplet(value, buffer, offset);
/*     */       } 
/* 139 */       return offset;
/*     */     } 
/* 147 */     boolean hasBillions = (value >= 1000000000);
/* 148 */     if (hasBillions) {
/* 149 */       value -= 1000000000;
/* 150 */       if (value >= 1000000000) {
/* 151 */         value -= 1000000000;
/* 152 */         buffer[offset++] = '2';
/*     */       } else {
/* 154 */         buffer[offset++] = '1';
/*     */       } 
/*     */     } 
/* 157 */     int newValue = value / 1000;
/* 158 */     int ones = value - newValue * 1000;
/* 159 */     value = newValue;
/* 160 */     newValue /= 1000;
/* 161 */     int thousands = value - newValue * 1000;
/* 164 */     if (hasBillions) {
/* 165 */       offset = writeFullTriplet(newValue, buffer, offset);
/*     */     } else {
/* 167 */       offset = writeLeadingTriplet(newValue, buffer, offset);
/*     */     } 
/* 169 */     offset = writeFullTriplet(thousands, buffer, offset);
/* 170 */     offset = writeFullTriplet(ones, buffer, offset);
/* 171 */     return offset;
/*     */   }
/*     */   
/*     */   public static int writeInt(int value, byte[] buffer, int offset) {
/* 177 */     if (value < 0) {
/* 178 */       if (value == Integer.MIN_VALUE)
/* 179 */         return writeLong(value, buffer, offset); 
/* 181 */       buffer[offset++] = 45;
/* 182 */       value = -value;
/*     */     } 
/* 184 */     if (value < 1000000) {
/* 185 */       if (value < 1000) {
/* 186 */         if (value < 10) {
/* 187 */           buffer[offset++] = (byte)(48 + value);
/*     */         } else {
/* 189 */           offset = writeLeadingTriplet(value, buffer, offset);
/*     */         } 
/*     */       } else {
/* 192 */         int i = value / 1000;
/* 193 */         value -= i * 1000;
/* 194 */         offset = writeLeadingTriplet(i, buffer, offset);
/* 195 */         offset = writeFullTriplet(value, buffer, offset);
/*     */       } 
/* 197 */       return offset;
/*     */     } 
/* 199 */     boolean hasBillions = (value >= 1000000000);
/* 200 */     if (hasBillions) {
/* 201 */       value -= 1000000000;
/* 202 */       if (value >= 1000000000) {
/* 203 */         value -= 1000000000;
/* 204 */         buffer[offset++] = 50;
/*     */       } else {
/* 206 */         buffer[offset++] = 49;
/*     */       } 
/*     */     } 
/* 209 */     int newValue = value / 1000;
/* 210 */     int ones = value - newValue * 1000;
/* 211 */     value = newValue;
/* 212 */     newValue /= 1000;
/* 213 */     int thousands = value - newValue * 1000;
/* 216 */     if (hasBillions) {
/* 217 */       offset = writeFullTriplet(newValue, buffer, offset);
/*     */     } else {
/* 219 */       offset = writeLeadingTriplet(newValue, buffer, offset);
/*     */     } 
/* 221 */     offset = writeFullTriplet(thousands, buffer, offset);
/* 222 */     offset = writeFullTriplet(ones, buffer, offset);
/* 223 */     return offset;
/*     */   }
/*     */   
/*     */   public static int writeLong(long value, char[] buffer, int offset) {
/* 236 */     if (value < 0L) {
/* 237 */       if (value >= MIN_INT_AS_LONG)
/* 238 */         return writeInt((int)value, buffer, offset); 
/* 240 */       if (value == Long.MIN_VALUE)
/* 242 */         return getChars(String.valueOf(value), buffer, offset); 
/* 244 */       buffer[offset++] = '-';
/* 245 */       value = -value;
/* 247 */     } else if (value <= MAX_INT_AS_LONG) {
/* 248 */       return writeInt((int)value, buffer, offset);
/*     */     } 
/* 255 */     int origOffset = offset;
/* 256 */     offset += calcLongStrLength(value);
/* 257 */     int ptr = offset;
/* 260 */     while (value > MAX_INT_AS_LONG) {
/* 261 */       ptr -= 3;
/* 262 */       long newValue = value / 1000L;
/* 263 */       int triplet = (int)(value - newValue * 1000L);
/* 264 */       writeFullTriplet(triplet, buffer, ptr);
/* 265 */       value = newValue;
/*     */     } 
/* 268 */     int ivalue = (int)value;
/* 269 */     while (ivalue >= 1000) {
/* 270 */       ptr -= 3;
/* 271 */       int newValue = ivalue / 1000;
/* 272 */       int triplet = ivalue - newValue * 1000;
/* 273 */       writeFullTriplet(triplet, buffer, ptr);
/* 274 */       ivalue = newValue;
/*     */     } 
/* 277 */     writeLeadingTriplet(ivalue, buffer, origOffset);
/* 279 */     return offset;
/*     */   }
/*     */   
/*     */   public static int writeLong(long value, byte[] buffer, int offset) {
/* 285 */     if (value < 0L) {
/* 286 */       if (value >= MIN_INT_AS_LONG)
/* 287 */         return writeInt((int)value, buffer, offset); 
/* 289 */       if (value == Long.MIN_VALUE)
/* 290 */         return getAsciiBytes(String.valueOf(value), buffer, offset); 
/* 292 */       buffer[offset++] = 45;
/* 293 */       value = -value;
/* 295 */     } else if (value <= MAX_INT_AS_LONG) {
/* 296 */       return writeInt((int)value, buffer, offset);
/*     */     } 
/* 299 */     int origOffset = offset;
/* 300 */     offset += calcLongStrLength(value);
/* 301 */     int ptr = offset;
/* 303 */     while (value > MAX_INT_AS_LONG) {
/* 304 */       ptr -= 3;
/* 305 */       long newValue = value / 1000L;
/* 306 */       int triplet = (int)(value - newValue * 1000L);
/* 307 */       writeFullTriplet(triplet, buffer, ptr);
/* 308 */       value = newValue;
/*     */     } 
/* 310 */     int ivalue = (int)value;
/* 311 */     while (ivalue >= 1000) {
/* 312 */       ptr -= 3;
/* 313 */       int newValue = ivalue / 1000;
/* 314 */       int triplet = ivalue - newValue * 1000;
/* 315 */       writeFullTriplet(triplet, buffer, ptr);
/* 316 */       ivalue = newValue;
/*     */     } 
/* 318 */     writeLeadingTriplet(ivalue, buffer, origOffset);
/* 319 */     return offset;
/*     */   }
/*     */   
/*     */   public static int writeFloat(float value, char[] buffer, int offset) {
/* 325 */     return getChars(String.valueOf(value), buffer, offset);
/*     */   }
/*     */   
/*     */   public static int writeFloat(float value, byte[] buffer, int offset) {
/* 331 */     return getAsciiBytes(String.valueOf(value), buffer, offset);
/*     */   }
/*     */   
/*     */   public static int writeDouble(double value, char[] buffer, int offset) {
/* 337 */     return getChars(String.valueOf(value), buffer, offset);
/*     */   }
/*     */   
/*     */   public static int writeDouble(double value, byte[] buffer, int offset) {
/* 342 */     return getAsciiBytes(String.valueOf(value), buffer, offset);
/*     */   }
/*     */   
/*     */   private static int writeLeadingTriplet(int triplet, char[] buffer, int offset) {
/* 353 */     int digitOffset = triplet << 2;
/* 354 */     char c = LEADING_TRIPLETS[digitOffset++];
/* 355 */     if (c != '\000')
/* 356 */       buffer[offset++] = c; 
/* 358 */     c = LEADING_TRIPLETS[digitOffset++];
/* 359 */     if (c != '\000')
/* 360 */       buffer[offset++] = c; 
/* 363 */     buffer[offset++] = LEADING_TRIPLETS[digitOffset];
/* 364 */     return offset;
/*     */   }
/*     */   
/*     */   private static int writeLeadingTriplet(int triplet, byte[] buffer, int offset) {
/* 369 */     int digitOffset = triplet << 2;
/* 370 */     char c = LEADING_TRIPLETS[digitOffset++];
/* 371 */     if (c != '\000')
/* 372 */       buffer[offset++] = (byte)c; 
/* 374 */     c = LEADING_TRIPLETS[digitOffset++];
/* 375 */     if (c != '\000')
/* 376 */       buffer[offset++] = (byte)c; 
/* 379 */     buffer[offset++] = (byte)LEADING_TRIPLETS[digitOffset];
/* 380 */     return offset;
/*     */   }
/*     */   
/*     */   private static int writeFullTriplet(int triplet, char[] buffer, int offset) {
/* 385 */     int digitOffset = triplet << 2;
/* 386 */     buffer[offset++] = FULL_TRIPLETS[digitOffset++];
/* 387 */     buffer[offset++] = FULL_TRIPLETS[digitOffset++];
/* 388 */     buffer[offset++] = FULL_TRIPLETS[digitOffset];
/* 389 */     return offset;
/*     */   }
/*     */   
/*     */   private static int writeFullTriplet(int triplet, byte[] buffer, int offset) {
/* 394 */     int digitOffset = triplet << 2;
/* 395 */     buffer[offset++] = (byte)FULL_TRIPLETS[digitOffset++];
/* 396 */     buffer[offset++] = (byte)FULL_TRIPLETS[digitOffset++];
/* 397 */     buffer[offset++] = (byte)FULL_TRIPLETS[digitOffset];
/* 398 */     return offset;
/*     */   }
/*     */   
/*     */   private static int calcLongStrLength(long posValue) {
/* 408 */     int len = 10;
/* 409 */     long comp = 10000000000L;
/* 412 */     while (posValue >= comp && 
/* 413 */       len != 19) {
/* 416 */       len++;
/* 417 */       comp = (comp << 3L) + (comp << 1L);
/*     */     } 
/* 419 */     return len;
/*     */   }
/*     */   
/*     */   private static int getChars(String str, char[] buffer, int ptr) {
/* 424 */     int len = str.length();
/* 425 */     str.getChars(0, len, buffer, ptr);
/* 426 */     return ptr + len;
/*     */   }
/*     */   
/*     */   private static int getAsciiBytes(String str, byte[] buffer, int ptr) {
/* 431 */     for (int i = 0, len = str.length(); i < len; i++)
/* 432 */       buffer[ptr++] = (byte)str.charAt(i); 
/* 434 */     return ptr;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\typed\NumberUtil.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */