/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.chart.event.AxisChangeEvent;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.Plot;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.text.TextUtilities;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.ui.TextAnchor;
/*     */ 
/*     */ public class SubCategoryAxis extends CategoryAxis implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = -1279463299793228344L;
/*     */   
/*     */   private List subCategories;
/*     */   
/*  84 */   private Font subLabelFont = new Font("SansSerif", 0, 10);
/*     */   
/*  87 */   private transient Paint subLabelPaint = Color.black;
/*     */   
/*     */   public SubCategoryAxis(String label) {
/*  95 */     super(label);
/*  96 */     this.subCategories = new ArrayList();
/*     */   }
/*     */   
/*     */   public void addSubCategory(Comparable subCategory) {
/* 105 */     this.subCategories.add(subCategory);
/*     */   }
/*     */   
/*     */   public Font getSubLabelFont() {
/* 114 */     return this.subLabelFont;
/*     */   }
/*     */   
/*     */   public void setSubLabelFont(Font font) {
/* 124 */     if (font == null)
/* 125 */       throw new IllegalArgumentException("Null 'font' argument."); 
/* 127 */     this.subLabelFont = font;
/* 128 */     notifyListeners(new AxisChangeEvent(this));
/*     */   }
/*     */   
/*     */   public Paint getSubLabelPaint() {
/* 137 */     return this.subLabelPaint;
/*     */   }
/*     */   
/*     */   public void setSubLabelPaint(Paint paint) {
/* 147 */     if (paint == null)
/* 148 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/* 150 */     this.subLabelPaint = paint;
/* 151 */     notifyListeners(new AxisChangeEvent(this));
/*     */   }
/*     */   
/*     */   public AxisSpace reserveSpace(Graphics2D g2, Plot plot, Rectangle2D plotArea, RectangleEdge edge, AxisSpace space) {
/* 170 */     if (space == null)
/* 171 */       space = new AxisSpace(); 
/* 175 */     if (!isVisible())
/* 176 */       return space; 
/* 179 */     space = super.reserveSpace(g2, plot, plotArea, edge, space);
/* 180 */     double maxdim = getMaxDim(g2, edge);
/* 181 */     if (RectangleEdge.isTopOrBottom(edge)) {
/* 182 */       space.add(maxdim, edge);
/* 184 */     } else if (RectangleEdge.isLeftOrRight(edge)) {
/* 185 */       space.add(maxdim, edge);
/*     */     } 
/* 187 */     return space;
/*     */   }
/*     */   
/*     */   private double getMaxDim(Graphics2D g2, RectangleEdge edge) {
/* 200 */     double result = 0.0D;
/* 201 */     g2.setFont(this.subLabelFont);
/* 202 */     FontMetrics fm = g2.getFontMetrics();
/* 203 */     Iterator iterator = this.subCategories.iterator();
/* 204 */     while (iterator.hasNext()) {
/* 205 */       Comparable subcategory = iterator.next();
/* 206 */       String label = subcategory.toString();
/* 207 */       Rectangle2D bounds = TextUtilities.getTextBounds(label, g2, fm);
/* 208 */       double dim = 0.0D;
/* 209 */       if (RectangleEdge.isLeftOrRight(edge)) {
/* 210 */         dim = bounds.getWidth();
/*     */       } else {
/* 213 */         dim = bounds.getHeight();
/*     */       } 
/* 215 */       result = Math.max(result, dim);
/*     */     } 
/* 217 */     return result;
/*     */   }
/*     */   
/*     */   public AxisState draw(Graphics2D g2, double cursor, Rectangle2D plotArea, Rectangle2D dataArea, RectangleEdge edge, PlotRenderingInfo plotState) {
/* 244 */     if (!isVisible())
/* 245 */       return new AxisState(cursor); 
/* 248 */     if (isAxisLineVisible())
/* 249 */       drawAxisLine(g2, cursor, dataArea, edge); 
/* 253 */     AxisState state = new AxisState(cursor);
/* 254 */     state = drawSubCategoryLabels(g2, plotArea, dataArea, edge, state, plotState);
/* 257 */     state = drawCategoryLabels(g2, dataArea, edge, state, plotState);
/* 260 */     state = drawLabel(getLabel(), g2, plotArea, dataArea, edge, state);
/* 262 */     return state;
/*     */   }
/*     */   
/*     */   protected AxisState drawSubCategoryLabels(Graphics2D g2, Rectangle2D plotArea, Rectangle2D dataArea, RectangleEdge edge, AxisState state, PlotRenderingInfo plotState) {
/* 287 */     if (state == null)
/* 288 */       throw new IllegalArgumentException("Null 'state' argument."); 
/* 291 */     g2.setFont(this.subLabelFont);
/* 292 */     g2.setPaint(this.subLabelPaint);
/* 293 */     CategoryPlot plot = (CategoryPlot)getPlot();
/* 294 */     CategoryDataset dataset = plot.getDataset();
/* 295 */     int categoryCount = dataset.getColumnCount();
/* 297 */     double maxdim = getMaxDim(g2, edge);
/* 298 */     for (int categoryIndex = 0; categoryIndex < categoryCount; 
/* 299 */       categoryIndex++) {
/* 301 */       double x0 = 0.0D;
/* 302 */       double x1 = 0.0D;
/* 303 */       double y0 = 0.0D;
/* 304 */       double y1 = 0.0D;
/* 305 */       if (edge == RectangleEdge.TOP) {
/* 306 */         x0 = getCategoryStart(categoryIndex, categoryCount, dataArea, edge);
/* 309 */         x1 = getCategoryEnd(categoryIndex, categoryCount, dataArea, edge);
/* 312 */         y1 = state.getCursor();
/* 313 */         y0 = y1 - maxdim;
/* 315 */       } else if (edge == RectangleEdge.BOTTOM) {
/* 316 */         x0 = getCategoryStart(categoryIndex, categoryCount, dataArea, edge);
/* 319 */         x1 = getCategoryEnd(categoryIndex, categoryCount, dataArea, edge);
/* 322 */         y0 = state.getCursor();
/* 323 */         y1 = y0 + maxdim;
/* 325 */       } else if (edge == RectangleEdge.LEFT) {
/* 326 */         y0 = getCategoryStart(categoryIndex, categoryCount, dataArea, edge);
/* 329 */         y1 = getCategoryEnd(categoryIndex, categoryCount, dataArea, edge);
/* 332 */         x1 = state.getCursor();
/* 333 */         x0 = x1 - maxdim;
/* 335 */       } else if (edge == RectangleEdge.RIGHT) {
/* 336 */         y0 = getCategoryStart(categoryIndex, categoryCount, dataArea, edge);
/* 339 */         y1 = getCategoryEnd(categoryIndex, categoryCount, dataArea, edge);
/* 342 */         x0 = state.getCursor();
/* 343 */         x1 = x0 + maxdim;
/*     */       } 
/* 345 */       Rectangle2D area = new Rectangle2D.Double(x0, y0, x1 - x0, y1 - y0);
/* 348 */       int subCategoryCount = this.subCategories.size();
/* 349 */       float width = (float)((x1 - x0) / subCategoryCount);
/* 350 */       float height = (float)((y1 - y0) / subCategoryCount);
/* 351 */       float xx = 0.0F;
/* 352 */       float yy = 0.0F;
/* 353 */       for (int i = 0; i < subCategoryCount; i++) {
/* 354 */         if (RectangleEdge.isTopOrBottom(edge)) {
/* 355 */           xx = (float)(x0 + (i + 0.5D) * width);
/* 356 */           yy = (float)area.getCenterY();
/*     */         } else {
/* 359 */           xx = (float)area.getCenterX();
/* 360 */           yy = (float)(y0 + (i + 0.5D) * height);
/*     */         } 
/* 362 */         String label = this.subCategories.get(i).toString();
/* 363 */         TextUtilities.drawRotatedString(label, g2, xx, yy, TextAnchor.CENTER, 0.0D, TextAnchor.CENTER);
/*     */       } 
/*     */     } 
/* 370 */     if (edge.equals(RectangleEdge.TOP)) {
/* 371 */       double h = maxdim;
/* 372 */       state.cursorUp(h);
/* 374 */     } else if (edge.equals(RectangleEdge.BOTTOM)) {
/* 375 */       double h = maxdim;
/* 376 */       state.cursorDown(h);
/* 378 */     } else if (edge == RectangleEdge.LEFT) {
/* 379 */       double w = maxdim;
/* 380 */       state.cursorLeft(w);
/* 382 */     } else if (edge == RectangleEdge.RIGHT) {
/* 383 */       double w = maxdim;
/* 384 */       state.cursorRight(w);
/*     */     } 
/* 386 */     return state;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 397 */     if (obj == this)
/* 398 */       return true; 
/* 400 */     if (obj instanceof SubCategoryAxis && super.equals(obj)) {
/* 401 */       SubCategoryAxis axis = (SubCategoryAxis)obj;
/* 402 */       if (!this.subCategories.equals(axis.subCategories))
/* 403 */         return false; 
/* 405 */       if (!this.subLabelFont.equals(axis.subLabelFont))
/* 406 */         return false; 
/* 408 */       if (!this.subLabelPaint.equals(axis.subLabelPaint))
/* 409 */         return false; 
/* 411 */       return true;
/*     */     } 
/* 413 */     return false;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 424 */     stream.defaultWriteObject();
/* 425 */     SerialUtilities.writePaint(this.subLabelPaint, stream);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 438 */     stream.defaultReadObject();
/* 439 */     this.subLabelPaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\SubCategoryAxis.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */