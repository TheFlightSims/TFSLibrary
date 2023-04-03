/*     */ package org.geotools.geometry.jts;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequenceFactory;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.MultiLineString;
/*     */ import com.vividsolutions.jts.geom.MultiPoint;
/*     */ import com.vividsolutions.jts.geom.MultiPolygon;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.TransformException;
/*     */ 
/*     */ public class GeometryCoordinateSequenceTransformer {
/*  55 */   private MathTransform transform = null;
/*     */   
/*     */   private CoordinateReferenceSystem crs;
/*     */   
/*  57 */   private CoordinateSequenceTransformer inputCSTransformer = null;
/*     */   
/*  58 */   private CoordinateSequenceTransformer csTransformer = null;
/*     */   
/*  59 */   private GeometryFactory currGeometryFactory = null;
/*     */   
/*     */   public GeometryCoordinateSequenceTransformer() {}
/*     */   
/*     */   public GeometryCoordinateSequenceTransformer(CoordinateSequenceTransformer transformer) {
/*  81 */     this.inputCSTransformer = transformer;
/*  82 */     this.csTransformer = transformer;
/*     */   }
/*     */   
/*     */   public void setMathTransform(MathTransform transform) {
/*  90 */     this.transform = transform;
/*     */   }
/*     */   
/*     */   public void setCoordinateReferenceSystem(CoordinateReferenceSystem crs) {
/* 102 */     this.crs = crs;
/*     */   }
/*     */   
/*     */   private void init(GeometryFactory gf) {
/* 114 */     if (this.inputCSTransformer != null)
/*     */       return; 
/* 117 */     if (this.currGeometryFactory == gf)
/*     */       return; 
/* 120 */     this.currGeometryFactory = gf;
/* 121 */     CoordinateSequenceFactory csf = gf.getCoordinateSequenceFactory();
/* 122 */     this.csTransformer = new DefaultCoordinateSequenceTransformer(csf);
/*     */   }
/*     */   
/*     */   public Geometry transform(Geometry g) throws TransformException {
/*     */     GeometryCollection geometryCollection;
/* 134 */     GeometryFactory factory = g.getFactory();
/* 135 */     Geometry transformed = null;
/* 138 */     init(factory);
/* 140 */     if (g instanceof Point) {
/* 141 */       Point point = transformPoint((Point)g, factory);
/* 142 */     } else if (g instanceof MultiPoint) {
/* 143 */       MultiPoint mp = (MultiPoint)g;
/* 144 */       Point[] points = new Point[mp.getNumGeometries()];
/* 146 */       for (int i = 0; i < points.length; i++)
/* 147 */         points[i] = transformPoint((Point)mp.getGeometryN(i), factory); 
/* 150 */       MultiPoint multiPoint1 = factory.createMultiPoint(points);
/* 151 */     } else if (g instanceof LineString) {
/* 152 */       LineString lineString = transformLineString((LineString)g, factory);
/* 153 */     } else if (g instanceof MultiLineString) {
/* 154 */       MultiLineString mls = (MultiLineString)g;
/* 155 */       LineString[] lines = new LineString[mls.getNumGeometries()];
/* 157 */       for (int i = 0; i < lines.length; i++)
/* 158 */         lines[i] = transformLineString((LineString)mls.getGeometryN(i), factory); 
/* 161 */       MultiLineString multiLineString1 = factory.createMultiLineString(lines);
/* 162 */     } else if (g instanceof Polygon) {
/* 163 */       Polygon polygon = transformPolygon((Polygon)g, factory);
/* 164 */     } else if (g instanceof MultiPolygon) {
/* 165 */       MultiPolygon mp = (MultiPolygon)g;
/* 166 */       Polygon[] polygons = new Polygon[mp.getNumGeometries()];
/* 168 */       for (int i = 0; i < polygons.length; i++)
/* 169 */         polygons[i] = transformPolygon((Polygon)mp.getGeometryN(i), factory); 
/* 172 */       MultiPolygon multiPolygon1 = factory.createMultiPolygon(polygons);
/* 173 */     } else if (g instanceof GeometryCollection) {
/* 174 */       GeometryCollection gc = (GeometryCollection)g;
/* 175 */       Geometry[] geoms = new Geometry[gc.getNumGeometries()];
/* 177 */       for (int i = 0; i < geoms.length; i++)
/* 178 */         geoms[i] = transform(gc.getGeometryN(i)); 
/* 181 */       geometryCollection = factory.createGeometryCollection(geoms);
/*     */     } else {
/* 183 */       throw new IllegalArgumentException("Unsupported geometry type " + g.getClass());
/*     */     } 
/* 188 */     geometryCollection.setUserData(g.getUserData());
/* 190 */     if (g.getUserData() == null || g.getUserData() instanceof CoordinateReferenceSystem)
/* 192 */       if (this.crs != null)
/* 193 */         geometryCollection.setUserData(this.crs);  
/* 197 */     return (Geometry)geometryCollection;
/*     */   }
/*     */   
/*     */   public LineString transformLineString(LineString ls, GeometryFactory gf) throws TransformException {
/* 208 */     init(gf);
/* 210 */     CoordinateSequence cs = projectCoordinateSequence(ls.getCoordinateSequence());
/* 211 */     LineString transformed = null;
/* 213 */     if (ls instanceof LinearRing) {
/* 214 */       LinearRing linearRing = gf.createLinearRing(cs);
/*     */     } else {
/* 216 */       transformed = gf.createLineString(cs);
/*     */     } 
/* 219 */     transformed.setUserData(ls.getUserData());
/* 220 */     return transformed;
/*     */   }
/*     */   
/*     */   public Point transformPoint(Point point, GeometryFactory gf) throws TransformException {
/* 232 */     init(gf);
/* 234 */     CoordinateSequence cs = projectCoordinateSequence(point.getCoordinateSequence());
/* 235 */     Point transformed = gf.createPoint(cs);
/* 236 */     transformed.setUserData(point.getUserData());
/* 237 */     return transformed;
/*     */   }
/*     */   
/*     */   private CoordinateSequence projectCoordinateSequence(CoordinateSequence cs) throws TransformException {
/* 247 */     return this.csTransformer.transform(cs, this.transform);
/*     */   }
/*     */   
/*     */   public Polygon transformPolygon(Polygon polygon, GeometryFactory gf) throws TransformException {
/* 256 */     LinearRing exterior = (LinearRing)transformLineString(polygon.getExteriorRing(), gf);
/* 257 */     LinearRing[] interiors = new LinearRing[polygon.getNumInteriorRing()];
/* 259 */     for (int i = 0; i < interiors.length; i++)
/* 260 */       interiors[i] = (LinearRing)transformLineString(polygon.getInteriorRingN(i), gf); 
/* 263 */     Polygon transformed = gf.createPolygon(exterior, interiors);
/* 264 */     transformed.setUserData(polygon.getUserData());
/* 265 */     return transformed;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\jts\GeometryCoordinateSequenceTransformer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */