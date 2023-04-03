/*     */ package com.vividsolutions.jts.index.quadtree;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.index.ItemVisitor;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public abstract class NodeBase implements Serializable {
/*     */   public static int getSubnodeIndex(Envelope env, double centrex, double centrey) {
/*  62 */     int subnodeIndex = -1;
/*  63 */     if (env.getMinX() >= centrex) {
/*  64 */       if (env.getMinY() >= centrey)
/*  64 */         subnodeIndex = 3; 
/*  65 */       if (env.getMaxY() <= centrey)
/*  65 */         subnodeIndex = 1; 
/*     */     } 
/*  67 */     if (env.getMaxX() <= centrex) {
/*  68 */       if (env.getMinY() >= centrey)
/*  68 */         subnodeIndex = 2; 
/*  69 */       if (env.getMaxY() <= centrey)
/*  69 */         subnodeIndex = 0; 
/*     */     } 
/*  71 */     return subnodeIndex;
/*     */   }
/*     */   
/*  74 */   protected List items = new ArrayList();
/*     */   
/*  84 */   protected Node[] subnode = new Node[4];
/*     */   
/*     */   public List getItems() {
/*  89 */     return this.items;
/*     */   }
/*     */   
/*     */   public boolean hasItems() {
/*  91 */     return !this.items.isEmpty();
/*     */   }
/*     */   
/*     */   public void add(Object item) {
/*  95 */     this.items.add(item);
/*     */   }
/*     */   
/*     */   public boolean remove(Envelope itemEnv, Object item) {
/* 110 */     if (!isSearchMatch(itemEnv))
/* 111 */       return false; 
/* 113 */     boolean found = false;
/* 114 */     for (int i = 0; i < 4; i++) {
/* 115 */       if (this.subnode[i] != null) {
/* 116 */         found = this.subnode[i].remove(itemEnv, item);
/* 117 */         if (found) {
/* 119 */           if (this.subnode[i].isPrunable())
/* 120 */             this.subnode[i] = null; 
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 126 */     if (found)
/* 126 */       return found; 
/* 128 */     found = this.items.remove(item);
/* 129 */     return found;
/*     */   }
/*     */   
/*     */   public boolean isPrunable() {
/* 134 */     return (!hasChildren() && !hasItems());
/*     */   }
/*     */   
/*     */   public boolean hasChildren() {
/* 139 */     for (int i = 0; i < 4; i++) {
/* 140 */       if (this.subnode[i] != null)
/* 141 */         return true; 
/*     */     } 
/* 143 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 148 */     boolean isEmpty = true;
/* 149 */     if (!this.items.isEmpty())
/* 149 */       isEmpty = false; 
/* 150 */     for (int i = 0; i < 4; i++) {
/* 151 */       if (this.subnode[i] != null && 
/* 152 */         !this.subnode[i].isEmpty())
/* 153 */         isEmpty = false; 
/*     */     } 
/* 156 */     return isEmpty;
/*     */   }
/*     */   
/*     */   public List addAllItems(List resultItems) {
/* 165 */     resultItems.addAll(this.items);
/* 166 */     for (int i = 0; i < 4; i++) {
/* 167 */       if (this.subnode[i] != null)
/* 168 */         this.subnode[i].addAllItems(resultItems); 
/*     */     } 
/* 171 */     return resultItems;
/*     */   }
/*     */   
/*     */   protected abstract boolean isSearchMatch(Envelope paramEnvelope);
/*     */   
/*     */   public void addAllItemsFromOverlapping(Envelope searchEnv, List resultItems) {
/* 177 */     if (!isSearchMatch(searchEnv))
/*     */       return; 
/* 182 */     resultItems.addAll(this.items);
/* 184 */     for (int i = 0; i < 4; i++) {
/* 185 */       if (this.subnode[i] != null)
/* 186 */         this.subnode[i].addAllItemsFromOverlapping(searchEnv, resultItems); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void visit(Envelope searchEnv, ItemVisitor visitor) {
/* 193 */     if (!isSearchMatch(searchEnv))
/*     */       return; 
/* 198 */     visitItems(searchEnv, visitor);
/* 200 */     for (int i = 0; i < 4; i++) {
/* 201 */       if (this.subnode[i] != null)
/* 202 */         this.subnode[i].visit(searchEnv, visitor); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void visitItems(Envelope searchEnv, ItemVisitor visitor) {
/* 210 */     for (Iterator i = this.items.iterator(); i.hasNext();)
/* 211 */       visitor.visitItem(i.next()); 
/*     */   }
/*     */   
/*     */   int depth() {
/* 219 */     int maxSubDepth = 0;
/* 220 */     for (int i = 0; i < 4; i++) {
/* 221 */       if (this.subnode[i] != null) {
/* 222 */         int sqd = this.subnode[i].depth();
/* 223 */         if (sqd > maxSubDepth)
/* 224 */           maxSubDepth = sqd; 
/*     */       } 
/*     */     } 
/* 227 */     return maxSubDepth + 1;
/*     */   }
/*     */   
/*     */   int size() {
/* 232 */     int subSize = 0;
/* 233 */     for (int i = 0; i < 4; i++) {
/* 234 */       if (this.subnode[i] != null)
/* 235 */         subSize += this.subnode[i].size(); 
/*     */     } 
/* 238 */     return subSize + this.items.size();
/*     */   }
/*     */   
/*     */   int getNodeCount() {
/* 243 */     int subSize = 0;
/* 244 */     for (int i = 0; i < 4; i++) {
/* 245 */       if (this.subnode[i] != null)
/* 246 */         subSize += this.subnode[i].size(); 
/*     */     } 
/* 249 */     return subSize + 1;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\index\quadtree\NodeBase.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */