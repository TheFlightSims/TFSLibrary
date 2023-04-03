/*     */ package com.vividsolutions.jtsexample.technique;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.util.Stopwatch;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class SearchUsingPreparedGeometryIndex {
/*  63 */   static GeometryFactory geomFact = new GeometryFactory();
/*     */   
/*     */   static final int MAX_ITER = 200000;
/*     */   
/*     */   static final int GRID_SIZE = 10;
/*     */   
/*     */   static final int POLYGON_SIZE = 100;
/*     */   
/*     */   public static void main(String[] args) throws Exception {
/*  72 */     List circleGrid = createCircleGrid(10);
/*  74 */     PreparedGeometryIndex pgIndex = new PreparedGeometryIndex();
/*  75 */     pgIndex.insert(circleGrid);
/*  77 */     Stopwatch sw = new Stopwatch();
/*  78 */     int inCount = runIndexedQuery(pgIndex);
/*  79 */     String indexTime = sw.getTimeString();
/*  81 */     System.out.println("Number of iterations       = 200000");
/*  82 */     System.out.println("Number of circles in grid  = " + circleGrid.size());
/*  83 */     System.out.println();
/*  84 */     System.out.println("The fraction of intersecting points should approximate the total area of the circles:");
/*  85 */     System.out.println();
/*  86 */     System.out.println("Area of circles                = " + area(circleGrid));
/*  87 */     System.out.println("Fraction of points in circles  = " + (inCount / 200000.0D));
/*  88 */     System.out.println();
/*  89 */     System.out.println("Indexed Execution time: " + indexTime);
/*  94 */     Stopwatch sw2 = new Stopwatch();
/*  95 */     int inCount2 = runBruteForceQuery(circleGrid);
/*  96 */     String bruteForceTime = sw2.getTimeString();
/*  98 */     System.out.println();
/*  99 */     System.out.println("Execution time: " + bruteForceTime);
/*     */   }
/*     */   
/*     */   static int runIndexedQuery(PreparedGeometryIndex pgIndex) {
/* 105 */     int inCount = 0;
/* 106 */     for (int i = 0; i < 200000; i++) {
/* 108 */       Point randPt = createRandomPoint();
/* 109 */       if (pgIndex.intersects((Geometry)randPt).size() > 0)
/* 110 */         inCount++; 
/*     */     } 
/* 113 */     return inCount;
/*     */   }
/*     */   
/*     */   static int runBruteForceQuery(Collection geoms) {
/* 118 */     int inCount = 0;
/* 119 */     for (int i = 0; i < 200000; i++) {
/* 121 */       Point randPt = createRandomPoint();
/* 122 */       if (findIntersecting(geoms, (Geometry)randPt).size() > 0)
/* 123 */         inCount++; 
/*     */     } 
/* 126 */     return inCount;
/*     */   }
/*     */   
/*     */   static double area(Collection geoms) {
/* 131 */     double area = 0.0D;
/* 132 */     for (Iterator<Geometry> i = geoms.iterator(); i.hasNext(); ) {
/* 133 */       Geometry geom = i.next();
/* 134 */       area += geom.getArea();
/*     */     } 
/* 136 */     return area;
/*     */   }
/*     */   
/*     */   static List createCircleGrid(int gridSize) {
/* 141 */     double diameter = 1.0D / gridSize;
/* 142 */     double radius = diameter / 2.0D;
/* 144 */     List<Geometry> circles = new ArrayList();
/* 145 */     for (int i = 0; i < gridSize; i++) {
/* 146 */       for (int j = 0; j < gridSize; j++) {
/* 147 */         Coordinate centre = new Coordinate(radius + i * diameter, radius + j * diameter);
/* 148 */         Geometry circle = createCircle(centre, radius);
/* 149 */         circles.add(circle);
/*     */       } 
/*     */     } 
/* 152 */     return circles;
/*     */   }
/*     */   
/*     */   static Geometry createCircle(Coordinate centre, double radius) {
/* 157 */     Point point = geomFact.createPoint(centre);
/* 158 */     return point.buffer(radius, 100);
/*     */   }
/*     */   
/*     */   static Point createRandomPoint() {
/* 163 */     return geomFact.createPoint(new Coordinate(Math.random(), Math.random()));
/*     */   }
/*     */   
/*     */   static List findIntersecting(Collection targetGeoms, Geometry queryGeom) {
/* 168 */     List<Geometry> result = new ArrayList();
/* 169 */     for (Iterator<Geometry> it = targetGeoms.iterator(); it.hasNext(); ) {
/* 170 */       Geometry test = it.next();
/* 171 */       if (test.intersects(queryGeom))
/* 172 */         result.add(test); 
/*     */     } 
/* 175 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jtsexample\technique\SearchUsingPreparedGeometryIndex.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */