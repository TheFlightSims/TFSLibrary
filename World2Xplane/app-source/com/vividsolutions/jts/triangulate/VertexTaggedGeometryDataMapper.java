/*     */ package com.vividsolutions.jts.triangulate;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class VertexTaggedGeometryDataMapper {
/*  55 */   private Map coordDataMap = new TreeMap<>();
/*     */   
/*     */   public void loadSourceGeometries(Collection geoms) {
/*  64 */     for (Iterator<Geometry> i = geoms.iterator(); i.hasNext(); ) {
/*  65 */       Geometry geom = i.next();
/*  66 */       loadVertices(geom.getCoordinates(), geom.getUserData());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void loadSourceGeometries(Geometry geomColl) {
/*  72 */     for (int i = 0; i < geomColl.getNumGeometries(); i++) {
/*  73 */       Geometry geom = geomColl.getGeometryN(i);
/*  74 */       loadVertices(geom.getCoordinates(), geom.getUserData());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void loadVertices(Coordinate[] pts, Object data) {
/*  80 */     for (int i = 0; i < pts.length; i++)
/*  81 */       this.coordDataMap.put(pts[i], data); 
/*     */   }
/*     */   
/*     */   public List getCoordinates() {
/*  87 */     return new ArrayList(this.coordDataMap.keySet());
/*     */   }
/*     */   
/*     */   public void transferData(Geometry targetGeom) {
/* 101 */     for (int i = 0; i < targetGeom.getNumGeometries(); i++) {
/* 102 */       Geometry geom = targetGeom.getGeometryN(i);
/* 103 */       Coordinate vertexKey = (Coordinate)geom.getUserData();
/* 104 */       if (vertexKey != null)
/* 105 */         geom.setUserData(this.coordDataMap.get(vertexKey)); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\triangulate\VertexTaggedGeometryDataMapper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */