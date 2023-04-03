/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenSet;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Map;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Set;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.TraversableView;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.generic.Subtractable;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.immutable.ParMap;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\021]baB\001\003!\003\r\t!\003\002\004\033\006\004(BA\002\005\003%IW.\\;uC\ndWM\003\002\006\r\005Q1m\0347mK\016$\030n\0348\013\003\035\tQa]2bY\006\034\001!F\002\0131\t\032R\001A\006\020I\035\002\"\001D\007\016\003\031I!A\004\004\003\r\005s\027PU3g!\r\001\022cE\007\002\005%\021!C\001\002\t\023R,'/\0312mKB!A\002\006\f\"\023\t)bA\001\004UkBdWM\r\t\003/aa\001\001B\003\032\001\t\007!DA\001B#\tYb\004\005\002\r9%\021QD\002\002\b\035>$\b.\0338h!\taq$\003\002!\r\t\031\021I\\=\021\005]\021CAB\022\001\t\013\007!DA\001C!\021)cEF\021\016\003\021I!!\001\003\021\013AAc#\t\026\n\005%\022!aB'ba2K7.\032\t\005!\0011\022\005C\003-\001\021\005Q&\001\004%S:LG\017\n\013\002]A\021AbL\005\003a\031\021A!\0268ji\")!\007\001C!g\005)Q-\0349usV\t!\006C\0036\001\021\005c'A\003u_6\013\007/F\0028uu\"\"\001O \021\tA\001\021\b\020\t\003/i\"Qa\017\033C\002i\021\021\001\026\t\003/u\"QA\020\033C\002i\021\021!\026\005\006\001R\002\035!Q\001\003KZ\004BAQ#\024\021:\021AbQ\005\003\t\032\ta\001\025:fI\0264\027B\001$H\005A!C.Z:tI\r|Gn\0348%Y\026\0348O\003\002E\rA!A\002F\035=\021\025Q\005\001\"\0214\003\r\031X-\035\005\006\031\002!\t!T\001\fo&$\b\016R3gCVdG/\006\002O#R\021q\n\026\t\005!\0011\002\013\005\002\030#\022)!k\023b\001'\n\021!)M\t\003CyAQ!V&A\002Y\013\021\001\032\t\005\031]3\002+\003\002Y\r\tIa)\0368di&|g.\r\005\0065\002!\taW\001\021o&$\b\016R3gCVdGOV1mk\026,\"\001X0\025\005u\003\007\003\002\t\001-y\003\"aF0\005\013IK&\031A*\t\013UK\006\031\0010\t\013\t\004a\021I2\002\017U\004H-\031;fIV\021Am\032\013\004K\"T\007\003\002\t\001-\031\004\"aF4\005\013I\013'\031A*\t\013%\f\007\031\001\f\002\007-,\027\020C\003lC\002\007a-A\003wC2,X\rC\003n\001\031\005a.A\003%a2,8/\006\002peR\021\001o\035\t\005!\0011\022\017\005\002\030e\022)!\013\034b\001'\")A\017\034a\001k\006\0211N\036\t\005\031Q1\022oB\003x\005!\005\0010A\002NCB\004\"\001E=\007\013\005\021\001\022\001>\024\005e\\\b\003\002?\000\003\007i\021! \006\003}\022\tqaZ3oKJL7-C\002\002\002u\0241#S7nkR\f'\r\\3NCB4\025m\031;pef\004\"\001\005\001\t\017\005\035\021\020\"\001\002\n\0051A(\0338jiz\"\022\001\037\005\b\003\033IH1AA\b\0031\031\027M\034\"vS2$gI]8n+\031\t\t\"!\013\002.U\021\0211\003\t\ny\006U\021\021DA\023\003_I1!a\006~\0051\031\025M\034\"vS2$gI]8n!\021\tY\"!\b\016\003eLA!a\b\002\"\t!1i\0347m\023\r\t\031# \002\016\017\026tW*\0319GC\016$xN]=\021\r1!\022qEA\026!\r9\022\021\006\003\0073\005-!\031\001\016\021\007]\ti\003\002\004$\003\027\021\rA\007\t\007!\001\t9#a\013\t\rIJH\021AA\032+\031\t)$a\017\002@U\021\021q\007\t\007!\001\tI$!\020\021\007]\tY\004\002\004\032\003c\021\rA\007\t\004/\005}BAB\022\0022\t\007!D\002\004\002De\004\021Q\t\002\f/&$\b\016R3gCVdG/\006\004\002H\005\r\024qM\n\007\003\003\nI%!\033\021\021\005-\023QLA1\003KrA!!\024\002\\9!\021qJA-\035\021\t\t&a\026\016\005\005M#bAA+\021\0051AH]8pizJ\021aB\005\003\013\031I!a\036\003\n\t\005\r\023q\f\006\003o\022\0012aFA2\t\031I\022\021\tb\0015A\031q#a\032\005\017\r\n\t\005\"b\0015A1\001\003AA1\003KB1\"!\034\002B\t\005\t\025!\003\002j\005QQO\0343fe2L\030N\\4\t\025U\013\tE!A!\002\023\t\t\b\005\004\r/\006\005\024Q\r\005\t\003\017\t\t\005\"\001\002vQ1\021qOA=\003w\002\002\"a\007\002B\005\005\024Q\r\005\t\003[\n\031\b1\001\002j!9Q+a\035A\002\005E\004b\002\032\002B\021\005\023qP\013\003\003oBqAYA!\t\003\n\031)\006\003\002\006\006-ECBAD\003\037\013\t\n\005\005\002\034\005\005\023\021MAE!\r9\0221\022\003\b%\006\005%\031AAG#\r\t)G\b\005\bS\006\005\005\031AA1\021\035Y\027\021\021a\001\003\023Cq!\\A!\t\003\n)*\006\003\002\030\006uE\003BAM\003?\003\002\"a\007\002B\005\005\0241\024\t\004/\005uEa\002*\002\024\n\007\021Q\022\005\bi\006M\005\031AAQ!\031aA#!\031\002\034\"A\021QUA!\t\003\n9+\001\004%[&tWo\035\013\005\003o\nI\013C\004j\003G\003\r!!\031\t\0171\013\t\005\"\021\002.V!\021qVA[)\021\t\t,a.\021\rA\001\021\021MAZ!\r9\022Q\027\003\b%\006-&\031AAG\021\035)\0261\026a\001\003s\003b\001D,\002b\005M\006b\002.\002B\021\005\023QX\013\005\003\013)\r\006\003\002B\006\035\007C\002\t\001\003C\n\031\rE\002\030\003\013$qAUA^\005\004\ti\tC\004V\003w\003\r!a1\b\017\005-\027\020#\003\002N\006AQ)\0349us6\013\007\017\005\003\002\034\005=gaBAis\"%\0211\033\002\t\0136\004H/_'baNA\021qZAk\0037\fi\016E\003\021\003/t2$C\002\002Z\n\0211\"\0212tiJ\f7\r^'baB!\001\003\001\020\034!\ra\021q\\\005\004\003C4!\001D*fe&\fG.\033>bE2,\007\002CA\004\003\037$\t!!:\025\005\0055\007\002CAu\003\037$\t%a;\002\tML'0Z\013\003\003[\0042\001DAx\023\r\t\tP\002\002\004\023:$\b\002CA{\003\037$\t!a>\002\007\035,G\017\006\003\002z\006}\b\003\002\007\002|nI1!!@\007\005\031y\005\017^5p]\"1\021.a=A\002yA\001Ba\001\002P\022\005!QA\001\tSR,'/\031;peV\021!q\001\t\006K\t%!QB\005\004\005\027!!\001C%uKJ\fGo\034:\021\t1!bd\007\005\bE\006=G\021\tB\t+\021\021\031B!\007\025\r\tU!1\004B\017!\025\001\002A\bB\f!\r9\"\021\004\003\007%\n=!\031\001\016\t\r%\024y\0011\001\037\021\035Y'q\002a\001\005/Aq!\\Ah\t\003\021\t#\006\003\003$\t%B\003\002B\023\005W\001R\001\005\001\037\005O\0012a\006B\025\t\031\021&q\004b\0015!9AOa\bA\002\t5\002#\002\007\025=\t\035\002\002CAS\003\037$\tA!\r\025\t\005m'1\007\005\007S\n=\002\031\001\020\t\025\t]\022qZA\001\n\023\021I$A\006sK\006$'+Z:pYZ,GC\001B\036!\021\021iDa\022\016\005\t}\"\002\002B!\005\007\nA\001\\1oO*\021!QI\001\005U\0064\030-\003\003\003J\t}\"AB(cU\026\034GO\002\004\003Ne\004!q\n\002\005\033\006\004\030'\006\004\003R\t]#1L\n\t\005\027\022\031F!\030\002^B9\001#a6\003V\te\003cA\f\003X\0211\021Da\023C\002i\0012a\006B.\t\035\031#1\nCC\002i\001b\001\005\001\003V\te\003b\003B1\005\027\022\t\021)A\005\005+\nAa[3zc!Y!Q\rB&\005\003\005\013\021\002B-\003\0311\030\r\\;fc!A\021q\001B&\t\003\021I\007\006\004\003l\t5$q\016\t\t\0037\021YE!\026\003Z!A!\021\rB4\001\004\021)\006\003\005\003f\t\035\004\031\001B-\021!\tIOa\023\005B\005-\b\002CA{\005\027\"\tA!\036\025\t\t]$\021\020\t\006\031\005m(\021\f\005\bS\nM\004\031\001B+\021!\021\031Aa\023\005\002\tuTC\001B@!\025)#\021\002BA!\031aAC!\026\003Z!9!Ma\023\005B\t\025U\003\002BD\005\033#bA!#\003\022\nM\005C\002\t\001\005+\022Y\tE\002\030\005\033#qA\025BB\005\004\021y)E\002\003ZyAq!\033BB\001\004\021)\006C\004l\005\007\003\rAa#\t\0175\024Y\005\"\001\003\030V!!\021\024BP)\021\021YJ!)\021\rA\001!Q\013BO!\r9\"q\024\003\b%\nU%\031\001BH\021\035!(Q\023a\001\005G\003b\001\004\013\003V\tu\005\002CAS\005\027\"\tAa*\025\t\tu#\021\026\005\bS\n\025\006\031\001B+\021!\021iKa\023\005B\t=\026a\0024pe\026\f7\r[\013\005\005c\023Y\fF\002/\005gC\001B!.\003,\002\007!qW\001\002MB1Ab\026BA\005s\0032a\006B^\t\031q$1\026b\0015\0311!qX=\001\005\003\024A!T1qeU1!1\031Be\005\033\034\002B!0\003F\n=\027Q\034\t\b!\005]'q\031Bf!\r9\"\021\032\003\0073\tu&\031\001\016\021\007]\021i\rB\004$\005{#)\031\001\016\021\rA\001!q\031Bf\021-\021\tG!0\003\002\003\006IAa2\t\027\t\025$Q\030B\001B\003%!1\032\005\f\005/\024iL!A!\002\023\0219-\001\003lKf\024\004b\003Bn\005{\023\t\021)A\005\005\027\faA^1mk\026\024\004\002CA\004\005{#\tAa8\025\025\t\005(1\035Bs\005O\024I\017\005\005\002\034\tu&q\031Bf\021!\021\tG!8A\002\t\035\007\002\003B3\005;\004\rAa3\t\021\t]'Q\034a\001\005\017D\001Ba7\003^\002\007!1\032\005\t\003S\024i\f\"\021\002l\"A\021Q\037B_\t\003\021y\017\006\003\003r\nM\b#\002\007\002|\n-\007bB5\003n\002\007!q\031\005\t\005\007\021i\f\"\001\003xV\021!\021 \t\006K\t%!1 \t\007\031Q\0219Ma3\t\017\t\024i\f\"\021\003\000V!1\021AB\004)\031\031\031aa\003\004\016A1\001\003\001Bd\007\013\0012aFB\004\t\035\021&Q b\001\007\023\t2Aa3\037\021\035I'Q a\001\005\017Dqa\033B\001\004\031)\001C\004n\005{#\ta!\005\026\t\rM1\021\004\013\005\007+\031Y\002\005\004\021\001\t\0357q\003\t\004/\reAa\002*\004\020\t\0071\021\002\005\bi\016=\001\031AB\017!\031aACa2\004\030!A\021Q\025B_\t\003\031\t\003\006\003\003P\016\r\002bB5\004 \001\007!q\031\005\t\005[\023i\f\"\021\004(U!1\021FB\031)\rq31\006\005\t\005k\033)\0031\001\004.A1Ab\026B~\007_\0012aFB\031\t\031q4Q\005b\0015\03111QG=\001\007o\021A!T1qgU11\021HB \007\007\032\002ba\r\004<\r\025\023Q\034\t\b!\005]7QHB!!\r92q\b\003\0073\rM\"\031\001\016\021\007]\031\031\005B\004$\007g!)\031\001\016\021\rA\0011QHB!\021-\021\tga\r\003\002\003\006Ia!\020\t\027\t\02541\007B\001B\003%1\021\t\005\f\005/\034\031D!A!\002\023\031i\004C\006\003\\\016M\"\021!Q\001\n\r\005\003bCB)\007g\021\t\021)A\005\007{\tAa[3zg!Y1QKB\032\005\003\005\013\021BB!\003\0311\030\r\\;fg!A\021qAB\032\t\003\031I\006\006\b\004\\\ru3qLB1\007G\032)ga\032\021\021\005m11GB\037\007\003B\001B!\031\004X\001\0071Q\b\005\t\005K\0329\0061\001\004B!A!q[B,\001\004\031i\004\003\005\003\\\016]\003\031AB!\021!\031\tfa\026A\002\ru\002\002CB+\007/\002\ra!\021\t\021\005%81\007C!\003WD\001\"!>\0044\021\0051Q\016\013\005\007_\032\t\bE\003\r\003w\034\t\005C\004j\007W\002\ra!\020\t\021\t\r11\007C\001\007k*\"aa\036\021\013\025\022Ia!\037\021\r1!2QHB!\021\035\02171\007C!\007{*Baa \004\006R11\021QBE\007\027\003b\001\005\001\004>\r\r\005cA\f\004\006\0229!ka\037C\002\r\035\025cAB!=!9\021na\037A\002\ru\002bB6\004|\001\00711\021\005\b[\016MB\021ABH+\021\031\tja&\025\t\rM5\021\024\t\007!\001\031id!&\021\007]\0319\nB\004S\007\033\023\raa\"\t\017Q\034i\t1\001\004\034B1A\002FB\037\007+C\001\"!*\0044\021\0051q\024\013\005\007\013\032\t\013C\004j\007;\003\ra!\020\t\021\t561\007C!\007K+Baa*\0040R\031af!+\t\021\tU61\025a\001\007W\003b\001D,\004z\r5\006cA\f\0040\0221aha)C\002i1aaa-z\001\rU&\001B'baR*baa.\004>\016\0057\003CBY\007s\033\031-!8\021\017A\t9na/\004@B\031qc!0\005\re\031\tL1\001\033!\r92\021\031\003\bG\rEFQ1\001\033!\031\001\002aa/\004@\"Y!\021MBY\005\003\005\013\021BB^\021-\021)g!-\003\002\003\006Iaa0\t\027\t]7\021\027B\001B\003%11\030\005\f\0057\034\tL!A!\002\023\031y\fC\006\004R\rE&\021!Q\001\n\rm\006bCB+\007c\023\t\021)A\005\007C1ba5\0042\n\005\t\025!\003\004<\006!1.Z=5\021-\0319n!-\003\002\003\006Iaa0\002\rY\fG.^35\021!\t9a!-\005\002\rmGCEBo\007?\034\toa9\004f\016\0358\021^Bv\007[\004\002\"a\007\0042\016m6q\030\005\t\005C\032I\0161\001\004<\"A!QMBm\001\004\031y\f\003\005\003X\016e\007\031AB^\021!\021Yn!7A\002\r}\006\002CB)\0073\004\raa/\t\021\rU3\021\034a\001\007C\001ba5\004Z\002\00711\030\005\t\007/\034I\0161\001\004@\"A\021\021^BY\t\003\nY\017\003\005\002v\016EF\021ABz)\021\031)pa>\021\0131\tYpa0\t\017%\034\t\0201\001\004<\"A!1ABY\t\003\031Y0\006\002\004~B)QE!\003\004\000B1A\002FB^\007CqAYBY\t\003\"\031!\006\003\005\006\021-AC\002C\004\t\037!\t\002\005\004\021\001\rmF\021\002\t\004/\021-Aa\002*\005\002\t\007AQB\t\004\007s\002bB5\005\002\001\00711\030\005\bW\022\005\001\031\001C\005\021\035i7\021\027C\001\t+)B\001b\006\005\036Q!A\021\004C\020!\031\001\002aa/\005\034A\031q\003\"\b\005\017I#\031B1\001\005\016!9A\017b\005A\002\021\005\002C\002\007\025\007w#Y\002\003\005\002&\016EF\021\001C\023)\021\031\031\rb\n\t\017%$\031\0031\001\004<\"A!QVBY\t\003\"Y#\006\003\005.\021UBc\001\030\0050!A!Q\027C\025\001\004!\t\004\005\004\r/\016}H1\007\t\004/\021UBA\002 \005*\t\007!\004")
/*     */ public interface Map<A, B> extends Iterable<Tuple2<A, B>>, Map<A, B>, MapLike<A, B, Map<A, B>> {
/*     */   Map<A, B> empty();
/*     */   
/*     */   <T, U> Map<T, U> toMap(scala.Predef$.less.colon.less<Tuple2<A, B>, Tuple2<T, U>> paramless);
/*     */   
/*     */   Map<A, B> seq();
/*     */   
/*     */   <B1> Map<A, B1> withDefault(Function1<A, B1> paramFunction1);
/*     */   
/*     */   <B1> Map<A, B1> withDefaultValue(B1 paramB1);
/*     */   
/*     */   <B1> Map<A, B1> updated(A paramA, B1 paramB1);
/*     */   
/*     */   <B1> Map<A, B1> $plus(Tuple2<A, B1> paramTuple2);
/*     */   
/*     */   public class Map$$anonfun$withDefaultValue$1 extends AbstractFunction1<A, B1> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Object d$2;
/*     */     
/*     */     public final B1 apply(Object x) {
/*  57 */       return (B1)this.d$2;
/*     */     }
/*     */     
/*     */     public Map$$anonfun$withDefaultValue$1(Map $outer, Object d$2) {}
/*     */   }
/*     */   
/*     */   public static class WithDefault<A, B> extends Map.WithDefault<A, B> implements Map<A, B> {
/*     */     private final Map<A, B> underlying;
/*     */     
/*     */     private final Function1<A, B> d;
/*     */     
/*     */     public <T, U> Map<T, U> toMap(scala.Predef$.less.colon.less ev) {
/*  79 */       return Map$class.toMap(this, ev);
/*     */     }
/*     */     
/*     */     public Map<A, B> seq() {
/*  79 */       return Map$class.seq(this);
/*     */     }
/*     */     
/*     */     public Combiner<Tuple2<A, B>, ParMap<A, B>> parCombiner() {
/*  79 */       return MapLike$class.parCombiner(this);
/*     */     }
/*     */     
/*     */     public <B1> Map<A, B1> $plus(Tuple2 elem1, Tuple2 elem2, Seq elems) {
/*  79 */       return MapLike$class.$plus(this, elem1, elem2, elems);
/*     */     }
/*     */     
/*     */     public <B1> Map<A, B1> $plus$plus(GenTraversableOnce xs) {
/*  79 */       return MapLike$class.$plus$plus(this, xs);
/*     */     }
/*     */     
/*     */     public Map<A, B> filterKeys(Function1 p) {
/*  79 */       return MapLike$class.filterKeys(this, p);
/*     */     }
/*     */     
/*     */     public <C> Map<A, C> mapValues(Function1 f) {
/*  79 */       return MapLike$class.mapValues(this, f);
/*     */     }
/*     */     
/*     */     public Set<A> keySet() {
/*  79 */       return MapLike$class.keySet(this);
/*     */     }
/*     */     
/*     */     public <C, That> That transform(Function2 f, CanBuildFrom bf) {
/*  79 */       return (That)MapLike$class.transform(this, f, bf);
/*     */     }
/*     */     
/*     */     public GenericCompanion<Iterable> companion() {
/*  79 */       return Iterable$class.companion(this);
/*     */     }
/*     */     
/*     */     public WithDefault(Map<A, B> underlying, Function1<A, B> d) {
/*  79 */       super(underlying, d);
/*  79 */       Traversable$class.$init$(this);
/*  79 */       Iterable$class.$init$(this);
/*  79 */       MapLike$class.$init$(this);
/*  79 */       Map$class.$init$(this);
/*     */     }
/*     */     
/*     */     public WithDefault<A, B> empty() {
/*  80 */       return new WithDefault(this.underlying.empty(), this.d);
/*     */     }
/*     */     
/*     */     public <B1> WithDefault<A, B1> updated(Object key, Object value) {
/*  81 */       return new WithDefault(this.underlying.updated((A)key, (B)value), this.d);
/*     */     }
/*     */     
/*     */     public <B1> WithDefault<A, B1> $plus(Tuple2 kv) {
/*  82 */       return updated((A)kv._1(), (B1)kv._2());
/*     */     }
/*     */     
/*     */     public WithDefault<A, B> $minus(Object key) {
/*  83 */       return new WithDefault((Map<A, B>)this.underlying.$minus(key), this.d);
/*     */     }
/*     */     
/*     */     public <B1> Map<A, B1> withDefault(Function1<A, B> d) {
/*  84 */       return new WithDefault(this.underlying, d);
/*     */     }
/*     */     
/*     */     public <B1> Map<A, B1> withDefaultValue(Object d) {
/*  85 */       return new WithDefault(this.underlying, (Function1<A, B>)new Map$WithDefault$$anonfun$withDefaultValue$2(this, (WithDefault<A, B>)d));
/*     */     }
/*     */     
/*     */     public class Map$WithDefault$$anonfun$withDefaultValue$2 extends AbstractFunction1<A, B1> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Object d$1;
/*     */       
/*     */       public final B1 apply(Object x) {
/*  85 */         return (B1)this.d$1;
/*     */       }
/*     */       
/*     */       public Map$WithDefault$$anonfun$withDefaultValue$2(Map.WithDefault $outer, Object d$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public static class EmptyMap$ extends AbstractMap<Object, scala.runtime.Nothing$> implements Map<Object, scala.runtime.Nothing$>, Serializable {
/*     */     public static final EmptyMap$ MODULE$;
/*     */     
/*     */     private Object readResolve() {
/*  88 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public EmptyMap$() {
/*  88 */       MODULE$ = this;
/*     */     }
/*     */     
/*     */     public int size() {
/*  89 */       return 0;
/*     */     }
/*     */     
/*     */     public Option<scala.runtime.Nothing$> get(Object key) {
/*  90 */       return (Option<scala.runtime.Nothing$>)scala.None$.MODULE$;
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<Object, scala.runtime.Nothing$>> iterator() {
/*  91 */       return scala.collection.Iterator$.MODULE$.empty();
/*     */     }
/*     */     
/*     */     public <B1> Map<Object, B1> updated(Object key, Object value) {
/*  92 */       return new Map.Map1<Object, B1>(key, (B1)value);
/*     */     }
/*     */     
/*     */     public <B1> Map<Object, B1> $plus(Tuple2 kv) {
/*  93 */       return updated(kv._1(), (B1)kv._2());
/*     */     }
/*     */     
/*     */     public Map<Object, scala.runtime.Nothing$> $minus(Object key) {
/*  94 */       return this;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Map1<A, B> extends AbstractMap<A, B> implements Map<A, B>, Serializable {
/*     */     private final A key1;
/*     */     
/*     */     private final B value1;
/*     */     
/*     */     public Map1(Object key1, Object value1) {}
/*     */     
/*     */     public int size() {
/*  98 */       return 1;
/*     */     }
/*     */     
/*     */     public Option<B> get(Object key) {
/* 100 */       A a = this.key1;
/* 100 */       return ((key == a) ? true : ((key == null) ? false : ((key instanceof Number) ? BoxesRunTime.equalsNumObject((Number)key, a) : ((key instanceof Character) ? BoxesRunTime.equalsCharObject((Character)key, a) : key.equals(a))))) ? (Option<B>)new Some(this.value1) : (Option<B>)scala.None$.MODULE$;
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<A, B>> iterator() {
/* 101 */       (new Tuple2[1])[0] = new Tuple2(this.key1, this.value1);
/* 101 */       return scala.collection.Iterator$.MODULE$.apply((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[1]));
/*     */     }
/*     */     
/*     */     public <B1> Map<A, B1> updated(Object key, Object value) {
/* 103 */       A a = this.key1;
/* 103 */       return ((key == a) ? true : ((key == null) ? false : ((key instanceof Number) ? BoxesRunTime.equalsNumObject((Number)key, a) : ((key instanceof Character) ? BoxesRunTime.equalsCharObject((Character)key, a) : key.equals(a))))) ? new Map1(this.key1, (B)value) : 
/* 104 */         new Map.Map2<A, B1>(this.key1, (B1)this.value1, (A)key, (B1)value);
/*     */     }
/*     */     
/*     */     public <B1> Map<A, B1> $plus(Tuple2 kv) {
/* 105 */       return updated((A)kv._1(), (B1)kv._2());
/*     */     }
/*     */     
/*     */     public Map<A, B> $minus(Object key) {
/* 107 */       A a = this.key1;
/* 107 */       return ((key == a) ? true : ((key == null) ? false : ((key instanceof Number) ? BoxesRunTime.equalsNumObject((Number)key, a) : ((key instanceof Character) ? BoxesRunTime.equalsCharObject((Character)key, a) : key.equals(a))))) ? Map$.MODULE$.<A, B>empty() : this;
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/* 109 */       f.apply(new Tuple2(this.key1, this.value1));
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Map2<A, B> extends AbstractMap<A, B> implements Map<A, B>, Serializable {
/*     */     private final A key1;
/*     */     
/*     */     private final B value1;
/*     */     
/*     */     private final A key2;
/*     */     
/*     */     private final B value2;
/*     */     
/*     */     public Map2(Object key1, Object value1, Object key2, Object value2) {}
/*     */     
/*     */     public int size() {
/* 114 */       return 2;
/*     */     }
/*     */     
/*     */     public Option<B> get(Object key) {
/* 116 */       A a1 = this.key1;
/* 117 */       A a2 = this.key2;
/* 117 */       return ((key == a1) ? true : ((key == null) ? false : ((key instanceof Number) ? BoxesRunTime.equalsNumObject((Number)key, a1) : ((key instanceof Character) ? BoxesRunTime.equalsCharObject((Character)key, a1) : key.equals(a1))))) ? (Option<B>)new Some(this.value1) : (((key == a2) ? true : ((key == null) ? false : ((key instanceof Number) ? BoxesRunTime.equalsNumObject((Number)key, a2) : ((key instanceof Character) ? BoxesRunTime.equalsCharObject((Character)key, a2) : key.equals(a2))))) ? (Option<B>)new Some(this.value2) : 
/* 118 */         (Option<B>)scala.None$.MODULE$);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<A, B>> iterator() {
/* 119 */       (new Tuple2[2])[0] = new Tuple2(this.key1, this.value1);
/* 119 */       (new Tuple2[2])[1] = new Tuple2(this.key2, this.value2);
/* 119 */       return scala.collection.Iterator$.MODULE$.apply((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[2]));
/*     */     }
/*     */     
/*     */     public <B1> Map<A, B1> updated(Object key, Object value) {
/* 121 */       A a1 = this.key1;
/* 122 */       A a2 = this.key2;
/* 122 */       return ((key == a1) ? true : ((key == null) ? false : ((key instanceof Number) ? BoxesRunTime.equalsNumObject((Number)key, a1) : ((key instanceof Character) ? BoxesRunTime.equalsCharObject((Character)key, a1) : key.equals(a1))))) ? new Map2(this.key1, (B)value, this.key2, this.value2) : (((key == a2) ? true : ((key == null) ? false : ((key instanceof Number) ? BoxesRunTime.equalsNumObject((Number)key, a2) : ((key instanceof Character) ? BoxesRunTime.equalsCharObject((Character)key, a2) : key.equals(a2))))) ? new Map2(this.key1, this.value1, this.key2, (B)value) : 
/* 123 */         new Map.Map3<A, B1>(this.key1, (B1)this.value1, this.key2, (B1)this.value2, (A)key, (B1)value));
/*     */     }
/*     */     
/*     */     public <B1> Map<A, B1> $plus(Tuple2 kv) {
/* 124 */       return updated((A)kv._1(), (B1)kv._2());
/*     */     }
/*     */     
/*     */     public Map<A, B> $minus(Object key) {
/* 126 */       A a1 = this.key1;
/* 127 */       A a2 = this.key2;
/* 127 */       return ((key == a1) ? true : ((key == null) ? false : ((key instanceof Number) ? BoxesRunTime.equalsNumObject((Number)key, a1) : ((key instanceof Character) ? BoxesRunTime.equalsCharObject((Character)key, a1) : key.equals(a1))))) ? new Map.Map1<A, B>(this.key2, this.value2) : (((key == a2) ? true : ((key == null) ? false : ((key instanceof Number) ? BoxesRunTime.equalsNumObject((Number)key, a2) : ((key instanceof Character) ? BoxesRunTime.equalsCharObject((Character)key, a2) : key.equals(a2))))) ? new Map.Map1<A, B>(this.key1, this.value1) : 
/* 128 */         this);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/* 130 */       f.apply(new Tuple2(this.key1, this.value1));
/* 130 */       f.apply(new Tuple2(this.key2, this.value2));
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Map3<A, B> extends AbstractMap<A, B> implements Map<A, B>, Serializable {
/*     */     private final A key1;
/*     */     
/*     */     private final B value1;
/*     */     
/*     */     private final A key2;
/*     */     
/*     */     private final B value2;
/*     */     
/*     */     private final A key3;
/*     */     
/*     */     private final B value3;
/*     */     
/*     */     public Map3(Object key1, Object value1, Object key2, Object value2, Object key3, Object value3) {}
/*     */     
/*     */     public int size() {
/* 135 */       return 3;
/*     */     }
/*     */     
/*     */     public Option<B> get(Object key) {
/* 137 */       A a1 = this.key1;
/* 138 */       A a2 = this.key2;
/* 139 */       A a3 = this.key3;
/* 139 */       return ((key == a1) ? true : ((key == null) ? false : ((key instanceof Number) ? BoxesRunTime.equalsNumObject((Number)key, a1) : ((key instanceof Character) ? BoxesRunTime.equalsCharObject((Character)key, a1) : key.equals(a1))))) ? (Option<B>)new Some(this.value1) : (((key == a2) ? true : ((key == null) ? false : ((key instanceof Number) ? BoxesRunTime.equalsNumObject((Number)key, a2) : ((key instanceof Character) ? BoxesRunTime.equalsCharObject((Character)key, a2) : key.equals(a2))))) ? (Option<B>)new Some(this.value2) : (((key == a3) ? true : ((key == null) ? false : ((key instanceof Number) ? BoxesRunTime.equalsNumObject((Number)key, a3) : ((key instanceof Character) ? BoxesRunTime.equalsCharObject((Character)key, a3) : key.equals(a3))))) ? (Option<B>)new Some(this.value3) : 
/* 140 */         (Option<B>)scala.None$.MODULE$));
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<A, B>> iterator() {
/* 141 */       (new Tuple2[3])[0] = new Tuple2(this.key1, this.value1);
/* 141 */       (new Tuple2[3])[1] = new Tuple2(this.key2, this.value2);
/* 141 */       (new Tuple2[3])[2] = new Tuple2(this.key3, this.value3);
/* 141 */       return scala.collection.Iterator$.MODULE$.apply((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[3]));
/*     */     }
/*     */     
/*     */     public <B1> Map<A, B1> updated(Object key, Object value) {
/* 143 */       A a1 = this.key1;
/* 144 */       A a2 = this.key2;
/* 145 */       A a3 = this.key3;
/* 145 */       return ((key == a1) ? true : ((key == null) ? false : ((key instanceof Number) ? BoxesRunTime.equalsNumObject((Number)key, a1) : ((key instanceof Character) ? BoxesRunTime.equalsCharObject((Character)key, a1) : key.equals(a1))))) ? new Map3(this.key1, (B)value, this.key2, this.value2, this.key3, this.value3) : (((key == a2) ? true : ((key == null) ? false : ((key instanceof Number) ? BoxesRunTime.equalsNumObject((Number)key, a2) : ((key instanceof Character) ? BoxesRunTime.equalsCharObject((Character)key, a2) : key.equals(a2))))) ? new Map3(this.key1, this.value1, this.key2, (B)value, this.key3, this.value3) : (((key == a3) ? true : ((key == null) ? false : ((key instanceof Number) ? BoxesRunTime.equalsNumObject((Number)key, a3) : ((key instanceof Character) ? BoxesRunTime.equalsCharObject((Character)key, a3) : key.equals(a3))))) ? new Map3(this.key1, this.value1, this.key2, this.value2, this.key3, (B)value) : 
/* 146 */         new Map.Map4<A, B1>(this.key1, (B1)this.value1, this.key2, (B1)this.value2, this.key3, (B1)this.value3, (A)key, (B1)value)));
/*     */     }
/*     */     
/*     */     public <B1> Map<A, B1> $plus(Tuple2 kv) {
/* 147 */       return updated((A)kv._1(), (B1)kv._2());
/*     */     }
/*     */     
/*     */     public Map<A, B> $minus(Object key) {
/* 149 */       A a1 = this.key1;
/* 150 */       A a2 = this.key2;
/* 151 */       A a3 = this.key3;
/* 151 */       return ((key == a1) ? true : ((key == null) ? false : ((key instanceof Number) ? BoxesRunTime.equalsNumObject((Number)key, a1) : ((key instanceof Character) ? BoxesRunTime.equalsCharObject((Character)key, a1) : key.equals(a1))))) ? new Map.Map2<A, B>(this.key2, this.value2, this.key3, this.value3) : (((key == a2) ? true : ((key == null) ? false : ((key instanceof Number) ? BoxesRunTime.equalsNumObject((Number)key, a2) : ((key instanceof Character) ? BoxesRunTime.equalsCharObject((Character)key, a2) : key.equals(a2))))) ? new Map.Map2<A, B>(this.key1, this.value1, this.key3, this.value3) : (((key == a3) ? true : ((key == null) ? false : ((key instanceof Number) ? BoxesRunTime.equalsNumObject((Number)key, a3) : ((key instanceof Character) ? BoxesRunTime.equalsCharObject((Character)key, a3) : key.equals(a3))))) ? new Map.Map2<A, B>(this.key1, this.value1, this.key2, this.value2) : 
/* 152 */         this));
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/* 154 */       f.apply(new Tuple2(this.key1, this.value1));
/* 154 */       f.apply(new Tuple2(this.key2, this.value2));
/* 154 */       f.apply(new Tuple2(this.key3, this.value3));
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Map4<A, B> extends AbstractMap<A, B> implements Map<A, B>, Serializable {
/*     */     private final A key1;
/*     */     
/*     */     private final B value1;
/*     */     
/*     */     private final A key2;
/*     */     
/*     */     private final B value2;
/*     */     
/*     */     private final A key3;
/*     */     
/*     */     private final B value3;
/*     */     
/*     */     private final A key4;
/*     */     
/*     */     private final B value4;
/*     */     
/*     */     public Map4(Object key1, Object value1, Object key2, Object value2, Object key3, Object value3, Object key4, Object value4) {}
/*     */     
/*     */     public int size() {
/* 159 */       return 4;
/*     */     }
/*     */     
/*     */     public Option<B> get(Object key) {
/* 161 */       A a1 = this.key1;
/* 162 */       A a2 = this.key2;
/* 163 */       A a3 = this.key3;
/* 164 */       A a4 = this.key4;
/* 164 */       return ((key == a1) ? true : ((key == null) ? false : ((key instanceof Number) ? BoxesRunTime.equalsNumObject((Number)key, a1) : ((key instanceof Character) ? BoxesRunTime.equalsCharObject((Character)key, a1) : key.equals(a1))))) ? (Option<B>)new Some(this.value1) : (((key == a2) ? true : ((key == null) ? false : ((key instanceof Number) ? BoxesRunTime.equalsNumObject((Number)key, a2) : ((key instanceof Character) ? BoxesRunTime.equalsCharObject((Character)key, a2) : key.equals(a2))))) ? (Option<B>)new Some(this.value2) : (((key == a3) ? true : ((key == null) ? false : ((key instanceof Number) ? BoxesRunTime.equalsNumObject((Number)key, a3) : ((key instanceof Character) ? BoxesRunTime.equalsCharObject((Character)key, a3) : key.equals(a3))))) ? (Option<B>)new Some(this.value3) : (((key == a4) ? true : ((key == null) ? false : ((key instanceof Number) ? BoxesRunTime.equalsNumObject((Number)key, a4) : ((key instanceof Character) ? BoxesRunTime.equalsCharObject((Character)key, a4) : key.equals(a4))))) ? (Option<B>)new Some(this.value4) : 
/* 165 */         (Option<B>)scala.None$.MODULE$)));
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<A, B>> iterator() {
/* 166 */       (new Tuple2[4])[0] = new Tuple2(this.key1, this.value1);
/* 166 */       (new Tuple2[4])[1] = new Tuple2(this.key2, this.value2);
/* 166 */       (new Tuple2[4])[2] = new Tuple2(this.key3, this.value3);
/* 166 */       (new Tuple2[4])[3] = new Tuple2(this.key4, this.value4);
/* 166 */       return scala.collection.Iterator$.MODULE$.apply((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[4]));
/*     */     }
/*     */     
/*     */     public <B1> Map<A, B1> updated(Object key, Object value) {
/* 168 */       A a1 = this.key1;
/* 169 */       A a2 = this.key2;
/* 170 */       A a3 = this.key3;
/* 171 */       A a4 = this.key4;
/* 172 */       (new Tuple2[3])[0] = new Tuple2(this.key3, this.value3);
/* 172 */       (new Tuple2[3])[1] = new Tuple2(this.key4, this.value4);
/* 172 */       (new Tuple2[3])[2] = new Tuple2(key, value);
/* 172 */       return ((key == a1) ? true : ((key == null) ? false : ((key instanceof Number) ? BoxesRunTime.equalsNumObject((Number)key, a1) : ((key instanceof Character) ? BoxesRunTime.equalsCharObject((Character)key, a1) : key.equals(a1))))) ? new Map4(this.key1, (B)value, this.key2, this.value2, this.key3, this.value3, this.key4, this.value4) : (((key == a2) ? true : ((key == null) ? false : ((key instanceof Number) ? BoxesRunTime.equalsNumObject((Number)key, a2) : ((key instanceof Character) ? BoxesRunTime.equalsCharObject((Character)key, a2) : key.equals(a2))))) ? new Map4(this.key1, this.value1, this.key2, (B)value, this.key3, this.value3, this.key4, this.value4) : (((key == a3) ? true : ((key == null) ? false : ((key instanceof Number) ? BoxesRunTime.equalsNumObject((Number)key, a3) : ((key instanceof Character) ? BoxesRunTime.equalsCharObject((Character)key, a3) : key.equals(a3))))) ? new Map4(this.key1, this.value1, this.key2, this.value2, this.key3, (B)value, this.key4, this.value4) : (((key == a4) ? true : ((key == null) ? false : ((key instanceof Number) ? BoxesRunTime.equalsNumObject((Number)key, a4) : ((key instanceof Character) ? BoxesRunTime.equalsCharObject((Character)key, a4) : key.equals(a4))))) ? new Map4(this.key1, this.value1, this.key2, this.value2, this.key3, this.value3, this.key4, (B)value) : (new HashMap<A, Object>()).<B1>$plus(new Tuple2(this.key1, this.value1), new Tuple2(this.key2, this.value2), (Seq<Tuple2<A, B1>>)scala.Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[3])))));
/*     */     }
/*     */     
/*     */     public <B1> Map<A, B1> $plus(Tuple2 kv) {
/* 173 */       return updated((A)kv._1(), (B1)kv._2());
/*     */     }
/*     */     
/*     */     public Map<A, B> $minus(Object key) {
/* 175 */       A a1 = this.key1;
/* 176 */       A a2 = this.key2;
/* 177 */       A a3 = this.key3;
/* 178 */       A a4 = this.key4;
/* 178 */       return ((key == a1) ? true : ((key == null) ? false : ((key instanceof Number) ? BoxesRunTime.equalsNumObject((Number)key, a1) : ((key instanceof Character) ? BoxesRunTime.equalsCharObject((Character)key, a1) : key.equals(a1))))) ? new Map.Map3<A, B>(this.key2, this.value2, this.key3, this.value3, this.key4, this.value4) : (((key == a2) ? true : ((key == null) ? false : ((key instanceof Number) ? BoxesRunTime.equalsNumObject((Number)key, a2) : ((key instanceof Character) ? BoxesRunTime.equalsCharObject((Character)key, a2) : key.equals(a2))))) ? new Map.Map3<A, B>(this.key1, this.value1, this.key3, this.value3, this.key4, this.value4) : (((key == a3) ? true : ((key == null) ? false : ((key instanceof Number) ? BoxesRunTime.equalsNumObject((Number)key, a3) : ((key instanceof Character) ? BoxesRunTime.equalsCharObject((Character)key, a3) : key.equals(a3))))) ? new Map.Map3<A, B>(this.key1, this.value1, this.key2, this.value2, this.key4, this.value4) : (((key == a4) ? true : ((key == null) ? false : ((key instanceof Number) ? BoxesRunTime.equalsNumObject((Number)key, a4) : ((key instanceof Character) ? BoxesRunTime.equalsCharObject((Character)key, a4) : key.equals(a4))))) ? new Map.Map3<A, B>(this.key1, this.value1, this.key2, this.value2, this.key3, this.value3) : 
/* 179 */         this)));
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/* 181 */       f.apply(new Tuple2(this.key1, this.value1));
/* 181 */       f.apply(new Tuple2(this.key2, this.value2));
/* 181 */       f.apply(new Tuple2(this.key3, this.value3));
/* 181 */       f.apply(new Tuple2(this.key4, this.value4));
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\Map.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */