/*    */ package com.vividsolutions.jts.util;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ObjectCounter {
/* 46 */   private Map counts = new HashMap<>();
/*    */   
/*    */   public void add(Object o) {
/* 53 */     Counter counter = (Counter)this.counts.get(o);
/* 54 */     if (counter == null) {
/* 55 */       this.counts.put(o, new Counter(1));
/*    */     } else {
/* 57 */       counter.increment();
/*    */     } 
/*    */   }
/*    */   
/*    */   public int count(Object o) {
/* 64 */     Counter counter = (Counter)this.counts.get(o);
/* 65 */     if (counter == null)
/* 66 */       return 0; 
/* 68 */     return counter.count();
/*    */   }
/*    */   
/*    */   private static class Counter {
/* 73 */     int count = 0;
/*    */     
/*    */     public Counter() {}
/*    */     
/*    */     public Counter(int count) {
/* 82 */       this.count = count;
/*    */     }
/*    */     
/*    */     public int count() {
/* 87 */       return this.count;
/*    */     }
/*    */     
/*    */     public void increment() {
/* 92 */       this.count++;
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jt\\util\ObjectCounter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */