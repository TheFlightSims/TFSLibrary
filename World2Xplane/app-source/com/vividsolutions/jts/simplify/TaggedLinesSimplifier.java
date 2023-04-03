/*    */ package com.vividsolutions.jts.simplify;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ class TaggedLinesSimplifier {
/* 47 */   private LineSegmentIndex inputIndex = new LineSegmentIndex();
/*    */   
/* 48 */   private LineSegmentIndex outputIndex = new LineSegmentIndex();
/*    */   
/* 49 */   private double distanceTolerance = 0.0D;
/*    */   
/*    */   public void setDistanceTolerance(double distanceTolerance) {
/* 64 */     this.distanceTolerance = distanceTolerance;
/*    */   }
/*    */   
/*    */   public void simplify(Collection taggedLines) {
/* 73 */     for (Iterator<TaggedLineString> iterator1 = taggedLines.iterator(); iterator1.hasNext();)
/* 74 */       this.inputIndex.add(iterator1.next()); 
/* 76 */     for (Iterator<TaggedLineString> i = taggedLines.iterator(); i.hasNext(); ) {
/* 77 */       TaggedLineStringSimplifier tlss = new TaggedLineStringSimplifier(this.inputIndex, this.outputIndex);
/* 79 */       tlss.setDistanceTolerance(this.distanceTolerance);
/* 80 */       tlss.simplify(i.next());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\simplify\TaggedLinesSimplifier.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */