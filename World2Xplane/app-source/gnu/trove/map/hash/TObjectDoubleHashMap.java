/*      */ package gnu.trove.map.hash;
/*      */ 
/*      */ import gnu.trove.TDoubleCollection;
/*      */ import gnu.trove.function.TDoubleFunction;
/*      */ import gnu.trove.impl.Constants;
/*      */ import gnu.trove.impl.hash.THash;
/*      */ import gnu.trove.impl.hash.TObjectHash;
/*      */ import gnu.trove.iterator.TDoubleIterator;
/*      */ import gnu.trove.iterator.TObjectDoubleIterator;
/*      */ import gnu.trove.iterator.hash.TObjectHashIterator;
/*      */ import gnu.trove.map.TObjectDoubleMap;
/*      */ import gnu.trove.procedure.TDoubleProcedure;
/*      */ import gnu.trove.procedure.TObjectDoubleProcedure;
/*      */ import gnu.trove.procedure.TObjectProcedure;
/*      */ import java.io.Externalizable;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInput;
/*      */ import java.io.ObjectOutput;
/*      */ import java.lang.reflect.Array;
/*      */ import java.util.AbstractSet;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Set;
/*      */ 
/*      */ public class TObjectDoubleHashMap<K> extends TObjectHash<K> implements TObjectDoubleMap<K>, Externalizable {
/*      */   static final long serialVersionUID = 1L;
/*      */   
/*   61 */   private final TObjectDoubleProcedure<K> PUT_ALL_PROC = new TObjectDoubleProcedure<K>() {
/*      */       public boolean execute(K key, double value) {
/*   63 */         TObjectDoubleHashMap.this.put(key, value);
/*   64 */         return true;
/*      */       }
/*      */     };
/*      */   
/*      */   protected transient double[] _values;
/*      */   
/*      */   protected double no_entry_value;
/*      */   
/*      */   public TObjectDoubleHashMap() {
/*   81 */     this.no_entry_value = Constants.DEFAULT_DOUBLE_NO_ENTRY_VALUE;
/*      */   }
/*      */   
/*      */   public TObjectDoubleHashMap(int initialCapacity) {
/*   93 */     super(initialCapacity);
/*   94 */     this.no_entry_value = Constants.DEFAULT_DOUBLE_NO_ENTRY_VALUE;
/*      */   }
/*      */   
/*      */   public TObjectDoubleHashMap(int initialCapacity, float loadFactor) {
/*  107 */     super(initialCapacity, loadFactor);
/*  108 */     this.no_entry_value = Constants.DEFAULT_DOUBLE_NO_ENTRY_VALUE;
/*      */   }
/*      */   
/*      */   public TObjectDoubleHashMap(int initialCapacity, float loadFactor, double noEntryValue) {
/*  122 */     super(initialCapacity, loadFactor);
/*  123 */     this.no_entry_value = noEntryValue;
/*  125 */     if (this.no_entry_value != 0.0D)
/*  126 */       Arrays.fill(this._values, this.no_entry_value); 
/*      */   }
/*      */   
/*      */   public TObjectDoubleHashMap(TObjectDoubleMap<? extends K> map) {
/*  138 */     this(map.size(), 0.5F, map.getNoEntryValue());
/*  139 */     if (map instanceof TObjectDoubleHashMap) {
/*  140 */       TObjectDoubleHashMap hashmap = (TObjectDoubleHashMap)map;
/*  141 */       this._loadFactor = hashmap._loadFactor;
/*  142 */       this.no_entry_value = hashmap.no_entry_value;
/*  144 */       if (this.no_entry_value != 0.0D)
/*  145 */         Arrays.fill(this._values, this.no_entry_value); 
/*  147 */       setUp((int)Math.ceil((10.0F / this._loadFactor)));
/*      */     } 
/*  149 */     putAll(map);
/*      */   }
/*      */   
/*      */   public int setUp(int initialCapacity) {
/*  163 */     int capacity = super.setUp(initialCapacity);
/*  164 */     this._values = new double[capacity];
/*  165 */     return capacity;
/*      */   }
/*      */   
/*      */   protected void rehash(int newCapacity) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield _set : [Ljava/lang/Object;
/*      */     //   4: arraylength
/*      */     //   5: istore_2
/*      */     //   6: aload_0
/*      */     //   7: getfield _set : [Ljava/lang/Object;
/*      */     //   10: checkcast [Ljava/lang/Object;
/*      */     //   13: astore_3
/*      */     //   14: aload_0
/*      */     //   15: getfield _values : [D
/*      */     //   18: astore #4
/*      */     //   20: aload_0
/*      */     //   21: iload_1
/*      */     //   22: anewarray java/lang/Object
/*      */     //   25: putfield _set : [Ljava/lang/Object;
/*      */     //   28: aload_0
/*      */     //   29: getfield _set : [Ljava/lang/Object;
/*      */     //   32: getstatic gnu/trove/map/hash/TObjectDoubleHashMap.FREE : Ljava/lang/Object;
/*      */     //   35: invokestatic fill : ([Ljava/lang/Object;Ljava/lang/Object;)V
/*      */     //   38: aload_0
/*      */     //   39: iload_1
/*      */     //   40: newarray double
/*      */     //   42: putfield _values : [D
/*      */     //   45: aload_0
/*      */     //   46: getfield _values : [D
/*      */     //   49: aload_0
/*      */     //   50: getfield no_entry_value : D
/*      */     //   53: invokestatic fill : ([DD)V
/*      */     //   56: iload_2
/*      */     //   57: istore #5
/*      */     //   59: iload #5
/*      */     //   61: iinc #5, -1
/*      */     //   64: ifle -> 146
/*      */     //   67: aload_3
/*      */     //   68: iload #5
/*      */     //   70: aaload
/*      */     //   71: getstatic gnu/trove/map/hash/TObjectDoubleHashMap.FREE : Ljava/lang/Object;
/*      */     //   74: if_acmpeq -> 59
/*      */     //   77: aload_3
/*      */     //   78: iload #5
/*      */     //   80: aaload
/*      */     //   81: getstatic gnu/trove/map/hash/TObjectDoubleHashMap.REMOVED : Ljava/lang/Object;
/*      */     //   84: if_acmpeq -> 59
/*      */     //   87: aload_3
/*      */     //   88: iload #5
/*      */     //   90: aaload
/*      */     //   91: astore #6
/*      */     //   93: aload_0
/*      */     //   94: aload #6
/*      */     //   96: invokevirtual insertKey : (Ljava/lang/Object;)I
/*      */     //   99: istore #7
/*      */     //   101: iload #7
/*      */     //   103: ifge -> 122
/*      */     //   106: aload_0
/*      */     //   107: aload_0
/*      */     //   108: getfield _set : [Ljava/lang/Object;
/*      */     //   111: iload #7
/*      */     //   113: ineg
/*      */     //   114: iconst_1
/*      */     //   115: isub
/*      */     //   116: aaload
/*      */     //   117: aload #6
/*      */     //   119: invokevirtual throwObjectContractViolation : (Ljava/lang/Object;Ljava/lang/Object;)V
/*      */     //   122: aload_0
/*      */     //   123: getfield _set : [Ljava/lang/Object;
/*      */     //   126: iload #7
/*      */     //   128: aload #6
/*      */     //   130: aastore
/*      */     //   131: aload_0
/*      */     //   132: getfield _values : [D
/*      */     //   135: iload #7
/*      */     //   137: aload #4
/*      */     //   139: iload #5
/*      */     //   141: daload
/*      */     //   142: dastore
/*      */     //   143: goto -> 59
/*      */     //   146: return
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #175	-> 0
/*      */     //   #178	-> 6
/*      */     //   #179	-> 14
/*      */     //   #181	-> 20
/*      */     //   #182	-> 28
/*      */     //   #183	-> 38
/*      */     //   #184	-> 45
/*      */     //   #186	-> 56
/*      */     //   #187	-> 67
/*      */     //   #188	-> 87
/*      */     //   #189	-> 93
/*      */     //   #190	-> 101
/*      */     //   #191	-> 106
/*      */     //   #193	-> 122
/*      */     //   #194	-> 131
/*      */     //   #195	-> 143
/*      */     //   #197	-> 146
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   93	50	6	o	Ljava/lang/Object;
/*      */     //   101	42	7	index	I
/*      */     //   59	87	5	i	I
/*      */     //   0	147	0	this	Lgnu/trove/map/hash/TObjectDoubleHashMap;
/*      */     //   0	147	1	newCapacity	I
/*      */     //   6	141	2	oldCapacity	I
/*      */     //   14	133	3	oldKeys	[Ljava/lang/Object;
/*      */     //   20	127	4	oldVals	[D
/*      */     // Local variable type table:
/*      */     //   start	length	slot	name	signature
/*      */     //   93	50	6	o	TK;
/*      */     //   0	147	0	this	Lgnu/trove/map/hash/TObjectDoubleHashMap<TK;>;
/*      */     //   14	133	3	oldKeys	[TK;
/*      */   }
/*      */   
/*      */   public double getNoEntryValue() {
/*  204 */     return this.no_entry_value;
/*      */   }
/*      */   
/*      */   public boolean containsKey(Object key) {
/*  210 */     return contains(key);
/*      */   }
/*      */   
/*      */   public boolean containsValue(double val) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield _set : [Ljava/lang/Object;
/*      */     //   4: astore_3
/*      */     //   5: aload_0
/*      */     //   6: getfield _values : [D
/*      */     //   9: astore #4
/*      */     //   11: aload #4
/*      */     //   13: arraylength
/*      */     //   14: istore #5
/*      */     //   16: iload #5
/*      */     //   18: iinc #5, -1
/*      */     //   21: ifle -> 56
/*      */     //   24: aload_3
/*      */     //   25: iload #5
/*      */     //   27: aaload
/*      */     //   28: getstatic gnu/trove/map/hash/TObjectDoubleHashMap.FREE : Ljava/lang/Object;
/*      */     //   31: if_acmpeq -> 16
/*      */     //   34: aload_3
/*      */     //   35: iload #5
/*      */     //   37: aaload
/*      */     //   38: getstatic gnu/trove/map/hash/TObjectDoubleHashMap.REMOVED : Ljava/lang/Object;
/*      */     //   41: if_acmpeq -> 16
/*      */     //   44: dload_1
/*      */     //   45: aload #4
/*      */     //   47: iload #5
/*      */     //   49: daload
/*      */     //   50: dcmpl
/*      */     //   51: ifne -> 16
/*      */     //   54: iconst_1
/*      */     //   55: ireturn
/*      */     //   56: iconst_0
/*      */     //   57: ireturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #216	-> 0
/*      */     //   #217	-> 5
/*      */     //   #219	-> 11
/*      */     //   #220	-> 24
/*      */     //   #221	-> 54
/*      */     //   #224	-> 56
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   16	40	5	i	I
/*      */     //   0	58	0	this	Lgnu/trove/map/hash/TObjectDoubleHashMap;
/*      */     //   0	58	1	val	D
/*      */     //   5	53	3	keys	[Ljava/lang/Object;
/*      */     //   11	47	4	vals	[D
/*      */     // Local variable type table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	58	0	this	Lgnu/trove/map/hash/TObjectDoubleHashMap<TK;>;
/*      */   }
/*      */   
/*      */   public double get(Object key) {
/*  230 */     int index = index(key);
/*  231 */     return (index < 0) ? this.no_entry_value : this._values[index];
/*      */   }
/*      */   
/*      */   public double put(K key, double value) {
/*  239 */     int index = insertKey(key);
/*  240 */     return doPut(value, index);
/*      */   }
/*      */   
/*      */   public double putIfAbsent(K key, double value) {
/*  246 */     int index = insertKey(key);
/*  247 */     if (index < 0)
/*  248 */       return this._values[-index - 1]; 
/*  249 */     return doPut(value, index);
/*      */   }
/*      */   
/*      */   private double doPut(double value, int index) {
/*  254 */     double previous = this.no_entry_value;
/*  255 */     boolean isNewMapping = true;
/*  256 */     if (index < 0) {
/*  257 */       index = -index - 1;
/*  258 */       previous = this._values[index];
/*  259 */       isNewMapping = false;
/*      */     } 
/*  262 */     this._values[index] = value;
/*  264 */     if (isNewMapping)
/*  265 */       postInsertHook(this.consumeFreeSlot); 
/*  267 */     return previous;
/*      */   }
/*      */   
/*      */   public double remove(Object key) {
/*  273 */     double prev = this.no_entry_value;
/*  274 */     int index = index(key);
/*  275 */     if (index >= 0) {
/*  276 */       prev = this._values[index];
/*  277 */       removeAt(index);
/*      */     } 
/*  279 */     return prev;
/*      */   }
/*      */   
/*      */   protected void removeAt(int index) {
/*  291 */     this._values[index] = this.no_entry_value;
/*  292 */     super.removeAt(index);
/*      */   }
/*      */   
/*      */   public void putAll(Map<? extends K, ? extends Double> map) {
/*  300 */     Set<? extends Map.Entry<? extends K, ? extends Double>> set = map.entrySet();
/*  301 */     for (Map.Entry<? extends K, ? extends Double> entry : set)
/*  302 */       put(entry.getKey(), ((Double)entry.getValue()).doubleValue()); 
/*      */   }
/*      */   
/*      */   public void putAll(TObjectDoubleMap<? extends K> map) {
/*  309 */     map.forEachEntry(this.PUT_ALL_PROC);
/*      */   }
/*      */   
/*      */   public void clear() {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokespecial clear : ()V
/*      */     //   4: aload_0
/*      */     //   5: getfield _set : [Ljava/lang/Object;
/*      */     //   8: iconst_0
/*      */     //   9: aload_0
/*      */     //   10: getfield _set : [Ljava/lang/Object;
/*      */     //   13: arraylength
/*      */     //   14: getstatic gnu/trove/map/hash/TObjectDoubleHashMap.FREE : Ljava/lang/Object;
/*      */     //   17: invokestatic fill : ([Ljava/lang/Object;IILjava/lang/Object;)V
/*      */     //   20: aload_0
/*      */     //   21: getfield _values : [D
/*      */     //   24: iconst_0
/*      */     //   25: aload_0
/*      */     //   26: getfield _values : [D
/*      */     //   29: arraylength
/*      */     //   30: aload_0
/*      */     //   31: getfield no_entry_value : D
/*      */     //   34: invokestatic fill : ([DIID)V
/*      */     //   37: return
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #315	-> 0
/*      */     //   #316	-> 4
/*      */     //   #317	-> 20
/*      */     //   #318	-> 37
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	38	0	this	Lgnu/trove/map/hash/TObjectDoubleHashMap;
/*      */     // Local variable type table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	38	0	this	Lgnu/trove/map/hash/TObjectDoubleHashMap<TK;>;
/*      */   }
/*      */   
/*      */   public Set<K> keySet() {
/*  325 */     return new KeyView();
/*      */   }
/*      */   
/*      */   public Object[] keys() {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokevirtual size : ()I
/*      */     //   4: anewarray java/lang/Object
/*      */     //   7: checkcast [Ljava/lang/Object;
/*      */     //   10: astore_1
/*      */     //   11: aload_0
/*      */     //   12: getfield _set : [Ljava/lang/Object;
/*      */     //   15: astore_2
/*      */     //   16: aload_2
/*      */     //   17: arraylength
/*      */     //   18: istore_3
/*      */     //   19: iconst_0
/*      */     //   20: istore #4
/*      */     //   22: iload_3
/*      */     //   23: iinc #3, -1
/*      */     //   26: ifle -> 60
/*      */     //   29: aload_2
/*      */     //   30: iload_3
/*      */     //   31: aaload
/*      */     //   32: getstatic gnu/trove/map/hash/TObjectDoubleHashMap.FREE : Ljava/lang/Object;
/*      */     //   35: if_acmpeq -> 22
/*      */     //   38: aload_2
/*      */     //   39: iload_3
/*      */     //   40: aaload
/*      */     //   41: getstatic gnu/trove/map/hash/TObjectDoubleHashMap.REMOVED : Ljava/lang/Object;
/*      */     //   44: if_acmpeq -> 22
/*      */     //   47: aload_1
/*      */     //   48: iload #4
/*      */     //   50: iinc #4, 1
/*      */     //   53: aload_2
/*      */     //   54: iload_3
/*      */     //   55: aaload
/*      */     //   56: aastore
/*      */     //   57: goto -> 22
/*      */     //   60: aload_1
/*      */     //   61: areturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #332	-> 0
/*      */     //   #333	-> 11
/*      */     //   #335	-> 16
/*      */     //   #336	-> 29
/*      */     //   #338	-> 47
/*      */     //   #341	-> 60
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   19	41	3	i	I
/*      */     //   22	38	4	j	I
/*      */     //   0	62	0	this	Lgnu/trove/map/hash/TObjectDoubleHashMap;
/*      */     //   11	51	1	keys	[Ljava/lang/Object;
/*      */     //   16	46	2	k	[Ljava/lang/Object;
/*      */     // Local variable type table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	62	0	this	Lgnu/trove/map/hash/TObjectDoubleHashMap<TK;>;
/*      */     //   11	51	1	keys	[TK;
/*      */   }
/*      */   
/*      */   public K[] keys(K[] a) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokevirtual size : ()I
/*      */     //   4: istore_2
/*      */     //   5: aload_1
/*      */     //   6: arraylength
/*      */     //   7: iload_2
/*      */     //   8: if_icmpge -> 29
/*      */     //   11: aload_1
/*      */     //   12: invokevirtual getClass : ()Ljava/lang/Class;
/*      */     //   15: invokevirtual getComponentType : ()Ljava/lang/Class;
/*      */     //   18: iload_2
/*      */     //   19: invokestatic newInstance : (Ljava/lang/Class;I)Ljava/lang/Object;
/*      */     //   22: checkcast [Ljava/lang/Object;
/*      */     //   25: checkcast [Ljava/lang/Object;
/*      */     //   28: astore_1
/*      */     //   29: aload_0
/*      */     //   30: getfield _set : [Ljava/lang/Object;
/*      */     //   33: astore_3
/*      */     //   34: aload_3
/*      */     //   35: arraylength
/*      */     //   36: istore #4
/*      */     //   38: iconst_0
/*      */     //   39: istore #5
/*      */     //   41: iload #4
/*      */     //   43: iinc #4, -1
/*      */     //   46: ifle -> 83
/*      */     //   49: aload_3
/*      */     //   50: iload #4
/*      */     //   52: aaload
/*      */     //   53: getstatic gnu/trove/map/hash/TObjectDoubleHashMap.FREE : Ljava/lang/Object;
/*      */     //   56: if_acmpeq -> 41
/*      */     //   59: aload_3
/*      */     //   60: iload #4
/*      */     //   62: aaload
/*      */     //   63: getstatic gnu/trove/map/hash/TObjectDoubleHashMap.REMOVED : Ljava/lang/Object;
/*      */     //   66: if_acmpeq -> 41
/*      */     //   69: aload_1
/*      */     //   70: iload #5
/*      */     //   72: iinc #5, 1
/*      */     //   75: aload_3
/*      */     //   76: iload #4
/*      */     //   78: aaload
/*      */     //   79: aastore
/*      */     //   80: goto -> 41
/*      */     //   83: aload_1
/*      */     //   84: areturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #347	-> 0
/*      */     //   #348	-> 5
/*      */     //   #350	-> 11
/*      */     //   #354	-> 29
/*      */     //   #356	-> 34
/*      */     //   #357	-> 49
/*      */     //   #359	-> 69
/*      */     //   #362	-> 83
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   38	45	4	i	I
/*      */     //   41	42	5	j	I
/*      */     //   0	85	0	this	Lgnu/trove/map/hash/TObjectDoubleHashMap;
/*      */     //   0	85	1	a	[Ljava/lang/Object;
/*      */     //   5	80	2	size	I
/*      */     //   34	51	3	k	[Ljava/lang/Object;
/*      */     // Local variable type table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	85	0	this	Lgnu/trove/map/hash/TObjectDoubleHashMap<TK;>;
/*      */     //   0	85	1	a	[TK;
/*      */   }
/*      */   
/*      */   public TDoubleCollection valueCollection() {
/*  368 */     return new TDoubleValueCollection();
/*      */   }
/*      */   
/*      */   public double[] values() {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokevirtual size : ()I
/*      */     //   4: newarray double
/*      */     //   6: astore_1
/*      */     //   7: aload_0
/*      */     //   8: getfield _values : [D
/*      */     //   11: astore_2
/*      */     //   12: aload_0
/*      */     //   13: getfield _set : [Ljava/lang/Object;
/*      */     //   16: astore_3
/*      */     //   17: aload_2
/*      */     //   18: arraylength
/*      */     //   19: istore #4
/*      */     //   21: iconst_0
/*      */     //   22: istore #5
/*      */     //   24: iload #4
/*      */     //   26: iinc #4, -1
/*      */     //   29: ifle -> 66
/*      */     //   32: aload_3
/*      */     //   33: iload #4
/*      */     //   35: aaload
/*      */     //   36: getstatic gnu/trove/map/hash/TObjectDoubleHashMap.FREE : Ljava/lang/Object;
/*      */     //   39: if_acmpeq -> 24
/*      */     //   42: aload_3
/*      */     //   43: iload #4
/*      */     //   45: aaload
/*      */     //   46: getstatic gnu/trove/map/hash/TObjectDoubleHashMap.REMOVED : Ljava/lang/Object;
/*      */     //   49: if_acmpeq -> 24
/*      */     //   52: aload_1
/*      */     //   53: iload #5
/*      */     //   55: iinc #5, 1
/*      */     //   58: aload_2
/*      */     //   59: iload #4
/*      */     //   61: daload
/*      */     //   62: dastore
/*      */     //   63: goto -> 24
/*      */     //   66: aload_1
/*      */     //   67: areturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #374	-> 0
/*      */     //   #375	-> 7
/*      */     //   #376	-> 12
/*      */     //   #378	-> 17
/*      */     //   #379	-> 32
/*      */     //   #380	-> 52
/*      */     //   #383	-> 66
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   21	45	4	i	I
/*      */     //   24	42	5	j	I
/*      */     //   0	68	0	this	Lgnu/trove/map/hash/TObjectDoubleHashMap;
/*      */     //   7	61	1	vals	[D
/*      */     //   12	56	2	v	[D
/*      */     //   17	51	3	keys	[Ljava/lang/Object;
/*      */     // Local variable type table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	68	0	this	Lgnu/trove/map/hash/TObjectDoubleHashMap<TK;>;
/*      */   }
/*      */   
/*      */   public double[] values(double[] array) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokevirtual size : ()I
/*      */     //   4: istore_2
/*      */     //   5: aload_1
/*      */     //   6: arraylength
/*      */     //   7: iload_2
/*      */     //   8: if_icmpge -> 15
/*      */     //   11: iload_2
/*      */     //   12: newarray double
/*      */     //   14: astore_1
/*      */     //   15: aload_0
/*      */     //   16: getfield _values : [D
/*      */     //   19: astore_3
/*      */     //   20: aload_0
/*      */     //   21: getfield _set : [Ljava/lang/Object;
/*      */     //   24: astore #4
/*      */     //   26: aload_3
/*      */     //   27: arraylength
/*      */     //   28: istore #5
/*      */     //   30: iconst_0
/*      */     //   31: istore #6
/*      */     //   33: iload #5
/*      */     //   35: iinc #5, -1
/*      */     //   38: ifle -> 77
/*      */     //   41: aload #4
/*      */     //   43: iload #5
/*      */     //   45: aaload
/*      */     //   46: getstatic gnu/trove/map/hash/TObjectDoubleHashMap.FREE : Ljava/lang/Object;
/*      */     //   49: if_acmpeq -> 33
/*      */     //   52: aload #4
/*      */     //   54: iload #5
/*      */     //   56: aaload
/*      */     //   57: getstatic gnu/trove/map/hash/TObjectDoubleHashMap.REMOVED : Ljava/lang/Object;
/*      */     //   60: if_acmpeq -> 33
/*      */     //   63: aload_1
/*      */     //   64: iload #6
/*      */     //   66: iinc #6, 1
/*      */     //   69: aload_3
/*      */     //   70: iload #5
/*      */     //   72: daload
/*      */     //   73: dastore
/*      */     //   74: goto -> 33
/*      */     //   77: aload_1
/*      */     //   78: arraylength
/*      */     //   79: iload_2
/*      */     //   80: if_icmple -> 90
/*      */     //   83: aload_1
/*      */     //   84: iload_2
/*      */     //   85: aload_0
/*      */     //   86: getfield no_entry_value : D
/*      */     //   89: dastore
/*      */     //   90: aload_1
/*      */     //   91: areturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #389	-> 0
/*      */     //   #390	-> 5
/*      */     //   #391	-> 11
/*      */     //   #394	-> 15
/*      */     //   #395	-> 20
/*      */     //   #397	-> 26
/*      */     //   #398	-> 41
/*      */     //   #399	-> 63
/*      */     //   #402	-> 77
/*      */     //   #403	-> 83
/*      */     //   #405	-> 90
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   30	47	5	i	I
/*      */     //   33	44	6	j	I
/*      */     //   0	92	0	this	Lgnu/trove/map/hash/TObjectDoubleHashMap;
/*      */     //   0	92	1	array	[D
/*      */     //   5	87	2	size	I
/*      */     //   20	72	3	v	[D
/*      */     //   26	66	4	keys	[Ljava/lang/Object;
/*      */     // Local variable type table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	92	0	this	Lgnu/trove/map/hash/TObjectDoubleHashMap<TK;>;
/*      */   }
/*      */   
/*      */   public TObjectDoubleIterator<K> iterator() {
/*  413 */     return new TObjectDoubleHashIterator<K>(this);
/*      */   }
/*      */   
/*      */   public boolean increment(K key) {
/*  421 */     return adjustValue(key, 1.0D);
/*      */   }
/*      */   
/*      */   public boolean adjustValue(K key, double amount) {
/*  427 */     int index = index(key);
/*  428 */     if (index < 0)
/*  429 */       return false; 
/*  431 */     this._values[index] = this._values[index] + amount;
/*  432 */     return true;
/*      */   }
/*      */   
/*      */   public double adjustOrPutValue(K key, double adjust_amount, double put_amount) {
/*  441 */     int index = insertKey(key);
/*  445 */     index = -index - 1;
/*  446 */     double newValue = this._values[index] = this._values[index] + adjust_amount;
/*  447 */     boolean isNewMapping = false;
/*  449 */     newValue = this._values[index] = put_amount;
/*  450 */     isNewMapping = true;
/*  455 */     if (isNewMapping)
/*  456 */       postInsertHook(this.consumeFreeSlot); 
/*  459 */     return newValue;
/*      */   }
/*      */   
/*      */   public boolean forEachKey(TObjectProcedure<? super K> procedure) {
/*  471 */     return forEach(procedure);
/*      */   }
/*      */   
/*      */   public boolean forEachValue(TDoubleProcedure procedure) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield _set : [Ljava/lang/Object;
/*      */     //   4: astore_2
/*      */     //   5: aload_0
/*      */     //   6: getfield _values : [D
/*      */     //   9: astore_3
/*      */     //   10: aload_3
/*      */     //   11: arraylength
/*      */     //   12: istore #4
/*      */     //   14: iload #4
/*      */     //   16: iinc #4, -1
/*      */     //   19: ifle -> 57
/*      */     //   22: aload_2
/*      */     //   23: iload #4
/*      */     //   25: aaload
/*      */     //   26: getstatic gnu/trove/map/hash/TObjectDoubleHashMap.FREE : Ljava/lang/Object;
/*      */     //   29: if_acmpeq -> 14
/*      */     //   32: aload_2
/*      */     //   33: iload #4
/*      */     //   35: aaload
/*      */     //   36: getstatic gnu/trove/map/hash/TObjectDoubleHashMap.REMOVED : Ljava/lang/Object;
/*      */     //   39: if_acmpeq -> 14
/*      */     //   42: aload_1
/*      */     //   43: aload_3
/*      */     //   44: iload #4
/*      */     //   46: daload
/*      */     //   47: invokeinterface execute : (D)Z
/*      */     //   52: ifne -> 14
/*      */     //   55: iconst_0
/*      */     //   56: ireturn
/*      */     //   57: iconst_1
/*      */     //   58: ireturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #483	-> 0
/*      */     //   #484	-> 5
/*      */     //   #485	-> 10
/*      */     //   #486	-> 22
/*      */     //   #488	-> 55
/*      */     //   #491	-> 57
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   14	43	4	i	I
/*      */     //   0	59	0	this	Lgnu/trove/map/hash/TObjectDoubleHashMap;
/*      */     //   0	59	1	procedure	Lgnu/trove/procedure/TDoubleProcedure;
/*      */     //   5	54	2	keys	[Ljava/lang/Object;
/*      */     //   10	49	3	values	[D
/*      */     // Local variable type table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	59	0	this	Lgnu/trove/map/hash/TObjectDoubleHashMap<TK;>;
/*      */   }
/*      */   
/*      */   public boolean forEachEntry(TObjectDoubleProcedure<? super K> procedure) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield _set : [Ljava/lang/Object;
/*      */     //   4: astore_2
/*      */     //   5: aload_0
/*      */     //   6: getfield _values : [D
/*      */     //   9: astore_3
/*      */     //   10: aload_2
/*      */     //   11: arraylength
/*      */     //   12: istore #4
/*      */     //   14: iload #4
/*      */     //   16: iinc #4, -1
/*      */     //   19: ifle -> 61
/*      */     //   22: aload_2
/*      */     //   23: iload #4
/*      */     //   25: aaload
/*      */     //   26: getstatic gnu/trove/map/hash/TObjectDoubleHashMap.FREE : Ljava/lang/Object;
/*      */     //   29: if_acmpeq -> 14
/*      */     //   32: aload_2
/*      */     //   33: iload #4
/*      */     //   35: aaload
/*      */     //   36: getstatic gnu/trove/map/hash/TObjectDoubleHashMap.REMOVED : Ljava/lang/Object;
/*      */     //   39: if_acmpeq -> 14
/*      */     //   42: aload_1
/*      */     //   43: aload_2
/*      */     //   44: iload #4
/*      */     //   46: aaload
/*      */     //   47: aload_3
/*      */     //   48: iload #4
/*      */     //   50: daload
/*      */     //   51: invokeinterface execute : (Ljava/lang/Object;D)Z
/*      */     //   56: ifne -> 14
/*      */     //   59: iconst_0
/*      */     //   60: ireturn
/*      */     //   61: iconst_1
/*      */     //   62: ireturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #505	-> 0
/*      */     //   #506	-> 5
/*      */     //   #507	-> 10
/*      */     //   #508	-> 22
/*      */     //   #511	-> 59
/*      */     //   #514	-> 61
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   14	47	4	i	I
/*      */     //   0	63	0	this	Lgnu/trove/map/hash/TObjectDoubleHashMap;
/*      */     //   0	63	1	procedure	Lgnu/trove/procedure/TObjectDoubleProcedure;
/*      */     //   5	58	2	keys	[Ljava/lang/Object;
/*      */     //   10	53	3	values	[D
/*      */     // Local variable type table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	63	0	this	Lgnu/trove/map/hash/TObjectDoubleHashMap<TK;>;
/*      */     //   0	63	1	procedure	Lgnu/trove/procedure/TObjectDoubleProcedure<-TK;>;
/*      */   }
/*      */   
/*      */   public boolean retainEntries(TObjectDoubleProcedure<? super K> procedure) {
/*      */     // Byte code:
/*      */     //   0: iconst_0
/*      */     //   1: istore_2
/*      */     //   2: aload_0
/*      */     //   3: getfield _set : [Ljava/lang/Object;
/*      */     //   6: checkcast [Ljava/lang/Object;
/*      */     //   9: astore_3
/*      */     //   10: aload_0
/*      */     //   11: getfield _values : [D
/*      */     //   14: astore #4
/*      */     //   16: aload_0
/*      */     //   17: invokevirtual tempDisableAutoCompaction : ()V
/*      */     //   20: aload_3
/*      */     //   21: arraylength
/*      */     //   22: istore #5
/*      */     //   24: iload #5
/*      */     //   26: iinc #5, -1
/*      */     //   29: ifle -> 81
/*      */     //   32: aload_3
/*      */     //   33: iload #5
/*      */     //   35: aaload
/*      */     //   36: getstatic gnu/trove/map/hash/TObjectDoubleHashMap.FREE : Ljava/lang/Object;
/*      */     //   39: if_acmpeq -> 24
/*      */     //   42: aload_3
/*      */     //   43: iload #5
/*      */     //   45: aaload
/*      */     //   46: getstatic gnu/trove/map/hash/TObjectDoubleHashMap.REMOVED : Ljava/lang/Object;
/*      */     //   49: if_acmpeq -> 24
/*      */     //   52: aload_1
/*      */     //   53: aload_3
/*      */     //   54: iload #5
/*      */     //   56: aaload
/*      */     //   57: aload #4
/*      */     //   59: iload #5
/*      */     //   61: daload
/*      */     //   62: invokeinterface execute : (Ljava/lang/Object;D)Z
/*      */     //   67: ifne -> 24
/*      */     //   70: aload_0
/*      */     //   71: iload #5
/*      */     //   73: invokevirtual removeAt : (I)V
/*      */     //   76: iconst_1
/*      */     //   77: istore_2
/*      */     //   78: goto -> 24
/*      */     //   81: aload_0
/*      */     //   82: iconst_1
/*      */     //   83: invokevirtual reenableAutoCompaction : (Z)V
/*      */     //   86: goto -> 99
/*      */     //   89: astore #6
/*      */     //   91: aload_0
/*      */     //   92: iconst_1
/*      */     //   93: invokevirtual reenableAutoCompaction : (Z)V
/*      */     //   96: aload #6
/*      */     //   98: athrow
/*      */     //   99: iload_2
/*      */     //   100: ireturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #526	-> 0
/*      */     //   #528	-> 2
/*      */     //   #529	-> 10
/*      */     //   #532	-> 16
/*      */     //   #534	-> 20
/*      */     //   #535	-> 32
/*      */     //   #538	-> 70
/*      */     //   #539	-> 76
/*      */     //   #544	-> 81
/*      */     //   #545	-> 86
/*      */     //   #544	-> 89
/*      */     //   #547	-> 99
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   24	57	5	i	I
/*      */     //   0	101	0	this	Lgnu/trove/map/hash/TObjectDoubleHashMap;
/*      */     //   0	101	1	procedure	Lgnu/trove/procedure/TObjectDoubleProcedure;
/*      */     //   2	99	2	modified	Z
/*      */     //   10	91	3	keys	[Ljava/lang/Object;
/*      */     //   16	85	4	values	[D
/*      */     // Local variable type table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	101	0	this	Lgnu/trove/map/hash/TObjectDoubleHashMap<TK;>;
/*      */     //   0	101	1	procedure	Lgnu/trove/procedure/TObjectDoubleProcedure<-TK;>;
/*      */     //   10	91	3	keys	[TK;
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   20	81	89	finally
/*      */     //   89	91	89	finally
/*      */   }
/*      */   
/*      */   public void transformValues(TDoubleFunction function) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield _set : [Ljava/lang/Object;
/*      */     //   4: astore_2
/*      */     //   5: aload_0
/*      */     //   6: getfield _values : [D
/*      */     //   9: astore_3
/*      */     //   10: aload_3
/*      */     //   11: arraylength
/*      */     //   12: istore #4
/*      */     //   14: iload #4
/*      */     //   16: iinc #4, -1
/*      */     //   19: ifle -> 56
/*      */     //   22: aload_2
/*      */     //   23: iload #4
/*      */     //   25: aaload
/*      */     //   26: ifnull -> 14
/*      */     //   29: aload_2
/*      */     //   30: iload #4
/*      */     //   32: aaload
/*      */     //   33: getstatic gnu/trove/map/hash/TObjectDoubleHashMap.REMOVED : Ljava/lang/Object;
/*      */     //   36: if_acmpeq -> 14
/*      */     //   39: aload_3
/*      */     //   40: iload #4
/*      */     //   42: aload_1
/*      */     //   43: aload_3
/*      */     //   44: iload #4
/*      */     //   46: daload
/*      */     //   47: invokeinterface execute : (D)D
/*      */     //   52: dastore
/*      */     //   53: goto -> 14
/*      */     //   56: return
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #557	-> 0
/*      */     //   #558	-> 5
/*      */     //   #559	-> 10
/*      */     //   #560	-> 22
/*      */     //   #561	-> 39
/*      */     //   #564	-> 56
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   14	42	4	i	I
/*      */     //   0	57	0	this	Lgnu/trove/map/hash/TObjectDoubleHashMap;
/*      */     //   0	57	1	function	Lgnu/trove/function/TDoubleFunction;
/*      */     //   5	52	2	keys	[Ljava/lang/Object;
/*      */     //   10	47	3	values	[D
/*      */     // Local variable type table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	57	0	this	Lgnu/trove/map/hash/TObjectDoubleHashMap<TK;>;
/*      */   }
/*      */   
/*      */   public boolean equals(Object other) {
/*  577 */     if (!(other instanceof TObjectDoubleMap))
/*  578 */       return false; 
/*  580 */     TObjectDoubleMap that = (TObjectDoubleMap)other;
/*  581 */     if (that.size() != size())
/*  582 */       return false; 
/*      */     try {
/*  585 */       TObjectDoubleIterator<K> iter = iterator();
/*  586 */       while (iter.hasNext()) {
/*  587 */         iter.advance();
/*  588 */         Object key = iter.key();
/*  589 */         double value = iter.value();
/*  590 */         if (value == this.no_entry_value) {
/*  591 */           if (that.get(key) != that.getNoEntryValue() || !that.containsKey(key))
/*  594 */             return false; 
/*      */           continue;
/*      */         } 
/*  597 */         if (value != that.get(key))
/*  598 */           return false; 
/*      */       } 
/*  602 */     } catch (ClassCastException ex) {}
/*  605 */     return true;
/*      */   }
/*      */   
/*      */   public int hashCode() {
/*      */     // Byte code:
/*      */     //   0: iconst_0
/*      */     //   1: istore_1
/*      */     //   2: aload_0
/*      */     //   3: getfield _set : [Ljava/lang/Object;
/*      */     //   6: astore_2
/*      */     //   7: aload_0
/*      */     //   8: getfield _values : [D
/*      */     //   11: astore_3
/*      */     //   12: aload_3
/*      */     //   13: arraylength
/*      */     //   14: istore #4
/*      */     //   16: iload #4
/*      */     //   18: iinc #4, -1
/*      */     //   21: ifle -> 76
/*      */     //   24: aload_2
/*      */     //   25: iload #4
/*      */     //   27: aaload
/*      */     //   28: getstatic gnu/trove/map/hash/TObjectDoubleHashMap.FREE : Ljava/lang/Object;
/*      */     //   31: if_acmpeq -> 16
/*      */     //   34: aload_2
/*      */     //   35: iload #4
/*      */     //   37: aaload
/*      */     //   38: getstatic gnu/trove/map/hash/TObjectDoubleHashMap.REMOVED : Ljava/lang/Object;
/*      */     //   41: if_acmpeq -> 16
/*      */     //   44: iload_1
/*      */     //   45: aload_3
/*      */     //   46: iload #4
/*      */     //   48: daload
/*      */     //   49: invokestatic hash : (D)I
/*      */     //   52: aload_2
/*      */     //   53: iload #4
/*      */     //   55: aaload
/*      */     //   56: ifnonnull -> 63
/*      */     //   59: iconst_0
/*      */     //   60: goto -> 70
/*      */     //   63: aload_2
/*      */     //   64: iload #4
/*      */     //   66: aaload
/*      */     //   67: invokevirtual hashCode : ()I
/*      */     //   70: ixor
/*      */     //   71: iadd
/*      */     //   72: istore_1
/*      */     //   73: goto -> 16
/*      */     //   76: iload_1
/*      */     //   77: ireturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #611	-> 0
/*      */     //   #612	-> 2
/*      */     //   #613	-> 7
/*      */     //   #614	-> 12
/*      */     //   #615	-> 24
/*      */     //   #616	-> 44
/*      */     //   #620	-> 76
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   16	60	4	i	I
/*      */     //   0	78	0	this	Lgnu/trove/map/hash/TObjectDoubleHashMap;
/*      */     //   2	76	1	hashcode	I
/*      */     //   7	71	2	keys	[Ljava/lang/Object;
/*      */     //   12	66	3	values	[D
/*      */     // Local variable type table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	78	0	this	Lgnu/trove/map/hash/TObjectDoubleHashMap<TK;>;
/*      */   }
/*      */   
/*      */   protected class KeyView extends MapBackedView<K> {
/*      */     public Iterator<K> iterator() {
/*  629 */       return (Iterator<K>)new TObjectHashIterator(TObjectDoubleHashMap.this);
/*      */     }
/*      */     
/*      */     public boolean removeElement(K key) {
/*  633 */       return (TObjectDoubleHashMap.this.no_entry_value != TObjectDoubleHashMap.this.remove(key));
/*      */     }
/*      */     
/*      */     public boolean containsElement(K key) {
/*  637 */       return TObjectDoubleHashMap.this.contains(key);
/*      */     }
/*      */   }
/*      */   
/*      */   private abstract class MapBackedView<E> extends AbstractSet<E> implements Set<E>, Iterable<E> {
/*      */     private MapBackedView() {}
/*      */     
/*      */     public boolean contains(Object key) {
/*  651 */       return containsElement((E)key);
/*      */     }
/*      */     
/*      */     public boolean remove(Object o) {
/*  656 */       return removeElement((E)o);
/*      */     }
/*      */     
/*      */     public void clear() {
/*  660 */       TObjectDoubleHashMap.this.clear();
/*      */     }
/*      */     
/*      */     public boolean add(E obj) {
/*  664 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public int size() {
/*  668 */       return TObjectDoubleHashMap.this.size();
/*      */     }
/*      */     
/*      */     public Object[] toArray() {
/*  672 */       Object[] result = new Object[size()];
/*  673 */       Iterator<E> e = iterator();
/*  674 */       for (int i = 0; e.hasNext(); i++)
/*  675 */         result[i] = e.next(); 
/*  677 */       return result;
/*      */     }
/*      */     
/*      */     public <T> T[] toArray(T[] a) {
/*  681 */       int size = size();
/*  682 */       if (a.length < size)
/*  684 */         a = (T[])Array.newInstance(a.getClass().getComponentType(), size); 
/*  688 */       Iterator<E> it = iterator();
/*  689 */       T[] arrayOfT = a;
/*  690 */       for (int i = 0; i < size; i++)
/*  691 */         arrayOfT[i] = (T)it.next(); 
/*  694 */       if (a.length > size)
/*  695 */         a[size] = null; 
/*  698 */       return a;
/*      */     }
/*      */     
/*      */     public boolean isEmpty() {
/*  702 */       return TObjectDoubleHashMap.this.isEmpty();
/*      */     }
/*      */     
/*      */     public boolean addAll(Collection<? extends E> collection) {
/*  706 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public boolean retainAll(Collection<?> collection) {
/*  711 */       boolean changed = false;
/*  712 */       Iterator<E> i = iterator();
/*  713 */       while (i.hasNext()) {
/*  714 */         if (!collection.contains(i.next())) {
/*  715 */           i.remove();
/*  716 */           changed = true;
/*      */         } 
/*      */       } 
/*  719 */       return changed;
/*      */     }
/*      */     
/*      */     public abstract boolean removeElement(E param1E);
/*      */     
/*      */     public abstract boolean containsElement(E param1E);
/*      */   }
/*      */   
/*      */   class TDoubleValueCollection implements TDoubleCollection {
/*      */     public TDoubleIterator iterator() {
/*  728 */       return new TObjectDoubleValueHashIterator();
/*      */     }
/*      */     
/*      */     public double getNoEntryValue() {
/*  733 */       return TObjectDoubleHashMap.this.no_entry_value;
/*      */     }
/*      */     
/*      */     public int size() {
/*  738 */       return TObjectDoubleHashMap.this._size;
/*      */     }
/*      */     
/*      */     public boolean isEmpty() {
/*  743 */       return (0 == TObjectDoubleHashMap.this._size);
/*      */     }
/*      */     
/*      */     public boolean contains(double entry) {
/*  748 */       return TObjectDoubleHashMap.this.containsValue(entry);
/*      */     }
/*      */     
/*      */     public double[] toArray() {
/*  753 */       return TObjectDoubleHashMap.this.values();
/*      */     }
/*      */     
/*      */     public double[] toArray(double[] dest) {
/*  758 */       return TObjectDoubleHashMap.this.values(dest);
/*      */     }
/*      */     
/*      */     public boolean add(double entry) {
/*  762 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public boolean remove(double entry) {
/*  767 */       double[] values = TObjectDoubleHashMap.this._values;
/*  768 */       Object[] set = TObjectDoubleHashMap.this._set;
/*  770 */       for (int i = values.length; i-- > 0;) {
/*  771 */         if (set[i] != TObjectHash.FREE && set[i] != TObjectHash.REMOVED && entry == values[i]) {
/*  772 */           TObjectDoubleHashMap.this.removeAt(i);
/*  773 */           return true;
/*      */         } 
/*      */       } 
/*  776 */       return false;
/*      */     }
/*      */     
/*      */     public boolean containsAll(Collection<?> collection) {
/*  781 */       for (Object element : collection) {
/*  782 */         if (element instanceof Double) {
/*  783 */           double ele = ((Double)element).doubleValue();
/*  784 */           if (!TObjectDoubleHashMap.this.containsValue(ele))
/*  785 */             return false; 
/*      */           continue;
/*      */         } 
/*  788 */         return false;
/*      */       } 
/*  791 */       return true;
/*      */     }
/*      */     
/*      */     public boolean containsAll(TDoubleCollection collection) {
/*  796 */       TDoubleIterator iter = collection.iterator();
/*  797 */       while (iter.hasNext()) {
/*  798 */         if (!TObjectDoubleHashMap.this.containsValue(iter.next()))
/*  799 */           return false; 
/*      */       } 
/*  802 */       return true;
/*      */     }
/*      */     
/*      */     public boolean containsAll(double[] array) {
/*  807 */       for (double element : array) {
/*  808 */         if (!TObjectDoubleHashMap.this.containsValue(element))
/*  809 */           return false; 
/*      */       } 
/*  812 */       return true;
/*      */     }
/*      */     
/*      */     public boolean addAll(Collection<? extends Double> collection) {
/*  817 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public boolean addAll(TDoubleCollection collection) {
/*  822 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public boolean addAll(double[] array) {
/*  827 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public boolean retainAll(Collection<?> collection) {
/*  833 */       boolean modified = false;
/*  834 */       TDoubleIterator iter = iterator();
/*  835 */       while (iter.hasNext()) {
/*  836 */         if (!collection.contains(Double.valueOf(iter.next()))) {
/*  837 */           iter.remove();
/*  838 */           modified = true;
/*      */         } 
/*      */       } 
/*  841 */       return modified;
/*      */     }
/*      */     
/*      */     public boolean retainAll(TDoubleCollection collection) {
/*  846 */       if (this == collection)
/*  847 */         return false; 
/*  849 */       boolean modified = false;
/*  850 */       TDoubleIterator iter = iterator();
/*  851 */       while (iter.hasNext()) {
/*  852 */         if (!collection.contains(iter.next())) {
/*  853 */           iter.remove();
/*  854 */           modified = true;
/*      */         } 
/*      */       } 
/*  857 */       return modified;
/*      */     }
/*      */     
/*      */     public boolean retainAll(double[] array) {
/*  862 */       boolean changed = false;
/*  863 */       Arrays.sort(array);
/*  864 */       double[] values = TObjectDoubleHashMap.this._values;
/*  866 */       Object[] set = TObjectDoubleHashMap.this._set;
/*  867 */       for (int i = set.length; i-- > 0;) {
/*  868 */         if (set[i] != TObjectHash.FREE && set[i] != TObjectHash.REMOVED && Arrays.binarySearch(array, values[i]) < 0) {
/*  871 */           TObjectDoubleHashMap.this.removeAt(i);
/*  872 */           changed = true;
/*      */         } 
/*      */       } 
/*  875 */       return changed;
/*      */     }
/*      */     
/*      */     public boolean removeAll(Collection<?> collection) {
/*  880 */       boolean changed = false;
/*  881 */       for (Object element : collection) {
/*  882 */         if (element instanceof Double) {
/*  883 */           double c = ((Double)element).doubleValue();
/*  884 */           if (remove(c))
/*  885 */             changed = true; 
/*      */         } 
/*      */       } 
/*  889 */       return changed;
/*      */     }
/*      */     
/*      */     public boolean removeAll(TDoubleCollection collection) {
/*  894 */       if (this == collection) {
/*  895 */         clear();
/*  896 */         return true;
/*      */       } 
/*  898 */       boolean changed = false;
/*  899 */       TDoubleIterator iter = collection.iterator();
/*  900 */       while (iter.hasNext()) {
/*  901 */         double element = iter.next();
/*  902 */         if (remove(element))
/*  903 */           changed = true; 
/*      */       } 
/*  906 */       return changed;
/*      */     }
/*      */     
/*      */     public boolean removeAll(double[] array) {
/*  911 */       boolean changed = false;
/*  912 */       for (int i = array.length; i-- > 0;) {
/*  913 */         if (remove(array[i]))
/*  914 */           changed = true; 
/*      */       } 
/*  917 */       return changed;
/*      */     }
/*      */     
/*      */     public void clear() {
/*  922 */       TObjectDoubleHashMap.this.clear();
/*      */     }
/*      */     
/*      */     public boolean forEach(TDoubleProcedure procedure) {
/*  927 */       return TObjectDoubleHashMap.this.forEachValue(procedure);
/*      */     }
/*      */     
/*      */     public String toString() {
/*  933 */       final StringBuilder buf = new StringBuilder("{");
/*  934 */       TObjectDoubleHashMap.this.forEachValue(new TDoubleProcedure() {
/*      */             private boolean first = true;
/*      */             
/*      */             public boolean execute(double value) {
/*  938 */               if (this.first) {
/*  939 */                 this.first = false;
/*      */               } else {
/*  941 */                 buf.append(", ");
/*      */               } 
/*  944 */               buf.append(value);
/*  945 */               return true;
/*      */             }
/*      */           });
/*  948 */       buf.append("}");
/*  949 */       return buf.toString();
/*      */     }
/*      */     
/*      */     class TObjectDoubleValueHashIterator implements TDoubleIterator {
/*  955 */       protected THash _hash = (THash)TObjectDoubleHashMap.this;
/*      */       
/*      */       protected int _expectedSize;
/*      */       
/*      */       protected int _index;
/*      */       
/*      */       TObjectDoubleValueHashIterator() {
/*  968 */         this._expectedSize = this._hash.size();
/*  969 */         this._index = this._hash.capacity();
/*      */       }
/*      */       
/*      */       public boolean hasNext() {
/*  974 */         return (nextIndex() >= 0);
/*      */       }
/*      */       
/*      */       public double next() {
/*  979 */         moveToNextIndex();
/*  980 */         return TObjectDoubleHashMap.this._values[this._index];
/*      */       }
/*      */       
/*      */       public void remove() {
/*  985 */         if (this._expectedSize != this._hash.size())
/*  986 */           throw new ConcurrentModificationException(); 
/*      */         try {
/*  992 */           this._hash.tempDisableAutoCompaction();
/*  993 */           TObjectDoubleHashMap.this.removeAt(this._index);
/*      */         } finally {
/*  996 */           this._hash.reenableAutoCompaction(false);
/*      */         } 
/*  999 */         this._expectedSize--;
/*      */       }
/*      */       
/*      */       protected final void moveToNextIndex() {
/* 1009 */         if ((this._index = nextIndex()) < 0)
/* 1010 */           throw new NoSuchElementException(); 
/*      */       }
/*      */       
/*      */       protected final int nextIndex() {
/* 1025 */         if (this._expectedSize != this._hash.size())
/* 1026 */           throw new ConcurrentModificationException(); 
/* 1029 */         Object[] set = TObjectDoubleHashMap.this._set;
/* 1030 */         int i = this._index;
/* 1031 */         while (i-- > 0 && (set[i] == TObjectHash.FREE || set[i] == TObjectHash.REMOVED));
/* 1036 */         return i;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   class TObjectDoubleHashIterator<K> extends TObjectHashIterator<K> implements TObjectDoubleIterator<K> {
/*      */     private final TObjectDoubleHashMap<K> _map;
/*      */     
/*      */     public TObjectDoubleHashIterator(TObjectDoubleHashMap<K> map) {
/* 1049 */       super(map);
/* 1050 */       this._map = map;
/*      */     }
/*      */     
/*      */     public void advance() {
/* 1055 */       moveToNextIndex();
/*      */     }
/*      */     
/*      */     public K key() {
/* 1061 */       return (K)this._map._set[this._index];
/*      */     }
/*      */     
/*      */     public double value() {
/* 1066 */       return this._map._values[this._index];
/*      */     }
/*      */     
/*      */     public double setValue(double val) {
/* 1071 */       double old = value();
/* 1072 */       this._map._values[this._index] = val;
/* 1073 */       return old;
/*      */     }
/*      */   }
/*      */   
/*      */   public void writeExternal(ObjectOutput out) throws IOException {
/*      */     // Byte code:
/*      */     //   0: aload_1
/*      */     //   1: iconst_0
/*      */     //   2: invokeinterface writeByte : (I)V
/*      */     //   7: aload_0
/*      */     //   8: aload_1
/*      */     //   9: invokespecial writeExternal : (Ljava/io/ObjectOutput;)V
/*      */     //   12: aload_1
/*      */     //   13: aload_0
/*      */     //   14: getfield no_entry_value : D
/*      */     //   17: invokeinterface writeDouble : (D)V
/*      */     //   22: aload_1
/*      */     //   23: aload_0
/*      */     //   24: getfield _size : I
/*      */     //   27: invokeinterface writeInt : (I)V
/*      */     //   32: aload_0
/*      */     //   33: getfield _set : [Ljava/lang/Object;
/*      */     //   36: arraylength
/*      */     //   37: istore_2
/*      */     //   38: iload_2
/*      */     //   39: iinc #2, -1
/*      */     //   42: ifle -> 96
/*      */     //   45: aload_0
/*      */     //   46: getfield _set : [Ljava/lang/Object;
/*      */     //   49: iload_2
/*      */     //   50: aaload
/*      */     //   51: getstatic gnu/trove/map/hash/TObjectDoubleHashMap.REMOVED : Ljava/lang/Object;
/*      */     //   54: if_acmpeq -> 38
/*      */     //   57: aload_0
/*      */     //   58: getfield _set : [Ljava/lang/Object;
/*      */     //   61: iload_2
/*      */     //   62: aaload
/*      */     //   63: getstatic gnu/trove/map/hash/TObjectDoubleHashMap.FREE : Ljava/lang/Object;
/*      */     //   66: if_acmpeq -> 38
/*      */     //   69: aload_1
/*      */     //   70: aload_0
/*      */     //   71: getfield _set : [Ljava/lang/Object;
/*      */     //   74: iload_2
/*      */     //   75: aaload
/*      */     //   76: invokeinterface writeObject : (Ljava/lang/Object;)V
/*      */     //   81: aload_1
/*      */     //   82: aload_0
/*      */     //   83: getfield _values : [D
/*      */     //   86: iload_2
/*      */     //   87: daload
/*      */     //   88: invokeinterface writeDouble : (D)V
/*      */     //   93: goto -> 38
/*      */     //   96: return
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #1082	-> 0
/*      */     //   #1085	-> 7
/*      */     //   #1088	-> 12
/*      */     //   #1091	-> 22
/*      */     //   #1094	-> 32
/*      */     //   #1095	-> 45
/*      */     //   #1096	-> 69
/*      */     //   #1097	-> 81
/*      */     //   #1100	-> 96
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   38	58	2	i	I
/*      */     //   0	97	0	this	Lgnu/trove/map/hash/TObjectDoubleHashMap;
/*      */     //   0	97	1	out	Ljava/io/ObjectOutput;
/*      */     // Local variable type table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	97	0	this	Lgnu/trove/map/hash/TObjectDoubleHashMap<TK;>;
/*      */   }
/*      */   
/*      */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 1107 */     in.readByte();
/* 1110 */     super.readExternal(in);
/* 1113 */     this.no_entry_value = in.readDouble();
/* 1116 */     int size = in.readInt();
/* 1117 */     setUp(size);
/* 1120 */     while (size-- > 0) {
/* 1122 */       K key = (K)in.readObject();
/* 1123 */       double val = in.readDouble();
/* 1124 */       put(key, val);
/*      */     } 
/*      */   }
/*      */   
/*      */   public String toString() {
/* 1131 */     final StringBuilder buf = new StringBuilder("{");
/* 1132 */     forEachEntry(new TObjectDoubleProcedure<K>() {
/*      */           private boolean first = true;
/*      */           
/*      */           public boolean execute(K key, double value) {
/* 1135 */             if (this.first) {
/* 1135 */               this.first = false;
/*      */             } else {
/* 1136 */               buf.append(",");
/*      */             } 
/* 1138 */             buf.append(key).append("=").append(value);
/* 1139 */             return true;
/*      */           }
/*      */         });
/* 1142 */     buf.append("}");
/* 1143 */     return buf.toString();
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\map\hash\TObjectDoubleHashMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */