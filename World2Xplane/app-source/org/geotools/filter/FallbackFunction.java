/*    */ package org.geotools.filter;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.opengis.feature.type.Name;
/*    */ import org.opengis.filter.expression.Literal;
/*    */ 
/*    */ public class FallbackFunction extends FunctionExpressionImpl {
/*    */   public FallbackFunction(String name, List params, Literal fallback) {
/* 40 */     super(name, fallback);
/* 41 */     setParameters(params);
/*    */   }
/*    */   
/*    */   public FallbackFunction(Name name, List params, Literal fallback) {
/* 45 */     super(name, fallback);
/* 46 */     setParameters(params);
/*    */   }
/*    */   
/*    */   public int getArgCount() {
/* 50 */     return this.params.size();
/*    */   }
/*    */   
/*    */   public Object evaluate(Object object) {
/* 54 */     return this.fallback.evaluate(object);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object object, Class context) {
/* 59 */     return this.fallback.evaluate(object, context);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\FallbackFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */