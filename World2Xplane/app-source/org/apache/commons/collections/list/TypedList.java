/*    */ package org.apache.commons.collections.list;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.apache.commons.collections.functors.InstanceofPredicate;
/*    */ 
/*    */ public class TypedList {
/*    */   public static List decorate(List list, Class type) {
/* 51 */     return new PredicatedList(list, InstanceofPredicate.getInstance(type));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\list\TypedList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */