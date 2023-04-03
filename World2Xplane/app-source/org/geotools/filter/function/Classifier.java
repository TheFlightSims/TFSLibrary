/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import org.opengis.filter.expression.Expression;
/*    */ 
/*    */ public abstract class Classifier {
/*    */   String[] titles;
/*    */   
/*    */   public String[] getTitles() {
/* 39 */     return this.titles;
/*    */   }
/*    */   
/*    */   public void setTitles(String[] titles) {
/* 43 */     this.titles = titles;
/*    */   }
/*    */   
/*    */   public void setTitle(int slot, String title) {
/* 47 */     this.titles[slot] = title;
/*    */   }
/*    */   
/*    */   public String getTitle(int slot) {
/* 51 */     return this.titles[slot];
/*    */   }
/*    */   
/*    */   public int classify(Expression expr, Object feature) {
/* 58 */     Object value = expr.evaluate(feature);
/* 59 */     return classify(value);
/*    */   }
/*    */   
/*    */   public abstract int classify(Object paramObject);
/*    */   
/*    */   public abstract int getSize();
/*    */   
/*    */   public String toString() {
/* 77 */     StringBuffer buffer = new StringBuffer();
/* 79 */     return super.toString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\Classifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */