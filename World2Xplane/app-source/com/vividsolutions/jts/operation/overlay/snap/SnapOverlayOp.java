/*     */ package com.vividsolutions.jts.operation.overlay.snap;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.operation.overlay.OverlayOp;
/*     */ import com.vividsolutions.jts.precision.CommonBitsRemover;
/*     */ 
/*     */ public class SnapOverlayOp {
/*     */   public static Geometry overlayOp(Geometry g0, Geometry g1, int opCode) {
/*  56 */     SnapOverlayOp op = new SnapOverlayOp(g0, g1);
/*  57 */     return op.getResultGeometry(opCode);
/*     */   }
/*     */   
/*     */   public static Geometry intersection(Geometry g0, Geometry g1) {
/*  62 */     return overlayOp(g0, g1, 1);
/*     */   }
/*     */   
/*     */   public static Geometry union(Geometry g0, Geometry g1) {
/*  67 */     return overlayOp(g0, g1, 2);
/*     */   }
/*     */   
/*     */   public static Geometry difference(Geometry g0, Geometry g1) {
/*  72 */     return overlayOp(g0, g1, 3);
/*     */   }
/*     */   
/*     */   public static Geometry symDifference(Geometry g0, Geometry g1) {
/*  77 */     return overlayOp(g0, g1, 4);
/*     */   }
/*     */   
/*  81 */   private Geometry[] geom = new Geometry[2];
/*     */   
/*     */   private double snapTolerance;
/*     */   
/*     */   private CommonBitsRemover cbr;
/*     */   
/*     */   public SnapOverlayOp(Geometry g1, Geometry g2) {
/*  86 */     this.geom[0] = g1;
/*  87 */     this.geom[1] = g2;
/*  88 */     computeSnapTolerance();
/*     */   }
/*     */   
/*     */   private void computeSnapTolerance() {
/*  92 */     this.snapTolerance = GeometrySnapper.computeOverlaySnapTolerance(this.geom[0], this.geom[1]);
/*     */   }
/*     */   
/*     */   public Geometry getResultGeometry(int opCode) {
/* 100 */     Geometry[] prepGeom = snap(this.geom);
/* 101 */     Geometry result = OverlayOp.overlayOp(prepGeom[0], prepGeom[1], opCode);
/* 102 */     return prepareResult(result);
/*     */   }
/*     */   
/*     */   private Geometry selfSnap(Geometry geom) {
/* 107 */     GeometrySnapper snapper0 = new GeometrySnapper(geom);
/* 108 */     Geometry snapGeom = snapper0.snapTo(geom, this.snapTolerance);
/* 111 */     return snapGeom;
/*     */   }
/*     */   
/*     */   private Geometry[] snap(Geometry[] geom) {
/* 116 */     Geometry[] remGeom = removeCommonBits(geom);
/* 121 */     Geometry[] snapGeom = GeometrySnapper.snap(remGeom[0], remGeom[1], this.snapTolerance);
/* 131 */     return snapGeom;
/*     */   }
/*     */   
/*     */   private Geometry prepareResult(Geometry geom) {
/* 136 */     this.cbr.addCommonBits(geom);
/* 137 */     return geom;
/*     */   }
/*     */   
/*     */   private Geometry[] removeCommonBits(Geometry[] geom) {
/* 144 */     this.cbr = new CommonBitsRemover();
/* 145 */     this.cbr.add(geom[0]);
/* 146 */     this.cbr.add(geom[1]);
/* 147 */     Geometry[] remGeom = new Geometry[2];
/* 148 */     remGeom[0] = this.cbr.removeCommonBits((Geometry)geom[0].clone());
/* 149 */     remGeom[1] = this.cbr.removeCommonBits((Geometry)geom[1].clone());
/* 150 */     return remGeom;
/*     */   }
/*     */   
/*     */   private void checkValid(Geometry g) {
/* 155 */     if (!g.isValid())
/* 156 */       System.out.println("Snapped geometry is invalid"); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\overlay\snap\SnapOverlayOp.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */