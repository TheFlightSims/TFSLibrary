/*    */ package com.ctc.wstx.util;
/*    */ 
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public final class SimpleCache {
/*    */   protected final LimitMap mItems;
/*    */   
/*    */   protected final int mMaxSize;
/*    */   
/*    */   public SimpleCache(int maxSize) {
/* 22 */     this.mItems = new LimitMap(maxSize);
/* 23 */     this.mMaxSize = maxSize;
/*    */   }
/*    */   
/*    */   public Object find(Object key) {
/* 27 */     return this.mItems.get(key);
/*    */   }
/*    */   
/*    */   public void add(Object key, Object value) {
/* 32 */     this.mItems.put((K)key, (V)value);
/*    */   }
/*    */   
/*    */   static final class LimitMap extends LinkedHashMap {
/*    */     private static final long serialVersionUID = 1L;
/*    */     
/*    */     protected final int mMaxSize;
/*    */     
/*    */     public LimitMap(int size) {
/* 50 */       super(size, 0.8F, true);
/* 52 */       this.mMaxSize = size;
/*    */     }
/*    */     
/*    */     public boolean removeEldestEntry(Map.Entry eldest) {
/* 56 */       return (size() >= this.mMaxSize);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wst\\util\SimpleCache.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */