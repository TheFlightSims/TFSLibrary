/*     */ package org.apache.commons.collections.set;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.iterators.AbstractIteratorDecorator;
/*     */ import org.apache.commons.collections.list.UnmodifiableList;
/*     */ 
/*     */ public class ListOrderedSet extends AbstractSerializableSetDecorator implements Set {
/*     */   private static final long serialVersionUID = -228664372470420141L;
/*     */   
/*     */   protected final List setOrder;
/*     */   
/*     */   public static ListOrderedSet decorate(Set set, List list) {
/*  73 */     if (set == null)
/*  74 */       throw new IllegalArgumentException("Set must not be null"); 
/*  76 */     if (list == null)
/*  77 */       throw new IllegalArgumentException("List must not be null"); 
/*  79 */     if (set.size() > 0 || list.size() > 0)
/*  80 */       throw new IllegalArgumentException("Set and List must be empty"); 
/*  82 */     return new ListOrderedSet(set, list);
/*     */   }
/*     */   
/*     */   public static ListOrderedSet decorate(Set set) {
/*  94 */     return new ListOrderedSet(set);
/*     */   }
/*     */   
/*     */   public static ListOrderedSet decorate(List list) {
/* 109 */     if (list == null)
/* 110 */       throw new IllegalArgumentException("List must not be null"); 
/* 112 */     Set set = new HashSet(list);
/* 113 */     list.retainAll(set);
/* 115 */     return new ListOrderedSet(set, list);
/*     */   }
/*     */   
/*     */   public ListOrderedSet() {
/* 126 */     super(new HashSet());
/* 127 */     this.setOrder = new ArrayList();
/*     */   }
/*     */   
/*     */   protected ListOrderedSet(Set set) {
/* 137 */     super(set);
/* 138 */     this.setOrder = new ArrayList(set);
/*     */   }
/*     */   
/*     */   protected ListOrderedSet(Set set, List list) {
/* 151 */     super(set);
/* 152 */     if (list == null)
/* 153 */       throw new IllegalArgumentException("List must not be null"); 
/* 155 */     this.setOrder = list;
/*     */   }
/*     */   
/*     */   public List asList() {
/* 165 */     return UnmodifiableList.decorate(this.setOrder);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 170 */     this.collection.clear();
/* 171 */     this.setOrder.clear();
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/* 175 */     return (Iterator)new OrderedSetIterator(this.setOrder.iterator(), this.collection);
/*     */   }
/*     */   
/*     */   public boolean add(Object object) {
/* 179 */     if (this.collection.contains(object))
/* 181 */       return this.collection.add(object); 
/* 184 */     boolean result = this.collection.add(object);
/* 185 */     this.setOrder.add(object);
/* 186 */     return result;
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection coll) {
/* 191 */     boolean result = false;
/* 192 */     for (Iterator it = coll.iterator(); it.hasNext(); ) {
/* 193 */       Object object = it.next();
/* 194 */       result |= add(object);
/*     */     } 
/* 196 */     return result;
/*     */   }
/*     */   
/*     */   public boolean remove(Object object) {
/* 200 */     boolean result = this.collection.remove(object);
/* 201 */     this.setOrder.remove(object);
/* 202 */     return result;
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection coll) {
/* 206 */     boolean result = false;
/* 207 */     for (Iterator it = coll.iterator(); it.hasNext(); ) {
/* 208 */       Object object = it.next();
/* 209 */       result |= remove(object);
/*     */     } 
/* 211 */     return result;
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection coll) {
/* 215 */     boolean result = this.collection.retainAll(coll);
/* 216 */     if (!result)
/* 217 */       return false; 
/* 218 */     if (this.collection.size() == 0) {
/* 219 */       this.setOrder.clear();
/*     */     } else {
/* 221 */       for (Iterator it = this.setOrder.iterator(); it.hasNext(); ) {
/* 222 */         Object object = it.next();
/* 223 */         if (!this.collection.contains(object))
/* 224 */           it.remove(); 
/*     */       } 
/*     */     } 
/* 228 */     return result;
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 232 */     return this.setOrder.toArray();
/*     */   }
/*     */   
/*     */   public Object[] toArray(Object[] a) {
/* 236 */     return this.setOrder.toArray(a);
/*     */   }
/*     */   
/*     */   public Object get(int index) {
/* 241 */     return this.setOrder.get(index);
/*     */   }
/*     */   
/*     */   public int indexOf(Object object) {
/* 245 */     return this.setOrder.indexOf(object);
/*     */   }
/*     */   
/*     */   public void add(int index, Object object) {
/* 249 */     if (!contains(object)) {
/* 250 */       this.collection.add(object);
/* 251 */       this.setOrder.add(index, object);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, Collection coll) {
/* 256 */     boolean changed = false;
/* 257 */     for (Iterator it = coll.iterator(); it.hasNext(); ) {
/* 258 */       Object object = it.next();
/* 259 */       if (!contains(object)) {
/* 260 */         this.collection.add(object);
/* 261 */         this.setOrder.add(index, object);
/* 262 */         index++;
/* 263 */         changed = true;
/*     */       } 
/*     */     } 
/* 266 */     return changed;
/*     */   }
/*     */   
/*     */   public Object remove(int index) {
/* 270 */     Object obj = this.setOrder.remove(index);
/* 271 */     remove(obj);
/* 272 */     return obj;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 282 */     return this.setOrder.toString();
/*     */   }
/*     */   
/*     */   static class OrderedSetIterator extends AbstractIteratorDecorator {
/*     */     protected final Collection set;
/*     */     
/*     */     protected Object last;
/*     */     
/*     */     private OrderedSetIterator(Iterator iterator, Collection set) {
/* 297 */       super(iterator);
/* 298 */       this.set = set;
/*     */     }
/*     */     
/*     */     public Object next() {
/* 302 */       this.last = this.iterator.next();
/* 303 */       return this.last;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 307 */       this.set.remove(this.last);
/* 308 */       this.iterator.remove();
/* 309 */       this.last = null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\set\ListOrderedSet.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */