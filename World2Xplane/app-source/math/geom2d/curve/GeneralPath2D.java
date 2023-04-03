/*     */ package math.geom2d.curve;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.PathIterator;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import math.geom2d.Point2D;
/*     */ 
/*     */ public final class GeneralPath2D implements Shape, Cloneable {
/*     */   GeneralPath path;
/*     */   
/*     */   public static final int WIND_EVEN_ODD = 0;
/*     */   
/*     */   public static final int WIND_NON_ZERO = 1;
/*     */   
/*     */   public GeneralPath2D() {
/*  63 */     this.path = new GeneralPath();
/*     */   }
/*     */   
/*     */   public GeneralPath2D(int rule) {
/*  76 */     this.path = new GeneralPath(rule);
/*     */   }
/*     */   
/*     */   public GeneralPath2D(int rule, int initialCapacity) {
/*  93 */     this.path = new GeneralPath(rule, initialCapacity);
/*     */   }
/*     */   
/*     */   public GeneralPath2D(Shape s) {
/* 104 */     this.path = new GeneralPath(s);
/*     */   }
/*     */   
/*     */   public synchronized void moveTo(double x, double y) {
/* 114 */     this.path.moveTo((float)x, (float)y);
/*     */   }
/*     */   
/*     */   public synchronized void moveTo(Point2D p) {
/* 123 */     this.path.moveTo((float)p.x(), (float)p.y());
/*     */   }
/*     */   
/*     */   public synchronized void lineTo(double x, double y) {
/* 134 */     this.path.lineTo((float)x, (float)y);
/*     */   }
/*     */   
/*     */   public synchronized void lineTo(Point2D p) {
/* 144 */     this.path.lineTo((float)p.x(), (float)p.y());
/*     */   }
/*     */   
/*     */   public synchronized void quadTo(double x1, double y1, double x2, double y2) {
/* 159 */     this.path.quadTo((float)x1, (float)y1, (float)x2, (float)y2);
/*     */   }
/*     */   
/*     */   public synchronized void quadTo(Point2D p1, Point2D p2) {
/* 172 */     this.path.quadTo((float)p1.x(), (float)p1.y(), (float)p2.x(), 
/* 173 */         (float)p2.y());
/*     */   }
/*     */   
/*     */   public synchronized void curveTo(double x1, double y1, double x2, double y2, double x3, double y3) {
/* 191 */     this.path.curveTo((float)x1, (float)y1, (float)x2, (float)y2, 
/* 192 */         (float)x3, (float)y3);
/*     */   }
/*     */   
/*     */   public synchronized void curveTo(Point2D p1, Point2D p2, Point2D p3) {
/* 207 */     this.path.curveTo((float)p1.x(), (float)p1.y(), (float)p2.x(), 
/* 208 */         (float)p2.y(), (float)p3.x(), (float)p3.y());
/*     */   }
/*     */   
/*     */   public synchronized void closePath() {
/* 217 */     this.path.closePath();
/*     */   }
/*     */   
/*     */   public void append(Shape s, boolean connect) {
/* 239 */     this.path.append(s, connect);
/*     */   }
/*     */   
/*     */   public void append(PathIterator pi, boolean connect) {
/* 262 */     this.path.append(pi, connect);
/*     */   }
/*     */   
/*     */   public synchronized int getWindingRule() {
/* 273 */     return this.path.getWindingRule();
/*     */   }
/*     */   
/*     */   public void setWindingRule(int rule) {
/* 287 */     this.path.setWindingRule(rule);
/*     */   }
/*     */   
/*     */   public synchronized Point2D getCurrentPoint() {
/* 299 */     return new Point2D(this.path.getCurrentPoint());
/*     */   }
/*     */   
/*     */   public synchronized void reset() {
/* 307 */     this.path.reset();
/*     */   }
/*     */   
/*     */   public void transform(AffineTransform at) {
/* 318 */     this.path.transform(at);
/*     */   }
/*     */   
/*     */   public synchronized Shape createTransformedShape(AffineTransform at) {
/* 330 */     return this.path.createTransformedShape(at);
/*     */   }
/*     */   
/*     */   public Rectangle getBounds() {
/* 339 */     return this.path.getBounds();
/*     */   }
/*     */   
/*     */   public synchronized Rectangle2D getBounds2D() {
/* 348 */     return this.path.getBounds2D();
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y) {
/* 361 */     return this.path.contains(x, y);
/*     */   }
/*     */   
/*     */   public boolean contains(Point2D p) {
/* 374 */     return this.path.contains(new Point2D.Double(p.getX(), p.getY()));
/*     */   }
/*     */   
/*     */   public boolean contains(Point2D p) {
/* 387 */     return this.path.contains(new Point2D.Double(p.x(), p.y()));
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y, double w, double h) {
/* 402 */     return this.path.contains(x, y, w, h);
/*     */   }
/*     */   
/*     */   public boolean contains(Rectangle2D r) {
/* 415 */     return this.path.contains(r);
/*     */   }
/*     */   
/*     */   public boolean intersects(double x, double y, double w, double h) {
/* 431 */     return this.path.intersects(x, y, w, h);
/*     */   }
/*     */   
/*     */   public boolean intersects(Rectangle2D r) {
/* 444 */     return this.path.intersects(r);
/*     */   }
/*     */   
/*     */   public PathIterator getPathIterator(AffineTransform at) {
/* 462 */     return this.path.getPathIterator(at);
/*     */   }
/*     */   
/*     */   public PathIterator getPathIterator(AffineTransform at, double flatness) {
/* 482 */     return this.path.getPathIterator(at, flatness);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 488 */     if (!(obj instanceof GeneralPath2D))
/* 489 */       return false; 
/* 490 */     GeneralPath2D that = (GeneralPath2D)obj;
/* 492 */     return this.path.equals(that.path);
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 505 */     return this.path.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\curve\GeneralPath2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */