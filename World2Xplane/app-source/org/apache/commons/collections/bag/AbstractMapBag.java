/*     */ package org.apache.commons.collections.bag;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.Collection;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.Bag;
/*     */ import org.apache.commons.collections.set.UnmodifiableSet;
/*     */ 
/*     */ public abstract class AbstractMapBag implements Bag {
/*     */   private transient Map map;
/*     */   
/*     */   private int size;
/*     */   
/*     */   private transient int modCount;
/*     */   
/*     */   private transient Set uniqueSet;
/*     */   
/*     */   protected AbstractMapBag() {}
/*     */   
/*     */   protected AbstractMapBag(Map map) {
/*  76 */     this.map = map;
/*     */   }
/*     */   
/*     */   protected Map getMap() {
/*  86 */     return this.map;
/*     */   }
/*     */   
/*     */   public int size() {
/*  96 */     return this.size;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 105 */     return this.map.isEmpty();
/*     */   }
/*     */   
/*     */   public int getCount(Object object) {
/* 116 */     MutableInteger count = (MutableInteger)this.map.get(object);
/* 117 */     if (count != null)
/* 118 */       return count.value; 
/* 120 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean contains(Object object) {
/* 132 */     return this.map.containsKey(object);
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection coll) {
/* 142 */     if (coll instanceof Bag)
/* 143 */       return containsAll((Bag)coll); 
/* 145 */     return containsAll(new HashBag(coll));
/*     */   }
/*     */   
/*     */   boolean containsAll(Bag other) {
/* 156 */     boolean result = true;
/* 157 */     Iterator it = other.uniqueSet().iterator();
/* 158 */     while (it.hasNext()) {
/* 159 */       Object current = it.next();
/* 160 */       boolean contains = (getCount(current) >= other.getCount(current));
/* 161 */       result = (result && contains);
/*     */     } 
/* 163 */     return result;
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/* 174 */     return new BagIterator(this);
/*     */   }
/*     */   
/*     */   static class BagIterator implements Iterator {
/*     */     private AbstractMapBag parent;
/*     */     
/*     */     private Iterator entryIterator;
/*     */     
/*     */     private Map.Entry current;
/*     */     
/*     */     private int itemCount;
/*     */     
/*     */     private final int mods;
/*     */     
/*     */     private boolean canRemove;
/*     */     
/*     */     public BagIterator(AbstractMapBag parent) {
/* 194 */       this.parent = parent;
/* 195 */       this.entryIterator = parent.map.entrySet().iterator();
/* 196 */       this.current = null;
/* 197 */       this.mods = parent.modCount;
/* 198 */       this.canRemove = false;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 202 */       return (this.itemCount > 0 || this.entryIterator.hasNext());
/*     */     }
/*     */     
/*     */     public Object next() {
/* 206 */       if (this.parent.modCount != this.mods)
/* 207 */         throw new ConcurrentModificationException(); 
/* 209 */       if (this.itemCount == 0) {
/* 210 */         this.current = this.entryIterator.next();
/* 211 */         this.itemCount = ((AbstractMapBag.MutableInteger)this.current.getValue()).value;
/*     */       } 
/* 213 */       this.canRemove = true;
/* 214 */       this.itemCount--;
/* 215 */       return this.current.getKey();
/*     */     }
/*     */     
/*     */     public void remove() {
/* 219 */       if (this.parent.modCount != this.mods)
/* 220 */         throw new ConcurrentModificationException(); 
/* 222 */       if (!this.canRemove)
/* 223 */         throw new IllegalStateException(); 
/* 225 */       AbstractMapBag.MutableInteger mut = (AbstractMapBag.MutableInteger)this.current.getValue();
/* 226 */       if (mut.value > 1) {
/* 227 */         mut.value--;
/*     */       } else {
/* 229 */         this.entryIterator.remove();
/*     */       } 
/* 231 */       this.parent.size--;
/* 232 */       this.canRemove = false;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean add(Object object) {
/* 244 */     return add(object, 1);
/*     */   }
/*     */   
/*     */   public boolean add(Object object, int nCopies) {
/* 255 */     this.modCount++;
/* 256 */     if (nCopies > 0) {
/* 257 */       MutableInteger mut = (MutableInteger)this.map.get(object);
/* 258 */       this.size += nCopies;
/* 259 */       if (mut == null) {
/* 260 */         this.map.put(object, new MutableInteger(nCopies));
/* 261 */         return true;
/*     */       } 
/* 263 */       mut.value += nCopies;
/* 264 */       return false;
/*     */     } 
/* 267 */     return false;
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection coll) {
/* 278 */     boolean changed = false;
/* 279 */     Iterator i = coll.iterator();
/* 280 */     while (i.hasNext()) {
/* 281 */       boolean added = add(i.next());
/* 282 */       changed = (changed || added);
/*     */     } 
/* 284 */     return changed;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 292 */     this.modCount++;
/* 293 */     this.map.clear();
/* 294 */     this.size = 0;
/*     */   }
/*     */   
/*     */   public boolean remove(Object object) {
/* 304 */     MutableInteger mut = (MutableInteger)this.map.get(object);
/* 305 */     if (mut == null)
/* 306 */       return false; 
/* 308 */     this.modCount++;
/* 309 */     this.map.remove(object);
/* 310 */     this.size -= mut.value;
/* 311 */     return true;
/*     */   }
/*     */   
/*     */   public boolean remove(Object object, int nCopies) {
/* 322 */     MutableInteger mut = (MutableInteger)this.map.get(object);
/* 323 */     if (mut == null)
/* 324 */       return false; 
/* 326 */     if (nCopies <= 0)
/* 327 */       return false; 
/* 329 */     this.modCount++;
/* 330 */     if (nCopies < mut.value) {
/* 331 */       mut.value -= nCopies;
/* 332 */       this.size -= nCopies;
/*     */     } else {
/* 334 */       this.map.remove(object);
/* 335 */       this.size -= mut.value;
/*     */     } 
/* 337 */     return true;
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection coll) {
/* 347 */     boolean result = false;
/* 348 */     if (coll != null) {
/* 349 */       Iterator i = coll.iterator();
/* 350 */       while (i.hasNext()) {
/* 351 */         boolean changed = remove(i.next(), 1);
/* 352 */         result = (result || changed);
/*     */       } 
/*     */     } 
/* 355 */     return result;
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection coll) {
/* 366 */     if (coll instanceof Bag)
/* 367 */       return retainAll((Bag)coll); 
/* 369 */     return retainAll(new HashBag(coll));
/*     */   }
/*     */   
/*     */   boolean retainAll(Bag other) {
/* 381 */     boolean result = false;
/* 382 */     Bag excess = new HashBag();
/* 383 */     Iterator i = uniqueSet().iterator();
/* 384 */     while (i.hasNext()) {
/* 385 */       Object current = i.next();
/* 386 */       int myCount = getCount(current);
/* 387 */       int otherCount = other.getCount(current);
/* 388 */       if (1 <= otherCount && otherCount <= myCount) {
/* 389 */         excess.add(current, myCount - otherCount);
/*     */         continue;
/*     */       } 
/* 391 */       excess.add(current, myCount);
/*     */     } 
/* 394 */     if (!excess.isEmpty())
/* 395 */       result = removeAll((Collection)excess); 
/* 397 */     return result;
/*     */   }
/*     */   
/*     */   protected static class MutableInteger {
/*     */     protected int value;
/*     */     
/*     */     MutableInteger(int value) {
/* 413 */       this.value = value;
/*     */     }
/*     */     
/*     */     public boolean equals(Object obj) {
/* 417 */       if (!(obj instanceof MutableInteger))
/* 418 */         return false; 
/* 420 */       return (((MutableInteger)obj).value == this.value);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 424 */       return this.value;
/*     */     }
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 435 */     Object[] result = new Object[size()];
/* 436 */     int i = 0;
/* 437 */     Iterator it = this.map.keySet().iterator();
/* 438 */     while (it.hasNext()) {
/* 439 */       Object current = it.next();
/* 440 */       for (int index = getCount(current); index > 0; index--)
/* 441 */         result[i++] = current; 
/*     */     } 
/* 444 */     return result;
/*     */   }
/*     */   
/*     */   public Object[] toArray(Object[] array) {
/* 454 */     int size = size();
/* 455 */     if (array.length < size)
/* 456 */       array = (Object[])Array.newInstance(array.getClass().getComponentType(), size); 
/* 459 */     int i = 0;
/* 460 */     Iterator it = this.map.keySet().iterator();
/* 461 */     while (it.hasNext()) {
/* 462 */       Object current = it.next();
/* 463 */       for (int index = getCount(current); index > 0; index--)
/* 464 */         array[i++] = current; 
/*     */     } 
/* 467 */     if (array.length > size)
/* 468 */       array[size] = null; 
/* 470 */     return array;
/*     */   }
/*     */   
/*     */   public Set uniqueSet() {
/* 479 */     if (this.uniqueSet == null)
/* 480 */       this.uniqueSet = UnmodifiableSet.decorate(this.map.keySet()); 
/* 482 */     return this.uniqueSet;
/*     */   }
/*     */   
/*     */   protected void doWriteObject(ObjectOutputStream out) throws IOException {
/* 492 */     out.writeInt(this.map.size());
/* 493 */     for (Iterator it = this.map.entrySet().iterator(); it.hasNext(); ) {
/* 494 */       Map.Entry entry = it.next();
/* 495 */       out.writeObject(entry.getKey());
/* 496 */       out.writeInt(((MutableInteger)entry.getValue()).value);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void doReadObject(Map map, ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 508 */     this.map = map;
/* 509 */     int entrySize = in.readInt();
/* 510 */     for (int i = 0; i < entrySize; i++) {
/* 511 */       Object obj = in.readObject();
/* 512 */       int count = in.readInt();
/* 513 */       map.put(obj, new MutableInteger(count));
/* 514 */       this.size += count;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 528 */     if (object == this)
/* 529 */       return true; 
/* 531 */     if (!(object instanceof Bag))
/* 532 */       return false; 
/* 534 */     Bag other = (Bag)object;
/* 535 */     if (other.size() != size())
/* 536 */       return false; 
/* 538 */     for (Iterator it = this.map.keySet().iterator(); it.hasNext(); ) {
/* 539 */       Object element = it.next();
/* 540 */       if (other.getCount(element) != getCount(element))
/* 541 */         return false; 
/*     */     } 
/* 544 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 557 */     int total = 0;
/* 558 */     for (Iterator it = this.map.entrySet().iterator(); it.hasNext(); ) {
/* 559 */       Map.Entry entry = it.next();
/* 560 */       Object element = entry.getKey();
/* 561 */       MutableInteger count = (MutableInteger)entry.getValue();
/* 562 */       total += ((element == null) ? 0 : element.hashCode()) ^ count.value;
/*     */     } 
/* 564 */     return total;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 573 */     if (size() == 0)
/* 574 */       return "[]"; 
/* 576 */     StringBuffer buf = new StringBuffer();
/* 577 */     buf.append('[');
/* 578 */     Iterator it = uniqueSet().iterator();
/* 579 */     while (it.hasNext()) {
/* 580 */       Object current = it.next();
/* 581 */       int count = getCount(current);
/* 582 */       buf.append(count);
/* 583 */       buf.append(':');
/* 584 */       buf.append(current);
/* 585 */       if (it.hasNext())
/* 586 */         buf.append(','); 
/*     */     } 
/* 589 */     buf.append(']');
/* 590 */     return buf.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\bag\AbstractMapBag.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */