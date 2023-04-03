/*     */ package com.vividsolutions.jts.awt;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.MultiLineString;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Point2D;
/*     */ 
/*     */ public class ShapeWriter {
/*  73 */   public static final PointTransformation DEFAULT_POINT_TRANSFORMATION = new IdentityPointTransformation();
/*     */   
/*  78 */   public static final PointShapeFactory DEFAULT_POINT_FACTORY = new PointShapeFactory.Square(3.0D);
/*     */   
/*  80 */   private PointTransformation pointTransformer = DEFAULT_POINT_TRANSFORMATION;
/*     */   
/*  81 */   private PointShapeFactory pointFactory = DEFAULT_POINT_FACTORY;
/*     */   
/*  86 */   private Point2D transPoint = new Point2D.Double();
/*     */   
/*     */   private boolean doRemoveDuplicatePoints = false;
/*     */   
/*  95 */   private double decimationDistance = 0.0D;
/*     */   
/*     */   public ShapeWriter(PointTransformation pointTransformer, PointShapeFactory pointFactory) {
/* 106 */     if (pointTransformer != null)
/* 107 */       this.pointTransformer = pointTransformer; 
/* 108 */     if (pointFactory != null)
/* 109 */       this.pointFactory = pointFactory; 
/*     */   }
/*     */   
/*     */   public ShapeWriter(PointTransformation pointTransformer) {
/* 120 */     this(pointTransformer, null);
/*     */   }
/*     */   
/*     */   public ShapeWriter() {}
/*     */   
/*     */   public void setRemoveDuplicatePoints(boolean doRemoveDuplicatePoints) {
/* 142 */     this.doRemoveDuplicatePoints = doRemoveDuplicatePoints;
/*     */   }
/*     */   
/*     */   public void setDecimation(double decimationDistance) {
/* 164 */     this.decimationDistance = decimationDistance;
/*     */   }
/*     */   
/*     */   public Shape toShape(Geometry geometry) {
/* 185 */     if (geometry.isEmpty())
/* 185 */       return new GeneralPath(); 
/* 186 */     if (geometry instanceof Polygon)
/* 186 */       return toShape((Polygon)geometry); 
/* 187 */     if (geometry instanceof LineString)
/* 187 */       return toShape((LineString)geometry); 
/* 188 */     if (geometry instanceof MultiLineString)
/* 188 */       return toShape((MultiLineString)geometry); 
/* 189 */     if (geometry instanceof Point)
/* 189 */       return toShape((Point)geometry); 
/* 190 */     if (geometry instanceof GeometryCollection)
/* 190 */       return toShape((GeometryCollection)geometry); 
/* 192 */     throw new IllegalArgumentException("Unrecognized Geometry class: " + geometry.getClass());
/*     */   }
/*     */   
/*     */   private Shape toShape(Polygon p) {
/* 198 */     PolygonShape poly = new PolygonShape();
/* 200 */     appendRing(poly, p.getExteriorRing().getCoordinates());
/* 201 */     for (int j = 0; j < p.getNumInteriorRing(); j++)
/* 202 */       appendRing(poly, p.getInteriorRingN(j).getCoordinates()); 
/* 205 */     return poly;
/*     */   }
/*     */   
/*     */   private void appendRing(PolygonShape poly, Coordinate[] coords) {
/* 210 */     double prevx = Double.NaN;
/* 211 */     double prevy = Double.NaN;
/* 212 */     Coordinate prev = null;
/* 214 */     int n = coords.length - 1;
/* 220 */     for (int i = 0; i < n; i++) {
/* 222 */       if (this.decimationDistance > 0.0D) {
/* 223 */         boolean isDecimated = (prev != null && Math.abs((coords[i]).x - prev.x) < this.decimationDistance && Math.abs((coords[i]).y - prev.y) < this.decimationDistance);
/* 226 */         if (i < n && isDecimated)
/*     */           continue; 
/* 228 */         prev = coords[i];
/*     */       } 
/* 231 */       transformPoint(coords[i], this.transPoint);
/* 233 */       if (this.doRemoveDuplicatePoints) {
/* 235 */         boolean isDup = (this.transPoint.getX() == prevx && this.transPoint.getY() == prevy);
/* 236 */         if (i < n && isDup)
/*     */           continue; 
/* 238 */         prevx = this.transPoint.getX();
/* 239 */         prevy = this.transPoint.getY();
/*     */       } 
/* 241 */       poly.addToRing(this.transPoint);
/*     */       continue;
/*     */     } 
/* 244 */     poly.endRing();
/*     */   }
/*     */   
/*     */   private Shape toShape(GeometryCollection gc) {
/* 249 */     GeometryCollectionShape shape = new GeometryCollectionShape();
/* 251 */     for (int i = 0; i < gc.getNumGeometries(); i++) {
/* 252 */       Geometry g = gc.getGeometryN(i);
/* 253 */       shape.add(toShape(g));
/*     */     } 
/* 255 */     return shape;
/*     */   }
/*     */   
/*     */   private GeneralPath toShape(MultiLineString mls) {
/* 260 */     GeneralPath path = new GeneralPath();
/* 262 */     for (int i = 0; i < mls.getNumGeometries(); i++) {
/* 263 */       LineString lineString = (LineString)mls.getGeometryN(i);
/* 264 */       path.append(toShape(lineString), false);
/*     */     } 
/* 266 */     return path;
/*     */   }
/*     */   
/*     */   private GeneralPath toShape(LineString lineString) {
/* 271 */     GeneralPath shape = new GeneralPath();
/* 273 */     Coordinate prev = lineString.getCoordinateN(0);
/* 274 */     transformPoint(prev, this.transPoint);
/* 275 */     shape.moveTo((float)this.transPoint.getX(), (float)this.transPoint.getY());
/* 277 */     double prevx = this.transPoint.getX();
/* 278 */     double prevy = this.transPoint.getY();
/* 280 */     int n = lineString.getNumPoints() - 1;
/* 282 */     for (int i = 1; i <= n; i++) {
/* 283 */       Coordinate currentCoord = lineString.getCoordinateN(i);
/* 284 */       if (this.decimationDistance > 0.0D) {
/* 285 */         boolean isDecimated = (prev != null && Math.abs(currentCoord.x - prev.x) < this.decimationDistance && Math.abs(currentCoord.y - prev.y) < this.decimationDistance);
/* 288 */         if (i < n && isDecimated)
/*     */           continue; 
/* 291 */         prev = currentCoord;
/*     */       } 
/* 294 */       transformPoint(currentCoord, this.transPoint);
/* 296 */       if (this.doRemoveDuplicatePoints) {
/* 298 */         boolean isDup = (this.transPoint.getX() == prevx && this.transPoint.getY() == prevy);
/* 299 */         if (i < n && isDup)
/*     */           continue; 
/* 301 */         prevx = this.transPoint.getX();
/* 302 */         prevy = this.transPoint.getY();
/*     */       } 
/* 305 */       shape.lineTo((float)this.transPoint.getX(), (float)this.transPoint.getY());
/*     */       continue;
/*     */     } 
/* 308 */     return shape;
/*     */   }
/*     */   
/*     */   private Shape toShape(Point point) {
/* 313 */     Point2D viewPoint = transformPoint(point.getCoordinate());
/* 314 */     return this.pointFactory.createPoint(viewPoint);
/*     */   }
/*     */   
/*     */   private Point2D transformPoint(Coordinate model) {
/* 318 */     return transformPoint(model, new Point2D.Double());
/*     */   }
/*     */   
/*     */   private Point2D transformPoint(Coordinate model, Point2D view) {
/* 322 */     this.pointTransformer.transform(model, view);
/* 323 */     return view;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\awt\ShapeWriter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */