/*     */ package org.geotools.data.shapefile.index.quadtree;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ 
/*     */ public class Node {
/*     */   protected int numShapesId;
/*     */   
/*     */   private boolean visited = false;
/*     */   
/*     */   private boolean childrenVisited = false;
/*     */   
/*     */   private Envelope bounds;
/*     */   
/*     */   protected int[] shapesId;
/*     */   
/*     */   protected List subNodes;
/*     */   
/*     */   public Node(Envelope bounds) {
/*  43 */     this.bounds = new Envelope(bounds);
/*  44 */     this.subNodes = new ArrayList(4);
/*  45 */     this.shapesId = null;
/*     */   }
/*     */   
/*     */   public Envelope getBounds() {
/*  54 */     return this.bounds;
/*     */   }
/*     */   
/*     */   public void setBounds(Envelope bounds) {
/*  64 */     this.bounds = bounds;
/*     */   }
/*     */   
/*     */   public int getNumSubNodes() {
/*  73 */     return this.subNodes.size();
/*     */   }
/*     */   
/*     */   public int getNumShapeIds() {
/*  82 */     return this.numShapesId;
/*     */   }
/*     */   
/*     */   public void addSubNode(Node node) {
/*  94 */     if (node == null)
/*  95 */       throw new NullPointerException("Cannot add null to subnodes"); 
/*  98 */     this.subNodes.add(node);
/*     */   }
/*     */   
/*     */   public boolean removeSubNode(Node node) {
/* 110 */     return this.subNodes.remove(node);
/*     */   }
/*     */   
/*     */   public void clearSubNodes() {
/* 118 */     this.subNodes.clear();
/*     */   }
/*     */   
/*     */   public Node getSubNode(int pos) throws StoreException {
/* 133 */     return this.subNodes.get(pos);
/*     */   }
/*     */   
/*     */   public void addShapeId(int id) {
/* 142 */     if (this.shapesId == null) {
/* 143 */       this.shapesId = new int[4];
/* 144 */       Arrays.fill(this.shapesId, -1);
/* 145 */     } else if (this.shapesId.length == this.numShapesId) {
/* 147 */       int[] newIds = new int[(int)Math.ceil(this.numShapesId * 3.0D / 2.0D)];
/* 148 */       Arrays.fill(newIds, -1);
/* 149 */       System.arraycopy(this.shapesId, 0, newIds, 0, this.numShapesId);
/* 150 */       this.shapesId = newIds;
/*     */     } 
/* 153 */     this.shapesId[this.numShapesId] = id;
/* 154 */     this.numShapesId++;
/*     */   }
/*     */   
/*     */   public int getShapeId(int pos) {
/* 169 */     if (pos >= this.numShapesId)
/* 170 */       throw new ArrayIndexOutOfBoundsException("Requsted " + pos + " but size = " + this.numShapesId); 
/* 174 */     return this.shapesId[pos];
/*     */   }
/*     */   
/*     */   public void setShapesId(int[] ids) {
/* 183 */     if (ids == null) {
/* 184 */       this.numShapesId = 0;
/*     */     } else {
/* 186 */       this.shapesId = ids;
/* 187 */       this.numShapesId = 0;
/* 189 */       for (int i = 0; i < ids.length && 
/* 190 */         ids[i] != -1; i++)
/* 194 */         this.numShapesId++; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setShapesId(Node other) {
/* 200 */     this.numShapesId = other.numShapesId;
/* 201 */     this.shapesId = other.shapesId;
/*     */   }
/*     */   
/*     */   public int[] getShapesId() {
/* 210 */     return this.shapesId;
/*     */   }
/*     */   
/*     */   public boolean isVisited() {
/* 214 */     return this.visited;
/*     */   }
/*     */   
/*     */   public void setVisited(boolean visited) {
/* 218 */     this.visited = visited;
/*     */   }
/*     */   
/*     */   public boolean isChildrenVisited() {
/* 222 */     return this.childrenVisited;
/*     */   }
/*     */   
/*     */   public void setChildrenVisited(boolean childrenVisited) {
/* 226 */     this.childrenVisited = childrenVisited;
/*     */   }
/*     */   
/*     */   public Node copy() throws IOException {
/* 230 */     Node copy = new Node(this.bounds);
/* 231 */     copy.setShapesId(this.shapesId);
/* 232 */     copy.numShapesId = this.numShapesId;
/* 234 */     return copy;
/*     */   }
/*     */   
/*     */   public void close() {}
/*     */   
/*     */   public void clean() {
/* 249 */     this.shapesId = null;
/* 250 */     this.numShapesId = 0;
/* 251 */     this.subNodes.clear();
/*     */   }
/*     */   
/*     */   public void pack() {
/* 255 */     if (this.numShapesId == 0) {
/* 256 */       this.shapesId = null;
/* 257 */     } else if (this.shapesId != null && this.shapesId.length > this.numShapesId) {
/* 258 */       int[] ids = new int[this.numShapesId];
/* 259 */       System.arraycopy(this.shapesId, 0, ids, 0, this.numShapesId);
/* 260 */       this.shapesId = ids;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\index\quadtree\Node.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */