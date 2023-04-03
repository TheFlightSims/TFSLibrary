/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.set.UnmodifiableSet;
/*     */ 
/*     */ public abstract class DefaultMapBag implements Bag {
/*  49 */   private Map _map = null;
/*     */   
/*  50 */   private int _total = 0;
/*     */   
/*  51 */   private int _mods = 0;
/*     */   
/*     */   protected DefaultMapBag(Map map) {
/*  68 */     setMap(map);
/*     */   }
/*     */   
/*     */   public boolean add(Object object) {
/*  79 */     return add(object, 1);
/*     */   }
/*     */   
/*     */   public boolean add(Object object, int nCopies) {
/*  90 */     this._mods++;
/*  91 */     if (nCopies > 0) {
/*  92 */       int count = nCopies + getCount(object);
/*  93 */       this._map.put(object, new Integer(count));
/*  94 */       this._total += nCopies;
/*  95 */       return (count == nCopies);
/*     */     } 
/*  97 */     return false;
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection coll) {
/* 108 */     boolean changed = false;
/* 109 */     Iterator i = coll.iterator();
/* 110 */     while (i.hasNext()) {
/* 111 */       boolean added = add(i.next());
/* 112 */       changed = (changed || added);
/*     */     } 
/* 114 */     return changed;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 121 */     this._mods++;
/* 122 */     this._map.clear();
/* 123 */     this._total = 0;
/*     */   }
/*     */   
/*     */   public boolean contains(Object object) {
/* 134 */     return this._map.containsKey(object);
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection coll) {
/* 144 */     return containsAll(new HashBag(coll));
/*     */   }
/*     */   
/*     */   public boolean containsAll(Bag other) {
/* 155 */     boolean result = true;
/* 156 */     Iterator i = other.uniqueSet().iterator();
/* 157 */     while (i.hasNext()) {
/* 158 */       Object current = i.next();
/* 159 */       boolean contains = (getCount(current) >= other.getCount(current));
/* 160 */       result = (result && contains);
/*     */     } 
/* 162 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 174 */     if (object == this)
/* 175 */       return true; 
/* 177 */     if (!(object instanceof Bag))
/* 178 */       return false; 
/* 180 */     Bag other = (Bag)object;
/* 181 */     if (other.size() != size())
/* 182 */       return false; 
/* 184 */     for (Iterator it = this._map.keySet().iterator(); it.hasNext(); ) {
/* 185 */       Object element = it.next();
/* 186 */       if (other.getCount(element) != getCount(element))
/* 187 */         return false; 
/*     */     } 
/* 190 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 199 */     return this._map.hashCode();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 208 */     return this._map.isEmpty();
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/* 212 */     return new BagIterator(this, extractList().iterator());
/*     */   }
/*     */   
/*     */   static class BagIterator implements Iterator {
/* 216 */     private DefaultMapBag _parent = null;
/*     */     
/* 217 */     private Iterator _support = null;
/*     */     
/* 218 */     private Object _current = null;
/*     */     
/* 219 */     private int _mods = 0;
/*     */     
/*     */     public BagIterator(DefaultMapBag parent, Iterator support) {
/* 222 */       this._parent = parent;
/* 223 */       this._support = support;
/* 224 */       this._current = null;
/* 225 */       this._mods = parent.modCount();
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 229 */       return this._support.hasNext();
/*     */     }
/*     */     
/*     */     public Object next() {
/* 233 */       if (this._parent.modCount() != this._mods)
/* 234 */         throw new ConcurrentModificationException(); 
/* 236 */       this._current = this._support.next();
/* 237 */       return this._current;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 241 */       if (this._parent.modCount() != this._mods)
/* 242 */         throw new ConcurrentModificationException(); 
/* 244 */       this._support.remove();
/* 245 */       this._parent.remove(this._current, 1);
/* 246 */       this._mods++;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean remove(Object object) {
/* 251 */     return remove(object, getCount(object));
/*     */   }
/*     */   
/*     */   public boolean remove(Object object, int nCopies) {
/* 255 */     this._mods++;
/* 256 */     boolean result = false;
/* 257 */     int count = getCount(object);
/* 258 */     if (nCopies <= 0) {
/* 259 */       result = false;
/* 260 */     } else if (count > nCopies) {
/* 261 */       this._map.put(object, new Integer(count - nCopies));
/* 262 */       result = true;
/* 263 */       this._total -= nCopies;
/*     */     } else {
/* 266 */       result = (this._map.remove(object) != null);
/* 267 */       this._total -= count;
/*     */     } 
/* 269 */     return result;
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection coll) {
/* 273 */     boolean result = false;
/* 274 */     if (coll != null) {
/* 275 */       Iterator i = coll.iterator();
/* 276 */       while (i.hasNext()) {
/* 277 */         boolean changed = remove(i.next(), 1);
/* 278 */         result = (result || changed);
/*     */       } 
/*     */     } 
/* 281 */     return result;
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection coll) {
/* 292 */     return retainAll(new HashBag(coll));
/*     */   }
/*     */   
/*     */   public boolean retainAll(Bag other) {
/* 304 */     boolean result = false;
/* 305 */     Bag excess = new HashBag();
/* 306 */     Iterator i = uniqueSet().iterator();
/* 307 */     while (i.hasNext()) {
/* 308 */       Object current = i.next();
/* 309 */       int myCount = getCount(current);
/* 310 */       int otherCount = other.getCount(current);
/* 311 */       if (1 <= otherCount && otherCount <= myCount) {
/* 312 */         excess.add(current, myCount - otherCount);
/*     */         continue;
/*     */       } 
/* 314 */       excess.add(current, myCount);
/*     */     } 
/* 317 */     if (!excess.isEmpty())
/* 318 */       result = removeAll(excess); 
/* 320 */     return result;
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 329 */     return extractList().toArray();
/*     */   }
/*     */   
/*     */   public Object[] toArray(Object[] array) {
/* 339 */     return extractList().toArray(array);
/*     */   }
/*     */   
/*     */   public int getCount(Object object) {
/* 350 */     int result = 0;
/* 351 */     Integer count = MapUtils.getInteger(this._map, object);
/* 352 */     if (count != null)
/* 353 */       result = count.intValue(); 
/* 355 */     return result;
/*     */   }
/*     */   
/*     */   public Set uniqueSet() {
/* 364 */     return UnmodifiableSet.decorate(this._map.keySet());
/*     */   }
/*     */   
/*     */   public int size() {
/* 373 */     return this._total;
/*     */   }
/*     */   
/*     */   protected int calcTotalSize() {
/* 383 */     this._total = extractList().size();
/* 384 */     return this._total;
/*     */   }
/*     */   
/*     */   protected void setMap(Map map) {
/* 393 */     if (map == null || !map.isEmpty())
/* 394 */       throw new IllegalArgumentException("The map must be non-null and empty"); 
/* 396 */     this._map = map;
/*     */   }
/*     */   
/*     */   protected Map getMap() {
/* 405 */     return this._map;
/*     */   }
/*     */   
/*     */   private List extractList() {
/* 412 */     List result = new ArrayList();
/* 413 */     Iterator i = uniqueSet().iterator();
/* 414 */     while (i.hasNext()) {
/* 415 */       Object current = i.next();
/* 416 */       for (int index = getCount(current); index > 0; index--)
/* 417 */         result.add(current); 
/*     */     } 
/* 420 */     return result;
/*     */   }
/*     */   
/*     */   private int modCount() {
/* 429 */     return this._mods;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 438 */     StringBuffer buf = new StringBuffer();
/* 439 */     buf.append("[");
/* 440 */     Iterator i = uniqueSet().iterator();
/* 441 */     while (i.hasNext()) {
/* 442 */       Object current = i.next();
/* 443 */       int count = getCount(current);
/* 444 */       buf.append(count);
/* 445 */       buf.append(":");
/* 446 */       buf.append(current);
/* 447 */       if (i.hasNext())
/* 448 */         buf.append(","); 
/*     */     } 
/* 451 */     buf.append("]");
/* 452 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public DefaultMapBag() {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\DefaultMapBag.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */