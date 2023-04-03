/*     */ package scala.collection.parallel.immutable;
/*     */ 
/*     */ import scala.Array$;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.MatchError;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.generic.Growable;
/*     */ import scala.collection.immutable.HashMap;
/*     */ import scala.collection.immutable.HashMap$;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.List$;
/*     */ import scala.collection.immutable.ListMap;
/*     */ import scala.collection.immutable.ListMap$;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.collection.mutable.UnrolledBuffer;
/*     */ import scala.collection.parallel.BucketCombiner;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.Task;
/*     */ import scala.collection.parallel.package$;
/*     */ import scala.reflect.ClassTag$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005ehAB\001\003\003\003!!BA\bICNDW*\0319D_6\024\027N\\3s\025\t\031A!A\005j[6,H/\0312mK*\021QAB\001\ta\006\024\030\r\0347fY*\021q\001C\001\013G>dG.Z2uS>t'\"A\005\002\013M\034\027\r\\1\026\007-1\022e\005\002\001\031A1QB\004\t$!\035j\021\001B\005\003\037\021\021aBQ;dW\026$8i\\7cS:,'\017\005\003\022%Q\001S\"\001\005\n\005MA!A\002+va2,'\007\005\002\026-1\001A!B\f\001\005\004I\"!A&\004\001E\021!$\b\t\003#mI!\001\b\005\003\0179{G\017[5oOB\021\021CH\005\003?!\0211!\0218z!\t)\022\005B\003#\001\t\007\021DA\001W!\021!S\005\006\021\016\003\tI!A\n\002\003\025A\013'\017S1tQ6\013\007\017\005\003%\001Q\001\003\"B\025\001\t\003Q\023A\002\037j]&$h\bF\001(\021\035a\003A1A\005\0025\n\021\"Z7qif$&/[3\026\0039\002BaL\031\025A5\t\001G\003\002\004\r%\021!\007\r\002\b\021\006\034\b.T1q\021\031!\004\001)A\005]\005QQ-\0349usR\023\030.\032\021\t\013Y\002A\021A\034\002\021\021\002H.^:%KF$\"\001O\035\016\003\001AQAO\033A\002A\tA!\0327f[\")A\b\001C\001{\0051!/Z:vYR$\022a\t\005\006\001!\t\001Q\001\013OJ|W\017\035\"z\027\026LXCA!E)\t\021e\t\005\003%KQ\031\005CA\013E\t\025)eH1\001\032\005\021\021V\r\035:\t\013\035s\004\031\001%\002\007\r\024g\rE\002\022\023.K!A\023\005\003\023\031+hn\031;j_:\004\004\003B\007MA\rK!!\024\003\003\021\r{WNY5oKJDQa\024\001\005BA\013\001\002^8TiJLgn\032\013\002#B\021!kV\007\002'*\021A+V\001\005Y\006twMC\001W\003\021Q\027M^1\n\005a\033&AB*ue&twM\002\003[\001\001Y&AC\"sK\006$X\r\026:jKN\031\021\fX0\021\005Ei\026B\0010\t\005\031\te.\037*fMB!Q\002\0312f\023\t\tGA\001\003UCN\\\007CA\td\023\t!\007B\001\003V]&$\bC\001\035Z\021!9\027L!A!\002\023A\027!\0022vG.\034\bcA\tjW&\021!\016\003\002\006\003J\024\030-\037\t\004Yj\004bBA7x\035\tqWO\004\002pi:\021\001o]\007\002c*\021!\017G\001\007yI|w\016\036 \n\003%I!a\002\005\n\005Y4\021aB7vi\006\024G.Z\005\003qf\fa\"\0268s_2dW\r\032\"vM\032,'O\003\002w\r%\0211\020 \002\t+:\024x\016\0347fI*\021\0010\037\005\t}f\023\t\021)A\005\006!!o\\8u!\r\t\022N\f\005\013\003\007I&\021!Q\001\n\005\025\021AB8gMN,G\017E\002\022\003\017I1!!\003\t\005\rIe\016\036\005\013\003\033I&\021!Q\001\n\005\025\021a\0025po6\fg.\037\005\007Se#\t!!\005\025\023\025\f\031\"!\006\002\030\005e\001BB4\002\020\001\007\001\016\003\004\003\037\001\ra \005\t\003\007\ty\0011\001\002\006!A\021QBA\b\001\004\t)\001\003\005=3\002\007I\021AA\017+\005\021\007\"CA\0213\002\007I\021AA\022\003)\021Xm];mi~#S-\035\013\004E\006\025\002\"CA\024\003?\t\t\0211\001c\003\rAH%\r\005\b\003WI\006\025)\003c\003\035\021Xm];mi\002BC!!\013\0020A\031\021#!\r\n\007\005M\002B\001\005w_2\fG/\0337f\021\035\t9$\027C\001\003s\tA\001\\3bMR\031!-a\017\t\021\005u\022Q\007a\001\003\tA\001\035:fmB!\021#!\021c\023\r\t\031\005\003\002\007\037B$\030n\0348\t\017\005\035\023\f\"\003\002J\005Q1M]3bi\026$&/[3\025\0079\nY\005C\004\002N\005\025\003\031A6\002\013\025dW-\\:\t\017\005E\023\f\"\001\002T\005)1\017\0357jiV\021\021Q\013\t\005_\005]S-C\002\002ZA\022A\001T5ti\"9\021QL-\005\002\005}\023AE:i_VdGm\0259mSR4UO\035;iKJ,\"!!\031\021\007E\t\031'C\002\002f!\021qAQ8pY\026\fgN\002\004\002j\001\001\0211\016\002\022\007J,\027\r^3He>,\b/\0323Ue&,W\003BA7\003k\032R!a\032]\003_\002R!\0041c\003c\002R\001OA4\003g\0022!FA;\t\031)\025q\rb\0013!Qq)a\032\003\002\003\006I!!\037\021\tEI\0251\020\t\006\0331\003\0231\017\005\nO\006\035$\021!Q\001\n!D!B`A4\005\003\005\013\021BAA!\021\t\022.a!\021\t=\nD\003\030\005\f\003\007\t9G!A!\002\023\t)\001C\006\002\016\005\035$\021!Q\001\n\005\025\001bB\025\002h\021\005\0211\022\013\r\003c\ni)a$\002\022\006M\025Q\023\005\b\017\006%\005\031AA=\021\0319\027\021\022a\001Q\"9a0!#A\002\005\005\005\002CA\002\003\023\003\r!!\002\t\021\0055\021\021\022a\001\003\013A\021\002PA4\001\004%\t!!\b\t\025\005\005\022q\ra\001\n\003\tY\nF\002c\003;C\021\"a\n\002\032\006\005\t\031\0012\t\021\005-\022q\rQ!\n\tDC!a(\0020!A\021qGA4\t\003\t)\013F\002c\003OC\001\"!\020\002$\002\007\021q\b\005\t\003W\0139\007\"\003\002.\006\t2M]3bi\026<%o\\;qK\022$&/[3\025\t\005=\026\021\027\t\006_E\"\0221\017\005\b\003\033\nI\0131\001l\021!\t),a\032\005\n\005]\026!E3wC2,\030\r^3D_6\024\027N\\3sgR!\021qVA]\021!\tY,a-A\002\005u\026\001\002;sS\026\004RaL\031\025\003wB\001\"!\025\002h\021\005\021\021Y\013\003\003\007\004RaLA,\003cB\001\"!\030\002h\021\005\021qL\004\t\003\023\024\001\022\001\003\002L\006y\001*Y:i\033\006\0048i\\7cS:,'\017E\002%\003\0334q!\001\002\t\002\021\tymE\002\002NrCq!KAg\t\003\t\031\016\006\002\002L\"A\021q[Ag\t\003\tI.A\003baBd\0270\006\004\002\\\006\005\030Q]\013\003\003;\004b\001\n\001\002`\006\r\bcA\013\002b\0221q#!6C\002e\0012!FAs\t\031\021\023Q\033b\0013!Y\021\021^Ag\005\004%\tAAAv\003!\021xn\034;cSR\034XCAA\003\021%\ty/!4!\002\023\t)!A\005s_>$(-\033;tA!Y\0211_Ag\005\004%\tAAAv\003!\021xn\034;tSj,\007\"CA|\003\033\004\013\021BA\003\003%\021xn\034;tSj,\007\005")
/*     */ public abstract class HashMapCombiner<K, V> extends BucketCombiner<Tuple2<K, V>, ParHashMap<K, V>, Tuple2<K, V>, HashMapCombiner<K, V>> {
/*     */   private final HashMap<K, V> emptyTrie;
/*     */   
/*     */   public HashMapCombiner() {
/* 162 */     super(HashMapCombiner$.MODULE$.rootsize());
/* 165 */     this.emptyTrie = HashMap$.MODULE$.empty();
/*     */   }
/*     */   
/*     */   public HashMap<K, V> emptyTrie() {
/* 165 */     return this.emptyTrie;
/*     */   }
/*     */   
/*     */   public HashMapCombiner<K, V> $plus$eq(Tuple2 elem) {
/* 168 */     sz_$eq(sz() + 1);
/* 169 */     int hc = emptyTrie().computeHash(elem._1());
/* 170 */     int pos = hc & 0x1F;
/* 171 */     if (buckets()[pos] == null)
/* 173 */       buckets()[pos] = new UnrolledBuffer(ClassTag$.MODULE$.apply(Tuple2.class)); 
/* 176 */     buckets()[pos].$plus$eq(elem);
/* 177 */     return this;
/*     */   }
/*     */   
/*     */   public ParHashMap<K, V> result() {
/* 181 */     UnrolledBuffer.Unrolled[] bucks = (UnrolledBuffer.Unrolled[])Predef$.MODULE$.refArrayOps((Object[])Predef$.MODULE$.refArrayOps((Object[])buckets()).filter((Function1)new HashMapCombiner$$anonfun$1(this))).map((Function1)new HashMapCombiner$$anonfun$2(this), Array$.MODULE$.canBuildFrom(ClassTag$.MODULE$.apply(UnrolledBuffer.Unrolled.class)));
/* 182 */     HashMap[] root = new HashMap[bucks.length];
/* 184 */     combinerTaskSupport().executeAndWaitResult(new CreateTrie(this, (UnrolledBuffer.Unrolled<Tuple2<K, V>>[])bucks, (HashMap<K, V>[])root, 0, bucks.length));
/* 186 */     int bitmap = 0;
/* 187 */     int i = 0;
/* 188 */     while (i < HashMapCombiner$.MODULE$.rootsize()) {
/* 189 */       if (buckets()[i] != null)
/* 189 */         bitmap |= 1 << i; 
/* 190 */       i++;
/*     */     } 
/* 192 */     int sz = BoxesRunTime.unboxToInt(Predef$.MODULE$.refArrayOps((Object[])root).foldLeft(BoxesRunTime.boxToInteger(0), (Function2)new HashMapCombiner$$anonfun$3(this)));
/* 197 */     HashMap.HashTrieMap trie = new HashMap.HashTrieMap(bitmap, root, sz);
/* 198 */     return (sz == 0) ? new ParHashMap<K, V>() : ((sz == 1) ? new ParHashMap<K, V>(root[0]) : new ParHashMap<K, V>((HashMap<K, V>)trie));
/*     */   }
/*     */   
/*     */   public class HashMapCombiner$$anonfun$1 extends AbstractFunction1<UnrolledBuffer<Tuple2<K, V>>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(UnrolledBuffer x$3) {
/*     */       return !(x$3 == null);
/*     */     }
/*     */     
/*     */     public HashMapCombiner$$anonfun$1(HashMapCombiner $outer) {}
/*     */   }
/*     */   
/*     */   public class HashMapCombiner$$anonfun$2 extends AbstractFunction1<UnrolledBuffer<Tuple2<K, V>>, UnrolledBuffer.Unrolled<Tuple2<K, V>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final UnrolledBuffer.Unrolled<Tuple2<K, V>> apply(UnrolledBuffer x$4) {
/*     */       return x$4.headPtr();
/*     */     }
/*     */     
/*     */     public HashMapCombiner$$anonfun$2(HashMapCombiner $outer) {}
/*     */   }
/*     */   
/*     */   public class HashMapCombiner$$anonfun$3 extends AbstractFunction2<Object, HashMap<K, V>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int apply(int x$5, HashMap x$6) {
/*     */       return x$5 + x$6.size();
/*     */     }
/*     */     
/*     */     public HashMapCombiner$$anonfun$3(HashMapCombiner $outer) {}
/*     */   }
/*     */   
/*     */   public <Repr> ParHashMap<K, Repr> groupByKey(Function0 cbf) {
/*     */     // Byte code:
/*     */     //   0: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   3: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   6: aload_0
/*     */     //   7: invokevirtual buckets : ()[Lscala/collection/mutable/UnrolledBuffer;
/*     */     //   10: checkcast [Ljava/lang/Object;
/*     */     //   13: invokevirtual refArrayOps : ([Ljava/lang/Object;)Lscala/collection/mutable/ArrayOps;
/*     */     //   16: new scala/collection/parallel/immutable/HashMapCombiner$$anonfun$4
/*     */     //   19: dup
/*     */     //   20: aload_0
/*     */     //   21: invokespecial <init> : (Lscala/collection/parallel/immutable/HashMapCombiner;)V
/*     */     //   24: invokeinterface filter : (Lscala/Function1;)Ljava/lang/Object;
/*     */     //   29: checkcast [Ljava/lang/Object;
/*     */     //   32: invokevirtual refArrayOps : ([Ljava/lang/Object;)Lscala/collection/mutable/ArrayOps;
/*     */     //   35: new scala/collection/parallel/immutable/HashMapCombiner$$anonfun$5
/*     */     //   38: dup
/*     */     //   39: aload_0
/*     */     //   40: invokespecial <init> : (Lscala/collection/parallel/immutable/HashMapCombiner;)V
/*     */     //   43: getstatic scala/Array$.MODULE$ : Lscala/Array$;
/*     */     //   46: getstatic scala/reflect/ClassTag$.MODULE$ : Lscala/reflect/ClassTag$;
/*     */     //   49: ldc scala/collection/mutable/UnrolledBuffer$Unrolled
/*     */     //   51: invokevirtual apply : (Ljava/lang/Class;)Lscala/reflect/ClassTag;
/*     */     //   54: invokevirtual canBuildFrom : (Lscala/reflect/ClassTag;)Lscala/collection/generic/CanBuildFrom;
/*     */     //   57: invokeinterface map : (Lscala/Function1;Lscala/collection/generic/CanBuildFrom;)Ljava/lang/Object;
/*     */     //   62: checkcast [Lscala/collection/mutable/UnrolledBuffer$Unrolled;
/*     */     //   65: astore_2
/*     */     //   66: aload_2
/*     */     //   67: arraylength
/*     */     //   68: anewarray scala/collection/immutable/HashMap
/*     */     //   71: astore #5
/*     */     //   73: aload_0
/*     */     //   74: invokevirtual combinerTaskSupport : ()Lscala/collection/parallel/TaskSupport;
/*     */     //   77: new scala/collection/parallel/immutable/HashMapCombiner$CreateGroupedTrie
/*     */     //   80: dup
/*     */     //   81: aload_0
/*     */     //   82: aload_1
/*     */     //   83: aload_2
/*     */     //   84: aload #5
/*     */     //   86: iconst_0
/*     */     //   87: aload_2
/*     */     //   88: arraylength
/*     */     //   89: invokespecial <init> : (Lscala/collection/parallel/immutable/HashMapCombiner;Lscala/Function0;[Lscala/collection/mutable/UnrolledBuffer$Unrolled;[Lscala/collection/immutable/HashMap;II)V
/*     */     //   92: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*     */     //   97: pop
/*     */     //   98: iconst_0
/*     */     //   99: istore #4
/*     */     //   101: iconst_0
/*     */     //   102: istore_3
/*     */     //   103: iload_3
/*     */     //   104: getstatic scala/collection/parallel/immutable/HashMapCombiner$.MODULE$ : Lscala/collection/parallel/immutable/HashMapCombiner$;
/*     */     //   107: invokevirtual rootsize : ()I
/*     */     //   110: if_icmpge -> 137
/*     */     //   113: aload_0
/*     */     //   114: invokevirtual buckets : ()[Lscala/collection/mutable/UnrolledBuffer;
/*     */     //   117: iload_3
/*     */     //   118: aaload
/*     */     //   119: ifnull -> 130
/*     */     //   122: iload #4
/*     */     //   124: iconst_1
/*     */     //   125: iload_3
/*     */     //   126: ishl
/*     */     //   127: ior
/*     */     //   128: istore #4
/*     */     //   130: iload_3
/*     */     //   131: iconst_1
/*     */     //   132: iadd
/*     */     //   133: istore_3
/*     */     //   134: goto -> 103
/*     */     //   137: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   140: aload #5
/*     */     //   142: checkcast [Ljava/lang/Object;
/*     */     //   145: invokevirtual refArrayOps : ([Ljava/lang/Object;)Lscala/collection/mutable/ArrayOps;
/*     */     //   148: iconst_0
/*     */     //   149: invokestatic boxToInteger : (I)Ljava/lang/Integer;
/*     */     //   152: new scala/collection/parallel/immutable/HashMapCombiner$$anonfun$6
/*     */     //   155: dup
/*     */     //   156: aload_0
/*     */     //   157: invokespecial <init> : (Lscala/collection/parallel/immutable/HashMapCombiner;)V
/*     */     //   160: invokeinterface foldLeft : (Ljava/lang/Object;Lscala/Function2;)Ljava/lang/Object;
/*     */     //   165: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*     */     //   168: istore #6
/*     */     //   170: iload #6
/*     */     //   172: iconst_0
/*     */     //   173: if_icmpne -> 186
/*     */     //   176: new scala/collection/parallel/immutable/ParHashMap
/*     */     //   179: dup
/*     */     //   180: invokespecial <init> : ()V
/*     */     //   183: goto -> 230
/*     */     //   186: iload #6
/*     */     //   188: iconst_1
/*     */     //   189: if_icmpne -> 206
/*     */     //   192: new scala/collection/parallel/immutable/ParHashMap
/*     */     //   195: dup
/*     */     //   196: aload #5
/*     */     //   198: iconst_0
/*     */     //   199: aaload
/*     */     //   200: invokespecial <init> : (Lscala/collection/immutable/HashMap;)V
/*     */     //   203: goto -> 230
/*     */     //   206: new scala/collection/immutable/HashMap$HashTrieMap
/*     */     //   209: dup
/*     */     //   210: iload #4
/*     */     //   212: aload #5
/*     */     //   214: iload #6
/*     */     //   216: invokespecial <init> : (I[Lscala/collection/immutable/HashMap;I)V
/*     */     //   219: astore #7
/*     */     //   221: new scala/collection/parallel/immutable/ParHashMap
/*     */     //   224: dup
/*     */     //   225: aload #7
/*     */     //   227: invokespecial <init> : (Lscala/collection/immutable/HashMap;)V
/*     */     //   230: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #203	-> 0
/*     */     //   #204	-> 66
/*     */     //   #206	-> 73
/*     */     //   #208	-> 98
/*     */     //   #209	-> 101
/*     */     //   #210	-> 103
/*     */     //   #211	-> 113
/*     */     //   #212	-> 130
/*     */     //   #214	-> 137
/*     */     //   #216	-> 170
/*     */     //   #217	-> 186
/*     */     //   #219	-> 206
/*     */     //   #220	-> 221
/*     */     //   #202	-> 230
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	231	0	this	Lscala/collection/parallel/immutable/HashMapCombiner;
/*     */     //   0	231	1	cbf	Lscala/Function0;
/*     */     //   66	165	2	bucks	[Lscala/collection/mutable/UnrolledBuffer$Unrolled;
/*     */     //   73	158	5	root	[Lscala/collection/immutable/HashMap;
/*     */     //   101	130	4	bitmap	I
/*     */     //   103	128	3	i	I
/*     */     //   170	61	6	sz	I
/*     */     //   221	9	7	trie	Lscala/collection/immutable/HashMap$HashTrieMap;
/*     */   }
/*     */   
/*     */   public class HashMapCombiner$$anonfun$4 extends AbstractFunction1<UnrolledBuffer<Tuple2<K, V>>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(UnrolledBuffer x$7) {
/* 203 */       return !(x$7 == null);
/*     */     }
/*     */     
/*     */     public HashMapCombiner$$anonfun$4(HashMapCombiner $outer) {}
/*     */   }
/*     */   
/*     */   public class HashMapCombiner$$anonfun$5 extends AbstractFunction1<UnrolledBuffer<Tuple2<K, V>>, UnrolledBuffer.Unrolled<Tuple2<K, V>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final UnrolledBuffer.Unrolled<Tuple2<K, V>> apply(UnrolledBuffer x$8) {
/* 203 */       return x$8.headPtr();
/*     */     }
/*     */     
/*     */     public HashMapCombiner$$anonfun$5(HashMapCombiner $outer) {}
/*     */   }
/*     */   
/*     */   public class HashMapCombiner$$anonfun$6 extends AbstractFunction2<Object, HashMap<K, Object>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int apply(int x$9, HashMap x$10) {
/* 214 */       return x$9 + x$10.size();
/*     */     }
/*     */     
/*     */     public HashMapCombiner$$anonfun$6(HashMapCombiner $outer) {}
/*     */   }
/*     */   
/*     */   public String toString() {
/* 225 */     return (new StringBuilder()).append("HashTrieCombiner(sz: ").append(BoxesRunTime.boxToInteger(size())).append(")").toString();
/*     */   }
/*     */   
/*     */   public static <K, V> HashMapCombiner<K, V> apply() {
/*     */     return HashMapCombiner$.MODULE$.apply();
/*     */   }
/*     */   
/*     */   public class CreateTrie implements Task<BoxedUnit, CreateTrie> {
/*     */     private final UnrolledBuffer.Unrolled<Tuple2<K, V>>[] bucks;
/*     */     
/*     */     private final HashMap<K, V>[] root;
/*     */     
/*     */     private final int offset;
/*     */     
/*     */     private final int howmany;
/*     */     
/*     */     private volatile BoxedUnit result;
/*     */     
/*     */     private volatile Throwable throwable;
/*     */     
/*     */     public Throwable throwable() {
/* 231 */       return this.throwable;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void throwable_$eq(Throwable x$1) {
/* 231 */       this.throwable = x$1;
/*     */     }
/*     */     
/*     */     public CreateTrie repr() {
/* 231 */       return (CreateTrie)Task.class.repr(this);
/*     */     }
/*     */     
/*     */     public void merge(Object that) {
/* 231 */       Task.class.merge(this, that);
/*     */     }
/*     */     
/*     */     public void forwardThrowable() {
/* 231 */       Task.class.forwardThrowable(this);
/*     */     }
/*     */     
/*     */     public void tryLeaf(Option lastres) {
/* 231 */       Task.class.tryLeaf(this, lastres);
/*     */     }
/*     */     
/*     */     public void tryMerge(Object t) {
/* 231 */       Task.class.tryMerge(this, t);
/*     */     }
/*     */     
/*     */     public void mergeThrowables(Task that) {
/* 231 */       Task.class.mergeThrowables(this, that);
/*     */     }
/*     */     
/*     */     public void signalAbort() {
/* 231 */       Task.class.signalAbort(this);
/*     */     }
/*     */     
/*     */     public CreateTrie(HashMapCombiner $outer, UnrolledBuffer.Unrolled[] bucks, HashMap[] root, int offset, int howmany) {
/* 231 */       Task.class.$init$(this);
/* 233 */       this.result = BoxedUnit.UNIT;
/*     */     }
/*     */     
/*     */     public void result() {}
/*     */     
/*     */     public void result_$eq(BoxedUnit x$1) {
/* 233 */       this.result = x$1;
/*     */     }
/*     */     
/*     */     public void leaf(Option prev) {
/* 235 */       int i = this.offset;
/* 236 */       int until = this.offset + this.howmany;
/* 237 */       while (i < until) {
/* 238 */         this.root[i] = createTrie(this.bucks[i]);
/* 239 */         i++;
/*     */       } 
/* 241 */       result();
/* 241 */       result_$eq(BoxedUnit.UNIT);
/*     */     }
/*     */     
/*     */     private HashMap<K, V> createTrie(UnrolledBuffer.Unrolled elems) {
/* 244 */       HashMap<K, V> trie = new HashMap();
/* 246 */       UnrolledBuffer.Unrolled unrolled = elems;
/* 247 */       int i = 0;
/* 248 */       while (unrolled != null) {
/* 249 */         Tuple2[] chunkarr = (Tuple2[])unrolled.array();
/* 250 */         int chunksz = unrolled.size();
/* 251 */         while (i < chunksz) {
/* 252 */           Tuple2 kv = chunkarr[i];
/* 253 */           int hc = trie.computeHash(kv._1());
/* 254 */           trie = trie.updated0(kv._1(), hc, HashMapCombiner$.MODULE$.rootbits(), kv._2(), kv, null);
/* 255 */           i++;
/*     */         } 
/* 257 */         i = 0;
/* 258 */         unrolled = unrolled.next();
/*     */       } 
/* 261 */       return trie;
/*     */     }
/*     */     
/*     */     public List<CreateTrie> split() {
/* 264 */       int fp = this.howmany / 2;
/* 265 */       (new CreateTrie[2])[0] = new CreateTrie(scala$collection$parallel$immutable$HashMapCombiner$CreateTrie$$$outer(), this.bucks, this.root, this.offset, fp);
/* 265 */       (new CreateTrie[2])[1] = new CreateTrie(scala$collection$parallel$immutable$HashMapCombiner$CreateTrie$$$outer(), this.bucks, this.root, this.offset + fp, this.howmany - fp);
/* 265 */       return List$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new CreateTrie[2]));
/*     */     }
/*     */     
/*     */     public boolean shouldSplitFurther() {
/* 267 */       return (this.howmany > package$.MODULE$.thresholdFromSize(this.root.length, scala$collection$parallel$immutable$HashMapCombiner$CreateTrie$$$outer().combinerTaskSupport().parallelismLevel()));
/*     */     }
/*     */   }
/*     */   
/*     */   public class CreateGroupedTrie<Repr> implements Task<BoxedUnit, CreateGroupedTrie<Repr>> {
/*     */     private final Function0<Combiner<V, Repr>> cbf;
/*     */     
/*     */     private final UnrolledBuffer.Unrolled<Tuple2<K, V>>[] bucks;
/*     */     
/*     */     private final HashMap<K, Object>[] root;
/*     */     
/*     */     private final int offset;
/*     */     
/*     */     private final int howmany;
/*     */     
/*     */     private volatile BoxedUnit result;
/*     */     
/*     */     private volatile Throwable throwable;
/*     */     
/*     */     public Throwable throwable() {
/* 270 */       return this.throwable;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void throwable_$eq(Throwable x$1) {
/* 270 */       this.throwable = x$1;
/*     */     }
/*     */     
/*     */     public CreateGroupedTrie<Repr> repr() {
/* 270 */       return (CreateGroupedTrie<Repr>)Task.class.repr(this);
/*     */     }
/*     */     
/*     */     public void merge(Object that) {
/* 270 */       Task.class.merge(this, that);
/*     */     }
/*     */     
/*     */     public void forwardThrowable() {
/* 270 */       Task.class.forwardThrowable(this);
/*     */     }
/*     */     
/*     */     public void tryLeaf(Option lastres) {
/* 270 */       Task.class.tryLeaf(this, lastres);
/*     */     }
/*     */     
/*     */     public void tryMerge(Object t) {
/* 270 */       Task.class.tryMerge(this, t);
/*     */     }
/*     */     
/*     */     public void mergeThrowables(Task that) {
/* 270 */       Task.class.mergeThrowables(this, that);
/*     */     }
/*     */     
/*     */     public void signalAbort() {
/* 270 */       Task.class.signalAbort(this);
/*     */     }
/*     */     
/*     */     public CreateGroupedTrie(HashMapCombiner $outer, Function0<Combiner<V, Repr>> cbf, UnrolledBuffer.Unrolled[] bucks, HashMap[] root, int offset, int howmany) {
/* 270 */       Task.class.$init$(this);
/* 272 */       this.result = BoxedUnit.UNIT;
/*     */     }
/*     */     
/*     */     public void result() {}
/*     */     
/*     */     public void result_$eq(BoxedUnit x$1) {
/* 272 */       this.result = x$1;
/*     */     }
/*     */     
/*     */     public void leaf(Option prev) {
/* 274 */       int i = this.offset;
/* 275 */       int until = this.offset + this.howmany;
/* 276 */       while (i < until) {
/* 277 */         this.root[i] = (HashMap)createGroupedTrie(this.bucks[i]);
/* 278 */         i++;
/*     */       } 
/* 280 */       result();
/* 280 */       result_$eq(BoxedUnit.UNIT);
/*     */     }
/*     */     
/*     */     private HashMap<K, Repr> createGroupedTrie(UnrolledBuffer.Unrolled elems) {
/* 283 */       HashMap<K, Combiner<V, Repr>> trie = new HashMap();
/* 285 */       UnrolledBuffer.Unrolled unrolled = elems;
/* 286 */       int i = 0;
/* 287 */       while (unrolled != null) {
/* 288 */         Tuple2[] chunkarr = (Tuple2[])unrolled.array();
/* 289 */         int chunksz = unrolled.size();
/*     */         while (true) {
/*     */           Combiner combiner;
/*     */           Tuple2 kv;
/* 290 */           if (i < chunksz) {
/* 291 */             kv = chunkarr[i];
/* 292 */             int hc = trie.computeHash(kv._1());
/* 295 */             Option option = trie.get0(kv._1(), hc, HashMapCombiner$.MODULE$.rootbits());
/* 296 */             if (option instanceof Some) {
/* 296 */               Some some = (Some)option;
/* 296 */               Combiner combiner1 = (Combiner)some.x();
/*     */             } 
/* 297 */             if (None$.MODULE$ == null) {
/* 297 */               if (option != null)
/*     */                 throw new MatchError(option); 
/* 297 */             } else if (!None$.MODULE$.equals(option)) {
/*     */               throw new MatchError(option);
/*     */             } 
/* 298 */             Combiner cmb = (Combiner)this.cbf.apply();
/* 299 */             trie = trie.updated0(kv._1(), hc, HashMapCombiner$.MODULE$.rootbits(), cmb, null, null);
/* 300 */             combiner = cmb;
/*     */           } else {
/*     */             break;
/*     */           } 
/*     */           combiner.$plus$eq(kv._2());
/* 303 */           i++;
/*     */         } 
/* 305 */         i = 0;
/* 306 */         unrolled = unrolled.next();
/*     */       } 
/* 309 */       return evaluateCombiners(trie);
/*     */     }
/*     */     
/*     */     private HashMap<K, Repr> evaluateCombiners(HashMap<K, Repr> trie) {
/*     */       HashMap<K, Repr> hashMap;
/* 311 */       if (trie instanceof HashMap.HashMap1) {
/* 311 */         HashMap.HashMap1 hashMap11 = (HashMap.HashMap1)trie;
/* 313 */         Object evaledvalue = ((Builder)hashMap11.value()).result();
/* 314 */         HashMap.HashMap1 hashMap12 = new HashMap.HashMap1(hashMap11.key(), hashMap11.hash(), evaledvalue, null);
/* 315 */       } else if (trie instanceof HashMap.HashMapCollision1) {
/* 315 */         HashMap.HashMapCollision1 hashMapCollision11 = (HashMap.HashMapCollision1)trie;
/* 316 */         ListMap evaledkvs = (ListMap)hashMapCollision11.kvs().map((Function1)new HashMapCombiner$CreateGroupedTrie$$anonfun$7(this), ListMap$.MODULE$.canBuildFrom());
/* 317 */         HashMap.HashMapCollision1 hashMapCollision12 = new HashMap.HashMapCollision1(hashMapCollision11.hash(), evaledkvs);
/* 318 */       } else if (trie instanceof HashMap.HashTrieMap) {
/* 318 */         HashMap.HashTrieMap hashTrieMap1 = (HashMap.HashTrieMap)trie;
/* 319 */         int i = 0;
/* 320 */         while (i < (hashTrieMap1.elems()).length) {
/* 321 */           hashTrieMap1.elems()[i] = evaluateCombiners(hashTrieMap1.elems()[i]);
/* 322 */           i++;
/*     */         } 
/* 324 */         HashMap.HashTrieMap hashTrieMap2 = hashTrieMap1;
/*     */       } else {
/* 325 */         hashMap = trie;
/*     */       } 
/*     */       return hashMap;
/*     */     }
/*     */     
/*     */     public class HashMapCombiner$CreateGroupedTrie$$anonfun$7 extends AbstractFunction1<Tuple2<Object, Object>, Tuple2<Object, Repr>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Tuple2<Object, Repr> apply(Tuple2 p) {
/*     */         return new Tuple2(p._1(), ((Builder)p._2()).result());
/*     */       }
/*     */       
/*     */       public HashMapCombiner$CreateGroupedTrie$$anonfun$7(HashMapCombiner.CreateGroupedTrie $outer) {}
/*     */     }
/*     */     
/*     */     public List<CreateGroupedTrie<Repr>> split() {
/* 328 */       int fp = this.howmany / 2;
/* 329 */       (new CreateGroupedTrie[2])[0] = new CreateGroupedTrie(scala$collection$parallel$immutable$HashMapCombiner$CreateGroupedTrie$$$outer(), this.cbf, this.bucks, this.root, this.offset, fp);
/* 329 */       (new CreateGroupedTrie[2])[1] = new CreateGroupedTrie(scala$collection$parallel$immutable$HashMapCombiner$CreateGroupedTrie$$$outer(), this.cbf, this.bucks, this.root, this.offset + fp, this.howmany - fp);
/* 329 */       return List$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new CreateGroupedTrie[2]));
/*     */     }
/*     */     
/*     */     public boolean shouldSplitFurther() {
/* 331 */       return (this.howmany > package$.MODULE$.thresholdFromSize(this.root.length, scala$collection$parallel$immutable$HashMapCombiner$CreateGroupedTrie$$$outer().combinerTaskSupport().parallelismLevel()));
/*     */     }
/*     */   }
/*     */   
/*     */   public static class HashMapCombiner$$anon$1 extends HashMapCombiner<K, V> {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\immutable\HashMapCombiner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */