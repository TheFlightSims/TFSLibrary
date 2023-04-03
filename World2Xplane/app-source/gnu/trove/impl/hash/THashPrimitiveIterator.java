/*     */ package gnu.trove.impl.hash;
/*     */ 
/*     */ import gnu.trove.iterator.TPrimitiveIterator;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public abstract class THashPrimitiveIterator implements TPrimitiveIterator {
/*     */   protected final TPrimitiveHash _hash;
/*     */   
/*     */   protected int _expectedSize;
/*     */   
/*     */   protected int _index;
/*     */   
/*     */   public THashPrimitiveIterator(TPrimitiveHash hash) {
/*  66 */     this._hash = hash;
/*  67 */     this._expectedSize = this._hash.size();
/*  68 */     this._index = this._hash.capacity();
/*     */   }
/*     */   
/*     */   protected final int nextIndex() {
/*  82 */     if (this._expectedSize != this._hash.size())
/*  83 */       throw new ConcurrentModificationException(); 
/*  86 */     byte[] states = this._hash._states;
/*  87 */     int i = this._index;
/*  88 */     while (i-- > 0 && states[i] != 1);
/*  91 */     return i;
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/* 102 */     return (nextIndex() >= 0);
/*     */   }
/*     */   
/*     */   public void remove() {
/* 113 */     if (this._expectedSize != this._hash.size())
/* 114 */       throw new ConcurrentModificationException(); 
/*     */     try {
/* 119 */       this._hash.tempDisableAutoCompaction();
/* 120 */       this._hash.removeAt(this._index);
/*     */     } finally {
/* 123 */       this._hash.reenableAutoCompaction(false);
/*     */     } 
/* 126 */     this._expectedSize--;
/*     */   }
/*     */   
/*     */   protected final void moveToNextIndex() {
/* 137 */     if ((this._index = nextIndex()) < 0)
/* 138 */       throw new NoSuchElementException(); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\impl\hash\THashPrimitiveIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */