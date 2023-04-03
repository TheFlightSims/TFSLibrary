/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class DerivedSet<B, E> extends AbstractSet<E> implements CheckedCollection<E>, Serializable {
/*     */   private static final long serialVersionUID = -4662336508586424581L;
/*     */   
/*     */   protected final Set<B> base;
/*     */   
/*     */   private final Class<E> derivedType;
/*     */   
/*     */   public DerivedSet(Set<B> base) {
/*  77 */     this(base, (Class)Object.class);
/*     */   }
/*     */   
/*     */   public DerivedSet(Set<B> base, Class<E> derivedType) {
/*  89 */     this.base = base;
/*  90 */     this.derivedType = derivedType;
/*     */   }
/*     */   
/*     */   public Class<E> getElementType() {
/*  99 */     return this.derivedType;
/*     */   }
/*     */   
/*     */   protected abstract E baseToDerived(B paramB);
/*     */   
/*     */   protected abstract B derivedToBase(E paramE);
/*     */   
/*     */   public Iterator<E> iterator() {
/* 128 */     return new Iter(this.base.iterator());
/*     */   }
/*     */   
/*     */   public int size() {
/* 138 */     int count = 0;
/* 139 */     for (Iterator<E> it = iterator(); it.hasNext(); ) {
/* 140 */       it.next();
/* 141 */       count++;
/*     */     } 
/* 143 */     return count;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 153 */     return (this.base.isEmpty() || super.isEmpty());
/*     */   }
/*     */   
/*     */   public boolean contains(Object element) {
/* 166 */     if (this.derivedType.isInstance(element))
/* 167 */       return this.base.contains(derivedToBase(this.derivedType.cast(element))); 
/* 169 */     return false;
/*     */   }
/*     */   
/*     */   public boolean add(E element) throws UnsupportedOperationException {
/* 185 */     return this.base.add(derivedToBase(element));
/*     */   }
/*     */   
/*     */   public boolean remove(Object element) throws UnsupportedOperationException {
/* 200 */     if (this.derivedType.isInstance(element))
/* 201 */       return this.base.remove(derivedToBase(this.derivedType.cast(element))); 
/* 203 */     return false;
/*     */   }
/*     */   
/*     */   private final class Iter implements Iterator<E> {
/*     */     private final Iterator<B> iterator;
/*     */     
/*     */     private transient E next;
/*     */     
/*     */     public Iter(Iterator<B> iterator) {
/* 225 */       this.iterator = iterator;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 232 */       while (this.next == null) {
/* 233 */         if (!this.iterator.hasNext())
/* 234 */           return false; 
/* 236 */         this.next = (E)DerivedSet.this.baseToDerived(this.iterator.next());
/*     */       } 
/* 238 */       return true;
/*     */     }
/*     */     
/*     */     public E next() {
/* 245 */       while (this.next == null)
/* 246 */         this.next = (E)DerivedSet.this.baseToDerived(this.iterator.next()); 
/* 248 */       E value = this.next;
/* 249 */       this.next = null;
/* 250 */       return value;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 260 */       this.iterator.remove();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\DerivedSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */