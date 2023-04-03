/*     */ package gnu.trove.set.hash;
/*     */ 
/*     */ import gnu.trove.impl.HashFunctions;
/*     */ import gnu.trove.impl.hash.TCustomObjectHash;
/*     */ import gnu.trove.impl.hash.TObjectHash;
/*     */ import gnu.trove.iterator.hash.TObjectHashIterator;
/*     */ import gnu.trove.procedure.TObjectProcedure;
/*     */ import gnu.trove.procedure.array.ToObjectArrayProceedure;
/*     */ import gnu.trove.strategy.HashingStrategy;
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class TCustomHashSet<E> extends TCustomObjectHash<E> implements Set<E>, Iterable<E>, Externalizable {
/*     */   static final long serialVersionUID = 1L;
/*     */   
/*     */   public TCustomHashSet() {}
/*     */   
/*     */   public TCustomHashSet(HashingStrategy<? super E> strategy) {
/*  62 */     super(strategy);
/*     */   }
/*     */   
/*     */   public TCustomHashSet(HashingStrategy<? super E> strategy, int initialCapacity) {
/*  74 */     super(strategy, initialCapacity);
/*     */   }
/*     */   
/*     */   public TCustomHashSet(HashingStrategy<? super E> strategy, int initialCapacity, float loadFactor) {
/*  89 */     super(strategy, initialCapacity, loadFactor);
/*     */   }
/*     */   
/*     */   public TCustomHashSet(HashingStrategy<? super E> strategy, Collection<? extends E> collection) {
/* 102 */     this(strategy, collection.size());
/* 103 */     addAll(collection);
/*     */   }
/*     */   
/*     */   public boolean add(E obj) {
/* 114 */     int index = insertKey(obj);
/* 116 */     if (index < 0)
/* 117 */       return false; 
/* 120 */     postInsertHook(this.consumeFreeSlot);
/* 121 */     return true;
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 127 */     if (!(other instanceof Set))
/* 128 */       return false; 
/* 130 */     Set<?> that = (Set)other;
/* 131 */     if (that.size() != size())
/* 132 */       return false; 
/* 134 */     return containsAll(that);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 139 */     HashProcedure p = new HashProcedure();
/* 140 */     forEach(p);
/* 141 */     return p.getHashCode();
/*     */   }
/*     */   
/*     */   private final class HashProcedure implements TObjectProcedure<E> {
/* 146 */     private int h = 0;
/*     */     
/*     */     public int getHashCode() {
/* 149 */       return this.h;
/*     */     }
/*     */     
/*     */     public final boolean execute(E key) {
/* 153 */       this.h += HashFunctions.hash(key);
/* 154 */       return true;
/*     */     }
/*     */     
/*     */     private HashProcedure() {}
/*     */   }
/*     */   
/*     */   protected void rehash(int newCapacity) {
/* 166 */     int oldCapacity = this._set.length;
/* 167 */     int oldSize = size();
/* 168 */     Object[] oldSet = this._set;
/* 170 */     this._set = new Object[newCapacity];
/* 171 */     Arrays.fill(this._set, FREE);
/* 173 */     for (int i = oldCapacity; i-- > 0; ) {
/* 174 */       E o = (E)oldSet[i];
/* 175 */       if (o != FREE && o != REMOVED) {
/* 176 */         int index = insertKey(o);
/* 177 */         if (index < 0)
/* 178 */           throwObjectContractViolation(this._set[-index - 1], o, size(), oldSize, oldSet); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 192 */     Object[] result = new Object[size()];
/* 193 */     forEach((TObjectProcedure)new ToObjectArrayProceedure(result));
/* 194 */     return result;
/*     */   }
/*     */   
/*     */   public <T> T[] toArray(T[] a) {
/* 206 */     int size = size();
/* 207 */     if (a.length < size)
/* 208 */       a = (T[])Array.newInstance(a.getClass().getComponentType(), size); 
/* 211 */     forEach((TObjectProcedure)new ToObjectArrayProceedure((Object[])a));
/* 221 */     if (a.length > size)
/* 222 */       a[size] = null; 
/* 225 */     return a;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 231 */     super.clear();
/* 233 */     Arrays.fill(this._set, 0, this._set.length, FREE);
/*     */   }
/*     */   
/*     */   public boolean remove(Object obj) {
/* 245 */     int index = index(obj);
/* 246 */     if (index >= 0) {
/* 247 */       removeAt(index);
/* 248 */       return true;
/*     */     } 
/* 250 */     return false;
/*     */   }
/*     */   
/*     */   public TObjectHashIterator<E> iterator() {
/* 262 */     return new TObjectHashIterator((TObjectHash)this);
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection<?> collection) {
/* 275 */     for (Iterator<?> i = collection.iterator(); i.hasNext();) {
/* 276 */       if (!contains(i.next()))
/* 277 */         return false; 
/*     */     } 
/* 280 */     return true;
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection<? extends E> collection) {
/* 291 */     boolean changed = false;
/* 292 */     int size = collection.size();
/* 294 */     ensureCapacity(size);
/* 295 */     Iterator<? extends E> it = collection.iterator();
/* 296 */     while (size-- > 0) {
/* 297 */       if (add(it.next()))
/* 298 */         changed = true; 
/*     */     } 
/* 301 */     return changed;
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection<?> collection) {
/* 312 */     boolean changed = false;
/* 313 */     int size = collection.size();
/* 316 */     Iterator<?> it = collection.iterator();
/* 317 */     while (size-- > 0) {
/* 318 */       if (remove(it.next()))
/* 319 */         changed = true; 
/*     */     } 
/* 322 */     return changed;
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection<?> collection) {
/* 335 */     boolean changed = false;
/* 336 */     int size = size();
/* 337 */     TObjectHashIterator<E> tObjectHashIterator = iterator();
/* 338 */     while (size-- > 0) {
/* 339 */       if (!collection.contains(tObjectHashIterator.next())) {
/* 340 */         tObjectHashIterator.remove();
/* 341 */         changed = true;
/*     */       } 
/*     */     } 
/* 344 */     return changed;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 349 */     final StringBuilder buf = new StringBuilder("{");
/* 350 */     forEach(new TObjectProcedure<E>() {
/*     */           private boolean first = true;
/*     */           
/*     */           public boolean execute(Object value) {
/* 355 */             if (this.first) {
/* 356 */               this.first = false;
/*     */             } else {
/* 358 */               buf.append(", ");
/*     */             } 
/* 361 */             buf.append(value);
/* 362 */             return true;
/*     */           }
/*     */         });
/* 365 */     buf.append("}");
/* 366 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 372 */     out.writeByte(1);
/* 375 */     super.writeExternal(out);
/* 378 */     out.writeInt(this._size);
/* 381 */     for (int i = this._set.length; i-- > 0;) {
/* 382 */       if (this._set[i] != REMOVED && this._set[i] != FREE)
/* 383 */         out.writeObject(this._set[i]); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 394 */     byte version = in.readByte();
/* 397 */     if (version != 0)
/* 398 */       super.readExternal(in); 
/* 402 */     int size = in.readInt();
/* 403 */     setUp(size);
/* 406 */     while (size-- > 0) {
/* 407 */       E val = (E)in.readObject();
/* 408 */       add(val);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\set\hash\TCustomHashSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */