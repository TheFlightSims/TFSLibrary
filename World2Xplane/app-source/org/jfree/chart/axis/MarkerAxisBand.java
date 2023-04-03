/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Color;
/*     */ import java.awt.Composite;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.font.LineMetrics;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.chart.plot.IntervalMarker;
/*     */ import org.jfree.text.TextUtilities;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ public class MarkerAxisBand implements Serializable {
/*     */   private static final long serialVersionUID = -1729482413886398919L;
/*     */   
/*     */   private NumberAxis axis;
/*     */   
/*     */   private double topOuterGap;
/*     */   
/*     */   private double topInnerGap;
/*     */   
/*     */   private double bottomOuterGap;
/*     */   
/*     */   private double bottomInnerGap;
/*     */   
/*     */   private Font font;
/*     */   
/*     */   private List markers;
/*     */   
/*     */   public MarkerAxisBand(NumberAxis axis, double topOuterGap, double topInnerGap, double bottomOuterGap, double bottomInnerGap, Font font) {
/* 112 */     this.axis = axis;
/* 113 */     this.topOuterGap = topOuterGap;
/* 114 */     this.topInnerGap = topInnerGap;
/* 115 */     this.bottomOuterGap = bottomOuterGap;
/* 116 */     this.bottomInnerGap = bottomInnerGap;
/* 117 */     this.font = font;
/* 118 */     this.markers = new ArrayList();
/*     */   }
/*     */   
/*     */   public void addMarker(IntervalMarker marker) {
/* 127 */     this.markers.add(marker);
/*     */   }
/*     */   
/*     */   public double getHeight(Graphics2D g2) {
/* 139 */     double result = 0.0D;
/* 140 */     if (this.markers.size() > 0) {
/* 141 */       LineMetrics metrics = this.font.getLineMetrics("123g", g2.getFontRenderContext());
/* 144 */       result = this.topOuterGap + this.topInnerGap + metrics.getHeight() + this.bottomInnerGap + this.bottomOuterGap;
/*     */     } 
/* 147 */     return result;
/*     */   }
/*     */   
/*     */   private void drawStringInRect(Graphics2D g2, Rectangle2D bounds, Font font, String text) {
/* 162 */     g2.setFont(font);
/* 163 */     FontMetrics fm = g2.getFontMetrics(font);
/* 164 */     Rectangle2D r = TextUtilities.getTextBounds(text, g2, fm);
/* 165 */     double x = bounds.getX();
/* 166 */     if (r.getWidth() < bounds.getWidth())
/* 167 */       x += (bounds.getWidth() - r.getWidth()) / 2.0D; 
/* 169 */     LineMetrics metrics = font.getLineMetrics(text, g2.getFontRenderContext());
/* 172 */     g2.drawString(text, (float)x, (float)(bounds.getMaxY() - this.bottomInnerGap - metrics.getDescent()));
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, Rectangle2D plotArea, Rectangle2D dataArea, double x, double y) {
/* 190 */     double h = getHeight(g2);
/* 191 */     Iterator iterator = this.markers.iterator();
/* 192 */     while (iterator.hasNext()) {
/* 193 */       IntervalMarker marker = iterator.next();
/* 194 */       double start = Math.max(marker.getStartValue(), this.axis.getRange().getLowerBound());
/* 197 */       double end = Math.min(marker.getEndValue(), this.axis.getRange().getUpperBound());
/* 200 */       double s = this.axis.valueToJava2D(start, dataArea, RectangleEdge.BOTTOM);
/* 203 */       double e = this.axis.valueToJava2D(end, dataArea, RectangleEdge.BOTTOM);
/* 206 */       Rectangle2D r = new Rectangle2D.Double(s, y + this.topOuterGap, e - s, h - this.topOuterGap - this.bottomOuterGap);
/* 211 */       Composite originalComposite = g2.getComposite();
/* 212 */       g2.setComposite(AlphaComposite.getInstance(3, marker.getAlpha()));
/* 215 */       g2.setPaint(marker.getPaint());
/* 216 */       g2.fill(r);
/* 217 */       g2.setPaint(marker.getOutlinePaint());
/* 218 */       g2.draw(r);
/* 219 */       g2.setComposite(originalComposite);
/* 221 */       g2.setPaint(Color.black);
/* 222 */       drawStringInRect(g2, r, this.font, marker.getLabel());
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 236 */     if (obj == this)
/* 237 */       return true; 
/* 239 */     if (!(obj instanceof MarkerAxisBand))
/* 240 */       return false; 
/* 242 */     MarkerAxisBand that = (MarkerAxisBand)obj;
/* 243 */     if (this.topOuterGap != that.topOuterGap)
/* 244 */       return false; 
/* 246 */     if (this.topInnerGap != that.topInnerGap)
/* 247 */       return false; 
/* 249 */     if (this.bottomInnerGap != that.bottomInnerGap)
/* 250 */       return false; 
/* 252 */     if (this.bottomOuterGap != that.bottomOuterGap)
/* 253 */       return false; 
/* 255 */     if (!ObjectUtilities.equal(this.font, that.font))
/* 256 */       return false; 
/* 258 */     if (!ObjectUtilities.equal(this.markers, that.markers))
/* 259 */       return false; 
/* 261 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 270 */     int result = 37;
/* 271 */     result = 19 * result + this.font.hashCode();
/* 272 */     result = 19 * result + this.markers.hashCode();
/* 273 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\MarkerAxisBand.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */