/*     */ package com.world2xplane.Rules.ObjectRules;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineSegment;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.MultiPoint;
/*     */ import com.vividsolutions.jts.geom.prep.PreparedGeometry;
/*     */ import com.vividsolutions.jts.geom.prep.PreparedGeometryFactory;
/*     */ import com.world2xplane.Geom.GeomUtils;
/*     */ import com.world2xplane.Network.Road;
/*     */ import com.world2xplane.Network.RoadNetwork;
/*     */ import com.world2xplane.OSM.OSMPolygon;
/*     */ import com.world2xplane.Rules.ObjectRule;
/*     */ import com.world2xplane.Rules.ObjectTagRule;
/*     */ import com.world2xplane.Rules.Rule;
/*     */ import gnu.trove.iterator.TIntObjectIterator;
/*     */ import gnu.trove.map.hash.TIntObjectHashMap;
/*     */ import java.util.ArrayList;
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
/*     */ public class BestFitList {
/*  51 */   private static Logger log = LoggerFactory.getLogger(BestFitList.class);
/*     */   
/*     */   public static boolean dEquals(double a, double b, double epsilon) {
/*  56 */     return (a == b) ? true : ((Math.abs(a - b) < epsilon));
/*     */   }
/*     */   
/*     */   public ObjectList.ObjectListEntry getBestFit(ObjectRule rule, OSMPolygon shape, Set<ObjectList.ObjectListEntry> objects, Set<String> regions, float width, float length, Float height, boolean restrictHeights, boolean restrictShape, Rule.Delegate delegate) {
/*  75 */     LinearRing2D minimumRectangle = shape.getMinRectangle();
/*  76 */     RoadNetwork roadNetwork = delegate.getRoadNetwork();
/*  78 */     double sideFacingWall = 0.0D;
/*  80 */     List<Road> collidingRoads = null;
/*  81 */     if (roadNetwork != null) {
/*  83 */       Double distance = null;
/*  84 */       Geometry minRectangle = shape.getGeometry();
/*  86 */       GeometryFactory geometryFactory = new GeometryFactory();
/*  87 */       collidingRoads = roadNetwork.getCollidingRoads(minRectangle.buffer(delegate.getMetreSize() * 25.0D), null);
/*  89 */       if (collidingRoads.size() > 0)
/*  90 */         for (int x = 0; x < shape.vertexNumber() - 1; x++) {
/*  92 */           Point2D e1 = shape.vertex(x);
/*  93 */           Point2D e2 = (x < shape.vertexNumber() - 1) ? shape.vertex(x + 1) : shape.vertex(0);
/*  94 */           LineSegment lineSegment = new LineSegment(new Coordinate(e1.x(), e1.y()), new Coordinate(e2.x(), e2.y()));
/*  96 */           Coordinate centre = lineSegment.midPoint();
/*  99 */           double bearing = GeomUtils.getBearing(lineSegment.p0.y, lineSegment.p0.x, lineSegment.p1.y, lineSegment.p1.x);
/* 101 */           Point2D p1 = GeomUtils.translateFastLatLon(new Point2D(centre.x, centre.y), bearing - 90.0D, 25.0D);
/* 102 */           Point2D p2 = GeomUtils.translateFastLatLon(new Point2D(centre.x, centre.y), bearing + 90.0D, 25.0D);
/* 106 */           LineString interesection = geometryFactory.createLineString(new Coordinate[] { new Coordinate(p1.x(), p1.y()), new Coordinate(p2.x(), p2.y()) });
/* 108 */           PreparedGeometry preparedGeometry = PreparedGeometryFactory.prepare((Geometry)interesection);
/* 110 */           for (Road item : collidingRoads) {
/* 112 */             if (preparedGeometry.intersects(item.lineString.getGeometry())) {
/* 114 */               Geometry intersectionPoint = interesection.intersection(item.lineString.getGeometry());
/* 116 */               if (intersectionPoint != null && intersectionPoint instanceof com.vividsolutions.jts.geom.Point) {
/* 118 */                 LineSegment lengthLine = new LineSegment(centre, intersectionPoint.getCoordinate());
/* 119 */                 double d = lengthLine.getLength();
/* 120 */                 if (distance == null || d < distance.doubleValue()) {
/* 121 */                   sideFacingWall = GeomUtils.distanceInMeters(new LineSegment2D(e1, e2));
/* 122 */                   distance = Double.valueOf(d);
/*     */                 } 
/*     */                 break;
/*     */               } 
/* 128 */               if (intersectionPoint != null && intersectionPoint instanceof MultiPoint) {
/* 129 */                 for (int z = 0; z < intersectionPoint.getNumPoints(); z++) {
/* 130 */                   LineSegment lengthLine = new LineSegment(centre, ((MultiPoint)intersectionPoint).getGeometryN(z).getCoordinate());
/* 131 */                   double d = lengthLine.getLength();
/* 132 */                   if (distance == null || d < distance.doubleValue()) {
/* 133 */                     sideFacingWall = GeomUtils.distanceInMeters(new LineSegment2D(e1, e2));
/* 134 */                     distance = Double.valueOf(d);
/*     */                   } 
/*     */                 } 
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         }  
/*     */     } 
/* 148 */     TIntObjectHashMap<List<BestFitItem>> objectRuleScores = new TIntObjectHashMap();
/* 152 */     float random = Rule.getRandomNumber(0, 100);
/* 153 */     Set<ObjectTagRule> tagRules = new HashSet<>();
/* 154 */     if (rule.getObjectTagRules() != null)
/* 155 */       for (ObjectTagRule item : rule.getObjectTagRules()) {
/* 156 */         if ((item.getRegion() == null || item.getRegion().length() == 0 || (item.getRegion() != null && shape.getRegions().contains(item.getRegion()))) && 
/* 157 */           random >= item.getMin() && random <= item.getMax())
/* 158 */           tagRules.add(item); 
/*     */       }  
/* 166 */     boolean rectangular = shape.isRectangular();
/* 167 */     for (ObjectList.ObjectListEntry item : objects) {
/* 170 */       if (restrictShape && "SQ".equals(item.getShape()) && 
/* 171 */         !rectangular)
/*     */         continue; 
/* 176 */       boolean fits = false;
/* 177 */       if (item.widthRange.containsFloat(width) && item.lengthRange.containsFloat(length))
/* 178 */         fits = true; 
/* 180 */       if (item.lengthRange.containsFloat(width) && item.widthRange.containsFloat(length))
/* 181 */         fits = true; 
/* 184 */       if (!fits)
/*     */         continue; 
/* 189 */       if (restrictHeights) {
/* 190 */         if (item.minheight != null && item.minheight.floatValue() > 0.0F && height.floatValue() < item.minheight.floatValue())
/*     */           continue; 
/* 193 */         if (item.maxheight != null && item.maxheight.floatValue() > 0.0F && height.floatValue() > item.maxheight.floatValue())
/*     */           continue; 
/*     */       } 
/* 199 */       BestFitItem bestFitItem = new BestFitItem(item);
/* 202 */       double score = 0.0D;
/* 203 */       if (item.widthRange.containsFloat(width) && item.lengthRange.containsFloat(length)) {
/* 204 */         double maxAreaX = item.widthRange.getMaximumFloat();
/* 205 */         double minAreaX = item.widthRange.getMinimumFloat();
/* 206 */         double maxAreaY = item.lengthRange.getMaximumFloat();
/* 207 */         double minAreaY = item.lengthRange.getMinimumFloat();
/* 209 */         double buildingWidth = minAreaX + (maxAreaX - minAreaX) / 2.0D;
/* 210 */         double buildingLength = minAreaY + (maxAreaY - minAreaY) / 2.0D;
/* 212 */         score = FastMath.abs(width - buildingWidth) + FastMath.abs(length - buildingLength);
/* 214 */         bestFitItem.setDimensionScore(score);
/* 216 */         if (dEquals(sideFacingWall, buildingWidth, 0.5D))
/* 217 */           bestFitItem.addPoint(); 
/* 221 */       } else if (item.widthRange.containsFloat(length) && item.lengthRange.containsFloat(width)) {
/* 222 */         double maxAreaX = item.lengthRange.getMaximumFloat();
/* 223 */         double minAreaX = item.lengthRange.getMinimumFloat();
/* 224 */         double maxAreaY = item.widthRange.getMaximumFloat();
/* 225 */         double minAreaY = item.widthRange.getMinimumFloat();
/* 227 */         double buildingWidth = minAreaX + (maxAreaX - minAreaX) / 2.0D;
/* 228 */         double buildingLength = minAreaY + (maxAreaY - minAreaY) / 2.0D;
/* 231 */         score = FastMath.abs(width - buildingWidth) + FastMath.abs(length - buildingLength);
/* 233 */         bestFitItem.setDimensionScore(score);
/* 235 */         if (dEquals(sideFacingWall, buildingLength, 0.5D))
/* 236 */           bestFitItem.addPoint(); 
/*     */       } 
/* 242 */       if (regions != null && item.getRegions() != null && item.getRegions().size() > 0 && score != 0.0D)
/* 245 */         for (String region : regions) {
/* 246 */           if (item.getRegions().contains(region)) {
/* 247 */             bestFitItem.addPoint();
/* 248 */             bestFitItem.setRegion(region);
/*     */           } 
/*     */         }  
/* 253 */       if (tagRules != null)
/* 254 */         for (ObjectTagRule objectTagRule : tagRules)
/* 255 */           objectTagRule.applyScore(item, bestFitItem, shape, random);  
/* 260 */       if (item.levels > 0 && shape.getHeight() != null) {
/* 261 */         if (shape.getHeight().floatValue() >= (item.levels * 3 - 1) && shape.getHeight().floatValue() <= (item.levels * 3 + 1)) {
/* 262 */           bestFitItem.addPoint();
/* 263 */           bestFitItem.addPoint();
/* 264 */         } else if (shape.getHeight().floatValue() <= (item.levels * 3 + 1)) {
/* 265 */           bestFitItem.addPoint();
/*     */         } 
/* 268 */         if ((item.levels * 3) > shape.getHeight().floatValue()) {
/* 269 */           float difference = (item.levels * 3) - shape.getHeight().floatValue();
/* 270 */           difference /= 3.0F;
/* 271 */           for (int x = 0; x < difference; x++)
/* 272 */             bestFitItem.removePoint(); 
/*     */         } 
/*     */       } 
/* 278 */       if (objectRuleScores.get(bestFitItem.points) == null)
/* 279 */         objectRuleScores.put(bestFitItem.points, new ArrayList()); 
/* 281 */       ((List<BestFitItem>)objectRuleScores.get(bestFitItem.points)).add(bestFitItem);
/*     */     } 
/* 286 */     if (objectRuleScores.size() > 0) {
/* 287 */       Integer highestScore = null;
/* 288 */       TIntObjectIterator<List<BestFitItem>> interator = objectRuleScores.iterator();
/* 289 */       while (interator.hasNext()) {
/* 290 */         interator.advance();
/* 291 */         if (highestScore == null)
/* 292 */           highestScore = Integer.valueOf(interator.key()); 
/* 294 */         if (interator.key() >= highestScore.intValue())
/* 295 */           highestScore = Integer.valueOf(interator.key()); 
/*     */       } 
/* 299 */       if (highestScore != null) {
/* 300 */         List<BestFitItem> items = (List<BestFitItem>)objectRuleScores.get(highestScore.intValue());
/* 301 */         if (items.size() == 1) {
/* 302 */           if (((BestFitItem)items.get(0)).getRegion() != null && shape != null)
/* 303 */             shape.setRegion(((BestFitItem)items.get(items.size() - 1)).getRegion()); 
/* 307 */           return ((BestFitItem)items.get(0)).getObjectListEntry();
/*     */         } 
/* 310 */         BestFitItem obj = items.get(Rule.getRandomNumber(0, items.size()));
/* 311 */         if (obj.getRegion() != null && shape != null)
/* 312 */           shape.setRegion(obj.getRegion()); 
/* 317 */         return obj.getObjectListEntry();
/*     */       } 
/*     */     } 
/* 325 */     return null;
/*     */   }
/*     */   
/*     */   private boolean compareAngle(float angle, double bestFitWall) {
/* 329 */     if ((angle - 5.0F) >= bestFitWall && (angle + 5.0F) <= bestFitWall)
/* 330 */       return true; 
/* 332 */     bestFitWall += 180.0D;
/* 333 */     bestFitWall %= 360.0D;
/* 334 */     if ((angle - 5.0F) >= bestFitWall && (angle + 5.0F) <= bestFitWall)
/* 335 */       return true; 
/* 337 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Rules\ObjectRules\BestFitList.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */