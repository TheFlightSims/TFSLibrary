/*    */ package org.apache.commons.collections.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import org.apache.commons.collections.Predicate;
/*    */ 
/*    */ public final class UniquePredicate implements Predicate, Serializable {
/*    */   private static final long serialVersionUID = -3319417438027438040L;
/*    */   
/* 40 */   private final Set iSet = new HashSet();
/*    */   
/*    */   public static Predicate getInstance() {
/* 49 */     return new UniquePredicate();
/*    */   }
/*    */   
/*    */   public boolean evaluate(Object object) {
/* 68 */     return this.iSet.add(object);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\UniquePredicate.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */