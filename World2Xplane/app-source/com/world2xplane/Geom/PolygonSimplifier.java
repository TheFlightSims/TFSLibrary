/*     */ package com.world2xplane.Geom;
/*     */ 
/*     */ import com.world2xplane.OSM.Node;
/*     */ import com.world2xplane.OSM.Way;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.polygon.LinearRing2D;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class PolygonSimplifier {
/*     */   public static final double EARTH_RAD = 6378137.0D;
/*     */   
/*     */   public LinearRing2D simplify(LinearRing2D ring) {
/*  40 */     Way way = new Way(ring);
/*  41 */     Way simplified = simplifyWay(way, 3.0D);
/*  44 */     LinearRing2D linearRing2D = new LinearRing2D();
/*  45 */     for (Node item : simplified.getNodes())
/*  46 */       linearRing2D.addVertex(new Point2D(item.getLon(), item.getLat())); 
/*  48 */     return linearRing2D;
/*     */   }
/*     */   
/*     */   public Way simplifyWay(Way w, double threshold) {
/*  52 */     int lower = 0;
/*  53 */     int i = 0;
/*  54 */     List<Node> newNodes = new ArrayList<>(w.getNodesCount());
/*  55 */     while (i < w.getNodesCount()) {
/*  57 */       i++;
/*  59 */       while (i < w.getNodesCount())
/*  60 */         i++; 
/*  63 */       buildSimplifiedNodeList(w.getNodes(), lower, FastMath.min(w.getNodesCount() - 1, i), threshold, newNodes);
/*  64 */       lower = i;
/*  65 */       i++;
/*     */     } 
/*  68 */     HashSet<Node> delNodes = new HashSet<>();
/*  69 */     delNodes.addAll(w.getNodes());
/*  70 */     delNodes.removeAll(newNodes);
/*  72 */     if (delNodes.isEmpty())
/*  72 */       return w; 
/*  74 */     w.getNodes().removeAll(delNodes);
/*  75 */     return w;
/*     */   }
/*     */   
/*     */   protected void buildSimplifiedNodeList(List<Node> wnew, int from, int to, double threshold, List<Node> simplifiedNodes) {
/*  89 */     Node fromN = wnew.get(from);
/*  90 */     Node toN = wnew.get(to);
/*  93 */     int imax = -1;
/*  94 */     double xtemax = 0.0D;
/*  95 */     for (int i = from + 1; i < to; i++) {
/*  96 */       Node n = wnew.get(i);
/*  97 */       double xte = FastMath.abs(6378137.0D * xtd(fromN.getLat() * Math.PI / 180.0D, fromN.getLon() * Math.PI / 180.0D, toN.getLat() * Math.PI / 180.0D, toN.getLon() * Math.PI / 180.0D, n.getLat() * Math.PI / 180.0D, n.getLon() * Math.PI / 180.0D));
/* 101 */       if (xte > xtemax) {
/* 102 */         xtemax = xte;
/* 103 */         imax = i;
/*     */       } 
/*     */     } 
/* 107 */     if (imax != -1 && xtemax >= threshold) {
/* 109 */       buildSimplifiedNodeList(wnew, from, imax, threshold, simplifiedNodes);
/* 110 */       buildSimplifiedNodeList(wnew, imax, to, threshold, simplifiedNodes);
/*     */     } else {
/* 113 */       if (simplifiedNodes.isEmpty() || simplifiedNodes.get(simplifiedNodes.size() - 1) != fromN)
/* 114 */         simplifiedNodes.add(fromN); 
/* 116 */       if (fromN != toN)
/* 117 */         simplifiedNodes.add(toN); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static double dist(double lat1, double lon1, double lat2, double lon2) {
/* 128 */     return 2.0D * FastMath.asin(FastMath.sqrt(FastMath.pow(FastMath.sin((lat1 - lat2) / 2.0D), 2.0D) + FastMath.cos(lat1) * FastMath.cos(lat2) * FastMath.pow(FastMath.sin((lon1 - lon2) / 2.0D), 2.0D)));
/*     */   }
/*     */   
/*     */   public static double course(double lat1, double lon1, double lat2, double lon2) {
/* 133 */     return FastMath.atan2(FastMath.sin(lon1 - lon2) * FastMath.cos(lat2), FastMath.cos(lat1) * FastMath.sin(lat2) - FastMath.sin(lat1) * FastMath.cos(lat2) * FastMath.cos(lon1 - lon2)) % 6.283185307179586D;
/*     */   }
/*     */   
/*     */   public static double xtd(double lat1, double lon1, double lat2, double lon2, double lat3, double lon3) {
/* 139 */     double dist_AD = dist(lat1, lon1, lat3, lon3);
/* 140 */     double crs_AD = course(lat1, lon1, lat3, lon3);
/* 141 */     double crs_AB = course(lat1, lon1, lat2, lon2);
/* 142 */     return FastMath.asin(FastMath.sin(dist_AD) * FastMath.sin(crs_AD - crs_AB));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Geom\PolygonSimplifier.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */