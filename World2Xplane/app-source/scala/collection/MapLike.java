/*     */ package scala.collection;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.generic.Subtractable;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.ParMap;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ObjectRef;
/*     */ import scala.runtime.StringAdd$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\rUcaB\001\003!\003\r\ta\002\002\b\033\006\004H*[6f\025\t\031A!\001\006d_2dWm\031;j_:T\021!B\001\006g\016\fG.Y\002\001+\021A!\003\b\024\024\017\001IQB\b\0314sA\021!bC\007\002\t%\021A\002\002\002\007\003:L(+\0324\021\t)q\001cG\005\003\037\021\021q\002U1si&\fGNR;oGRLwN\034\t\003#Ia\001\001B\003\024\001\t\007ACA\001B#\t)\002\004\005\002\013-%\021q\003\002\002\b\035>$\b.\0338h!\tQ\021$\003\002\033\t\t\031\021I\\=\021\005EaBAB\017\001\t\013\007ACA\001C!\021y\002EI\023\016\003\tI!!\t\002\003\031%#XM]1cY\026d\025n[3\021\t)\031\003cG\005\003I\021\021a\001V;qY\026\024\004CA\t'\t\0319\003\001\"b\001Q\t!A\013[5t#\t)\022FE\002+Y52Aa\013\001\001S\taAH]3gS:,W.\0328u}A)q\004\001\t\034KA!qD\f\t\034\023\ty#AA\002NCB\004RaH\031\0217\025J!A\r\002\003\025\035+g.T1q\031&\\W\r\005\0035oA)S\"A\033\013\005Y\022\021aB4f]\026\024\030nY\005\003qU\022AbU;ciJ\f7\r^1cY\026\004Ba\b\036#y%\0211H\001\002\017!\006\024\030\r\0347fY&T\030M\0317f!\021i\004\tE\016\016\003yR!a\020\002\002\021A\f'/\0317mK2L!!\021 \003\rA\013'/T1q\021\025\031\005\001\"\001E\003\031!\023N\\5uIQ\tQ\t\005\002\013\r&\021q\t\002\002\005+:LG\017C\003J\001\031\005!*A\003f[B$\0300F\001&\021\031a\005\001)C)\033\006Qa.Z<Ck&dG-\032:\026\0039\003Ba\024*#K5\t\001K\003\002R\005\0059Q.\036;bE2,\027BA*Q\005\035\021U/\0337eKJDQ!\026\001\007\002Y\0131aZ3u)\t9&\fE\002\0131nI!!\027\003\003\r=\003H/[8o\021\025YF\0131\001\021\003\rYW-\037\005\006;\0021\tAX\001\tSR,'/\031;peV\tq\fE\002 A\nJ!!\031\002\003\021%#XM]1u_JDQa\031\001\007\002\021\fQ\001\n9mkN,\"!\0325\025\005\031\\\007\003B\020/!\035\004\"!\0055\005\013%\024'\031\0016\003\005\t\013\024CA\016\031\021\025a'\r1\001n\003\tYg\017\005\003\013GA9\007\"B8\001\r\003\001\030A\002\023nS:,8\017\006\002&c\")1L\034a\001!!)1\017\001C!i\0069\021n]#naRLX#A;\021\005)1\030BA<\005\005\035\021un\0347fC:DQ!\037\001\005\002i\f\021bZ3u\037J,En]3\026\005mlHc\001?B\021\021# \003\006Sb\024\rA\033\005\0067b\004\r\001\005\005\t\003\003AH\0211\001\002\004\0059A-\0324bk2$\b\003\002\006\002\006qL1!a\002\005\005!a$-\0378b[\026t\004bBA\006\001\021\005\021QB\001\006CB\004H.\037\013\0047\005=\001BB.\002\n\001\007\001\003C\004\002\024\001!\t!!\006\002\021\r|g\016^1j]N$2!^A\f\021\031Y\026\021\003a\001!!9\0211\004\001\005\002\005u\021aC5t\t\0264\027N\\3e\003R$2!^A\020\021\031Y\026\021\004a\001!!9\0211\005\001\005\002\005\025\022AB6fsN+G/\006\002\002(A!q$!\013\021\023\r\tYC\001\002\004'\026$hABA\030\001!\t\tDA\007EK\032\fW\017\034;LKf\034V\r^\n\t\003[\t\031$a\n\002:A!q$!\016\021\023\r\t9D\001\002\f\003\n\034HO]1diN+G\017E\002\013\003wI1!!\020\005\0051\031VM]5bY&T\030M\0317f\021!\t\t%!\f\005\002\005\r\023A\002\037j]&$h\b\006\002\002FA!\021qIA\027\033\005\001\001\002CA\n\003[!\t!a\023\025\007U\fi\005\003\004\\\003\023\002\r\001\005\005\b;\0065B\021AA)+\t\t\031\006E\002 ABAqaYA\027\t\003\t9\006\006\003\002(\005e\003bBA.\003+\002\r\001E\001\005K2,W\016C\004p\003[!\t!a\030\025\t\005\035\022\021\r\005\b\0037\ni\0061\001\021\021!\t)'!\f\005B\005\035\024\001B:ju\026,\"!!\033\021\007)\tY'C\002\002n\021\0211!\0238u\021!\t\t(!\f\005B\005M\024a\0024pe\026\f7\r[\013\005\003k\n\031\tF\002F\003oB\001\"!\037\002p\001\007\0211P\001\002MB1!\"! \021\003\003K1!a \005\005%1UO\\2uS>t\027\007E\002\022\003\007#q!!\"\002p\t\007ACA\001D\021\035\tI\t\001C\001\003#\nAb[3zg&#XM]1u_JDq!!$\001\t\003\ty)\001\003lKf\034XCAAI!\021y\0221\023\t\n\007\005U%A\001\005Ji\026\024\030M\0317fQ!\tY)!'\002&\006%\006\003BAN\003Ck!!!(\013\007\005}E!\001\006b]:|G/\031;j_:LA!a)\002\036\nIQ.[4sCRLwN\\\021\003\003O\013q\007Y6fsN\004\007E]3ukJt7\017\t1Ji\026\024\030M\0317f7\006k\006\r\t:bi\",'\017\t;iC:\004\003-\023;fe\006$xN].B;\002t\023EAAV\003\025\021d\006\017\0301\021\035\ty\013\001C\001\003c\013aA^1mk\026\034XCAAZ!\021y\0221S\016)\021\0055\026\021TA\\\003S\013#!!/\002s\0014\030\r\\;fg\002\004#/\032;ve:\034\b\005Y%uKJ\f'\r\\3\\\005v\003\007E]1uQ\026\024\b\005\0365b]\002\002\027\n^3sCR|'o\027\"^A:2a!!0\001\021\005}&!\006#fM\006,H\016\036,bYV,7/\023;fe\006\024G.Z\n\t\003w\013\t-a-\002:A!q$a1\034\023\r\t)M\001\002\021\003\n\034HO]1di&#XM]1cY\026D\001\"!\021\002<\022\005\021\021\032\013\003\003\027\004B!a\022\002<\"9Q,a/\005\002\005=WCAAi!\ry\002m\007\005\t\003K\nY\f\"\021\002h!A\021\021OA^\t\003\n9.\006\003\002Z\006\005HcA#\002\\\"A\021\021PAk\001\004\ti\016\005\004\013\003{Z\022q\034\t\004#\005\005HaBAC\003+\024\r\001\006\005\b\003K\004A\021AAh\00391\030\r\\;fg&#XM]1u_JDq!!\001\001\t\003\tI\017F\002\034\003WDaaWAt\001\004\001bABAx\001!\t\tP\001\007GS2$XM]3e\027\026L8o\005\004\002n\006M\030\021 \t\006?\005U\bcG\005\004\003o\024!aC!cgR\024\030m\031;NCB\004RaHA~!mI1!!@\003\005)!UMZ1vYRl\025\r\035\005\f\005\003\tiO!A!\002\023\021\031!A\001q!\025Q\021Q\020\tv\021!\t\t%!<\005\002\t\035A\003\002B\005\005\027\001B!a\022\002n\"A!\021\001B\003\001\004\021\031\001\003\005\002r\0055H\021\tB\b+\021\021\tB!\007\025\007\025\023\031\002\003\005\002z\t5\001\031\001B\013!\031Q\021Q\020\022\003\030A\031\021C!\007\005\017\005\025%Q\002b\001)!1Q,!<\005\002yC\001\"a\005\002n\022\005#q\004\013\004k\n\005\002BB.\003\036\001\007\001\003C\004V\003[$\tA!\n\025\007]\0239\003\003\004\\\005G\001\r\001\005\005\b\005W\001A\021\001B\027\003)1\027\016\034;fe.+\027p\035\013\004[\t=\002\002\003B\001\005S\001\rAa\001\007\r\tM\002\001\003B\033\0051i\025\r\0359fIZ\013G.^3t+\021\0219D!\020\024\r\tE\"\021\bB !\031y\022Q\037\t\003<A\031\021C!\020\005\017\005\025%\021\007b\001)A1q$a?\021\005wA1\"!\037\0032\t\005\t\025!\003\003DA1!\"! \034\005wA\001\"!\021\0032\021\005!q\t\013\005\005\023\022Y\005\005\004\002H\tE\"1\b\005\t\003s\022)\0051\001\003D!A\021\021\017B\031\t\003\022y%\006\003\003R\tuCcA#\003T!A!Q\013B'\001\004\0219&A\001h!\035Q\021Q\020B-\0057\002RAC\022\021\005w\0012!\005B/\t\035\021yF!\024C\002Q\021\021\001\022\005\b;\nEB\021\001B2+\t\021)\007\005\003 A\ne\003\002CA3\005c!\t%a\032\t\021\005M!\021\007C!\005W\"2!\036B7\021\031Y&\021\016a\001!!9QK!\r\005\002\tED\003\002B:\005k\002BA\003-\003<!11La\034A\002AAqA!\037\001\t\003\021Y(A\005nCB4\026\r\\;fgV!!Q\020BB)\021\021yH!\"\021\013}q\003C!!\021\007E\021\031\tB\004\002\006\n]$\031\001\013\t\021\005e$q\017a\001\005\017\003bACA?7\t\005\005b\002BF\001\021\005!QR\001\bkB$\027\r^3e+\021\021yI!&\025\r\tE%q\023BM!\025yb\006\005BJ!\r\t\"Q\023\003\007S\n%%\031\0016\t\rm\023I\t1\001\021\021!\021YJ!#A\002\tM\025!\002<bYV,\007BB2\001\t\003\021y*\006\003\003\"\n\035F\003\003BR\005S\023yKa-\021\013}q\003C!*\021\007E\0219\013\002\004j\005;\023\rA\033\005\t\005W\023i\n1\001\003.\006\0311N^\031\021\013)\031\003C!*\t\021\tE&Q\024a\001\005[\0131a\033<3\021!\021)L!(A\002\t]\026aA6wgB)!B!/\003.&\031!1\030\003\003\025q\022X\r]3bi\026$g\bC\004\003@\002!\tA!1\002\025\021\002H.^:%a2,8/\006\003\003D\n%G\003\002Bc\005\027\004Ra\b\030\021\005\017\0042!\005Be\t\031I'Q\030b\001U\"A!Q\032B_\001\004\021y-\001\002ygB)qD!5\003V&\031!1\033\002\003%\035+g\016\026:bm\026\0248/\0312mK>s7-\032\t\006\025\r\002\"q\031\005\b\0053\004A\021\tBn\003%1\027\016\034;fe:{G\017F\002&\005;D\001B!\001\003X\002\007!q\034\t\006\025\005u$%\036\005\b\005G\004A\021\tBs\003\025!xnU3r+\t\0219\017\005\003 \005S\024\023b\001Bv\005\t\0311+Z9\t\017\t=\b\001\"\021\003r\006AAo\034\"vM\032,'/\006\003\003t\nuXC\001B{!\025y%q\037B~\023\r\021I\020\025\002\007\005V4g-\032:\021\007E\021i\020\002\005\002\006\n5(\031\001B\000#\t\021\003\004\003\005\004\004\001\001K\021KB\003\003-\001\030M]\"p[\nLg.\032:\026\005\r\035\001#B\037\004\n\tb\024bAB\006}\tA1i\\7cS:,'\017C\004\004\020\001!\te!\005\002\023\005$Gm\025;sS:<GCCB\n\007W\031yc!\021\004FA!1QCB\023\035\021\0319b!\t\017\t\re1qD\007\003\0077Q1a!\b\007\003\031a$o\\8u}%\tQ!C\002\004$\021\tq\001]1dW\006<W-\003\003\004(\r%\"!D*ue&twMQ;jY\022,'OC\002\004$\021A\001b!\f\004\016\001\00711C\001\002E\"A1\021GB\007\001\004\031\031$A\003ti\006\024H\017\005\003\0046\rmbb\001\006\0048%\0311\021\b\003\002\rA\023X\rZ3g\023\021\031ida\020\003\rM#(/\0338h\025\r\031I\004\002\005\t\007\007\032i\0011\001\0044\005\0311/\0329\t\021\r\0353Q\002a\001\007g\t1!\0328e\021\035\031Y\005\001C!\007\033\nAb\035;sS:<\007K]3gSb,\"aa\r\t\017\rE\003\001\"\021\004T\005AAo\\*ue&tw\r\006\002\0044\001")
/*     */ public interface MapLike<A, B, This extends MapLike<A, B, This> & Map<A, B>> extends PartialFunction<A, B>, IterableLike<Tuple2<A, B>, This>, GenMapLike<A, B, This>, Subtractable<A, This>, Parallelizable<Tuple2<A, B>, ParMap<A, B>> {
/*     */   This empty();
/*     */   
/*     */   Builder<Tuple2<A, B>, This> newBuilder();
/*     */   
/*     */   Option<B> get(A paramA);
/*     */   
/*     */   Iterator<Tuple2<A, B>> iterator();
/*     */   
/*     */   <B1> Map<A, B1> $plus(Tuple2<A, B1> paramTuple2);
/*     */   
/*     */   This $minus(A paramA);
/*     */   
/*     */   boolean isEmpty();
/*     */   
/*     */   <B1> B1 getOrElse(A paramA, Function0<B1> paramFunction0);
/*     */   
/*     */   B apply(A paramA);
/*     */   
/*     */   boolean contains(A paramA);
/*     */   
/*     */   boolean isDefinedAt(A paramA);
/*     */   
/*     */   Set<A> keySet();
/*     */   
/*     */   Iterator<A> keysIterator();
/*     */   
/*     */   Iterable<A> keys();
/*     */   
/*     */   Iterable<B> values();
/*     */   
/*     */   Iterator<B> valuesIterator();
/*     */   
/*     */   B default(A paramA);
/*     */   
/*     */   Map<A, B> filterKeys(Function1<A, Object> paramFunction1);
/*     */   
/*     */   <C> Map<A, C> mapValues(Function1<B, C> paramFunction1);
/*     */   
/*     */   <B1> Map<A, B1> updated(A paramA, B1 paramB1);
/*     */   
/*     */   <B1> Map<A, B1> $plus(Tuple2<A, B1> paramTuple21, Tuple2<A, B1> paramTuple22, Seq<Tuple2<A, B1>> paramSeq);
/*     */   
/*     */   <B1> Map<A, B1> $plus$plus(GenTraversableOnce<Tuple2<A, B1>> paramGenTraversableOnce);
/*     */   
/*     */   This filterNot(Function1<Tuple2<A, B>, Object> paramFunction1);
/*     */   
/*     */   Seq<Tuple2<A, B>> toSeq();
/*     */   
/*     */   <C> Buffer<C> toBuffer();
/*     */   
/*     */   Combiner<Tuple2<A, B>, ParMap<A, B>> parCombiner();
/*     */   
/*     */   StringBuilder addString(StringBuilder paramStringBuilder, String paramString1, String paramString2, String paramString3);
/*     */   
/*     */   String stringPrefix();
/*     */   
/*     */   String toString();
/*     */   
/*     */   public class DefaultKeySet extends AbstractSet<A> implements Set<A>, Serializable {
/*     */     public DefaultKeySet(MapLike $outer) {}
/*     */     
/*     */     public boolean contains(Object key) {
/* 169 */       return scala$collection$MapLike$DefaultKeySet$$$outer().contains(key);
/*     */     }
/*     */     
/*     */     public Iterator<A> iterator() {
/* 170 */       return scala$collection$MapLike$DefaultKeySet$$$outer().keysIterator();
/*     */     }
/*     */     
/*     */     public Set<A> $plus(Object elem) {
/* 171 */       return ((SetLike)Set$.MODULE$.apply((Seq)Nil$.MODULE$)).$plus$plus(this).$plus((A)elem);
/*     */     }
/*     */     
/*     */     public Set<A> $minus(Object elem) {
/* 172 */       return ((SetLike)Set$.MODULE$.apply((Seq)Nil$.MODULE$)).$plus$plus(this).$minus((A)elem);
/*     */     }
/*     */     
/*     */     public int size() {
/* 173 */       return scala$collection$MapLike$DefaultKeySet$$$outer().size();
/*     */     }
/*     */     
/*     */     public <C> void foreach(Function1 f) {
/* 174 */       scala$collection$MapLike$DefaultKeySet$$$outer().keysIterator().foreach(f);
/*     */     }
/*     */   }
/*     */   
/*     */   public class MapLike$$anon$1 extends AbstractIterator<A> {
/*     */     private final Iterator<Tuple2<A, B>> iter;
/*     */     
/*     */     public MapLike$$anon$1(MapLike $outer) {
/* 182 */       this.iter = $outer.iterator();
/*     */     }
/*     */     
/*     */     private Iterator<Tuple2<A, B>> iter() {
/* 182 */       return this.iter;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 183 */       return iter().hasNext();
/*     */     }
/*     */     
/*     */     public A next() {
/* 184 */       return (A)((Tuple2)iter().next())._1();
/*     */     }
/*     */   }
/*     */   
/*     */   public class DefaultValuesIterable extends AbstractIterable<B> implements Iterable<B>, Serializable {
/*     */     public DefaultValuesIterable(MapLike $outer) {}
/*     */     
/*     */     public Iterator<B> iterator() {
/* 204 */       return scala$collection$MapLike$DefaultValuesIterable$$$outer().valuesIterator();
/*     */     }
/*     */     
/*     */     public int size() {
/* 205 */       return scala$collection$MapLike$DefaultValuesIterable$$$outer().size();
/*     */     }
/*     */     
/*     */     public <C> void foreach(Function1 f) {
/* 206 */       scala$collection$MapLike$DefaultValuesIterable$$$outer().valuesIterator().foreach(f);
/*     */     }
/*     */   }
/*     */   
/*     */   public class MapLike$$anon$2 extends AbstractIterator<B> {
/*     */     private final Iterator<Tuple2<A, B>> iter;
/*     */     
/*     */     public MapLike$$anon$2(MapLike $outer) {
/* 214 */       this.iter = $outer.iterator();
/*     */     }
/*     */     
/*     */     private Iterator<Tuple2<A, B>> iter() {
/* 214 */       return this.iter;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 215 */       return iter().hasNext();
/*     */     }
/*     */     
/*     */     public B next() {
/* 216 */       return (B)((Tuple2)iter().next())._2();
/*     */     }
/*     */   }
/*     */   
/*     */   public class FilteredKeys extends AbstractMap<A, B> implements DefaultMap<A, B> {
/*     */     public final Function1<A, Object> scala$collection$MapLike$FilteredKeys$$p;
/*     */     
/*     */     public <B1> Map<A, B1> $plus(Tuple2 kv) {
/* 230 */       return DefaultMap$class.$plus(this, kv);
/*     */     }
/*     */     
/*     */     public Map<A, B> $minus(Object key) {
/* 230 */       return DefaultMap$class.$minus(this, key);
/*     */     }
/*     */     
/*     */     public FilteredKeys(MapLike $outer, Function1<A, Object> p) {
/* 230 */       DefaultMap$class.$init$(this);
/*     */     }
/*     */     
/*     */     public <C> void foreach(Function1 f) {
/* 231 */       scala$collection$MapLike$FilteredKeys$$$outer().foreach((Function1)new MapLike$FilteredKeys$$anonfun$foreach$1(this, (FilteredKeys)f));
/*     */     }
/*     */     
/*     */     public class MapLike$FilteredKeys$$anonfun$foreach$1 extends AbstractFunction1<Tuple2<A, B>, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Function1 f$1;
/*     */       
/*     */       public final Object apply(Tuple2 kv) {
/* 231 */         return BoxesRunTime.unboxToBoolean(this.$outer.scala$collection$MapLike$FilteredKeys$$p.apply(kv._1())) ? this.f$1.apply(kv) : BoxedUnit.UNIT;
/*     */       }
/*     */       
/*     */       public MapLike$FilteredKeys$$anonfun$foreach$1(MapLike.FilteredKeys $outer, Function1 f$1) {}
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<A, B>> iterator() {
/* 232 */       return scala$collection$MapLike$FilteredKeys$$$outer().iterator().filter((Function1<Tuple2<A, B>, Object>)new MapLike$FilteredKeys$$anonfun$iterator$1(this));
/*     */     }
/*     */     
/*     */     public class MapLike$FilteredKeys$$anonfun$iterator$1 extends AbstractFunction1<Tuple2<A, B>, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final boolean apply(Tuple2 kv) {
/* 232 */         return BoxesRunTime.unboxToBoolean(this.$outer.scala$collection$MapLike$FilteredKeys$$p.apply(kv._1()));
/*     */       }
/*     */       
/*     */       public MapLike$FilteredKeys$$anonfun$iterator$1(MapLike.FilteredKeys $outer) {}
/*     */     }
/*     */     
/*     */     public boolean contains(Object key) {
/* 233 */       return (scala$collection$MapLike$FilteredKeys$$$outer().contains(key) && BoxesRunTime.unboxToBoolean(this.scala$collection$MapLike$FilteredKeys$$p.apply(key)));
/*     */     }
/*     */     
/*     */     public Option<B> get(Object key) {
/* 234 */       return BoxesRunTime.unboxToBoolean(this.scala$collection$MapLike$FilteredKeys$$p.apply(key)) ? scala$collection$MapLike$FilteredKeys$$$outer().get(key) : (Option<B>)None$.MODULE$;
/*     */     }
/*     */   }
/*     */   
/*     */   public class MappedValues<C> extends AbstractMap<A, C> implements DefaultMap<A, C> {
/*     */     public final Function1<B, C> scala$collection$MapLike$MappedValues$$f;
/*     */     
/*     */     public <B1> Map<A, B1> $plus(Tuple2 kv) {
/* 244 */       return DefaultMap$class.$plus(this, kv);
/*     */     }
/*     */     
/*     */     public Map<A, C> $minus(Object key) {
/* 244 */       return DefaultMap$class.$minus(this, key);
/*     */     }
/*     */     
/*     */     public MappedValues(MapLike $outer, Function1<B, C> f) {
/* 244 */       DefaultMap$class.$init$(this);
/*     */     }
/*     */     
/*     */     public <D> void foreach(Function1 g) {
/* 245 */       scala$collection$MapLike$MappedValues$$$outer().withFilter((Function1<Tuple2<A, B>, Object>)new MapLike$MappedValues$$anonfun$foreach$2(this)).foreach((Function1)new MapLike$MappedValues$$anonfun$foreach$3(this, (MappedValues<C>)g));
/*     */     }
/*     */     
/*     */     public class MapLike$MappedValues$$anonfun$foreach$2 extends AbstractFunction1<Tuple2<A, B>, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final boolean apply(Tuple2 check$ifrefutable$1) {
/*     */         boolean bool;
/* 245 */         if (check$ifrefutable$1 != null) {
/* 245 */           bool = true;
/*     */         } else {
/* 245 */           bool = false;
/*     */         } 
/* 245 */         return bool;
/*     */       }
/*     */       
/*     */       public MapLike$MappedValues$$anonfun$foreach$2(MapLike.MappedValues $outer) {}
/*     */     }
/*     */     
/*     */     public class MapLike$MappedValues$$anonfun$foreach$3 extends AbstractFunction1<Tuple2<A, B>, D> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Function1 g$1;
/*     */       
/*     */       public final D apply(Tuple2 x$1) {
/* 245 */         if (x$1 != null)
/* 245 */           return (D)this.g$1.apply(new Tuple2(x$1._1(), this.$outer.scala$collection$MapLike$MappedValues$$f.apply(x$1._2()))); 
/* 245 */         throw new MatchError(x$1);
/*     */       }
/*     */       
/*     */       public MapLike$MappedValues$$anonfun$foreach$3(MapLike.MappedValues $outer, Function1 g$1) {}
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<A, C>> iterator() {
/* 246 */       return scala$collection$MapLike$MappedValues$$$outer().iterator().withFilter((Function1)new MapLike$MappedValues$$anonfun$iterator$2(this)).map((Function1)new MapLike$MappedValues$$anonfun$iterator$3(this));
/*     */     }
/*     */     
/*     */     public class MapLike$MappedValues$$anonfun$iterator$2 extends AbstractFunction1<Tuple2<A, B>, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final boolean apply(Tuple2 check$ifrefutable$2) {
/*     */         boolean bool;
/* 246 */         if (check$ifrefutable$2 != null) {
/* 246 */           bool = true;
/*     */         } else {
/* 246 */           bool = false;
/*     */         } 
/* 246 */         return bool;
/*     */       }
/*     */       
/*     */       public MapLike$MappedValues$$anonfun$iterator$2(MapLike.MappedValues $outer) {}
/*     */     }
/*     */     
/*     */     public class MapLike$MappedValues$$anonfun$iterator$3 extends AbstractFunction1<Tuple2<A, B>, Tuple2<A, C>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Tuple2<A, C> apply(Tuple2 x$2) {
/* 246 */         if (x$2 != null)
/* 246 */           return new Tuple2(x$2._1(), this.$outer.scala$collection$MapLike$MappedValues$$f.apply(x$2._2())); 
/* 246 */         throw new MatchError(x$2);
/*     */       }
/*     */       
/*     */       public MapLike$MappedValues$$anonfun$iterator$3(MapLike.MappedValues $outer) {}
/*     */     }
/*     */     
/*     */     public int size() {
/* 247 */       return scala$collection$MapLike$MappedValues$$$outer().size();
/*     */     }
/*     */     
/*     */     public boolean contains(Object key) {
/* 248 */       return scala$collection$MapLike$MappedValues$$$outer().contains(key);
/*     */     }
/*     */     
/*     */     public Option<C> get(Object key) {
/* 249 */       Function1<B, C> function1 = this.scala$collection$MapLike$MappedValues$$f;
/*     */       Option option;
/* 249 */       return (option = scala$collection$MapLike$MappedValues$$$outer().get(key)).isEmpty() ? (Option<C>)None$.MODULE$ : (Option<C>)new Some(function1.apply(option.get()));
/*     */     }
/*     */   }
/*     */   
/*     */   public class MapLike$$anonfun$$plus$plus$1 extends AbstractFunction2<Map<A, B1>, Tuple2<A, B1>, Map<A, B1>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Map<A, B1> apply(Map x$4, Tuple2 x$5) {
/* 302 */       return x$4.$plus(x$5);
/*     */     }
/*     */     
/*     */     public MapLike$$anonfun$$plus$plus$1(MapLike $outer) {}
/*     */   }
/*     */   
/*     */   public class MapLike$$anonfun$filterNot$1 extends AbstractFunction1<Tuple2<A, B>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ObjectRef res$1;
/*     */     
/*     */     private final Function1 p$1;
/*     */     
/*     */     public MapLike$$anonfun$filterNot$1(MapLike $outer, ObjectRef res$1, Function1 p$1) {}
/*     */     
/*     */     public final void apply(Tuple2 kv) {
/* 318 */       if (BoxesRunTime.unboxToBoolean(this.p$1.apply(kv)))
/* 318 */         this.res$1.elem = ((Map)this.res$1.elem).$minus(kv._1()); 
/*     */     }
/*     */   }
/*     */   
/*     */   public class MapLike$$anonfun$addString$1 extends AbstractFunction1<Tuple2<A, B>, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(Tuple2 x0$1) {
/* 344 */       if (x0$1 != null)
/* 344 */         return (new StringBuilder()).append(StringAdd$.MODULE$.$plus$extension(Predef$.MODULE$.any2stringadd(x0$1._1()), " -> ")).append(x0$1._2()).toString(); 
/* 344 */       throw new MatchError(x0$1);
/*     */     }
/*     */     
/*     */     public MapLike$$anonfun$addString$1(MapLike $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\MapLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */