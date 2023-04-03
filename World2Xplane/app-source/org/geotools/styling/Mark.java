/*    */ package org.geotools.styling;
/*    */ 
/*    */ import org.opengis.filter.expression.Expression;
/*    */ import org.opengis.style.ExternalMark;
/*    */ import org.opengis.style.Fill;
/*    */ import org.opengis.style.Mark;
/*    */ import org.opengis.style.Stroke;
/*    */ 
/*    */ public interface Mark extends Mark, Symbol {
/* 72 */   public static final Mark[] MARKS_EMPTY = new Mark[0];
/*    */   
/*    */   Expression getWellKnownName();
/*    */   
/*    */   void setWellKnownName(Expression paramExpression);
/*    */   
/*    */   Stroke getStroke();
/*    */   
/*    */   void setStroke(Stroke paramStroke);
/*    */   
/*    */   Fill getFill();
/*    */   
/*    */   void setFill(Fill paramFill);
/*    */   
/*    */   ExternalMark getExternalMark();
/*    */   
/*    */   void setExternalMark(ExternalMark paramExternalMark);
/*    */   
/*    */   void accept(StyleVisitor paramStyleVisitor);
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\Mark.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */