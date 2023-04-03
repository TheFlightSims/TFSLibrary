/*     */ package com.google.common.collect;
/*     */ 
/*     */ import com.google.common.annotations.GwtCompatible;
/*     */ import com.google.common.annotations.GwtIncompatible;
/*     */ import com.google.common.base.Ascii;
/*     */ import com.google.common.base.Equivalence;
/*     */ import com.google.common.base.Function;
/*     */ import com.google.common.base.Objects;
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.base.Throwables;
/*     */ import com.google.common.base.Ticker;
/*     */ import java.io.Serializable;
/*     */ import java.util.AbstractMap;
/*     */ import java.util.Collections;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.ConcurrentMap;
/*     */ import java.util.concurrent.ExecutionException;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ @GwtCompatible(emulated = true)
/*     */ public final class MapMaker extends GenericMapMaker<Object, Object> {
/*     */   private static final int DEFAULT_INITIAL_CAPACITY = 16;
/*     */   
/*     */   private static final int DEFAULT_CONCURRENCY_LEVEL = 4;
/*     */   
/*     */   private static final int DEFAULT_EXPIRATION_NANOS = 0;
/*     */   
/*     */   static final int UNSET_INT = -1;
/*     */   
/*     */   boolean useCustomMap;
/*     */   
/* 117 */   int initialCapacity = -1;
/*     */   
/* 118 */   int concurrencyLevel = -1;
/*     */   
/* 119 */   int maximumSize = -1;
/*     */   
/*     */   MapMakerInternalMap.Strength keyStrength;
/*     */   
/*     */   MapMakerInternalMap.Strength valueStrength;
/*     */   
/* 124 */   long expireAfterWriteNanos = -1L;
/*     */   
/* 125 */   long expireAfterAccessNanos = -1L;
/*     */   
/*     */   RemovalCause nullRemovalCause;
/*     */   
/*     */   Equivalence<Object> keyEquivalence;
/*     */   
/*     */   Ticker ticker;
/*     */   
/*     */   @GwtIncompatible("To be supported")
/*     */   MapMaker keyEquivalence(Equivalence<Object> equivalence) {
/* 149 */     Preconditions.checkState((this.keyEquivalence == null), "key equivalence was already set to %s", new Object[] { this.keyEquivalence });
/* 150 */     this.keyEquivalence = (Equivalence<Object>)Preconditions.checkNotNull(equivalence);
/* 151 */     this.useCustomMap = true;
/* 152 */     return this;
/*     */   }
/*     */   
/*     */   Equivalence<Object> getKeyEquivalence() {
/* 156 */     return (Equivalence<Object>)Objects.firstNonNull(this.keyEquivalence, getKeyStrength().defaultEquivalence());
/*     */   }
/*     */   
/*     */   public MapMaker initialCapacity(int initialCapacity) {
/* 171 */     Preconditions.checkState((this.initialCapacity == -1), "initial capacity was already set to %s", new Object[] { Integer.valueOf(this.initialCapacity) });
/* 173 */     Preconditions.checkArgument((initialCapacity >= 0));
/* 174 */     this.initialCapacity = initialCapacity;
/* 175 */     return this;
/*     */   }
/*     */   
/*     */   int getInitialCapacity() {
/* 179 */     return (this.initialCapacity == -1) ? 16 : this.initialCapacity;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   MapMaker maximumSize(int size) {
/* 208 */     Preconditions.checkState((this.maximumSize == -1), "maximum size was already set to %s", new Object[] { Integer.valueOf(this.maximumSize) });
/* 210 */     Preconditions.checkArgument((size >= 0), "maximum size must not be negative");
/* 211 */     this.maximumSize = size;
/* 212 */     this.useCustomMap = true;
/* 213 */     if (this.maximumSize == 0)
/* 215 */       this.nullRemovalCause = RemovalCause.SIZE; 
/* 217 */     return this;
/*     */   }
/*     */   
/*     */   public MapMaker concurrencyLevel(int concurrencyLevel) {
/* 241 */     Preconditions.checkState((this.concurrencyLevel == -1), "concurrency level was already set to %s", new Object[] { Integer.valueOf(this.concurrencyLevel) });
/* 243 */     Preconditions.checkArgument((concurrencyLevel > 0));
/* 244 */     this.concurrencyLevel = concurrencyLevel;
/* 245 */     return this;
/*     */   }
/*     */   
/*     */   int getConcurrencyLevel() {
/* 249 */     return (this.concurrencyLevel == -1) ? 4 : this.concurrencyLevel;
/*     */   }
/*     */   
/*     */   @GwtIncompatible("java.lang.ref.WeakReference")
/*     */   public MapMaker weakKeys() {
/* 266 */     return setKeyStrength(MapMakerInternalMap.Strength.WEAK);
/*     */   }
/*     */   
/*     */   MapMaker setKeyStrength(MapMakerInternalMap.Strength strength) {
/* 270 */     Preconditions.checkState((this.keyStrength == null), "Key strength was already set to %s", new Object[] { this.keyStrength });
/* 271 */     this.keyStrength = (MapMakerInternalMap.Strength)Preconditions.checkNotNull(strength);
/* 272 */     Preconditions.checkArgument((this.keyStrength != MapMakerInternalMap.Strength.SOFT), "Soft keys are not supported");
/* 273 */     if (strength != MapMakerInternalMap.Strength.STRONG)
/* 275 */       this.useCustomMap = true; 
/* 277 */     return this;
/*     */   }
/*     */   
/*     */   MapMakerInternalMap.Strength getKeyStrength() {
/* 281 */     return (MapMakerInternalMap.Strength)Objects.firstNonNull(this.keyStrength, MapMakerInternalMap.Strength.STRONG);
/*     */   }
/*     */   
/*     */   @GwtIncompatible("java.lang.ref.WeakReference")
/*     */   public MapMaker weakValues() {
/* 304 */     return setValueStrength(MapMakerInternalMap.Strength.WEAK);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   @GwtIncompatible("java.lang.ref.SoftReference")
/*     */   public MapMaker softValues() {
/* 336 */     return setValueStrength(MapMakerInternalMap.Strength.SOFT);
/*     */   }
/*     */   
/*     */   MapMaker setValueStrength(MapMakerInternalMap.Strength strength) {
/* 340 */     Preconditions.checkState((this.valueStrength == null), "Value strength was already set to %s", new Object[] { this.valueStrength });
/* 341 */     this.valueStrength = (MapMakerInternalMap.Strength)Preconditions.checkNotNull(strength);
/* 342 */     if (strength != MapMakerInternalMap.Strength.STRONG)
/* 344 */       this.useCustomMap = true; 
/* 346 */     return this;
/*     */   }
/*     */   
/*     */   MapMakerInternalMap.Strength getValueStrength() {
/* 350 */     return (MapMakerInternalMap.Strength)Objects.firstNonNull(this.valueStrength, MapMakerInternalMap.Strength.STRONG);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   MapMaker expireAfterWrite(long duration, TimeUnit unit) {
/* 381 */     checkExpiration(duration, unit);
/* 382 */     this.expireAfterWriteNanos = unit.toNanos(duration);
/* 383 */     if (duration == 0L && this.nullRemovalCause == null)
/* 385 */       this.nullRemovalCause = RemovalCause.EXPIRED; 
/* 387 */     this.useCustomMap = true;
/* 388 */     return this;
/*     */   }
/*     */   
/*     */   private void checkExpiration(long duration, TimeUnit unit) {
/* 392 */     Preconditions.checkState((this.expireAfterWriteNanos == -1L), "expireAfterWrite was already set to %s ns", new Object[] { Long.valueOf(this.expireAfterWriteNanos) });
/* 394 */     Preconditions.checkState((this.expireAfterAccessNanos == -1L), "expireAfterAccess was already set to %s ns", new Object[] { Long.valueOf(this.expireAfterAccessNanos) });
/* 396 */     Preconditions.checkArgument((duration >= 0L), "duration cannot be negative: %s %s", new Object[] { Long.valueOf(duration), unit });
/*     */   }
/*     */   
/*     */   long getExpireAfterWriteNanos() {
/* 400 */     return (this.expireAfterWriteNanos == -1L) ? 0L : this.expireAfterWriteNanos;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   @GwtIncompatible("To be supported")
/*     */   MapMaker expireAfterAccess(long duration, TimeUnit unit) {
/* 432 */     checkExpiration(duration, unit);
/* 433 */     this.expireAfterAccessNanos = unit.toNanos(duration);
/* 434 */     if (duration == 0L && this.nullRemovalCause == null)
/* 436 */       this.nullRemovalCause = RemovalCause.EXPIRED; 
/* 438 */     this.useCustomMap = true;
/* 439 */     return this;
/*     */   }
/*     */   
/*     */   long getExpireAfterAccessNanos() {
/* 443 */     return (this.expireAfterAccessNanos == -1L) ? 0L : this.expireAfterAccessNanos;
/*     */   }
/*     */   
/*     */   Ticker getTicker() {
/* 448 */     return (Ticker)Objects.firstNonNull(this.ticker, Ticker.systemTicker());
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   @GwtIncompatible("To be supported")
/*     */   <K, V> GenericMapMaker<K, V> removalListener(RemovalListener<K, V> listener) {
/* 483 */     Preconditions.checkState((this.removalListener == null));
/* 487 */     GenericMapMaker<K, V> me = this;
/* 488 */     me.removalListener = (RemovalListener<K, V>)Preconditions.checkNotNull(listener);
/* 489 */     this.useCustomMap = true;
/* 490 */     return me;
/*     */   }
/*     */   
/*     */   public <K, V> ConcurrentMap<K, V> makeMap() {
/* 507 */     if (!this.useCustomMap)
/* 508 */       return new ConcurrentHashMap<K, V>(getInitialCapacity(), 0.75F, getConcurrencyLevel()); 
/* 510 */     return (this.nullRemovalCause == null) ? new MapMakerInternalMap<K, V>(this) : new NullConcurrentMap<K, V>(this);
/*     */   }
/*     */   
/*     */   @GwtIncompatible("MapMakerInternalMap")
/*     */   <K, V> MapMakerInternalMap<K, V> makeCustomMap() {
/* 522 */     return new MapMakerInternalMap<K, V>(this);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   <K, V> ConcurrentMap<K, V> makeComputingMap(Function<? super K, ? extends V> computingFunction) {
/* 586 */     return (this.nullRemovalCause == null) ? new ComputingMapAdapter<K, V>(this, computingFunction) : new NullComputingConcurrentMap<K, V>(this, computingFunction);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 597 */     Objects.ToStringHelper s = Objects.toStringHelper(this);
/* 598 */     if (this.initialCapacity != -1)
/* 599 */       s.add("initialCapacity", this.initialCapacity); 
/* 601 */     if (this.concurrencyLevel != -1)
/* 602 */       s.add("concurrencyLevel", this.concurrencyLevel); 
/* 604 */     if (this.maximumSize != -1)
/* 605 */       s.add("maximumSize", this.maximumSize); 
/* 607 */     if (this.expireAfterWriteNanos != -1L)
/* 608 */       s.add("expireAfterWrite", this.expireAfterWriteNanos + "ns"); 
/* 610 */     if (this.expireAfterAccessNanos != -1L)
/* 611 */       s.add("expireAfterAccess", this.expireAfterAccessNanos + "ns"); 
/* 613 */     if (this.keyStrength != null)
/* 614 */       s.add("keyStrength", Ascii.toLowerCase(this.keyStrength.toString())); 
/* 616 */     if (this.valueStrength != null)
/* 617 */       s.add("valueStrength", Ascii.toLowerCase(this.valueStrength.toString())); 
/* 619 */     if (this.keyEquivalence != null)
/* 620 */       s.addValue("keyEquivalence"); 
/* 622 */     if (this.removalListener != null)
/* 623 */       s.addValue("removalListener"); 
/* 625 */     return s.toString();
/*     */   }
/*     */   
/*     */   static interface RemovalListener<K, V> {
/*     */     void onRemoval(MapMaker.RemovalNotification<K, V> param1RemovalNotification);
/*     */   }
/*     */   
/*     */   static final class RemovalNotification<K, V> extends ImmutableEntry<K, V> {
/*     */     private static final long serialVersionUID = 0L;
/*     */     
/*     */     private final MapMaker.RemovalCause cause;
/*     */     
/*     */     RemovalNotification(@Nullable K key, @Nullable V value, MapMaker.RemovalCause cause) {
/* 663 */       super(key, value);
/* 664 */       this.cause = cause;
/*     */     }
/*     */     
/*     */     public MapMaker.RemovalCause getCause() {
/* 671 */       return this.cause;
/*     */     }
/*     */     
/*     */     public boolean wasEvicted() {
/* 679 */       return this.cause.wasEvicted();
/*     */     }
/*     */   }
/*     */   
/*     */   enum RemovalCause {
/* 691 */     EXPLICIT {
/*     */       boolean wasEvicted() {
/* 694 */         return false;
/*     */       }
/*     */     },
/* 704 */     REPLACED {
/*     */       boolean wasEvicted() {
/* 707 */         return false;
/*     */       }
/*     */     },
/* 715 */     COLLECTED {
/*     */       boolean wasEvicted() {
/* 718 */         return true;
/*     */       }
/*     */     },
/* 726 */     EXPIRED {
/*     */       boolean wasEvicted() {
/* 729 */         return true;
/*     */       }
/*     */     },
/* 737 */     SIZE {
/*     */       boolean wasEvicted() {
/* 740 */         return true;
/*     */       }
/*     */     };
/*     */     
/*     */     abstract boolean wasEvicted();
/*     */   }
/*     */   
/*     */   static class NullConcurrentMap<K, V> extends AbstractMap<K, V> implements ConcurrentMap<K, V>, Serializable {
/*     */     private static final long serialVersionUID = 0L;
/*     */     
/*     */     private final MapMaker.RemovalListener<K, V> removalListener;
/*     */     
/*     */     private final MapMaker.RemovalCause removalCause;
/*     */     
/*     */     NullConcurrentMap(MapMaker mapMaker) {
/* 760 */       this.removalListener = mapMaker.getRemovalListener();
/* 761 */       this.removalCause = mapMaker.nullRemovalCause;
/*     */     }
/*     */     
/*     */     public boolean containsKey(@Nullable Object key) {
/* 768 */       return false;
/*     */     }
/*     */     
/*     */     public boolean containsValue(@Nullable Object value) {
/* 773 */       return false;
/*     */     }
/*     */     
/*     */     public V get(@Nullable Object key) {
/* 778 */       return null;
/*     */     }
/*     */     
/*     */     void notifyRemoval(K key, V value) {
/* 782 */       MapMaker.RemovalNotification<K, V> notification = new MapMaker.RemovalNotification<K, V>(key, value, this.removalCause);
/* 784 */       this.removalListener.onRemoval(notification);
/*     */     }
/*     */     
/*     */     public V put(K key, V value) {
/* 789 */       Preconditions.checkNotNull(key);
/* 790 */       Preconditions.checkNotNull(value);
/* 791 */       notifyRemoval(key, value);
/* 792 */       return null;
/*     */     }
/*     */     
/*     */     public V putIfAbsent(K key, V value) {
/* 797 */       return put(key, value);
/*     */     }
/*     */     
/*     */     public V remove(@Nullable Object key) {
/* 802 */       return null;
/*     */     }
/*     */     
/*     */     public boolean remove(@Nullable Object key, @Nullable Object value) {
/* 807 */       return false;
/*     */     }
/*     */     
/*     */     public V replace(K key, V value) {
/* 812 */       Preconditions.checkNotNull(key);
/* 813 */       Preconditions.checkNotNull(value);
/* 814 */       return null;
/*     */     }
/*     */     
/*     */     public boolean replace(K key, @Nullable V oldValue, V newValue) {
/* 819 */       Preconditions.checkNotNull(key);
/* 820 */       Preconditions.checkNotNull(newValue);
/* 821 */       return false;
/*     */     }
/*     */     
/*     */     public Set<Map.Entry<K, V>> entrySet() {
/* 826 */       return Collections.emptySet();
/*     */     }
/*     */   }
/*     */   
/*     */   static final class NullComputingConcurrentMap<K, V> extends NullConcurrentMap<K, V> {
/*     */     private static final long serialVersionUID = 0L;
/*     */     
/*     */     final Function<? super K, ? extends V> computingFunction;
/*     */     
/*     */     NullComputingConcurrentMap(MapMaker mapMaker, Function<? super K, ? extends V> computingFunction) {
/* 838 */       super(mapMaker);
/* 839 */       this.computingFunction = (Function<? super K, ? extends V>)Preconditions.checkNotNull(computingFunction);
/*     */     }
/*     */     
/*     */     public V get(Object k) {
/* 845 */       K key = (K)k;
/* 846 */       V value = compute(key);
/* 847 */       Preconditions.checkNotNull(value, "%s returned null for key %s.", new Object[] { this.computingFunction, key });
/* 848 */       notifyRemoval(key, value);
/* 849 */       return value;
/*     */     }
/*     */     
/*     */     private V compute(K key) {
/* 853 */       Preconditions.checkNotNull(key);
/*     */       try {
/* 855 */         return (V)this.computingFunction.apply(key);
/* 856 */       } catch (ComputationException e) {
/* 857 */         throw e;
/* 858 */       } catch (Throwable t) {
/* 859 */         throw new ComputationException(t);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static final class ComputingMapAdapter<K, V> extends ComputingConcurrentHashMap<K, V> implements Serializable {
/*     */     private static final long serialVersionUID = 0L;
/*     */     
/*     */     ComputingMapAdapter(MapMaker mapMaker, Function<? super K, ? extends V> computingFunction) {
/* 878 */       super(mapMaker, computingFunction);
/*     */     }
/*     */     
/*     */     public V get(Object key) {
/*     */       V value;
/*     */       try {
/* 886 */         value = getOrCompute((K)key);
/* 887 */       } catch (ExecutionException e) {
/* 888 */         Throwable cause = e.getCause();
/* 889 */         Throwables.propagateIfInstanceOf(cause, ComputationException.class);
/* 890 */         throw new ComputationException(cause);
/*     */       } 
/* 893 */       if (value == null)
/* 894 */         throw new NullPointerException(this.computingFunction + " returned null for key " + key + "."); 
/* 896 */       return value;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\common\collect\MapMaker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */