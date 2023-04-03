/*     */ package org.jfree.chart.block;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.jfree.ui.HorizontalAlignment;
/*     */ import org.jfree.ui.Size2D;
/*     */ import org.jfree.ui.VerticalAlignment;
/*     */ 
/*     */ public class ColumnArrangement implements Arrangement, Serializable {
/*     */   private static final long serialVersionUID = -5315388482898581555L;
/*     */   
/*     */   private HorizontalAlignment horizontalAlignment;
/*     */   
/*     */   private VerticalAlignment verticalAlignment;
/*     */   
/*     */   private double horizontalGap;
/*     */   
/*     */   private double verticalGap;
/*     */   
/*     */   public ColumnArrangement() {}
/*     */   
/*     */   public ColumnArrangement(HorizontalAlignment hAlign, VerticalAlignment vAlign, double hGap, double vGap) {
/*  93 */     this.horizontalAlignment = hAlign;
/*  94 */     this.verticalAlignment = vAlign;
/*  95 */     this.horizontalGap = hGap;
/*  96 */     this.verticalGap = vGap;
/*     */   }
/*     */   
/*     */   public void add(Block block, Object key) {}
/*     */   
/*     */   public Size2D arrange(BlockContainer container, Graphics2D g2, RectangleConstraint constraint) {
/* 127 */     LengthConstraintType w = constraint.getWidthConstraintType();
/* 128 */     LengthConstraintType h = constraint.getHeightConstraintType();
/* 129 */     if (w == LengthConstraintType.NONE) {
/* 130 */       if (h == LengthConstraintType.NONE)
/* 131 */         return arrangeNN(container, g2); 
/* 133 */       if (h == LengthConstraintType.FIXED)
/* 134 */         throw new RuntimeException("Not implemented."); 
/* 136 */       if (h == LengthConstraintType.RANGE)
/* 137 */         throw new RuntimeException("Not implemented."); 
/* 140 */     } else if (w == LengthConstraintType.FIXED) {
/* 141 */       if (h == LengthConstraintType.NONE)
/* 142 */         throw new RuntimeException("Not implemented."); 
/* 144 */       if (h == LengthConstraintType.FIXED)
/* 145 */         return arrangeFF(container, g2, constraint); 
/* 147 */       if (h == LengthConstraintType.RANGE)
/* 148 */         throw new RuntimeException("Not implemented."); 
/* 151 */     } else if (w == LengthConstraintType.RANGE) {
/* 152 */       if (h == LengthConstraintType.NONE)
/* 153 */         throw new RuntimeException("Not implemented."); 
/* 155 */       if (h == LengthConstraintType.FIXED)
/* 156 */         return arrangeRF(container, g2, constraint); 
/* 158 */       if (h == LengthConstraintType.RANGE)
/* 159 */         return arrangeRR(container, g2, constraint); 
/*     */     } 
/* 162 */     return new Size2D();
/*     */   }
/*     */   
/*     */   protected Size2D arrangeFF(BlockContainer container, Graphics2D g2, RectangleConstraint constraint) {
/* 181 */     return arrangeNF(container, g2, constraint);
/*     */   }
/*     */   
/*     */   protected Size2D arrangeNF(BlockContainer container, Graphics2D g2, RectangleConstraint constraint) {
/* 199 */     List blocks = container.getBlocks();
/* 201 */     double height = constraint.getHeight();
/* 202 */     if (height <= 0.0D)
/* 203 */       height = Double.POSITIVE_INFINITY; 
/* 206 */     double x = 0.0D;
/* 207 */     double y = 0.0D;
/* 208 */     double maxWidth = 0.0D;
/* 209 */     List itemsInColumn = new ArrayList();
/* 210 */     for (int i = 0; i < blocks.size(); i++) {
/* 211 */       Block block = blocks.get(i);
/* 212 */       Size2D size = block.arrange(g2, RectangleConstraint.NONE);
/* 213 */       if (y + size.height <= height) {
/* 214 */         itemsInColumn.add(block);
/* 215 */         block.setBounds(new Rectangle2D.Double(x, y, size.width, size.height));
/* 218 */         y = y + size.height + this.verticalGap;
/* 219 */         maxWidth = Math.max(maxWidth, size.width);
/* 222 */       } else if (itemsInColumn.isEmpty()) {
/* 224 */         block.setBounds(new Rectangle2D.Double(x, y, size.width, Math.min(size.height, height - y)));
/* 229 */         y = 0.0D;
/* 230 */         x = x + size.width + this.horizontalGap;
/*     */       } else {
/* 234 */         itemsInColumn.clear();
/* 235 */         x = x + maxWidth + this.horizontalGap;
/* 236 */         y = 0.0D;
/* 237 */         maxWidth = size.width;
/* 238 */         block.setBounds(new Rectangle2D.Double(x, y, size.width, Math.min(size.height, height)));
/* 243 */         y = size.height + this.verticalGap;
/* 244 */         itemsInColumn.add(block);
/*     */       } 
/*     */     } 
/* 248 */     return new Size2D(x + maxWidth, constraint.getHeight());
/*     */   }
/*     */   
/*     */   protected Size2D arrangeRR(BlockContainer container, Graphics2D g2, RectangleConstraint constraint) {
/* 256 */     Size2D s1 = arrangeNN(container, g2);
/* 257 */     if (constraint.getHeightRange().contains(s1.height))
/* 258 */       return s1; 
/* 261 */     RectangleConstraint c = constraint.toFixedHeight(constraint.getHeightRange().getUpperBound());
/* 264 */     return arrangeRF(container, g2, c);
/*     */   }
/*     */   
/*     */   protected Size2D arrangeRF(BlockContainer container, Graphics2D g2, RectangleConstraint constraint) {
/* 281 */     Size2D s = arrangeNF(container, g2, constraint);
/* 282 */     if (constraint.getWidthRange().contains(s.width))
/* 283 */       return s; 
/* 286 */     RectangleConstraint c = constraint.toFixedWidth(constraint.getWidthRange().constrain(s.getWidth()));
/* 289 */     return arrangeFF(container, g2, c);
/*     */   }
/*     */   
/*     */   protected Size2D arrangeNN(BlockContainer container, Graphics2D g2) {
/* 303 */     double y = 0.0D;
/* 304 */     double height = 0.0D;
/* 305 */     double maxWidth = 0.0D;
/* 306 */     List blocks = container.getBlocks();
/* 307 */     int blockCount = blocks.size();
/* 308 */     if (blockCount > 0) {
/* 309 */       Size2D[] sizes = new Size2D[blocks.size()];
/*     */       int i;
/* 310 */       for (i = 0; i < blocks.size(); i++) {
/* 311 */         Block block = blocks.get(i);
/* 312 */         sizes[i] = block.arrange(g2, RectangleConstraint.NONE);
/* 313 */         height += sizes[i].getHeight();
/* 314 */         maxWidth = Math.max((sizes[i]).width, maxWidth);
/* 315 */         block.setBounds(new Rectangle2D.Double(0.0D, y, (sizes[i]).width, (sizes[i]).height));
/* 320 */         y = y + (sizes[i]).height + this.verticalGap;
/*     */       } 
/* 322 */       if (blockCount > 1)
/* 323 */         height += this.verticalGap * (blockCount - 1); 
/* 325 */       if (this.horizontalAlignment != HorizontalAlignment.LEFT)
/* 326 */         for (i = 0; i < blocks.size(); i++) {
/* 327 */           Block b = blocks.get(i);
/* 328 */           if (this.horizontalAlignment != HorizontalAlignment.CENTER)
/* 332 */             if (this.horizontalAlignment == HorizontalAlignment.RIGHT); 
/*     */         }  
/*     */     } 
/* 339 */     return new Size2D(maxWidth, height);
/*     */   }
/*     */   
/*     */   public void clear() {}
/*     */   
/*     */   public boolean equals(Object obj) {
/* 357 */     if (obj == this)
/* 358 */       return true; 
/* 360 */     if (!(obj instanceof ColumnArrangement))
/* 361 */       return false; 
/* 363 */     ColumnArrangement that = (ColumnArrangement)obj;
/* 364 */     if (this.horizontalAlignment != that.horizontalAlignment)
/* 365 */       return false; 
/* 367 */     if (this.verticalAlignment != that.verticalAlignment)
/* 368 */       return false; 
/* 370 */     if (this.horizontalGap != that.horizontalGap)
/* 371 */       return false; 
/* 373 */     if (this.verticalGap != that.verticalGap)
/* 374 */       return false; 
/* 376 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\block\ColumnArrangement.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */