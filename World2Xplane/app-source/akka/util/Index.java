/*     */ package akka.util;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.ConcurrentSkipListSet;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.JavaConverters$;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.package$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005=c\001B\001\003\001\035\021Q!\0238eKbT!a\001\003\002\tU$\030\016\034\006\002\013\005!\021m[6b\007\001)2\001\003\033$'\t\001\021\002\005\002\013\0335\t1BC\001\r\003\025\0318-\0317b\023\tq1B\001\004B]f\024VM\032\005\t!\001\021)\031!C\001#\0059Q.\0319TSj,W#\001\n\021\005)\031\022B\001\013\f\005\rIe\016\036\005\t-\001\021\t\021)A\005%\005AQ.\0319TSj,\007\005\003\005\031\001\t\025\r\021\"\001\032\003=1\030\r\\;f\007>l\007/\031:bi>\024X#\001\016\021\007my\022%D\001\035\025\t\031QDC\001\037\003\021Q\027M^1\n\005\001b\"AC\"p[B\f'/\031;peB\021!e\t\007\001\t\025!\003A1\001&\005\0051\026C\001\024*!\tQq%\003\002)\027\t9aj\034;iS:<\007C\001\006+\023\tY3BA\002B]fD\001\"\f\001\003\002\003\006IAG\001\021m\006dW/Z\"p[B\f'/\031;pe\002BQa\f\001\005\002A\na\001P5oSRtDcA\0317oA!!\007A\032\"\033\005\021\001C\001\0225\t\025)\004A1\001&\005\005Y\005\"\002\t/\001\004\021\002\"\002\r/\001\004Q\002\"B\030\001\t\003IDcA\031;w!)\001\003\017a\001%!)A\b\017a\001{\005\0311-\0349\021\013)q\024%\t\n\n\005}Z!!\003$v]\016$\030n\03483\021\035\t\005A1A\005\n\t\013\021bY8oi\006Lg.\032:\026\003\r\003B\001R$4\0236\tQI\003\002G9\005Q1m\0348dkJ\024XM\034;\n\005!+%!E\"p]\016,(O]3oi\"\0137\017['baB\031AIS\021\n\005-+%!F\"p]\016,(O]3oiN[\027\016\035'jgR\034V\r\036\005\007\033\002\001\013\021B\"\002\025\r|g\016^1j]\026\024\b\005C\004P\001\t\007I\021\002)\002\021\025l\007\017^=TKR,\022!\023\005\007%\002\001\013\021B%\002\023\025l\007\017^=TKR\004\003\"\002+\001\t\003)\026a\0019viR\031a+W.\021\005)9\026B\001-\f\005\035\021un\0347fC:DQAW*A\002M\n1a[3z\021\025a6\0131\001\"\003\0251\030\r\\;f\021\025q\006\001\"\001`\003%1\027N\0343WC2,X\r\006\002aSR\021\021\r\032\t\004\025\t\f\023BA2\f\005\031y\005\017^5p]\")Q-\030a\001M\006\ta\r\005\003\013O\0062\026B\0015\f\005%1UO\\2uS>t\027\007C\003[;\002\0071\007C\003l\001\021\005A.A\007wC2,X-\023;fe\006$xN\035\013\003[f\0042A\034<\"\035\tyGO\004\002qg6\t\021O\003\002s\r\0051AH]8pizJ\021\001D\005\003k.\tq\001]1dW\006<W-\003\002xq\nA\021\n^3sCR|'O\003\002v\027!)!L\033a\001g!)1\020\001C\001y\0069am\034:fC\016DGcA?\002\002A\021!B`\005\003.\021A!\0268ji\"9\0211\001>A\002\005\025\021a\0014v]B)!BP\032\"{\"9\021\021\002\001\005\002\005-\021A\002<bYV,7/\006\002\002\016A)\021qBA\013C9\031!\"!\005\n\007\005M1\"\001\004Qe\026$WMZ\005\005\003/\tIBA\002TKRT1!a\005\f\021\035\ti\002\001C\001\003?\tAa[3zgV\021\021\021\005\t\005]\006\r2'C\002\002&a\024\001\"\023;fe\006\024G.\032\005\b\003S\001A\021AA\026\003\031\021X-\\8wKR)a+!\f\0020!1!,a\nA\002MBa\001XA\024\001\004\t\003bBA\025\001\021\005\0211\007\013\005\003k\tI\004\005\003\013E\006]\002\003\0028\002$\005BaAWA\031\001\004\031\004bBA\037\001\021\005\021qH\001\fe\026lwN^3WC2,X\rF\002~\003\003Ba\001XA\036\001\004\t\003bBA#\001\021\005\021qI\001\bSN,U\016\035;z+\0051\006bBA&\001\021\005\021QJ\001\006G2,\027M\035\013\002{\002")
/*     */ public class Index<K, V> {
/*     */   private final int mapSize;
/*     */   
/*     */   private final Comparator<V> valueComparator;
/*     */   
/*     */   private final ConcurrentHashMap<K, ConcurrentSkipListSet<V>> container;
/*     */   
/*     */   private final ConcurrentSkipListSet<V> emptySet;
/*     */   
/*     */   public int mapSize() {
/*  18 */     return this.mapSize;
/*     */   }
/*     */   
/*     */   public Comparator<V> valueComparator() {
/*  18 */     return this.valueComparator;
/*     */   }
/*     */   
/*     */   public Index(int mapSize, Comparator<V> valueComparator) {
/*  24 */     this.container = new ConcurrentHashMap<K, ConcurrentSkipListSet<V>>(mapSize);
/*  25 */     this.emptySet = new ConcurrentSkipListSet<V>();
/*     */   }
/*     */   
/*     */   public Index(int mapSize, Function2 cmp) {
/*     */     this(mapSize, new Index$$anon$1(cmp));
/*     */   }
/*     */   
/*     */   public class Index$$anon$1 implements Comparator<V> {
/*     */     private final Function2 cmp$1;
/*     */     
/*     */     public Index$$anon$1(Function2 cmp$1) {}
/*     */     
/*     */     public int compare(Object a, Object b) {
/*     */       return BoxesRunTime.unboxToInt(this.cmp$1.apply(a, b));
/*     */     }
/*     */   }
/*     */   
/*     */   private ConcurrentHashMap<K, ConcurrentSkipListSet<V>> container() {
/*     */     return this.container;
/*     */   }
/*     */   
/*     */   private ConcurrentSkipListSet<V> emptySet() {
/*  25 */     return this.emptySet;
/*     */   }
/*     */   
/*     */   private final boolean spinPut$1(Object k, Object v) {
/*     */     while (true) {
/*  35 */       boolean retry = false;
/*  36 */       boolean added = false;
/*  37 */       ConcurrentSkipListSet<Object> set = (ConcurrentSkipListSet)container().get(k);
/*  39 */       if (set != null)
/*  40 */         synchronized (set) {
/*  41 */           retry = true;
/*  43 */           added = set.add(v);
/*  44 */           retry = false;
/*  44 */           BoxedUnit boxedUnit = set.isEmpty() ? BoxedUnit.UNIT : BoxedUnit.UNIT;
/*     */         }  
/*  48 */       ConcurrentSkipListSet<V> newSet = new ConcurrentSkipListSet<V>(valueComparator());
/*  49 */       newSet.add((V)v);
/*  52 */       ConcurrentSkipListSet<Object> oldSet = (ConcurrentSkipListSet)container().putIfAbsent((K)k, newSet);
/*  53 */       if (oldSet != null)
/*  54 */         synchronized (oldSet) {
/*  55 */           retry = true;
/*  57 */           added = oldSet.add(v);
/*  58 */           retry = false;
/*  58 */           BoxedUnit boxedUnit = oldSet.isEmpty() ? BoxedUnit.UNIT : BoxedUnit.UNIT;
/*     */         }  
/*  61 */       added = true;
/*  64 */       if (retry) {
/*  64 */         v = v;
/*  64 */         k = k;
/*     */         continue;
/*     */       } 
/*  65 */       return added;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean put(Object key, Object value) {
/*  68 */     return spinPut$1(key, value);
/*     */   }
/*     */   
/*     */   public Option<V> findValue(Object key, Function1 f) {
/*     */     Option<V> option;
/*  76 */     ConcurrentSkipListSet concurrentSkipListSet = container().get(key);
/*  77 */     if (concurrentSkipListSet == null) {
/*  77 */       None$ none$ = None$.MODULE$;
/*     */     } else {
/*  78 */       option = ((Iterator)JavaConverters$.MODULE$.asScalaIteratorConverter(concurrentSkipListSet.iterator()).asScala()).find(f);
/*     */     } 
/*     */     return option;
/*     */   }
/*     */   
/*     */   public Iterator<V> valueIterator(Object key) {
/*     */     Iterator<V> iterator;
/*  85 */     ConcurrentSkipListSet concurrentSkipListSet = container().get(key);
/*  86 */     if (concurrentSkipListSet == null) {
/*  86 */       iterator = package$.MODULE$.Iterator().empty();
/*     */     } else {
/*  87 */       iterator = (Iterator)JavaConverters$.MODULE$.asScalaIteratorConverter(concurrentSkipListSet.iterator()).asScala();
/*     */     } 
/*     */     return iterator;
/*     */   }
/*     */   
/*     */   public void foreach(Function2 fun) {
/*  95 */     ((Iterator)JavaConverters$.MODULE$.asScalaIteratorConverter(container().entrySet().iterator()).asScala()).foreach((Function1)new Index$$anonfun$foreach$1(this, (Index<K, V>)fun));
/*     */   }
/*     */   
/*     */   public class Index$$anonfun$foreach$1 extends AbstractFunction1<Map.Entry<K, ConcurrentSkipListSet<V>>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Function2 fun$1;
/*     */     
/*     */     public final void apply(Map.Entry e) {
/*  95 */       ((Iterator)JavaConverters$.MODULE$.asScalaIteratorConverter(((ConcurrentSkipListSet)e.getValue()).iterator()).asScala()).foreach((Function1)new Index$$anonfun$foreach$1$$anonfun$apply$1(this, ($anonfun$foreach$1)e));
/*     */     }
/*     */     
/*     */     public Index$$anonfun$foreach$1(Index $outer, Function2 fun$1) {}
/*     */     
/*     */     public class Index$$anonfun$foreach$1$$anonfun$apply$1 extends AbstractFunction1<V, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Map.Entry e$1;
/*     */       
/*     */       public final void apply(Object x$1) {
/*  95 */         this.$outer.fun$1.apply(this.e$1.getKey(), x$1);
/*     */       }
/*     */       
/*     */       public Index$$anonfun$foreach$1$$anonfun$apply$1(Index$$anonfun$foreach$1 $outer, Map.Entry e$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public Set<V> values() {
/* 101 */     Builder builder = Predef$.MODULE$.Set().newBuilder();
/* 103 */     ((Iterator)JavaConverters$.MODULE$.asScalaIteratorConverter(container().values().iterator()).asScala()).foreach((Function1)new Index$$anonfun$values$1(this, (Index<K, V>)builder));
/* 106 */     return (Set<V>)builder.result();
/*     */   }
/*     */   
/*     */   public class Index$$anonfun$values$1 extends AbstractFunction1<ConcurrentSkipListSet<V>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Builder builder$1;
/*     */     
/*     */     public Index$$anonfun$values$1(Index $outer, Builder builder$1) {}
/*     */     
/*     */     public final void apply(ConcurrentSkipListSet values) {
/*     */       ((Iterator)JavaConverters$.MODULE$.asScalaIteratorConverter(values.iterator()).asScala()).foreach((Function1)new Index$$anonfun$values$1$$anonfun$apply$2(this));
/*     */     }
/*     */     
/*     */     public class Index$$anonfun$values$1$$anonfun$apply$2 extends AbstractFunction1<V, Builder<V, Set<V>>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public Index$$anonfun$values$1$$anonfun$apply$2(Index$$anonfun$values$1 $outer) {}
/*     */       
/*     */       public final Builder<V, Set<V>> apply(Object v) {
/*     */         return this.$outer.builder$1.$plus$eq(v);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public Iterable<K> keys() {
/* 112 */     return (Iterable<K>)JavaConverters$.MODULE$.collectionAsScalaIterableConverter(container().keySet()).asScala();
/*     */   }
/*     */   
/*     */   public boolean remove(Object key, Object value) {
/* 119 */     ConcurrentSkipListSet set = container().get(key);
/* 121 */     if (set != null)
/* 122 */       synchronized (set) {
/* 124 */         set.isEmpty() ? 
/* 125 */           BoxesRunTime.boxToBoolean(container().remove(key, emptySet())) : BoxedUnit.UNIT;
/* 128 */         Boolean bool = set.remove(value) ? BoxesRunTime.boxToBoolean(true) : BoxesRunTime.boxToBoolean(false);
/*     */       }  
/*     */     return false;
/*     */   }
/*     */   
/*     */   public Option<Iterable<V>> remove(Object key) {
/* 138 */     ConcurrentSkipListSet set = container().get(key);
/* 140 */     if (set != null)
/* 141 */       synchronized (set) {
/* 142 */         container().remove(key, set);
/* 143 */         Iterable ret = (Iterable)JavaConverters$.MODULE$.collectionAsScalaIterableConverter(set.clone()).asScala();
/* 144 */         set.clear();
/* 145 */         Some some = new Some(ret);
/*     */       }  
/* 147 */     return (Option<Iterable<V>>)None$.MODULE$;
/*     */   }
/*     */   
/*     */   public void removeValue(Object value) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokespecial container : ()Ljava/util/concurrent/ConcurrentHashMap;
/*     */     //   4: invokevirtual entrySet : ()Ljava/util/Set;
/*     */     //   7: invokeinterface iterator : ()Ljava/util/Iterator;
/*     */     //   12: astore_2
/*     */     //   13: aload_2
/*     */     //   14: invokeinterface hasNext : ()Z
/*     */     //   19: ifeq -> 120
/*     */     //   22: aload_2
/*     */     //   23: invokeinterface next : ()Ljava/lang/Object;
/*     */     //   28: checkcast java/util/Map$Entry
/*     */     //   31: astore_3
/*     */     //   32: aload_3
/*     */     //   33: invokeinterface getValue : ()Ljava/lang/Object;
/*     */     //   38: checkcast java/util/concurrent/ConcurrentSkipListSet
/*     */     //   41: astore #4
/*     */     //   43: aload #4
/*     */     //   45: ifnull -> 113
/*     */     //   48: aload #4
/*     */     //   50: dup
/*     */     //   51: astore #5
/*     */     //   53: monitorenter
/*     */     //   54: aload #4
/*     */     //   56: aload_1
/*     */     //   57: invokevirtual remove : (Ljava/lang/Object;)Z
/*     */     //   60: ifeq -> 100
/*     */     //   63: aload #4
/*     */     //   65: invokevirtual isEmpty : ()Z
/*     */     //   68: ifeq -> 94
/*     */     //   71: aload_0
/*     */     //   72: invokespecial container : ()Ljava/util/concurrent/ConcurrentHashMap;
/*     */     //   75: aload_3
/*     */     //   76: invokeinterface getKey : ()Ljava/lang/Object;
/*     */     //   81: aload_0
/*     */     //   82: invokespecial emptySet : ()Ljava/util/concurrent/ConcurrentSkipListSet;
/*     */     //   85: invokevirtual remove : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*     */     //   88: invokestatic boxToBoolean : (Z)Ljava/lang/Boolean;
/*     */     //   91: goto -> 103
/*     */     //   94: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   97: goto -> 103
/*     */     //   100: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   103: astore #6
/*     */     //   105: aload #5
/*     */     //   107: monitorexit
/*     */     //   108: aload #6
/*     */     //   110: goto -> 116
/*     */     //   113: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   116: pop
/*     */     //   117: goto -> 13
/*     */     //   120: return
/*     */     //   121: aload #5
/*     */     //   123: monitorexit
/*     */     //   124: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #154	-> 0
/*     */     //   #155	-> 13
/*     */     //   #156	-> 22
/*     */     //   #157	-> 32
/*     */     //   #159	-> 43
/*     */     //   #160	-> 48
/*     */     //   #161	-> 54
/*     */     //   #162	-> 63
/*     */     //   #163	-> 71
/*     */     //   #162	-> 94
/*     */     //   #161	-> 100
/*     */     //   #160	-> 107
/*     */     //   #159	-> 113
/*     */     //   #153	-> 120
/*     */     //   #160	-> 121
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	125	0	this	Lakka/util/Index;
/*     */     //   0	125	1	value	Ljava/lang/Object;
/*     */     //   13	112	2	i	Ljava/util/Iterator;
/*     */     //   32	85	3	e	Ljava/util/Map$Entry;
/*     */     //   43	74	4	set	Ljava/util/concurrent/ConcurrentSkipListSet;
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   54	108	121	finally
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 173 */     return container().isEmpty();
/*     */   }
/*     */   
/*     */   public void clear() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokespecial container : ()Ljava/util/concurrent/ConcurrentHashMap;
/*     */     //   4: invokevirtual entrySet : ()Ljava/util/Set;
/*     */     //   7: invokeinterface iterator : ()Ljava/util/Iterator;
/*     */     //   12: astore_1
/*     */     //   13: aload_1
/*     */     //   14: invokeinterface hasNext : ()Z
/*     */     //   19: ifeq -> 92
/*     */     //   22: aload_1
/*     */     //   23: invokeinterface next : ()Ljava/lang/Object;
/*     */     //   28: checkcast java/util/Map$Entry
/*     */     //   31: astore_2
/*     */     //   32: aload_2
/*     */     //   33: invokeinterface getValue : ()Ljava/lang/Object;
/*     */     //   38: checkcast java/util/concurrent/ConcurrentSkipListSet
/*     */     //   41: astore_3
/*     */     //   42: aload_3
/*     */     //   43: ifnull -> 85
/*     */     //   46: aload_3
/*     */     //   47: dup
/*     */     //   48: astore #4
/*     */     //   50: monitorenter
/*     */     //   51: aload_3
/*     */     //   52: invokevirtual clear : ()V
/*     */     //   55: aload_0
/*     */     //   56: invokespecial container : ()Ljava/util/concurrent/ConcurrentHashMap;
/*     */     //   59: aload_2
/*     */     //   60: invokeinterface getKey : ()Ljava/lang/Object;
/*     */     //   65: aload_0
/*     */     //   66: invokespecial emptySet : ()Ljava/util/concurrent/ConcurrentSkipListSet;
/*     */     //   69: invokevirtual remove : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*     */     //   72: invokestatic boxToBoolean : (Z)Ljava/lang/Boolean;
/*     */     //   75: astore #5
/*     */     //   77: aload #4
/*     */     //   79: monitorexit
/*     */     //   80: aload #5
/*     */     //   82: goto -> 88
/*     */     //   85: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   88: pop
/*     */     //   89: goto -> 13
/*     */     //   92: return
/*     */     //   93: aload #4
/*     */     //   95: monitorexit
/*     */     //   96: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #179	-> 0
/*     */     //   #180	-> 13
/*     */     //   #181	-> 22
/*     */     //   #182	-> 32
/*     */     //   #183	-> 42
/*     */     //   #178	-> 92
/*     */     //   #183	-> 93
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	97	0	this	Lakka/util/Index;
/*     */     //   13	84	1	i	Ljava/util/Iterator;
/*     */     //   32	57	2	e	Ljava/util/Map$Entry;
/*     */     //   42	47	3	set	Ljava/util/concurrent/ConcurrentSkipListSet;
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   51	80	93	finally
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akk\\util\Index.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */