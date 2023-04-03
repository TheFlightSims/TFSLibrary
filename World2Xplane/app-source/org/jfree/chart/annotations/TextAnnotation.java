/*     */ package org.jfree.chart.annotations;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Paint;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.TextAnchor;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ 
/*     */ public class TextAnnotation implements Serializable {
/*     */   private static final long serialVersionUID = 7008912287533127432L;
/*     */   
/*  76 */   public static final Font DEFAULT_FONT = new Font("SansSerif", 0, 10);
/*     */   
/*  80 */   public static final Paint DEFAULT_PAINT = Color.black;
/*     */   
/*  83 */   public static final TextAnchor DEFAULT_TEXT_ANCHOR = TextAnchor.CENTER;
/*     */   
/*  86 */   public static final TextAnchor DEFAULT_ROTATION_ANCHOR = TextAnchor.CENTER;
/*     */   
/*     */   public static final double DEFAULT_ROTATION_ANGLE = 0.0D;
/*     */   
/*     */   private String text;
/*     */   
/*     */   private Font font;
/*     */   
/*     */   private transient Paint paint;
/*     */   
/*     */   private TextAnchor textAnchor;
/*     */   
/*     */   private TextAnchor rotationAnchor;
/*     */   
/*     */   private double rotationAngle;
/*     */   
/*     */   protected TextAnnotation(String text) {
/* 115 */     if (text == null)
/* 116 */       throw new IllegalArgumentException("Null 'text' argument."); 
/* 118 */     this.text = text;
/* 119 */     this.font = DEFAULT_FONT;
/* 120 */     this.paint = DEFAULT_PAINT;
/* 121 */     this.textAnchor = DEFAULT_TEXT_ANCHOR;
/* 122 */     this.rotationAnchor = DEFAULT_ROTATION_ANCHOR;
/* 123 */     this.rotationAngle = 0.0D;
/*     */   }
/*     */   
/*     */   public String getText() {
/* 132 */     return this.text;
/*     */   }
/*     */   
/*     */   public void setText(String text) {
/* 141 */     this.text = text;
/*     */   }
/*     */   
/*     */   public Font getFont() {
/* 150 */     return this.font;
/*     */   }
/*     */   
/*     */   public void setFont(Font font) {
/* 159 */     this.font = font;
/*     */   }
/*     */   
/*     */   public Paint getPaint() {
/* 168 */     return this.paint;
/*     */   }
/*     */   
/*     */   public void setPaint(Paint paint) {
/* 177 */     this.paint = paint;
/*     */   }
/*     */   
/*     */   public TextAnchor getTextAnchor() {
/* 186 */     return this.textAnchor;
/*     */   }
/*     */   
/*     */   public void setTextAnchor(TextAnchor anchor) {
/* 196 */     this.textAnchor = anchor;
/*     */   }
/*     */   
/*     */   public TextAnchor getRotationAnchor() {
/* 205 */     return this.rotationAnchor;
/*     */   }
/*     */   
/*     */   public void setRotationAnchor(TextAnchor anchor) {
/* 214 */     this.rotationAnchor = anchor;
/*     */   }
/*     */   
/*     */   public double getRotationAngle() {
/* 223 */     return this.rotationAngle;
/*     */   }
/*     */   
/*     */   public void setRotationAngle(double angle) {
/* 234 */     this.rotationAngle = angle;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 245 */     if (obj == this)
/* 246 */       return true; 
/* 249 */     if (!(obj instanceof TextAnnotation))
/* 250 */       return false; 
/* 252 */     TextAnnotation that = (TextAnnotation)obj;
/* 253 */     if (!ObjectUtilities.equal(this.text, that.getText()))
/* 254 */       return false; 
/* 256 */     if (!ObjectUtilities.equal(this.font, that.getFont()))
/* 257 */       return false; 
/* 259 */     if (!PaintUtilities.equal(this.paint, that.getPaint()))
/* 260 */       return false; 
/* 262 */     if (!ObjectUtilities.equal(this.textAnchor, that.getTextAnchor()))
/* 263 */       return false; 
/* 265 */     if (!ObjectUtilities.equal(this.rotationAnchor, that.getRotationAnchor()))
/* 268 */       return false; 
/* 270 */     if (this.rotationAngle != that.getRotationAngle())
/* 271 */       return false; 
/* 275 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 286 */     return this.text.hashCode();
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 297 */     stream.defaultWriteObject();
/* 298 */     SerialUtilities.writePaint(this.paint, stream);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 311 */     stream.defaultReadObject();
/* 312 */     this.paint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\annotations\TextAnnotation.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */