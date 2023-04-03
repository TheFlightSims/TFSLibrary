/*     */ package org.jfree.chart.renderer;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ 
/*     */ public class Outlier implements Comparable {
/*     */   private Point2D point;
/*     */   
/*     */   private double radius;
/*     */   
/*     */   public Outlier(double xCoord, double yCoord, double radius) {
/*  75 */     this.point = new Point2D.Double(xCoord - radius, yCoord - radius);
/*  76 */     this.radius = radius;
/*     */   }
/*     */   
/*     */   public Point2D getPoint() {
/*  86 */     return this.point;
/*     */   }
/*     */   
/*     */   public void setPoint(Point2D point) {
/*  96 */     this.point = point;
/*     */   }
/*     */   
/*     */   public double getX() {
/* 106 */     return getPoint().getX();
/*     */   }
/*     */   
/*     */   public double getY() {
/* 116 */     return getPoint().getY();
/*     */   }
/*     */   
/*     */   public double getRadius() {
/* 125 */     return this.radius;
/*     */   }
/*     */   
/*     */   public void setRadius(double radius) {
/* 134 */     this.radius = radius;
/*     */   }
/*     */   
/*     */   public int compareTo(Object o) {
/* 147 */     Outlier outlier = (Outlier)o;
/* 148 */     Point2D p1 = getPoint();
/* 149 */     Point2D p2 = outlier.getPoint();
/* 150 */     if (p1.equals(p2))
/* 151 */       return 0; 
/* 153 */     if (p1.getX() < p2.getX() || p1.getY() < p2.getY())
/* 154 */       return -1; 
/* 157 */     return 1;
/*     */   }
/*     */   
/*     */   public boolean overlaps(Outlier other) {
/* 172 */     return (other.getX() >= getX() - this.radius * 1.1D && other.getX() <= getX() + this.radius * 1.1D && other.getY() >= getY() - this.radius * 1.1D && other.getY() <= getY() + this.radius * 1.1D);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 184 */     return "{" + getX() + "," + getY() + "}";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\Outlier.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */