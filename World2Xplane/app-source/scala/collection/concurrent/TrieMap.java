/*     */ package scala.collection.concurrent;
/*     */ 
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.Option$;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.Tuple3;
/*     */ import scala.collection.CustomParallelizable;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenMapLike;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenSet;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.IterableLike;
/*     */ import scala.collection.IterableView;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Map;
/*     */ import scala.collection.MapLike;
/*     */ import scala.collection.Parallel;
/*     */ import scala.collection.Parallelizable;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Set;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.TraversableView;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.FilterMonadic;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.generic.GenericTraversableTemplate;
/*     */ import scala.collection.generic.Growable;
/*     */ import scala.collection.generic.Shrinkable;
/*     */ import scala.collection.generic.Subtractable;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.collection.immutable.Stream;
/*     */ import scala.collection.immutable.Vector;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.Cloneable;
/*     */ import scala.collection.mutable.Iterable;
/*     */ import scala.collection.mutable.Map;
/*     */ import scala.collection.mutable.MapLike;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.collection.mutable.Traversable;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.mutable.ParTrieMap;
/*     */ import scala.math.Equiv;
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Ordering;
/*     */ import scala.package$;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.util.hashing.Hashing;
/*     */ import scala.util.hashing.Hashing$;
/*     */ import scala.util.hashing.package$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\rmf\001B\001\003\005%\021q\001\026:jK6\013\007O\003\002\004\t\005Q1m\0348dkJ\024XM\034;\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\026\007))rd\005\004\001\027=\t\003F\016\t\003\0315i\021AB\005\003\035\031\021a!\0218z%\0264\007\003\002\t\022'yi\021AA\005\003%\t\0211!T1q!\t!R\003\004\001\005\013Y\001!\031A\f\003\003-\013\"\001G\016\021\0051I\022B\001\016\007\005\035qu\016\0365j]\036\004\"\001\004\017\n\005u1!aA!osB\021Ac\b\003\006A\001\021\ra\006\002\002-B)!%J\n\037O5\t1E\003\002%\t\0059Q.\036;bE2,\027B\001\024$\005\035i\025\r\035'jW\026\004B\001\005\001\024=A!\021F\013\0270\033\005!\021BA\026\005\005Q\031Uo\035;p[B\013'/\0317mK2L'0\0312mKB!A\"L\n\037\023\tqcA\001\004UkBdWM\r\t\005aQ\032b$D\0012\025\t!#G\003\0024\t\005A\001/\031:bY2,G.\003\0026c\tQ\001+\031:Ue&,W*\0319\021\00519\024B\001\035\007\0051\031VM]5bY&T\030M\0317f\021!Q\004A!A!\002\023Y\021!\001:\t\021q\002!\021!Q\001\nu\nQA\035;va\022\004BA\020$(\0275\tqH\003\002A\003\0061\021\r^8nS\016T!a\001\"\013\005\r#\025\001B;uS2T\021!R\001\005U\0064\030-\003\002H\tY\022\t^8nS\016\024VMZ3sK:\034WMR5fY\022,\006\017Z1uKJD\001\"\023\001\003\002\003\006IAS\001\006Q\006\034\bN\032\t\004\027>\033R\"\001'\013\0055s\025a\0025bg\"Lgn\032\006\003\007\032I!\001\025'\003\017!\0137\017[5oO\"A!\013\001B\001B\003%1+\001\002fMB\031A\013X\n\017\005USfB\001,Z\033\0059&B\001-\t\003\031a$o\\8u}%\tq!\003\002\\\r\0059\001/Y2lC\036,\027BA/_\005\025)\025/^5w\025\tYf\001C\003a\001\021%\021-\001\004=S:LGO\020\013\006O\t\034G-\032\005\006u}\003\ra\003\005\006y}\003\r!\020\005\006\023~\003\rA\023\005\006%~\003\ra\025\005\bO\002\001\r\021\"\003i\003)A\027m\0355j]\036|'M[\013\002\025\"9!\016\001a\001\n\023Y\027A\0045bg\"LgnZ8cU~#S-\035\013\003Y>\004\"\001D7\n\00594!\001B+oSRDq\001]5\002\002\003\007!*A\002yIEBaA\035\001!B\023Q\025a\0035bg\"LgnZ8cU\002Bq\001\036\001A\002\023%Q/A\006fcV\fG.\033;z_\nTW#A*\t\017]\004\001\031!C\005q\006yQ-];bY&$\030p\0342k?\022*\027\017\006\002ms\"9\001O^A\001\002\004\031\006BB>\001A\003&1+\001\007fcV\fG.\033;z_\nT\007\005C\004~\001\001\007I\021\002@\002\027I|w\016^;qI\006$XM]\013\002{!I\021\021\001\001A\002\023%\0211A\001\020e>|G/\0369eCR,'o\030\023fcR\031A.!\002\t\017A|\030\021!a\001{!9\021\021\002\001!B\023i\024\001\004:p_R,\b\017Z1uKJ\004\003\"B'\001\t\003A\007BBA\b\001\021\005Q/\001\005fcV\fG.\033;z\021%\t\031\002\001a\001\n\003\t)\"\001\003s_>$X#A\006\t\023\005e\001\0011A\005\002\005m\021\001\003:p_R|F%Z9\025\0071\fi\002\003\005q\003/\t\t\0211\001\f\021\035\t\t\003\001Q!\n-\tQA]8pi\002BC!a\b\002&A\031A\"a\n\n\007\005%bA\001\005w_2\fG/\0337f\021\031\001\007\001\"\001\002.Q)q%a\f\0022!1\021*a\013A\002)CaAUA\026\001\004\031\006B\0021\001\t\003\t)\004F\001(\021\035\tI\004\001C\005\003w\t1b\036:ji\026|%M[3diR\031A.!\020\t\021\005}\022q\007a\001\003\003\n1a\\;u!\021\t\031%!\023\016\005\005\025#bAA$\t\006\021\021n\\\005\005\003\027\n)E\001\nPE*,7\r^(viB,Ho\025;sK\006l\007bBA(\001\021%\021\021K\001\013e\026\fGm\0242kK\016$Hc\0017\002T!A\021QKA'\001\004\t9&\001\002j]B!\0211IA-\023\021\tY&!\022\003#=\023'.Z2u\023:\004X\017^*ue\026\fW\016C\004\002`\001!\t!!\031\002\021\r\0135k\030*P\037R#b!a\031\002j\0055\004c\001\007\002f%\031\021q\r\004\003\017\t{w\016\\3b]\"9\0211NA/\001\004Y\021AA8w\021\035\ty'!\030A\002-\t!A\034<\t\017\005M\004\001\"\001\002v\005A!/Z1e%>|G\017\006\003\002x\005u\004#\002\t\002zMq\022bAA>\005\t)\021JT8eK\"Q\021qPA9!\003\005\r!a\031\002\013\005\024wN\035;\t\017\005\r\005\001\"\001\002\006\006y!\013R\"T'~\023V)\021#`%>{E\013\006\003\002x\005\035\005BCA@\003\003\003\n\0211\001\002d!9\0211\022\001\005\n\0055\025A\004*E\007N\033vlQ8na2,G/\032\013\005\003o\ny\t\003\005\002\000\005%\005\031AA2Q\021\tI)a%\021\t\005U\0251T\007\003\003/S1!!'\007\003)\tgN\\8uCRLwN\\\005\005\003;\0139JA\004uC&d'/Z2\t\017\005\005\006\001\"\003\002$\006Q!\013R\"T'~\023vj\024+\025\021\005\r\024QUAT\003cC\001\"a\033\002 \002\007\021q\017\005\t\003S\013y\n1\001\002,\006aQ\r\0379fGR,G-\\1j]B)\001#!,\024=%\031\021q\026\002\003\0215\013\027N\034(pI\026D\001\"a\034\002 \002\007\021q\017\005\b\003k\003A\021BA\\\003!Ign]3si\"\034Gc\0027\002:\006u\026q\031\005\b\003w\013\031\f1\001\024\003\005Y\007\002CA`\003g\003\r!!1\002\005!\034\007c\001\007\002D&\031\021Q\031\004\003\007%sG\017C\004\002J\006M\006\031\001\020\002\003YDC!a-\002\024\"9\021q\032\001\005\n\005E\027AC5og\026\024H/\0334iGRQ\0211[Am\0037\fi.a8\021\t1\t)NH\005\004\003/4!AB(qi&|g\016C\004\002<\0065\007\031A\n\t\021\005}\026Q\032a\001\003\003Dq!!3\002N\002\007a\004C\004\002b\0065\007\031A\006\002\t\r|g\016\032\025\005\003\033\f\031\nC\004\002h\002!I!!;\002\0211|wn[;qQ\016$RaCAv\003[Dq!a/\002f\002\0071\003\003\005\002@\006\025\b\031AAaQ\021\t)/a%\t\017\005M\b\001\"\003\002v\006A!/Z7pm\026D7\r\006\005\002T\006]\030\021`A~\021\035\tY,!=A\002MAq!!3\002r\002\007a\004\003\005\002@\006E\b\031AAaQ\021\t\t0a%\t\017\t\005\001\001\"\001\003\004\00511\017\036:j]\036,\"A!\002\021\t\t\035!Q\002\b\004\031\t%\021b\001B\006\r\0051\001K]3eK\032LAAa\004\003\022\t11\013\036:j]\036T1Aa\003\007\021\035\021)\002\001C!\005/\t1a]3r+\0059\003b\002B\016\001\021\005#QD\001\004a\006\024X#A\030\t\017\t\005\002\001\"\021\003\030\005)Q-\0349us\"9!Q\005\001\005\002\t\035\022AC5t%\026\fGm\0248msV\021\0211\r\005\b\005W\001A\021\001B\024\003-qwN\034*fC\022|e\016\\=\t\017\t=\002\001\"\001\0026\005A1O\\1qg\"|G\017\013\003\003.\005M\005b\002B\033\001\021\005!qG\001\021e\026\fGm\0248msNs\027\r]:i_R$\"A!\017\021\013%\022Yd\005\020\n\005I!\001\006\002B\032\003'CqA!\021\001\t\003\022\031%A\003dY\026\f'\017F\001mQ\021\021y$a%\t\017\t%\003\001\"\001\003L\005Y1m\\7qkR,\007*Y:i)\021\t\tM!\024\t\017\005m&q\ta\001'!9!\021\013\001\005\002\tM\023A\0027p_.,\b\017F\002\037\005+Bq!a/\003P\001\0071\003C\004\003Z\001!\tEa\027\002\013\005\004\b\017\\=\025\007y\021i\006C\004\002<\n]\003\031A\n\t\017\t\005\004\001\"\001\003d\005\031q-\032;\025\t\005M'Q\r\005\b\003w\023y\0061\001\024\021\035\021I\007\001C!\005W\n1\001];u)\031\t\031N!\034\003r!9!q\016B4\001\004\031\022aA6fs\"9!1\017B4\001\004q\022!\002<bYV,\007b\002B<\001\021\005#\021P\001\007kB$\027\r^3\025\0131\024YH! \t\017\005m&Q\017a\001'!9\021\021\032B;\001\004q\002b\002BA\001\021\005!1Q\001\tIAdWo\035\023fcR!!Q\021BD\033\005\001\001b\002BE\005\002\r\001L\001\003WZDqA!$\001\t\003\022y)\001\004sK6|g/\032\013\005\003'\024\t\nC\004\002<\n-\005\031A\n\t\017\tU\005\001\"\001\003\030\006IA%\\5okN$S-\035\013\005\005\013\023I\nC\004\002<\nM\005\031A\n\t\017\tu\005\001\"\001\003 \006Y\001/\036;JM\006\0237/\0328u)\031\t\031N!)\003$\"9\0211\030BN\001\004\031\002bBAe\0057\003\rA\b\005\b\005\033\003A\021\001BT)\031\t\031G!+\003,\"9\0211\030BS\001\004\031\002bBAe\005K\003\rA\b\005\b\005_\003A\021\001BY\003\035\021X\r\0357bG\026$\002\"a\031\0034\nU&\021\030\005\b\003w\023i\0131\001\024\021\035\0219L!,A\002y\t\001b\0347em\006dW/\032\005\b\005w\023i\0131\001\037\003!qWm\036<bYV,\007b\002BX\001\021\005!q\030\013\007\003'\024\tMa1\t\017\005m&Q\030a\001'!9\021\021\032B_\001\004q\002b\002Bd\001\021\005!\021Z\001\tSR,'/\031;peV\021!1\032\t\005S\t5G&C\002\003P\022\021\001\"\023;fe\006$xN\035\005\b\005'\004A\021\002Bk\003)\031\027m\0315fINK'0\032\013\003\003\003DqA!7\001\t\003\022Y.\001\003tSj,WCAAa\021\035\021y\016\001C!\005C\fAb\035;sS:<\007K]3gSb,\"Aa9\021\t\t\025(1^\007\003\005OT1A!;E\003\021a\027M\\4\n\t\t=!q\035\005\n\005_\004\021\023!C\001\005c\f!C]3bIJ{w\016\036\023eK\032\fW\017\034;%cU\021!1\037\026\005\003G\022)p\013\002\003xB!!\021 B\000\033\t\021YP\003\003\003~\006]\025!C;oG\",7m[3e\023\021\031\tAa?\003#Ut7\r[3dW\026$g+\031:jC:\034W\rC\005\004\006\001\t\n\021\"\001\003r\006I\"\013R\"T'~\023V)\021#`%>{E\013\n3fM\006,H\016\036\0232Q\025\0011\021BB\b!\ra11B\005\004\007\0331!\001E*fe&\fGNV3sg&|g.V%E=!9KE2M\017C\005}}aBB\n\005!\0051QC\001\b)JLW-T1q!\r\0012q\003\004\007\003\tA\ta!\007\024\013\r]11\004\034\021\r\ru11EB\024\033\t\031yBC\002\004\"\021\tqaZ3oKJL7-\003\003\004&\r}!!E'vi\006\024G.Z'ba\032\0137\r^8ssB\021\001\003\001\005\bA\016]A\021AB\026)\t\031)\002\003\006\0040\r]!\031!C\001\007c\tA\"\0338pI\026,\b\017Z1uKJ,\"aa\r\021\ry25QGB'a\031\0319da\020\004JA9\001c!\017\004>\r\035\023bAB\036\005\tI\021JT8eK\n\0137/\032\t\004)\r}BaCB!\007\007\n\t\021!A\003\002]\0211a\030\0235\021%\031)ea\006!\002\023\031\031$A\007j]>$W-\0369eCR,'\017\t\t\004)\r%CaCB&\007\007\n\t\021!A\003\002]\0211a\030\0236a\031\031yea\025\004ZA9\001#!,\004R\r]\003c\001\013\004T\021Y1QKB\"\003\003\005\tQ!\001\030\005\ryFE\016\t\004)\reCaCB.\007\007\n\t\021!A\003\002]\0211a\030\0238\021!\031yfa\006\005\004\r\005\024\001D2b]\n+\030\016\0343Ge>lWCBB2\007w\032y(\006\002\004fAQ1QDB4\007W\0329h!!\n\t\r%4q\004\002\r\007\006t')^5mI\032\023x.\034\t\005\007[\032y'\004\002\004\030%!1\021OB:\005\021\031u\016\0347\n\t\rU4q\004\002\016\017\026tW*\0319GC\016$xN]=\021\r1i3\021PB?!\r!21\020\003\007-\ru#\031A\f\021\007Q\031y\b\002\004!\007;\022\ra\006\t\007!\001\031Ih! \t\021\t\0052q\003C\001\007\013+baa\"\004\016\016EUCABE!\031\001\002aa#\004\020B\031Ac!$\005\rY\031\031I1\001\030!\r!2\021\023\003\007A\r\r%\031A\f\007\017\rU5q\003\001\004\030\nqQ*\0318hY\026$\007*Y:iS:<W\003BBM\007?\033Raa%\f\0077\003BaS(\004\036B\031Aca(\005\rY\031\031J1\001\030\021\035\00171\023C\001\007G#\"a!*\021\r\r541SBO\021!\031Ika%\005\002\r-\026\001\0025bg\"$B!!1\004.\"A\0211XBT\001\004\031i\n\003\006\0042\016]\021\021!C\005\007g\0131B]3bIJ+7o\0347wKR\0211Q\027\t\005\005K\0349,\003\003\004:\n\035(AB(cU\026\034G\017")
/*     */ public final class TrieMap<K, V> implements Map<K, V>, MapLike<K, V, TrieMap<K, V>>, CustomParallelizable<Tuple2<K, V>, ParTrieMap<K, V>>, Serializable {
/*     */   public static final long serialVersionUID = -6402774413839597105L;
/*     */   
/*     */   private final Hashing<K> hashf;
/*     */   
/*     */   private final Equiv<K> ef;
/*     */   
/*     */   private Hashing<K> hashingobj;
/*     */   
/*     */   private Equiv<K> equalityobj;
/*     */   
/*     */   private AtomicReferenceFieldUpdater<TrieMap<K, V>, Object> rootupdater;
/*     */   
/*     */   private volatile Object root;
/*     */   
/*     */   public Combiner<Tuple2<K, V>, ParTrieMap<K, V>> parCombiner() {
/* 632 */     return CustomParallelizable.class.parCombiner(this);
/*     */   }
/*     */   
/*     */   public Map<K, V> withDefault(Function1 d) {
/* 632 */     return Map.class.withDefault(this, d);
/*     */   }
/*     */   
/*     */   public Map<K, V> withDefaultValue(Object d) {
/* 632 */     return Map.class.withDefaultValue(this, d);
/*     */   }
/*     */   
/*     */   public Builder<Tuple2<K, V>, TrieMap<K, V>> newBuilder() {
/* 632 */     return MapLike.class.newBuilder(this);
/*     */   }
/*     */   
/*     */   public <B1> Map<K, B1> updated(Object key, Object value) {
/* 632 */     return MapLike.class.updated(this, key, value);
/*     */   }
/*     */   
/*     */   public <B1> Map<K, B1> $plus(Tuple2 kv) {
/* 632 */     return MapLike.class.$plus(this, kv);
/*     */   }
/*     */   
/*     */   public <B1> Map<K, B1> $plus(Tuple2 elem1, Tuple2 elem2, Seq elems) {
/* 632 */     return MapLike.class.$plus(this, elem1, elem2, elems);
/*     */   }
/*     */   
/*     */   public <B1> Map<K, B1> $plus$plus(GenTraversableOnce xs) {
/* 632 */     return MapLike.class.$plus$plus(this, xs);
/*     */   }
/*     */   
/*     */   public TrieMap<K, V> $minus(Object key) {
/* 632 */     return (TrieMap<K, V>)MapLike.class.$minus(this, key);
/*     */   }
/*     */   
/*     */   public V getOrElseUpdate(Object key, Function0 op) {
/* 632 */     return (V)MapLike.class.getOrElseUpdate(this, key, op);
/*     */   }
/*     */   
/*     */   public MapLike<K, V, TrieMap<K, V>> transform(Function2 f) {
/* 632 */     return MapLike.class.transform(this, f);
/*     */   }
/*     */   
/*     */   public MapLike<K, V, TrieMap<K, V>> retain(Function2 p) {
/* 632 */     return MapLike.class.retain(this, p);
/*     */   }
/*     */   
/*     */   public TrieMap<K, V> clone() {
/* 632 */     return (TrieMap<K, V>)MapLike.class.clone(this);
/*     */   }
/*     */   
/*     */   public TrieMap<K, V> result() {
/* 632 */     return (TrieMap<K, V>)MapLike.class.result(this);
/*     */   }
/*     */   
/*     */   public TrieMap<K, V> $minus(Object elem1, Object elem2, Seq elems) {
/* 632 */     return (TrieMap<K, V>)MapLike.class.$minus(this, elem1, elem2, elems);
/*     */   }
/*     */   
/*     */   public TrieMap<K, V> $minus$minus(GenTraversableOnce xs) {
/* 632 */     return (TrieMap<K, V>)MapLike.class.$minus$minus(this, xs);
/*     */   }
/*     */   
/*     */   public Object scala$collection$mutable$Cloneable$$super$clone() {
/* 632 */     return super.clone();
/*     */   }
/*     */   
/*     */   public Shrinkable<K> $minus$eq(Object elem1, Object elem2, Seq elems) {
/* 632 */     return Shrinkable.class.$minus$eq((Shrinkable)this, elem1, elem2, elems);
/*     */   }
/*     */   
/*     */   public Shrinkable<K> $minus$minus$eq(TraversableOnce xs) {
/* 632 */     return Shrinkable.class.$minus$minus$eq((Shrinkable)this, xs);
/*     */   }
/*     */   
/*     */   public void sizeHint(int size) {
/* 632 */     Builder.class.sizeHint((Builder)this, size);
/*     */   }
/*     */   
/*     */   public void sizeHint(TraversableLike coll) {
/* 632 */     Builder.class.sizeHint((Builder)this, coll);
/*     */   }
/*     */   
/*     */   public void sizeHint(TraversableLike coll, int delta) {
/* 632 */     Builder.class.sizeHint((Builder)this, coll, delta);
/*     */   }
/*     */   
/*     */   public void sizeHintBounded(int size, TraversableLike boundingColl) {
/* 632 */     Builder.class.sizeHintBounded((Builder)this, size, boundingColl);
/*     */   }
/*     */   
/*     */   public <NewTo> Builder<Tuple2<K, V>, NewTo> mapResult(Function1 f) {
/* 632 */     return Builder.class.mapResult((Builder)this, f);
/*     */   }
/*     */   
/*     */   public Growable<Tuple2<K, V>> $plus$eq(Object elem1, Object elem2, Seq elems) {
/* 632 */     return Growable.class.$plus$eq((Growable)this, elem1, elem2, elems);
/*     */   }
/*     */   
/*     */   public Growable<Tuple2<K, V>> $plus$plus$eq(TraversableOnce xs) {
/* 632 */     return Growable.class.$plus$plus$eq((Growable)this, xs);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 632 */     return MapLike.class.isEmpty((MapLike)this);
/*     */   }
/*     */   
/*     */   public <B1> B1 getOrElse(Object key, Function0 default) {
/* 632 */     return (B1)MapLike.class.getOrElse((MapLike)this, key, default);
/*     */   }
/*     */   
/*     */   public boolean contains(Object key) {
/* 632 */     return MapLike.class.contains((MapLike)this, key);
/*     */   }
/*     */   
/*     */   public boolean isDefinedAt(Object key) {
/* 632 */     return MapLike.class.isDefinedAt((MapLike)this, key);
/*     */   }
/*     */   
/*     */   public Set<K> keySet() {
/* 632 */     return MapLike.class.keySet((MapLike)this);
/*     */   }
/*     */   
/*     */   public Iterator<K> keysIterator() {
/* 632 */     return MapLike.class.keysIterator((MapLike)this);
/*     */   }
/*     */   
/*     */   public Iterable<K> keys() {
/* 632 */     return MapLike.class.keys((MapLike)this);
/*     */   }
/*     */   
/*     */   public Iterable<V> values() {
/* 632 */     return MapLike.class.values((MapLike)this);
/*     */   }
/*     */   
/*     */   public Iterator<V> valuesIterator() {
/* 632 */     return MapLike.class.valuesIterator((MapLike)this);
/*     */   }
/*     */   
/*     */   public V default(Object key) {
/* 632 */     return (V)MapLike.class.default((MapLike)this, key);
/*     */   }
/*     */   
/*     */   public Map<K, V> filterKeys(Function1 p) {
/* 632 */     return MapLike.class.filterKeys((MapLike)this, p);
/*     */   }
/*     */   
/*     */   public <C> Map<K, C> mapValues(Function1 f) {
/* 632 */     return MapLike.class.mapValues((MapLike)this, f);
/*     */   }
/*     */   
/*     */   public TrieMap<K, V> filterNot(Function1 p) {
/* 632 */     return (TrieMap<K, V>)MapLike.class.filterNot((MapLike)this, p);
/*     */   }
/*     */   
/*     */   public Seq<Tuple2<K, V>> toSeq() {
/* 632 */     return MapLike.class.toSeq((MapLike)this);
/*     */   }
/*     */   
/*     */   public <C> Buffer<C> toBuffer() {
/* 632 */     return MapLike.class.toBuffer((MapLike)this);
/*     */   }
/*     */   
/*     */   public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/* 632 */     return MapLike.class.addString((MapLike)this, b, start, sep, end);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 632 */     return MapLike.class.toString((MapLike)this);
/*     */   }
/*     */   
/*     */   public <A1 extends K, B1> PartialFunction<A1, B1> orElse(PartialFunction that) {
/* 632 */     return PartialFunction.class.orElse((PartialFunction)this, that);
/*     */   }
/*     */   
/*     */   public <C> PartialFunction<K, C> andThen(Function1 k) {
/* 632 */     return PartialFunction.class.andThen((PartialFunction)this, k);
/*     */   }
/*     */   
/*     */   public Function1<K, Option<V>> lift() {
/* 632 */     return PartialFunction.class.lift((PartialFunction)this);
/*     */   }
/*     */   
/*     */   public <A1 extends K, B1> B1 applyOrElse(Object x, Function1 default) {
/* 632 */     return (B1)PartialFunction.class.applyOrElse((PartialFunction)this, x, default);
/*     */   }
/*     */   
/*     */   public <U> Function1<K, Object> runWith(Function1 action) {
/* 632 */     return PartialFunction.class.runWith((PartialFunction)this, action);
/*     */   }
/*     */   
/*     */   public boolean apply$mcZD$sp(double v1) {
/* 632 */     return Function1.class.apply$mcZD$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public double apply$mcDD$sp(double v1) {
/* 632 */     return Function1.class.apply$mcDD$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public float apply$mcFD$sp(double v1) {
/* 632 */     return Function1.class.apply$mcFD$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public int apply$mcID$sp(double v1) {
/* 632 */     return Function1.class.apply$mcID$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public long apply$mcJD$sp(double v1) {
/* 632 */     return Function1.class.apply$mcJD$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public void apply$mcVD$sp(double v1) {
/* 632 */     Function1.class.apply$mcVD$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public boolean apply$mcZF$sp(float v1) {
/* 632 */     return Function1.class.apply$mcZF$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public double apply$mcDF$sp(float v1) {
/* 632 */     return Function1.class.apply$mcDF$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public float apply$mcFF$sp(float v1) {
/* 632 */     return Function1.class.apply$mcFF$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public int apply$mcIF$sp(float v1) {
/* 632 */     return Function1.class.apply$mcIF$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public long apply$mcJF$sp(float v1) {
/* 632 */     return Function1.class.apply$mcJF$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public void apply$mcVF$sp(float v1) {
/* 632 */     Function1.class.apply$mcVF$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public boolean apply$mcZI$sp(int v1) {
/* 632 */     return Function1.class.apply$mcZI$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public double apply$mcDI$sp(int v1) {
/* 632 */     return Function1.class.apply$mcDI$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public float apply$mcFI$sp(int v1) {
/* 632 */     return Function1.class.apply$mcFI$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public int apply$mcII$sp(int v1) {
/* 632 */     return Function1.class.apply$mcII$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public long apply$mcJI$sp(int v1) {
/* 632 */     return Function1.class.apply$mcJI$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public void apply$mcVI$sp(int v1) {
/* 632 */     Function1.class.apply$mcVI$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public boolean apply$mcZJ$sp(long v1) {
/* 632 */     return Function1.class.apply$mcZJ$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public double apply$mcDJ$sp(long v1) {
/* 632 */     return Function1.class.apply$mcDJ$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public float apply$mcFJ$sp(long v1) {
/* 632 */     return Function1.class.apply$mcFJ$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public int apply$mcIJ$sp(long v1) {
/* 632 */     return Function1.class.apply$mcIJ$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public long apply$mcJJ$sp(long v1) {
/* 632 */     return Function1.class.apply$mcJJ$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public void apply$mcVJ$sp(long v1) {
/* 632 */     Function1.class.apply$mcVJ$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, V> compose(Function1 g) {
/* 632 */     return Function1.class.compose((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/* 632 */     return Function1.class.compose$mcZD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/* 632 */     return Function1.class.compose$mcDD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/* 632 */     return Function1.class.compose$mcFD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/* 632 */     return Function1.class.compose$mcID$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/* 632 */     return Function1.class.compose$mcJD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/* 632 */     return Function1.class.compose$mcVD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/* 632 */     return Function1.class.compose$mcZF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/* 632 */     return Function1.class.compose$mcDF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/* 632 */     return Function1.class.compose$mcFF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/* 632 */     return Function1.class.compose$mcIF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/* 632 */     return Function1.class.compose$mcJF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/* 632 */     return Function1.class.compose$mcVF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/* 632 */     return Function1.class.compose$mcZI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/* 632 */     return Function1.class.compose$mcDI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/* 632 */     return Function1.class.compose$mcFI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/* 632 */     return Function1.class.compose$mcII$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/* 632 */     return Function1.class.compose$mcJI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/* 632 */     return Function1.class.compose$mcVI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/* 632 */     return Function1.class.compose$mcZJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/* 632 */     return Function1.class.compose$mcDJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/* 632 */     return Function1.class.compose$mcFJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/* 632 */     return Function1.class.compose$mcIJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/* 632 */     return Function1.class.compose$mcJJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/* 632 */     return Function1.class.compose$mcVJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/* 632 */     return Function1.class.andThen$mcZD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/* 632 */     return Function1.class.andThen$mcDD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/* 632 */     return Function1.class.andThen$mcFD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/* 632 */     return Function1.class.andThen$mcID$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/* 632 */     return Function1.class.andThen$mcJD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/* 632 */     return Function1.class.andThen$mcVD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/* 632 */     return Function1.class.andThen$mcZF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/* 632 */     return Function1.class.andThen$mcDF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/* 632 */     return Function1.class.andThen$mcFF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/* 632 */     return Function1.class.andThen$mcIF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/* 632 */     return Function1.class.andThen$mcJF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/* 632 */     return Function1.class.andThen$mcVF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/* 632 */     return Function1.class.andThen$mcZI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/* 632 */     return Function1.class.andThen$mcDI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/* 632 */     return Function1.class.andThen$mcFI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/* 632 */     return Function1.class.andThen$mcII$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/* 632 */     return Function1.class.andThen$mcJI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/* 632 */     return Function1.class.andThen$mcVI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/* 632 */     return Function1.class.andThen$mcZJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/* 632 */     return Function1.class.andThen$mcDJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/* 632 */     return Function1.class.andThen$mcFJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/* 632 */     return Function1.class.andThen$mcIJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/* 632 */     return Function1.class.andThen$mcJJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/* 632 */     return Function1.class.andThen$mcVJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 632 */     return GenMapLike.class.hashCode((GenMapLike)this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object that) {
/* 632 */     return GenMapLike.class.equals((GenMapLike)this, that);
/*     */   }
/*     */   
/*     */   public GenericCompanion<Iterable> companion() {
/* 632 */     return Iterable.class.companion((Iterable)this);
/*     */   }
/*     */   
/*     */   public Iterable<Tuple2<K, V>> thisCollection() {
/* 632 */     return IterableLike.class.thisCollection((IterableLike)this);
/*     */   }
/*     */   
/*     */   public Iterable<Tuple2<K, V>> toCollection(Object repr) {
/* 632 */     return IterableLike.class.toCollection((IterableLike)this, repr);
/*     */   }
/*     */   
/*     */   public <U> void foreach(Function1 f) {
/* 632 */     IterableLike.class.foreach((IterableLike)this, f);
/*     */   }
/*     */   
/*     */   public boolean forall(Function1 p) {
/* 632 */     return IterableLike.class.forall((IterableLike)this, p);
/*     */   }
/*     */   
/*     */   public boolean exists(Function1 p) {
/* 632 */     return IterableLike.class.exists((IterableLike)this, p);
/*     */   }
/*     */   
/*     */   public Option<Tuple2<K, V>> find(Function1 p) {
/* 632 */     return IterableLike.class.find((IterableLike)this, p);
/*     */   }
/*     */   
/*     */   public <B> B foldRight(Object z, Function2 op) {
/* 632 */     return (B)IterableLike.class.foldRight((IterableLike)this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B reduceRight(Function2 op) {
/* 632 */     return (B)IterableLike.class.reduceRight((IterableLike)this, op);
/*     */   }
/*     */   
/*     */   public Iterable<Tuple2<K, V>> toIterable() {
/* 632 */     return IterableLike.class.toIterable((IterableLike)this);
/*     */   }
/*     */   
/*     */   public Iterator<Tuple2<K, V>> toIterator() {
/* 632 */     return IterableLike.class.toIterator((IterableLike)this);
/*     */   }
/*     */   
/*     */   public Tuple2<K, V> head() {
/* 632 */     return (Tuple2<K, V>)IterableLike.class.head((IterableLike)this);
/*     */   }
/*     */   
/*     */   public TrieMap<K, V> slice(int from, int until) {
/* 632 */     return (TrieMap<K, V>)IterableLike.class.slice((IterableLike)this, from, until);
/*     */   }
/*     */   
/*     */   public TrieMap<K, V> take(int n) {
/* 632 */     return (TrieMap<K, V>)IterableLike.class.take((IterableLike)this, n);
/*     */   }
/*     */   
/*     */   public TrieMap<K, V> drop(int n) {
/* 632 */     return (TrieMap<K, V>)IterableLike.class.drop((IterableLike)this, n);
/*     */   }
/*     */   
/*     */   public TrieMap<K, V> takeWhile(Function1 p) {
/* 632 */     return (TrieMap<K, V>)IterableLike.class.takeWhile((IterableLike)this, p);
/*     */   }
/*     */   
/*     */   public Iterator<TrieMap<K, V>> grouped(int size) {
/* 632 */     return IterableLike.class.grouped((IterableLike)this, size);
/*     */   }
/*     */   
/*     */   public Iterator<TrieMap<K, V>> sliding(int size) {
/* 632 */     return IterableLike.class.sliding((IterableLike)this, size);
/*     */   }
/*     */   
/*     */   public Iterator<TrieMap<K, V>> sliding(int size, int step) {
/* 632 */     return IterableLike.class.sliding((IterableLike)this, size, step);
/*     */   }
/*     */   
/*     */   public TrieMap<K, V> takeRight(int n) {
/* 632 */     return (TrieMap<K, V>)IterableLike.class.takeRight((IterableLike)this, n);
/*     */   }
/*     */   
/*     */   public TrieMap<K, V> dropRight(int n) {
/* 632 */     return (TrieMap<K, V>)IterableLike.class.dropRight((IterableLike)this, n);
/*     */   }
/*     */   
/*     */   public <B> void copyToArray(Object xs, int start, int len) {
/* 632 */     IterableLike.class.copyToArray((IterableLike)this, xs, start, len);
/*     */   }
/*     */   
/*     */   public <A1, B, That> That zip(GenIterable that, CanBuildFrom bf) {
/* 632 */     return (That)IterableLike.class.zip((IterableLike)this, that, bf);
/*     */   }
/*     */   
/*     */   public <B, A1, That> That zipAll(GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/* 632 */     return (That)IterableLike.class.zipAll((IterableLike)this, that, thisElem, thatElem, bf);
/*     */   }
/*     */   
/*     */   public <A1, That> That zipWithIndex(CanBuildFrom bf) {
/* 632 */     return (That)IterableLike.class.zipWithIndex((IterableLike)this, bf);
/*     */   }
/*     */   
/*     */   public <B> boolean sameElements(GenIterable that) {
/* 632 */     return IterableLike.class.sameElements((IterableLike)this, that);
/*     */   }
/*     */   
/*     */   public Stream<Tuple2<K, V>> toStream() {
/* 632 */     return IterableLike.class.toStream((IterableLike)this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object that) {
/* 632 */     return IterableLike.class.canEqual((IterableLike)this, that);
/*     */   }
/*     */   
/*     */   public Object view() {
/* 632 */     return IterableLike.class.view((IterableLike)this);
/*     */   }
/*     */   
/*     */   public IterableView<Tuple2<K, V>, TrieMap<K, V>> view(int from, int until) {
/* 632 */     return IterableLike.class.view((IterableLike)this, from, until);
/*     */   }
/*     */   
/*     */   public <B> Builder<B, Iterable<B>> genericBuilder() {
/* 632 */     return GenericTraversableTemplate.class.genericBuilder((GenericTraversableTemplate)this);
/*     */   }
/*     */   
/*     */   public <A1, A2> Tuple2<Iterable<A1>, Iterable<A2>> unzip(Function1 asPair) {
/* 632 */     return GenericTraversableTemplate.class.unzip((GenericTraversableTemplate)this, asPair);
/*     */   }
/*     */   
/*     */   public <A1, A2, A3> Tuple3<Iterable<A1>, Iterable<A2>, Iterable<A3>> unzip3(Function1 asTriple) {
/* 632 */     return GenericTraversableTemplate.class.unzip3((GenericTraversableTemplate)this, asTriple);
/*     */   }
/*     */   
/*     */   public <B> Iterable<B> flatten(Function1 asTraversable) {
/* 632 */     return (Iterable<B>)GenericTraversableTemplate.class.flatten((GenericTraversableTemplate)this, asTraversable);
/*     */   }
/*     */   
/*     */   public <B> Iterable<Iterable<B>> transpose(Function1 asTraversable) {
/* 632 */     return (Iterable<Iterable<B>>)GenericTraversableTemplate.class.transpose((GenericTraversableTemplate)this, asTraversable);
/*     */   }
/*     */   
/*     */   public TrieMap<K, V> repr() {
/* 632 */     return (TrieMap<K, V>)TraversableLike.class.repr((TraversableLike)this);
/*     */   }
/*     */   
/*     */   public final boolean isTraversableAgain() {
/* 632 */     return TraversableLike.class.isTraversableAgain((TraversableLike)this);
/*     */   }
/*     */   
/*     */   public boolean hasDefiniteSize() {
/* 632 */     return TraversableLike.class.hasDefiniteSize((TraversableLike)this);
/*     */   }
/*     */   
/*     */   public <B, That> That $plus$plus(GenTraversableOnce that, CanBuildFrom bf) {
/* 632 */     return (That)TraversableLike.class.$plus$plus((TraversableLike)this, that, bf);
/*     */   }
/*     */   
/*     */   public <B, That> That $plus$plus$colon(TraversableOnce that, CanBuildFrom bf) {
/* 632 */     return (That)TraversableLike.class.$plus$plus$colon((TraversableLike)this, that, bf);
/*     */   }
/*     */   
/*     */   public <B, That> That $plus$plus$colon(Traversable that, CanBuildFrom bf) {
/* 632 */     return (That)TraversableLike.class.$plus$plus$colon((TraversableLike)this, that, bf);
/*     */   }
/*     */   
/*     */   public <B, That> That map(Function1 f, CanBuildFrom bf) {
/* 632 */     return (That)TraversableLike.class.map((TraversableLike)this, f, bf);
/*     */   }
/*     */   
/*     */   public <B, That> That flatMap(Function1 f, CanBuildFrom bf) {
/* 632 */     return (That)TraversableLike.class.flatMap((TraversableLike)this, f, bf);
/*     */   }
/*     */   
/*     */   public TrieMap<K, V> filter(Function1 p) {
/* 632 */     return (TrieMap<K, V>)TraversableLike.class.filter((TraversableLike)this, p);
/*     */   }
/*     */   
/*     */   public <B, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/* 632 */     return (That)TraversableLike.class.collect((TraversableLike)this, pf, bf);
/*     */   }
/*     */   
/*     */   public Tuple2<TrieMap<K, V>, TrieMap<K, V>> partition(Function1 p) {
/* 632 */     return TraversableLike.class.partition((TraversableLike)this, p);
/*     */   }
/*     */   
/*     */   public <K> Map<K, TrieMap<K, V>> groupBy(Function1 f) {
/* 632 */     return TraversableLike.class.groupBy((TraversableLike)this, f);
/*     */   }
/*     */   
/*     */   public <B, That> That scan(Object z, Function2 op, CanBuildFrom cbf) {
/* 632 */     return (That)TraversableLike.class.scan((TraversableLike)this, z, op, cbf);
/*     */   }
/*     */   
/*     */   public <B, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/* 632 */     return (That)TraversableLike.class.scanLeft((TraversableLike)this, z, op, bf);
/*     */   }
/*     */   
/*     */   public <B, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/* 632 */     return (That)TraversableLike.class.scanRight((TraversableLike)this, z, op, bf);
/*     */   }
/*     */   
/*     */   public Option<Tuple2<K, V>> headOption() {
/* 632 */     return TraversableLike.class.headOption((TraversableLike)this);
/*     */   }
/*     */   
/*     */   public TrieMap<K, V> tail() {
/* 632 */     return (TrieMap<K, V>)TraversableLike.class.tail((TraversableLike)this);
/*     */   }
/*     */   
/*     */   public Tuple2<K, V> last() {
/* 632 */     return (Tuple2<K, V>)TraversableLike.class.last((TraversableLike)this);
/*     */   }
/*     */   
/*     */   public Option<Tuple2<K, V>> lastOption() {
/* 632 */     return TraversableLike.class.lastOption((TraversableLike)this);
/*     */   }
/*     */   
/*     */   public TrieMap<K, V> init() {
/* 632 */     return (TrieMap<K, V>)TraversableLike.class.init((TraversableLike)this);
/*     */   }
/*     */   
/*     */   public TrieMap<K, V> sliceWithKnownDelta(int from, int until, int delta) {
/* 632 */     return (TrieMap<K, V>)TraversableLike.class.sliceWithKnownDelta((TraversableLike)this, from, until, delta);
/*     */   }
/*     */   
/*     */   public TrieMap<K, V> sliceWithKnownBound(int from, int until) {
/* 632 */     return (TrieMap<K, V>)TraversableLike.class.sliceWithKnownBound((TraversableLike)this, from, until);
/*     */   }
/*     */   
/*     */   public TrieMap<K, V> dropWhile(Function1 p) {
/* 632 */     return (TrieMap<K, V>)TraversableLike.class.dropWhile((TraversableLike)this, p);
/*     */   }
/*     */   
/*     */   public Tuple2<TrieMap<K, V>, TrieMap<K, V>> span(Function1 p) {
/* 632 */     return TraversableLike.class.span((TraversableLike)this, p);
/*     */   }
/*     */   
/*     */   public Tuple2<TrieMap<K, V>, TrieMap<K, V>> splitAt(int n) {
/* 632 */     return TraversableLike.class.splitAt((TraversableLike)this, n);
/*     */   }
/*     */   
/*     */   public Iterator<TrieMap<K, V>> tails() {
/* 632 */     return TraversableLike.class.tails((TraversableLike)this);
/*     */   }
/*     */   
/*     */   public Iterator<TrieMap<K, V>> inits() {
/* 632 */     return TraversableLike.class.inits((TraversableLike)this);
/*     */   }
/*     */   
/*     */   public Traversable<Tuple2<K, V>> toTraversable() {
/* 632 */     return TraversableLike.class.toTraversable((TraversableLike)this);
/*     */   }
/*     */   
/*     */   public <Col> Col to(CanBuildFrom cbf) {
/* 632 */     return (Col)TraversableLike.class.to((TraversableLike)this, cbf);
/*     */   }
/*     */   
/*     */   public FilterMonadic<Tuple2<K, V>, TrieMap<K, V>> withFilter(Function1 p) {
/* 632 */     return TraversableLike.class.withFilter((TraversableLike)this, p);
/*     */   }
/*     */   
/*     */   public List<Tuple2<K, V>> reversed() {
/* 632 */     return TraversableOnce.class.reversed((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public boolean nonEmpty() {
/* 632 */     return TraversableOnce.class.nonEmpty((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public int count(Function1 p) {
/* 632 */     return TraversableOnce.class.count((TraversableOnce)this, p);
/*     */   }
/*     */   
/*     */   public <B> Option<B> collectFirst(PartialFunction pf) {
/* 632 */     return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*     */   }
/*     */   
/*     */   public <B> B $div$colon(Object z, Function2 op) {
/* 632 */     return (B)TraversableOnce.class.$div$colon((TraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B $colon$bslash(Object z, Function2 op) {
/* 632 */     return (B)TraversableOnce.class.$colon$bslash((TraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B foldLeft(Object z, Function2 op) {
/* 632 */     return (B)TraversableOnce.class.foldLeft((TraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B reduceLeft(Function2 op) {
/* 632 */     return (B)TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public <B> Option<B> reduceLeftOption(Function2 op) {
/* 632 */     return TraversableOnce.class.reduceLeftOption((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public <B> Option<B> reduceRightOption(Function2 op) {
/* 632 */     return TraversableOnce.class.reduceRightOption((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public <A1> A1 reduce(Function2 op) {
/* 632 */     return (A1)TraversableOnce.class.reduce((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public <A1> Option<A1> reduceOption(Function2 op) {
/* 632 */     return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public <A1> A1 fold(Object z, Function2 op) {
/* 632 */     return (A1)TraversableOnce.class.fold((TraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/* 632 */     return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*     */   }
/*     */   
/*     */   public <B> B sum(Numeric num) {
/* 632 */     return (B)TraversableOnce.class.sum((TraversableOnce)this, num);
/*     */   }
/*     */   
/*     */   public <B> B product(Numeric num) {
/* 632 */     return (B)TraversableOnce.class.product((TraversableOnce)this, num);
/*     */   }
/*     */   
/*     */   public <B> Tuple2<K, V> min(Ordering cmp) {
/* 632 */     return (Tuple2<K, V>)TraversableOnce.class.min((TraversableOnce)this, cmp);
/*     */   }
/*     */   
/*     */   public <B> Tuple2<K, V> max(Ordering cmp) {
/* 632 */     return (Tuple2<K, V>)TraversableOnce.class.max((TraversableOnce)this, cmp);
/*     */   }
/*     */   
/*     */   public <B> Tuple2<K, V> maxBy(Function1 f, Ordering cmp) {
/* 632 */     return (Tuple2<K, V>)TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*     */   }
/*     */   
/*     */   public <B> Tuple2<K, V> minBy(Function1 f, Ordering cmp) {
/* 632 */     return (Tuple2<K, V>)TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*     */   }
/*     */   
/*     */   public <B> void copyToBuffer(Buffer dest) {
/* 632 */     TraversableOnce.class.copyToBuffer((TraversableOnce)this, dest);
/*     */   }
/*     */   
/*     */   public <B> void copyToArray(Object xs, int start) {
/* 632 */     TraversableOnce.class.copyToArray((TraversableOnce)this, xs, start);
/*     */   }
/*     */   
/*     */   public <B> void copyToArray(Object xs) {
/* 632 */     TraversableOnce.class.copyToArray((TraversableOnce)this, xs);
/*     */   }
/*     */   
/*     */   public <B> Object toArray(ClassTag evidence$1) {
/* 632 */     return TraversableOnce.class.toArray((TraversableOnce)this, evidence$1);
/*     */   }
/*     */   
/*     */   public List<Tuple2<K, V>> toList() {
/* 632 */     return TraversableOnce.class.toList((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public IndexedSeq<Tuple2<K, V>> toIndexedSeq() {
/* 632 */     return TraversableOnce.class.toIndexedSeq((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public <B> Set<B> toSet() {
/* 632 */     return TraversableOnce.class.toSet((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public Vector<Tuple2<K, V>> toVector() {
/* 632 */     return TraversableOnce.class.toVector((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/* 632 */     return TraversableOnce.class.toMap((TraversableOnce)this, ev);
/*     */   }
/*     */   
/*     */   public String mkString(String start, String sep, String end) {
/* 632 */     return TraversableOnce.class.mkString((TraversableOnce)this, start, sep, end);
/*     */   }
/*     */   
/*     */   public String mkString(String sep) {
/* 632 */     return TraversableOnce.class.mkString((TraversableOnce)this, sep);
/*     */   }
/*     */   
/*     */   public String mkString() {
/* 632 */     return TraversableOnce.class.mkString((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public StringBuilder addString(StringBuilder b, String sep) {
/* 632 */     return TraversableOnce.class.addString((TraversableOnce)this, b, sep);
/*     */   }
/*     */   
/*     */   public StringBuilder addString(StringBuilder b) {
/* 632 */     return TraversableOnce.class.addString((TraversableOnce)this, b);
/*     */   }
/*     */   
/*     */   public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/* 632 */     return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   private TrieMap(Object r, AtomicReferenceFieldUpdater<TrieMap<K, V>, Object> rtupd, Hashing<K> hashf, Equiv<K> ef) {
/* 632 */     GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/* 632 */     TraversableOnce.class.$init$((TraversableOnce)this);
/* 632 */     Parallelizable.class.$init$((Parallelizable)this);
/* 632 */     TraversableLike.class.$init$((TraversableLike)this);
/* 632 */     GenericTraversableTemplate.class.$init$((GenericTraversableTemplate)this);
/* 632 */     GenTraversable.class.$init$((GenTraversable)this);
/* 632 */     Traversable.class.$init$((Traversable)this);
/* 632 */     Traversable.class.$init$((Traversable)this);
/* 632 */     GenIterable.class.$init$((GenIterable)this);
/* 632 */     IterableLike.class.$init$((IterableLike)this);
/* 632 */     Iterable.class.$init$((Iterable)this);
/* 632 */     Iterable.class.$init$((Iterable)this);
/* 632 */     GenMapLike.class.$init$((GenMapLike)this);
/* 632 */     Function1.class.$init$((Function1)this);
/* 632 */     PartialFunction.class.$init$((PartialFunction)this);
/* 632 */     Subtractable.class.$init$((Subtractable)this);
/* 632 */     MapLike.class.$init$((MapLike)this);
/* 632 */     Map.class.$init$((Map)this);
/* 632 */     Growable.class.$init$((Growable)this);
/* 632 */     Builder.class.$init$((Builder)this);
/* 632 */     Shrinkable.class.$init$((Shrinkable)this);
/* 632 */     Cloneable.class.$init$((Cloneable)this);
/* 632 */     MapLike.class.$init$(this);
/* 632 */     Map.class.$init$(this);
/* 632 */     CustomParallelizable.class.$init$(this);
/* 638 */     this.hashingobj = (hashf instanceof Hashing.Default) ? new MangledHashing<K>() : hashf;
/* 639 */     this.equalityobj = ef;
/* 640 */     this.rootupdater = rtupd;
/* 643 */     this.root = r;
/*     */   }
/*     */   
/*     */   private Hashing<K> hashingobj() {
/*     */     return this.hashingobj;
/*     */   }
/*     */   
/*     */   private void hashingobj_$eq(Hashing<K> x$1) {
/*     */     this.hashingobj = x$1;
/*     */   }
/*     */   
/*     */   private Equiv<K> equalityobj() {
/*     */     return this.equalityobj;
/*     */   }
/*     */   
/*     */   private void equalityobj_$eq(Equiv<K> x$1) {
/*     */     this.equalityobj = x$1;
/*     */   }
/*     */   
/*     */   private AtomicReferenceFieldUpdater<TrieMap<K, V>, Object> rootupdater() {
/*     */     return this.rootupdater;
/*     */   }
/*     */   
/*     */   private void rootupdater_$eq(AtomicReferenceFieldUpdater<TrieMap<K, V>, Object> x$1) {
/*     */     this.rootupdater = x$1;
/*     */   }
/*     */   
/*     */   public Hashing<K> hashing() {
/*     */     return hashingobj();
/*     */   }
/*     */   
/*     */   public Equiv<K> equality() {
/*     */     return equalityobj();
/*     */   }
/*     */   
/*     */   public Object root() {
/* 643 */     return this.root;
/*     */   }
/*     */   
/*     */   public void root_$eq(Object x$1) {
/* 643 */     this.root = x$1;
/*     */   }
/*     */   
/*     */   public TrieMap(Hashing<K> hashf, Equiv<K> ef) {
/* 645 */     this(
/* 646 */         INode$.MODULE$.newRootNode(), 
/* 647 */         AtomicReferenceFieldUpdater.newUpdater((Class)TrieMap.class, Object.class, "root"), 
/* 648 */         hashf, 
/* 649 */         ef);
/*     */   }
/*     */   
/*     */   public TrieMap() {
/* 652 */     this((Hashing<K>)Hashing$.MODULE$.default(), package$.MODULE$.Equiv().universal());
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) {
/* 657 */     out.writeObject(this.hashf);
/* 658 */     out.writeObject(this.ef);
/* 660 */     Iterator<Tuple2<K, V>> it = iterator();
/* 661 */     while (it.hasNext()) {
/* 662 */       Tuple2 tuple2 = (Tuple2)it.next();
/* 662 */       if (tuple2 != null) {
/* 662 */         Tuple2 tuple21 = new Tuple2(tuple2._1(), tuple2._2());
/* 662 */         Object k = tuple21._1(), v = tuple21._2();
/* 663 */         out.writeObject(k);
/* 664 */         out.writeObject(v);
/*     */         continue;
/*     */       } 
/*     */       throw new MatchError(tuple2);
/*     */     } 
/* 666 */     out.writeObject(TrieMapSerializationEnd$.MODULE$);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getstatic scala/collection/concurrent/INode$.MODULE$ : Lscala/collection/concurrent/INode$;
/*     */     //   4: invokevirtual newRootNode : ()Lscala/collection/concurrent/INode;
/*     */     //   7: invokevirtual root_$eq : (Ljava/lang/Object;)V
/*     */     //   10: aload_0
/*     */     //   11: ldc scala/collection/concurrent/TrieMap
/*     */     //   13: ldc java/lang/Object
/*     */     //   15: ldc_w 'root'
/*     */     //   18: invokestatic newUpdater : (Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/String;)Ljava/util/concurrent/atomic/AtomicReferenceFieldUpdater;
/*     */     //   21: invokespecial rootupdater_$eq : (Ljava/util/concurrent/atomic/AtomicReferenceFieldUpdater;)V
/*     */     //   24: aload_0
/*     */     //   25: aload_1
/*     */     //   26: invokevirtual readObject : ()Ljava/lang/Object;
/*     */     //   29: checkcast scala/util/hashing/Hashing
/*     */     //   32: invokespecial hashingobj_$eq : (Lscala/util/hashing/Hashing;)V
/*     */     //   35: aload_0
/*     */     //   36: aload_1
/*     */     //   37: invokevirtual readObject : ()Ljava/lang/Object;
/*     */     //   40: checkcast scala/math/Equiv
/*     */     //   43: invokespecial equalityobj_$eq : (Lscala/math/Equiv;)V
/*     */     //   46: aload_1
/*     */     //   47: invokevirtual readObject : ()Ljava/lang/Object;
/*     */     //   50: dup
/*     */     //   51: astore #4
/*     */     //   53: getstatic scala/collection/concurrent/TrieMapSerializationEnd$.MODULE$ : Lscala/collection/concurrent/TrieMapSerializationEnd$;
/*     */     //   56: astore_2
/*     */     //   57: dup
/*     */     //   58: ifnonnull -> 69
/*     */     //   61: pop
/*     */     //   62: aload_2
/*     */     //   63: ifnull -> 88
/*     */     //   66: goto -> 76
/*     */     //   69: aload_2
/*     */     //   70: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   73: ifne -> 88
/*     */     //   76: aload_1
/*     */     //   77: invokevirtual readObject : ()Ljava/lang/Object;
/*     */     //   80: astore_3
/*     */     //   81: aload_0
/*     */     //   82: aload #4
/*     */     //   84: aload_3
/*     */     //   85: invokevirtual update : (Ljava/lang/Object;Ljava/lang/Object;)V
/*     */     //   88: aload #4
/*     */     //   90: getstatic scala/collection/concurrent/TrieMapSerializationEnd$.MODULE$ : Lscala/collection/concurrent/TrieMapSerializationEnd$;
/*     */     //   93: astore #5
/*     */     //   95: dup
/*     */     //   96: ifnonnull -> 108
/*     */     //   99: pop
/*     */     //   100: aload #5
/*     */     //   102: ifnull -> 116
/*     */     //   105: goto -> 46
/*     */     //   108: aload #5
/*     */     //   110: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   113: ifeq -> 46
/*     */     //   116: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #670	-> 0
/*     */     //   #671	-> 10
/*     */     //   #673	-> 24
/*     */     //   #674	-> 35
/*     */     //   #678	-> 46
/*     */     //   #677	-> 46
/*     */     //   #676	-> 46
/*     */     //   #679	-> 53
/*     */     //   #681	-> 76
/*     */     //   #680	-> 76
/*     */     //   #682	-> 81
/*     */     //   #684	-> 88
/*     */     //   #669	-> 116
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	117	0	this	Lscala/collection/concurrent/TrieMap;
/*     */     //   0	117	1	in	Ljava/io/ObjectInputStream;
/*     */     //   46	71	4	obj	Ljava/lang/Object;
/*     */     //   81	7	3	v	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public boolean CAS_ROOT(Object ov, Object nv) {
/* 687 */     return rootupdater().compareAndSet(this, ov, nv);
/*     */   }
/*     */   
/*     */   public INode<K, V> readRoot(boolean abort) {
/* 689 */     return RDCSS_READ_ROOT(abort);
/*     */   }
/*     */   
/*     */   public boolean readRoot$default$1() {
/* 689 */     return false;
/*     */   }
/*     */   
/*     */   public boolean RDCSS_READ_ROOT$default$1() {
/* 691 */     return false;
/*     */   }
/*     */   
/*     */   public INode<K, V> RDCSS_READ_ROOT(boolean abort) {
/* 692 */     Object r = root();
/* 693 */     if (r instanceof INode) {
/* 693 */       INode iNode1 = (INode)r, iNode2 = iNode1;
/*     */     } else {
/* 695 */       if (r instanceof RDCSS_Descriptor)
/* 695 */         return RDCSS_Complete(abort); 
/*     */       throw new MatchError(r);
/*     */     } 
/*     */     return (INode<K, V>)SYNTHETIC_LOCAL_VARIABLE_3;
/*     */   }
/*     */   
/*     */   private INode<K, V> RDCSS_Complete(boolean abort) {
/*     */     while (true) {
/*     */       INode<K, V> iNode;
/* 700 */       Object v = root();
/* 701 */       if (v instanceof INode) {
/* 701 */         INode iNode1 = (INode)v;
/*     */       } else {
/* 703 */         if (v instanceof RDCSS_Descriptor) {
/* 703 */           RDCSS_Descriptor rDCSS_Descriptor = (RDCSS_Descriptor)v;
/* 704 */           if (rDCSS_Descriptor != null) {
/* 704 */             Tuple3 tuple3 = new Tuple3(rDCSS_Descriptor.old(), rDCSS_Descriptor.expectedmain(), rDCSS_Descriptor.nv());
/* 704 */             INode<K, V> ov = (INode)tuple3._1();
/* 704 */             MainNode exp = (MainNode)tuple3._2();
/* 704 */             INode nv = (INode)tuple3._3();
/* 705 */             if (abort) {
/* 706 */               if (CAS_ROOT(rDCSS_Descriptor, ov)) {
/*     */               
/*     */               } else {
/*     */                 continue;
/*     */               } 
/*     */             } else {
/* 709 */               MainNode oldmain = ov.gcasRead(this);
/* 710 */               if (oldmain == exp) {
/* 711 */                 if (CAS_ROOT(rDCSS_Descriptor, nv)) {
/* 712 */                   rDCSS_Descriptor.committed_$eq(true);
/*     */                 } else {
/*     */                   continue;
/*     */                 } 
/* 716 */               } else if (CAS_ROOT(rDCSS_Descriptor, ov)) {
/*     */               
/*     */               } else {
/*     */                 continue;
/*     */               } 
/*     */             } 
/* 716 */             iNode = ov;
/*     */           } 
/*     */           throw new MatchError(rDCSS_Descriptor);
/*     */         } 
/*     */         throw new MatchError(v);
/*     */       } 
/*     */       return iNode;
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean RDCSS_ROOT(INode<?, ?> ov, MainNode<?, ?> expectedmain, INode<?, ?> nv) {
/* 724 */     RDCSS_Descriptor<Object, Object> desc = new RDCSS_Descriptor<Object, Object>(ov, expectedmain, nv);
/* 726 */     RDCSS_Complete(false);
/* 727 */     return CAS_ROOT(ov, desc) ? desc.committed() : false;
/*     */   }
/*     */   
/*     */   private void inserthc(Object k, int hc, Object v) {
/*     */     INode<K, V> r;
/*     */     do {
/* 732 */       r = RDCSS_READ_ROOT(RDCSS_READ_ROOT$default$1());
/* 733 */     } while (!r.rec_insert((K)k, (V)v, hc, 0, (INode<K, V>)null, r.gen, this));
/*     */   }
/*     */   
/*     */   private Option<V> insertifhc(Object k, int hc, Object v, Object cond) {
/*     */     while (true) {
/* 737 */       INode<K, V> r = RDCSS_READ_ROOT(RDCSS_READ_ROOT$default$1());
/* 739 */       Option<V> ret = r.rec_insertif((K)k, (V)v, hc, cond, 0, (INode<K, V>)null, r.gen, this);
/* 740 */       if (ret != null)
/* 741 */         return ret; 
/*     */     } 
/*     */   }
/*     */   
/*     */   private Object lookuphc(Object k, int hc) {
/*     */     while (true) {
/* 745 */       INode<K, V> r = RDCSS_READ_ROOT(RDCSS_READ_ROOT$default$1());
/* 746 */       Object res = r.rec_lookup((K)k, hc, 0, (INode<K, V>)null, r.gen, this);
/* 747 */       if (res != INodeBase.RESTART)
/* 748 */         return res; 
/*     */     } 
/*     */   }
/*     */   
/*     */   private Option<V> removehc(Object k, Object v, int hc) {
/*     */     while (true) {
/* 765 */       INode<K, V> r = RDCSS_READ_ROOT(RDCSS_READ_ROOT$default$1());
/* 766 */       Option<V> res = r.rec_remove((K)k, (V)v, hc, 0, (INode<K, V>)null, r.gen, this);
/* 767 */       if (res != null)
/* 767 */         return res; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public String string() {
/* 771 */     return RDCSS_READ_ROOT(RDCSS_READ_ROOT$default$1()).string(0);
/*     */   }
/*     */   
/*     */   public TrieMap<K, V> seq() {
/* 775 */     return this;
/*     */   }
/*     */   
/*     */   public ParTrieMap<K, V> par() {
/* 777 */     return new ParTrieMap(this);
/*     */   }
/*     */   
/*     */   public TrieMap<K, V> empty() {
/* 779 */     return new TrieMap();
/*     */   }
/*     */   
/*     */   public boolean isReadOnly() {
/* 781 */     return (rootupdater() == null);
/*     */   }
/*     */   
/*     */   public boolean nonReadOnly() {
/* 783 */     return (rootupdater() != null);
/*     */   }
/*     */   
/*     */   public TrieMap<K, V> snapshot() {
/*     */     MainNode<K, V> expmain;
/*     */     INode<K, V> r;
/*     */     do {
/* 795 */       r = RDCSS_READ_ROOT(RDCSS_READ_ROOT$default$1());
/* 796 */       expmain = r.gcasRead(this);
/* 797 */     } while (!RDCSS_ROOT(r, expmain, r.copyToGen(new Gen(), this)));
/* 797 */     return new TrieMap(r.copyToGen(new Gen(), this), rootupdater(), hashing(), equality());
/*     */   }
/*     */   
/*     */   public Map<K, V> readOnlySnapshot() {
/*     */     MainNode<K, V> expmain;
/*     */     INode<K, V> r;
/*     */     do {
/* 814 */       r = RDCSS_READ_ROOT(RDCSS_READ_ROOT$default$1());
/* 815 */       expmain = r.gcasRead(this);
/* 816 */     } while (!RDCSS_ROOT(r, expmain, r.copyToGen(new Gen(), this)));
/* 816 */     return (Map<K, V>)new TrieMap(r, null, hashing(), equality());
/*     */   }
/*     */   
/*     */   public void clear() {
/*     */     INode<K, V> r;
/*     */     do {
/* 821 */       r = RDCSS_READ_ROOT(RDCSS_READ_ROOT$default$1());
/* 822 */     } while (!RDCSS_ROOT(r, r.gcasRead(this), INode$.MODULE$.newRootNode()));
/*     */   }
/*     */   
/*     */   public int computeHash(Object k) {
/* 826 */     return hashingobj().hash(k);
/*     */   }
/*     */   
/*     */   public V lookup(Object k) {
/* 829 */     int hc = computeHash((K)k);
/* 830 */     return (V)lookuphc((K)k, hc);
/*     */   }
/*     */   
/*     */   public V apply(Object k) {
/* 834 */     int hc = computeHash((K)k);
/* 835 */     Object res = lookuphc((K)k, hc);
/* 836 */     if (res == null)
/* 836 */       throw new NoSuchElementException(); 
/* 837 */     return (V)res;
/*     */   }
/*     */   
/*     */   public Option<V> get(Object k) {
/* 841 */     int hc = computeHash((K)k);
/* 842 */     return Option$.MODULE$.apply(lookuphc((K)k, hc));
/*     */   }
/*     */   
/*     */   public Option<V> put(Object key, Object value) {
/* 846 */     int hc = computeHash((K)key);
/* 847 */     return insertifhc((K)key, hc, (V)value, null);
/*     */   }
/*     */   
/*     */   public void update(Object k, Object v) {
/* 851 */     int hc = computeHash((K)k);
/* 852 */     inserthc((K)k, hc, (V)v);
/*     */   }
/*     */   
/*     */   public TrieMap<K, V> $plus$eq(Tuple2 kv) {
/* 856 */     update((K)kv._1(), (V)kv._2());
/* 857 */     return this;
/*     */   }
/*     */   
/*     */   public Option<V> remove(Object k) {
/* 861 */     int hc = computeHash((K)k);
/* 862 */     return removehc((K)k, null, hc);
/*     */   }
/*     */   
/*     */   public TrieMap<K, V> $minus$eq(Object k) {
/* 866 */     remove((K)k);
/* 867 */     return this;
/*     */   }
/*     */   
/*     */   public Option<V> putIfAbsent(Object k, Object v) {
/* 871 */     int hc = computeHash((K)k);
/* 872 */     return insertifhc((K)k, hc, (V)v, INode$.MODULE$.KEY_ABSENT());
/*     */   }
/*     */   
/*     */   public boolean remove(Object k, Object v) {
/* 876 */     int hc = computeHash((K)k);
/* 877 */     return removehc((K)k, (V)v, hc).nonEmpty();
/*     */   }
/*     */   
/*     */   public boolean replace(Object k, Object oldvalue, Object newvalue) {
/* 881 */     int hc = computeHash((K)k);
/* 882 */     return insertifhc((K)k, hc, (V)newvalue, oldvalue).nonEmpty();
/*     */   }
/*     */   
/*     */   public Option<V> replace(Object k, Object v) {
/* 886 */     int hc = computeHash((K)k);
/* 887 */     return insertifhc((K)k, hc, (V)v, INode$.MODULE$.KEY_PRESENT());
/*     */   }
/*     */   
/*     */   public Iterator<Tuple2<K, V>> iterator() {
/* 891 */     return nonReadOnly() ? readOnlySnapshot().iterator() : 
/* 892 */       new TrieMapIterator<K, V>(0, this, TrieMapIterator$.MODULE$.$lessinit$greater$default$3());
/*     */   }
/*     */   
/*     */   private int cachedSize() {
/* 895 */     INode<K, V> r = RDCSS_READ_ROOT(RDCSS_READ_ROOT$default$1());
/* 896 */     return r.cachedSize(this);
/*     */   }
/*     */   
/*     */   public int size() {
/* 900 */     return nonReadOnly() ? readOnlySnapshot().size() : 
/* 901 */       cachedSize();
/*     */   }
/*     */   
/*     */   public String stringPrefix() {
/* 903 */     return "TrieMap";
/*     */   }
/*     */   
/*     */   public static <K, V> CanBuildFrom<TrieMap<?, ?>, Tuple2<K, V>, TrieMap<K, V>> canBuildFrom() {
/*     */     return TrieMap$.MODULE$.canBuildFrom();
/*     */   }
/*     */   
/*     */   public static AtomicReferenceFieldUpdater<INodeBase<?, ?>, MainNode<?, ?>> inodeupdater() {
/*     */     return TrieMap$.MODULE$.inodeupdater();
/*     */   }
/*     */   
/*     */   public static class MangledHashing<K> implements Hashing<K> {
/*     */     public int hash(Object k) {
/* 916 */       return package$.MODULE$.byteswap32(ScalaRunTime$.MODULE$.hash(k));
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\concurrent\TrieMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */