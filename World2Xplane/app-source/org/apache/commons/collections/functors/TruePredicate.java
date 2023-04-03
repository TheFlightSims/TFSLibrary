/*    */ package org.apache.commons.collections.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.collections.Predicate;
/*    */ 
/*    */ public final class TruePredicate implements Predicate, Serializable {
/*    */   private static final long serialVersionUID = 3374767158756189740L;
/*    */   
/* 37 */   public static final Predicate INSTANCE = new TruePredicate();
/*    */   
/*    */   public static Predicate getInstance() {
/* 46 */     return INSTANCE;
/*    */   }
/*    */   
/*    */   public boolean evaluate(Object object) {
/* 63 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\TruePredicate.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */