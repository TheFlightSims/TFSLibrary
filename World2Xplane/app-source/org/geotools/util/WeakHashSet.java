/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.LogRecord;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.resources.XArray;
/*     */ import org.geotools.util.logging.Logging;
/*     */ 
/*     */ public class WeakHashSet<E> extends AbstractSet<E> implements CheckedCollection<E> {
/*     */   private static final int MIN_CAPACITY = 7;
/*     */   
/*     */   private static final float LOAD_FACTOR = 0.75F;
/*     */   
/*     */   private Entry[] table;
/*     */   
/*     */   private final Class<E> type;
/*     */   
/*     */   private int count;
/*     */   
/*     */   private int threshold;
/*     */   
/*     */   private long lastRehashTime;
/*     */   
/*     */   private static final long HOLD_TIME = 20000L;
/*     */   
/*     */   static final int REMOVE = -1;
/*     */   
/*     */   static final int GET = 0;
/*     */   
/*     */   static final int ADD = 1;
/*     */   
/*     */   static final int INTERN = 2;
/*     */   
/*     */   private final class Entry extends WeakReference<E> {
/*     */     Entry next;
/*     */     
/*     */     int index;
/*     */     
/*     */     Entry(E obj, Entry next, int index) {
/*  87 */       super(obj, WeakCollectionCleaner.DEFAULT.referenceQueue);
/*  88 */       this.next = next;
/*  89 */       this.index = index;
/*     */     }
/*     */     
/*     */     public void clear() {
/*  97 */       super.clear();
/*  98 */       WeakHashSet.this.removeEntry(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public WeakHashSet() {
/* 145 */     this((Class)Object.class);
/*     */   }
/*     */   
/*     */   public WeakHashSet(Class<E> type) {
/* 156 */     this.type = type;
/* 157 */     newEntryTable(7);
/* 158 */     this.threshold = Math.round(this.table.length * 0.75F);
/* 159 */     this.lastRehashTime = System.currentTimeMillis();
/*     */   }
/*     */   
/*     */   private void newEntryTable(int size) {
/* 169 */     this.table = (Entry[])Array.newInstance(Entry.class, size);
/*     */   }
/*     */   
/*     */   public Class<E> getElementType() {
/* 178 */     return this.type;
/*     */   }
/*     */   
/*     */   private synchronized void removeEntry(Entry toRemove) {
/* 186 */     assert valid() : this.count;
/* 187 */     int i = toRemove.index;
/* 190 */     if (i < this.table.length) {
/* 191 */       Entry prev = null;
/* 192 */       Entry e = this.table[i];
/* 193 */       while (e != null) {
/* 194 */         if (e == toRemove) {
/* 195 */           if (prev != null) {
/* 196 */             prev.next = e.next;
/*     */           } else {
/* 198 */             this.table[i] = e.next;
/*     */           } 
/* 200 */           this.count--;
/* 201 */           assert valid();
/* 205 */           if (this.count <= this.threshold / 4)
/* 206 */             rehash(false); 
/*     */           return;
/*     */         } 
/* 212 */         prev = e;
/* 213 */         e = e.next;
/*     */       } 
/*     */     } 
/* 216 */     assert valid();
/*     */   }
/*     */   
/*     */   private void rehash(boolean augmentation) {
/* 232 */     assert Thread.holdsLock(this);
/* 233 */     assert valid();
/* 234 */     long currentTime = System.currentTimeMillis();
/* 235 */     int capacity = Math.max(Math.round(this.count / 0.375F), this.count + 7);
/* 236 */     if (augmentation ? (capacity <= this.table.length) : (capacity >= this.table.length || currentTime - this.lastRehashTime < 20000L))
/*     */       return; 
/* 241 */     this.lastRehashTime = currentTime;
/* 242 */     Entry[] oldTable = this.table;
/* 243 */     newEntryTable(capacity);
/* 244 */     this.threshold = Math.round(capacity * 0.75F);
/* 245 */     for (int i = 0; i < oldTable.length; i++) {
/* 246 */       for (Entry old = oldTable[i]; old != null; ) {
/* 247 */         Entry e = old;
/* 248 */         old = old.next;
/* 249 */         E obj_e = e.get();
/* 250 */         if (obj_e != null) {
/* 251 */           int index = (obj_e.hashCode() & Integer.MAX_VALUE) % this.table.length;
/* 252 */           e.index = index;
/* 253 */           e.next = this.table[index];
/* 254 */           this.table[index] = e;
/*     */           continue;
/*     */         } 
/* 256 */         this.count--;
/*     */       } 
/*     */     } 
/* 260 */     Logger logger = Logging.getLogger("org.geotools.util");
/* 261 */     Level level = Level.FINEST;
/* 262 */     if (logger.isLoggable(level)) {
/* 263 */       LogRecord record = new LogRecord(level, "Rehash from " + oldTable.length + " to " + this.table.length);
/* 265 */       record.setSourceMethodName(augmentation ? "unique" : "remove");
/* 266 */       record.setSourceClassName(WeakHashSet.class.getName());
/* 267 */       record.setLoggerName(logger.getName());
/* 268 */       logger.log(record);
/*     */     } 
/* 270 */     assert valid();
/*     */   }
/*     */   
/*     */   private boolean valid() {
/* 282 */     int n = 0;
/* 283 */     for (int i = 0; i < this.table.length; i++) {
/* 284 */       for (Entry e = this.table[i]; e != null; e = e.next)
/* 285 */         n++; 
/*     */     } 
/* 288 */     if (n != this.count) {
/* 289 */       this.count = n;
/* 290 */       return false;
/*     */     } 
/* 292 */     return true;
/*     */   }
/*     */   
/*     */   public synchronized int size() {
/* 300 */     assert valid();
/* 301 */     return this.count;
/*     */   }
/*     */   
/*     */   public synchronized boolean contains(Object obj) {
/* 312 */     return (obj != null && intern(this.type.cast(obj), 0) != null);
/*     */   }
/*     */   
/*     */   public synchronized boolean remove(Object obj) {
/* 323 */     return (intern(this.type.cast(obj), -1) != null);
/*     */   }
/*     */   
/*     */   public synchronized boolean add(E obj) {
/* 336 */     return (intern(obj, 1) == null);
/*     */   }
/*     */   
/*     */   final <T extends E> T intern(T obj, int operation) {
/* 364 */     assert Thread.holdsLock(this);
/* 365 */     assert WeakCollectionCleaner.DEFAULT.isAlive();
/* 366 */     assert valid() : this.count;
/* 367 */     if (obj != null) {
/* 368 */       assert obj.equals(obj) : obj;
/* 373 */       int hash = obj.hashCode() & Integer.MAX_VALUE;
/* 374 */       int index = hash % this.table.length;
/* 375 */       for (Entry e = this.table[index]; e != null; e = e.next) {
/* 376 */         E candidate = e.get();
/* 377 */         if (candidate != null && 
/* 378 */           candidate.equals(obj)) {
/* 379 */           if (operation == -1)
/* 380 */             e.clear(); 
/* 382 */           assert candidate.getClass().equals(obj.getClass()) : candidate;
/* 384 */           return (T)candidate;
/*     */         } 
/*     */       } 
/* 391 */       if (operation >= 1) {
/* 396 */         if (this.count >= this.threshold) {
/* 397 */           rehash(true);
/* 398 */           index = hash % this.table.length;
/*     */         } 
/* 400 */         this.table[index] = new Entry((E)obj, this.table[index], index);
/* 401 */         this.count++;
/*     */       } 
/*     */     } 
/* 404 */     assert valid();
/* 405 */     return (operation == 2) ? obj : null;
/*     */   }
/*     */   
/*     */   public synchronized void clear() {
/* 413 */     Arrays.fill((Object[])this.table, (Object)null);
/* 414 */     this.count = 0;
/*     */   }
/*     */   
/*     */   public synchronized E[] toArray() {
/* 424 */     assert valid();
/* 426 */     E[] elements = (E[])Array.newInstance(this.type, this.count);
/* 427 */     int index = 0;
/* 428 */     for (int i = 0; i < this.table.length; i++) {
/* 429 */       for (Entry el = this.table[i]; el != null; el = el.next) {
/* 430 */         elements[index] = el.get();
/* 430 */         if (el.get() != null)
/* 431 */           index++; 
/*     */       } 
/*     */     } 
/* 435 */     return (E[])XArray.resize((Object[])elements, index);
/*     */   }
/*     */   
/*     */   public Iterator<E> iterator() {
/* 445 */     return Arrays.<E>asList(toArray()).iterator();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\WeakHashSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */