/*     */ package org.apache.commons.collections.list;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ 
/*     */ public class NodeCachingLinkedList extends AbstractLinkedList implements Serializable {
/*     */   private static final long serialVersionUID = 6897789178562232073L;
/*     */   
/*     */   protected static final int DEFAULT_MAXIMUM_CACHE_SIZE = 20;
/*     */   
/*     */   protected transient AbstractLinkedList.Node firstCachedNode;
/*     */   
/*     */   protected transient int cacheSize;
/*     */   
/*     */   protected int maximumCacheSize;
/*     */   
/*     */   public NodeCachingLinkedList() {
/*  79 */     this(20);
/*     */   }
/*     */   
/*     */   public NodeCachingLinkedList(Collection coll) {
/*  88 */     super(coll);
/*  89 */     this.maximumCacheSize = 20;
/*     */   }
/*     */   
/*     */   public NodeCachingLinkedList(int maximumCacheSize) {
/*  99 */     this.maximumCacheSize = maximumCacheSize;
/* 100 */     init();
/*     */   }
/*     */   
/*     */   protected int getMaximumCacheSize() {
/* 110 */     return this.maximumCacheSize;
/*     */   }
/*     */   
/*     */   protected void setMaximumCacheSize(int maximumCacheSize) {
/* 119 */     this.maximumCacheSize = maximumCacheSize;
/* 120 */     shrinkCacheToMaximumSize();
/*     */   }
/*     */   
/*     */   protected void shrinkCacheToMaximumSize() {
/* 128 */     while (this.cacheSize > this.maximumCacheSize)
/* 129 */       getNodeFromCache(); 
/*     */   }
/*     */   
/*     */   protected AbstractLinkedList.Node getNodeFromCache() {
/* 141 */     if (this.cacheSize == 0)
/* 142 */       return null; 
/* 144 */     AbstractLinkedList.Node cachedNode = this.firstCachedNode;
/* 145 */     this.firstCachedNode = cachedNode.next;
/* 146 */     cachedNode.next = null;
/* 148 */     this.cacheSize--;
/* 149 */     return cachedNode;
/*     */   }
/*     */   
/*     */   protected boolean isCacheFull() {
/* 158 */     return (this.cacheSize >= this.maximumCacheSize);
/*     */   }
/*     */   
/*     */   protected void addNodeToCache(AbstractLinkedList.Node node) {
/* 168 */     if (isCacheFull())
/*     */       return; 
/* 173 */     AbstractLinkedList.Node nextCachedNode = this.firstCachedNode;
/* 174 */     node.previous = null;
/* 175 */     node.next = nextCachedNode;
/* 176 */     node.setValue(null);
/* 177 */     this.firstCachedNode = node;
/* 178 */     this.cacheSize++;
/*     */   }
/*     */   
/*     */   protected AbstractLinkedList.Node createNode(Object value) {
/* 190 */     AbstractLinkedList.Node cachedNode = getNodeFromCache();
/* 191 */     if (cachedNode == null)
/* 192 */       return super.createNode(value); 
/* 194 */     cachedNode.setValue(value);
/* 195 */     return cachedNode;
/*     */   }
/*     */   
/*     */   protected void removeNode(AbstractLinkedList.Node node) {
/* 206 */     super.removeNode(node);
/* 207 */     addNodeToCache(node);
/*     */   }
/*     */   
/*     */   protected void removeAllNodes() {
/* 220 */     int numberOfNodesToCache = Math.min(this.size, this.maximumCacheSize - this.cacheSize);
/* 221 */     AbstractLinkedList.Node node = this.header.next;
/* 222 */     for (int currentIndex = 0; currentIndex < numberOfNodesToCache; currentIndex++) {
/* 223 */       AbstractLinkedList.Node oldNode = node;
/* 224 */       node = node.next;
/* 225 */       addNodeToCache(oldNode);
/*     */     } 
/* 227 */     super.removeAllNodes();
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 235 */     out.defaultWriteObject();
/* 236 */     doWriteObject(out);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 243 */     in.defaultReadObject();
/* 244 */     doReadObject(in);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\list\NodeCachingLinkedList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */