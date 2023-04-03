/*     */ package org.apache.commons.collections.set;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.CollectionUtils;
/*     */ import org.apache.commons.collections.collection.CompositeCollection;
/*     */ 
/*     */ public class CompositeSet extends CompositeCollection implements Set {
/*     */   public CompositeSet() {}
/*     */   
/*     */   public CompositeSet(Set set) {
/*  51 */     super(set);
/*     */   }
/*     */   
/*     */   public CompositeSet(Set[] sets) {
/*  58 */     super((Collection[])sets);
/*     */   }
/*     */   
/*     */   public synchronized void addComposited(Collection c) {
/*  73 */     if (!(c instanceof Set))
/*  74 */       throw new IllegalArgumentException("Collections added must implement java.util.Set"); 
/*  77 */     for (Iterator i = getCollections().iterator(); i.hasNext(); ) {
/*  78 */       Set set = i.next();
/*  79 */       Collection intersects = CollectionUtils.intersection(set, c);
/*  80 */       if (intersects.size() > 0) {
/*  81 */         if (this.mutator == null)
/*  82 */           throw new UnsupportedOperationException("Collision adding composited collection with no SetMutator set"); 
/*  85 */         if (!(this.mutator instanceof SetMutator))
/*  86 */           throw new UnsupportedOperationException("Collision adding composited collection to a CompositeSet with a CollectionMutator instead of a SetMutator"); 
/*  89 */         ((SetMutator)this.mutator).resolveCollision(this, set, (Set)c, intersects);
/*  90 */         if (CollectionUtils.intersection(set, c).size() > 0)
/*  91 */           throw new IllegalArgumentException("Attempt to add illegal entry unresolved by SetMutator.resolveCollision()"); 
/*     */       } 
/*     */     } 
/*  96 */     super.addComposited(new Collection[] { c });
/*     */   }
/*     */   
/*     */   public synchronized void addComposited(Collection c, Collection d) {
/* 105 */     if (!(c instanceof Set))
/* 105 */       throw new IllegalArgumentException("Argument must implement java.util.Set"); 
/* 106 */     if (!(d instanceof Set))
/* 106 */       throw new IllegalArgumentException("Argument must implement java.util.Set"); 
/* 107 */     addComposited((Collection[])new Set[] { (Set)c, (Set)d });
/*     */   }
/*     */   
/*     */   public synchronized void addComposited(Collection[] comps) {
/* 116 */     for (int i = comps.length - 1; i >= 0; i--)
/* 117 */       addComposited(comps[i]); 
/*     */   }
/*     */   
/*     */   public void setMutator(CompositeCollection.CollectionMutator mutator) {
/* 129 */     super.setMutator(mutator);
/*     */   }
/*     */   
/*     */   public boolean remove(Object obj) {
/* 142 */     for (Iterator i = getCollections().iterator(); i.hasNext(); ) {
/* 143 */       Set set = i.next();
/* 144 */       if (set.contains(obj))
/* 144 */         return set.remove(obj); 
/*     */     } 
/* 146 */     return false;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 154 */     if (obj instanceof Set) {
/* 155 */       Set set = (Set)obj;
/* 156 */       if (set.containsAll(this) && set.size() == size())
/* 157 */         return true; 
/*     */     } 
/* 160 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 167 */     int code = 0;
/* 168 */     for (Iterator i = iterator(); i.hasNext(); ) {
/* 169 */       Object next = i.next();
/* 170 */       code += (next != null) ? next.hashCode() : 0;
/*     */     } 
/* 172 */     return code;
/*     */   }
/*     */   
/*     */   public static interface SetMutator extends CompositeCollection.CollectionMutator {
/*     */     void resolveCollision(CompositeSet param1CompositeSet, Set param1Set1, Set param1Set2, Collection param1Collection);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\set\CompositeSet.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */