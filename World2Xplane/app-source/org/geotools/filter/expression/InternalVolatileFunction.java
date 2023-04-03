/*    */ package org.geotools.filter.expression;
/*    */ 
/*    */ import org.geotools.filter.FunctionImpl;
/*    */ import org.opengis.filter.expression.Expression;
/*    */ import org.opengis.filter.expression.InternalFunction;
/*    */ import org.opengis.filter.expression.VolatileFunction;
/*    */ 
/*    */ public abstract class InternalVolatileFunction extends FunctionImpl implements InternalFunction, VolatileFunction {
/*    */   public InternalVolatileFunction() {
/* 36 */     this("InternalFunctionImpl");
/*    */   }
/*    */   
/*    */   public InternalVolatileFunction(String name) {
/* 40 */     setName(name);
/*    */   }
/*    */   
/*    */   public InternalFunction duplicate(Expression... parameters) {
/* 55 */     int expectedParams = getParameters().size();
/* 56 */     if (expectedParams > 0)
/* 57 */       throw new IllegalArgumentException("This method must be overriten by subclasses that expect Expression arguments"); 
/* 61 */     return this;
/*    */   }
/*    */   
/*    */   public abstract Object evaluate(Object paramObject);
/*    */   
/*    */   public Object evaluate(Object object, Class context) {
/* 69 */     return super.evaluate(object, context);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\expression\InternalVolatileFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */