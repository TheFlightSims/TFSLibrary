/*     */ package com.vividsolutions.jts.shape.fractal;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateList;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineSegment;
/*     */ import com.vividsolutions.jts.math.Vector2D;
/*     */ import com.vividsolutions.jts.shape.GeometricShapeBuilder;
/*     */ 
/*     */ public class KochSnowflakeBuilder extends GeometricShapeBuilder {
/*  43 */   private CoordinateList coordList = new CoordinateList();
/*     */   
/*     */   public KochSnowflakeBuilder(GeometryFactory geomFactory) {
/*  47 */     super(geomFactory);
/*     */   }
/*     */   
/*     */   public static int recursionLevelForSize(int numPts) {
/*  52 */     double pow4 = (numPts / 3);
/*  53 */     double exp = Math.log(pow4) / Math.log(4.0D);
/*  54 */     return (int)exp;
/*     */   }
/*     */   
/*     */   public Geometry getGeometry() {
/*  59 */     int level = recursionLevelForSize(this.numPts);
/*  60 */     LineSegment baseLine = getSquareBaseLine();
/*  61 */     Coordinate[] pts = getBoundary(level, baseLine.getCoordinate(0), baseLine.getLength());
/*  62 */     return (Geometry)this.geomFactory.createPolygon(this.geomFactory.createLinearRing(pts), null);
/*     */   }
/*     */   
/*  69 */   private static final double HEIGHT_FACTOR = Math.sin(1.0471975511965976D);
/*     */   
/*     */   private static final double ONE_THIRD = 0.3333333333333333D;
/*     */   
/*  71 */   private static final double THIRD_HEIGHT = HEIGHT_FACTOR / 3.0D;
/*     */   
/*     */   private static final double TWO_THIRDS = 0.6666666666666666D;
/*     */   
/*     */   private Coordinate[] getBoundary(int level, Coordinate origin, double width) {
/*  76 */     double y = origin.y;
/*  78 */     if (level > 0)
/*  79 */       y += THIRD_HEIGHT * width; 
/*  82 */     Coordinate p0 = new Coordinate(origin.x, y);
/*  83 */     Coordinate p1 = new Coordinate(origin.x + width / 2.0D, y + width * HEIGHT_FACTOR);
/*  84 */     Coordinate p2 = new Coordinate(origin.x + width, y);
/*  85 */     addSide(level, p0, p1);
/*  86 */     addSide(level, p1, p2);
/*  87 */     addSide(level, p2, p0);
/*  88 */     this.coordList.closeRing();
/*  89 */     return this.coordList.toCoordinateArray();
/*     */   }
/*     */   
/*     */   public void addSide(int level, Coordinate p0, Coordinate p1) {
/*  93 */     if (level == 0) {
/*  94 */       addSegment(p0, p1);
/*     */     } else {
/*  96 */       Vector2D base = Vector2D.create(p0, p1);
/*  97 */       Coordinate midPt = base.multiply(0.5D).translate(p0);
/*  99 */       Vector2D heightVec = base.multiply(THIRD_HEIGHT);
/* 100 */       Vector2D offsetVec = heightVec.rotateByQuarterCircle(1);
/* 101 */       Coordinate offsetPt = offsetVec.translate(midPt);
/* 103 */       int n2 = level - 1;
/* 104 */       Coordinate thirdPt = base.multiply(0.3333333333333333D).translate(p0);
/* 105 */       Coordinate twoThirdPt = base.multiply(0.6666666666666666D).translate(p0);
/* 108 */       addSide(n2, p0, thirdPt);
/* 109 */       addSide(n2, thirdPt, offsetPt);
/* 110 */       addSide(n2, offsetPt, twoThirdPt);
/* 111 */       addSide(n2, twoThirdPt, p1);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addSegment(Coordinate p0, Coordinate p1) {
/* 117 */     this.coordList.add(p1);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\shape\fractal\KochSnowflakeBuilder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */