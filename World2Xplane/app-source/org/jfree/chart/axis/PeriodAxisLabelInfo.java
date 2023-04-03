/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.text.DateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.TimeZone;
/*     */ import org.jfree.data.time.RegularTimePeriod;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ 
/*     */ public class PeriodAxisLabelInfo implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = 5710451740920277357L;
/*     */   
/*  82 */   public static final RectangleInsets DEFAULT_INSETS = new RectangleInsets(2.0D, 2.0D, 2.0D, 2.0D);
/*     */   
/*  86 */   public static final Font DEFAULT_FONT = new Font("SansSerif", 0, 10);
/*     */   
/*  90 */   public static final Paint DEFAULT_LABEL_PAINT = Color.black;
/*     */   
/*  93 */   public static final Stroke DEFAULT_DIVIDER_STROKE = new BasicStroke(0.5F);
/*     */   
/*  96 */   public static final Paint DEFAULT_DIVIDER_PAINT = Color.gray;
/*     */   
/*     */   private Class periodClass;
/*     */   
/*     */   private RectangleInsets padding;
/*     */   
/*     */   private DateFormat dateFormat;
/*     */   
/*     */   private Font labelFont;
/*     */   
/*     */   private transient Paint labelPaint;
/*     */   
/*     */   private boolean drawDividers;
/*     */   
/*     */   private transient Stroke dividerStroke;
/*     */   
/*     */   private transient Paint dividerPaint;
/*     */   
/*     */   public PeriodAxisLabelInfo(Class periodClass, DateFormat dateFormat) {
/* 130 */     this(periodClass, dateFormat, DEFAULT_INSETS, DEFAULT_FONT, DEFAULT_LABEL_PAINT, true, DEFAULT_DIVIDER_STROKE, DEFAULT_DIVIDER_PAINT);
/*     */   }
/*     */   
/*     */   public PeriodAxisLabelInfo(Class periodClass, DateFormat dateFormat, RectangleInsets padding, Font labelFont, Paint labelPaint, boolean drawDividers, Stroke dividerStroke, Paint dividerPaint) {
/* 158 */     if (periodClass == null)
/* 159 */       throw new IllegalArgumentException("Null 'periodClass' argument."); 
/* 161 */     if (dateFormat == null)
/* 162 */       throw new IllegalArgumentException("Null 'dateFormat' argument."); 
/* 164 */     if (padding == null)
/* 165 */       throw new IllegalArgumentException("Null 'padding' argument."); 
/* 167 */     if (labelFont == null)
/* 168 */       throw new IllegalArgumentException("Null 'labelFont' argument."); 
/* 170 */     if (labelPaint == null)
/* 171 */       throw new IllegalArgumentException("Null 'labelPaint' argument."); 
/* 173 */     if (dividerStroke == null)
/* 174 */       throw new IllegalArgumentException("Null 'dividerStroke' argument."); 
/* 176 */     if (dividerPaint == null)
/* 177 */       throw new IllegalArgumentException("Null 'dividerPaint' argument."); 
/* 179 */     this.periodClass = periodClass;
/* 180 */     this.dateFormat = dateFormat;
/* 181 */     this.padding = padding;
/* 182 */     this.labelFont = labelFont;
/* 183 */     this.labelPaint = labelPaint;
/* 184 */     this.drawDividers = drawDividers;
/* 185 */     this.dividerStroke = dividerStroke;
/* 186 */     this.dividerPaint = dividerPaint;
/*     */   }
/*     */   
/*     */   public Class getPeriodClass() {
/* 196 */     return this.periodClass;
/*     */   }
/*     */   
/*     */   public DateFormat getDateFormat() {
/* 205 */     return this.dateFormat;
/*     */   }
/*     */   
/*     */   public RectangleInsets getPadding() {
/* 214 */     return this.padding;
/*     */   }
/*     */   
/*     */   public Font getLabelFont() {
/* 223 */     return this.labelFont;
/*     */   }
/*     */   
/*     */   public Paint getLabelPaint() {
/* 232 */     return this.labelPaint;
/*     */   }
/*     */   
/*     */   public boolean getDrawDividers() {
/* 241 */     return this.drawDividers;
/*     */   }
/*     */   
/*     */   public Stroke getDividerStroke() {
/* 250 */     return this.dividerStroke;
/*     */   }
/*     */   
/*     */   public Paint getDividerPaint() {
/* 259 */     return this.dividerPaint;
/*     */   }
/*     */   
/*     */   public RegularTimePeriod createInstance(Date millisecond, TimeZone zone) {
/* 272 */     RegularTimePeriod result = null;
/*     */     try {
/* 274 */       Constructor c = this.periodClass.getDeclaredConstructor(new Class[] { Date.class, TimeZone.class });
/* 277 */       result = c.newInstance(new Object[] { millisecond, zone });
/* 281 */     } catch (Exception e) {}
/* 284 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 295 */     if (obj == this)
/* 296 */       return true; 
/* 298 */     if (obj instanceof PeriodAxisLabelInfo) {
/* 299 */       PeriodAxisLabelInfo info = (PeriodAxisLabelInfo)obj;
/* 300 */       if (!info.periodClass.equals(this.periodClass))
/* 301 */         return false; 
/* 303 */       if (!info.dateFormat.equals(this.dateFormat))
/* 304 */         return false; 
/* 306 */       if (!info.padding.equals(this.padding))
/* 307 */         return false; 
/* 309 */       if (!info.labelFont.equals(this.labelFont))
/* 310 */         return false; 
/* 312 */       if (!info.labelPaint.equals(this.labelPaint))
/* 313 */         return false; 
/* 315 */       if (info.drawDividers != this.drawDividers)
/* 316 */         return false; 
/* 318 */       if (!info.dividerStroke.equals(this.dividerStroke))
/* 319 */         return false; 
/* 321 */       if (!info.dividerPaint.equals(this.dividerPaint))
/* 322 */         return false; 
/* 324 */       return true;
/*     */     } 
/* 326 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 335 */     int result = 41;
/* 336 */     result = 37 * this.periodClass.hashCode();
/* 337 */     result = 37 * this.dateFormat.hashCode();
/* 338 */     return result;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 349 */     Object clone = super.clone();
/* 350 */     return clone;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 361 */     stream.defaultWriteObject();
/* 362 */     SerialUtilities.writePaint(this.labelPaint, stream);
/* 363 */     SerialUtilities.writeStroke(this.dividerStroke, stream);
/* 364 */     SerialUtilities.writePaint(this.dividerPaint, stream);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 377 */     stream.defaultReadObject();
/* 378 */     this.labelPaint = SerialUtilities.readPaint(stream);
/* 379 */     this.dividerStroke = SerialUtilities.readStroke(stream);
/* 380 */     this.dividerPaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\PeriodAxisLabelInfo.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */