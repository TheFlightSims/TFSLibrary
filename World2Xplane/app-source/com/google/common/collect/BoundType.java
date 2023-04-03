/*    */ package com.google.common.collect;
/*    */ 
/*    */ import com.google.common.annotations.GwtCompatible;
/*    */ 
/*    */ @GwtCompatible
/*    */ public enum BoundType {
/* 31 */   OPEN {
/*    */     BoundType flip() {
/* 34 */       return CLOSED;
/*    */     }
/*    */   },
/* 40 */   CLOSED {
/*    */     BoundType flip() {
/* 43 */       return OPEN;
/*    */     }
/*    */   };
/*    */   
/*    */   static BoundType forBoolean(boolean inclusive) {
/* 51 */     return inclusive ? CLOSED : OPEN;
/*    */   }
/*    */   
/*    */   abstract BoundType flip();
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\common\collect\BoundType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */