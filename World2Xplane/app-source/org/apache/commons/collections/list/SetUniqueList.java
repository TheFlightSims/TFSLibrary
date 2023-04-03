/*     */ package org.apache.commons.collections.list;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.iterators.AbstractIteratorDecorator;
/*     */ import org.apache.commons.collections.iterators.AbstractListIteratorDecorator;
/*     */ import org.apache.commons.collections.set.UnmodifiableSet;
/*     */ 
/*     */ public class SetUniqueList extends AbstractSerializableListDecorator {
/*     */   private static final long serialVersionUID = 7196982186153478694L;
/*     */   
/*     */   protected final Set set;
/*     */   
/*     */   public static SetUniqueList decorate(List list) {
/*  74 */     if (list == null)
/*  75 */       throw new IllegalArgumentException("List must not be null"); 
/*  77 */     if (list.isEmpty())
/*  78 */       return new SetUniqueList(list, new HashSet()); 
/*  80 */     List temp = new ArrayList(list);
/*  81 */     list.clear();
/*  82 */     SetUniqueList sl = new SetUniqueList(list, new HashSet());
/*  83 */     sl.addAll(temp);
/*  84 */     return sl;
/*     */   }
/*     */   
/*     */   protected SetUniqueList(List list, Set set) {
/*  99 */     super(list);
/* 100 */     if (set == null)
/* 101 */       throw new IllegalArgumentException("Set must not be null"); 
/* 103 */     this.set = set;
/*     */   }
/*     */   
/*     */   public Set asSet() {
/* 113 */     return UnmodifiableSet.decorate(this.set);
/*     */   }
/*     */   
/*     */   public boolean add(Object object) {
/* 130 */     int sizeBefore = size();
/* 133 */     add(size(), object);
/* 136 */     return (sizeBefore != size());
/*     */   }
/*     */   
/*     */   public void add(int index, Object object) {
/* 151 */     if (!this.set.contains(object)) {
/* 152 */       super.add(index, object);
/* 153 */       this.set.add(object);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection coll) {
/* 167 */     return addAll(size(), coll);
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, Collection coll) {
/* 186 */     int sizeBefore = size();
/* 189 */     for (Iterator it = coll.iterator(); it.hasNext();)
/* 190 */       add(it.next()); 
/* 194 */     return (sizeBefore != size());
/*     */   }
/*     */   
/*     */   public Object set(int index, Object object) {
/* 211 */     int pos = indexOf(object);
/* 212 */     Object removed = super.set(index, object);
/* 213 */     if (pos == -1 || pos == index)
/* 214 */       return removed; 
/* 219 */     super.remove(pos);
/* 220 */     this.set.remove(removed);
/* 221 */     return removed;
/*     */   }
/*     */   
/*     */   public boolean remove(Object object) {
/* 225 */     boolean result = super.remove(object);
/* 226 */     this.set.remove(object);
/* 227 */     return result;
/*     */   }
/*     */   
/*     */   public Object remove(int index) {
/* 231 */     Object result = super.remove(index);
/* 232 */     this.set.remove(result);
/* 233 */     return result;
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection coll) {
/* 237 */     boolean result = super.removeAll(coll);
/* 238 */     this.set.removeAll(coll);
/* 239 */     return result;
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection coll) {
/* 243 */     boolean result = super.retainAll(coll);
/* 244 */     this.set.retainAll(coll);
/* 245 */     return result;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 249 */     super.clear();
/* 250 */     this.set.clear();
/*     */   }
/*     */   
/*     */   public boolean contains(Object object) {
/* 254 */     return this.set.contains(object);
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection coll) {
/* 258 */     return this.set.containsAll(coll);
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/* 262 */     return (Iterator)new SetListIterator(super.iterator(), this.set);
/*     */   }
/*     */   
/*     */   public ListIterator listIterator() {
/* 266 */     return (ListIterator)new SetListListIterator(super.listIterator(), this.set);
/*     */   }
/*     */   
/*     */   public ListIterator listIterator(int index) {
/* 270 */     return (ListIterator)new SetListListIterator(super.listIterator(index), this.set);
/*     */   }
/*     */   
/*     */   public List subList(int fromIndex, int toIndex) {
/* 274 */     return new SetUniqueList(super.subList(fromIndex, toIndex), this.set);
/*     */   }
/*     */   
/*     */   static class SetListIterator extends AbstractIteratorDecorator {
/*     */     protected final Set set;
/*     */     
/* 284 */     protected Object last = null;
/*     */     
/*     */     protected SetListIterator(Iterator it, Set set) {
/* 287 */       super(it);
/* 288 */       this.set = set;
/*     */     }
/*     */     
/*     */     public Object next() {
/* 292 */       this.last = super.next();
/* 293 */       return this.last;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 297 */       super.remove();
/* 298 */       this.set.remove(this.last);
/* 299 */       this.last = null;
/*     */     }
/*     */   }
/*     */   
/*     */   static class SetListListIterator extends AbstractListIteratorDecorator {
/*     */     protected final Set set;
/*     */     
/* 309 */     protected Object last = null;
/*     */     
/*     */     protected SetListListIterator(ListIterator it, Set set) {
/* 312 */       super(it);
/* 313 */       this.set = set;
/*     */     }
/*     */     
/*     */     public Object next() {
/* 317 */       this.last = super.next();
/* 318 */       return this.last;
/*     */     }
/*     */     
/*     */     public Object previous() {
/* 322 */       this.last = super.previous();
/* 323 */       return this.last;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 327 */       super.remove();
/* 328 */       this.set.remove(this.last);
/* 329 */       this.last = null;
/*     */     }
/*     */     
/*     */     public void add(Object object) {
/* 333 */       if (!this.set.contains(object)) {
/* 334 */         super.add(object);
/* 335 */         this.set.add(object);
/*     */       } 
/*     */     }
/*     */     
/*     */     public void set(Object object) {
/* 340 */       throw new UnsupportedOperationException("ListIterator does not support set");
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\list\SetUniqueList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */