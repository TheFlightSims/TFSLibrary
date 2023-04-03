/*     */ package gnu.trove.impl.hash;
/*     */ 
/*     */ import gnu.trove.procedure.TObjectProcedure;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class TObjectHash<T> extends THash {
/*     */   static final long serialVersionUID = -3461112548087185871L;
/*     */   
/*     */   public transient Object[] _set;
/*     */   
/*  54 */   public static final Object REMOVED = new Object();
/*     */   
/*  54 */   public static final Object FREE = new Object();
/*     */   
/*     */   protected boolean consumeFreeSlot;
/*     */   
/*     */   public TObjectHash() {}
/*     */   
/*     */   public TObjectHash(int initialCapacity) {
/*  80 */     super(initialCapacity);
/*     */   }
/*     */   
/*     */   public TObjectHash(int initialCapacity, float loadFactor) {
/*  93 */     super(initialCapacity, loadFactor);
/*     */   }
/*     */   
/*     */   public int capacity() {
/*  98 */     return this._set.length;
/*     */   }
/*     */   
/*     */   protected void removeAt(int index) {
/* 103 */     this._set[index] = REMOVED;
/* 104 */     super.removeAt(index);
/*     */   }
/*     */   
/*     */   public int setUp(int initialCapacity) {
/* 117 */     int capacity = super.setUp(initialCapacity);
/* 118 */     this._set = new Object[capacity];
/* 119 */     Arrays.fill(this._set, FREE);
/* 120 */     return capacity;
/*     */   }
/*     */   
/*     */   public boolean forEach(TObjectProcedure<? super T> procedure) {
/* 133 */     Object[] set = this._set;
/* 134 */     for (int i = set.length; i-- > 0;) {
/* 135 */       if (set[i] != FREE && set[i] != REMOVED && !procedure.execute(set[i]))
/* 138 */         return false; 
/*     */     } 
/* 141 */     return true;
/*     */   }
/*     */   
/*     */   public boolean contains(Object obj) {
/* 153 */     return (index(obj) >= 0);
/*     */   }
/*     */   
/*     */   protected int index(Object obj) {
/* 164 */     if (obj == null)
/* 165 */       return indexForNull(); 
/* 168 */     int hash = hash(obj) & Integer.MAX_VALUE;
/* 169 */     int index = hash % this._set.length;
/* 170 */     Object cur = this._set[index];
/* 173 */     if (cur == FREE)
/* 174 */       return -1; 
/* 177 */     if (cur == obj || equals(obj, cur))
/* 178 */       return index; 
/* 181 */     return indexRehashed(obj, index, hash, cur);
/*     */   }
/*     */   
/*     */   private int indexRehashed(Object obj, int index, int hash, Object cur) {
/* 194 */     Object[] set = this._set;
/* 195 */     int length = set.length;
/* 199 */     int probe = 1 + hash % (length - 2);
/* 201 */     int loopIndex = index;
/*     */     do {
/* 204 */       index -= probe;
/* 205 */       if (index < 0)
/* 206 */         index += length; 
/* 208 */       cur = set[index];
/* 210 */       if (cur == FREE)
/* 211 */         return -1; 
/* 214 */       if (cur == obj || equals(obj, cur))
/* 215 */         return index; 
/* 216 */     } while (index != loopIndex);
/* 218 */     return -1;
/*     */   }
/*     */   
/*     */   private int indexForNull() {
/* 234 */     int index = 0;
/* 235 */     for (Object o : this._set) {
/* 236 */       if (o == null)
/* 237 */         return index; 
/* 239 */       if (o == FREE)
/* 240 */         return -1; 
/* 242 */       index++;
/*     */     } 
/* 245 */     return -1;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   protected int insertionIndex(T obj) {
/* 258 */     return insertKey(obj);
/*     */   }
/*     */   
/*     */   protected int insertKey(T key) {
/* 275 */     this.consumeFreeSlot = false;
/* 277 */     if (key == null)
/* 278 */       return insertKeyForNull(); 
/* 280 */     int hash = hash(key) & Integer.MAX_VALUE;
/* 281 */     int index = hash % this._set.length;
/* 282 */     Object cur = this._set[index];
/* 284 */     if (cur == FREE) {
/* 285 */       this.consumeFreeSlot = true;
/* 286 */       this._set[index] = key;
/* 287 */       return index;
/*     */     } 
/* 290 */     if (cur == key || equals(key, cur))
/* 291 */       return -index - 1; 
/* 294 */     return insertKeyRehash(key, index, hash, cur);
/*     */   }
/*     */   
/*     */   private int insertKeyRehash(T key, int index, int hash, Object cur) {
/* 308 */     Object[] set = this._set;
/* 309 */     int length = set.length;
/* 312 */     int probe = 1 + hash % (length - 2);
/* 314 */     int loopIndex = index;
/* 315 */     int firstRemoved = -1;
/*     */     do {
/* 322 */       if (cur == REMOVED && firstRemoved == -1)
/* 323 */         firstRemoved = index; 
/* 325 */       index -= probe;
/* 326 */       if (index < 0)
/* 327 */         index += length; 
/* 329 */       cur = set[index];
/* 332 */       if (cur == FREE) {
/* 333 */         if (firstRemoved != -1) {
/* 334 */           this._set[firstRemoved] = key;
/* 335 */           return firstRemoved;
/*     */         } 
/* 337 */         this.consumeFreeSlot = true;
/* 338 */         this._set[index] = key;
/* 339 */         return index;
/*     */       } 
/* 343 */       if (cur == key || equals(key, cur))
/* 344 */         return -index - 1; 
/* 348 */     } while (index != loopIndex);
/* 352 */     if (firstRemoved != -1) {
/* 353 */       this._set[firstRemoved] = key;
/* 354 */       return firstRemoved;
/*     */     } 
/* 358 */     throw new IllegalStateException("No free or removed slots available. Key set full?!!");
/*     */   }
/*     */   
/*     */   private int insertKeyForNull() {
/* 372 */     int index = 0;
/* 373 */     int firstRemoved = -1;
/* 376 */     for (Object o : this._set) {
/* 378 */       if (o == REMOVED && firstRemoved == -1)
/* 379 */         firstRemoved = index; 
/* 381 */       if (o == FREE) {
/* 382 */         if (firstRemoved != -1) {
/* 383 */           this._set[firstRemoved] = null;
/* 384 */           return firstRemoved;
/*     */         } 
/* 386 */         this.consumeFreeSlot = true;
/* 387 */         this._set[index] = null;
/* 388 */         return index;
/*     */       } 
/* 392 */       if (o == null)
/* 393 */         return -index - 1; 
/* 396 */       index++;
/*     */     } 
/* 401 */     if (firstRemoved != -1) {
/* 402 */       this._set[firstRemoved] = null;
/* 403 */       return firstRemoved;
/*     */     } 
/* 408 */     throw new IllegalStateException("Could not find insertion index for null key. Key set full!?!!");
/*     */   }
/*     */   
/*     */   protected final void throwObjectContractViolation(Object o1, Object o2) throws IllegalArgumentException {
/* 426 */     throw buildObjectContractViolation(o1, o2, "");
/*     */   }
/*     */   
/*     */   protected final void throwObjectContractViolation(Object o1, Object o2, int size, int oldSize, Object[] oldKeys) throws IllegalArgumentException {
/* 445 */     String extra = dumpExtraInfo(o1, o2, size(), oldSize, oldKeys);
/* 448 */     throw buildObjectContractViolation(o1, o2, extra);
/*     */   }
/*     */   
/*     */   protected final IllegalArgumentException buildObjectContractViolation(Object o1, Object o2, String extra) {
/* 464 */     return new IllegalArgumentException("Equal objects must have equal hashcodes. During rehashing, Trove discovered that the following two objects claim to be equal (as in java.lang.Object.equals()) but their hashCodes (or those calculated by your TObjectHashingStrategy) are not equal.This violates the general contract of java.lang.Object.hashCode().  See bullet point two in that method's documentation. object #1 =" + objectInfo(o1) + "; object #2 =" + objectInfo(o2) + "\n" + extra);
/*     */   }
/*     */   
/*     */   protected boolean equals(Object notnull, Object two) {
/* 475 */     if (two == null || two == REMOVED)
/* 476 */       return false; 
/* 478 */     return notnull.equals(two);
/*     */   }
/*     */   
/*     */   protected int hash(Object notnull) {
/* 482 */     return notnull.hashCode();
/*     */   }
/*     */   
/*     */   protected static String reportPotentialConcurrentMod(int newSize, int oldSize) {
/* 488 */     if (newSize != oldSize)
/* 489 */       return "[Warning] apparent concurrent modification of the key set. Size before and after rehash() do not match " + oldSize + " vs " + newSize; 
/* 492 */     return "";
/*     */   }
/*     */   
/*     */   protected String dumpExtraInfo(Object newVal, Object oldVal, int currentSize, int oldSize, Object[] oldKeys) {
/* 504 */     StringBuilder b = new StringBuilder();
/* 506 */     b.append(dumpKeyTypes(newVal, oldVal));
/* 508 */     b.append(reportPotentialConcurrentMod(currentSize, oldSize));
/* 509 */     b.append(detectKeyLoss(oldKeys, oldSize));
/* 512 */     if (newVal == oldVal)
/* 513 */       b.append("Inserting same object twice, rehashing bug. Object= ").append(oldVal); 
/* 516 */     return b.toString();
/*     */   }
/*     */   
/*     */   private static String detectKeyLoss(Object[] keys, int oldSize) {
/* 527 */     StringBuilder buf = new StringBuilder();
/* 528 */     Set<Object> k = makeKeySet(keys);
/* 529 */     if (k.size() != oldSize) {
/* 530 */       buf.append("\nhashCode() and/or equals() have inconsistent implementation");
/* 531 */       buf.append("\nKey set lost entries, now got ").append(k.size()).append(" instead of ").append(oldSize);
/* 532 */       buf.append(". This can manifest itself as an apparent duplicate key.");
/*     */     } 
/* 535 */     return buf.toString();
/*     */   }
/*     */   
/*     */   private static Set<Object> makeKeySet(Object[] keys) {
/* 539 */     Set<Object> types = new HashSet();
/* 540 */     for (Object o : keys) {
/* 541 */       if (o != FREE && o != REMOVED)
/* 542 */         types.add(o); 
/*     */     } 
/* 546 */     return types;
/*     */   }
/*     */   
/*     */   private static String equalsSymmetryInfo(Object a, Object b) {
/* 550 */     StringBuilder buf = new StringBuilder();
/* 551 */     if (a == b)
/* 552 */       return "a == b"; 
/* 555 */     if (a.getClass() != b.getClass()) {
/* 556 */       buf.append("Class of objects differ a=").append(a.getClass()).append(" vs b=").append(b.getClass());
/* 558 */       boolean aEb = a.equals(b);
/* 559 */       boolean bEa = b.equals(a);
/* 560 */       if (aEb != bEa) {
/* 561 */         buf.append("\nequals() of a or b object are asymmetric");
/* 562 */         buf.append("\na.equals(b) =").append(aEb);
/* 563 */         buf.append("\nb.equals(a) =").append(bEa);
/*     */       } 
/*     */     } 
/* 567 */     return buf.toString();
/*     */   }
/*     */   
/*     */   protected static String objectInfo(Object o) {
/* 571 */     return ((o == null) ? "class null" : (String)o.getClass()) + " id= " + System.identityHashCode(o) + " hashCode= " + ((o == null) ? 0 : o.hashCode()) + " toString= " + String.valueOf(o);
/*     */   }
/*     */   
/*     */   private String dumpKeyTypes(Object newVal, Object oldVal) {
/* 576 */     StringBuilder buf = new StringBuilder();
/* 577 */     Set<Class<?>> types = new HashSet<Class<?>>();
/* 578 */     for (Object o : this._set) {
/* 579 */       if (o != FREE && o != REMOVED)
/* 580 */         if (o != null) {
/* 581 */           types.add(o.getClass());
/*     */         } else {
/* 583 */           types.add(null);
/*     */         }  
/*     */     } 
/* 587 */     if (types.size() > 1) {
/* 588 */       buf.append("\nMore than one type used for keys. Watch out for asymmetric equals(). Read about the 'Liskov substitution principle' and the implications for equals() in java.");
/* 591 */       buf.append("\nKey types: ").append(types);
/* 592 */       buf.append(equalsSymmetryInfo(newVal, oldVal));
/*     */     } 
/* 595 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 602 */     out.writeByte(0);
/* 605 */     super.writeExternal(out);
/*     */   }
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 614 */     in.readByte();
/* 617 */     super.readExternal(in);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\impl\hash\TObjectHash.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */