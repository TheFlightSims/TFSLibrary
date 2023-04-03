/*     */ package com.vividsolutions.jts.geom.util;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFilter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class GeometryExtracter implements GeometryFilter {
/*     */   private Class clz;
/*     */   
/*     */   private List comps;
/*     */   
/*     */   protected static boolean isOfClass(Object o, Class clz) {
/*  50 */     return clz.isAssignableFrom(o.getClass());
/*     */   }
/*     */   
/*     */   public static List extract(Geometry geom, Class clz, List<Geometry> list) {
/*  63 */     if (isOfClass(geom, clz)) {
/*  64 */       list.add(geom);
/*  66 */     } else if (geom instanceof com.vividsolutions.jts.geom.GeometryCollection) {
/*  67 */       geom.apply(new GeometryExtracter(clz, list));
/*     */     } 
/*  71 */     return list;
/*     */   }
/*     */   
/*     */   public static List extract(Geometry geom, Class clz) {
/*  82 */     return extract(geom, clz, new ArrayList());
/*     */   }
/*     */   
/*     */   public GeometryExtracter(Class clz, List comps) {
/*  96 */     this.clz = clz;
/*  97 */     this.comps = comps;
/*     */   }
/*     */   
/*     */   public void filter(Geometry geom) {
/* 102 */     if (this.clz == null || isOfClass(geom, this.clz))
/* 102 */       this.comps.add(geom); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geo\\util\GeometryExtracter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */