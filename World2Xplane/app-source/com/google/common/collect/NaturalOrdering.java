/*    */ package com.google.common.collect;
/*    */ 
/*    */ import com.google.common.annotations.GwtCompatible;
/*    */ import com.google.common.base.Preconditions;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ @GwtCompatible(serializable = true)
/*    */ final class NaturalOrdering extends Ordering<Comparable> implements Serializable {
/* 30 */   static final NaturalOrdering INSTANCE = new NaturalOrdering();
/*    */   
/*    */   private static final long serialVersionUID = 0L;
/*    */   
/*    */   public int compare(Comparable<Comparable> left, Comparable right) {
/* 33 */     Preconditions.checkNotNull(left);
/* 34 */     Preconditions.checkNotNull(right);
/* 35 */     return left.compareTo(right);
/*    */   }
/*    */   
/*    */   public <S extends Comparable> Ordering<S> reverse() {
/* 39 */     return ReverseNaturalOrdering.INSTANCE;
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 44 */     return INSTANCE;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 48 */     return "Ordering.natural()";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\common\collect\NaturalOrdering.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */