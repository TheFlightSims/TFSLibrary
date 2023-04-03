/*     */ package org.geotools.geometry.jts;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.PathIterator;
/*     */ 
/*     */ public final class GeomCollectionIterator extends AbstractLiteIterator {
/*     */   private AffineTransform at;
/*     */   
/*     */   private GeometryCollection gc;
/*     */   
/*     */   private int currentGeom;
/*     */   
/*     */   private PathIterator currentIterator;
/*     */   
/*     */   private boolean done = false;
/*     */   
/*     */   private boolean generalize = false;
/*     */   
/*  65 */   private double maxDistance = 1.0D;
/*     */   
/*     */   public GeomCollectionIterator() {}
/*     */   
/*     */   public void init(GeometryCollection gc, AffineTransform at, boolean generalize, double maxDistance) {
/*  77 */     this.gc = gc;
/*  78 */     this.at = (at == null) ? new AffineTransform() : at;
/*  79 */     this.generalize = generalize;
/*  80 */     this.maxDistance = maxDistance;
/*  81 */     this.currentGeom = 0;
/*  82 */     this.done = false;
/*  83 */     this.currentIterator = gc.isEmpty() ? EmptyIterator.INSTANCE : getIterator(gc.getGeometryN(0));
/*     */   }
/*     */   
/*     */   public GeomCollectionIterator(GeometryCollection gc, AffineTransform at, boolean generalize, double maxDistance) {
/*  98 */     init(gc, at, generalize, maxDistance);
/*     */   }
/*     */   
/*     */   public void setMaxDistance(double distance) {
/* 108 */     this.maxDistance = distance;
/*     */   }
/*     */   
/*     */   public double getMaxDistance() {
/* 118 */     return this.maxDistance;
/*     */   }
/*     */   
/*     */   private AbstractLiteIterator getIterator(Geometry g) {
/* 129 */     AbstractLiteIterator pi = null;
/* 131 */     if (g.isEmpty())
/* 132 */       return EmptyIterator.INSTANCE; 
/* 133 */     if (g instanceof Polygon) {
/* 134 */       Polygon p = (Polygon)g;
/* 135 */       pi = new PolygonIterator(p, this.at, this.generalize, this.maxDistance);
/* 136 */     } else if (g instanceof GeometryCollection) {
/* 137 */       GeometryCollection gc = (GeometryCollection)g;
/* 138 */       pi = new GeomCollectionIterator(gc, this.at, this.generalize, this.maxDistance);
/* 139 */     } else if (g instanceof LineString || g instanceof com.vividsolutions.jts.geom.LinearRing) {
/* 140 */       LineString ls = (LineString)g;
/* 141 */       pi = new LineIterator(ls, this.at, this.generalize, (float)this.maxDistance);
/* 142 */     } else if (g instanceof Point) {
/* 143 */       Point p = (Point)g;
/* 144 */       pi = new PointIterator(p, this.at);
/*     */     } 
/* 147 */     return pi;
/*     */   }
/*     */   
/*     */   public int currentSegment(double[] coords) {
/* 171 */     return this.currentIterator.currentSegment(coords);
/*     */   }
/*     */   
/*     */   public int currentSegment(float[] coords) {
/* 195 */     return this.currentIterator.currentSegment(coords);
/*     */   }
/*     */   
/*     */   public int getWindingRule() {
/* 207 */     return 1;
/*     */   }
/*     */   
/*     */   public boolean isDone() {
/* 217 */     return this.done;
/*     */   }
/*     */   
/*     */   public void next() {
/* 227 */     if (!this.currentIterator.isDone())
/* 228 */       this.currentIterator.next(); 
/* 232 */     while (this.currentIterator.isDone() && !this.done) {
/* 233 */       if (this.currentGeom < this.gc.getNumGeometries() - 1) {
/* 234 */         this.currentGeom++;
/* 235 */         this.currentIterator = getIterator(this.gc.getGeometryN(this.currentGeom));
/*     */         continue;
/*     */       } 
/* 237 */       this.done = true;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\jts\GeomCollectionIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */