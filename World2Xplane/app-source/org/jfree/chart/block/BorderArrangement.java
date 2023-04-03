/*     */ package org.jfree.chart.block;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.ui.Size2D;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ public class BorderArrangement implements Arrangement, Serializable {
/*     */   private static final long serialVersionUID = 506071142274883745L;
/*     */   
/*     */   private Block centerBlock;
/*     */   
/*     */   private Block topBlock;
/*     */   
/*     */   private Block bottomBlock;
/*     */   
/*     */   private Block leftBlock;
/*     */   
/*     */   private Block rightBlock;
/*     */   
/*     */   public void add(Block block, Object key) {
/*  97 */     if (key == null) {
/*  98 */       this.centerBlock = block;
/*     */     } else {
/* 101 */       RectangleEdge edge = (RectangleEdge)key;
/* 102 */       if (edge == RectangleEdge.TOP) {
/* 103 */         this.topBlock = block;
/* 105 */       } else if (edge == RectangleEdge.BOTTOM) {
/* 106 */         this.bottomBlock = block;
/* 108 */       } else if (edge == RectangleEdge.LEFT) {
/* 109 */         this.leftBlock = block;
/* 111 */       } else if (edge == RectangleEdge.RIGHT) {
/* 112 */         this.rightBlock = block;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Size2D arrange(BlockContainer container, Graphics2D g2, RectangleConstraint constraint) {
/* 130 */     RectangleConstraint contentConstraint = container.toContentConstraint(constraint);
/* 132 */     Size2D contentSize = null;
/* 133 */     LengthConstraintType w = contentConstraint.getWidthConstraintType();
/* 134 */     LengthConstraintType h = contentConstraint.getHeightConstraintType();
/* 135 */     if (w == LengthConstraintType.NONE) {
/* 136 */       if (h == LengthConstraintType.NONE) {
/* 137 */         contentSize = arrangeNN(container, g2);
/*     */       } else {
/* 139 */         if (h == LengthConstraintType.FIXED)
/* 140 */           throw new RuntimeException("Not implemented."); 
/* 142 */         if (h == LengthConstraintType.RANGE)
/* 143 */           throw new RuntimeException("Not implemented."); 
/*     */       } 
/* 146 */     } else if (w == LengthConstraintType.FIXED) {
/* 147 */       if (h == LengthConstraintType.NONE) {
/* 148 */         contentSize = arrangeFN(container, g2, constraint.getWidth());
/* 150 */       } else if (h == LengthConstraintType.FIXED) {
/* 151 */         contentSize = arrangeFF(container, g2, constraint);
/* 153 */       } else if (h == LengthConstraintType.RANGE) {
/* 154 */         contentSize = arrangeFR(container, g2, constraint);
/*     */       } 
/* 157 */     } else if (w == LengthConstraintType.RANGE) {
/* 158 */       if (h == LengthConstraintType.NONE)
/* 159 */         throw new RuntimeException("Not implemented."); 
/* 161 */       if (h == LengthConstraintType.FIXED)
/* 162 */         throw new RuntimeException("Not implemented."); 
/* 164 */       if (h == LengthConstraintType.RANGE)
/* 165 */         contentSize = arrangeRR(container, constraint.getWidthRange(), constraint.getHeightRange(), g2); 
/*     */     } 
/* 171 */     return new Size2D(container.calculateTotalWidth(contentSize.getWidth()), container.calculateTotalHeight(contentSize.getHeight()));
/*     */   }
/*     */   
/*     */   protected Size2D arrangeNN(BlockContainer container, Graphics2D g2) {
/* 186 */     double[] w = new double[5];
/* 187 */     double[] h = new double[5];
/* 188 */     if (this.topBlock != null) {
/* 189 */       Size2D size = this.topBlock.arrange(g2, RectangleConstraint.NONE);
/* 192 */       w[0] = size.width;
/* 193 */       h[0] = size.height;
/*     */     } 
/* 195 */     if (this.bottomBlock != null) {
/* 196 */       Size2D size = this.bottomBlock.arrange(g2, RectangleConstraint.NONE);
/* 199 */       w[1] = size.width;
/* 200 */       h[1] = size.height;
/*     */     } 
/* 202 */     if (this.leftBlock != null) {
/* 203 */       Size2D size = this.leftBlock.arrange(g2, RectangleConstraint.NONE);
/* 206 */       w[2] = size.width;
/* 207 */       h[2] = size.height;
/*     */     } 
/* 209 */     if (this.rightBlock != null) {
/* 210 */       Size2D size = this.rightBlock.arrange(g2, RectangleConstraint.NONE);
/* 213 */       w[3] = size.width;
/* 214 */       h[3] = size.height;
/*     */     } 
/* 217 */     h[2] = Math.max(h[2], h[3]);
/* 218 */     h[3] = h[2];
/* 220 */     if (this.centerBlock != null) {
/* 221 */       Size2D size = this.centerBlock.arrange(g2, RectangleConstraint.NONE);
/* 224 */       w[4] = size.width;
/* 225 */       h[4] = size.height;
/*     */     } 
/* 227 */     double width = Math.max(w[0], Math.max(w[1], w[2] + w[4] + w[3]));
/* 228 */     double centerHeight = Math.max(h[2], Math.max(h[3], h[4]));
/* 229 */     double height = h[0] + h[1] + centerHeight;
/* 230 */     if (this.topBlock != null)
/* 231 */       this.topBlock.setBounds(new Rectangle2D.Double(0.0D, 0.0D, width, h[0])); 
/* 235 */     if (this.bottomBlock != null)
/* 236 */       this.bottomBlock.setBounds(new Rectangle2D.Double(0.0D, height - h[1], width, h[1])); 
/* 240 */     if (this.leftBlock != null)
/* 241 */       this.leftBlock.setBounds(new Rectangle2D.Double(0.0D, h[0], w[2], centerHeight)); 
/* 245 */     if (this.rightBlock != null)
/* 246 */       this.rightBlock.setBounds(new Rectangle2D.Double(width - w[3], h[0], w[3], centerHeight)); 
/* 251 */     if (this.centerBlock != null)
/* 252 */       this.centerBlock.setBounds(new Rectangle2D.Double(w[2], h[0], width - w[2] - w[3], centerHeight)); 
/* 258 */     return new Size2D(width, height);
/*     */   }
/*     */   
/*     */   protected Size2D arrangeFR(BlockContainer container, Graphics2D g2, RectangleConstraint constraint) {
/* 272 */     Size2D size1 = arrangeFN(container, g2, constraint.getWidth());
/* 273 */     if (constraint.getHeightRange().contains(size1.getHeight()))
/* 274 */       return size1; 
/* 277 */     double h = constraint.getHeightRange().constrain(size1.getHeight());
/* 278 */     RectangleConstraint c2 = constraint.toFixedHeight(h);
/* 279 */     return arrange(container, g2, c2);
/*     */   }
/*     */   
/*     */   protected Size2D arrangeFN(BlockContainer container, Graphics2D g2, double width) {
/* 295 */     double[] w = new double[5];
/* 296 */     double[] h = new double[5];
/* 297 */     RectangleConstraint c1 = new RectangleConstraint(width, null, LengthConstraintType.FIXED, 0.0D, null, LengthConstraintType.NONE);
/* 301 */     if (this.topBlock != null) {
/* 302 */       Size2D size = this.topBlock.arrange(g2, c1);
/* 303 */       w[0] = size.width;
/* 304 */       h[0] = size.height;
/*     */     } 
/* 306 */     if (this.bottomBlock != null) {
/* 307 */       Size2D size = this.bottomBlock.arrange(g2, c1);
/* 308 */       w[1] = size.width;
/* 309 */       h[1] = size.height;
/*     */     } 
/* 311 */     RectangleConstraint c2 = new RectangleConstraint(0.0D, new Range(0.0D, width), LengthConstraintType.RANGE, 0.0D, null, LengthConstraintType.NONE);
/* 315 */     if (this.leftBlock != null) {
/* 316 */       Size2D size = this.leftBlock.arrange(g2, c2);
/* 317 */       w[2] = size.width;
/* 318 */       h[2] = size.height;
/*     */     } 
/* 320 */     if (this.rightBlock != null) {
/* 321 */       double maxW = Math.max(width - w[2], 0.0D);
/* 322 */       RectangleConstraint c3 = new RectangleConstraint(0.0D, new Range(Math.min(w[2], maxW), maxW), LengthConstraintType.RANGE, 0.0D, null, LengthConstraintType.NONE);
/* 327 */       Size2D size = this.rightBlock.arrange(g2, c3);
/* 328 */       w[3] = size.width;
/* 329 */       h[3] = size.height;
/*     */     } 
/* 332 */     h[2] = Math.max(h[2], h[3]);
/* 333 */     h[3] = h[2];
/* 335 */     if (this.centerBlock != null) {
/* 336 */       RectangleConstraint c4 = new RectangleConstraint(width - w[2] - w[3], null, LengthConstraintType.FIXED, 0.0D, null, LengthConstraintType.NONE);
/* 340 */       Size2D size = this.centerBlock.arrange(g2, c4);
/* 341 */       w[4] = size.width;
/* 342 */       h[4] = size.height;
/*     */     } 
/* 344 */     double height = h[0] + h[1] + Math.max(h[2], Math.max(h[3], h[4]));
/* 345 */     return arrange(container, g2, new RectangleConstraint(width, height));
/*     */   }
/*     */   
/*     */   protected Size2D arrangeRR(BlockContainer container, Range widthRange, Range heightRange, Graphics2D g2) {
/* 362 */     double[] w = new double[5];
/* 363 */     double[] h = new double[5];
/* 364 */     if (this.topBlock != null) {
/* 365 */       RectangleConstraint c1 = new RectangleConstraint(widthRange, heightRange);
/* 368 */       Size2D size = this.topBlock.arrange(g2, c1);
/* 369 */       w[0] = size.width;
/* 370 */       h[0] = size.height;
/*     */     } 
/* 372 */     if (this.bottomBlock != null) {
/* 373 */       Range heightRange2 = Range.shift(heightRange, -h[0], false);
/* 374 */       RectangleConstraint c2 = new RectangleConstraint(widthRange, heightRange2);
/* 377 */       Size2D size = this.bottomBlock.arrange(g2, c2);
/* 378 */       w[1] = size.width;
/* 379 */       h[1] = size.height;
/*     */     } 
/* 381 */     Range heightRange3 = Range.shift(heightRange, -(h[0] + h[1]));
/* 382 */     if (this.leftBlock != null) {
/* 383 */       RectangleConstraint c3 = new RectangleConstraint(widthRange, heightRange3);
/* 386 */       Size2D size = this.leftBlock.arrange(g2, c3);
/* 387 */       w[2] = size.width;
/* 388 */       h[2] = size.height;
/*     */     } 
/* 390 */     Range widthRange2 = Range.shift(widthRange, -w[2], false);
/* 391 */     if (this.rightBlock != null) {
/* 392 */       RectangleConstraint c4 = new RectangleConstraint(widthRange2, heightRange3);
/* 395 */       Size2D size = this.rightBlock.arrange(g2, c4);
/* 396 */       w[3] = size.width;
/* 397 */       h[3] = size.height;
/*     */     } 
/* 400 */     h[2] = Math.max(h[2], h[3]);
/* 401 */     h[3] = h[2];
/* 402 */     Range widthRange3 = Range.shift(widthRange, -(w[2] + w[3]), false);
/* 403 */     if (this.centerBlock != null) {
/* 404 */       RectangleConstraint c5 = new RectangleConstraint(widthRange3, heightRange3);
/* 410 */       Size2D size = this.centerBlock.arrange(g2, c5);
/* 411 */       w[4] = size.width;
/* 412 */       h[4] = size.height;
/*     */     } 
/* 414 */     double width = Math.max(w[0], Math.max(w[1], w[2] + w[4] + w[3]));
/* 415 */     double height = h[0] + h[1] + Math.max(h[2], Math.max(h[3], h[4]));
/* 416 */     if (this.topBlock != null)
/* 417 */       this.topBlock.setBounds(new Rectangle2D.Double(0.0D, 0.0D, width, h[0])); 
/* 421 */     if (this.bottomBlock != null)
/* 422 */       this.bottomBlock.setBounds(new Rectangle2D.Double(0.0D, height - h[1], width, h[1])); 
/* 426 */     if (this.leftBlock != null)
/* 427 */       this.leftBlock.setBounds(new Rectangle2D.Double(0.0D, h[0], w[2], h[2])); 
/* 431 */     if (this.rightBlock != null)
/* 432 */       this.rightBlock.setBounds(new Rectangle2D.Double(width - w[3], h[0], w[3], h[3])); 
/* 437 */     if (this.centerBlock != null)
/* 438 */       this.centerBlock.setBounds(new Rectangle2D.Double(w[2], h[0], width - w[2] - w[3], height - h[0] - h[1])); 
/* 444 */     return new Size2D(width, height);
/*     */   }
/*     */   
/*     */   protected Size2D arrangeFF(BlockContainer container, Graphics2D g2, RectangleConstraint constraint) {
/* 458 */     double[] w = new double[5];
/* 459 */     double[] h = new double[5];
/* 460 */     w[0] = constraint.getWidth();
/* 461 */     if (this.topBlock != null) {
/* 462 */       RectangleConstraint c1 = new RectangleConstraint(w[0], null, LengthConstraintType.FIXED, 0.0D, new Range(0.0D, constraint.getHeight()), LengthConstraintType.RANGE);
/* 467 */       Size2D size = this.topBlock.arrange(g2, c1);
/* 468 */       h[0] = size.height;
/*     */     } 
/* 470 */     w[1] = w[0];
/* 471 */     if (this.bottomBlock != null) {
/* 472 */       RectangleConstraint c2 = new RectangleConstraint(w[0], null, LengthConstraintType.FIXED, 0.0D, new Range(0.0D, constraint.getHeight() - h[0]), LengthConstraintType.RANGE);
/* 477 */       Size2D size = this.bottomBlock.arrange(g2, c2);
/* 478 */       h[1] = size.height;
/*     */     } 
/* 480 */     h[2] = constraint.getHeight() - h[1] - h[0];
/* 481 */     if (this.leftBlock != null) {
/* 482 */       RectangleConstraint c3 = new RectangleConstraint(0.0D, new Range(0.0D, constraint.getWidth()), LengthConstraintType.RANGE, h[2], null, LengthConstraintType.FIXED);
/* 487 */       Size2D size = this.leftBlock.arrange(g2, c3);
/* 488 */       w[2] = size.width;
/*     */     } 
/* 490 */     h[3] = h[2];
/* 491 */     if (this.rightBlock != null) {
/* 492 */       RectangleConstraint c4 = new RectangleConstraint(0.0D, new Range(0.0D, constraint.getWidth() - w[2]), LengthConstraintType.RANGE, h[2], null, LengthConstraintType.FIXED);
/* 497 */       Size2D size = this.rightBlock.arrange(g2, c4);
/* 498 */       w[3] = size.width;
/*     */     } 
/* 500 */     h[4] = h[2];
/* 501 */     w[4] = constraint.getWidth() - w[3] - w[2];
/* 502 */     RectangleConstraint c5 = new RectangleConstraint(w[4], h[4]);
/* 503 */     if (this.centerBlock != null)
/* 504 */       this.centerBlock.arrange(g2, c5); 
/* 507 */     if (this.topBlock != null)
/* 508 */       this.topBlock.setBounds(new Rectangle2D.Double(0.0D, 0.0D, w[0], h[0])); 
/* 512 */     if (this.bottomBlock != null)
/* 513 */       this.bottomBlock.setBounds(new Rectangle2D.Double(0.0D, h[0] + h[2], w[1], h[1])); 
/* 517 */     if (this.leftBlock != null)
/* 518 */       this.leftBlock.setBounds(new Rectangle2D.Double(0.0D, h[0], w[2], h[2])); 
/* 522 */     if (this.rightBlock != null)
/* 523 */       this.rightBlock.setBounds(new Rectangle2D.Double(w[2] + w[4], h[0], w[3], h[3])); 
/* 527 */     if (this.centerBlock != null)
/* 528 */       this.centerBlock.setBounds(new Rectangle2D.Double(w[2], h[0], w[4], h[4])); 
/* 532 */     return new Size2D(constraint.getWidth(), constraint.getHeight());
/*     */   }
/*     */   
/*     */   public void clear() {
/* 539 */     this.centerBlock = null;
/* 540 */     this.topBlock = null;
/* 541 */     this.bottomBlock = null;
/* 542 */     this.leftBlock = null;
/* 543 */     this.rightBlock = null;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 554 */     if (obj == this)
/* 555 */       return true; 
/* 557 */     if (!(obj instanceof BorderArrangement))
/* 558 */       return false; 
/* 560 */     BorderArrangement that = (BorderArrangement)obj;
/* 561 */     if (!ObjectUtilities.equal(this.topBlock, that.topBlock))
/* 562 */       return false; 
/* 564 */     if (!ObjectUtilities.equal(this.bottomBlock, that.bottomBlock))
/* 565 */       return false; 
/* 567 */     if (!ObjectUtilities.equal(this.leftBlock, that.leftBlock))
/* 568 */       return false; 
/* 570 */     if (!ObjectUtilities.equal(this.rightBlock, that.rightBlock))
/* 571 */       return false; 
/* 573 */     if (!ObjectUtilities.equal(this.centerBlock, that.centerBlock))
/* 574 */       return false; 
/* 576 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\block\BorderArrangement.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */