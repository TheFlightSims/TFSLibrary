/*     */ package com.vividsolutions.jtsexample.technique;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.prep.PreparedGeometry;
/*     */ import com.vividsolutions.jts.geom.prep.PreparedGeometryFactory;
/*     */ import com.vividsolutions.jts.index.SpatialIndex;
/*     */ import com.vividsolutions.jts.index.strtree.STRtree;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ class PreparedGeometryIndex {
/* 190 */   private SpatialIndex index = (SpatialIndex)new STRtree();
/*     */   
/*     */   public void insert(Collection geoms) {
/* 208 */     for (Iterator<Geometry> i = geoms.iterator(); i.hasNext(); ) {
/* 209 */       Geometry geom = i.next();
/* 210 */       this.index.insert(geom.getEnvelopeInternal(), PreparedGeometryFactory.prepare(geom));
/*     */     } 
/*     */   }
/*     */   
/*     */   public List query(Geometry g) {
/* 223 */     return this.index.query(g.getEnvelopeInternal());
/*     */   }
/*     */   
/*     */   public List intersects(Geometry g) {
/* 234 */     List<PreparedGeometry> result = new ArrayList();
/* 235 */     List candidates = query(g);
/* 236 */     for (Iterator<PreparedGeometry> it = candidates.iterator(); it.hasNext(); ) {
/* 237 */       PreparedGeometry prepGeom = it.next();
/* 238 */       if (prepGeom.intersects(g))
/* 239 */         result.add(prepGeom); 
/*     */     } 
/* 242 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jtsexample\technique\PreparedGeometryIndex.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */