/*     */ package gnu.trove.impl.hash;
/*     */ 
/*     */ import gnu.trove.impl.Constants;
/*     */ import gnu.trove.impl.HashFunctions;
/*     */ import gnu.trove.procedure.TLongProcedure;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ public abstract class TLongHash extends TPrimitiveHash {
/*     */   static final long serialVersionUID = 1L;
/*     */   
/*     */   public transient long[] _set;
/*     */   
/*     */   protected long no_entry_value;
/*     */   
/*     */   protected boolean consumeFreeSlot;
/*     */   
/*     */   public TLongHash() {
/*  66 */     this.no_entry_value = Constants.DEFAULT_LONG_NO_ENTRY_VALUE;
/*  68 */     if (this.no_entry_value != 0L)
/*  69 */       Arrays.fill(this._set, this.no_entry_value); 
/*     */   }
/*     */   
/*     */   public TLongHash(int initialCapacity) {
/*  82 */     super(initialCapacity);
/*  83 */     this.no_entry_value = Constants.DEFAULT_LONG_NO_ENTRY_VALUE;
/*  85 */     if (this.no_entry_value != 0L)
/*  86 */       Arrays.fill(this._set, this.no_entry_value); 
/*     */   }
/*     */   
/*     */   public TLongHash(int initialCapacity, float loadFactor) {
/* 100 */     super(initialCapacity, loadFactor);
/* 101 */     this.no_entry_value = Constants.DEFAULT_LONG_NO_ENTRY_VALUE;
/* 103 */     if (this.no_entry_value != 0L)
/* 104 */       Arrays.fill(this._set, this.no_entry_value); 
/*     */   }
/*     */   
/*     */   public TLongHash(int initialCapacity, float loadFactor, long no_entry_value) {
/* 119 */     super(initialCapacity, loadFactor);
/* 120 */     this.no_entry_value = no_entry_value;
/* 122 */     if (no_entry_value != 0L)
/* 123 */       Arrays.fill(this._set, no_entry_value); 
/*     */   }
/*     */   
/*     */   public long getNoEntryValue() {
/* 136 */     return this.no_entry_value;
/*     */   }
/*     */   
/*     */   protected int setUp(int initialCapacity) {
/* 150 */     int capacity = super.setUp(initialCapacity);
/* 151 */     this._set = new long[capacity];
/* 152 */     return capacity;
/*     */   }
/*     */   
/*     */   public boolean contains(long val) {
/* 163 */     return (index(val) >= 0);
/*     */   }
/*     */   
/*     */   public boolean forEach(TLongProcedure procedure) {
/* 175 */     byte[] states = this._states;
/* 176 */     long[] set = this._set;
/* 177 */     for (int i = set.length; i-- > 0;) {
/* 178 */       if (states[i] == 1 && !procedure.execute(set[i]))
/* 179 */         return false; 
/*     */     } 
/* 182 */     return true;
/*     */   }
/*     */   
/*     */   protected void removeAt(int index) {
/* 192 */     this._set[index] = this.no_entry_value;
/* 193 */     super.removeAt(index);
/*     */   }
/*     */   
/*     */   protected int index(long val) {
/* 206 */     byte[] states = this._states;
/* 207 */     long[] set = this._set;
/* 208 */     int length = states.length;
/* 209 */     int hash = HashFunctions.hash(val) & Integer.MAX_VALUE;
/* 210 */     int index = hash % length;
/* 211 */     byte state = states[index];
/* 213 */     if (state == 0)
/* 214 */       return -1; 
/* 216 */     if (state == 1 && set[index] == val)
/* 217 */       return index; 
/* 219 */     return indexRehashed(val, index, hash, state);
/*     */   }
/*     */   
/*     */   int indexRehashed(long key, int index, int hash, byte state) {
/* 224 */     int length = this._set.length;
/* 225 */     int probe = 1 + hash % (length - 2);
/* 226 */     int loopIndex = index;
/*     */     do {
/* 229 */       index -= probe;
/* 230 */       if (index < 0)
/* 231 */         index += length; 
/* 233 */       state = this._states[index];
/* 235 */       if (state == 0)
/* 236 */         return -1; 
/* 239 */       if (key == this._set[index] && state != 2)
/* 240 */         return index; 
/* 241 */     } while (index != loopIndex);
/* 243 */     return -1;
/*     */   }
/*     */   
/*     */   protected int insertKey(long val) {
/* 257 */     int hash = HashFunctions.hash(val) & Integer.MAX_VALUE;
/* 258 */     int index = hash % this._states.length;
/* 259 */     byte state = this._states[index];
/* 261 */     this.consumeFreeSlot = false;
/* 263 */     if (state == 0) {
/* 264 */       this.consumeFreeSlot = true;
/* 265 */       insertKeyAt(index, val);
/* 267 */       return index;
/*     */     } 
/* 270 */     if (state == 1 && this._set[index] == val)
/* 271 */       return -index - 1; 
/* 275 */     return insertKeyRehash(val, index, hash, state);
/*     */   }
/*     */   
/*     */   int insertKeyRehash(long val, int index, int hash, byte state) {
/* 280 */     int length = this._set.length;
/* 281 */     int probe = 1 + hash % (length - 2);
/* 282 */     int loopIndex = index;
/* 283 */     int firstRemoved = -1;
/*     */     do {
/* 290 */       if (state == 2 && firstRemoved == -1)
/* 291 */         firstRemoved = index; 
/* 293 */       index -= probe;
/* 294 */       if (index < 0)
/* 295 */         index += length; 
/* 297 */       state = this._states[index];
/* 300 */       if (state == 0) {
/* 301 */         if (firstRemoved != -1) {
/* 302 */           insertKeyAt(firstRemoved, val);
/* 303 */           return firstRemoved;
/*     */         } 
/* 305 */         this.consumeFreeSlot = true;
/* 306 */         insertKeyAt(index, val);
/* 307 */         return index;
/*     */       } 
/* 311 */       if (state == 1 && this._set[index] == val)
/* 312 */         return -index - 1; 
/* 316 */     } while (index != loopIndex);
/* 320 */     if (firstRemoved != -1) {
/* 321 */       insertKeyAt(firstRemoved, val);
/* 322 */       return firstRemoved;
/*     */     } 
/* 326 */     throw new IllegalStateException("No free or removed slots available. Key set full?!!");
/*     */   }
/*     */   
/*     */   void insertKeyAt(int index, long val) {
/* 330 */     this._set[index] = val;
/* 331 */     this._states[index] = 1;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\impl\hash\TLongHash.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */