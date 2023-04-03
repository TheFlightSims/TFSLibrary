/*    */ package com.google.common.collect;
/*    */ 
/*    */ import com.google.common.annotations.GwtCompatible;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ @GwtCompatible(serializable = true)
/*    */ final class UsingToStringOrdering extends Ordering<Object> implements Serializable {
/* 30 */   static final UsingToStringOrdering INSTANCE = new UsingToStringOrdering();
/*    */   
/*    */   private static final long serialVersionUID = 0L;
/*    */   
/*    */   public int compare(Object left, Object right) {
/* 33 */     return left.toString().compareTo(right.toString());
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 38 */     return INSTANCE;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 42 */     return "Ordering.usingToString()";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\common\collect\UsingToStringOrdering.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */