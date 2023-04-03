/*     */ package org.jfree.util;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class HashNMap implements Serializable, Cloneable {
/*     */   private static final long serialVersionUID = -670924844536074826L;
/*     */   
/*     */   private static final class EmptyIterator implements Iterator {
/*     */     private EmptyIterator() {}
/*     */     
/*     */     public boolean hasNext() {
/*  91 */       return false;
/*     */     }
/*     */     
/*     */     public Object next() {
/* 101 */       throw new NoSuchElementException("This iterator is empty.");
/*     */     }
/*     */     
/*     */     public void remove() {
/* 119 */       throw new UnsupportedOperationException("This iterator is empty, no remove supported.");
/*     */     }
/*     */   }
/*     */   
/* 127 */   private static final Iterator EMPTY_ITERATOR = new EmptyIterator();
/*     */   
/*     */   private HashMap table;
/*     */   
/* 137 */   private static final Object[] EMPTY_ARRAY = new Object[0];
/*     */   
/*     */   public HashNMap() {
/* 143 */     this.table = new HashMap();
/*     */   }
/*     */   
/*     */   protected List createList() {
/* 152 */     return new ArrayList();
/*     */   }
/*     */   
/*     */   public boolean put(Object key, Object val) {
/* 164 */     List v = (List)this.table.get(key);
/* 165 */     if (v == null) {
/* 166 */       List newList = createList();
/* 167 */       newList.add(val);
/* 168 */       this.table.put(key, newList);
/* 169 */       return true;
/*     */     } 
/* 172 */     v.clear();
/* 173 */     return v.add(val);
/*     */   }
/*     */   
/*     */   public boolean add(Object key, Object val) {
/* 187 */     List v = (List)this.table.get(key);
/* 188 */     if (v == null) {
/* 189 */       put(key, val);
/* 190 */       return true;
/*     */     } 
/* 193 */     return v.add(val);
/*     */   }
/*     */   
/*     */   public Object getFirst(Object key) {
/* 205 */     return get(key, 0);
/*     */   }
/*     */   
/*     */   public Object get(Object key, int n) {
/* 218 */     List v = (List)this.table.get(key);
/* 219 */     if (v == null)
/* 220 */       return null; 
/* 222 */     return v.get(n);
/*     */   }
/*     */   
/*     */   public Iterator getAll(Object key) {
/* 232 */     List v = (List)this.table.get(key);
/* 233 */     if (v == null)
/* 234 */       return EMPTY_ITERATOR; 
/* 236 */     return v.iterator();
/*     */   }
/*     */   
/*     */   public Iterator keys() {
/* 245 */     return this.table.keySet().iterator();
/*     */   }
/*     */   
/*     */   public Set keySet() {
/* 254 */     return this.table.keySet();
/*     */   }
/*     */   
/*     */   public boolean remove(Object key, Object value) {
/* 266 */     List v = (List)this.table.get(key);
/* 267 */     if (v == null)
/* 268 */       return false; 
/* 271 */     if (!v.remove(value))
/* 272 */       return false; 
/* 274 */     if (v.size() == 0)
/* 275 */       this.table.remove(key); 
/* 277 */     return true;
/*     */   }
/*     */   
/*     */   public void removeAll(Object key) {
/* 286 */     this.table.remove(key);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 293 */     this.table.clear();
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 303 */     return this.table.containsKey(key);
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object value) {
/* 313 */     Iterator e = this.table.values().iterator();
/* 314 */     boolean found = false;
/* 315 */     while (e.hasNext() && !found) {
/* 316 */       List v = e.next();
/* 317 */       found = v.contains(value);
/*     */     } 
/* 319 */     return found;
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object key, Object value) {
/* 330 */     List v = (List)this.table.get(key);
/* 331 */     if (v == null)
/* 332 */       return false; 
/* 334 */     return v.contains(value);
/*     */   }
/*     */   
/*     */   public boolean contains(Object value) {
/* 344 */     if (containsKey(value))
/* 345 */       return true; 
/* 347 */     return containsValue(value);
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 357 */     HashNMap map = (HashNMap)super.clone();
/* 358 */     map.table = new HashMap();
/* 359 */     Iterator iterator = keys();
/* 360 */     while (iterator.hasNext()) {
/* 361 */       Object key = iterator.next();
/* 362 */       List list = (List)map.table.get(key);
/* 363 */       if (list != null)
/* 364 */         map.table.put(key, ObjectUtilities.clone(list)); 
/*     */     } 
/* 367 */     return map;
/*     */   }
/*     */   
/*     */   public Object[] toArray(Object key, Object[] data) {
/* 379 */     if (key == null)
/* 380 */       throw new NullPointerException("Key must not be null."); 
/* 382 */     List list = (List)this.table.get(key);
/* 383 */     if (list != null)
/* 384 */       return list.toArray(data); 
/* 386 */     if (data.length > 0)
/* 387 */       data[0] = null; 
/* 389 */     return data;
/*     */   }
/*     */   
/*     */   public Object[] toArray(Object key) {
/* 400 */     if (key == null)
/* 401 */       throw new NullPointerException("Key must not be null."); 
/* 403 */     List list = (List)this.table.get(key);
/* 404 */     if (list != null)
/* 405 */       return list.toArray(); 
/* 407 */     return EMPTY_ARRAY;
/*     */   }
/*     */   
/*     */   public int getValueCount(Object key) {
/* 418 */     if (key == null)
/* 419 */       throw new NullPointerException("Key must not be null."); 
/* 421 */     List list = (List)this.table.get(key);
/* 422 */     if (list != null)
/* 423 */       return list.size(); 
/* 425 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\util\HashNMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */