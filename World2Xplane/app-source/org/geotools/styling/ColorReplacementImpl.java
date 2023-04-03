/*    */ package org.geotools.styling;
/*    */ 
/*    */ import org.opengis.filter.expression.Function;
/*    */ import org.opengis.style.StyleVisitor;
/*    */ 
/*    */ public class ColorReplacementImpl implements ColorReplacement {
/*    */   private Function function;
/*    */   
/*    */   public ColorReplacementImpl(Function function) {
/* 37 */     this.function = function;
/*    */   }
/*    */   
/*    */   public Function getRecoding() {
/* 49 */     return this.function;
/*    */   }
/*    */   
/*    */   public Object accept(StyleVisitor visitor, Object extraData) {
/* 53 */     return visitor.visit(this, extraData);
/*    */   }
/*    */   
/*    */   public void setRecoding(Function function) {
/* 57 */     this.function = function;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\ColorReplacementImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */