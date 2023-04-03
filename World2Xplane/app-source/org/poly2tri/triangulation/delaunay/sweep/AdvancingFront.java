/*     */ package org.poly2tri.triangulation.delaunay.sweep;
/*     */ 
/*     */ import org.poly2tri.triangulation.TriangulationPoint;
/*     */ 
/*     */ public class AdvancingFront {
/*     */   public AdvancingFrontNode head;
/*     */   
/*     */   public AdvancingFrontNode tail;
/*     */   
/*     */   protected AdvancingFrontNode search;
/*     */   
/*     */   public AdvancingFront(AdvancingFrontNode head, AdvancingFrontNode tail) {
/*  38 */     this.head = head;
/*  39 */     this.tail = tail;
/*  40 */     this.search = head;
/*  41 */     addNode(head);
/*  42 */     addNode(tail);
/*     */   }
/*     */   
/*     */   public void addNode(AdvancingFrontNode node) {}
/*     */   
/*     */   public void removeNode(AdvancingFrontNode node) {}
/*     */   
/*     */   public String toString() {
/*  57 */     StringBuilder sb = new StringBuilder();
/*  58 */     AdvancingFrontNode node = this.head;
/*  59 */     while (node != this.tail) {
/*  61 */       sb.append(node.point.getX()).append("->");
/*  62 */       node = node.next;
/*     */     } 
/*  64 */     sb.append(this.tail.point.getX());
/*  65 */     return sb.toString();
/*     */   }
/*     */   
/*     */   private final AdvancingFrontNode findSearchNode(double x) {
/*  71 */     return this.search;
/*     */   }
/*     */   
/*     */   public AdvancingFrontNode locateNode(TriangulationPoint point) {
/*  83 */     return locateNode(point.getX());
/*     */   }
/*     */   
/*     */   private AdvancingFrontNode locateNode(double x) {
/*  88 */     AdvancingFrontNode node = findSearchNode(x);
/*  89 */     if (x < node.value) {
/*  91 */       while ((node = node.prev) != null) {
/*  93 */         if (x >= node.value) {
/*  95 */           this.search = node;
/*  96 */           return node;
/*     */         } 
/*     */       } 
/*     */     } else {
/* 102 */       while ((node = node.next) != null) {
/* 104 */         if (x < node.value) {
/* 106 */           this.search = node.prev;
/* 107 */           return node.prev;
/*     */         } 
/*     */       } 
/*     */     } 
/* 111 */     return null;
/*     */   }
/*     */   
/*     */   public AdvancingFrontNode locatePoint(TriangulationPoint point) {
/* 123 */     double px = point.getX();
/* 124 */     AdvancingFrontNode node = findSearchNode(px);
/* 125 */     double nx = node.point.getX();
/* 127 */     if (px == nx) {
/* 129 */       if (point != node.point)
/* 132 */         if (point == node.prev.point) {
/* 134 */           node = node.prev;
/* 136 */         } else if (point == node.next.point) {
/* 138 */           node = node.next;
/*     */         } else {
/* 142 */           throw new RuntimeException("Failed to find Node for given afront point");
/*     */         }  
/* 147 */     } else if (px < nx) {
/* 149 */       while ((node = node.prev) != null) {
/* 151 */         if (point == node.point)
/*     */           break; 
/*     */       } 
/*     */     } else {
/* 159 */       while ((node = node.next) != null) {
/* 161 */         if (point == node.point)
/*     */           break; 
/*     */       } 
/*     */     } 
/* 167 */     this.search = node;
/* 168 */     return node;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\poly2tri\triangulation\delaunay\sweep\AdvancingFront.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */