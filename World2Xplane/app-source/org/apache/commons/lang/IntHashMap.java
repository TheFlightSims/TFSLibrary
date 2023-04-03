/*     */ package org.apache.commons.lang;
/*     */ 
/*     */ class IntHashMap {
/*     */   private transient Entry[] table;
/*     */   
/*     */   private transient int count;
/*     */   
/*     */   private int threshold;
/*     */   
/*     */   private final float loadFactor;
/*     */   
/*     */   private static class Entry {
/*     */     final int hash;
/*     */     
/*     */     final int key;
/*     */     
/*     */     Object value;
/*     */     
/*     */     Entry next;
/*     */     
/*     */     protected Entry(int hash, int key, Object value, Entry next) {
/*  84 */       this.hash = hash;
/*  85 */       this.key = key;
/*  86 */       this.value = value;
/*  87 */       this.next = next;
/*     */     }
/*     */   }
/*     */   
/*     */   public IntHashMap() {
/*  96 */     this(20, 0.75F);
/*     */   }
/*     */   
/*     */   public IntHashMap(int initialCapacity) {
/* 108 */     this(initialCapacity, 0.75F);
/*     */   }
/*     */   
/*     */   public IntHashMap(int initialCapacity, float loadFactor) {
/* 122 */     if (initialCapacity < 0)
/* 123 */       throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity); 
/* 125 */     if (loadFactor <= 0.0F)
/* 126 */       throw new IllegalArgumentException("Illegal Load: " + loadFactor); 
/* 128 */     if (initialCapacity == 0)
/* 129 */       initialCapacity = 1; 
/* 132 */     this.loadFactor = loadFactor;
/* 133 */     this.table = new Entry[initialCapacity];
/* 134 */     this.threshold = (int)(initialCapacity * loadFactor);
/*     */   }
/*     */   
/*     */   public int size() {
/* 143 */     return this.count;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 153 */     return (this.count == 0);
/*     */   }
/*     */   
/*     */   public boolean contains(Object value) {
/* 175 */     if (value == null)
/* 176 */       throw new NullPointerException(); 
/* 179 */     Entry[] tab = this.table;
/* 180 */     for (int i = tab.length; i-- > 0;) {
/* 181 */       for (Entry e = tab[i]; e != null; e = e.next) {
/* 182 */         if (e.value.equals(value))
/* 183 */           return true; 
/*     */       } 
/*     */     } 
/* 187 */     return false;
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object value) {
/* 203 */     return contains(value);
/*     */   }
/*     */   
/*     */   public boolean containsKey(int key) {
/* 216 */     Entry[] tab = this.table;
/* 217 */     int hash = key;
/* 218 */     int index = (hash & Integer.MAX_VALUE) % tab.length;
/* 219 */     for (Entry e = tab[index]; e != null; e = e.next) {
/* 220 */       if (e.hash == hash)
/* 221 */         return true; 
/*     */     } 
/* 224 */     return false;
/*     */   }
/*     */   
/*     */   public Object get(int key) {
/* 237 */     Entry[] tab = this.table;
/* 238 */     int hash = key;
/* 239 */     int index = (hash & Integer.MAX_VALUE) % tab.length;
/* 240 */     for (Entry e = tab[index]; e != null; e = e.next) {
/* 241 */       if (e.hash == hash)
/* 242 */         return e.value; 
/*     */     } 
/* 245 */     return null;
/*     */   }
/*     */   
/*     */   protected void rehash() {
/* 258 */     int oldCapacity = this.table.length;
/* 259 */     Entry[] oldMap = this.table;
/* 261 */     int newCapacity = oldCapacity * 2 + 1;
/* 262 */     Entry[] newMap = new Entry[newCapacity];
/* 264 */     this.threshold = (int)(newCapacity * this.loadFactor);
/* 265 */     this.table = newMap;
/* 267 */     for (int i = oldCapacity; i-- > 0;) {
/* 268 */       for (Entry old = oldMap[i]; old != null; ) {
/* 269 */         Entry e = old;
/* 270 */         old = old.next;
/* 272 */         int index = (e.hash & Integer.MAX_VALUE) % newCapacity;
/* 273 */         e.next = newMap[index];
/* 274 */         newMap[index] = e;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object put(int key, Object value) {
/* 296 */     Entry[] tab = this.table;
/* 297 */     int hash = key;
/* 298 */     int index = (hash & Integer.MAX_VALUE) % tab.length;
/*     */     Entry e;
/* 299 */     for (e = tab[index]; e != null; e = e.next) {
/* 300 */       if (e.hash == hash) {
/* 301 */         Object old = e.value;
/* 302 */         e.value = value;
/* 303 */         return old;
/*     */       } 
/*     */     } 
/* 307 */     if (this.count >= this.threshold) {
/* 309 */       rehash();
/* 311 */       tab = this.table;
/* 312 */       index = (hash & Integer.MAX_VALUE) % tab.length;
/*     */     } 
/* 316 */     e = new Entry(hash, key, value, tab[index]);
/* 317 */     tab[index] = e;
/* 318 */     this.count++;
/* 319 */     return null;
/*     */   }
/*     */   
/*     */   public Object remove(int key) {
/* 334 */     Entry[] tab = this.table;
/* 335 */     int hash = key;
/* 336 */     int index = (hash & Integer.MAX_VALUE) % tab.length;
/* 337 */     for (Entry e = tab[index], prev = null; e != null; prev = e, e = e.next) {
/* 338 */       if (e.hash == hash) {
/* 339 */         if (prev != null) {
/* 340 */           prev.next = e.next;
/*     */         } else {
/* 342 */           tab[index] = e.next;
/*     */         } 
/* 344 */         this.count--;
/* 345 */         Object oldValue = e.value;
/* 346 */         e.value = null;
/* 347 */         return oldValue;
/*     */       } 
/*     */     } 
/* 350 */     return null;
/*     */   }
/*     */   
/*     */   public synchronized void clear() {
/* 357 */     Entry[] tab = this.table;
/* 358 */     for (int index = tab.length; --index >= 0;)
/* 359 */       tab[index] = null; 
/* 361 */     this.count = 0;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\IntHashMap.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */