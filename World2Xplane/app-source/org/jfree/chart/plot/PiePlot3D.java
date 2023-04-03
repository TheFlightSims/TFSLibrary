/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Color;
/*     */ import java.awt.Composite;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Polygon;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Arc2D;
/*     */ import java.awt.geom.Area;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.chart.entity.ChartEntity;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.entity.PieSectionEntity;
/*     */ import org.jfree.chart.labels.PieToolTipGenerator;
/*     */ import org.jfree.data.general.DatasetUtilities;
/*     */ import org.jfree.data.general.PieDataset;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ 
/*     */ public class PiePlot3D extends PiePlot implements Serializable {
/*     */   private static final long serialVersionUID = 3408984188945161432L;
/*     */   
/* 118 */   private double depthFactor = 0.2D;
/*     */   
/*     */   public PiePlot3D() {
/* 124 */     this((PieDataset)null);
/*     */   }
/*     */   
/*     */   public PiePlot3D(PieDataset dataset) {
/* 134 */     super(dataset);
/* 135 */     setCircular(false, false);
/*     */   }
/*     */   
/*     */   public void setDepthFactor(double factor) {
/* 144 */     this.depthFactor = factor;
/*     */   }
/*     */   
/*     */   public double getDepthFactor() {
/* 153 */     return this.depthFactor;
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, Rectangle2D plotArea, Point2D anchor, PlotState parentState, PlotRenderingInfo info) {
/* 174 */     RectangleInsets insets = getInsets();
/* 175 */     insets.trim(plotArea);
/* 177 */     Rectangle2D originalPlotArea = (Rectangle2D)plotArea.clone();
/* 178 */     if (info != null) {
/* 179 */       info.setPlotArea(plotArea);
/* 180 */       info.setDataArea(plotArea);
/*     */     } 
/* 183 */     Shape savedClip = g2.getClip();
/* 184 */     g2.clip(plotArea);
/* 187 */     double gapPercent = getInteriorGap();
/* 188 */     double labelPercent = 0.0D;
/* 189 */     if (getLabelGenerator() != null)
/* 190 */       labelPercent = getLabelGap() + getMaximumLabelWidth() + getLabelLinkMargin(); 
/* 193 */     double gapHorizontal = plotArea.getWidth() * (gapPercent + labelPercent);
/* 195 */     double gapVertical = plotArea.getHeight() * gapPercent;
/* 197 */     double linkX = plotArea.getX() + gapHorizontal / 2.0D;
/* 198 */     double linkY = plotArea.getY() + gapVertical / 2.0D;
/* 199 */     double linkW = plotArea.getWidth() - gapHorizontal;
/* 200 */     double linkH = plotArea.getHeight() - gapVertical;
/* 203 */     if (isCircular()) {
/* 204 */       double min = Math.min(linkW, linkH) / 2.0D;
/* 205 */       linkX = (linkX + linkX + linkW) / 2.0D - min;
/* 206 */       linkY = (linkY + linkY + linkH) / 2.0D - min;
/* 207 */       linkW = 2.0D * min;
/* 208 */       linkH = 2.0D * min;
/*     */     } 
/* 211 */     PiePlotState state = initialise(g2, plotArea, this, (Integer)null, info);
/* 215 */     double hh = linkW * getLabelLinkMargin();
/* 216 */     double vv = linkH * getLabelLinkMargin();
/* 217 */     Rectangle2D explodeArea = new Rectangle2D.Double(linkX + hh / 2.0D, linkY + vv / 2.0D, linkW - hh, linkH - vv);
/* 221 */     state.setExplodedPieArea(explodeArea);
/* 226 */     double maximumExplodePercent = getMaximumExplodePercent();
/* 227 */     double percent = maximumExplodePercent / (1.0D + maximumExplodePercent);
/* 229 */     double h1 = explodeArea.getWidth() * percent;
/* 230 */     double v1 = explodeArea.getHeight() * percent;
/* 231 */     Rectangle2D pieArea = new Rectangle2D.Double(explodeArea.getX() + h1 / 2.0D, explodeArea.getY() + v1 / 2.0D, explodeArea.getWidth() - h1, explodeArea.getHeight() - v1);
/* 236 */     int depth = (int)(pieArea.getHeight() * this.depthFactor);
/* 239 */     Rectangle2D linkArea = new Rectangle2D.Double(linkX, linkY, linkW, linkH - depth);
/* 242 */     state.setLinkArea(linkArea);
/* 244 */     state.setPieArea(pieArea);
/* 245 */     state.setPieCenterX(pieArea.getCenterX());
/* 246 */     state.setPieCenterY(pieArea.getCenterY() - depth / 2.0D);
/* 247 */     state.setPieWRadius(pieArea.getWidth() / 2.0D);
/* 248 */     state.setPieHRadius((pieArea.getHeight() - depth) / 2.0D);
/* 250 */     drawBackground(g2, plotArea);
/* 252 */     PieDataset dataset = getDataset();
/* 253 */     if (DatasetUtilities.isEmptyOrNull(getDataset())) {
/* 254 */       drawNoDataMessage(g2, plotArea);
/* 255 */       g2.setClip(savedClip);
/* 256 */       drawOutline(g2, plotArea);
/*     */       return;
/*     */     } 
/* 261 */     if (dataset.getKeys().size() > plotArea.getWidth()) {
/* 262 */       String text = "Too many elements";
/* 263 */       Font sfont = new Font("dialog", 1, 10);
/* 264 */       g2.setFont(sfont);
/* 265 */       FontMetrics fm = g2.getFontMetrics(sfont);
/* 266 */       int stringWidth = fm.stringWidth(text);
/* 268 */       g2.drawString(text, (int)(plotArea.getX() + (plotArea.getWidth() - stringWidth) / 2.0D), (int)(plotArea.getY() + plotArea.getHeight() / 2.0D));
/*     */       return;
/*     */     } 
/* 279 */     if (isCircular()) {
/* 280 */       double min = Math.min(plotArea.getWidth(), plotArea.getHeight()) / 2.0D;
/* 282 */       plotArea = new Rectangle2D.Double(plotArea.getCenterX() - min, plotArea.getCenterY() - min, 2.0D * min, 2.0D * min);
/*     */     } 
/* 288 */     List sectionKeys = dataset.getKeys();
/* 290 */     if (sectionKeys.size() == 0)
/*     */       return; 
/* 295 */     double arcX = pieArea.getX();
/* 296 */     double arcY = pieArea.getY();
/* 299 */     Composite originalComposite = g2.getComposite();
/* 300 */     g2.setComposite(AlphaComposite.getInstance(3, getForegroundAlpha()));
/* 303 */     double totalValue = DatasetUtilities.calculatePieDatasetTotal(dataset);
/* 304 */     double runningTotal = 0.0D;
/* 305 */     if (depth < 0)
/*     */       return; 
/* 309 */     ArrayList arcList = new ArrayList();
/* 315 */     Iterator iterator = sectionKeys.iterator();
/* 316 */     while (iterator.hasNext()) {
/* 318 */       Comparable currentKey = iterator.next();
/* 319 */       Number dataValue = dataset.getValue(currentKey);
/* 320 */       if (dataValue == null) {
/* 321 */         arcList.add(null);
/*     */         continue;
/*     */       } 
/* 324 */       double value = dataValue.doubleValue();
/* 325 */       if (value <= 0.0D) {
/* 326 */         arcList.add(null);
/*     */         continue;
/*     */       } 
/* 329 */       double startAngle = getStartAngle();
/* 330 */       double direction = getDirection().getFactor();
/* 331 */       double angle1 = startAngle + direction * runningTotal * 360.0D / totalValue;
/* 333 */       double angle2 = startAngle + direction * (runningTotal + value) * 360.0D / totalValue;
/* 335 */       if (Math.abs(angle2 - angle1) > getMinimumArcAngleToDraw()) {
/* 336 */         arcList.add(new Arc2D.Double(arcX, arcY + depth, pieArea.getWidth(), pieArea.getHeight() - depth, angle1, angle2 - angle1, 2));
/*     */       } else {
/* 345 */         arcList.add(null);
/*     */       } 
/* 347 */       runningTotal += value;
/*     */     } 
/* 350 */     Shape oldClip = g2.getClip();
/* 352 */     Ellipse2D top = new Ellipse2D.Double(pieArea.getX(), pieArea.getY(), pieArea.getWidth(), pieArea.getHeight() - depth);
/* 357 */     Ellipse2D bottom = new Ellipse2D.Double(pieArea.getX(), pieArea.getY() + depth, pieArea.getWidth(), pieArea.getHeight() - depth);
/* 362 */     Rectangle2D lower = new Rectangle2D.Double(top.getX(), top.getCenterY(), pieArea.getWidth(), bottom.getMaxY() - top.getCenterY());
/* 367 */     Rectangle2D upper = new Rectangle2D.Double(pieArea.getX(), top.getY(), pieArea.getWidth(), bottom.getCenterY() - top.getY());
/* 372 */     Area a = new Area(top);
/* 373 */     a.add(new Area(lower));
/* 374 */     Area b = new Area(bottom);
/* 375 */     b.add(new Area(upper));
/* 376 */     Area pie = new Area(a);
/* 377 */     pie.intersect(b);
/* 379 */     Area front = new Area(pie);
/* 380 */     front.subtract(new Area(top));
/* 382 */     Area back = new Area(pie);
/* 383 */     back.subtract(new Area(bottom));
/* 388 */     Paint outlinePaint = getSectionOutlinePaint(0);
/* 389 */     Arc2D.Double arc = new Arc2D.Double(arcX, arcY + depth, pieArea.getWidth(), pieArea.getHeight() - depth, 0.0D, 360.0D, 2);
/* 394 */     int categoryCount = arcList.size();
/* 395 */     for (int categoryIndex = 0; categoryIndex < categoryCount; 
/* 396 */       categoryIndex++) {
/* 397 */       arc = arcList.get(categoryIndex);
/* 398 */       if (arc != null) {
/* 401 */         Paint paint = getSectionPaint(categoryIndex);
/* 402 */         outlinePaint = getSectionOutlinePaint(categoryIndex);
/* 403 */         Stroke outlineStroke = getSectionOutlineStroke(categoryIndex);
/* 404 */         g2.setPaint(paint);
/* 405 */         g2.fill(arc);
/* 406 */         g2.setPaint(outlinePaint);
/* 407 */         g2.setStroke(outlineStroke);
/* 408 */         g2.draw(arc);
/* 409 */         g2.setPaint(paint);
/* 411 */         Point2D p1 = arc.getStartPoint();
/* 414 */         int[] xs = { (int)arc.getCenterX(), (int)arc.getCenterX(), (int)p1.getX(), (int)p1.getX() };
/* 418 */         int[] ys = { (int)arc.getCenterY(), (int)arc.getCenterY() - depth, (int)p1.getY() - depth, (int)p1.getY() };
/* 422 */         Polygon polygon = new Polygon(xs, ys, 4);
/* 423 */         g2.setPaint(Color.lightGray);
/* 424 */         g2.fill(polygon);
/* 425 */         g2.setPaint(outlinePaint);
/* 426 */         g2.setStroke(outlineStroke);
/* 427 */         g2.draw(polygon);
/* 428 */         g2.setPaint(paint);
/*     */       } 
/*     */     } 
/* 432 */     g2.setPaint(Color.gray);
/* 433 */     g2.fill(back);
/* 434 */     g2.fill(front);
/* 437 */     int cat = 0;
/* 438 */     iterator = (Iterator)arcList.iterator();
/* 439 */     while (iterator.hasNext()) {
/* 440 */       Arc2D segment = (Arc2D)iterator.next();
/* 441 */       if (segment != null) {
/* 442 */         Paint paint = getSectionPaint(cat);
/* 443 */         outlinePaint = getSectionOutlinePaint(cat);
/* 444 */         Stroke outlineStroke = getSectionOutlineStroke(cat);
/* 445 */         drawSide(g2, pieArea, segment, front, back, paint, outlinePaint, outlineStroke, false, true);
/*     */       } 
/* 451 */       cat++;
/*     */     } 
/* 455 */     cat = 0;
/* 456 */     iterator = (Iterator)arcList.iterator();
/* 457 */     while (iterator.hasNext()) {
/* 458 */       Arc2D segment = (Arc2D)iterator.next();
/* 459 */       if (segment != null) {
/* 460 */         Paint paint = getSectionPaint(cat);
/* 461 */         outlinePaint = getSectionOutlinePaint(cat);
/* 462 */         Stroke outlineStroke = getSectionOutlineStroke(cat);
/* 463 */         drawSide(g2, pieArea, segment, front, back, paint, outlinePaint, outlineStroke, true, false);
/*     */       } 
/* 469 */       cat++;
/*     */     } 
/* 472 */     g2.setClip(oldClip);
/* 476 */     for (int sectionIndex = 0; sectionIndex < categoryCount; 
/* 477 */       sectionIndex++) {
/* 478 */       arc = arcList.get(sectionIndex);
/* 479 */       if (arc != null) {
/* 482 */         Arc2D upperArc = new Arc2D.Double(arcX, arcY, pieArea.getWidth(), pieArea.getHeight() - depth, arc.getAngleStart(), arc.getAngleExtent(), 2);
/* 487 */         Paint paint = getSectionPaint(sectionIndex);
/* 488 */         outlinePaint = getSectionOutlinePaint(sectionIndex);
/* 489 */         Stroke outlineStroke = getSectionOutlineStroke(sectionIndex);
/* 490 */         g2.setPaint(paint);
/* 491 */         g2.fill(upperArc);
/* 492 */         g2.setStroke(outlineStroke);
/* 493 */         g2.setPaint(outlinePaint);
/* 494 */         g2.draw(upperArc);
/* 497 */         Comparable currentKey = sectionKeys.get(sectionIndex);
/* 498 */         if (info != null) {
/* 499 */           EntityCollection entities = info.getOwner().getEntityCollection();
/* 501 */           if (entities != null) {
/* 502 */             String tip = null;
/* 503 */             PieToolTipGenerator tipster = getToolTipGenerator();
/* 504 */             if (tipster != null)
/* 506 */               tip = tipster.generateToolTip(dataset, currentKey); 
/* 508 */             String url = null;
/* 509 */             if (getURLGenerator() != null)
/* 510 */               url = getURLGenerator().generateURL(dataset, currentKey, getPieIndex()); 
/* 513 */             PieSectionEntity entity = new PieSectionEntity(upperArc, dataset, getPieIndex(), sectionIndex, currentKey, tip, url);
/* 517 */             entities.add((ChartEntity)entity);
/*     */           } 
/*     */         } 
/* 520 */         List keys = dataset.getKeys();
/* 521 */         Rectangle2D adjustedPlotArea = new Rectangle2D.Double(originalPlotArea.getX(), originalPlotArea.getY(), originalPlotArea.getWidth(), originalPlotArea.getHeight() - depth);
/* 526 */         drawLabels(g2, keys, totalValue, adjustedPlotArea, linkArea, state);
/*     */       } 
/*     */     } 
/* 529 */     g2.setClip(savedClip);
/* 530 */     g2.setComposite(originalComposite);
/* 531 */     drawOutline(g2, originalPlotArea);
/*     */   }
/*     */   
/*     */   protected void drawSide(Graphics2D g2, Rectangle2D plotArea, Arc2D arc, Area front, Area back, Paint paint, Paint outlinePaint, Stroke outlineStroke, boolean drawFront, boolean drawBack) {
/* 560 */     double start = arc.getAngleStart();
/* 561 */     double extent = arc.getAngleExtent();
/* 562 */     double end = start + extent;
/* 564 */     g2.setStroke(outlineStroke);
/* 567 */     if (extent < 0.0D) {
/* 569 */       if (isAngleAtFront(start)) {
/* 571 */         if (!isAngleAtBack(end)) {
/* 573 */           if (extent > -180.0D) {
/* 575 */             if (drawFront) {
/* 576 */               Area side = new Area(new Rectangle2D.Double(arc.getEndPoint().getX(), plotArea.getY(), arc.getStartPoint().getX() - arc.getEndPoint().getX(), plotArea.getHeight()));
/* 584 */               side.intersect(front);
/* 585 */               g2.setPaint(paint);
/* 586 */               g2.fill(side);
/* 587 */               g2.setPaint(outlinePaint);
/* 588 */               g2.draw(side);
/*     */             } 
/*     */           } else {
/* 594 */             Area side1 = new Area(new Rectangle2D.Double(plotArea.getX(), plotArea.getY(), arc.getStartPoint().getX() - plotArea.getX(), plotArea.getHeight()));
/* 601 */             side1.intersect(front);
/* 603 */             Area side2 = new Area(new Rectangle2D.Double(arc.getEndPoint().getX(), plotArea.getY(), plotArea.getMaxX() - arc.getEndPoint().getX(), plotArea.getHeight()));
/* 611 */             side2.intersect(front);
/* 612 */             g2.setPaint(paint);
/* 613 */             if (drawFront) {
/* 614 */               g2.fill(side1);
/* 615 */               g2.fill(side2);
/*     */             } 
/* 618 */             if (drawBack)
/* 619 */               g2.fill(back); 
/* 622 */             g2.setPaint(outlinePaint);
/* 623 */             if (drawFront) {
/* 624 */               g2.draw(side1);
/* 625 */               g2.draw(side2);
/*     */             } 
/* 628 */             if (drawBack)
/* 629 */               g2.draw(back); 
/*     */           } 
/*     */         } else {
/* 637 */           if (drawBack) {
/* 638 */             Area side2 = new Area(new Rectangle2D.Double(plotArea.getX(), plotArea.getY(), arc.getEndPoint().getX() - plotArea.getX(), plotArea.getHeight()));
/* 645 */             side2.intersect(back);
/* 646 */             g2.setPaint(paint);
/* 647 */             g2.fill(side2);
/* 648 */             g2.setPaint(outlinePaint);
/* 649 */             g2.draw(side2);
/*     */           } 
/* 652 */           if (drawFront) {
/* 653 */             Area side1 = new Area(new Rectangle2D.Double(plotArea.getX(), plotArea.getY(), arc.getStartPoint().getX() - plotArea.getX(), plotArea.getHeight()));
/* 660 */             side1.intersect(front);
/* 661 */             g2.setPaint(paint);
/* 662 */             g2.fill(side1);
/* 663 */             g2.setPaint(outlinePaint);
/* 664 */             g2.draw(side1);
/*     */           } 
/*     */         } 
/* 671 */       } else if (!isAngleAtFront(end)) {
/* 672 */         if (extent > -180.0D) {
/* 673 */           if (drawBack) {
/* 674 */             Area side = new Area(new Rectangle2D.Double(arc.getStartPoint().getX(), plotArea.getY(), arc.getEndPoint().getX() - arc.getStartPoint().getX(), plotArea.getHeight()));
/* 682 */             side.intersect(back);
/* 683 */             g2.setPaint(paint);
/* 684 */             g2.fill(side);
/* 685 */             g2.setPaint(outlinePaint);
/* 686 */             g2.draw(side);
/*     */           } 
/*     */         } else {
/* 691 */           Area side1 = new Area(new Rectangle2D.Double(arc.getStartPoint().getX(), plotArea.getY(), plotArea.getMaxX() - arc.getStartPoint().getX(), plotArea.getHeight()));
/* 698 */           side1.intersect(back);
/* 700 */           Area side2 = new Area(new Rectangle2D.Double(plotArea.getX(), plotArea.getY(), arc.getEndPoint().getX() - plotArea.getX(), plotArea.getHeight()));
/* 708 */           side2.intersect(back);
/* 710 */           g2.setPaint(paint);
/* 711 */           if (drawBack) {
/* 712 */             g2.fill(side1);
/* 713 */             g2.fill(side2);
/*     */           } 
/* 716 */           if (drawFront)
/* 717 */             g2.fill(front); 
/* 720 */           g2.setPaint(outlinePaint);
/* 721 */           if (drawBack) {
/* 722 */             g2.draw(side1);
/* 723 */             g2.draw(side2);
/*     */           } 
/* 726 */           if (drawFront)
/* 727 */             g2.draw(front); 
/*     */         } 
/*     */       } else {
/* 734 */         if (drawBack) {
/* 735 */           Area side1 = new Area(new Rectangle2D.Double(arc.getStartPoint().getX(), plotArea.getY(), plotArea.getMaxX() - arc.getStartPoint().getX(), plotArea.getHeight()));
/* 742 */           side1.intersect(back);
/* 743 */           g2.setPaint(paint);
/* 744 */           g2.fill(side1);
/* 745 */           g2.setPaint(outlinePaint);
/* 746 */           g2.draw(side1);
/*     */         } 
/* 749 */         if (drawFront) {
/* 750 */           Area side2 = new Area(new Rectangle2D.Double(arc.getEndPoint().getX(), plotArea.getY(), plotArea.getMaxX() - arc.getEndPoint().getX(), plotArea.getHeight()));
/* 757 */           side2.intersect(front);
/* 758 */           g2.setPaint(paint);
/* 759 */           g2.fill(side2);
/* 760 */           g2.setPaint(outlinePaint);
/* 761 */           g2.draw(side2);
/*     */         } 
/*     */       } 
/* 767 */     } else if (extent > 0.0D) {
/* 769 */       if (isAngleAtFront(start)) {
/* 771 */         if (!isAngleAtBack(end)) {
/* 773 */           if (extent < 180.0D) {
/* 774 */             if (drawFront) {
/* 775 */               Area side = new Area(new Rectangle2D.Double(arc.getStartPoint().getX(), plotArea.getY(), arc.getEndPoint().getX() - arc.getStartPoint().getX(), plotArea.getHeight()));
/* 783 */               side.intersect(front);
/* 784 */               g2.setPaint(paint);
/* 785 */               g2.fill(side);
/* 786 */               g2.setPaint(outlinePaint);
/* 787 */               g2.draw(side);
/*     */             } 
/*     */           } else {
/* 791 */             Area side1 = new Area(new Rectangle2D.Double(arc.getStartPoint().getX(), plotArea.getY(), plotArea.getMaxX() - arc.getStartPoint().getX(), plotArea.getHeight()));
/* 798 */             side1.intersect(front);
/* 800 */             Area side2 = new Area(new Rectangle2D.Double(plotArea.getX(), plotArea.getY(), arc.getEndPoint().getX() - plotArea.getX(), plotArea.getHeight()));
/* 807 */             side2.intersect(front);
/* 809 */             g2.setPaint(paint);
/* 810 */             if (drawFront) {
/* 811 */               g2.fill(side1);
/* 812 */               g2.fill(side2);
/*     */             } 
/* 815 */             if (drawBack)
/* 816 */               g2.fill(back); 
/* 819 */             g2.setPaint(outlinePaint);
/* 820 */             if (drawFront) {
/* 821 */               g2.draw(side1);
/* 822 */               g2.draw(side2);
/*     */             } 
/* 825 */             if (drawBack)
/* 826 */               g2.draw(back); 
/*     */           } 
/*     */         } else {
/* 832 */           if (drawBack) {
/* 833 */             Area side2 = new Area(new Rectangle2D.Double(arc.getEndPoint().getX(), plotArea.getY(), plotArea.getMaxX() - arc.getEndPoint().getX(), plotArea.getHeight()));
/* 840 */             side2.intersect(back);
/* 841 */             g2.setPaint(paint);
/* 842 */             g2.fill(side2);
/* 843 */             g2.setPaint(outlinePaint);
/* 844 */             g2.draw(side2);
/*     */           } 
/* 847 */           if (drawFront) {
/* 848 */             Area side1 = new Area(new Rectangle2D.Double(arc.getStartPoint().getX(), plotArea.getY(), plotArea.getMaxX() - arc.getStartPoint().getX(), plotArea.getHeight()));
/* 855 */             side1.intersect(front);
/* 856 */             g2.setPaint(paint);
/* 857 */             g2.fill(side1);
/* 858 */             g2.setPaint(outlinePaint);
/* 859 */             g2.draw(side1);
/*     */           } 
/*     */         } 
/* 865 */       } else if (!isAngleAtFront(end)) {
/* 866 */         if (extent < 180.0D) {
/* 867 */           if (drawBack) {
/* 868 */             Area side = new Area(new Rectangle2D.Double(arc.getEndPoint().getX(), plotArea.getY(), arc.getStartPoint().getX() - arc.getEndPoint().getX(), plotArea.getHeight()));
/* 876 */             side.intersect(back);
/* 877 */             g2.setPaint(paint);
/* 878 */             g2.fill(side);
/* 879 */             g2.setPaint(outlinePaint);
/* 880 */             g2.draw(side);
/*     */           } 
/*     */         } else {
/* 885 */           Area side1 = new Area(new Rectangle2D.Double(arc.getStartPoint().getX(), plotArea.getY(), plotArea.getX() - arc.getStartPoint().getX(), plotArea.getHeight()));
/* 892 */           side1.intersect(back);
/* 894 */           Area side2 = new Area(new Rectangle2D.Double(arc.getEndPoint().getX(), plotArea.getY(), plotArea.getMaxX() - arc.getEndPoint().getX(), plotArea.getHeight()));
/* 901 */           side2.intersect(back);
/* 903 */           g2.setPaint(paint);
/* 904 */           if (drawBack) {
/* 905 */             g2.fill(side1);
/* 906 */             g2.fill(side2);
/*     */           } 
/* 909 */           if (drawFront)
/* 910 */             g2.fill(front); 
/* 913 */           g2.setPaint(outlinePaint);
/* 914 */           if (drawBack) {
/* 915 */             g2.draw(side1);
/* 916 */             g2.draw(side2);
/*     */           } 
/* 919 */           if (drawFront)
/* 920 */             g2.draw(front); 
/*     */         } 
/*     */       } else {
/* 927 */         if (drawBack) {
/* 928 */           Area side1 = new Area(new Rectangle2D.Double(plotArea.getX(), plotArea.getY(), arc.getStartPoint().getX() - plotArea.getX(), plotArea.getHeight()));
/* 935 */           side1.intersect(back);
/* 936 */           g2.setPaint(paint);
/* 937 */           g2.fill(side1);
/* 938 */           g2.setPaint(outlinePaint);
/* 939 */           g2.draw(side1);
/*     */         } 
/* 942 */         if (drawFront) {
/* 943 */           Area side2 = new Area(new Rectangle2D.Double(plotArea.getX(), plotArea.getY(), arc.getEndPoint().getX() - plotArea.getX(), plotArea.getHeight()));
/* 950 */           side2.intersect(front);
/* 951 */           g2.setPaint(paint);
/* 952 */           g2.fill(side2);
/* 953 */           g2.setPaint(outlinePaint);
/* 954 */           g2.draw(side2);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getPlotType() {
/* 969 */     return localizationResources.getString("Pie_3D_Plot");
/*     */   }
/*     */   
/*     */   private boolean isAngleAtFront(double angle) {
/* 982 */     return (Math.sin(Math.toRadians(angle)) < 0.0D);
/*     */   }
/*     */   
/*     */   private boolean isAngleAtBack(double angle) {
/* 995 */     return (Math.sin(Math.toRadians(angle)) > 0.0D);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\PiePlot3D.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */