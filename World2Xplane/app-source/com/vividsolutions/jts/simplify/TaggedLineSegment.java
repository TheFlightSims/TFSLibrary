/*    */ package com.vividsolutions.jts.simplify;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.LineSegment;
/*    */ 
/*    */ class TaggedLineSegment extends LineSegment {
/*    */   private Geometry parent;
/*    */   
/*    */   private int index;
/*    */   
/*    */   public TaggedLineSegment(Coordinate p0, Coordinate p1, Geometry parent, int index) {
/* 50 */     super(p0, p1);
/* 51 */     this.parent = parent;
/* 52 */     this.index = index;
/*    */   }
/*    */   
/*    */   public TaggedLineSegment(Coordinate p0, Coordinate p1) {
/* 56 */     this(p0, p1, null, -1);
/*    */   }
/*    */   
/*    */   public Geometry getParent() {
/* 59 */     return this.parent;
/*    */   }
/*    */   
/*    */   public int getIndex() {
/* 60 */     return this.index;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\simplify\TaggedLineSegment.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */