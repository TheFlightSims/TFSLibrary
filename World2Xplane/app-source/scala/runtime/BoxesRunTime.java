/*     */ package scala.runtime;
/*     */ 
/*     */ public final class BoxesRunTime {
/*     */   private static final int CHAR = 0;
/*     */   
/*     */   private static final int BYTE = 1;
/*     */   
/*     */   private static final int SHORT = 2;
/*     */   
/*     */   private static final int INT = 3;
/*     */   
/*     */   private static final int LONG = 4;
/*     */   
/*     */   private static final int FLOAT = 5;
/*     */   
/*     */   private static final int DOUBLE = 6;
/*     */   
/*     */   private static final int OTHER = 7;
/*     */   
/*     */   private static int typeCode(Object a) {
/*  38 */     if (a instanceof Integer)
/*  38 */       return 3; 
/*  39 */     if (a instanceof Double)
/*  39 */       return 6; 
/*  40 */     if (a instanceof Long)
/*  40 */       return 4; 
/*  41 */     if (a instanceof Character)
/*  41 */       return 0; 
/*  42 */     if (a instanceof Float)
/*  42 */       return 5; 
/*  43 */     if (a instanceof Byte || a instanceof Short)
/*  43 */       return 3; 
/*  44 */     return 7;
/*     */   }
/*     */   
/*     */   private static String boxDescription(Object a) {
/*  48 */     return "" + a.getClass().getSimpleName() + "(" + a + ")";
/*     */   }
/*     */   
/*     */   public static Boolean boxToBoolean(boolean b) {
/*  54 */     return Boolean.valueOf(b);
/*     */   }
/*     */   
/*     */   public static Character boxToCharacter(char c) {
/*  58 */     return Character.valueOf(c);
/*     */   }
/*     */   
/*     */   public static Byte boxToByte(byte b) {
/*  62 */     return Byte.valueOf(b);
/*     */   }
/*     */   
/*     */   public static Short boxToShort(short s) {
/*  66 */     return Short.valueOf(s);
/*     */   }
/*     */   
/*     */   public static Integer boxToInteger(int i) {
/*  70 */     return Integer.valueOf(i);
/*     */   }
/*     */   
/*     */   public static Long boxToLong(long l) {
/*  74 */     return Long.valueOf(l);
/*     */   }
/*     */   
/*     */   public static Float boxToFloat(float f) {
/*  78 */     return Float.valueOf(f);
/*     */   }
/*     */   
/*     */   public static Double boxToDouble(double d) {
/*  84 */     return Double.valueOf(d);
/*     */   }
/*     */   
/*     */   public static boolean unboxToBoolean(Object b) {
/*  90 */     return (b == null) ? false : ((Boolean)b).booleanValue();
/*     */   }
/*     */   
/*     */   public static char unboxToChar(Object c) {
/*  94 */     return (c == null) ? Character.MIN_VALUE : ((Character)c).charValue();
/*     */   }
/*     */   
/*     */   public static byte unboxToByte(Object b) {
/*  98 */     return (b == null) ? 0 : ((Byte)b).byteValue();
/*     */   }
/*     */   
/*     */   public static short unboxToShort(Object s) {
/* 102 */     return (s == null) ? 0 : ((Short)s).shortValue();
/*     */   }
/*     */   
/*     */   public static int unboxToInt(Object i) {
/* 106 */     return (i == null) ? 0 : ((Integer)i).intValue();
/*     */   }
/*     */   
/*     */   public static long unboxToLong(Object l) {
/* 110 */     return (l == null) ? 0L : ((Long)l).longValue();
/*     */   }
/*     */   
/*     */   public static float unboxToFloat(Object f) {
/* 114 */     return (f == null) ? 0.0F : ((Float)f).floatValue();
/*     */   }
/*     */   
/*     */   public static double unboxToDouble(Object d) {
/* 119 */     return (d == null) ? 0.0D : ((Double)d).doubleValue();
/*     */   }
/*     */   
/*     */   public static boolean equals(Object x, Object y) {
/* 125 */     if (x == y)
/* 125 */       return true; 
/* 126 */     return equals2(x, y);
/*     */   }
/*     */   
/*     */   public static boolean equals2(Object x, Object y) {
/* 133 */     if (x instanceof Number)
/* 134 */       return equalsNumObject((Number)x, y); 
/* 135 */     if (x instanceof Character)
/* 136 */       return equalsCharObject((Character)x, y); 
/* 137 */     if (x == null)
/* 138 */       return (y == null); 
/* 140 */     return x.equals(y);
/*     */   }
/*     */   
/*     */   public static boolean equalsNumObject(Number xn, Object y) {
/* 144 */     if (y instanceof Number)
/* 145 */       return equalsNumNum(xn, (Number)y); 
/* 146 */     if (y instanceof Character)
/* 147 */       return equalsNumChar(xn, (Character)y); 
/* 148 */     if (xn == null)
/* 149 */       return (y == null); 
/* 151 */     return xn.equals(y);
/*     */   }
/*     */   
/*     */   public static boolean equalsNumNum(Number xn, Number yn) {
/* 155 */     int xcode = typeCode(xn);
/* 156 */     int ycode = typeCode(yn);
/* 157 */     switch ((ycode > xcode) ? ycode : xcode) {
/*     */       case 3:
/* 159 */         return (xn.intValue() == yn.intValue());
/*     */       case 4:
/* 161 */         return (xn.longValue() == yn.longValue());
/*     */       case 5:
/* 163 */         return (xn.floatValue() == yn.floatValue());
/*     */       case 6:
/* 165 */         return (xn.doubleValue() == yn.doubleValue());
/*     */     } 
/* 167 */     if (yn instanceof scala.math.ScalaNumber && !(xn instanceof scala.math.ScalaNumber))
/* 168 */       return yn.equals(xn); 
/* 170 */     if (xn == null)
/* 171 */       return (yn == null); 
/* 173 */     return xn.equals(yn);
/*     */   }
/*     */   
/*     */   public static boolean equalsCharObject(Character xc, Object y) {
/* 177 */     if (y instanceof Character)
/* 178 */       return (xc.charValue() == ((Character)y).charValue()); 
/* 179 */     if (y instanceof Number)
/* 180 */       return equalsNumChar((Number)y, xc); 
/* 181 */     if (xc == null)
/* 182 */       return (y == null); 
/* 184 */     return xc.equals(y);
/*     */   }
/*     */   
/*     */   private static boolean equalsNumChar(Number xn, Character yc) {
/* 188 */     if (yc == null)
/* 189 */       return (xn == null); 
/* 191 */     char ch = yc.charValue();
/* 192 */     switch (typeCode(xn)) {
/*     */       case 3:
/* 194 */         return (xn.intValue() == ch);
/*     */       case 4:
/* 196 */         return (xn.longValue() == ch);
/*     */       case 5:
/* 198 */         return (xn.floatValue() == ch);
/*     */       case 6:
/* 200 */         return (xn.doubleValue() == ch);
/*     */     } 
/* 202 */     return xn.equals(yc);
/*     */   }
/*     */   
/*     */   public static int hashFromLong(Long n) {
/* 234 */     int iv = n.intValue();
/* 235 */     if (iv == n.longValue())
/* 235 */       return iv; 
/* 236 */     return n.hashCode();
/*     */   }
/*     */   
/*     */   public static int hashFromDouble(Double n) {
/* 239 */     int iv = n.intValue();
/* 240 */     double dv = n.doubleValue();
/* 241 */     if (iv == dv)
/* 241 */       return iv; 
/* 243 */     long lv = n.longValue();
/* 244 */     if (lv == dv)
/* 244 */       return Long.valueOf(lv).hashCode(); 
/* 246 */     float fv = n.floatValue();
/* 247 */     if (fv == dv)
/* 247 */       return Float.valueOf(fv).hashCode(); 
/* 248 */     return n.hashCode();
/*     */   }
/*     */   
/*     */   public static int hashFromFloat(Float n) {
/* 251 */     int iv = n.intValue();
/* 252 */     float fv = n.floatValue();
/* 253 */     if (iv == fv)
/* 253 */       return iv; 
/* 255 */     long lv = n.longValue();
/* 256 */     if ((float)lv == fv)
/* 256 */       return Long.valueOf(lv).hashCode(); 
/* 257 */     return n.hashCode();
/*     */   }
/*     */   
/*     */   public static int hashFromNumber(Number n) {
/* 260 */     if (n instanceof Long)
/* 260 */       return hashFromLong((Long)n); 
/* 261 */     if (n instanceof Double)
/* 261 */       return hashFromDouble((Double)n); 
/* 262 */     if (n instanceof Float)
/* 262 */       return hashFromFloat((Float)n); 
/* 263 */     return n.hashCode();
/*     */   }
/*     */   
/*     */   public static int hashFromObject(Object a) {
/* 266 */     if (a instanceof Number)
/* 266 */       return hashFromNumber((Number)a); 
/* 267 */     return a.hashCode();
/*     */   }
/*     */   
/*     */   private static int unboxCharOrInt(Object arg1, int code) {
/* 271 */     if (code == 0)
/* 272 */       return ((Character)arg1).charValue(); 
/* 274 */     return ((Number)arg1).intValue();
/*     */   }
/*     */   
/*     */   private static long unboxCharOrLong(Object arg1, int code) {
/* 277 */     if (code == 0)
/* 278 */       return ((Character)arg1).charValue(); 
/* 280 */     return ((Number)arg1).longValue();
/*     */   }
/*     */   
/*     */   private static float unboxCharOrFloat(Object arg1, int code) {
/* 283 */     if (code == 0)
/* 284 */       return ((Character)arg1).charValue(); 
/* 286 */     return ((Number)arg1).floatValue();
/*     */   }
/*     */   
/*     */   private static double unboxCharOrDouble(Object arg1, int code) {
/* 289 */     if (code == 0)
/* 290 */       return ((Character)arg1).charValue(); 
/* 292 */     return ((Number)arg1).doubleValue();
/*     */   }
/*     */   
/*     */   public static Object add(Object arg1, Object arg2) throws NoSuchMethodException {
/* 299 */     int code1 = typeCode(arg1);
/* 300 */     int code2 = typeCode(arg2);
/* 301 */     int maxcode = (code1 < code2) ? code2 : code1;
/* 302 */     if (maxcode <= 3)
/* 303 */       return boxToInteger(unboxCharOrInt(arg1, code1) + unboxCharOrInt(arg2, code2)); 
/* 305 */     if (maxcode <= 4)
/* 306 */       return boxToLong(unboxCharOrLong(arg1, code1) + unboxCharOrLong(arg2, code2)); 
/* 308 */     if (maxcode <= 5)
/* 309 */       return boxToFloat(unboxCharOrFloat(arg1, code1) + unboxCharOrFloat(arg2, code2)); 
/* 311 */     if (maxcode <= 6)
/* 312 */       return boxToDouble(unboxCharOrDouble(arg1, code1) + unboxCharOrDouble(arg2, code2)); 
/* 314 */     throw new NoSuchMethodException();
/*     */   }
/*     */   
/*     */   public static Object subtract(Object arg1, Object arg2) throws NoSuchMethodException {
/* 319 */     int code1 = typeCode(arg1);
/* 320 */     int code2 = typeCode(arg2);
/* 321 */     int maxcode = (code1 < code2) ? code2 : code1;
/* 322 */     if (maxcode <= 3)
/* 323 */       return boxToInteger(unboxCharOrInt(arg1, code1) - unboxCharOrInt(arg2, code2)); 
/* 325 */     if (maxcode <= 4)
/* 326 */       return boxToLong(unboxCharOrLong(arg1, code1) - unboxCharOrLong(arg2, code2)); 
/* 328 */     if (maxcode <= 5)
/* 329 */       return boxToFloat(unboxCharOrFloat(arg1, code1) - unboxCharOrFloat(arg2, code2)); 
/* 331 */     if (maxcode <= 6)
/* 332 */       return boxToDouble(unboxCharOrDouble(arg1, code1) - unboxCharOrDouble(arg2, code2)); 
/* 334 */     throw new NoSuchMethodException();
/*     */   }
/*     */   
/*     */   public static Object multiply(Object arg1, Object arg2) throws NoSuchMethodException {
/* 339 */     int code1 = typeCode(arg1);
/* 340 */     int code2 = typeCode(arg2);
/* 341 */     int maxcode = (code1 < code2) ? code2 : code1;
/* 342 */     if (maxcode <= 3)
/* 343 */       return boxToInteger(unboxCharOrInt(arg1, code1) * unboxCharOrInt(arg2, code2)); 
/* 345 */     if (maxcode <= 4)
/* 346 */       return boxToLong(unboxCharOrLong(arg1, code1) * unboxCharOrLong(arg2, code2)); 
/* 348 */     if (maxcode <= 5)
/* 349 */       return boxToFloat(unboxCharOrFloat(arg1, code1) * unboxCharOrFloat(arg2, code2)); 
/* 351 */     if (maxcode <= 6)
/* 352 */       return boxToDouble(unboxCharOrDouble(arg1, code1) * unboxCharOrDouble(arg2, code2)); 
/* 354 */     throw new NoSuchMethodException();
/*     */   }
/*     */   
/*     */   public static Object divide(Object arg1, Object arg2) throws NoSuchMethodException {
/* 359 */     int code1 = typeCode(arg1);
/* 360 */     int code2 = typeCode(arg2);
/* 361 */     int maxcode = (code1 < code2) ? code2 : code1;
/* 363 */     if (maxcode <= 3)
/* 364 */       return boxToInteger(unboxCharOrInt(arg1, code1) / unboxCharOrInt(arg2, code2)); 
/* 365 */     if (maxcode <= 4)
/* 366 */       return boxToLong(unboxCharOrLong(arg1, code1) / unboxCharOrLong(arg2, code2)); 
/* 367 */     if (maxcode <= 5)
/* 368 */       return boxToFloat(unboxCharOrFloat(arg1, code1) / unboxCharOrFloat(arg2, code2)); 
/* 369 */     if (maxcode <= 6)
/* 370 */       return boxToDouble(unboxCharOrDouble(arg1, code1) / unboxCharOrDouble(arg2, code2)); 
/* 372 */     throw new NoSuchMethodException();
/*     */   }
/*     */   
/*     */   public static Object takeModulo(Object arg1, Object arg2) throws NoSuchMethodException {
/* 377 */     int code1 = typeCode(arg1);
/* 378 */     int code2 = typeCode(arg2);
/* 379 */     int maxcode = (code1 < code2) ? code2 : code1;
/* 381 */     if (maxcode <= 3)
/* 382 */       return boxToInteger(unboxCharOrInt(arg1, code1) % unboxCharOrInt(arg2, code2)); 
/* 383 */     if (maxcode <= 4)
/* 384 */       return boxToLong(unboxCharOrLong(arg1, code1) % unboxCharOrLong(arg2, code2)); 
/* 385 */     if (maxcode <= 5)
/* 386 */       return boxToFloat(unboxCharOrFloat(arg1, code1) % unboxCharOrFloat(arg2, code2)); 
/* 387 */     if (maxcode <= 6)
/* 388 */       return boxToDouble(unboxCharOrDouble(arg1, code1) % unboxCharOrDouble(arg2, code2)); 
/* 390 */     throw new NoSuchMethodException();
/*     */   }
/*     */   
/*     */   public static Object shiftSignedRight(Object arg1, Object arg2) throws NoSuchMethodException {
/* 395 */     int code1 = typeCode(arg1);
/* 396 */     int code2 = typeCode(arg2);
/* 397 */     if (code1 <= 3) {
/* 398 */       int val1 = unboxCharOrInt(arg1, code1);
/* 399 */       if (code2 <= 3) {
/* 400 */         int val2 = unboxCharOrInt(arg2, code2);
/* 401 */         return boxToInteger(val1 >> val2);
/*     */       } 
/* 403 */       if (code2 <= 4) {
/* 404 */         long val2 = unboxCharOrLong(arg2, code2);
/* 405 */         return boxToInteger(val1 >> (int)val2);
/*     */       } 
/*     */     } 
/* 408 */     if (code1 <= 4) {
/* 409 */       long val1 = unboxCharOrLong(arg1, code1);
/* 410 */       if (code2 <= 3) {
/* 411 */         int val2 = unboxCharOrInt(arg2, code2);
/* 412 */         return boxToLong(val1 >> val2);
/*     */       } 
/* 414 */       if (code2 <= 4) {
/* 415 */         long val2 = unboxCharOrLong(arg2, code2);
/* 416 */         return boxToLong(val1 >> (int)val2);
/*     */       } 
/*     */     } 
/* 419 */     throw new NoSuchMethodException();
/*     */   }
/*     */   
/*     */   public static Object shiftSignedLeft(Object arg1, Object arg2) throws NoSuchMethodException {
/* 424 */     int code1 = typeCode(arg1);
/* 425 */     int code2 = typeCode(arg2);
/* 426 */     if (code1 <= 3) {
/* 427 */       int val1 = unboxCharOrInt(arg1, code1);
/* 428 */       if (code2 <= 3) {
/* 429 */         int val2 = unboxCharOrInt(arg2, code2);
/* 430 */         return boxToInteger(val1 << val2);
/*     */       } 
/* 432 */       if (code2 <= 4) {
/* 433 */         long val2 = unboxCharOrLong(arg2, code2);
/* 434 */         return boxToInteger(val1 << (int)val2);
/*     */       } 
/*     */     } 
/* 437 */     if (code1 <= 4) {
/* 438 */       long val1 = unboxCharOrLong(arg1, code1);
/* 439 */       if (code2 <= 3) {
/* 440 */         int val2 = unboxCharOrInt(arg2, code2);
/* 441 */         return boxToLong(val1 << val2);
/*     */       } 
/* 443 */       if (code2 <= 4) {
/* 444 */         long val2 = unboxCharOrLong(arg2, code2);
/* 445 */         return boxToLong(val1 << (int)val2);
/*     */       } 
/*     */     } 
/* 448 */     throw new NoSuchMethodException();
/*     */   }
/*     */   
/*     */   public static Object shiftLogicalRight(Object arg1, Object arg2) throws NoSuchMethodException {
/* 453 */     int code1 = typeCode(arg1);
/* 454 */     int code2 = typeCode(arg2);
/* 455 */     if (code1 <= 3) {
/* 456 */       int val1 = unboxCharOrInt(arg1, code1);
/* 457 */       if (code2 <= 3) {
/* 458 */         int val2 = unboxCharOrInt(arg2, code2);
/* 459 */         return boxToInteger(val1 >>> val2);
/*     */       } 
/* 461 */       if (code2 <= 4) {
/* 462 */         long val2 = unboxCharOrLong(arg2, code2);
/* 463 */         return boxToInteger(val1 >>> (int)val2);
/*     */       } 
/*     */     } 
/* 466 */     if (code1 <= 4) {
/* 467 */       long val1 = unboxCharOrLong(arg1, code1);
/* 468 */       if (code2 <= 3) {
/* 469 */         int val2 = unboxCharOrInt(arg2, code2);
/* 470 */         return boxToLong(val1 >>> val2);
/*     */       } 
/* 472 */       if (code2 <= 4) {
/* 473 */         long val2 = unboxCharOrLong(arg2, code2);
/* 474 */         return boxToLong(val1 >>> (int)val2);
/*     */       } 
/*     */     } 
/* 477 */     throw new NoSuchMethodException();
/*     */   }
/*     */   
/*     */   public static Object negate(Object arg) throws NoSuchMethodException {
/* 482 */     int code = typeCode(arg);
/* 483 */     if (code <= 3) {
/* 484 */       int val = unboxCharOrInt(arg, code);
/* 485 */       return boxToInteger(-val);
/*     */     } 
/* 487 */     if (code <= 4) {
/* 488 */       long val = unboxCharOrLong(arg, code);
/* 489 */       return boxToLong(-val);
/*     */     } 
/* 491 */     if (code <= 5) {
/* 492 */       float val = unboxCharOrFloat(arg, code);
/* 493 */       return boxToFloat(-val);
/*     */     } 
/* 495 */     if (code <= 6) {
/* 496 */       double val = unboxCharOrDouble(arg, code);
/* 497 */       return boxToDouble(-val);
/*     */     } 
/* 499 */     throw new NoSuchMethodException();
/*     */   }
/*     */   
/*     */   public static Object positive(Object arg) throws NoSuchMethodException {
/* 504 */     int code = typeCode(arg);
/* 505 */     if (code <= 3)
/* 506 */       return boxToInteger(unboxCharOrInt(arg, code)); 
/* 508 */     if (code <= 4)
/* 509 */       return boxToLong(unboxCharOrLong(arg, code)); 
/* 511 */     if (code <= 5)
/* 512 */       return boxToFloat(unboxCharOrFloat(arg, code)); 
/* 514 */     if (code <= 6)
/* 515 */       return boxToDouble(unboxCharOrDouble(arg, code)); 
/* 517 */     throw new NoSuchMethodException();
/*     */   }
/*     */   
/*     */   public static Object takeAnd(Object arg1, Object arg2) throws NoSuchMethodException {
/* 522 */     if (arg1 instanceof Boolean || arg2 instanceof Boolean) {
/* 523 */       if (arg1 instanceof Boolean && arg2 instanceof Boolean)
/* 524 */         return boxToBoolean(((Boolean)arg1).booleanValue() & ((Boolean)arg2).booleanValue()); 
/* 526 */       throw new NoSuchMethodException();
/*     */     } 
/* 528 */     int code1 = typeCode(arg1);
/* 529 */     int code2 = typeCode(arg2);
/* 530 */     int maxcode = (code1 < code2) ? code2 : code1;
/* 532 */     if (maxcode <= 3)
/* 533 */       return boxToInteger(unboxCharOrInt(arg1, code1) & unboxCharOrInt(arg2, code2)); 
/* 534 */     if (maxcode <= 4)
/* 535 */       return boxToLong(unboxCharOrLong(arg1, code1) & unboxCharOrLong(arg2, code2)); 
/* 537 */     throw new NoSuchMethodException();
/*     */   }
/*     */   
/*     */   public static Object takeOr(Object arg1, Object arg2) throws NoSuchMethodException {
/* 542 */     if (arg1 instanceof Boolean || arg2 instanceof Boolean) {
/* 543 */       if (arg1 instanceof Boolean && arg2 instanceof Boolean)
/* 544 */         return boxToBoolean(((Boolean)arg1).booleanValue() | ((Boolean)arg2).booleanValue()); 
/* 546 */       throw new NoSuchMethodException();
/*     */     } 
/* 548 */     int code1 = typeCode(arg1);
/* 549 */     int code2 = typeCode(arg2);
/* 550 */     int maxcode = (code1 < code2) ? code2 : code1;
/* 552 */     if (maxcode <= 3)
/* 553 */       return boxToInteger(unboxCharOrInt(arg1, code1) | unboxCharOrInt(arg2, code2)); 
/* 554 */     if (maxcode <= 4)
/* 555 */       return boxToLong(unboxCharOrLong(arg1, code1) | unboxCharOrLong(arg2, code2)); 
/* 557 */     throw new NoSuchMethodException();
/*     */   }
/*     */   
/*     */   public static Object takeXor(Object arg1, Object arg2) throws NoSuchMethodException {
/* 562 */     if (arg1 instanceof Boolean || arg2 instanceof Boolean) {
/* 563 */       if (arg1 instanceof Boolean && arg2 instanceof Boolean)
/* 564 */         return boxToBoolean(((Boolean)arg1).booleanValue() ^ ((Boolean)arg2).booleanValue()); 
/* 566 */       throw new NoSuchMethodException();
/*     */     } 
/* 568 */     int code1 = typeCode(arg1);
/* 569 */     int code2 = typeCode(arg2);
/* 570 */     int maxcode = (code1 < code2) ? code2 : code1;
/* 572 */     if (maxcode <= 3)
/* 573 */       return boxToInteger(unboxCharOrInt(arg1, code1) ^ unboxCharOrInt(arg2, code2)); 
/* 574 */     if (maxcode <= 4)
/* 575 */       return boxToLong(unboxCharOrLong(arg1, code1) ^ unboxCharOrLong(arg2, code2)); 
/* 577 */     throw new NoSuchMethodException();
/*     */   }
/*     */   
/*     */   public static Object takeConditionalAnd(Object arg1, Object arg2) throws NoSuchMethodException {
/* 582 */     if (arg1 instanceof Boolean && arg2 instanceof Boolean)
/* 583 */       return boxToBoolean((((Boolean)arg1).booleanValue() && ((Boolean)arg2).booleanValue())); 
/* 585 */     throw new NoSuchMethodException();
/*     */   }
/*     */   
/*     */   public static Object takeConditionalOr(Object arg1, Object arg2) throws NoSuchMethodException {
/* 590 */     if (arg1 instanceof Boolean && arg2 instanceof Boolean)
/* 591 */       return boxToBoolean((((Boolean)arg1).booleanValue() || ((Boolean)arg2).booleanValue())); 
/* 593 */     throw new NoSuchMethodException();
/*     */   }
/*     */   
/*     */   public static Object complement(Object arg) throws NoSuchMethodException {
/* 598 */     int code = typeCode(arg);
/* 599 */     if (code <= 3)
/* 600 */       return boxToInteger(unboxCharOrInt(arg, code) ^ 0xFFFFFFFF); 
/* 602 */     if (code <= 4)
/* 603 */       return boxToLong(unboxCharOrLong(arg, code) ^ 0xFFFFFFFFFFFFFFFFL); 
/* 605 */     throw new NoSuchMethodException();
/*     */   }
/*     */   
/*     */   public static Object takeNot(Object arg) throws NoSuchMethodException {
/* 610 */     if (arg instanceof Boolean)
/* 611 */       return boxToBoolean(!((Boolean)arg).booleanValue()); 
/* 613 */     throw new NoSuchMethodException();
/*     */   }
/*     */   
/*     */   public static Object testEqual(Object arg1, Object arg2) throws NoSuchMethodException {
/* 617 */     return boxToBoolean((arg1 == arg2));
/*     */   }
/*     */   
/*     */   public static Object testNotEqual(Object arg1, Object arg2) throws NoSuchMethodException {
/* 621 */     return boxToBoolean((arg1 != arg2));
/*     */   }
/*     */   
/*     */   public static Object testLessThan(Object arg1, Object arg2) throws NoSuchMethodException {
/* 625 */     int code1 = typeCode(arg1);
/* 626 */     int code2 = typeCode(arg2);
/* 627 */     int maxcode = (code1 < code2) ? code2 : code1;
/* 628 */     if (maxcode <= 3) {
/* 629 */       int val1 = unboxCharOrInt(arg1, code1);
/* 630 */       int val2 = unboxCharOrInt(arg2, code2);
/* 631 */       return boxToBoolean((val1 < val2));
/*     */     } 
/* 633 */     if (maxcode <= 4) {
/* 634 */       long val1 = unboxCharOrLong(arg1, code1);
/* 635 */       long val2 = unboxCharOrLong(arg2, code2);
/* 636 */       return boxToBoolean((val1 < val2));
/*     */     } 
/* 638 */     if (maxcode <= 5) {
/* 639 */       float val1 = unboxCharOrFloat(arg1, code1);
/* 640 */       float val2 = unboxCharOrFloat(arg2, code2);
/* 641 */       return boxToBoolean((val1 < val2));
/*     */     } 
/* 643 */     if (maxcode <= 6) {
/* 644 */       double val1 = unboxCharOrDouble(arg1, code1);
/* 645 */       double val2 = unboxCharOrDouble(arg2, code2);
/* 646 */       return boxToBoolean((val1 < val2));
/*     */     } 
/* 648 */     throw new NoSuchMethodException();
/*     */   }
/*     */   
/*     */   public static Object testLessOrEqualThan(Object arg1, Object arg2) throws NoSuchMethodException {
/* 652 */     int code1 = typeCode(arg1);
/* 653 */     int code2 = typeCode(arg2);
/* 654 */     int maxcode = (code1 < code2) ? code2 : code1;
/* 655 */     if (maxcode <= 3) {
/* 656 */       int val1 = unboxCharOrInt(arg1, code1);
/* 657 */       int val2 = unboxCharOrInt(arg2, code2);
/* 658 */       return boxToBoolean((val1 <= val2));
/*     */     } 
/* 660 */     if (maxcode <= 4) {
/* 661 */       long val1 = unboxCharOrLong(arg1, code1);
/* 662 */       long val2 = unboxCharOrLong(arg2, code2);
/* 663 */       return boxToBoolean((val1 <= val2));
/*     */     } 
/* 665 */     if (maxcode <= 5) {
/* 666 */       float val1 = unboxCharOrFloat(arg1, code1);
/* 667 */       float val2 = unboxCharOrFloat(arg2, code2);
/* 668 */       return boxToBoolean((val1 <= val2));
/*     */     } 
/* 670 */     if (maxcode <= 6) {
/* 671 */       double val1 = unboxCharOrDouble(arg1, code1);
/* 672 */       double val2 = unboxCharOrDouble(arg2, code2);
/* 673 */       return boxToBoolean((val1 <= val2));
/*     */     } 
/* 675 */     throw new NoSuchMethodException();
/*     */   }
/*     */   
/*     */   public static Object testGreaterOrEqualThan(Object arg1, Object arg2) throws NoSuchMethodException {
/* 679 */     int code1 = typeCode(arg1);
/* 680 */     int code2 = typeCode(arg2);
/* 681 */     int maxcode = (code1 < code2) ? code2 : code1;
/* 682 */     if (maxcode <= 3) {
/* 683 */       int val1 = unboxCharOrInt(arg1, code1);
/* 684 */       int val2 = unboxCharOrInt(arg2, code2);
/* 685 */       return boxToBoolean((val1 >= val2));
/*     */     } 
/* 687 */     if (maxcode <= 4) {
/* 688 */       long val1 = unboxCharOrLong(arg1, code1);
/* 689 */       long val2 = unboxCharOrLong(arg2, code2);
/* 690 */       return boxToBoolean((val1 >= val2));
/*     */     } 
/* 692 */     if (maxcode <= 5) {
/* 693 */       float val1 = unboxCharOrFloat(arg1, code1);
/* 694 */       float val2 = unboxCharOrFloat(arg2, code2);
/* 695 */       return boxToBoolean((val1 >= val2));
/*     */     } 
/* 697 */     if (maxcode <= 6) {
/* 698 */       double val1 = unboxCharOrDouble(arg1, code1);
/* 699 */       double val2 = unboxCharOrDouble(arg2, code2);
/* 700 */       return boxToBoolean((val1 >= val2));
/*     */     } 
/* 702 */     throw new NoSuchMethodException();
/*     */   }
/*     */   
/*     */   public static Object testGreaterThan(Object arg1, Object arg2) throws NoSuchMethodException {
/* 706 */     int code1 = typeCode(arg1);
/* 707 */     int code2 = typeCode(arg2);
/* 708 */     int maxcode = (code1 < code2) ? code2 : code1;
/* 709 */     if (maxcode <= 3) {
/* 710 */       int val1 = unboxCharOrInt(arg1, code1);
/* 711 */       int val2 = unboxCharOrInt(arg2, code2);
/* 712 */       return boxToBoolean((val1 > val2));
/*     */     } 
/* 714 */     if (maxcode <= 4) {
/* 715 */       long val1 = unboxCharOrLong(arg1, code1);
/* 716 */       long val2 = unboxCharOrLong(arg2, code2);
/* 717 */       return boxToBoolean((val1 > val2));
/*     */     } 
/* 719 */     if (maxcode <= 5) {
/* 720 */       float val1 = unboxCharOrFloat(arg1, code1);
/* 721 */       float val2 = unboxCharOrFloat(arg2, code2);
/* 722 */       return boxToBoolean((val1 > val2));
/*     */     } 
/* 724 */     if (maxcode <= 6) {
/* 725 */       double val1 = unboxCharOrDouble(arg1, code1);
/* 726 */       double val2 = unboxCharOrDouble(arg2, code2);
/* 727 */       return boxToBoolean((val1 > val2));
/*     */     } 
/* 729 */     throw new NoSuchMethodException();
/*     */   }
/*     */   
/*     */   public static boolean isBoxedNumberOrBoolean(Object arg) {
/* 733 */     return (arg instanceof Boolean || isBoxedNumber(arg));
/*     */   }
/*     */   
/*     */   public static boolean isBoxedNumber(Object arg) {
/* 736 */     return (arg instanceof Integer || arg instanceof Long || arg instanceof Double || arg instanceof Float || arg instanceof Short || arg instanceof Character || arg instanceof Byte);
/*     */   }
/*     */   
/*     */   public static Character toCharacter(Object arg) throws NoSuchMethodException {
/* 749 */     if (arg instanceof Integer)
/* 749 */       return boxToCharacter((char)unboxToInt(arg)); 
/* 750 */     if (arg instanceof Short)
/* 750 */       return boxToCharacter((char)unboxToShort(arg)); 
/* 751 */     if (arg instanceof Character)
/* 751 */       return (Character)arg; 
/* 752 */     if (arg instanceof Long)
/* 752 */       return boxToCharacter((char)(int)unboxToLong(arg)); 
/* 753 */     if (arg instanceof Byte)
/* 753 */       return boxToCharacter((char)unboxToByte(arg)); 
/* 754 */     if (arg instanceof Float)
/* 754 */       return boxToCharacter((char)(int)unboxToFloat(arg)); 
/* 755 */     if (arg instanceof Double)
/* 755 */       return boxToCharacter((char)(int)unboxToDouble(arg)); 
/* 756 */     throw new NoSuchMethodException();
/*     */   }
/*     */   
/*     */   public static Byte toByte(Object arg) throws NoSuchMethodException {
/* 761 */     if (arg instanceof Integer)
/* 761 */       return boxToByte((byte)unboxToInt(arg)); 
/* 762 */     if (arg instanceof Character)
/* 762 */       return boxToByte((byte)unboxToChar(arg)); 
/* 763 */     if (arg instanceof Byte)
/* 763 */       return (Byte)arg; 
/* 764 */     if (arg instanceof Long)
/* 764 */       return boxToByte((byte)(int)unboxToLong(arg)); 
/* 765 */     if (arg instanceof Short)
/* 765 */       return boxToByte((byte)unboxToShort(arg)); 
/* 766 */     if (arg instanceof Float)
/* 766 */       return boxToByte((byte)(int)unboxToFloat(arg)); 
/* 767 */     if (arg instanceof Double)
/* 767 */       return boxToByte((byte)(int)unboxToDouble(arg)); 
/* 768 */     throw new NoSuchMethodException();
/*     */   }
/*     */   
/*     */   public static Short toShort(Object arg) throws NoSuchMethodException {
/* 773 */     if (arg instanceof Integer)
/* 773 */       return boxToShort((short)unboxToInt(arg)); 
/* 774 */     if (arg instanceof Long)
/* 774 */       return boxToShort((short)(int)unboxToLong(arg)); 
/* 775 */     if (arg instanceof Character)
/* 775 */       return boxToShort((short)unboxToChar(arg)); 
/* 776 */     if (arg instanceof Byte)
/* 776 */       return boxToShort((short)unboxToByte(arg)); 
/* 777 */     if (arg instanceof Short)
/* 777 */       return (Short)arg; 
/* 778 */     if (arg instanceof Float)
/* 778 */       return boxToShort((short)(int)unboxToFloat(arg)); 
/* 779 */     if (arg instanceof Double)
/* 779 */       return boxToShort((short)(int)unboxToDouble(arg)); 
/* 780 */     throw new NoSuchMethodException();
/*     */   }
/*     */   
/*     */   public static Integer toInteger(Object arg) throws NoSuchMethodException {
/* 785 */     if (arg instanceof Integer)
/* 785 */       return (Integer)arg; 
/* 786 */     if (arg instanceof Long)
/* 786 */       return boxToInteger((int)unboxToLong(arg)); 
/* 787 */     if (arg instanceof Double)
/* 787 */       return boxToInteger((int)unboxToDouble(arg)); 
/* 788 */     if (arg instanceof Float)
/* 788 */       return boxToInteger((int)unboxToFloat(arg)); 
/* 789 */     if (arg instanceof Character)
/* 789 */       return boxToInteger(unboxToChar(arg)); 
/* 790 */     if (arg instanceof Byte)
/* 790 */       return boxToInteger(unboxToByte(arg)); 
/* 791 */     if (arg instanceof Short)
/* 791 */       return boxToInteger(unboxToShort(arg)); 
/* 792 */     throw new NoSuchMethodException();
/*     */   }
/*     */   
/*     */   public static Long toLong(Object arg) throws NoSuchMethodException {
/* 797 */     if (arg instanceof Integer)
/* 797 */       return boxToLong(unboxToInt(arg)); 
/* 798 */     if (arg instanceof Double)
/* 798 */       return boxToLong((long)unboxToDouble(arg)); 
/* 799 */     if (arg instanceof Float)
/* 799 */       return boxToLong((long)unboxToFloat(arg)); 
/* 800 */     if (arg instanceof Long)
/* 800 */       return (Long)arg; 
/* 801 */     if (arg instanceof Character)
/* 801 */       return boxToLong(unboxToChar(arg)); 
/* 802 */     if (arg instanceof Byte)
/* 802 */       return boxToLong(unboxToByte(arg)); 
/* 803 */     if (arg instanceof Short)
/* 803 */       return boxToLong(unboxToShort(arg)); 
/* 804 */     throw new NoSuchMethodException();
/*     */   }
/*     */   
/*     */   public static Float toFloat(Object arg) throws NoSuchMethodException {
/* 809 */     if (arg instanceof Integer)
/* 809 */       return boxToFloat(unboxToInt(arg)); 
/* 810 */     if (arg instanceof Long)
/* 810 */       return boxToFloat((float)unboxToLong(arg)); 
/* 811 */     if (arg instanceof Float)
/* 811 */       return (Float)arg; 
/* 812 */     if (arg instanceof Double)
/* 812 */       return boxToFloat((float)unboxToDouble(arg)); 
/* 813 */     if (arg instanceof Character)
/* 813 */       return boxToFloat(unboxToChar(arg)); 
/* 814 */     if (arg instanceof Byte)
/* 814 */       return boxToFloat(unboxToByte(arg)); 
/* 815 */     if (arg instanceof Short)
/* 815 */       return boxToFloat(unboxToShort(arg)); 
/* 816 */     throw new NoSuchMethodException();
/*     */   }
/*     */   
/*     */   public static Double toDouble(Object arg) throws NoSuchMethodException {
/* 821 */     if (arg instanceof Integer)
/* 821 */       return boxToDouble(unboxToInt(arg)); 
/* 822 */     if (arg instanceof Float)
/* 822 */       return boxToDouble(unboxToFloat(arg)); 
/* 823 */     if (arg instanceof Double)
/* 823 */       return (Double)arg; 
/* 824 */     if (arg instanceof Long)
/* 824 */       return boxToDouble(unboxToLong(arg)); 
/* 825 */     if (arg instanceof Character)
/* 825 */       return boxToDouble(unboxToChar(arg)); 
/* 826 */     if (arg instanceof Byte)
/* 826 */       return boxToDouble(unboxToByte(arg)); 
/* 827 */     if (arg instanceof Short)
/* 827 */       return boxToDouble(unboxToShort(arg)); 
/* 828 */     throw new NoSuchMethodException();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\BoxesRunTime.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */