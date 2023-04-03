/*     */ package org.apache.commons.collections.bidimap;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Map;
/*     */ import java.util.SortedMap;
/*     */ import java.util.TreeMap;
/*     */ import org.apache.commons.collections.BidiMap;
/*     */ import org.apache.commons.collections.OrderedBidiMap;
/*     */ import org.apache.commons.collections.OrderedMap;
/*     */ import org.apache.commons.collections.OrderedMapIterator;
/*     */ import org.apache.commons.collections.ResettableIterator;
/*     */ import org.apache.commons.collections.SortedBidiMap;
/*     */ import org.apache.commons.collections.map.AbstractSortedMapDecorator;
/*     */ 
/*     */ public class DualTreeBidiMap extends AbstractDualBidiMap implements SortedBidiMap, Serializable {
/*     */   private static final long serialVersionUID = 721969328361809L;
/*     */   
/*     */   protected final Comparator comparator;
/*     */   
/*     */   public DualTreeBidiMap() {
/*  70 */     super(new TreeMap(), new TreeMap());
/*  71 */     this.comparator = null;
/*     */   }
/*     */   
/*     */   public DualTreeBidiMap(Map map) {
/*  81 */     super(new TreeMap(), new TreeMap());
/*  82 */     putAll(map);
/*  83 */     this.comparator = null;
/*     */   }
/*     */   
/*     */   public DualTreeBidiMap(Comparator comparator) {
/*  92 */     super(new TreeMap(comparator), new TreeMap(comparator));
/*  93 */     this.comparator = comparator;
/*     */   }
/*     */   
/*     */   protected DualTreeBidiMap(Map normalMap, Map reverseMap, BidiMap inverseBidiMap) {
/* 104 */     super(normalMap, reverseMap, inverseBidiMap);
/* 105 */     this.comparator = ((SortedMap)normalMap).comparator();
/*     */   }
/*     */   
/*     */   protected BidiMap createBidiMap(Map normalMap, Map reverseMap, BidiMap inverseMap) {
/* 117 */     return new DualTreeBidiMap(normalMap, reverseMap, inverseMap);
/*     */   }
/*     */   
/*     */   public Comparator comparator() {
/* 122 */     return ((SortedMap)this.maps[0]).comparator();
/*     */   }
/*     */   
/*     */   public Object firstKey() {
/* 126 */     return ((SortedMap)this.maps[0]).firstKey();
/*     */   }
/*     */   
/*     */   public Object lastKey() {
/* 130 */     return ((SortedMap)this.maps[0]).lastKey();
/*     */   }
/*     */   
/*     */   public Object nextKey(Object key) {
/* 134 */     if (isEmpty())
/* 135 */       return null; 
/* 137 */     if (this.maps[0] instanceof OrderedMap)
/* 138 */       return ((OrderedMap)this.maps[0]).nextKey(key); 
/* 140 */     SortedMap sm = (SortedMap)this.maps[0];
/* 141 */     Iterator it = sm.tailMap(key).keySet().iterator();
/* 142 */     it.next();
/* 143 */     if (it.hasNext())
/* 144 */       return it.next(); 
/* 146 */     return null;
/*     */   }
/*     */   
/*     */   public Object previousKey(Object key) {
/* 150 */     if (isEmpty())
/* 151 */       return null; 
/* 153 */     if (this.maps[0] instanceof OrderedMap)
/* 154 */       return ((OrderedMap)this.maps[0]).previousKey(key); 
/* 156 */     SortedMap sm = (SortedMap)this.maps[0];
/* 157 */     SortedMap hm = sm.headMap(key);
/* 158 */     if (hm.isEmpty())
/* 159 */       return null; 
/* 161 */     return hm.lastKey();
/*     */   }
/*     */   
/*     */   public OrderedMapIterator orderedMapIterator() {
/* 174 */     return new BidiOrderedMapIterator(this);
/*     */   }
/*     */   
/*     */   public SortedBidiMap inverseSortedBidiMap() {
/* 178 */     return (SortedBidiMap)inverseBidiMap();
/*     */   }
/*     */   
/*     */   public OrderedBidiMap inverseOrderedBidiMap() {
/* 182 */     return (OrderedBidiMap)inverseBidiMap();
/*     */   }
/*     */   
/*     */   public SortedMap headMap(Object toKey) {
/* 187 */     SortedMap sub = ((SortedMap)this.maps[0]).headMap(toKey);
/* 188 */     return (SortedMap)new ViewMap(this, sub);
/*     */   }
/*     */   
/*     */   public SortedMap tailMap(Object fromKey) {
/* 192 */     SortedMap sub = ((SortedMap)this.maps[0]).tailMap(fromKey);
/* 193 */     return (SortedMap)new ViewMap(this, sub);
/*     */   }
/*     */   
/*     */   public SortedMap subMap(Object fromKey, Object toKey) {
/* 197 */     SortedMap sub = ((SortedMap)this.maps[0]).subMap(fromKey, toKey);
/* 198 */     return (SortedMap)new ViewMap(this, sub);
/*     */   }
/*     */   
/*     */   protected static class ViewMap extends AbstractSortedMapDecorator {
/*     */     final DualTreeBidiMap bidi;
/*     */     
/*     */     protected ViewMap(DualTreeBidiMap bidi, SortedMap sm) {
/* 218 */       super((SortedMap)bidi.createBidiMap(sm, bidi.maps[1], bidi.inverseBidiMap));
/* 219 */       this.bidi = (DualTreeBidiMap)this.map;
/*     */     }
/*     */     
/*     */     public boolean containsValue(Object value) {
/* 224 */       return this.bidi.maps[0].containsValue(value);
/*     */     }
/*     */     
/*     */     public void clear() {
/* 229 */       for (Iterator it = keySet().iterator(); it.hasNext(); ) {
/* 230 */         it.next();
/* 231 */         it.remove();
/*     */       } 
/*     */     }
/*     */     
/*     */     public SortedMap headMap(Object toKey) {
/* 236 */       return (SortedMap)new ViewMap(this.bidi, super.headMap(toKey));
/*     */     }
/*     */     
/*     */     public SortedMap tailMap(Object fromKey) {
/* 240 */       return (SortedMap)new ViewMap(this.bidi, super.tailMap(fromKey));
/*     */     }
/*     */     
/*     */     public SortedMap subMap(Object fromKey, Object toKey) {
/* 244 */       return (SortedMap)new ViewMap(this.bidi, super.subMap(fromKey, toKey));
/*     */     }
/*     */   }
/*     */   
/*     */   protected static class BidiOrderedMapIterator implements OrderedMapIterator, ResettableIterator {
/*     */     protected final AbstractDualBidiMap parent;
/*     */     
/*     */     protected ListIterator iterator;
/*     */     
/* 259 */     private Map.Entry last = null;
/*     */     
/*     */     protected BidiOrderedMapIterator(AbstractDualBidiMap parent) {
/* 267 */       this.parent = parent;
/* 268 */       this.iterator = (new ArrayList(parent.entrySet())).listIterator();
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 272 */       return this.iterator.hasNext();
/*     */     }
/*     */     
/*     */     public Object next() {
/* 276 */       this.last = this.iterator.next();
/* 277 */       return this.last.getKey();
/*     */     }
/*     */     
/*     */     public boolean hasPrevious() {
/* 281 */       return this.iterator.hasPrevious();
/*     */     }
/*     */     
/*     */     public Object previous() {
/* 285 */       this.last = this.iterator.previous();
/* 286 */       return this.last.getKey();
/*     */     }
/*     */     
/*     */     public void remove() {
/* 290 */       this.iterator.remove();
/* 291 */       this.parent.remove(this.last.getKey());
/* 292 */       this.last = null;
/*     */     }
/*     */     
/*     */     public Object getKey() {
/* 296 */       if (this.last == null)
/* 297 */         throw new IllegalStateException("Iterator getKey() can only be called after next() and before remove()"); 
/* 299 */       return this.last.getKey();
/*     */     }
/*     */     
/*     */     public Object getValue() {
/* 303 */       if (this.last == null)
/* 304 */         throw new IllegalStateException("Iterator getValue() can only be called after next() and before remove()"); 
/* 306 */       return this.last.getValue();
/*     */     }
/*     */     
/*     */     public Object setValue(Object value) {
/* 310 */       if (this.last == null)
/* 311 */         throw new IllegalStateException("Iterator setValue() can only be called after next() and before remove()"); 
/* 313 */       if (this.parent.maps[1].containsKey(value) && this.parent.maps[1].get(value) != this.last.getKey())
/* 315 */         throw new IllegalArgumentException("Cannot use setValue() when the object being set is already in the map"); 
/* 317 */       return this.parent.put(this.last.getKey(), value);
/*     */     }
/*     */     
/*     */     public void reset() {
/* 321 */       this.iterator = (new ArrayList(this.parent.entrySet())).listIterator();
/* 322 */       this.last = null;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 326 */       if (this.last != null)
/* 327 */         return "MapIterator[" + getKey() + "=" + getValue() + "]"; 
/* 329 */       return "MapIterator[]";
/*     */     }
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 337 */     out.defaultWriteObject();
/* 338 */     out.writeObject(this.maps[0]);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 342 */     in.defaultReadObject();
/* 343 */     this.maps[0] = new TreeMap(this.comparator);
/* 344 */     this.maps[1] = new TreeMap(this.comparator);
/* 345 */     Map map = (Map)in.readObject();
/* 346 */     putAll(map);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\bidimap\DualTreeBidiMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */