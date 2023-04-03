/*     */ package com.vividsolutions.jts.algorithm;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ 
/*     */ public class InteriorPointArea {
/*     */   private GeometryFactory factory;
/*     */   
/*     */   private static double avg(double a, double b) {
/*  67 */     return (a + b) / 2.0D;
/*     */   }
/*     */   
/*  71 */   private Coordinate interiorPoint = null;
/*     */   
/*  72 */   private double maxWidth = 0.0D;
/*     */   
/*     */   public InteriorPointArea(Geometry g) {
/*  82 */     this.factory = g.getFactory();
/*  83 */     add(g);
/*     */   }
/*     */   
/*     */   public Coordinate getInteriorPoint() {
/*  93 */     return this.interiorPoint;
/*     */   }
/*     */   
/*     */   private void add(Geometry geom) {
/* 105 */     if (geom instanceof Polygon) {
/* 106 */       addPolygon(geom);
/* 108 */     } else if (geom instanceof GeometryCollection) {
/* 109 */       GeometryCollection gc = (GeometryCollection)geom;
/* 110 */       for (int i = 0; i < gc.getNumGeometries(); i++)
/* 111 */         add(gc.getGeometryN(i)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addPolygon(Geometry geometry) {
/*     */     Coordinate intPt;
/* 121 */     if (geometry.isEmpty())
/*     */       return; 
/* 125 */     double width = 0.0D;
/* 127 */     LineString bisector = horizontalBisector(geometry);
/* 128 */     if (bisector.getLength() == 0.0D) {
/* 129 */       width = 0.0D;
/* 130 */       intPt = bisector.getCoordinate();
/*     */     } else {
/* 133 */       Geometry intersections = bisector.intersection(geometry);
/* 134 */       Geometry widestIntersection = widestGeometry(intersections);
/* 135 */       width = widestIntersection.getEnvelopeInternal().getWidth();
/* 136 */       intPt = centre(widestIntersection.getEnvelopeInternal());
/*     */     } 
/* 138 */     if (this.interiorPoint == null || width > this.maxWidth) {
/* 139 */       this.interiorPoint = intPt;
/* 140 */       this.maxWidth = width;
/*     */     } 
/*     */   }
/*     */   
/*     */   private Geometry widestGeometry(Geometry geometry) {
/* 147 */     if (!(geometry instanceof GeometryCollection))
/* 148 */       return geometry; 
/* 150 */     return widestGeometry((GeometryCollection)geometry);
/*     */   }
/*     */   
/*     */   private Geometry widestGeometry(GeometryCollection gc) {
/* 154 */     if (gc.isEmpty())
/* 155 */       return (Geometry)gc; 
/* 158 */     Geometry widestGeometry = gc.getGeometryN(0);
/* 160 */     for (int i = 1; i < gc.getNumGeometries(); i++) {
/* 161 */       if (gc.getGeometryN(i).getEnvelopeInternal().getWidth() > widestGeometry.getEnvelopeInternal().getWidth())
/* 163 */         widestGeometry = gc.getGeometryN(i); 
/*     */     } 
/* 166 */     return widestGeometry;
/*     */   }
/*     */   
/*     */   protected LineString horizontalBisector(Geometry geometry) {
/* 170 */     Envelope envelope = geometry.getEnvelopeInternal();
/* 179 */     double bisectY = SafeBisectorFinder.getBisectorY((Polygon)geometry);
/* 180 */     return this.factory.createLineString(new Coordinate[] { new Coordinate(envelope.getMinX(), bisectY), new Coordinate(envelope.getMaxX(), bisectY) });
/*     */   }
/*     */   
/*     */   public static Coordinate centre(Envelope envelope) {
/* 192 */     return new Coordinate(avg(envelope.getMinX(), envelope.getMaxX()), avg(envelope.getMinY(), envelope.getMaxY()));
/*     */   }
/*     */   
/*     */   private static class SafeBisectorFinder {
/*     */     private Polygon poly;
/*     */     
/*     */     private double centreY;
/*     */     
/*     */     public static double getBisectorY(Polygon poly) {
/* 211 */       SafeBisectorFinder finder = new SafeBisectorFinder(poly);
/* 212 */       return finder.getBisectorY();
/*     */     }
/*     */     
/* 218 */     private double hiY = Double.MAX_VALUE;
/*     */     
/* 219 */     private double loY = -1.7976931348623157E308D;
/*     */     
/*     */     public SafeBisectorFinder(Polygon poly) {
/* 222 */       this.poly = poly;
/* 225 */       this.hiY = poly.getEnvelopeInternal().getMaxY();
/* 226 */       this.loY = poly.getEnvelopeInternal().getMinY();
/* 227 */       this.centreY = InteriorPointArea.avg(this.loY, this.hiY);
/*     */     }
/*     */     
/*     */     public double getBisectorY() {
/* 232 */       process(this.poly.getExteriorRing());
/* 233 */       for (int i = 0; i < this.poly.getNumInteriorRing(); i++)
/* 234 */         process(this.poly.getInteriorRingN(i)); 
/* 236 */       double bisectY = InteriorPointArea.avg(this.hiY, this.loY);
/* 237 */       return bisectY;
/*     */     }
/*     */     
/*     */     private void process(LineString line) {
/* 241 */       CoordinateSequence seq = line.getCoordinateSequence();
/* 242 */       for (int i = 0; i < seq.size(); i++) {
/* 243 */         double y = seq.getY(i);
/* 244 */         updateInterval(y);
/*     */       } 
/*     */     }
/*     */     
/*     */     private void updateInterval(double y) {
/* 249 */       if (y <= this.centreY) {
/* 250 */         if (y > this.loY)
/* 251 */           this.loY = y; 
/* 253 */       } else if (y > this.centreY && 
/* 254 */         y < this.hiY) {
/* 255 */         this.hiY = y;
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\algorithm\InteriorPointArea.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */