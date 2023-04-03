/*    */ package org.geotools.styling;
/*    */ 
/*    */ import org.opengis.filter.expression.Expression;
/*    */ 
/*    */ public class OtherTextImpl implements OtherText {
/*    */   String location;
/*    */   
/*    */   Expression text;
/*    */   
/*    */   public String getTarget() {
/* 33 */     return this.location;
/*    */   }
/*    */   
/*    */   public void setTarget(String location) {
/* 37 */     this.location = location;
/*    */   }
/*    */   
/*    */   public Expression getText() {
/* 41 */     return this.text;
/*    */   }
/*    */   
/*    */   public void setText(Expression text) {
/* 45 */     this.text = text;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\OtherTextImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */