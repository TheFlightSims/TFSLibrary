/*     */ package org.mapdb;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ 
/*     */ public interface Hasher<K> {
/*  34 */   public static final Hasher BASIC = new Hasher() {
/*     */       public final int hashCode(Object k) {
/*  37 */         return k.hashCode();
/*     */       }
/*     */       
/*     */       public boolean equals(Object k1, Object k2) {
/*  42 */         return k1.equals(k2);
/*     */       }
/*     */     };
/*     */   
/*  46 */   public static final Hasher<byte[]> BYTE_ARRAY = new Hasher<byte[]>() {
/*     */       public final int hashCode(byte[] k) {
/*  49 */         return Arrays.hashCode(k);
/*     */       }
/*     */       
/*     */       public boolean equals(byte[] k1, byte[] k2) {
/*  54 */         return Arrays.equals(k1, k2);
/*     */       }
/*     */     };
/*     */   
/*  58 */   public static final Hasher<char[]> CHAR_ARRAY = new Hasher<char[]>() {
/*     */       public final int hashCode(char[] k) {
/*  61 */         return Arrays.hashCode(k);
/*     */       }
/*     */       
/*     */       public boolean equals(char[] k1, char[] k2) {
/*  66 */         return Arrays.equals(k1, k2);
/*     */       }
/*     */     };
/*     */   
/*  70 */   public static final Hasher<int[]> INT_ARRAY = new Hasher<int[]>() {
/*     */       public final int hashCode(int[] k) {
/*  73 */         return Arrays.hashCode(k);
/*     */       }
/*     */       
/*     */       public boolean equals(int[] k1, int[] k2) {
/*  78 */         return Arrays.equals(k1, k2);
/*     */       }
/*     */     };
/*     */   
/*  82 */   public static final Hasher<long[]> LONG_ARRAY = new Hasher<long[]>() {
/*     */       public final int hashCode(long[] k) {
/*  85 */         return Arrays.hashCode(k);
/*     */       }
/*     */       
/*     */       public boolean equals(long[] k1, long[] k2) {
/*  90 */         return Arrays.equals(k1, k2);
/*     */       }
/*     */     };
/*     */   
/*  94 */   public static final Hasher<double[]> DOUBLE_ARRAY = new Hasher<double[]>() {
/*     */       public final int hashCode(double[] k) {
/*  97 */         return Arrays.hashCode(k);
/*     */       }
/*     */       
/*     */       public boolean equals(double[] k1, double[] k2) {
/* 102 */         return Arrays.equals(k1, k2);
/*     */       }
/*     */     };
/*     */   
/* 107 */   public static final Hasher<Object[]> ARRAY = new Hasher<Object[]>() {
/*     */       public final int hashCode(Object[] k) {
/* 110 */         return Arrays.hashCode(k);
/*     */       }
/*     */       
/*     */       public boolean equals(Object[] k1, Object[] k2) {
/* 115 */         return Arrays.equals(k1, k2);
/*     */       }
/*     */     };
/*     */   
/*     */   int hashCode(K paramK);
/*     */   
/*     */   boolean equals(K paramK1, K paramK2);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\mapdb\Hasher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */