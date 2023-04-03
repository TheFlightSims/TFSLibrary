/*     */ package com.vividsolutions.jts.precision;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateFilter;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ 
/*     */ public class CommonBitsRemover {
/*     */   private Coordinate commonCoord;
/*     */   
/*  70 */   private CommonCoordinateFilter ccFilter = new CommonCoordinateFilter();
/*     */   
/*     */   public void add(Geometry geom) {
/*  86 */     geom.apply(this.ccFilter);
/*  87 */     this.commonCoord = this.ccFilter.getCommonCoordinate();
/*     */   }
/*     */   
/*     */   public Coordinate getCommonCoordinate() {
/*  93 */     return this.commonCoord;
/*     */   }
/*     */   
/*     */   public Geometry removeCommonBits(Geometry geom) {
/* 104 */     if (this.commonCoord.x == 0.0D && this.commonCoord.y == 0.0D)
/* 105 */       return geom; 
/* 106 */     Coordinate invCoord = new Coordinate(this.commonCoord);
/* 107 */     invCoord.x = -invCoord.x;
/* 108 */     invCoord.y = -invCoord.y;
/* 109 */     Translater trans = new Translater(invCoord);
/* 110 */     geom.apply(trans);
/* 111 */     geom.geometryChanged();
/* 112 */     return geom;
/*     */   }
/*     */   
/*     */   public void addCommonBits(Geometry geom) {
/* 123 */     Translater trans = new Translater(this.commonCoord);
/* 124 */     geom.apply(trans);
/* 125 */     geom.geometryChanged();
/*     */   }
/*     */   
/*     */   class CommonCoordinateFilter implements CoordinateFilter {
/* 131 */     private CommonBits commonBitsX = new CommonBits();
/*     */     
/* 132 */     private CommonBits commonBitsY = new CommonBits();
/*     */     
/*     */     public void filter(Coordinate coord) {
/* 136 */       this.commonBitsX.add(coord.x);
/* 137 */       this.commonBitsY.add(coord.y);
/*     */     }
/*     */     
/*     */     public Coordinate getCommonCoordinate() {
/* 142 */       return new Coordinate(this.commonBitsX.getCommon(), this.commonBitsY.getCommon());
/*     */     }
/*     */   }
/*     */   
/*     */   class Translater implements CoordinateFilter {
/* 151 */     Coordinate trans = null;
/*     */     
/*     */     public Translater(Coordinate trans) {
/* 155 */       this.trans = trans;
/*     */     }
/*     */     
/*     */     public void filter(Coordinate coord) {
/* 159 */       coord.x += this.trans.x;
/* 160 */       coord.y += this.trans.y;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\precision\CommonBitsRemover.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */