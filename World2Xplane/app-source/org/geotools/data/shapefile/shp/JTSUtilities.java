/*     */ package org.geotools.data.shapefile.shp;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.CGAlgorithms;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequenceFactory;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.MultiLineString;
/*     */ import com.vividsolutions.jts.geom.MultiPoint;
/*     */ import com.vividsolutions.jts.geom.MultiPolygon;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.opengis.feature.type.GeometryDescriptor;
/*     */ 
/*     */ public class JTSUtilities {
/*  48 */   static final CGAlgorithms cga = new CGAlgorithms();
/*     */   
/*     */   public static final double[] zMinMax(Coordinate[] cs) {
/*  62 */     double[] result = { Double.NaN, Double.NaN };
/*  63 */     zMinMax((CoordinateSequence)new CoordinateArraySequence(cs), result);
/*  64 */     return result;
/*     */   }
/*     */   
/*     */   public static final void zMinMax(CoordinateSequence cs, double[] target) {
/*  79 */     if (cs.getDimension() < 3)
/*     */       return; 
/*  84 */     boolean validZFound = false;
/*  86 */     double zmin = Double.NaN;
/*  87 */     double zmax = Double.NaN;
/*  90 */     int size = cs.size();
/*  91 */     for (int t = size - 1; t >= 0; t--) {
/*  92 */       double z = cs.getOrdinate(t, 2);
/*  94 */       if (!Double.isNaN(z))
/*  95 */         if (validZFound) {
/*  96 */           if (z < zmin)
/*  97 */             zmin = z; 
/* 100 */           if (z > zmax)
/* 101 */             zmax = z; 
/*     */         } else {
/* 104 */           validZFound = true;
/* 105 */           zmin = z;
/* 106 */           zmax = z;
/*     */         }  
/*     */     } 
/* 111 */     if (!Double.isNaN(zmin))
/* 112 */       target[0] = zmin; 
/* 114 */     if (!Double.isNaN(zmax))
/* 115 */       target[1] = zmax; 
/*     */   }
/*     */   
/*     */   public static final ShapeType findBestGeometryType(Geometry geom) {
/* 127 */     ShapeType type = ShapeType.UNDEFINED;
/* 129 */     if (geom instanceof Point) {
/* 130 */       type = ShapeType.POINT;
/* 131 */     } else if (geom instanceof MultiPoint) {
/* 132 */       type = ShapeType.MULTIPOINT;
/* 133 */     } else if (geom instanceof Polygon) {
/* 134 */       type = ShapeType.POLYGON;
/* 135 */     } else if (geom instanceof MultiPolygon) {
/* 136 */       type = ShapeType.POLYGON;
/* 137 */     } else if (geom instanceof LineString) {
/* 138 */       type = ShapeType.ARC;
/* 139 */     } else if (geom instanceof MultiLineString) {
/* 140 */       type = ShapeType.ARC;
/*     */     } 
/* 142 */     return type;
/*     */   }
/*     */   
/*     */   public static final Class findBestGeometryClass(ShapeType type) {
/*     */     Class<MultiPolygon> best;
/* 147 */     if (type == null || type == ShapeType.NULL) {
/* 148 */       Class<Geometry> clazz = Geometry.class;
/* 149 */     } else if (type.isLineType()) {
/* 150 */       Class<MultiLineString> clazz = MultiLineString.class;
/* 151 */     } else if (type.isMultiPointType()) {
/* 152 */       Class<MultiPoint> clazz = MultiPoint.class;
/* 153 */     } else if (type.isPointType()) {
/* 154 */       Class<Point> clazz = Point.class;
/* 155 */     } else if (type.isPolygonType()) {
/* 156 */       best = MultiPolygon.class;
/*     */     } else {
/* 158 */       throw new RuntimeException("Unknown ShapeType->GeometryClass : " + type);
/*     */     } 
/* 161 */     return best;
/*     */   }
/*     */   
/*     */   public static final LinearRing reverseRing(LinearRing lr) {
/* 175 */     GeometryFactory gf = lr.getFactory();
/* 176 */     CoordinateSequenceFactory csf = gf.getCoordinateSequenceFactory();
/* 178 */     CoordinateSequence csOrig = lr.getCoordinateSequence();
/* 179 */     int numPoints = csOrig.size();
/* 180 */     int dimensions = csOrig.getDimension();
/* 181 */     CoordinateSequence csNew = csf.create(numPoints, dimensions);
/* 183 */     for (int i = 0; i < numPoints; i++) {
/* 184 */       for (int j = 0; j < dimensions; j++)
/* 185 */         csNew.setOrdinate(numPoints - 1 - i, j, csOrig.getOrdinate(i, j)); 
/*     */     } 
/* 189 */     return gf.createLinearRing(csNew);
/*     */   }
/*     */   
/*     */   public static final Polygon makeGoodShapePolygon(Polygon p) {
/*     */     LinearRing outer;
/* 201 */     GeometryFactory factory = p.getFactory();
/* 203 */     LinearRing[] holes = new LinearRing[p.getNumInteriorRing()];
/* 206 */     Coordinate[] coords = p.getExteriorRing().getCoordinates();
/* 208 */     if (CGAlgorithms.isCCW(coords)) {
/* 209 */       outer = reverseRing((LinearRing)p.getExteriorRing());
/*     */     } else {
/* 211 */       outer = (LinearRing)p.getExteriorRing();
/*     */     } 
/* 214 */     for (int t = 0, tt = p.getNumInteriorRing(); t < tt; t++) {
/* 215 */       coords = p.getInteriorRingN(t).getCoordinates();
/* 217 */       if (!CGAlgorithms.isCCW(coords)) {
/* 218 */         holes[t] = reverseRing((LinearRing)p.getInteriorRingN(t));
/*     */       } else {
/* 220 */         holes[t] = (LinearRing)p.getInteriorRingN(t);
/*     */       } 
/*     */     } 
/* 224 */     return factory.createPolygon(outer, holes);
/*     */   }
/*     */   
/*     */   public static final MultiPolygon makeGoodShapeMultiPolygon(MultiPolygon mp) {
/* 236 */     Polygon[] ps = new Polygon[mp.getNumGeometries()];
/* 239 */     for (int t = 0; t < mp.getNumGeometries(); t++)
/* 240 */       ps[t] = makeGoodShapePolygon((Polygon)mp.getGeometryN(t)); 
/* 243 */     MultiPolygon result = mp.getFactory().createMultiPolygon(ps);
/* 245 */     return result;
/*     */   }
/*     */   
/*     */   public static final int guessCoorinateDims(Coordinate[] cs) {
/* 259 */     int dims = 2;
/* 261 */     for (int t = cs.length - 1; t >= 0; t--) {
/* 262 */       if (!Double.isNaN((cs[t]).z)) {
/* 263 */         dims = 4;
/*     */         break;
/*     */       } 
/*     */     } 
/* 268 */     return dims;
/*     */   }
/*     */   
/*     */   public static Geometry convertToCollection(Geometry geom, ShapeType type) {
/*     */     MultiPoint multiPoint;
/* 272 */     Geometry retVal = null;
/* 274 */     if (geom == null)
/* 275 */       return null; 
/* 277 */     GeometryFactory factory = geom.getFactory();
/* 279 */     if (type.isPointType()) {
/* 280 */       if (geom instanceof Point) {
/* 281 */         retVal = geom;
/*     */       } else {
/* 283 */         Point[] pNull = null;
/* 284 */         multiPoint = factory.createMultiPoint(pNull);
/*     */       } 
/* 286 */     } else if (type.isLineType()) {
/* 287 */       if (geom instanceof LineString) {
/* 288 */         MultiLineString multiLineString = factory.createMultiLineString(new LineString[] { (LineString)geom });
/* 290 */       } else if (geom instanceof MultiLineString) {
/* 291 */         retVal = geom;
/*     */       } else {
/* 293 */         MultiLineString multiLineString = factory.createMultiLineString(null);
/*     */       } 
/* 295 */     } else if (type.isPolygonType()) {
/* 296 */       if (geom instanceof Polygon) {
/* 297 */         Polygon p = makeGoodShapePolygon((Polygon)geom);
/* 298 */         MultiPolygon multiPolygon = factory.createMultiPolygon(new Polygon[] { p });
/* 299 */       } else if (geom instanceof MultiPolygon) {
/* 300 */         MultiPolygon multiPolygon = makeGoodShapeMultiPolygon((MultiPolygon)geom);
/*     */       } else {
/* 303 */         MultiPolygon multiPolygon = factory.createMultiPolygon(null);
/*     */       } 
/* 305 */     } else if (type.isMultiPointType()) {
/* 306 */       if (geom instanceof Point) {
/* 307 */         multiPoint = factory.createMultiPoint(new Point[] { (Point)geom });
/* 308 */       } else if (geom instanceof MultiPoint) {
/* 309 */         retVal = geom;
/*     */       } else {
/* 311 */         Point[] pNull = null;
/* 312 */         multiPoint = factory.createMultiPoint(pNull);
/*     */       } 
/*     */     } else {
/* 315 */       throw new RuntimeException("Could not convert " + geom.getClass() + " to " + type);
/*     */     } 
/* 318 */     return (Geometry)multiPoint;
/*     */   }
/*     */   
/*     */   public static final ShapeType getShapeType(Geometry geom, int shapeFileDimentions) throws ShapefileException {
/* 335 */     ShapeType type = null;
/* 337 */     if (geom instanceof Point) {
/* 338 */       switch (shapeFileDimentions) {
/*     */         case 2:
/* 340 */           type = ShapeType.POINT;
/*     */           break;
/*     */         case 3:
/* 343 */           type = ShapeType.POINTM;
/*     */           break;
/*     */         case 4:
/* 346 */           type = ShapeType.POINTZ;
/*     */           break;
/*     */         default:
/* 349 */           throw new ShapefileException("Too many dimensions for shapefile : " + shapeFileDimentions);
/*     */       } 
/* 353 */     } else if (geom instanceof MultiPoint) {
/* 354 */       switch (shapeFileDimentions) {
/*     */         case 2:
/* 356 */           type = ShapeType.MULTIPOINT;
/*     */           break;
/*     */         case 3:
/* 359 */           type = ShapeType.MULTIPOINTM;
/*     */           break;
/*     */         case 4:
/* 362 */           type = ShapeType.MULTIPOINTZ;
/*     */           break;
/*     */         default:
/* 365 */           throw new ShapefileException("Too many dimensions for shapefile : " + shapeFileDimentions);
/*     */       } 
/* 369 */     } else if (geom instanceof Polygon || geom instanceof MultiPolygon) {
/* 370 */       switch (shapeFileDimentions) {
/*     */         case 2:
/* 372 */           type = ShapeType.POLYGON;
/*     */           break;
/*     */         case 3:
/* 375 */           type = ShapeType.POLYGONM;
/*     */           break;
/*     */         case 4:
/* 378 */           type = ShapeType.POLYGONZ;
/*     */           break;
/*     */         default:
/* 381 */           throw new ShapefileException("Too many dimensions for shapefile : " + shapeFileDimentions);
/*     */       } 
/* 385 */     } else if (geom instanceof LineString || geom instanceof MultiLineString) {
/* 387 */       switch (shapeFileDimentions) {
/*     */         case 2:
/* 389 */           type = ShapeType.ARC;
/*     */           break;
/*     */         case 3:
/* 392 */           type = ShapeType.ARCM;
/*     */           break;
/*     */         case 4:
/* 395 */           type = ShapeType.ARCZ;
/*     */           break;
/*     */         default:
/* 398 */           throw new ShapefileException("Too many dimensions for shapefile : " + shapeFileDimentions);
/*     */       } 
/*     */     } 
/* 404 */     if (type == null)
/* 405 */       throw new ShapefileException("Cannot handle geometry type : " + ((geom == null) ? "null" : geom.getClass().getName())); 
/* 408 */     return type;
/*     */   }
/*     */   
/*     */   public static final ShapeType getShapeType(Class featureClass) throws ShapefileException {
/* 424 */     ShapeType type = null;
/* 426 */     if (Point.class.equals(featureClass)) {
/* 427 */       type = ShapeType.POINT;
/* 428 */     } else if (MultiPoint.class.equals(featureClass)) {
/* 429 */       type = ShapeType.MULTIPOINT;
/* 430 */     } else if (Polygon.class.equals(featureClass) || MultiPolygon.class.equals(featureClass)) {
/* 432 */       type = ShapeType.POLYGON;
/* 433 */     } else if (LineString.class.equals(featureClass) || MultiLineString.class.equals(featureClass)) {
/* 435 */       type = ShapeType.ARC;
/*     */     } 
/* 438 */     if (type == null)
/* 439 */       throw new ShapefileException("Cannot handle geometry class : " + ((featureClass == null) ? "null" : featureClass.getName())); 
/* 442 */     return type;
/*     */   }
/*     */   
/*     */   public static final ShapeType getShapeType(GeometryDescriptor gd) throws ShapefileException {
/* 454 */     Class featureClass = gd.getType().getBinding();
/* 455 */     Integer dimension = (Integer)gd.getUserData().get(Hints.COORDINATE_DIMENSION);
/* 457 */     ShapeType type = null;
/* 458 */     if (Point.class.equals(featureClass)) {
/* 459 */       if (dimension != null && dimension.intValue() == 3) {
/* 460 */         type = ShapeType.POINTZ;
/*     */       } else {
/* 462 */         type = ShapeType.POINT;
/*     */       } 
/* 463 */     } else if (MultiPoint.class.equals(featureClass)) {
/* 464 */       if (dimension != null && dimension.intValue() == 3) {
/* 465 */         type = ShapeType.MULTIPOINTZ;
/*     */       } else {
/* 467 */         type = ShapeType.MULTIPOINT;
/*     */       } 
/* 468 */     } else if (Polygon.class.equals(featureClass) || MultiPolygon.class.equals(featureClass)) {
/* 470 */       if (dimension != null && dimension.intValue() == 3) {
/* 471 */         type = ShapeType.POLYGON;
/*     */       } else {
/* 473 */         type = ShapeType.POLYGONZ;
/*     */       } 
/* 474 */     } else if (LineString.class.equals(featureClass) || MultiLineString.class.equals(featureClass)) {
/* 476 */       if (dimension != null && dimension.intValue() == 3) {
/* 477 */         type = ShapeType.ARC;
/*     */       } else {
/* 479 */         type = ShapeType.ARCZ;
/*     */       } 
/*     */     } 
/* 482 */     if (type == null)
/* 483 */       throw new ShapefileException("Cannot handle geometry class : " + ((featureClass == null) ? "null" : featureClass.getName())); 
/* 487 */     return type;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\shp\JTSUtilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */