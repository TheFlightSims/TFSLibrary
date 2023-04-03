/*    */ package org.apache.commons.math3.geometry.euclidean.twod;
/*    */ 
/*    */ public class Segment {
/*    */   private final Vector2D start;
/*    */   
/*    */   private final Vector2D end;
/*    */   
/*    */   private final Line line;
/*    */   
/*    */   public Segment(Vector2D start, Vector2D end, Line line) {
/* 41 */     this.start = start;
/* 42 */     this.end = end;
/* 43 */     this.line = line;
/*    */   }
/*    */   
/*    */   public Vector2D getStart() {
/* 50 */     return this.start;
/*    */   }
/*    */   
/*    */   public Vector2D getEnd() {
/* 57 */     return this.end;
/*    */   }
/*    */   
/*    */   public Line getLine() {
/* 64 */     return this.line;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\euclidean\twod\Segment.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */