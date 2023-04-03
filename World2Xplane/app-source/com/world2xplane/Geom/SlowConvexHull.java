/*     */ package com.world2xplane.Geom;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import math.geom2d.Point2D;
/*     */ 
/*     */ public class SlowConvexHull implements ConvexHullAlgorithm {
/*     */   public ArrayList<Point2D> execute(ArrayList<Point2D> points) {
/*  36 */     int n = points.size();
/*  38 */     ArrayList<LineSegment> edges = new ArrayList<>();
/*  40 */     for (int i = 0; i < n; i++) {
/*  42 */       for (int j = 0; j < n; j++) {
/*  44 */         if (i != j) {
/*  46 */           boolean valid = true;
/*  48 */           for (int k = 0; k < n; k++) {
/*  50 */             if (k != i && k != j)
/*  52 */               if (!rightOfLine(points.get(k), new LineSegment(points.get(i), points.get(j)))) {
/*  54 */                 valid = false;
/*     */                 break;
/*     */               }  
/*     */           } 
/*  59 */           if (valid)
/*  61 */             edges.add(new LineSegment(points.get(i), points.get(j))); 
/*     */         } 
/*     */       } 
/*     */     } 
/*  66 */     return sortedVertexList(edges);
/*     */   }
/*     */   
/*     */   private boolean rightOfLine(Point2D p, LineSegment line) {
/*  71 */     return ((line.p2.x() - line.p1.x()) * (p.y() - line.p1.y()) - (line.p2.y() - line.p1.y()) * (p.x() - line.p1.x()) < 0.0D);
/*     */   }
/*     */   
/*     */   private boolean leftOfLine(Point2D p, LineSegment line) {
/*  76 */     return ((line.p2.x() - line.p1.x()) * (p.y() - line.p1.y()) - (line.p2.y() - line.p1.y()) * (p.x() - line.p1.x()) > 0.0D);
/*     */   }
/*     */   
/*     */   private ArrayList<Point2D> sortedVertexList(ArrayList<LineSegment> lines) {
/*  81 */     ArrayList<LineSegment> xSorted = (ArrayList<LineSegment>)lines.clone();
/*  82 */     Collections.sort(xSorted, new XCompare());
/*  84 */     int n = xSorted.size();
/*  86 */     LineSegment baseLine = new LineSegment(((LineSegment)xSorted.get(0)).p1, ((LineSegment)xSorted.get(n - 1)).p1);
/*  88 */     ArrayList<Point2D> result = new ArrayList<>();
/*  90 */     result.add(((LineSegment)xSorted.get(0)).p1);
/*     */     int i;
/*  92 */     for (i = 1; i < n; i++) {
/*  94 */       if (leftOfLine(((LineSegment)xSorted.get(i)).p1, baseLine))
/*  96 */         result.add(((LineSegment)xSorted.get(i)).p1); 
/*     */     } 
/* 100 */     result.add(((LineSegment)xSorted.get(n - 1)).p1);
/* 102 */     for (i = n - 2; i > 0; i--) {
/* 104 */       if (rightOfLine(((LineSegment)xSorted.get(i)).p1, baseLine))
/* 106 */         result.add(((LineSegment)xSorted.get(i)).p1); 
/*     */     } 
/* 110 */     return result;
/*     */   }
/*     */   
/*     */   private class LineSegment {
/*     */     public Point2D p1;
/*     */     
/*     */     public Point2D p2;
/*     */     
/*     */     public LineSegment(Point2D p1, Point2D p2) {
/* 121 */       this.p1 = p1;
/* 122 */       this.p2 = p2;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 127 */       return this.p1.toString() + "," + this.p2.toString() + "\n";
/*     */     }
/*     */   }
/*     */   
/*     */   private class XCompare implements Comparator<LineSegment> {
/*     */     private XCompare() {}
/*     */     
/*     */     public int compare(SlowConvexHull.LineSegment o1, SlowConvexHull.LineSegment o2) {
/* 135 */       return (new Double(o1.p1.x())).compareTo(new Double(o2.p1.y()));
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Geom\SlowConvexHull.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */