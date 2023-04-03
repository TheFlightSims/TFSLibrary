/*     */ package com.vividsolutions.jts.noding;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ 
/*     */ class NodeVertexIterator implements Iterator {
/*     */   private SegmentNodeList nodeList;
/*     */   
/*     */   private NodedSegmentString edge;
/*     */   
/*     */   private Iterator nodeIt;
/*     */   
/* 275 */   private SegmentNode currNode = null;
/*     */   
/* 276 */   private SegmentNode nextNode = null;
/*     */   
/* 277 */   private int currSegIndex = 0;
/*     */   
/*     */   NodeVertexIterator(SegmentNodeList nodeList) {
/* 281 */     this.nodeList = nodeList;
/* 282 */     this.edge = nodeList.getEdge();
/* 283 */     this.nodeIt = nodeList.iterator();
/* 284 */     readNextNode();
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/* 288 */     if (this.nextNode == null)
/* 288 */       return false; 
/* 289 */     return true;
/*     */   }
/*     */   
/*     */   public Object next() {
/* 294 */     if (this.currNode == null) {
/* 295 */       this.currNode = this.nextNode;
/* 296 */       this.currSegIndex = this.currNode.segmentIndex;
/* 297 */       readNextNode();
/* 298 */       return this.currNode;
/*     */     } 
/* 301 */     if (this.nextNode == null)
/* 301 */       return null; 
/* 303 */     if (this.nextNode.segmentIndex == this.currNode.segmentIndex) {
/* 304 */       this.currNode = this.nextNode;
/* 305 */       this.currSegIndex = this.currNode.segmentIndex;
/* 306 */       readNextNode();
/* 307 */       return this.currNode;
/*     */     } 
/* 310 */     if (this.nextNode.segmentIndex > this.currNode.segmentIndex);
/* 313 */     return null;
/*     */   }
/*     */   
/*     */   private void readNextNode() {
/* 318 */     if (this.nodeIt.hasNext()) {
/* 319 */       this.nextNode = this.nodeIt.next();
/*     */     } else {
/* 321 */       this.nextNode = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void remove() {
/* 329 */     throw new UnsupportedOperationException(getClass().getName());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\noding\NodeVertexIterator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */