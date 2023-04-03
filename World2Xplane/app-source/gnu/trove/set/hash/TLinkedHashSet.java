/*     */ package gnu.trove.set.hash;
/*     */ 
/*     */ import gnu.trove.impl.hash.TObjectHash;
/*     */ import gnu.trove.iterator.TIntIterator;
/*     */ import gnu.trove.iterator.hash.TObjectHashIterator;
/*     */ import gnu.trove.list.TIntList;
/*     */ import gnu.trove.list.array.TIntArrayList;
/*     */ import gnu.trove.list.linked.TIntLinkedList;
/*     */ import gnu.trove.procedure.TIntProcedure;
/*     */ import gnu.trove.procedure.TObjectProcedure;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectOutput;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class TLinkedHashSet<E> extends THashSet<E> {
/*     */   TIntList order;
/*     */   
/*     */   public TLinkedHashSet() {}
/*     */   
/*     */   public TLinkedHashSet(int initialCapacity) {
/*  42 */     super(initialCapacity);
/*     */   }
/*     */   
/*     */   public TLinkedHashSet(int initialCapacity, float loadFactor) {
/*  54 */     super(initialCapacity, loadFactor);
/*     */   }
/*     */   
/*     */   public TLinkedHashSet(Collection<? extends E> es) {
/*  64 */     super(es);
/*     */   }
/*     */   
/*     */   public int setUp(int initialCapacity) {
/*  75 */     this.order = (TIntList)new TIntArrayList(initialCapacity) {
/*     */         public void ensureCapacity(int capacity) {
/*  83 */           if (capacity > this._data.length) {
/*  84 */             int newCap = Math.max(TLinkedHashSet.this._set.length, capacity);
/*  85 */             int[] tmp = new int[newCap];
/*  86 */             System.arraycopy(this._data, 0, tmp, 0, this._data.length);
/*  87 */             this._data = tmp;
/*     */           } 
/*     */         }
/*     */       };
/*  91 */     return super.setUp(initialCapacity);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 100 */     super.clear();
/* 101 */     this.order.clear();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 106 */     StringBuilder buf = new StringBuilder("{");
/* 107 */     boolean first = true;
/* 109 */     for (TObjectHashIterator<E> tObjectHashIterator = iterator(); tObjectHashIterator.hasNext(); ) {
/* 110 */       if (first) {
/* 111 */         first = false;
/*     */       } else {
/* 113 */         buf.append(", ");
/*     */       } 
/* 116 */       buf.append(tObjectHashIterator.next());
/*     */     } 
/* 119 */     buf.append("}");
/* 120 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public boolean add(E obj) {
/* 131 */     int index = insertKey(obj);
/* 133 */     if (index < 0)
/* 134 */       return false; 
/* 137 */     if (!this.order.add(index))
/* 138 */       throw new IllegalStateException("Order not changed after insert"); 
/* 140 */     postInsertHook(this.consumeFreeSlot);
/* 141 */     return true;
/*     */   }
/*     */   
/*     */   protected void removeAt(int index) {
/* 148 */     this.order.remove(index);
/* 149 */     super.removeAt(index);
/*     */   }
/*     */   
/*     */   protected void rehash(int newCapacity) {
/* 160 */     TIntLinkedList oldOrder = new TIntLinkedList(this.order);
/* 161 */     int oldSize = size();
/* 163 */     Object[] oldSet = this._set;
/* 165 */     this.order.clear();
/* 166 */     this._set = new Object[newCapacity];
/* 167 */     Arrays.fill(this._set, FREE);
/* 169 */     for (TIntIterator iterator = oldOrder.iterator(); iterator.hasNext(); ) {
/* 170 */       int i = iterator.next();
/* 171 */       E o = (E)oldSet[i];
/* 172 */       if (o == FREE || o == REMOVED)
/* 173 */         throw new IllegalStateException("Iterating over empty location while rehashing"); 
/* 176 */       if (o != FREE && o != REMOVED) {
/* 177 */         int index = insertKey(o);
/* 178 */         if (index < 0)
/* 179 */           throwObjectContractViolation(this._set[-index - 1], o, size(), oldSize, oldSet); 
/* 182 */         if (!this.order.add(index))
/* 183 */           throw new IllegalStateException("Order not changed after insert"); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   class WriteProcedure implements TIntProcedure {
/*     */     final ObjectOutput output;
/*     */     
/*     */     IOException ioException;
/*     */     
/*     */     WriteProcedure(ObjectOutput output) {
/* 194 */       this.output = output;
/*     */     }
/*     */     
/*     */     public IOException getIoException() {
/* 198 */       return this.ioException;
/*     */     }
/*     */     
/*     */     public boolean execute(int value) {
/*     */       try {
/* 203 */         this.output.writeObject(TLinkedHashSet.this._set[value]);
/* 204 */       } catch (IOException e) {
/* 205 */         this.ioException = e;
/* 206 */         return false;
/*     */       } 
/* 208 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   protected void writeEntries(ObjectOutput out) throws IOException {
/* 216 */     WriteProcedure writeProcedure = new WriteProcedure(out);
/* 217 */     if (!this.order.forEach(writeProcedure))
/* 218 */       throw writeProcedure.getIoException(); 
/*     */   }
/*     */   
/*     */   public TObjectHashIterator<E> iterator() {
/* 229 */     return new TObjectHashIterator<E>(this) {
/* 230 */         TIntIterator localIterator = TLinkedHashSet.this.order.iterator();
/*     */         
/*     */         int lastIndex;
/*     */         
/*     */         public E next() {
/* 246 */           this.lastIndex = this.localIterator.next();
/* 247 */           return (E)objectAtIndex(this.lastIndex);
/*     */         }
/*     */         
/*     */         public boolean hasNext() {
/* 258 */           return this.localIterator.hasNext();
/*     */         }
/*     */         
/*     */         public void remove() {
/* 270 */           this.localIterator.remove();
/*     */           try {
/* 274 */             this._hash.tempDisableAutoCompaction();
/* 275 */             TLinkedHashSet.this.removeAt(this.lastIndex);
/*     */           } finally {
/* 277 */             this._hash.reenableAutoCompaction(false);
/*     */           } 
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   class ForEachProcedure implements TIntProcedure {
/*     */     boolean changed = false;
/*     */     
/*     */     final Object[] set;
/*     */     
/*     */     final TObjectProcedure<? super E> procedure;
/*     */     
/*     */     public ForEachProcedure(Object[] set, TObjectProcedure<? super E> procedure) {
/* 289 */       this.set = set;
/* 290 */       this.procedure = procedure;
/*     */     }
/*     */     
/*     */     public boolean execute(int value) {
/* 303 */       return this.procedure.execute(this.set[value]);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean forEach(TObjectProcedure<? super E> procedure) {
/* 316 */     ForEachProcedure forEachProcedure = new ForEachProcedure(this._set, procedure);
/* 317 */     return this.order.forEach(forEachProcedure);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\set\hash\TLinkedHashSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */