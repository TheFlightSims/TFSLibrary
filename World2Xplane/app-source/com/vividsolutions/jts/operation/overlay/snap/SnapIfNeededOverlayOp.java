/*     */ package com.vividsolutions.jts.operation.overlay.snap;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.operation.overlay.OverlayOp;
/*     */ 
/*     */ public class SnapIfNeededOverlayOp {
/*     */   public static Geometry overlayOp(Geometry g0, Geometry g1, int opCode) {
/*  57 */     SnapIfNeededOverlayOp op = new SnapIfNeededOverlayOp(g0, g1);
/*  58 */     return op.getResultGeometry(opCode);
/*     */   }
/*     */   
/*     */   public static Geometry intersection(Geometry g0, Geometry g1) {
/*  63 */     return overlayOp(g0, g1, 1);
/*     */   }
/*     */   
/*     */   public static Geometry union(Geometry g0, Geometry g1) {
/*  68 */     return overlayOp(g0, g1, 2);
/*     */   }
/*     */   
/*     */   public static Geometry difference(Geometry g0, Geometry g1) {
/*  73 */     return overlayOp(g0, g1, 3);
/*     */   }
/*     */   
/*     */   public static Geometry symDifference(Geometry g0, Geometry g1) {
/*  78 */     return overlayOp(g0, g1, 4);
/*     */   }
/*     */   
/*  81 */   private Geometry[] geom = new Geometry[2];
/*     */   
/*     */   public SnapIfNeededOverlayOp(Geometry g1, Geometry g2) {
/*  85 */     this.geom[0] = g1;
/*  86 */     this.geom[1] = g2;
/*     */   }
/*     */   
/*     */   public Geometry getResultGeometry(int opCode) {
/*  91 */     Geometry result = null;
/*  92 */     boolean isSuccess = false;
/*  93 */     RuntimeException savedException = null;
/*     */     try {
/*  96 */       result = OverlayOp.overlayOp(this.geom[0], this.geom[1], opCode);
/*  97 */       boolean isValid = true;
/* 100 */       if (isValid)
/* 101 */         isSuccess = true; 
/* 103 */     } catch (RuntimeException ex) {
/* 104 */       savedException = ex;
/*     */     } 
/* 112 */     if (!isSuccess)
/*     */       try {
/* 116 */         result = SnapOverlayOp.overlayOp(this.geom[0], this.geom[1], opCode);
/* 118 */       } catch (RuntimeException ex) {
/* 119 */         throw savedException;
/*     */       }  
/* 122 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\overlay\snap\SnapIfNeededOverlayOp.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */