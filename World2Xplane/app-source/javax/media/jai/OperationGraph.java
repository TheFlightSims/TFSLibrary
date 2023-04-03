/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ 
/*     */ class OperationGraph implements Serializable {
/*  46 */   Vector operations = new Vector();
/*     */   
/*     */   Vector orderedOperations;
/*     */   
/*     */   boolean isChanged = true;
/*     */   
/*     */   private boolean lookupByName = false;
/*     */   
/*     */   OperationGraph() {}
/*     */   
/*     */   OperationGraph(boolean lookupByName) {
/*  77 */     this.lookupByName = lookupByName;
/*     */   }
/*     */   
/*     */   private boolean compare(PartialOrderNode poNode, Object op) {
/*  84 */     if (this.lookupByName)
/*  85 */       return poNode.getName().equalsIgnoreCase((String)op); 
/*  87 */     return (poNode.getData() == op);
/*     */   }
/*     */   
/*     */   void addOp(PartialOrderNode poNode) {
/*  95 */     this.operations.addElement(poNode);
/*  96 */     this.isChanged = true;
/*     */   }
/*     */   
/*     */   synchronized boolean removeOp(Object op) {
/* 108 */     boolean retval = false;
/* 110 */     PartialOrderNode poNode = lookupOp(op);
/* 112 */     if (poNode != null) {
/* 113 */       retval = this.operations.removeElement(poNode);
/* 115 */       if (retval)
/* 116 */         this.isChanged = true; 
/*     */     } 
/* 119 */     return retval;
/*     */   }
/*     */   
/*     */   PartialOrderNode lookupOp(Object op) {
/* 128 */     int num = this.operations.size();
/* 130 */     for (int i = 0; i < num; i++) {
/* 131 */       PartialOrderNode poNode = this.operations.elementAt(i);
/* 133 */       if (compare(poNode, op)) {
/* 134 */         PartialOrderNode tempNode = poNode;
/* 135 */         return tempNode;
/*     */       } 
/*     */     } 
/* 139 */     return null;
/*     */   }
/*     */   
/*     */   synchronized boolean setPreference(Object preferred, Object other) {
/* 145 */     boolean retval = false;
/* 147 */     if (preferred == null || other == null)
/* 148 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 152 */     if (preferred == other)
/* 153 */       return retval; 
/* 155 */     PartialOrderNode preferredPONode = lookupOp(preferred);
/* 156 */     PartialOrderNode otherPONode = lookupOp(other);
/* 158 */     if (preferredPONode != null && otherPONode != null) {
/* 159 */       preferredPONode.addEdge(otherPONode);
/* 161 */       retval = true;
/* 162 */       this.isChanged = true;
/*     */     } 
/* 165 */     return retval;
/*     */   }
/*     */   
/*     */   synchronized boolean unsetPreference(Object preferred, Object other) {
/* 171 */     boolean retval = false;
/* 173 */     if (preferred == null || other == null)
/* 174 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 178 */     if (preferred == other)
/* 179 */       return retval; 
/* 181 */     PartialOrderNode preferredPONode = lookupOp(preferred);
/* 182 */     PartialOrderNode otherPONode = lookupOp(other);
/* 184 */     if (preferredPONode != null && otherPONode != null) {
/* 185 */       preferredPONode.removeEdge(otherPONode);
/* 187 */       retval = true;
/* 188 */       this.isChanged = true;
/*     */     } 
/* 191 */     return retval;
/*     */   }
/*     */   
/*     */   public synchronized Vector getOrderedOperationList() {
/* 201 */     if (!this.isChanged) {
/* 203 */       Vector vector = this.orderedOperations;
/* 204 */       return vector;
/*     */     } 
/* 207 */     int num = this.operations.size();
/* 208 */     for (int i = 0; i < num; i++) {
/* 209 */       PartialOrderNode pon = this.operations.elementAt(i);
/* 210 */       pon.setCopyInDegree(pon.getInDegree());
/*     */     } 
/* 215 */     this.orderedOperations = new Vector(num);
/* 216 */     this.isChanged = false;
/* 219 */     PartialOrderNode zeroList = null;
/*     */     int j;
/* 224 */     for (j = 0; j < num; j++) {
/* 225 */       PartialOrderNode poNode = this.operations.elementAt(j);
/* 226 */       if (poNode.getCopyInDegree() == 0) {
/* 227 */         poNode.setZeroLink(zeroList);
/* 228 */         zeroList = poNode;
/*     */       } 
/*     */     } 
/* 233 */     for (j = 0; j < num; j++) {
/* 235 */       if (zeroList == null) {
/* 236 */         this.orderedOperations = null;
/* 237 */         return null;
/*     */       } 
/* 242 */       PartialOrderNode firstNode = zeroList;
/* 244 */       this.orderedOperations.addElement(firstNode);
/* 247 */       zeroList = zeroList.getZeroLink();
/* 249 */       Enumeration neighbors = firstNode.getNeighbors();
/* 250 */       while (neighbors.hasMoreElements()) {
/* 251 */         PartialOrderNode poNode = neighbors.nextElement();
/* 252 */         poNode.decrementCopyInDegree();
/* 256 */         if (poNode.getCopyInDegree() == 0) {
/* 257 */           poNode.setZeroLink(zeroList);
/* 258 */           zeroList = poNode;
/*     */         } 
/*     */       } 
/*     */     } 
/* 263 */     Vector ordered = this.orderedOperations;
/* 264 */     return ordered;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\OperationGraph.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */