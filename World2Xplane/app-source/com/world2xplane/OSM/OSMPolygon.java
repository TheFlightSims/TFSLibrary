/*     */ package com.world2xplane.OSM;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.impl.CoordinateArraySequenceFactory;
/*     */ import com.world2xplane.DataStore.WayInfo;
/*     */ import com.world2xplane.Geom.ConcaveHull.ConcaveHullBuilder;
/*     */ import com.world2xplane.Geom.GeomUtils;
/*     */ import com.world2xplane.Rules.Rule;
/*     */ import com.world2xplane.XPlane.DSFTile;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.line.LineSegment2D;
/*     */ import math.geom2d.polygon.LinearRing2D;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class OSMPolygon {
/*  50 */   private static Logger log = LoggerFactory.getLogger(OSMPolygon.class);
/*     */   
/*     */   private LinearRing2D shape;
/*     */   
/*     */   private Float height;
/*     */   
/*     */   private Integer customFacade;
/*     */   
/*     */   private Byte role;
/*     */   
/*     */   private Rule rule;
/*     */   
/*  57 */   private long identifier = 0L;
/*     */   
/*     */   private Set<String> regions;
/*     */   
/*     */   private LinearRing2D minRectangle;
/*     */   
/*     */   private Float longestSide;
/*     */   
/*     */   private Float shortestSide;
/*     */   
/*     */   private Float area;
/*     */   
/*     */   private Boolean isRound;
/*     */   
/*     */   private Double radius;
/*     */   
/*     */   private Envelope envelope;
/*     */   
/*     */   private Geometry geometry;
/*     */   
/*     */   private Set<String> areaIdentifiers;
/*     */   
/*     */   private Point centroid;
/*     */   
/*     */   private String region;
/*     */   
/*     */   private boolean buildingPart;
/*     */   
/*     */   private boolean multipolygon;
/*     */   
/*     */   private Boolean rectangular;
/*     */   
/*  77 */   private ArrayList<DSFTile.NamedArea> namedAreas = new ArrayList<>();
/*     */   
/*     */   public OSMPolygon() {
/*  81 */     this.shape = new LinearRing2D();
/*  82 */     this.minRectangle = null;
/*  83 */     this.longestSide = null;
/*  84 */     this.shortestSide = null;
/*  85 */     this.rectangular = null;
/*  86 */     this.area = null;
/*  87 */     this.envelope = null;
/*     */   }
/*     */   
/*     */   public OSMPolygon(LinearRing2D shape) {
/*  91 */     this.shape = shape;
/*     */   }
/*     */   
/*     */   public static OSMPolygon copyWithoutShape(OSMPolygon outer) {
/*  97 */     OSMPolygon osmPolygon = new OSMPolygon();
/*  98 */     osmPolygon.height = outer.height;
/*  99 */     osmPolygon.customFacade = outer.customFacade;
/* 100 */     osmPolygon.shape = new LinearRing2D();
/* 101 */     osmPolygon.role = outer.role;
/* 102 */     osmPolygon.identifier = outer.identifier;
/* 103 */     osmPolygon.minRectangle = outer.minRectangle;
/* 104 */     osmPolygon.longestSide = outer.longestSide;
/* 105 */     osmPolygon.shortestSide = outer.shortestSide;
/* 106 */     osmPolygon.rectangular = outer.rectangular;
/* 107 */     osmPolygon.area = outer.area;
/* 108 */     osmPolygon.isRound = outer.isRound;
/* 109 */     osmPolygon.radius = outer.radius;
/* 110 */     osmPolygon.buildingPart = outer.buildingPart;
/* 111 */     osmPolygon.areaIdentifiers = outer.areaIdentifiers;
/* 112 */     osmPolygon.envelope = outer.envelope;
/* 113 */     osmPolygon.centroid = outer.centroid;
/* 114 */     return osmPolygon;
/*     */   }
/*     */   
/*     */   public LinearRing2D getShape() {
/* 118 */     return this.shape;
/*     */   }
/*     */   
/*     */   public void setShape(LinearRing2D shape) {
/* 122 */     this.shape = shape;
/* 124 */     this.minRectangle = null;
/* 125 */     this.longestSide = null;
/* 126 */     this.shortestSide = null;
/* 127 */     this.area = null;
/* 128 */     this.isRound = null;
/* 129 */     this.radius = null;
/* 130 */     this.envelope = null;
/* 131 */     this.centroid = null;
/* 132 */     this.rectangular = null;
/* 133 */     this.geometry = null;
/*     */   }
/*     */   
/*     */   public Float getHeight() {
/* 138 */     return this.height;
/*     */   }
/*     */   
/*     */   public void setHeight(Float height) {
/* 142 */     this.height = height;
/*     */   }
/*     */   
/*     */   public void checkAndClose() {
/* 147 */     if (!this.shape.vertex(this.shape.vertexNumber() - 1).equals(this.shape.firstPoint()))
/* 148 */       this.shape.addVertex(this.shape.firstPoint()); 
/* 150 */     this.geometry = null;
/*     */   }
/*     */   
/*     */   public void setCounterClockwise() {
/* 154 */     this.shape = GeomUtils.setCounterClockwise(this.shape);
/*     */   }
/*     */   
/*     */   public void setClockwise() {
/* 159 */     this.shape = GeomUtils.setClockwise(this.shape);
/*     */   }
/*     */   
/*     */   public OSMPolygon clone() {
/* 163 */     OSMPolygon polygon = new OSMPolygon();
/* 164 */     polygon.setShape(this.shape.clone());
/* 165 */     polygon.setHeight(this.height);
/* 166 */     polygon.setCustomFacade(this.customFacade);
/* 167 */     polygon.setRole(this.role);
/* 168 */     polygon.identifier = this.identifier;
/* 169 */     polygon.minRectangle = this.minRectangle;
/* 170 */     polygon.longestSide = this.longestSide;
/* 171 */     polygon.shortestSide = this.shortestSide;
/* 172 */     polygon.area = this.area;
/* 173 */     polygon.isRound = this.isRound;
/* 174 */     polygon.radius = this.radius;
/* 175 */     polygon.areaIdentifiers = this.areaIdentifiers;
/* 176 */     polygon.centroid = this.centroid;
/* 177 */     polygon.namedAreas = this.namedAreas;
/* 178 */     polygon.setBuildingPart(this.buildingPart);
/* 179 */     return polygon;
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 185 */     if (other == null || !(other instanceof OSMPolygon))
/* 186 */       return false; 
/* 188 */     return ((OSMPolygon)other).shape.equals(this.shape);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 193 */     if (this.shape == null)
/* 194 */       return super.hashCode(); 
/* 196 */     return this.shape.hashCode();
/*     */   }
/*     */   
/*     */   public int vertexNumber() {
/* 200 */     if (this.shape == null)
/* 201 */       return 0; 
/* 203 */     return this.shape.vertexNumber();
/*     */   }
/*     */   
/*     */   public void addVertex(Point2D point2D) {
/* 207 */     this.shape.addVertex(point2D);
/*     */   }
/*     */   
/*     */   public Point2D firstPoint() {
/* 211 */     return this.shape.firstPoint();
/*     */   }
/*     */   
/*     */   public Point2D vertex(int i) {
/* 215 */     return this.shape.vertex(i);
/*     */   }
/*     */   
/*     */   public Point2D lastPoint() {
/* 219 */     return this.shape.vertex(this.shape.vertexNumber() - 1);
/*     */   }
/*     */   
/*     */   public Collection<LineSegment2D> edges() {
/* 223 */     return this.shape.edges();
/*     */   }
/*     */   
/*     */   public LineSegment2D firstEdge() {
/* 227 */     return this.shape.firstEdge();
/*     */   }
/*     */   
/*     */   public boolean isClosed() {
/* 231 */     if (this.shape.vertexNumber() == 0)
/* 232 */       return false; 
/* 234 */     return this.shape.vertex(this.shape.vertexNumber() - 1).equals(this.shape.firstPoint());
/*     */   }
/*     */   
/*     */   public Integer getCustomFacade() {
/* 240 */     return this.customFacade;
/*     */   }
/*     */   
/*     */   public void setCustomFacade(Integer customFacade) {
/* 244 */     this.customFacade = customFacade;
/*     */   }
/*     */   
/*     */   public Byte getRole() {
/* 248 */     return this.role;
/*     */   }
/*     */   
/*     */   public void setRole(Byte role) {
/* 252 */     this.role = role;
/*     */   }
/*     */   
/*     */   public Rule getRule() {
/* 256 */     return this.rule;
/*     */   }
/*     */   
/*     */   public void setRule(Rule rule) {
/* 260 */     this.rule = rule;
/*     */   }
/*     */   
/*     */   public void setIdentifier(long identifier) {
/* 264 */     this.identifier = identifier;
/*     */   }
/*     */   
/*     */   public Long getIdentifier() {
/* 268 */     return Long.valueOf(this.identifier);
/*     */   }
/*     */   
/*     */   public void setIdentifier(Long identifier) {
/* 272 */     this.identifier = identifier.longValue();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 276 */     if (this.role != null) {
/* 277 */       if (this.role == WayInfo.INNER)
/* 278 */         return "Inner: " + this.identifier; 
/* 280 */       if (this.role == WayInfo.OUTER)
/* 281 */         return "Outer: " + this.identifier; 
/* 283 */       if (this.role == WayInfo.PART)
/* 284 */         return "Part: " + this.identifier; 
/*     */     } 
/* 287 */     return "Way: " + this.identifier;
/*     */   }
/*     */   
/*     */   public double getPerimeter() {
/* 291 */     double length = 0.0D;
/* 292 */     for (int x = 0; x < vertexNumber() - 1; x++)
/* 293 */       length += GeomUtils.distanceInMeters(new LineSegment2D(vertex(x).y(), vertex(x).x(), vertex(x + 1).y(), vertex(x + 1).x())); 
/* 295 */     return length;
/*     */   }
/*     */   
/*     */   public void analyseShape() {
/* 299 */     if (this.shape == null) {
/* 300 */       this.shortestSide = Float.valueOf(0.0F);
/* 301 */       this.longestSide = Float.valueOf(0.0F);
/* 302 */       this.area = Float.valueOf(0.0F);
/*     */       return;
/*     */     } 
/* 305 */     if (this.shape.vertexNumber() > 1) {
/* 306 */       if (this.minRectangle == null)
/* 307 */         if (this.shape.vertexNumber() != 5) {
/* 308 */           this.minRectangle = GeomUtils.getMinimumRectangle(this.shape);
/*     */         } else {
/* 310 */           this.minRectangle = this.shape;
/*     */         }  
/*     */       try {
/* 314 */         if (this.shortestSide == null)
/* 315 */           this.shortestSide = Float.valueOf((float)GeomUtils.distanceInMeters(GeomUtils.getShortestLine(this.minRectangle))); 
/* 317 */         if (this.longestSide == null)
/* 318 */           this.longestSide = Float.valueOf((float)GeomUtils.distanceInMeters(GeomUtils.getLongestLine(this.minRectangle))); 
/* 320 */         if (this.area == null)
/* 321 */           this.area = Float.valueOf((float)FastMath.abs(GeomUtils.areaInMeters(this.minRectangle))); 
/* 323 */       } catch (Exception e) {
/* 324 */         this.shortestSide = Float.valueOf(0.0F);
/* 325 */         this.longestSide = Float.valueOf(0.0F);
/* 326 */         this.area = Float.valueOf(0.0F);
/* 327 */         log.error("Analysis Failed for Way #" + this.identifier, e);
/*     */       } 
/*     */     } else {
/* 331 */       this.shortestSide = Float.valueOf(0.0F);
/* 332 */       this.longestSide = Float.valueOf(0.0F);
/* 333 */       this.area = Float.valueOf(0.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   public LinearRing2D getMinRectangle() {
/* 338 */     if (this.minRectangle == null)
/* 339 */       analyseShape(); 
/* 341 */     return this.minRectangle;
/*     */   }
/*     */   
/*     */   public LinearRing2D getConcaveHull() {
/* 345 */     Geometry bob = ConcaveHullBuilder.getConcaveHull(getGeometry().getCoordinates(), 1.0D);
/* 346 */     return GeomUtils.polygonToLinearRing2D(bob);
/*     */   }
/*     */   
/*     */   public static Envelope enclosingEnvelopFromGeometry(Geometry geometry) {
/* 352 */     Envelope envelope = new Envelope();
/* 353 */     Geometry enclosingGeometry = geometry.getEnvelope();
/* 354 */     Coordinate[] enclosingCoordinates = enclosingGeometry.getCoordinates();
/* 355 */     for (Coordinate c : enclosingCoordinates)
/* 356 */       envelope.expandToInclude(c); 
/* 358 */     return envelope;
/*     */   }
/*     */   
/*     */   public void setMinRectangle(LinearRing2D minRectangle) {
/* 362 */     this.minRectangle = minRectangle;
/*     */   }
/*     */   
/*     */   public Float getLongestSide() {
/* 366 */     if (this.longestSide == null)
/* 367 */       analyseShape(); 
/* 369 */     return this.longestSide;
/*     */   }
/*     */   
/*     */   public void setLongestSide(Float longestSide) {
/* 373 */     this.longestSide = longestSide;
/*     */   }
/*     */   
/*     */   public Float getShortestSide() {
/* 377 */     if (this.shortestSide == null)
/* 378 */       analyseShape(); 
/* 380 */     return this.shortestSide;
/*     */   }
/*     */   
/*     */   public void setShortestSide(Float shortestSide) {
/* 384 */     this.shortestSide = shortestSide;
/*     */   }
/*     */   
/*     */   public Float getArea() {
/* 388 */     if (this.area == null)
/* 389 */       analyseShape(); 
/* 391 */     return this.area;
/*     */   }
/*     */   
/*     */   public void setArea(Float area) {
/* 395 */     this.area = area;
/*     */   }
/*     */   
/*     */   public boolean isRound() {
/* 399 */     if (this.isRound == null)
/* 400 */       this.isRound = Boolean.valueOf(GeomUtils.isRound(this)); 
/* 401 */     return this.isRound.booleanValue();
/*     */   }
/*     */   
/*     */   public Double getRadius() {
/* 405 */     if (this.radius == null)
/* 406 */       this.radius = GeomUtils.radius(this); 
/* 408 */     return this.radius;
/*     */   }
/*     */   
/*     */   public Envelope getEnvelope() {
/* 412 */     if (this.shape.vertexNumber() > 1) {
/* 414 */       if (this.envelope == null) {
/* 415 */         Geometry min = GeomUtils.linearRingToJTSPolygon(getMinRectangle());
/* 416 */         this.envelope = min.getEnvelopeInternal();
/*     */       } 
/* 418 */       return this.envelope;
/*     */     } 
/* 420 */     return null;
/*     */   }
/*     */   
/*     */   public Geometry getGeometry() {
/* 427 */     if (this.geometry == null) {
/* 428 */       if (getShape().vertexNumber() == 2 || !isClosed()) {
/* 429 */         CoordinateSequence coordSeq = CoordinateArraySequenceFactory.instance().create(GeomUtils.getCoordsFromRing(getShape()));
/* 431 */         GeometryFactory geometryFactory = new GeometryFactory();
/* 432 */         LineString linearRing = geometryFactory.createLineString(coordSeq);
/* 433 */         this.geometry = (Geometry)linearRing;
/* 434 */         return this.geometry;
/*     */       } 
/* 436 */       if (getShape().vertexNumber() < 3)
/* 437 */         return null; 
/* 439 */       this.geometry = GeomUtils.linearRingToJTSPolygon(getShape());
/*     */     } 
/* 441 */     return this.geometry;
/*     */   }
/*     */   
/*     */   public void setAreaIdentifiers(List<String> idents) {
/* 445 */     this.areaIdentifiers = new HashSet<>();
/* 446 */     this.areaIdentifiers.addAll(idents);
/*     */   }
/*     */   
/*     */   public Set<String> getAreaIdentifiers() {
/* 450 */     return this.areaIdentifiers;
/*     */   }
/*     */   
/*     */   public void addAreaIdentifier(String identifier) {
/* 454 */     if (this.areaIdentifiers == null)
/* 455 */       setAreaIdentifiers(new ArrayList<>()); 
/* 457 */     if (!this.areaIdentifiers.contains(identifier))
/* 458 */       this.areaIdentifiers.add(identifier); 
/*     */   }
/*     */   
/*     */   public Point2D getCentroid() {
/* 464 */     if (this.centroid != null)
/* 465 */       return new Point2D(this.centroid.getX(), this.centroid.getY()); 
/* 467 */     if (getGeometry() == null)
/* 468 */       return null; 
/* 471 */     this.centroid = getGeometry().getCentroid();
/*     */     try {
/* 473 */       return new Point2D(this.centroid.getX(), this.centroid.getY());
/* 474 */     } catch (Exception e) {
/* 475 */       e.printStackTrace();
/* 476 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Set<String> getRegions() {
/* 481 */     return this.regions;
/*     */   }
/*     */   
/*     */   public void setRegions(Set<String> regions) {
/* 485 */     this.regions = regions;
/*     */   }
/*     */   
/*     */   public void setRegion(String region) {
/* 489 */     this.region = region;
/*     */   }
/*     */   
/*     */   public String getRegion() {
/* 493 */     return this.region;
/*     */   }
/*     */   
/*     */   public boolean isBuildingPart() {
/* 497 */     return this.buildingPart;
/*     */   }
/*     */   
/*     */   public void setBuildingPart(boolean buildingPart) {
/* 501 */     this.buildingPart = buildingPart;
/*     */   }
/*     */   
/*     */   public void clearGeometry() {
/* 505 */     this.geometry = null;
/*     */   }
/*     */   
/*     */   public boolean isRectangular() {
/* 510 */     if (this.rectangular != null)
/* 511 */       return this.rectangular.booleanValue(); 
/* 514 */     if (getShape() != null && getShape().vertexNumber() < 6) {
/* 515 */       this.rectangular = Boolean.valueOf(true);
/* 516 */       return true;
/*     */     } 
/* 519 */     if (this.area != null) {
/* 521 */       double realArea = Math.abs(GeomUtils.areaInMeters(getShape()));
/* 523 */       if (realArea >= (this.area.floatValue() - 80.0F) && realArea <= (this.area.floatValue() + 80.0F)) {
/* 524 */         this.rectangular = Boolean.valueOf(true);
/* 525 */         return true;
/*     */       } 
/*     */     } 
/* 529 */     this.rectangular = Boolean.valueOf(false);
/* 530 */     return false;
/*     */   }
/*     */   
/*     */   public void setMultipolygon(boolean multipolygon) {
/* 534 */     this.multipolygon = multipolygon;
/*     */   }
/*     */   
/*     */   public boolean isMultipolygon() {
/* 538 */     return this.multipolygon;
/*     */   }
/*     */   
/*     */   public void addArea(DSFTile.NamedArea area) {
/* 542 */     this.namedAreas.add(area);
/*     */   }
/*     */   
/*     */   public ArrayList<DSFTile.NamedArea> getNamedAreas() {
/* 546 */     return this.namedAreas;
/*     */   }
/*     */   
/*     */   public void reverse() {
/* 550 */     LinearRing2D reversed = new LinearRing2D();
/* 551 */     for (int x = vertexNumber() - 1; x > -1; x--)
/* 552 */       reversed.addVertex(vertex(x)); 
/* 554 */     this.shape = reversed;
/*     */   }
/*     */   
/*     */   public void setGeometry(Geometry geometry) {
/* 559 */     this.geometry = geometry;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\OSM\OSMPolygon.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */