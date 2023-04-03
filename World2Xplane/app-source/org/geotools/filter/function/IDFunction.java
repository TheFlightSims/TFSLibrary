/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.opengis.feature.Attribute;
/*    */ import org.opengis.feature.simple.SimpleFeature;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ 
/*    */ public class IDFunction extends FunctionExpressionImpl {
/* 40 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("id", String.class, new org.opengis.parameter.Parameter[0]);
/*    */   
/*    */   public IDFunction() {
/* 44 */     super("id");
/*    */   }
/*    */   
/*    */   public int getArgCount() {
/* 48 */     return 0;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 52 */     return "ID()";
/*    */   }
/*    */   
/*    */   public Object evaluate(Object obj) {
/* 57 */     if (obj instanceof SimpleFeature) {
/* 58 */       SimpleFeature feature = (SimpleFeature)obj;
/* 59 */       return feature.getID();
/*    */     } 
/* 61 */     if (obj instanceof Attribute) {
/* 62 */       Attribute attribute = (Attribute)obj;
/* 63 */       return attribute.getIdentifier().getID();
/*    */     } 
/* 65 */     return "";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\IDFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */