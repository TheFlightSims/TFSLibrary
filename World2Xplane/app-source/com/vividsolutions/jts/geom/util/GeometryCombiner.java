/*     */ package com.vividsolutions.jts.geom.util;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class GeometryCombiner {
/*     */   private GeometryFactory geomFactory;
/*     */   
/*     */   public static Geometry combine(Collection geoms) {
/*  62 */     GeometryCombiner combiner = new GeometryCombiner(geoms);
/*  63 */     return combiner.combine();
/*     */   }
/*     */   
/*     */   public static Geometry combine(Geometry g0, Geometry g1) {
/*  75 */     GeometryCombiner combiner = new GeometryCombiner(createList(g0, g1));
/*  76 */     return combiner.combine();
/*     */   }
/*     */   
/*     */   public static Geometry combine(Geometry g0, Geometry g1, Geometry g2) {
/*  89 */     GeometryCombiner combiner = new GeometryCombiner(createList(g0, g1, g2));
/*  90 */     return combiner.combine();
/*     */   }
/*     */   
/*     */   private static List createList(Object obj0, Object obj1) {
/* 102 */     List<Object> list = new ArrayList();
/* 103 */     list.add(obj0);
/* 104 */     list.add(obj1);
/* 105 */     return list;
/*     */   }
/*     */   
/*     */   private static List createList(Object obj0, Object obj1, Object obj2) {
/* 117 */     List<Object> list = new ArrayList();
/* 118 */     list.add(obj0);
/* 119 */     list.add(obj1);
/* 120 */     list.add(obj2);
/* 121 */     return list;
/*     */   }
/*     */   
/*     */   private boolean skipEmpty = false;
/*     */   
/*     */   private Collection inputGeoms;
/*     */   
/*     */   public GeometryCombiner(Collection geoms) {
/* 135 */     this.geomFactory = extractFactory(geoms);
/* 136 */     this.inputGeoms = geoms;
/*     */   }
/*     */   
/*     */   public static GeometryFactory extractFactory(Collection<Geometry> geoms) {
/* 146 */     if (geoms.isEmpty())
/* 147 */       return null; 
/* 148 */     return ((Geometry)geoms.iterator().next()).getFactory();
/*     */   }
/*     */   
/*     */   public Geometry combine() {
/* 159 */     List elems = new ArrayList();
/* 160 */     for (Iterator<Geometry> i = this.inputGeoms.iterator(); i.hasNext(); ) {
/* 161 */       Geometry g = i.next();
/* 162 */       extractElements(g, elems);
/*     */     } 
/* 165 */     if (elems.size() == 0) {
/* 166 */       if (this.geomFactory != null)
/* 168 */         return (Geometry)this.geomFactory.createGeometryCollection(null); 
/* 170 */       return null;
/*     */     } 
/* 173 */     return this.geomFactory.buildGeometry(elems);
/*     */   }
/*     */   
/*     */   private void extractElements(Geometry geom, List<Geometry> elems) {
/* 178 */     if (geom == null)
/*     */       return; 
/* 181 */     for (int i = 0; i < geom.getNumGeometries(); i++) {
/* 182 */       Geometry elemGeom = geom.getGeometryN(i);
/* 183 */       if (!this.skipEmpty || !elemGeom.isEmpty())
/* 185 */         elems.add(elemGeom); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geo\\util\GeometryCombiner.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */