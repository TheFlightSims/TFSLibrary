/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ import java.util.AbstractQueue;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.Queue;
/*     */ import java.util.Set;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ 
/*     */ public final class Utilities {
/*  77 */   private static final String[] spaces = new String[21];
/*     */   
/*     */   private static final int PRIME_NUMBER = 37;
/*     */   
/*     */   static {
/*  79 */     int last = spaces.length - 1;
/*  80 */     char[] blancs = new char[last];
/*  81 */     Arrays.fill(blancs, ' ');
/*  82 */     spaces[last] = (new String(blancs)).intern();
/*     */   }
/*     */   
/*  93 */   private static final Queue<?> EMPTY_QUEUE = new EmptyQueue();
/*     */   
/*     */   private static final class EmptyQueue<E> extends AbstractQueue<E> implements Serializable {
/*     */     private static final long serialVersionUID = -6147951199761870325L;
/*     */     
/*     */     private EmptyQueue() {}
/*     */     
/*     */     public void clear() {}
/*     */     
/*     */     public boolean isEmpty() {
/* 111 */       return true;
/*     */     }
/*     */     
/*     */     public int size() {
/* 116 */       return 0;
/*     */     }
/*     */     
/*     */     public Iterator<E> iterator() {
/* 121 */       Set<E> empty = Collections.emptySet();
/* 122 */       return empty.iterator();
/*     */     }
/*     */     
/*     */     public boolean offer(E e) {
/* 127 */       return false;
/*     */     }
/*     */     
/*     */     public E poll() {
/* 132 */       return null;
/*     */     }
/*     */     
/*     */     public E peek() {
/* 137 */       return null;
/*     */     }
/*     */     
/*     */     protected Object readResolve() throws ObjectStreamException {
/* 142 */       return Utilities.EMPTY_QUEUE;
/*     */     }
/*     */   }
/*     */   
/*     */   public static boolean equals(boolean o1, boolean o2) {
/* 164 */     return (o1 == o2);
/*     */   }
/*     */   
/*     */   public static boolean equals(char o1, char o2) {
/* 179 */     return (o1 == o2);
/*     */   }
/*     */   
/*     */   public static boolean equals(byte o1, byte o2) {
/* 194 */     return (o1 == o2);
/*     */   }
/*     */   
/*     */   public static boolean equals(short o1, short o2) {
/* 209 */     return (o1 == o2);
/*     */   }
/*     */   
/*     */   public static boolean equals(int o1, int o2) {
/* 224 */     return (o1 == o2);
/*     */   }
/*     */   
/*     */   public static boolean equals(long o1, long o2) {
/* 239 */     return (o1 == o2);
/*     */   }
/*     */   
/*     */   public static boolean equals(float o1, float o2) {
/* 253 */     if (Float.floatToIntBits(o1) == Float.floatToIntBits(o2))
/* 254 */       return true; 
/* 256 */     double tol = getTolerance();
/* 257 */     double min = o1 - o1 * tol;
/* 258 */     double max = o1 + o1 * tol;
/* 259 */     return (min <= o2 && o2 <= max);
/*     */   }
/*     */   
/*     */   public static boolean equals(double o1, double o2) {
/* 273 */     if (Double.doubleToLongBits(o1) == Double.doubleToLongBits(o2))
/* 274 */       return true; 
/* 276 */     double tol = getTolerance();
/* 277 */     double min = o1 - Math.signum(o1) * o1 * tol;
/* 278 */     double max = o1 + Math.signum(o1) * o1 * tol;
/* 279 */     return (min <= o2 && o2 <= max);
/*     */   }
/*     */   
/*     */   private static double getTolerance() {
/* 287 */     Double tol = (Double)Hints.getSystemDefault((RenderingHints.Key)Hints.COMPARISON_TOLERANCE);
/* 288 */     if (tol == null)
/* 289 */       return Hints.COMPARISON_TOLERANCE.getDefault(); 
/* 291 */     return tol.doubleValue();
/*     */   }
/*     */   
/*     */   public static boolean equals(Object object1, Object object2) throws AssertionError {
/* 320 */     assert object1 == null || !object1.getClass().isArray() : object1;
/* 321 */     assert object2 == null || !object2.getClass().isArray() : object2;
/* 322 */     return (object1 == object2 || (object1 != null && object1.equals(object2)));
/*     */   }
/*     */   
/*     */   public static boolean deepEquals(Object object1, Object object2) {
/* 360 */     if (object1 == object2)
/* 361 */       return true; 
/* 363 */     if (object1 == null || object2 == null)
/* 364 */       return false; 
/* 366 */     if (object1 instanceof Object[])
/* 367 */       return (object2 instanceof Object[] && Arrays.deepEquals((Object[])object1, (Object[])object2)); 
/* 370 */     if (object1 instanceof double[])
/* 371 */       return (object2 instanceof double[] && Arrays.equals((double[])object1, (double[])object2)); 
/* 374 */     if (object1 instanceof float[])
/* 375 */       return (object2 instanceof float[] && Arrays.equals((float[])object1, (float[])object2)); 
/* 378 */     if (object1 instanceof long[])
/* 379 */       return (object2 instanceof long[] && Arrays.equals((long[])object1, (long[])object2)); 
/* 382 */     if (object1 instanceof int[])
/* 383 */       return (object2 instanceof int[] && Arrays.equals((int[])object1, (int[])object2)); 
/* 386 */     if (object1 instanceof short[])
/* 387 */       return (object2 instanceof short[] && Arrays.equals((short[])object1, (short[])object2)); 
/* 390 */     if (object1 instanceof byte[])
/* 391 */       return (object2 instanceof byte[] && Arrays.equals((byte[])object1, (byte[])object2)); 
/* 394 */     if (object1 instanceof char[])
/* 395 */       return (object2 instanceof char[] && Arrays.equals((char[])object1, (char[])object2)); 
/* 398 */     if (object1 instanceof boolean[])
/* 399 */       return (object2 instanceof boolean[] && Arrays.equals((boolean[])object1, (boolean[])object2)); 
/* 402 */     return object1.equals(object2);
/*     */   }
/*     */   
/*     */   public static int hash(boolean value, int seed) {
/* 415 */     return seed * 37 + (value ? 1231 : 1237);
/*     */   }
/*     */   
/*     */   public static int hash(char value, int seed) {
/* 427 */     return seed * 37 + value;
/*     */   }
/*     */   
/*     */   public static int hash(int value, int seed) {
/* 441 */     return seed * 37 + value;
/*     */   }
/*     */   
/*     */   public static int hash(long value, int seed) {
/* 455 */     return seed * 37 + ((int)value ^ (int)(value >>> 32L));
/*     */   }
/*     */   
/*     */   public static int hash(float value, int seed) {
/* 467 */     return seed * 37 + Float.floatToIntBits(value);
/*     */   }
/*     */   
/*     */   public static int hash(double value, int seed) {
/* 479 */     return hash(Double.doubleToLongBits(value), seed);
/*     */   }
/*     */   
/*     */   public static int hash(Object value, int seed) throws AssertionError {
/* 502 */     seed *= 37;
/* 503 */     if (value != null) {
/* 504 */       assert !value.getClass().isArray() : value;
/* 505 */       seed += value.hashCode();
/*     */     } 
/* 507 */     return seed;
/*     */   }
/*     */   
/*     */   public static int deepHashCode(Object object) {
/* 531 */     if (object == null)
/* 532 */       return 0; 
/* 534 */     if (object instanceof Object[])
/* 535 */       return Arrays.deepHashCode((Object[])object); 
/* 537 */     if (object instanceof double[])
/* 538 */       return Arrays.hashCode((double[])object); 
/* 540 */     if (object instanceof float[])
/* 541 */       return Arrays.hashCode((float[])object); 
/* 543 */     if (object instanceof long[])
/* 544 */       return Arrays.hashCode((long[])object); 
/* 546 */     if (object instanceof int[])
/* 547 */       return Arrays.hashCode((int[])object); 
/* 549 */     if (object instanceof short[])
/* 550 */       return Arrays.hashCode((short[])object); 
/* 552 */     if (object instanceof byte[])
/* 553 */       return Arrays.hashCode((byte[])object); 
/* 555 */     if (object instanceof char[])
/* 556 */       return Arrays.hashCode((char[])object); 
/* 558 */     if (object instanceof boolean[])
/* 559 */       return Arrays.hashCode((boolean[])object); 
/* 561 */     return object.hashCode();
/*     */   }
/*     */   
/*     */   public static String deepToString(Object object) {
/* 584 */     if (object instanceof Object[])
/* 585 */       return Arrays.deepToString((Object[])object); 
/* 587 */     if (object instanceof double[])
/* 588 */       return Arrays.toString((double[])object); 
/* 590 */     if (object instanceof float[])
/* 591 */       return Arrays.toString((float[])object); 
/* 593 */     if (object instanceof long[])
/* 594 */       return Arrays.toString((long[])object); 
/* 596 */     if (object instanceof int[])
/* 597 */       return Arrays.toString((int[])object); 
/* 599 */     if (object instanceof short[])
/* 600 */       return Arrays.toString((short[])object); 
/* 602 */     if (object instanceof byte[])
/* 603 */       return Arrays.toString((byte[])object); 
/* 605 */     if (object instanceof char[])
/* 606 */       return Arrays.toString((char[])object); 
/* 608 */     if (object instanceof boolean[])
/* 609 */       return Arrays.toString((boolean[])object); 
/* 611 */     return String.valueOf(object);
/*     */   }
/*     */   
/*     */   public static <E> Queue<E> emptyQueue() {
/* 625 */     return (Queue)EMPTY_QUEUE;
/*     */   }
/*     */   
/*     */   public static String spaces(int length) {
/*     */     String s;
/* 641 */     if (length < 0)
/* 642 */       length = 0; 
/* 645 */     if (length < spaces.length) {
/* 646 */       s = spaces[length];
/* 647 */       if (s == null) {
/* 648 */         s = spaces[spaces.length - 1].substring(0, length).intern();
/* 649 */         spaces[length] = s;
/*     */       } 
/*     */     } else {
/* 652 */       char[] blancs = new char[length];
/* 653 */       Arrays.fill(blancs, ' ');
/* 654 */       s = new String(blancs);
/*     */     } 
/* 656 */     return s;
/*     */   }
/*     */   
/*     */   public static void ensureNonNull(String name, Object object) throws NullPointerException {
/* 668 */     if (object == null)
/* 669 */       throw new NullPointerException(Errors.format(143, name)); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\Utilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */