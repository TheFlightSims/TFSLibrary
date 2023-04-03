/*     */ package com.world2xplane.Parser;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.MultiLineString;
/*     */ import com.vividsolutions.jts.geom.MultiPolygon;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.world2xplane.DataStore.RelationInfo;
/*     */ import com.world2xplane.DataStore.WayInfo;
/*     */ import com.world2xplane.Geom.GeomUtils;
/*     */ import com.world2xplane.OSM.OSMPolygon;
/*     */ import com.world2xplane.Rules.AcceptingRule;
/*     */ import com.world2xplane.XPlane.DSFTile;
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.polygon.LinearRing2D;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ import org.geotools.data.DataStore;
/*     */ import org.geotools.data.FileDataStore;
/*     */ import org.geotools.data.FileDataStoreFinder;
/*     */ import org.geotools.data.simple.SimpleFeatureSource;
/*     */ import org.geotools.feature.FeatureCollection;
/*     */ import org.geotools.feature.FeatureIterator;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class ShapeFileProcessor implements ExternalProcessor {
/*     */   private final String filename;
/*     */   
/*     */   private final Delegate delegate;
/*     */   
/*     */   private Envelope bounds;
/*     */   
/*     */   private DSFTile dsfTile;
/*     */   
/*  60 */   private static Logger log = LoggerFactory.getLogger(ShapeFileProcessor.class);
/*     */   
/*     */   private List<AcceptingRule> rules;
/*     */   
/*  62 */   private long count = 1L;
/*     */   
/*     */   private boolean valid = false;
/*     */   
/*     */   public ShapeFileProcessor(String filename, Delegate delegate) {
/*  86 */     this.filename = filename;
/*  87 */     this.delegate = delegate;
/*  88 */     checkFile();
/*     */   }
/*     */   
/*     */   public void checkFile() {
/*     */     FileDataStore fileDataStore;
/*  96 */     FeatureIterator iterator = null;
/*  97 */     DataStore dataStore = null;
/*     */     try {
/* 100 */       File dataFile = new File(this.filename);
/* 101 */       if (!dataFile.exists()) {
/* 102 */         log.error("File does not exist " + this.filename.toString());
/* 103 */         this.valid = false;
/*     */         return;
/*     */       } 
/* 106 */       dataFile.setReadOnly();
/* 107 */       fileDataStore = FileDataStoreFinder.getDataStore(dataFile);
/* 108 */       if (fileDataStore == null)
/* 109 */         log.error("Could not open shapefile " + this.filename); 
/* 111 */       String[] typeNames = fileDataStore.getTypeNames();
/* 112 */       String typeName = typeNames[0];
/* 114 */       SimpleFeatureSource simpleFeatureSource = fileDataStore.getFeatureSource(typeName);
/* 115 */       FeatureCollection collection = simpleFeatureSource.getFeatures();
/* 116 */       iterator = collection.features();
/* 118 */       ReferencedEnvelope env = collection.getBounds();
/* 119 */       double left = env.getMinX();
/* 120 */       double right = env.getMaxX();
/* 121 */       double top = env.getMaxY();
/* 122 */       double bottom = env.getMinY();
/* 123 */       Point2D topLeft = projectPoint(left, top);
/* 124 */       Point2D bottomRight = projectPoint(right, bottom);
/* 126 */       this.bounds = new Envelope(topLeft.x(), bottomRight.x(), topLeft.y(), bottomRight.y());
/* 127 */       this.valid = true;
/* 128 */     } catch (Throwable e) {
/* 129 */       e.printStackTrace();
/* 130 */       log.error("Could not load shapefile ", e);
/*     */     } finally {
/* 132 */       if (iterator != null)
/* 133 */         iterator.close(); 
/* 134 */       if (fileDataStore != null)
/* 135 */         fileDataStore.dispose(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void process(DSFTile dsfTile) {
/* 143 */     this.dsfTile = dsfTile;
/* 144 */     readShapeFile(this.filename);
/*     */   }
/*     */   
/*     */   public boolean acceptsTile(DSFTile tile) {
/* 150 */     return this.bounds.intersects(tile.getEnvelope());
/*     */   }
/*     */   
/*     */   public boolean readShapeFile(String filename) {
/*     */     FileDataStore fileDataStore;
/* 156 */     FeatureIterator iterator = null;
/* 157 */     DataStore dataStore = null;
/*     */     try {
/* 160 */       File dataFile = new File(filename);
/* 161 */       dataFile.setReadOnly();
/* 162 */       fileDataStore = FileDataStoreFinder.getDataStore(dataFile);
/* 163 */       if (fileDataStore == null) {
/* 164 */         log.error("Could not open shapefile " + filename);
/* 165 */         return false;
/*     */       } 
/* 167 */       String[] typeNames = fileDataStore.getTypeNames();
/* 168 */       String typeName = typeNames[0];
/* 170 */       SimpleFeatureSource simpleFeatureSource = fileDataStore.getFeatureSource(typeName);
/* 171 */       FeatureCollection collection = simpleFeatureSource.getFeatures();
/* 172 */       iterator = collection.features();
/* 174 */       ReferencedEnvelope env = collection.getBounds();
/* 175 */       double left = env.getMinX();
/* 176 */       double right = env.getMaxX();
/* 177 */       double top = env.getMaxY();
/* 178 */       double bottom = env.getMinY();
/* 179 */       Point2D topLeft = projectPoint(left, top);
/* 180 */       Point2D bottomRight = projectPoint(right, bottom);
/* 181 */       log.info("Reading Shapefile: " + filename + " " + topLeft + "->" + bottomRight);
/* 183 */       int count = collection.size();
/* 184 */       int index = 0;
/* 185 */       while (iterator.hasNext()) {
/* 187 */         index++;
/* 188 */         Feature feature = iterator.next();
/* 189 */         HashMap<String, String> osmTags = this.delegate.getTagsFor(feature);
/* 191 */         if (this.delegate != null) {
/* 192 */           this.rules = this.dsfTile.generatorStore.acceptingRulesAndFilters(osmTags);
/* 193 */           if (this.rules == null || this.rules.size() == 0)
/*     */             continue; 
/*     */         } 
/* 200 */         boolean accepted = false;
/* 202 */         Geometry sourceGeometry = (Geometry)feature.getDefaultGeometryProperty().getValue();
/* 204 */         if (sourceGeometry instanceof MultiPolygon) {
/* 206 */           MultiPolygon multiPolygon = (MultiPolygon)sourceGeometry;
/* 207 */           for (int x = 0; x < multiPolygon.getNumGeometries(); x++)
/* 208 */             accepted = processPolygon((Polygon)multiPolygon.getGeometryN(x)); 
/* 210 */         } else if (sourceGeometry instanceof Polygon) {
/* 212 */           Polygon polygon = (Polygon)sourceGeometry;
/* 213 */           accepted = processPolygon(polygon);
/* 214 */         } else if (sourceGeometry instanceof MultiLineString) {
/* 215 */           MultiLineString multiLineString = (MultiLineString)sourceGeometry;
/* 216 */           for (int x = 0; x < multiLineString.getNumGeometries(); x++)
/* 217 */             accepted = processLineString((LineString)multiLineString.getGeometryN(x)); 
/* 219 */         } else if (sourceGeometry instanceof Point) {
/* 221 */           Point point = (Point)sourceGeometry;
/* 222 */           accepted = processPoint(point, osmTags, index);
/*     */         } else {
/* 224 */           throw new RuntimeException("No class for " + sourceGeometry.getClass());
/*     */         } 
/* 226 */         if (accepted && 
/* 227 */           index % 2000 == 0)
/* 228 */           log.info("Feature: " + index + " of " + count); 
/*     */       } 
/* 233 */     } catch (Throwable e) {
/* 234 */       e.printStackTrace();
/* 235 */       log.error("Could not load shapefile ", e);
/* 236 */       return false;
/*     */     } finally {
/* 238 */       if (iterator != null)
/* 239 */         iterator.close(); 
/* 240 */       if (fileDataStore != null)
/* 241 */         fileDataStore.dispose(); 
/*     */     } 
/* 244 */     return true;
/*     */   }
/*     */   
/*     */   private boolean processPolygon(Polygon poly) {
/*     */     Geometry geometry;
/* 252 */     boolean accepted = false;
/* 253 */     LinearRing2D linearRing2D = new LinearRing2D();
/* 254 */     for (int x = 0; x < poly.getExteriorRing().getNumPoints(); x++) {
/* 255 */       Point2D point = projectPoint(poly.getExteriorRing().getPointN(x).getX(), poly.getExteriorRing().getPointN(x).getY());
/* 256 */       linearRing2D.addVertex(point);
/* 259 */       if (this.dsfTile.containsPoint(point))
/* 260 */         accepted = true; 
/*     */     } 
/* 266 */     if (!accepted)
/* 267 */       return false; 
/* 270 */     Polygon polygon = poly;
/* 271 */     if (this.delegate != null) {
/* 272 */       if (!this.delegate.acceptGeometry(GeomUtils.linearRingToJTSPolygon(linearRing2D)))
/* 273 */         return false; 
/* 275 */       this.rules = this.delegate.selectRule(linearRing2D, this.rules, this.dsfTile);
/* 277 */       geometry = this.delegate.processGeometry((Geometry)poly);
/*     */     } 
/* 280 */     for (int d = 0; d < geometry.getNumGeometries(); d++) {
/* 281 */       Polygon polygon1 = (Polygon)geometry.getGeometryN(d);
/* 283 */       if (geometry.getNumGeometries() != poly.getNumGeometries()) {
/* 284 */         linearRing2D = new LinearRing2D();
/* 285 */         for (int i = 0; i < polygon1.getExteriorRing().getNumPoints(); i++) {
/* 286 */           Point2D point = projectPoint(polygon1.getExteriorRing().getPointN(i).getX(), polygon1.getExteriorRing().getPointN(i).getY());
/* 287 */           linearRing2D.addVertex(point);
/*     */         } 
/*     */       } 
/* 291 */       List<LinearRing2D> inner = new ArrayList<>();
/* 292 */       for (int z = 0; z < polygon1.getNumInteriorRing(); z++) {
/* 293 */         LinearRing2D innerRing = new LinearRing2D();
/* 294 */         for (int i = 0; i < polygon1.getInteriorRingN(z).getNumPoints(); i++) {
/* 295 */           Point2D point = projectPoint(polygon1.getInteriorRingN(z).getPointN(i).getX(), polygon1.getInteriorRingN(z).getPointN(i).getY());
/* 296 */           innerRing.addVertex(point);
/*     */         } 
/* 298 */         inner.add(innerRing);
/*     */       } 
/* 301 */       if (inner.size() > 0) {
/* 302 */         addMultiPolygon(linearRing2D, inner);
/*     */       } else {
/* 304 */         addSimplePolygon(linearRing2D);
/*     */       } 
/*     */     } 
/* 307 */     return true;
/*     */   }
/*     */   
/*     */   private boolean processPoint(Point point, HashMap<String, String> osmTags, int index) {
/* 316 */     Point2D point2D = projectPoint(point.getX(), point.getY());
/* 317 */     if (!this.dsfTile.containsPoint(point2D))
/* 318 */       return false; 
/* 320 */     this.dsfTile.addNode(osmTags, index, point2D, this.rules);
/* 321 */     return true;
/*     */   }
/*     */   
/*     */   private boolean processLineString(LineString lineString) {
/* 329 */     boolean accepted = false;
/* 330 */     LinearRing2D linearRing2D = new LinearRing2D();
/* 331 */     for (int x = 0; x < lineString.getNumPoints(); x++) {
/* 332 */       Point2D point = projectPoint(lineString.getPointN(x).getX(), lineString.getPointN(x).getY());
/* 333 */       linearRing2D.addVertex(point);
/* 334 */       if (this.dsfTile.containsPoint(point))
/* 335 */         accepted = true; 
/*     */     } 
/* 340 */     if (!accepted)
/* 341 */       return false; 
/* 344 */     if (this.delegate != null) {
/* 345 */       if (!this.delegate.acceptGeometry((Geometry)lineString))
/* 346 */         return false; 
/* 348 */       this.rules = this.delegate.selectRule(linearRing2D, this.rules, this.dsfTile);
/*     */     } 
/* 354 */     addSimplePolygon(linearRing2D);
/* 355 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isClosed(LinearRing2D linearRing2D) {
/* 359 */     if (linearRing2D.vertexNumber() == 0)
/* 360 */       return false; 
/* 362 */     return linearRing2D.vertex(linearRing2D.vertexNumber() - 1).equals(linearRing2D.firstPoint());
/*     */   }
/*     */   
/*     */   private Point2D projectPoint(double x, double y) {
/* 367 */     if (this.delegate != null)
/* 368 */       return this.delegate.reprojectPoint(x, y); 
/* 370 */     return new Point2D(x, y);
/*     */   }
/*     */   
/*     */   private void addSimplePolygon(LinearRing2D outer) {
/* 378 */     OSMPolygon shape = new OSMPolygon(outer);
/* 379 */     shape.setIdentifier(this.count);
/* 380 */     this.count++;
/* 381 */     this.dsfTile.addSimplePolygon(shape, shape.getIdentifier(), this.rules);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 386 */     return this.filename;
/*     */   }
/*     */   
/*     */   public List<Point2D> getBoundCoords() {
/* 390 */     if (this.bounds == null) {
/* 391 */       readShapeFile(this.filename);
/* 392 */       if (this.bounds == null)
/* 393 */         return null; 
/*     */     } 
/* 396 */     ArrayList<Point2D> points = new ArrayList<>();
/* 398 */     int minX = (int)FastMath.floor(this.bounds.getMinX() - 1.0D);
/* 399 */     int maxX = (int)FastMath.floor(this.bounds.getMaxX() + 1.0D);
/* 400 */     int minY = (int)FastMath.floor(this.bounds.getMinY() - 1.0D);
/* 401 */     int maxY = (int)FastMath.floor(this.bounds.getMaxY() + 1.0D);
/* 404 */     for (int x = minX; x <= maxX; x++) {
/* 405 */       for (int y = minY; y <= maxY; y++)
/* 406 */         points.add(new Point2D(x, y)); 
/*     */     } 
/* 409 */     return points;
/*     */   }
/*     */   
/*     */   public boolean isValid() {
/* 413 */     return this.valid;
/*     */   }
/*     */   
/*     */   private void addMultiPolygon(LinearRing2D outer, List<LinearRing2D> inner) {
/* 421 */     OSMPolygon shape = new OSMPolygon(outer);
/* 422 */     shape.setIdentifier(this.count);
/* 423 */     Long relationId = Long.valueOf(this.count);
/* 424 */     this.count++;
/* 426 */     RelationInfo relationInfo = new RelationInfo();
/* 427 */     relationInfo.rules = this.rules;
/* 429 */     this.dsfTile.addMultiPolygon(relationId, shape, relationInfo, null, WayInfo.OUTER.byteValue(), this.rules);
/* 430 */     for (LinearRing2D item : inner) {
/* 431 */       OSMPolygon innerShape = new OSMPolygon(item);
/* 432 */       innerShape.setIdentifier(this.count);
/* 433 */       this.count++;
/* 434 */       this.dsfTile.addMultiPolygon(relationId, innerShape, relationInfo, null, WayInfo.INNER.byteValue(), null);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static interface Delegate {
/*     */     HashMap<String, String> getTagsFor(Feature param1Feature);
/*     */     
/*     */     Point2D reprojectPoint(double param1Double1, double param1Double2);
/*     */     
/*     */     boolean acceptGeometry(Geometry param1Geometry);
/*     */     
/*     */     List<AcceptingRule> selectRule(LinearRing2D param1LinearRing2D, List<AcceptingRule> param1List, DSFTile param1DSFTile);
/*     */     
/*     */     Geometry processGeometry(Geometry param1Geometry);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Parser\ShapeFileProcessor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */