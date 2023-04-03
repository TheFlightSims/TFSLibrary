/*     */ package org.jfree.chart;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Point2D;
/*     */ 
/*     */ public class DrawableLegendItem {
/*     */   private LegendItem item;
/*     */   
/*     */   private double x;
/*     */   
/*     */   private double y;
/*     */   
/*     */   private double width;
/*     */   
/*     */   private double height;
/*     */   
/*     */   private Shape marker;
/*     */   
/*     */   private Line2D line;
/*     */   
/*     */   private Point2D labelPosition;
/*     */   
/*     */   public DrawableLegendItem(LegendItem item) {
/*  95 */     this.item = item;
/*     */   }
/*     */   
/*     */   public LegendItem getItem() {
/* 104 */     return this.item;
/*     */   }
/*     */   
/*     */   public double getX() {
/* 113 */     return this.x;
/*     */   }
/*     */   
/*     */   public void setX(double x) {
/* 122 */     this.x = x;
/*     */   }
/*     */   
/*     */   public double getY() {
/* 131 */     return this.y;
/*     */   }
/*     */   
/*     */   public void setY(double y) {
/* 140 */     this.y = y;
/*     */   }
/*     */   
/*     */   public double getWidth() {
/* 149 */     return this.width;
/*     */   }
/*     */   
/*     */   public double getHeight() {
/* 158 */     return this.height;
/*     */   }
/*     */   
/*     */   public double getMaxX() {
/* 169 */     return getX() + getWidth();
/*     */   }
/*     */   
/*     */   public double getMaxY() {
/* 180 */     return getY() + getHeight();
/*     */   }
/*     */   
/*     */   public Shape getMarker() {
/* 189 */     return this.marker;
/*     */   }
/*     */   
/*     */   public void setMarker(Shape marker) {
/* 199 */     this.marker = marker;
/*     */   }
/*     */   
/*     */   public void setLine(Line2D l) {
/* 208 */     this.line = l;
/*     */   }
/*     */   
/*     */   public Line2D getLine() {
/* 217 */     return this.line;
/*     */   }
/*     */   
/*     */   public Point2D getLabelPosition() {
/* 226 */     return this.labelPosition;
/*     */   }
/*     */   
/*     */   public void setLabelPosition(Point2D position) {
/* 235 */     this.labelPosition = position;
/*     */   }
/*     */   
/*     */   public void setBounds(double x, double y, double width, double height) {
/* 247 */     this.x = x;
/* 248 */     this.y = y;
/* 249 */     this.width = width;
/* 250 */     this.height = height;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\DrawableLegendItem.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */