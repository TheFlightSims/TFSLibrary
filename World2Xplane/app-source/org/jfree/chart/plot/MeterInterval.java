/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ 
/*     */ public class MeterInterval implements Serializable {
/*     */   private static final long serialVersionUID = 1530982090622488257L;
/*     */   
/*     */   private String label;
/*     */   
/*     */   private Range range;
/*     */   
/*     */   private transient Paint outlinePaint;
/*     */   
/*     */   private transient Stroke outlineStroke;
/*     */   
/*     */   private transient Paint backgroundPaint;
/*     */   
/*     */   public MeterInterval(String label, Range range) {
/*  91 */     this(label, range, Color.yellow, new BasicStroke(2.0F), null);
/*     */   }
/*     */   
/*     */   public MeterInterval(String label, Range range, Paint outlinePaint, Stroke outlineStroke, Paint backgroundPaint) {
/* 106 */     if (label == null)
/* 107 */       throw new IllegalArgumentException("Null 'label' argument."); 
/* 109 */     if (range == null)
/* 110 */       throw new IllegalArgumentException("Null 'range' argument."); 
/* 112 */     this.label = label;
/* 113 */     this.range = range;
/* 114 */     this.outlinePaint = outlinePaint;
/* 115 */     this.outlineStroke = outlineStroke;
/* 116 */     this.backgroundPaint = backgroundPaint;
/*     */   }
/*     */   
/*     */   public String getLabel() {
/* 125 */     return this.label;
/*     */   }
/*     */   
/*     */   public Range getRange() {
/* 134 */     return this.range;
/*     */   }
/*     */   
/*     */   public Paint getBackgroundPaint() {
/* 144 */     return this.backgroundPaint;
/*     */   }
/*     */   
/*     */   public Paint getOutlinePaint() {
/* 153 */     return this.outlinePaint;
/*     */   }
/*     */   
/*     */   public Stroke getOutlineStroke() {
/* 162 */     return this.outlineStroke;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 173 */     if (obj == this)
/* 174 */       return true; 
/* 176 */     if (!(obj instanceof MeterInterval))
/* 177 */       return false; 
/* 179 */     MeterInterval that = (MeterInterval)obj;
/* 180 */     if (!this.label.equals(that.label))
/* 181 */       return false; 
/* 183 */     if (!this.range.equals(that.range))
/* 184 */       return false; 
/* 186 */     if (!PaintUtilities.equal(this.outlinePaint, that.outlinePaint))
/* 187 */       return false; 
/* 189 */     if (!ObjectUtilities.equal(this.outlineStroke, that.outlineStroke))
/* 190 */       return false; 
/* 192 */     if (!PaintUtilities.equal(this.backgroundPaint, that.backgroundPaint))
/* 193 */       return false; 
/* 195 */     return true;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 206 */     stream.defaultWriteObject();
/* 207 */     SerialUtilities.writePaint(this.outlinePaint, stream);
/* 208 */     SerialUtilities.writeStroke(this.outlineStroke, stream);
/* 209 */     SerialUtilities.writePaint(this.backgroundPaint, stream);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 222 */     stream.defaultReadObject();
/* 223 */     this.outlinePaint = SerialUtilities.readPaint(stream);
/* 224 */     this.outlineStroke = SerialUtilities.readStroke(stream);
/* 225 */     this.backgroundPaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\MeterInterval.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */