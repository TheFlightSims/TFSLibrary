/*     */ package org.jfree.chart.renderer.category;
/*     */ 
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Color;
/*     */ import java.awt.Composite;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.Effect3D;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.labels.CategoryItemLabelGenerator;
/*     */ import org.jfree.chart.labels.ItemLabelAnchor;
/*     */ import org.jfree.chart.labels.ItemLabelPosition;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.Marker;
/*     */ import org.jfree.chart.plot.Plot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.ValueMarker;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.text.TextUtilities;
/*     */ import org.jfree.ui.LengthAdjustmentType;
/*     */ import org.jfree.ui.RectangleAnchor;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.ui.TextAnchor;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class BarRenderer3D extends BarRenderer implements Effect3D, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = 7686976503536003636L;
/*     */   
/*     */   public static final double DEFAULT_X_OFFSET = 12.0D;
/*     */   
/*     */   public static final double DEFAULT_Y_OFFSET = 8.0D;
/*     */   
/* 155 */   public static final Paint DEFAULT_WALL_PAINT = new Color(221, 221, 221);
/*     */   
/*     */   private double xOffset;
/*     */   
/*     */   private double yOffset;
/*     */   
/*     */   private transient Paint wallPaint;
/*     */   
/*     */   public BarRenderer3D() {
/* 170 */     this(12.0D, 8.0D);
/*     */   }
/*     */   
/*     */   public BarRenderer3D(double xOffset, double yOffset) {
/* 182 */     this.xOffset = xOffset;
/* 183 */     this.yOffset = yOffset;
/* 184 */     this.wallPaint = DEFAULT_WALL_PAINT;
/* 186 */     ItemLabelPosition p1 = new ItemLabelPosition(ItemLabelAnchor.INSIDE12, TextAnchor.TOP_CENTER);
/* 189 */     setPositiveItemLabelPosition(p1);
/* 190 */     ItemLabelPosition p2 = new ItemLabelPosition(ItemLabelAnchor.INSIDE12, TextAnchor.TOP_CENTER);
/* 193 */     setNegativeItemLabelPosition(p2);
/*     */   }
/*     */   
/*     */   public double getXOffset() {
/* 203 */     return this.xOffset;
/*     */   }
/*     */   
/*     */   public double getYOffset() {
/* 212 */     return this.yOffset;
/*     */   }
/*     */   
/*     */   public Paint getWallPaint() {
/* 222 */     return this.wallPaint;
/*     */   }
/*     */   
/*     */   public void setWallPaint(Paint paint) {
/* 232 */     this.wallPaint = paint;
/*     */   }
/*     */   
/*     */   public CategoryItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea, CategoryPlot plot, int rendererIndex, PlotRenderingInfo info) {
/* 255 */     Rectangle2D adjusted = new Rectangle2D.Double(dataArea.getX(), dataArea.getY() + getYOffset(), dataArea.getWidth() - getXOffset(), dataArea.getHeight() - getYOffset());
/* 260 */     CategoryItemRendererState state = super.initialise(g2, adjusted, plot, rendererIndex, info);
/* 263 */     return state;
/*     */   }
/*     */   
/*     */   public void drawBackground(Graphics2D g2, CategoryPlot plot, Rectangle2D dataArea) {
/* 277 */     float x0 = (float)dataArea.getX();
/* 278 */     float x1 = x0 + (float)Math.abs(this.xOffset);
/* 279 */     float x3 = (float)dataArea.getMaxX();
/* 280 */     float x2 = x3 - (float)Math.abs(this.xOffset);
/* 282 */     float y0 = (float)dataArea.getMaxY();
/* 283 */     float y1 = y0 - (float)Math.abs(this.yOffset);
/* 284 */     float y3 = (float)dataArea.getMinY();
/* 285 */     float y2 = y3 + (float)Math.abs(this.yOffset);
/* 287 */     GeneralPath clip = new GeneralPath();
/* 288 */     clip.moveTo(x0, y0);
/* 289 */     clip.lineTo(x0, y2);
/* 290 */     clip.lineTo(x1, y3);
/* 291 */     clip.lineTo(x3, y3);
/* 292 */     clip.lineTo(x3, y1);
/* 293 */     clip.lineTo(x2, y0);
/* 294 */     clip.closePath();
/* 297 */     Paint backgroundPaint = plot.getBackgroundPaint();
/* 298 */     if (backgroundPaint != null) {
/* 299 */       g2.setPaint(backgroundPaint);
/* 300 */       g2.fill(clip);
/*     */     } 
/* 303 */     GeneralPath leftWall = new GeneralPath();
/* 304 */     leftWall.moveTo(x0, y0);
/* 305 */     leftWall.lineTo(x0, y2);
/* 306 */     leftWall.lineTo(x1, y3);
/* 307 */     leftWall.lineTo(x1, y1);
/* 308 */     leftWall.closePath();
/* 309 */     g2.setPaint(getWallPaint());
/* 310 */     g2.fill(leftWall);
/* 312 */     GeneralPath bottomWall = new GeneralPath();
/* 313 */     bottomWall.moveTo(x0, y0);
/* 314 */     bottomWall.lineTo(x1, y1);
/* 315 */     bottomWall.lineTo(x3, y1);
/* 316 */     bottomWall.lineTo(x2, y0);
/* 317 */     bottomWall.closePath();
/* 318 */     g2.setPaint(getWallPaint());
/* 319 */     g2.fill(bottomWall);
/* 322 */     g2.setPaint(Color.lightGray);
/* 323 */     Line2D corner = new Line2D.Double(x0, y0, x1, y1);
/* 324 */     g2.draw(corner);
/* 325 */     corner.setLine(x1, y1, x1, y3);
/* 326 */     g2.draw(corner);
/* 327 */     corner.setLine(x1, y1, x3, y1);
/* 328 */     g2.draw(corner);
/* 331 */     Image backgroundImage = plot.getBackgroundImage();
/* 332 */     if (backgroundImage != null) {
/* 333 */       Composite originalComposite = g2.getComposite();
/* 334 */       g2.setComposite(AlphaComposite.getInstance(2, plot.getBackgroundAlpha()));
/* 339 */       g2.drawImage(backgroundImage, (int)x1, (int)y3, (int)(x3 - x1 + 1.0F), (int)(y1 - y3 + 1.0F), null);
/* 345 */       g2.setComposite(originalComposite);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void drawOutline(Graphics2D g2, CategoryPlot plot, Rectangle2D dataArea) {
/* 360 */     float x0 = (float)dataArea.getX();
/* 361 */     float x1 = x0 + (float)Math.abs(this.xOffset);
/* 362 */     float x3 = (float)dataArea.getMaxX();
/* 363 */     float x2 = x3 - (float)Math.abs(this.xOffset);
/* 365 */     float y0 = (float)dataArea.getMaxY();
/* 366 */     float y1 = y0 - (float)Math.abs(this.yOffset);
/* 367 */     float y3 = (float)dataArea.getMinY();
/* 368 */     float y2 = y3 + (float)Math.abs(this.yOffset);
/* 370 */     GeneralPath clip = new GeneralPath();
/* 371 */     clip.moveTo(x0, y0);
/* 372 */     clip.lineTo(x0, y2);
/* 373 */     clip.lineTo(x1, y3);
/* 374 */     clip.lineTo(x3, y3);
/* 375 */     clip.lineTo(x3, y1);
/* 376 */     clip.lineTo(x2, y0);
/* 377 */     clip.closePath();
/* 380 */     Stroke outlineStroke = plot.getOutlineStroke();
/* 381 */     Paint outlinePaint = plot.getOutlinePaint();
/* 382 */     if (outlineStroke != null && outlinePaint != null) {
/* 383 */       g2.setStroke(outlineStroke);
/* 384 */       g2.setPaint(outlinePaint);
/* 385 */       g2.draw(clip);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void drawDomainGridline(Graphics2D g2, CategoryPlot plot, Rectangle2D dataArea, double value) {
/* 405 */     Line2D line1 = null;
/* 406 */     Line2D line2 = null;
/* 407 */     PlotOrientation orientation = plot.getOrientation();
/* 408 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 409 */       double y0 = value;
/* 410 */       double y1 = value - getYOffset();
/* 411 */       double x0 = dataArea.getMinX();
/* 412 */       double x1 = x0 + getXOffset();
/* 413 */       double x2 = dataArea.getMaxY();
/* 414 */       line1 = new Line2D.Double(x0, y0, x1, y1);
/* 415 */       line2 = new Line2D.Double(x1, y1, x2, y1);
/* 417 */     } else if (orientation == PlotOrientation.VERTICAL) {
/* 418 */       double x0 = value;
/* 419 */       double x1 = value + getXOffset();
/* 420 */       double y0 = dataArea.getMaxY();
/* 421 */       double y1 = y0 - getYOffset();
/* 422 */       double y2 = dataArea.getMinY();
/* 423 */       line1 = new Line2D.Double(x0, y0, x1, y1);
/* 424 */       line2 = new Line2D.Double(x1, y1, x1, y2);
/*     */     } 
/* 426 */     Paint paint = plot.getDomainGridlinePaint();
/* 427 */     Stroke stroke = plot.getDomainGridlineStroke();
/* 428 */     g2.setPaint((paint != null) ? paint : Plot.DEFAULT_OUTLINE_PAINT);
/* 429 */     g2.setStroke((stroke != null) ? stroke : Plot.DEFAULT_OUTLINE_STROKE);
/* 430 */     g2.draw(line1);
/* 431 */     g2.draw(line2);
/*     */   }
/*     */   
/*     */   public void drawRangeGridline(Graphics2D g2, CategoryPlot plot, ValueAxis axis, Rectangle2D dataArea, double value) {
/* 452 */     Range range = axis.getRange();
/* 454 */     if (!range.contains(value))
/*     */       return; 
/* 458 */     Rectangle2D adjusted = new Rectangle2D.Double(dataArea.getX(), dataArea.getY() + getYOffset(), dataArea.getWidth() - getXOffset(), dataArea.getHeight() - getYOffset());
/* 465 */     Line2D line1 = null;
/* 466 */     Line2D line2 = null;
/* 467 */     PlotOrientation orientation = plot.getOrientation();
/* 468 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 469 */       double x0 = axis.valueToJava2D(value, adjusted, plot.getRangeAxisEdge());
/* 472 */       double x1 = x0 + getXOffset();
/* 473 */       double y0 = dataArea.getMaxY();
/* 474 */       double y1 = y0 - getYOffset();
/* 475 */       double y2 = dataArea.getMinY();
/* 476 */       line1 = new Line2D.Double(x0, y0, x1, y1);
/* 477 */       line2 = new Line2D.Double(x1, y1, x1, y2);
/* 479 */     } else if (orientation == PlotOrientation.VERTICAL) {
/* 480 */       double y0 = axis.valueToJava2D(value, adjusted, plot.getRangeAxisEdge());
/* 483 */       double y1 = y0 - getYOffset();
/* 484 */       double x0 = dataArea.getMinX();
/* 485 */       double x1 = x0 + getXOffset();
/* 486 */       double x2 = dataArea.getMaxX();
/* 487 */       line1 = new Line2D.Double(x0, y0, x1, y1);
/* 488 */       line2 = new Line2D.Double(x1, y1, x2, y1);
/*     */     } 
/* 490 */     Paint paint = plot.getRangeGridlinePaint();
/* 491 */     Stroke stroke = plot.getRangeGridlineStroke();
/* 492 */     g2.setPaint((paint != null) ? paint : Plot.DEFAULT_OUTLINE_PAINT);
/* 493 */     g2.setStroke((stroke != null) ? stroke : Plot.DEFAULT_OUTLINE_STROKE);
/* 494 */     g2.draw(line1);
/* 495 */     g2.draw(line2);
/*     */   }
/*     */   
/*     */   public void drawRangeMarker(Graphics2D g2, CategoryPlot plot, ValueAxis axis, Marker marker, Rectangle2D dataArea) {
/* 514 */     if (marker instanceof ValueMarker) {
/* 515 */       ValueMarker vm = (ValueMarker)marker;
/* 516 */       double value = vm.getValue();
/* 517 */       Range range = axis.getRange();
/* 518 */       if (!range.contains(value))
/*     */         return; 
/* 522 */       Rectangle2D adjusted = new Rectangle2D.Double(dataArea.getX(), dataArea.getY() + getYOffset(), dataArea.getWidth() - getXOffset(), dataArea.getHeight() - getYOffset());
/* 528 */       GeneralPath path = null;
/* 529 */       PlotOrientation orientation = plot.getOrientation();
/* 530 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 531 */         float x = (float)axis.valueToJava2D(value, adjusted, plot.getRangeAxisEdge());
/* 534 */         float y = (float)adjusted.getMaxY();
/* 535 */         path = new GeneralPath();
/* 536 */         path.moveTo(x, y);
/* 537 */         path.lineTo((float)(x + getXOffset()), y - (float)getYOffset());
/* 540 */         path.lineTo((float)(x + getXOffset()), (float)(adjusted.getMinY() - getYOffset()));
/* 544 */         path.lineTo(x, (float)adjusted.getMinY());
/* 545 */         path.closePath();
/* 547 */       } else if (orientation == PlotOrientation.VERTICAL) {
/* 548 */         float y = (float)axis.valueToJava2D(value, adjusted, plot.getRangeAxisEdge());
/* 551 */         float x = (float)dataArea.getX();
/* 552 */         path = new GeneralPath();
/* 553 */         path.moveTo(x, y);
/* 554 */         path.lineTo(x + (float)this.xOffset, y - (float)this.yOffset);
/* 555 */         path.lineTo((float)(adjusted.getMaxX() + this.xOffset), y - (float)this.yOffset);
/* 559 */         path.lineTo((float)adjusted.getMaxX(), y);
/* 560 */         path.closePath();
/*     */       } 
/* 562 */       g2.setPaint(marker.getPaint());
/* 563 */       g2.fill(path);
/* 564 */       g2.setPaint(marker.getOutlinePaint());
/* 565 */       g2.draw(path);
/* 567 */       String label = marker.getLabel();
/* 568 */       RectangleAnchor anchor = marker.getLabelAnchor();
/* 569 */       if (label != null) {
/* 570 */         Font labelFont = marker.getLabelFont();
/* 571 */         g2.setFont(labelFont);
/* 572 */         g2.setPaint(marker.getLabelPaint());
/* 573 */         Point2D coordinates = calculateRangeMarkerTextAnchorPoint(g2, orientation, dataArea, path.getBounds2D(), marker.getLabelOffset(), LengthAdjustmentType.EXPAND, anchor);
/* 577 */         TextUtilities.drawAlignedString(label, g2, (float)coordinates.getX(), (float)coordinates.getY(), marker.getLabelTextAnchor());
/*     */       } 
/*     */     } else {
/* 586 */       super.drawRangeMarker(g2, plot, axis, marker, dataArea);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column, int pass) {
/* 617 */     Number dataValue = dataset.getValue(row, column);
/* 618 */     if (dataValue == null)
/*     */       return; 
/* 622 */     double value = dataValue.doubleValue();
/* 624 */     Rectangle2D adjusted = new Rectangle2D.Double(dataArea.getX(), dataArea.getY() + getYOffset(), dataArea.getWidth() - getXOffset(), dataArea.getHeight() - getYOffset());
/* 631 */     PlotOrientation orientation = plot.getOrientation();
/* 633 */     double barW0 = calculateBarW0(plot, orientation, adjusted, domainAxis, state, row, column);
/* 636 */     double[] barL0L1 = calculateBarL0L1(value);
/* 637 */     if (barL0L1 == null)
/*     */       return; 
/* 641 */     RectangleEdge edge = plot.getRangeAxisEdge();
/* 642 */     double transL0 = rangeAxis.valueToJava2D(barL0L1[0], adjusted, edge);
/* 643 */     double transL1 = rangeAxis.valueToJava2D(barL0L1[1], adjusted, edge);
/* 644 */     double barL0 = Math.min(transL0, transL1);
/* 645 */     double barLength = Math.abs(transL1 - transL0);
/* 648 */     Rectangle2D bar = null;
/* 649 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 650 */       bar = new Rectangle2D.Double(barL0, barW0, barLength, state.getBarWidth());
/*     */     } else {
/* 655 */       bar = new Rectangle2D.Double(barW0, barL0, state.getBarWidth(), barLength);
/*     */     } 
/* 659 */     Paint itemPaint = getItemPaint(row, column);
/* 660 */     g2.setPaint(itemPaint);
/* 661 */     g2.fill(bar);
/* 663 */     double x0 = bar.getMinX();
/* 664 */     double x1 = x0 + getXOffset();
/* 665 */     double x2 = bar.getMaxX();
/* 666 */     double x3 = x2 + getXOffset();
/* 668 */     double y0 = bar.getMinY() - getYOffset();
/* 669 */     double y1 = bar.getMinY();
/* 670 */     double y2 = bar.getMaxY() - getYOffset();
/* 671 */     double y3 = bar.getMaxY();
/* 673 */     GeneralPath bar3dRight = null;
/* 674 */     GeneralPath bar3dTop = null;
/* 675 */     if (barLength > 0.0D) {
/* 676 */       bar3dRight = new GeneralPath();
/* 677 */       bar3dRight.moveTo((float)x2, (float)y3);
/* 678 */       bar3dRight.lineTo((float)x2, (float)y1);
/* 679 */       bar3dRight.lineTo((float)x3, (float)y0);
/* 680 */       bar3dRight.lineTo((float)x3, (float)y2);
/* 681 */       bar3dRight.closePath();
/* 683 */       if (itemPaint instanceof Color)
/* 684 */         g2.setPaint(((Color)itemPaint).darker()); 
/* 686 */       g2.fill(bar3dRight);
/*     */     } 
/* 689 */     bar3dTop = new GeneralPath();
/* 690 */     bar3dTop.moveTo((float)x0, (float)y1);
/* 691 */     bar3dTop.lineTo((float)x1, (float)y0);
/* 692 */     bar3dTop.lineTo((float)x3, (float)y0);
/* 693 */     bar3dTop.lineTo((float)x2, (float)y1);
/* 694 */     bar3dTop.closePath();
/* 695 */     g2.fill(bar3dTop);
/* 697 */     if (isDrawBarOutline() && state.getBarWidth() > 3.0D) {
/* 699 */       g2.setStroke(getItemOutlineStroke(row, column));
/* 700 */       g2.setPaint(getItemOutlinePaint(row, column));
/* 701 */       g2.draw(bar);
/* 702 */       if (bar3dRight != null)
/* 703 */         g2.draw(bar3dRight); 
/* 705 */       if (bar3dTop != null)
/* 706 */         g2.draw(bar3dTop); 
/*     */     } 
/* 710 */     CategoryItemLabelGenerator generator = getItemLabelGenerator(row, column);
/* 712 */     if (generator != null && isItemLabelVisible(row, column))
/* 713 */       drawItemLabel(g2, dataset, row, column, plot, generator, bar, (value < 0.0D)); 
/* 719 */     EntityCollection entities = state.getEntityCollection();
/* 720 */     if (entities != null) {
/* 721 */       GeneralPath barOutline = new GeneralPath();
/* 722 */       barOutline.moveTo((float)x0, (float)y3);
/* 723 */       barOutline.lineTo((float)x0, (float)y1);
/* 724 */       barOutline.lineTo((float)x1, (float)y0);
/* 725 */       barOutline.lineTo((float)x3, (float)y0);
/* 726 */       barOutline.lineTo((float)x3, (float)y2);
/* 727 */       barOutline.lineTo((float)x2, (float)y3);
/* 728 */       barOutline.closePath();
/* 729 */       addItemEntity(entities, dataset, row, column, barOutline);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 742 */     stream.defaultWriteObject();
/* 743 */     SerialUtilities.writePaint(this.wallPaint, stream);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 756 */     stream.defaultReadObject();
/* 757 */     this.wallPaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\category\BarRenderer3D.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */