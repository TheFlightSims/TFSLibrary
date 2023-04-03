/*     */ package gnu.trove.impl.hash;
/*     */ 
/*     */ import gnu.trove.iterator.TIterator;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public abstract class THashIterator<V> implements TIterator, Iterator<V> {
/*     */   private final TObjectHash<V> _object_hash;
/*     */   
/*     */   protected final THash _hash;
/*     */   
/*     */   protected int _expectedSize;
/*     */   
/*     */   protected int _index;
/*     */   
/*     */   protected THashIterator(TObjectHash<V> hash) {
/*  73 */     this._hash = hash;
/*  74 */     this._expectedSize = this._hash.size();
/*  75 */     this._index = this._hash.capacity();
/*  76 */     this._object_hash = hash;
/*     */   }
/*     */   
/*     */   public V next() {
/*  91 */     moveToNextIndex();
/*  92 */     return objectAtIndex(this._index);
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/* 103 */     return (nextIndex() >= 0);
/*     */   }
/*     */   
/*     */   public void remove() {
/* 114 */     if (this._expectedSize != this._hash.size())
/* 115 */       throw new ConcurrentModificationException(); 
/*     */     try {
/* 120 */       this._hash.tempDisableAutoCompaction();
/* 121 */       this._hash.removeAt(this._index);
/*     */     } finally {
/* 124 */       this._hash.reenableAutoCompaction(false);
/*     */     } 
/* 127 */     this._expectedSize--;
/*     */   }
/*     */   
/*     */   protected final void moveToNextIndex() {
/* 138 */     if ((this._index = nextIndex()) < 0)
/* 139 */       throw new NoSuchElementException(); 
/*     */   }
/*     */   
/*     */   protected final int nextIndex() {
/* 155 */     if (this._expectedSize != this._hash.size())
/* 156 */       throw new ConcurrentModificationException(); 
/* 159 */     Object[] set = this._object_hash._set;
/* 160 */     int i = this._index;
/* 161 */     while (i-- > 0 && (set[i] == TObjectHash.FREE || set[i] == TObjectHash.REMOVED));
/* 164 */     return i;
/*     */   }
/*     */   
/*     */   protected abstract V objectAtIndex(int paramInt);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\impl\hash\THashIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */