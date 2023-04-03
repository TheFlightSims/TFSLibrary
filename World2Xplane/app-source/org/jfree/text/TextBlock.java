/*     */ package org.jfree.text;
/*     */ 
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.ui.HorizontalAlignment;
/*     */ import org.jfree.ui.Size2D;
/*     */ import org.jfree.ui.TextAnchor;
/*     */ import org.jfree.util.Log;
/*     */ import org.jfree.util.LogContext;
/*     */ import org.jfree.util.ShapeUtilities;
/*     */ 
/*     */ public class TextBlock implements Serializable {
/*  89 */   protected static final LogContext logger = Log.createContext(TextBlock.class);
/*     */   
/*  96 */   private List lines = new ArrayList();
/*     */   
/*  97 */   private HorizontalAlignment lineAlignment = HorizontalAlignment.CENTER;
/*     */   
/*     */   private static final long serialVersionUID = -4333175719424385526L;
/*     */   
/*     */   public HorizontalAlignment getLineAlignment() {
/* 106 */     return this.lineAlignment;
/*     */   }
/*     */   
/*     */   public void setLineAlignment(HorizontalAlignment alignment) {
/* 115 */     if (alignment == null)
/* 116 */       throw new IllegalArgumentException("Null 'alignment' argument."); 
/* 118 */     this.lineAlignment = alignment;
/*     */   }
/*     */   
/*     */   public void addLine(String text, Font font, Paint paint) {
/* 129 */     addLine(new TextLine(text, font, paint));
/*     */   }
/*     */   
/*     */   public void addLine(TextLine line) {
/* 138 */     this.lines.add(line);
/*     */   }
/*     */   
/*     */   public TextLine getLastLine() {
/* 147 */     TextLine last = null;
/* 148 */     int index = this.lines.size() - 1;
/* 149 */     if (index >= 0)
/* 150 */       last = this.lines.get(index); 
/* 152 */     return last;
/*     */   }
/*     */   
/*     */   public List getLines() {
/* 161 */     return Collections.unmodifiableList(this.lines);
/*     */   }
/*     */   
/*     */   public Size2D calculateDimensions(Graphics2D g2) {
/* 172 */     double width = 0.0D;
/* 173 */     double height = 0.0D;
/* 174 */     Iterator iterator = this.lines.iterator();
/* 175 */     while (iterator.hasNext()) {
/* 176 */       TextLine line = iterator.next();
/* 177 */       Size2D dimension = line.calculateDimensions(g2);
/* 178 */       width = Math.max(width, dimension.getWidth());
/* 179 */       height += dimension.getHeight();
/*     */     } 
/* 181 */     if (logger.isDebugEnabled())
/* 182 */       logger.debug("width = " + width + ", height = " + height); 
/* 184 */     return new Size2D(width, height);
/*     */   }
/*     */   
/*     */   public Shape calculateBounds(Graphics2D g2, float anchorX, float anchorY, TextBlockAnchor anchor, float rotateX, float rotateY, double angle) {
/* 206 */     Size2D d = calculateDimensions(g2);
/* 207 */     float[] offsets = calculateOffsets(anchor, d.getWidth(), d.getHeight());
/* 210 */     Rectangle2D bounds = new Rectangle2D.Double((anchorX + offsets[0]), (anchorY + offsets[1]), d.getWidth(), d.getHeight());
/* 214 */     Shape rotatedBounds = ShapeUtilities.rotateShape(bounds, angle, rotateX, rotateY);
/* 217 */     return rotatedBounds;
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, float x, float y, TextBlockAnchor anchor) {
/* 231 */     draw(g2, x, y, anchor, 0.0F, 0.0F, 0.0D);
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, float anchorX, float anchorY, TextBlockAnchor anchor, float rotateX, float rotateY, double angle) {
/* 253 */     Size2D d = calculateDimensions(g2);
/* 254 */     float[] offsets = calculateOffsets(anchor, d.getWidth(), d.getHeight());
/* 256 */     Iterator iterator = this.lines.iterator();
/* 257 */     float yCursor = 0.0F;
/* 258 */     while (iterator.hasNext()) {
/* 259 */       TextLine line = iterator.next();
/* 260 */       Size2D dimension = line.calculateDimensions(g2);
/* 261 */       float lineOffset = 0.0F;
/* 262 */       if (this.lineAlignment == HorizontalAlignment.CENTER) {
/* 263 */         lineOffset = (float)(d.getWidth() - dimension.getWidth()) / 2.0F;
/* 266 */       } else if (this.lineAlignment == HorizontalAlignment.RIGHT) {
/* 267 */         lineOffset = (float)(d.getWidth() - dimension.getWidth());
/*     */       } 
/* 269 */       line.draw(g2, anchorX + offsets[0] + lineOffset, anchorY + offsets[1] + yCursor, TextAnchor.TOP_LEFT, rotateX, rotateY, angle);
/* 273 */       yCursor += (float)dimension.getHeight();
/*     */     } 
/*     */   }
/*     */   
/*     */   private float[] calculateOffsets(TextBlockAnchor anchor, double width, double height) {
/* 291 */     float[] result = new float[2];
/* 292 */     float xAdj = 0.0F;
/* 293 */     float yAdj = 0.0F;
/* 295 */     if (anchor == TextBlockAnchor.TOP_CENTER || anchor == TextBlockAnchor.CENTER || anchor == TextBlockAnchor.BOTTOM_CENTER) {
/* 299 */       xAdj = (float)-width / 2.0F;
/* 302 */     } else if (anchor == TextBlockAnchor.TOP_RIGHT || anchor == TextBlockAnchor.CENTER_RIGHT || anchor == TextBlockAnchor.BOTTOM_RIGHT) {
/* 306 */       xAdj = (float)-width;
/*     */     } 
/* 310 */     if (anchor == TextBlockAnchor.TOP_LEFT || anchor == TextBlockAnchor.TOP_CENTER || anchor == TextBlockAnchor.TOP_RIGHT) {
/* 314 */       yAdj = 0.0F;
/* 317 */     } else if (anchor == TextBlockAnchor.CENTER_LEFT || anchor == TextBlockAnchor.CENTER || anchor == TextBlockAnchor.CENTER_RIGHT) {
/* 321 */       yAdj = (float)-height / 2.0F;
/* 324 */     } else if (anchor == TextBlockAnchor.BOTTOM_LEFT || anchor == TextBlockAnchor.BOTTOM_CENTER || anchor == TextBlockAnchor.BOTTOM_RIGHT) {
/* 328 */       yAdj = (float)-height;
/*     */     } 
/* 331 */     result[0] = xAdj;
/* 332 */     result[1] = yAdj;
/* 333 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 344 */     if (obj == this)
/* 345 */       return true; 
/* 347 */     if (obj instanceof TextBlock) {
/* 348 */       TextBlock block = (TextBlock)obj;
/* 349 */       return this.lines.equals(block.lines);
/*     */     } 
/* 351 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 360 */     return (this.lines != null) ? this.lines.hashCode() : 0;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\text\TextBlock.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */