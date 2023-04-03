/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.IterableMap;
/*     */ import org.apache.commons.collections.MapIterator;
/*     */ import org.apache.commons.collections.keyvalue.MultiKey;
/*     */ 
/*     */ public class MultiKeyMap implements IterableMap, Serializable {
/*     */   private static final long serialVersionUID = -1788199231038721040L;
/*     */   
/*     */   protected final AbstractHashedMap map;
/*     */   
/*     */   public static MultiKeyMap decorate(AbstractHashedMap map) {
/*  98 */     if (map == null)
/*  99 */       throw new IllegalArgumentException("Map must not be null"); 
/* 101 */     if (map.size() > 0)
/* 102 */       throw new IllegalArgumentException("Map must be empty"); 
/* 104 */     return new MultiKeyMap(map);
/*     */   }
/*     */   
/*     */   public MultiKeyMap() {
/* 113 */     this.map = new HashedMap();
/*     */   }
/*     */   
/*     */   protected MultiKeyMap(AbstractHashedMap map) {
/* 126 */     this.map = map;
/*     */   }
/*     */   
/*     */   public Object get(Object key1, Object key2) {
/* 138 */     int hashCode = hash(key1, key2);
/* 139 */     AbstractHashedMap.HashEntry entry = this.map.data[this.map.hashIndex(hashCode, this.map.data.length)];
/* 140 */     while (entry != null) {
/* 141 */       if (entry.hashCode == hashCode && isEqualKey(entry, key1, key2))
/* 142 */         return entry.getValue(); 
/* 144 */       entry = entry.next;
/*     */     } 
/* 146 */     return null;
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key1, Object key2) {
/* 157 */     int hashCode = hash(key1, key2);
/* 158 */     AbstractHashedMap.HashEntry entry = this.map.data[this.map.hashIndex(hashCode, this.map.data.length)];
/* 159 */     while (entry != null) {
/* 160 */       if (entry.hashCode == hashCode && isEqualKey(entry, key1, key2))
/* 161 */         return true; 
/* 163 */       entry = entry.next;
/*     */     } 
/* 165 */     return false;
/*     */   }
/*     */   
/*     */   public Object put(Object key1, Object key2, Object value) {
/* 177 */     int hashCode = hash(key1, key2);
/* 178 */     int index = this.map.hashIndex(hashCode, this.map.data.length);
/* 179 */     AbstractHashedMap.HashEntry entry = this.map.data[index];
/* 180 */     while (entry != null) {
/* 181 */       if (entry.hashCode == hashCode && isEqualKey(entry, key1, key2)) {
/* 182 */         Object oldValue = entry.getValue();
/* 183 */         this.map.updateEntry(entry, value);
/* 184 */         return oldValue;
/*     */       } 
/* 186 */       entry = entry.next;
/*     */     } 
/* 189 */     this.map.addMapping(index, hashCode, new MultiKey(key1, key2), value);
/* 190 */     return null;
/*     */   }
/*     */   
/*     */   public Object remove(Object key1, Object key2) {
/* 201 */     int hashCode = hash(key1, key2);
/* 202 */     int index = this.map.hashIndex(hashCode, this.map.data.length);
/* 203 */     AbstractHashedMap.HashEntry entry = this.map.data[index];
/* 204 */     AbstractHashedMap.HashEntry previous = null;
/* 205 */     while (entry != null) {
/* 206 */       if (entry.hashCode == hashCode && isEqualKey(entry, key1, key2)) {
/* 207 */         Object oldValue = entry.getValue();
/* 208 */         this.map.removeMapping(entry, index, previous);
/* 209 */         return oldValue;
/*     */       } 
/* 211 */       previous = entry;
/* 212 */       entry = entry.next;
/*     */     } 
/* 214 */     return null;
/*     */   }
/*     */   
/*     */   protected int hash(Object key1, Object key2) {
/* 225 */     int h = 0;
/* 226 */     if (key1 != null)
/* 227 */       h ^= key1.hashCode(); 
/* 229 */     if (key2 != null)
/* 230 */       h ^= key2.hashCode(); 
/* 232 */     h += h << 9 ^ 0xFFFFFFFF;
/* 233 */     h ^= h >>> 14;
/* 234 */     h += h << 4;
/* 235 */     h ^= h >>> 10;
/* 236 */     return h;
/*     */   }
/*     */   
/*     */   protected boolean isEqualKey(AbstractHashedMap.HashEntry entry, Object key1, Object key2) {
/* 248 */     MultiKey multi = (MultiKey)entry.getKey();
/* 249 */     return (multi.size() == 2 && ((key1 == null) ? (multi.getKey(0) == null) : key1.equals(multi.getKey(0))) && ((key2 == null) ? (multi.getKey(1) == null) : key2.equals(multi.getKey(1))));
/*     */   }
/*     */   
/*     */   public Object get(Object key1, Object key2, Object key3) {
/* 265 */     int hashCode = hash(key1, key2, key3);
/* 266 */     AbstractHashedMap.HashEntry entry = this.map.data[this.map.hashIndex(hashCode, this.map.data.length)];
/* 267 */     while (entry != null) {
/* 268 */       if (entry.hashCode == hashCode && isEqualKey(entry, key1, key2, key3))
/* 269 */         return entry.getValue(); 
/* 271 */       entry = entry.next;
/*     */     } 
/* 273 */     return null;
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key1, Object key2, Object key3) {
/* 285 */     int hashCode = hash(key1, key2, key3);
/* 286 */     AbstractHashedMap.HashEntry entry = this.map.data[this.map.hashIndex(hashCode, this.map.data.length)];
/* 287 */     while (entry != null) {
/* 288 */       if (entry.hashCode == hashCode && isEqualKey(entry, key1, key2, key3))
/* 289 */         return true; 
/* 291 */       entry = entry.next;
/*     */     } 
/* 293 */     return false;
/*     */   }
/*     */   
/*     */   public Object put(Object key1, Object key2, Object key3, Object value) {
/* 306 */     int hashCode = hash(key1, key2, key3);
/* 307 */     int index = this.map.hashIndex(hashCode, this.map.data.length);
/* 308 */     AbstractHashedMap.HashEntry entry = this.map.data[index];
/* 309 */     while (entry != null) {
/* 310 */       if (entry.hashCode == hashCode && isEqualKey(entry, key1, key2, key3)) {
/* 311 */         Object oldValue = entry.getValue();
/* 312 */         this.map.updateEntry(entry, value);
/* 313 */         return oldValue;
/*     */       } 
/* 315 */       entry = entry.next;
/*     */     } 
/* 318 */     this.map.addMapping(index, hashCode, new MultiKey(key1, key2, key3), value);
/* 319 */     return null;
/*     */   }
/*     */   
/*     */   public Object remove(Object key1, Object key2, Object key3) {
/* 331 */     int hashCode = hash(key1, key2, key3);
/* 332 */     int index = this.map.hashIndex(hashCode, this.map.data.length);
/* 333 */     AbstractHashedMap.HashEntry entry = this.map.data[index];
/* 334 */     AbstractHashedMap.HashEntry previous = null;
/* 335 */     while (entry != null) {
/* 336 */       if (entry.hashCode == hashCode && isEqualKey(entry, key1, key2, key3)) {
/* 337 */         Object oldValue = entry.getValue();
/* 338 */         this.map.removeMapping(entry, index, previous);
/* 339 */         return oldValue;
/*     */       } 
/* 341 */       previous = entry;
/* 342 */       entry = entry.next;
/*     */     } 
/* 344 */     return null;
/*     */   }
/*     */   
/*     */   protected int hash(Object key1, Object key2, Object key3) {
/* 356 */     int h = 0;
/* 357 */     if (key1 != null)
/* 358 */       h ^= key1.hashCode(); 
/* 360 */     if (key2 != null)
/* 361 */       h ^= key2.hashCode(); 
/* 363 */     if (key3 != null)
/* 364 */       h ^= key3.hashCode(); 
/* 366 */     h += h << 9 ^ 0xFFFFFFFF;
/* 367 */     h ^= h >>> 14;
/* 368 */     h += h << 4;
/* 369 */     h ^= h >>> 10;
/* 370 */     return h;
/*     */   }
/*     */   
/*     */   protected boolean isEqualKey(AbstractHashedMap.HashEntry entry, Object key1, Object key2, Object key3) {
/* 383 */     MultiKey multi = (MultiKey)entry.getKey();
/* 384 */     return (multi.size() == 3 && ((key1 == null) ? (multi.getKey(0) == null) : key1.equals(multi.getKey(0))) && ((key2 == null) ? (multi.getKey(1) == null) : key2.equals(multi.getKey(1))) && ((key3 == null) ? (multi.getKey(2) == null) : key3.equals(multi.getKey(2))));
/*     */   }
/*     */   
/*     */   public Object get(Object key1, Object key2, Object key3, Object key4) {
/* 402 */     int hashCode = hash(key1, key2, key3, key4);
/* 403 */     AbstractHashedMap.HashEntry entry = this.map.data[this.map.hashIndex(hashCode, this.map.data.length)];
/* 404 */     while (entry != null) {
/* 405 */       if (entry.hashCode == hashCode && isEqualKey(entry, key1, key2, key3, key4))
/* 406 */         return entry.getValue(); 
/* 408 */       entry = entry.next;
/*     */     } 
/* 410 */     return null;
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key1, Object key2, Object key3, Object key4) {
/* 423 */     int hashCode = hash(key1, key2, key3, key4);
/* 424 */     AbstractHashedMap.HashEntry entry = this.map.data[this.map.hashIndex(hashCode, this.map.data.length)];
/* 425 */     while (entry != null) {
/* 426 */       if (entry.hashCode == hashCode && isEqualKey(entry, key1, key2, key3, key4))
/* 427 */         return true; 
/* 429 */       entry = entry.next;
/*     */     } 
/* 431 */     return false;
/*     */   }
/*     */   
/*     */   public Object put(Object key1, Object key2, Object key3, Object key4, Object value) {
/* 445 */     int hashCode = hash(key1, key2, key3, key4);
/* 446 */     int index = this.map.hashIndex(hashCode, this.map.data.length);
/* 447 */     AbstractHashedMap.HashEntry entry = this.map.data[index];
/* 448 */     while (entry != null) {
/* 449 */       if (entry.hashCode == hashCode && isEqualKey(entry, key1, key2, key3, key4)) {
/* 450 */         Object oldValue = entry.getValue();
/* 451 */         this.map.updateEntry(entry, value);
/* 452 */         return oldValue;
/*     */       } 
/* 454 */       entry = entry.next;
/*     */     } 
/* 457 */     this.map.addMapping(index, hashCode, new MultiKey(key1, key2, key3, key4), value);
/* 458 */     return null;
/*     */   }
/*     */   
/*     */   public Object remove(Object key1, Object key2, Object key3, Object key4) {
/* 471 */     int hashCode = hash(key1, key2, key3, key4);
/* 472 */     int index = this.map.hashIndex(hashCode, this.map.data.length);
/* 473 */     AbstractHashedMap.HashEntry entry = this.map.data[index];
/* 474 */     AbstractHashedMap.HashEntry previous = null;
/* 475 */     while (entry != null) {
/* 476 */       if (entry.hashCode == hashCode && isEqualKey(entry, key1, key2, key3, key4)) {
/* 477 */         Object oldValue = entry.getValue();
/* 478 */         this.map.removeMapping(entry, index, previous);
/* 479 */         return oldValue;
/*     */       } 
/* 481 */       previous = entry;
/* 482 */       entry = entry.next;
/*     */     } 
/* 484 */     return null;
/*     */   }
/*     */   
/*     */   protected int hash(Object key1, Object key2, Object key3, Object key4) {
/* 497 */     int h = 0;
/* 498 */     if (key1 != null)
/* 499 */       h ^= key1.hashCode(); 
/* 501 */     if (key2 != null)
/* 502 */       h ^= key2.hashCode(); 
/* 504 */     if (key3 != null)
/* 505 */       h ^= key3.hashCode(); 
/* 507 */     if (key4 != null)
/* 508 */       h ^= key4.hashCode(); 
/* 510 */     h += h << 9 ^ 0xFFFFFFFF;
/* 511 */     h ^= h >>> 14;
/* 512 */     h += h << 4;
/* 513 */     h ^= h >>> 10;
/* 514 */     return h;
/*     */   }
/*     */   
/*     */   protected boolean isEqualKey(AbstractHashedMap.HashEntry entry, Object key1, Object key2, Object key3, Object key4) {
/* 528 */     MultiKey multi = (MultiKey)entry.getKey();
/* 529 */     return (multi.size() == 4 && ((key1 == null) ? (multi.getKey(0) == null) : key1.equals(multi.getKey(0))) && ((key2 == null) ? (multi.getKey(1) == null) : key2.equals(multi.getKey(1))) && ((key3 == null) ? (multi.getKey(2) == null) : key3.equals(multi.getKey(2))) && ((key4 == null) ? (multi.getKey(3) == null) : key4.equals(multi.getKey(3))));
/*     */   }
/*     */   
/*     */   public Object get(Object key1, Object key2, Object key3, Object key4, Object key5) {
/* 549 */     int hashCode = hash(key1, key2, key3, key4, key5);
/* 550 */     AbstractHashedMap.HashEntry entry = this.map.data[this.map.hashIndex(hashCode, this.map.data.length)];
/* 551 */     while (entry != null) {
/* 552 */       if (entry.hashCode == hashCode && isEqualKey(entry, key1, key2, key3, key4, key5))
/* 553 */         return entry.getValue(); 
/* 555 */       entry = entry.next;
/*     */     } 
/* 557 */     return null;
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key1, Object key2, Object key3, Object key4, Object key5) {
/* 571 */     int hashCode = hash(key1, key2, key3, key4, key5);
/* 572 */     AbstractHashedMap.HashEntry entry = this.map.data[this.map.hashIndex(hashCode, this.map.data.length)];
/* 573 */     while (entry != null) {
/* 574 */       if (entry.hashCode == hashCode && isEqualKey(entry, key1, key2, key3, key4, key5))
/* 575 */         return true; 
/* 577 */       entry = entry.next;
/*     */     } 
/* 579 */     return false;
/*     */   }
/*     */   
/*     */   public Object put(Object key1, Object key2, Object key3, Object key4, Object key5, Object value) {
/* 594 */     int hashCode = hash(key1, key2, key3, key4, key5);
/* 595 */     int index = this.map.hashIndex(hashCode, this.map.data.length);
/* 596 */     AbstractHashedMap.HashEntry entry = this.map.data[index];
/* 597 */     while (entry != null) {
/* 598 */       if (entry.hashCode == hashCode && isEqualKey(entry, key1, key2, key3, key4, key5)) {
/* 599 */         Object oldValue = entry.getValue();
/* 600 */         this.map.updateEntry(entry, value);
/* 601 */         return oldValue;
/*     */       } 
/* 603 */       entry = entry.next;
/*     */     } 
/* 606 */     this.map.addMapping(index, hashCode, new MultiKey(key1, key2, key3, key4, key5), value);
/* 607 */     return null;
/*     */   }
/*     */   
/*     */   public Object remove(Object key1, Object key2, Object key3, Object key4, Object key5) {
/* 621 */     int hashCode = hash(key1, key2, key3, key4, key5);
/* 622 */     int index = this.map.hashIndex(hashCode, this.map.data.length);
/* 623 */     AbstractHashedMap.HashEntry entry = this.map.data[index];
/* 624 */     AbstractHashedMap.HashEntry previous = null;
/* 625 */     while (entry != null) {
/* 626 */       if (entry.hashCode == hashCode && isEqualKey(entry, key1, key2, key3, key4, key5)) {
/* 627 */         Object oldValue = entry.getValue();
/* 628 */         this.map.removeMapping(entry, index, previous);
/* 629 */         return oldValue;
/*     */       } 
/* 631 */       previous = entry;
/* 632 */       entry = entry.next;
/*     */     } 
/* 634 */     return null;
/*     */   }
/*     */   
/*     */   protected int hash(Object key1, Object key2, Object key3, Object key4, Object key5) {
/* 648 */     int h = 0;
/* 649 */     if (key1 != null)
/* 650 */       h ^= key1.hashCode(); 
/* 652 */     if (key2 != null)
/* 653 */       h ^= key2.hashCode(); 
/* 655 */     if (key3 != null)
/* 656 */       h ^= key3.hashCode(); 
/* 658 */     if (key4 != null)
/* 659 */       h ^= key4.hashCode(); 
/* 661 */     if (key5 != null)
/* 662 */       h ^= key5.hashCode(); 
/* 664 */     h += h << 9 ^ 0xFFFFFFFF;
/* 665 */     h ^= h >>> 14;
/* 666 */     h += h << 4;
/* 667 */     h ^= h >>> 10;
/* 668 */     return h;
/*     */   }
/*     */   
/*     */   protected boolean isEqualKey(AbstractHashedMap.HashEntry entry, Object key1, Object key2, Object key3, Object key4, Object key5) {
/* 683 */     MultiKey multi = (MultiKey)entry.getKey();
/* 684 */     return (multi.size() == 5 && ((key1 == null) ? (multi.getKey(0) == null) : key1.equals(multi.getKey(0))) && ((key2 == null) ? (multi.getKey(1) == null) : key2.equals(multi.getKey(1))) && ((key3 == null) ? (multi.getKey(2) == null) : key3.equals(multi.getKey(2))) && ((key4 == null) ? (multi.getKey(3) == null) : key4.equals(multi.getKey(3))) && ((key5 == null) ? (multi.getKey(4) == null) : key5.equals(multi.getKey(4))));
/*     */   }
/*     */   
/*     */   public boolean removeAll(Object key1) {
/* 704 */     boolean modified = false;
/* 705 */     MapIterator it = mapIterator();
/* 706 */     while (it.hasNext()) {
/* 707 */       MultiKey multi = (MultiKey)it.next();
/* 708 */       if (multi.size() >= 1 && ((key1 == null) ? (multi.getKey(0) == null) : key1.equals(multi.getKey(0)))) {
/* 710 */         it.remove();
/* 711 */         modified = true;
/*     */       } 
/*     */     } 
/* 714 */     return modified;
/*     */   }
/*     */   
/*     */   public boolean removeAll(Object key1, Object key2) {
/* 728 */     boolean modified = false;
/* 729 */     MapIterator it = mapIterator();
/* 730 */     while (it.hasNext()) {
/* 731 */       MultiKey multi = (MultiKey)it.next();
/* 732 */       if (multi.size() >= 2 && ((key1 == null) ? (multi.getKey(0) == null) : key1.equals(multi.getKey(0))) && ((key2 == null) ? (multi.getKey(1) == null) : key2.equals(multi.getKey(1)))) {
/* 735 */         it.remove();
/* 736 */         modified = true;
/*     */       } 
/*     */     } 
/* 739 */     return modified;
/*     */   }
/*     */   
/*     */   public boolean removeAll(Object key1, Object key2, Object key3) {
/* 754 */     boolean modified = false;
/* 755 */     MapIterator it = mapIterator();
/* 756 */     while (it.hasNext()) {
/* 757 */       MultiKey multi = (MultiKey)it.next();
/* 758 */       if (multi.size() >= 3 && ((key1 == null) ? (multi.getKey(0) == null) : key1.equals(multi.getKey(0))) && ((key2 == null) ? (multi.getKey(1) == null) : key2.equals(multi.getKey(1))) && ((key3 == null) ? (multi.getKey(2) == null) : key3.equals(multi.getKey(2)))) {
/* 762 */         it.remove();
/* 763 */         modified = true;
/*     */       } 
/*     */     } 
/* 766 */     return modified;
/*     */   }
/*     */   
/*     */   public boolean removeAll(Object key1, Object key2, Object key3, Object key4) {
/* 782 */     boolean modified = false;
/* 783 */     MapIterator it = mapIterator();
/* 784 */     while (it.hasNext()) {
/* 785 */       MultiKey multi = (MultiKey)it.next();
/* 786 */       if (multi.size() >= 4 && ((key1 == null) ? (multi.getKey(0) == null) : key1.equals(multi.getKey(0))) && ((key2 == null) ? (multi.getKey(1) == null) : key2.equals(multi.getKey(1))) && ((key3 == null) ? (multi.getKey(2) == null) : key3.equals(multi.getKey(2))) && ((key4 == null) ? (multi.getKey(3) == null) : key4.equals(multi.getKey(3)))) {
/* 791 */         it.remove();
/* 792 */         modified = true;
/*     */       } 
/*     */     } 
/* 795 */     return modified;
/*     */   }
/*     */   
/*     */   protected void checkKey(Object key) {
/* 805 */     if (key == null)
/* 806 */       throw new NullPointerException("Key must not be null"); 
/* 808 */     if (!(key instanceof MultiKey))
/* 809 */       throw new ClassCastException("Key must be a MultiKey"); 
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 819 */     return new MultiKeyMap((AbstractHashedMap)this.map.clone());
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 833 */     checkKey(key);
/* 834 */     return this.map.put(key, value);
/*     */   }
/*     */   
/*     */   public void putAll(Map mapToCopy) {
/* 846 */     for (Iterator it = mapToCopy.keySet().iterator(); it.hasNext(); ) {
/* 847 */       Object key = it.next();
/* 848 */       checkKey(key);
/*     */     } 
/* 850 */     this.map.putAll(mapToCopy);
/*     */   }
/*     */   
/*     */   public MapIterator mapIterator() {
/* 855 */     return this.map.mapIterator();
/*     */   }
/*     */   
/*     */   public int size() {
/* 859 */     return this.map.size();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 863 */     return this.map.isEmpty();
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 867 */     return this.map.containsKey(key);
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object value) {
/* 871 */     return this.map.containsValue(value);
/*     */   }
/*     */   
/*     */   public Object get(Object key) {
/* 875 */     return this.map.get(key);
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/* 879 */     return this.map.remove(key);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 883 */     this.map.clear();
/*     */   }
/*     */   
/*     */   public Set keySet() {
/* 887 */     return this.map.keySet();
/*     */   }
/*     */   
/*     */   public Collection values() {
/* 891 */     return this.map.values();
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/* 895 */     return this.map.entrySet();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 899 */     if (obj == this)
/* 900 */       return true; 
/* 902 */     return this.map.equals(obj);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 906 */     return this.map.hashCode();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 910 */     return this.map.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\map\MultiKeyMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */