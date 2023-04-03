/*     */ package com.vividsolutions.jts.operation.union;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.geom.util.GeometryCombiner;
/*     */ import com.vividsolutions.jts.geom.util.PolygonExtracter;
/*     */ import com.vividsolutions.jts.index.strtree.STRtree;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class CascadedPolygonUnion {
/*     */   private Collection inputPolys;
/*     */   
/*     */   public static Geometry union(Collection polys) {
/*  79 */     CascadedPolygonUnion op = new CascadedPolygonUnion(polys);
/*  80 */     return op.union();
/*     */   }
/*     */   
/*  84 */   private GeometryFactory geomFactory = null;
/*     */   
/*     */   private static final int STRTREE_NODE_CAPACITY = 4;
/*     */   
/*     */   public CascadedPolygonUnion(Collection polys) {
/*  94 */     this.inputPolys = polys;
/*  96 */     if (this.inputPolys == null)
/*  97 */       this.inputPolys = new ArrayList(); 
/*     */   }
/*     */   
/*     */   public Geometry union() {
/* 125 */     if (this.inputPolys == null)
/* 126 */       throw new IllegalStateException("union() method cannot be called twice"); 
/* 127 */     if (this.inputPolys.isEmpty())
/* 128 */       return null; 
/* 129 */     this.geomFactory = ((Geometry)this.inputPolys.iterator().next()).getFactory();
/* 138 */     STRtree index = new STRtree(4);
/* 139 */     for (Iterator<Geometry> i = this.inputPolys.iterator(); i.hasNext(); ) {
/* 140 */       Geometry item = i.next();
/* 141 */       index.insert(item.getEnvelopeInternal(), item);
/*     */     } 
/* 144 */     this.inputPolys = null;
/* 146 */     List itemTree = index.itemsTree();
/* 148 */     Geometry unionAll = unionTree(itemTree);
/* 149 */     return unionAll;
/*     */   }
/*     */   
/*     */   private Geometry unionTree(List geomTree) {
/* 158 */     List geoms = reduceToGeometries(geomTree);
/* 160 */     Geometry union = binaryUnion(geoms);
/* 165 */     return union;
/*     */   }
/*     */   
/*     */   private Geometry repeatedUnion(List geoms) {
/* 178 */     Geometry union = null;
/* 179 */     for (Iterator<Geometry> i = geoms.iterator(); i.hasNext(); ) {
/* 180 */       Geometry g = i.next();
/* 181 */       if (union == null) {
/* 182 */         union = (Geometry)g.clone();
/*     */         continue;
/*     */       } 
/* 184 */       union = union.union(g);
/*     */     } 
/* 186 */     return union;
/*     */   }
/*     */   
/*     */   private Geometry bufferUnion(List<Geometry> geoms) {
/* 191 */     GeometryFactory factory = ((Geometry)geoms.get(0)).getFactory();
/* 192 */     Geometry gColl = factory.buildGeometry(geoms);
/* 193 */     Geometry unionAll = gColl.buffer(0.0D);
/* 194 */     return unionAll;
/*     */   }
/*     */   
/*     */   private Geometry bufferUnion(Geometry g0, Geometry g1) {
/* 199 */     GeometryFactory factory = g0.getFactory();
/* 200 */     GeometryCollection geometryCollection = factory.createGeometryCollection(new Geometry[] { g0, g1 });
/* 201 */     Geometry unionAll = geometryCollection.buffer(0.0D);
/* 202 */     return unionAll;
/*     */   }
/*     */   
/*     */   private Geometry binaryUnion(List geoms) {
/* 214 */     return binaryUnion(geoms, 0, geoms.size());
/*     */   }
/*     */   
/*     */   private Geometry binaryUnion(List geoms, int start, int end) {
/* 228 */     if (end - start <= 1) {
/* 229 */       Geometry geometry = getGeometry(geoms, start);
/* 230 */       return unionSafe(geometry, null);
/*     */     } 
/* 232 */     if (end - start == 2)
/* 233 */       return unionSafe(getGeometry(geoms, start), getGeometry(geoms, start + 1)); 
/* 237 */     int mid = (end + start) / 2;
/* 238 */     Geometry g0 = binaryUnion(geoms, start, mid);
/* 239 */     Geometry g1 = binaryUnion(geoms, mid, end);
/* 240 */     return unionSafe(g0, g1);
/*     */   }
/*     */   
/*     */   private static Geometry getGeometry(List<Geometry> list, int index) {
/* 255 */     if (index >= list.size())
/* 255 */       return null; 
/* 256 */     return list.get(index);
/*     */   }
/*     */   
/*     */   private List reduceToGeometries(List geomTree) {
/* 268 */     List<Geometry> geoms = new ArrayList();
/* 269 */     for (Iterator i = geomTree.iterator(); i.hasNext(); ) {
/* 270 */       Object o = i.next();
/* 271 */       Geometry geom = null;
/* 272 */       if (o instanceof List) {
/* 273 */         geom = unionTree((List)o);
/* 275 */       } else if (o instanceof Geometry) {
/* 276 */         geom = (Geometry)o;
/*     */       } 
/* 278 */       geoms.add(geom);
/*     */     } 
/* 280 */     return geoms;
/*     */   }
/*     */   
/*     */   private Geometry unionSafe(Geometry g0, Geometry g1) {
/* 294 */     if (g0 == null && g1 == null)
/* 295 */       return null; 
/* 297 */     if (g0 == null)
/* 298 */       return (Geometry)g1.clone(); 
/* 299 */     if (g1 == null)
/* 300 */       return (Geometry)g0.clone(); 
/* 302 */     return unionOptimized(g0, g1);
/*     */   }
/*     */   
/*     */   private Geometry unionOptimized(Geometry g0, Geometry g1) {
/* 307 */     Envelope g0Env = g0.getEnvelopeInternal();
/* 308 */     Envelope g1Env = g1.getEnvelopeInternal();
/* 310 */     if (!g0Env.intersects(g1Env)) {
/* 312 */       Geometry combo = GeometryCombiner.combine(g0, g1);
/* 315 */       return combo;
/*     */     } 
/* 320 */     if (g0.getNumGeometries() <= 1 && g1.getNumGeometries() <= 1)
/* 321 */       return unionActual(g0, g1); 
/* 326 */     Envelope commonEnv = g0Env.intersection(g1Env);
/* 327 */     return unionUsingEnvelopeIntersection(g0, g1, commonEnv);
/*     */   }
/*     */   
/*     */   private Geometry unionUsingEnvelopeIntersection(Geometry g0, Geometry g1, Envelope common) {
/* 351 */     List<Geometry> disjointPolys = new ArrayList();
/* 353 */     Geometry g0Int = extractByEnvelope(common, g0, disjointPolys);
/* 354 */     Geometry g1Int = extractByEnvelope(common, g1, disjointPolys);
/* 357 */     Geometry union = unionActual(g0Int, g1Int);
/* 359 */     disjointPolys.add(union);
/* 360 */     Geometry overallUnion = GeometryCombiner.combine(disjointPolys);
/* 362 */     return overallUnion;
/*     */   }
/*     */   
/*     */   private Geometry extractByEnvelope(Envelope env, Geometry geom, List<Geometry> disjointGeoms) {
/* 368 */     List<Geometry> intersectingGeoms = new ArrayList();
/* 369 */     for (int i = 0; i < geom.getNumGeometries(); i++) {
/* 370 */       Geometry elem = geom.getGeometryN(i);
/* 371 */       if (elem.getEnvelopeInternal().intersects(env)) {
/* 372 */         intersectingGeoms.add(elem);
/*     */       } else {
/* 374 */         disjointGeoms.add(elem);
/*     */       } 
/*     */     } 
/* 376 */     return this.geomFactory.buildGeometry(intersectingGeoms);
/*     */   }
/*     */   
/*     */   private Geometry unionActual(Geometry g0, Geometry g1) {
/* 398 */     return restrictToPolygons(g0.union(g1));
/*     */   }
/*     */   
/*     */   private static Geometry restrictToPolygons(Geometry g) {
/* 416 */     if (g instanceof com.vividsolutions.jts.geom.Polygonal)
/* 417 */       return g; 
/* 419 */     List<Polygon> polygons = PolygonExtracter.getPolygons(g);
/* 420 */     if (polygons.size() == 1)
/* 421 */       return (Geometry)polygons.get(0); 
/* 422 */     return (Geometry)g.getFactory().createMultiPolygon(GeometryFactory.toPolygonArray(polygons));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operatio\\union\CascadedPolygonUnion.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */