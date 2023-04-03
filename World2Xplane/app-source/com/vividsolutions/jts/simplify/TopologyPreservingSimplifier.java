/*     */ package com.vividsolutions.jts.simplify;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryComponentFilter;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.util.GeometryTransformer;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class TopologyPreservingSimplifier {
/*     */   private Geometry inputGeom;
/*     */   
/*     */   public static Geometry simplify(Geometry geom, double distanceTolerance) {
/*  93 */     TopologyPreservingSimplifier tss = new TopologyPreservingSimplifier(geom);
/*  94 */     tss.setDistanceTolerance(distanceTolerance);
/*  95 */     return tss.getResultGeometry();
/*     */   }
/*     */   
/*  99 */   private TaggedLinesSimplifier lineSimplifier = new TaggedLinesSimplifier();
/*     */   
/*     */   private Map linestringMap;
/*     */   
/*     */   public TopologyPreservingSimplifier(Geometry inputGeom) {
/* 104 */     this.inputGeom = inputGeom;
/*     */   }
/*     */   
/*     */   public void setDistanceTolerance(double distanceTolerance) {
/* 117 */     if (distanceTolerance < 0.0D)
/* 118 */       throw new IllegalArgumentException("Tolerance must be non-negative"); 
/* 119 */     this.lineSimplifier.setDistanceTolerance(distanceTolerance);
/*     */   }
/*     */   
/*     */   public Geometry getResultGeometry() {
/* 125 */     if (this.inputGeom.isEmpty())
/* 125 */       return (Geometry)this.inputGeom.clone(); 
/* 127 */     this.linestringMap = new HashMap<>();
/* 128 */     this.inputGeom.apply(new LineStringMapBuilderFilter());
/* 129 */     this.lineSimplifier.simplify(this.linestringMap.values());
/* 130 */     Geometry result = (new LineStringTransformer()).transform(this.inputGeom);
/* 131 */     return result;
/*     */   }
/*     */   
/*     */   class LineStringTransformer extends GeometryTransformer {
/*     */     protected CoordinateSequence transformCoordinates(CoordinateSequence coords, Geometry parent) {
/* 139 */       if (coords.size() == 0)
/* 139 */         return null; 
/* 141 */       if (parent instanceof LineString) {
/* 142 */         TaggedLineString taggedLine = (TaggedLineString)TopologyPreservingSimplifier.this.linestringMap.get(parent);
/* 143 */         return createCoordinateSequence(taggedLine.getResultCoordinates());
/*     */       } 
/* 146 */       return super.transformCoordinates(coords, parent);
/*     */     }
/*     */   }
/*     */   
/*     */   class LineStringMapBuilderFilter implements GeometryComponentFilter {
/*     */     public void filter(Geometry geom) {
/* 171 */       if (geom instanceof LineString) {
/* 172 */         LineString line = (LineString)geom;
/* 174 */         if (line.isEmpty())
/*     */           return; 
/* 176 */         int minSize = line.isClosed() ? 4 : 2;
/* 177 */         TaggedLineString taggedLine = new TaggedLineString(line, minSize);
/* 178 */         TopologyPreservingSimplifier.this.linestringMap.put(line, taggedLine);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\simplify\TopologyPreservingSimplifier.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */