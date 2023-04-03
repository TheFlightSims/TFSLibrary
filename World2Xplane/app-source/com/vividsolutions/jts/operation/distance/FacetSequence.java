/*     */ package com.vividsolutions.jts.operation.distance;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.CGAlgorithms;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ 
/*     */ public class FacetSequence {
/*     */   private CoordinateSequence pts;
/*     */   
/*     */   private int start;
/*     */   
/*     */   private int end;
/*     */   
/*  54 */   private Coordinate pt = new Coordinate();
/*     */   
/*  55 */   private Coordinate seqPt = new Coordinate();
/*     */   
/*     */   private Coordinate p0;
/*     */   
/*     */   private Coordinate p1;
/*     */   
/*     */   private Coordinate q0;
/*     */   
/*     */   private Coordinate q1;
/*     */   
/*     */   public Envelope getEnvelope() {
/*  86 */     Envelope env = new Envelope();
/*  87 */     for (int i = this.start; i < this.end; i++)
/*  88 */       env.expandToInclude(this.pts.getX(i), this.pts.getY(i)); 
/*  90 */     return env;
/*     */   }
/*     */   
/*     */   public int size() {
/*  95 */     return this.end - this.start;
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinate(int index) {
/* 100 */     return this.pts.getCoordinate(this.start + index);
/*     */   }
/*     */   
/*     */   public boolean isPoint() {
/* 105 */     return (this.end - this.start == 1);
/*     */   }
/*     */   
/*     */   public double distance(FacetSequence facetSeq) {
/* 111 */     boolean isPoint = isPoint();
/* 112 */     boolean isPointOther = facetSeq.isPoint();
/* 114 */     if (isPoint && isPointOther) {
/* 115 */       this.pts.getCoordinate(this.start, this.pt);
/* 116 */       facetSeq.pts.getCoordinate(facetSeq.start, this.seqPt);
/* 117 */       return this.pt.distance(this.seqPt);
/*     */     } 
/* 119 */     if (isPoint) {
/* 120 */       this.pts.getCoordinate(this.start, this.pt);
/* 121 */       return computePointLineDistance(this.pt, facetSeq);
/*     */     } 
/* 123 */     if (isPointOther) {
/* 124 */       facetSeq.pts.getCoordinate(facetSeq.start, this.seqPt);
/* 125 */       return computePointLineDistance(this.seqPt, this);
/*     */     } 
/* 127 */     return computeLineLineDistance(facetSeq);
/*     */   }
/*     */   
/*     */   public FacetSequence(CoordinateSequence pts, int start, int end) {
/* 132 */     this.p0 = new Coordinate();
/* 133 */     this.p1 = new Coordinate();
/* 134 */     this.q0 = new Coordinate();
/* 135 */     this.q1 = new Coordinate();
/*     */     this.pts = pts;
/*     */     this.start = start;
/*     */     this.end = end;
/*     */   }
/*     */   
/*     */   public FacetSequence(CoordinateSequence pts, int start) {
/*     */     this.p0 = new Coordinate();
/*     */     this.p1 = new Coordinate();
/*     */     this.q0 = new Coordinate();
/* 135 */     this.q1 = new Coordinate();
/*     */     this.pts = pts;
/*     */     this.start = start;
/*     */     this.end = start + 1;
/*     */   }
/*     */   
/*     */   private double computeLineLineDistance(FacetSequence facetSeq) {
/* 140 */     double minDistance = Double.MAX_VALUE;
/* 142 */     for (int i = this.start; i < this.end - 1; i++) {
/* 143 */       for (int j = facetSeq.start; j < facetSeq.end - 1; j++) {
/* 144 */         this.pts.getCoordinate(i, this.p0);
/* 145 */         this.pts.getCoordinate(i + 1, this.p1);
/* 146 */         facetSeq.pts.getCoordinate(j, this.q0);
/* 147 */         facetSeq.pts.getCoordinate(j + 1, this.q1);
/* 149 */         double dist = CGAlgorithms.distanceLineLine(this.p0, this.p1, this.q0, this.q1);
/* 150 */         if (dist == 0.0D)
/* 151 */           return 0.0D; 
/* 152 */         if (dist < minDistance)
/* 153 */           minDistance = dist; 
/*     */       } 
/*     */     } 
/* 157 */     return minDistance;
/*     */   }
/*     */   
/*     */   private double computePointLineDistance(Coordinate pt, FacetSequence facetSeq) {
/* 162 */     double minDistance = Double.MAX_VALUE;
/* 164 */     for (int i = facetSeq.start; i < facetSeq.end - 1; i++) {
/* 165 */       facetSeq.pts.getCoordinate(i, this.q0);
/* 166 */       facetSeq.pts.getCoordinate(i + 1, this.q1);
/* 167 */       double dist = CGAlgorithms.distancePointLine(pt, this.q0, this.q1);
/* 168 */       if (dist == 0.0D)
/* 168 */         return 0.0D; 
/* 169 */       if (dist < minDistance)
/* 170 */         minDistance = dist; 
/*     */     } 
/* 173 */     return minDistance;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 178 */     StringBuffer buf = new StringBuffer();
/* 179 */     buf.append("LINESTRING ( ");
/* 180 */     Coordinate p = new Coordinate();
/* 181 */     for (int i = this.start; i < this.end; i++) {
/* 182 */       if (i > this.start)
/* 183 */         buf.append(", "); 
/* 184 */       this.pts.getCoordinate(i, p);
/* 185 */       buf.append(p.x + " " + p.y);
/*     */     } 
/* 187 */     buf.append(" )");
/* 188 */     return buf.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\distance\FacetSequence.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */