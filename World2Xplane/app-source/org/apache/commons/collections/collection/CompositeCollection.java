/*     */ package org.apache.commons.collections.collection;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.collections.iterators.EmptyIterator;
/*     */ import org.apache.commons.collections.iterators.IteratorChain;
/*     */ import org.apache.commons.collections.list.UnmodifiableList;
/*     */ 
/*     */ public class CompositeCollection implements Collection {
/*     */   protected CollectionMutator mutator;
/*     */   
/*  56 */   protected Collection[] all = new Collection[0];
/*     */   
/*     */   public CompositeCollection() {}
/*     */   
/*     */   public CompositeCollection(Collection coll) {
/*  65 */     this();
/*  66 */     addComposited(coll);
/*     */   }
/*     */   
/*     */   public CompositeCollection(Collection[] colls) {
/*  76 */     this();
/*  77 */     addComposited(colls);
/*     */   }
/*     */   
/*     */   public int size() {
/*  89 */     int size = 0;
/*  90 */     for (int i = this.all.length - 1; i >= 0; i--)
/*  91 */       size += this.all[i].size(); 
/*  93 */     return size;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 104 */     for (int i = this.all.length - 1; i >= 0; i--) {
/* 105 */       if (!this.all[i].isEmpty())
/* 106 */         return false; 
/*     */     } 
/* 109 */     return true;
/*     */   }
/*     */   
/*     */   public boolean contains(Object obj) {
/* 121 */     for (int i = this.all.length - 1; i >= 0; i--) {
/* 122 */       if (this.all[i].contains(obj))
/* 123 */         return true; 
/*     */     } 
/* 126 */     return false;
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/* 140 */     if (this.all.length == 0)
/* 141 */       return EmptyIterator.INSTANCE; 
/* 143 */     IteratorChain chain = new IteratorChain();
/* 144 */     for (int i = 0; i < this.all.length; i++)
/* 145 */       chain.addIterator(this.all[i].iterator()); 
/* 147 */     return (Iterator)chain;
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 156 */     Object[] result = new Object[size()];
/* 157 */     int i = 0;
/* 158 */     for (Iterator it = iterator(); it.hasNext(); i++)
/* 159 */       result[i] = it.next(); 
/* 161 */     return result;
/*     */   }
/*     */   
/*     */   public Object[] toArray(Object[] array) {
/* 172 */     int size = size();
/* 173 */     Object[] result = null;
/* 174 */     if (array.length >= size) {
/* 175 */       result = array;
/*     */     } else {
/* 178 */       result = (Object[])Array.newInstance(array.getClass().getComponentType(), size);
/*     */     } 
/* 181 */     int offset = 0;
/* 182 */     for (int i = 0; i < this.all.length; i++) {
/* 183 */       for (Iterator it = this.all[i].iterator(); it.hasNext();)
/* 184 */         result[offset++] = it.next(); 
/*     */     } 
/* 187 */     if (result.length > size)
/* 188 */       result[size] = null; 
/* 190 */     return result;
/*     */   }
/*     */   
/*     */   public boolean add(Object obj) {
/* 206 */     if (this.mutator == null)
/* 207 */       throw new UnsupportedOperationException("add() is not supported on CompositeCollection without a CollectionMutator strategy"); 
/* 210 */     return this.mutator.add(this, this.all, obj);
/*     */   }
/*     */   
/*     */   public boolean remove(Object obj) {
/* 225 */     if (this.mutator == null)
/* 226 */       throw new UnsupportedOperationException("remove() is not supported on CompositeCollection without a CollectionMutator strategy"); 
/* 229 */     return this.mutator.remove(this, this.all, obj);
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection coll) {
/* 242 */     for (Iterator it = coll.iterator(); it.hasNext();) {
/* 243 */       if (!contains(it.next()))
/* 244 */         return false; 
/*     */     } 
/* 247 */     return true;
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection coll) {
/* 263 */     if (this.mutator == null)
/* 264 */       throw new UnsupportedOperationException("addAll() is not supported on CompositeCollection without a CollectionMutator strategy"); 
/* 267 */     return this.mutator.addAll(this, this.all, coll);
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection coll) {
/* 280 */     if (coll.size() == 0)
/* 281 */       return false; 
/* 283 */     boolean changed = false;
/* 284 */     for (int i = this.all.length - 1; i >= 0; i--)
/* 285 */       changed = (this.all[i].removeAll(coll) || changed); 
/* 287 */     return changed;
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection coll) {
/* 301 */     boolean changed = false;
/* 302 */     for (int i = this.all.length - 1; i >= 0; i--)
/* 303 */       changed = (this.all[i].retainAll(coll) || changed); 
/* 305 */     return changed;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 316 */     for (int i = 0; i < this.all.length; i++)
/* 317 */       this.all[i].clear(); 
/*     */   }
/*     */   
/*     */   public void setMutator(CollectionMutator mutator) {
/* 328 */     this.mutator = mutator;
/*     */   }
/*     */   
/*     */   public void addComposited(Collection[] comps) {
/* 337 */     ArrayList list = new ArrayList(Arrays.asList((Object[])this.all));
/* 338 */     list.addAll(Arrays.asList(comps));
/* 339 */     this.all = (Collection[])list.toArray((Object[])new Collection[list.size()]);
/*     */   }
/*     */   
/*     */   public void addComposited(Collection c) {
/* 348 */     addComposited(new Collection[] { c });
/*     */   }
/*     */   
/*     */   public void addComposited(Collection c, Collection d) {
/* 358 */     addComposited(new Collection[] { c, d });
/*     */   }
/*     */   
/*     */   public void removeComposited(Collection coll) {
/* 367 */     ArrayList list = new ArrayList(this.all.length);
/* 368 */     list.addAll(Arrays.asList(this.all));
/* 369 */     list.remove(coll);
/* 370 */     this.all = (Collection[])list.toArray((Object[])new Collection[list.size()]);
/*     */   }
/*     */   
/*     */   public Collection toCollection() {
/* 380 */     return new ArrayList(this);
/*     */   }
/*     */   
/*     */   public Collection getCollections() {
/* 389 */     return UnmodifiableList.decorate(Arrays.asList(this.all));
/*     */   }
/*     */   
/*     */   public static interface CollectionMutator {
/*     */     boolean add(CompositeCollection param1CompositeCollection, Collection[] param1ArrayOfCollection, Object param1Object);
/*     */     
/*     */     boolean addAll(CompositeCollection param1CompositeCollection, Collection[] param1ArrayOfCollection, Collection param1Collection);
/*     */     
/*     */     boolean remove(CompositeCollection param1CompositeCollection, Collection[] param1ArrayOfCollection, Object param1Object);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\collection\CompositeCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */