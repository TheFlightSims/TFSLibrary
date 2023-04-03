/*    */ package org.geotools.feature.visitor;
/*    */ 
/*    */ import org.geotools.data.simple.SimpleFeatureCollection;
/*    */ import org.geotools.data.simple.SimpleFeatureIterator;
/*    */ import org.opengis.feature.Feature;
/*    */ import org.opengis.feature.FeatureVisitor;
/*    */ import org.opengis.feature.simple.SimpleFeature;
/*    */ 
/*    */ public class CollectionUtil {
/*    */   static void accept(SimpleFeatureCollection collection, FeatureVisitor visitor) {
/* 44 */     SimpleFeatureIterator iterator = collection.features();
/*    */     try {
/* 46 */       while (iterator.hasNext()) {
/* 47 */         SimpleFeature feature = (SimpleFeature)iterator.next();
/* 48 */         visitor.visit((Feature)feature);
/*    */       } 
/*    */     } finally {
/* 52 */       iterator.close();
/*    */     } 
/*    */   }
/*    */   
/*    */   static void accept(SimpleFeatureCollection collection, FeatureVisitor[] visitors) {
/* 57 */     SimpleFeatureIterator iterator = collection.features();
/*    */     try {
/* 59 */       while (iterator.hasNext()) {
/* 60 */         SimpleFeature feature = (SimpleFeature)iterator.next();
/* 62 */         for (int i = 0; i < visitors.length; i++) {
/* 63 */           FeatureVisitor visitor = visitors[i];
/* 64 */           visitor.visit((Feature)feature);
/*    */         } 
/*    */       } 
/*    */     } finally {
/* 68 */       iterator.close();
/*    */     } 
/*    */   }
/*    */   
/*    */   public static Object calc(SimpleFeatureCollection collection, FeatureCalc calculator) {
/* 74 */     accept(collection, calculator);
/* 76 */     return calculator.getResult();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\visitor\CollectionUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */