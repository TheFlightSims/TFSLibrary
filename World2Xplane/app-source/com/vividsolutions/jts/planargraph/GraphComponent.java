/*     */ package com.vividsolutions.jts.planargraph;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public abstract class GraphComponent {
/*     */   public static void setVisited(Iterator<GraphComponent> i, boolean visited) {
/*  67 */     while (i.hasNext()) {
/*  68 */       GraphComponent comp = i.next();
/*  69 */       comp.setVisited(visited);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void setMarked(Iterator<GraphComponent> i, boolean marked) {
/*  81 */     while (i.hasNext()) {
/*  82 */       GraphComponent comp = i.next();
/*  83 */       comp.setMarked(marked);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static GraphComponent getComponentWithVisitedState(Iterator<GraphComponent> i, boolean visitedState) {
/*  97 */     while (i.hasNext()) {
/*  98 */       GraphComponent comp = i.next();
/*  99 */       if (comp.isVisited() == visitedState)
/* 100 */         return comp; 
/*     */     } 
/* 102 */     return null;
/*     */   }
/*     */   
/*     */   protected boolean isMarked = false;
/*     */   
/*     */   protected boolean isVisited = false;
/*     */   
/*     */   private Object data;
/*     */   
/*     */   public boolean isVisited() {
/* 116 */     return this.isVisited;
/*     */   }
/*     */   
/*     */   public void setVisited(boolean isVisited) {
/* 122 */     this.isVisited = isVisited;
/*     */   }
/*     */   
/*     */   public boolean isMarked() {
/* 129 */     return this.isMarked;
/*     */   }
/*     */   
/*     */   public void setMarked(boolean isMarked) {
/* 135 */     this.isMarked = isMarked;
/*     */   }
/*     */   
/*     */   public void setContext(Object data) {
/* 142 */     this.data = data;
/*     */   }
/*     */   
/*     */   public Object getContext() {
/* 149 */     return this.data;
/*     */   }
/*     */   
/*     */   public void setData(Object data) {
/* 156 */     this.data = data;
/*     */   }
/*     */   
/*     */   public Object getData() {
/* 163 */     return this.data;
/*     */   }
/*     */   
/*     */   public abstract boolean isRemoved();
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\planargraph\GraphComponent.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */