/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ 
/*     */ public class Ranks {
/*     */   private final int index;
/*     */   
/*     */   private Ranks(int index) {
/*  64 */     this.index = index;
/*     */   }
/*     */   
/*     */   public static <T extends Comparable<T>> int[] ranks(T[] source, T[] target) {
/*  80 */     if (source == null)
/*  81 */       return null; 
/*  83 */     Any[] entries = new Any[source.length];
/*  84 */     for (int i = 0; i < entries.length; i++)
/*  85 */       entries[i] = new Any<T>(i, source[i]); 
/*  87 */     int[] ranks = ranks((Ranks[])entries);
/*  88 */     if (target != null)
/*  89 */       for (int j = Math.min(entries.length, target.length); --j >= 0; ) {
/*  91 */         Any<T> entry = entries[j];
/*  92 */         target[j] = entry.value;
/*     */       }  
/*  95 */     return ranks;
/*     */   }
/*     */   
/*     */   public static int[] ranks(double[] source, double[] target) {
/* 110 */     if (source == null)
/* 111 */       return null; 
/* 113 */     Double[] entries = new Double[source.length];
/* 114 */     for (int i = 0; i < entries.length; i++)
/* 115 */       entries[i] = new Double(i, source[i]); 
/* 117 */     int[] ranks = ranks((Ranks[])entries);
/* 118 */     if (target != null)
/* 119 */       for (int j = Math.min(entries.length, target.length); --j >= 0;)
/* 120 */         target[j] = (entries[j]).value;  
/* 123 */     return ranks;
/*     */   }
/*     */   
/*     */   public static int[] ranks(float[] source, float[] target) {
/* 138 */     if (source == null)
/* 139 */       return null; 
/* 141 */     Float[] entries = new Float[source.length];
/* 142 */     for (int i = 0; i < entries.length; i++)
/* 143 */       entries[i] = new Float(i, source[i]); 
/* 145 */     int[] ranks = ranks((Ranks[])entries);
/* 146 */     if (target != null)
/* 147 */       for (int j = Math.min(entries.length, target.length); --j >= 0;)
/* 148 */         target[j] = (entries[j]).value;  
/* 151 */     return ranks;
/*     */   }
/*     */   
/*     */   public static int[] ranks(long[] source, long[] target) {
/* 166 */     if (source == null)
/* 167 */       return null; 
/* 169 */     Long[] entries = new Long[source.length];
/* 170 */     for (int i = 0; i < entries.length; i++)
/* 171 */       entries[i] = new Long(i, source[i]); 
/* 173 */     int[] ranks = ranks((Ranks[])entries);
/* 174 */     if (target != null)
/* 175 */       for (int j = Math.min(entries.length, target.length); --j >= 0;)
/* 176 */         target[j] = (entries[j]).value;  
/* 179 */     return ranks;
/*     */   }
/*     */   
/*     */   public static int[] ranks(int[] source, int[] target) {
/* 194 */     if (source == null)
/* 195 */       return null; 
/* 197 */     Integer[] entries = new Integer[source.length];
/* 198 */     for (int i = 0; i < entries.length; i++)
/* 199 */       entries[i] = new Integer(i, source[i]); 
/* 201 */     int[] ranks = ranks((Ranks[])entries);
/* 202 */     if (target != null)
/* 203 */       for (int j = Math.min(entries.length, target.length); --j >= 0;)
/* 204 */         target[j] = (entries[j]).value;  
/* 207 */     return ranks;
/*     */   }
/*     */   
/*     */   public static int[] ranks(short[] source, short[] target) {
/* 222 */     if (source == null)
/* 223 */       return null; 
/* 225 */     Short[] entries = new Short[source.length];
/* 226 */     for (int i = 0; i < entries.length; i++)
/* 227 */       entries[i] = new Short(i, source[i]); 
/* 229 */     int[] ranks = ranks((Ranks[])entries);
/* 230 */     if (target != null)
/* 231 */       for (int j = Math.min(entries.length, target.length); --j >= 0;)
/* 232 */         target[j] = (entries[j]).value;  
/* 235 */     return ranks;
/*     */   }
/*     */   
/*     */   public static int[] ranks(byte[] source, byte[] target) {
/* 250 */     if (source == null)
/* 251 */       return null; 
/* 253 */     Byte[] entries = new Byte[source.length];
/* 254 */     for (int i = 0; i < entries.length; i++)
/* 255 */       entries[i] = new Byte(i, source[i]); 
/* 257 */     int[] ranks = ranks((Ranks[])entries);
/* 258 */     if (target != null)
/* 259 */       for (int j = Math.min(entries.length, target.length); --j >= 0;)
/* 260 */         target[j] = (entries[j]).value;  
/* 263 */     return ranks;
/*     */   }
/*     */   
/*     */   private static int[] ranks(Ranks[] entries) {
/* 270 */     Arrays.sort((Object[])entries);
/* 271 */     int[] ranks = new int[entries.length];
/* 272 */     for (int i = 0; i < ranks.length; i++)
/* 273 */       ranks[i] = (entries[i]).index; 
/* 275 */     return ranks;
/*     */   }
/*     */   
/*     */   private static final class Any<T extends Comparable<T>> extends Ranks implements Comparable<Any<T>> {
/*     */     private final T value;
/*     */     
/*     */     Any(int index, T value) {
/* 285 */       super(index);
/* 286 */       this.value = value;
/*     */     }
/*     */     
/*     */     public int compareTo(Any<T> other) {
/* 291 */       return this.value.compareTo(other.value);
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class Double extends Ranks implements Comparable<Double> {
/*     */     private final double value;
/*     */     
/*     */     Double(int index, double value) {
/* 302 */       super(index);
/* 303 */       this.value = value;
/*     */     }
/*     */     
/*     */     public int compareTo(Double other) {
/* 308 */       return Double.compare(this.value, other.value);
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class Float extends Ranks implements Comparable<Float> {
/*     */     private final float value;
/*     */     
/*     */     Float(int index, float value) {
/* 319 */       super(index);
/* 320 */       this.value = value;
/*     */     }
/*     */     
/*     */     public int compareTo(Float other) {
/* 325 */       return Float.compare(this.value, other.value);
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class Long extends Ranks implements Comparable<Long> {
/*     */     private final long value;
/*     */     
/*     */     Long(int index, long value) {
/* 336 */       super(index);
/* 337 */       this.value = value;
/*     */     }
/*     */     
/*     */     public int compareTo(Long other) {
/* 342 */       return (this.value < other.value) ? -1 : ((this.value == other.value) ? 0 : 1);
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class Integer extends Ranks implements Comparable<Integer> {
/*     */     private final int value;
/*     */     
/*     */     Integer(int index, int value) {
/* 353 */       super(index);
/* 354 */       this.value = value;
/*     */     }
/*     */     
/*     */     public int compareTo(Integer other) {
/* 359 */       return (this.value < other.value) ? -1 : ((this.value == other.value) ? 0 : 1);
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class Short extends Ranks implements Comparable<Short> {
/*     */     private final short value;
/*     */     
/*     */     Short(int index, short value) {
/* 370 */       super(index);
/* 371 */       this.value = value;
/*     */     }
/*     */     
/*     */     public int compareTo(Short other) {
/* 376 */       return this.value - other.value;
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class Byte extends Ranks implements Comparable<Byte> {
/*     */     private final byte value;
/*     */     
/*     */     Byte(int index, byte value) {
/* 387 */       super(index);
/* 388 */       this.value = value;
/*     */     }
/*     */     
/*     */     public int compareTo(Byte other) {
/* 393 */       return this.value - other.value;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\Ranks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */