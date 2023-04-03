/*     */ package math.geom2d.domain;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import math.geom2d.AffineTransform2D;
/*     */ import math.geom2d.Box2D;
/*     */ import math.geom2d.GeometricObject2D;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.Shape2D;
/*     */ import math.geom2d.UnboundedShape2DException;
/*     */ import math.geom2d.polygon.LinearCurve2D;
/*     */ import math.geom2d.polygon.LinearRing2D;
/*     */ import math.geom2d.polygon.MultiPolygon2D;
/*     */ import math.geom2d.polygon.Polygon2D;
/*     */ 
/*     */ public class GenericDomain2D implements Domain2D {
/*     */   public static GenericDomain2D create(Boundary2D boundary) {
/*  56 */     return new GenericDomain2D(boundary);
/*     */   }
/*     */   
/*  66 */   protected Boundary2D boundary = null;
/*     */   
/*     */   public GenericDomain2D(Boundary2D boundary) {
/*  72 */     this.boundary = boundary;
/*     */   }
/*     */   
/*     */   public Polygon2D asPolygon(int n) {
/*  82 */     Collection<? extends Contour2D> contours = this.boundary.continuousCurves();
/*  83 */     ArrayList<LinearRing2D> rings = new ArrayList<LinearRing2D>(contours.size());
/*  84 */     for (Contour2D contour : contours) {
/*  86 */       if (!contour.isBounded())
/*  87 */         throw new UnboundedShape2DException(this); 
/*  90 */       if (!contour.isClosed())
/*  91 */         throw new IllegalArgumentException("Can not transform open curve to linear ring"); 
/*  93 */       LinearCurve2D poly = contour.asPolyline(n);
/*  94 */       assert poly instanceof LinearRing2D : "expected result as a linear ring";
/*  96 */       rings.add((LinearRing2D)poly);
/*     */     } 
/*  99 */     return (Polygon2D)new MultiPolygon2D(rings);
/*     */   }
/*     */   
/*     */   public Boundary2D boundary() {
/* 103 */     return this.boundary;
/*     */   }
/*     */   
/*     */   public Collection<? extends Contour2D> contours() {
/* 110 */     return this.boundary.continuousCurves();
/*     */   }
/*     */   
/*     */   public Domain2D complement() {
/* 114 */     return new GenericDomain2D(this.boundary.reverse());
/*     */   }
/*     */   
/*     */   public double distance(Point2D p) {
/* 121 */     return Math.max(this.boundary.signedDistance(p.x(), p.y()), 0.0D);
/*     */   }
/*     */   
/*     */   public double distance(double x, double y) {
/* 125 */     return Math.max(this.boundary.signedDistance(x, y), 0.0D);
/*     */   }
/*     */   
/*     */   public boolean isBounded() {
/* 135 */     if (!this.boundary.isBounded())
/* 136 */       return false; 
/* 140 */     Box2D box = this.boundary.boundingBox();
/* 141 */     Point2D point = new Point2D(box.getMinX(), box.getMinY());
/* 143 */     return !this.boundary.isInside(point);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 147 */     return (this.boundary.isEmpty() && !contains(0.0D, 0.0D));
/*     */   }
/*     */   
/*     */   public Domain2D clip(Box2D box) {
/* 151 */     return new GenericDomain2D(
/* 152 */         Boundaries2D.clipBoundary(boundary(), box));
/*     */   }
/*     */   
/*     */   public Box2D boundingBox() {
/* 160 */     if (isBounded())
/* 161 */       return this.boundary.boundingBox(); 
/* 162 */     return Box2D.INFINITE_BOX;
/*     */   }
/*     */   
/*     */   public GenericDomain2D transform(AffineTransform2D trans) {
/* 170 */     Boundary2D transformed = this.boundary.transform(trans);
/* 171 */     if (!trans.isDirect())
/* 172 */       transformed = transformed.reverse(); 
/* 173 */     return new GenericDomain2D(transformed);
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y) {
/* 177 */     return (this.boundary.signedDistance(x, y) <= 0.0D);
/*     */   }
/*     */   
/*     */   public boolean contains(Point2D p) {
/* 184 */     return contains(p.x(), p.y());
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2) {
/* 188 */     this.boundary.draw(g2);
/*     */   }
/*     */   
/*     */   public void fill(Graphics2D g2) {
/* 192 */     this.boundary.fill(g2);
/*     */   }
/*     */   
/*     */   public boolean almostEquals(GeometricObject2D obj, double eps) {
/* 203 */     if (this == obj)
/* 204 */       return true; 
/* 206 */     if (!(obj instanceof GenericDomain2D))
/* 207 */       return false; 
/* 208 */     GenericDomain2D domain = (GenericDomain2D)obj;
/* 210 */     if (!this.boundary.almostEquals((GeometricObject2D)domain.boundary, eps))
/* 210 */       return false; 
/* 211 */     return true;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 220 */     return "GenericDomain2D(boundary=" + this.boundary + ")";
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 228 */     if (this == obj)
/* 229 */       return true; 
/* 231 */     if (!(obj instanceof GenericDomain2D))
/* 232 */       return false; 
/* 233 */     GenericDomain2D domain = (GenericDomain2D)obj;
/* 235 */     if (!this.boundary.equals(domain.boundary))
/* 235 */       return false; 
/* 236 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\domain\GenericDomain2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */