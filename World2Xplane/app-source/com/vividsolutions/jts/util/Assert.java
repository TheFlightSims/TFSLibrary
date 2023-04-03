/*     */ package com.vividsolutions.jts.util;
/*     */ 
/*     */ public class Assert {
/*     */   public static void isTrue(boolean assertion) {
/*  54 */     isTrue(assertion, null);
/*     */   }
/*     */   
/*     */   public static void isTrue(boolean assertion, String message) {
/*  66 */     if (!assertion) {
/*  67 */       if (message == null)
/*  68 */         throw new AssertionFailedException(); 
/*  71 */       throw new AssertionFailedException(message);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void equals(Object expectedValue, Object actualValue) {
/*  85 */     equals(expectedValue, actualValue, null);
/*     */   }
/*     */   
/*     */   public static void equals(Object expectedValue, Object actualValue, String message) {
/*  99 */     if (!actualValue.equals(expectedValue))
/* 100 */       throw new AssertionFailedException("Expected " + expectedValue + " but encountered " + actualValue + ((message != null) ? (": " + message) : "")); 
/*     */   }
/*     */   
/*     */   public static void shouldNeverReachHere() {
/* 111 */     shouldNeverReachHere(null);
/*     */   }
/*     */   
/*     */   public static void shouldNeverReachHere(String message) {
/* 122 */     throw new AssertionFailedException("Should never reach here" + ((message != null) ? (": " + message) : ""));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jt\\util\Assert.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */