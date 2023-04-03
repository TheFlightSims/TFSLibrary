/*     */ package com.world2xplane.OSM;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.polygon.LinearRing2D;
/*     */ 
/*     */ public class Way {
/*     */   public int id;
/*     */   
/*     */   public OSMRelation.Member osmPolygon;
/*     */   
/*     */   private List<Node> nodes;
/*     */   
/*     */   public Way(OSMRelation.Member osmPolygon) {
/*  56 */     this.nodes = new ArrayList<>();
/*     */     this.osmPolygon = osmPolygon;
/*     */   }
/*     */   
/*     */   public Way() {
/*  56 */     this.nodes = new ArrayList<>();
/*     */   }
/*     */   
/*     */   public int getId() {
/*     */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(int id) {
/*     */     this.id = id;
/*     */   }
/*     */   
/*     */   public List<Node> getNodes() {
/*  59 */     return this.nodes;
/*     */   }
/*     */   
/*     */   public void setNodes(List<Node> nodes) {
/*  63 */     this.nodes = nodes;
/*     */   }
/*     */   
/*     */   public Node getLast() {
/*  67 */     return this.nodes.get(this.nodes.size() - 1);
/*     */   }
/*     */   
/*     */   public Node getFirst() {
/*  71 */     return this.nodes.get(0);
/*     */   }
/*     */   
/*     */   public int getNodesCount() {
/*  76 */     return this.nodes.size();
/*     */   }
/*     */   
/*     */   public Node getNode(int pos) {
/*  81 */     return this.nodes.get(pos);
/*     */   }
/*     */   
/*     */   public boolean isClosed() {
/*  85 */     return getFirst().equals(getLast());
/*     */   }
/*     */   
/*     */   public Way(LinearRing2D way) {
/*     */     this.nodes = new ArrayList<>();
/*  89 */     for (Point2D item : way.vertices()) {
/*  90 */       Node node = new Node(item);
/*  91 */       this.nodes.add(node);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Way(Way w) {
/*     */     this.nodes = new ArrayList<>();
/*  96 */     for (Node item : w.getNodes())
/*  97 */       this.nodes.add(item); 
/*     */   }
/*     */   
/*     */   public LinearRing2D toLinearString() {
/* 105 */     LinearRing2D linearRing2D = new LinearRing2D();
/* 106 */     for (Node node : this.nodes)
/* 107 */       linearRing2D.addVertex(node.getCoord()); 
/* 109 */     return linearRing2D;
/*     */   }
/*     */   
/*     */   public OSMRelation.Member getOsmPolygon() {
/* 113 */     return this.osmPolygon;
/*     */   }
/*     */   
/*     */   public void setOsmPolygon(OSMRelation.Member osmPolygon) {
/* 117 */     this.osmPolygon = osmPolygon;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 127 */     if (obj instanceof Way)
/* 128 */       return (((Way)obj).id == this.id && obj.getClass() == getClass()); 
/* 129 */     return false;
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/* 138 */     return this.id;
/*     */   }
/*     */   
/*     */   public void clearSelection(HashSet<Node> delNodes) {
/* 142 */     for (Node item : delNodes)
/* 143 */       this.nodes.remove(item); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\OSM\Way.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */