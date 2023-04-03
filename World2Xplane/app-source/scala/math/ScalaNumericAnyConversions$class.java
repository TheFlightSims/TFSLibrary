/*     */ package scala.math;
/*     */ 
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ public abstract class ScalaNumericAnyConversions$class {
/*     */   public static void $init$(ScalaNumericAnyConversions $this) {}
/*     */   
/*     */   public static char toChar(ScalaNumericAnyConversions $this) {
/*  37 */     return (char)$this.intValue();
/*     */   }
/*     */   
/*     */   public static byte toByte(ScalaNumericAnyConversions $this) {
/*  42 */     return $this.byteValue();
/*     */   }
/*     */   
/*     */   public static short toShort(ScalaNumericAnyConversions $this) {
/*  47 */     return $this.shortValue();
/*     */   }
/*     */   
/*     */   public static int toInt(ScalaNumericAnyConversions $this) {
/*  52 */     return $this.intValue();
/*     */   }
/*     */   
/*     */   public static long toLong(ScalaNumericAnyConversions $this) {
/*  57 */     return $this.longValue();
/*     */   }
/*     */   
/*     */   public static float toFloat(ScalaNumericAnyConversions $this) {
/*  62 */     return $this.floatValue();
/*     */   }
/*     */   
/*     */   public static double toDouble(ScalaNumericAnyConversions $this) {
/*  67 */     return $this.doubleValue();
/*     */   }
/*     */   
/*     */   public static boolean isValidByte(ScalaNumericAnyConversions $this) {
/*  72 */     return ($this.isWhole() && $this.toInt() == $this.toByte());
/*     */   }
/*     */   
/*     */   public static boolean isValidShort(ScalaNumericAnyConversions $this) {
/*  77 */     return ($this.isWhole() && $this.toInt() == $this.toShort());
/*     */   }
/*     */   
/*     */   public static boolean isValidInt(ScalaNumericAnyConversions $this) {
/*  82 */     return ($this.isWhole() && $this.toLong() == $this.toInt());
/*     */   }
/*     */   
/*     */   public static boolean isValidChar(ScalaNumericAnyConversions $this) {
/*  87 */     return ($this.isWhole() && $this.toInt() >= 0 && $this.toInt() <= 65535);
/*     */   }
/*     */   
/*     */   public static int unifiedPrimitiveHashcode(ScalaNumericAnyConversions $this) {
/*  90 */     long lv = $this.toLong();
/*  91 */     return (lv >= -2147483648L && lv <= 2147483647L) ? (int)lv : ScalaRunTime$.MODULE$
/*  92 */       .hash(lv);
/*     */   }
/*     */   
/*     */   public static boolean unifiedPrimitiveEquals(ScalaNumericAnyConversions $this, Object x) {
/*     */     boolean bool;
/* 109 */     if (x instanceof Character) {
/* 109 */       char c = BoxesRunTime.unboxToChar(x);
/* 109 */       bool = ($this.isValidChar() && $this.toInt() == c) ? true : false;
/* 111 */     } else if (x instanceof Byte) {
/* 111 */       byte b = BoxesRunTime.unboxToByte(x);
/* 111 */       bool = ($this.isValidByte() && $this.toByte() == b) ? true : false;
/* 112 */     } else if (x instanceof Short) {
/* 112 */       short s = BoxesRunTime.unboxToShort(x);
/* 112 */       bool = ($this.isValidShort() && $this.toShort() == s) ? true : false;
/* 113 */     } else if (x instanceof Integer) {
/* 113 */       int i = BoxesRunTime.unboxToInt(x);
/* 113 */       bool = ($this.isValidInt() && $this.toInt() == i) ? true : false;
/* 114 */     } else if (x instanceof Long) {
/* 114 */       long l = BoxesRunTime.unboxToLong(x);
/* 114 */       bool = ($this.toLong() == l) ? true : false;
/* 115 */     } else if (x instanceof Float) {
/* 115 */       float f = BoxesRunTime.unboxToFloat(x);
/* 115 */       bool = ($this.toFloat() == f) ? true : false;
/* 116 */     } else if (x instanceof Double) {
/* 116 */       double d = BoxesRunTime.unboxToDouble(x);
/* 116 */       bool = ($this.toDouble() == d) ? true : false;
/*     */     } else {
/* 117 */       bool = false;
/*     */     } 
/*     */     return bool;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\math\ScalaNumericAnyConversions$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */