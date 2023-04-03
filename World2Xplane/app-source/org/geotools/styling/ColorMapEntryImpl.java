/*    */ package org.geotools.styling;
/*    */ 
/*    */ import org.opengis.filter.expression.Expression;
/*    */ 
/*    */ public class ColorMapEntryImpl implements ColorMapEntry {
/*    */   private Expression quantity;
/*    */   
/*    */   private Expression opacity;
/*    */   
/*    */   private Expression color;
/*    */   
/*    */   private String label;
/*    */   
/*    */   public String getLabel() {
/* 41 */     return this.label;
/*    */   }
/*    */   
/*    */   public void setLabel(String label) {
/* 48 */     this.label = label;
/*    */   }
/*    */   
/*    */   public void setColor(Expression color) {
/* 55 */     this.color = color;
/*    */   }
/*    */   
/*    */   public Expression getColor() {
/* 62 */     return this.color;
/*    */   }
/*    */   
/*    */   public void setOpacity(Expression opacity) {
/* 69 */     this.opacity = opacity;
/*    */   }
/*    */   
/*    */   public Expression getOpacity() {
/* 76 */     return this.opacity;
/*    */   }
/*    */   
/*    */   public void setQuantity(Expression quantity) {
/* 83 */     this.quantity = quantity;
/*    */   }
/*    */   
/*    */   public Expression getQuantity() {
/* 90 */     return this.quantity;
/*    */   }
/*    */   
/*    */   public void accept(StyleVisitor visitor) {
/* 94 */     visitor.visit(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\ColorMapEntryImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */