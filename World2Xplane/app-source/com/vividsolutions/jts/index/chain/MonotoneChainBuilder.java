/*     */ package com.vividsolutions.jts.index.chain;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geomgraph.Quadrant;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class MonotoneChainBuilder {
/*     */   public static int[] toIntArray(List list) {
/*  51 */     int[] array = new int[list.size()];
/*  52 */     for (int i = 0; i < array.length; i++)
/*  53 */       array[i] = ((Integer)list.get(i)).intValue(); 
/*  55 */     return array;
/*     */   }
/*     */   
/*     */   public static List getChains(Coordinate[] pts) {
/*  60 */     return getChains(pts, null);
/*     */   }
/*     */   
/*     */   public static List getChains(Coordinate[] pts, Object context) {
/*  69 */     List<MonotoneChain> mcList = new ArrayList();
/*  70 */     int[] startIndex = getChainStartIndices(pts);
/*  71 */     for (int i = 0; i < startIndex.length - 1; i++) {
/*  72 */       MonotoneChain mc = new MonotoneChain(pts, startIndex[i], startIndex[i + 1], context);
/*  73 */       mcList.add(mc);
/*     */     } 
/*  75 */     return mcList;
/*     */   }
/*     */   
/*     */   public static int[] getChainStartIndices(Coordinate[] pts) {
/*  87 */     int start = 0;
/*  88 */     List<Integer> startIndexList = new ArrayList();
/*  89 */     startIndexList.add(new Integer(start));
/*     */     do {
/*  91 */       int last = findChainEnd(pts, start);
/*  92 */       startIndexList.add(new Integer(last));
/*  93 */       start = last;
/*  94 */     } while (start < pts.length - 1);
/*  96 */     int[] startIndex = toIntArray(startIndexList);
/*  97 */     return startIndex;
/*     */   }
/*     */   
/*     */   private static int findChainEnd(Coordinate[] pts, int start) {
/* 111 */     int safeStart = start;
/* 114 */     while (safeStart < pts.length - 1 && pts[safeStart].equals2D(pts[safeStart + 1]))
/* 115 */       safeStart++; 
/* 118 */     if (safeStart >= pts.length - 1)
/* 119 */       return pts.length - 1; 
/* 122 */     int chainQuad = Quadrant.quadrant(pts[safeStart], pts[safeStart + 1]);
/* 123 */     int last = start + 1;
/* 124 */     while (last < pts.length) {
/* 126 */       if (!pts[last - 1].equals2D(pts[last])) {
/* 128 */         int quad = Quadrant.quadrant(pts[last - 1], pts[last]);
/* 129 */         if (quad != chainQuad)
/*     */           break; 
/*     */       } 
/* 131 */       last++;
/*     */     } 
/* 133 */     return last - 1;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\index\chain\MonotoneChainBuilder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */