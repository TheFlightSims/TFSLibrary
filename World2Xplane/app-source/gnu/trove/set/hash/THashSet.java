/*     */ package gnu.trove.set.hash;
/*     */ 
/*     */ import gnu.trove.impl.HashFunctions;
/*     */ import gnu.trove.impl.hash.TObjectHash;
/*     */ import gnu.trove.iterator.hash.TObjectHashIterator;
/*     */ import gnu.trove.procedure.TObjectProcedure;
/*     */ import gnu.trove.procedure.array.ToObjectArrayProceedure;
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
/*     */ public class THashSet<E> extends TObjectHash<E> implements Set<E>, Iterable<E>, Externalizable {
/*     */   static final long serialVersionUID = 1L;
/*     */   
/*     */   public THashSet() {}
/*     */   
/*     */   public THashSet(int initialCapacity) {
/*  70 */     super(initialCapacity);
/*     */   }
/*     */   
/*     */   public THashSet(int initialCapacity, float loadFactor) {
/*  83 */     super(initialCapacity, loadFactor);
/*     */   }
/*     */   
/*     */   public THashSet(Collection<? extends E> collection) {
/*  94 */     this(collection.size());
/*  95 */     addAll(collection);
/*     */   }
/*     */   
/*     */   public boolean add(E obj) {
/* 106 */     int index = insertKey(obj);
/* 108 */     if (index < 0)
/* 109 */       return false; 
/* 112 */     postInsertHook(this.consumeFreeSlot);
/* 113 */     return true;
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 119 */     if (!(other instanceof Set))
/* 120 */       return false; 
/* 122 */     Set<?> that = (Set)other;
/* 123 */     if (that.size() != size())
/* 124 */       return false; 
/* 126 */     return containsAll(that);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 131 */     HashProcedure p = new HashProcedure();
/* 132 */     forEach(p);
/* 133 */     return p.getHashCode();
/*     */   }
/*     */   
/*     */   private final class HashProcedure implements TObjectProcedure<E> {
/* 138 */     private int h = 0;
/*     */     
/*     */     public int getHashCode() {
/* 141 */       return this.h;
/*     */     }
/*     */     
/*     */     public final boolean execute(E key) {
/* 145 */       this.h += HashFunctions.hash(key);
/* 146 */       return true;
/*     */     }
/*     */     
/*     */     private HashProcedure() {}
/*     */   }
/*     */   
/*     */   protected void rehash(int newCapacity) {
/* 158 */     int oldCapacity = this._set.length;
/* 160 */     int oldSize = size();
/* 161 */     Object[] oldSet = this._set;
/* 163 */     this._set = new Object[newCapacity];
/* 164 */     Arrays.fill(this._set, FREE);
/* 166 */     int count = 0;
/* 167 */     for (int i = oldCapacity; i-- > 0; ) {
/* 168 */       E o = (E)oldSet[i];
/* 169 */       if (o != FREE && o != REMOVED) {
/* 170 */         int index = insertKey(o);
/* 171 */         if (index < 0)
/* 172 */           throwObjectContractViolation(this._set[-index - 1], o, size(), oldSize, oldSet); 
/* 175 */         count++;
/*     */       } 
/*     */     } 
/* 179 */     reportPotentialConcurrentMod(size(), oldSize);
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 189 */     Object[] result = new Object[size()];
/* 190 */     forEach((TObjectProcedure)new ToObjectArrayProceedure(result));
/* 191 */     return result;
/*     */   }
/*     */   
/*     */   public <T> T[] toArray(T[] a) {
/* 203 */     int size = size();
/* 204 */     if (a.length < size)
/* 205 */       a = (T[])Array.newInstance(a.getClass().getComponentType(), size); 
/* 208 */     forEach((TObjectProcedure)new ToObjectArrayProceedure((Object[])a));
/* 218 */     if (a.length > size)
/* 219 */       a[size] = null; 
/* 222 */     return a;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 230 */     super.clear();
/* 232 */     Arrays.fill(this._set, 0, this._set.length, FREE);
/*     */   }
/*     */   
/*     */   public boolean remove(Object obj) {
/* 244 */     int index = index(obj);
/* 245 */     if (index >= 0) {
/* 246 */       removeAt(index);
/* 247 */       return true;
/*     */     } 
/* 249 */     return false;
/*     */   }
/*     */   
/*     */   public TObjectHashIterator<E> iterator() {
/* 261 */     return new TObjectHashIterator(this);
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection<?> collection) {
/* 274 */     for (Iterator<?> i = collection.iterator(); i.hasNext();) {
/* 275 */       if (!contains(i.next()))
/* 276 */         return false; 
/*     */     } 
/* 279 */     return true;
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection<? extends E> collection) {
/* 290 */     boolean changed = false;
/* 291 */     int size = collection.size();
/* 293 */     ensureCapacity(size);
/* 294 */     Iterator<? extends E> it = collection.iterator();
/* 295 */     while (size-- > 0) {
/* 296 */       if (add(it.next()))
/* 297 */         changed = true; 
/*     */     } 
/* 300 */     return changed;
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection<?> collection) {
/* 311 */     boolean changed = false;
/* 312 */     int size = collection.size();
/* 315 */     Iterator<?> it = collection.iterator();
/* 316 */     while (size-- > 0) {
/* 317 */       if (remove(it.next()))
/* 318 */         changed = true; 
/*     */     } 
/* 321 */     return changed;
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection<?> collection) {
/* 334 */     boolean changed = false;
/* 335 */     int size = size();
/* 336 */     TObjectHashIterator<E> tObjectHashIterator = iterator();
/* 337 */     while (size-- > 0) {
/* 338 */       if (!collection.contains(tObjectHashIterator.next())) {
/* 339 */         tObjectHashIterator.remove();
/* 340 */         changed = true;
/*     */       } 
/*     */     } 
/* 343 */     return changed;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 348 */     final StringBuilder buf = new StringBuilder("{");
/* 349 */     forEach(new TObjectProcedure<E>() {
/*     */           private boolean first = true;
/*     */           
/*     */           public boolean execute(Object value) {
/* 354 */             if (this.first) {
/* 355 */               this.first = false;
/*     */             } else {
/* 357 */               buf.append(", ");
/*     */             } 
/* 360 */             buf.append(value);
/* 361 */             return true;
/*     */           }
/*     */         });
/* 364 */     buf.append("}");
/* 365 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 371 */     out.writeByte(1);
/* 374 */     super.writeExternal(out);
/* 377 */     out.writeInt(this._size);
/* 380 */     writeEntries(out);
/*     */   }
/*     */   
/*     */   protected void writeEntries(ObjectOutput out) throws IOException {
/* 384 */     for (int i = this._set.length; i-- > 0;) {
/* 385 */       if (this._set[i] != REMOVED && this._set[i] != FREE)
/* 386 */         out.writeObject(this._set[i]); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 396 */     byte version = in.readByte();
/* 399 */     if (version != 0)
/* 400 */       super.readExternal(in); 
/* 404 */     int size = in.readInt();
/* 405 */     setUp(size);
/* 408 */     while (size-- > 0) {
/* 409 */       E val = (E)in.readObject();
/* 410 */       add(val);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\set\hash\THashSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */