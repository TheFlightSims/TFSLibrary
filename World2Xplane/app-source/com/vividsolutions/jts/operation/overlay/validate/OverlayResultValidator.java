/*     */ package com.vividsolutions.jts.operation.overlay.validate;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.Location;
/*     */ import com.vividsolutions.jts.operation.overlay.OverlayOp;
/*     */ import com.vividsolutions.jts.operation.overlay.snap.GeometrySnapper;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class OverlayResultValidator {
/*     */   private static final double TOLERANCE = 1.0E-6D;
/*     */   
/*     */   private Geometry[] geom;
/*     */   
/*     */   private FuzzyPointLocator[] locFinder;
/*     */   
/*     */   public static boolean isValid(Geometry a, Geometry b, int overlayOp, Geometry result) {
/*  61 */     OverlayResultValidator validator = new OverlayResultValidator(a, b, result);
/*  62 */     return validator.isValid(overlayOp);
/*     */   }
/*     */   
/*     */   private static double computeBoundaryDistanceTolerance(Geometry g0, Geometry g1) {
/*  67 */     return Math.min(GeometrySnapper.computeSizeBasedSnapTolerance(g0), GeometrySnapper.computeSizeBasedSnapTolerance(g1));
/*     */   }
/*     */   
/*  75 */   private int[] location = new int[3];
/*     */   
/*  76 */   private Coordinate invalidLocation = null;
/*     */   
/*  77 */   private double boundaryDistanceTolerance = 1.0E-6D;
/*     */   
/*  79 */   private List testCoords = new ArrayList();
/*     */   
/*     */   public OverlayResultValidator(Geometry a, Geometry b, Geometry result) {
/*  87 */     this.boundaryDistanceTolerance = computeBoundaryDistanceTolerance(a, b);
/*  88 */     this.geom = new Geometry[] { a, b, result };
/*  89 */     this.locFinder = new FuzzyPointLocator[] { new FuzzyPointLocator(this.geom[0], this.boundaryDistanceTolerance), new FuzzyPointLocator(this.geom[1], this.boundaryDistanceTolerance), new FuzzyPointLocator(this.geom[2], this.boundaryDistanceTolerance) };
/*     */   }
/*     */   
/*     */   public boolean isValid(int overlayOp) {
/*  98 */     addTestPts(this.geom[0]);
/*  99 */     addTestPts(this.geom[1]);
/* 100 */     boolean isValid = checkValid(overlayOp);
/* 112 */     return isValid;
/*     */   }
/*     */   
/*     */   public Coordinate getInvalidLocation() {
/* 115 */     return this.invalidLocation;
/*     */   }
/*     */   
/*     */   private void addTestPts(Geometry g) {
/* 119 */     OffsetPointGenerator ptGen = new OffsetPointGenerator(g);
/* 120 */     this.testCoords.addAll(ptGen.getPoints(5.0D * this.boundaryDistanceTolerance));
/*     */   }
/*     */   
/*     */   private boolean checkValid(int overlayOp) {
/* 125 */     for (int i = 0; i < this.testCoords.size(); i++) {
/* 126 */       Coordinate pt = this.testCoords.get(i);
/* 127 */       if (!checkValid(overlayOp, pt)) {
/* 128 */         this.invalidLocation = pt;
/* 129 */         return false;
/*     */       } 
/*     */     } 
/* 132 */     return true;
/*     */   }
/*     */   
/*     */   private boolean checkValid(int overlayOp, Coordinate pt) {
/* 137 */     this.location[0] = this.locFinder[0].getLocation(pt);
/* 138 */     this.location[1] = this.locFinder[1].getLocation(pt);
/* 139 */     this.location[2] = this.locFinder[2].getLocation(pt);
/* 144 */     if (hasLocation(this.location, 1))
/* 145 */       return true; 
/* 147 */     return isValidResult(overlayOp, this.location);
/*     */   }
/*     */   
/*     */   private static boolean hasLocation(int[] location, int loc) {
/* 152 */     for (int i = 0; i < 3; i++) {
/* 153 */       if (location[i] == loc)
/* 154 */         return true; 
/*     */     } 
/* 156 */     return false;
/*     */   }
/*     */   
/*     */   private boolean isValidResult(int overlayOp, int[] location) {
/* 161 */     boolean expectedInterior = OverlayOp.isResultOfOp(location[0], location[1], overlayOp);
/* 163 */     boolean resultInInterior = (location[2] == 0);
/* 165 */     boolean isValid = !(expectedInterior ^ resultInInterior);
/* 167 */     if (!isValid)
/* 167 */       reportResult(overlayOp, location, expectedInterior); 
/* 169 */     return isValid;
/*     */   }
/*     */   
/*     */   private void reportResult(int overlayOp, int[] location, boolean expectedInterior) {
/* 174 */     System.out.println("Overlay result invalid - A:" + Location.toLocationSymbol(location[0]) + " B:" + Location.toLocationSymbol(location[1]) + " expected:" + (expectedInterior ? 105 : 101) + " actual:" + Location.toLocationSymbol(location[2]));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\overlay\validate\OverlayResultValidator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */