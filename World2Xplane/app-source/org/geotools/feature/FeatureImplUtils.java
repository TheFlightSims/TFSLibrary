/*    */ package org.geotools.feature;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import java.util.SortedSet;
/*    */ 
/*    */ public class FeatureImplUtils {
/*    */   public static Collection unmodifiable(Collection<?> original) {
/* 51 */     if (original instanceof Set) {
/* 52 */       if (original instanceof SortedSet)
/* 53 */         return Collections.unmodifiableSortedSet((SortedSet)original); 
/* 56 */       return Collections.unmodifiableSet((Set)original);
/*    */     } 
/* 58 */     if (original instanceof List)
/* 59 */       return Collections.unmodifiableList((List)original); 
/* 62 */     return Collections.unmodifiableCollection(original);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\FeatureImplUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */