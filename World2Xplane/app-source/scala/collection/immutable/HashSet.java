/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.collection.AbstractSet;
/*     */ import scala.collection.CustomParallelizable;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenSet;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Parallel;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Set;
/*     */ import scala.collection.SetLike;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.TraversableView;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.generic.GenericSetTemplate;
/*     */ import scala.collection.generic.Subtractable;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.immutable.ParHashSet;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\rEd\001B\001\003\001%\021q\001S1tQN+GO\003\002\004\t\005I\021.\\7vi\006\024G.\032\006\003\013\031\t!bY8mY\026\034G/[8o\025\0059\021!B:dC2\f7\001A\013\003\025E\031r\001A\006\034?\031RC\007E\002\r\033=i\021\001B\005\003\035\021\0211\"\0212tiJ\f7\r^*fiB\021\001#\005\007\001\t\025\021\002A1\001\024\005\005\t\025C\001\013\031!\t)b#D\001\007\023\t9bAA\004O_RD\027N\\4\021\005UI\022B\001\016\007\005\r\te.\037\t\0049uyQ\"\001\002\n\005y\021!aA*fiB!\001eI\b&\033\005\t#B\001\022\005\003\0359WM\\3sS\016L!\001J\021\003%\035+g.\032:jGN+G\017V3na2\fG/\032\t\0039\001\001B\001D\024\020S%\021\001\006\002\002\b'\026$H*[6f!\ra\002a\004\t\005\031-zQ&\003\002-\t\t!2)^:u_6\004\026M]1mY\026d\027N_1cY\026\0042A\f\032\020\033\005y#BA\0021\025\t\tD!\001\005qCJ\fG\016\\3m\023\t\031tF\001\006QCJD\025m\0355TKR\004\"!F\033\n\005Y2!\001D*fe&\fG.\033>bE2,\007\"\002\035\001\t\003I\024A\002\037j]&$h\bF\001*\021\025Y\004\001\"\021=\003%\031w.\0349b]&|g.F\001>!\r\001c(J\005\003\005\022\001cR3oKJL7mQ8na\006t\027n\0348\t\013\005\003A\021\t\"\002\007A\f'/F\001.\021\025!\005\001\"\021F\003\021\031\030N_3\026\003\031\003\"!F$\n\005!3!aA%oi\")!\n\001C!\027\006)Q-\0349usV\t\021\006C\003N\001\021\005a*\001\005ji\026\024\030\r^8s+\005y\005c\001\007Q\037%\021\021\013\002\002\t\023R,'/\031;pe\")1\013\001C!)\0069am\034:fC\016DWCA+`)\t1\026\f\005\002\026/&\021\001L\002\002\005+:LG\017C\003[%\002\0071,A\001g!\021)Bl\0040\n\005u3!!\003$v]\016$\030n\03482!\t\001r\fB\003a%\n\0071CA\001V\021\025\021\007\001\"\001d\003!\031wN\034;bS:\034HC\0013h!\t)R-\003\002g\r\t9!i\\8mK\006t\007\"\0025b\001\004y\021!A3\t\013)\004A\021I6\002\013\021\002H.^:\025\005%b\007\"\0025j\001\004y\001\"\0026\001\t\003rG\003B\025pcNDQ\001]7A\002=\tQ!\0327f[FBQA]7A\002=\tQ!\0327f[JBQ\001^7A\002U\fQ!\0327f[N\0042!\006<\020\023\t9hA\001\006=e\026\004X-\031;fIzBQ!\037\001\005\002i\fa\001J7j]V\034HCA\025|\021\025A\007\0201\001\020\021\025i\b\001\"\005\0031)G.Z7ICND7i\0343f)\t1u\020\003\004\002\002q\004\raD\001\004W\026L\bbBA\003\001\021U\021qA\001\bS6\004(o\034<f)\r1\025\021\002\005\b\003\027\t\031\0011\001G\003\025A7m\0343f\021!\ty\001\001C\001\t\005E\021aC2p[B,H/\032%bg\"$2ARA\n\021\035\t\t!!\004A\002=Aq!a\006\001\t#\tI\"\001\003hKR\004Dc\0023\002\034\005u\021\021\005\005\b\003\003\t)\0021\001\020\021\035\ty\"!\006A\002\031\013A\001[1tQ\"9\0211EA\013\001\0041\025!\0027fm\026d\007bBA\024\001\021\005\021\021F\001\tkB$\027\r^3eaQ9\021&a\013\002.\005=\002bBA\001\003K\001\ra\004\005\b\003?\t)\0031\001G\021\035\t\031#!\nA\002\031Cq!a\r\001\t#\t)$\001\005sK6|g/\03231)\035I\023qGA\035\003wAq!!\001\0022\001\007q\002C\004\002 \005E\002\031\001$\t\017\005\r\022\021\007a\001\r\"9\021q\b\001\005\022\005\005\023\001D<sSR,'+\0329mC\016,GCAA\"!\r)\022QI\005\004\003\0172!AB!osJ+g\rK\003\001\003\027\n\t\006E\002\026\003\033J1!a\024\007\005A\031VM]5bYZ+'o]5p]VKEIH\001\003\017\035\t)F\001E\001\003/\nq\001S1tQN+G\017E\002\035\00332a!\001\002\t\002\005m3#BA-\003;\"\004\003\002\021\002`\025J1!!\031\"\005MIU.\\;uC\ndWmU3u\r\006\034Go\034:z\021\035A\024\021\fC\001\003K\"\"!a\026\t\021\005%\024\021\fC\002\003W\nAbY1o\005VLG\016\032$s_6,B!!\034\002\000U\021\021q\016\t\nA\005E\024QOA?\003\003K1!a\035\"\0051\031\025M\034\"vS2$gI]8n!\021\t9(!\037\016\005\005e\023bAA>}\t!1i\0347m!\r\001\022q\020\003\007%\005\035$\031A\n\021\tq\001\021Q\020\005\b\025\006eC\021IAC+\021\t9)!$\026\005\005%\005\003\002\017\001\003\027\0032\001EAG\t\031\021\0221\021b\001'\035A\021\021SA-\021\023\t\031*\001\007F[B$\030\020S1tQN+G\017\005\003\002x\005Ue\001CAL\0033BI!!'\003\031\025k\007\017^=ICND7+\032;\024\t\005U\0251\024\t\0049\001A\002b\002\035\002\026\022\005\021q\024\013\003\003'C!\"a)\002\026\006\005I\021BAS\003-\021X-\0313SKN|GN^3\025\005\005\035\006\003BAU\003gk!!a+\013\t\0055\026qV\001\005Y\006twM\003\002\0022\006!!.\031<b\023\021\t),a+\003\r=\023'.Z2u\021!\tI,!\027\005\n\005m\026aD7bW\026D\025m\0355Ue&,7+\032;\026\t\005u&1\007\013\r\003\023)D!\017\003@\t\r#Q\t\t\007\003o\n\tM!\r\007\017\005\r\027\021\f\001\002F\nY\001*Y:i)JLWmU3u+\021\t9-!4\024\t\005\005\027\021\032\t\0059\001\tY\rE\002\021\003\033$aAEAa\005\004\031\002BCAi\003\003\024)\031!C\005\013\0061!-\033;nCBD!\"!6\002B\n\005\t\025!\003G\003\035\021\027\016^7ba\002B1\002^Aa\005\013\007I\021\001\003\002ZV\021\0211\034\t\006+\005u\027\021Z\005\004\003?4!!B!se\006L\bbCAr\003\003\024\t\021)A\005\0037\fa!\0327f[N\004\003BCAt\003\003\024)\031!C\005\013\006)1/\033>fa!Q\0211^Aa\005\003\005\013\021\002$\002\rML'0\032\031!\021\035A\024\021\031C\001\003_$\002\"!=\002t\006U\030q\037\t\007\003o\n\t-a3\t\017\005E\027Q\036a\001\r\"9A/!<A\002\005m\007bBAt\003[\004\rA\022\005\007\t\006\005G\021I#\t\021\005]\021\021\031C!\003{$r\001ZA\000\005\003\021\031\001\003\005\002\002\005m\b\031AAf\021\035\ty\"a?A\002\031Cq!a\t\002|\002\007a\t\003\005\002(\005\005G\021\tB\004)!\tIM!\003\003\f\t5\001\002CA\001\005\013\001\r!a3\t\017\005}!Q\001a\001\r\"9\0211\005B\003\001\0041\005\002CA\032\003\003$\tE!\005\025\021\005%'1\003B\013\005/A\001\"!\001\003\020\001\007\0211\032\005\b\003?\021y\0011\001G\021\035\t\031Ca\004A\002\031Cq!TAa\t\003\022Y\"\006\002\003\036A)ADa\b\002L&\031!\021\005\002\003\031Q\023\030.Z%uKJ\fGo\034:\t\017M\013\t\r\"\021\003&U!!q\005B\030)\r1&\021\006\005\b5\n\r\002\031\001B\026!\031)B,a3\003.A\031\001Ca\f\005\r\001\024\031C1\001\024!\r\001\"1\007\003\007%\005]&\031A\n\t\017\t]\022q\027a\001\r\006)\001.Y:ia!A!1HA\\\001\004\021i$A\003fY\026l\007\007\005\003\035\001\tE\002b\002B!\003o\003\rAR\001\006Q\006\034\b.\r\005\ba\006]\006\031\001B\037\021\035\t\031#a.A\002\0313qA!\023\002Z\001\021YE\001\005ICND7+\032;2+\021\021iEa\025\024\t\t\035#q\n\t\0059\001\021\t\006E\002\021\005'\"aA\005B$\005\004\031\002\"DA\001\005\017\022)\031!C\001\0033\0229&\006\002\003R!Y!1\fB$\005\003\005\013\021\002B)\003\021YW-\037\021\t\031\005}!q\tBC\002\023\005\021\021L#\t\025\t\005$q\tB\001B\003%a)A\003iCND\007\005C\0049\005\017\"\tA!\032\025\r\t\035$\021\016B6!\031\t9Ha\022\003R!A\021\021\001B2\001\004\021\t\006C\004\002 \t\r\004\031\001$\t\r\021\0239\005\"\021F\021!\t9Ba\022\005B\tEDc\0023\003t\tU$q\017\005\t\003\003\021y\0071\001\003R!9\021q\004B8\001\0041\005bBA\022\005_\002\rA\022\005\t\003O\0219\005\"\021\003|QA!q\nB?\005\022\t\t\003\005\002\002\te\004\031\001B)\021\035\tyB!\037A\002\031Cq!a\t\003z\001\007a\t\003\005\0024\t\035C\021\tBC)!\021yEa\"\003\n\n-\005\002CA\001\005\007\003\rA!\025\t\017\005}!1\021a\001\r\"9\0211\005BB\001\0041\005bB'\003H\021\005#qR\013\003\005#\003B\001\004)\003R!91Ka\022\005B\tUU\003\002BL\005?#2A\026BM\021\035Q&1\023a\001\0057\003b!\006/\003R\tu\005c\001\t\003 \0221\001Ma%C\002M1\001Ba)\002Z\001\021!Q\025\002\022\021\006\034\bnU3u\007>dG.[:j_:\fT\003\002BT\005[\033BA!)\003*B!A\004\001BV!\r\001\"Q\026\003\007%\t\005&\031A\n\t\031\005}!\021\025BC\002\023\005\021\021L#\t\025\t\005$\021\025B\001B\003%a\tC\006\0036\n\005&Q1A\005\002\t]\026AA6t+\t\021I\fE\003\035\005w\023Y+C\002\003>\n\021q\001T5tiN+G\017C\006\003B\n\005&\021!Q\001\n\te\026aA6tA!9\001H!)\005\002\t\025GC\002Bd\005\023\024Y\r\005\004\002x\t\005&1\026\005\b\003?\021\031\r1\001G\021!\021)La1A\002\te\006B\002#\003\"\022\005S\t\003\005\002\030\t\005F\021\tBi)\035!'1\033Bk\005/D\001\"!\001\003P\002\007!1\026\005\b\003?\021y\r1\001G\021\035\t\031Ca4A\002\031C\001\"a\n\003\"\022\005#1\034\013\t\005S\023iNa8\003b\"A\021\021\001Bm\001\004\021Y\013C\004\002 \te\007\031\001$\t\017\005\r\"\021\034a\001\r\"A\0211\007BQ\t\003\022)\017\006\005\003*\n\035(\021\036Bv\021!\t\tAa9A\002\t-\006bBA\020\005G\004\rA\022\005\b\003G\021\031\0171\001G\021\035i%\021\025C!\005_,\"A!=\021\t1\001&1\026\005\b'\n\005F\021\tB{+\021\0219Pa@\025\007Y\023I\020C\004[\005g\004\rAa?\021\rUa&1\026B!\r\001\"q \003\007A\nM(\031A\n\t\021\r\r!\021\025C\005\007\013\t1b\036:ji\026|%M[3diR\031aka\002\t\021\r%1\021\001a\001\007\027\t1a\\;u!\021\031iaa\005\016\005\r=!\002BB\t\003_\013!![8\n\t\rU1q\002\002\023\037\nTWm\031;PkR\004X\017^*ue\026\fW\016\003\005\004\032\t\005F\021BB\016\003)\021X-\0313PE*,7\r\036\013\004-\016u\001\002CB\020\007/\001\ra!\t\002\005%t\007\003BB\007\007GIAa!\n\004\020\t\trJ\0316fGRLe\016];u'R\024X-Y7\007\017\r%\022\021\f\003\004,\t\0212+\032:jC2L'0\031;j_:\004&o\034=z+\031\031ic!\017\004ZM)1qEA\"i!Y1\021GB\024\005\003\007I\021BB\032\003\021y'/[4\026\005\rU\002\003\002\017\001\007o\0012\001EB\035\t\031\0212q\005b\001'!Y1QHB\024\005\003\007I\021BB \003!y'/[4`I\025\fHc\001,\004B!Q11IB\036\003\003\005\ra!\016\002\007a$\023\007C\006\004H\r\035\"\021!Q!\n\rU\022!B8sS\036\004\003\006BB#\007\027\0022!FB'\023\r\031yE\002\002\niJ\fgn]5f]RDq\001OB\024\t\003\031\031\006\006\003\004V\ru\003\003CA<\007O\0319da\026\021\007A\031I\006B\004\004\\\r\035\"\031A\n\003\003\tC\001b!\r\004R\001\0071Q\007\005\t\007\007\0319\003\"\003\004bQ\031aka\031\t\021\r%1q\fa\001\007\027A\001b!\007\004(\021%1q\r\013\004-\016%\004\002CB\020\007K\002\ra!\t\t\021\005\r6q\005C\005\003\003Bcaa\n\002L\005E\003BCAR\0033\n\t\021\"\003\002&\002")
/*     */ public class HashSet<A> extends AbstractSet<A> implements Set<A>, GenericSetTemplate<A, HashSet>, SetLike<A, HashSet<A>>, CustomParallelizable<A, ParHashSet<A>>, Serializable {
/*     */   public static final long serialVersionUID = 2L;
/*     */   
/*     */   public Combiner<A, ParHashSet<A>> parCombiner() {
/*  33 */     return CustomParallelizable.class.parCombiner(this);
/*     */   }
/*     */   
/*     */   public <B> Set<B> toSet() {
/*  33 */     return Set$class.toSet(this);
/*     */   }
/*     */   
/*     */   public Set<A> seq() {
/*  33 */     return Set$class.seq(this);
/*     */   }
/*     */   
/*     */   public HashSet() {
/*  33 */     Traversable$class.$init$(this);
/*  33 */     Iterable$class.$init$(this);
/*  33 */     Set$class.$init$(this);
/*  33 */     CustomParallelizable.class.$init$(this);
/*     */   }
/*     */   
/*     */   public GenericCompanion<HashSet> companion() {
/*  40 */     return (GenericCompanion<HashSet>)HashSet$.MODULE$;
/*     */   }
/*     */   
/*     */   public ParHashSet<A> par() {
/*  44 */     return scala.collection.parallel.immutable.ParHashSet$.MODULE$.fromTrie(this);
/*     */   }
/*     */   
/*     */   public int size() {
/*  46 */     return 0;
/*     */   }
/*     */   
/*     */   public HashSet<A> empty() {
/*  48 */     return HashSet$.MODULE$.empty();
/*     */   }
/*     */   
/*     */   public Iterator<A> iterator() {
/*  50 */     return scala.collection.Iterator$.MODULE$.empty();
/*     */   }
/*     */   
/*     */   public <U> void foreach(Function1 f) {}
/*     */   
/*     */   public boolean contains(Object e) {
/*  54 */     return get0((A)e, computeHash((A)e), 0);
/*     */   }
/*     */   
/*     */   public HashSet<A> $plus(Object e) {
/*  56 */     return updated0((A)e, computeHash((A)e), 0);
/*     */   }
/*     */   
/*     */   public HashSet<A> $plus(Object elem1, Object elem2, Seq elems) {
/*  59 */     return (HashSet<A>)$plus((A)elem1).$plus((A)elem2).$plus$plus((GenTraversableOnce)elems);
/*     */   }
/*     */   
/*     */   public HashSet<A> $minus(Object e) {
/*  63 */     return removed0((A)e, computeHash((A)e), 0);
/*     */   }
/*     */   
/*     */   public int elemHashCode(Object key) {
/*  65 */     return scala.runtime.ScalaRunTime$.MODULE$.hash(key);
/*     */   }
/*     */   
/*     */   public final int improve(int hcode) {
/*  68 */     int h = hcode + (hcode << 9 ^ 0xFFFFFFFF);
/*  69 */     h ^= h >>> 14;
/*  70 */     h += h << 4;
/*  71 */     return h ^ h >>> 10;
/*     */   }
/*     */   
/*     */   public int computeHash(Object key) {
/*  74 */     return improve(elemHashCode((A)key));
/*     */   }
/*     */   
/*     */   public boolean get0(Object key, int hash, int level) {
/*  76 */     return false;
/*     */   }
/*     */   
/*     */   public HashSet<A> updated0(Object key, int hash, int level) {
/*  79 */     return new HashSet1<A>((A)key, hash);
/*     */   }
/*     */   
/*     */   public HashSet<A> removed0(Object key, int hash, int level) {
/*  81 */     return this;
/*     */   }
/*     */   
/*     */   public Object writeReplace() {
/*  83 */     return new SerializationProxy<Object, Object>(this);
/*     */   }
/*     */   
/*     */   public static <A> CanBuildFrom<HashSet<?>, A, HashSet<A>> canBuildFrom() {
/*     */     return HashSet$.MODULE$.canBuildFrom();
/*     */   }
/*     */   
/*     */   public static <A> Object setCanBuildFrom() {
/*     */     return HashSet$.MODULE$.setCanBuildFrom();
/*     */   }
/*     */   
/*     */   public static class EmptyHashSet$ extends HashSet<Object> {
/*     */     public static final EmptyHashSet$ MODULE$;
/*     */     
/*     */     private Object readResolve() {
/* 104 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public EmptyHashSet$() {
/* 104 */       MODULE$ = this;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class HashSet1<A> extends HashSet<A> {
/*     */     private final A key;
/*     */     
/*     */     private final int hash;
/*     */     
/*     */     public A key() {
/* 132 */       return this.key;
/*     */     }
/*     */     
/*     */     public int hash() {
/* 132 */       return this.hash;
/*     */     }
/*     */     
/*     */     public HashSet1(Object key, int hash) {}
/*     */     
/*     */     public int size() {
/* 133 */       return 1;
/*     */     }
/*     */     
/*     */     public boolean get0(Object key, int hash, int level) {
/* 136 */       if (hash == hash()) {
/* 136 */         A a = key();
/* 136 */         if ((key == a) ? true : ((key == null) ? false : ((key instanceof Number) ? BoxesRunTime.equalsNumObject((Number)key, a) : ((key instanceof Character) ? BoxesRunTime.equalsCharObject((Character)key, a) : key.equals(a)))));
/*     */       } 
/* 136 */       return false;
/*     */     }
/*     */     
/*     */     public HashSet<A> updated0(Object key, int hash, int level) {
/* 139 */       if (hash == hash()) {
/* 139 */         A a = key();
/* 139 */         if ((key == a) ? true : ((key == null) ? false : ((key instanceof Number) ? BoxesRunTime.equalsNumObject((Number)key, a) : ((key instanceof Character) ? BoxesRunTime.equalsCharObject((Character)key, a) : key.equals(a)))));
/*     */       } 
/* 139 */       return 
/*     */         
/* 141 */         (hash != hash()) ? 
/* 142 */         HashSet$.MODULE$.<A>scala$collection$immutable$HashSet$$makeHashTrieSet(hash(), this, hash, new HashSet1((A)key, hash), level) : 
/*     */         
/* 145 */         new HashSet.HashSetCollision1<A>(hash, ListSet$.MODULE$.<A>empty().$plus(key()).$plus((A)key));
/*     */     }
/*     */     
/*     */     public HashSet<A> removed0(Object key, int hash, int level) {
/* 150 */       if (hash == hash()) {
/* 150 */         A a = key();
/* 150 */         if ((key == a) ? true : ((key == null) ? false : ((key instanceof Number) ? BoxesRunTime.equalsNumObject((Number)key, a) : ((key instanceof Character) ? BoxesRunTime.equalsCharObject((Character)key, a) : key.equals(a)))));
/*     */       } 
/* 150 */       return this;
/*     */     }
/*     */     
/*     */     public Iterator<A> iterator() {
/* 152 */       return scala.collection.Iterator$.MODULE$.apply((Seq)scala.Predef$.MODULE$.genericWrapArray(new Object[] { key() }));
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/* 153 */       f.apply(key());
/*     */     }
/*     */   }
/*     */   
/*     */   public static class HashSetCollision1<A> extends HashSet<A> {
/*     */     private final int hash;
/*     */     
/*     */     private final ListSet<A> ks;
/*     */     
/*     */     public int hash() {
/* 156 */       return this.hash;
/*     */     }
/*     */     
/*     */     public ListSet<A> ks() {
/* 156 */       return this.ks;
/*     */     }
/*     */     
/*     */     public HashSetCollision1(int hash, ListSet<A> ks) {}
/*     */     
/*     */     public int size() {
/* 159 */       return ks().size();
/*     */     }
/*     */     
/*     */     public boolean get0(Object key, int hash, int level) {
/* 162 */       return (hash == hash()) ? ks().contains((A)key) : false;
/*     */     }
/*     */     
/*     */     public HashSet<A> updated0(Object key, int hash, int level) {
/* 165 */       return (hash == hash()) ? new HashSetCollision1(hash, ks().$plus((A)key)) : 
/* 166 */         HashSet$.MODULE$.<A>scala$collection$immutable$HashSet$$makeHashTrieSet(hash(), this, hash, new HashSet.HashSet1<A>((A)key, hash), level);
/*     */     }
/*     */     
/*     */     public HashSet<A> removed0(Object key, int hash, int level) {
/* 170 */       ListSet<A> ks1 = ks().$minus((A)key);
/* 171 */       return (hash == hash()) ? (ks1.isEmpty() ? 
/* 172 */         HashSet$.MODULE$.<A>empty() : (
/* 173 */         ks1.tail().isEmpty() ? 
/* 174 */         new HashSet.HashSet1<A>(ks1.head(), hash) : 
/*     */         
/* 176 */         new HashSetCollision1(hash, ks1))) : 
/* 177 */         this;
/*     */     }
/*     */     
/*     */     public Iterator<A> iterator() {
/* 179 */       return ks().iterator();
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/* 180 */       ks().foreach(f);
/*     */     }
/*     */     
/*     */     private void writeObject(ObjectOutputStream out) {
/* 187 */       throw scala.sys.package$.MODULE$.error("cannot serialize an immutable.HashSet where all items have the same 32-bit hash code");
/*     */     }
/*     */     
/*     */     private void readObject(ObjectInputStream in) {
/* 192 */       throw scala.sys.package$.MODULE$.error("cannot deserialize an immutable.HashSet where all items have the same 32-bit hash code");
/*     */     }
/*     */   }
/*     */   
/*     */   public static class HashTrieSet<A> extends HashSet<A> {
/*     */     private final int bitmap;
/*     */     
/*     */     private final HashSet<A>[] elems;
/*     */     
/*     */     private final int size0;
/*     */     
/*     */     private int bitmap() {
/* 199 */       return this.bitmap;
/*     */     }
/*     */     
/*     */     public HashSet<A>[] elems() {
/* 199 */       return this.elems;
/*     */     }
/*     */     
/*     */     private int size0() {
/* 199 */       return this.size0;
/*     */     }
/*     */     
/*     */     public HashTrieSet(int bitmap, HashSet[] elems, int size0) {
/* 201 */       scala.Predef$.MODULE$.assert((Integer.bitCount(bitmap) == elems.length));
/*     */     }
/*     */     
/*     */     public int size() {
/* 205 */       return size0();
/*     */     }
/*     */     
/*     */     public boolean get0(Object key, int hash, int level) {
/* 208 */       int index = hash >>> level & 0x1F;
/* 209 */       int mask = 1 << index;
/* 213 */       int offset = Integer.bitCount(bitmap() & mask - 1);
/* 215 */       return (bitmap() == -1) ? elems()[index & 0x1F].get0((A)key, hash, level + 5) : (((bitmap() & mask) != 0) ? elems()[offset].get0((A)key, hash, level + 5) : false);
/*     */     }
/*     */     
/*     */     public HashSet<A> updated0(Object key, int hash, int level) {
/* 221 */       int index = hash >>> level & 0x1F;
/* 222 */       int mask = 1 << index;
/* 223 */       int offset = Integer.bitCount(bitmap() & mask - 1);
/* 226 */       HashSet<A> sub = elems()[offset];
/* 227 */       HashSet<A> subNew = sub.updated0((A)key, hash, level + 5);
/* 230 */       HashSet[] elemsNew = new HashSet[(elems()).length];
/* 231 */       scala.Array$.MODULE$.copy(elems(), 0, elemsNew, 0, (elems()).length);
/* 232 */       elemsNew[offset] = subNew;
/* 236 */       HashSet[] arrayOfHashSet1 = new HashSet[(elems()).length + 1];
/* 237 */       scala.Array$.MODULE$.copy(elems(), 0, arrayOfHashSet1, 0, offset);
/* 238 */       arrayOfHashSet1[offset] = new HashSet.HashSet1(key, hash);
/* 239 */       scala.Array$.MODULE$.copy(elems(), offset, arrayOfHashSet1, offset + 1, (elems()).length - offset);
/* 240 */       int bitmapNew = bitmap() | mask;
/* 241 */       return ((bitmap() & mask) != 0) ? ((sub == subNew) ? this : new HashTrieSet(bitmap(), (HashSet<A>[])elemsNew, size() + subNew.size() - sub.size())) : new HashTrieSet(bitmapNew, (HashSet<A>[])arrayOfHashSet1, size() + 1);
/*     */     }
/*     */     
/*     */     public HashSet<A> removed0(Object key, int hash, int level) {
/* 246 */       int index = hash >>> level & 0x1F;
/* 247 */       int mask = 1 << index;
/* 248 */       int offset = Integer.bitCount(bitmap() & mask - 1);
/* 250 */       HashSet<A> sub = elems()[offset];
/* 252 */       HashSet<A> subNew = sub.removed0((A)key, hash, level + 5);
/* 255 */       int bitmapNew = bitmap() ^ mask;
/* 257 */       HashSet[] elemsNew = new HashSet[(elems()).length - 1];
/* 258 */       scala.Array$.MODULE$.copy(elems(), 0, elemsNew, 0, offset);
/* 259 */       scala.Array$.MODULE$.copy(elems(), offset + 1, elemsNew, offset, (elems()).length - offset - 1);
/* 260 */       int sizeNew = size() - sub.size();
/* 270 */       HashSet[] arrayOfHashSet1 = new HashSet[(elems()).length];
/* 271 */       scala.Array$.MODULE$.copy(elems(), 0, arrayOfHashSet1, 0, (elems()).length);
/* 272 */       arrayOfHashSet1[offset] = subNew;
/* 273 */       int i = size() + subNew.size() - sub.size();
/* 274 */       return ((bitmap() & mask) != 0) ? ((sub == subNew) ? this : (subNew.isEmpty() ? ((bitmapNew != 0) ? ((elemsNew.length == 1 && !(elemsNew[0] instanceof HashTrieSet)) ? elemsNew[0] : new HashTrieSet(bitmapNew, (HashSet<A>[])elemsNew, sizeNew)) : HashSet$.MODULE$.<A>empty()) : new HashTrieSet(bitmap(), (HashSet<A>[])arrayOfHashSet1, i))) : 
/*     */         
/* 277 */         this;
/*     */     }
/*     */     
/*     */     public TrieIterator<A> iterator() {
/* 281 */       return new HashSet$HashTrieSet$$anon$1(this);
/*     */     }
/*     */     
/*     */     public class HashSet$HashTrieSet$$anon$1 extends TrieIterator<A> {
/*     */       public HashSet$HashTrieSet$$anon$1(HashSet.HashTrieSet $outer) {
/* 281 */         super((Iterable<A>[])$outer.elems());
/*     */       }
/*     */       
/*     */       public final A getElem(Object cc) {
/* 282 */         return ((HashSet.HashSet1<A>)cc).key();
/*     */       }
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1<A, ?> f) {
/* 304 */       int i = 0;
/* 305 */       while (i < (elems()).length) {
/* 306 */         elems()[i].foreach(f);
/* 307 */         i++;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static class SerializationProxy<A, B> implements Serializable {
/*     */     public static final long serialVersionUID = 2L;
/*     */     
/*     */     private transient HashSet<A> scala$collection$immutable$HashSet$SerializationProxy$$orig;
/*     */     
/*     */     public HashSet<A> scala$collection$immutable$HashSet$SerializationProxy$$orig() {
/* 312 */       return this.scala$collection$immutable$HashSet$SerializationProxy$$orig;
/*     */     }
/*     */     
/*     */     public void scala$collection$immutable$HashSet$SerializationProxy$$orig_$eq(HashSet<A> x$1) {
/* 312 */       this.scala$collection$immutable$HashSet$SerializationProxy$$orig = x$1;
/*     */     }
/*     */     
/*     */     public SerializationProxy(HashSet<A> orig) {}
/*     */     
/*     */     private void writeObject(ObjectOutputStream out) {
/* 314 */       int s = scala$collection$immutable$HashSet$SerializationProxy$$orig().size();
/* 315 */       out.writeInt(s);
/* 316 */       scala$collection$immutable$HashSet$SerializationProxy$$orig().foreach((Function1<A, ?>)new HashSet$SerializationProxy$$anonfun$writeObject$1(this, (SerializationProxy<A, B>)out));
/*     */     }
/*     */     
/*     */     public class HashSet$SerializationProxy$$anonfun$writeObject$1 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final ObjectOutputStream out$1;
/*     */       
/*     */       public HashSet$SerializationProxy$$anonfun$writeObject$1(HashSet.SerializationProxy $outer, ObjectOutputStream out$1) {}
/*     */       
/*     */       public final void apply(Object e) {
/* 317 */         this.out$1.writeObject(e);
/*     */       }
/*     */     }
/*     */     
/*     */     private void readObject(ObjectInputStream in) {
/* 322 */       HashSet$ hashSet$ = HashSet$.MODULE$;
/* 322 */       scala$collection$immutable$HashSet$SerializationProxy$$orig_$eq(HashSet.EmptyHashSet$.MODULE$);
/* 323 */       int s = in.readInt();
/* 324 */       scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 324 */       Range$ range$ = Range$.MODULE$;
/* 324 */       HashSet$SerializationProxy$$anonfun$readObject$1 hashSet$SerializationProxy$$anonfun$readObject$1 = new HashSet$SerializationProxy$$anonfun$readObject$1(this, (SerializationProxy<A, B>)in);
/*     */       Range range;
/* 324 */       if ((range = new Range(0, s, 1)).validateRangeBoundaries((Function1<Object, Object>)hashSet$SerializationProxy$$anonfun$readObject$1)) {
/*     */         int terminal1;
/*     */         int step1;
/*     */         int i1;
/* 324 */         for (i1 = range.start(), terminal1 = range.terminalElement(), step1 = range.step(); i1 != terminal1; ) {
/* 324 */           Object e1 = in.readObject();
/* 324 */           scala$collection$immutable$HashSet$SerializationProxy$$orig_$eq(scala$collection$immutable$HashSet$SerializationProxy$$orig().$plus((A)e1));
/* 324 */           i1 += step1;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     public class HashSet$SerializationProxy$$anonfun$readObject$1 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final ObjectInputStream in$1;
/*     */       
/*     */       public final void apply(int i) {
/* 324 */         apply$mcVI$sp(i);
/*     */       }
/*     */       
/*     */       public HashSet$SerializationProxy$$anonfun$readObject$1(HashSet.SerializationProxy $outer, ObjectInputStream in$1) {}
/*     */       
/*     */       public void apply$mcVI$sp(int i) {
/* 325 */         Object e = this.in$1.readObject();
/* 326 */         this.$outer.scala$collection$immutable$HashSet$SerializationProxy$$orig_$eq(this.$outer.scala$collection$immutable$HashSet$SerializationProxy$$orig().$plus(e));
/*     */       }
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 330 */       return scala$collection$immutable$HashSet$SerializationProxy$$orig();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\HashSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */