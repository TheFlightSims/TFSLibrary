/*     */ package com.vividsolutions.jts.simplify;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateList;
/*     */ import com.vividsolutions.jts.geom.Triangle;
/*     */ 
/*     */ class VWLineSimplifier {
/*     */   private Coordinate[] pts;
/*     */   
/*     */   private double tolerance;
/*     */   
/*     */   public static Coordinate[] simplify(Coordinate[] pts, double distanceTolerance) {
/*  19 */     VWLineSimplifier simp = new VWLineSimplifier(pts, distanceTolerance);
/*  20 */     return simp.simplify();
/*     */   }
/*     */   
/*     */   public VWLineSimplifier(Coordinate[] pts, double distanceTolerance) {
/*  28 */     this.pts = pts;
/*  29 */     this.tolerance = distanceTolerance * distanceTolerance;
/*     */   }
/*     */   
/*     */   public Coordinate[] simplify() {
/*  34 */     VWVertex vwLine = VWVertex.buildLine(this.pts);
/*  35 */     double minArea = this.tolerance;
/*     */     while (true) {
/*  37 */       minArea = simplifyVertex(vwLine);
/*  38 */       if (minArea >= this.tolerance) {
/*  39 */         Coordinate[] simp = vwLine.getCoordinates();
/*  41 */         if (simp.length < 2)
/*  42 */           return new Coordinate[] { simp[0], new Coordinate(simp[0]) }; 
/*  44 */         return simp;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private double simplifyVertex(VWVertex vwLine) {
/*  53 */     VWVertex curr = vwLine;
/*  54 */     double minArea = curr.getArea();
/*  55 */     VWVertex minVertex = null;
/*  56 */     while (curr != null) {
/*  57 */       double area = curr.getArea();
/*  58 */       if (area < minArea) {
/*  59 */         minArea = area;
/*  60 */         minVertex = curr;
/*     */       } 
/*  62 */       curr = curr.next;
/*     */     } 
/*  64 */     if (minVertex != null && minArea < this.tolerance)
/*  65 */       minVertex.remove(); 
/*  67 */     if (!vwLine.isLive())
/*  67 */       return -1.0D; 
/*  68 */     return minArea;
/*     */   }
/*     */   
/*     */   static class VWVertex {
/*     */     public static VWVertex buildLine(Coordinate[] pts) {
/*  76 */       VWVertex first = null;
/*  77 */       VWVertex prev = null;
/*  78 */       for (int i = 0; i < pts.length; i++) {
/*  79 */         VWVertex v = new VWVertex(pts[i]);
/*  80 */         if (first == null)
/*  81 */           first = v; 
/*  82 */         v.setPrev(prev);
/*  83 */         if (prev != null) {
/*  84 */           prev.setNext(v);
/*  85 */           prev.updateArea();
/*     */         } 
/*  87 */         prev = v;
/*     */       } 
/*  89 */       return first;
/*     */     }
/*     */     
/*  92 */     public static double MAX_AREA = Double.MAX_VALUE;
/*     */     
/*     */     private Coordinate pt;
/*     */     
/*     */     private VWVertex prev;
/*     */     
/*     */     private VWVertex next;
/*     */     
/*  97 */     private double area = MAX_AREA;
/*     */     
/*     */     private boolean isLive = true;
/*     */     
/*     */     public VWVertex(Coordinate pt) {
/* 102 */       this.pt = pt;
/*     */     }
/*     */     
/*     */     public void setPrev(VWVertex prev) {
/* 107 */       this.prev = prev;
/*     */     }
/*     */     
/*     */     public void setNext(VWVertex next) {
/* 112 */       this.next = next;
/*     */     }
/*     */     
/*     */     public void updateArea() {
/* 117 */       if (this.prev == null || this.next == null) {
/* 118 */         this.area = MAX_AREA;
/*     */         return;
/*     */       } 
/* 121 */       this.area = Math.abs(Triangle.area(this.prev.pt, this.pt, this.next.pt));
/*     */     }
/*     */     
/*     */     public double getArea() {
/* 126 */       return this.area;
/*     */     }
/*     */     
/*     */     public boolean isLive() {
/* 130 */       return this.isLive;
/*     */     }
/*     */     
/*     */     public VWVertex remove() {
/* 134 */       VWVertex tmpPrev = this.prev;
/* 135 */       VWVertex tmpNext = this.next;
/* 136 */       VWVertex result = null;
/* 137 */       if (this.prev != null) {
/* 138 */         this.prev.setNext(tmpNext);
/* 139 */         this.prev.updateArea();
/* 140 */         result = this.prev;
/*     */       } 
/* 142 */       if (this.next != null) {
/* 143 */         this.next.setPrev(tmpPrev);
/* 144 */         this.next.updateArea();
/* 145 */         if (result == null)
/* 146 */           result = this.next; 
/*     */       } 
/* 148 */       this.isLive = false;
/* 149 */       return result;
/*     */     }
/*     */     
/*     */     public Coordinate[] getCoordinates() {
/* 153 */       CoordinateList coords = new CoordinateList();
/* 154 */       VWVertex curr = this;
/*     */       while (true) {
/* 156 */         coords.add(curr.pt, false);
/* 157 */         curr = curr.next;
/* 158 */         if (curr == null)
/* 159 */           return coords.toCoordinateArray(); 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\simplify\VWLineSimplifier.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */