/*     */ package com.vividsolutions.jts.operation.relate;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geomgraph.Edge;
/*     */ import com.vividsolutions.jts.geomgraph.EdgeEnd;
/*     */ import com.vividsolutions.jts.geomgraph.EdgeIntersection;
/*     */ import com.vividsolutions.jts.geomgraph.EdgeIntersectionList;
/*     */ import com.vividsolutions.jts.geomgraph.Label;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class EdgeEndBuilder {
/*     */   public List computeEdgeEnds(Iterator<Edge> edges) {
/*  62 */     List l = new ArrayList();
/*  63 */     for (Iterator<Edge> i = edges; i.hasNext(); ) {
/*  64 */       Edge e = i.next();
/*  65 */       computeEdgeEnds(e, l);
/*     */     } 
/*  67 */     return l;
/*     */   }
/*     */   
/*     */   public void computeEdgeEnds(Edge edge, List l) {
/*  76 */     EdgeIntersectionList eiList = edge.getEdgeIntersectionList();
/*  79 */     eiList.addEndpoints();
/*  81 */     Iterator<EdgeIntersection> it = eiList.iterator();
/*  82 */     EdgeIntersection eiPrev = null;
/*  83 */     EdgeIntersection eiCurr = null;
/*  85 */     if (!it.hasNext())
/*     */       return; 
/*  86 */     EdgeIntersection eiNext = it.next();
/*     */     do {
/*  88 */       eiPrev = eiCurr;
/*  89 */       eiCurr = eiNext;
/*  90 */       eiNext = null;
/*  91 */       if (it.hasNext())
/*  91 */         eiNext = it.next(); 
/*  93 */       if (eiCurr == null)
/*     */         continue; 
/*  94 */       createEdgeEndForPrev(edge, l, eiCurr, eiPrev);
/*  95 */       createEdgeEndForNext(edge, l, eiCurr, eiNext);
/*  98 */     } while (eiCurr != null);
/*     */   }
/*     */   
/*     */   void createEdgeEndForPrev(Edge edge, List<EdgeEnd> l, EdgeIntersection eiCurr, EdgeIntersection eiPrev) {
/* 117 */     int iPrev = eiCurr.segmentIndex;
/* 118 */     if (eiCurr.dist == 0.0D) {
/* 120 */       if (iPrev == 0)
/*     */         return; 
/* 121 */       iPrev--;
/*     */     } 
/* 123 */     Coordinate pPrev = edge.getCoordinate(iPrev);
/* 125 */     if (eiPrev != null && eiPrev.segmentIndex >= iPrev)
/* 126 */       pPrev = eiPrev.coord; 
/* 128 */     Label label = new Label(edge.getLabel());
/* 130 */     label.flip();
/* 131 */     EdgeEnd e = new EdgeEnd(edge, eiCurr.coord, pPrev, label);
/* 133 */     l.add(e);
/*     */   }
/*     */   
/*     */   void createEdgeEndForNext(Edge edge, List<EdgeEnd> l, EdgeIntersection eiCurr, EdgeIntersection eiNext) {
/* 150 */     int iNext = eiCurr.segmentIndex + 1;
/* 152 */     if (iNext >= edge.getNumPoints() && eiNext == null)
/*     */       return; 
/* 154 */     Coordinate pNext = edge.getCoordinate(iNext);
/* 157 */     if (eiNext != null && eiNext.segmentIndex == eiCurr.segmentIndex)
/* 158 */       pNext = eiNext.coord; 
/* 160 */     EdgeEnd e = new EdgeEnd(edge, eiCurr.coord, pNext, new Label(edge.getLabel()));
/* 162 */     l.add(e);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\relate\EdgeEndBuilder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */