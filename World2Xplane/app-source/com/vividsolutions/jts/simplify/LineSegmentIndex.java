/*    */ package com.vividsolutions.jts.simplify;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Envelope;
/*    */ import com.vividsolutions.jts.geom.LineSegment;
/*    */ import com.vividsolutions.jts.index.quadtree.Quadtree;
/*    */ import java.util.List;
/*    */ 
/*    */ class LineSegmentIndex {
/* 49 */   private Quadtree index = new Quadtree();
/*    */   
/*    */   public void add(TaggedLineString line) {
/* 56 */     TaggedLineSegment[] segs = line.getSegments();
/* 57 */     for (int i = 0; i < segs.length; i++) {
/* 58 */       TaggedLineSegment seg = segs[i];
/* 59 */       add(seg);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void add(LineSegment seg) {
/* 65 */     this.index.insert(new Envelope(seg.p0, seg.p1), seg);
/*    */   }
/*    */   
/*    */   public void remove(LineSegment seg) {
/* 70 */     this.index.remove(new Envelope(seg.p0, seg.p1), seg);
/*    */   }
/*    */   
/*    */   public List query(LineSegment querySeg) {
/* 75 */     Envelope env = new Envelope(querySeg.p0, querySeg.p1);
/* 77 */     LineSegmentVisitor visitor = new LineSegmentVisitor(querySeg);
/* 78 */     this.index.query(env, visitor);
/* 79 */     List itemsFound = visitor.getItems();
/* 86 */     return itemsFound;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\simplify\LineSegmentIndex.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */