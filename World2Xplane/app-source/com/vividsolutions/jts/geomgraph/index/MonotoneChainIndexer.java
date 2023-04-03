/*     */ package com.vividsolutions.jts.geomgraph.index;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geomgraph.Quadrant;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class MonotoneChainIndexer {
/*     */   public static int[] toIntArray(List list) {
/*  68 */     int[] array = new int[list.size()];
/*  69 */     for (int i = 0; i < array.length; i++)
/*  70 */       array[i] = ((Integer)list.get(i)).intValue(); 
/*  72 */     return array;
/*     */   }
/*     */   
/*     */   public int[] getChainStartIndices(Coordinate[] pts) {
/*  81 */     int start = 0;
/*  82 */     List<Integer> startIndexList = new ArrayList();
/*  83 */     startIndexList.add(new Integer(start));
/*     */     do {
/*  85 */       int last = findChainEnd(pts, start);
/*  86 */       startIndexList.add(new Integer(last));
/*  87 */       start = last;
/*  88 */     } while (start < pts.length - 1);
/*  90 */     int[] startIndex = toIntArray(startIndexList);
/*  91 */     return startIndex;
/*     */   }
/*     */   
/*     */   private int findChainEnd(Coordinate[] pts, int start) {
/* 100 */     int chainQuad = Quadrant.quadrant(pts[start], pts[start + 1]);
/* 101 */     int last = start + 1;
/* 102 */     while (last < pts.length) {
/* 104 */       int quad = Quadrant.quadrant(pts[last - 1], pts[last]);
/* 105 */       if (quad != chainQuad)
/*     */         break; 
/* 106 */       last++;
/*     */     } 
/* 108 */     return last - 1;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geomgraph\index\MonotoneChainIndexer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */