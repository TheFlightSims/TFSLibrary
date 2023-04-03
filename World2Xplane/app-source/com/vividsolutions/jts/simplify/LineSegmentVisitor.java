/*     */ package com.vividsolutions.jts.simplify;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.LineSegment;
/*     */ import com.vividsolutions.jts.index.ItemVisitor;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ class LineSegmentVisitor implements ItemVisitor {
/*     */   private LineSegment querySeg;
/*     */   
/*  99 */   private ArrayList items = new ArrayList();
/*     */   
/*     */   public LineSegmentVisitor(LineSegment querySeg) {
/* 102 */     this.querySeg = querySeg;
/*     */   }
/*     */   
/*     */   public void visitItem(Object item) {
/* 107 */     LineSegment seg = (LineSegment)item;
/* 108 */     if (Envelope.intersects(seg.p0, seg.p1, this.querySeg.p0, this.querySeg.p1))
/* 109 */       this.items.add(item); 
/*     */   }
/*     */   
/*     */   public ArrayList getItems() {
/* 112 */     return this.items;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\simplify\LineSegmentVisitor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */