/*      */ package org.mapdb;
/*      */ 
/*      */ import java.io.DataInput;
/*      */ import java.io.DataOutput;
/*      */ import java.io.IOException;
/*      */ import java.lang.ref.WeakReference;
/*      */ import java.util.AbstractCollection;
/*      */ import java.util.AbstractMap;
/*      */ import java.util.AbstractSet;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Set;
/*      */ import java.util.concurrent.ConcurrentMap;
/*      */ import java.util.concurrent.CountDownLatch;
/*      */ import java.util.concurrent.locks.Lock;
/*      */ import java.util.concurrent.locks.ReentrantReadWriteLock;
/*      */ 
/*      */ public class HTreeMap<K, V> extends AbstractMap<K, V> implements ConcurrentMap<K, V>, Bind.MapWithModificationListener<K, V> {
/*      */   protected static final int BUCKET_OVERFLOW = 4;
/*      */   
/*      */   protected static final int DIV8 = 3;
/*      */   
/*      */   protected static final int MOD8 = 7;
/*      */   
/*      */   protected final boolean hasValues;
/*      */   
/*      */   protected final int hashSalt;
/*      */   
/*      */   protected final Atomic.Long counter;
/*      */   
/*      */   protected final Serializer<K> keySerializer;
/*      */   
/*      */   protected final Serializer<V> valueSerializer;
/*      */   
/*      */   protected final Hasher<K> hasher;
/*      */   
/*      */   protected final Engine engine;
/*      */   
/*      */   protected final boolean expireFlag;
/*      */   
/*      */   protected final long expireTimeStart;
/*      */   
/*      */   protected final long expire;
/*      */   
/*      */   protected final boolean expireAccessFlag;
/*      */   
/*      */   protected final long expireAccess;
/*      */   
/*      */   protected final long expireMaxSize;
/*      */   
/*      */   protected final long expireStoreSize;
/*      */   
/*      */   protected final boolean expireMaxSizeFlag;
/*      */   
/*      */   protected final long[] expireHeads;
/*      */   
/*      */   protected final long[] expireTails;
/*      */   
/*      */   protected final Fun.Function1<V, K> valueCreator;
/*      */   
/*   82 */   protected final CountDownLatch closeLatch = new CountDownLatch(2);
/*      */   
/*   84 */   protected final Runnable closeListener = new Runnable() {
/*      */       public void run() {
/*   86 */         if (HTreeMap.this.closeLatch.getCount() > 1L)
/*   87 */           HTreeMap.this.closeLatch.countDown(); 
/*      */         try {
/*   91 */           HTreeMap.this.closeLatch.await();
/*   92 */         } catch (InterruptedException e) {
/*   93 */           throw new RuntimeException(e);
/*      */         } 
/*   97 */         HTreeMap.this.engine.closeListenerUnregister(HTreeMap.this.closeListener);
/*      */       }
/*      */     };
/*      */   
/*      */   protected static final class LinkedNode<K, V> {
/*      */     public final long next;
/*      */     
/*      */     public final long expireLinkNodeRecid;
/*      */     
/*      */     public final K key;
/*      */     
/*      */     public final V value;
/*      */     
/*      */     public LinkedNode(long next, long expireLinkNodeRecid, K key, V value) {
/*  112 */       this.key = key;
/*  113 */       this.expireLinkNodeRecid = expireLinkNodeRecid;
/*  114 */       this.value = value;
/*  115 */       this.next = next;
/*      */     }
/*      */   }
/*      */   
/*  121 */   protected final Serializer<LinkedNode<K, V>> LN_SERIALIZER = new Serializer<LinkedNode<K, V>>() {
/*      */       public void serialize(DataOutput out, HTreeMap.LinkedNode<K, V> value) throws IOException {
/*  124 */         DataOutput2.packLong(out, value.next);
/*  125 */         if (HTreeMap.this.expireFlag)
/*  126 */           DataOutput2.packLong(out, value.expireLinkNodeRecid); 
/*  127 */         HTreeMap.this.keySerializer.serialize(out, value.key);
/*  128 */         if (HTreeMap.this.hasValues)
/*  129 */           HTreeMap.this.valueSerializer.serialize(out, value.value); 
/*      */       }
/*      */       
/*      */       public HTreeMap.LinkedNode<K, V> deserialize(DataInput in, int available) throws IOException {
/*  134 */         assert available != 0;
/*  135 */         return new HTreeMap.LinkedNode<K, V>(DataInput2.unpackLong(in), HTreeMap.this.expireFlag ? DataInput2.unpackLong(in) : 0L, HTreeMap.this.keySerializer.deserialize(in, -1), HTreeMap.this.hasValues ? HTreeMap.this.valueSerializer.deserialize(in, -1) : (V)BTreeMap.EMPTY);
/*      */       }
/*      */       
/*      */       public int fixedSize() {
/*  145 */         return -1;
/*      */       }
/*      */     };
/*      */   
/*  151 */   protected static final Serializer<long[][]> DIR_SERIALIZER = new Serializer<long[][]>() {
/*      */       public void serialize(DataOutput out, long[][] value) throws IOException {
/*  154 */         assert value.length == 16;
/*  157 */         int nulls = 0;
/*      */         int i;
/*  158 */         for (i = 0; i < 16; i++) {
/*  159 */           if (value[i] != null)
/*  160 */             for (long l : value[i]) {
/*  161 */               if (l != 0L) {
/*  162 */                 nulls |= 1 << i;
/*      */                 break;
/*      */               } 
/*      */             }  
/*      */         } 
/*  168 */         out.writeShort(nulls);
/*  171 */         for (i = 0; i < 16; i++) {
/*  172 */           if (value[i] != null) {
/*  173 */             assert (value[i]).length == 8;
/*  174 */             for (long l : value[i])
/*  175 */               DataOutput2.packLong(out, l); 
/*      */           } 
/*      */         } 
/*      */       }
/*      */       
/*      */       public long[][] deserialize(DataInput in, int available) throws IOException {
/*  185 */         long[][] ret = new long[16][];
/*  188 */         int nulls = in.readUnsignedShort();
/*  189 */         for (int i = 0; i < 16; i++) {
/*  190 */           if ((nulls & 0x1) != 0) {
/*  191 */             long[] subarray = new long[8];
/*  192 */             for (int j = 0; j < 8; j++)
/*  193 */               subarray[j] = DataInput2.unpackLong(in); 
/*  195 */             ret[i] = subarray;
/*      */           } 
/*  197 */           nulls >>>= 1;
/*      */         } 
/*  200 */         return ret;
/*      */       }
/*      */       
/*      */       public int fixedSize() {
/*  205 */         return -1;
/*      */       }
/*      */     };
/*      */   
/*      */   protected final long[] segmentRecids;
/*      */   
/*  213 */   protected final ReentrantReadWriteLock[] segmentLocks = new ReentrantReadWriteLock[16];
/*      */   
/*      */   private final Set<K> _keySet;
/*      */   
/*      */   private final Collection<V> _values;
/*      */   
/*      */   private final Set<Map.Entry<K, V>> _entrySet;
/*      */   
/*      */   protected final Object modListenersLock;
/*      */   
/*      */   protected Bind.MapListener<K, V>[] modListeners;
/*      */   
/*      */   public HTreeMap(Engine engine, long counterRecid, int hashSalt, long[] segmentRecids, Serializer<K> keySerializer, Serializer<V> valueSerializer, long expireTimeStart, long expire, long expireAccess, long expireMaxSize, long expireStoreSize, long[] expireHeads, long[] expireTails, Fun.Function1<V, K> valueCreator, Hasher<K> hasher, boolean disableLocks) {
/*  215 */     for (int i = 0; i < 16; ) {
/*  215 */       this.segmentLocks[i] = new ReentrantReadWriteLock(false);
/*  215 */       i++;
/*      */     } 
/*  862 */     this._keySet = new KeySet();
/*  869 */     this._values = new AbstractCollection<V>() {
/*      */         public int size() {
/*  873 */           return HTreeMap.this.size();
/*      */         }
/*      */         
/*      */         public boolean isEmpty() {
/*  878 */           return HTreeMap.this.isEmpty();
/*      */         }
/*      */         
/*      */         public boolean contains(Object o) {
/*  883 */           return HTreeMap.this.containsValue(o);
/*      */         }
/*      */         
/*      */         public Iterator<V> iterator() {
/*  890 */           return new HTreeMap.ValueIterator();
/*      */         }
/*      */       };
/*  900 */     this._entrySet = new AbstractSet<Map.Entry<K, V>>() {
/*      */         public int size() {
/*  904 */           return HTreeMap.this.size();
/*      */         }
/*      */         
/*      */         public boolean isEmpty() {
/*  909 */           return HTreeMap.this.isEmpty();
/*      */         }
/*      */         
/*      */         public boolean contains(Object o) {
/*  914 */           if (o instanceof Map.Entry) {
/*  915 */             Map.Entry e = (Map.Entry)o;
/*  916 */             Object val = HTreeMap.this.get(e.getKey());
/*  917 */             return (val != null && val.equals(e.getValue()));
/*      */           } 
/*  919 */           return false;
/*      */         }
/*      */         
/*      */         public Iterator<Map.Entry<K, V>> iterator() {
/*  924 */           return new HTreeMap.EntryIterator();
/*      */         }
/*      */         
/*      */         public boolean add(Map.Entry<K, V> kvEntry) {
/*  930 */           K key = kvEntry.getKey();
/*  931 */           V value = kvEntry.getValue();
/*  932 */           if (key == null || value == null)
/*  932 */             throw new NullPointerException(); 
/*  933 */           HTreeMap.this.put(key, value);
/*  934 */           return true;
/*      */         }
/*      */         
/*      */         public boolean remove(Object o) {
/*  939 */           if (o instanceof Map.Entry) {
/*  940 */             Map.Entry e = (Map.Entry)o;
/*  941 */             Object key = e.getKey();
/*  942 */             if (key == null)
/*  942 */               return false; 
/*  943 */             return HTreeMap.this.remove(key, e.getValue());
/*      */           } 
/*  945 */           return false;
/*      */         }
/*      */         
/*      */         public void clear() {
/*  951 */           HTreeMap.this.clear();
/*      */         }
/*      */       };
/* 1664 */     this.modListenersLock = new Object();
/* 1665 */     this.modListeners = (Bind.MapListener<K, V>[])new Bind.MapListener[0];
/*      */     if (counterRecid < 0L)
/*      */       throw new IllegalArgumentException(); 
/*      */     if (engine == null)
/*      */       throw new NullPointerException(); 
/*      */     if (segmentRecids == null)
/*      */       throw new NullPointerException(); 
/*      */     if (keySerializer == null)
/*      */       throw new NullPointerException(); 
/*      */     SerializerBase.assertSerializable(keySerializer);
/*      */     this.hasValues = (valueSerializer != null);
/*      */     if (this.hasValues)
/*      */       SerializerBase.assertSerializable(valueSerializer); 
/*      */     if (segmentRecids.length != 16)
/*      */       throw new IllegalArgumentException(); 
/*      */     this.engine = engine;
/*      */     this.hashSalt = hashSalt;
/*      */     this.segmentRecids = Arrays.copyOf(segmentRecids, 16);
/*      */     this.keySerializer = keySerializer;
/*      */     this.valueSerializer = valueSerializer;
/*      */     this.hasher = (hasher != null) ? hasher : Hasher.BASIC;
/*      */     if (expire == 0L && expireAccess != 0L)
/*      */       expire = expireAccess; 
/*      */     if (expireMaxSize != 0L && counterRecid == 0L)
/*      */       throw new IllegalArgumentException("expireMaxSize must have counter enabled"); 
/*      */     this.expireFlag = (expire != 0L || expireAccess != 0L || expireMaxSize != 0L || expireStoreSize != 0L);
/*      */     this.expire = expire;
/*      */     this.expireTimeStart = expireTimeStart;
/*      */     this.expireAccessFlag = (expireAccess != 0L || expireMaxSize != 0L || expireStoreSize != 0L);
/*      */     this.expireAccess = expireAccess;
/*      */     this.expireHeads = (expireHeads == null) ? null : Arrays.copyOf(expireHeads, 16);
/*      */     this.expireTails = (expireTails == null) ? null : Arrays.copyOf(expireTails, 16);
/*      */     this.expireMaxSizeFlag = (expireMaxSize != 0L);
/*      */     this.expireMaxSize = expireMaxSize;
/*      */     this.expireStoreSize = expireStoreSize;
/*      */     this.valueCreator = valueCreator;
/*      */     if (counterRecid != 0L) {
/*      */       this.counter = new Atomic.Long(engine, counterRecid);
/*      */       Bind.size(this, this.counter);
/*      */     } else {
/*      */       this.counter = null;
/*      */     } 
/*      */     if (this.expireFlag) {
/*      */       Thread t = new Thread(new ExpireRunnable(this), "HTreeMap expirator");
/*      */       t.setDaemon(true);
/*      */       t.start();
/*      */       engine.closeListenerRegister(this.closeListener);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected static long[] preallocateSegments(Engine engine) {
/*      */     long[] ret = new long[16];
/*      */     for (int i = 0; i < 16; i++)
/*      */       ret[i] = engine.put((A)new long[16][], (Serializer)DIR_SERIALIZER); 
/*      */     return ret;
/*      */   }
/*      */   
/*      */   public boolean containsKey(Object o) {
/*      */     return (getPeek(o) != null);
/*      */   }
/*      */   
/*      */   public int size() {
/*      */     long size = sizeLong();
/*      */     if (size > 2147483647L)
/*      */       return Integer.MAX_VALUE; 
/*      */     return (int)size;
/*      */   }
/*      */   
/*      */   public long sizeLong() {
/*      */     if (this.counter != null)
/*      */       return this.counter.get(); 
/*      */     long counter = 0L;
/*      */     for (int i = 0; i < 16; i++) {
/*      */       try {
/*      */         this.segmentLocks[i].readLock().lock();
/*      */         long dirRecid = this.segmentRecids[i];
/*      */         counter += recursiveDirCount(dirRecid);
/*      */         this.segmentLocks[i].readLock().unlock();
/*      */       } finally {
/*      */         this.segmentLocks[i].readLock().unlock();
/*      */       } 
/*      */     } 
/*      */     return counter;
/*      */   }
/*      */   
/*      */   private long recursiveDirCount(long dirRecid) {
/*      */     long[][] dir = this.engine.<long[][]>get(dirRecid, (Serializer)DIR_SERIALIZER);
/*      */     long counter = 0L;
/*      */     for (long[] subdir : dir) {
/*      */       if (subdir != null)
/*      */         for (long recid : subdir) {
/*      */           if (recid != 0L)
/*      */             if ((recid & 0x1L) == 0L) {
/*      */               recid >>>= 1L;
/*      */               counter += recursiveDirCount(recid);
/*      */             } else {
/*      */               recid >>>= 1L;
/*      */               while (recid != 0L) {
/*      */                 LinkedNode n = this.engine.<LinkedNode>get(recid, (Serializer)this.LN_SERIALIZER);
/*      */                 if (n != null) {
/*      */                   counter++;
/*      */                   recid = n.next;
/*      */                   continue;
/*      */                 } 
/*      */                 recid = 0L;
/*      */               } 
/*      */             }  
/*      */         }  
/*      */     } 
/*      */     return counter;
/*      */   }
/*      */   
/*      */   public boolean isEmpty() {
/*      */     for (int i = 0; i < 16; i++) {
/*      */       try {
/*      */         this.segmentLocks[i].readLock().lock();
/*      */         long dirRecid = this.segmentRecids[i];
/*      */         long[][] dir = this.engine.<long[][]>get(dirRecid, (Serializer)DIR_SERIALIZER);
/*      */         for (long[] d : dir) {
/*      */           if (d != null)
/*      */             return false; 
/*      */         } 
/*      */         this.segmentLocks[i].readLock().unlock();
/*      */       } finally {
/*      */         this.segmentLocks[i].readLock().unlock();
/*      */       } 
/*      */     } 
/*      */     return true;
/*      */   }
/*      */   
/*      */   public V get(Object o) {
/*      */     LinkedNode<K, V> ln;
/*      */     if (o == null)
/*      */       return null; 
/*      */     int h = hash(o);
/*      */     int segment = h >>> 28;
/*      */     Lock lock = this.expireAccessFlag ? this.segmentLocks[segment].writeLock() : this.segmentLocks[segment].readLock();
/*      */     lock.lock();
/*      */     try {
/*      */       ln = getInner(o, h, segment);
/*      */       if (ln != null && this.expireAccessFlag)
/*      */         expireLinkBump(segment, ln.expireLinkNodeRecid, true); 
/*      */     } finally {
/*      */       lock.unlock();
/*      */     } 
/*      */     if (this.valueCreator == null) {
/*      */       if (ln == null)
/*      */         return null; 
/*      */       return ln.value;
/*      */     } 
/*      */     V value = this.valueCreator.run((K)o);
/*      */     V prevVal = putIfAbsent((K)o, value);
/*      */     if (prevVal != null)
/*      */       return prevVal; 
/*      */     return value;
/*      */   }
/*      */   
/*      */   public V getPeek(Object key) {
/*      */     if (key == null)
/*      */       return null; 
/*      */     int h = hash(key);
/*      */     int segment = h >>> 28;
/*      */     Lock lock = this.segmentLocks[segment].readLock();
/*      */     lock.lock();
/*      */     try {
/*      */       LinkedNode<K, V> ln = getInner(key, h, segment);
/*      */       if (ln == null)
/*      */         return null; 
/*      */       return ln.value;
/*      */     } finally {
/*      */       lock.unlock();
/*      */     } 
/*      */   }
/*      */   
/*      */   protected LinkedNode<K, V> getInner(Object o, int h, int segment) {
/*      */     long recid = this.segmentRecids[segment];
/*      */     for (int level = 3; level >= 0; level--) {
/*      */       long[][] dir = this.engine.<long[][]>get(recid, (Serializer)DIR_SERIALIZER);
/*      */       if (dir == null)
/*      */         return null; 
/*      */       int slot = h >>> level * 7 & 0x7F;
/*      */       assert slot < 128;
/*      */       if (dir[slot >>> 3] == null)
/*      */         return null; 
/*      */       recid = dir[slot >>> 3][slot & 0x7];
/*      */       if (recid == 0L)
/*      */         return null; 
/*      */       if ((recid & 0x1L) != 0L) {
/*      */         recid >>>= 1L;
/*      */         while (true) {
/*      */           LinkedNode<K, V> ln = this.engine.<LinkedNode<K, V>>get(recid, this.LN_SERIALIZER);
/*      */           if (ln == null)
/*      */             return null; 
/*      */           if (this.hasher.equals(ln.key, (K)o)) {
/*      */             assert hash(ln.key) == h;
/*      */             return ln;
/*      */           } 
/*      */           if (ln.next == 0L)
/*      */             return null; 
/*      */           recid = ln.next;
/*      */         } 
/*      */       } 
/*      */       recid >>>= 1L;
/*      */     } 
/*      */     return null;
/*      */   }
/*      */   
/*      */   public V put(K key, V value) {
/*      */     if (key == null)
/*      */       throw new IllegalArgumentException("null key"); 
/*      */     if (value == null)
/*      */       throw new IllegalArgumentException("null value"); 
/*      */     int h = hash(key);
/*      */     int segment = h >>> 28;
/*      */     this.segmentLocks[segment].writeLock().lock();
/*      */     try {
/*      */       return putInner(key, value, h, segment);
/*      */     } finally {
/*      */       this.segmentLocks[segment].writeLock().unlock();
/*      */     } 
/*      */   }
/*      */   
/*      */   private V putInner(K key, V value, int h, int segment) {
/*      */     int slot, counter;
/*      */     long dirRecid = this.segmentRecids[segment];
/*      */     int level = 3;
/*      */     while (true) {
/*      */       dir = this.engine.<long[][]>get(dirRecid, (Serializer)DIR_SERIALIZER);
/*      */       slot = h >>> 7 * level & 0x7F;
/*      */       assert slot <= 127;
/*      */       if (dir == null)
/*      */         dir = new long[16][]; 
/*      */       if (dir[slot >>> 3] == null) {
/*      */         dir = Arrays.<long[]>copyOf(dir, 16);
/*      */         dir[slot >>> 3] = new long[8];
/*      */       } 
/*      */       counter = 0;
/*      */       long recid = dir[slot >>> 3][slot & 0x7];
/*      */       if (recid != 0L) {
/*      */         if ((recid & 0x1L) == 0L) {
/*      */           dirRecid = recid >>> 1L;
/*      */           level--;
/*      */           continue;
/*      */         } 
/*      */         recid >>>= 1L;
/*      */         LinkedNode<K, V> ln = this.engine.<LinkedNode<K, V>>get(recid, this.LN_SERIALIZER);
/*      */         while (ln != null) {
/*      */           if (this.hasher.equals(ln.key, key)) {
/*      */             V oldVal = ln.value;
/*      */             ln = new LinkedNode<K, V>(ln.next, ln.expireLinkNodeRecid, ln.key, value);
/*      */             this.engine.update(recid, ln, this.LN_SERIALIZER);
/*      */             if (this.expireFlag)
/*      */               expireLinkBump(segment, ln.expireLinkNodeRecid, false); 
/*      */             notify(key, oldVal, value);
/*      */             return oldVal;
/*      */           } 
/*      */           recid = ln.next;
/*      */           ln = (recid == 0L) ? null : this.engine.<LinkedNode<K, V>>get(recid, this.LN_SERIALIZER);
/*      */           counter++;
/*      */         } 
/*      */       } 
/*      */       break;
/*      */     } 
/*      */     if (counter >= 4 && level >= 1) {
/*      */       long[][] nextDir = new long[16][];
/*      */       long l2 = this.expireFlag ? this.engine.preallocate() : 0L;
/*      */       LinkedNode<K, V> node = new LinkedNode<K, V>(0L, l2, key, value);
/*      */       long l3 = this.engine.put(node, this.LN_SERIALIZER);
/*      */       int pos = h >>> 7 * (level - 1) & 0x7F;
/*      */       nextDir[pos >>> 3] = new long[8];
/*      */       nextDir[pos >>> 3][pos & 0x7] = l3 << 1L | 0x1L;
/*      */       if (this.expireFlag)
/*      */         expireLinkAdd(segment, l2, l3, h); 
/*      */       long nodeRecid = dir[slot >>> 3][slot & 0x7] >>> 1L;
/*      */       while (nodeRecid != 0L) {
/*      */         LinkedNode<K, V> n = this.engine.<LinkedNode<K, V>>get(nodeRecid, this.LN_SERIALIZER);
/*      */         long nextRecid = n.next;
/*      */         pos = hash(n.key) >>> 7 * (level - 1) & 0x7F;
/*      */         if (nextDir[pos >>> 3] == null)
/*      */           nextDir[pos >>> 3] = new long[8]; 
/*      */         n = new LinkedNode<K, V>(nextDir[pos >>> 3][pos & 0x7] >>> 1L, n.expireLinkNodeRecid, n.key, n.value);
/*      */         nextDir[pos >>> 3][pos & 0x7] = nodeRecid << 1L | 0x1L;
/*      */         this.engine.update(nodeRecid, n, this.LN_SERIALIZER);
/*      */         nodeRecid = nextRecid;
/*      */       } 
/*      */       long nextDirRecid = this.engine.put(nextDir, (Serializer)DIR_SERIALIZER);
/*      */       int parentPos = h >>> 7 * level & 0x7F;
/*      */       dir = Arrays.<long[]>copyOf(dir, 16);
/*      */       dir[parentPos >>> 3] = Arrays.copyOf(dir[parentPos >>> 3], 8);
/*      */       dir[parentPos >>> 3][parentPos & 0x7] = nextDirRecid << 1L | 0x0L;
/*      */       this.engine.update(dirRecid, dir, (Serializer)DIR_SERIALIZER);
/*      */       notify(key, null, value);
/*      */       return null;
/*      */     } 
/*      */     long l1 = dir[slot >>> 3][slot & 0x7] >>> 1L;
/*      */     long expireNodeRecid = this.expireFlag ? this.engine.<ExpireLinkNode>put(ExpireLinkNode.EMPTY, ExpireLinkNode.SERIALIZER) : 0L;
/*      */     long newRecid = this.engine.put(new LinkedNode<K, V>(l1, expireNodeRecid, key, value), this.LN_SERIALIZER);
/*      */     long[][] dir = Arrays.<long[]>copyOf(dir, 16);
/*      */     dir[slot >>> 3] = Arrays.copyOf(dir[slot >>> 3], 8);
/*      */     dir[slot >>> 3][slot & 0x7] = newRecid << 1L | 0x1L;
/*      */     this.engine.update(dirRecid, dir, (Serializer)DIR_SERIALIZER);
/*      */     if (this.expireFlag)
/*      */       expireLinkAdd(segment, expireNodeRecid, newRecid, h); 
/*      */     notify(key, null, value);
/*      */     return null;
/*      */   }
/*      */   
/*      */   public V remove(Object key) {
/*      */     int h = hash(key);
/*      */     int segment = h >>> 28;
/*      */     this.segmentLocks[segment].writeLock().lock();
/*      */     try {
/*      */       return removeInternal(key, segment, h, true);
/*      */     } finally {
/*      */       this.segmentLocks[segment].writeLock().unlock();
/*      */     } 
/*      */   }
/*      */   
/*      */   protected V removeInternal(Object key, int segment, int h, boolean removeExpire) {
/*      */     long[] dirRecids = new long[4];
/*      */     int level = 3;
/*      */     dirRecids[level] = this.segmentRecids[segment];
/*      */     assert segment == h >>> 28;
/*      */     while (true) {
/*      */       long[][] dir = this.engine.<long[][]>get(dirRecids[level], (Serializer)DIR_SERIALIZER);
/*      */       int slot = h >>> 7 * level & 0x7F;
/*      */       assert slot <= 127;
/*      */       if (dir == null)
/*      */         dir = new long[16][]; 
/*      */       if (dir[slot >>> 3] == null) {
/*      */         dir = Arrays.<long[]>copyOf(dir, 16);
/*      */         dir[slot >>> 3] = new long[8];
/*      */       } 
/*      */       long recid = dir[slot >>> 3][slot & 0x7];
/*      */       if (recid != 0L) {
/*      */         if ((recid & 0x1L) == 0L) {
/*      */           level--;
/*      */           dirRecids[level] = recid >>> 1L;
/*      */           continue;
/*      */         } 
/*      */         recid >>>= 1L;
/*      */         LinkedNode<K, V> ln = this.engine.<LinkedNode<K, V>>get(recid, this.LN_SERIALIZER);
/*      */         LinkedNode<K, V> prevLn = null;
/*      */         long prevRecid = 0L;
/*      */         while (ln != null) {
/*      */           if (this.hasher.equals(ln.key, (K)key)) {
/*      */             if (prevLn == null) {
/*      */               if (ln.next == 0L) {
/*      */                 recursiveDirDelete(h, level, dirRecids, dir, slot);
/*      */               } else {
/*      */                 dir = Arrays.<long[]>copyOf(dir, 16);
/*      */                 dir[slot >>> 3] = Arrays.copyOf(dir[slot >>> 3], 8);
/*      */                 dir[slot >>> 3][slot & 0x7] = ln.next << 1L | 0x1L;
/*      */                 this.engine.update(dirRecids[level], dir, (Serializer)DIR_SERIALIZER);
/*      */               } 
/*      */             } else {
/*      */               prevLn = new LinkedNode<K, V>(ln.next, prevLn.expireLinkNodeRecid, prevLn.key, prevLn.value);
/*      */               this.engine.update(prevRecid, prevLn, this.LN_SERIALIZER);
/*      */             } 
/*      */             assert hash(ln.key) == h;
/*      */             this.engine.delete(recid, this.LN_SERIALIZER);
/*      */             if (removeExpire && this.expireFlag)
/*      */               expireLinkRemove(segment, ln.expireLinkNodeRecid); 
/*      */             notify((K)key, ln.value, null);
/*      */             return ln.value;
/*      */           } 
/*      */           prevRecid = recid;
/*      */           prevLn = ln;
/*      */           recid = ln.next;
/*      */           ln = (recid == 0L) ? null : this.engine.<LinkedNode<K, V>>get(recid, this.LN_SERIALIZER);
/*      */         } 
/*      */         return null;
/*      */       } 
/*      */       break;
/*      */     } 
/*      */     return null;
/*      */   }
/*      */   
/*      */   private void recursiveDirDelete(int h, int level, long[] dirRecids, long[][] dir, int slot) {
/*      */     dir = Arrays.<long[]>copyOf(dir, 16);
/*      */     dir[slot >>> 3] = Arrays.copyOf(dir[slot >>> 3], 8);
/*      */     dir[slot >>> 3][slot & 0x7] = 0L;
/*      */     boolean allZero = true;
/*      */     for (long l : dir[slot >>> 3]) {
/*      */       if (l != 0L) {
/*      */         allZero = false;
/*      */         break;
/*      */       } 
/*      */     } 
/*      */     if (allZero)
/*      */       dir[slot >>> 3] = null; 
/*      */     allZero = true;
/*      */     for (long[] l : dir) {
/*      */       if (l != null) {
/*      */         allZero = false;
/*      */         break;
/*      */       } 
/*      */     } 
/*      */     if (allZero) {
/*      */       if (level == 3) {
/*      */         this.engine.update(dirRecids[level], new long[16][], (Serializer)DIR_SERIALIZER);
/*      */       } else {
/*      */         this.engine.delete(dirRecids[level], (Serializer)DIR_SERIALIZER);
/*      */         long[][] parentDir = this.engine.<long[][]>get(dirRecids[level + 1], (Serializer)DIR_SERIALIZER);
/*      */         int parentPos = h >>> 7 * (level + 1) & 0x7F;
/*      */         recursiveDirDelete(h, level + 1, dirRecids, parentDir, parentPos);
/*      */       } 
/*      */     } else {
/*      */       this.engine.update(dirRecids[level], dir, (Serializer)DIR_SERIALIZER);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void clear() {
/*      */     for (int i = 0; i < 16; i++) {
/*      */       try {
/*      */         this.segmentLocks[i].writeLock().lock();
/*      */         long dirRecid = this.segmentRecids[i];
/*      */         recursiveDirClear(dirRecid);
/*      */         this.engine.update(dirRecid, new long[16][], (Serializer)DIR_SERIALIZER);
/*      */         if (this.expireFlag)
/*      */           while (expireLinkRemoveLast(i) != null); 
/*      */         this.segmentLocks[i].writeLock().unlock();
/*      */       } finally {
/*      */         this.segmentLocks[i].writeLock().unlock();
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void recursiveDirClear(long dirRecid) {
/*      */     long[][] dir = this.engine.<long[][]>get(dirRecid, (Serializer)DIR_SERIALIZER);
/*      */     if (dir == null)
/*      */       return; 
/*      */     for (long[] subdir : dir) {
/*      */       if (subdir != null)
/*      */         for (long recid : subdir) {
/*      */           if (recid != 0L)
/*      */             if ((recid & 0x1L) == 0L) {
/*      */               recid >>>= 1L;
/*      */               recursiveDirClear(recid);
/*      */               this.engine.delete(recid, (Serializer)DIR_SERIALIZER);
/*      */             } else {
/*      */               recid >>>= 1L;
/*      */               while (recid != 0L) {
/*      */                 LinkedNode n = this.engine.<LinkedNode>get(recid, (Serializer)this.LN_SERIALIZER);
/*      */                 this.engine.delete(recid, this.LN_SERIALIZER);
/*      */                 notify(n.key, n.value, null);
/*      */                 recid = n.next;
/*      */               } 
/*      */             }  
/*      */         }  
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean containsValue(Object value) {
/*      */     for (V v : values()) {
/*      */       if (v.equals(value))
/*      */         return true; 
/*      */     } 
/*      */     return false;
/*      */   }
/*      */   
/*      */   protected class KeySet extends AbstractSet<K> {
/*      */     public int size() {
/*      */       return HTreeMap.this.size();
/*      */     }
/*      */     
/*      */     public boolean isEmpty() {
/*      */       return HTreeMap.this.isEmpty();
/*      */     }
/*      */     
/*      */     public boolean contains(Object o) {
/*      */       return HTreeMap.this.containsKey(o);
/*      */     }
/*      */     
/*      */     public Iterator<K> iterator() {
/*      */       return new HTreeMap.KeyIterator();
/*      */     }
/*      */     
/*      */     public boolean add(K k) {
/*      */       if (HTreeMap.this.hasValues)
/*      */         throw new UnsupportedOperationException(); 
/*      */       return (HTreeMap.this.put(k, BTreeMap.EMPTY) == null);
/*      */     }
/*      */     
/*      */     public boolean remove(Object o) {
/*      */       return (HTreeMap.this.remove(o) != null);
/*      */     }
/*      */     
/*      */     public void clear() {
/*      */       HTreeMap.this.clear();
/*      */     }
/*      */     
/*      */     public HTreeMap<K, V> parent() {
/*      */       return HTreeMap.this;
/*      */     }
/*      */     
/*      */     public int hashCode() {
/*      */       int result = 0;
/*      */       for (K k : this)
/*      */         result += HTreeMap.this.hasher.hashCode(k); 
/*      */       return result;
/*      */     }
/*      */   }
/*      */   
/*      */   public Set<K> keySet() {
/*      */     return this._keySet;
/*      */   }
/*      */   
/*      */   public Collection<V> values() {
/*      */     return this._values;
/*      */   }
/*      */   
/*      */   public Set<Map.Entry<K, V>> entrySet() {
/*      */     return this._entrySet;
/*      */   }
/*      */   
/*      */   protected int hash(Object key) {
/*      */     int h = this.hasher.hashCode((K)key) ^ this.hashSalt;
/*      */     h ^= h >>> 20 ^ h >>> 12;
/*      */     return h ^ h >>> 7 ^ h >>> 4;
/*      */   }
/*      */   
/*      */   abstract class HashIterator {
/*      */     protected HTreeMap.LinkedNode[] currentLinkedList;
/*      */     
/*      */     protected int currentLinkedListPos = 0;
/*      */     
/*      */     private K lastReturnedKey = null;
/*      */     
/*      */     private int lastSegment = 0;
/*      */     
/*      */     HashIterator() {
/*      */       this.currentLinkedList = findNextLinkedNode(0);
/*      */     }
/*      */     
/*      */     public void remove() {
/*      */       K keyToRemove = this.lastReturnedKey;
/*      */       if (this.lastReturnedKey == null)
/*      */         throw new IllegalStateException(); 
/*      */       this.lastReturnedKey = null;
/*      */       HTreeMap.this.remove(keyToRemove);
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*      */       return (this.currentLinkedList != null && this.currentLinkedListPos < this.currentLinkedList.length);
/*      */     }
/*      */     
/*      */     protected void moveToNext() {
/*      */       this.lastReturnedKey = (this.currentLinkedList[this.currentLinkedListPos]).key;
/*      */       this.currentLinkedListPos++;
/*      */       if (this.currentLinkedListPos == this.currentLinkedList.length) {
/*      */         int lastHash = HTreeMap.this.hash(this.lastReturnedKey);
/*      */         this.currentLinkedList = advance(lastHash);
/*      */         this.currentLinkedListPos = 0;
/*      */       } 
/*      */     }
/*      */     
/*      */     private HTreeMap.LinkedNode[] advance(int lastHash) {
/*      */       int segment = lastHash >>> 28;
/*      */       try {
/*      */         HTreeMap.this.segmentLocks[segment].readLock().lock();
/*      */         long dirRecid = HTreeMap.this.segmentRecids[segment];
/*      */         int level = 3;
/*      */         while (true) {
/*      */           long[][] dir = HTreeMap.this.engine.<long[][]>get(dirRecid, (Serializer)HTreeMap.DIR_SERIALIZER);
/*      */           int pos = lastHash >>> 7 * level & 0x7F;
/*      */           if (dir[pos >>> 3] == null || dir[pos >>> 3][pos & 0x7] == 0L || (dir[pos >>> 3][pos & 0x7] & 0x1L) == 1L) {
/*      */             if (level != 0) {
/*      */               lastHash = (lastHash >>> 7 * level) + 1 << 7 * level;
/*      */             } else {
/*      */               lastHash++;
/*      */             } 
/*      */             if (lastHash == 0)
/*      */               return null; 
/*      */             break;
/*      */           } 
/*      */           dirRecid = dir[pos >>> 3][pos & 0x7] >>> 1L;
/*      */           level--;
/*      */         } 
/*      */       } finally {
/*      */         HTreeMap.this.segmentLocks[segment].readLock().unlock();
/*      */       } 
/*      */       return findNextLinkedNode(lastHash);
/*      */     }
/*      */     
/*      */     private HTreeMap.LinkedNode[] findNextLinkedNode(int hash) {
/*      */       for (int segment = Math.max(hash >>> 28, this.lastSegment); segment < 16; segment++) {
/*      */         Lock lock = HTreeMap.this.expireAccessFlag ? HTreeMap.this.segmentLocks[segment].writeLock() : HTreeMap.this.segmentLocks[segment].readLock();
/*      */         lock.lock();
/*      */       } 
/*      */       return null;
/*      */     }
/*      */     
/*      */     private HTreeMap.LinkedNode[] findNextLinkedNodeRecur(long dirRecid, int newHash, int level) {
/*      */       long[][] dir = HTreeMap.this.engine.<long[][]>get(dirRecid, (Serializer)HTreeMap.DIR_SERIALIZER);
/*      */       if (dir == null)
/*      */         return null; 
/*      */       int pos = newHash >>> level * 7 & 0x7F;
/*      */       boolean first = true;
/*      */       while (pos < 128) {
/*      */         if (dir[pos >>> 3] != null) {
/*      */           long recid = dir[pos >>> 3][pos & 0x7];
/*      */           if (recid != 0L) {
/*      */             if ((recid & 0x1L) == 1L) {
/*      */               recid >>= 1L;
/*      */               HTreeMap.LinkedNode[] array = new HTreeMap.LinkedNode[1];
/*      */               int arrayPos = 0;
/*      */               while (recid != 0L) {
/*      */                 HTreeMap.LinkedNode ln = HTreeMap.this.engine.<HTreeMap.LinkedNode>get(recid, HTreeMap.this.LN_SERIALIZER);
/*      */                 if (ln == null) {
/*      */                   recid = 0L;
/*      */                   continue;
/*      */                 } 
/*      */                 if (arrayPos == array.length)
/*      */                   array = Arrays.<HTreeMap.LinkedNode>copyOf(array, array.length + 1); 
/*      */                 array[arrayPos++] = ln;
/*      */                 recid = ln.next;
/*      */               } 
/*      */               return array;
/*      */             } 
/*      */             recid >>= 1L;
/*      */             HTreeMap.LinkedNode[] ret = findNextLinkedNodeRecur(recid, first ? newHash : 0, level - 1);
/*      */             if (ret != null)
/*      */               return ret; 
/*      */           } 
/*      */         } 
/*      */         first = false;
/*      */         pos++;
/*      */       } 
/*      */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */   class KeyIterator extends HashIterator implements Iterator<K> {
/*      */     public K next() {
/*      */       if (this.currentLinkedList == null)
/*      */         throw new NoSuchElementException(); 
/*      */       K key = (this.currentLinkedList[this.currentLinkedListPos]).key;
/*      */       moveToNext();
/*      */       return key;
/*      */     }
/*      */   }
/*      */   
/*      */   class ValueIterator extends HashIterator implements Iterator<V> {
/*      */     public V next() {
/*      */       if (this.currentLinkedList == null)
/*      */         throw new NoSuchElementException(); 
/*      */       V value = (this.currentLinkedList[this.currentLinkedListPos]).value;
/*      */       moveToNext();
/*      */       return value;
/*      */     }
/*      */   }
/*      */   
/*      */   class EntryIterator extends HashIterator implements Iterator<Map.Entry<K, V>> {
/*      */     public Map.Entry<K, V> next() {
/*      */       if (this.currentLinkedList == null)
/*      */         throw new NoSuchElementException(); 
/*      */       K key = (this.currentLinkedList[this.currentLinkedListPos]).key;
/*      */       moveToNext();
/*      */       return new HTreeMap.Entry2(key);
/*      */     }
/*      */   }
/*      */   
/*      */   class Entry2 implements Map.Entry<K, V> {
/*      */     private final K key;
/*      */     
/*      */     Entry2(K key) {
/*      */       this.key = key;
/*      */     }
/*      */     
/*      */     public K getKey() {
/*      */       return this.key;
/*      */     }
/*      */     
/*      */     public V getValue() {
/*      */       return (V)HTreeMap.this.get(this.key);
/*      */     }
/*      */     
/*      */     public V setValue(V value) {
/*      */       return HTreeMap.this.put(this.key, value);
/*      */     }
/*      */     
/*      */     public boolean equals(Object o) {
/*      */       return (o instanceof Map.Entry && HTreeMap.this.hasher.equals(this.key, (K)((Map.Entry)o).getKey()));
/*      */     }
/*      */     
/*      */     public int hashCode() {
/*      */       V value = (V)HTreeMap.this.get(this.key);
/*      */       return ((this.key == null) ? 0 : HTreeMap.this.hasher.hashCode(this.key)) ^ ((value == null) ? 0 : value.hashCode());
/*      */     }
/*      */   }
/*      */   
/*      */   public V putIfAbsent(K key, V value) {
/*      */     if (key == null || value == null)
/*      */       throw new NullPointerException(); 
/*      */     int h = hash(key);
/*      */     int segment = h >>> 28;
/*      */     try {
/*      */       this.segmentLocks[segment].writeLock().lock();
/*      */       LinkedNode<K, V> ln = getInner(key, h, segment);
/*      */       if (ln == null)
/*      */         return put(key, value); 
/*      */       return ln.value;
/*      */     } finally {
/*      */       this.segmentLocks[segment].writeLock().unlock();
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean remove(Object key, Object value) {
/*      */     if (key == null || value == null)
/*      */       throw new NullPointerException(); 
/*      */     int h = hash(key);
/*      */     int segment = h >>> 28;
/*      */     try {
/*      */       this.segmentLocks[segment].writeLock().lock();
/*      */       LinkedNode<K, V> otherVal = getInner(key, h, segment);
/*      */       if (otherVal != null && otherVal.value.equals(value)) {
/*      */         removeInternal(key, segment, h, true);
/*      */         return true;
/*      */       } 
/*      */       return false;
/*      */     } finally {
/*      */       this.segmentLocks[segment].writeLock().unlock();
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean replace(K key, V oldValue, V newValue) {
/*      */     if (key == null || oldValue == null || newValue == null)
/*      */       throw new NullPointerException(); 
/*      */     int h = hash(key);
/*      */     int segment = h >>> 28;
/*      */     try {
/*      */       this.segmentLocks[segment].writeLock().lock();
/*      */       LinkedNode<K, V> ln = getInner(key, h, segment);
/*      */       if (ln != null && ln.value.equals(oldValue)) {
/*      */         putInner(key, newValue, h, segment);
/*      */         return true;
/*      */       } 
/*      */       return false;
/*      */     } finally {
/*      */       this.segmentLocks[segment].writeLock().unlock();
/*      */     } 
/*      */   }
/*      */   
/*      */   public V replace(K key, V value) {
/*      */     if (key == null || value == null)
/*      */       throw new NullPointerException(); 
/*      */     int h = hash(key);
/*      */     int segment = h >>> 28;
/*      */     try {
/*      */       this.segmentLocks[segment].writeLock().lock();
/*      */       if (getInner(key, h, segment) != null)
/*      */         return putInner(key, value, h, segment); 
/*      */       return null;
/*      */     } finally {
/*      */       this.segmentLocks[segment].writeLock().unlock();
/*      */     } 
/*      */   }
/*      */   
/*      */   protected static final class ExpireLinkNode {
/*      */     public static final ExpireLinkNode EMPTY = new ExpireLinkNode(0L, 0L, 0L, 0L, 0);
/*      */     
/*      */     public static final Serializer<ExpireLinkNode> SERIALIZER = new Serializer<ExpireLinkNode>() {
/*      */         public void serialize(DataOutput out, HTreeMap.ExpireLinkNode value) throws IOException {
/*      */           if (value == HTreeMap.ExpireLinkNode.EMPTY)
/*      */             return; 
/*      */           DataOutput2.packLong(out, value.prev);
/*      */           DataOutput2.packLong(out, value.next);
/*      */           DataOutput2.packLong(out, value.keyRecid);
/*      */           DataOutput2.packLong(out, value.time);
/*      */           out.writeInt(value.hash);
/*      */         }
/*      */         
/*      */         public HTreeMap.ExpireLinkNode deserialize(DataInput in, int available) throws IOException {
/*      */           if (available == 0)
/*      */             return HTreeMap.ExpireLinkNode.EMPTY; 
/*      */           return new HTreeMap.ExpireLinkNode(DataInput2.unpackLong(in), DataInput2.unpackLong(in), DataInput2.unpackLong(in), DataInput2.unpackLong(in), in.readInt());
/*      */         }
/*      */         
/*      */         public int fixedSize() {
/*      */           return -1;
/*      */         }
/*      */       };
/*      */     
/*      */     public final long prev;
/*      */     
/*      */     public final long next;
/*      */     
/*      */     public final long keyRecid;
/*      */     
/*      */     public final long time;
/*      */     
/*      */     public final int hash;
/*      */     
/*      */     public ExpireLinkNode(long prev, long next, long keyRecid, long time, int hash) {
/*      */       this.prev = prev;
/*      */       this.next = next;
/*      */       this.keyRecid = keyRecid;
/*      */       this.time = time;
/*      */       this.hash = hash;
/*      */     }
/*      */     
/*      */     public ExpireLinkNode copyNext(long next2) {
/*      */       return new ExpireLinkNode(this.prev, next2, this.keyRecid, this.time, this.hash);
/*      */     }
/*      */     
/*      */     public ExpireLinkNode copyPrev(long prev2) {
/*      */       return new ExpireLinkNode(prev2, this.next, this.keyRecid, this.time, this.hash);
/*      */     }
/*      */     
/*      */     public ExpireLinkNode copyTime(long time2) {
/*      */       return new ExpireLinkNode(this.prev, this.next, this.keyRecid, time2, this.hash);
/*      */     }
/*      */   }
/*      */   
/*      */   protected void expireLinkAdd(int segment, long expireNodeRecid, long keyRecid, int hash) {
/*      */     assert this.segmentLocks[segment].writeLock().isHeldByCurrentThread();
/*      */     assert expireNodeRecid > 0L;
/*      */     assert keyRecid > 0L;
/*      */     long time = (this.expire == 0L) ? 0L : (this.expire + System.currentTimeMillis() - this.expireTimeStart);
/*      */     long head = ((Long)this.engine.<Long>get(this.expireHeads[segment], Serializer.LONG)).longValue();
/*      */     if (head == 0L) {
/*      */       ExpireLinkNode n = new ExpireLinkNode(0L, 0L, keyRecid, time, hash);
/*      */       this.engine.update(expireNodeRecid, n, ExpireLinkNode.SERIALIZER);
/*      */       this.engine.update(this.expireHeads[segment], Long.valueOf(expireNodeRecid), Serializer.LONG);
/*      */       this.engine.update(this.expireTails[segment], Long.valueOf(expireNodeRecid), Serializer.LONG);
/*      */     } else {
/*      */       ExpireLinkNode n = new ExpireLinkNode(head, 0L, keyRecid, time, hash);
/*      */       this.engine.update(expireNodeRecid, n, ExpireLinkNode.SERIALIZER);
/*      */       ExpireLinkNode oldHead = this.engine.<ExpireLinkNode>get(head, ExpireLinkNode.SERIALIZER);
/*      */       oldHead = oldHead.copyNext(expireNodeRecid);
/*      */       this.engine.update(head, oldHead, ExpireLinkNode.SERIALIZER);
/*      */       this.engine.update(this.expireHeads[segment], Long.valueOf(expireNodeRecid), Serializer.LONG);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void expireLinkBump(int segment, long nodeRecid, boolean access) {
/*      */     assert this.segmentLocks[segment].writeLock().isHeldByCurrentThread();
/*      */     ExpireLinkNode n = this.engine.<ExpireLinkNode>get(nodeRecid, ExpireLinkNode.SERIALIZER);
/*      */     long newTime = access ? ((this.expireAccess == 0L) ? n.time : (this.expireAccess + System.currentTimeMillis() - this.expireTimeStart)) : ((this.expire == 0L) ? n.time : (this.expire + System.currentTimeMillis() - this.expireTimeStart));
/*      */     if (n.next == 0L) {
/*      */       n = n.copyTime(newTime);
/*      */       this.engine.update(nodeRecid, n, ExpireLinkNode.SERIALIZER);
/*      */     } else {
/*      */       if (n.prev != 0L) {
/*      */         ExpireLinkNode prev = this.engine.<ExpireLinkNode>get(n.prev, ExpireLinkNode.SERIALIZER);
/*      */         prev = prev.copyNext(n.next);
/*      */         this.engine.update(n.prev, prev, ExpireLinkNode.SERIALIZER);
/*      */       } else {
/*      */         this.engine.update(this.expireTails[segment], Long.valueOf(n.next), Serializer.LONG);
/*      */       } 
/*      */       ExpireLinkNode next = this.engine.<ExpireLinkNode>get(n.next, ExpireLinkNode.SERIALIZER);
/*      */       next = next.copyPrev(n.prev);
/*      */       this.engine.update(n.next, next, ExpireLinkNode.SERIALIZER);
/*      */       long oldHeadRecid = ((Long)this.engine.<Long>get(this.expireHeads[segment], Serializer.LONG)).longValue();
/*      */       ExpireLinkNode oldHead = this.engine.<ExpireLinkNode>get(oldHeadRecid, ExpireLinkNode.SERIALIZER);
/*      */       oldHead = oldHead.copyNext(nodeRecid);
/*      */       this.engine.update(oldHeadRecid, oldHead, ExpireLinkNode.SERIALIZER);
/*      */       this.engine.update(this.expireHeads[segment], Long.valueOf(nodeRecid), Serializer.LONG);
/*      */       n = new ExpireLinkNode(oldHeadRecid, 0L, n.keyRecid, newTime, n.hash);
/*      */       this.engine.update(nodeRecid, n, ExpireLinkNode.SERIALIZER);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected ExpireLinkNode expireLinkRemoveLast(int segment) {
/*      */     assert this.segmentLocks[segment].writeLock().isHeldByCurrentThread();
/*      */     long tail = ((Long)this.engine.<Long>get(this.expireTails[segment], Serializer.LONG)).longValue();
/*      */     if (tail == 0L)
/*      */       return null; 
/*      */     ExpireLinkNode n = this.engine.<ExpireLinkNode>get(tail, ExpireLinkNode.SERIALIZER);
/*      */     if (n.next == 0L) {
/*      */       this.engine.update(this.expireHeads[segment], Long.valueOf(0L), Serializer.LONG);
/*      */       this.engine.update(this.expireTails[segment], Long.valueOf(0L), Serializer.LONG);
/*      */     } else {
/*      */       this.engine.update(this.expireTails[segment], Long.valueOf(n.next), Serializer.LONG);
/*      */       ExpireLinkNode next = this.engine.<ExpireLinkNode>get(n.next, ExpireLinkNode.SERIALIZER);
/*      */       next = next.copyPrev(0L);
/*      */       this.engine.update(n.next, next, ExpireLinkNode.SERIALIZER);
/*      */     } 
/*      */     this.engine.delete(tail, ExpireLinkNode.SERIALIZER);
/*      */     return n;
/*      */   }
/*      */   
/*      */   protected ExpireLinkNode expireLinkRemove(int segment, long nodeRecid) {
/*      */     assert this.segmentLocks[segment].writeLock().isHeldByCurrentThread();
/*      */     ExpireLinkNode n = this.engine.<ExpireLinkNode>get(nodeRecid, ExpireLinkNode.SERIALIZER);
/*      */     this.engine.delete(nodeRecid, ExpireLinkNode.SERIALIZER);
/*      */     if (n.next == 0L && n.prev == 0L) {
/*      */       this.engine.update(this.expireHeads[segment], Long.valueOf(0L), Serializer.LONG);
/*      */       this.engine.update(this.expireTails[segment], Long.valueOf(0L), Serializer.LONG);
/*      */     } else if (n.next == 0L) {
/*      */       ExpireLinkNode prev = this.engine.<ExpireLinkNode>get(n.prev, ExpireLinkNode.SERIALIZER);
/*      */       prev = prev.copyNext(0L);
/*      */       this.engine.update(n.prev, prev, ExpireLinkNode.SERIALIZER);
/*      */       this.engine.update(this.expireHeads[segment], Long.valueOf(n.prev), Serializer.LONG);
/*      */     } else if (n.prev == 0L) {
/*      */       ExpireLinkNode next = this.engine.<ExpireLinkNode>get(n.next, ExpireLinkNode.SERIALIZER);
/*      */       next = next.copyPrev(0L);
/*      */       this.engine.update(n.next, next, ExpireLinkNode.SERIALIZER);
/*      */       this.engine.update(this.expireTails[segment], Long.valueOf(n.next), Serializer.LONG);
/*      */     } else {
/*      */       ExpireLinkNode next = this.engine.<ExpireLinkNode>get(n.next, ExpireLinkNode.SERIALIZER);
/*      */       next = next.copyPrev(n.prev);
/*      */       this.engine.update(n.next, next, ExpireLinkNode.SERIALIZER);
/*      */       ExpireLinkNode prev = this.engine.<ExpireLinkNode>get(n.prev, ExpireLinkNode.SERIALIZER);
/*      */       prev = prev.copyNext(n.next);
/*      */       this.engine.update(n.prev, prev, ExpireLinkNode.SERIALIZER);
/*      */     } 
/*      */     return n;
/*      */   }
/*      */   
/*      */   public long getMaxExpireTime() {
/*      */     if (!this.expireFlag)
/*      */       return 0L; 
/*      */     long ret = 0L;
/*      */     for (int segment = 0; segment < 16; segment++)
/*      */       this.segmentLocks[segment].readLock().lock(); 
/*      */     return ret;
/*      */   }
/*      */   
/*      */   public long getMinExpireTime() {
/*      */     if (!this.expireFlag)
/*      */       return 0L; 
/*      */     long ret = Long.MAX_VALUE;
/*      */     for (int segment = 0; segment < 16; segment++)
/*      */       this.segmentLocks[segment].readLock().lock(); 
/*      */     if (ret == Long.MAX_VALUE)
/*      */       ret = 0L; 
/*      */     return ret;
/*      */   }
/*      */   
/*      */   protected static class ExpireRunnable implements Runnable {
/*      */     final WeakReference<HTreeMap> mapRef;
/*      */     
/*      */     public ExpireRunnable(HTreeMap map) {
/*      */       this.mapRef = new WeakReference<HTreeMap>(map);
/*      */     }
/*      */     
/*      */     public void run() {
/*      */       boolean pause = false;
/*      */       try {
/*      */         while (true) {
/*      */           if (pause)
/*      */             Thread.sleep(1000L); 
/*      */           HTreeMap map = this.mapRef.get();
/*      */           if (map == null || map.engine.isClosed() || map.closeLatch.getCount() < 2L)
/*      */             return; 
/*      */           map.expirePurge();
/*      */           if (map.engine.isClosed() || map.closeLatch.getCount() < 2L)
/*      */             return; 
/*      */           pause = ((!map.expireMaxSizeFlag || map.size() < map.expireMaxSize) && (map.expireStoreSize == 0L || Store.forEngine(map.engine).getCurrSize() - Store.forEngine(map.engine).getFreeSize() < map.expireStoreSize));
/*      */         } 
/*      */       } catch (Throwable e) {
/*      */         e.printStackTrace();
/*      */       } finally {
/*      */         HTreeMap m = this.mapRef.get();
/*      */         if (m != null)
/*      */           m.closeLatch.countDown(); 
/*      */         this.mapRef.clear();
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   protected void expirePurge() {
/*      */     if (!this.expireFlag)
/*      */       return; 
/*      */     long removePerSegment = 0L;
/*      */     if (this.expireMaxSizeFlag) {
/*      */       long size = this.counter.get();
/*      */       if (size > this.expireMaxSize)
/*      */         removePerSegment = 1L + (size - this.expireMaxSize) / 16L; 
/*      */     } 
/*      */     if (this.expireStoreSize != 0L && removePerSegment == 0L) {
/*      */       Store store = Store.forEngine(this.engine);
/*      */       if (this.expireStoreSize < store.getCurrSize() - store.getFreeSize())
/*      */         removePerSegment = 640L; 
/*      */     } 
/*      */     for (int seg = 0; seg < 16; seg++) {
/*      */       if (this.closeLatch.getCount() < 2L)
/*      */         return; 
/*      */       expirePurgeSegment(seg, removePerSegment);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void expirePurgeSegment(int seg, long removePerSegment) {
/*      */     this.segmentLocks[seg].writeLock().lock();
/*      */     try {
/*      */       long recid = ((Long)this.engine.<Long>get(this.expireTails[seg], Serializer.LONG)).longValue();
/*      */       long counter = 0L;
/*      */       ExpireLinkNode last = null, n = null;
/*      */       while (recid != 0L) {
/*      */         n = this.engine.<ExpireLinkNode>get(recid, ExpireLinkNode.SERIALIZER);
/*      */         assert n != ExpireLinkNode.EMPTY;
/*      */         assert n.hash >>> 28 == seg;
/*      */         boolean remove = (++counter < removePerSegment || ((this.expire != 0L || this.expireAccess != 0L) && n.time + this.expireTimeStart < System.currentTimeMillis()));
/*      */         if (remove) {
/*      */           this.engine.delete(recid, ExpireLinkNode.SERIALIZER);
/*      */           LinkedNode<K, V> ln = this.engine.<LinkedNode<K, V>>get(n.keyRecid, this.LN_SERIALIZER);
/*      */           removeInternal(ln.key, seg, n.hash, false);
/*      */           last = n;
/*      */           recid = n.next;
/*      */         } 
/*      */       } 
/*      */       if (last != null)
/*      */         if (recid == 0L) {
/*      */           this.engine.update(this.expireTails[seg], Long.valueOf(0L), Serializer.LONG);
/*      */           this.engine.update(this.expireHeads[seg], Long.valueOf(0L), Serializer.LONG);
/*      */         } else {
/*      */           this.engine.update(this.expireTails[seg], Long.valueOf(recid), Serializer.LONG);
/*      */           n = this.engine.<ExpireLinkNode>get(recid, ExpireLinkNode.SERIALIZER);
/*      */           n = n.copyPrev(0L);
/*      */           this.engine.update(recid, n, ExpireLinkNode.SERIALIZER);
/*      */         }  
/*      */     } finally {
/*      */       this.segmentLocks[seg].writeLock().unlock();
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void expireCheckSegment(int segment) {
/*      */     long current = ((Long)this.engine.<Long>get(this.expireTails[segment], Serializer.LONG)).longValue();
/*      */     if (current == 0L) {
/*      */       if (((Long)this.engine.<Long>get(this.expireHeads[segment], Serializer.LONG)).longValue() != 0L)
/*      */         throw new AssertionError("head not 0"); 
/*      */       return;
/*      */     } 
/*      */     long prev = 0L;
/*      */     while (current != 0L) {
/*      */       ExpireLinkNode curr = this.engine.<ExpireLinkNode>get(current, ExpireLinkNode.SERIALIZER);
/*      */       assert curr.prev == prev : "wrong prev " + curr.prev + " - " + prev;
/*      */       prev = current;
/*      */       current = curr.next;
/*      */     } 
/*      */     if (((Long)this.engine.<Long>get(this.expireHeads[segment], Serializer.LONG)).longValue() != prev)
/*      */       throw new AssertionError("wrong head"); 
/*      */   }
/*      */   
/*      */   public Map<K, V> snapshot() {
/*      */     Engine snapshot = TxEngine.createSnapshotFor(this.engine);
/*      */     return new HTreeMap(snapshot, (this.counter == null) ? 0L : this.counter.recid, this.hashSalt, this.segmentRecids, this.keySerializer, this.valueSerializer, 0L, 0L, 0L, 0L, 0L, null, null, null, null, false);
/*      */   }
/*      */   
/*      */   public void modificationListenerAdd(Bind.MapListener<K, V> listener) {
/* 1669 */     synchronized (this.modListenersLock) {
/* 1670 */       Bind.MapListener[] arrayOfMapListener = Arrays.<Bind.MapListener>copyOf((Bind.MapListener[])this.modListeners, this.modListeners.length + 1);
/* 1672 */       arrayOfMapListener[arrayOfMapListener.length - 1] = listener;
/* 1673 */       this.modListeners = (Bind.MapListener<K, V>[])arrayOfMapListener;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void modificationListenerRemove(Bind.MapListener<K, V> listener) {
/* 1680 */     synchronized (this.modListenersLock) {
/* 1681 */       for (int i = 0; i < this.modListeners.length; i++) {
/* 1682 */         if (this.modListeners[i] == listener)
/* 1682 */           this.modListeners[i] = null; 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void notify(K key, V oldValue, V newValue) {
/* 1688 */     assert this.segmentLocks[hash(key) >>> 28].isWriteLockedByCurrentThread();
/* 1689 */     Bind.MapListener<K, V>[] modListeners2 = this.modListeners;
/* 1690 */     for (Bind.MapListener<K, V> listener : modListeners2) {
/* 1691 */       if (listener != null)
/* 1692 */         listener.update(key, oldValue, newValue); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void close() {
/* 1701 */     this.engine.close();
/*      */   }
/*      */   
/*      */   public Engine getEngine() {
/* 1705 */     return this.engine;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\mapdb\HTreeMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */