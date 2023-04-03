/*     */ package org.jfree.chart.renderer.category;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Arc2D;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import javax.swing.Icon;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.CategoryItemEntity;
/*     */ import org.jfree.chart.entity.ChartEntity;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.event.RendererChangeEvent;
/*     */ import org.jfree.chart.labels.CategoryToolTipGenerator;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ 
/*     */ public class MinMaxCategoryRenderer extends AbstractCategoryItemRenderer {
/*     */   private static final long serialVersionUID = 2935615937671064911L;
/*     */   
/*     */   private boolean plotLines = false;
/*     */   
/* 109 */   private transient Paint groupPaint = Color.black;
/*     */   
/* 114 */   private transient Stroke groupStroke = new BasicStroke(1.0F);
/*     */   
/* 117 */   private transient Icon minIcon = getIcon(new Arc2D.Double(-4.0D, -4.0D, 8.0D, 8.0D, 0.0D, 360.0D, 0), (Paint)null, Color.black);
/*     */   
/* 121 */   private transient Icon maxIcon = getIcon(new Arc2D.Double(-4.0D, -4.0D, 8.0D, 8.0D, 0.0D, 360.0D, 0), (Paint)null, Color.black);
/*     */   
/* 125 */   private transient Icon objectIcon = getIcon(new Line2D.Double(-4.0D, 0.0D, 4.0D, 0.0D), false, true);
/*     */   
/* 129 */   private int lastCategory = -1;
/*     */   
/*     */   private double min;
/*     */   
/*     */   private double max;
/*     */   
/*     */   public boolean isDrawLines() {
/* 153 */     return this.plotLines;
/*     */   }
/*     */   
/*     */   public void setDrawLines(boolean draw) {
/* 166 */     if (this.plotLines != draw) {
/* 167 */       this.plotLines = draw;
/* 168 */       notifyListeners(new RendererChangeEvent(this));
/*     */     } 
/*     */   }
/*     */   
/*     */   public Paint getGroupPaint() {
/* 182 */     return this.groupPaint;
/*     */   }
/*     */   
/*     */   public void setGroupPaint(Paint paint) {
/* 195 */     if (paint == null)
/* 196 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/* 198 */     this.groupPaint = paint;
/* 199 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public Stroke getGroupStroke() {
/* 211 */     return this.groupStroke;
/*     */   }
/*     */   
/*     */   public void setGroupStroke(Stroke groupStroke) {
/* 221 */     this.groupStroke = groupStroke;
/*     */   }
/*     */   
/*     */   public Icon getObjectIcon() {
/* 232 */     return this.objectIcon;
/*     */   }
/*     */   
/*     */   public void setObjectIcon(Icon icon) {
/* 243 */     if (icon == null)
/* 244 */       throw new IllegalArgumentException("Null 'icon' argument."); 
/* 246 */     this.objectIcon = icon;
/* 247 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public Icon getMaxIcon() {
/* 259 */     return this.maxIcon;
/*     */   }
/*     */   
/*     */   public void setMaxIcon(Icon icon) {
/* 272 */     if (icon == null)
/* 273 */       throw new IllegalArgumentException("Null 'icon' argument."); 
/* 275 */     this.maxIcon = icon;
/* 276 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public Icon getMinIcon() {
/* 288 */     return this.minIcon;
/*     */   }
/*     */   
/*     */   public void setMinIcon(Icon icon) {
/* 301 */     if (icon == null)
/* 302 */       throw new IllegalArgumentException("Null 'icon' argument."); 
/* 304 */     this.minIcon = icon;
/* 305 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column, int pass) {
/* 328 */     Number value = dataset.getValue(row, column);
/* 329 */     if (value != null) {
/* 331 */       double x1 = domainAxis.getCategoryMiddle(column, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/* 333 */       double y1 = rangeAxis.valueToJava2D(value.doubleValue(), dataArea, plot.getRangeAxisEdge());
/* 335 */       g2.setPaint(getItemPaint(row, column));
/* 336 */       g2.setStroke(getItemStroke(row, column));
/* 337 */       Shape shape = null;
/* 338 */       shape = new Rectangle2D.Double(x1 - 4.0D, y1 - 4.0D, 8.0D, 8.0D);
/* 339 */       this.objectIcon.paintIcon(null, g2, (int)x1, (int)y1);
/* 340 */       if (this.lastCategory == column) {
/* 341 */         if (this.min > value.doubleValue())
/* 342 */           this.min = value.doubleValue(); 
/* 344 */         if (this.max < value.doubleValue())
/* 345 */           this.max = value.doubleValue(); 
/* 347 */         if (dataset.getRowCount() - 1 == row) {
/* 348 */           g2.setPaint(this.groupPaint);
/* 349 */           g2.setStroke(this.groupStroke);
/* 350 */           double minY = rangeAxis.valueToJava2D(this.min, dataArea, plot.getRangeAxisEdge());
/* 352 */           double maxY = rangeAxis.valueToJava2D(this.max, dataArea, plot.getRangeAxisEdge());
/* 354 */           g2.draw(new Line2D.Double(x1, minY, x1, maxY));
/* 355 */           this.minIcon.paintIcon(null, g2, (int)x1, (int)minY);
/* 356 */           this.maxIcon.paintIcon(null, g2, (int)x1, (int)maxY);
/*     */         } 
/*     */       } else {
/* 360 */         this.lastCategory = column;
/* 361 */         this.min = value.doubleValue();
/* 362 */         this.max = value.doubleValue();
/*     */       } 
/* 365 */       if (this.plotLines && 
/* 366 */         column != 0) {
/* 367 */         Number previousValue = dataset.getValue(row, column - 1);
/* 368 */         if (previousValue != null) {
/* 370 */           double previous = previousValue.doubleValue();
/* 371 */           double x0 = domainAxis.getCategoryMiddle(column - 1, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/* 374 */           double y0 = rangeAxis.valueToJava2D(previous, dataArea, plot.getRangeAxisEdge());
/* 376 */           g2.setPaint(getItemPaint(row, column));
/* 377 */           g2.setStroke(getItemStroke(row, column));
/* 378 */           Line2D line = new Line2D.Double(x0, y0, x1, y1);
/* 379 */           g2.draw(line);
/*     */         } 
/*     */       } 
/* 385 */       if (state.getInfo() != null) {
/* 386 */         EntityCollection entities = state.getEntityCollection();
/* 387 */         if (entities != null && shape != null) {
/* 388 */           String tip = null;
/* 389 */           CategoryToolTipGenerator tipster = getToolTipGenerator(row, column);
/* 391 */           if (tipster != null)
/* 392 */             tip = tipster.generateToolTip(dataset, row, column); 
/* 394 */           CategoryItemEntity entity = new CategoryItemEntity(shape, tip, null, dataset, row, dataset.getColumnKey(column), column);
/* 397 */           entities.add((ChartEntity)entity);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private Icon getIcon(Shape shape, Paint fillPaint, Paint outlinePaint) {
/* 415 */     int width = (shape.getBounds()).width;
/* 416 */     int height = (shape.getBounds()).height;
/* 417 */     GeneralPath path = new GeneralPath(shape);
/* 418 */     return new Icon(this, path, fillPaint, outlinePaint, width, height) {
/*     */         private final GeneralPath val$path;
/*     */         
/*     */         private final Paint val$fillPaint;
/*     */         
/*     */         private final Paint val$outlinePaint;
/*     */         
/*     */         private final int val$width;
/*     */         
/*     */         private final int val$height;
/*     */         
/*     */         private final MinMaxCategoryRenderer this$0;
/*     */         
/*     */         public void paintIcon(Component c, Graphics g, int x, int y) {
/* 420 */           Graphics2D g2 = (Graphics2D)g;
/* 421 */           this.val$path.transform(AffineTransform.getTranslateInstance(x, y));
/* 422 */           if (this.val$fillPaint != null) {
/* 423 */             g2.setPaint(this.val$fillPaint);
/* 424 */             g2.fill(this.val$path);
/*     */           } 
/* 426 */           if (this.val$outlinePaint != null) {
/* 427 */             g2.setPaint(this.val$outlinePaint);
/* 428 */             g2.draw(this.val$path);
/*     */           } 
/* 430 */           this.val$path.transform(AffineTransform.getTranslateInstance(-x, -y));
/*     */         }
/*     */         
/*     */         public int getIconWidth() {
/* 434 */           return this.val$width;
/*     */         }
/*     */         
/*     */         public int getIconHeight() {
/* 438 */           return this.val$height;
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   private Icon getIcon(Shape shape, boolean fill, boolean outline) {
/* 455 */     int width = (shape.getBounds()).width;
/* 456 */     int height = (shape.getBounds()).height;
/* 457 */     GeneralPath path = new GeneralPath(shape);
/* 458 */     return new Icon(this, path, fill, outline, width, height) {
/*     */         private final GeneralPath val$path;
/*     */         
/*     */         private final boolean val$fill;
/*     */         
/*     */         private final boolean val$outline;
/*     */         
/*     */         private final int val$width;
/*     */         
/*     */         private final int val$height;
/*     */         
/*     */         private final MinMaxCategoryRenderer this$0;
/*     */         
/*     */         public void paintIcon(Component c, Graphics g, int x, int y) {
/* 460 */           Graphics2D g2 = (Graphics2D)g;
/* 461 */           this.val$path.transform(AffineTransform.getTranslateInstance(x, y));
/* 462 */           if (this.val$fill)
/* 463 */             g2.fill(this.val$path); 
/* 465 */           if (this.val$outline)
/* 466 */             g2.draw(this.val$path); 
/* 468 */           this.val$path.transform(AffineTransform.getTranslateInstance(-x, -y));
/*     */         }
/*     */         
/*     */         public int getIconWidth() {
/* 472 */           return this.val$width;
/*     */         }
/*     */         
/*     */         public int getIconHeight() {
/* 476 */           return this.val$height;
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 489 */     stream.defaultWriteObject();
/* 490 */     SerialUtilities.writeStroke(this.groupStroke, stream);
/* 491 */     SerialUtilities.writePaint(this.groupPaint, stream);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 504 */     stream.defaultReadObject();
/* 505 */     this.groupStroke = SerialUtilities.readStroke(stream);
/* 506 */     this.groupPaint = SerialUtilities.readPaint(stream);
/* 508 */     this.minIcon = getIcon(new Arc2D.Double(-4.0D, -4.0D, 8.0D, 8.0D, 0.0D, 360.0D, 0), (Paint)null, Color.black);
/* 511 */     this.maxIcon = getIcon(new Arc2D.Double(-4.0D, -4.0D, 8.0D, 8.0D, 0.0D, 360.0D, 0), (Paint)null, Color.black);
/* 514 */     this.objectIcon = getIcon(new Line2D.Double(-4.0D, 0.0D, 4.0D, 0.0D), false, true);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\category\MinMaxCategoryRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */