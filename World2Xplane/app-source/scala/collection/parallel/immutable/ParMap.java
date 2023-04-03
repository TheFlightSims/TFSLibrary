/*    */ package scala.collection.parallel.immutable;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Predef$;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.GenIterable;
/*    */ import scala.collection.GenMap;
/*    */ import scala.collection.GenSeq;
/*    */ import scala.collection.GenSet;
/*    */ import scala.collection.Iterable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Map;
/*    */ import scala.collection.Parallel;
/*    */ import scala.collection.Traversable;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.generic.GenericCompanion;
/*    */ import scala.collection.generic.GenericParMapCompanion;
/*    */ import scala.collection.generic.GenericParMapTemplate;
/*    */ import scala.collection.immutable.Map;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.collection.parallel.ParIterable;
/*    */ import scala.collection.parallel.ParMap;
/*    */ import scala.collection.parallel.ParMapLike;
/*    */ import scala.collection.parallel.ParSeq;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\t%baB\001\003!\003\r\ta\003\002\007!\006\024X*\0319\013\005\r!\021!C5n[V$\030M\0317f\025\t)a!\001\005qCJ\fG\016\\3m\025\t9\001\"\001\006d_2dWm\031;j_:T\021!C\001\006g\016\fG.Y\002\001+\raq#I\n\b\0015\t2e\013\0305!\tqq\"D\001\t\023\t\001\002B\001\004B]f\024VM\032\t\005%M)\002%D\001\007\023\t!bA\001\004HK:l\025\r\035\t\003-]a\001\001B\003\031\001\t\007\021DA\001L#\tQR\004\005\002\0177%\021A\004\003\002\b\035>$\b.\0338h!\tqa$\003\002 \021\t\031\021I\\=\021\005Y\tCA\002\022\001\t\013\007\021DA\001W!\025!s%\006\021*\033\005)#B\001\024\007\003\0359WM\\3sS\016L!\001K\023\003+\035+g.\032:jGB\013'/T1q)\026l\007\017\\1uKB\021!\006A\007\002\005A!A&L\013!\033\005!\021BA\001\005!\rQs&M\005\003a\t\0211\002U1s\023R,'/\0312mKB!aBM\013!\023\t\031\004B\001\004UkBdWM\r\t\007YU*\002e\016\035\n\005Y\"!A\003)be6\013\007\017T5lKB!!\006A\013!!\021I4(\006\021\016\003iR!a\001\004\n\005qR$aA'ba\")a\b\001C\001\0051A%\0338ji\022\"\022\001\021\t\003\035\005K!A\021\005\003\tUs\027\016\036\005\006\t\002!\t%R\001\r[\006\0048i\\7qC:LwN\\\013\002\rB\031AeR\025\n\005!+#AF$f]\026\024\030n\031)be6\013\007oQ8na\006t\027n\0348\t\013)\003A\021I&\002\013\025l\007\017^=\026\003]BQ!\024\001\005B9\013Ab\035;sS:<\007K]3gSb,\022a\024\t\003!Vk\021!\025\006\003%N\013A\001\\1oO*\tA+\001\003kCZ\f\027B\001,R\005\031\031FO]5oO\")\001\f\001C!3\006)Ao\\'baV\031!,\0301\025\005m\023\007\003\002\026\0019~\003\"AF/\005\013y;&\031A\r\003\003A\003\"A\0061\005\013\005<&\031A\r\003\003ECQaY,A\004\021\f!!\032<\021\t\025D\027g\033\b\003\035\031L!a\032\005\002\rA\023X\rZ3g\023\tI'N\001\t%Y\026\0348\017J2pY>tG\005\\3tg*\021q\r\003\t\005\035Ibv\fC\003n\001\021\005c.A\004va\022\fG/\0323\026\005=\024Hc\0019voB!!\006A\013r!\t1\"\017B\003tY\n\007AOA\001V#\t\001S\004C\003wY\002\007Q#A\002lKfDQ\001\0377A\002E\fQA^1mk\026DQA\037\001\007\002m\fQ\001\n9mkN,\"\001`@\025\007u\f\t\001\005\003+\001Uq\bC\001\f\000\t\025\031\030P1\001u\021\035\t\031!\037a\001\003\013\t!a\033<\021\t9\021TC \005\b\003\023\001A\021AA\006\003-9\030\016\0365EK\032\fW\017\034;\026\t\0055\0211\003\013\005\003\037\t)\002E\003+\001U\t\t\002E\002\027\003'!aa]A\004\005\004!\b\002CA\f\003\017\001\r!!\007\002\003\021\004bADA\016+\005E\021bAA\017\021\tIa)\0368di&|g.\r\005\b\003C\001A\021AA\022\003A9\030\016\0365EK\032\fW\017\034;WC2,X-\006\003\002&\005-B\003BA\024\003[\001RA\013\001\026\003S\0012AFA\026\t\031\031\030q\004b\001i\"A\021qCA\020\001\004\tIcB\004\0022\tA\t!a\r\002\rA\013'/T1q!\rQ\023Q\007\004\007\003\tA\t!a\016\024\t\005U\022\021\b\t\005I\005m\022&C\002\002>\025\022Q\002U1s\033\006\004h)Y2u_JL\b\002CA!\003k!\t!a\021\002\rqJg.\033;?)\t\t\031\004C\004K\003k!\t!a\022\026\r\005%\023qJA*+\t\tY\005\005\004+\001\0055\023\021\013\t\004-\005=CA\002\r\002F\t\007\021\004E\002\027\003'\"aAIA#\005\004I\002\002CA,\003k!\t!!\027\002\0279,woQ8nE&tWM]\013\007\0037\n9'a\033\026\005\005u\003c\002\027\002`\005\r\024QN\005\004\003C\"!\001C\"p[\nLg.\032:\021\r9\021\024QMA5!\r1\022q\r\003\0071\005U#\031A\r\021\007Y\tY\007\002\004#\003+\022\r!\007\t\007U\001\t)'!\033\t\021\005E\024Q\007C\002\003g\nAbY1o\005VLG\016\032$s_6,b!!\036\002\016\006EUCAA<!%!\023\021PA?\003\023\013\031*C\002\002|\025\022abQ1o\007>l'-\0338f\rJ|W\016\005\003\002\000\005\005UBAA\033\023\021\t\031)!\"\003\t\r{G\016\\\005\004\003\017+#!D$f]6\013\007OR1di>\024\030\020\005\004\017e\005-\025q\022\t\004-\0055EA\002\r\002p\t\007\021\004E\002\027\003##aAIA8\005\004I\002C\002\026\001\003\027\013yIB\004\002\030\006U\002!!'\003\027]KG\017\033#fM\006,H\016^\013\007\0037\013Y,a0\024\r\005U\025QTAa!!\ty*!.\002:\006uf\002BAQ\003gsA!a)\0022:!\021QUAX\035\021\t9+!,\016\005\005%&bAAV\025\0051AH]8pizJ\021!C\005\003\017!I!!\002\004\n\007\005EB!\003\003\002\030\006]&bAA\031\tA\031a#a/\005\ra\t)J1\001\032!\r1\022q\030\003\bE\005UEQ1\001\032!\031Q\003!!/\002>\"Y\021QYAK\005\003\005\013\021BAa\003))h\016Z3sYfLgn\032\005\f\003/\t)J!A!\002\023\tI\rE\004\017\0037\tI,!0\t\021\005\005\023Q\023C\001\003\033$b!a4\002R\006M\007\003CA@\003+\013I,!0\t\021\005\025\0271\032a\001\003\003D\001\"a\006\002L\002\007\021\021\032\005\b\025\006UE\021IAl+\t\ty\rC\004n\003+#\t%a7\026\t\005u\0271\035\013\007\003?\f9/!;\021\021\005}\024QSA]\003C\0042AFAr\t\035\031\030\021\034b\001\003K\f2!!0\036\021\0351\030\021\034a\001\003sCq\001_Am\001\004\t\t\017C\004{\003+#\t%!<\026\t\005=\030Q\037\013\005\003c\f9\020\005\005\002\000\005U\025\021XAz!\r1\022Q\037\003\bg\006-(\031AAs\021!\t\031!a;A\002\005e\bC\002\b3\003s\013\031\020\003\005\002~\006UE\021IA\000\003\031!S.\0338vgR!\021q\032B\001\021\0351\0301 a\001\003sC\001\"!\003\002\026\022\005#QA\013\005\005\017\021i\001\006\003\003\n\t=\001C\002\026\001\003s\023Y\001E\002\027\005\033!qa\035B\002\005\004\t)\017\003\005\002\030\t\r\001\031\001B\t!\035q\0211DA]\005\027A\001\"!\t\002\026\022\005#QC\013\005\005/\021i\002\006\003\003\032\t}\001C\002\026\001\003s\023Y\002E\002\027\005;!qa\035B\n\005\004\t)\017\003\005\002\030\tM\001\031\001B\016\021!\021\031#!&\005B\t\025\022aA:fcV\021!q\005\t\007sm\nI,!0")
/*    */ public interface ParMap<K, V> extends GenMap<K, V>, GenericParMapTemplate<K, V, ParMap>, ParMap<K, V>, ParIterable<Tuple2<K, V>>, ParMapLike<K, V, ParMap<K, V>, Map<K, V>> {
/*    */   GenericParMapCompanion<ParMap> mapCompanion();
/*    */   
/*    */   ParMap<K, V> empty();
/*    */   
/*    */   String stringPrefix();
/*    */   
/*    */   <P, Q> ParMap<P, Q> toMap(Predef$.less.colon.less<Tuple2<K, V>, Tuple2<P, Q>> paramless);
/*    */   
/*    */   <U> ParMap<K, U> updated(K paramK, U paramU);
/*    */   
/*    */   <U> ParMap<K, U> $plus(Tuple2<K, U> paramTuple2);
/*    */   
/*    */   <U> ParMap<K, U> withDefault(Function1<K, U> paramFunction1);
/*    */   
/*    */   <U> ParMap<K, U> withDefaultValue(U paramU);
/*    */   
/*    */   public class ParMap$$anonfun$withDefaultValue$1 extends AbstractFunction1<K, U> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Object d$2;
/*    */     
/*    */     public final U apply(Object x) {
/* 68 */       return (U)this.d$2;
/*    */     }
/*    */     
/*    */     public ParMap$$anonfun$withDefaultValue$1(ParMap $outer, Object d$2) {}
/*    */   }
/*    */   
/*    */   public static class WithDefault<K, V> extends ParMap.WithDefault<K, V> implements ParMap<K, V> {
/*    */     private final ParMap<K, V> underlying;
/*    */     
/*    */     private final Function1<K, V> d;
/*    */     
/*    */     public GenericParMapCompanion<ParMap> mapCompanion() {
/* 81 */       return ParMap$class.mapCompanion(this);
/*    */     }
/*    */     
/*    */     public String stringPrefix() {
/* 81 */       return ParMap$class.stringPrefix(this);
/*    */     }
/*    */     
/*    */     public <P, Q> ParMap<P, Q> toMap(Predef$.less.colon.less ev) {
/* 81 */       return ParMap$class.toMap(this, ev);
/*    */     }
/*    */     
/*    */     public GenericCompanion<ParIterable> companion() {
/* 81 */       return ParIterable$class.companion(this);
/*    */     }
/*    */     
/*    */     public ParIterable<Tuple2<K, V>> toIterable() {
/* 81 */       return ParIterable$class.toIterable(this);
/*    */     }
/*    */     
/*    */     public ParSeq<Tuple2<K, V>> toSeq() {
/* 81 */       return ParIterable$class.toSeq(this);
/*    */     }
/*    */     
/*    */     public WithDefault(ParMap<K, V> underlying, Function1<K, V> d) {
/* 81 */       super(
/* 82 */           underlying, d);
/*    */       ParIterable$class.$init$(this);
/*    */       ParMap$class.$init$(this);
/*    */     }
/*    */     
/*    */     public WithDefault<K, V> empty() {
/* 83 */       return new WithDefault(this.underlying.empty(), this.d);
/*    */     }
/*    */     
/*    */     public <U> WithDefault<K, U> updated(Object key, Object value) {
/* 84 */       return new WithDefault(this.underlying.updated((K)key, (V)value), this.d);
/*    */     }
/*    */     
/*    */     public <U> WithDefault<K, U> $plus(Tuple2 kv) {
/* 85 */       return updated((K)kv._1(), (U)kv._2());
/*    */     }
/*    */     
/*    */     public WithDefault<K, V> $minus(Object key) {
/* 86 */       return new WithDefault((ParMap<K, V>)this.underlying.$minus(key), this.d);
/*    */     }
/*    */     
/*    */     public <U> ParMap<K, U> withDefault(Function1<K, V> d) {
/* 87 */       return new WithDefault(this.underlying, d);
/*    */     }
/*    */     
/*    */     public <U> ParMap<K, U> withDefaultValue(Object d) {
/* 88 */       return new WithDefault(this.underlying, (Function1<K, V>)new ParMap$WithDefault$$anonfun$withDefaultValue$2(this, (WithDefault<K, V>)d));
/*    */     }
/*    */     
/*    */     public class ParMap$WithDefault$$anonfun$withDefaultValue$2 extends AbstractFunction1<K, U> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final Object d$1;
/*    */       
/*    */       public final U apply(Object x) {
/* 88 */         return (U)this.d$1;
/*    */       }
/*    */       
/*    */       public ParMap$WithDefault$$anonfun$withDefaultValue$2(ParMap.WithDefault $outer, Object d$1) {}
/*    */     }
/*    */     
/*    */     public Map<K, V> seq() {
/* 89 */       return ((Map)this.underlying.seq()).withDefault(this.d);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\immutable\ParMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */