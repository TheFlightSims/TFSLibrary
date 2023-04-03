/*     */ package com.world2xplane.XPlane;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.world2xplane.Rules.AutogenStringRule;
/*     */ import com.world2xplane.Rules.GeneratorStore;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import math.geom2d.Point2D;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class AutogenObject {
/*  49 */   private static Logger log = LoggerFactory.getLogger(Autogen.class);
/*     */   
/*     */   private AutogenStringRule rule;
/*     */   
/*     */   private int objectDefinitionNumber;
/*     */   
/*     */   private Geometry originalObject;
/*     */   
/*  54 */   private Set<Short> roadTypes = new HashSet<>();
/*     */   
/*     */   public static class Contour {
/*  59 */     private final List<Point2D> points = new ArrayList<>();
/*     */     
/*     */     private final boolean spawnBuildings;
/*     */     
/*     */     public Contour(boolean spawnBuildings) {
/*  63 */       this.spawnBuildings = spawnBuildings;
/*     */     }
/*     */     
/*     */     public void addPoint(Point2D point2D) {
/*  69 */       if (this.points.size() > 0) {
/*  70 */         Point2D lastPoint = this.points.get(this.points.size() - 1);
/*  71 */         if (lastPoint.equals(point2D))
/*     */           return; 
/*     */       } 
/*  75 */       this.points.add(point2D);
/*     */     }
/*     */     
/*     */     public List<Point2D> getPoints() {
/*  79 */       return this.points;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Edge {
/*     */     private final Point2D p1;
/*     */     
/*     */     private final Point2D p2;
/*     */     
/*     */     private final boolean spawnBuildings;
/*     */     
/*     */     public Edge(Point2D p1, Point2D p2, boolean spawnBuildings) {
/*  89 */       this.p1 = p1;
/*  90 */       this.p2 = p2;
/*  91 */       this.spawnBuildings = spawnBuildings;
/*     */     }
/*     */   }
/*     */   
/*  95 */   private List<Edge> edgeList = new ArrayList<>();
/*     */   
/*  96 */   private List<Contour> outerContours = new ArrayList<>();
/*     */   
/*  97 */   private List<Contour> innerContours = new ArrayList<>();
/*     */   
/*     */   public AutogenObject(AutogenStringRule rule, Geometry originalObject) {
/* 100 */     this.rule = rule;
/* 101 */     this.originalObject = originalObject;
/*     */   }
/*     */   
/*     */   public void addEdge(Edge edge) {
/* 105 */     this.edgeList.add(edge);
/*     */   }
/*     */   
/*     */   public void buildContours(DSFTile dsfTile) {
/* 111 */     this.outerContours.clear();
/* 112 */     this.innerContours.clear();
/* 115 */     if (this.edgeList.size() == 0)
/*     */       return; 
/* 121 */     Edge first = this.edgeList.get(0);
/* 122 */     Edge last = this.edgeList.get(this.edgeList.size() - 1);
/* 123 */     if (!first.p1.equals(last.p2)) {
/* 124 */       log.error("Autogen is unclosed and invalid");
/*     */       return;
/*     */     } 
/* 127 */     int firstIndex = -1;
/*     */     int x;
/* 128 */     for (x = 0; x < this.edgeList.size(); x++) {
/* 129 */       Edge edge = this.edgeList.get(x);
/* 130 */       if (edge.spawnBuildings) {
/* 131 */         firstIndex = x;
/*     */         break;
/*     */       } 
/*     */     } 
/* 135 */     if (firstIndex == -1)
/*     */       return; 
/* 142 */     if (firstIndex == 0)
/* 143 */       for (x = this.edgeList.size() - 1; x >= 0; ) {
/* 144 */         Edge edge = this.edgeList.get(x);
/* 145 */         if (edge.spawnBuildings) {
/* 146 */           firstIndex = x;
/*     */           x--;
/*     */         } 
/*     */       }  
/* 154 */     int start = firstIndex;
/* 155 */     Contour currentContour = null;
/*     */     do {
/* 158 */       Edge edge = this.edgeList.get(start);
/* 159 */       if (currentContour == null) {
/* 160 */         currentContour = new Contour(edge.spawnBuildings);
/* 161 */         currentContour.addPoint(edge.p1);
/* 162 */         currentContour.addPoint(edge.p2);
/* 163 */         if (edge.spawnBuildings) {
/* 164 */           this.outerContours.add(currentContour);
/*     */         } else {
/* 166 */           this.innerContours.add(currentContour);
/*     */         } 
/* 169 */       } else if (edge.spawnBuildings == currentContour.spawnBuildings) {
/* 170 */         currentContour.addPoint(edge.p1);
/* 171 */         currentContour.addPoint(edge.p2);
/*     */       } else {
/* 173 */         currentContour = new Contour(edge.spawnBuildings);
/* 174 */         currentContour.addPoint(edge.p1);
/* 175 */         currentContour.addPoint(edge.p2);
/* 176 */         if (edge.spawnBuildings) {
/* 177 */           this.outerContours.add(currentContour);
/*     */         } else {
/* 179 */           this.innerContours.add(currentContour);
/*     */         } 
/*     */       } 
/* 184 */       start++;
/* 185 */       if (start < this.edgeList.size())
/*     */         continue; 
/* 186 */       start = 0;
/* 188 */     } while (start != firstIndex);
/* 195 */     Point local = this.originalObject.getCentroid();
/* 196 */     Set<String> regions = GeneratorStore.getGeneratorStore().getRegionsAtPoint(new Point2D(local.getX(), local.getY()), dsfTile.possibleRegions);
/* 197 */     List<GeneratorStore.HotspotHit> hotspots = GeneratorStore.getGeneratorStore().getHotspots(local.buffer(dsfTile.getMetreXSize() * 4000.0D).getEnvelopeInternal(), local.getX(), local.getY(), 4000);
/* 199 */     if (hotspots == null || hotspots.size() == 0) {
/* 200 */       this.objectDefinitionNumber = this.rule.getDefinitionNumber(regions).intValue();
/*     */     } else {
/* 203 */       this.objectDefinitionNumber = this.rule.getDefinitionNumberFromHotspot(hotspots, regions).intValue();
/*     */     } 
/* 208 */     this.edgeList.clear();
/* 209 */     this.originalObject = null;
/*     */   }
/*     */   
/*     */   public AutogenStringRule getRule() {
/* 216 */     return this.rule;
/*     */   }
/*     */   
/*     */   public int getObjectDefinitionNumber() {
/* 220 */     return this.objectDefinitionNumber;
/*     */   }
/*     */   
/*     */   public List<Contour> getOuterContours() {
/* 224 */     return this.outerContours;
/*     */   }
/*     */   
/*     */   public List<Contour> getInnerContours() {
/* 228 */     return this.innerContours;
/*     */   }
/*     */   
/*     */   public void addRoadType(short roadType) {
/* 232 */     this.roadTypes.add(Short.valueOf(roadType));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\XPlane\AutogenObject.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */