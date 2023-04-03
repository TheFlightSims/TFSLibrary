/*     */ package com.vividsolutions.jts.shape.fractal;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateList;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineSegment;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.shape.GeometricShapeBuilder;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class SierpinskiCarpetBuilder extends GeometricShapeBuilder {
/*  44 */   private CoordinateList coordList = new CoordinateList();
/*     */   
/*     */   public SierpinskiCarpetBuilder(GeometryFactory geomFactory) {
/*  48 */     super(geomFactory);
/*     */   }
/*     */   
/*     */   public static int recursionLevelForSize(int numPts) {
/*  53 */     double pow4 = (numPts / 3);
/*  54 */     double exp = Math.log(pow4) / Math.log(4.0D);
/*  55 */     return (int)exp;
/*     */   }
/*     */   
/*     */   public Geometry getGeometry() {
/*  60 */     int level = recursionLevelForSize(this.numPts);
/*  61 */     LineSegment baseLine = getSquareBaseLine();
/*  62 */     Coordinate origin = baseLine.getCoordinate(0);
/*  63 */     LinearRing[] holes = getHoles(level, origin.x, origin.y, getDiameter());
/*  64 */     LinearRing shell = (LinearRing)((Polygon)this.geomFactory.toGeometry(getSquareExtent())).getExteriorRing();
/*  65 */     return (Geometry)this.geomFactory.createPolygon(shell, holes);
/*     */   }
/*     */   
/*     */   private LinearRing[] getHoles(int n, double originX, double originY, double width) {
/*  71 */     List holeList = new ArrayList();
/*  73 */     addHoles(n, originX, originY, width, holeList);
/*  75 */     return GeometryFactory.toLinearRingArray(holeList);
/*     */   }
/*     */   
/*     */   private void addHoles(int n, double originX, double originY, double width, List<LinearRing> holeList) {
/*  80 */     if (n < 0)
/*     */       return; 
/*  81 */     int n2 = n - 1;
/*  82 */     double widthThird = width / 3.0D;
/*  83 */     double widthTwoThirds = width * 2.0D / 3.0D;
/*  84 */     double widthNinth = width / 9.0D;
/*  85 */     addHoles(n2, originX, originY, widthThird, holeList);
/*  86 */     addHoles(n2, originX + widthThird, originY, widthThird, holeList);
/*  87 */     addHoles(n2, originX + 2.0D * widthThird, originY, widthThird, holeList);
/*  89 */     addHoles(n2, originX, originY + widthThird, widthThird, holeList);
/*  90 */     addHoles(n2, originX + 2.0D * widthThird, originY + widthThird, widthThird, holeList);
/*  92 */     addHoles(n2, originX, originY + 2.0D * widthThird, widthThird, holeList);
/*  93 */     addHoles(n2, originX + widthThird, originY + 2.0D * widthThird, widthThird, holeList);
/*  94 */     addHoles(n2, originX + 2.0D * widthThird, originY + 2.0D * widthThird, widthThird, holeList);
/*  97 */     holeList.add(createSquareHole(originX + widthThird, originY + widthThird, widthThird));
/*     */   }
/*     */   
/*     */   private LinearRing createSquareHole(double x, double y, double width) {
/* 102 */     Coordinate[] pts = { new Coordinate(x, y), new Coordinate(x + width, y), new Coordinate(x + width, y + width), new Coordinate(x, y + width), new Coordinate(x, y) };
/* 109 */     return this.geomFactory.createLinearRing(pts);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\shape\fractal\SierpinskiCarpetBuilder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */