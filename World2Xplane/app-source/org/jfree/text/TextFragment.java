/*     */ package org.jfree.text;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.font.LineMetrics;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.Size2D;
/*     */ import org.jfree.ui.TextAnchor;
/*     */ import org.jfree.util.Log;
/*     */ import org.jfree.util.LogContext;
/*     */ 
/*     */ public class TextFragment implements Serializable {
/*     */   private static final long serialVersionUID = 4465945952903143262L;
/*     */   
/*  83 */   public static final Font DEFAULT_FONT = new Font("Serif", 0, 12);
/*     */   
/*  86 */   public static final Paint DEFAULT_PAINT = Color.black;
/*     */   
/*     */   private String text;
/*     */   
/*     */   private Font font;
/*     */   
/*     */   private Paint paint;
/*     */   
/*     */   private float baselineOffset;
/*     */   
/* 104 */   protected static final LogContext logger = Log.createContext(TextFragment.class);
/*     */   
/*     */   public TextFragment(String text) {
/* 113 */     this(text, DEFAULT_FONT, DEFAULT_PAINT);
/*     */   }
/*     */   
/*     */   public TextFragment(String text, Font font) {
/* 123 */     this(text, font, DEFAULT_PAINT);
/*     */   }
/*     */   
/*     */   public TextFragment(String text, Font font, Paint paint) {
/* 134 */     this(text, font, paint, 0.0F);
/*     */   }
/*     */   
/*     */   public TextFragment(String text, Font font, Paint paint, float baselineOffset) {
/* 147 */     if (text == null)
/* 148 */       throw new IllegalArgumentException("Null 'text' argument."); 
/* 150 */     if (font == null)
/* 151 */       throw new IllegalArgumentException("Null 'font' argument."); 
/* 153 */     if (paint == null)
/* 154 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/* 156 */     this.text = text;
/* 157 */     this.font = font;
/* 158 */     this.paint = paint;
/* 159 */     this.baselineOffset = baselineOffset;
/*     */   }
/*     */   
/*     */   public String getText() {
/* 168 */     return this.text;
/*     */   }
/*     */   
/*     */   public Font getFont() {
/* 177 */     return this.font;
/*     */   }
/*     */   
/*     */   public Paint getPaint() {
/* 186 */     return this.paint;
/*     */   }
/*     */   
/*     */   public float getBaselineOffset() {
/* 190 */     return this.baselineOffset;
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, float anchorX, float anchorY, TextAnchor anchor, float rotateX, float rotateY, double angle) {
/* 210 */     g2.setFont(this.font);
/* 211 */     g2.setPaint(this.paint);
/* 212 */     TextUtilities.drawRotatedString(this.text, g2, anchorX, anchorY + this.baselineOffset, anchor, angle, rotateX, rotateY);
/*     */   }
/*     */   
/*     */   public Size2D calculateDimensions(Graphics2D g2) {
/* 227 */     FontMetrics fm = g2.getFontMetrics(this.font);
/* 228 */     Rectangle2D bounds = TextUtilities.getTextBounds(this.text, g2, fm);
/* 230 */     Size2D result = new Size2D(bounds.getWidth(), bounds.getHeight());
/* 231 */     return result;
/*     */   }
/*     */   
/*     */   public float calculateBaselineOffset(Graphics2D g2, TextAnchor anchor) {
/* 245 */     float result = 0.0F;
/* 246 */     FontMetrics fm = g2.getFontMetrics(this.font);
/* 247 */     LineMetrics lm = fm.getLineMetrics("ABCxyz", g2);
/* 248 */     if (anchor == TextAnchor.TOP_LEFT || anchor == TextAnchor.TOP_CENTER || anchor == TextAnchor.TOP_RIGHT) {
/* 250 */       result = lm.getAscent();
/* 252 */     } else if (anchor == TextAnchor.BOTTOM_LEFT || anchor == TextAnchor.BOTTOM_CENTER || anchor == TextAnchor.BOTTOM_RIGHT) {
/* 255 */       result = -lm.getDescent() - lm.getLeading();
/*     */     } 
/* 257 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 268 */     if (obj == null)
/* 269 */       return false; 
/* 271 */     if (obj == this)
/* 272 */       return true; 
/* 274 */     if (obj instanceof TextFragment) {
/* 275 */       TextFragment tf = (TextFragment)obj;
/* 276 */       if (!this.text.equals(tf.text))
/* 277 */         return false; 
/* 279 */       if (!this.font.equals(tf.font))
/* 280 */         return false; 
/* 282 */       if (!this.paint.equals(tf.paint))
/* 283 */         return false; 
/* 285 */       return true;
/*     */     } 
/* 287 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 297 */     int result = (this.text != null) ? this.text.hashCode() : 0;
/* 298 */     result = 29 * result + ((this.font != null) ? this.font.hashCode() : 0);
/* 299 */     result = 29 * result + ((this.paint != null) ? this.paint.hashCode() : 0);
/* 300 */     return result;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 312 */     stream.defaultWriteObject();
/* 313 */     SerialUtilities.writePaint(this.paint, stream);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 326 */     stream.defaultReadObject();
/* 327 */     this.paint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\text\TextFragment.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */