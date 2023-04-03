/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.plot.CrosshairState;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.data.xy.WindDataset;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class WindItemRenderer extends AbstractXYItemRenderer implements XYItemRenderer, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = 8078914101916976844L;
/*     */   
/*     */   public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D plotArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass) {
/* 130 */     WindDataset windData = (WindDataset)dataset;
/* 132 */     Paint seriesPaint = getItemPaint(series, item);
/* 133 */     Stroke seriesStroke = getItemStroke(series, item);
/* 134 */     g2.setPaint(seriesPaint);
/* 135 */     g2.setStroke(seriesStroke);
/* 139 */     Number x = windData.getX(series, item);
/* 140 */     Number windDir = windData.getWindDirection(series, item);
/* 141 */     Number wforce = windData.getWindForce(series, item);
/* 142 */     double windForce = wforce.doubleValue();
/* 144 */     double wdirt = Math.toRadians(windDir.doubleValue() * -30.0D - 90.0D);
/* 148 */     RectangleEdge domainAxisLocation = plot.getDomainAxisEdge();
/* 149 */     RectangleEdge rangeAxisLocation = plot.getRangeAxisEdge();
/* 150 */     double ax1 = domainAxis.valueToJava2D(x.doubleValue(), plotArea, domainAxisLocation);
/* 152 */     double ay1 = rangeAxis.valueToJava2D(0.0D, plotArea, rangeAxisLocation);
/* 154 */     double rax2 = x.doubleValue() + windForce * Math.cos(wdirt) * 8000000.0D;
/* 155 */     double ray2 = windForce * Math.sin(wdirt);
/* 157 */     double ax2 = domainAxis.valueToJava2D(rax2, plotArea, domainAxisLocation);
/* 158 */     double ay2 = rangeAxis.valueToJava2D(ray2, plotArea, rangeAxisLocation);
/* 160 */     int diri = windDir.intValue();
/* 161 */     int forcei = wforce.intValue();
/* 162 */     String dirforce = diri + "-" + forcei;
/* 163 */     Line2D line = new Line2D.Double(ax1, ay1, ax2, ay2);
/* 165 */     g2.draw(line);
/* 166 */     g2.setPaint(Color.blue);
/* 167 */     g2.setFont(new Font("foo", 1, 9));
/* 169 */     g2.drawString(dirforce, (float)ax1, (float)ay1);
/* 171 */     g2.setPaint(seriesPaint);
/* 172 */     g2.setStroke(seriesStroke);
/* 177 */     double aldir = Math.toRadians(windDir.doubleValue() * -30.0D - 90.0D - 5.0D);
/* 179 */     double ralx2 = wforce.doubleValue() * Math.cos(aldir) * 8000000.0D * 0.8D + x.doubleValue();
/* 181 */     double raly2 = wforce.doubleValue() * Math.sin(aldir) * 0.8D;
/* 183 */     double alx2 = domainAxis.valueToJava2D(ralx2, plotArea, domainAxisLocation);
/* 184 */     double aly2 = rangeAxis.valueToJava2D(raly2, plotArea, rangeAxisLocation);
/* 186 */     line = new Line2D.Double(alx2, aly2, ax2, ay2);
/* 187 */     g2.draw(line);
/* 189 */     double ardir = Math.toRadians(windDir.doubleValue() * -30.0D - 90.0D + 5.0D);
/* 191 */     double rarx2 = wforce.doubleValue() * Math.cos(ardir) * 8000000.0D * 0.8D + x.doubleValue();
/* 193 */     double rary2 = wforce.doubleValue() * Math.sin(ardir) * 0.8D;
/* 195 */     double arx2 = domainAxis.valueToJava2D(rarx2, plotArea, domainAxisLocation);
/* 196 */     double ary2 = rangeAxis.valueToJava2D(rary2, plotArea, rangeAxisLocation);
/* 198 */     line = new Line2D.Double(arx2, ary2, ax2, ay2);
/* 199 */     g2.draw(line);
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 211 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\xy\WindItemRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */