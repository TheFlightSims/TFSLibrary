/*    */ package com.ctc.wstx.util;
/*    */ 
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public final class InternCache extends LinkedHashMap {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   private static final int DEFAULT_SIZE = 64;
/*    */   
/*    */   private static final int MAX_SIZE = 660;
/*    */   
/* 31 */   private static final InternCache sInstance = new InternCache();
/*    */   
/*    */   private InternCache() {
/* 39 */     super(64, 0.6666F, false);
/*    */   }
/*    */   
/*    */   public static InternCache getInstance() {
/* 43 */     return sInstance;
/*    */   }
/*    */   
/*    */   public String intern(String input) {
/*    */     String result;
/* 53 */     synchronized (this) {
/* 54 */       result = (String)get(input);
/*    */     } 
/* 56 */     if (result == null) {
/* 57 */       result = input.intern();
/* 58 */       synchronized (this) {
/* 59 */         put((K)result, (V)result);
/*    */       } 
/*    */     } 
/* 62 */     return result;
/*    */   }
/*    */   
/*    */   protected boolean removeEldestEntry(Map.Entry eldest) {
/* 69 */     return (size() > 660);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wst\\util\InternCache.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */