/*     */ package org.jfree.chart.entity;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ public class StandardEntityCollection implements EntityCollection, Cloneable, Serializable {
/*     */   private static final long serialVersionUID = 5384773031184897047L;
/*     */   
/*  76 */   private List entities = new ArrayList();
/*     */   
/*     */   public int getEntityCount() {
/*  85 */     return this.entities.size();
/*     */   }
/*     */   
/*     */   public ChartEntity getEntity(int index) {
/*  96 */     return this.entities.get(index);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 103 */     this.entities.clear();
/*     */   }
/*     */   
/*     */   public void add(ChartEntity entity) {
/* 112 */     if (entity == null)
/* 113 */       throw new IllegalArgumentException("Null 'entity' argument."); 
/* 115 */     this.entities.add(entity);
/*     */   }
/*     */   
/*     */   public void addAll(EntityCollection collection) {
/* 124 */     this.entities.addAll(collection.getEntities());
/*     */   }
/*     */   
/*     */   public ChartEntity getEntity(double x, double y) {
/* 137 */     int entityCount = this.entities.size();
/* 138 */     for (int i = entityCount - 1; i >= 0; i--) {
/* 139 */       ChartEntity entity = this.entities.get(i);
/* 140 */       if (entity.getArea().contains(x, y))
/* 141 */         return entity; 
/*     */     } 
/* 144 */     return null;
/*     */   }
/*     */   
/*     */   public Collection getEntities() {
/* 153 */     return Collections.unmodifiableCollection(this.entities);
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/* 162 */     return this.entities.iterator();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 173 */     if (obj == this)
/* 174 */       return true; 
/* 176 */     if (obj instanceof StandardEntityCollection) {
/* 177 */       StandardEntityCollection that = (StandardEntityCollection)obj;
/* 178 */       return ObjectUtilities.equal(this.entities, that.entities);
/*     */     } 
/* 180 */     return false;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 191 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\entity\StandardEntityCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */