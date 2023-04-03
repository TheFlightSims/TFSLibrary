/*     */ package com.vividsolutions.jts.shape.random;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.math.MathUtil;
/*     */ import com.vividsolutions.jts.shape.GeometricShapeBuilder;
/*     */ 
/*     */ public class RandomPointsInGridBuilder extends GeometricShapeBuilder {
/*     */   private boolean isConstrainedToCircle = false;
/*     */   
/*  56 */   private double gutterFraction = 0.0D;
/*     */   
/*     */   public RandomPointsInGridBuilder() {
/*  64 */     super(new GeometryFactory());
/*     */   }
/*     */   
/*     */   public RandomPointsInGridBuilder(GeometryFactory geomFact) {
/*  75 */     super(geomFact);
/*     */   }
/*     */   
/*     */   public void setConstrainedToCircle(boolean isConstrainedToCircle) {
/*  89 */     this.isConstrainedToCircle = isConstrainedToCircle;
/*     */   }
/*     */   
/*     */   public void setGutterFraction(double gutterFraction) {
/* 101 */     this.gutterFraction = gutterFraction;
/*     */   }
/*     */   
/*     */   public Geometry getGeometry() {
/* 111 */     int nCells = (int)Math.sqrt(this.numPts);
/* 113 */     if (nCells * nCells < this.numPts)
/* 114 */       nCells++; 
/* 116 */     double gridDX = getExtent().getWidth() / nCells;
/* 117 */     double gridDY = getExtent().getHeight() / nCells;
/* 119 */     double gutterFrac = MathUtil.clamp(this.gutterFraction, 0.0D, 1.0D);
/* 120 */     double gutterOffsetX = gridDX * gutterFrac / 2.0D;
/* 121 */     double gutterOffsetY = gridDY * gutterFrac / 2.0D;
/* 122 */     double cellFrac = 1.0D - gutterFrac;
/* 123 */     double cellDX = cellFrac * gridDX;
/* 124 */     double cellDY = cellFrac * gridDY;
/* 126 */     Coordinate[] pts = new Coordinate[nCells * nCells];
/* 127 */     int index = 0;
/* 128 */     for (int i = 0; i < nCells; i++) {
/* 129 */       for (int j = 0; j < nCells; j++) {
/* 130 */         double orgX = getExtent().getMinX() + i * gridDX + gutterOffsetX;
/* 131 */         double orgY = getExtent().getMinY() + j * gridDY + gutterOffsetY;
/* 132 */         pts[index++] = randomPointInCell(orgX, orgY, cellDX, cellDY);
/*     */       } 
/*     */     } 
/* 135 */     return (Geometry)this.geomFactory.createMultiPoint(pts);
/*     */   }
/*     */   
/*     */   private Coordinate randomPointInCell(double orgX, double orgY, double xLen, double yLen) {
/* 140 */     if (this.isConstrainedToCircle)
/* 141 */       return randomPointInCircle(orgX, orgY, xLen, yLen); 
/* 146 */     return randomPointInGridCell(orgX, orgY, xLen, yLen);
/*     */   }
/*     */   
/*     */   private Coordinate randomPointInGridCell(double orgX, double orgY, double xLen, double yLen) {
/* 151 */     double x = orgX + xLen * Math.random();
/* 152 */     double y = orgY + yLen * Math.random();
/* 153 */     return createCoord(x, y);
/*     */   }
/*     */   
/*     */   private static Coordinate randomPointInCircle(double orgX, double orgY, double width, double height) {
/* 158 */     double centreX = orgX + width / 2.0D;
/* 159 */     double centreY = orgY + height / 2.0D;
/* 161 */     double rndAng = 6.283185307179586D * Math.random();
/* 162 */     double rndRadius = Math.random();
/* 164 */     double rndRadius2 = Math.sqrt(rndRadius);
/* 165 */     double rndX = width / 2.0D * rndRadius2 * Math.cos(rndAng);
/* 166 */     double rndY = height / 2.0D * rndRadius2 * Math.sin(rndAng);
/* 168 */     double x0 = centreX + rndX;
/* 169 */     double y0 = centreY + rndY;
/* 170 */     return new Coordinate(x0, y0);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\shape\random\RandomPointsInGridBuilder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */