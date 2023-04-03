/*     */ package gnu.trove.impl.hash;
/*     */ 
/*     */ import gnu.trove.impl.HashFunctions;
/*     */ import gnu.trove.impl.PrimeFinder;
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ 
/*     */ public abstract class THash implements Externalizable {
/*     */   static final long serialVersionUID = -1792948471915530295L;
/*     */   
/*     */   protected static final float DEFAULT_LOAD_FACTOR = 0.5F;
/*     */   
/*     */   protected static final int DEFAULT_CAPACITY = 10;
/*     */   
/*     */   protected transient int _size;
/*     */   
/*     */   protected transient int _free;
/*     */   
/*     */   protected float _loadFactor;
/*     */   
/*     */   protected int _maxSize;
/*     */   
/*     */   protected int _autoCompactRemovesRemaining;
/*     */   
/*     */   protected float _autoCompactionFactor;
/*     */   
/*     */   protected transient boolean _autoCompactTemporaryDisable = false;
/*     */   
/*     */   public THash() {
/* 104 */     this(10, 0.5F);
/*     */   }
/*     */   
/*     */   public THash(int initialCapacity) {
/* 116 */     this(initialCapacity, 0.5F);
/*     */   }
/*     */   
/*     */   public THash(int initialCapacity, float loadFactor) {
/* 131 */     this._loadFactor = loadFactor;
/* 135 */     this._autoCompactionFactor = loadFactor;
/* 137 */     setUp(HashFunctions.fastCeil(initialCapacity / loadFactor));
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 147 */     return (0 == this._size);
/*     */   }
/*     */   
/*     */   public int size() {
/* 157 */     return this._size;
/*     */   }
/*     */   
/*     */   public abstract int capacity();
/*     */   
/*     */   public void ensureCapacity(int desiredCapacity) {
/* 174 */     if (desiredCapacity > this._maxSize - size()) {
/* 175 */       rehash(PrimeFinder.nextPrime(Math.max(size() + 1, HashFunctions.fastCeil((desiredCapacity + size()) / this._loadFactor) + 1)));
/* 177 */       computeMaxSize(capacity());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void compact() {
/* 201 */     rehash(PrimeFinder.nextPrime(Math.max(this._size + 1, HashFunctions.fastCeil(size() / this._loadFactor) + 1)));
/* 203 */     computeMaxSize(capacity());
/* 206 */     if (this._autoCompactionFactor != 0.0F)
/* 207 */       computeNextAutoCompactionAmount(size()); 
/*     */   }
/*     */   
/*     */   public void setAutoCompactionFactor(float factor) {
/* 224 */     if (factor < 0.0F)
/* 225 */       throw new IllegalArgumentException("Factor must be >= 0: " + factor); 
/* 228 */     this._autoCompactionFactor = factor;
/*     */   }
/*     */   
/*     */   public float getAutoCompactionFactor() {
/* 238 */     return this._autoCompactionFactor;
/*     */   }
/*     */   
/*     */   public final void trimToSize() {
/* 252 */     compact();
/*     */   }
/*     */   
/*     */   protected void removeAt(int index) {
/* 263 */     this._size--;
/* 266 */     if (this._autoCompactionFactor != 0.0F) {
/* 267 */       this._autoCompactRemovesRemaining--;
/* 269 */       if (!this._autoCompactTemporaryDisable && this._autoCompactRemovesRemaining <= 0)
/* 272 */         compact(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clear() {
/* 280 */     this._size = 0;
/* 281 */     this._free = capacity();
/*     */   }
/*     */   
/*     */   protected int setUp(int initialCapacity) {
/* 295 */     int capacity = PrimeFinder.nextPrime(initialCapacity);
/* 296 */     computeMaxSize(capacity);
/* 297 */     computeNextAutoCompactionAmount(initialCapacity);
/* 299 */     return capacity;
/*     */   }
/*     */   
/*     */   protected abstract void rehash(int paramInt);
/*     */   
/*     */   public void tempDisableAutoCompaction() {
/* 316 */     this._autoCompactTemporaryDisable = true;
/*     */   }
/*     */   
/*     */   public void reenableAutoCompaction(boolean check_for_compaction) {
/* 329 */     this._autoCompactTemporaryDisable = false;
/* 331 */     if (check_for_compaction && this._autoCompactRemovesRemaining <= 0 && this._autoCompactionFactor != 0.0F)
/* 336 */       compact(); 
/*     */   }
/*     */   
/*     */   protected void computeMaxSize(int capacity) {
/* 349 */     this._maxSize = Math.min(capacity - 1, (int)(capacity * this._loadFactor));
/* 350 */     this._free = capacity - this._size;
/*     */   }
/*     */   
/*     */   protected void computeNextAutoCompactionAmount(int size) {
/* 361 */     if (this._autoCompactionFactor != 0.0F)
/* 364 */       this._autoCompactRemovesRemaining = (int)(size * this._autoCompactionFactor + 0.5F); 
/*     */   }
/*     */   
/*     */   protected final void postInsertHook(boolean usedFreeSlot) {
/* 377 */     if (usedFreeSlot)
/* 378 */       this._free--; 
/* 382 */     if (++this._size > this._maxSize || this._free == 0) {
/* 387 */       int newCapacity = (this._size > this._maxSize) ? PrimeFinder.nextPrime(capacity() << 1) : capacity();
/* 388 */       rehash(newCapacity);
/* 389 */       computeMaxSize(capacity());
/*     */     } 
/*     */   }
/*     */   
/*     */   protected int calculateGrownCapacity() {
/* 395 */     return capacity() << 1;
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 401 */     out.writeByte(0);
/* 404 */     out.writeFloat(this._loadFactor);
/* 407 */     out.writeFloat(this._autoCompactionFactor);
/*     */   }
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 415 */     in.readByte();
/* 418 */     float old_factor = this._loadFactor;
/* 419 */     this._loadFactor = in.readFloat();
/* 422 */     this._autoCompactionFactor = in.readFloat();
/* 425 */     if (old_factor != this._loadFactor)
/* 426 */       setUp((int)Math.ceil((10.0F / this._loadFactor))); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\impl\hash\THash.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */