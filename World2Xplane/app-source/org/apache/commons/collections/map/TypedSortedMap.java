/*    */ package org.apache.commons.collections.map;
/*    */ 
/*    */ import java.util.SortedMap;
/*    */ import org.apache.commons.collections.functors.InstanceofPredicate;
/*    */ 
/*    */ public class TypedSortedMap {
/*    */   public static SortedMap decorate(SortedMap map, Class keyType, Class valueType) {
/* 60 */     return new PredicatedSortedMap(map, InstanceofPredicate.getInstance(keyType), InstanceofPredicate.getInstance(valueType));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\map\TypedSortedMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */