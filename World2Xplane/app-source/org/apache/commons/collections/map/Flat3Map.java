/*      */ package org.apache.commons.collections.map;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.AbstractCollection;
/*      */ import java.util.AbstractSet;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Set;
/*      */ import org.apache.commons.collections.IterableMap;
/*      */ import org.apache.commons.collections.MapIterator;
/*      */ import org.apache.commons.collections.ResettableIterator;
/*      */ import org.apache.commons.collections.iterators.EmptyIterator;
/*      */ import org.apache.commons.collections.iterators.EmptyMapIterator;
/*      */ 
/*      */ public class Flat3Map implements IterableMap, Serializable, Cloneable {
/*      */   private static final long serialVersionUID = -6701087419741928296L;
/*      */   
/*      */   private transient int size;
/*      */   
/*      */   private transient int hash1;
/*      */   
/*      */   private transient int hash2;
/*      */   
/*      */   private transient int hash3;
/*      */   
/*      */   private transient Object key1;
/*      */   
/*      */   private transient Object key2;
/*      */   
/*      */   private transient Object key3;
/*      */   
/*      */   private transient Object value1;
/*      */   
/*      */   private transient Object value2;
/*      */   
/*      */   private transient Object value3;
/*      */   
/*      */   private transient AbstractHashedMap delegateMap;
/*      */   
/*      */   public Flat3Map() {}
/*      */   
/*      */   public Flat3Map(Map map) {
/*  118 */     putAll(map);
/*      */   }
/*      */   
/*      */   public Object get(Object key) {
/*  129 */     if (this.delegateMap != null)
/*  130 */       return this.delegateMap.get(key); 
/*  132 */     if (key == null) {
/*  133 */       switch (this.size) {
/*      */         case 3:
/*  136 */           if (this.key3 == null)
/*  136 */             return this.value3; 
/*      */         case 2:
/*  138 */           if (this.key2 == null)
/*  138 */             return this.value2; 
/*      */         case 1:
/*  140 */           if (this.key1 == null)
/*  140 */             return this.value1; 
/*      */           break;
/*      */       } 
/*  143 */     } else if (this.size > 0) {
/*  144 */       int hashCode = key.hashCode();
/*  145 */       switch (this.size) {
/*      */         case 3:
/*  148 */           if (this.hash3 == hashCode && key.equals(this.key3))
/*  148 */             return this.value3; 
/*      */         case 2:
/*  150 */           if (this.hash2 == hashCode && key.equals(this.key2))
/*  150 */             return this.value2; 
/*      */         case 1:
/*  152 */           if (this.hash1 == hashCode && key.equals(this.key1))
/*  152 */             return this.value1; 
/*      */           break;
/*      */       } 
/*      */     } 
/*  156 */     return null;
/*      */   }
/*      */   
/*      */   public int size() {
/*  165 */     if (this.delegateMap != null)
/*  166 */       return this.delegateMap.size(); 
/*  168 */     return this.size;
/*      */   }
/*      */   
/*      */   public boolean isEmpty() {
/*  177 */     return (size() == 0);
/*      */   }
/*      */   
/*      */   public boolean containsKey(Object key) {
/*  188 */     if (this.delegateMap != null)
/*  189 */       return this.delegateMap.containsKey(key); 
/*  191 */     if (key == null) {
/*  192 */       switch (this.size) {
/*      */         case 3:
/*  194 */           if (this.key3 == null)
/*  194 */             return true; 
/*      */         case 2:
/*  196 */           if (this.key2 == null)
/*  196 */             return true; 
/*      */         case 1:
/*  198 */           if (this.key1 == null)
/*  198 */             return true; 
/*      */           break;
/*      */       } 
/*  201 */     } else if (this.size > 0) {
/*  202 */       int hashCode = key.hashCode();
/*  203 */       switch (this.size) {
/*      */         case 3:
/*  205 */           if (this.hash3 == hashCode && key.equals(this.key3))
/*  205 */             return true; 
/*      */         case 2:
/*  207 */           if (this.hash2 == hashCode && key.equals(this.key2))
/*  207 */             return true; 
/*      */         case 1:
/*  209 */           if (this.hash1 == hashCode && key.equals(this.key1))
/*  209 */             return true; 
/*      */           break;
/*      */       } 
/*      */     } 
/*  213 */     return false;
/*      */   }
/*      */   
/*      */   public boolean containsValue(Object value) {
/*  223 */     if (this.delegateMap != null)
/*  224 */       return this.delegateMap.containsValue(value); 
/*  226 */     if (value == null) {
/*  227 */       switch (this.size) {
/*      */         case 3:
/*  229 */           if (this.value3 == null)
/*  229 */             return true; 
/*      */         case 2:
/*  231 */           if (this.value2 == null)
/*  231 */             return true; 
/*      */         case 1:
/*  233 */           if (this.value1 == null)
/*  233 */             return true; 
/*      */           break;
/*      */       } 
/*      */     } else {
/*  236 */       switch (this.size) {
/*      */         case 3:
/*  238 */           if (value.equals(this.value3))
/*  238 */             return true; 
/*      */         case 2:
/*  240 */           if (value.equals(this.value2))
/*  240 */             return true; 
/*      */         case 1:
/*  242 */           if (value.equals(this.value1))
/*  242 */             return true; 
/*      */           break;
/*      */       } 
/*      */     } 
/*  245 */     return false;
/*      */   }
/*      */   
/*      */   public Object put(Object key, Object value) {
/*  257 */     if (this.delegateMap != null)
/*  258 */       return this.delegateMap.put(key, value); 
/*  261 */     if (key == null) {
/*  262 */       switch (this.size) {
/*      */         case 3:
/*  264 */           if (this.key3 == null) {
/*  265 */             Object old = this.value3;
/*  266 */             this.value3 = value;
/*  267 */             return old;
/*      */           } 
/*      */         case 2:
/*  270 */           if (this.key2 == null) {
/*  271 */             Object old = this.value2;
/*  272 */             this.value2 = value;
/*  273 */             return old;
/*      */           } 
/*      */         case 1:
/*  276 */           if (this.key1 == null) {
/*  277 */             Object old = this.value1;
/*  278 */             this.value1 = value;
/*  279 */             return old;
/*      */           } 
/*      */           break;
/*      */       } 
/*  283 */     } else if (this.size > 0) {
/*  284 */       int hashCode = key.hashCode();
/*  285 */       switch (this.size) {
/*      */         case 3:
/*  287 */           if (this.hash3 == hashCode && key.equals(this.key3)) {
/*  288 */             Object old = this.value3;
/*  289 */             this.value3 = value;
/*  290 */             return old;
/*      */           } 
/*      */         case 2:
/*  293 */           if (this.hash2 == hashCode && key.equals(this.key2)) {
/*  294 */             Object old = this.value2;
/*  295 */             this.value2 = value;
/*  296 */             return old;
/*      */           } 
/*      */         case 1:
/*  299 */           if (this.hash1 == hashCode && key.equals(this.key1)) {
/*  300 */             Object old = this.value1;
/*  301 */             this.value1 = value;
/*  302 */             return old;
/*      */           } 
/*      */           break;
/*      */       } 
/*      */     } 
/*  309 */     switch (this.size) {
/*      */       default:
/*  311 */         convertToMap();
/*  312 */         this.delegateMap.put(key, value);
/*  313 */         return null;
/*      */       case 2:
/*  315 */         this.hash3 = (key == null) ? 0 : key.hashCode();
/*  316 */         this.key3 = key;
/*  317 */         this.value3 = value;
/*  330 */         this.size++;
/*  331 */         return null;
/*      */       case 1:
/*      */         this.hash2 = (key == null) ? 0 : key.hashCode();
/*      */         this.key2 = key;
/*      */         this.value2 = value;
/*      */         this.size++;
/*  331 */         return null;
/*      */       case 0:
/*      */         break;
/*      */     } 
/*      */     this.hash1 = (key == null) ? 0 : key.hashCode();
/*      */     this.key1 = key;
/*      */     this.value1 = value;
/*      */     this.size++;
/*  331 */     return null;
/*      */   }
/*      */   
/*      */   public void putAll(Map map) {
/*  341 */     int size = map.size();
/*  342 */     if (size == 0)
/*      */       return; 
/*  345 */     if (this.delegateMap != null) {
/*  346 */       this.delegateMap.putAll(map);
/*      */       return;
/*      */     } 
/*  349 */     if (size < 4) {
/*  350 */       for (Iterator it = map.entrySet().iterator(); it.hasNext(); ) {
/*  351 */         Map.Entry entry = it.next();
/*  352 */         put(entry.getKey(), entry.getValue());
/*      */       } 
/*      */     } else {
/*  355 */       convertToMap();
/*  356 */       this.delegateMap.putAll(map);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void convertToMap() {
/*  364 */     this.delegateMap = createDelegateMap();
/*  365 */     switch (this.size) {
/*      */       case 3:
/*  367 */         this.delegateMap.put(this.key3, this.value3);
/*      */       case 2:
/*  369 */         this.delegateMap.put(this.key2, this.value2);
/*      */       case 1:
/*  371 */         this.delegateMap.put(this.key1, this.value1);
/*      */         break;
/*      */     } 
/*  374 */     this.size = 0;
/*  375 */     this.hash1 = this.hash2 = this.hash3 = 0;
/*  376 */     this.key1 = this.key2 = this.key3 = null;
/*  377 */     this.value1 = this.value2 = this.value3 = null;
/*      */   }
/*      */   
/*      */   protected AbstractHashedMap createDelegateMap() {
/*  391 */     return new HashedMap();
/*      */   }
/*      */   
/*      */   public Object remove(Object key) {
/*  401 */     if (this.delegateMap != null)
/*  402 */       return this.delegateMap.remove(key); 
/*  404 */     if (this.size == 0)
/*  405 */       return null; 
/*  407 */     if (key == null) {
/*  408 */       switch (this.size) {
/*      */         case 3:
/*  410 */           if (this.key3 == null) {
/*  411 */             Object old = this.value3;
/*  412 */             this.hash3 = 0;
/*  413 */             this.key3 = null;
/*  414 */             this.value3 = null;
/*  415 */             this.size = 2;
/*  416 */             return old;
/*      */           } 
/*  418 */           if (this.key2 == null) {
/*  419 */             Object old = this.value3;
/*  420 */             this.hash2 = this.hash3;
/*  421 */             this.key2 = this.key3;
/*  422 */             this.value2 = this.value3;
/*  423 */             this.hash3 = 0;
/*  424 */             this.key3 = null;
/*  425 */             this.value3 = null;
/*  426 */             this.size = 2;
/*  427 */             return old;
/*      */           } 
/*  429 */           if (this.key1 == null) {
/*  430 */             Object old = this.value3;
/*  431 */             this.hash1 = this.hash3;
/*  432 */             this.key1 = this.key3;
/*  433 */             this.value1 = this.value3;
/*  434 */             this.hash3 = 0;
/*  435 */             this.key3 = null;
/*  436 */             this.value3 = null;
/*  437 */             this.size = 2;
/*  438 */             return old;
/*      */           } 
/*  440 */           return null;
/*      */         case 2:
/*  442 */           if (this.key2 == null) {
/*  443 */             Object old = this.value2;
/*  444 */             this.hash2 = 0;
/*  445 */             this.key2 = null;
/*  446 */             this.value2 = null;
/*  447 */             this.size = 1;
/*  448 */             return old;
/*      */           } 
/*  450 */           if (this.key1 == null) {
/*  451 */             Object old = this.value2;
/*  452 */             this.hash1 = this.hash2;
/*  453 */             this.key1 = this.key2;
/*  454 */             this.value1 = this.value2;
/*  455 */             this.hash2 = 0;
/*  456 */             this.key2 = null;
/*  457 */             this.value2 = null;
/*  458 */             this.size = 1;
/*  459 */             return old;
/*      */           } 
/*  461 */           return null;
/*      */         case 1:
/*  463 */           if (this.key1 == null) {
/*  464 */             Object old = this.value1;
/*  465 */             this.hash1 = 0;
/*  466 */             this.key1 = null;
/*  467 */             this.value1 = null;
/*  468 */             this.size = 0;
/*  469 */             return old;
/*      */           } 
/*      */           break;
/*      */       } 
/*  473 */     } else if (this.size > 0) {
/*  474 */       int hashCode = key.hashCode();
/*  475 */       switch (this.size) {
/*      */         case 3:
/*  477 */           if (this.hash3 == hashCode && key.equals(this.key3)) {
/*  478 */             Object old = this.value3;
/*  479 */             this.hash3 = 0;
/*  480 */             this.key3 = null;
/*  481 */             this.value3 = null;
/*  482 */             this.size = 2;
/*  483 */             return old;
/*      */           } 
/*  485 */           if (this.hash2 == hashCode && key.equals(this.key2)) {
/*  486 */             Object old = this.value3;
/*  487 */             this.hash2 = this.hash3;
/*  488 */             this.key2 = this.key3;
/*  489 */             this.value2 = this.value3;
/*  490 */             this.hash3 = 0;
/*  491 */             this.key3 = null;
/*  492 */             this.value3 = null;
/*  493 */             this.size = 2;
/*  494 */             return old;
/*      */           } 
/*  496 */           if (this.hash1 == hashCode && key.equals(this.key1)) {
/*  497 */             Object old = this.value3;
/*  498 */             this.hash1 = this.hash3;
/*  499 */             this.key1 = this.key3;
/*  500 */             this.value1 = this.value3;
/*  501 */             this.hash3 = 0;
/*  502 */             this.key3 = null;
/*  503 */             this.value3 = null;
/*  504 */             this.size = 2;
/*  505 */             return old;
/*      */           } 
/*  507 */           return null;
/*      */         case 2:
/*  509 */           if (this.hash2 == hashCode && key.equals(this.key2)) {
/*  510 */             Object old = this.value2;
/*  511 */             this.hash2 = 0;
/*  512 */             this.key2 = null;
/*  513 */             this.value2 = null;
/*  514 */             this.size = 1;
/*  515 */             return old;
/*      */           } 
/*  517 */           if (this.hash1 == hashCode && key.equals(this.key1)) {
/*  518 */             Object old = this.value2;
/*  519 */             this.hash1 = this.hash2;
/*  520 */             this.key1 = this.key2;
/*  521 */             this.value1 = this.value2;
/*  522 */             this.hash2 = 0;
/*  523 */             this.key2 = null;
/*  524 */             this.value2 = null;
/*  525 */             this.size = 1;
/*  526 */             return old;
/*      */           } 
/*  528 */           return null;
/*      */         case 1:
/*  530 */           if (this.hash1 == hashCode && key.equals(this.key1)) {
/*  531 */             Object old = this.value1;
/*  532 */             this.hash1 = 0;
/*  533 */             this.key1 = null;
/*  534 */             this.value1 = null;
/*  535 */             this.size = 0;
/*  536 */             return old;
/*      */           } 
/*      */           break;
/*      */       } 
/*      */     } 
/*  541 */     return null;
/*      */   }
/*      */   
/*      */   public void clear() {
/*  549 */     if (this.delegateMap != null) {
/*  550 */       this.delegateMap.clear();
/*  551 */       this.delegateMap = null;
/*      */     } else {
/*  553 */       this.size = 0;
/*  554 */       this.hash1 = this.hash2 = this.hash3 = 0;
/*  555 */       this.key1 = this.key2 = this.key3 = null;
/*  556 */       this.value1 = this.value2 = this.value3 = null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public MapIterator mapIterator() {
/*  573 */     if (this.delegateMap != null)
/*  574 */       return this.delegateMap.mapIterator(); 
/*  576 */     if (this.size == 0)
/*  577 */       return EmptyMapIterator.INSTANCE; 
/*  579 */     return new FlatMapIterator(this);
/*      */   }
/*      */   
/*      */   static class FlatMapIterator implements MapIterator, ResettableIterator {
/*      */     private final Flat3Map parent;
/*      */     
/*  587 */     private int nextIndex = 0;
/*      */     
/*      */     private boolean canRemove = false;
/*      */     
/*      */     FlatMapIterator(Flat3Map parent) {
/*  592 */       this.parent = parent;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  596 */       return (this.nextIndex < this.parent.size);
/*      */     }
/*      */     
/*      */     public Object next() {
/*  600 */       if (!hasNext())
/*  601 */         throw new NoSuchElementException("No next() entry in the iteration"); 
/*  603 */       this.canRemove = true;
/*  604 */       this.nextIndex++;
/*  605 */       return getKey();
/*      */     }
/*      */     
/*      */     public void remove() {
/*  609 */       if (!this.canRemove)
/*  610 */         throw new IllegalStateException("remove() can only be called once after next()"); 
/*  612 */       this.parent.remove(getKey());
/*  613 */       this.nextIndex--;
/*  614 */       this.canRemove = false;
/*      */     }
/*      */     
/*      */     public Object getKey() {
/*  618 */       if (!this.canRemove)
/*  619 */         throw new IllegalStateException("getKey() can only be called after next() and before remove()"); 
/*  621 */       switch (this.nextIndex) {
/*      */         case 3:
/*  623 */           return this.parent.key3;
/*      */         case 2:
/*  625 */           return this.parent.key2;
/*      */         case 1:
/*  627 */           return this.parent.key1;
/*      */       } 
/*  629 */       throw new IllegalStateException("Invalid map index");
/*      */     }
/*      */     
/*      */     public Object getValue() {
/*  633 */       if (!this.canRemove)
/*  634 */         throw new IllegalStateException("getValue() can only be called after next() and before remove()"); 
/*  636 */       switch (this.nextIndex) {
/*      */         case 3:
/*  638 */           return this.parent.value3;
/*      */         case 2:
/*  640 */           return this.parent.value2;
/*      */         case 1:
/*  642 */           return this.parent.value1;
/*      */       } 
/*  644 */       throw new IllegalStateException("Invalid map index");
/*      */     }
/*      */     
/*      */     public Object setValue(Object value) {
/*  648 */       if (!this.canRemove)
/*  649 */         throw new IllegalStateException("setValue() can only be called after next() and before remove()"); 
/*  651 */       Object old = getValue();
/*  652 */       switch (this.nextIndex) {
/*      */         case 3:
/*  654 */           this.parent.value3 = value;
/*      */         case 2:
/*  656 */           this.parent.value2 = value;
/*      */         case 1:
/*  658 */           this.parent.value1 = value;
/*      */           break;
/*      */       } 
/*  660 */       return old;
/*      */     }
/*      */     
/*      */     public void reset() {
/*  664 */       this.nextIndex = 0;
/*  665 */       this.canRemove = false;
/*      */     }
/*      */     
/*      */     public String toString() {
/*  669 */       if (this.canRemove)
/*  670 */         return "Iterator[" + getKey() + "=" + getValue() + "]"; 
/*  672 */       return "Iterator[]";
/*      */     }
/*      */   }
/*      */   
/*      */   public Set entrySet() {
/*  687 */     if (this.delegateMap != null)
/*  688 */       return this.delegateMap.entrySet(); 
/*  690 */     return new EntrySet(this);
/*      */   }
/*      */   
/*      */   static class EntrySet extends AbstractSet {
/*      */     private final Flat3Map parent;
/*      */     
/*      */     EntrySet(Flat3Map parent) {
/*  701 */       this.parent = parent;
/*      */     }
/*      */     
/*      */     public int size() {
/*  705 */       return this.parent.size();
/*      */     }
/*      */     
/*      */     public void clear() {
/*  709 */       this.parent.clear();
/*      */     }
/*      */     
/*      */     public boolean remove(Object obj) {
/*  713 */       if (!(obj instanceof Map.Entry))
/*  714 */         return false; 
/*  716 */       Map.Entry entry = (Map.Entry)obj;
/*  717 */       Object key = entry.getKey();
/*  718 */       boolean result = this.parent.containsKey(key);
/*  719 */       this.parent.remove(key);
/*  720 */       return result;
/*      */     }
/*      */     
/*      */     public Iterator iterator() {
/*  724 */       if (this.parent.delegateMap != null)
/*  725 */         return this.parent.delegateMap.entrySet().iterator(); 
/*  727 */       if (this.parent.size() == 0)
/*  728 */         return EmptyIterator.INSTANCE; 
/*  730 */       return new Flat3Map.EntrySetIterator(this.parent);
/*      */     }
/*      */   }
/*      */   
/*      */   static class EntrySetIterator implements Iterator, Map.Entry {
/*      */     private final Flat3Map parent;
/*      */     
/*  739 */     private int nextIndex = 0;
/*      */     
/*      */     private boolean canRemove = false;
/*      */     
/*      */     EntrySetIterator(Flat3Map parent) {
/*  744 */       this.parent = parent;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  748 */       return (this.nextIndex < this.parent.size);
/*      */     }
/*      */     
/*      */     public Object next() {
/*  752 */       if (!hasNext())
/*  753 */         throw new NoSuchElementException("No next() entry in the iteration"); 
/*  755 */       this.canRemove = true;
/*  756 */       this.nextIndex++;
/*  757 */       return this;
/*      */     }
/*      */     
/*      */     public void remove() {
/*  761 */       if (!this.canRemove)
/*  762 */         throw new IllegalStateException("remove() can only be called once after next()"); 
/*  764 */       this.parent.remove(getKey());
/*  765 */       this.nextIndex--;
/*  766 */       this.canRemove = false;
/*      */     }
/*      */     
/*      */     public Object getKey() {
/*  770 */       if (!this.canRemove)
/*  771 */         throw new IllegalStateException("getKey() can only be called after next() and before remove()"); 
/*  773 */       switch (this.nextIndex) {
/*      */         case 3:
/*  775 */           return this.parent.key3;
/*      */         case 2:
/*  777 */           return this.parent.key2;
/*      */         case 1:
/*  779 */           return this.parent.key1;
/*      */       } 
/*  781 */       throw new IllegalStateException("Invalid map index");
/*      */     }
/*      */     
/*      */     public Object getValue() {
/*  785 */       if (!this.canRemove)
/*  786 */         throw new IllegalStateException("getValue() can only be called after next() and before remove()"); 
/*  788 */       switch (this.nextIndex) {
/*      */         case 3:
/*  790 */           return this.parent.value3;
/*      */         case 2:
/*  792 */           return this.parent.value2;
/*      */         case 1:
/*  794 */           return this.parent.value1;
/*      */       } 
/*  796 */       throw new IllegalStateException("Invalid map index");
/*      */     }
/*      */     
/*      */     public Object setValue(Object value) {
/*  800 */       if (!this.canRemove)
/*  801 */         throw new IllegalStateException("setValue() can only be called after next() and before remove()"); 
/*  803 */       Object old = getValue();
/*  804 */       switch (this.nextIndex) {
/*      */         case 3:
/*  806 */           this.parent.value3 = value;
/*      */         case 2:
/*  808 */           this.parent.value2 = value;
/*      */         case 1:
/*  810 */           this.parent.value1 = value;
/*      */           break;
/*      */       } 
/*  812 */       return old;
/*      */     }
/*      */     
/*      */     public boolean equals(Object obj) {
/*  816 */       if (!this.canRemove)
/*  817 */         return false; 
/*  819 */       if (!(obj instanceof Map.Entry))
/*  820 */         return false; 
/*  822 */       Map.Entry other = (Map.Entry)obj;
/*  823 */       Object key = getKey();
/*  824 */       Object value = getValue();
/*  825 */       return (((key == null) ? (other.getKey() == null) : key.equals(other.getKey())) && ((value == null) ? (other.getValue() == null) : value.equals(other.getValue())));
/*      */     }
/*      */     
/*      */     public int hashCode() {
/*  830 */       if (!this.canRemove)
/*  831 */         return 0; 
/*  833 */       Object key = getKey();
/*  834 */       Object value = getValue();
/*  835 */       return ((key == null) ? 0 : key.hashCode()) ^ ((value == null) ? 0 : value.hashCode());
/*      */     }
/*      */     
/*      */     public String toString() {
/*  840 */       if (this.canRemove)
/*  841 */         return getKey() + "=" + getValue(); 
/*  843 */       return "";
/*      */     }
/*      */   }
/*      */   
/*      */   public Set keySet() {
/*  856 */     if (this.delegateMap != null)
/*  857 */       return this.delegateMap.keySet(); 
/*  859 */     return new KeySet(this);
/*      */   }
/*      */   
/*      */   static class KeySet extends AbstractSet {
/*      */     private final Flat3Map parent;
/*      */     
/*      */     KeySet(Flat3Map parent) {
/*  870 */       this.parent = parent;
/*      */     }
/*      */     
/*      */     public int size() {
/*  874 */       return this.parent.size();
/*      */     }
/*      */     
/*      */     public void clear() {
/*  878 */       this.parent.clear();
/*      */     }
/*      */     
/*      */     public boolean contains(Object key) {
/*  882 */       return this.parent.containsKey(key);
/*      */     }
/*      */     
/*      */     public boolean remove(Object key) {
/*  886 */       boolean result = this.parent.containsKey(key);
/*  887 */       this.parent.remove(key);
/*  888 */       return result;
/*      */     }
/*      */     
/*      */     public Iterator iterator() {
/*  892 */       if (this.parent.delegateMap != null)
/*  893 */         return this.parent.delegateMap.keySet().iterator(); 
/*  895 */       if (this.parent.size() == 0)
/*  896 */         return EmptyIterator.INSTANCE; 
/*  898 */       return new Flat3Map.KeySetIterator(this.parent);
/*      */     }
/*      */   }
/*      */   
/*      */   static class KeySetIterator extends EntrySetIterator {
/*      */     KeySetIterator(Flat3Map parent) {
/*  908 */       super(parent);
/*      */     }
/*      */     
/*      */     public Object next() {
/*  912 */       super.next();
/*  913 */       return getKey();
/*      */     }
/*      */   }
/*      */   
/*      */   public Collection values() {
/*  925 */     if (this.delegateMap != null)
/*  926 */       return this.delegateMap.values(); 
/*  928 */     return new Values(this);
/*      */   }
/*      */   
/*      */   static class Values extends AbstractCollection {
/*      */     private final Flat3Map parent;
/*      */     
/*      */     Values(Flat3Map parent) {
/*  939 */       this.parent = parent;
/*      */     }
/*      */     
/*      */     public int size() {
/*  943 */       return this.parent.size();
/*      */     }
/*      */     
/*      */     public void clear() {
/*  947 */       this.parent.clear();
/*      */     }
/*      */     
/*      */     public boolean contains(Object value) {
/*  951 */       return this.parent.containsValue(value);
/*      */     }
/*      */     
/*      */     public Iterator iterator() {
/*  955 */       if (this.parent.delegateMap != null)
/*  956 */         return this.parent.delegateMap.values().iterator(); 
/*  958 */       if (this.parent.size() == 0)
/*  959 */         return EmptyIterator.INSTANCE; 
/*  961 */       return new Flat3Map.ValuesIterator(this.parent);
/*      */     }
/*      */   }
/*      */   
/*      */   static class ValuesIterator extends EntrySetIterator {
/*      */     ValuesIterator(Flat3Map parent) {
/*  971 */       super(parent);
/*      */     }
/*      */     
/*      */     public Object next() {
/*  975 */       super.next();
/*  976 */       return getValue();
/*      */     }
/*      */   }
/*      */   
/*      */   private void writeObject(ObjectOutputStream out) throws IOException {
/*  985 */     out.defaultWriteObject();
/*  986 */     out.writeInt(size());
/*  987 */     for (MapIterator it = mapIterator(); it.hasNext(); ) {
/*  988 */       out.writeObject(it.next());
/*  989 */       out.writeObject(it.getValue());
/*      */     } 
/*      */   }
/*      */   
/*      */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/*  997 */     in.defaultReadObject();
/*  998 */     int count = in.readInt();
/*  999 */     if (count > 3)
/* 1000 */       this.delegateMap = createDelegateMap(); 
/* 1002 */     for (int i = count; i > 0; i--)
/* 1003 */       put(in.readObject(), in.readObject()); 
/*      */   }
/*      */   
/*      */   public Object clone() {
/*      */     try {
/* 1016 */       Flat3Map cloned = (Flat3Map)super.clone();
/* 1017 */       if (cloned.delegateMap != null)
/* 1018 */         cloned.delegateMap = (HashedMap)cloned.delegateMap.clone(); 
/* 1020 */       return cloned;
/* 1021 */     } catch (CloneNotSupportedException ex) {
/* 1022 */       throw new InternalError();
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean equals(Object obj) {
/* 1033 */     if (obj == this)
/* 1034 */       return true; 
/* 1036 */     if (this.delegateMap != null)
/* 1037 */       return this.delegateMap.equals(obj); 
/* 1039 */     if (!(obj instanceof Map))
/* 1040 */       return false; 
/* 1042 */     Map other = (Map)obj;
/* 1043 */     if (this.size != other.size())
/* 1044 */       return false; 
/* 1046 */     if (this.size > 0) {
/* 1047 */       Object otherValue = null;
/* 1048 */       switch (this.size) {
/*      */         case 3:
/* 1050 */           if (!other.containsKey(this.key3))
/* 1051 */             return false; 
/* 1053 */           otherValue = other.get(this.key3);
/* 1054 */           if ((this.value3 == null) ? (otherValue != null) : !this.value3.equals(otherValue))
/* 1055 */             return false; 
/*      */         case 2:
/* 1058 */           if (!other.containsKey(this.key2))
/* 1059 */             return false; 
/* 1061 */           otherValue = other.get(this.key2);
/* 1062 */           if ((this.value2 == null) ? (otherValue != null) : !this.value2.equals(otherValue))
/* 1063 */             return false; 
/*      */         case 1:
/* 1066 */           if (!other.containsKey(this.key1))
/* 1067 */             return false; 
/* 1069 */           otherValue = other.get(this.key1);
/* 1070 */           if ((this.value1 == null) ? (otherValue != null) : !this.value1.equals(otherValue))
/* 1071 */             return false; 
/*      */           break;
/*      */       } 
/*      */     } 
/* 1075 */     return true;
/*      */   }
/*      */   
/*      */   public int hashCode() {
/* 1084 */     if (this.delegateMap != null)
/* 1085 */       return this.delegateMap.hashCode(); 
/* 1087 */     int total = 0;
/* 1088 */     switch (this.size) {
/*      */       case 3:
/* 1090 */         total += this.hash3 ^ ((this.value3 == null) ? 0 : this.value3.hashCode());
/*      */       case 2:
/* 1092 */         total += this.hash2 ^ ((this.value2 == null) ? 0 : this.value2.hashCode());
/*      */       case 1:
/* 1094 */         total += this.hash1 ^ ((this.value1 == null) ? 0 : this.value1.hashCode());
/*      */         break;
/*      */     } 
/* 1096 */     return total;
/*      */   }
/*      */   
/*      */   public String toString() {
/* 1105 */     if (this.delegateMap != null)
/* 1106 */       return this.delegateMap.toString(); 
/* 1108 */     if (this.size == 0)
/* 1109 */       return "{}"; 
/* 1111 */     StringBuffer buf = new StringBuffer(128);
/* 1112 */     buf.append('{');
/* 1113 */     switch (this.size) {
/*      */       case 3:
/* 1115 */         buf.append((this.key3 == this) ? "(this Map)" : this.key3);
/* 1116 */         buf.append('=');
/* 1117 */         buf.append((this.value3 == this) ? "(this Map)" : this.value3);
/* 1118 */         buf.append(',');
/*      */       case 2:
/* 1120 */         buf.append((this.key2 == this) ? "(this Map)" : this.key2);
/* 1121 */         buf.append('=');
/* 1122 */         buf.append((this.value2 == this) ? "(this Map)" : this.value2);
/* 1123 */         buf.append(',');
/*      */       case 1:
/* 1125 */         buf.append((this.key1 == this) ? "(this Map)" : this.key1);
/* 1126 */         buf.append('=');
/* 1127 */         buf.append((this.value1 == this) ? "(this Map)" : this.value1);
/*      */         break;
/*      */     } 
/* 1129 */     buf.append('}');
/* 1130 */     return buf.toString();
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\map\Flat3Map.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */