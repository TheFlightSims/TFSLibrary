/*     */ package com.vividsolutions.jts.index.strtree;
/*     */ 
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public abstract class AbstractNode implements Boundable, Serializable {
/*     */   private static final long serialVersionUID = 6493722185909573708L;
/*     */   
/*  57 */   private ArrayList childBoundables = new ArrayList();
/*     */   
/*  58 */   private Object bounds = null;
/*     */   
/*     */   private int level;
/*     */   
/*     */   public AbstractNode() {}
/*     */   
/*     */   public AbstractNode(int level) {
/*  73 */     this.level = level;
/*     */   }
/*     */   
/*     */   public List getChildBoundables() {
/*  81 */     return this.childBoundables;
/*     */   }
/*     */   
/*     */   protected abstract Object computeBounds();
/*     */   
/*     */   public Object getBounds() {
/* 102 */     if (this.bounds == null)
/* 103 */       this.bounds = computeBounds(); 
/* 105 */     return this.bounds;
/*     */   }
/*     */   
/*     */   public int getLevel() {
/* 113 */     return this.level;
/*     */   }
/*     */   
/*     */   public int size() {
/* 123 */     return this.childBoundables.size();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 133 */     return this.childBoundables.isEmpty();
/*     */   }
/*     */   
/*     */   public void addChildBoundable(Boundable childBoundable) {
/* 141 */     Assert.isTrue((this.bounds == null));
/* 142 */     this.childBoundables.add(childBoundable);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\index\strtree\AbstractNode.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */