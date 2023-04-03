/*     */ package org.jfree.chart.block;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class EmptyBlock extends AbstractBlock implements Block, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = -4083197869412648579L;
/*     */   
/*     */   public EmptyBlock(double width, double height) {
/*  70 */     setWidth(width);
/*  71 */     setHeight(height);
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, Rectangle2D area) {}
/*     */   
/*     */   public Object draw(Graphics2D g2, Rectangle2D area, Object params) {
/*  95 */     return null;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 106 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\block\EmptyBlock.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */