/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Arc2D;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.entity.ChartEntity;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.entity.PieSectionEntity;
/*     */ import org.jfree.chart.event.PlotChangeEvent;
/*     */ import org.jfree.chart.labels.PieToolTipGenerator;
/*     */ import org.jfree.chart.urls.PieURLGenerator;
/*     */ import org.jfree.data.general.PieDataset;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ import org.jfree.util.Rotation;
/*     */ import org.jfree.util.ShapeUtilities;
/*     */ import org.jfree.util.UnitType;
/*     */ 
/*     */ public class RingPlot extends PiePlot implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = 1556064784129676620L;
/*     */   
/*     */   private boolean separatorsVisible;
/*     */   
/*     */   private transient Stroke separatorStroke;
/*     */   
/*     */   private transient Paint separatorPaint;
/*     */   
/*     */   private double innerSeparatorExtension;
/*     */   
/*     */   private double outerSeparatorExtension;
/*     */   
/*     */   public RingPlot() {
/* 113 */     this((PieDataset)null);
/*     */   }
/*     */   
/*     */   public RingPlot(PieDataset dataset) {
/* 122 */     super(dataset);
/* 123 */     this.separatorsVisible = true;
/* 124 */     this.separatorStroke = new BasicStroke(0.5F);
/* 125 */     this.separatorPaint = Color.gray;
/* 126 */     this.innerSeparatorExtension = 0.2D;
/* 127 */     this.outerSeparatorExtension = 0.2D;
/*     */   }
/*     */   
/*     */   public boolean getSeparatorsVisible() {
/* 137 */     return this.separatorsVisible;
/*     */   }
/*     */   
/*     */   public void setSeparatorsVisible(boolean visible) {
/* 148 */     this.separatorsVisible = visible;
/* 149 */     notifyListeners(new PlotChangeEvent(this));
/*     */   }
/*     */   
/*     */   public Stroke getSeparatorStroke() {
/* 158 */     return this.separatorStroke;
/*     */   }
/*     */   
/*     */   public void setSeparatorStroke(Stroke stroke) {
/* 167 */     if (stroke == null)
/* 168 */       throw new IllegalArgumentException("Null 'stroke' argument."); 
/* 170 */     this.separatorStroke = stroke;
/* 171 */     notifyListeners(new PlotChangeEvent(this));
/*     */   }
/*     */   
/*     */   public Paint getSeparatorPaint() {
/* 180 */     return this.separatorPaint;
/*     */   }
/*     */   
/*     */   public void setSeparatorPaint(Paint paint) {
/* 189 */     if (paint == null)
/* 190 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/* 192 */     this.separatorPaint = paint;
/* 193 */     notifyListeners(new PlotChangeEvent(this));
/*     */   }
/*     */   
/*     */   public double getInnerSeparatorExtension() {
/* 204 */     return this.innerSeparatorExtension;
/*     */   }
/*     */   
/*     */   public void setInnerSeparatorExtension(double percent) {
/* 216 */     this.innerSeparatorExtension = percent;
/* 217 */     notifyListeners(new PlotChangeEvent(this));
/*     */   }
/*     */   
/*     */   public double getOuterSeparatorExtension() {
/* 228 */     return this.outerSeparatorExtension;
/*     */   }
/*     */   
/*     */   public void setOuterSeparatorExtension(double percent) {
/* 240 */     this.outerSeparatorExtension = percent;
/* 241 */     notifyListeners(new PlotChangeEvent(this));
/*     */   }
/*     */   
/*     */   protected void drawItem(Graphics2D g2, int section, Rectangle2D dataArea, PiePlotState state, int currentPass) {
/* 259 */     PieDataset dataset = getDataset();
/* 260 */     Number n = dataset.getValue(section);
/* 261 */     if (n == null)
/*     */       return; 
/* 264 */     double value = n.doubleValue();
/* 265 */     double angle1 = 0.0D;
/* 266 */     double angle2 = 0.0D;
/* 268 */     Rotation direction = getDirection();
/* 269 */     if (direction == Rotation.CLOCKWISE) {
/* 270 */       angle1 = state.getLatestAngle();
/* 271 */       angle2 = angle1 - value / state.getTotal() * 360.0D;
/* 273 */     } else if (direction == Rotation.ANTICLOCKWISE) {
/* 274 */       angle1 = state.getLatestAngle();
/* 275 */       angle2 = angle1 + value / state.getTotal() * 360.0D;
/*     */     } else {
/* 278 */       throw new IllegalStateException("Rotation type not recognised.");
/*     */     } 
/* 281 */     double angle = angle2 - angle1;
/* 282 */     if (Math.abs(angle) > getMinimumArcAngleToDraw()) {
/* 283 */       double ep = 0.0D;
/* 284 */       double mep = getMaximumExplodePercent();
/* 285 */       if (mep > 0.0D)
/* 286 */         ep = getExplodePercent(section) / mep; 
/* 288 */       Rectangle2D arcBounds = getArcBounds(state.getPieArea(), state.getExplodedPieArea(), angle1, angle, ep);
/* 292 */       Arc2D.Double arc = new Arc2D.Double(arcBounds, angle1, angle, 0);
/* 297 */       RectangleInsets s = new RectangleInsets(UnitType.RELATIVE, 0.1D, 0.1D, 0.1D, 0.1D);
/* 300 */       Rectangle2D innerArcBounds = new Rectangle2D.Double();
/* 301 */       innerArcBounds.setRect(arcBounds);
/* 302 */       s.trim(innerArcBounds);
/* 305 */       Arc2D.Double arc2 = new Arc2D.Double(innerArcBounds, angle1 + angle, -angle, 0);
/* 308 */       GeneralPath path = new GeneralPath();
/* 309 */       path.moveTo((float)arc.getStartPoint().getX(), (float)arc.getStartPoint().getY());
/* 313 */       path.append(arc.getPathIterator(null), false);
/* 314 */       path.append(arc2.getPathIterator(null), true);
/* 315 */       path.closePath();
/* 317 */       Line2D separator = new Line2D.Double(arc2.getEndPoint(), arc.getStartPoint());
/* 321 */       if (currentPass == 0) {
/* 322 */         Paint shadowPaint = getShadowPaint();
/* 323 */         double shadowXOffset = getShadowXOffset();
/* 324 */         double shadowYOffset = getShadowYOffset();
/* 325 */         if (shadowPaint != null) {
/* 326 */           Shape shadowArc = ShapeUtilities.createTranslatedShape(path, (float)shadowXOffset, (float)shadowYOffset);
/* 329 */           g2.setPaint(shadowPaint);
/* 330 */           g2.fill(shadowArc);
/*     */         } 
/* 333 */       } else if (currentPass == 1) {
/* 335 */         Paint paint = getSectionPaint(section);
/* 336 */         g2.setPaint(paint);
/* 337 */         g2.fill(path);
/* 338 */         Paint outlinePaint = getSectionOutlinePaint(section);
/* 339 */         Stroke outlineStroke = getSectionOutlineStroke(section);
/* 340 */         if (outlinePaint != null && outlineStroke != null) {
/* 341 */           g2.setPaint(outlinePaint);
/* 342 */           g2.setStroke(outlineStroke);
/* 343 */           g2.draw(path);
/*     */         } 
/* 346 */         if (this.separatorsVisible) {
/* 347 */           Line2D extendedSeparator = extendLine(separator, this.innerSeparatorExtension, this.innerSeparatorExtension);
/* 351 */           g2.setStroke(this.separatorStroke);
/* 352 */           g2.setPaint(this.separatorPaint);
/* 353 */           g2.draw(extendedSeparator);
/*     */         } 
/* 357 */         if (state.getInfo() != null) {
/* 358 */           EntityCollection entities = state.getEntityCollection();
/* 359 */           if (entities != null) {
/* 360 */             Comparable key = dataset.getKey(section);
/* 361 */             String tip = null;
/* 362 */             PieToolTipGenerator toolTipGenerator = getToolTipGenerator();
/* 364 */             if (toolTipGenerator != null)
/* 365 */               tip = toolTipGenerator.generateToolTip(dataset, key); 
/* 369 */             String url = null;
/* 370 */             PieURLGenerator urlGenerator = getURLGenerator();
/* 371 */             if (urlGenerator != null)
/* 372 */               url = urlGenerator.generateURL(dataset, key, getPieIndex()); 
/* 376 */             PieSectionEntity entity = new PieSectionEntity(arc, dataset, getPieIndex(), section, key, tip, url);
/* 379 */             entities.add((ChartEntity)entity);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 384 */     state.setLatestAngle(angle2);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 395 */     if (this == obj)
/* 396 */       return true; 
/* 398 */     if (!(obj instanceof RingPlot))
/* 399 */       return false; 
/* 401 */     if (!super.equals(obj))
/* 402 */       return false; 
/* 404 */     RingPlot that = (RingPlot)obj;
/* 405 */     if (this.separatorsVisible != that.separatorsVisible)
/* 406 */       return false; 
/* 408 */     if (!ObjectUtilities.equal(this.separatorStroke, that.separatorStroke))
/* 411 */       return false; 
/* 413 */     if (!PaintUtilities.equal(this.separatorPaint, that.separatorPaint))
/* 414 */       return false; 
/* 416 */     if (this.innerSeparatorExtension != that.innerSeparatorExtension)
/* 417 */       return false; 
/* 419 */     if (this.outerSeparatorExtension != that.outerSeparatorExtension)
/* 420 */       return false; 
/* 422 */     return true;
/*     */   }
/*     */   
/*     */   private Line2D extendLine(Line2D line, double startPercent, double endPercent) {
/* 437 */     if (line == null)
/* 438 */       throw new IllegalArgumentException("Null 'line' argument."); 
/* 440 */     double x1 = line.getX1();
/* 441 */     double x2 = line.getX2();
/* 442 */     double deltaX = x2 - x1;
/* 443 */     double y1 = line.getY1();
/* 444 */     double y2 = line.getY2();
/* 445 */     double deltaY = y2 - y1;
/* 446 */     x1 -= startPercent * deltaX;
/* 447 */     y1 -= startPercent * deltaY;
/* 448 */     x2 += endPercent * deltaX;
/* 449 */     y2 += endPercent * deltaY;
/* 450 */     return new Line2D.Double(x1, y1, x2, y2);
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 461 */     stream.defaultWriteObject();
/* 462 */     SerialUtilities.writeStroke(this.separatorStroke, stream);
/* 463 */     SerialUtilities.writePaint(this.separatorPaint, stream);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 476 */     stream.defaultReadObject();
/* 477 */     this.separatorStroke = SerialUtilities.readStroke(stream);
/* 478 */     this.separatorPaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\RingPlot.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */