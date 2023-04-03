/*     */ package com.vividsolutions.jts.operation.overlay.validate;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFilter;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ class PolygonalLineworkExtracter implements GeometryFilter {
/* 128 */   private List linework = new ArrayList();
/*     */   
/*     */   public void filter(Geometry g) {
/* 136 */     if (g instanceof Polygon) {
/* 137 */       Polygon poly = (Polygon)g;
/* 138 */       this.linework.add(poly.getExteriorRing());
/* 139 */       for (int i = 0; i < poly.getNumInteriorRing(); i++)
/* 140 */         this.linework.add(poly.getInteriorRingN(i)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public List getLinework() {
/* 150 */     return this.linework;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\overlay\validate\PolygonalLineworkExtracter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */