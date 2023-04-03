/*    */ package com.world2xplane.Geom;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.Comparator;
/*    */ import math.geom2d.Point2D;
/*    */ 
/*    */ public class FastConvexHull implements ConvexHullAlgorithm {
/*    */   public ArrayList<Point2D> execute(ArrayList<Point2D> points) {
/* 34 */     ArrayList<Point2D> xSorted = (ArrayList<Point2D>)points.clone();
/* 35 */     Collections.sort(xSorted, new XCompare());
/* 37 */     int n = xSorted.size();
/* 39 */     Point2D[] lUpper = new Point2D[n];
/* 41 */     lUpper[0] = xSorted.get(0);
/* 42 */     lUpper[1] = xSorted.get(1);
/* 44 */     int lUpperSize = 2;
/* 46 */     for (int i = 2; i < n; i++) {
/* 47 */       lUpper[lUpperSize] = xSorted.get(i);
/* 48 */       lUpperSize++;
/* 50 */       while (lUpperSize > 2 && !rightTurn(lUpper[lUpperSize - 3], lUpper[lUpperSize - 2], lUpper[lUpperSize - 1])) {
/* 52 */         lUpper[lUpperSize - 2] = lUpper[lUpperSize - 1];
/* 53 */         lUpperSize--;
/*    */       } 
/*    */     } 
/* 57 */     Point2D[] lLower = new Point2D[n];
/* 59 */     lLower[0] = xSorted.get(n - 1);
/* 60 */     lLower[1] = xSorted.get(n - 2);
/* 62 */     int lLowerSize = 2;
/* 64 */     for (int j = n - 3; j >= 0; j--) {
/* 65 */       lLower[lLowerSize] = xSorted.get(j);
/* 66 */       lLowerSize++;
/* 68 */       while (lLowerSize > 2 && !rightTurn(lLower[lLowerSize - 3], lLower[lLowerSize - 2], lLower[lLowerSize - 1])) {
/* 70 */         lLower[lLowerSize - 2] = lLower[lLowerSize - 1];
/* 71 */         lLowerSize--;
/*    */       } 
/*    */     } 
/* 75 */     ArrayList<Point2D> result = new ArrayList<>();
/*    */     int k;
/* 77 */     for (k = 0; k < lUpperSize; k++)
/* 78 */       result.add(lUpper[k]); 
/* 81 */     for (k = 1; k < lLowerSize - 1; k++)
/* 82 */       result.add(lLower[k]); 
/* 85 */     return result;
/*    */   }
/*    */   
/*    */   private boolean rightTurn(Point2D a, Point2D b, Point2D c) {
/* 89 */     return ((b.x() - a.x()) * (c.y() - a.y()) - (b.y() - a.y()) * (c.x() - a.x()) > 0.0D);
/*    */   }
/*    */   
/*    */   private class XCompare implements Comparator<Point2D> {
/*    */     private XCompare() {}
/*    */     
/*    */     public int compare(Point2D o1, Point2D o2) {
/* 94 */       return (new Double(o1.x())).compareTo(new Double(o2.x()));
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Geom\FastConvexHull.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */