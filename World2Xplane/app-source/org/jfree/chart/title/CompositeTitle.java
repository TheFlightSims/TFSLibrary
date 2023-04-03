/*     */ package org.jfree.chart.title;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.block.Arrangement;
/*     */ import org.jfree.chart.block.BlockContainer;
/*     */ import org.jfree.chart.block.BorderArrangement;
/*     */ import org.jfree.chart.block.RectangleConstraint;
/*     */ import org.jfree.ui.Size2D;
/*     */ 
/*     */ public class CompositeTitle extends Title implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = -6770854036232562290L;
/*     */   
/*     */   private BlockContainer container;
/*     */   
/*     */   public CompositeTitle() {
/*  73 */     this(new BlockContainer((Arrangement)new BorderArrangement()));
/*     */   }
/*     */   
/*     */   public CompositeTitle(BlockContainer container) {
/*  82 */     if (container == null)
/*  83 */       throw new IllegalArgumentException("Null 'container' argument."); 
/*  85 */     this.container = container;
/*     */   }
/*     */   
/*     */   public BlockContainer getContainer() {
/*  94 */     return this.container;
/*     */   }
/*     */   
/*     */   public void setTitleContainer(BlockContainer container) {
/* 103 */     if (container == null)
/* 104 */       throw new IllegalArgumentException("Null 'container' argument."); 
/* 106 */     this.container = container;
/*     */   }
/*     */   
/*     */   public Size2D arrange(Graphics2D g2, RectangleConstraint constraint) {
/* 119 */     RectangleConstraint contentConstraint = toContentConstraint(constraint);
/* 120 */     Size2D contentSize = this.container.arrange(g2, contentConstraint);
/* 121 */     return new Size2D(calculateTotalWidth(contentSize.getWidth()), calculateTotalHeight(contentSize.getHeight()));
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, Rectangle2D area) {
/* 135 */     area = trimMargin(area);
/* 136 */     drawBorder(g2, area);
/* 137 */     area = trimBorder(area);
/* 138 */     area = trimPadding(area);
/* 139 */     this.container.draw(g2, area);
/*     */   }
/*     */   
/*     */   public Object draw(Graphics2D g2, Rectangle2D area, Object params) {
/* 152 */     draw(g2, area);
/* 153 */     return null;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 164 */     if (obj == this)
/* 165 */       return true; 
/* 167 */     if (!(obj instanceof CompositeTitle))
/* 168 */       return false; 
/* 170 */     if (!super.equals(obj))
/* 171 */       return false; 
/* 173 */     CompositeTitle that = (CompositeTitle)obj;
/* 174 */     if (!this.container.equals(that.container))
/* 175 */       return false; 
/* 177 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\title\CompositeTitle.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */