/*    */ package org.jfree.text;
/*    */ 
/*    */ import java.awt.FontMetrics;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.geom.Rectangle2D;
/*    */ 
/*    */ public class G2TextMeasurer implements TextMeasurer {
/*    */   private Graphics2D g2;
/*    */   
/*    */   public G2TextMeasurer(Graphics2D g2) {
/* 65 */     this.g2 = g2;
/*    */   }
/*    */   
/*    */   public float getStringWidth(String text, int start, int end) {
/* 79 */     FontMetrics fm = this.g2.getFontMetrics();
/* 80 */     Rectangle2D bounds = TextUtilities.getTextBounds(text.substring(start, end), this.g2, fm);
/* 83 */     float result = (float)bounds.getWidth();
/* 84 */     return result;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\text\G2TextMeasurer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */