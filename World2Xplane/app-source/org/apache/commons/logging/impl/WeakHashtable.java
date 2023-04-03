/*     */ package org.apache.commons.logging.impl;
/*     */ 
/*     */ import java.lang.ref.ReferenceQueue;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.Collection;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashSet;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public final class WeakHashtable extends Hashtable {
/*     */   private static final int MAX_CHANGES_BEFORE_PURGE = 100;
/*     */   
/*     */   private static final int PARTIAL_PURGE_COUNT = 10;
/*     */   
/* 126 */   private ReferenceQueue queue = new ReferenceQueue();
/*     */   
/* 128 */   private int changeCount = 0;
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 142 */     Referenced referenced = new Referenced(key);
/* 143 */     return super.containsKey(referenced);
/*     */   }
/*     */   
/*     */   public Enumeration elements() {
/* 150 */     purge();
/* 151 */     return super.elements();
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/* 158 */     purge();
/* 159 */     Set referencedEntries = super.entrySet();
/* 160 */     Set unreferencedEntries = new HashSet();
/* 161 */     for (Iterator it = referencedEntries.iterator(); it.hasNext(); ) {
/* 162 */       Map.Entry entry = it.next();
/* 163 */       Referenced referencedKey = (Referenced)entry.getKey();
/* 164 */       Object key = referencedKey.getValue();
/* 165 */       Object value = entry.getValue();
/* 166 */       if (key != null) {
/* 167 */         Entry dereferencedEntry = new Entry(key, value);
/* 168 */         unreferencedEntries.add(dereferencedEntry);
/*     */       } 
/*     */     } 
/* 171 */     return unreferencedEntries;
/*     */   }
/*     */   
/*     */   public Object get(Object key) {
/* 179 */     Referenced referenceKey = new Referenced(key);
/* 180 */     return super.get(referenceKey);
/*     */   }
/*     */   
/*     */   public Enumeration keys() {
/* 187 */     purge();
/* 188 */     Enumeration enumer = super.keys();
/* 189 */     return new Enumeration(this, enumer) {
/*     */         private final Enumeration val$enumer;
/*     */         
/*     */         private final WeakHashtable this$0;
/*     */         
/*     */         public boolean hasMoreElements() {
/* 191 */           return this.val$enumer.hasMoreElements();
/*     */         }
/*     */         
/*     */         public Object nextElement() {
/* 194 */           WeakHashtable.Referenced nextReference = this.val$enumer.nextElement();
/* 195 */           return nextReference.getValue();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public Set keySet() {
/* 205 */     purge();
/* 206 */     Set referencedKeys = super.keySet();
/* 207 */     Set unreferencedKeys = new HashSet();
/* 208 */     for (Iterator it = referencedKeys.iterator(); it.hasNext(); ) {
/* 209 */       Referenced referenceKey = (Referenced)it.next();
/* 210 */       Object keyValue = referenceKey.getValue();
/* 211 */       if (keyValue != null)
/* 212 */         unreferencedKeys.add(keyValue); 
/*     */     } 
/* 215 */     return unreferencedKeys;
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 223 */     if (key == null)
/* 224 */       throw new NullPointerException("Null keys are not allowed"); 
/* 226 */     if (value == null)
/* 227 */       throw new NullPointerException("Null values are not allowed"); 
/* 232 */     if (this.changeCount++ > 100) {
/* 233 */       purge();
/* 234 */       this.changeCount = 0;
/* 237 */     } else if (this.changeCount % 10 == 0) {
/* 238 */       purgeOne();
/*     */     } 
/* 241 */     Referenced keyRef = new Referenced(key, this.queue);
/* 242 */     return super.put(keyRef, value);
/*     */   }
/*     */   
/*     */   public void putAll(Map t) {
/* 249 */     if (t != null) {
/* 250 */       Set entrySet = t.entrySet();
/* 251 */       for (Iterator it = entrySet.iterator(); it.hasNext(); ) {
/* 252 */         Map.Entry entry = it.next();
/* 253 */         put(entry.getKey(), entry.getValue());
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Collection values() {
/* 262 */     purge();
/* 263 */     return super.values();
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/* 272 */     if (this.changeCount++ > 100) {
/* 273 */       purge();
/* 274 */       this.changeCount = 0;
/* 277 */     } else if (this.changeCount % 10 == 0) {
/* 278 */       purgeOne();
/*     */     } 
/* 280 */     return super.remove(new Referenced(key));
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 287 */     purge();
/* 288 */     return super.isEmpty();
/*     */   }
/*     */   
/*     */   public int size() {
/* 295 */     purge();
/* 296 */     return super.size();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 303 */     purge();
/* 304 */     return super.toString();
/*     */   }
/*     */   
/*     */   protected void rehash() {
/* 312 */     purge();
/* 313 */     super.rehash();
/*     */   }
/*     */   
/*     */   private void purge() {
/* 321 */     synchronized (this.queue) {
/*     */       WeakKey key;
/* 323 */       while ((key = (WeakKey)this.queue.poll()) != null)
/* 324 */         super.remove(key.getReferenced()); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void purgeOne() {
/* 335 */     synchronized (this.queue) {
/* 336 */       WeakKey key = (WeakKey)this.queue.poll();
/* 337 */       if (key != null)
/* 338 */         super.remove(key.getReferenced()); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static final class Entry implements Map.Entry {
/*     */     private final Object key;
/*     */     
/*     */     private final Object value;
/*     */     
/*     */     private Entry(Object key, Object value) {
/* 350 */       this.key = key;
/* 351 */       this.value = value;
/*     */     }
/*     */     
/*     */     public boolean equals(Object o) {
/* 355 */       boolean result = false;
/* 356 */       if (o != null && o instanceof Map.Entry) {
/* 357 */         Map.Entry entry = (Map.Entry)o;
/* 358 */         result = (((getKey() == null) ? (entry.getKey() == null) : getKey().equals(entry.getKey())) && ((getValue() == null) ? (entry.getValue() == null) : getValue().equals(entry.getValue())));
/*     */       } 
/* 366 */       return result;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 371 */       return ((getKey() == null) ? 0 : getKey().hashCode()) ^ ((getValue() == null) ? 0 : getValue().hashCode());
/*     */     }
/*     */     
/*     */     public Object setValue(Object value) {
/* 376 */       throw new UnsupportedOperationException("Entry.setValue is not supported.");
/*     */     }
/*     */     
/*     */     public Object getValue() {
/* 380 */       return this.value;
/*     */     }
/*     */     
/*     */     public Object getKey() {
/* 384 */       return this.key;
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class Referenced {
/*     */     private final WeakReference reference;
/*     */     
/*     */     private final int hashCode;
/*     */     
/*     */     private Referenced(Object referant) {
/* 400 */       this.reference = new WeakReference(referant);
/* 403 */       this.hashCode = referant.hashCode();
/*     */     }
/*     */     
/*     */     private Referenced(Object key, ReferenceQueue queue) {
/* 411 */       this.reference = new WeakHashtable.WeakKey(key, queue, this);
/* 414 */       this.hashCode = key.hashCode();
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 419 */       return this.hashCode;
/*     */     }
/*     */     
/*     */     private Object getValue() {
/* 423 */       return this.reference.get();
/*     */     }
/*     */     
/*     */     public boolean equals(Object o) {
/* 427 */       boolean result = false;
/* 428 */       if (o instanceof Referenced) {
/* 429 */         Referenced otherKey = (Referenced)o;
/* 430 */         Object thisKeyValue = getValue();
/* 431 */         Object otherKeyValue = otherKey.getValue();
/* 432 */         if (thisKeyValue == null) {
/* 433 */           result = (otherKeyValue == null);
/* 441 */           if (result == true)
/* 442 */             result = (hashCode() == otherKey.hashCode()); 
/*     */         } else {
/* 451 */           result = thisKeyValue.equals(otherKeyValue);
/*     */         } 
/*     */       } 
/* 454 */       return result;
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class WeakKey extends WeakReference {
/*     */     private final WeakHashtable.Referenced referenced;
/*     */     
/*     */     private WeakKey(Object key, ReferenceQueue queue, WeakHashtable.Referenced referenced) {
/* 470 */       super((T)key, queue);
/* 471 */       this.referenced = referenced;
/*     */     }
/*     */     
/*     */     private WeakHashtable.Referenced getReferenced() {
/* 475 */       return this.referenced;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\logging\impl\WeakHashtable.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */