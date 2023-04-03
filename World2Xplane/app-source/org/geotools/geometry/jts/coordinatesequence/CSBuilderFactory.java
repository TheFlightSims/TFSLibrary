/*    */ package org.geotools.geometry.jts.coordinatesequence;
/*    */ 
/*    */ public class CSBuilderFactory {
/*    */   private static Class defaultBuilderClass;
/*    */   
/*    */   public static CSBuilder getDefaultBuilder() {
/*    */     try {
/* 37 */       return getDefaultBuilderClass().newInstance();
/* 38 */     } catch (Exception e) {
/* 40 */       throw new RuntimeException("Could not create a coordinate sequence builder", e);
/*    */     } 
/*    */   }
/*    */   
/*    */   private static Class getDefaultBuilderClass() {
/* 45 */     if (defaultBuilderClass == null)
/* 46 */       defaultBuilderClass = DefaultCSBuilder.class; 
/* 48 */     return defaultBuilderClass;
/*    */   }
/*    */   
/*    */   public static void setDefaultBuilderClass(Class<?> builderClass) {
/* 55 */     if (!CSBuilder.class.isAssignableFrom(builderClass))
/* 56 */       throw new RuntimeException(builderClass.getName() + " does not implement the CSBuilder interface"); 
/* 57 */     defaultBuilderClass = builderClass;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\jts\coordinatesequence\CSBuilderFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */