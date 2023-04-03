/*     */ package com.world2xplane.XPlane;
/*     */ 
/*     */ import com.world2xplane.Geom.GeomUtils;
/*     */ import com.world2xplane.OSM.OSMPolygon;
/*     */ import com.world2xplane.Rules.ObjectDefinitionStore;
/*     */ import com.world2xplane.Rules.ObjectRule;
/*     */ import com.world2xplane.Rules.ObjectRules.ObjectList;
/*     */ import com.world2xplane.Rules.Rule;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.line.LineSegment2D;
/*     */ import math.geom2d.polygon.LinearRing2D;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class DSFObject {
/*  40 */   private static Logger log = LoggerFactory.getLogger(DSFObject.class);
/*     */   
/*     */   private byte requiredLevel;
/*     */   
/*     */   OSMPolygon polygon;
/*     */   
/*     */   public Integer objectDefinitionNumber;
/*     */   
/*     */   public DSFObjectDefinition generatedObject;
/*     */   
/*     */   public Point2D position;
/*     */   
/*     */   public Float angle;
/*     */   
/*     */   public Rule rule;
/*     */   
/*     */   public String wayTracker;
/*     */   
/*     */   public DSFObject(Rule rule) {
/*  45 */     this.rule = rule;
/*     */   }
/*     */   
/*     */   public void getOriginAndAngle(ObjectDefinitionStore.ObjectDefinition objectDefinition) {
/*  61 */     if (!(this.rule instanceof ObjectRule)) {
/*  62 */       this.position = null;
/*  63 */       this.angle = null;
/*     */       return;
/*     */     } 
/*  67 */     ObjectRule objectRule = (ObjectRule)this.rule;
/*  69 */     if (objectRule.isCircular()) {
/*  70 */       this.position = GeomUtils.getPolygonCenter(this.polygon.getShape());
/*  71 */       this.angle = Float.valueOf(0.0F);
/*     */       return;
/*     */     } 
/*  76 */     if (objectRule.isClosedOverride()) {
/*  77 */       this.position = GeomUtils.getPolygonCenter(this.polygon.getShape());
/*  78 */       this.angle = Float.valueOf(0.0F);
/*     */       return;
/*     */     } 
/*  83 */     this.polygon.setCounterClockwise();
/*  85 */     LinearRing2D minimumRectangle = this.polygon.getMinRectangle();
/*  86 */     if (this.position == null)
/*  87 */       this.position = GeomUtils.getPolygonCenter(minimumRectangle); 
/*  90 */     double minAreaY = objectRule.getMinAreaY();
/*  91 */     double maxAreaY = objectRule.getMaxAreaY();
/*  92 */     double minAreaX = objectRule.getMinAreaX();
/*  93 */     double maxAreaX = objectRule.getMaxAreaX();
/*  95 */     ObjectList.ObjectListEntry object = null;
/*  96 */     if (objectRule.getBestFitList() != null) {
/* 100 */       object = objectRule.getBestFitList().getObjectEntry(objectDefinition.getPath());
/* 101 */       if (object == null)
/*     */         return; 
/* 104 */       minAreaY = object.getMinAreaY() - 0.1D;
/* 105 */       maxAreaY = object.getMaxAreaY() + 0.1D;
/* 106 */       minAreaX = object.getMinAreaX() - 0.1D;
/* 107 */       maxAreaX = object.getMaxAreaX() + 0.1D;
/*     */     } 
/* 112 */     if (minimumRectangle == null)
/*     */       return; 
/*     */     int x;
/* 117 */     for (x = 0; x < minimumRectangle.vertexNumber(); x++) {
/* 118 */       Point2D vertex = minimumRectangle.vertex(x);
/* 119 */       Point2D next = minimumRectangle.vertex((x < minimumRectangle.vertexNumber() - 1) ? (x + 1) : 0);
/* 120 */       double distance = GeomUtils.distanceInMeters(new LineSegment2D(vertex, next));
/* 121 */       if (distance >= minAreaX && distance <= maxAreaX) {
/* 123 */         this.angle = Float.valueOf((float)(90.0D + GeomUtils.getBearing((vertex.x() < next.x()) ? vertex : next, (vertex.x() < next.x()) ? next : vertex)));
/*     */         break;
/*     */       } 
/*     */     } 
/* 128 */     if (this.angle == null)
/* 130 */       for (x = 0; x < minimumRectangle.vertexNumber(); x++) {
/* 131 */         Point2D vertex = minimumRectangle.vertex(x);
/* 132 */         Point2D next = minimumRectangle.vertex((x < minimumRectangle.vertexNumber() - 1) ? (x + 1) : 0);
/* 133 */         double distance = GeomUtils.distanceInMeters(new LineSegment2D(vertex, next));
/* 134 */         if (distance >= minAreaY && distance <= maxAreaY) {
/* 136 */           this.angle = Float.valueOf((float)GeomUtils.getBearing((vertex.y() < next.y()) ? vertex : next, (vertex.y() < next.y()) ? next : vertex));
/*     */           break;
/*     */         } 
/*     */       }  
/* 145 */     if (this.angle == null && object != null && minAreaX != -1.0D) {
/* 146 */       x = 0;
/* 146 */       if (x < minimumRectangle.vertexNumber()) {
/* 147 */         Point2D vertex = minimumRectangle.vertex(x);
/* 148 */         Point2D next = minimumRectangle.vertex((x < minimumRectangle.vertexNumber() - 1) ? (x + 1) : 0);
/* 149 */         this.angle = Float.valueOf((float)GeomUtils.getBearing((vertex.y() < next.y()) ? vertex : next, (vertex.y() < next.y()) ? next : vertex));
/*     */       } 
/*     */     } 
/* 154 */     if (this.angle == null && object != null && minAreaX != -1.0D) {
/* 155 */       log.error("Could not determine rotation angle for object " + getPolygon().getIdentifier());
/*     */       return;
/*     */     } 
/* 160 */     if (this.angle == null)
/*     */       return; 
/*     */   }
/*     */   
/*     */   private int getIndexOfBottomLeft(LinearRing2D polygon) {
/* 167 */     Point2D point = polygon.firstPoint();
/* 168 */     double minX = point.x();
/* 169 */     double minY = point.y();
/* 170 */     for (int x = 0; x < polygon.vertexNumber(); x++) {
/* 171 */       Point2D item = polygon.vertex(x);
/* 172 */       if (item.x() < minX)
/* 173 */         minX = item.x(); 
/* 175 */       if (item.y() < minY)
/* 176 */         minY = item.y(); 
/*     */     } 
/* 180 */     for (int count = 0; count < polygon.vertexNumber(); count++) {
/* 181 */       Point2D item = polygon.vertex(count);
/* 182 */       if (item.x() == minX && item.y() == minY)
/* 183 */         return count; 
/*     */     } 
/* 186 */     return 0;
/*     */   }
/*     */   
/*     */   public static double getBearing(Point2D topLeft, Point2D topRight, Point2D bottomRight, Point2D bottomLeft) {
/* 216 */     Point2D diffs = new Point2D(topLeft.x() - bottomLeft.x(), topLeft.y() - bottomLeft.y());
/* 219 */     double rotation = FastMath.atan(diffs.x() / diffs.y()) * 180.0D / Math.PI;
/* 222 */     if (diffs.y() < 0.0D) {
/* 223 */       rotation += 180.0D;
/* 227 */     } else if (diffs.x() < 0.0D) {
/* 228 */       rotation += 360.0D;
/*     */     } 
/* 231 */     return rotation;
/*     */   }
/*     */   
/*     */   public static Double latLongDistance(double lat1, double lon1, double lat2, double lon2) {
/* 236 */     double earthRadius = 3958.75D;
/* 237 */     double dLat = FastMath.toRadians(lat2 - lat1);
/* 238 */     double dLng = FastMath.toRadians(lon2 - lon1);
/* 239 */     double a = FastMath.sin(dLat / 2.0D) * FastMath.sin(dLat / 2.0D) + FastMath.cos(FastMath.toRadians(lat1)) * FastMath.cos(FastMath.toRadians(lat2)) * FastMath.sin(dLng / 2.0D) * FastMath.sin(dLng / 2.0D);
/* 243 */     double c = 2.0D * FastMath.atan2(FastMath.sqrt(a), FastMath.sqrt(1.0D - a));
/* 244 */     double dist = earthRadius * c;
/* 246 */     int meterConversion = 1609;
/* 248 */     return new Double(dist * meterConversion);
/*     */   }
/*     */   
/*     */   public OSMPolygon getPolygon() {
/* 253 */     return this.polygon;
/*     */   }
/*     */   
/*     */   public void setPolygon(OSMPolygon polygon) {
/* 257 */     this.polygon = polygon;
/*     */   }
/*     */   
/*     */   public Integer getObjectDefinitionNumber() {
/* 262 */     return this.objectDefinitionNumber;
/*     */   }
/*     */   
/*     */   public void setObjectDefinitionNumber(Integer objectDefinitionNumber) {
/* 266 */     this.objectDefinitionNumber = objectDefinitionNumber;
/*     */   }
/*     */   
/*     */   public Point2D getPosition() {
/* 270 */     return this.position;
/*     */   }
/*     */   
/*     */   public void setPosition(Point2D position) {
/* 274 */     this.position = position;
/*     */   }
/*     */   
/*     */   public Float getAngle() {
/* 279 */     return this.angle;
/*     */   }
/*     */   
/*     */   public void setAngle(Float angle) {
/* 283 */     this.angle = angle;
/*     */   }
/*     */   
/*     */   public void setRequiredLevel(byte requiredLevel) {
/* 288 */     this.requiredLevel = requiredLevel;
/*     */   }
/*     */   
/*     */   public byte getRequiredLevel() {
/* 292 */     return this.requiredLevel;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\XPlane\DSFObject.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */