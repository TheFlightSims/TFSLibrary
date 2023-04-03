/*     */ package org.jfree.chart.block;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ 
/*     */ public class BlockBorder implements Serializable {
/*     */   private static final long serialVersionUID = 4961579220410228283L;
/*     */   
/*  70 */   public static final BlockBorder NONE = new BlockBorder(RectangleInsets.ZERO_INSETS, Color.white);
/*     */   
/*     */   private RectangleInsets insets;
/*     */   
/*     */   private transient Paint paint;
/*     */   
/*     */   public BlockBorder() {
/*  83 */     this(Color.black);
/*     */   }
/*     */   
/*     */   public BlockBorder(Paint paint) {
/*  92 */     this(new RectangleInsets(1.0D, 1.0D, 1.0D, 1.0D), paint);
/*     */   }
/*     */   
/*     */   public BlockBorder(double top, double left, double bottom, double right) {
/* 104 */     this(new RectangleInsets(top, left, bottom, right), Color.black);
/*     */   }
/*     */   
/*     */   public BlockBorder(double top, double left, double bottom, double right, Paint paint) {
/* 118 */     this(new RectangleInsets(top, left, bottom, right), paint);
/*     */   }
/*     */   
/*     */   public BlockBorder(RectangleInsets insets, Paint paint) {
/* 128 */     if (insets == null)
/* 129 */       throw new IllegalArgumentException("Null 'insets' argument."); 
/* 131 */     if (paint == null)
/* 132 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/* 134 */     this.insets = insets;
/* 135 */     this.paint = paint;
/*     */   }
/*     */   
/*     */   public RectangleInsets getInsets() {
/* 144 */     return this.insets;
/*     */   }
/*     */   
/*     */   public Paint getPaint() {
/* 153 */     return this.paint;
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, Rectangle2D area) {
/* 165 */     double t = this.insets.calculateTopInset(area.getHeight());
/* 166 */     double b = this.insets.calculateBottomInset(area.getHeight());
/* 167 */     double l = this.insets.calculateLeftInset(area.getWidth());
/* 168 */     double r = this.insets.calculateRightInset(area.getWidth());
/* 169 */     double x = area.getX();
/* 170 */     double y = area.getY();
/* 171 */     double w = area.getWidth();
/* 172 */     double h = area.getHeight();
/* 173 */     g2.setPaint(this.paint);
/* 174 */     Rectangle2D rect = new Rectangle2D.Double();
/* 175 */     if (t > 0.0D) {
/* 176 */       rect.setRect(x, y, w, t);
/* 177 */       g2.fill(rect);
/*     */     } 
/* 179 */     if (b > 0.0D) {
/* 180 */       rect.setRect(x, y + h - b, w, b);
/* 181 */       g2.fill(rect);
/*     */     } 
/* 183 */     if (l > 0.0D) {
/* 184 */       rect.setRect(x, y, l, h);
/* 185 */       g2.fill(rect);
/*     */     } 
/* 187 */     if (r > 0.0D) {
/* 188 */       rect.setRect(x + w - r, y, r, h);
/* 189 */       g2.fill(rect);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 201 */     if (obj == this)
/* 202 */       return true; 
/* 204 */     if (!(obj instanceof BlockBorder))
/* 205 */       return false; 
/* 207 */     BlockBorder that = (BlockBorder)obj;
/* 208 */     if (!this.insets.equals(that.insets))
/* 209 */       return false; 
/* 211 */     if (!PaintUtilities.equal(this.paint, that.paint))
/* 212 */       return false; 
/* 214 */     return true;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 225 */     stream.defaultWriteObject();
/* 226 */     SerialUtilities.writePaint(this.paint, stream);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 239 */     stream.defaultReadObject();
/* 240 */     this.paint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\block\BlockBorder.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */