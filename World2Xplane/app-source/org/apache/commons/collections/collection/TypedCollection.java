/*    */ package org.apache.commons.collections.collection;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import org.apache.commons.collections.functors.InstanceofPredicate;
/*    */ 
/*    */ public class TypedCollection {
/*    */   public static Collection decorate(Collection coll, Class type) {
/* 51 */     return new PredicatedCollection(coll, InstanceofPredicate.getInstance(type));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\collection\TypedCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */