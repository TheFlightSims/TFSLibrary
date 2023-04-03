/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections.BoundedMap;
/*     */ 
/*     */ public class LRUMap extends AbstractLinkedMap implements BoundedMap, Serializable, Cloneable {
/*     */   private static final long serialVersionUID = -612114643488955218L;
/*     */   
/*     */   protected static final int DEFAULT_MAX_SIZE = 100;
/*     */   
/*     */   private transient int maxSize;
/*     */   
/*     */   private boolean scanUntilRemovable;
/*     */   
/*     */   public LRUMap() {
/*  76 */     this(100, 0.75F, false);
/*     */   }
/*     */   
/*     */   public LRUMap(int maxSize) {
/*  86 */     this(maxSize, 0.75F);
/*     */   }
/*     */   
/*     */   public LRUMap(int maxSize, boolean scanUntilRemovable) {
/*  98 */     this(maxSize, 0.75F, scanUntilRemovable);
/*     */   }
/*     */   
/*     */   public LRUMap(int maxSize, float loadFactor) {
/* 111 */     this(maxSize, loadFactor, false);
/*     */   }
/*     */   
/*     */   public LRUMap(int maxSize, float loadFactor, boolean scanUntilRemovable) {
/* 126 */     super((maxSize < 1) ? 16 : maxSize, loadFactor);
/* 127 */     if (maxSize < 1)
/* 128 */       throw new IllegalArgumentException("LRUMap max size must be greater than 0"); 
/* 130 */     this.maxSize = maxSize;
/* 131 */     this.scanUntilRemovable = scanUntilRemovable;
/*     */   }
/*     */   
/*     */   public LRUMap(Map map) {
/* 144 */     this(map, false);
/*     */   }
/*     */   
/*     */   public LRUMap(Map map, boolean scanUntilRemovable) {
/* 159 */     this(map.size(), 0.75F, scanUntilRemovable);
/* 160 */     putAll(map);
/*     */   }
/*     */   
/*     */   public Object get(Object key) {
/* 174 */     AbstractLinkedMap.LinkEntry entry = (AbstractLinkedMap.LinkEntry)getEntry(key);
/* 175 */     if (entry == null)
/* 176 */       return null; 
/* 178 */     moveToMRU(entry);
/* 179 */     return entry.getValue();
/*     */   }
/*     */   
/*     */   protected void moveToMRU(AbstractLinkedMap.LinkEntry entry) {
/* 191 */     if (entry.after != this.header) {
/* 192 */       this.modCount++;
/* 194 */       entry.before.after = entry.after;
/* 195 */       entry.after.before = entry.before;
/* 197 */       entry.after = this.header;
/* 198 */       entry.before = this.header.before;
/* 199 */       this.header.before.after = entry;
/* 200 */       this.header.before = entry;
/* 201 */     } else if (entry == this.header) {
/* 202 */       throw new IllegalStateException("Can't move header to MRU (please report this to commons-dev@jakarta.apache.org)");
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void updateEntry(AbstractHashedMap.HashEntry entry, Object newValue) {
/* 217 */     moveToMRU((AbstractLinkedMap.LinkEntry)entry);
/* 218 */     entry.setValue(newValue);
/*     */   }
/*     */   
/*     */   protected void addMapping(int hashIndex, int hashCode, Object key, Object value) {
/* 237 */     if (isFull()) {
/* 238 */       AbstractLinkedMap.LinkEntry reuse = this.header.after;
/* 239 */       boolean removeLRUEntry = false;
/* 240 */       if (this.scanUntilRemovable) {
/* 241 */         while (reuse != this.header && reuse != null) {
/* 242 */           if (removeLRU(reuse)) {
/* 243 */             removeLRUEntry = true;
/*     */             break;
/*     */           } 
/* 246 */           reuse = reuse.after;
/*     */         } 
/* 248 */         if (reuse == null)
/* 249 */           throw new IllegalStateException("Entry.after=null, header.after" + this.header.after + " header.before" + this.header.before + " key=" + key + " value=" + value + " size=" + this.size + " maxSize=" + this.maxSize + " Please check that your keys are immutable, and that you have used synchronization properly." + " If so, then please report this to commons-dev@jakarta.apache.org as a bug."); 
/*     */       } else {
/* 256 */         removeLRUEntry = removeLRU(reuse);
/*     */       } 
/* 259 */       if (removeLRUEntry) {
/* 260 */         if (reuse == null)
/* 261 */           throw new IllegalStateException("reuse=null, header.after=" + this.header.after + " header.before" + this.header.before + " key=" + key + " value=" + value + " size=" + this.size + " maxSize=" + this.maxSize + " Please check that your keys are immutable, and that you have used synchronization properly." + " If so, then please report this to commons-dev@jakarta.apache.org as a bug."); 
/* 267 */         reuseMapping(reuse, hashIndex, hashCode, key, value);
/*     */       } else {
/* 269 */         super.addMapping(hashIndex, hashCode, key, value);
/*     */       } 
/*     */     } else {
/* 272 */       super.addMapping(hashIndex, hashCode, key, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void reuseMapping(AbstractLinkedMap.LinkEntry entry, int hashIndex, int hashCode, Object key, Object value) {
/*     */     try {
/* 292 */       int removeIndex = hashIndex(entry.hashCode, this.data.length);
/* 293 */       AbstractHashedMap.HashEntry[] tmp = this.data;
/* 294 */       AbstractHashedMap.HashEntry loop = tmp[removeIndex];
/* 295 */       AbstractHashedMap.HashEntry previous = null;
/* 296 */       while (loop != entry && loop != null) {
/* 297 */         previous = loop;
/* 298 */         loop = loop.next;
/*     */       } 
/* 300 */       if (loop == null)
/* 301 */         throw new IllegalStateException("Entry.next=null, data[removeIndex]=" + this.data[removeIndex] + " previous=" + previous + " key=" + key + " value=" + value + " size=" + this.size + " maxSize=" + this.maxSize + " Please check that your keys are immutable, and that you have used synchronization properly." + " If so, then please report this to commons-dev@jakarta.apache.org as a bug."); 
/* 309 */       this.modCount++;
/* 310 */       removeEntry(entry, removeIndex, previous);
/* 311 */       reuseEntry(entry, hashIndex, hashCode, key, value);
/* 312 */       addEntry(entry, hashIndex);
/* 313 */     } catch (NullPointerException ex) {
/* 314 */       throw new IllegalStateException("NPE, entry=" + entry + " entryIsHeader=" + ((entry == this.header) ? 1 : 0) + " key=" + key + " value=" + value + " size=" + this.size + " maxSize=" + this.maxSize + " Please check that your keys are immutable, and that you have used synchronization properly." + " If so, then please report this to commons-dev@jakarta.apache.org as a bug.");
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean removeLRU(AbstractLinkedMap.LinkEntry entry) {
/* 356 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isFull() {
/* 366 */     return (this.size >= this.maxSize);
/*     */   }
/*     */   
/*     */   public int maxSize() {
/* 375 */     return this.maxSize;
/*     */   }
/*     */   
/*     */   public boolean isScanUntilRemovable() {
/* 386 */     return this.scanUntilRemovable;
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 396 */     return super.clone();
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 403 */     out.defaultWriteObject();
/* 404 */     doWriteObject(out);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 411 */     in.defaultReadObject();
/* 412 */     doReadObject(in);
/*     */   }
/*     */   
/*     */   protected void doWriteObject(ObjectOutputStream out) throws IOException {
/* 419 */     out.writeInt(this.maxSize);
/* 420 */     super.doWriteObject(out);
/*     */   }
/*     */   
/*     */   protected void doReadObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 427 */     this.maxSize = in.readInt();
/* 428 */     super.doReadObject(in);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\map\LRUMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */