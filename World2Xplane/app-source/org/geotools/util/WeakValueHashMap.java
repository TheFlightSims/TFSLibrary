/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.AbstractMap;
/*     */ import java.util.Arrays;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.LogRecord;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.util.logging.Logging;
/*     */ 
/*     */ public class WeakValueHashMap<K, V> extends AbstractMap<K, V> {
/*     */   private static final int MIN_CAPACITY = 7;
/*     */   
/*     */   private static final float LOAD_FACTOR = 0.75F;
/*     */   
/*     */   private Entry[] table;
/*     */   
/*     */   private int count;
/*     */   
/*     */   private int threshold;
/*     */   
/*     */   private long lastRehashTime;
/*     */   
/*     */   private static final long HOLD_TIME = 20000L;
/*     */   
/*     */   private final class Entry extends WeakReference<V> implements Map.Entry<K, V> {
/*     */     K key;
/*     */     
/*     */     Entry next;
/*     */     
/*     */     int index;
/*     */     
/*     */     Entry(K key, V value, Entry next, int index) {
/*  90 */       super(value, WeakCollectionCleaner.DEFAULT.referenceQueue);
/*  91 */       this.key = key;
/*  92 */       this.next = next;
/*  93 */       this.index = index;
/*     */     }
/*     */     
/*     */     public K getKey() {
/* 100 */       return this.key;
/*     */     }
/*     */     
/*     */     public V getValue() {
/* 107 */       return get();
/*     */     }
/*     */     
/*     */     public V setValue(V value) {
/* 114 */       if (value != null)
/* 115 */         throw new UnsupportedOperationException(); 
/* 117 */       V old = get();
/* 118 */       clear();
/* 119 */       return old;
/*     */     }
/*     */     
/*     */     public void clear() {
/* 128 */       super.clear();
/* 129 */       WeakValueHashMap.this.removeEntry(this);
/* 130 */       this.key = null;
/*     */     }
/*     */     
/*     */     public boolean equals(Object other) {
/* 138 */       if (other instanceof Map.Entry) {
/* 139 */         Map.Entry that = (Map.Entry)other;
/* 140 */         return (Utilities.equals(getKey(), that.getKey()) && Utilities.equals(getValue(), that.getValue()));
/*     */       } 
/* 143 */       return false;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 151 */       Object val = get();
/* 152 */       return ((this.key == null) ? 0 : this.key.hashCode()) ^ ((val == null) ? 0 : val.hashCode());
/*     */     }
/*     */   }
/*     */   
/*     */   public WeakValueHashMap() {
/* 192 */     this(7);
/*     */   }
/*     */   
/*     */   public WeakValueHashMap(int initialSize) {
/* 201 */     newEntryTable(initialSize);
/* 202 */     this.threshold = Math.round(this.table.length * 0.75F);
/* 203 */     this.lastRehashTime = System.currentTimeMillis();
/*     */   }
/*     */   
/*     */   private void newEntryTable(int size) {
/* 213 */     this.table = (Entry[])Array.newInstance(Entry.class, size);
/*     */   }
/*     */   
/*     */   public WeakValueHashMap(Map<K, V> map) {
/* 222 */     this(Math.round(map.size() / 0.75F) + 1);
/* 223 */     putAll(map);
/*     */   }
/*     */   
/*     */   private synchronized void removeEntry(Entry toRemove) {
/* 232 */     assert valid() : this.count;
/* 233 */     int i = toRemove.index;
/* 236 */     if (i < this.table.length) {
/* 237 */       Entry prev = null;
/* 238 */       Entry e = this.table[i];
/* 239 */       while (e != null) {
/* 240 */         if (e == toRemove) {
/* 241 */           if (prev != null) {
/* 242 */             prev.next = e.next;
/*     */           } else {
/* 244 */             this.table[i] = e.next;
/*     */           } 
/* 246 */           this.count--;
/* 247 */           assert valid();
/* 251 */           if (this.count <= this.threshold / 4)
/* 252 */             rehash(false); 
/*     */           return;
/*     */         } 
/* 258 */         prev = e;
/* 259 */         e = e.next;
/*     */       } 
/*     */     } 
/* 262 */     assert valid();
/*     */   }
/*     */   
/*     */   private void rehash(boolean augmentation) {
/* 278 */     assert Thread.holdsLock(this);
/* 279 */     assert valid();
/* 280 */     long currentTime = System.currentTimeMillis();
/* 281 */     int capacity = Math.max(Math.round(this.count / 0.375F), this.count + 7);
/* 282 */     if (augmentation ? (capacity <= this.table.length) : (capacity >= this.table.length || currentTime - this.lastRehashTime < 20000L))
/*     */       return; 
/* 287 */     this.lastRehashTime = currentTime;
/* 288 */     Entry[] oldTable = this.table;
/* 289 */     newEntryTable(capacity);
/* 290 */     this.threshold = Math.round(capacity * 0.75F);
/* 291 */     for (int i = 0; i < oldTable.length; i++) {
/* 292 */       for (Entry old = oldTable[i]; old != null; ) {
/* 293 */         Entry e = old;
/* 294 */         old = old.next;
/* 295 */         Object key = e.key;
/* 296 */         if (key != null) {
/* 297 */           int index = (key.hashCode() & Integer.MAX_VALUE) % this.table.length;
/* 298 */           e.index = index;
/* 299 */           e.next = this.table[index];
/* 300 */           this.table[index] = e;
/*     */           continue;
/*     */         } 
/* 302 */         this.count--;
/*     */       } 
/*     */     } 
/* 306 */     Logger logger = Logging.getLogger("org.geotools.util");
/* 307 */     Level level = Level.FINEST;
/* 308 */     if (logger.isLoggable(level)) {
/* 309 */       LogRecord record = new LogRecord(level, "Rehash from " + oldTable.length + " to " + this.table.length);
/* 311 */       record.setSourceMethodName(augmentation ? "unique" : "remove");
/* 312 */       record.setSourceClassName(WeakValueHashMap.class.getName());
/* 313 */       record.setLoggerName(logger.getName());
/* 314 */       logger.log(record);
/*     */     } 
/* 316 */     assert valid();
/*     */   }
/*     */   
/*     */   private boolean valid() {
/* 328 */     int n = 0;
/* 329 */     for (int i = 0; i < this.table.length; i++) {
/* 330 */       for (Entry e = this.table[i]; e != null; e = e.next)
/* 331 */         n++; 
/*     */     } 
/* 334 */     if (n != this.count) {
/* 335 */       this.count = n;
/* 336 */       return false;
/*     */     } 
/* 338 */     return true;
/*     */   }
/*     */   
/*     */   public synchronized int size() {
/* 347 */     assert valid();
/* 348 */     return this.count;
/*     */   }
/*     */   
/*     */   public synchronized boolean containsValue(Object value) {
/* 359 */     return super.containsValue(value);
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 371 */     return (get(key) != null);
/*     */   }
/*     */   
/*     */   public synchronized V get(Object key) {
/* 384 */     assert WeakCollectionCleaner.DEFAULT.isAlive();
/* 385 */     assert valid() : this.count;
/* 386 */     int index = (key.hashCode() & Integer.MAX_VALUE) % this.table.length;
/* 387 */     for (Entry e = this.table[index]; e != null; e = e.next) {
/* 388 */       if (key.equals(e.key))
/* 389 */         return e.get(); 
/*     */     } 
/* 392 */     return null;
/*     */   }
/*     */   
/*     */   private synchronized V intern(K key, V value) {
/* 399 */     assert WeakCollectionCleaner.DEFAULT.isAlive();
/* 400 */     assert valid() : this.count;
/* 405 */     V oldValue = null;
/* 406 */     int hash = key.hashCode() & Integer.MAX_VALUE;
/* 407 */     int index = hash % this.table.length;
/* 408 */     for (Entry e = this.table[index]; e != null; e = e.next) {
/* 409 */       if (key.equals(e.key)) {
/* 410 */         oldValue = e.get();
/* 411 */         e.clear();
/*     */       } 
/*     */     } 
/* 414 */     if (value != null) {
/* 415 */       if (this.count >= this.threshold) {
/* 416 */         rehash(true);
/* 417 */         index = hash % this.table.length;
/*     */       } 
/* 419 */       this.table[index] = new Entry(key, value, this.table[index], index);
/* 420 */       this.count++;
/*     */     } 
/* 422 */     assert valid();
/* 423 */     return oldValue;
/*     */   }
/*     */   
/*     */   public V put(K key, V value) {
/* 439 */     if (value == null)
/* 440 */       throw new NullPointerException("Null value not allowed"); 
/* 443 */     return intern(key, value);
/*     */   }
/*     */   
/*     */   public V remove(Object key) {
/* 456 */     return intern((K)key, (V)null);
/*     */   }
/*     */   
/*     */   public synchronized void clear() {
/* 464 */     Arrays.fill((Object[])this.table, (Object)null);
/* 465 */     this.count = 0;
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<K, V>> entrySet() {
/* 477 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\WeakValueHashMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */