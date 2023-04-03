/*     */ package org.geotools.data.shapefile.shp;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.CGAlgorithms;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.MultiPolygon;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.DoubleBuffer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.geometry.jts.coordinatesequence.CoordinateSequences;
/*     */ import org.geotools.util.logging.Logging;
/*     */ 
/*     */ public class PolygonHandler implements ShapeHandler {
/*  50 */   protected static final Logger LOGGER = Logging.getLogger("org.geotools.data.shapefile");
/*     */   
/*     */   GeometryFactory geometryFactory;
/*     */   
/*     */   final ShapeType shapeType;
/*     */   
/*     */   public PolygonHandler(GeometryFactory gf) {
/*  57 */     this.shapeType = ShapeType.POLYGON;
/*  58 */     this.geometryFactory = gf;
/*     */   }
/*     */   
/*     */   public PolygonHandler(ShapeType type, GeometryFactory gf) throws ShapefileException {
/*  62 */     if (type != ShapeType.POLYGON && type != ShapeType.POLYGONM && type != ShapeType.POLYGONZ)
/*  64 */       throw new ShapefileException("PolygonHandler constructor - expected type to be 5, 15, or 25."); 
/*  68 */     this.shapeType = type;
/*  69 */     this.geometryFactory = gf;
/*     */   }
/*     */   
/*     */   boolean pointInList(Coordinate testPoint, Coordinate[] pointList) {
/*  76 */     for (int t = pointList.length - 1; t >= 0; t--) {
/*  77 */       Coordinate p = pointList[t];
/*  80 */       if (testPoint.x == p.x && testPoint.y == p.y && (testPoint.z == p.z || testPoint.z != testPoint.z))
/*  84 */         return true; 
/*     */     } 
/*  88 */     return false;
/*     */   }
/*     */   
/*     */   public ShapeType getShapeType() {
/*  92 */     return this.shapeType;
/*     */   }
/*     */   
/*     */   public int getLength(Object geometry) {
/*     */     MultiPolygon multi;
/*     */     int length;
/*  98 */     if (geometry instanceof MultiPolygon) {
/*  99 */       multi = (MultiPolygon)geometry;
/*     */     } else {
/* 101 */       multi = this.geometryFactory.createMultiPolygon(new Polygon[] { (Polygon)geometry });
/*     */     } 
/* 105 */     int nrings = 0;
/* 107 */     for (int t = 0; t < multi.getNumGeometries(); t++) {
/* 109 */       Polygon p = (Polygon)multi.getGeometryN(t);
/* 110 */       nrings = nrings + 1 + p.getNumInteriorRing();
/*     */     } 
/* 113 */     int npoints = multi.getNumPoints();
/* 116 */     if (this.shapeType == ShapeType.POLYGONZ) {
/* 117 */       length = 44 + 4 * nrings + 16 * npoints + 8 * npoints + 16 + 8 * npoints + 16;
/* 119 */     } else if (this.shapeType == ShapeType.POLYGONM) {
/* 120 */       length = 44 + 4 * nrings + 16 * npoints + 8 * npoints + 16;
/* 121 */     } else if (this.shapeType == ShapeType.POLYGON) {
/* 122 */       length = 44 + 4 * nrings + 16 * npoints;
/*     */     } else {
/* 124 */       throw new IllegalStateException("Expected ShapeType of Polygon, got " + this.shapeType);
/*     */     } 
/* 127 */     return length;
/*     */   }
/*     */   
/*     */   public Object read(ByteBuffer buffer, ShapeType type, boolean flatFeature) {
/* 131 */     if (type == ShapeType.NULL)
/* 132 */       return createNull(); 
/* 135 */     buffer.position(buffer.position() + 32);
/* 139 */     int numParts = buffer.getInt();
/* 140 */     int numPoints = buffer.getInt();
/* 141 */     int dimensions = (this.shapeType == ShapeType.POLYGONZ && !flatFeature) ? 3 : 2;
/* 143 */     int[] partOffsets = new int[numParts];
/* 145 */     for (int i = 0; i < numParts; i++)
/* 146 */       partOffsets[i] = buffer.getInt(); 
/* 149 */     ArrayList<LinearRing> shells = new ArrayList();
/* 150 */     ArrayList<LinearRing> holes = new ArrayList();
/* 151 */     CoordinateSequence coords = readCoordinates(buffer, numPoints, dimensions);
/* 153 */     int offset = 0;
/* 158 */     for (int part = 0; part < numParts; part++) {
/* 159 */       int finish, start = partOffsets[part];
/* 161 */       if (part == numParts - 1) {
/* 162 */         finish = numPoints;
/*     */       } else {
/* 164 */         finish = partOffsets[part + 1];
/*     */       } 
/* 167 */       int length = finish - start;
/* 168 */       int close = 0;
/* 169 */       if (coords.getOrdinate(start, 0) != coords.getOrdinate(finish - 1, 0) || coords.getOrdinate(start, 1) != coords.getOrdinate(finish - 1, 1))
/* 172 */         close = 1; 
/* 174 */       if (dimensions == 3 && 
/* 175 */         coords.getOrdinate(start, 2) != coords.getOrdinate(finish - 1, 2))
/* 176 */         close = 1; 
/* 180 */       CoordinateSequence csRing = this.geometryFactory.getCoordinateSequenceFactory().create(length + close, dimensions);
/* 183 */       for (int j = 0; j < length; j++) {
/* 184 */         csRing.setOrdinate(j, 0, coords.getOrdinate(offset, 0));
/* 185 */         csRing.setOrdinate(j, 1, coords.getOrdinate(offset, 1));
/* 186 */         if (dimensions == 3)
/* 187 */           csRing.setOrdinate(j, 2, coords.getOrdinate(offset, 2)); 
/* 189 */         offset++;
/*     */       } 
/* 191 */       if (close == 1) {
/* 192 */         csRing.setOrdinate(length, 0, coords.getOrdinate(start, 0));
/* 193 */         csRing.setOrdinate(length, 1, coords.getOrdinate(start, 1));
/* 194 */         if (dimensions == 3)
/* 195 */           csRing.setOrdinate(length, 2, coords.getOrdinate(start, 2)); 
/*     */       } 
/* 200 */       if (csRing.size() == 0 || csRing.size() > 3) {
/* 201 */         LinearRing ring = this.geometryFactory.createLinearRing(csRing);
/* 203 */         if (CoordinateSequences.isCCW(csRing)) {
/* 205 */           holes.add(ring);
/*     */         } else {
/* 208 */           shells.add(ring);
/*     */         } 
/*     */       } 
/*     */     } 
/* 215 */     if (shells.size() == 1)
/* 216 */       return createMulti(shells.get(0), holes); 
/* 220 */     if (holes.size() == 1 && shells.size() == 0)
/* 221 */       return createMulti(holes.get(0)); 
/* 225 */     ArrayList holesForShells = assignHolesToShells(shells, holes);
/* 227 */     Geometry g = buildGeometries(shells, holes, holesForShells);
/* 229 */     return g;
/*     */   }
/*     */   
/*     */   private CoordinateSequence readCoordinates(ByteBuffer buffer, int numPoints, int dimensions) {
/* 239 */     CoordinateSequence cs = this.geometryFactory.getCoordinateSequenceFactory().create(numPoints, dimensions);
/* 241 */     DoubleBuffer dbuffer = buffer.asDoubleBuffer();
/* 242 */     double[] ordinates = new double[numPoints * 2];
/* 243 */     dbuffer.get(ordinates);
/*     */     int t;
/* 244 */     for (t = 0; t < numPoints; t++) {
/* 245 */       cs.setOrdinate(t, 0, ordinates[t * 2]);
/* 246 */       cs.setOrdinate(t, 1, ordinates[t * 2 + 1]);
/*     */     } 
/* 249 */     if (dimensions > 2) {
/* 251 */       dbuffer.position(dbuffer.position() + 2);
/* 252 */       dbuffer.get(ordinates, 0, numPoints);
/* 254 */       for (t = 0; t < numPoints; t++)
/* 255 */         cs.setOrdinate(t, 2, ordinates[t]); 
/*     */     } 
/* 259 */     return cs;
/*     */   }
/*     */   
/*     */   private Geometry buildGeometries(List<LinearRing> shells, List<LinearRing> holes, List<ArrayList> holesForShells) {
/*     */     Polygon[] polygons;
/* 272 */     if (shells.size() > 0) {
/* 273 */       polygons = new Polygon[shells.size()];
/*     */     } else {
/* 276 */       polygons = new Polygon[holes.size()];
/*     */     } 
/*     */     int i;
/* 280 */     for (i = 0; i < shells.size(); i++)
/* 281 */       polygons[i] = this.geometryFactory.createPolygon(shells.get(i), (LinearRing[])((ArrayList)holesForShells.get(i)).toArray((Object[])new LinearRing[0])); 
/* 288 */     if (shells.size() == 0) {
/*     */       int ii;
/* 289 */       for (i = 0, ii = holes.size(); i < ii; i++) {
/* 290 */         LinearRing hole = holes.get(i);
/* 291 */         polygons[i] = this.geometryFactory.createPolygon(hole, null);
/*     */       } 
/*     */     } 
/* 295 */     return (Geometry)this.geometryFactory.createMultiPolygon(polygons);
/*     */   }
/*     */   
/*     */   ArrayList assignHolesToShells(ArrayList<LinearRing> shells, ArrayList<LinearRing> holes) {
/* 307 */     ArrayList<ArrayList<LinearRing>> holesForShells = new ArrayList(shells.size());
/*     */     int i;
/* 308 */     for (i = 0; i < shells.size(); i++)
/* 309 */       holesForShells.add(new ArrayList()); 
/* 313 */     for (i = 0; i < holes.size(); i++) {
/* 314 */       LinearRing testRing = holes.get(i);
/* 315 */       LinearRing minShell = null;
/* 316 */       Envelope minEnv = null;
/* 317 */       Envelope testEnv = testRing.getEnvelopeInternal();
/* 318 */       Coordinate testPt = testRing.getCoordinateN(0);
/* 321 */       for (int j = 0; j < shells.size(); j++) {
/* 322 */         LinearRing tryRing = shells.get(j);
/* 324 */         Envelope tryEnv = tryRing.getEnvelopeInternal();
/* 325 */         if (minShell != null)
/* 326 */           minEnv = minShell.getEnvelopeInternal(); 
/* 329 */         boolean isContained = false;
/* 330 */         Coordinate[] coordList = tryRing.getCoordinates();
/* 332 */         if (tryEnv.contains(testEnv) && (CGAlgorithms.isPointInRing(testPt, coordList) || pointInList(testPt, coordList)))
/* 335 */           isContained = true; 
/* 340 */         if (isContained && (
/* 341 */           minShell == null || minEnv.contains(tryEnv)))
/* 342 */           minShell = tryRing; 
/*     */       } 
/* 347 */       if (minShell == null) {
/* 349 */         shells.add(testRing);
/* 350 */         holesForShells.add(new ArrayList());
/*     */       } else {
/* 352 */         ((ArrayList<LinearRing>)holesForShells.get(shells.indexOf(minShell))).add(testRing);
/*     */       } 
/*     */     } 
/* 357 */     return holesForShells;
/*     */   }
/*     */   
/*     */   private MultiPolygon createMulti(LinearRing single) {
/* 361 */     return createMulti(single, Collections.EMPTY_LIST);
/*     */   }
/*     */   
/*     */   private MultiPolygon createMulti(LinearRing single, List holes) {
/* 365 */     return this.geometryFactory.createMultiPolygon(new Polygon[] { this.geometryFactory.createPolygon(single, (LinearRing[])holes.toArray((Object[])new LinearRing[holes.size()])) });
/*     */   }
/*     */   
/*     */   private MultiPolygon createNull() {
/* 372 */     return this.geometryFactory.createMultiPolygon(null);
/*     */   }
/*     */   
/*     */   public void write(ByteBuffer buffer, Object geometry) {
/*     */     MultiPolygon multi;
/* 378 */     if (geometry instanceof MultiPolygon) {
/* 379 */       multi = (MultiPolygon)geometry;
/*     */     } else {
/* 381 */       multi = this.geometryFactory.createMultiPolygon(new Polygon[] { (Polygon)geometry });
/*     */     } 
/* 384 */     Envelope box = multi.getEnvelopeInternal();
/* 385 */     buffer.putDouble(box.getMinX());
/* 386 */     buffer.putDouble(box.getMinY());
/* 387 */     buffer.putDouble(box.getMaxX());
/* 388 */     buffer.putDouble(box.getMaxY());
/* 394 */     List<CoordinateSequence> allCoords = new ArrayList();
/* 395 */     for (int t = 0; t < multi.getNumGeometries(); t++) {
/* 397 */       Polygon p = (Polygon)multi.getGeometryN(t);
/* 398 */       allCoords.add(p.getExteriorRing().getCoordinateSequence());
/* 399 */       for (int j = 0; j < p.getNumInteriorRing(); j++)
/* 400 */         allCoords.add(p.getInteriorRingN(j).getCoordinateSequence()); 
/*     */     } 
/* 403 */     CoordinateSequence[] coordinates = allCoords.<CoordinateSequence>toArray(new CoordinateSequence[allCoords.size()]);
/* 404 */     int nrings = coordinates.length;
/* 407 */     int npoints = multi.getNumPoints();
/* 409 */     buffer.putInt(nrings);
/* 410 */     buffer.putInt(npoints);
/* 412 */     int count = 0;
/* 413 */     for (int i = 0; i < nrings; i++) {
/* 414 */       buffer.putInt(count);
/* 415 */       count += coordinates[i].size();
/*     */     } 
/* 418 */     double[] zExtreame = { Double.NaN, Double.NaN };
/*     */     int ringN;
/* 421 */     for (ringN = 0; ringN < nrings; ringN++) {
/* 422 */       CoordinateSequence coords = coordinates[ringN];
/* 424 */       JTSUtilities.zMinMax(coords, zExtreame);
/* 426 */       int seqSize = coords.size();
/* 427 */       for (int coordN = 0; coordN < seqSize; coordN++) {
/* 428 */         buffer.putDouble(coords.getOrdinate(coordN, 0));
/* 429 */         buffer.putDouble(coords.getOrdinate(coordN, 1));
/*     */       } 
/*     */     } 
/* 433 */     if (this.shapeType == ShapeType.POLYGONZ) {
/* 435 */       if (Double.isNaN(zExtreame[0])) {
/* 436 */         buffer.putDouble(0.0D);
/* 437 */         buffer.putDouble(0.0D);
/*     */       } else {
/* 439 */         buffer.putDouble(zExtreame[0]);
/* 440 */         buffer.putDouble(zExtreame[1]);
/*     */       } 
/* 443 */       for (ringN = 0; ringN < nrings; ringN++) {
/* 444 */         CoordinateSequence coords = coordinates[ringN];
/* 446 */         int seqSize = coords.size();
/* 448 */         for (int coordN = 0; coordN < seqSize; coordN++) {
/* 449 */           double z = coords.getOrdinate(coordN, 2);
/* 450 */           if (Double.isNaN(z)) {
/* 451 */             buffer.putDouble(0.0D);
/*     */           } else {
/* 453 */             buffer.putDouble(z);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 459 */     if (this.shapeType == ShapeType.POLYGONM || this.shapeType == ShapeType.POLYGONZ) {
/* 461 */       buffer.putDouble(-1.0E41D);
/* 462 */       buffer.putDouble(-1.0E41D);
/* 464 */       for (int j = 0; j < npoints; j++)
/* 465 */         buffer.putDouble(-1.0E41D); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\shp\PolygonHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */