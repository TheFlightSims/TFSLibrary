/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import org.geotools.factory.CommonFactoryFinder;
/*    */ import org.geotools.filter.FunctionExpressionImpl;
/*    */ import org.geotools.filter.capability.FunctionNameImpl;
/*    */ import org.geotools.util.Converters;
/*    */ import org.opengis.filter.FilterFactory2;
/*    */ import org.opengis.filter.capability.FunctionName;
/*    */ import org.opengis.filter.expression.PropertyName;
/*    */ import org.opengis.filter.expression.VolatileFunction;
/*    */ import org.opengis.parameter.Parameter;
/*    */ 
/*    */ public class FilterFunction_property extends FunctionExpressionImpl implements VolatileFunction {
/* 40 */   FilterFactory2 FF = CommonFactoryFinder.getFilterFactory2();
/*    */   
/* 42 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("property", FunctionNameImpl.parameter("propertyValue", Object.class), new Parameter[] { FunctionNameImpl.parameter("propertyName", String.class) });
/*    */   
/*    */   volatile PropertyName lastPropertyName;
/*    */   
/*    */   public FilterFunction_property() {
/* 51 */     super(NAME);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object object, Class context) {
/* 56 */     Object result = evaluate(object);
/* 57 */     if (result == null)
/* 58 */       return null; 
/* 60 */     return Converters.convert(result, context);
/*    */   }
/*    */   
/*    */   public Object evaluate(Object feature) {
/* 65 */     String name = (String)getExpression(0).evaluate(feature, String.class);
/* 67 */     if (name == null)
/* 68 */       return null; 
/* 71 */     PropertyName pn = this.lastPropertyName;
/* 72 */     if (pn != null && pn.getPropertyName().equals(name))
/* 73 */       return pn.evaluate(feature); 
/* 75 */     pn = this.FF.property(name);
/* 76 */     Object result = pn.evaluate(feature);
/* 77 */     this.lastPropertyName = pn;
/* 78 */     return result;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\FilterFunction_property.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */