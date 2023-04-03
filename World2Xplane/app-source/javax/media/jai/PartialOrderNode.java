/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ 
/*     */ final class PartialOrderNode implements Cloneable, Serializable {
/*     */   protected String name;
/*     */   
/*     */   protected Object nodeData;
/*     */   
/*  48 */   protected int inDegree = 0;
/*     */   
/*  51 */   protected int copyInDegree = 0;
/*     */   
/*  54 */   protected PartialOrderNode zeroLink = null;
/*     */   
/*  57 */   Vector neighbors = new Vector();
/*     */   
/*     */   PartialOrderNode(Object nodeData, String name) {
/*  65 */     this.nodeData = nodeData;
/*  66 */     this.name = name;
/*     */   }
/*     */   
/*     */   Object getData() {
/*  71 */     return this.nodeData;
/*     */   }
/*     */   
/*     */   String getName() {
/*  76 */     return this.name;
/*     */   }
/*     */   
/*     */   int getInDegree() {
/*  81 */     return this.inDegree;
/*     */   }
/*     */   
/*     */   int getCopyInDegree() {
/*  86 */     return this.copyInDegree;
/*     */   }
/*     */   
/*     */   void setCopyInDegree(int copyInDegree) {
/*  91 */     this.copyInDegree = copyInDegree;
/*     */   }
/*     */   
/*     */   PartialOrderNode getZeroLink() {
/*  96 */     return this.zeroLink;
/*     */   }
/*     */   
/*     */   void setZeroLink(PartialOrderNode poNode) {
/* 101 */     this.zeroLink = poNode;
/*     */   }
/*     */   
/*     */   Enumeration getNeighbors() {
/* 106 */     return this.neighbors.elements();
/*     */   }
/*     */   
/*     */   void addEdge(PartialOrderNode poNode) {
/* 114 */     this.neighbors.addElement(poNode);
/* 115 */     poNode.incrementInDegree();
/*     */   }
/*     */   
/*     */   void removeEdge(PartialOrderNode poNode) {
/* 123 */     this.neighbors.removeElement(poNode);
/* 124 */     poNode.decrementInDegree();
/*     */   }
/*     */   
/*     */   void incrementInDegree() {
/* 129 */     this.inDegree++;
/*     */   }
/*     */   
/*     */   void incrementCopyInDegree() {
/* 134 */     this.copyInDegree++;
/*     */   }
/*     */   
/*     */   void decrementInDegree() {
/* 139 */     this.inDegree--;
/*     */   }
/*     */   
/*     */   void decrementCopyInDegree() {
/* 144 */     this.copyInDegree--;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\PartialOrderNode.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */