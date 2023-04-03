/*    */ package org.apache.commons.collections.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.collections.Predicate;
/*    */ 
/*    */ public final class NotNullPredicate implements Predicate, Serializable {
/*    */   private static final long serialVersionUID = 7533784454832764388L;
/*    */   
/* 37 */   public static final Predicate INSTANCE = new NotNullPredicate();
/*    */   
/*    */   public static Predicate getInstance() {
/* 46 */     return INSTANCE;
/*    */   }
/*    */   
/*    */   public boolean evaluate(Object object) {
/* 63 */     return (object != null);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\NotNullPredicate.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */