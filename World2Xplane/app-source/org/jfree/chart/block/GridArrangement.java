/*     */ package org.jfree.chart.block;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.ui.Size2D;
/*     */ 
/*     */ public class GridArrangement implements Arrangement, Serializable {
/*     */   private static final long serialVersionUID = -2563758090144655938L;
/*     */   
/*     */   private int rows;
/*     */   
/*     */   private int columns;
/*     */   
/*     */   public GridArrangement(int rows, int columns) {
/*  74 */     this.rows = rows;
/*  75 */     this.columns = columns;
/*     */   }
/*     */   
/*     */   public void add(Block block, Object key) {}
/*     */   
/*     */   public Size2D arrange(BlockContainer container, Graphics2D g2, RectangleConstraint constraint) {
/* 103 */     LengthConstraintType w = constraint.getWidthConstraintType();
/* 104 */     LengthConstraintType h = constraint.getHeightConstraintType();
/* 105 */     if (w == LengthConstraintType.NONE) {
/* 106 */       if (h == LengthConstraintType.NONE)
/* 107 */         return arrangeNN(container, g2); 
/* 109 */       if (h == LengthConstraintType.FIXED)
/* 111 */         throw new RuntimeException("Not yet implemented."); 
/* 113 */       if (h == LengthConstraintType.RANGE)
/* 115 */         throw new RuntimeException("Not yet implemented."); 
/* 118 */     } else if (w == LengthConstraintType.FIXED) {
/* 119 */       if (h == LengthConstraintType.NONE)
/* 121 */         return arrangeFN(container, g2, constraint); 
/* 123 */       if (h == LengthConstraintType.FIXED)
/* 124 */         return arrangeFF(container, g2, constraint); 
/* 126 */       if (h == LengthConstraintType.RANGE)
/* 128 */         return arrangeFR(container, g2, constraint); 
/* 131 */     } else if (w == LengthConstraintType.RANGE) {
/* 133 */       if (h == LengthConstraintType.NONE)
/* 135 */         throw new RuntimeException("Not yet implemented."); 
/* 137 */       if (h == LengthConstraintType.FIXED)
/* 139 */         throw new RuntimeException("Not yet implemented."); 
/* 141 */       if (h == LengthConstraintType.RANGE)
/* 142 */         throw new RuntimeException("Not yet implemented."); 
/*     */     } 
/* 145 */     return new Size2D();
/*     */   }
/*     */   
/*     */   protected Size2D arrangeNN(BlockContainer container, Graphics2D g2) {
/* 157 */     double maxW = 0.0D;
/* 158 */     double maxH = 0.0D;
/* 159 */     List blocks = container.getBlocks();
/* 160 */     Iterator iterator = blocks.iterator();
/* 161 */     while (iterator.hasNext()) {
/* 162 */       Block b = iterator.next();
/* 163 */       Size2D s = b.arrange(g2, RectangleConstraint.NONE);
/* 164 */       maxW = Math.max(maxW, s.width);
/* 165 */       maxH = Math.max(maxH, s.height);
/*     */     } 
/* 167 */     double width = this.columns * maxW;
/* 168 */     double height = this.rows * maxH;
/* 169 */     RectangleConstraint c = new RectangleConstraint(width, height);
/* 170 */     return arrangeFF(container, g2, c);
/*     */   }
/*     */   
/*     */   protected Size2D arrangeFF(BlockContainer container, Graphics2D g2, RectangleConstraint constraint) {
/* 184 */     double width = constraint.getWidth() / this.columns;
/* 185 */     double height = constraint.getHeight() / this.rows;
/* 186 */     List blocks = container.getBlocks();
/* 187 */     for (int c = 0; c < this.columns; c++) {
/* 188 */       for (int r = 0; r < this.rows; r++) {
/* 189 */         int index = r * this.columns + c;
/* 190 */         if (index == blocks.size())
/*     */           break; 
/* 193 */         Block b = blocks.get(index);
/* 194 */         b.setBounds(new Rectangle2D.Double(c * width, r * height, width, height));
/*     */       } 
/*     */     } 
/* 199 */     return new Size2D(this.columns * width, this.rows * height);
/*     */   }
/*     */   
/*     */   protected Size2D arrangeFR(BlockContainer container, Graphics2D g2, RectangleConstraint constraint) {
/* 214 */     RectangleConstraint c1 = constraint.toUnconstrainedHeight();
/* 215 */     Size2D size1 = arrange(container, g2, c1);
/* 217 */     if (constraint.getHeightRange().contains(size1.getHeight()))
/* 218 */       return size1; 
/* 221 */     double h = constraint.getHeightRange().constrain(size1.getHeight());
/* 222 */     RectangleConstraint c2 = constraint.toFixedHeight(h);
/* 223 */     return arrange(container, g2, c2);
/*     */   }
/*     */   
/*     */   protected Size2D arrangeFN(BlockContainer container, Graphics2D g2, RectangleConstraint constraint) {
/* 239 */     double width = constraint.getWidth() / this.columns;
/* 240 */     RectangleConstraint constraint2 = constraint.toFixedWidth(width);
/* 241 */     List blocks = container.getBlocks();
/* 242 */     double maxH = 0.0D;
/* 243 */     for (int r = 0; r < this.rows; r++) {
/* 244 */       for (int c = 0; c < this.columns; c++) {
/* 245 */         int index = r * this.columns + c;
/* 246 */         if (index == blocks.size())
/*     */           break; 
/* 249 */         Block b = blocks.get(index);
/* 250 */         Size2D s = b.arrange(g2, constraint2);
/* 251 */         maxH = Math.max(maxH, s.getHeight());
/*     */       } 
/*     */     } 
/* 254 */     RectangleConstraint constraint3 = constraint.toFixedHeight(maxH * this.rows);
/* 257 */     return arrange(container, g2, constraint3);
/*     */   }
/*     */   
/*     */   public void clear() {}
/*     */   
/*     */   public boolean equals(Object obj) {
/* 275 */     if (obj == this)
/* 276 */       return true; 
/* 278 */     if (!(obj instanceof GridArrangement))
/* 279 */       return false; 
/* 281 */     GridArrangement that = (GridArrangement)obj;
/* 282 */     if (this.columns != that.columns)
/* 283 */       return false; 
/* 285 */     if (this.rows != that.rows)
/* 286 */       return false; 
/* 288 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\block\GridArrangement.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */