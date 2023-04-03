/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.collections.MapIterator;
/*     */ import org.apache.commons.collections.OrderedIterator;
/*     */ import org.apache.commons.collections.OrderedMap;
/*     */ import org.apache.commons.collections.OrderedMapIterator;
/*     */ import org.apache.commons.collections.ResettableIterator;
/*     */ import org.apache.commons.collections.iterators.EmptyOrderedIterator;
/*     */ import org.apache.commons.collections.iterators.EmptyOrderedMapIterator;
/*     */ 
/*     */ public class AbstractLinkedMap extends AbstractHashedMap implements OrderedMap {
/*     */   protected transient LinkEntry header;
/*     */   
/*     */   protected AbstractLinkedMap() {}
/*     */   
/*     */   protected AbstractLinkedMap(int initialCapacity, float loadFactor, int threshold) {
/*  86 */     super(initialCapacity, loadFactor, threshold);
/*     */   }
/*     */   
/*     */   protected AbstractLinkedMap(int initialCapacity) {
/*  96 */     super(initialCapacity);
/*     */   }
/*     */   
/*     */   protected AbstractLinkedMap(int initialCapacity, float loadFactor) {
/* 109 */     super(initialCapacity, loadFactor);
/*     */   }
/*     */   
/*     */   protected AbstractLinkedMap(Map map) {
/* 119 */     super(map);
/*     */   }
/*     */   
/*     */   protected void init() {
/* 130 */     this.header = (LinkEntry)createEntry((AbstractHashedMap.HashEntry)null, -1, (Object)null, (Object)null);
/* 131 */     this.header.before = this.header.after = this.header;
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object value) {
/* 143 */     if (value == null) {
/* 144 */       for (LinkEntry entry = this.header.after; entry != this.header; entry = entry.after) {
/* 145 */         if (entry.getValue() == null)
/* 146 */           return true; 
/*     */       } 
/*     */     } else {
/* 150 */       for (LinkEntry entry = this.header.after; entry != this.header; entry = entry.after) {
/* 151 */         if (isEqualValue(value, entry.getValue()))
/* 152 */           return true; 
/*     */       } 
/*     */     } 
/* 156 */     return false;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 165 */     super.clear();
/* 166 */     this.header.before = this.header.after = this.header;
/*     */   }
/*     */   
/*     */   public Object firstKey() {
/* 176 */     if (this.size == 0)
/* 177 */       throw new NoSuchElementException("Map is empty"); 
/* 179 */     return this.header.after.getKey();
/*     */   }
/*     */   
/*     */   public Object lastKey() {
/* 188 */     if (this.size == 0)
/* 189 */       throw new NoSuchElementException("Map is empty"); 
/* 191 */     return this.header.before.getKey();
/*     */   }
/*     */   
/*     */   public Object nextKey(Object key) {
/* 201 */     LinkEntry entry = (LinkEntry)getEntry(key);
/* 202 */     return (entry == null || entry.after == this.header) ? null : entry.after.getKey();
/*     */   }
/*     */   
/*     */   public Object previousKey(Object key) {
/* 212 */     LinkEntry entry = (LinkEntry)getEntry(key);
/* 213 */     return (entry == null || entry.before == this.header) ? null : entry.before.getKey();
/*     */   }
/*     */   
/*     */   protected LinkEntry getEntry(int index) {
/*     */     LinkEntry entry;
/* 225 */     if (index < 0)
/* 226 */       throw new IndexOutOfBoundsException("Index " + index + " is less than zero"); 
/* 228 */     if (index >= this.size)
/* 229 */       throw new IndexOutOfBoundsException("Index " + index + " is invalid for size " + this.size); 
/* 232 */     if (index < this.size / 2) {
/* 234 */       entry = this.header.after;
/* 235 */       for (int currentIndex = 0; currentIndex < index; currentIndex++)
/* 236 */         entry = entry.after; 
/*     */     } else {
/* 240 */       entry = this.header;
/* 241 */       for (int currentIndex = this.size; currentIndex > index; currentIndex--)
/* 242 */         entry = entry.before; 
/*     */     } 
/* 245 */     return entry;
/*     */   }
/*     */   
/*     */   protected void addEntry(AbstractHashedMap.HashEntry entry, int hashIndex) {
/* 258 */     LinkEntry link = (LinkEntry)entry;
/* 259 */     link.after = this.header;
/* 260 */     link.before = this.header.before;
/* 261 */     this.header.before.after = link;
/* 262 */     this.header.before = link;
/* 263 */     this.data[hashIndex] = entry;
/*     */   }
/*     */   
/*     */   protected AbstractHashedMap.HashEntry createEntry(AbstractHashedMap.HashEntry next, int hashCode, Object key, Object value) {
/* 278 */     return new LinkEntry(next, hashCode, key, value);
/*     */   }
/*     */   
/*     */   protected void removeEntry(AbstractHashedMap.HashEntry entry, int hashIndex, AbstractHashedMap.HashEntry previous) {
/* 292 */     LinkEntry link = (LinkEntry)entry;
/* 293 */     link.before.after = link.after;
/* 294 */     link.after.before = link.before;
/* 295 */     link.after = null;
/* 296 */     link.before = null;
/* 297 */     super.removeEntry(entry, hashIndex, previous);
/*     */   }
/*     */   
/*     */   protected LinkEntry entryBefore(LinkEntry entry) {
/* 311 */     return entry.before;
/*     */   }
/*     */   
/*     */   protected LinkEntry entryAfter(LinkEntry entry) {
/* 324 */     return entry.after;
/*     */   }
/*     */   
/*     */   public MapIterator mapIterator() {
/* 339 */     if (this.size == 0)
/* 340 */       return (MapIterator)EmptyOrderedMapIterator.INSTANCE; 
/* 342 */     return (MapIterator)new LinkMapIterator(this);
/*     */   }
/*     */   
/*     */   public OrderedMapIterator orderedMapIterator() {
/* 356 */     if (this.size == 0)
/* 357 */       return EmptyOrderedMapIterator.INSTANCE; 
/* 359 */     return new LinkMapIterator(this);
/*     */   }
/*     */   
/*     */   protected static class LinkMapIterator extends LinkIterator implements OrderedMapIterator {
/*     */     protected LinkMapIterator(AbstractLinkedMap parent) {
/* 368 */       super(parent);
/*     */     }
/*     */     
/*     */     public Object next() {
/* 372 */       return nextEntry().getKey();
/*     */     }
/*     */     
/*     */     public Object previous() {
/* 376 */       return previousEntry().getKey();
/*     */     }
/*     */     
/*     */     public Object getKey() {
/* 380 */       AbstractHashedMap.HashEntry current = currentEntry();
/* 381 */       if (current == null)
/* 382 */         throw new IllegalStateException("getKey() can only be called after next() and before remove()"); 
/* 384 */       return current.getKey();
/*     */     }
/*     */     
/*     */     public Object getValue() {
/* 388 */       AbstractHashedMap.HashEntry current = currentEntry();
/* 389 */       if (current == null)
/* 390 */         throw new IllegalStateException("getValue() can only be called after next() and before remove()"); 
/* 392 */       return current.getValue();
/*     */     }
/*     */     
/*     */     public Object setValue(Object value) {
/* 396 */       AbstractHashedMap.HashEntry current = currentEntry();
/* 397 */       if (current == null)
/* 398 */         throw new IllegalStateException("setValue() can only be called after next() and before remove()"); 
/* 400 */       return current.setValue(value);
/*     */     }
/*     */   }
/*     */   
/*     */   protected Iterator createEntrySetIterator() {
/* 412 */     if (size() == 0)
/* 413 */       return (Iterator)EmptyOrderedIterator.INSTANCE; 
/* 415 */     return (Iterator)new EntrySetIterator(this);
/*     */   }
/*     */   
/*     */   protected static class EntrySetIterator extends LinkIterator {
/*     */     protected EntrySetIterator(AbstractLinkedMap parent) {
/* 424 */       super(parent);
/*     */     }
/*     */     
/*     */     public Object next() {
/* 428 */       return nextEntry();
/*     */     }
/*     */     
/*     */     public Object previous() {
/* 432 */       return previousEntry();
/*     */     }
/*     */   }
/*     */   
/*     */   protected Iterator createKeySetIterator() {
/* 444 */     if (size() == 0)
/* 445 */       return (Iterator)EmptyOrderedIterator.INSTANCE; 
/* 447 */     return (Iterator)new KeySetIterator(this);
/*     */   }
/*     */   
/*     */   protected static class KeySetIterator extends EntrySetIterator {
/*     */     protected KeySetIterator(AbstractLinkedMap parent) {
/* 456 */       super(parent);
/*     */     }
/*     */     
/*     */     public Object next() {
/* 460 */       return nextEntry().getKey();
/*     */     }
/*     */     
/*     */     public Object previous() {
/* 464 */       return previousEntry().getKey();
/*     */     }
/*     */   }
/*     */   
/*     */   protected Iterator createValuesIterator() {
/* 476 */     if (size() == 0)
/* 477 */       return (Iterator)EmptyOrderedIterator.INSTANCE; 
/* 479 */     return (Iterator)new ValuesIterator(this);
/*     */   }
/*     */   
/*     */   protected static class ValuesIterator extends LinkIterator {
/*     */     protected ValuesIterator(AbstractLinkedMap parent) {
/* 488 */       super(parent);
/*     */     }
/*     */     
/*     */     public Object next() {
/* 492 */       return nextEntry().getValue();
/*     */     }
/*     */     
/*     */     public Object previous() {
/* 496 */       return previousEntry().getValue();
/*     */     }
/*     */   }
/*     */   
/*     */   protected static class LinkEntry extends AbstractHashedMap.HashEntry {
/*     */     protected LinkEntry before;
/*     */     
/*     */     protected LinkEntry after;
/*     */     
/*     */     protected LinkEntry(AbstractHashedMap.HashEntry next, int hashCode, Object key, Object value) {
/* 524 */       super(next, hashCode, key, value);
/*     */     }
/*     */   }
/*     */   
/*     */   protected static abstract class LinkIterator implements OrderedIterator, ResettableIterator {
/*     */     protected final AbstractLinkedMap parent;
/*     */     
/*     */     protected AbstractLinkedMap.LinkEntry last;
/*     */     
/*     */     protected AbstractLinkedMap.LinkEntry next;
/*     */     
/*     */     protected int expectedModCount;
/*     */     
/*     */     protected LinkIterator(AbstractLinkedMap parent) {
/* 545 */       this.parent = parent;
/* 546 */       this.next = parent.header.after;
/* 547 */       this.expectedModCount = parent.modCount;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 551 */       return (this.next != this.parent.header);
/*     */     }
/*     */     
/*     */     public boolean hasPrevious() {
/* 555 */       return (this.next.before != this.parent.header);
/*     */     }
/*     */     
/*     */     protected AbstractLinkedMap.LinkEntry nextEntry() {
/* 559 */       if (this.parent.modCount != this.expectedModCount)
/* 560 */         throw new ConcurrentModificationException(); 
/* 562 */       if (this.next == this.parent.header)
/* 563 */         throw new NoSuchElementException("No next() entry in the iteration"); 
/* 565 */       this.last = this.next;
/* 566 */       this.next = this.next.after;
/* 567 */       return this.last;
/*     */     }
/*     */     
/*     */     protected AbstractLinkedMap.LinkEntry previousEntry() {
/* 571 */       if (this.parent.modCount != this.expectedModCount)
/* 572 */         throw new ConcurrentModificationException(); 
/* 574 */       AbstractLinkedMap.LinkEntry previous = this.next.before;
/* 575 */       if (previous == this.parent.header)
/* 576 */         throw new NoSuchElementException("No previous() entry in the iteration"); 
/* 578 */       this.next = previous;
/* 579 */       this.last = previous;
/* 580 */       return this.last;
/*     */     }
/*     */     
/*     */     protected AbstractLinkedMap.LinkEntry currentEntry() {
/* 584 */       return this.last;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 588 */       if (this.last == null)
/* 589 */         throw new IllegalStateException("remove() can only be called once after next()"); 
/* 591 */       if (this.parent.modCount != this.expectedModCount)
/* 592 */         throw new ConcurrentModificationException(); 
/* 594 */       this.parent.remove(this.last.getKey());
/* 595 */       this.last = null;
/* 596 */       this.expectedModCount = this.parent.modCount;
/*     */     }
/*     */     
/*     */     public void reset() {
/* 600 */       this.last = null;
/* 601 */       this.next = this.parent.header.after;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 605 */       if (this.last != null)
/* 606 */         return "Iterator[" + this.last.getKey() + "=" + this.last.getValue() + "]"; 
/* 608 */       return "Iterator[]";
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\map\AbstractLinkedMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */