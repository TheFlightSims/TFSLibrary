/*     */ package org.jfree.chart.block;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import org.jfree.ui.Size2D;
/*     */ 
/*     */ public class CenterArrangement implements Arrangement, Serializable {
/*     */   private static final long serialVersionUID = -353308149220382047L;
/*     */   
/*     */   public void add(Block block, Object key) {}
/*     */   
/*     */   public Size2D arrange(BlockContainer container, Graphics2D g2, RectangleConstraint constraint) {
/*  94 */     LengthConstraintType w = constraint.getWidthConstraintType();
/*  95 */     LengthConstraintType h = constraint.getHeightConstraintType();
/*  96 */     if (w == LengthConstraintType.NONE) {
/*  97 */       if (h == LengthConstraintType.NONE)
/*  98 */         return arrangeNN(container, g2); 
/* 100 */       if (h == LengthConstraintType.FIXED)
/* 101 */         throw new RuntimeException("Not implemented."); 
/* 103 */       if (h == LengthConstraintType.RANGE)
/* 104 */         throw new RuntimeException("Not implemented."); 
/* 107 */     } else if (w == LengthConstraintType.FIXED) {
/* 108 */       if (h == LengthConstraintType.NONE)
/* 109 */         return arrangeFN(container, g2, constraint); 
/* 111 */       if (h == LengthConstraintType.FIXED)
/* 112 */         throw new RuntimeException("Not implemented."); 
/* 114 */       if (h == LengthConstraintType.RANGE)
/* 115 */         throw new RuntimeException("Not implemented."); 
/* 118 */     } else if (w == LengthConstraintType.RANGE) {
/* 119 */       if (h == LengthConstraintType.NONE)
/* 120 */         return arrangeRN(container, g2, constraint); 
/* 122 */       if (h == LengthConstraintType.FIXED)
/* 123 */         return arrangeRF(container, g2, constraint); 
/* 125 */       if (h == LengthConstraintType.RANGE)
/* 126 */         return arrangeRR(container, g2, constraint); 
/*     */     } 
/* 129 */     throw new IllegalArgumentException("Unknown LengthConstraintType.");
/*     */   }
/*     */   
/*     */   protected Size2D arrangeFN(BlockContainer container, Graphics2D g2, RectangleConstraint constraint) {
/* 146 */     List blocks = container.getBlocks();
/* 147 */     Block b = blocks.get(0);
/* 148 */     Size2D s = b.arrange(g2, RectangleConstraint.NONE);
/* 149 */     double width = constraint.getWidth();
/* 150 */     Rectangle2D bounds = new Rectangle2D.Double((width - s.width) / 2.0D, 0.0D, s.width, s.height);
/* 153 */     b.setBounds(bounds);
/* 154 */     return new Size2D((width - s.width) / 2.0D, s.height);
/*     */   }
/*     */   
/*     */   protected Size2D arrangeFR(BlockContainer container, Graphics2D g2, RectangleConstraint constraint) {
/* 170 */     Size2D s = arrangeFN(container, g2, constraint);
/* 171 */     if (constraint.getHeightRange().contains(s.height))
/* 172 */       return s; 
/* 175 */     RectangleConstraint c = constraint.toFixedHeight(constraint.getHeightRange().constrain(s.getHeight()));
/* 178 */     return arrangeFF(container, g2, c);
/*     */   }
/*     */   
/*     */   protected Size2D arrangeFF(BlockContainer container, Graphics2D g2, RectangleConstraint constraint) {
/* 196 */     return arrangeFN(container, g2, constraint);
/*     */   }
/*     */   
/*     */   protected Size2D arrangeRR(BlockContainer container, Graphics2D g2, RectangleConstraint constraint) {
/* 214 */     Size2D s1 = arrangeNN(container, g2);
/* 215 */     if (constraint.getWidthRange().contains(s1.width))
/* 216 */       return s1; 
/* 219 */     RectangleConstraint c = constraint.toFixedWidth(constraint.getWidthRange().getUpperBound());
/* 222 */     return arrangeFR(container, g2, c);
/*     */   }
/*     */   
/*     */   protected Size2D arrangeRF(BlockContainer container, Graphics2D g2, RectangleConstraint constraint) {
/* 239 */     Size2D s = arrangeNF(container, g2, constraint);
/* 240 */     if (constraint.getWidthRange().contains(s.width))
/* 241 */       return s; 
/* 244 */     RectangleConstraint c = constraint.toFixedWidth(constraint.getWidthRange().constrain(s.getWidth()));
/* 247 */     return arrangeFF(container, g2, c);
/*     */   }
/*     */   
/*     */   protected Size2D arrangeRN(BlockContainer container, Graphics2D g2, RectangleConstraint constraint) {
/* 265 */     Size2D s1 = arrangeNN(container, g2);
/* 266 */     if (constraint.getWidthRange().contains(s1.width))
/* 267 */       return s1; 
/* 270 */     RectangleConstraint c = constraint.toFixedWidth(constraint.getWidthRange().getUpperBound());
/* 273 */     return arrangeFN(container, g2, c);
/*     */   }
/*     */   
/*     */   protected Size2D arrangeNN(BlockContainer container, Graphics2D g2) {
/* 287 */     List blocks = container.getBlocks();
/* 288 */     Block b = blocks.get(0);
/* 289 */     Size2D s = b.arrange(g2, RectangleConstraint.NONE);
/* 290 */     return new Size2D(s.width, s.height);
/*     */   }
/*     */   
/*     */   protected Size2D arrangeNF(BlockContainer container, Graphics2D g2, RectangleConstraint constraint) {
/* 306 */     return arrangeNN(container, g2);
/*     */   }
/*     */   
/*     */   public void clear() {}
/*     */   
/*     */   public boolean equals(Object obj) {
/* 324 */     if (obj == this)
/* 325 */       return true; 
/* 327 */     if (!(obj instanceof CenterArrangement))
/* 328 */       return false; 
/* 330 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\block\CenterArrangement.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */