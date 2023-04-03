/*    */ package org.jfree.chart.block;
/*    */ 
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.Paint;
/*    */ import java.awt.geom.Rectangle2D;
/*    */ 
/*    */ public class ColorBlock extends AbstractBlock implements Block {
/*    */   private Paint paint;
/*    */   
/*    */   public ColorBlock(Paint paint, double width, double height) {
/* 65 */     this.paint = paint;
/* 66 */     setWidth(width);
/* 67 */     setHeight(height);
/*    */   }
/*    */   
/*    */   public void draw(Graphics2D g2, Rectangle2D area) {
/* 77 */     Rectangle2D bounds = getBounds();
/* 78 */     g2.setPaint(this.paint);
/* 79 */     g2.fill(bounds);
/*    */   }
/*    */   
/*    */   public Object draw(Graphics2D g2, Rectangle2D area, Object params) {
/* 92 */     draw(g2, area);
/* 93 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\block\ColorBlock.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */