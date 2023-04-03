/*     */ package gnu.trove.impl;
/*     */ 
/*     */ public class Constants {
/*  26 */   private static final boolean VERBOSE = (System.getProperty("gnu.trove.verbose", null) != null);
/*     */   
/*     */   public static final int DEFAULT_CAPACITY = 10;
/*     */   
/*     */   public static final float DEFAULT_LOAD_FACTOR = 0.5F;
/*     */   
/*     */   public static final byte DEFAULT_BYTE_NO_ENTRY_VALUE;
/*     */   
/*     */   public static final short DEFAULT_SHORT_NO_ENTRY_VALUE;
/*     */   
/*     */   public static final char DEFAULT_CHAR_NO_ENTRY_VALUE;
/*     */   
/*     */   public static final int DEFAULT_INT_NO_ENTRY_VALUE;
/*     */   
/*     */   public static final long DEFAULT_LONG_NO_ENTRY_VALUE;
/*     */   
/*     */   public static final float DEFAULT_FLOAT_NO_ENTRY_VALUE;
/*     */   
/*     */   public static final double DEFAULT_DOUBLE_NO_ENTRY_VALUE;
/*     */   
/*     */   static {
/*  40 */     String property = System.getProperty("gnu.trove.no_entry.byte", "0");
/*  41 */     if ("MAX_VALUE".equalsIgnoreCase(property)) {
/*  41 */       s = 127;
/*  42 */     } else if ("MIN_VALUE".equalsIgnoreCase(property)) {
/*  42 */       s = -128;
/*     */     } else {
/*  43 */       s = Byte.valueOf(property).byteValue();
/*     */     } 
/*  45 */     if (s > 127) {
/*  45 */       s = 127;
/*  46 */     } else if (s < -128) {
/*  46 */       s = -128;
/*     */     } 
/*  47 */     DEFAULT_BYTE_NO_ENTRY_VALUE = s;
/*  48 */     if (VERBOSE)
/*  49 */       System.out.println("DEFAULT_BYTE_NO_ENTRY_VALUE: " + DEFAULT_BYTE_NO_ENTRY_VALUE); 
/*  59 */     property = System.getProperty("gnu.trove.no_entry.short", "0");
/*  60 */     if ("MAX_VALUE".equalsIgnoreCase(property)) {
/*  60 */       short s1 = Short.MAX_VALUE;
/*  61 */     } else if ("MIN_VALUE".equalsIgnoreCase(property)) {
/*  61 */       short s1 = Short.MIN_VALUE;
/*     */     } else {
/*  62 */       s = Short.valueOf(property).shortValue();
/*     */     } 
/*  64 */     if (s > Short.MAX_VALUE) {
/*  64 */       s = Short.MAX_VALUE;
/*  65 */     } else if (s < Short.MIN_VALUE) {
/*  65 */       s = Short.MIN_VALUE;
/*     */     } 
/*  66 */     DEFAULT_SHORT_NO_ENTRY_VALUE = s;
/*  67 */     if (VERBOSE)
/*  68 */       System.out.println("DEFAULT_SHORT_NO_ENTRY_VALUE: " + DEFAULT_SHORT_NO_ENTRY_VALUE); 
/*  78 */     property = System.getProperty("gnu.trove.no_entry.char", "\000");
/*  79 */     if ("MAX_VALUE".equalsIgnoreCase(property)) {
/*  79 */       i = 65535;
/*  80 */     } else if ("MIN_VALUE".equalsIgnoreCase(property)) {
/*  80 */       i = 0;
/*     */     } else {
/*  81 */       i = property.toCharArray()[0];
/*     */     } 
/*  83 */     if (i > 65535) {
/*  83 */       i = 65535;
/*  84 */     } else if (i < 0) {
/*  84 */       i = 0;
/*     */     } 
/*  85 */     DEFAULT_CHAR_NO_ENTRY_VALUE = i;
/*  86 */     if (VERBOSE)
/*  87 */       System.out.println("DEFAULT_CHAR_NO_ENTRY_VALUE: " + Integer.valueOf(i)); 
/*  97 */     property = System.getProperty("gnu.trove.no_entry.int", "0");
/*  98 */     if ("MAX_VALUE".equalsIgnoreCase(property)) {
/*  98 */       int j = Integer.MAX_VALUE;
/*  99 */     } else if ("MIN_VALUE".equalsIgnoreCase(property)) {
/*  99 */       int j = Integer.MIN_VALUE;
/*     */     } else {
/* 100 */       i = Integer.valueOf(property).intValue();
/*     */     } 
/* 101 */     DEFAULT_INT_NO_ENTRY_VALUE = i;
/* 102 */     if (VERBOSE)
/* 103 */       System.out.println("DEFAULT_INT_NO_ENTRY_VALUE: " + DEFAULT_INT_NO_ENTRY_VALUE); 
/* 113 */     String str1 = System.getProperty("gnu.trove.no_entry.long", "0");
/* 114 */     if ("MAX_VALUE".equalsIgnoreCase(str1)) {
/* 114 */       l = Long.MAX_VALUE;
/* 115 */     } else if ("MIN_VALUE".equalsIgnoreCase(str1)) {
/* 115 */       l = Long.MIN_VALUE;
/*     */     } else {
/* 116 */       l = Long.valueOf(str1).longValue();
/*     */     } 
/* 117 */     DEFAULT_LONG_NO_ENTRY_VALUE = l;
/* 118 */     if (VERBOSE)
/* 119 */       System.out.println("DEFAULT_LONG_NO_ENTRY_VALUE: " + DEFAULT_LONG_NO_ENTRY_VALUE); 
/* 129 */     property = System.getProperty("gnu.trove.no_entry.float", "0");
/* 130 */     if ("MAX_VALUE".equalsIgnoreCase(property)) {
/* 130 */       f = Float.MAX_VALUE;
/* 131 */     } else if ("MIN_VALUE".equalsIgnoreCase(property)) {
/* 131 */       f = Float.MIN_VALUE;
/* 133 */     } else if ("MIN_NORMAL".equalsIgnoreCase(property)) {
/* 133 */       f = 1.1754944E-38F;
/* 134 */     } else if ("NEGATIVE_INFINITY".equalsIgnoreCase(property)) {
/* 134 */       f = Float.NEGATIVE_INFINITY;
/* 135 */     } else if ("POSITIVE_INFINITY".equalsIgnoreCase(property)) {
/* 135 */       f = Float.POSITIVE_INFINITY;
/*     */     } else {
/* 137 */       f = Float.valueOf(property).floatValue();
/*     */     } 
/* 138 */     DEFAULT_FLOAT_NO_ENTRY_VALUE = f;
/* 139 */     if (VERBOSE)
/* 140 */       System.out.println("DEFAULT_FLOAT_NO_ENTRY_VALUE: " + DEFAULT_FLOAT_NO_ENTRY_VALUE); 
/* 150 */     str1 = System.getProperty("gnu.trove.no_entry.double", "0");
/* 151 */     if ("MAX_VALUE".equalsIgnoreCase(str1)) {
/* 151 */       value = Double.MAX_VALUE;
/* 152 */     } else if ("MIN_VALUE".equalsIgnoreCase(str1)) {
/* 152 */       value = Double.MIN_VALUE;
/* 154 */     } else if ("MIN_NORMAL".equalsIgnoreCase(str1)) {
/* 154 */       value = 2.2250738585072014E-308D;
/* 155 */     } else if ("NEGATIVE_INFINITY".equalsIgnoreCase(str1)) {
/* 155 */       value = Double.NEGATIVE_INFINITY;
/* 156 */     } else if ("POSITIVE_INFINITY".equalsIgnoreCase(str1)) {
/* 156 */       value = Double.POSITIVE_INFINITY;
/*     */     } else {
/* 158 */       value = Double.valueOf(str1).doubleValue();
/*     */     } 
/* 159 */     DEFAULT_DOUBLE_NO_ENTRY_VALUE = value;
/* 160 */     if (VERBOSE)
/* 161 */       System.out.println("DEFAULT_DOUBLE_NO_ENTRY_VALUE: " + DEFAULT_DOUBLE_NO_ENTRY_VALUE); 
/*     */   }
/*     */   
/*     */   static {
/*     */     short s;
/*     */     int i;
/*     */     long l;
/*     */     float f;
/*     */     double value;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\impl\Constants.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */