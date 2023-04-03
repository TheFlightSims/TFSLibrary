/*     */ package org.jfree.chart.block;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import org.jfree.chart.entity.ChartEntity;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.entity.StandardEntityCollection;
/*     */ import org.jfree.text.TextBlock;
/*     */ import org.jfree.text.TextBlockAnchor;
/*     */ import org.jfree.text.TextUtilities;
/*     */ import org.jfree.ui.Size2D;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class LabelBlock extends AbstractBlock implements Block, PublicCloneable {
/*     */   private String text;
/*     */   
/*     */   private TextBlock label;
/*     */   
/*     */   private Font font;
/*     */   
/*     */   private String toolTipText;
/*     */   
/*     */   private String urlText;
/*     */   
/*  93 */   public static final Paint DEFAULT_PAINT = Color.black;
/*     */   
/*     */   private Paint paint;
/*     */   
/*     */   public LabelBlock(String label) {
/* 104 */     this(label, new Font("SansSerif", 0, 10), DEFAULT_PAINT);
/*     */   }
/*     */   
/*     */   public LabelBlock(String text, Font font) {
/* 114 */     this(text, font, DEFAULT_PAINT);
/*     */   }
/*     */   
/*     */   public LabelBlock(String text, Font font, Paint paint) {
/* 125 */     this.text = text;
/* 126 */     this.paint = paint;
/* 127 */     this.label = TextUtilities.createTextBlock(text, font, this.paint);
/* 128 */     this.font = font;
/* 129 */     this.toolTipText = null;
/* 130 */     this.urlText = null;
/*     */   }
/*     */   
/*     */   public Font getFont() {
/* 139 */     return this.font;
/*     */   }
/*     */   
/*     */   public void setFont(Font font) {
/* 148 */     if (font == null)
/* 149 */       throw new IllegalArgumentException("Null 'font' argument."); 
/* 151 */     this.font = font;
/* 152 */     this.label = TextUtilities.createTextBlock(this.text, font, this.paint);
/*     */   }
/*     */   
/*     */   public Paint getPaint() {
/* 161 */     return this.paint;
/*     */   }
/*     */   
/*     */   public void setPaint(Paint paint) {
/* 170 */     if (paint == null)
/* 171 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/* 173 */     this.paint = paint;
/* 174 */     this.label = TextUtilities.createTextBlock(this.text, this.font, this.paint);
/*     */   }
/*     */   
/*     */   public String getToolTipText() {
/* 183 */     return this.toolTipText;
/*     */   }
/*     */   
/*     */   public void setToolTipText(String text) {
/* 192 */     this.toolTipText = text;
/*     */   }
/*     */   
/*     */   public String getURLText() {
/* 201 */     return this.urlText;
/*     */   }
/*     */   
/*     */   public void setURLText(String text) {
/* 210 */     this.urlText = text;
/*     */   }
/*     */   
/*     */   public Size2D arrange(Graphics2D g2, RectangleConstraint constraint) {
/* 223 */     RectangleConstraint contentConstraint = toContentConstraint(constraint);
/* 224 */     g2.setFont(this.font);
/* 225 */     Size2D s = this.label.calculateDimensions(g2);
/* 226 */     return new Size2D(calculateTotalWidth(s.getWidth()), calculateTotalHeight(s.getHeight()));
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, Rectangle2D area) {
/* 239 */     draw(g2, area, (Object)null);
/*     */   }
/*     */   
/*     */   public Object draw(Graphics2D g2, Rectangle2D area, Object params) {
/* 252 */     area = trimMargin(area);
/* 253 */     drawBorder(g2, area);
/* 254 */     area = trimBorder(area);
/* 255 */     area = trimPadding(area);
/* 258 */     EntityBlockParams ebp = null;
/* 259 */     StandardEntityCollection sec = null;
/* 260 */     Shape entityArea = null;
/* 261 */     if (params instanceof EntityBlockParams) {
/* 262 */       ebp = (EntityBlockParams)params;
/* 263 */       if (ebp.getGenerateEntities()) {
/* 264 */         sec = new StandardEntityCollection();
/* 266 */         entityArea = g2.getTransform().createTransformedShape(area);
/*     */       } 
/*     */     } 
/* 269 */     g2.setPaint(this.paint);
/* 270 */     g2.setFont(this.font);
/* 271 */     this.label.draw(g2, (float)area.getX(), (float)area.getY(), TextBlockAnchor.TOP_LEFT);
/* 275 */     BlockResult result = null;
/* 276 */     if (ebp != null && sec != null && (
/* 277 */       this.toolTipText != null || this.urlText != null)) {
/* 278 */       ChartEntity entity = new ChartEntity(entityArea, this.toolTipText, this.urlText);
/* 281 */       sec.add(entity);
/* 282 */       result = new BlockResult();
/* 283 */       result.setEntityCollection((EntityCollection)sec);
/*     */     } 
/* 286 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 296 */     if (!(obj instanceof LabelBlock))
/* 297 */       return false; 
/* 299 */     LabelBlock that = (LabelBlock)obj;
/* 300 */     if (!this.text.equals(that.text))
/* 301 */       return false; 
/* 303 */     if (!this.font.equals(that.font))
/* 304 */       return false; 
/* 306 */     if (!PaintUtilities.equal(this.paint, that.paint))
/* 307 */       return false; 
/* 309 */     if (!ObjectUtilities.equal(this.toolTipText, that.toolTipText))
/* 310 */       return false; 
/* 312 */     if (!ObjectUtilities.equal(this.urlText, that.urlText))
/* 313 */       return false; 
/* 315 */     if (!super.equals(obj))
/* 316 */       return false; 
/* 318 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 329 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\block\LabelBlock.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */