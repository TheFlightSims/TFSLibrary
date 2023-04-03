/*     */ package org.geotools.filter.visitor;
/*     */ 
/*     */ import org.opengis.filter.spatial.BBOX;
/*     */ import org.opengis.filter.spatial.Beyond;
/*     */ import org.opengis.filter.spatial.Contains;
/*     */ import org.opengis.filter.spatial.Crosses;
/*     */ import org.opengis.filter.spatial.DWithin;
/*     */ import org.opengis.filter.spatial.Disjoint;
/*     */ import org.opengis.filter.spatial.Equals;
/*     */ import org.opengis.filter.spatial.Intersects;
/*     */ import org.opengis.filter.spatial.Overlaps;
/*     */ import org.opengis.filter.spatial.Touches;
/*     */ import org.opengis.filter.spatial.Within;
/*     */ 
/*     */ public class SpatialFilterVisitor extends DefaultFilterVisitor {
/*     */   boolean hasSpatialFilter = false;
/*     */   
/*     */   public boolean hasSpatialFilter() {
/*  46 */     return this.hasSpatialFilter;
/*     */   }
/*     */   
/*     */   public void reset() {
/*  53 */     this.hasSpatialFilter = false;
/*     */   }
/*     */   
/*     */   public Object visit(BBOX filter, Object data) {
/*  57 */     this.hasSpatialFilter = true;
/*  58 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Beyond filter, Object data) {
/*  62 */     this.hasSpatialFilter = true;
/*  63 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Contains filter, Object data) {
/*  67 */     this.hasSpatialFilter = true;
/*  68 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Crosses filter, Object data) {
/*  72 */     this.hasSpatialFilter = true;
/*  73 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Disjoint filter, Object data) {
/*  77 */     this.hasSpatialFilter = true;
/*  78 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(DWithin filter, Object data) {
/*  82 */     this.hasSpatialFilter = true;
/*  83 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Equals filter, Object data) {
/*  87 */     this.hasSpatialFilter = true;
/*  88 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Intersects filter, Object data) {
/*  92 */     this.hasSpatialFilter = true;
/*  93 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Overlaps filter, Object data) {
/*  97 */     this.hasSpatialFilter = true;
/*  98 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Touches filter, Object data) {
/* 102 */     this.hasSpatialFilter = true;
/* 103 */     return data;
/*     */   }
/*     */   
/*     */   public Object visit(Within filter, Object data) {
/* 107 */     this.hasSpatialFilter = true;
/* 108 */     return data;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\visitor\SpatialFilterVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */