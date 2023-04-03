/*     */ package com.vividsolutions.jts.triangulate;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.LineSegment;
/*     */ 
/*     */ public class Segment {
/*     */   private LineSegment ls;
/*     */   
/*  50 */   private Object data = null;
/*     */   
/*     */   public Segment(double x1, double y1, double z1, double x2, double y2, double z2) {
/*  56 */     this(new Coordinate(x1, y1, z1), new Coordinate(x2, y2, z2));
/*     */   }
/*     */   
/*     */   public Segment(double x1, double y1, double z1, double x2, double y2, double z2, Object data) {
/*  63 */     this(new Coordinate(x1, y1, z1), new Coordinate(x2, y2, z2), data);
/*     */   }
/*     */   
/*     */   public Segment(Coordinate p0, Coordinate p1, Object data) {
/*  74 */     this.ls = new LineSegment(p0, p1);
/*  75 */     this.data = data;
/*     */   }
/*     */   
/*     */   public Segment(Coordinate p0, Coordinate p1) {
/*  85 */     this.ls = new LineSegment(p0, p1);
/*     */   }
/*     */   
/*     */   public Coordinate getStart() {
/*  94 */     return this.ls.getCoordinate(0);
/*     */   }
/*     */   
/*     */   public Coordinate getEnd() {
/* 103 */     return this.ls.getCoordinate(1);
/*     */   }
/*     */   
/*     */   public double getStartX() {
/* 112 */     Coordinate p = this.ls.getCoordinate(0);
/* 113 */     return p.x;
/*     */   }
/*     */   
/*     */   public double getStartY() {
/* 122 */     Coordinate p = this.ls.getCoordinate(0);
/* 123 */     return p.y;
/*     */   }
/*     */   
/*     */   public double getStartZ() {
/* 132 */     Coordinate p = this.ls.getCoordinate(0);
/* 133 */     return p.z;
/*     */   }
/*     */   
/*     */   public double getEndX() {
/* 142 */     Coordinate p = this.ls.getCoordinate(1);
/* 143 */     return p.x;
/*     */   }
/*     */   
/*     */   public double getEndY() {
/* 152 */     Coordinate p = this.ls.getCoordinate(1);
/* 153 */     return p.y;
/*     */   }
/*     */   
/*     */   public double getEndZ() {
/* 162 */     Coordinate p = this.ls.getCoordinate(1);
/* 163 */     return p.z;
/*     */   }
/*     */   
/*     */   public LineSegment getLineSegment() {
/* 172 */     return this.ls;
/*     */   }
/*     */   
/*     */   public Object getData() {
/* 181 */     return this.data;
/*     */   }
/*     */   
/*     */   public void setData(Object data) {
/* 190 */     this.data = data;
/*     */   }
/*     */   
/*     */   public boolean equalsTopo(Segment s) {
/* 201 */     return this.ls.equalsTopo(s.getLineSegment());
/*     */   }
/*     */   
/*     */   public Coordinate intersection(Segment s) {
/* 211 */     return this.ls.intersection(s.getLineSegment());
/*     */   }
/*     */   
/*     */   public String toString() {
/* 220 */     return this.ls.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\triangulate\Segment.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */