/*     */ package org.jfree.chart.renderer;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.jfree.chart.LegendItem;
/*     */ import org.jfree.chart.LegendItemCollection;
/*     */ import org.jfree.chart.plot.DrawingSupplier;
/*     */ import org.jfree.chart.plot.WaferMapPlot;
/*     */ import org.jfree.data.general.WaferMapDataset;
/*     */ 
/*     */ public class WaferMapRenderer extends AbstractRenderer {
/*     */   public WaferMapRenderer() {
/*  96 */     this((Integer)null, (Integer)null);
/*     */   }
/*     */   
/*     */   public WaferMapRenderer(int paintLimit, int paintIndexMethod) {
/* 106 */     this(new Integer(paintLimit), new Integer(paintIndexMethod));
/*     */   }
/*     */   
/* 118 */   private Map paintIndex = new HashMap();
/*     */   
/*     */   private WaferMapPlot plot;
/*     */   
/*     */   private int paintLimit;
/*     */   
/*     */   private static final int DEFAULT_PAINT_LIMIT = 35;
/*     */   
/*     */   public static final int POSITION_INDEX = 0;
/*     */   
/*     */   public static final int VALUE_INDEX = 1;
/*     */   
/*     */   private int paintIndexMethod;
/*     */   
/*     */   public WaferMapRenderer(Integer paintLimit, Integer paintIndexMethod) {
/* 120 */     if (paintLimit == null) {
/* 121 */       this.paintLimit = 35;
/*     */     } else {
/* 124 */       this.paintLimit = paintLimit.intValue();
/*     */     } 
/* 127 */     this.paintIndexMethod = 1;
/* 128 */     if (paintIndexMethod != null && 
/* 129 */       isMethodValid(paintIndexMethod.intValue()))
/* 130 */       this.paintIndexMethod = paintIndexMethod.intValue(); 
/*     */   }
/*     */   
/*     */   private boolean isMethodValid(int method) {
/* 143 */     switch (method) {
/*     */       case 0:
/* 144 */         return true;
/*     */       case 1:
/* 145 */         return true;
/*     */     } 
/* 146 */     return false;
/*     */   }
/*     */   
/*     */   public DrawingSupplier getDrawingSupplier() {
/* 156 */     DrawingSupplier result = null;
/* 157 */     WaferMapPlot p = getPlot();
/* 158 */     if (p != null)
/* 159 */       result = p.getDrawingSupplier(); 
/* 161 */     return result;
/*     */   }
/*     */   
/*     */   public WaferMapPlot getPlot() {
/* 170 */     return this.plot;
/*     */   }
/*     */   
/*     */   public void setPlot(WaferMapPlot plot) {
/* 179 */     this.plot = plot;
/* 180 */     makePaintIndex();
/*     */   }
/*     */   
/*     */   public Paint getChipColor(Number value) {
/* 191 */     return getSeriesPaint(getPaintIndex(value));
/*     */   }
/*     */   
/*     */   private int getPaintIndex(Number value) {
/* 202 */     return ((Integer)this.paintIndex.get(value)).intValue();
/*     */   }
/*     */   
/*     */   private void makePaintIndex() {
/* 210 */     if (this.plot == null)
/*     */       return; 
/* 213 */     WaferMapDataset data = this.plot.getDataset();
/* 214 */     Number dataMin = data.getMinValue();
/* 215 */     Number dataMax = data.getMaxValue();
/* 216 */     Set uniqueValues = data.getUniqueValues();
/* 217 */     if (uniqueValues.size() <= this.paintLimit) {
/* 218 */       int count = 0;
/* 219 */       for (Iterator i = uniqueValues.iterator(); i.hasNext();)
/* 220 */         this.paintIndex.put(i.next(), new Integer(count++)); 
/*     */     } else {
/* 226 */       switch (this.paintIndexMethod) {
/*     */         case 0:
/* 228 */           makePositionIndex(uniqueValues);
/*     */           break;
/*     */         case 1:
/* 231 */           makeValueIndex(dataMax, dataMin, uniqueValues);
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void makePositionIndex(Set uniqueValues) {
/* 246 */     int valuesPerColor = (int)Math.ceil(uniqueValues.size() / this.paintLimit);
/* 249 */     int count = 0;
/* 250 */     int paint = 0;
/* 251 */     for (Iterator i = uniqueValues.iterator(); i.hasNext(); ) {
/* 252 */       this.paintIndex.put(i.next(), new Integer(paint));
/* 253 */       if (++count % valuesPerColor == 0)
/* 254 */         paint++; 
/* 256 */       if (paint > this.paintLimit)
/* 257 */         paint = this.paintLimit; 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void makeValueIndex(Number max, Number min, Set uniqueValues) {
/* 271 */     double valueRange = max.doubleValue() - min.doubleValue();
/* 272 */     double valueStep = valueRange / this.paintLimit;
/* 273 */     int paint = 0;
/* 274 */     double cutPoint = min.doubleValue() + valueStep;
/* 275 */     for (Iterator i = uniqueValues.iterator(); i.hasNext(); ) {
/* 276 */       Number value = i.next();
/* 277 */       while (value.doubleValue() > cutPoint) {
/* 278 */         cutPoint += valueStep;
/* 279 */         paint++;
/* 280 */         if (paint > this.paintLimit)
/* 281 */           paint = this.paintLimit; 
/*     */       } 
/* 284 */       this.paintIndex.put(value, new Integer(paint));
/*     */     } 
/*     */   }
/*     */   
/*     */   public LegendItemCollection getLegendCollection() {
/* 295 */     LegendItemCollection result = new LegendItemCollection();
/* 296 */     if (this.paintIndex != null && this.paintIndex.size() > 0)
/* 297 */       if (this.paintIndex.size() <= this.paintLimit) {
/* 298 */         Iterator i = this.paintIndex.entrySet().iterator();
/* 299 */         while (i.hasNext()) {
/* 301 */           Map.Entry entry = i.next();
/* 302 */           String label = entry.getKey().toString();
/* 303 */           String description = label;
/* 304 */           Shape shape = new Rectangle2D.Double(1.0D, 1.0D, 1.0D, 1.0D);
/* 305 */           Paint paint = getSeriesPaint(((Integer)entry.getValue()).intValue());
/* 308 */           Paint outlinePaint = Color.black;
/* 309 */           Stroke outlineStroke = DEFAULT_STROKE;
/* 311 */           result.add(new LegendItem(label, description, null, null, shape, paint, outlineStroke, outlinePaint));
/*     */         } 
/*     */       } else {
/* 318 */         Set unique = new HashSet();
/* 319 */         Iterator i = this.paintIndex.entrySet().iterator();
/* 320 */         while (i.hasNext()) {
/* 321 */           Map.Entry entry = i.next();
/* 322 */           if (unique.add(entry.getValue())) {
/* 323 */             String label = getMinPaintValue((Integer)entry.getValue()).toString() + " - " + getMaxPaintValue((Integer)entry.getValue()).toString();
/* 327 */             String description = label;
/* 328 */             Shape shape = new Rectangle2D.Double(1.0D, 1.0D, 1.0D, 1.0D);
/* 329 */             Paint paint = getSeriesPaint(((Integer)entry.getValue()).intValue());
/* 332 */             Paint outlinePaint = Color.black;
/* 333 */             Stroke outlineStroke = DEFAULT_STROKE;
/* 335 */             result.add(new LegendItem(label, description, null, null, shape, paint, outlineStroke, outlinePaint));
/*     */           } 
/*     */         } 
/*     */       }  
/* 342 */     return result;
/*     */   }
/*     */   
/*     */   private Number getMinPaintValue(Integer index) {
/* 354 */     double minValue = Double.POSITIVE_INFINITY;
/* 355 */     for (Iterator i = this.paintIndex.entrySet().iterator(); i.hasNext(); ) {
/* 356 */       Map.Entry entry = i.next();
/* 357 */       if (((Integer)entry.getValue()).equals(index) && (
/* 358 */         (Number)entry.getKey()).doubleValue() < minValue)
/* 359 */         minValue = ((Number)entry.getKey()).doubleValue(); 
/*     */     } 
/* 363 */     return new Double(minValue);
/*     */   }
/*     */   
/*     */   private Number getMaxPaintValue(Integer index) {
/* 375 */     double maxValue = Double.NEGATIVE_INFINITY;
/* 376 */     for (Iterator i = this.paintIndex.entrySet().iterator(); i.hasNext(); ) {
/* 377 */       Map.Entry entry = i.next();
/* 378 */       if (((Integer)entry.getValue()).equals(index) && (
/* 379 */         (Number)entry.getKey()).doubleValue() > maxValue)
/* 380 */         maxValue = ((Number)entry.getKey()).doubleValue(); 
/*     */     } 
/* 384 */     return new Double(maxValue);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\WaferMapRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */