/*     */ package org.apache.commons.lang.math;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ public class RandomUtils {
/*  37 */   public static final Random JVM_RANDOM = new JVMRandom();
/*     */   
/*     */   public static int nextInt() {
/*  51 */     return nextInt(JVM_RANDOM);
/*     */   }
/*     */   
/*     */   public static int nextInt(Random random) {
/*  62 */     return random.nextInt();
/*     */   }
/*     */   
/*     */   public static int nextInt(int n) {
/*  74 */     return nextInt(JVM_RANDOM, n);
/*     */   }
/*     */   
/*     */   public static int nextInt(Random random, int n) {
/*  88 */     return random.nextInt(n);
/*     */   }
/*     */   
/*     */   public static long nextLong() {
/*  98 */     return nextLong(JVM_RANDOM);
/*     */   }
/*     */   
/*     */   public static long nextLong(Random random) {
/* 109 */     return random.nextLong();
/*     */   }
/*     */   
/*     */   public static boolean nextBoolean() {
/* 119 */     return nextBoolean(JVM_RANDOM);
/*     */   }
/*     */   
/*     */   public static boolean nextBoolean(Random random) {
/* 130 */     return random.nextBoolean();
/*     */   }
/*     */   
/*     */   public static float nextFloat() {
/* 141 */     return nextFloat(JVM_RANDOM);
/*     */   }
/*     */   
/*     */   public static float nextFloat(Random random) {
/* 153 */     return random.nextFloat();
/*     */   }
/*     */   
/*     */   public static double nextDouble() {
/* 164 */     return nextDouble(JVM_RANDOM);
/*     */   }
/*     */   
/*     */   public static double nextDouble(Random random) {
/* 176 */     return random.nextDouble();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\math\RandomUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */