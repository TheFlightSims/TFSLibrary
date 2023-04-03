/*     */ package org.jfree.text;
/*     */ 
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.ui.Size2D;
/*     */ import org.jfree.ui.TextAnchor;
/*     */ import org.jfree.util.Log;
/*     */ import org.jfree.util.LogContext;
/*     */ 
/*     */ public class TextLine implements Serializable {
/*     */   private static final long serialVersionUID = 7100085690160465444L;
/*     */   
/*     */   private List fragments;
/*     */   
/*  79 */   protected static final LogContext logger = Log.createContext(TextLine.class);
/*     */   
/*     */   public TextLine() {
/*  86 */     this.fragments = new ArrayList();
/*     */   }
/*     */   
/*     */   public TextLine(String text) {
/*  95 */     this(text, TextFragment.DEFAULT_FONT);
/*     */   }
/*     */   
/*     */   public TextLine(String text, Font font) {
/* 105 */     this.fragments = new ArrayList();
/* 106 */     TextFragment fragment = new TextFragment(text, font);
/* 107 */     this.fragments.add(fragment);
/*     */   }
/*     */   
/*     */   public TextLine(String text, Font font, Paint paint) {
/* 118 */     if (text == null)
/* 119 */       throw new IllegalArgumentException("Null 'text' argument."); 
/* 121 */     if (font == null)
/* 122 */       throw new IllegalArgumentException("Null 'font' argument."); 
/* 124 */     if (paint == null)
/* 125 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/* 127 */     this.fragments = new ArrayList();
/* 128 */     TextFragment fragment = new TextFragment(text, font, paint);
/* 129 */     this.fragments.add(fragment);
/*     */   }
/*     */   
/*     */   public void addFragment(TextFragment fragment) {
/* 138 */     this.fragments.add(fragment);
/*     */   }
/*     */   
/*     */   public void removeFragment(TextFragment fragment) {
/* 147 */     this.fragments.remove(fragment);
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, float anchorX, float anchorY, TextAnchor anchor, float rotateX, float rotateY, double angle) {
/* 168 */     float x = anchorX;
/* 169 */     float yOffset = calculateBaselineOffset(g2, anchor);
/* 170 */     Iterator iterator = this.fragments.iterator();
/* 171 */     while (iterator.hasNext()) {
/* 172 */       TextFragment fragment = iterator.next();
/* 173 */       Size2D d = fragment.calculateDimensions(g2);
/* 174 */       fragment.draw(g2, x, anchorY + yOffset, TextAnchor.BASELINE_LEFT, rotateX, rotateY, angle);
/* 178 */       x += (float)d.getWidth();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Size2D calculateDimensions(Graphics2D g2) {
/* 191 */     double width = 0.0D;
/* 192 */     double height = 0.0D;
/* 193 */     Iterator iterator = this.fragments.iterator();
/* 194 */     while (iterator.hasNext()) {
/* 195 */       TextFragment fragment = iterator.next();
/* 196 */       Size2D dimension = fragment.calculateDimensions(g2);
/* 197 */       width += dimension.getWidth();
/* 198 */       height = Math.max(height, dimension.getHeight());
/* 199 */       if (logger.isDebugEnabled())
/* 200 */         logger.debug("width = " + width + ", height = " + height); 
/*     */     } 
/* 203 */     return new Size2D(width, height);
/*     */   }
/*     */   
/*     */   public TextFragment getFirstTextFragment() {
/* 212 */     TextFragment result = null;
/* 213 */     if (this.fragments.size() > 0)
/* 214 */       result = this.fragments.get(0); 
/* 216 */     return result;
/*     */   }
/*     */   
/*     */   public TextFragment getLastTextFragment() {
/* 225 */     TextFragment result = null;
/* 226 */     if (this.fragments.size() > 0)
/* 227 */       result = this.fragments.get(this.fragments.size() - 1); 
/* 230 */     return result;
/*     */   }
/*     */   
/*     */   private float calculateBaselineOffset(Graphics2D g2, TextAnchor anchor) {
/* 244 */     float result = 0.0F;
/* 245 */     Iterator iterator = this.fragments.iterator();
/* 246 */     while (iterator.hasNext()) {
/* 247 */       TextFragment fragment = iterator.next();
/* 248 */       result = Math.max(result, fragment.calculateBaselineOffset(g2, anchor));
/*     */     } 
/* 251 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 262 */     if (obj == null)
/* 263 */       return false; 
/* 265 */     if (obj == this)
/* 266 */       return true; 
/* 268 */     if (obj instanceof TextLine) {
/* 269 */       TextLine line = (TextLine)obj;
/* 270 */       return this.fragments.equals(line.fragments);
/*     */     } 
/* 272 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 281 */     return (this.fragments != null) ? this.fragments.hashCode() : 0;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\text\TextLine.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */