/*     */ package org.geotools.geometry.jts;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.PathIterator;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.TransformException;
/*     */ 
/*     */ public final class LiteShape2 implements Shape, Cloneable {
/*     */   private Geometry geometry;
/*     */   
/*     */   private boolean generalize = false;
/*     */   
/*  66 */   private double maxDistance = 1.0D;
/*     */   
/*     */   private static GeometryFactory geomFac;
/*     */   
/*     */   private MathTransform mathTransform;
/*     */   
/*     */   public LiteShape2(Geometry geom, MathTransform mathTransform, Decimator decimator, boolean generalize, double maxDistance) throws TransformException, FactoryException {
/*  94 */     this(geom, mathTransform, decimator, generalize);
/*  95 */     this.maxDistance = maxDistance;
/*     */   }
/*     */   
/*     */   public LiteShape2(Geometry geom, MathTransform mathTransform, Decimator decimator, boolean generalize) throws TransformException, FactoryException {
/* 118 */     this(geom, mathTransform, decimator, generalize, true);
/*     */   }
/*     */   
/*     */   public LiteShape2(Geometry geom, MathTransform mathTransform, Decimator decimator, boolean generalize, boolean clone) throws TransformException, FactoryException {
/* 144 */     if (geom != null)
/* 145 */       if (!clone && geom.getFactory().getCoordinateSequenceFactory() instanceof LiteCoordinateSequenceFactory) {
/* 146 */         this.geometry = geom;
/*     */       } else {
/* 148 */         this.geometry = LiteCoordinateSequence.cloneGeometry(geom);
/*     */       }  
/* 151 */     this.mathTransform = mathTransform;
/* 152 */     if (decimator != null) {
/* 153 */       decimator.decimateTransformGeneralize(this.geometry, this.mathTransform);
/* 154 */       this.geometry.geometryChanged();
/*     */     } else {
/* 157 */       if (mathTransform != null && !mathTransform.isIdentity() && generalize && this.geometry != null) {
/* 158 */         (new Decimator(mathTransform.inverse())).decimate(this.geometry);
/* 159 */         this.geometry.geometryChanged();
/*     */       } 
/* 161 */       if (this.geometry != null) {
/* 162 */         transformGeometry(this.geometry);
/* 163 */         this.geometry.geometryChanged();
/*     */       } 
/*     */     } 
/* 166 */     this.generalize = false;
/*     */   }
/*     */   
/*     */   private void transformGeometry(Geometry geometry) throws TransformException, FactoryException {
/* 172 */     if (this.mathTransform == null || this.mathTransform.isIdentity())
/*     */       return; 
/* 175 */     if (geometry instanceof GeometryCollection) {
/* 176 */       GeometryCollection collection = (GeometryCollection)geometry;
/* 177 */       for (int i = 0; i < collection.getNumGeometries(); i++)
/* 178 */         transformGeometry(collection.getGeometryN(i)); 
/* 180 */     } else if (geometry instanceof Point) {
/* 181 */       LiteCoordinateSequence seq = (LiteCoordinateSequence)((Point)geometry).getCoordinateSequence();
/* 183 */       double[] coords = seq.getArray();
/* 184 */       double[] newCoords = new double[coords.length];
/* 185 */       this.mathTransform.transform(coords, 0, newCoords, 0, seq.size());
/* 186 */       seq.setArray(newCoords);
/* 187 */     } else if (geometry instanceof Polygon) {
/* 188 */       Polygon polygon = (Polygon)geometry;
/* 189 */       transformGeometry((Geometry)polygon.getExteriorRing());
/* 190 */       for (int i = 0; i < polygon.getNumInteriorRing(); i++)
/* 191 */         transformGeometry((Geometry)polygon.getInteriorRingN(i)); 
/* 193 */     } else if (geometry instanceof LineString) {
/* 194 */       LiteCoordinateSequence seq = (LiteCoordinateSequence)((LineString)geometry).getCoordinateSequence();
/* 196 */       double[] coords = seq.getArray();
/* 197 */       this.mathTransform.transform(coords, 0, coords, 0, seq.size());
/* 198 */       seq.setArray(coords);
/*     */     } 
/*     */   }
/*     */   
/*     */   private GeometryFactory getGeometryFactory() {
/* 203 */     if (geomFac == null)
/* 204 */       geomFac = new GeometryFactory(new LiteCoordinateSequenceFactory()); 
/* 207 */     return geomFac;
/*     */   }
/*     */   
/*     */   public void setGeometry(Geometry g) throws TransformException, FactoryException {
/* 220 */     if (g != null) {
/* 221 */       this.geometry = getGeometryFactory().createGeometry(g);
/* 222 */       transformGeometry(this.geometry);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean contains(Rectangle2D r) {
/* 258 */     Geometry rect = rectangleToGeometry(r);
/* 260 */     return this.geometry.contains(rect);
/*     */   }
/*     */   
/*     */   public boolean contains(Point2D p) {
/* 275 */     Coordinate coord = new Coordinate(p.getX(), p.getY());
/* 276 */     Point point = this.geometry.getFactory().createPoint(coord);
/* 278 */     return this.geometry.contains((Geometry)point);
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y) {
/* 294 */     Coordinate coord = new Coordinate(x, y);
/* 295 */     Point point = this.geometry.getFactory().createPoint(coord);
/* 297 */     return this.geometry.contains((Geometry)point);
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y, double w, double h) {
/* 343 */     Geometry rect = createRectangle(x, y, w, h);
/* 345 */     return this.geometry.contains(rect);
/*     */   }
/*     */   
/*     */   public Rectangle getBounds() {
/* 366 */     Rectangle2D env = getBounds2D();
/* 367 */     return new Rectangle((int)Math.round(env.getMinX()), (int)Math.round(env.getMinY()), (int)Math.ceil(env.getWidth()), (int)Math.ceil(env.getHeight()));
/*     */   }
/*     */   
/*     */   public Rectangle2D getBounds2D() {
/* 391 */     Envelope env = this.geometry.getEnvelopeInternal();
/* 393 */     return new Rectangle2D.Double(env.getMinX(), env.getMinY(), env.getMaxX() - env.getMinX(), env.getMaxY() - env.getMinY());
/*     */   }
/*     */   
/*     */   public PathIterator getPathIterator(AffineTransform at) {
/* 433 */     PathIterator pi = null;
/* 435 */     if (this.geometry == null || this.geometry.isEmpty())
/* 436 */       return EmptyIterator.INSTANCE; 
/* 439 */     if (this.geometry instanceof Point)
/* 440 */       pi = new PointIterator((Point)this.geometry, at); 
/* 443 */     if (this.geometry instanceof Polygon) {
/* 444 */       pi = new PolygonIterator((Polygon)this.geometry, at, this.generalize, this.maxDistance);
/* 445 */     } else if (this.geometry instanceof LineString) {
/* 446 */       pi = new LineIterator((LineString)this.geometry, at, this.generalize, (float)this.maxDistance);
/* 447 */     } else if (this.geometry instanceof GeometryCollection) {
/* 448 */       pi = new GeomCollectionIterator((GeometryCollection)this.geometry, at, this.generalize, this.maxDistance);
/*     */     } 
/* 450 */     return pi;
/*     */   }
/*     */   
/*     */   public PathIterator getPathIterator(AffineTransform at, double flatness) {
/* 511 */     return getPathIterator(at);
/*     */   }
/*     */   
/*     */   public boolean intersects(Rectangle2D r) {
/* 541 */     Geometry rect = rectangleToGeometry(r);
/* 543 */     return this.geometry.intersects(rect);
/*     */   }
/*     */   
/*     */   public boolean intersects(double x, double y, double w, double h) {
/* 586 */     Geometry rect = createRectangle(x, y, w, h);
/* 588 */     return this.geometry.intersects(rect);
/*     */   }
/*     */   
/*     */   private Geometry rectangleToGeometry(Rectangle2D r) {
/* 600 */     return createRectangle(r.getMinX(), r.getMinY(), r.getWidth(), r.getHeight());
/*     */   }
/*     */   
/*     */   private Geometry createRectangle(double x, double y, double w, double h) {
/* 620 */     Coordinate[] coords = { new Coordinate(x, y), new Coordinate(x, y + h), new Coordinate(x + w, y + h), new Coordinate(x + w, y), new Coordinate(x, y) };
/* 623 */     LinearRing lr = this.geometry.getFactory().createLinearRing(coords);
/* 625 */     return (Geometry)this.geometry.getFactory().createPolygon(lr, null);
/*     */   }
/*     */   
/*     */   public MathTransform getMathTransform() {
/* 629 */     return this.mathTransform;
/*     */   }
/*     */   
/*     */   public Geometry getGeometry() {
/* 633 */     return this.geometry;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\jts\LiteShape2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */