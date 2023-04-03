/*      */ package com.google.common.cache;
/*      */ 
/*      */ import com.google.common.annotations.GwtCompatible;
/*      */ import com.google.common.annotations.GwtIncompatible;
/*      */ import com.google.common.annotations.VisibleForTesting;
/*      */ import com.google.common.base.Equivalence;
/*      */ import com.google.common.base.Function;
/*      */ import com.google.common.base.Preconditions;
/*      */ import com.google.common.base.Stopwatch;
/*      */ import com.google.common.base.Ticker;
/*      */ import com.google.common.collect.AbstractSequentialIterator;
/*      */ import com.google.common.collect.ImmutableMap;
/*      */ import com.google.common.collect.Iterators;
/*      */ import com.google.common.collect.Maps;
/*      */ import com.google.common.collect.Sets;
/*      */ import com.google.common.primitives.Ints;
/*      */ import com.google.common.util.concurrent.ExecutionError;
/*      */ import com.google.common.util.concurrent.Futures;
/*      */ import com.google.common.util.concurrent.ListenableFuture;
/*      */ import com.google.common.util.concurrent.ListeningExecutorService;
/*      */ import com.google.common.util.concurrent.MoreExecutors;
/*      */ import com.google.common.util.concurrent.SettableFuture;
/*      */ import com.google.common.util.concurrent.UncheckedExecutionException;
/*      */ import com.google.common.util.concurrent.Uninterruptibles;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.Serializable;
/*      */ import java.lang.ref.Reference;
/*      */ import java.lang.ref.ReferenceQueue;
/*      */ import java.lang.ref.SoftReference;
/*      */ import java.lang.ref.WeakReference;
/*      */ import java.util.AbstractCollection;
/*      */ import java.util.AbstractMap;
/*      */ import java.util.AbstractQueue;
/*      */ import java.util.AbstractSet;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Queue;
/*      */ import java.util.Set;
/*      */ import java.util.concurrent.Callable;
/*      */ import java.util.concurrent.ConcurrentLinkedQueue;
/*      */ import java.util.concurrent.ConcurrentMap;
/*      */ import java.util.concurrent.ExecutionException;
/*      */ import java.util.concurrent.Executor;
/*      */ import java.util.concurrent.Future;
/*      */ import java.util.concurrent.TimeUnit;
/*      */ import java.util.concurrent.atomic.AtomicInteger;
/*      */ import java.util.concurrent.atomic.AtomicReferenceArray;
/*      */ import java.util.concurrent.locks.ReentrantLock;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.Logger;
/*      */ import javax.annotation.Nullable;
/*      */ import javax.annotation.concurrent.GuardedBy;
/*      */ 
/*      */ @GwtCompatible(emulated = true)
/*      */ class LocalCache<K, V> extends AbstractMap<K, V> implements ConcurrentMap<K, V> {
/*      */   static final int MAXIMUM_CAPACITY = 1073741824;
/*      */   
/*      */   static final int MAX_SEGMENTS = 65536;
/*      */   
/*      */   static final int CONTAINS_VALUE_RETRIES = 3;
/*      */   
/*      */   static final int DRAIN_THRESHOLD = 63;
/*      */   
/*      */   static final int DRAIN_MAX = 16;
/*      */   
/*  158 */   static final Logger logger = Logger.getLogger(LocalCache.class.getName());
/*      */   
/*  160 */   static final ListeningExecutorService sameThreadExecutor = MoreExecutors.sameThreadExecutor();
/*      */   
/*      */   final int segmentMask;
/*      */   
/*      */   final int segmentShift;
/*      */   
/*      */   final Segment<K, V>[] segments;
/*      */   
/*      */   final int concurrencyLevel;
/*      */   
/*      */   final Equivalence<Object> keyEquivalence;
/*      */   
/*      */   final Equivalence<Object> valueEquivalence;
/*      */   
/*      */   final Strength keyStrength;
/*      */   
/*      */   final Strength valueStrength;
/*      */   
/*      */   final long maxWeight;
/*      */   
/*      */   final Weigher<K, V> weigher;
/*      */   
/*      */   final long expireAfterAccessNanos;
/*      */   
/*      */   final long expireAfterWriteNanos;
/*      */   
/*      */   final long refreshNanos;
/*      */   
/*      */   final Queue<RemovalNotification<K, V>> removalNotificationQueue;
/*      */   
/*      */   final RemovalListener<K, V> removalListener;
/*      */   
/*      */   final Ticker ticker;
/*      */   
/*      */   final EntryFactory entryFactory;
/*      */   
/*      */   final AbstractCache.StatsCounter globalStatsCounter;
/*      */   
/*      */   @Nullable
/*      */   final CacheLoader<? super K, V> defaultLoader;
/*      */   
/*      */   LocalCache(CacheBuilder<? super K, ? super V> builder, @Nullable CacheLoader<? super K, V> loader) {
/*  240 */     this.concurrencyLevel = Math.min(builder.getConcurrencyLevel(), 65536);
/*  242 */     this.keyStrength = builder.getKeyStrength();
/*  243 */     this.valueStrength = builder.getValueStrength();
/*  245 */     this.keyEquivalence = builder.getKeyEquivalence();
/*  246 */     this.valueEquivalence = builder.getValueEquivalence();
/*  248 */     this.maxWeight = builder.getMaximumWeight();
/*  249 */     this.weigher = builder.getWeigher();
/*  250 */     this.expireAfterAccessNanos = builder.getExpireAfterAccessNanos();
/*  251 */     this.expireAfterWriteNanos = builder.getExpireAfterWriteNanos();
/*  252 */     this.refreshNanos = builder.getRefreshNanos();
/*  254 */     this.removalListener = builder.getRemovalListener();
/*  255 */     this.removalNotificationQueue = (this.removalListener == CacheBuilder.NullListener.INSTANCE) ? discardingQueue() : new ConcurrentLinkedQueue<RemovalNotification<K, V>>();
/*  259 */     this.ticker = builder.getTicker(recordsTime());
/*  260 */     this.entryFactory = EntryFactory.getFactory(this.keyStrength, usesAccessEntries(), usesWriteEntries());
/*  261 */     this.globalStatsCounter = (AbstractCache.StatsCounter)builder.getStatsCounterSupplier().get();
/*  262 */     this.defaultLoader = loader;
/*  264 */     int initialCapacity = Math.min(builder.getInitialCapacity(), 1073741824);
/*  265 */     if (evictsBySize() && !customWeigher())
/*  266 */       initialCapacity = Math.min(initialCapacity, (int)this.maxWeight); 
/*  274 */     int segmentShift = 0;
/*  275 */     int segmentCount = 1;
/*  277 */     while (segmentCount < this.concurrencyLevel && (!evictsBySize() || (segmentCount * 20) <= this.maxWeight)) {
/*  278 */       segmentShift++;
/*  279 */       segmentCount <<= 1;
/*      */     } 
/*  281 */     this.segmentShift = 32 - segmentShift;
/*  282 */     this.segmentMask = segmentCount - 1;
/*  284 */     this.segments = newSegmentArray(segmentCount);
/*  286 */     int segmentCapacity = initialCapacity / segmentCount;
/*  287 */     if (segmentCapacity * segmentCount < initialCapacity)
/*  288 */       segmentCapacity++; 
/*  291 */     int segmentSize = 1;
/*  292 */     while (segmentSize < segmentCapacity)
/*  293 */       segmentSize <<= 1; 
/*  296 */     if (evictsBySize()) {
/*  298 */       long maxSegmentWeight = this.maxWeight / segmentCount + 1L;
/*  299 */       long remainder = this.maxWeight % segmentCount;
/*  300 */       for (int i = 0; i < this.segments.length; i++) {
/*  301 */         if (i == remainder)
/*  302 */           maxSegmentWeight--; 
/*  304 */         this.segments[i] = createSegment(segmentSize, maxSegmentWeight, (AbstractCache.StatsCounter)builder.getStatsCounterSupplier().get());
/*      */       } 
/*      */     } else {
/*  308 */       for (int i = 0; i < this.segments.length; i++)
/*  309 */         this.segments[i] = createSegment(segmentSize, -1L, (AbstractCache.StatsCounter)builder.getStatsCounterSupplier().get()); 
/*      */     } 
/*      */   }
/*      */   
/*      */   boolean evictsBySize() {
/*  316 */     return (this.maxWeight >= 0L);
/*      */   }
/*      */   
/*      */   boolean customWeigher() {
/*  320 */     return (this.weigher != CacheBuilder.OneWeigher.INSTANCE);
/*      */   }
/*      */   
/*      */   boolean expires() {
/*  324 */     return (expiresAfterWrite() || expiresAfterAccess());
/*      */   }
/*      */   
/*      */   boolean expiresAfterWrite() {
/*  328 */     return (this.expireAfterWriteNanos > 0L);
/*      */   }
/*      */   
/*      */   boolean expiresAfterAccess() {
/*  332 */     return (this.expireAfterAccessNanos > 0L);
/*      */   }
/*      */   
/*      */   boolean refreshes() {
/*  336 */     return (this.refreshNanos > 0L);
/*      */   }
/*      */   
/*      */   boolean usesAccessQueue() {
/*  340 */     return (expiresAfterAccess() || evictsBySize());
/*      */   }
/*      */   
/*      */   boolean usesWriteQueue() {
/*  344 */     return expiresAfterWrite();
/*      */   }
/*      */   
/*      */   boolean recordsWrite() {
/*  348 */     return (expiresAfterWrite() || refreshes());
/*      */   }
/*      */   
/*      */   boolean recordsAccess() {
/*  352 */     return expiresAfterAccess();
/*      */   }
/*      */   
/*      */   boolean recordsTime() {
/*  356 */     return (recordsWrite() || recordsAccess());
/*      */   }
/*      */   
/*      */   boolean usesWriteEntries() {
/*  360 */     return (usesWriteQueue() || recordsWrite());
/*      */   }
/*      */   
/*      */   boolean usesAccessEntries() {
/*  364 */     return (usesAccessQueue() || recordsAccess());
/*      */   }
/*      */   
/*      */   boolean usesKeyReferences() {
/*  368 */     return (this.keyStrength != Strength.STRONG);
/*      */   }
/*      */   
/*      */   boolean usesValueReferences() {
/*  372 */     return (this.valueStrength != Strength.STRONG);
/*      */   }
/*      */   
/*      */   enum Strength {
/*  381 */     STRONG {
/*      */       <K, V> LocalCache.ValueReference<K, V> referenceValue(LocalCache.Segment<K, V> segment, LocalCache.ReferenceEntry<K, V> entry, V value, int weight) {
/*  385 */         return (weight == 1) ? new LocalCache.StrongValueReference<K, V>(value) : new LocalCache.WeightedStrongValueReference<K, V>(value, weight);
/*      */       }
/*      */       
/*      */       Equivalence<Object> defaultEquivalence() {
/*  392 */         return Equivalence.equals();
/*      */       }
/*      */     },
/*  396 */     SOFT {
/*      */       <K, V> LocalCache.ValueReference<K, V> referenceValue(LocalCache.Segment<K, V> segment, LocalCache.ReferenceEntry<K, V> entry, V value, int weight) {
/*  400 */         return (weight == 1) ? new LocalCache.SoftValueReference<K, V>(segment.valueReferenceQueue, value, entry) : new LocalCache.WeightedSoftValueReference<K, V>(segment.valueReferenceQueue, value, entry, weight);
/*      */       }
/*      */       
/*      */       Equivalence<Object> defaultEquivalence() {
/*  408 */         return Equivalence.identity();
/*      */       }
/*      */     },
/*  412 */     WEAK {
/*      */       <K, V> LocalCache.ValueReference<K, V> referenceValue(LocalCache.Segment<K, V> segment, LocalCache.ReferenceEntry<K, V> entry, V value, int weight) {
/*  416 */         return (weight == 1) ? new LocalCache.WeakValueReference<K, V>(segment.valueReferenceQueue, value, entry) : new LocalCache.WeightedWeakValueReference<K, V>(segment.valueReferenceQueue, value, entry, weight);
/*      */       }
/*      */       
/*      */       Equivalence<Object> defaultEquivalence() {
/*  424 */         return Equivalence.identity();
/*      */       }
/*      */     };
/*      */     
/*      */     abstract <K, V> LocalCache.ValueReference<K, V> referenceValue(LocalCache.Segment<K, V> param1Segment, LocalCache.ReferenceEntry<K, V> param1ReferenceEntry, V param1V, int param1Int);
/*      */     
/*      */     abstract Equivalence<Object> defaultEquivalence();
/*      */   }
/*      */   
/*      */   enum EntryFactory {
/*  446 */     STRONG {
/*      */       <K, V> LocalCache.ReferenceEntry<K, V> newEntry(LocalCache.Segment<K, V> segment, K key, int hash, @Nullable LocalCache.ReferenceEntry<K, V> next) {
/*  450 */         return new LocalCache.StrongEntry<K, V>(key, hash, next);
/*      */       }
/*      */     },
/*  453 */     STRONG_ACCESS {
/*      */       <K, V> LocalCache.ReferenceEntry<K, V> newEntry(LocalCache.Segment<K, V> segment, K key, int hash, @Nullable LocalCache.ReferenceEntry<K, V> next) {
/*  457 */         return new LocalCache.StrongAccessEntry<K, V>(key, hash, next);
/*      */       }
/*      */       
/*      */       <K, V> LocalCache.ReferenceEntry<K, V> copyEntry(LocalCache.Segment<K, V> segment, LocalCache.ReferenceEntry<K, V> original, LocalCache.ReferenceEntry<K, V> newNext) {
/*  463 */         LocalCache.ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext);
/*  464 */         copyAccessEntry(original, newEntry);
/*  465 */         return newEntry;
/*      */       }
/*      */     },
/*  468 */     STRONG_WRITE {
/*      */       <K, V> LocalCache.ReferenceEntry<K, V> newEntry(LocalCache.Segment<K, V> segment, K key, int hash, @Nullable LocalCache.ReferenceEntry<K, V> next) {
/*  472 */         return new LocalCache.StrongWriteEntry<K, V>(key, hash, next);
/*      */       }
/*      */       
/*      */       <K, V> LocalCache.ReferenceEntry<K, V> copyEntry(LocalCache.Segment<K, V> segment, LocalCache.ReferenceEntry<K, V> original, LocalCache.ReferenceEntry<K, V> newNext) {
/*  478 */         LocalCache.ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext);
/*  479 */         copyWriteEntry(original, newEntry);
/*  480 */         return newEntry;
/*      */       }
/*      */     },
/*  483 */     STRONG_ACCESS_WRITE {
/*      */       <K, V> LocalCache.ReferenceEntry<K, V> newEntry(LocalCache.Segment<K, V> segment, K key, int hash, @Nullable LocalCache.ReferenceEntry<K, V> next) {
/*  487 */         return new LocalCache.StrongAccessWriteEntry<K, V>(key, hash, next);
/*      */       }
/*      */       
/*      */       <K, V> LocalCache.ReferenceEntry<K, V> copyEntry(LocalCache.Segment<K, V> segment, LocalCache.ReferenceEntry<K, V> original, LocalCache.ReferenceEntry<K, V> newNext) {
/*  493 */         LocalCache.ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext);
/*  494 */         copyAccessEntry(original, newEntry);
/*  495 */         copyWriteEntry(original, newEntry);
/*  496 */         return newEntry;
/*      */       }
/*      */     },
/*  500 */     WEAK {
/*      */       <K, V> LocalCache.ReferenceEntry<K, V> newEntry(LocalCache.Segment<K, V> segment, K key, int hash, @Nullable LocalCache.ReferenceEntry<K, V> next) {
/*  504 */         return new LocalCache.WeakEntry<K, V>(segment.keyReferenceQueue, key, hash, next);
/*      */       }
/*      */     },
/*  507 */     WEAK_ACCESS {
/*      */       <K, V> LocalCache.ReferenceEntry<K, V> newEntry(LocalCache.Segment<K, V> segment, K key, int hash, @Nullable LocalCache.ReferenceEntry<K, V> next) {
/*  511 */         return new LocalCache.WeakAccessEntry<K, V>(segment.keyReferenceQueue, key, hash, next);
/*      */       }
/*      */       
/*      */       <K, V> LocalCache.ReferenceEntry<K, V> copyEntry(LocalCache.Segment<K, V> segment, LocalCache.ReferenceEntry<K, V> original, LocalCache.ReferenceEntry<K, V> newNext) {
/*  517 */         LocalCache.ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext);
/*  518 */         copyAccessEntry(original, newEntry);
/*  519 */         return newEntry;
/*      */       }
/*      */     },
/*  522 */     WEAK_WRITE {
/*      */       <K, V> LocalCache.ReferenceEntry<K, V> newEntry(LocalCache.Segment<K, V> segment, K key, int hash, @Nullable LocalCache.ReferenceEntry<K, V> next) {
/*  526 */         return new LocalCache.WeakWriteEntry<K, V>(segment.keyReferenceQueue, key, hash, next);
/*      */       }
/*      */       
/*      */       <K, V> LocalCache.ReferenceEntry<K, V> copyEntry(LocalCache.Segment<K, V> segment, LocalCache.ReferenceEntry<K, V> original, LocalCache.ReferenceEntry<K, V> newNext) {
/*  532 */         LocalCache.ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext);
/*  533 */         copyWriteEntry(original, newEntry);
/*  534 */         return newEntry;
/*      */       }
/*      */     },
/*  537 */     WEAK_ACCESS_WRITE {
/*      */       <K, V> LocalCache.ReferenceEntry<K, V> newEntry(LocalCache.Segment<K, V> segment, K key, int hash, @Nullable LocalCache.ReferenceEntry<K, V> next) {
/*  541 */         return new LocalCache.WeakAccessWriteEntry<K, V>(segment.keyReferenceQueue, key, hash, next);
/*      */       }
/*      */       
/*      */       <K, V> LocalCache.ReferenceEntry<K, V> copyEntry(LocalCache.Segment<K, V> segment, LocalCache.ReferenceEntry<K, V> original, LocalCache.ReferenceEntry<K, V> newNext) {
/*  547 */         LocalCache.ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext);
/*  548 */         copyAccessEntry(original, newEntry);
/*  549 */         copyWriteEntry(original, newEntry);
/*  550 */         return newEntry;
/*      */       }
/*      */     };
/*      */     
/*      */     static final int ACCESS_MASK = 1;
/*      */     
/*      */     static final int WRITE_MASK = 2;
/*      */     
/*      */     static final int WEAK_MASK = 4;
/*      */     
/*  564 */     static final EntryFactory[] factories = new EntryFactory[] { STRONG, STRONG_ACCESS, STRONG_WRITE, STRONG_ACCESS_WRITE, WEAK, WEAK_ACCESS, WEAK_WRITE, WEAK_ACCESS_WRITE };
/*      */     
/*      */     static {
/*      */     
/*      */     }
/*      */     
/*      */     static EntryFactory getFactory(LocalCache.Strength keyStrength, boolean usesAccessQueue, boolean usesWriteQueue) {
/*  571 */       int flags = ((keyStrength == LocalCache.Strength.WEAK) ? 4 : 0) | (usesAccessQueue ? 1 : 0) | (usesWriteQueue ? 2 : 0);
/*  574 */       return factories[flags];
/*      */     }
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     <K, V> LocalCache.ReferenceEntry<K, V> copyEntry(LocalCache.Segment<K, V> segment, LocalCache.ReferenceEntry<K, V> original, LocalCache.ReferenceEntry<K, V> newNext) {
/*  597 */       return newEntry(segment, original.getKey(), original.getHash(), newNext);
/*      */     }
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     <K, V> void copyAccessEntry(LocalCache.ReferenceEntry<K, V> original, LocalCache.ReferenceEntry<K, V> newEntry) {
/*  604 */       newEntry.setAccessTime(original.getAccessTime());
/*  606 */       LocalCache.connectAccessOrder(original.getPreviousInAccessQueue(), newEntry);
/*  607 */       LocalCache.connectAccessOrder(newEntry, original.getNextInAccessQueue());
/*  609 */       LocalCache.nullifyAccessOrder(original);
/*      */     }
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     <K, V> void copyWriteEntry(LocalCache.ReferenceEntry<K, V> original, LocalCache.ReferenceEntry<K, V> newEntry) {
/*  616 */       newEntry.setWriteTime(original.getWriteTime());
/*  618 */       LocalCache.connectWriteOrder(original.getPreviousInWriteQueue(), newEntry);
/*  619 */       LocalCache.connectWriteOrder(newEntry, original.getNextInWriteQueue());
/*  621 */       LocalCache.nullifyWriteOrder(original);
/*      */     }
/*      */     
/*      */     abstract <K, V> LocalCache.ReferenceEntry<K, V> newEntry(LocalCache.Segment<K, V> param1Segment, K param1K, int param1Int, @Nullable LocalCache.ReferenceEntry<K, V> param1ReferenceEntry);
/*      */   }
/*      */   
/*  690 */   static final ValueReference<Object, Object> UNSET = new ValueReference<Object, Object>() {
/*      */       public Object get() {
/*  693 */         return null;
/*      */       }
/*      */       
/*      */       public int getWeight() {
/*  698 */         return 0;
/*      */       }
/*      */       
/*      */       public LocalCache.ReferenceEntry<Object, Object> getEntry() {
/*  703 */         return null;
/*      */       }
/*      */       
/*      */       public LocalCache.ValueReference<Object, Object> copyFor(ReferenceQueue<Object> queue, @Nullable Object value, LocalCache.ReferenceEntry<Object, Object> entry) {
/*  709 */         return this;
/*      */       }
/*      */       
/*      */       public boolean isLoading() {
/*  714 */         return false;
/*      */       }
/*      */       
/*      */       public boolean isActive() {
/*  719 */         return false;
/*      */       }
/*      */       
/*      */       public Object waitForValue() {
/*  724 */         return null;
/*      */       }
/*      */       
/*      */       public void notifyNewValue(Object newValue) {}
/*      */     };
/*      */   
/*      */   static <K, V> ValueReference<K, V> unset() {
/*  736 */     return (ValueReference)UNSET;
/*      */   }
/*      */   
/*      */   private enum NullEntry implements ReferenceEntry<Object, Object> {
/*  855 */     INSTANCE;
/*      */     
/*      */     public LocalCache.ValueReference<Object, Object> getValueReference() {
/*  859 */       return null;
/*      */     }
/*      */     
/*      */     public void setValueReference(LocalCache.ValueReference<Object, Object> valueReference) {}
/*      */     
/*      */     public LocalCache.ReferenceEntry<Object, Object> getNext() {
/*  867 */       return null;
/*      */     }
/*      */     
/*      */     public int getHash() {
/*  872 */       return 0;
/*      */     }
/*      */     
/*      */     public Object getKey() {
/*  877 */       return null;
/*      */     }
/*      */     
/*      */     public long getAccessTime() {
/*  882 */       return 0L;
/*      */     }
/*      */     
/*      */     public void setAccessTime(long time) {}
/*      */     
/*      */     public LocalCache.ReferenceEntry<Object, Object> getNextInAccessQueue() {
/*  890 */       return this;
/*      */     }
/*      */     
/*      */     public void setNextInAccessQueue(LocalCache.ReferenceEntry<Object, Object> next) {}
/*      */     
/*      */     public LocalCache.ReferenceEntry<Object, Object> getPreviousInAccessQueue() {
/*  898 */       return this;
/*      */     }
/*      */     
/*      */     public void setPreviousInAccessQueue(LocalCache.ReferenceEntry<Object, Object> previous) {}
/*      */     
/*      */     public long getWriteTime() {
/*  906 */       return 0L;
/*      */     }
/*      */     
/*      */     public void setWriteTime(long time) {}
/*      */     
/*      */     public LocalCache.ReferenceEntry<Object, Object> getNextInWriteQueue() {
/*  914 */       return this;
/*      */     }
/*      */     
/*      */     public void setNextInWriteQueue(LocalCache.ReferenceEntry<Object, Object> next) {}
/*      */     
/*      */     public LocalCache.ReferenceEntry<Object, Object> getPreviousInWriteQueue() {
/*  922 */       return this;
/*      */     }
/*      */     
/*      */     public void setPreviousInWriteQueue(LocalCache.ReferenceEntry<Object, Object> previous) {}
/*      */   }
/*      */   
/*      */   static abstract class AbstractReferenceEntry<K, V> implements ReferenceEntry<K, V> {
/*      */     public LocalCache.ValueReference<K, V> getValueReference() {
/*  932 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public void setValueReference(LocalCache.ValueReference<K, V> valueReference) {
/*  937 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public LocalCache.ReferenceEntry<K, V> getNext() {
/*  942 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public int getHash() {
/*  947 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public K getKey() {
/*  952 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public long getAccessTime() {
/*  957 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public void setAccessTime(long time) {
/*  962 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public LocalCache.ReferenceEntry<K, V> getNextInAccessQueue() {
/*  967 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public void setNextInAccessQueue(LocalCache.ReferenceEntry<K, V> next) {
/*  972 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public LocalCache.ReferenceEntry<K, V> getPreviousInAccessQueue() {
/*  977 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public void setPreviousInAccessQueue(LocalCache.ReferenceEntry<K, V> previous) {
/*  982 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public long getWriteTime() {
/*  987 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public void setWriteTime(long time) {
/*  992 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public LocalCache.ReferenceEntry<K, V> getNextInWriteQueue() {
/*  997 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public void setNextInWriteQueue(LocalCache.ReferenceEntry<K, V> next) {
/* 1002 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public LocalCache.ReferenceEntry<K, V> getPreviousInWriteQueue() {
/* 1007 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public void setPreviousInWriteQueue(LocalCache.ReferenceEntry<K, V> previous) {
/* 1012 */       throw new UnsupportedOperationException();
/*      */     }
/*      */   }
/*      */   
/*      */   static <K, V> ReferenceEntry<K, V> nullEntry() {
/* 1018 */     return NullEntry.INSTANCE;
/*      */   }
/*      */   
/* 1021 */   static final Queue<? extends Object> DISCARDING_QUEUE = new AbstractQueue() {
/*      */       public boolean offer(Object o) {
/* 1024 */         return true;
/*      */       }
/*      */       
/*      */       public Object peek() {
/* 1029 */         return null;
/*      */       }
/*      */       
/*      */       public Object poll() {
/* 1034 */         return null;
/*      */       }
/*      */       
/*      */       public int size() {
/* 1039 */         return 0;
/*      */       }
/*      */       
/*      */       public Iterator<Object> iterator() {
/* 1044 */         return (Iterator<Object>)Iterators.emptyIterator();
/*      */       }
/*      */     };
/*      */   
/*      */   Set<K> keySet;
/*      */   
/*      */   Collection<V> values;
/*      */   
/*      */   Set<Map.Entry<K, V>> entrySet;
/*      */   
/*      */   static <E> Queue<E> discardingQueue() {
/* 1053 */     return (Queue)DISCARDING_QUEUE;
/*      */   }
/*      */   
/*      */   static class StrongEntry<K, V> extends AbstractReferenceEntry<K, V> {
/*      */     final K key;
/*      */     
/*      */     final int hash;
/*      */     
/*      */     final LocalCache.ReferenceEntry<K, V> next;
/*      */     
/*      */     volatile LocalCache.ValueReference<K, V> valueReference;
/*      */     
/*      */     StrongEntry(K key, int hash, @Nullable LocalCache.ReferenceEntry<K, V> next) {
/* 1085 */       this.valueReference = LocalCache.unset();
/*      */       this.key = key;
/*      */       this.hash = hash;
/*      */       this.next = next;
/*      */     }
/*      */     
/*      */     public K getKey() {
/*      */       return this.key;
/*      */     }
/*      */     
/*      */     public LocalCache.ValueReference<K, V> getValueReference() {
/* 1089 */       return this.valueReference;
/*      */     }
/*      */     
/*      */     public void setValueReference(LocalCache.ValueReference<K, V> valueReference) {
/* 1094 */       this.valueReference = valueReference;
/*      */     }
/*      */     
/*      */     public int getHash() {
/* 1099 */       return this.hash;
/*      */     }
/*      */     
/*      */     public LocalCache.ReferenceEntry<K, V> getNext() {
/* 1104 */       return this.next;
/*      */     }
/*      */   }
/*      */   
/*      */   static final class StrongAccessEntry<K, V> extends StrongEntry<K, V> {
/*      */     volatile long accessTime;
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     LocalCache.ReferenceEntry<K, V> nextAccess;
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     LocalCache.ReferenceEntry<K, V> previousAccess;
/*      */     
/*      */     StrongAccessEntry(K key, int hash, @Nullable LocalCache.ReferenceEntry<K, V> next) {
/* 1110 */       super(key, hash, next);
/* 1115 */       this.accessTime = Long.MAX_VALUE;
/* 1127 */       this.nextAccess = LocalCache.nullEntry();
/* 1140 */       this.previousAccess = LocalCache.nullEntry();
/*      */     }
/*      */     
/*      */     public long getAccessTime() {
/*      */       return this.accessTime;
/*      */     }
/*      */     
/*      */     public void setAccessTime(long time) {
/*      */       this.accessTime = time;
/*      */     }
/*      */     
/*      */     public LocalCache.ReferenceEntry<K, V> getNextInAccessQueue() {
/*      */       return this.nextAccess;
/*      */     }
/*      */     
/*      */     public void setNextInAccessQueue(LocalCache.ReferenceEntry<K, V> next) {
/*      */       this.nextAccess = next;
/*      */     }
/*      */     
/*      */     public LocalCache.ReferenceEntry<K, V> getPreviousInAccessQueue() {
/* 1145 */       return this.previousAccess;
/*      */     }
/*      */     
/*      */     public void setPreviousInAccessQueue(LocalCache.ReferenceEntry<K, V> previous) {
/* 1150 */       this.previousAccess = previous;
/*      */     }
/*      */   }
/*      */   
/*      */   static final class StrongWriteEntry<K, V> extends StrongEntry<K, V> {
/*      */     volatile long writeTime;
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     LocalCache.ReferenceEntry<K, V> nextWrite;
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     LocalCache.ReferenceEntry<K, V> previousWrite;
/*      */     
/*      */     StrongWriteEntry(K key, int hash, @Nullable LocalCache.ReferenceEntry<K, V> next) {
/* 1156 */       super(key, hash, next);
/* 1161 */       this.writeTime = Long.MAX_VALUE;
/* 1173 */       this.nextWrite = LocalCache.nullEntry();
/* 1186 */       this.previousWrite = LocalCache.nullEntry();
/*      */     }
/*      */     
/*      */     public long getWriteTime() {
/*      */       return this.writeTime;
/*      */     }
/*      */     
/*      */     public void setWriteTime(long time) {
/*      */       this.writeTime = time;
/*      */     }
/*      */     
/*      */     public LocalCache.ReferenceEntry<K, V> getNextInWriteQueue() {
/*      */       return this.nextWrite;
/*      */     }
/*      */     
/*      */     public void setNextInWriteQueue(LocalCache.ReferenceEntry<K, V> next) {
/*      */       this.nextWrite = next;
/*      */     }
/*      */     
/*      */     public LocalCache.ReferenceEntry<K, V> getPreviousInWriteQueue() {
/* 1191 */       return this.previousWrite;
/*      */     }
/*      */     
/*      */     public void setPreviousInWriteQueue(LocalCache.ReferenceEntry<K, V> previous) {
/* 1196 */       this.previousWrite = previous;
/*      */     }
/*      */   }
/*      */   
/*      */   static final class StrongAccessWriteEntry<K, V> extends StrongEntry<K, V> {
/*      */     volatile long accessTime;
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     LocalCache.ReferenceEntry<K, V> nextAccess;
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     LocalCache.ReferenceEntry<K, V> previousAccess;
/*      */     
/*      */     volatile long writeTime;
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     LocalCache.ReferenceEntry<K, V> nextWrite;
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     LocalCache.ReferenceEntry<K, V> previousWrite;
/*      */     
/*      */     StrongAccessWriteEntry(K key, int hash, @Nullable LocalCache.ReferenceEntry<K, V> next) {
/* 1202 */       super(key, hash, next);
/* 1207 */       this.accessTime = Long.MAX_VALUE;
/* 1219 */       this.nextAccess = LocalCache.nullEntry();
/* 1232 */       this.previousAccess = LocalCache.nullEntry();
/* 1247 */       this.writeTime = Long.MAX_VALUE;
/* 1259 */       this.nextWrite = LocalCache.nullEntry();
/* 1272 */       this.previousWrite = LocalCache.nullEntry();
/*      */     }
/*      */     
/*      */     public long getAccessTime() {
/*      */       return this.accessTime;
/*      */     }
/*      */     
/*      */     public void setAccessTime(long time) {
/*      */       this.accessTime = time;
/*      */     }
/*      */     
/*      */     public LocalCache.ReferenceEntry<K, V> getNextInAccessQueue() {
/*      */       return this.nextAccess;
/*      */     }
/*      */     
/*      */     public void setNextInAccessQueue(LocalCache.ReferenceEntry<K, V> next) {
/*      */       this.nextAccess = next;
/*      */     }
/*      */     
/*      */     public LocalCache.ReferenceEntry<K, V> getPreviousInAccessQueue() {
/*      */       return this.previousAccess;
/*      */     }
/*      */     
/*      */     public void setPreviousInAccessQueue(LocalCache.ReferenceEntry<K, V> previous) {
/*      */       this.previousAccess = previous;
/*      */     }
/*      */     
/*      */     public long getWriteTime() {
/*      */       return this.writeTime;
/*      */     }
/*      */     
/*      */     public void setWriteTime(long time) {
/*      */       this.writeTime = time;
/*      */     }
/*      */     
/*      */     public LocalCache.ReferenceEntry<K, V> getNextInWriteQueue() {
/*      */       return this.nextWrite;
/*      */     }
/*      */     
/*      */     public void setNextInWriteQueue(LocalCache.ReferenceEntry<K, V> next) {
/*      */       this.nextWrite = next;
/*      */     }
/*      */     
/*      */     public LocalCache.ReferenceEntry<K, V> getPreviousInWriteQueue() {
/* 1277 */       return this.previousWrite;
/*      */     }
/*      */     
/*      */     public void setPreviousInWriteQueue(LocalCache.ReferenceEntry<K, V> previous) {
/* 1282 */       this.previousWrite = previous;
/*      */     }
/*      */   }
/*      */   
/*      */   static class WeakEntry<K, V> extends WeakReference<K> implements ReferenceEntry<K, V> {
/*      */     final int hash;
/*      */     
/*      */     final LocalCache.ReferenceEntry<K, V> next;
/*      */     
/*      */     volatile LocalCache.ValueReference<K, V> valueReference;
/*      */     
/*      */     WeakEntry(ReferenceQueue<K> queue, K key, int hash, @Nullable LocalCache.ReferenceEntry<K, V> next) {
/* 1291 */       super(key, queue);
/* 1374 */       this.valueReference = LocalCache.unset();
/*      */       this.hash = hash;
/*      */       this.next = next;
/*      */     }
/*      */     
/*      */     public K getKey() {
/*      */       return get();
/*      */     }
/*      */     
/*      */     public long getAccessTime() {
/*      */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public void setAccessTime(long time) {
/*      */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public LocalCache.ReferenceEntry<K, V> getNextInAccessQueue() {
/*      */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public void setNextInAccessQueue(LocalCache.ReferenceEntry<K, V> next) {
/*      */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public LocalCache.ReferenceEntry<K, V> getPreviousInAccessQueue() {
/*      */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public void setPreviousInAccessQueue(LocalCache.ReferenceEntry<K, V> previous) {
/*      */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public long getWriteTime() {
/*      */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public void setWriteTime(long time) {
/*      */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public LocalCache.ReferenceEntry<K, V> getNextInWriteQueue() {
/*      */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public void setNextInWriteQueue(LocalCache.ReferenceEntry<K, V> next) {
/*      */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public LocalCache.ReferenceEntry<K, V> getPreviousInWriteQueue() {
/*      */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public void setPreviousInWriteQueue(LocalCache.ReferenceEntry<K, V> previous) {
/*      */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public LocalCache.ValueReference<K, V> getValueReference() {
/* 1378 */       return this.valueReference;
/*      */     }
/*      */     
/*      */     public void setValueReference(LocalCache.ValueReference<K, V> valueReference) {
/* 1383 */       this.valueReference = valueReference;
/*      */     }
/*      */     
/*      */     public int getHash() {
/* 1388 */       return this.hash;
/*      */     }
/*      */     
/*      */     public LocalCache.ReferenceEntry<K, V> getNext() {
/* 1393 */       return this.next;
/*      */     }
/*      */   }
/*      */   
/*      */   static final class WeakAccessEntry<K, V> extends WeakEntry<K, V> {
/*      */     volatile long accessTime;
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     LocalCache.ReferenceEntry<K, V> nextAccess;
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     LocalCache.ReferenceEntry<K, V> previousAccess;
/*      */     
/*      */     WeakAccessEntry(ReferenceQueue<K> queue, K key, int hash, @Nullable LocalCache.ReferenceEntry<K, V> next) {
/* 1400 */       super(queue, key, hash, next);
/* 1405 */       this.accessTime = Long.MAX_VALUE;
/* 1417 */       this.nextAccess = LocalCache.nullEntry();
/* 1430 */       this.previousAccess = LocalCache.nullEntry();
/*      */     }
/*      */     
/*      */     public long getAccessTime() {
/*      */       return this.accessTime;
/*      */     }
/*      */     
/*      */     public void setAccessTime(long time) {
/*      */       this.accessTime = time;
/*      */     }
/*      */     
/*      */     public LocalCache.ReferenceEntry<K, V> getNextInAccessQueue() {
/*      */       return this.nextAccess;
/*      */     }
/*      */     
/*      */     public void setNextInAccessQueue(LocalCache.ReferenceEntry<K, V> next) {
/*      */       this.nextAccess = next;
/*      */     }
/*      */     
/*      */     public LocalCache.ReferenceEntry<K, V> getPreviousInAccessQueue() {
/* 1435 */       return this.previousAccess;
/*      */     }
/*      */     
/*      */     public void setPreviousInAccessQueue(LocalCache.ReferenceEntry<K, V> previous) {
/* 1440 */       this.previousAccess = previous;
/*      */     }
/*      */   }
/*      */   
/*      */   static final class WeakWriteEntry<K, V> extends WeakEntry<K, V> {
/*      */     volatile long writeTime;
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     LocalCache.ReferenceEntry<K, V> nextWrite;
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     LocalCache.ReferenceEntry<K, V> previousWrite;
/*      */     
/*      */     WeakWriteEntry(ReferenceQueue<K> queue, K key, int hash, @Nullable LocalCache.ReferenceEntry<K, V> next) {
/* 1447 */       super(queue, key, hash, next);
/* 1452 */       this.writeTime = Long.MAX_VALUE;
/* 1464 */       this.nextWrite = LocalCache.nullEntry();
/* 1477 */       this.previousWrite = LocalCache.nullEntry();
/*      */     }
/*      */     
/*      */     public long getWriteTime() {
/*      */       return this.writeTime;
/*      */     }
/*      */     
/*      */     public void setWriteTime(long time) {
/*      */       this.writeTime = time;
/*      */     }
/*      */     
/*      */     public LocalCache.ReferenceEntry<K, V> getNextInWriteQueue() {
/*      */       return this.nextWrite;
/*      */     }
/*      */     
/*      */     public void setNextInWriteQueue(LocalCache.ReferenceEntry<K, V> next) {
/*      */       this.nextWrite = next;
/*      */     }
/*      */     
/*      */     public LocalCache.ReferenceEntry<K, V> getPreviousInWriteQueue() {
/* 1482 */       return this.previousWrite;
/*      */     }
/*      */     
/*      */     public void setPreviousInWriteQueue(LocalCache.ReferenceEntry<K, V> previous) {
/* 1487 */       this.previousWrite = previous;
/*      */     }
/*      */   }
/*      */   
/*      */   static final class WeakAccessWriteEntry<K, V> extends WeakEntry<K, V> {
/*      */     volatile long accessTime;
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     LocalCache.ReferenceEntry<K, V> nextAccess;
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     LocalCache.ReferenceEntry<K, V> previousAccess;
/*      */     
/*      */     volatile long writeTime;
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     LocalCache.ReferenceEntry<K, V> nextWrite;
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     LocalCache.ReferenceEntry<K, V> previousWrite;
/*      */     
/*      */     WeakAccessWriteEntry(ReferenceQueue<K> queue, K key, int hash, @Nullable LocalCache.ReferenceEntry<K, V> next) {
/* 1494 */       super(queue, key, hash, next);
/* 1499 */       this.accessTime = Long.MAX_VALUE;
/* 1511 */       this.nextAccess = LocalCache.nullEntry();
/* 1524 */       this.previousAccess = LocalCache.nullEntry();
/* 1539 */       this.writeTime = Long.MAX_VALUE;
/* 1551 */       this.nextWrite = LocalCache.nullEntry();
/* 1564 */       this.previousWrite = LocalCache.nullEntry();
/*      */     }
/*      */     
/*      */     public long getAccessTime() {
/*      */       return this.accessTime;
/*      */     }
/*      */     
/*      */     public void setAccessTime(long time) {
/*      */       this.accessTime = time;
/*      */     }
/*      */     
/*      */     public LocalCache.ReferenceEntry<K, V> getNextInAccessQueue() {
/*      */       return this.nextAccess;
/*      */     }
/*      */     
/*      */     public void setNextInAccessQueue(LocalCache.ReferenceEntry<K, V> next) {
/*      */       this.nextAccess = next;
/*      */     }
/*      */     
/*      */     public LocalCache.ReferenceEntry<K, V> getPreviousInAccessQueue() {
/*      */       return this.previousAccess;
/*      */     }
/*      */     
/*      */     public void setPreviousInAccessQueue(LocalCache.ReferenceEntry<K, V> previous) {
/*      */       this.previousAccess = previous;
/*      */     }
/*      */     
/*      */     public long getWriteTime() {
/*      */       return this.writeTime;
/*      */     }
/*      */     
/*      */     public void setWriteTime(long time) {
/*      */       this.writeTime = time;
/*      */     }
/*      */     
/*      */     public LocalCache.ReferenceEntry<K, V> getNextInWriteQueue() {
/*      */       return this.nextWrite;
/*      */     }
/*      */     
/*      */     public void setNextInWriteQueue(LocalCache.ReferenceEntry<K, V> next) {
/*      */       this.nextWrite = next;
/*      */     }
/*      */     
/*      */     public LocalCache.ReferenceEntry<K, V> getPreviousInWriteQueue() {
/* 1569 */       return this.previousWrite;
/*      */     }
/*      */     
/*      */     public void setPreviousInWriteQueue(LocalCache.ReferenceEntry<K, V> previous) {
/* 1574 */       this.previousWrite = previous;
/*      */     }
/*      */   }
/*      */   
/*      */   static class WeakValueReference<K, V> extends WeakReference<V> implements ValueReference<K, V> {
/*      */     final LocalCache.ReferenceEntry<K, V> entry;
/*      */     
/*      */     WeakValueReference(ReferenceQueue<V> queue, V referent, LocalCache.ReferenceEntry<K, V> entry) {
/* 1586 */       super(referent, queue);
/* 1587 */       this.entry = entry;
/*      */     }
/*      */     
/*      */     public int getWeight() {
/* 1592 */       return 1;
/*      */     }
/*      */     
/*      */     public LocalCache.ReferenceEntry<K, V> getEntry() {
/* 1597 */       return this.entry;
/*      */     }
/*      */     
/*      */     public void notifyNewValue(V newValue) {}
/*      */     
/*      */     public LocalCache.ValueReference<K, V> copyFor(ReferenceQueue<V> queue, V value, LocalCache.ReferenceEntry<K, V> entry) {
/* 1606 */       return new WeakValueReference(queue, value, entry);
/*      */     }
/*      */     
/*      */     public boolean isLoading() {
/* 1611 */       return false;
/*      */     }
/*      */     
/*      */     public boolean isActive() {
/* 1616 */       return true;
/*      */     }
/*      */     
/*      */     public V waitForValue() {
/* 1621 */       return get();
/*      */     }
/*      */   }
/*      */   
/*      */   static class SoftValueReference<K, V> extends SoftReference<V> implements ValueReference<K, V> {
/*      */     final LocalCache.ReferenceEntry<K, V> entry;
/*      */     
/*      */     SoftValueReference(ReferenceQueue<V> queue, V referent, LocalCache.ReferenceEntry<K, V> entry) {
/* 1633 */       super(referent, queue);
/* 1634 */       this.entry = entry;
/*      */     }
/*      */     
/*      */     public int getWeight() {
/* 1639 */       return 1;
/*      */     }
/*      */     
/*      */     public LocalCache.ReferenceEntry<K, V> getEntry() {
/* 1644 */       return this.entry;
/*      */     }
/*      */     
/*      */     public void notifyNewValue(V newValue) {}
/*      */     
/*      */     public LocalCache.ValueReference<K, V> copyFor(ReferenceQueue<V> queue, V value, LocalCache.ReferenceEntry<K, V> entry) {
/* 1653 */       return new SoftValueReference(queue, value, entry);
/*      */     }
/*      */     
/*      */     public boolean isLoading() {
/* 1658 */       return false;
/*      */     }
/*      */     
/*      */     public boolean isActive() {
/* 1663 */       return true;
/*      */     }
/*      */     
/*      */     public V waitForValue() {
/* 1668 */       return get();
/*      */     }
/*      */   }
/*      */   
/*      */   static class StrongValueReference<K, V> implements ValueReference<K, V> {
/*      */     final V referent;
/*      */     
/*      */     StrongValueReference(V referent) {
/* 1679 */       this.referent = referent;
/*      */     }
/*      */     
/*      */     public V get() {
/* 1684 */       return this.referent;
/*      */     }
/*      */     
/*      */     public int getWeight() {
/* 1689 */       return 1;
/*      */     }
/*      */     
/*      */     public LocalCache.ReferenceEntry<K, V> getEntry() {
/* 1694 */       return null;
/*      */     }
/*      */     
/*      */     public LocalCache.ValueReference<K, V> copyFor(ReferenceQueue<V> queue, V value, LocalCache.ReferenceEntry<K, V> entry) {
/* 1700 */       return this;
/*      */     }
/*      */     
/*      */     public boolean isLoading() {
/* 1705 */       return false;
/*      */     }
/*      */     
/*      */     public boolean isActive() {
/* 1710 */       return true;
/*      */     }
/*      */     
/*      */     public V waitForValue() {
/* 1715 */       return get();
/*      */     }
/*      */     
/*      */     public void notifyNewValue(V newValue) {}
/*      */   }
/*      */   
/*      */   static final class WeightedWeakValueReference<K, V> extends WeakValueReference<K, V> {
/*      */     final int weight;
/*      */     
/*      */     WeightedWeakValueReference(ReferenceQueue<V> queue, V referent, LocalCache.ReferenceEntry<K, V> entry, int weight) {
/* 1730 */       super(queue, referent, entry);
/* 1731 */       this.weight = weight;
/*      */     }
/*      */     
/*      */     public int getWeight() {
/* 1736 */       return this.weight;
/*      */     }
/*      */     
/*      */     public LocalCache.ValueReference<K, V> copyFor(ReferenceQueue<V> queue, V value, LocalCache.ReferenceEntry<K, V> entry) {
/* 1742 */       return new WeightedWeakValueReference(queue, value, entry, this.weight);
/*      */     }
/*      */   }
/*      */   
/*      */   static final class WeightedSoftValueReference<K, V> extends SoftValueReference<K, V> {
/*      */     final int weight;
/*      */     
/*      */     WeightedSoftValueReference(ReferenceQueue<V> queue, V referent, LocalCache.ReferenceEntry<K, V> entry, int weight) {
/* 1754 */       super(queue, referent, entry);
/* 1755 */       this.weight = weight;
/*      */     }
/*      */     
/*      */     public int getWeight() {
/* 1760 */       return this.weight;
/*      */     }
/*      */     
/*      */     public LocalCache.ValueReference<K, V> copyFor(ReferenceQueue<V> queue, V value, LocalCache.ReferenceEntry<K, V> entry) {
/* 1765 */       return new WeightedSoftValueReference(queue, value, entry, this.weight);
/*      */     }
/*      */   }
/*      */   
/*      */   static final class WeightedStrongValueReference<K, V> extends StrongValueReference<K, V> {
/*      */     final int weight;
/*      */     
/*      */     WeightedStrongValueReference(V referent, int weight) {
/* 1777 */       super(referent);
/* 1778 */       this.weight = weight;
/*      */     }
/*      */     
/*      */     public int getWeight() {
/* 1783 */       return this.weight;
/*      */     }
/*      */   }
/*      */   
/*      */   static int rehash(int h) {
/* 1799 */     h += h << 15 ^ 0xFFFFCD7D;
/* 1800 */     h ^= h >>> 10;
/* 1801 */     h += h << 3;
/* 1802 */     h ^= h >>> 6;
/* 1803 */     h += (h << 2) + (h << 14);
/* 1804 */     return h ^ h >>> 16;
/*      */   }
/*      */   
/*      */   @GuardedBy("Segment.this")
/*      */   @VisibleForTesting
/*      */   ReferenceEntry<K, V> newEntry(K key, int hash, @Nullable ReferenceEntry<K, V> next) {
/* 1813 */     return segmentFor(hash).newEntry(key, hash, next);
/*      */   }
/*      */   
/*      */   @GuardedBy("Segment.this")
/*      */   @VisibleForTesting
/*      */   ReferenceEntry<K, V> copyEntry(ReferenceEntry<K, V> original, ReferenceEntry<K, V> newNext) {
/* 1822 */     int hash = original.getHash();
/* 1823 */     return segmentFor(hash).copyEntry(original, newNext);
/*      */   }
/*      */   
/*      */   @GuardedBy("Segment.this")
/*      */   @VisibleForTesting
/*      */   ValueReference<K, V> newValueReference(ReferenceEntry<K, V> entry, V value, int weight) {
/* 1832 */     int hash = entry.getHash();
/* 1833 */     return this.valueStrength.referenceValue(segmentFor(hash), entry, (V)Preconditions.checkNotNull(value), weight);
/*      */   }
/*      */   
/*      */   int hash(@Nullable Object key) {
/* 1837 */     int h = this.keyEquivalence.hash(key);
/* 1838 */     return rehash(h);
/*      */   }
/*      */   
/*      */   void reclaimValue(ValueReference<K, V> valueReference) {
/* 1842 */     ReferenceEntry<K, V> entry = valueReference.getEntry();
/* 1843 */     int hash = entry.getHash();
/* 1844 */     segmentFor(hash).reclaimValue(entry.getKey(), hash, valueReference);
/*      */   }
/*      */   
/*      */   void reclaimKey(ReferenceEntry<K, V> entry) {
/* 1848 */     int hash = entry.getHash();
/* 1849 */     segmentFor(hash).reclaimKey(entry, hash);
/*      */   }
/*      */   
/*      */   @VisibleForTesting
/*      */   boolean isLive(ReferenceEntry<K, V> entry, long now) {
/* 1858 */     return (segmentFor(entry.getHash()).getLiveValue(entry, now) != null);
/*      */   }
/*      */   
/*      */   Segment<K, V> segmentFor(int hash) {
/* 1869 */     return this.segments[hash >>> this.segmentShift & this.segmentMask];
/*      */   }
/*      */   
/*      */   Segment<K, V> createSegment(int initialCapacity, long maxSegmentWeight, AbstractCache.StatsCounter statsCounter) {
/* 1874 */     return new Segment<K, V>(this, initialCapacity, maxSegmentWeight, statsCounter);
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   V getLiveValue(ReferenceEntry<K, V> entry, long now) {
/* 1885 */     if (entry.getKey() == null)
/* 1886 */       return null; 
/* 1888 */     V value = (V)entry.getValueReference().get();
/* 1889 */     if (value == null)
/* 1890 */       return null; 
/* 1893 */     if (isExpired(entry, now))
/* 1894 */       return null; 
/* 1896 */     return value;
/*      */   }
/*      */   
/*      */   boolean isExpired(ReferenceEntry<K, V> entry, long now) {
/* 1905 */     Preconditions.checkNotNull(entry);
/* 1906 */     if (expiresAfterAccess() && now - entry.getAccessTime() >= this.expireAfterAccessNanos)
/* 1908 */       return true; 
/* 1910 */     if (expiresAfterWrite() && now - entry.getWriteTime() >= this.expireAfterWriteNanos)
/* 1912 */       return true; 
/* 1914 */     return false;
/*      */   }
/*      */   
/*      */   @GuardedBy("Segment.this")
/*      */   static <K, V> void connectAccessOrder(ReferenceEntry<K, V> previous, ReferenceEntry<K, V> next) {
/* 1921 */     previous.setNextInAccessQueue(next);
/* 1922 */     next.setPreviousInAccessQueue(previous);
/*      */   }
/*      */   
/*      */   @GuardedBy("Segment.this")
/*      */   static <K, V> void nullifyAccessOrder(ReferenceEntry<K, V> nulled) {
/* 1927 */     ReferenceEntry<K, V> nullEntry = nullEntry();
/* 1928 */     nulled.setNextInAccessQueue(nullEntry);
/* 1929 */     nulled.setPreviousInAccessQueue(nullEntry);
/*      */   }
/*      */   
/*      */   @GuardedBy("Segment.this")
/*      */   static <K, V> void connectWriteOrder(ReferenceEntry<K, V> previous, ReferenceEntry<K, V> next) {
/* 1934 */     previous.setNextInWriteQueue(next);
/* 1935 */     next.setPreviousInWriteQueue(previous);
/*      */   }
/*      */   
/*      */   @GuardedBy("Segment.this")
/*      */   static <K, V> void nullifyWriteOrder(ReferenceEntry<K, V> nulled) {
/* 1940 */     ReferenceEntry<K, V> nullEntry = nullEntry();
/* 1941 */     nulled.setNextInWriteQueue(nullEntry);
/* 1942 */     nulled.setPreviousInWriteQueue(nullEntry);
/*      */   }
/*      */   
/*      */   void processPendingNotifications() {
/*      */     RemovalNotification<K, V> notification;
/* 1952 */     while ((notification = this.removalNotificationQueue.poll()) != null) {
/*      */       try {
/* 1954 */         this.removalListener.onRemoval(notification);
/* 1955 */       } catch (Throwable e) {
/* 1956 */         logger.log(Level.WARNING, "Exception thrown by removal listener", e);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   final Segment<K, V>[] newSegmentArray(int ssize) {
/* 1963 */     return (Segment<K, V>[])new Segment[ssize];
/*      */   }
/*      */   
/*      */   static class Segment<K, V> extends ReentrantLock {
/*      */     final LocalCache<K, V> map;
/*      */     
/*      */     volatile int count;
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     int totalWeight;
/*      */     
/*      */     int modCount;
/*      */     
/*      */     int threshold;
/*      */     
/*      */     volatile AtomicReferenceArray<LocalCache.ReferenceEntry<K, V>> table;
/*      */     
/*      */     final long maxSegmentWeight;
/*      */     
/*      */     final ReferenceQueue<K> keyReferenceQueue;
/*      */     
/*      */     final ReferenceQueue<V> valueReferenceQueue;
/*      */     
/*      */     final Queue<LocalCache.ReferenceEntry<K, V>> recencyQueue;
/*      */     
/* 2068 */     final AtomicInteger readCount = new AtomicInteger();
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     final Queue<LocalCache.ReferenceEntry<K, V>> writeQueue;
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     final Queue<LocalCache.ReferenceEntry<K, V>> accessQueue;
/*      */     
/*      */     final AbstractCache.StatsCounter statsCounter;
/*      */     
/*      */     Segment(LocalCache<K, V> map, int initialCapacity, long maxSegmentWeight, AbstractCache.StatsCounter statsCounter) {
/* 2089 */       this.map = map;
/* 2090 */       this.maxSegmentWeight = maxSegmentWeight;
/* 2091 */       this.statsCounter = (AbstractCache.StatsCounter)Preconditions.checkNotNull(statsCounter);
/* 2092 */       initTable(newEntryArray(initialCapacity));
/* 2094 */       this.keyReferenceQueue = map.usesKeyReferences() ? new ReferenceQueue<K>() : null;
/* 2097 */       this.valueReferenceQueue = map.usesValueReferences() ? new ReferenceQueue<V>() : null;
/* 2100 */       this.recencyQueue = map.usesAccessQueue() ? new ConcurrentLinkedQueue<LocalCache.ReferenceEntry<K, V>>() : LocalCache.<LocalCache.ReferenceEntry<K, V>>discardingQueue();
/* 2104 */       this.writeQueue = map.usesWriteQueue() ? new LocalCache.WriteQueue<K, V>() : LocalCache.<LocalCache.ReferenceEntry<K, V>>discardingQueue();
/* 2108 */       this.accessQueue = map.usesAccessQueue() ? new LocalCache.AccessQueue<K, V>() : LocalCache.<LocalCache.ReferenceEntry<K, V>>discardingQueue();
/*      */     }
/*      */     
/*      */     AtomicReferenceArray<LocalCache.ReferenceEntry<K, V>> newEntryArray(int size) {
/* 2114 */       return new AtomicReferenceArray<LocalCache.ReferenceEntry<K, V>>(size);
/*      */     }
/*      */     
/*      */     void initTable(AtomicReferenceArray<LocalCache.ReferenceEntry<K, V>> newTable) {
/* 2118 */       this.threshold = newTable.length() * 3 / 4;
/* 2119 */       if (!this.map.customWeigher() && this.threshold == this.maxSegmentWeight)
/* 2121 */         this.threshold++; 
/* 2123 */       this.table = newTable;
/*      */     }
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     LocalCache.ReferenceEntry<K, V> newEntry(K key, int hash, @Nullable LocalCache.ReferenceEntry<K, V> next) {
/* 2128 */       return this.map.entryFactory.newEntry(this, (K)Preconditions.checkNotNull(key), hash, next);
/*      */     }
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     LocalCache.ReferenceEntry<K, V> copyEntry(LocalCache.ReferenceEntry<K, V> original, LocalCache.ReferenceEntry<K, V> newNext) {
/* 2137 */       if (original.getKey() == null)
/* 2139 */         return null; 
/* 2142 */       LocalCache.ValueReference<K, V> valueReference = original.getValueReference();
/* 2143 */       V value = valueReference.get();
/* 2144 */       if (value == null && valueReference.isActive())
/* 2146 */         return null; 
/* 2149 */       LocalCache.ReferenceEntry<K, V> newEntry = this.map.entryFactory.copyEntry(this, original, newNext);
/* 2150 */       newEntry.setValueReference(valueReference.copyFor(this.valueReferenceQueue, value, newEntry));
/* 2151 */       return newEntry;
/*      */     }
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     void setValue(LocalCache.ReferenceEntry<K, V> entry, K key, V value, long now) {
/* 2159 */       LocalCache.ValueReference<K, V> previous = entry.getValueReference();
/* 2160 */       int weight = this.map.weigher.weigh(key, value);
/* 2161 */       Preconditions.checkState((weight >= 0), "Weights must be non-negative");
/* 2163 */       LocalCache.ValueReference<K, V> valueReference = this.map.valueStrength.referenceValue(this, entry, value, weight);
/* 2165 */       entry.setValueReference(valueReference);
/* 2166 */       recordWrite(entry, weight, now);
/* 2167 */       previous.notifyNewValue(value);
/*      */     }
/*      */     
/*      */     V get(K key, int hash, CacheLoader<? super K, V> loader) throws ExecutionException {
/* 2173 */       Preconditions.checkNotNull(key);
/* 2174 */       Preconditions.checkNotNull(loader);
/*      */       try {
/* 2176 */         if (this.count != 0) {
/* 2178 */           LocalCache.ReferenceEntry<K, V> e = getEntry(key, hash);
/* 2179 */           if (e != null) {
/* 2180 */             long now = this.map.ticker.read();
/* 2181 */             V value = getLiveValue(e, now);
/* 2182 */             if (value != null) {
/* 2183 */               recordRead(e, now);
/* 2184 */               this.statsCounter.recordHits(1);
/* 2185 */               return scheduleRefresh(e, key, hash, value, now, loader);
/*      */             } 
/* 2187 */             LocalCache.ValueReference<K, V> valueReference = e.getValueReference();
/* 2188 */             if (valueReference.isLoading())
/* 2189 */               return waitForLoadingValue(e, key, valueReference); 
/*      */           } 
/*      */         } 
/* 2195 */         return lockedGetOrLoad(key, hash, loader);
/* 2196 */       } catch (ExecutionException ee) {
/* 2197 */         Throwable cause = ee.getCause();
/* 2198 */         if (cause instanceof Error)
/* 2199 */           throw new ExecutionError((Error)cause); 
/* 2200 */         if (cause instanceof RuntimeException)
/* 2201 */           throw new UncheckedExecutionException(cause); 
/* 2203 */         throw ee;
/*      */       } finally {
/* 2205 */         postReadCleanup();
/*      */       } 
/*      */     }
/*      */     
/*      */     V lockedGetOrLoad(K key, int hash, CacheLoader<? super K, V> loader) throws ExecutionException {
/*      */       LocalCache.ReferenceEntry<K, V> e;
/* 2212 */       LocalCache.ValueReference<K, V> valueReference = null;
/* 2213 */       LocalCache.LoadingValueReference<K, V> loadingValueReference = null;
/* 2214 */       boolean createNewEntry = true;
/* 2216 */       lock();
/*      */       try {
/* 2219 */         long now = this.map.ticker.read();
/* 2220 */         preWriteCleanup(now);
/* 2222 */         int newCount = this.count - 1;
/* 2223 */         AtomicReferenceArray<LocalCache.ReferenceEntry<K, V>> table = this.table;
/* 2224 */         int index = hash & table.length() - 1;
/* 2225 */         LocalCache.ReferenceEntry<K, V> first = table.get(index);
/* 2227 */         for (e = first; e != null; e = e.getNext()) {
/* 2228 */           K entryKey = e.getKey();
/* 2229 */           if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
/* 2231 */             valueReference = e.getValueReference();
/* 2232 */             if (valueReference.isLoading()) {
/* 2233 */               createNewEntry = false;
/*      */               break;
/*      */             } 
/* 2235 */             V value = valueReference.get();
/* 2236 */             if (value == null) {
/* 2237 */               enqueueNotification(entryKey, hash, valueReference, RemovalCause.COLLECTED);
/* 2238 */             } else if (this.map.isExpired(e, now)) {
/* 2241 */               enqueueNotification(entryKey, hash, valueReference, RemovalCause.EXPIRED);
/*      */             } else {
/* 2243 */               recordLockedRead(e, now);
/* 2244 */               this.statsCounter.recordHits(1);
/* 2246 */               return value;
/*      */             } 
/* 2250 */             this.writeQueue.remove(e);
/* 2251 */             this.accessQueue.remove(e);
/* 2252 */             this.count = newCount;
/*      */             break;
/*      */           } 
/*      */         } 
/* 2258 */         if (createNewEntry) {
/* 2259 */           loadingValueReference = new LocalCache.LoadingValueReference<K, V>();
/* 2261 */           if (e == null) {
/* 2262 */             e = newEntry(key, hash, first);
/* 2263 */             e.setValueReference(loadingValueReference);
/* 2264 */             table.set(index, e);
/*      */           } else {
/* 2266 */             e.setValueReference(loadingValueReference);
/*      */           } 
/*      */         } 
/*      */       } finally {
/* 2270 */         unlock();
/* 2271 */         postWriteCleanup();
/*      */       } 
/* 2274 */       if (createNewEntry)
/*      */         try {
/*      */         
/*      */         } finally {
/* 2283 */           this.statsCounter.recordMisses(1);
/*      */         }  
/* 2287 */       return waitForLoadingValue(e, key, valueReference);
/*      */     }
/*      */     
/*      */     V waitForLoadingValue(LocalCache.ReferenceEntry<K, V> e, K key, LocalCache.ValueReference<K, V> valueReference) throws ExecutionException {
/* 2293 */       if (!valueReference.isLoading())
/* 2294 */         throw new AssertionError(); 
/* 2297 */       Preconditions.checkState(!Thread.holdsLock(e), "Recursive load of: %s", new Object[] { key });
/*      */       try {
/* 2300 */         V value = valueReference.waitForValue();
/* 2301 */         if (value == null)
/* 2302 */           throw new CacheLoader.InvalidCacheLoadException("CacheLoader returned null for key " + key + "."); 
/* 2305 */         long now = this.map.ticker.read();
/* 2306 */         recordRead(e, now);
/* 2307 */         return value;
/*      */       } finally {
/* 2309 */         this.statsCounter.recordMisses(1);
/*      */       } 
/*      */     }
/*      */     
/*      */     V loadSync(K key, int hash, LocalCache.LoadingValueReference<K, V> loadingValueReference, CacheLoader<? super K, V> loader) throws ExecutionException {
/* 2317 */       ListenableFuture<V> loadingFuture = loadingValueReference.loadFuture(key, loader);
/* 2318 */       return getAndRecordStats(key, hash, loadingValueReference, loadingFuture);
/*      */     }
/*      */     
/*      */     ListenableFuture<V> loadAsync(final K key, final int hash, final LocalCache.LoadingValueReference<K, V> loadingValueReference, CacheLoader<? super K, V> loader) {
/* 2323 */       final ListenableFuture<V> loadingFuture = loadingValueReference.loadFuture(key, loader);
/* 2324 */       loadingFuture.addListener(new Runnable() {
/*      */             public void run() {
/*      */               try {
/* 2329 */                 V newValue = LocalCache.Segment.this.getAndRecordStats(key, hash, loadingValueReference, loadingFuture);
/* 2330 */               } catch (Throwable t) {
/* 2331 */                 LocalCache.logger.log(Level.WARNING, "Exception thrown during refresh", t);
/* 2332 */                 loadingValueReference.setException(t);
/*      */               } 
/*      */             }
/*      */           }(Executor)LocalCache.sameThreadExecutor);
/* 2336 */       return loadingFuture;
/*      */     }
/*      */     
/*      */     V getAndRecordStats(K key, int hash, LocalCache.LoadingValueReference<K, V> loadingValueReference, ListenableFuture<V> newValue) throws ExecutionException {
/* 2344 */       V value = null;
/*      */       try {
/* 2346 */         value = (V)Uninterruptibles.getUninterruptibly((Future)newValue);
/* 2347 */         if (value == null)
/* 2348 */           throw new CacheLoader.InvalidCacheLoadException("CacheLoader returned null for key " + key + "."); 
/* 2350 */         this.statsCounter.recordLoadSuccess(loadingValueReference.elapsedNanos());
/* 2351 */         storeLoadedValue(key, hash, loadingValueReference, value);
/* 2352 */         return value;
/*      */       } finally {
/* 2354 */         if (value == null) {
/* 2355 */           this.statsCounter.recordLoadException(loadingValueReference.elapsedNanos());
/* 2356 */           removeLoadingValue(key, hash, loadingValueReference);
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     V scheduleRefresh(LocalCache.ReferenceEntry<K, V> entry, K key, int hash, V oldValue, long now, CacheLoader<? super K, V> loader) {
/* 2363 */       if (this.map.refreshes() && now - entry.getWriteTime() > this.map.refreshNanos && !entry.getValueReference().isLoading()) {
/* 2365 */         V newValue = refresh(key, hash, loader, true);
/* 2366 */         if (newValue != null)
/* 2367 */           return newValue; 
/*      */       } 
/* 2370 */       return oldValue;
/*      */     }
/*      */     
/*      */     @Nullable
/*      */     V refresh(K key, int hash, CacheLoader<? super K, V> loader, boolean checkTime) {
/* 2381 */       LocalCache.LoadingValueReference<K, V> loadingValueReference = insertLoadingValueReference(key, hash, checkTime);
/* 2383 */       if (loadingValueReference == null)
/* 2384 */         return null; 
/* 2387 */       ListenableFuture<V> result = loadAsync(key, hash, loadingValueReference, loader);
/* 2388 */       if (result.isDone())
/*      */         try {
/* 2390 */           return (V)Uninterruptibles.getUninterruptibly((Future)result);
/* 2391 */         } catch (Throwable t) {} 
/* 2395 */       return null;
/*      */     }
/*      */     
/*      */     @Nullable
/*      */     LocalCache.LoadingValueReference<K, V> insertLoadingValueReference(K key, int hash, boolean checkTime) {
/* 2405 */       LocalCache.ReferenceEntry<K, V> e = null;
/* 2406 */       lock();
/*      */       try {
/* 2408 */         long now = this.map.ticker.read();
/* 2409 */         preWriteCleanup(now);
/* 2411 */         AtomicReferenceArray<LocalCache.ReferenceEntry<K, V>> table = this.table;
/* 2412 */         int index = hash & table.length() - 1;
/* 2413 */         LocalCache.ReferenceEntry<K, V> first = table.get(index);
/* 2416 */         for (e = first; e != null; e = e.getNext()) {
/* 2417 */           K entryKey = e.getKey();
/* 2418 */           if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
/* 2422 */             LocalCache.ValueReference<K, V> valueReference = e.getValueReference();
/* 2423 */             if (valueReference.isLoading() || (checkTime && now - e.getWriteTime() < this.map.refreshNanos))
/* 2428 */               return null; 
/* 2432 */             this.modCount++;
/* 2433 */             LocalCache.LoadingValueReference<K, V> loadingValueReference1 = new LocalCache.LoadingValueReference<K, V>(valueReference);
/* 2435 */             e.setValueReference(loadingValueReference1);
/* 2436 */             return loadingValueReference1;
/*      */           } 
/*      */         } 
/* 2440 */         this.modCount++;
/* 2441 */         LocalCache.LoadingValueReference<K, V> loadingValueReference = new LocalCache.LoadingValueReference<K, V>();
/* 2442 */         e = newEntry(key, hash, first);
/* 2443 */         e.setValueReference(loadingValueReference);
/* 2444 */         table.set(index, e);
/* 2445 */         return loadingValueReference;
/*      */       } finally {
/* 2447 */         unlock();
/* 2448 */         postWriteCleanup();
/*      */       } 
/*      */     }
/*      */     
/*      */     void tryDrainReferenceQueues() {
/* 2458 */       if (tryLock())
/*      */         try {
/* 2460 */           drainReferenceQueues();
/*      */         } finally {
/* 2462 */           unlock();
/*      */         }  
/*      */     }
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     void drainReferenceQueues() {
/* 2473 */       if (this.map.usesKeyReferences())
/* 2474 */         drainKeyReferenceQueue(); 
/* 2476 */       if (this.map.usesValueReferences())
/* 2477 */         drainValueReferenceQueue(); 
/*      */     }
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     void drainKeyReferenceQueue() {
/* 2484 */       int i = 0;
/*      */       Reference<? extends K> ref;
/* 2485 */       while ((ref = this.keyReferenceQueue.poll()) != null) {
/* 2487 */         LocalCache.ReferenceEntry<K, V> entry = (LocalCache.ReferenceEntry)ref;
/* 2488 */         this.map.reclaimKey(entry);
/* 2489 */         if (++i == 16)
/*      */           break; 
/*      */       } 
/*      */     }
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     void drainValueReferenceQueue() {
/* 2498 */       int i = 0;
/*      */       Reference<? extends V> ref;
/* 2499 */       while ((ref = this.valueReferenceQueue.poll()) != null) {
/* 2501 */         LocalCache.ValueReference<K, V> valueReference = (LocalCache.ValueReference)ref;
/* 2502 */         this.map.reclaimValue(valueReference);
/* 2503 */         if (++i == 16)
/*      */           break; 
/*      */       } 
/*      */     }
/*      */     
/*      */     void clearReferenceQueues() {
/* 2513 */       if (this.map.usesKeyReferences())
/* 2514 */         clearKeyReferenceQueue(); 
/* 2516 */       if (this.map.usesValueReferences())
/* 2517 */         clearValueReferenceQueue(); 
/*      */     }
/*      */     
/*      */     void clearKeyReferenceQueue() {
/* 2522 */       while (this.keyReferenceQueue.poll() != null);
/*      */     }
/*      */     
/*      */     void clearValueReferenceQueue() {
/* 2526 */       while (this.valueReferenceQueue.poll() != null);
/*      */     }
/*      */     
/*      */     void recordRead(LocalCache.ReferenceEntry<K, V> entry, long now) {
/* 2539 */       if (this.map.recordsAccess())
/* 2540 */         entry.setAccessTime(now); 
/* 2542 */       this.recencyQueue.add(entry);
/*      */     }
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     void recordLockedRead(LocalCache.ReferenceEntry<K, V> entry, long now) {
/* 2554 */       if (this.map.recordsAccess())
/* 2555 */         entry.setAccessTime(now); 
/* 2557 */       this.accessQueue.add(entry);
/*      */     }
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     void recordWrite(LocalCache.ReferenceEntry<K, V> entry, int weight, long now) {
/* 2567 */       drainRecencyQueue();
/* 2568 */       this.totalWeight += weight;
/* 2570 */       if (this.map.recordsAccess())
/* 2571 */         entry.setAccessTime(now); 
/* 2573 */       if (this.map.recordsWrite())
/* 2574 */         entry.setWriteTime(now); 
/* 2576 */       this.accessQueue.add(entry);
/* 2577 */       this.writeQueue.add(entry);
/*      */     }
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     void drainRecencyQueue() {
/*      */       LocalCache.ReferenceEntry<K, V> e;
/* 2589 */       while ((e = this.recencyQueue.poll()) != null) {
/* 2594 */         if (this.accessQueue.contains(e))
/* 2595 */           this.accessQueue.add(e); 
/*      */       } 
/*      */     }
/*      */     
/*      */     void tryExpireEntries(long now) {
/* 2606 */       if (tryLock())
/*      */         try {
/* 2608 */           expireEntries(now);
/*      */         } finally {
/* 2610 */           unlock();
/*      */         }  
/*      */     }
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     void expireEntries(long now) {
/* 2618 */       drainRecencyQueue();
/*      */       LocalCache.ReferenceEntry<K, V> e;
/* 2621 */       while ((e = this.writeQueue.peek()) != null && this.map.isExpired(e, now)) {
/* 2622 */         if (!removeEntry(e, e.getHash(), RemovalCause.EXPIRED))
/* 2623 */           throw new AssertionError(); 
/*      */       } 
/* 2626 */       while ((e = this.accessQueue.peek()) != null && this.map.isExpired(e, now)) {
/* 2627 */         if (!removeEntry(e, e.getHash(), RemovalCause.EXPIRED))
/* 2628 */           throw new AssertionError(); 
/*      */       } 
/*      */     }
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     void enqueueNotification(LocalCache.ReferenceEntry<K, V> entry, RemovalCause cause) {
/* 2637 */       enqueueNotification(entry.getKey(), entry.getHash(), entry.getValueReference(), cause);
/*      */     }
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     void enqueueNotification(@Nullable K key, int hash, LocalCache.ValueReference<K, V> valueReference, RemovalCause cause) {
/* 2643 */       this.totalWeight -= valueReference.getWeight();
/* 2644 */       if (cause.wasEvicted())
/* 2645 */         this.statsCounter.recordEviction(); 
/* 2647 */       if (this.map.removalNotificationQueue != LocalCache.DISCARDING_QUEUE) {
/* 2648 */         V value = valueReference.get();
/* 2649 */         RemovalNotification<K, V> notification = new RemovalNotification<K, V>(key, value, cause);
/* 2650 */         this.map.removalNotificationQueue.offer(notification);
/*      */       } 
/*      */     }
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     void evictEntries() {
/* 2660 */       if (!this.map.evictsBySize())
/*      */         return; 
/* 2664 */       drainRecencyQueue();
/* 2665 */       while (this.totalWeight > this.maxSegmentWeight) {
/* 2666 */         LocalCache.ReferenceEntry<K, V> e = getNextEvictable();
/* 2667 */         if (!removeEntry(e, e.getHash(), RemovalCause.SIZE))
/* 2668 */           throw new AssertionError(); 
/*      */       } 
/*      */     }
/*      */     
/*      */     LocalCache.ReferenceEntry<K, V> getNextEvictable() {
/* 2675 */       for (LocalCache.ReferenceEntry<K, V> e : this.accessQueue) {
/* 2676 */         int weight = e.getValueReference().getWeight();
/* 2677 */         if (weight > 0)
/* 2678 */           return e; 
/*      */       } 
/* 2681 */       throw new AssertionError();
/*      */     }
/*      */     
/*      */     LocalCache.ReferenceEntry<K, V> getFirst(int hash) {
/* 2689 */       AtomicReferenceArray<LocalCache.ReferenceEntry<K, V>> table = this.table;
/* 2690 */       return table.get(hash & table.length() - 1);
/*      */     }
/*      */     
/*      */     @Nullable
/*      */     LocalCache.ReferenceEntry<K, V> getEntry(Object key, int hash) {
/* 2697 */       for (LocalCache.ReferenceEntry<K, V> e = getFirst(hash); e != null; e = e.getNext()) {
/* 2698 */         if (e.getHash() == hash) {
/* 2702 */           K entryKey = e.getKey();
/* 2703 */           if (entryKey == null) {
/* 2704 */             tryDrainReferenceQueues();
/* 2708 */           } else if (this.map.keyEquivalence.equivalent(key, entryKey)) {
/* 2709 */             return e;
/*      */           } 
/*      */         } 
/*      */       } 
/* 2713 */       return null;
/*      */     }
/*      */     
/*      */     @Nullable
/*      */     LocalCache.ReferenceEntry<K, V> getLiveEntry(Object key, int hash, long now) {
/* 2718 */       LocalCache.ReferenceEntry<K, V> e = getEntry(key, hash);
/* 2719 */       if (e == null)
/* 2720 */         return null; 
/* 2721 */       if (this.map.isExpired(e, now)) {
/* 2722 */         tryExpireEntries(now);
/* 2723 */         return null;
/*      */       } 
/* 2725 */       return e;
/*      */     }
/*      */     
/*      */     V getLiveValue(LocalCache.ReferenceEntry<K, V> entry, long now) {
/* 2733 */       if (entry.getKey() == null) {
/* 2734 */         tryDrainReferenceQueues();
/* 2735 */         return null;
/*      */       } 
/* 2737 */       V value = (V)entry.getValueReference().get();
/* 2738 */       if (value == null) {
/* 2739 */         tryDrainReferenceQueues();
/* 2740 */         return null;
/*      */       } 
/* 2743 */       if (this.map.isExpired(entry, now)) {
/* 2744 */         tryExpireEntries(now);
/* 2745 */         return null;
/*      */       } 
/* 2747 */       return value;
/*      */     }
/*      */     
/*      */     @Nullable
/*      */     V get(Object key, int hash) {
/*      */       try {
/* 2753 */         if (this.count != 0) {
/* 2754 */           long now = this.map.ticker.read();
/* 2755 */           LocalCache.ReferenceEntry<K, V> e = getLiveEntry(key, hash, now);
/* 2756 */           if (e == null)
/* 2757 */             return null; 
/* 2760 */           V value = (V)e.getValueReference().get();
/* 2761 */           if (value != null) {
/* 2762 */             recordRead(e, now);
/* 2763 */             return scheduleRefresh(e, e.getKey(), hash, value, now, this.map.defaultLoader);
/*      */           } 
/* 2765 */           tryDrainReferenceQueues();
/*      */         } 
/* 2767 */         return null;
/*      */       } finally {
/* 2769 */         postReadCleanup();
/*      */       } 
/*      */     }
/*      */     
/*      */     boolean containsKey(Object key, int hash) {
/*      */       try {
/* 2775 */         if (this.count != 0) {
/* 2776 */           long now = this.map.ticker.read();
/* 2777 */           LocalCache.ReferenceEntry<K, V> e = getLiveEntry(key, hash, now);
/* 2778 */           if (e == null)
/* 2779 */             return false; 
/* 2781 */           return (e.getValueReference().get() != null);
/*      */         } 
/* 2784 */         return false;
/*      */       } finally {
/* 2786 */         postReadCleanup();
/*      */       } 
/*      */     }
/*      */     
/*      */     @VisibleForTesting
/*      */     boolean containsValue(Object value) {
/*      */       try {
/* 2797 */         if (this.count != 0) {
/* 2798 */           long now = this.map.ticker.read();
/* 2799 */           AtomicReferenceArray<LocalCache.ReferenceEntry<K, V>> table = this.table;
/* 2800 */           int length = table.length();
/* 2801 */           for (int i = 0; i < length; i++) {
/* 2802 */             for (LocalCache.ReferenceEntry<K, V> e = table.get(i); e != null; e = e.getNext()) {
/* 2803 */               V entryValue = getLiveValue(e, now);
/* 2804 */               if (entryValue != null)
/* 2807 */                 if (this.map.valueEquivalence.equivalent(value, entryValue))
/* 2808 */                   return true;  
/*      */             } 
/*      */           } 
/*      */         } 
/* 2814 */         return false;
/*      */       } finally {
/* 2816 */         postReadCleanup();
/*      */       } 
/*      */     }
/*      */     
/*      */     @Nullable
/*      */     V put(K key, int hash, V value, boolean onlyIfAbsent) {
/* 2822 */       lock();
/*      */       try {
/* 2824 */         long now = this.map.ticker.read();
/* 2825 */         preWriteCleanup(now);
/* 2827 */         int newCount = this.count + 1;
/* 2828 */         if (newCount > this.threshold) {
/* 2829 */           expand();
/* 2830 */           newCount = this.count + 1;
/*      */         } 
/* 2833 */         AtomicReferenceArray<LocalCache.ReferenceEntry<K, V>> table = this.table;
/* 2834 */         int index = hash & table.length() - 1;
/* 2835 */         LocalCache.ReferenceEntry<K, V> first = table.get(index);
/* 2838 */         for (LocalCache.ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
/* 2839 */           K entryKey = e.getKey();
/* 2840 */           if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
/* 2844 */             LocalCache.ValueReference<K, V> valueReference = e.getValueReference();
/* 2845 */             V entryValue = valueReference.get();
/* 2847 */             if (entryValue == null) {
/* 2848 */               this.modCount++;
/* 2849 */               if (valueReference.isActive()) {
/* 2850 */                 enqueueNotification(key, hash, valueReference, RemovalCause.COLLECTED);
/* 2851 */                 setValue(e, key, value, now);
/* 2852 */                 newCount = this.count;
/*      */               } else {
/* 2854 */                 setValue(e, key, value, now);
/* 2855 */                 newCount = this.count + 1;
/*      */               } 
/* 2857 */               this.count = newCount;
/* 2858 */               evictEntries();
/* 2859 */               return null;
/*      */             } 
/* 2860 */             if (onlyIfAbsent) {
/* 2864 */               recordLockedRead(e, now);
/* 2865 */               return entryValue;
/*      */             } 
/* 2868 */             this.modCount++;
/* 2869 */             enqueueNotification(key, hash, valueReference, RemovalCause.REPLACED);
/* 2870 */             setValue(e, key, value, now);
/* 2871 */             evictEntries();
/* 2872 */             return entryValue;
/*      */           } 
/*      */         } 
/* 2878 */         this.modCount++;
/* 2879 */         LocalCache.ReferenceEntry<K, V> newEntry = newEntry(key, hash, first);
/* 2880 */         setValue(newEntry, key, value, now);
/* 2881 */         table.set(index, newEntry);
/* 2882 */         newCount = this.count + 1;
/* 2883 */         this.count = newCount;
/* 2884 */         evictEntries();
/* 2885 */         return null;
/*      */       } finally {
/* 2887 */         unlock();
/* 2888 */         postWriteCleanup();
/*      */       } 
/*      */     }
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     void expand() {
/* 2897 */       AtomicReferenceArray<LocalCache.ReferenceEntry<K, V>> oldTable = this.table;
/* 2898 */       int oldCapacity = oldTable.length();
/* 2899 */       if (oldCapacity >= 1073741824)
/*      */         return; 
/* 2913 */       int newCount = this.count;
/* 2914 */       AtomicReferenceArray<LocalCache.ReferenceEntry<K, V>> newTable = newEntryArray(oldCapacity << 1);
/* 2915 */       this.threshold = newTable.length() * 3 / 4;
/* 2916 */       int newMask = newTable.length() - 1;
/* 2917 */       for (int oldIndex = 0; oldIndex < oldCapacity; oldIndex++) {
/* 2920 */         LocalCache.ReferenceEntry<K, V> head = oldTable.get(oldIndex);
/* 2922 */         if (head != null) {
/* 2923 */           LocalCache.ReferenceEntry<K, V> next = head.getNext();
/* 2924 */           int headIndex = head.getHash() & newMask;
/* 2927 */           if (next == null) {
/* 2928 */             newTable.set(headIndex, head);
/*      */           } else {
/* 2933 */             LocalCache.ReferenceEntry<K, V> tail = head;
/* 2934 */             int tailIndex = headIndex;
/*      */             LocalCache.ReferenceEntry<K, V> e;
/* 2935 */             for (e = next; e != null; e = e.getNext()) {
/* 2936 */               int newIndex = e.getHash() & newMask;
/* 2937 */               if (newIndex != tailIndex) {
/* 2939 */                 tailIndex = newIndex;
/* 2940 */                 tail = e;
/*      */               } 
/*      */             } 
/* 2943 */             newTable.set(tailIndex, tail);
/* 2946 */             for (e = head; e != tail; e = e.getNext()) {
/* 2947 */               int newIndex = e.getHash() & newMask;
/* 2948 */               LocalCache.ReferenceEntry<K, V> newNext = newTable.get(newIndex);
/* 2949 */               LocalCache.ReferenceEntry<K, V> newFirst = copyEntry(e, newNext);
/* 2950 */               if (newFirst != null) {
/* 2951 */                 newTable.set(newIndex, newFirst);
/*      */               } else {
/* 2953 */                 removeCollectedEntry(e);
/* 2954 */                 newCount--;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/* 2960 */       this.table = newTable;
/* 2961 */       this.count = newCount;
/*      */     }
/*      */     
/*      */     boolean replace(K key, int hash, V oldValue, V newValue) {
/* 2965 */       lock();
/*      */       try {
/* 2967 */         long now = this.map.ticker.read();
/* 2968 */         preWriteCleanup(now);
/* 2970 */         AtomicReferenceArray<LocalCache.ReferenceEntry<K, V>> table = this.table;
/* 2971 */         int index = hash & table.length() - 1;
/* 2972 */         LocalCache.ReferenceEntry<K, V> first = table.get(index);
/* 2974 */         for (LocalCache.ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
/* 2975 */           K entryKey = e.getKey();
/* 2976 */           if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
/* 2978 */             LocalCache.ValueReference<K, V> valueReference = e.getValueReference();
/* 2979 */             V entryValue = valueReference.get();
/* 2980 */             if (entryValue == null) {
/* 2981 */               if (valueReference.isActive()) {
/* 2983 */                 int newCount = this.count - 1;
/* 2984 */                 this.modCount++;
/* 2985 */                 LocalCache.ReferenceEntry<K, V> newFirst = removeValueFromChain(first, e, entryKey, hash, valueReference, RemovalCause.COLLECTED);
/* 2987 */                 newCount = this.count - 1;
/* 2988 */                 table.set(index, newFirst);
/* 2989 */                 this.count = newCount;
/*      */               } 
/* 2991 */               return false;
/*      */             } 
/* 2994 */             if (this.map.valueEquivalence.equivalent(oldValue, entryValue)) {
/* 2995 */               this.modCount++;
/* 2996 */               enqueueNotification(key, hash, valueReference, RemovalCause.REPLACED);
/* 2997 */               setValue(e, key, newValue, now);
/* 2998 */               evictEntries();
/* 2999 */               return true;
/*      */             } 
/* 3003 */             recordLockedRead(e, now);
/* 3004 */             return false;
/*      */           } 
/*      */         } 
/* 3009 */         return false;
/*      */       } finally {
/* 3011 */         unlock();
/* 3012 */         postWriteCleanup();
/*      */       } 
/*      */     }
/*      */     
/*      */     @Nullable
/*      */     V replace(K key, int hash, V newValue) {
/* 3018 */       lock();
/*      */       try {
/* 3020 */         long now = this.map.ticker.read();
/* 3021 */         preWriteCleanup(now);
/* 3023 */         AtomicReferenceArray<LocalCache.ReferenceEntry<K, V>> table = this.table;
/* 3024 */         int index = hash & table.length() - 1;
/* 3025 */         LocalCache.ReferenceEntry<K, V> first = table.get(index);
/*      */         LocalCache.ReferenceEntry<K, V> e;
/* 3027 */         for (e = first; e != null; e = e.getNext()) {
/* 3028 */           K entryKey = e.getKey();
/* 3029 */           if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
/* 3031 */             LocalCache.ValueReference<K, V> valueReference = e.getValueReference();
/* 3032 */             V entryValue = valueReference.get();
/* 3033 */             if (entryValue == null) {
/* 3034 */               if (valueReference.isActive()) {
/* 3036 */                 int newCount = this.count - 1;
/* 3037 */                 this.modCount++;
/* 3038 */                 LocalCache.ReferenceEntry<K, V> newFirst = removeValueFromChain(first, e, entryKey, hash, valueReference, RemovalCause.COLLECTED);
/* 3040 */                 newCount = this.count - 1;
/* 3041 */                 table.set(index, newFirst);
/* 3042 */                 this.count = newCount;
/*      */               } 
/* 3044 */               return null;
/*      */             } 
/* 3047 */             this.modCount++;
/* 3048 */             enqueueNotification(key, hash, valueReference, RemovalCause.REPLACED);
/* 3049 */             setValue(e, key, newValue, now);
/* 3050 */             evictEntries();
/* 3051 */             return entryValue;
/*      */           } 
/*      */         } 
/* 3055 */         e = null;
/* 3058 */         return (V)e;
/*      */       } finally {
/*      */         unlock();
/* 3058 */         postWriteCleanup();
/*      */       } 
/*      */     }
/*      */     
/*      */     @Nullable
/*      */     V remove(Object key, int hash) {
/* 3064 */       lock();
/*      */       try {
/* 3066 */         long now = this.map.ticker.read();
/* 3067 */         preWriteCleanup(now);
/* 3069 */         int newCount = this.count - 1;
/* 3070 */         AtomicReferenceArray<LocalCache.ReferenceEntry<K, V>> table = this.table;
/* 3071 */         int index = hash & table.length() - 1;
/* 3072 */         LocalCache.ReferenceEntry<K, V> first = table.get(index);
/*      */         LocalCache.ReferenceEntry<K, V> e;
/* 3074 */         for (e = first; e != null; e = e.getNext()) {
/* 3075 */           K entryKey = e.getKey();
/* 3076 */           if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
/*      */             RemovalCause cause;
/* 3078 */             LocalCache.ValueReference<K, V> valueReference = e.getValueReference();
/* 3079 */             V entryValue = valueReference.get();
/* 3082 */             if (entryValue != null) {
/* 3083 */               cause = RemovalCause.EXPLICIT;
/* 3084 */             } else if (valueReference.isActive()) {
/* 3085 */               cause = RemovalCause.COLLECTED;
/*      */             } else {
/* 3088 */               return null;
/*      */             } 
/* 3091 */             this.modCount++;
/* 3092 */             LocalCache.ReferenceEntry<K, V> newFirst = removeValueFromChain(first, e, entryKey, hash, valueReference, cause);
/* 3094 */             newCount = this.count - 1;
/* 3095 */             table.set(index, newFirst);
/* 3096 */             this.count = newCount;
/* 3097 */             return entryValue;
/*      */           } 
/*      */         } 
/* 3101 */         e = null;
/* 3104 */         return (V)e;
/*      */       } finally {
/*      */         unlock();
/* 3104 */         postWriteCleanup();
/*      */       } 
/*      */     }
/*      */     
/*      */     boolean storeLoadedValue(K key, int hash, LocalCache.LoadingValueReference<K, V> oldValueReference, V newValue) {
/* 3110 */       lock();
/*      */       try {
/* 3112 */         long now = this.map.ticker.read();
/* 3113 */         preWriteCleanup(now);
/* 3115 */         int newCount = this.count + 1;
/* 3116 */         if (newCount > this.threshold) {
/* 3117 */           expand();
/* 3118 */           newCount = this.count + 1;
/*      */         } 
/* 3121 */         AtomicReferenceArray<LocalCache.ReferenceEntry<K, V>> table = this.table;
/* 3122 */         int index = hash & table.length() - 1;
/* 3123 */         LocalCache.ReferenceEntry<K, V> first = table.get(index);
/* 3125 */         for (LocalCache.ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
/* 3126 */           K entryKey = e.getKey();
/* 3127 */           if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
/* 3129 */             LocalCache.ValueReference<K, V> valueReference = e.getValueReference();
/* 3130 */             V entryValue = valueReference.get();
/* 3133 */             if (oldValueReference == valueReference || (entryValue == null && valueReference != LocalCache.UNSET)) {
/* 3135 */               this.modCount++;
/* 3136 */               if (oldValueReference.isActive()) {
/* 3137 */                 RemovalCause cause = (entryValue == null) ? RemovalCause.COLLECTED : RemovalCause.REPLACED;
/* 3139 */                 enqueueNotification(key, hash, oldValueReference, cause);
/* 3140 */                 newCount--;
/*      */               } 
/* 3142 */               setValue(e, key, newValue, now);
/* 3143 */               this.count = newCount;
/* 3144 */               evictEntries();
/* 3145 */               return true;
/*      */             } 
/* 3149 */             valueReference = new LocalCache.WeightedStrongValueReference<K, V>(newValue, 0);
/* 3150 */             enqueueNotification(key, hash, valueReference, RemovalCause.REPLACED);
/* 3151 */             return false;
/*      */           } 
/*      */         } 
/* 3155 */         this.modCount++;
/* 3156 */         LocalCache.ReferenceEntry<K, V> newEntry = newEntry(key, hash, first);
/* 3157 */         setValue(newEntry, key, newValue, now);
/* 3158 */         table.set(index, newEntry);
/* 3159 */         this.count = newCount;
/* 3160 */         evictEntries();
/* 3161 */         return true;
/*      */       } finally {
/* 3163 */         unlock();
/* 3164 */         postWriteCleanup();
/*      */       } 
/*      */     }
/*      */     
/*      */     boolean remove(Object key, int hash, Object value) {
/* 3169 */       lock();
/*      */       try {
/* 3171 */         long now = this.map.ticker.read();
/* 3172 */         preWriteCleanup(now);
/* 3174 */         int newCount = this.count - 1;
/* 3175 */         AtomicReferenceArray<LocalCache.ReferenceEntry<K, V>> table = this.table;
/* 3176 */         int index = hash & table.length() - 1;
/* 3177 */         LocalCache.ReferenceEntry<K, V> first = table.get(index);
/* 3179 */         for (LocalCache.ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
/* 3180 */           K entryKey = e.getKey();
/* 3181 */           if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
/*      */             RemovalCause cause;
/* 3183 */             LocalCache.ValueReference<K, V> valueReference = e.getValueReference();
/* 3184 */             V entryValue = valueReference.get();
/* 3187 */             if (this.map.valueEquivalence.equivalent(value, entryValue)) {
/* 3188 */               cause = RemovalCause.EXPLICIT;
/* 3189 */             } else if (entryValue == null && valueReference.isActive()) {
/* 3190 */               cause = RemovalCause.COLLECTED;
/*      */             } else {
/* 3193 */               return false;
/*      */             } 
/* 3196 */             this.modCount++;
/* 3197 */             LocalCache.ReferenceEntry<K, V> newFirst = removeValueFromChain(first, e, entryKey, hash, valueReference, cause);
/* 3199 */             newCount = this.count - 1;
/* 3200 */             table.set(index, newFirst);
/* 3201 */             this.count = newCount;
/* 3202 */             return (cause == RemovalCause.EXPLICIT);
/*      */           } 
/*      */         } 
/* 3206 */         return false;
/*      */       } finally {
/* 3208 */         unlock();
/* 3209 */         postWriteCleanup();
/*      */       } 
/*      */     }
/*      */     
/*      */     void clear() {
/* 3214 */       if (this.count != 0) {
/* 3215 */         lock();
/*      */         try {
/* 3217 */           AtomicReferenceArray<LocalCache.ReferenceEntry<K, V>> table = this.table;
/*      */           int i;
/* 3218 */           for (i = 0; i < table.length(); i++) {
/* 3219 */             for (LocalCache.ReferenceEntry<K, V> e = table.get(i); e != null; e = e.getNext()) {
/* 3221 */               if (e.getValueReference().isActive())
/* 3222 */                 enqueueNotification(e, RemovalCause.EXPLICIT); 
/*      */             } 
/*      */           } 
/* 3226 */           for (i = 0; i < table.length(); i++)
/* 3227 */             table.set(i, null); 
/* 3229 */           clearReferenceQueues();
/* 3230 */           this.writeQueue.clear();
/* 3231 */           this.accessQueue.clear();
/* 3232 */           this.readCount.set(0);
/* 3234 */           this.modCount++;
/* 3235 */           this.count = 0;
/*      */         } finally {
/* 3237 */           unlock();
/* 3238 */           postWriteCleanup();
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     @Nullable
/*      */     @GuardedBy("Segment.this")
/*      */     LocalCache.ReferenceEntry<K, V> removeValueFromChain(LocalCache.ReferenceEntry<K, V> first, LocalCache.ReferenceEntry<K, V> entry, @Nullable K key, int hash, LocalCache.ValueReference<K, V> valueReference, RemovalCause cause) {
/* 3248 */       enqueueNotification(key, hash, valueReference, cause);
/* 3249 */       this.writeQueue.remove(entry);
/* 3250 */       this.accessQueue.remove(entry);
/* 3252 */       if (valueReference.isLoading()) {
/* 3253 */         valueReference.notifyNewValue(null);
/* 3254 */         return first;
/*      */       } 
/* 3256 */       return removeEntryFromChain(first, entry);
/*      */     }
/*      */     
/*      */     @Nullable
/*      */     @GuardedBy("Segment.this")
/*      */     LocalCache.ReferenceEntry<K, V> removeEntryFromChain(LocalCache.ReferenceEntry<K, V> first, LocalCache.ReferenceEntry<K, V> entry) {
/* 3264 */       int newCount = this.count;
/* 3265 */       LocalCache.ReferenceEntry<K, V> newFirst = entry.getNext();
/* 3266 */       for (LocalCache.ReferenceEntry<K, V> e = first; e != entry; e = e.getNext()) {
/* 3267 */         LocalCache.ReferenceEntry<K, V> next = copyEntry(e, newFirst);
/* 3268 */         if (next != null) {
/* 3269 */           newFirst = next;
/*      */         } else {
/* 3271 */           removeCollectedEntry(e);
/* 3272 */           newCount--;
/*      */         } 
/*      */       } 
/* 3275 */       this.count = newCount;
/* 3276 */       return newFirst;
/*      */     }
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     void removeCollectedEntry(LocalCache.ReferenceEntry<K, V> entry) {
/* 3281 */       enqueueNotification(entry, RemovalCause.COLLECTED);
/* 3282 */       this.writeQueue.remove(entry);
/* 3283 */       this.accessQueue.remove(entry);
/*      */     }
/*      */     
/*      */     boolean reclaimKey(LocalCache.ReferenceEntry<K, V> entry, int hash) {
/* 3290 */       lock();
/*      */       try {
/* 3292 */         int newCount = this.count - 1;
/* 3293 */         AtomicReferenceArray<LocalCache.ReferenceEntry<K, V>> table = this.table;
/* 3294 */         int index = hash & table.length() - 1;
/* 3295 */         LocalCache.ReferenceEntry<K, V> first = table.get(index);
/* 3297 */         for (LocalCache.ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
/* 3298 */           if (e == entry) {
/* 3299 */             this.modCount++;
/* 3300 */             LocalCache.ReferenceEntry<K, V> newFirst = removeValueFromChain(first, e, e.getKey(), hash, e.getValueReference(), RemovalCause.COLLECTED);
/* 3302 */             newCount = this.count - 1;
/* 3303 */             table.set(index, newFirst);
/* 3304 */             this.count = newCount;
/* 3305 */             return true;
/*      */           } 
/*      */         } 
/* 3309 */         return false;
/*      */       } finally {
/* 3311 */         unlock();
/* 3312 */         postWriteCleanup();
/*      */       } 
/*      */     }
/*      */     
/*      */     boolean reclaimValue(K key, int hash, LocalCache.ValueReference<K, V> valueReference) {
/* 3320 */       lock();
/*      */       try {
/* 3322 */         int newCount = this.count - 1;
/* 3323 */         AtomicReferenceArray<LocalCache.ReferenceEntry<K, V>> table = this.table;
/* 3324 */         int index = hash & table.length() - 1;
/* 3325 */         LocalCache.ReferenceEntry<K, V> first = table.get(index);
/* 3327 */         for (LocalCache.ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
/* 3328 */           K entryKey = e.getKey();
/* 3329 */           if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
/* 3331 */             LocalCache.ValueReference<K, V> v = e.getValueReference();
/* 3332 */             if (v == valueReference) {
/* 3333 */               this.modCount++;
/* 3334 */               LocalCache.ReferenceEntry<K, V> newFirst = removeValueFromChain(first, e, entryKey, hash, valueReference, RemovalCause.COLLECTED);
/* 3336 */               newCount = this.count - 1;
/* 3337 */               table.set(index, newFirst);
/* 3338 */               this.count = newCount;
/* 3339 */               return true;
/*      */             } 
/* 3341 */             return false;
/*      */           } 
/*      */         } 
/* 3345 */         return false;
/*      */       } finally {
/* 3347 */         unlock();
/* 3348 */         if (!isHeldByCurrentThread())
/* 3349 */           postWriteCleanup(); 
/*      */       } 
/*      */     }
/*      */     
/*      */     boolean removeLoadingValue(K key, int hash, LocalCache.LoadingValueReference<K, V> valueReference) {
/* 3355 */       lock();
/*      */       try {
/* 3357 */         AtomicReferenceArray<LocalCache.ReferenceEntry<K, V>> table = this.table;
/* 3358 */         int index = hash & table.length() - 1;
/* 3359 */         LocalCache.ReferenceEntry<K, V> first = table.get(index);
/* 3361 */         for (LocalCache.ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
/* 3362 */           K entryKey = e.getKey();
/* 3363 */           if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
/* 3365 */             LocalCache.ValueReference<K, V> v = e.getValueReference();
/* 3366 */             if (v == valueReference) {
/* 3367 */               if (valueReference.isActive()) {
/* 3368 */                 e.setValueReference(valueReference.getOldValue());
/*      */               } else {
/* 3370 */                 LocalCache.ReferenceEntry<K, V> newFirst = removeEntryFromChain(first, e);
/* 3371 */                 table.set(index, newFirst);
/*      */               } 
/* 3373 */               return true;
/*      */             } 
/* 3375 */             return false;
/*      */           } 
/*      */         } 
/* 3379 */         return false;
/*      */       } finally {
/* 3381 */         unlock();
/* 3382 */         postWriteCleanup();
/*      */       } 
/*      */     }
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     boolean removeEntry(LocalCache.ReferenceEntry<K, V> entry, int hash, RemovalCause cause) {
/* 3388 */       int newCount = this.count - 1;
/* 3389 */       AtomicReferenceArray<LocalCache.ReferenceEntry<K, V>> table = this.table;
/* 3390 */       int index = hash & table.length() - 1;
/* 3391 */       LocalCache.ReferenceEntry<K, V> first = table.get(index);
/* 3393 */       for (LocalCache.ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
/* 3394 */         if (e == entry) {
/* 3395 */           this.modCount++;
/* 3396 */           LocalCache.ReferenceEntry<K, V> newFirst = removeValueFromChain(first, e, e.getKey(), hash, e.getValueReference(), cause);
/* 3398 */           newCount = this.count - 1;
/* 3399 */           table.set(index, newFirst);
/* 3400 */           this.count = newCount;
/* 3401 */           return true;
/*      */         } 
/*      */       } 
/* 3405 */       return false;
/*      */     }
/*      */     
/*      */     void postReadCleanup() {
/* 3413 */       if ((this.readCount.incrementAndGet() & 0x3F) == 0)
/* 3414 */         cleanUp(); 
/*      */     }
/*      */     
/*      */     @GuardedBy("Segment.this")
/*      */     void preWriteCleanup(long now) {
/* 3426 */       runLockedCleanup(now);
/*      */     }
/*      */     
/*      */     void postWriteCleanup() {
/* 3433 */       runUnlockedCleanup();
/*      */     }
/*      */     
/*      */     void cleanUp() {
/* 3437 */       long now = this.map.ticker.read();
/* 3438 */       runLockedCleanup(now);
/* 3439 */       runUnlockedCleanup();
/*      */     }
/*      */     
/*      */     void runLockedCleanup(long now) {
/* 3443 */       if (tryLock())
/*      */         try {
/* 3445 */           drainReferenceQueues();
/* 3446 */           expireEntries(now);
/* 3447 */           this.readCount.set(0);
/*      */         } finally {
/* 3449 */           unlock();
/*      */         }  
/*      */     }
/*      */     
/*      */     void runUnlockedCleanup() {
/* 3456 */       if (!isHeldByCurrentThread())
/* 3457 */         this.map.processPendingNotifications(); 
/*      */     }
/*      */   }
/*      */   
/*      */   static class LoadingValueReference<K, V> implements ValueReference<K, V> {
/*      */     volatile LocalCache.ValueReference<K, V> oldValue;
/*      */     
/* 3467 */     final SettableFuture<V> futureValue = SettableFuture.create();
/*      */     
/* 3468 */     final Stopwatch stopwatch = Stopwatch.createUnstarted();
/*      */     
/*      */     public LoadingValueReference() {
/* 3471 */       this(LocalCache.unset());
/*      */     }
/*      */     
/*      */     public LoadingValueReference(LocalCache.ValueReference<K, V> oldValue) {
/* 3475 */       this.oldValue = oldValue;
/*      */     }
/*      */     
/*      */     public boolean isLoading() {
/* 3480 */       return true;
/*      */     }
/*      */     
/*      */     public boolean isActive() {
/* 3485 */       return this.oldValue.isActive();
/*      */     }
/*      */     
/*      */     public int getWeight() {
/* 3490 */       return this.oldValue.getWeight();
/*      */     }
/*      */     
/*      */     public boolean set(@Nullable V newValue) {
/* 3494 */       return this.futureValue.set(newValue);
/*      */     }
/*      */     
/*      */     public boolean setException(Throwable t) {
/* 3498 */       return this.futureValue.setException(t);
/*      */     }
/*      */     
/*      */     private ListenableFuture<V> fullyFailedFuture(Throwable t) {
/* 3502 */       return Futures.immediateFailedFuture(t);
/*      */     }
/*      */     
/*      */     public void notifyNewValue(@Nullable V newValue) {
/* 3507 */       if (newValue != null) {
/* 3510 */         set(newValue);
/*      */       } else {
/* 3513 */         this.oldValue = LocalCache.unset();
/*      */       } 
/*      */     }
/*      */     
/*      */     public ListenableFuture<V> loadFuture(K key, CacheLoader<? super K, V> loader) {
/* 3520 */       this.stopwatch.start();
/* 3521 */       V previousValue = this.oldValue.get();
/*      */       try {
/* 3523 */         if (previousValue == null) {
/* 3524 */           V v = loader.load(key);
/* 3525 */           return set(v) ? (ListenableFuture<V>)this.futureValue : Futures.immediateFuture(v);
/*      */         } 
/* 3527 */         ListenableFuture<V> newValue = loader.reload(key, previousValue);
/* 3528 */         if (newValue == null)
/* 3529 */           return Futures.immediateFuture(null); 
/* 3533 */         return Futures.transform(newValue, new Function<V, V>() {
/*      */               public V apply(V newValue) {
/* 3536 */                 LocalCache.LoadingValueReference.this.set(newValue);
/* 3537 */                 return newValue;
/*      */               }
/*      */             });
/* 3540 */       } catch (Throwable t) {
/* 3541 */         if (t instanceof InterruptedException)
/* 3542 */           Thread.currentThread().interrupt(); 
/* 3544 */         return setException(t) ? (ListenableFuture<V>)this.futureValue : fullyFailedFuture(t);
/*      */       } 
/*      */     }
/*      */     
/*      */     public long elapsedNanos() {
/* 3549 */       return this.stopwatch.elapsed(TimeUnit.NANOSECONDS);
/*      */     }
/*      */     
/*      */     public V waitForValue() throws ExecutionException {
/* 3554 */       return (V)Uninterruptibles.getUninterruptibly((Future)this.futureValue);
/*      */     }
/*      */     
/*      */     public V get() {
/* 3559 */       return this.oldValue.get();
/*      */     }
/*      */     
/*      */     public LocalCache.ValueReference<K, V> getOldValue() {
/* 3563 */       return this.oldValue;
/*      */     }
/*      */     
/*      */     public LocalCache.ReferenceEntry<K, V> getEntry() {
/* 3568 */       return null;
/*      */     }
/*      */     
/*      */     public LocalCache.ValueReference<K, V> copyFor(ReferenceQueue<V> queue, @Nullable V value, LocalCache.ReferenceEntry<K, V> entry) {
/* 3574 */       return this;
/*      */     }
/*      */   }
/*      */   
/*      */   static final class WriteQueue<K, V> extends AbstractQueue<ReferenceEntry<K, V>> {
/* 3592 */     final LocalCache.ReferenceEntry<K, V> head = new LocalCache.AbstractReferenceEntry<K, V>() {
/*      */         public long getWriteTime() {
/* 3596 */           return Long.MAX_VALUE;
/*      */         }
/*      */         
/*      */         public void setWriteTime(long time) {}
/*      */         
/* 3602 */         LocalCache.ReferenceEntry<K, V> nextWrite = this;
/*      */         
/*      */         public LocalCache.ReferenceEntry<K, V> getNextInWriteQueue() {
/* 3606 */           return this.nextWrite;
/*      */         }
/*      */         
/*      */         public void setNextInWriteQueue(LocalCache.ReferenceEntry<K, V> next) {
/* 3611 */           this.nextWrite = next;
/*      */         }
/*      */         
/* 3614 */         LocalCache.ReferenceEntry<K, V> previousWrite = this;
/*      */         
/*      */         public LocalCache.ReferenceEntry<K, V> getPreviousInWriteQueue() {
/* 3618 */           return this.previousWrite;
/*      */         }
/*      */         
/*      */         public void setPreviousInWriteQueue(LocalCache.ReferenceEntry<K, V> previous) {
/* 3623 */           this.previousWrite = previous;
/*      */         }
/*      */       };
/*      */     
/*      */     public boolean offer(LocalCache.ReferenceEntry<K, V> entry) {
/* 3632 */       LocalCache.connectWriteOrder(entry.getPreviousInWriteQueue(), entry.getNextInWriteQueue());
/* 3635 */       LocalCache.connectWriteOrder(this.head.getPreviousInWriteQueue(), entry);
/* 3636 */       LocalCache.connectWriteOrder(entry, this.head);
/* 3638 */       return true;
/*      */     }
/*      */     
/*      */     public LocalCache.ReferenceEntry<K, V> peek() {
/* 3643 */       LocalCache.ReferenceEntry<K, V> next = this.head.getNextInWriteQueue();
/* 3644 */       return (next == this.head) ? null : next;
/*      */     }
/*      */     
/*      */     public LocalCache.ReferenceEntry<K, V> poll() {
/* 3649 */       LocalCache.ReferenceEntry<K, V> next = this.head.getNextInWriteQueue();
/* 3650 */       if (next == this.head)
/* 3651 */         return null; 
/* 3654 */       remove(next);
/* 3655 */       return next;
/*      */     }
/*      */     
/*      */     public boolean remove(Object o) {
/* 3661 */       LocalCache.ReferenceEntry<K, V> e = (LocalCache.ReferenceEntry<K, V>)o;
/* 3662 */       LocalCache.ReferenceEntry<K, V> previous = e.getPreviousInWriteQueue();
/* 3663 */       LocalCache.ReferenceEntry<K, V> next = e.getNextInWriteQueue();
/* 3664 */       LocalCache.connectWriteOrder(previous, next);
/* 3665 */       LocalCache.nullifyWriteOrder(e);
/* 3667 */       return (next != LocalCache.NullEntry.INSTANCE);
/*      */     }
/*      */     
/*      */     public boolean contains(Object o) {
/* 3673 */       LocalCache.ReferenceEntry<K, V> e = (LocalCache.ReferenceEntry<K, V>)o;
/* 3674 */       return (e.getNextInWriteQueue() != LocalCache.NullEntry.INSTANCE);
/*      */     }
/*      */     
/*      */     public boolean isEmpty() {
/* 3679 */       return (this.head.getNextInWriteQueue() == this.head);
/*      */     }
/*      */     
/*      */     public int size() {
/* 3684 */       int size = 0;
/* 3685 */       for (LocalCache.ReferenceEntry<K, V> e = this.head.getNextInWriteQueue(); e != this.head; 
/* 3686 */         e = e.getNextInWriteQueue())
/* 3687 */         size++; 
/* 3689 */       return size;
/*      */     }
/*      */     
/*      */     public void clear() {
/* 3694 */       LocalCache.ReferenceEntry<K, V> e = this.head.getNextInWriteQueue();
/* 3695 */       while (e != this.head) {
/* 3696 */         LocalCache.ReferenceEntry<K, V> next = e.getNextInWriteQueue();
/* 3697 */         LocalCache.nullifyWriteOrder(e);
/* 3698 */         e = next;
/*      */       } 
/* 3701 */       this.head.setNextInWriteQueue(this.head);
/* 3702 */       this.head.setPreviousInWriteQueue(this.head);
/*      */     }
/*      */     
/*      */     public Iterator<LocalCache.ReferenceEntry<K, V>> iterator() {
/* 3707 */       return (Iterator<LocalCache.ReferenceEntry<K, V>>)new AbstractSequentialIterator<LocalCache.ReferenceEntry<K, V>>(peek()) {
/*      */           protected LocalCache.ReferenceEntry<K, V> computeNext(LocalCache.ReferenceEntry<K, V> previous) {
/* 3710 */             LocalCache.ReferenceEntry<K, V> next = previous.getNextInWriteQueue();
/* 3711 */             return (next == LocalCache.WriteQueue.this.head) ? null : next;
/*      */           }
/*      */         };
/*      */     }
/*      */   }
/*      */   
/*      */   static final class AccessQueue<K, V> extends AbstractQueue<ReferenceEntry<K, V>> {
/* 3729 */     final LocalCache.ReferenceEntry<K, V> head = new LocalCache.AbstractReferenceEntry<K, V>() {
/*      */         public long getAccessTime() {
/* 3733 */           return Long.MAX_VALUE;
/*      */         }
/*      */         
/*      */         public void setAccessTime(long time) {}
/*      */         
/* 3739 */         LocalCache.ReferenceEntry<K, V> nextAccess = this;
/*      */         
/*      */         public LocalCache.ReferenceEntry<K, V> getNextInAccessQueue() {
/* 3743 */           return this.nextAccess;
/*      */         }
/*      */         
/*      */         public void setNextInAccessQueue(LocalCache.ReferenceEntry<K, V> next) {
/* 3748 */           this.nextAccess = next;
/*      */         }
/*      */         
/* 3751 */         LocalCache.ReferenceEntry<K, V> previousAccess = this;
/*      */         
/*      */         public LocalCache.ReferenceEntry<K, V> getPreviousInAccessQueue() {
/* 3755 */           return this.previousAccess;
/*      */         }
/*      */         
/*      */         public void setPreviousInAccessQueue(LocalCache.ReferenceEntry<K, V> previous) {
/* 3760 */           this.previousAccess = previous;
/*      */         }
/*      */       };
/*      */     
/*      */     public boolean offer(LocalCache.ReferenceEntry<K, V> entry) {
/* 3769 */       LocalCache.connectAccessOrder(entry.getPreviousInAccessQueue(), entry.getNextInAccessQueue());
/* 3772 */       LocalCache.connectAccessOrder(this.head.getPreviousInAccessQueue(), entry);
/* 3773 */       LocalCache.connectAccessOrder(entry, this.head);
/* 3775 */       return true;
/*      */     }
/*      */     
/*      */     public LocalCache.ReferenceEntry<K, V> peek() {
/* 3780 */       LocalCache.ReferenceEntry<K, V> next = this.head.getNextInAccessQueue();
/* 3781 */       return (next == this.head) ? null : next;
/*      */     }
/*      */     
/*      */     public LocalCache.ReferenceEntry<K, V> poll() {
/* 3786 */       LocalCache.ReferenceEntry<K, V> next = this.head.getNextInAccessQueue();
/* 3787 */       if (next == this.head)
/* 3788 */         return null; 
/* 3791 */       remove(next);
/* 3792 */       return next;
/*      */     }
/*      */     
/*      */     public boolean remove(Object o) {
/* 3798 */       LocalCache.ReferenceEntry<K, V> e = (LocalCache.ReferenceEntry<K, V>)o;
/* 3799 */       LocalCache.ReferenceEntry<K, V> previous = e.getPreviousInAccessQueue();
/* 3800 */       LocalCache.ReferenceEntry<K, V> next = e.getNextInAccessQueue();
/* 3801 */       LocalCache.connectAccessOrder(previous, next);
/* 3802 */       LocalCache.nullifyAccessOrder(e);
/* 3804 */       return (next != LocalCache.NullEntry.INSTANCE);
/*      */     }
/*      */     
/*      */     public boolean contains(Object o) {
/* 3810 */       LocalCache.ReferenceEntry<K, V> e = (LocalCache.ReferenceEntry<K, V>)o;
/* 3811 */       return (e.getNextInAccessQueue() != LocalCache.NullEntry.INSTANCE);
/*      */     }
/*      */     
/*      */     public boolean isEmpty() {
/* 3816 */       return (this.head.getNextInAccessQueue() == this.head);
/*      */     }
/*      */     
/*      */     public int size() {
/* 3821 */       int size = 0;
/* 3822 */       for (LocalCache.ReferenceEntry<K, V> e = this.head.getNextInAccessQueue(); e != this.head; 
/* 3823 */         e = e.getNextInAccessQueue())
/* 3824 */         size++; 
/* 3826 */       return size;
/*      */     }
/*      */     
/*      */     public void clear() {
/* 3831 */       LocalCache.ReferenceEntry<K, V> e = this.head.getNextInAccessQueue();
/* 3832 */       while (e != this.head) {
/* 3833 */         LocalCache.ReferenceEntry<K, V> next = e.getNextInAccessQueue();
/* 3834 */         LocalCache.nullifyAccessOrder(e);
/* 3835 */         e = next;
/*      */       } 
/* 3838 */       this.head.setNextInAccessQueue(this.head);
/* 3839 */       this.head.setPreviousInAccessQueue(this.head);
/*      */     }
/*      */     
/*      */     public Iterator<LocalCache.ReferenceEntry<K, V>> iterator() {
/* 3844 */       return (Iterator<LocalCache.ReferenceEntry<K, V>>)new AbstractSequentialIterator<LocalCache.ReferenceEntry<K, V>>(peek()) {
/*      */           protected LocalCache.ReferenceEntry<K, V> computeNext(LocalCache.ReferenceEntry<K, V> previous) {
/* 3847 */             LocalCache.ReferenceEntry<K, V> next = previous.getNextInAccessQueue();
/* 3848 */             return (next == LocalCache.AccessQueue.this.head) ? null : next;
/*      */           }
/*      */         };
/*      */     }
/*      */   }
/*      */   
/*      */   public void cleanUp() {
/* 3857 */     for (Segment<?, ?> segment : this.segments)
/* 3858 */       segment.cleanUp(); 
/*      */   }
/*      */   
/*      */   public boolean isEmpty() {
/* 3873 */     long sum = 0L;
/* 3874 */     Segment<K, V>[] segments = this.segments;
/*      */     int i;
/* 3875 */     for (i = 0; i < segments.length; i++) {
/* 3876 */       if ((segments[i]).count != 0)
/* 3877 */         return false; 
/* 3879 */       sum += (segments[i]).modCount;
/*      */     } 
/* 3882 */     if (sum != 0L) {
/* 3883 */       for (i = 0; i < segments.length; i++) {
/* 3884 */         if ((segments[i]).count != 0)
/* 3885 */           return false; 
/* 3887 */         sum -= (segments[i]).modCount;
/*      */       } 
/* 3889 */       if (sum != 0L)
/* 3890 */         return false; 
/*      */     } 
/* 3893 */     return true;
/*      */   }
/*      */   
/*      */   long longSize() {
/* 3897 */     Segment<K, V>[] segments = this.segments;
/* 3898 */     long sum = 0L;
/* 3899 */     for (int i = 0; i < segments.length; i++)
/* 3900 */       sum += (segments[i]).count; 
/* 3902 */     return sum;
/*      */   }
/*      */   
/*      */   public int size() {
/* 3907 */     return Ints.saturatedCast(longSize());
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public V get(@Nullable Object key) {
/* 3913 */     if (key == null)
/* 3914 */       return null; 
/* 3916 */     int hash = hash(key);
/* 3917 */     return segmentFor(hash).get(key, hash);
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public V getIfPresent(Object key) {
/* 3922 */     int hash = hash(Preconditions.checkNotNull(key));
/* 3923 */     V value = segmentFor(hash).get(key, hash);
/* 3924 */     if (value == null) {
/* 3925 */       this.globalStatsCounter.recordMisses(1);
/*      */     } else {
/* 3927 */       this.globalStatsCounter.recordHits(1);
/*      */     } 
/* 3929 */     return value;
/*      */   }
/*      */   
/*      */   V get(K key, CacheLoader<? super K, V> loader) throws ExecutionException {
/* 3933 */     int hash = hash(Preconditions.checkNotNull(key));
/* 3934 */     return segmentFor(hash).get(key, hash, loader);
/*      */   }
/*      */   
/*      */   V getOrLoad(K key) throws ExecutionException {
/* 3938 */     return get(key, this.defaultLoader);
/*      */   }
/*      */   
/*      */   ImmutableMap<K, V> getAllPresent(Iterable<?> keys) {
/* 3942 */     int hits = 0;
/* 3943 */     int misses = 0;
/* 3945 */     Map<K, V> result = Maps.newLinkedHashMap();
/* 3946 */     for (Object key : keys) {
/* 3947 */       V value = get(key);
/* 3948 */       if (value == null) {
/* 3949 */         misses++;
/*      */         continue;
/*      */       } 
/* 3953 */       K castKey = (K)key;
/* 3954 */       result.put(castKey, value);
/* 3955 */       hits++;
/*      */     } 
/* 3958 */     this.globalStatsCounter.recordHits(hits);
/* 3959 */     this.globalStatsCounter.recordMisses(misses);
/* 3960 */     return ImmutableMap.copyOf(result);
/*      */   }
/*      */   
/*      */   ImmutableMap<K, V> getAll(Iterable<? extends K> keys) throws ExecutionException {
/* 3964 */     int hits = 0;
/* 3965 */     int misses = 0;
/* 3967 */     Map<K, V> result = Maps.newLinkedHashMap();
/* 3968 */     Set<K> keysToLoad = Sets.newLinkedHashSet();
/* 3969 */     for (K key : keys) {
/* 3970 */       V value = get(key);
/* 3971 */       if (!result.containsKey(key)) {
/* 3972 */         result.put(key, value);
/* 3973 */         if (value == null) {
/* 3974 */           misses++;
/* 3975 */           keysToLoad.add(key);
/*      */           continue;
/*      */         } 
/* 3977 */         hits++;
/*      */       } 
/*      */     } 
/*      */     try {
/* 3983 */       if (!keysToLoad.isEmpty())
/*      */         try {
/* 3985 */           Map<K, V> newEntries = loadAll(keysToLoad, this.defaultLoader);
/* 3986 */           for (K key : keysToLoad) {
/* 3987 */             V value = newEntries.get(key);
/* 3988 */             if (value == null)
/* 3989 */               throw new CacheLoader.InvalidCacheLoadException("loadAll failed to return a value for " + key); 
/* 3991 */             result.put(key, value);
/*      */           } 
/* 3993 */         } catch (UnsupportedLoadingOperationException e) {
/* 3995 */           for (K key : keysToLoad) {
/* 3996 */             misses--;
/* 3997 */             result.put(key, get(key, this.defaultLoader));
/*      */           } 
/*      */         }  
/* 4001 */       return ImmutableMap.copyOf(result);
/*      */     } finally {
/* 4003 */       this.globalStatsCounter.recordHits(hits);
/* 4004 */       this.globalStatsCounter.recordMisses(misses);
/*      */     } 
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   Map<K, V> loadAll(Set<? extends K> keys, CacheLoader<? super K, V> loader) throws ExecutionException {
/*      */     Map<K, V> result;
/* 4015 */     Preconditions.checkNotNull(loader);
/* 4016 */     Preconditions.checkNotNull(keys);
/* 4017 */     Stopwatch stopwatch = Stopwatch.createStarted();
/* 4019 */     boolean success = false;
/*      */     try {
/* 4022 */       Map<K, V> map = (Map)loader.loadAll(keys);
/* 4023 */       result = map;
/* 4024 */       success = true;
/* 4025 */     } catch (UnsupportedLoadingOperationException e) {
/* 4026 */       success = true;
/* 4027 */       throw e;
/* 4028 */     } catch (InterruptedException e) {
/* 4029 */       Thread.currentThread().interrupt();
/* 4030 */       throw new ExecutionException(e);
/* 4031 */     } catch (RuntimeException e) {
/* 4032 */       throw new UncheckedExecutionException(e);
/* 4033 */     } catch (Exception e) {
/* 4034 */       throw new ExecutionException(e);
/* 4035 */     } catch (Error e) {
/* 4036 */       throw new ExecutionError(e);
/*      */     } finally {
/* 4038 */       if (!success)
/* 4039 */         this.globalStatsCounter.recordLoadException(stopwatch.elapsed(TimeUnit.NANOSECONDS)); 
/*      */     } 
/* 4043 */     if (result == null) {
/* 4044 */       this.globalStatsCounter.recordLoadException(stopwatch.elapsed(TimeUnit.NANOSECONDS));
/* 4045 */       throw new CacheLoader.InvalidCacheLoadException(loader + " returned null map from loadAll");
/*      */     } 
/* 4048 */     stopwatch.stop();
/* 4050 */     boolean nullsPresent = false;
/* 4051 */     for (Map.Entry<K, V> entry : result.entrySet()) {
/* 4052 */       K key = entry.getKey();
/* 4053 */       V value = entry.getValue();
/* 4054 */       if (key == null || value == null) {
/* 4056 */         nullsPresent = true;
/*      */         continue;
/*      */       } 
/* 4058 */       put(key, value);
/*      */     } 
/* 4062 */     if (nullsPresent) {
/* 4063 */       this.globalStatsCounter.recordLoadException(stopwatch.elapsed(TimeUnit.NANOSECONDS));
/* 4064 */       throw new CacheLoader.InvalidCacheLoadException(loader + " returned null keys or values from loadAll");
/*      */     } 
/* 4068 */     this.globalStatsCounter.recordLoadSuccess(stopwatch.elapsed(TimeUnit.NANOSECONDS));
/* 4069 */     return result;
/*      */   }
/*      */   
/*      */   ReferenceEntry<K, V> getEntry(@Nullable Object key) {
/* 4078 */     if (key == null)
/* 4079 */       return null; 
/* 4081 */     int hash = hash(key);
/* 4082 */     return segmentFor(hash).getEntry(key, hash);
/*      */   }
/*      */   
/*      */   void refresh(K key) {
/* 4086 */     int hash = hash(Preconditions.checkNotNull(key));
/* 4087 */     segmentFor(hash).refresh(key, hash, this.defaultLoader, false);
/*      */   }
/*      */   
/*      */   public boolean containsKey(@Nullable Object key) {
/* 4093 */     if (key == null)
/* 4094 */       return false; 
/* 4096 */     int hash = hash(key);
/* 4097 */     return segmentFor(hash).containsKey(key, hash);
/*      */   }
/*      */   
/*      */   public boolean containsValue(@Nullable Object value) {
/* 4103 */     if (value == null)
/* 4104 */       return false; 
/* 4112 */     long now = this.ticker.read();
/* 4113 */     Segment<K, V>[] segments = this.segments;
/* 4114 */     long last = -1L;
/* 4115 */     for (int i = 0; i < 3; i++) {
/* 4116 */       long sum = 0L;
/* 4117 */       for (Segment<K, V> segment : segments) {
/* 4120 */         int c = segment.count;
/* 4122 */         AtomicReferenceArray<ReferenceEntry<K, V>> table = segment.table;
/* 4123 */         for (int j = 0; j < table.length(); j++) {
/* 4124 */           for (ReferenceEntry<K, V> e = table.get(j); e != null; e = e.getNext()) {
/* 4125 */             V v = segment.getLiveValue(e, now);
/* 4126 */             if (v != null && this.valueEquivalence.equivalent(value, v))
/* 4127 */               return true; 
/*      */           } 
/*      */         } 
/* 4131 */         sum += segment.modCount;
/*      */       } 
/* 4133 */       if (sum == last)
/*      */         break; 
/* 4136 */       last = sum;
/*      */     } 
/* 4138 */     return false;
/*      */   }
/*      */   
/*      */   public V put(K key, V value) {
/* 4143 */     Preconditions.checkNotNull(key);
/* 4144 */     Preconditions.checkNotNull(value);
/* 4145 */     int hash = hash(key);
/* 4146 */     return segmentFor(hash).put(key, hash, value, false);
/*      */   }
/*      */   
/*      */   public V putIfAbsent(K key, V value) {
/* 4151 */     Preconditions.checkNotNull(key);
/* 4152 */     Preconditions.checkNotNull(value);
/* 4153 */     int hash = hash(key);
/* 4154 */     return segmentFor(hash).put(key, hash, value, true);
/*      */   }
/*      */   
/*      */   public void putAll(Map<? extends K, ? extends V> m) {
/* 4159 */     for (Map.Entry<? extends K, ? extends V> e : m.entrySet())
/* 4160 */       put(e.getKey(), e.getValue()); 
/*      */   }
/*      */   
/*      */   public V remove(@Nullable Object key) {
/* 4166 */     if (key == null)
/* 4167 */       return null; 
/* 4169 */     int hash = hash(key);
/* 4170 */     return segmentFor(hash).remove(key, hash);
/*      */   }
/*      */   
/*      */   public boolean remove(@Nullable Object key, @Nullable Object value) {
/* 4175 */     if (key == null || value == null)
/* 4176 */       return false; 
/* 4178 */     int hash = hash(key);
/* 4179 */     return segmentFor(hash).remove(key, hash, value);
/*      */   }
/*      */   
/*      */   public boolean replace(K key, @Nullable V oldValue, V newValue) {
/* 4184 */     Preconditions.checkNotNull(key);
/* 4185 */     Preconditions.checkNotNull(newValue);
/* 4186 */     if (oldValue == null)
/* 4187 */       return false; 
/* 4189 */     int hash = hash(key);
/* 4190 */     return segmentFor(hash).replace(key, hash, oldValue, newValue);
/*      */   }
/*      */   
/*      */   public V replace(K key, V value) {
/* 4195 */     Preconditions.checkNotNull(key);
/* 4196 */     Preconditions.checkNotNull(value);
/* 4197 */     int hash = hash(key);
/* 4198 */     return segmentFor(hash).replace(key, hash, value);
/*      */   }
/*      */   
/*      */   public void clear() {
/* 4203 */     for (Segment<K, V> segment : this.segments)
/* 4204 */       segment.clear(); 
/*      */   }
/*      */   
/*      */   void invalidateAll(Iterable<?> keys) {
/* 4210 */     for (Object key : keys)
/* 4211 */       remove(key); 
/*      */   }
/*      */   
/*      */   public Set<K> keySet() {
/* 4220 */     Set<K> ks = this.keySet;
/* 4221 */     return (ks != null) ? ks : (this.keySet = new KeySet(this));
/*      */   }
/*      */   
/*      */   public Collection<V> values() {
/* 4229 */     Collection<V> vs = this.values;
/* 4230 */     return (vs != null) ? vs : (this.values = new Values(this));
/*      */   }
/*      */   
/*      */   @GwtIncompatible("Not supported.")
/*      */   public Set<Map.Entry<K, V>> entrySet() {
/* 4239 */     Set<Map.Entry<K, V>> es = this.entrySet;
/* 4240 */     return (es != null) ? es : (this.entrySet = new EntrySet(this));
/*      */   }
/*      */   
/*      */   abstract class HashIterator<T> implements Iterator<T> {
/*      */     int nextSegmentIndex;
/*      */     
/*      */     int nextTableIndex;
/*      */     
/*      */     LocalCache.Segment<K, V> currentSegment;
/*      */     
/*      */     AtomicReferenceArray<LocalCache.ReferenceEntry<K, V>> currentTable;
/*      */     
/*      */     LocalCache.ReferenceEntry<K, V> nextEntry;
/*      */     
/*      */     LocalCache<K, V>.WriteThroughEntry nextExternal;
/*      */     
/*      */     LocalCache<K, V>.WriteThroughEntry lastReturned;
/*      */     
/*      */     HashIterator() {
/* 4256 */       this.nextSegmentIndex = LocalCache.this.segments.length - 1;
/* 4257 */       this.nextTableIndex = -1;
/* 4258 */       advance();
/*      */     }
/*      */     
/*      */     public abstract T next();
/*      */     
/*      */     final void advance() {
/* 4265 */       this.nextExternal = null;
/* 4267 */       if (nextInChain())
/*      */         return; 
/* 4271 */       if (nextInTable())
/*      */         return; 
/* 4275 */       while (this.nextSegmentIndex >= 0) {
/* 4276 */         this.currentSegment = LocalCache.this.segments[this.nextSegmentIndex--];
/* 4277 */         if (this.currentSegment.count != 0) {
/* 4278 */           this.currentTable = this.currentSegment.table;
/* 4279 */           this.nextTableIndex = this.currentTable.length() - 1;
/* 4280 */           if (nextInTable())
/*      */             return; 
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     boolean nextInChain() {
/* 4291 */       if (this.nextEntry != null)
/* 4292 */         for (this.nextEntry = this.nextEntry.getNext(); this.nextEntry != null; this.nextEntry = this.nextEntry.getNext()) {
/* 4293 */           if (advanceTo(this.nextEntry))
/* 4294 */             return true; 
/*      */         }  
/* 4298 */       return false;
/*      */     }
/*      */     
/*      */     boolean nextInTable() {
/* 4305 */       while (this.nextTableIndex >= 0) {
/* 4306 */         if ((this.nextEntry = this.currentTable.get(this.nextTableIndex--)) != null && (
/* 4307 */           advanceTo(this.nextEntry) || nextInChain()))
/* 4308 */           return true; 
/*      */       } 
/* 4312 */       return false;
/*      */     }
/*      */     
/*      */     boolean advanceTo(LocalCache.ReferenceEntry<K, V> entry) {
/*      */       try {
/* 4321 */         long now = LocalCache.this.ticker.read();
/* 4322 */         K key = entry.getKey();
/* 4323 */         V value = LocalCache.this.getLiveValue(entry, now);
/* 4324 */         if (value != null) {
/* 4325 */           this.nextExternal = new LocalCache.WriteThroughEntry(key, value);
/* 4326 */           return true;
/*      */         } 
/* 4329 */         return false;
/*      */       } finally {
/* 4332 */         this.currentSegment.postReadCleanup();
/*      */       } 
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/* 4338 */       return (this.nextExternal != null);
/*      */     }
/*      */     
/*      */     LocalCache<K, V>.WriteThroughEntry nextEntry() {
/* 4342 */       if (this.nextExternal == null)
/* 4343 */         throw new NoSuchElementException(); 
/* 4345 */       this.lastReturned = this.nextExternal;
/* 4346 */       advance();
/* 4347 */       return this.lastReturned;
/*      */     }
/*      */     
/*      */     public void remove() {
/* 4352 */       Preconditions.checkState((this.lastReturned != null));
/* 4353 */       LocalCache.this.remove(this.lastReturned.getKey());
/* 4354 */       this.lastReturned = null;
/*      */     }
/*      */   }
/*      */   
/*      */   final class KeyIterator extends HashIterator<K> {
/*      */     public K next() {
/* 4362 */       return nextEntry().getKey();
/*      */     }
/*      */   }
/*      */   
/*      */   final class ValueIterator extends HashIterator<V> {
/*      */     public V next() {
/* 4370 */       return nextEntry().getValue();
/*      */     }
/*      */   }
/*      */   
/*      */   final class WriteThroughEntry implements Map.Entry<K, V> {
/*      */     final K key;
/*      */     
/*      */     V value;
/*      */     
/*      */     WriteThroughEntry(K key, V value) {
/* 4383 */       this.key = key;
/* 4384 */       this.value = value;
/*      */     }
/*      */     
/*      */     public K getKey() {
/* 4389 */       return this.key;
/*      */     }
/*      */     
/*      */     public V getValue() {
/* 4394 */       return this.value;
/*      */     }
/*      */     
/*      */     public boolean equals(@Nullable Object object) {
/* 4400 */       if (object instanceof Map.Entry) {
/* 4401 */         Map.Entry<?, ?> that = (Map.Entry<?, ?>)object;
/* 4402 */         return (this.key.equals(that.getKey()) && this.value.equals(that.getValue()));
/*      */       } 
/* 4404 */       return false;
/*      */     }
/*      */     
/*      */     public int hashCode() {
/* 4410 */       return this.key.hashCode() ^ this.value.hashCode();
/*      */     }
/*      */     
/*      */     public V setValue(V newValue) {
/* 4415 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public String toString() {
/* 4422 */       return (new StringBuilder()).append(getKey()).append("=").append(getValue()).toString();
/*      */     }
/*      */   }
/*      */   
/*      */   final class EntryIterator extends HashIterator<Map.Entry<K, V>> {
/*      */     public Map.Entry<K, V> next() {
/* 4430 */       return nextEntry();
/*      */     }
/*      */   }
/*      */   
/*      */   abstract class AbstractCacheSet<T> extends AbstractSet<T> {
/*      */     final ConcurrentMap<?, ?> map;
/*      */     
/*      */     AbstractCacheSet(ConcurrentMap<?, ?> map) {
/* 4438 */       this.map = map;
/*      */     }
/*      */     
/*      */     public int size() {
/* 4443 */       return this.map.size();
/*      */     }
/*      */     
/*      */     public boolean isEmpty() {
/* 4448 */       return this.map.isEmpty();
/*      */     }
/*      */     
/*      */     public void clear() {
/* 4453 */       this.map.clear();
/*      */     }
/*      */   }
/*      */   
/*      */   final class KeySet extends AbstractCacheSet<K> {
/*      */     KeySet(ConcurrentMap<?, ?> map) {
/* 4460 */       super(map);
/*      */     }
/*      */     
/*      */     public Iterator<K> iterator() {
/* 4465 */       return new LocalCache.KeyIterator();
/*      */     }
/*      */     
/*      */     public boolean contains(Object o) {
/* 4470 */       return this.map.containsKey(o);
/*      */     }
/*      */     
/*      */     public boolean remove(Object o) {
/* 4475 */       return (this.map.remove(o) != null);
/*      */     }
/*      */   }
/*      */   
/*      */   final class Values extends AbstractCollection<V> {
/*      */     private final ConcurrentMap<?, ?> map;
/*      */     
/*      */     Values(ConcurrentMap<?, ?> map) {
/* 4483 */       this.map = map;
/*      */     }
/*      */     
/*      */     public int size() {
/* 4487 */       return this.map.size();
/*      */     }
/*      */     
/*      */     public boolean isEmpty() {
/* 4491 */       return this.map.isEmpty();
/*      */     }
/*      */     
/*      */     public void clear() {
/* 4495 */       this.map.clear();
/*      */     }
/*      */     
/*      */     public Iterator<V> iterator() {
/* 4500 */       return new LocalCache.ValueIterator();
/*      */     }
/*      */     
/*      */     public boolean contains(Object o) {
/* 4505 */       return this.map.containsValue(o);
/*      */     }
/*      */   }
/*      */   
/*      */   final class EntrySet extends AbstractCacheSet<Map.Entry<K, V>> {
/*      */     EntrySet(ConcurrentMap<?, ?> map) {
/* 4512 */       super(map);
/*      */     }
/*      */     
/*      */     public Iterator<Map.Entry<K, V>> iterator() {
/* 4517 */       return new LocalCache.EntryIterator();
/*      */     }
/*      */     
/*      */     public boolean contains(Object o) {
/* 4522 */       if (!(o instanceof Map.Entry))
/* 4523 */         return false; 
/* 4525 */       Map.Entry<?, ?> e = (Map.Entry<?, ?>)o;
/* 4526 */       Object key = e.getKey();
/* 4527 */       if (key == null)
/* 4528 */         return false; 
/* 4530 */       V v = (V)LocalCache.this.get(key);
/* 4532 */       return (v != null && LocalCache.this.valueEquivalence.equivalent(e.getValue(), v));
/*      */     }
/*      */     
/*      */     public boolean remove(Object o) {
/* 4537 */       if (!(o instanceof Map.Entry))
/* 4538 */         return false; 
/* 4540 */       Map.Entry<?, ?> e = (Map.Entry<?, ?>)o;
/* 4541 */       Object key = e.getKey();
/* 4542 */       return (key != null && LocalCache.this.remove(key, e.getValue()));
/*      */     }
/*      */   }
/*      */   
/*      */   static class ManualSerializationProxy<K, V> extends ForwardingCache<K, V> implements Serializable {
/*      */     private static final long serialVersionUID = 1L;
/*      */     
/*      */     final LocalCache.Strength keyStrength;
/*      */     
/*      */     final LocalCache.Strength valueStrength;
/*      */     
/*      */     final Equivalence<Object> keyEquivalence;
/*      */     
/*      */     final Equivalence<Object> valueEquivalence;
/*      */     
/*      */     final long expireAfterWriteNanos;
/*      */     
/*      */     final long expireAfterAccessNanos;
/*      */     
/*      */     final long maxWeight;
/*      */     
/*      */     final Weigher<K, V> weigher;
/*      */     
/*      */     final int concurrencyLevel;
/*      */     
/*      */     final RemovalListener<? super K, ? super V> removalListener;
/*      */     
/*      */     final Ticker ticker;
/*      */     
/*      */     final CacheLoader<? super K, V> loader;
/*      */     
/*      */     transient Cache<K, V> delegate;
/*      */     
/*      */     ManualSerializationProxy(LocalCache<K, V> cache) {
/* 4576 */       this(cache.keyStrength, cache.valueStrength, cache.keyEquivalence, cache.valueEquivalence, cache.expireAfterWriteNanos, cache.expireAfterAccessNanos, cache.maxWeight, cache.weigher, cache.concurrencyLevel, cache.removalListener, cache.ticker, cache.defaultLoader);
/*      */     }
/*      */     
/*      */     private ManualSerializationProxy(LocalCache.Strength keyStrength, LocalCache.Strength valueStrength, Equivalence<Object> keyEquivalence, Equivalence<Object> valueEquivalence, long expireAfterWriteNanos, long expireAfterAccessNanos, long maxWeight, Weigher<K, V> weigher, int concurrencyLevel, RemovalListener<? super K, ? super V> removalListener, Ticker ticker, CacheLoader<? super K, V> loader) {
/* 4598 */       this.keyStrength = keyStrength;
/* 4599 */       this.valueStrength = valueStrength;
/* 4600 */       this.keyEquivalence = keyEquivalence;
/* 4601 */       this.valueEquivalence = valueEquivalence;
/* 4602 */       this.expireAfterWriteNanos = expireAfterWriteNanos;
/* 4603 */       this.expireAfterAccessNanos = expireAfterAccessNanos;
/* 4604 */       this.maxWeight = maxWeight;
/* 4605 */       this.weigher = weigher;
/* 4606 */       this.concurrencyLevel = concurrencyLevel;
/* 4607 */       this.removalListener = removalListener;
/* 4608 */       this.ticker = (ticker == Ticker.systemTicker() || ticker == CacheBuilder.NULL_TICKER) ? null : ticker;
/* 4610 */       this.loader = loader;
/*      */     }
/*      */     
/*      */     CacheBuilder<K, V> recreateCacheBuilder() {
/* 4614 */       CacheBuilder<K, V> builder = CacheBuilder.newBuilder().setKeyStrength(this.keyStrength).setValueStrength(this.valueStrength).keyEquivalence(this.keyEquivalence).valueEquivalence(this.valueEquivalence).concurrencyLevel(this.concurrencyLevel).removalListener(this.removalListener);
/* 4621 */       builder.strictParsing = false;
/* 4622 */       if (this.expireAfterWriteNanos > 0L)
/* 4623 */         builder.expireAfterWrite(this.expireAfterWriteNanos, TimeUnit.NANOSECONDS); 
/* 4625 */       if (this.expireAfterAccessNanos > 0L)
/* 4626 */         builder.expireAfterAccess(this.expireAfterAccessNanos, TimeUnit.NANOSECONDS); 
/* 4628 */       if (this.weigher != CacheBuilder.OneWeigher.INSTANCE) {
/* 4629 */         builder.weigher(this.weigher);
/* 4630 */         if (this.maxWeight != -1L)
/* 4631 */           builder.maximumWeight(this.maxWeight); 
/* 4634 */       } else if (this.maxWeight != -1L) {
/* 4635 */         builder.maximumSize(this.maxWeight);
/*      */       } 
/* 4638 */       if (this.ticker != null)
/* 4639 */         builder.ticker(this.ticker); 
/* 4641 */       return builder;
/*      */     }
/*      */     
/*      */     private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 4645 */       in.defaultReadObject();
/* 4646 */       CacheBuilder<K, V> builder = recreateCacheBuilder();
/* 4647 */       this.delegate = builder.build();
/*      */     }
/*      */     
/*      */     private Object readResolve() {
/* 4651 */       return this.delegate;
/*      */     }
/*      */     
/*      */     protected Cache<K, V> delegate() {
/* 4656 */       return this.delegate;
/*      */     }
/*      */   }
/*      */   
/*      */   static final class LoadingSerializationProxy<K, V> extends ManualSerializationProxy<K, V> implements LoadingCache<K, V>, Serializable {
/*      */     private static final long serialVersionUID = 1L;
/*      */     
/*      */     transient LoadingCache<K, V> autoDelegate;
/*      */     
/*      */     LoadingSerializationProxy(LocalCache<K, V> cache) {
/* 4675 */       super(cache);
/*      */     }
/*      */     
/*      */     private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 4679 */       in.defaultReadObject();
/* 4680 */       CacheBuilder<K, V> builder = recreateCacheBuilder();
/* 4681 */       this.autoDelegate = builder.build(this.loader);
/*      */     }
/*      */     
/*      */     public V get(K key) throws ExecutionException {
/* 4686 */       return this.autoDelegate.get(key);
/*      */     }
/*      */     
/*      */     public V getUnchecked(K key) {
/* 4691 */       return this.autoDelegate.getUnchecked(key);
/*      */     }
/*      */     
/*      */     public ImmutableMap<K, V> getAll(Iterable<? extends K> keys) throws ExecutionException {
/* 4696 */       return this.autoDelegate.getAll(keys);
/*      */     }
/*      */     
/*      */     public final V apply(K key) {
/* 4701 */       return this.autoDelegate.apply(key);
/*      */     }
/*      */     
/*      */     public void refresh(K key) {
/* 4706 */       this.autoDelegate.refresh(key);
/*      */     }
/*      */     
/*      */     private Object readResolve() {
/* 4710 */       return this.autoDelegate;
/*      */     }
/*      */   }
/*      */   
/*      */   static class LocalManualCache<K, V> implements Cache<K, V>, Serializable {
/*      */     final LocalCache<K, V> localCache;
/*      */     
/*      */     private static final long serialVersionUID = 1L;
/*      */     
/*      */     LocalManualCache(CacheBuilder<? super K, ? super V> builder) {
/* 4718 */       this(new LocalCache<K, V>(builder, null));
/*      */     }
/*      */     
/*      */     private LocalManualCache(LocalCache<K, V> localCache) {
/* 4722 */       this.localCache = localCache;
/*      */     }
/*      */     
/*      */     @Nullable
/*      */     public V getIfPresent(Object key) {
/* 4730 */       return this.localCache.getIfPresent(key);
/*      */     }
/*      */     
/*      */     public V get(K key, final Callable<? extends V> valueLoader) throws ExecutionException {
/* 4735 */       Preconditions.checkNotNull(valueLoader);
/* 4736 */       return this.localCache.get(key, (CacheLoader)new CacheLoader<Object, V>() {
/*      */             public V load(Object key) throws Exception {
/* 4739 */               return valueLoader.call();
/*      */             }
/*      */           });
/*      */     }
/*      */     
/*      */     public ImmutableMap<K, V> getAllPresent(Iterable<?> keys) {
/* 4746 */       return this.localCache.getAllPresent(keys);
/*      */     }
/*      */     
/*      */     public void put(K key, V value) {
/* 4751 */       this.localCache.put(key, value);
/*      */     }
/*      */     
/*      */     public void putAll(Map<? extends K, ? extends V> m) {
/* 4756 */       this.localCache.putAll(m);
/*      */     }
/*      */     
/*      */     public void invalidate(Object key) {
/* 4761 */       Preconditions.checkNotNull(key);
/* 4762 */       this.localCache.remove(key);
/*      */     }
/*      */     
/*      */     public void invalidateAll(Iterable<?> keys) {
/* 4767 */       this.localCache.invalidateAll(keys);
/*      */     }
/*      */     
/*      */     public void invalidateAll() {
/* 4772 */       this.localCache.clear();
/*      */     }
/*      */     
/*      */     public long size() {
/* 4777 */       return this.localCache.longSize();
/*      */     }
/*      */     
/*      */     public ConcurrentMap<K, V> asMap() {
/* 4782 */       return this.localCache;
/*      */     }
/*      */     
/*      */     public CacheStats stats() {
/* 4787 */       AbstractCache.SimpleStatsCounter aggregator = new AbstractCache.SimpleStatsCounter();
/* 4788 */       aggregator.incrementBy(this.localCache.globalStatsCounter);
/* 4789 */       for (LocalCache.Segment<K, V> segment : this.localCache.segments)
/* 4790 */         aggregator.incrementBy(segment.statsCounter); 
/* 4792 */       return aggregator.snapshot();
/*      */     }
/*      */     
/*      */     public void cleanUp() {
/* 4797 */       this.localCache.cleanUp();
/*      */     }
/*      */     
/*      */     Object writeReplace() {
/* 4805 */       return new LocalCache.ManualSerializationProxy<K, V>(this.localCache);
/*      */     }
/*      */   }
/*      */   
/*      */   static class LocalLoadingCache<K, V> extends LocalManualCache<K, V> implements LoadingCache<K, V> {
/*      */     private static final long serialVersionUID = 1L;
/*      */     
/*      */     LocalLoadingCache(CacheBuilder<? super K, ? super V> builder, CacheLoader<? super K, V> loader) {
/* 4814 */       super(new LocalCache<K, V>(builder, (CacheLoader<? super K, V>)Preconditions.checkNotNull(loader)));
/*      */     }
/*      */     
/*      */     public V get(K key) throws ExecutionException {
/* 4821 */       return this.localCache.getOrLoad(key);
/*      */     }
/*      */     
/*      */     public V getUnchecked(K key) {
/*      */       try {
/* 4827 */         return get(key);
/* 4828 */       } catch (ExecutionException e) {
/* 4829 */         throw new UncheckedExecutionException(e.getCause());
/*      */       } 
/*      */     }
/*      */     
/*      */     public ImmutableMap<K, V> getAll(Iterable<? extends K> keys) throws ExecutionException {
/* 4835 */       return this.localCache.getAll(keys);
/*      */     }
/*      */     
/*      */     public void refresh(K key) {
/* 4840 */       this.localCache.refresh(key);
/*      */     }
/*      */     
/*      */     public final V apply(K key) {
/* 4845 */       return getUnchecked(key);
/*      */     }
/*      */     
/*      */     Object writeReplace() {
/* 4854 */       return new LocalCache.LoadingSerializationProxy<K, V>(this.localCache);
/*      */     }
/*      */   }
/*      */   
/*      */   static interface ReferenceEntry<K, V> {
/*      */     LocalCache.ValueReference<K, V> getValueReference();
/*      */     
/*      */     void setValueReference(LocalCache.ValueReference<K, V> param1ValueReference);
/*      */     
/*      */     @Nullable
/*      */     ReferenceEntry<K, V> getNext();
/*      */     
/*      */     int getHash();
/*      */     
/*      */     @Nullable
/*      */     K getKey();
/*      */     
/*      */     long getAccessTime();
/*      */     
/*      */     void setAccessTime(long param1Long);
/*      */     
/*      */     ReferenceEntry<K, V> getNextInAccessQueue();
/*      */     
/*      */     void setNextInAccessQueue(ReferenceEntry<K, V> param1ReferenceEntry);
/*      */     
/*      */     ReferenceEntry<K, V> getPreviousInAccessQueue();
/*      */     
/*      */     void setPreviousInAccessQueue(ReferenceEntry<K, V> param1ReferenceEntry);
/*      */     
/*      */     long getWriteTime();
/*      */     
/*      */     void setWriteTime(long param1Long);
/*      */     
/*      */     ReferenceEntry<K, V> getNextInWriteQueue();
/*      */     
/*      */     void setNextInWriteQueue(ReferenceEntry<K, V> param1ReferenceEntry);
/*      */     
/*      */     ReferenceEntry<K, V> getPreviousInWriteQueue();
/*      */     
/*      */     void setPreviousInWriteQueue(ReferenceEntry<K, V> param1ReferenceEntry);
/*      */   }
/*      */   
/*      */   static interface ValueReference<K, V> {
/*      */     @Nullable
/*      */     V get();
/*      */     
/*      */     V waitForValue() throws ExecutionException;
/*      */     
/*      */     int getWeight();
/*      */     
/*      */     @Nullable
/*      */     LocalCache.ReferenceEntry<K, V> getEntry();
/*      */     
/*      */     ValueReference<K, V> copyFor(ReferenceQueue<V> param1ReferenceQueue, @Nullable V param1V, LocalCache.ReferenceEntry<K, V> param1ReferenceEntry);
/*      */     
/*      */     void notifyNewValue(@Nullable V param1V);
/*      */     
/*      */     boolean isLoading();
/*      */     
/*      */     boolean isActive();
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\common\cache\LocalCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */