/*     */ package com.vividsolutions.jts.index.bintree;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public abstract class NodeBase {
/*     */   public static int getSubnodeIndex(Interval interval, double centre) {
/*  56 */     int subnodeIndex = -1;
/*  57 */     if (interval.min >= centre)
/*  57 */       subnodeIndex = 1; 
/*  58 */     if (interval.max <= centre)
/*  58 */       subnodeIndex = 0; 
/*  59 */     return subnodeIndex;
/*     */   }
/*     */   
/*  62 */   protected List items = new ArrayList();
/*     */   
/*  69 */   protected Node[] subnode = new Node[2];
/*     */   
/*     */   public List getItems() {
/*  74 */     return this.items;
/*     */   }
/*     */   
/*     */   public void add(Object item) {
/*  78 */     this.items.add(item);
/*     */   }
/*     */   
/*     */   public List addAllItems(List items) {
/*  82 */     items.addAll(this.items);
/*  83 */     for (int i = 0; i < 2; i++) {
/*  84 */       if (this.subnode[i] != null)
/*  85 */         this.subnode[i].addAllItems(items); 
/*     */     } 
/*  88 */     return items;
/*     */   }
/*     */   
/*     */   protected abstract boolean isSearchMatch(Interval paramInterval);
/*     */   
/*     */   public void addAllItemsFromOverlapping(Interval interval, Collection resultItems) {
/* 102 */     if (interval != null && !isSearchMatch(interval))
/*     */       return; 
/* 106 */     resultItems.addAll(this.items);
/* 108 */     if (this.subnode[0] != null)
/* 108 */       this.subnode[0].addAllItemsFromOverlapping(interval, resultItems); 
/* 109 */     if (this.subnode[1] != null)
/* 109 */       this.subnode[1].addAllItemsFromOverlapping(interval, resultItems); 
/*     */   }
/*     */   
/*     */   public boolean remove(Interval itemInterval, Object item) {
/* 122 */     if (!isSearchMatch(itemInterval))
/* 123 */       return false; 
/* 125 */     boolean found = false;
/* 126 */     for (int i = 0; i < 2; i++) {
/* 127 */       if (this.subnode[i] != null) {
/* 128 */         found = this.subnode[i].remove(itemInterval, item);
/* 129 */         if (found) {
/* 131 */           if (this.subnode[i].isPrunable())
/* 132 */             this.subnode[i] = null; 
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 138 */     if (found)
/* 138 */       return found; 
/* 140 */     found = this.items.remove(item);
/* 141 */     return found;
/*     */   }
/*     */   
/*     */   public boolean isPrunable() {
/* 146 */     return (!hasChildren() && !hasItems());
/*     */   }
/*     */   
/*     */   public boolean hasChildren() {
/* 151 */     for (int i = 0; i < 2; i++) {
/* 152 */       if (this.subnode[i] != null)
/* 153 */         return true; 
/*     */     } 
/* 155 */     return false;
/*     */   }
/*     */   
/*     */   public boolean hasItems() {
/* 158 */     return !this.items.isEmpty();
/*     */   }
/*     */   
/*     */   int depth() {
/* 162 */     int maxSubDepth = 0;
/* 163 */     for (int i = 0; i < 2; i++) {
/* 164 */       if (this.subnode[i] != null) {
/* 165 */         int sqd = this.subnode[i].depth();
/* 166 */         if (sqd > maxSubDepth)
/* 167 */           maxSubDepth = sqd; 
/*     */       } 
/*     */     } 
/* 170 */     return maxSubDepth + 1;
/*     */   }
/*     */   
/*     */   int size() {
/* 175 */     int subSize = 0;
/* 176 */     for (int i = 0; i < 2; i++) {
/* 177 */       if (this.subnode[i] != null)
/* 178 */         subSize += this.subnode[i].size(); 
/*     */     } 
/* 181 */     return subSize + this.items.size();
/*     */   }
/*     */   
/*     */   int nodeSize() {
/* 186 */     int subSize = 0;
/* 187 */     for (int i = 0; i < 2; i++) {
/* 188 */       if (this.subnode[i] != null)
/* 189 */         subSize += this.subnode[i].nodeSize(); 
/*     */     } 
/* 192 */     return subSize + 1;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\index\bintree\NodeBase.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */