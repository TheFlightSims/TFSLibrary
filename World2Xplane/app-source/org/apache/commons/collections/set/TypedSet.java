/*    */ package org.apache.commons.collections.set;
/*    */ 
/*    */ import java.util.Set;
/*    */ import org.apache.commons.collections.functors.InstanceofPredicate;
/*    */ 
/*    */ public class TypedSet {
/*    */   public static Set decorate(Set set, Class type) {
/* 51 */     return new PredicatedSet(set, InstanceofPredicate.getInstance(type));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\set\TypedSet.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */