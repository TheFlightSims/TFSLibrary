/*     */ package com.vividsolutions.jts.awt;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ 
/*     */ public interface PointShapeFactory {
/*     */   Shape createPoint(Point2D paramPoint2D);
/*     */   
/*     */   public static abstract class BasePointShapeFactory implements PointShapeFactory {
/*     */     public static final double DEFAULT_SIZE = 3.0D;
/*     */     
/*  68 */     protected double size = 3.0D;
/*     */     
/*     */     public BasePointShapeFactory() {}
/*     */     
/*     */     public BasePointShapeFactory(double size) {
/*  84 */       this.size = size;
/*     */     }
/*     */     
/*     */     public abstract Shape createPoint(Point2D param1Point2D);
/*     */   }
/*     */   
/*     */   public static class Point extends BasePointShapeFactory {
/*     */     public Point() {}
/*     */     
/*     */     public Point(double size) {
/* 113 */       super(size);
/*     */     }
/*     */     
/*     */     public Shape createPoint(Point2D point) {
/* 124 */       Line2D.Double pointMarker = new Line2D.Double(point.getX(), point.getY(), point.getX(), point.getY());
/* 130 */       return pointMarker;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Square extends BasePointShapeFactory {
/*     */     public Square() {}
/*     */     
/*     */     public Square(double size) {
/* 150 */       super(size);
/*     */     }
/*     */     
/*     */     public Shape createPoint(Point2D point) {
/* 161 */       Rectangle2D.Double pointMarker = new Rectangle2D.Double(0.0D, 0.0D, this.size, this.size);
/* 167 */       pointMarker.x = point.getX() - this.size / 2.0D;
/* 168 */       pointMarker.y = point.getY() - this.size / 2.0D;
/* 170 */       return pointMarker;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Star extends BasePointShapeFactory {
/*     */     public Star() {}
/*     */     
/*     */     public Star(double size) {
/* 190 */       super(size);
/*     */     }
/*     */     
/*     */     public Shape createPoint(Point2D point) {
/* 201 */       GeneralPath path = new GeneralPath();
/* 202 */       path.moveTo((float)point.getX(), (float)(point.getY() - this.size / 2.0D));
/* 203 */       path.lineTo((float)(point.getX() + this.size * 1.0D / 8.0D), (float)(point.getY() - this.size * 1.0D / 8.0D));
/* 204 */       path.lineTo((float)(point.getX() + this.size / 2.0D), (float)(point.getY() - this.size * 1.0D / 8.0D));
/* 205 */       path.lineTo((float)(point.getX() + this.size * 2.0D / 8.0D), (float)(point.getY() + this.size * 1.0D / 8.0D));
/* 206 */       path.lineTo((float)(point.getX() + this.size * 3.0D / 8.0D), (float)(point.getY() + this.size / 2.0D));
/* 207 */       path.lineTo((float)point.getX(), (float)(point.getY() + this.size * 2.0D / 8.0D));
/* 208 */       path.lineTo((float)(point.getX() - this.size * 3.0D / 8.0D), (float)(point.getY() + this.size / 2.0D));
/* 209 */       path.lineTo((float)(point.getX() - this.size * 2.0D / 8.0D), (float)(point.getY() + this.size * 1.0D / 8.0D));
/* 210 */       path.lineTo((float)(point.getX() - this.size / 2.0D), (float)(point.getY() - this.size * 1.0D / 8.0D));
/* 211 */       path.lineTo((float)(point.getX() - this.size * 1.0D / 8.0D), (float)(point.getY() - this.size * 1.0D / 8.0D));
/* 212 */       path.closePath();
/* 213 */       return path;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Triangle extends BasePointShapeFactory {
/*     */     public Triangle() {}
/*     */     
/*     */     public Triangle(double size) {
/* 233 */       super(size);
/*     */     }
/*     */     
/*     */     public Shape createPoint(Point2D point) {
/* 245 */       GeneralPath path = new GeneralPath();
/* 246 */       path.moveTo((float)point.getX(), (float)(point.getY() - this.size / 2.0D));
/* 247 */       path.lineTo((float)(point.getX() + this.size / 2.0D), (float)(point.getY() + this.size / 2.0D));
/* 248 */       path.lineTo((float)(point.getX() - this.size / 2.0D), (float)(point.getY() + this.size / 2.0D));
/* 249 */       path.lineTo((float)point.getX(), (float)(point.getY() - this.size / 2.0D));
/* 251 */       return path;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Circle extends BasePointShapeFactory {
/*     */     public Circle() {}
/*     */     
/*     */     public Circle(double size) {
/* 271 */       super(size);
/*     */     }
/*     */     
/*     */     public Shape createPoint(Point2D point) {
/* 282 */       Ellipse2D.Double pointMarker = new Ellipse2D.Double(0.0D, 0.0D, this.size, this.size);
/* 288 */       pointMarker.x = point.getX() - this.size / 2.0D;
/* 289 */       pointMarker.y = point.getY() - this.size / 2.0D;
/* 291 */       return pointMarker;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Cross extends BasePointShapeFactory {
/*     */     public Cross() {}
/*     */     
/*     */     public Cross(double size) {
/* 311 */       super(size);
/*     */     }
/*     */     
/*     */     public Shape createPoint(Point2D point) {
/* 323 */       float x1 = (float)(point.getX() - this.size / 2.0D);
/* 324 */       float x2 = (float)(point.getX() - this.size / 4.0D);
/* 325 */       float x3 = (float)(point.getX() + this.size / 4.0D);
/* 326 */       float x4 = (float)(point.getX() + this.size / 2.0D);
/* 328 */       float y1 = (float)(point.getY() - this.size / 2.0D);
/* 329 */       float y2 = (float)(point.getY() - this.size / 4.0D);
/* 330 */       float y3 = (float)(point.getY() + this.size / 4.0D);
/* 331 */       float y4 = (float)(point.getY() + this.size / 2.0D);
/* 333 */       GeneralPath path = new GeneralPath();
/* 334 */       path.moveTo(x2, y1);
/* 335 */       path.lineTo(x3, y1);
/* 336 */       path.lineTo(x3, y2);
/* 337 */       path.lineTo(x4, y2);
/* 338 */       path.lineTo(x4, y3);
/* 339 */       path.lineTo(x3, y3);
/* 340 */       path.lineTo(x3, y4);
/* 341 */       path.lineTo(x2, y4);
/* 342 */       path.lineTo(x2, y3);
/* 343 */       path.lineTo(x1, y3);
/* 344 */       path.lineTo(x1, y2);
/* 345 */       path.lineTo(x2, y2);
/* 346 */       path.lineTo(x2, y1);
/* 348 */       return path;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class X extends BasePointShapeFactory {
/*     */     public X() {}
/*     */     
/*     */     public X(double size) {
/* 368 */       super(size);
/*     */     }
/*     */     
/*     */     public Shape createPoint(Point2D point) {
/* 379 */       GeneralPath path = new GeneralPath();
/* 380 */       path.moveTo((float)point.getX(), (float)(point.getY() - this.size * 1.0D / 8.0D));
/* 381 */       path.lineTo((float)(point.getX() + this.size * 2.0D / 8.0D), (float)(point.getY() - this.size / 2.0D));
/* 382 */       path.lineTo((float)(point.getX() + this.size / 2.0D), (float)(point.getY() - this.size / 2.0D));
/* 383 */       path.lineTo((float)(point.getX() + this.size * 1.0D / 8.0D), (float)point.getY());
/* 384 */       path.lineTo((float)(point.getX() + this.size / 2.0D), (float)(point.getY() + this.size / 2.0D));
/* 385 */       path.lineTo((float)(point.getX() + this.size * 2.0D / 8.0D), (float)(point.getY() + this.size / 2.0D));
/* 386 */       path.lineTo((float)point.getX(), (float)(point.getY() + this.size * 1.0D / 8.0D));
/* 387 */       path.lineTo((float)(point.getX() - this.size * 2.0D / 8.0D), (float)(point.getY() + this.size / 2.0D));
/* 388 */       path.lineTo((float)(point.getX() - this.size / 2.0D), (float)(point.getY() + this.size / 2.0D));
/* 389 */       path.lineTo((float)(point.getX() - this.size * 1.0D / 8.0D), (float)point.getY());
/* 390 */       path.lineTo((float)(point.getX() - this.size / 2.0D), (float)(point.getY() - this.size / 2.0D));
/* 391 */       path.lineTo((float)(point.getX() - this.size * 2.0D / 8.0D), (float)(point.getY() - this.size / 2.0D));
/* 392 */       path.closePath();
/* 393 */       return path;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\awt\PointShapeFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */