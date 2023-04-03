/*     */ package gnu.trove.impl.hash;
/*     */ 
/*     */ import gnu.trove.impl.HashFunctions;
/*     */ 
/*     */ public abstract class TPrimitiveHash extends THash {
/*     */   static final long serialVersionUID = 1L;
/*     */   
/*     */   public transient byte[] _states;
/*     */   
/*     */   public static final byte FREE = 0;
/*     */   
/*     */   public static final byte FULL = 1;
/*     */   
/*     */   public static final byte REMOVED = 2;
/*     */   
/*     */   public TPrimitiveHash() {}
/*     */   
/*     */   public TPrimitiveHash(int initialCapacity) {
/*  80 */     this(initialCapacity, 0.5F);
/*     */   }
/*     */   
/*     */   public TPrimitiveHash(int initialCapacity, float loadFactor) {
/*  95 */     initialCapacity = Math.max(1, initialCapacity);
/*  96 */     this._loadFactor = loadFactor;
/*  97 */     setUp(HashFunctions.fastCeil(initialCapacity / loadFactor));
/*     */   }
/*     */   
/*     */   public int capacity() {
/* 108 */     return this._states.length;
/*     */   }
/*     */   
/*     */   protected void removeAt(int index) {
/* 118 */     this._states[index] = 2;
/* 119 */     super.removeAt(index);
/*     */   }
/*     */   
/*     */   protected int setUp(int initialCapacity) {
/* 133 */     int capacity = super.setUp(initialCapacity);
/* 134 */     this._states = new byte[capacity];
/* 135 */     return capacity;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\impl\hash\TPrimitiveHash.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */