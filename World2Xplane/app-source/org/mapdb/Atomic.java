/*     */ package org.mapdb;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.IOException;
/*     */ 
/*     */ public final class Atomic {
/*     */   public static final class Integer extends Number {
/*     */     private static final long serialVersionUID = 4615119399830853054L;
/*     */     
/*     */     protected final Engine engine;
/*     */     
/*     */     protected final long recid;
/*     */     
/*     */     public Integer(Engine engine, long recid) {
/* 109 */       this.engine = engine;
/* 110 */       this.recid = recid;
/*     */     }
/*     */     
/*     */     public long getRecid() {
/* 117 */       return this.recid;
/*     */     }
/*     */     
/*     */     public final int get() {
/* 126 */       return ((Integer)this.engine.<Integer>get(this.recid, Serializer.INTEGER)).intValue();
/*     */     }
/*     */     
/*     */     public final void set(int newValue) {
/* 135 */       this.engine.update(this.recid, Integer.valueOf(newValue), Serializer.INTEGER);
/*     */     }
/*     */     
/*     */     public final int getAndSet(int newValue) {
/*     */       while (true) {
/* 147 */         int current = get();
/* 148 */         if (compareAndSet(current, newValue))
/* 149 */           return current; 
/*     */       } 
/*     */     }
/*     */     
/*     */     public final boolean compareAndSet(int expect, int update) {
/* 163 */       return this.engine.compareAndSwap(this.recid, Integer.valueOf(expect), Integer.valueOf(update), Serializer.INTEGER);
/*     */     }
/*     */     
/*     */     public final int getAndIncrement() {
/*     */       while (true) {
/* 174 */         int current = get();
/* 175 */         int next = current + 1;
/* 176 */         if (compareAndSet(current, next))
/* 177 */           return current; 
/*     */       } 
/*     */     }
/*     */     
/*     */     public final int getAndDecrement() {
/*     */       while (true) {
/* 188 */         int current = get();
/* 189 */         int next = current - 1;
/* 190 */         if (compareAndSet(current, next))
/* 191 */           return current; 
/*     */       } 
/*     */     }
/*     */     
/*     */     public final int getAndAdd(int delta) {
/*     */       while (true) {
/* 203 */         int current = get();
/* 204 */         int next = current + delta;
/* 205 */         if (compareAndSet(current, next))
/* 206 */           return current; 
/*     */       } 
/*     */     }
/*     */     
/*     */     public final int incrementAndGet() {
/*     */       while (true) {
/* 217 */         int current = get();
/* 218 */         int next = current + 1;
/* 219 */         if (compareAndSet(current, next))
/* 220 */           return next; 
/*     */       } 
/*     */     }
/*     */     
/*     */     public final int decrementAndGet() {
/*     */       while (true) {
/* 231 */         int current = get();
/* 232 */         int next = current - 1;
/* 233 */         if (compareAndSet(current, next))
/* 234 */           return next; 
/*     */       } 
/*     */     }
/*     */     
/*     */     public final int addAndGet(int delta) {
/*     */       while (true) {
/* 246 */         int current = get();
/* 247 */         int next = current + delta;
/* 248 */         if (compareAndSet(current, next))
/* 249 */           return next; 
/*     */       } 
/*     */     }
/*     */     
/*     */     public String toString() {
/* 258 */       return Integer.toString(get());
/*     */     }
/*     */     
/*     */     public int intValue() {
/* 263 */       return get();
/*     */     }
/*     */     
/*     */     public long longValue() {
/* 267 */       return get();
/*     */     }
/*     */     
/*     */     public float floatValue() {
/* 271 */       return get();
/*     */     }
/*     */     
/*     */     public double doubleValue() {
/* 275 */       return get();
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class Long extends Number {
/*     */     private static final long serialVersionUID = 2882620413591274781L;
/*     */     
/*     */     protected final Engine engine;
/*     */     
/*     */     protected final long recid;
/*     */     
/*     */     public Long(Engine engine, long recid) {
/* 297 */       this.engine = engine;
/* 298 */       this.recid = recid;
/*     */     }
/*     */     
/*     */     public long getRecid() {
/* 305 */       return this.recid;
/*     */     }
/*     */     
/*     */     public final long get() {
/* 315 */       return ((Long)this.engine.<Long>get(this.recid, Serializer.LONG)).longValue();
/*     */     }
/*     */     
/*     */     public final void set(long newValue) {
/* 324 */       this.engine.update(this.recid, Long.valueOf(newValue), Serializer.LONG);
/*     */     }
/*     */     
/*     */     public final long getAndSet(long newValue) {
/*     */       while (true) {
/* 336 */         long current = get();
/* 337 */         if (compareAndSet(current, newValue))
/* 338 */           return current; 
/*     */       } 
/*     */     }
/*     */     
/*     */     public final boolean compareAndSet(long expect, long update) {
/* 352 */       return this.engine.compareAndSwap(this.recid, Long.valueOf(expect), Long.valueOf(update), Serializer.LONG);
/*     */     }
/*     */     
/*     */     public final long getAndIncrement() {
/*     */       while (true) {
/* 363 */         long current = get();
/* 364 */         long next = current + 1L;
/* 365 */         if (compareAndSet(current, next))
/* 366 */           return current; 
/*     */       } 
/*     */     }
/*     */     
/*     */     public final long getAndDecrement() {
/*     */       while (true) {
/* 377 */         long current = get();
/* 378 */         long next = current - 1L;
/* 379 */         if (compareAndSet(current, next))
/* 380 */           return current; 
/*     */       } 
/*     */     }
/*     */     
/*     */     public final long getAndAdd(long delta) {
/*     */       while (true) {
/* 392 */         long current = get();
/* 393 */         long next = current + delta;
/* 394 */         if (compareAndSet(current, next))
/* 395 */           return current; 
/*     */       } 
/*     */     }
/*     */     
/*     */     public final long incrementAndGet() {
/*     */       while (true) {
/* 406 */         long current = get();
/* 407 */         long next = current + 1L;
/* 408 */         if (compareAndSet(current, next))
/* 409 */           return next; 
/*     */       } 
/*     */     }
/*     */     
/*     */     public final long decrementAndGet() {
/*     */       while (true) {
/* 420 */         long current = get();
/* 421 */         long next = current - 1L;
/* 422 */         if (compareAndSet(current, next))
/* 423 */           return next; 
/*     */       } 
/*     */     }
/*     */     
/*     */     public final long addAndGet(long delta) {
/*     */       while (true) {
/* 435 */         long current = get();
/* 436 */         long next = current + delta;
/* 437 */         if (compareAndSet(current, next))
/* 438 */           return next; 
/*     */       } 
/*     */     }
/*     */     
/*     */     public String toString() {
/* 447 */       return Long.toString(get());
/*     */     }
/*     */     
/*     */     public int intValue() {
/* 452 */       return (int)get();
/*     */     }
/*     */     
/*     */     public long longValue() {
/* 456 */       return get();
/*     */     }
/*     */     
/*     */     public float floatValue() {
/* 460 */       return (float)get();
/*     */     }
/*     */     
/*     */     public double doubleValue() {
/* 464 */       return get();
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class Boolean {
/*     */     protected final Engine engine;
/*     */     
/*     */     protected final long recid;
/*     */     
/*     */     public Boolean(Engine engine, long recid) {
/* 479 */       this.engine = engine;
/* 480 */       this.recid = recid;
/*     */     }
/*     */     
/*     */     public long getRecid() {
/* 487 */       return this.recid;
/*     */     }
/*     */     
/*     */     public final boolean get() {
/* 497 */       return ((Boolean)this.engine.<Boolean>get(this.recid, Serializer.BOOLEAN)).booleanValue();
/*     */     }
/*     */     
/*     */     public final boolean compareAndSet(boolean expect, boolean update) {
/* 510 */       return this.engine.compareAndSwap(this.recid, Boolean.valueOf(expect), Boolean.valueOf(update), Serializer.BOOLEAN);
/*     */     }
/*     */     
/*     */     public final void set(boolean newValue) {
/* 520 */       this.engine.update(this.recid, Boolean.valueOf(newValue), Serializer.BOOLEAN);
/*     */     }
/*     */     
/*     */     public final boolean getAndSet(boolean newValue) {
/*     */       while (true) {
/* 532 */         boolean current = get();
/* 533 */         if (compareAndSet(current, newValue))
/* 534 */           return current; 
/*     */       } 
/*     */     }
/*     */     
/*     */     public String toString() {
/* 543 */       return Boolean.toString(get());
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class String {
/*     */     protected final Engine engine;
/*     */     
/*     */     protected final long recid;
/*     */     
/*     */     public String(Engine engine, long recid) {
/* 557 */       this.engine = engine;
/* 558 */       this.recid = recid;
/*     */     }
/*     */     
/*     */     public long getRecid() {
/* 566 */       return this.recid;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 570 */       return get();
/*     */     }
/*     */     
/*     */     public final String get() {
/* 579 */       return this.engine.<String>get(this.recid, Serializer.STRING_NOSIZE);
/*     */     }
/*     */     
/*     */     public final boolean compareAndSet(String expect, String update) {
/* 592 */       return this.engine.compareAndSwap(this.recid, expect, update, Serializer.STRING_NOSIZE);
/*     */     }
/*     */     
/*     */     public final void set(String newValue) {
/* 602 */       this.engine.update(this.recid, newValue, Serializer.STRING_NOSIZE);
/*     */     }
/*     */     
/*     */     public final String getAndSet(String newValue) {
/*     */       while (true) {
/* 614 */         String current = get();
/* 615 */         if (compareAndSet(current, newValue))
/* 616 */           return current; 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class Var<E> {
/*     */     protected final Engine engine;
/*     */     
/*     */     protected final long recid;
/*     */     
/*     */     protected final Serializer<E> serializer;
/*     */     
/*     */     public Var(Engine engine, long recid, Serializer<E> serializer) {
/* 632 */       this.engine = engine;
/* 633 */       this.recid = recid;
/* 634 */       this.serializer = serializer;
/*     */     }
/*     */     
/*     */     protected Var(Engine engine, SerializerBase serializerBase, DataInput is, SerializerBase.FastArrayList<Object> objectStack) throws IOException {
/* 639 */       objectStack.add(this);
/* 640 */       this.engine = engine;
/* 641 */       this.recid = DataInput2.unpackLong(is);
/* 642 */       this.serializer = (Serializer<E>)serializerBase.deserialize(is, objectStack);
/*     */     }
/*     */     
/*     */     public long getRecid() {
/* 649 */       return this.recid;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 654 */       E v = get();
/* 655 */       return (v == null) ? null : v.toString();
/*     */     }
/*     */     
/*     */     public final E get() {
/* 664 */       return this.engine.get(this.recid, this.serializer);
/*     */     }
/*     */     
/*     */     public final boolean compareAndSet(E expect, E update) {
/* 677 */       return this.engine.compareAndSwap(this.recid, expect, update, this.serializer);
/*     */     }
/*     */     
/*     */     public final void set(E newValue) {
/* 687 */       this.engine.update(this.recid, newValue, this.serializer);
/*     */     }
/*     */     
/*     */     public final E getAndSet(E newValue) {
/*     */       while (true) {
/* 699 */         E current = get();
/* 700 */         if (compareAndSet(current, newValue))
/* 701 */           return current; 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\mapdb\Atomic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */