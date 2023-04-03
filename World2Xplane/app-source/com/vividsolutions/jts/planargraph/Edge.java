/*     */ package com.vividsolutions.jts.planargraph;
/*     */ 
/*     */ public class Edge extends GraphComponent {
/*     */   protected DirectedEdge[] dirEdge;
/*     */   
/*     */   public Edge() {}
/*     */   
/*     */   public Edge(DirectedEdge de0, DirectedEdge de1) {
/*  71 */     setDirectedEdges(de0, de1);
/*     */   }
/*     */   
/*     */   public void setDirectedEdges(DirectedEdge de0, DirectedEdge de1) {
/*  80 */     this.dirEdge = new DirectedEdge[] { de0, de1 };
/*  81 */     de0.setEdge(this);
/*  82 */     de1.setEdge(this);
/*  83 */     de0.setSym(de1);
/*  84 */     de1.setSym(de0);
/*  85 */     de0.getFromNode().addOutEdge(de0);
/*  86 */     de1.getFromNode().addOutEdge(de1);
/*     */   }
/*     */   
/*     */   public DirectedEdge getDirEdge(int i) {
/*  95 */     return this.dirEdge[i];
/*     */   }
/*     */   
/*     */   public DirectedEdge getDirEdge(Node fromNode) {
/* 104 */     if (this.dirEdge[0].getFromNode() == fromNode)
/* 104 */       return this.dirEdge[0]; 
/* 105 */     if (this.dirEdge[1].getFromNode() == fromNode)
/* 105 */       return this.dirEdge[1]; 
/* 108 */     return null;
/*     */   }
/*     */   
/*     */   public Node getOppositeNode(Node node) {
/* 117 */     if (this.dirEdge[0].getFromNode() == node)
/* 117 */       return this.dirEdge[0].getToNode(); 
/* 118 */     if (this.dirEdge[1].getFromNode() == node)
/* 118 */       return this.dirEdge[1].getToNode(); 
/* 121 */     return null;
/*     */   }
/*     */   
/*     */   void remove() {
/* 128 */     this.dirEdge = null;
/*     */   }
/*     */   
/*     */   public boolean isRemoved() {
/* 138 */     return (this.dirEdge == null);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\planargraph\Edge.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */