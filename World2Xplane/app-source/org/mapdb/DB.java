/*      */ package org.mapdb;
/*      */ 
/*      */ import java.lang.ref.WeakReference;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.Comparator;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.NavigableSet;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Queue;
/*      */ import java.util.Random;
/*      */ import java.util.Set;
/*      */ import java.util.SortedMap;
/*      */ import java.util.TreeMap;
/*      */ import java.util.concurrent.BlockingQueue;
/*      */ import java.util.concurrent.TimeUnit;
/*      */ 
/*      */ public class DB {
/*      */   protected final boolean strictDBGet;
/*      */   
/*      */   protected Engine engine;
/*      */   
/*   38 */   protected Map<String, WeakReference<?>> namesInstanciated = new HashMap<String, WeakReference<?>>();
/*      */   
/*   40 */   protected Map<IdentityWrapper, String> namesLookup = Collections.synchronizedMap(new HashMap<IdentityWrapper, String>());
/*      */   
/*      */   protected SortedMap<String, Object> catalog;
/*      */   
/*      */   protected static class IdentityWrapper {
/*      */     final Object o;
/*      */     
/*      */     public IdentityWrapper(Object o) {
/*   52 */       this.o = o;
/*      */     }
/*      */     
/*      */     public int hashCode() {
/*   57 */       return System.identityHashCode(this.o);
/*      */     }
/*      */     
/*      */     public boolean equals(Object v) {
/*   62 */       return (((IdentityWrapper)v).o == this.o);
/*      */     }
/*      */   }
/*      */   
/*      */   public DB(Engine engine) {
/*   71 */     this(engine, false, false);
/*      */   }
/*      */   
/*      */   public DB(Engine engine, boolean strictDBGet, boolean disableLocks) {
/*   75 */     if (!(engine instanceof EngineWrapper))
/*   78 */       engine = new EngineWrapper(engine); 
/*   80 */     this.engine = engine;
/*   81 */     this.strictDBGet = strictDBGet;
/*   82 */     engine.getSerializerPojo().setDb(this);
/*   83 */     reinit();
/*      */   }
/*      */   
/*      */   protected void reinit() {
/*   88 */     this.catalog = BTreeMap.preinitCatalog(this);
/*      */   }
/*      */   
/*      */   public <A> A catGet(String name, A init) {
/*   92 */     assert Thread.holdsLock(this);
/*   93 */     A ret = (A)this.catalog.get(name);
/*   94 */     return (ret != null) ? ret : init;
/*      */   }
/*      */   
/*      */   public <A> A catGet(String name) {
/*   99 */     assert Thread.holdsLock(this);
/*  100 */     return (A)this.catalog.get(name);
/*      */   }
/*      */   
/*      */   public <A> A catPut(String name, A value) {
/*  104 */     assert Thread.holdsLock(this);
/*  105 */     this.catalog.put(name, value);
/*  106 */     return value;
/*      */   }
/*      */   
/*      */   public <A> A catPut(String name, A value, A retValueIfNull) {
/*  110 */     assert Thread.holdsLock(this);
/*  111 */     if (value == null)
/*  111 */       return retValueIfNull; 
/*  112 */     this.catalog.put(name, value);
/*  113 */     return value;
/*      */   }
/*      */   
/*      */   public String getNameForObject(Object obj) {
/*  119 */     return this.namesLookup.get(new IdentityWrapper(obj));
/*      */   }
/*      */   
/*      */   public class HTreeMapMaker {
/*      */     protected final String name;
/*      */     
/*      */     protected boolean counter;
/*      */     
/*      */     protected Serializer<?> keySerializer;
/*      */     
/*      */     protected Serializer<?> valueSerializer;
/*      */     
/*      */     protected long expireMaxSize;
/*      */     
/*      */     protected long expire;
/*      */     
/*      */     protected long expireAccess;
/*      */     
/*      */     protected long expireStoreSize;
/*      */     
/*      */     protected Hasher<?> hasher;
/*      */     
/*      */     protected Fun.Function1<?, ?> valueCreator;
/*      */     
/*      */     public HTreeMapMaker(String name) {
/*  131 */       this.counter = false;
/*  132 */       this.keySerializer = null;
/*  133 */       this.valueSerializer = null;
/*  134 */       this.expireMaxSize = 0L;
/*  135 */       this.expire = 0L;
/*  136 */       this.expireAccess = 0L;
/*  138 */       this.hasher = null;
/*  140 */       this.valueCreator = null;
/*      */       this.name = name;
/*      */     }
/*      */     
/*      */     public HTreeMapMaker counterEnable() {
/*  147 */       this.counter = true;
/*  148 */       return this;
/*      */     }
/*      */     
/*      */     public HTreeMapMaker keySerializer(Serializer<?> keySerializer) {
/*  155 */       this.keySerializer = keySerializer;
/*  156 */       return this;
/*      */     }
/*      */     
/*      */     public HTreeMapMaker valueSerializer(Serializer<?> valueSerializer) {
/*  161 */       this.valueSerializer = valueSerializer;
/*  162 */       return this;
/*      */     }
/*      */     
/*      */     public HTreeMapMaker expireMaxSize(long maxSize) {
/*  167 */       this.expireMaxSize = maxSize;
/*  168 */       this.counter = true;
/*  169 */       return this;
/*      */     }
/*      */     
/*      */     public HTreeMapMaker expireAfterWrite(long interval, TimeUnit timeUnit) {
/*  174 */       this.expire = timeUnit.toMillis(interval);
/*  175 */       return this;
/*      */     }
/*      */     
/*      */     public HTreeMapMaker expireAfterWrite(long interval) {
/*  180 */       this.expire = interval;
/*  181 */       return this;
/*      */     }
/*      */     
/*      */     public HTreeMapMaker expireAfterAccess(long interval, TimeUnit timeUnit) {
/*  186 */       this.expireAccess = timeUnit.toMillis(interval);
/*  187 */       return this;
/*      */     }
/*      */     
/*      */     public HTreeMapMaker expireAfterAccess(long interval) {
/*  192 */       this.expireAccess = interval;
/*  193 */       return this;
/*      */     }
/*      */     
/*      */     public HTreeMapMaker expireStoreSize(double maxStoreSize) {
/*  197 */       this.expireStoreSize = (long)(maxStoreSize * 1024.0D * 1024.0D * 1024.0D);
/*  198 */       return this;
/*      */     }
/*      */     
/*      */     public HTreeMapMaker valueCreator(Fun.Function1<?, ?> valueCreator) {
/*  204 */       this.valueCreator = valueCreator;
/*  205 */       return this;
/*      */     }
/*      */     
/*      */     public HTreeMapMaker hasher(Hasher<?> hasher) {
/*  209 */       this.hasher = hasher;
/*  210 */       return this;
/*      */     }
/*      */     
/*      */     public <K, V> HTreeMap<K, V> make() {
/*  215 */       if (this.expireMaxSize != 0L)
/*  215 */         this.counter = true; 
/*  216 */       return DB.this.createHashMap(this);
/*      */     }
/*      */     
/*      */     public <K, V> HTreeMap<K, V> makeOrGet() {
/*  220 */       synchronized (DB.this) {
/*  222 */         return (DB.this.catGet(this.name + ".type") == null) ? make() : DB.this.<K, V>getHashMap(this.name);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public class HTreeSetMaker {
/*      */     protected final String name;
/*      */     
/*      */     protected boolean counter;
/*      */     
/*      */     protected Serializer<?> serializer;
/*      */     
/*      */     protected long expireMaxSize;
/*      */     
/*      */     protected long expireStoreSize;
/*      */     
/*      */     protected long expire;
/*      */     
/*      */     protected long expireAccess;
/*      */     
/*      */     protected Hasher<?> hasher;
/*      */     
/*      */     public HTreeSetMaker(String name) {
/*  237 */       this.counter = false;
/*  238 */       this.serializer = null;
/*  239 */       this.expireMaxSize = 0L;
/*  240 */       this.expireStoreSize = 0L;
/*  241 */       this.expire = 0L;
/*  242 */       this.expireAccess = 0L;
/*  243 */       this.hasher = null;
/*      */       this.name = name;
/*      */     }
/*      */     
/*      */     public HTreeSetMaker counterEnable() {
/*  247 */       this.counter = true;
/*  248 */       return this;
/*      */     }
/*      */     
/*      */     public HTreeSetMaker serializer(Serializer<?> serializer) {
/*  254 */       this.serializer = serializer;
/*  255 */       return this;
/*      */     }
/*      */     
/*      */     public HTreeSetMaker expireMaxSize(long maxSize) {
/*  261 */       this.expireMaxSize = maxSize;
/*  262 */       this.counter = true;
/*  263 */       return this;
/*      */     }
/*      */     
/*      */     public HTreeSetMaker expireStoreSize(double maxStoreSize) {
/*  268 */       this.expireStoreSize = (long)(maxStoreSize * 1024.0D * 1024.0D * 1024.0D);
/*  269 */       return this;
/*      */     }
/*      */     
/*      */     public HTreeSetMaker expireAfterWrite(long interval, TimeUnit timeUnit) {
/*  274 */       this.expire = timeUnit.toMillis(interval);
/*  275 */       return this;
/*      */     }
/*      */     
/*      */     public HTreeSetMaker expireAfterWrite(long interval) {
/*  280 */       this.expire = interval;
/*  281 */       return this;
/*      */     }
/*      */     
/*      */     public HTreeSetMaker expireAfterAccess(long interval, TimeUnit timeUnit) {
/*  286 */       this.expireAccess = timeUnit.toMillis(interval);
/*  287 */       return this;
/*      */     }
/*      */     
/*      */     public HTreeSetMaker expireAfterAccess(long interval) {
/*  292 */       this.expireAccess = interval;
/*  293 */       return this;
/*      */     }
/*      */     
/*      */     public HTreeSetMaker hasher(Hasher<?> hasher) {
/*  298 */       this.hasher = hasher;
/*  299 */       return this;
/*      */     }
/*      */     
/*      */     public <K> Set<K> make() {
/*  304 */       if (this.expireMaxSize != 0L)
/*  304 */         this.counter = true; 
/*  305 */       return DB.this.createHashSet(this);
/*      */     }
/*      */     
/*      */     public <K> Set<K> makeOrGet() {
/*  309 */       synchronized (DB.this) {
/*  311 */         return (DB.this.catGet(this.name + ".type") == null) ? make() : DB.this.<K>getHashSet(this.name);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public synchronized <K, V> HTreeMap<K, V> getHashMap(String name) {
/*  329 */     return getHashMap(name, null);
/*      */   }
/*      */   
/*      */   public synchronized <K, V> HTreeMap<K, V> getHashMap(String name, Fun.Function1<V, K> valueCreator) {
/*  342 */     checkNotClosed();
/*  343 */     HTreeMap<K, V> ret = (HTreeMap<K, V>)getFromWeakCollection(name);
/*  344 */     if (ret != null)
/*  344 */       return ret; 
/*  345 */     String type = catGet(name + ".type", null);
/*  346 */     if (type == null) {
/*  347 */       checkShouldCreate(name);
/*  348 */       if (this.engine.isReadOnly()) {
/*  349 */         Engine e = new StoreHeap();
/*  350 */         (new DB(e)).getHashMap("a");
/*  351 */         return namedPut(name, (new DB(new EngineWrapper.ReadOnlyEngine(e))).getHashMap("a"));
/*      */       } 
/*  354 */       if (valueCreator != null)
/*  355 */         return createHashMap(name).valueCreator(valueCreator).make(); 
/*  356 */       return createHashMap(name).make();
/*      */     } 
/*  361 */     checkType(type, "HashMap");
/*  363 */     ret = new HTreeMap<K, V>(this.engine, ((Long)catGet(name + ".counterRecid")).longValue(), ((Integer)catGet(name + ".hashSalt")).intValue(), catGet(name + ".segmentRecids"), catGet(name + ".keySerializer", getDefaultSerializer()), catGet(name + ".valueSerializer", getDefaultSerializer()), ((Long)catGet(name + ".expireTimeStart", Long.valueOf(0L))).longValue(), ((Long)catGet(name + ".expire", Long.valueOf(0L))).longValue(), ((Long)catGet(name + ".expireAccess", Long.valueOf(0L))).longValue(), ((Long)catGet(name + ".expireMaxSize", Long.valueOf(0L))).longValue(), ((Long)catGet(name + ".expireStoreSize", Long.valueOf(0L))).longValue(), catGet(name + ".expireHeads", null), catGet(name + ".expireTails", null), valueCreator, catGet(name + ".hasher", Hasher.BASIC), false);
/*  381 */     namedPut(name, ret);
/*  382 */     return ret;
/*      */   }
/*      */   
/*      */   public <V> V namedPut(String name, Object ret) {
/*  386 */     this.namesInstanciated.put(name, new WeakReference(ret));
/*  387 */     this.namesLookup.put(new IdentityWrapper(ret), name);
/*  388 */     return (V)ret;
/*      */   }
/*      */   
/*      */   public HTreeMapMaker createHashMap(String name) {
/*  400 */     return new HTreeMapMaker(name);
/*      */   }
/*      */   
/*      */   protected synchronized <K, V> HTreeMap<K, V> createHashMap(HTreeMapMaker m) {
/*  412 */     String name = m.name;
/*  413 */     checkNameNotExists(name);
/*  415 */     long expireTimeStart = 0L, expire = 0L, expireAccess = 0L, expireMaxSize = 0L, expireStoreSize = 0L;
/*  416 */     long[] expireHeads = null, expireTails = null;
/*  418 */     if (m.expire != 0L || m.expireAccess != 0L || m.expireMaxSize != 0L || m.expireStoreSize != 0L) {
/*  419 */       expireTimeStart = ((Long)catPut(name + ".expireTimeStart", Long.valueOf(System.currentTimeMillis()))).longValue();
/*  420 */       expire = ((Long)catPut(name + ".expire", Long.valueOf(m.expire))).longValue();
/*  421 */       expireAccess = ((Long)catPut(name + ".expireAccess", Long.valueOf(m.expireAccess))).longValue();
/*  422 */       expireMaxSize = ((Long)catPut(name + ".expireMaxSize", Long.valueOf(m.expireMaxSize))).longValue();
/*  423 */       expireStoreSize = ((Long)catPut(name + ".expireStoreSize", Long.valueOf(m.expireStoreSize))).longValue();
/*  424 */       expireHeads = new long[16];
/*  425 */       expireTails = new long[16];
/*  426 */       for (int i = 0; i < 16; i++) {
/*  427 */         expireHeads[i] = this.engine.put((A)Long.valueOf(0L), (Serializer)Serializer.LONG);
/*  428 */         expireTails[i] = this.engine.put((A)Long.valueOf(0L), (Serializer)Serializer.LONG);
/*      */       } 
/*  430 */       catPut(name + ".expireHeads", expireHeads);
/*  431 */       catPut(name + ".expireTails", expireTails);
/*      */     } 
/*  434 */     if (m.hasher != null)
/*  435 */       catPut(name + ".hasher", m.hasher); 
/*  439 */     HTreeMap<K, V> ret = new HTreeMap<K, V>(this.engine, ((Long)catPut(name + ".counterRecid", Long.valueOf(!m.counter ? 0L : this.engine.<Long>put(Long.valueOf(0L), Serializer.LONG)))).longValue(), ((Integer)catPut(name + ".hashSalt", Integer.valueOf((new Random()).nextInt()))).intValue(), catPut(name + ".segmentRecids", HTreeMap.preallocateSegments(this.engine)), (Serializer<K>)catPut(name + ".keySerializer", m.keySerializer, getDefaultSerializer()), (Serializer<V>)catPut(name + ".valueSerializer", m.valueSerializer, getDefaultSerializer()), expireTimeStart, expire, expireAccess, expireMaxSize, expireStoreSize, expireHeads, expireTails, (Fun.Function1)m.valueCreator, m.hasher, false);
/*  450 */     this.catalog.put(name + ".type", "HashMap");
/*  451 */     namedPut(name, ret);
/*  452 */     return ret;
/*      */   }
/*      */   
/*      */   public synchronized <K> Set<K> getHashSet(String name) {
/*  462 */     checkNotClosed();
/*  463 */     Set<K> ret = (Set<K>)getFromWeakCollection(name);
/*  464 */     if (ret != null)
/*  464 */       return ret; 
/*  465 */     String type = catGet(name + ".type", null);
/*  466 */     if (type == null) {
/*  467 */       checkShouldCreate(name);
/*  468 */       if (this.engine.isReadOnly()) {
/*  469 */         Engine e = new StoreHeap();
/*  470 */         (new DB(e)).getHashSet("a");
/*  471 */         return namedPut(name, (new DB(new EngineWrapper.ReadOnlyEngine(e))).getHashSet("a"));
/*      */       } 
/*  474 */       return createHashSet(name).makeOrGet();
/*      */     } 
/*  479 */     checkType(type, "HashSet");
/*  481 */     ret = (new HTreeMap<K, Object>(this.engine, ((Long)catGet(name + ".counterRecid")).longValue(), ((Integer)catGet(name + ".hashSalt")).intValue(), catGet(name + ".segmentRecids"), catGet(name + ".serializer", getDefaultSerializer()), null, ((Long)catGet(name + ".expireTimeStart", Long.valueOf(0L))).longValue(), ((Long)catGet(name + ".expire", Long.valueOf(0L))).longValue(), ((Long)catGet(name + ".expireAccess", Long.valueOf(0L))).longValue(), ((Long)catGet(name + ".expireMaxSize", Long.valueOf(0L))).longValue(), ((Long)catGet(name + ".expireStoreSize", Long.valueOf(0L))).longValue(), catGet(name + ".expireHeads", null), catGet(name + ".expireTails", null), null, catGet(name + ".hasher", Hasher.BASIC), false)).keySet();
/*  499 */     namedPut(name, ret);
/*  500 */     return ret;
/*      */   }
/*      */   
/*      */   public synchronized HTreeSetMaker createHashSet(String name) {
/*  509 */     return new HTreeSetMaker(name);
/*      */   }
/*      */   
/*      */   protected synchronized <K> Set<K> createHashSet(HTreeSetMaker m) {
/*  514 */     String name = m.name;
/*  515 */     checkNameNotExists(name);
/*  517 */     long expireTimeStart = 0L, expire = 0L, expireAccess = 0L, expireMaxSize = 0L, expireStoreSize = 0L;
/*  518 */     long[] expireHeads = null, expireTails = null;
/*  520 */     if (m.expire != 0L || m.expireAccess != 0L || m.expireMaxSize != 0L) {
/*  521 */       expireTimeStart = ((Long)catPut(name + ".expireTimeStart", Long.valueOf(System.currentTimeMillis()))).longValue();
/*  522 */       expire = ((Long)catPut(name + ".expire", Long.valueOf(m.expire))).longValue();
/*  523 */       expireAccess = ((Long)catPut(name + ".expireAccess", Long.valueOf(m.expireAccess))).longValue();
/*  524 */       expireMaxSize = ((Long)catPut(name + ".expireMaxSize", Long.valueOf(m.expireMaxSize))).longValue();
/*  525 */       expireStoreSize = ((Long)catPut(name + ".expireStoreSize", Long.valueOf(m.expireStoreSize))).longValue();
/*  526 */       expireHeads = new long[16];
/*  527 */       expireTails = new long[16];
/*  528 */       for (int i = 0; i < 16; i++) {
/*  529 */         expireHeads[i] = this.engine.put((A)Long.valueOf(0L), (Serializer)Serializer.LONG);
/*  530 */         expireTails[i] = this.engine.put((A)Long.valueOf(0L), (Serializer)Serializer.LONG);
/*      */       } 
/*  532 */       catPut(name + ".expireHeads", expireHeads);
/*  533 */       catPut(name + ".expireTails", expireTails);
/*      */     } 
/*  536 */     if (m.hasher != null)
/*  537 */       catPut(name + ".hasher", m.hasher); 
/*  541 */     HTreeMap<K, Object> ret = new HTreeMap<K, Object>(this.engine, ((Long)catPut(name + ".counterRecid", Long.valueOf(!m.counter ? 0L : this.engine.<Long>put(Long.valueOf(0L), Serializer.LONG)))).longValue(), ((Integer)catPut(name + ".hashSalt", Integer.valueOf((new Random()).nextInt()))).intValue(), catPut(name + ".segmentRecids", HTreeMap.preallocateSegments(this.engine)), (Serializer<K>)catPut(name + ".serializer", m.serializer, getDefaultSerializer()), null, expireTimeStart, expire, expireAccess, expireMaxSize, expireStoreSize, expireHeads, expireTails, null, m.hasher, false);
/*  551 */     Set<K> ret2 = ret.keySet();
/*  553 */     this.catalog.put(name + ".type", "HashSet");
/*  554 */     namedPut(name, ret2);
/*  555 */     return ret2;
/*      */   }
/*      */   
/*      */   public class BTreeMapMaker {
/*      */     protected final String name;
/*      */     
/*      */     protected int nodeSize;
/*      */     
/*      */     protected boolean valuesOutsideNodes;
/*      */     
/*      */     protected boolean counter;
/*      */     
/*      */     protected BTreeKeySerializer keySerializer;
/*      */     
/*      */     protected Serializer valueSerializer;
/*      */     
/*      */     protected Comparator comparator;
/*      */     
/*      */     protected Iterator pumpSource;
/*      */     
/*      */     protected Fun.Function1 pumpKeyExtractor;
/*      */     
/*      */     protected Fun.Function1 pumpValueExtractor;
/*      */     
/*      */     protected int pumpPresortBatchSize;
/*      */     
/*      */     protected boolean pumpIgnoreDuplicates;
/*      */     
/*      */     public BTreeMapMaker(String name) {
/*  567 */       this.nodeSize = 32;
/*  568 */       this.valuesOutsideNodes = false;
/*  569 */       this.counter = false;
/*  577 */       this.pumpPresortBatchSize = -1;
/*  578 */       this.pumpIgnoreDuplicates = false;
/*      */       this.name = name;
/*      */     }
/*      */     
/*      */     public BTreeMapMaker nodeSize(int nodeSize) {
/*  583 */       this.nodeSize = nodeSize;
/*  584 */       return this;
/*      */     }
/*      */     
/*      */     public BTreeMapMaker valuesOutsideNodesEnable() {
/*  589 */       this.valuesOutsideNodes = true;
/*  590 */       return this;
/*      */     }
/*      */     
/*      */     public BTreeMapMaker counterEnable() {
/*  595 */       this.counter = true;
/*  596 */       return this;
/*      */     }
/*      */     
/*      */     public BTreeMapMaker keySerializer(BTreeKeySerializer<?> keySerializer) {
/*  601 */       this.keySerializer = keySerializer;
/*  602 */       return this;
/*      */     }
/*      */     
/*      */     public BTreeMapMaker keySerializerWrap(Serializer<?> serializer) {
/*  607 */       this.keySerializer = new BTreeKeySerializer.BasicKeySerializer(serializer);
/*  608 */       return this;
/*      */     }
/*      */     
/*      */     public BTreeMapMaker valueSerializer(Serializer<?> valueSerializer) {
/*  613 */       this.valueSerializer = valueSerializer;
/*  614 */       return this;
/*      */     }
/*      */     
/*      */     public BTreeMapMaker comparator(Comparator<?> comparator) {
/*  619 */       this.comparator = comparator;
/*  620 */       return this;
/*      */     }
/*      */     
/*      */     public <K, V> BTreeMapMaker pumpSource(Iterator<K> keysSource, Fun.Function1<V, K> valueExtractor) {
/*  624 */       this.pumpSource = keysSource;
/*  625 */       this.pumpKeyExtractor = Fun.extractNoTransform();
/*  626 */       this.pumpValueExtractor = valueExtractor;
/*  627 */       return this;
/*      */     }
/*      */     
/*      */     public <K, V> BTreeMapMaker pumpSource(Iterator<Fun.Tuple2<K, V>> entriesSource) {
/*  632 */       this.pumpSource = entriesSource;
/*  633 */       this.pumpKeyExtractor = Fun.extractKey();
/*  634 */       this.pumpValueExtractor = Fun.extractValue();
/*  635 */       return this;
/*      */     }
/*      */     
/*      */     public BTreeMapMaker pumpPresort(int batchSize) {
/*  639 */       this.pumpPresortBatchSize = batchSize;
/*  640 */       return this;
/*      */     }
/*      */     
/*      */     public <K> BTreeMapMaker pumpIgnoreDuplicates() {
/*  649 */       this.pumpIgnoreDuplicates = true;
/*  650 */       return this;
/*      */     }
/*      */     
/*      */     public <K, V> BTreeMap<K, V> make() {
/*  654 */       return DB.this.createTreeMap(this);
/*      */     }
/*      */     
/*      */     public <K, V> BTreeMap<K, V> makeOrGet() {
/*  658 */       synchronized (DB.this) {
/*  660 */         return (DB.this.catGet(this.name + ".type") == null) ? make() : DB.this.<K, V>getTreeMap(this.name);
/*      */       } 
/*      */     }
/*      */     
/*      */     public <V> BTreeMap<String, V> makeStringMap() {
/*  668 */       this.keySerializer = BTreeKeySerializer.STRING;
/*  669 */       return make();
/*      */     }
/*      */     
/*      */     public <V> BTreeMap<Long, V> makeLongMap() {
/*  674 */       this.keySerializer = BTreeKeySerializer.ZERO_OR_POSITIVE_LONG;
/*  675 */       return make();
/*      */     }
/*      */   }
/*      */   
/*      */   public class BTreeSetMaker {
/*      */     protected final String name;
/*      */     
/*      */     protected int nodeSize;
/*      */     
/*      */     protected boolean counter;
/*      */     
/*      */     protected BTreeKeySerializer<?> serializer;
/*      */     
/*      */     protected Comparator<?> comparator;
/*      */     
/*      */     protected Iterator<?> pumpSource;
/*      */     
/*      */     protected int pumpPresortBatchSize;
/*      */     
/*      */     protected boolean pumpIgnoreDuplicates;
/*      */     
/*      */     public BTreeSetMaker(String name) {
/*  687 */       this.nodeSize = 32;
/*  688 */       this.counter = false;
/*  693 */       this.pumpPresortBatchSize = -1;
/*  694 */       this.pumpIgnoreDuplicates = false;
/*      */       this.name = name;
/*      */     }
/*      */     
/*      */     public BTreeSetMaker nodeSize(int nodeSize) {
/*  698 */       this.nodeSize = nodeSize;
/*  699 */       return this;
/*      */     }
/*      */     
/*      */     public BTreeSetMaker counterEnable() {
/*  705 */       this.counter = true;
/*  706 */       return this;
/*      */     }
/*      */     
/*      */     public BTreeSetMaker serializer(BTreeKeySerializer<?> serializer) {
/*  711 */       this.serializer = serializer;
/*  712 */       return this;
/*      */     }
/*      */     
/*      */     public BTreeSetMaker comparator(Comparator<?> comparator) {
/*  717 */       this.comparator = comparator;
/*  718 */       return this;
/*      */     }
/*      */     
/*      */     public BTreeSetMaker pumpSource(Iterator<?> source) {
/*  722 */       this.pumpSource = source;
/*  723 */       return this;
/*      */     }
/*      */     
/*      */     public <K> BTreeSetMaker pumpIgnoreDuplicates() {
/*  731 */       this.pumpIgnoreDuplicates = true;
/*  732 */       return this;
/*      */     }
/*      */     
/*      */     public BTreeSetMaker pumpPresort(int batchSize) {
/*  736 */       this.pumpPresortBatchSize = batchSize;
/*  737 */       return this;
/*      */     }
/*      */     
/*      */     public <K> NavigableSet<K> make() {
/*  742 */       return DB.this.createTreeSet(this);
/*      */     }
/*      */     
/*      */     public <K> NavigableSet<K> makeOrGet() {
/*  746 */       synchronized (DB.this) {
/*  748 */         return (DB.this.catGet(this.name + ".type") == null) ? make() : DB.this.<K>getTreeSet(this.name);
/*      */       } 
/*      */     }
/*      */     
/*      */     public NavigableSet<String> makeStringSet() {
/*  758 */       this.serializer = BTreeKeySerializer.STRING;
/*  759 */       return make();
/*      */     }
/*      */     
/*      */     public NavigableSet<Long> makeLongSet() {
/*  764 */       this.serializer = BTreeKeySerializer.ZERO_OR_POSITIVE_LONG;
/*  765 */       return make();
/*      */     }
/*      */   }
/*      */   
/*      */   public synchronized <K, V> BTreeMap<K, V> getTreeMap(String name) {
/*  781 */     checkNotClosed();
/*  782 */     BTreeMap<K, V> ret = (BTreeMap<K, V>)getFromWeakCollection(name);
/*  783 */     if (ret != null)
/*  783 */       return ret; 
/*  784 */     String type = catGet(name + ".type", null);
/*  785 */     if (type == null) {
/*  786 */       checkShouldCreate(name);
/*  787 */       if (this.engine.isReadOnly()) {
/*  788 */         Engine e = new StoreHeap();
/*  789 */         (new DB(e)).getTreeMap("a");
/*  790 */         return namedPut(name, (new DB(new EngineWrapper.ReadOnlyEngine(e))).getTreeMap("a"));
/*      */       } 
/*  793 */       return createTreeMap(name).make();
/*      */     } 
/*  796 */     checkType(type, "TreeMap");
/*  798 */     ret = new BTreeMap<K, V>(this.engine, ((Long)catGet(name + ".rootRecidRef")).longValue(), ((Integer)catGet(name + ".maxNodeSize", Integer.valueOf(32))).intValue(), ((Boolean)catGet(name + ".valuesOutsideNodes", Boolean.valueOf(false))).booleanValue(), ((Long)catGet(name + ".counterRecid", Long.valueOf(0L))).longValue(), catGet(name + ".keySerializer", new BTreeKeySerializer.BasicKeySerializer(getDefaultSerializer())), catGet(name + ".valueSerializer", getDefaultSerializer()), catGet(name + ".comparator", BTreeMap.COMPARABLE_COMPARATOR), ((Integer)catGet(name + ".numberOfNodeMetas", Integer.valueOf(0))).intValue(), false);
/*  809 */     namedPut(name, ret);
/*  810 */     return ret;
/*      */   }
/*      */   
/*      */   public BTreeMapMaker createTreeMap(String name) {
/*  821 */     return new BTreeMapMaker(name);
/*      */   }
/*      */   
/*      */   protected synchronized <K, V> BTreeMap<K, V> createTreeMap(final BTreeMapMaker m) {
/*      */     long rootRecidRef;
/*  826 */     String name = m.name;
/*  827 */     checkNameNotExists(name);
/*  828 */     m.keySerializer = fillNulls(m.keySerializer);
/*  829 */     m.keySerializer = catPut(name + ".keySerializer", m.keySerializer, new BTreeKeySerializer.BasicKeySerializer(getDefaultSerializer()));
/*  830 */     m.valueSerializer = catPut(name + ".valueSerializer", m.valueSerializer, getDefaultSerializer());
/*  831 */     if (m.comparator == null) {
/*  832 */       m.comparator = m.keySerializer.getComparator();
/*  833 */       if (m.comparator == null)
/*  834 */         m.comparator = BTreeMap.COMPARABLE_COMPARATOR; 
/*      */     } 
/*  838 */     m.comparator = catPut(name + ".comparator", m.comparator);
/*  840 */     if (m.pumpPresortBatchSize != -1 && m.pumpSource != null) {
/*  841 */       Comparator presortComp = new Comparator() {
/*      */           public int compare(Object o1, Object o2) {
/*  845 */             return -m.comparator.compare((T)m.pumpKeyExtractor.run((A)o1), (T)m.pumpKeyExtractor.run((A)o2));
/*      */           }
/*      */         };
/*  849 */       m.pumpSource = Pump.sort(m.pumpSource, m.pumpIgnoreDuplicates, m.pumpPresortBatchSize, presortComp, getDefaultSerializer());
/*      */     } 
/*  853 */     long counterRecid = !m.counter ? 0L : this.engine.<Long>put(Long.valueOf(0L), Serializer.LONG);
/*  856 */     if (m.pumpSource == null) {
/*  857 */       rootRecidRef = BTreeMap.createRootRef(this.engine, m.keySerializer, m.valueSerializer, m.comparator, 0);
/*      */     } else {
/*  859 */       rootRecidRef = Pump.buildTreeMap(m.pumpSource, this.engine, m.pumpKeyExtractor, m.pumpValueExtractor, m.pumpIgnoreDuplicates, m.nodeSize, m.valuesOutsideNodes, counterRecid, m.keySerializer, m.valueSerializer, m.comparator);
/*      */     } 
/*  872 */     BTreeMap<K, V> ret = new BTreeMap<K, V>(this.engine, ((Long)catPut(name + ".rootRecidRef", Long.valueOf(rootRecidRef))).longValue(), ((Integer)catPut(name + ".maxNodeSize", Integer.valueOf(m.nodeSize))).intValue(), ((Boolean)catPut(name + ".valuesOutsideNodes", Boolean.valueOf(m.valuesOutsideNodes))).booleanValue(), ((Long)catPut(name + ".counterRecid", Long.valueOf(counterRecid))).longValue(), m.keySerializer, m.valueSerializer, m.comparator, ((Integer)catPut(m.name + ".numberOfNodeMetas", Integer.valueOf(0))).intValue(), false);
/*  883 */     this.catalog.put(name + ".type", "TreeMap");
/*  884 */     namedPut(name, ret);
/*  885 */     return ret;
/*      */   }
/*      */   
/*      */   protected <K> BTreeKeySerializer<K> fillNulls(BTreeKeySerializer<K> keySerializer) {
/*  895 */     if (keySerializer == null)
/*  896 */       return null; 
/*  897 */     if (keySerializer instanceof BTreeKeySerializer.Tuple2KeySerializer) {
/*  898 */       BTreeKeySerializer.Tuple2KeySerializer<?, ?> s = (BTreeKeySerializer.Tuple2KeySerializer)keySerializer;
/*  900 */       return (BTreeKeySerializer)new BTreeKeySerializer.Tuple2KeySerializer<Object, Object>((s.aComparator != null) ? s.aComparator : BTreeMap.COMPARABLE_COMPARATOR, (s.aSerializer != null) ? s.aSerializer : getDefaultSerializer(), (s.bSerializer != null) ? s.bSerializer : getDefaultSerializer());
/*      */     } 
/*  906 */     if (keySerializer instanceof BTreeKeySerializer.Tuple3KeySerializer) {
/*  907 */       BTreeKeySerializer.Tuple3KeySerializer<?, ?, ?> s = (BTreeKeySerializer.Tuple3KeySerializer)keySerializer;
/*  909 */       return (BTreeKeySerializer)new BTreeKeySerializer.Tuple3KeySerializer<Object, Object, Object>((s.aComparator != null) ? s.aComparator : BTreeMap.COMPARABLE_COMPARATOR, (s.bComparator != null) ? s.bComparator : BTreeMap.COMPARABLE_COMPARATOR, (s.aSerializer != null) ? s.aSerializer : getDefaultSerializer(), (s.bSerializer != null) ? s.bSerializer : getDefaultSerializer(), (s.cSerializer != null) ? s.cSerializer : getDefaultSerializer());
/*      */     } 
/*  917 */     if (keySerializer instanceof BTreeKeySerializer.Tuple4KeySerializer) {
/*  918 */       BTreeKeySerializer.Tuple4KeySerializer<?, ?, ?, ?> s = (BTreeKeySerializer.Tuple4KeySerializer)keySerializer;
/*  920 */       return (BTreeKeySerializer)new BTreeKeySerializer.Tuple4KeySerializer<Object, Object, Object, Object>((s.aComparator != null) ? s.aComparator : BTreeMap.COMPARABLE_COMPARATOR, (s.bComparator != null) ? s.bComparator : BTreeMap.COMPARABLE_COMPARATOR, (s.cComparator != null) ? s.cComparator : BTreeMap.COMPARABLE_COMPARATOR, (s.aSerializer != null) ? s.aSerializer : getDefaultSerializer(), (s.bSerializer != null) ? s.bSerializer : getDefaultSerializer(), (s.cSerializer != null) ? s.cSerializer : getDefaultSerializer(), (s.dSerializer != null) ? s.dSerializer : getDefaultSerializer());
/*      */     } 
/*  931 */     if (keySerializer instanceof BTreeKeySerializer.Tuple5KeySerializer) {
/*  932 */       BTreeKeySerializer.Tuple5KeySerializer<?, ?, ?, ?, ?> s = (BTreeKeySerializer.Tuple5KeySerializer)keySerializer;
/*  934 */       return (BTreeKeySerializer)new BTreeKeySerializer.Tuple5KeySerializer<Object, Object, Object, Object, Object>((s.aComparator != null) ? s.aComparator : BTreeMap.COMPARABLE_COMPARATOR, (s.bComparator != null) ? s.bComparator : BTreeMap.COMPARABLE_COMPARATOR, (s.cComparator != null) ? s.cComparator : BTreeMap.COMPARABLE_COMPARATOR, (s.dComparator != null) ? s.dComparator : BTreeMap.COMPARABLE_COMPARATOR, (s.aSerializer != null) ? s.aSerializer : getDefaultSerializer(), (s.bSerializer != null) ? s.bSerializer : getDefaultSerializer(), (s.cSerializer != null) ? s.cSerializer : getDefaultSerializer(), (s.dSerializer != null) ? s.dSerializer : getDefaultSerializer(), (s.eSerializer != null) ? s.eSerializer : getDefaultSerializer());
/*      */     } 
/*  947 */     if (keySerializer instanceof BTreeKeySerializer.Tuple6KeySerializer) {
/*  948 */       BTreeKeySerializer.Tuple6KeySerializer<?, ?, ?, ?, ?, ?> s = (BTreeKeySerializer.Tuple6KeySerializer)keySerializer;
/*  950 */       return (BTreeKeySerializer)new BTreeKeySerializer.Tuple6KeySerializer<Object, Object, Object, Object, Object, Object>((s.aComparator != null) ? s.aComparator : BTreeMap.COMPARABLE_COMPARATOR, (s.bComparator != null) ? s.bComparator : BTreeMap.COMPARABLE_COMPARATOR, (s.cComparator != null) ? s.cComparator : BTreeMap.COMPARABLE_COMPARATOR, (s.dComparator != null) ? s.dComparator : BTreeMap.COMPARABLE_COMPARATOR, (s.eComparator != null) ? s.eComparator : BTreeMap.COMPARABLE_COMPARATOR, (s.aSerializer != null) ? s.aSerializer : getDefaultSerializer(), (s.bSerializer != null) ? s.bSerializer : getDefaultSerializer(), (s.cSerializer != null) ? s.cSerializer : getDefaultSerializer(), (s.dSerializer != null) ? s.dSerializer : getDefaultSerializer(), (s.eSerializer != null) ? s.eSerializer : getDefaultSerializer(), (s.fSerializer != null) ? s.fSerializer : getDefaultSerializer());
/*      */     } 
/*  965 */     return keySerializer;
/*      */   }
/*      */   
/*      */   public SortedMap<String, Object> getCatalog() {
/*  979 */     return this.catalog;
/*      */   }
/*      */   
/*      */   public synchronized <K> NavigableSet<K> getTreeSet(String name) {
/*  990 */     checkNotClosed();
/*  991 */     NavigableSet<K> ret = (NavigableSet<K>)getFromWeakCollection(name);
/*  992 */     if (ret != null)
/*  992 */       return ret; 
/*  993 */     String type = catGet(name + ".type", null);
/*  994 */     if (type == null) {
/*  995 */       checkShouldCreate(name);
/*  996 */       if (this.engine.isReadOnly()) {
/*  997 */         Engine e = new StoreHeap();
/*  998 */         (new DB(e)).getTreeSet("a");
/*  999 */         return namedPut(name, (new DB(new EngineWrapper.ReadOnlyEngine(e))).getTreeSet("a"));
/*      */       } 
/* 1002 */       return createTreeSet(name).make();
/*      */     } 
/* 1005 */     checkType(type, "TreeSet");
/* 1007 */     ret = (new BTreeMap<K, Object>(this.engine, ((Long)catGet(name + ".rootRecidRef")).longValue(), ((Integer)catGet(name + ".maxNodeSize", Integer.valueOf(32))).intValue(), false, ((Long)catGet(name + ".counterRecid", Long.valueOf(0L))).longValue(), catGet(name + ".keySerializer", new BTreeKeySerializer.BasicKeySerializer(getDefaultSerializer())), null, catGet(name + ".comparator", BTreeMap.COMPARABLE_COMPARATOR), ((Integer)catGet(name + ".numberOfNodeMetas", Integer.valueOf(0))).intValue(), false)).keySet();
/* 1019 */     namedPut(name, ret);
/* 1020 */     return ret;
/*      */   }
/*      */   
/*      */   public synchronized BTreeSetMaker createTreeSet(String name) {
/* 1031 */     return new BTreeSetMaker(name);
/*      */   }
/*      */   
/*      */   public synchronized <K> NavigableSet<K> createTreeSet(BTreeSetMaker m) {
/*      */     long rootRecidRef;
/* 1035 */     checkNameNotExists(m.name);
/* 1036 */     m.serializer = fillNulls(m.serializer);
/* 1037 */     m.serializer = catPut(m.name + ".keySerializer", m.serializer, new BTreeKeySerializer.BasicKeySerializer(getDefaultSerializer()));
/* 1038 */     m.comparator = catPut(m.name + ".comparator", m.comparator, BTreeMap.COMPARABLE_COMPARATOR);
/* 1040 */     if (m.pumpPresortBatchSize != -1)
/* 1041 */       m.pumpSource = Pump.sort(m.pumpSource, m.pumpIgnoreDuplicates, m.pumpPresortBatchSize, Collections.reverseOrder(m.comparator), getDefaultSerializer()); 
/* 1044 */     long counterRecid = !m.counter ? 0L : this.engine.<Long>put(Long.valueOf(0L), Serializer.LONG);
/* 1047 */     if (m.pumpSource == null) {
/* 1048 */       rootRecidRef = BTreeMap.createRootRef(this.engine, m.serializer, null, m.comparator, 0);
/*      */     } else {
/* 1050 */       rootRecidRef = Pump.buildTreeMap(m.pumpSource, this.engine, Fun.extractNoTransform(), null, m.pumpIgnoreDuplicates, m.nodeSize, false, counterRecid, m.serializer, null, m.comparator);
/*      */     } 
/* 1064 */     NavigableSet<K> ret = (new BTreeMap<K, Object>(this.engine, ((Long)catPut(m.name + ".rootRecidRef", Long.valueOf(rootRecidRef))).longValue(), ((Integer)catPut(m.name + ".maxNodeSize", Integer.valueOf(m.nodeSize))).intValue(), false, ((Long)catPut(m.name + ".counterRecid", Long.valueOf(counterRecid))).longValue(), (BTreeKeySerializer)m.serializer, null, (Comparator)m.comparator, ((Integer)catPut(m.name + ".numberOfNodeMetas", Integer.valueOf(0))).intValue(), false)).keySet();
/* 1076 */     this.catalog.put(m.name + ".type", "TreeSet");
/* 1077 */     namedPut(m.name, ret);
/* 1078 */     return ret;
/*      */   }
/*      */   
/*      */   public synchronized <E> BlockingQueue<E> getQueue(String name) {
/* 1082 */     checkNotClosed();
/* 1083 */     Queues.Queue<E> ret = (Queues.Queue<E>)getFromWeakCollection(name);
/* 1084 */     if (ret != null)
/* 1084 */       return ret; 
/* 1085 */     String type = catGet(name + ".type", null);
/* 1086 */     if (type == null) {
/* 1087 */       checkShouldCreate(name);
/* 1088 */       if (this.engine.isReadOnly()) {
/* 1089 */         Engine e = new StoreHeap();
/* 1090 */         (new DB(e)).getQueue("a");
/* 1091 */         return namedPut(name, (new DB(new EngineWrapper.ReadOnlyEngine(e))).getQueue("a"));
/*      */       } 
/* 1094 */       return createQueue(name, null, true);
/*      */     } 
/* 1096 */     checkType(type, "Queue");
/* 1098 */     ret = new Queues.Queue<E>(this.engine, catGet(name + ".serializer", getDefaultSerializer()), ((Long)catGet(name + ".headRecid")).longValue(), ((Long)catGet(name + ".tailRecid")).longValue(), ((Boolean)catGet(name + ".useLocks")).booleanValue());
/* 1105 */     namedPut(name, ret);
/* 1106 */     return ret;
/*      */   }
/*      */   
/*      */   public synchronized <E> BlockingQueue<E> createQueue(String name, Serializer<E> serializer, boolean useLocks) {
/* 1110 */     checkNameNotExists(name);
/* 1112 */     long node = this.engine.put(Queues.SimpleQueue.Node.EMPTY, new Queues.SimpleQueue.NodeSerializer(serializer));
/* 1113 */     long headRecid = this.engine.put(Long.valueOf(node), Serializer.LONG);
/* 1114 */     long tailRecid = this.engine.put(Long.valueOf(node), Serializer.LONG);
/* 1116 */     Queues.Queue<E> ret = new Queues.Queue<E>(this.engine, catPut(name + ".serializer", serializer, getDefaultSerializer()), ((Long)catPut(name + ".headRecid", Long.valueOf(headRecid))).longValue(), ((Long)catPut(name + ".tailRecid", Long.valueOf(tailRecid))).longValue(), ((Boolean)catPut(name + ".useLocks", Boolean.valueOf(useLocks))).booleanValue());
/* 1122 */     this.catalog.put(name + ".type", "Queue");
/* 1123 */     namedPut(name, ret);
/* 1124 */     return ret;
/*      */   }
/*      */   
/*      */   public synchronized <E> BlockingQueue<E> getStack(String name) {
/* 1130 */     checkNotClosed();
/* 1131 */     Queues.Stack<E> ret = (Queues.Stack<E>)getFromWeakCollection(name);
/* 1132 */     if (ret != null)
/* 1132 */       return ret; 
/* 1133 */     String type = catGet(name + ".type", null);
/* 1134 */     if (type == null) {
/* 1135 */       checkShouldCreate(name);
/* 1136 */       if (this.engine.isReadOnly()) {
/* 1137 */         Engine e = new StoreHeap();
/* 1138 */         (new DB(e)).getStack("a");
/* 1139 */         return namedPut(name, (new DB(new EngineWrapper.ReadOnlyEngine(e))).getStack("a"));
/*      */       } 
/* 1142 */       return createStack(name, null, true);
/*      */     } 
/* 1145 */     checkType(type, "Stack");
/* 1147 */     ret = new Queues.Stack<E>(this.engine, catGet(name + ".serializer", getDefaultSerializer()), ((Long)catGet(name + ".headRecid")).longValue(), ((Boolean)catGet(name + ".useLocks")).booleanValue());
/* 1153 */     namedPut(name, ret);
/* 1154 */     return ret;
/*      */   }
/*      */   
/*      */   public synchronized <E> BlockingQueue<E> createStack(String name, Serializer<E> serializer, boolean useLocks) {
/* 1160 */     checkNameNotExists(name);
/* 1162 */     long node = this.engine.put(Queues.SimpleQueue.Node.EMPTY, new Queues.SimpleQueue.NodeSerializer(serializer));
/* 1163 */     long headRecid = this.engine.put(Long.valueOf(node), Serializer.LONG);
/* 1165 */     Queues.Stack<E> ret = new Queues.Stack<E>(this.engine, catPut(name + ".serializer", serializer, getDefaultSerializer()), ((Long)catPut(name + ".headRecid", Long.valueOf(headRecid))).longValue(), ((Boolean)catPut(name + ".useLocks", Boolean.valueOf(useLocks))).booleanValue());
/* 1170 */     this.catalog.put(name + ".type", "Stack");
/* 1171 */     namedPut(name, ret);
/* 1172 */     return ret;
/*      */   }
/*      */   
/*      */   public synchronized <E> BlockingQueue<E> getCircularQueue(String name) {
/* 1177 */     checkNotClosed();
/* 1178 */     BlockingQueue<E> ret = (BlockingQueue<E>)getFromWeakCollection(name);
/* 1179 */     if (ret != null)
/* 1179 */       return ret; 
/* 1180 */     String type = catGet(name + ".type", null);
/* 1181 */     if (type == null) {
/* 1182 */       checkShouldCreate(name);
/* 1183 */       if (this.engine.isReadOnly()) {
/* 1184 */         Engine e = new StoreHeap();
/* 1185 */         (new DB(e)).getCircularQueue("a");
/* 1186 */         return namedPut(name, (new DB(new EngineWrapper.ReadOnlyEngine(e))).getCircularQueue("a"));
/*      */       } 
/* 1189 */       return createCircularQueue(name, null, 1024L);
/*      */     } 
/* 1192 */     checkType(type, "CircularQueue");
/* 1194 */     ret = new Queues.CircularQueue<E>(this.engine, catGet(name + ".serializer", getDefaultSerializer()), ((Long)catGet(name + ".headRecid")).longValue(), ((Long)catGet(name + ".headInsertRecid")).longValue(), ((Long)catGet(name + ".size")).longValue());
/* 1201 */     namedPut(name, ret);
/* 1202 */     return ret;
/*      */   }
/*      */   
/*      */   public synchronized <E> BlockingQueue<E> createCircularQueue(String name, Serializer<E> serializer, long size) {
/* 1208 */     checkNameNotExists(name);
/* 1209 */     if (serializer == null)
/* 1209 */       serializer = getDefaultSerializer(); 
/* 1213 */     long prevRecid = 0L;
/* 1214 */     long firstRecid = 0L;
/* 1215 */     Serializer<Queues.SimpleQueue.Node<E>> nodeSer = new Queues.SimpleQueue.NodeSerializer<E>(serializer);
/*      */     long i;
/* 1216 */     for (i = 0L; i < size; i++) {
/* 1217 */       Queues.SimpleQueue.Node<E> n = new Queues.SimpleQueue.Node<E>(prevRecid, null);
/* 1218 */       prevRecid = this.engine.put(n, nodeSer);
/* 1219 */       if (firstRecid == 0L)
/* 1219 */         firstRecid = prevRecid; 
/*      */     } 
/* 1222 */     this.engine.update(firstRecid, new Queues.SimpleQueue.Node<E>(prevRecid, null), nodeSer);
/* 1224 */     long headRecid = this.engine.put(Long.valueOf(prevRecid), Serializer.LONG);
/* 1225 */     long headInsertRecid = this.engine.put(Long.valueOf(prevRecid), Serializer.LONG);
/* 1229 */     Queues.CircularQueue<E> ret = new Queues.CircularQueue<E>(this.engine, catPut(name + ".serializer", serializer), ((Long)catPut(name + ".headRecid", Long.valueOf(headRecid))).longValue(), ((Long)catPut(name + ".headInsertRecid", Long.valueOf(headInsertRecid))).longValue(), ((Long)catPut(name + ".size", Long.valueOf(size))).longValue());
/* 1235 */     this.catalog.put(name + ".type", "CircularQueue");
/* 1236 */     namedPut(name, ret);
/* 1237 */     return ret;
/*      */   }
/*      */   
/*      */   public synchronized Atomic.Long createAtomicLong(String name, long initValue) {
/* 1241 */     checkNameNotExists(name);
/* 1242 */     long recid = this.engine.put(Long.valueOf(initValue), Serializer.LONG);
/* 1243 */     Atomic.Long ret = new Atomic.Long(this.engine, ((Long)catPut(name + ".recid", Long.valueOf(recid))).longValue());
/* 1246 */     this.catalog.put(name + ".type", "AtomicLong");
/* 1247 */     namedPut(name, ret);
/* 1248 */     return ret;
/*      */   }
/*      */   
/*      */   public synchronized Atomic.Long getAtomicLong(String name) {
/* 1254 */     checkNotClosed();
/* 1255 */     Atomic.Long ret = (Atomic.Long)getFromWeakCollection(name);
/* 1256 */     if (ret != null)
/* 1256 */       return ret; 
/* 1257 */     String type = catGet(name + ".type", null);
/* 1258 */     if (type == null) {
/* 1259 */       checkShouldCreate(name);
/* 1260 */       if (this.engine.isReadOnly()) {
/* 1261 */         Engine e = new StoreHeap();
/* 1262 */         (new DB(e)).getAtomicLong("a");
/* 1263 */         return namedPut(name, (new DB(new EngineWrapper.ReadOnlyEngine(e))).getAtomicLong("a"));
/*      */       } 
/* 1266 */       return createAtomicLong(name, 0L);
/*      */     } 
/* 1268 */     checkType(type, "AtomicLong");
/* 1270 */     ret = new Atomic.Long(this.engine, ((Long)catGet(name + ".recid")).longValue());
/* 1271 */     namedPut(name, ret);
/* 1272 */     return ret;
/*      */   }
/*      */   
/*      */   public synchronized Atomic.Integer createAtomicInteger(String name, int initValue) {
/* 1278 */     checkNameNotExists(name);
/* 1279 */     long recid = this.engine.put(Integer.valueOf(initValue), Serializer.INTEGER);
/* 1280 */     Atomic.Integer ret = new Atomic.Integer(this.engine, ((Long)catPut(name + ".recid", Long.valueOf(recid))).longValue());
/* 1283 */     this.catalog.put(name + ".type", "AtomicInteger");
/* 1284 */     namedPut(name, ret);
/* 1285 */     return ret;
/*      */   }
/*      */   
/*      */   public synchronized Atomic.Integer getAtomicInteger(String name) {
/* 1291 */     checkNotClosed();
/* 1292 */     Atomic.Integer ret = (Atomic.Integer)getFromWeakCollection(name);
/* 1293 */     if (ret != null)
/* 1293 */       return ret; 
/* 1294 */     String type = catGet(name + ".type", null);
/* 1295 */     if (type == null) {
/* 1296 */       checkShouldCreate(name);
/* 1297 */       if (this.engine.isReadOnly()) {
/* 1298 */         Engine e = new StoreHeap();
/* 1299 */         (new DB(e)).getAtomicInteger("a");
/* 1300 */         return namedPut(name, (new DB(new EngineWrapper.ReadOnlyEngine(e))).getAtomicInteger("a"));
/*      */       } 
/* 1303 */       return createAtomicInteger(name, 0);
/*      */     } 
/* 1305 */     checkType(type, "AtomicInteger");
/* 1307 */     ret = new Atomic.Integer(this.engine, ((Long)catGet(name + ".recid")).longValue());
/* 1308 */     namedPut(name, ret);
/* 1309 */     return ret;
/*      */   }
/*      */   
/*      */   public synchronized Atomic.Boolean createAtomicBoolean(String name, boolean initValue) {
/* 1315 */     checkNameNotExists(name);
/* 1316 */     long recid = this.engine.put(Boolean.valueOf(initValue), Serializer.BOOLEAN);
/* 1317 */     Atomic.Boolean ret = new Atomic.Boolean(this.engine, ((Long)catPut(name + ".recid", Long.valueOf(recid))).longValue());
/* 1320 */     this.catalog.put(name + ".type", "AtomicBoolean");
/* 1321 */     namedPut(name, ret);
/* 1322 */     return ret;
/*      */   }
/*      */   
/*      */   public synchronized Atomic.Boolean getAtomicBoolean(String name) {
/* 1328 */     checkNotClosed();
/* 1329 */     Atomic.Boolean ret = (Atomic.Boolean)getFromWeakCollection(name);
/* 1330 */     if (ret != null)
/* 1330 */       return ret; 
/* 1331 */     String type = catGet(name + ".type", null);
/* 1332 */     if (type == null) {
/* 1333 */       checkShouldCreate(name);
/* 1334 */       if (this.engine.isReadOnly()) {
/* 1335 */         Engine e = new StoreHeap();
/* 1336 */         (new DB(e)).getAtomicBoolean("a");
/* 1337 */         return namedPut(name, (new DB(new EngineWrapper.ReadOnlyEngine(e))).getAtomicBoolean("a"));
/*      */       } 
/* 1340 */       return createAtomicBoolean(name, false);
/*      */     } 
/* 1342 */     checkType(type, "AtomicBoolean");
/* 1344 */     ret = new Atomic.Boolean(this.engine, ((Long)catGet(name + ".recid")).longValue());
/* 1345 */     namedPut(name, ret);
/* 1346 */     return ret;
/*      */   }
/*      */   
/*      */   public void checkShouldCreate(String name) {
/* 1350 */     if (this.strictDBGet)
/* 1350 */       throw new NoSuchElementException("No record with this name was found: " + name); 
/*      */   }
/*      */   
/*      */   public synchronized Atomic.String createAtomicString(String name, String initValue) {
/* 1355 */     checkNameNotExists(name);
/* 1356 */     if (initValue == null)
/* 1356 */       throw new IllegalArgumentException("initValue may not be null"); 
/* 1357 */     long recid = this.engine.put(initValue, Serializer.STRING_NOSIZE);
/* 1358 */     Atomic.String ret = new Atomic.String(this.engine, ((Long)catPut(name + ".recid", Long.valueOf(recid))).longValue());
/* 1361 */     this.catalog.put(name + ".type", "AtomicString");
/* 1362 */     namedPut(name, ret);
/* 1363 */     return ret;
/*      */   }
/*      */   
/*      */   public synchronized Atomic.String getAtomicString(String name) {
/* 1369 */     checkNotClosed();
/* 1370 */     Atomic.String ret = (Atomic.String)getFromWeakCollection(name);
/* 1371 */     if (ret != null)
/* 1371 */       return ret; 
/* 1372 */     String type = catGet(name + ".type", null);
/* 1373 */     if (type == null) {
/* 1374 */       checkShouldCreate(name);
/* 1375 */       if (this.engine.isReadOnly()) {
/* 1376 */         Engine e = new StoreHeap();
/* 1377 */         (new DB(e)).getAtomicString("a");
/* 1378 */         return namedPut(name, (new DB(new EngineWrapper.ReadOnlyEngine(e))).getAtomicString("a"));
/*      */       } 
/* 1381 */       return createAtomicString(name, "");
/*      */     } 
/* 1383 */     checkType(type, "AtomicString");
/* 1385 */     ret = new Atomic.String(this.engine, ((Long)catGet(name + ".recid")).longValue());
/* 1386 */     namedPut(name, ret);
/* 1387 */     return ret;
/*      */   }
/*      */   
/*      */   public synchronized <E> Atomic.Var<E> createAtomicVar(String name, E initValue, Serializer<E> serializer) {
/* 1391 */     checkNameNotExists(name);
/* 1392 */     if (serializer == null)
/* 1392 */       serializer = getDefaultSerializer(); 
/* 1393 */     long recid = this.engine.put(initValue, serializer);
/* 1394 */     Atomic.Var<E> ret = new Atomic.Var(this.engine, ((Long)catPut(name + ".recid", Long.valueOf(recid))).longValue(), catPut(name + ".serializer", serializer));
/* 1398 */     this.catalog.put(name + ".type", "AtomicVar");
/* 1399 */     namedPut(name, ret);
/* 1400 */     return ret;
/*      */   }
/*      */   
/*      */   public synchronized <E> Atomic.Var<E> getAtomicVar(String name) {
/* 1406 */     checkNotClosed();
/* 1407 */     Atomic.Var<E> ret = (Atomic.Var)getFromWeakCollection(name);
/* 1408 */     if (ret != null)
/* 1408 */       return ret; 
/* 1409 */     String type = catGet(name + ".type", null);
/* 1410 */     if (type == null) {
/* 1411 */       checkShouldCreate(name);
/* 1412 */       if (this.engine.isReadOnly()) {
/* 1413 */         Engine e = new StoreHeap();
/* 1414 */         (new DB(e)).getAtomicVar("a");
/* 1415 */         return namedPut(name, (new DB(new EngineWrapper.ReadOnlyEngine(e))).getAtomicVar("a"));
/*      */       } 
/* 1418 */       return createAtomicVar(name, null, getDefaultSerializer());
/*      */     } 
/* 1420 */     checkType(type, "AtomicVar");
/* 1422 */     ret = new Atomic.Var<E>(this.engine, ((Long)catGet(name + ".recid")).longValue(), catGet(name + ".serializer"));
/* 1423 */     namedPut(name, ret);
/* 1424 */     return ret;
/*      */   }
/*      */   
/*      */   public synchronized <E> E get(String name) {
/* 1429 */     String type = catGet(name + ".type");
/* 1430 */     if (type == null)
/* 1430 */       return null; 
/* 1431 */     if ("HashMap".equals(type))
/* 1431 */       return (E)getHashMap(name); 
/* 1432 */     if ("HashSet".equals(type))
/* 1432 */       return (E)getHashSet(name); 
/* 1433 */     if ("TreeMap".equals(type))
/* 1433 */       return (E)getTreeMap(name); 
/* 1434 */     if ("TreeSet".equals(type))
/* 1434 */       return (E)getTreeSet(name); 
/* 1435 */     if ("AtomicBoolean".equals(type))
/* 1435 */       return (E)getAtomicBoolean(name); 
/* 1436 */     if ("AtomicInteger".equals(type))
/* 1436 */       return (E)getAtomicInteger(name); 
/* 1437 */     if ("AtomicLong".equals(type))
/* 1437 */       return (E)getAtomicLong(name); 
/* 1438 */     if ("AtomicString".equals(type))
/* 1438 */       return (E)getAtomicString(name); 
/* 1439 */     if ("AtomicVar".equals(type))
/* 1439 */       return (E)getAtomicVar(name); 
/* 1440 */     if ("Queue".equals(type))
/* 1440 */       return (E)getQueue(name); 
/* 1441 */     if ("Stack".equals(type))
/* 1441 */       return (E)getStack(name); 
/* 1442 */     if ("CircularQueue".equals(type))
/* 1442 */       return (E)getCircularQueue(name); 
/* 1443 */     throw new AssertionError("Unknown type: " + name);
/*      */   }
/*      */   
/*      */   public synchronized boolean exists(String name) {
/* 1447 */     return (catGet(name + ".type") != null);
/*      */   }
/*      */   
/*      */   public synchronized void delete(String name) {
/* 1452 */     Object r = get(name);
/* 1453 */     if (r instanceof Atomic.Boolean) {
/* 1454 */       this.engine.delete(((Atomic.Boolean)r).recid, Serializer.BOOLEAN);
/* 1455 */     } else if (r instanceof Atomic.Integer) {
/* 1456 */       this.engine.delete(((Atomic.Integer)r).recid, Serializer.INTEGER);
/* 1457 */     } else if (r instanceof Atomic.Long) {
/* 1458 */       this.engine.delete(((Atomic.Long)r).recid, Serializer.LONG);
/* 1459 */     } else if (r instanceof Atomic.String) {
/* 1460 */       this.engine.delete(((Atomic.String)r).recid, Serializer.STRING_NOSIZE);
/* 1461 */     } else if (r instanceof Atomic.Var) {
/* 1462 */       this.engine.delete(((Atomic.Var)r).recid, ((Atomic.Var)r).serializer);
/* 1463 */     } else if (r instanceof Queue) {
/* 1465 */       Queue q = (Queue)r;
/* 1466 */       while (q.poll() != null);
/* 1469 */     } else if (r instanceof HTreeMap || r instanceof HTreeMap.KeySet) {
/* 1470 */       HTreeMap m = (r instanceof HTreeMap) ? (HTreeMap)r : ((HTreeMap.KeySet)r).parent();
/* 1471 */       m.clear();
/* 1473 */       for (long segmentRecid : m.segmentRecids)
/* 1474 */         this.engine.delete(segmentRecid, (Serializer)HTreeMap.DIR_SERIALIZER); 
/* 1476 */     } else if (r instanceof BTreeMap || r instanceof BTreeMap.KeySet) {
/* 1477 */       BTreeMap m = (r instanceof BTreeMap) ? (BTreeMap)r : (BTreeMap)((BTreeMap.KeySet)r).m;
/* 1480 */       m.clear();
/* 1482 */       if (m.counter != null)
/* 1483 */         this.engine.delete(m.counter.recid, Serializer.LONG); 
/*      */     } 
/* 1486 */     for (String n : this.catalog.keySet()) {
/* 1487 */       if (!n.startsWith(name))
/*      */         continue; 
/* 1489 */       String suffix = n.substring(name.length());
/* 1490 */       if (suffix.charAt(0) == '.' && suffix.length() > 1 && !suffix.substring(1).contains("."))
/* 1491 */         this.catalog.remove(n); 
/*      */     } 
/* 1493 */     this.namesInstanciated.remove(name);
/* 1494 */     this.namesLookup.remove(new IdentityWrapper(r));
/*      */   }
/*      */   
/*      */   public synchronized Map<String, Object> getAll() {
/* 1502 */     TreeMap<String, Object> ret = new TreeMap<String, Object>();
/* 1504 */     for (String name : this.catalog.keySet()) {
/* 1505 */       if (!name.endsWith(".type"))
/*      */         continue; 
/* 1506 */       name = name.substring(0, name.length() - 5);
/* 1507 */       ret.put(name, get(name));
/*      */     } 
/* 1510 */     return Collections.unmodifiableMap(ret);
/*      */   }
/*      */   
/*      */   public synchronized void rename(String oldName, String newName) {
/* 1521 */     if (oldName.equals(newName))
/*      */       return; 
/* 1523 */     Map<String, Object> sub = this.catalog.tailMap(oldName);
/* 1524 */     List<String> toRemove = new ArrayList<String>();
/* 1526 */     for (String param : sub.keySet()) {
/* 1527 */       if (!param.startsWith(oldName))
/*      */         break; 
/* 1529 */       String suffix = param.substring(oldName.length());
/* 1530 */       this.catalog.put(newName + suffix, this.catalog.get(param));
/* 1531 */       toRemove.add(param);
/*      */     } 
/* 1533 */     if (toRemove.isEmpty())
/* 1533 */       throw new NoSuchElementException("Could not rename, name does not exist: " + oldName); 
/* 1535 */     WeakReference old = this.namesInstanciated.remove(oldName);
/* 1536 */     if (old != null) {
/* 1537 */       Object old2 = old.get();
/* 1538 */       if (old2 != null) {
/* 1539 */         this.namesLookup.remove(new IdentityWrapper(old2));
/* 1540 */         namedPut(newName, old2);
/*      */       } 
/*      */     } 
/* 1543 */     for (String param : toRemove)
/* 1543 */       this.catalog.remove(param); 
/*      */   }
/*      */   
/*      */   public void checkNameNotExists(String name) {
/* 1553 */     if (this.catalog.get(name + ".type") != null)
/* 1554 */       throw new IllegalArgumentException("Name already used: " + name); 
/*      */   }
/*      */   
/*      */   public synchronized void close() {
/* 1565 */     if (this.engine == null)
/*      */       return; 
/* 1566 */     this.engine.close();
/* 1568 */     this.engine = EngineWrapper.CLOSED;
/* 1569 */     this.namesInstanciated = Collections.unmodifiableMap(new HashMap<String, WeakReference<?>>());
/* 1570 */     this.namesLookup = Collections.unmodifiableMap(new HashMap<IdentityWrapper, String>());
/*      */   }
/*      */   
/*      */   public Object getFromWeakCollection(String name) {
/* 1578 */     WeakReference<?> r = this.namesInstanciated.get(name);
/* 1579 */     if (r == null)
/* 1579 */       return null; 
/* 1580 */     Object o = r.get();
/* 1581 */     if (o == null)
/* 1581 */       this.namesInstanciated.remove(name); 
/* 1582 */     return o;
/*      */   }
/*      */   
/*      */   public void checkNotClosed() {
/* 1588 */     if (this.engine == null)
/* 1588 */       throw new IllegalAccessError("DB was already closed"); 
/*      */   }
/*      */   
/*      */   public synchronized boolean isClosed() {
/* 1595 */     return (this.engine == null || this.engine.isClosed());
/*      */   }
/*      */   
/*      */   public synchronized void commit() {
/* 1604 */     checkNotClosed();
/* 1605 */     this.engine.commit();
/*      */   }
/*      */   
/*      */   public synchronized void rollback() {
/* 1614 */     checkNotClosed();
/* 1615 */     this.engine.rollback();
/*      */   }
/*      */   
/*      */   public synchronized void compact() {
/* 1626 */     this.engine.compact();
/*      */   }
/*      */   
/*      */   public synchronized DB snapshot() {
/* 1638 */     Engine snapshot = TxEngine.createSnapshotFor(this.engine);
/* 1639 */     return new DB(snapshot);
/*      */   }
/*      */   
/*      */   public Serializer getDefaultSerializer() {
/* 1646 */     return this.engine.getSerializerPojo();
/*      */   }
/*      */   
/*      */   public Engine getEngine() {
/* 1653 */     return this.engine;
/*      */   }
/*      */   
/*      */   public void checkType(String type, String expected) {
/* 1657 */     if (!expected.equals(type))
/* 1657 */       throw new IllegalArgumentException("Wrong type: " + type); 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\mapdb\DB.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */