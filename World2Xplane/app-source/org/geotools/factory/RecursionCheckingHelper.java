/*    */ package org.geotools.factory;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ 
/*    */ class RecursionCheckingHelper {
/*  8 */   private final ThreadLocal<Set> threadLocalSet = new ThreadLocal<Set>();
/*    */   
/*    */   boolean addAndCheck(Object item) {
/* 11 */     Set<Object> set = this.threadLocalSet.get();
/* 12 */     if (set == null) {
/* 13 */       set = new HashSet();
/* 14 */       this.threadLocalSet.set(set);
/*    */     } 
/* 16 */     return set.add(item);
/*    */   }
/*    */   
/*    */   boolean contains(Object item) {
/* 20 */     Set<Class<?>> set = this.threadLocalSet.get();
/* 21 */     if (set == null)
/* 22 */       return false; 
/* 24 */     return set.contains(item);
/*    */   }
/*    */   
/*    */   void removeAndCheck(Object item) {
/* 28 */     Set<Class<?>> set = this.threadLocalSet.get();
/* 29 */     if (set == null)
/* 30 */       throw new AssertionError(null); 
/* 31 */     if (!set.remove(item))
/* 32 */       throw new AssertionError(item); 
/* 34 */     if (set.isEmpty())
/* 35 */       this.threadLocalSet.remove(); 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\factory\RecursionCheckingHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */