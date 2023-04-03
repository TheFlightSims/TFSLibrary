/*     */ package scala.collection;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.collection.immutable.Range;
/*     */ import scala.collection.immutable.Range$;
/*     */ import scala.collection.mutable.ArrayOps;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.IntRef;
/*     */ import scala.runtime.Nothing$;
/*     */ import scala.runtime.ObjectRef;
/*     */ import scala.runtime.RichInt$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t=gaB\001\003!\003\r\ta\002\002\017\017\026t7+Z9WS\026<H*[6f\025\t\031A!\001\006d_2dWm\031;j_:T\021!B\001\006g\016\fG.Y\002\001+\021A1C\013\021\024\r\001IQ\002H\0271!\tQ1\"D\001\005\023\taAA\001\004B]f\024VM\032\t\004\035=\tR\"\001\002\n\005A\021!AB$f]N+\027\017\005\002\023'1\001AA\002\013\001\t\013\007QCA\001B#\t1\022\004\005\002\013/%\021\001\004\002\002\b\035>$\b.\0338h!\tQ!$\003\002\034\t\t\031\021I\\=\021\t9i\022cH\005\003=\t\021!bR3o'\026\fH*[6f!\t\021\002\005\002\004\"\001\021\025\rA\t\002\005)\"L7/\005\002\027GI\031AE\n\027\007\t\025\002\001a\t\002\ryI,g-\0338f[\026tGO\020\t\005\035\035\n\022&\003\002)\005\tQq)\0328TKF4\026.Z<\021\005IQCAB\026\001\t\013\007QC\001\003D_2d\007#\002\b\001#%z\002\003\002\b/#%J!a\f\002\003\037\035+g.\023;fe\006\024G.\032,jK^\004RAD\031\022S}I!A\r\002\003'\035+g.\023;fe\006\024G.\032,jK^d\025n[3\t\013Q\002A\021A\033\002\r\021Jg.\033;%)\0051\004C\001\0068\023\tADA\001\003V]&$ha\002\036\001!\003\r\ta\017\002\f)J\fgn\0354pe6,G-\006\002=M!\021(C\037B!\021qqEP\025\021\005IyDA\002!:\t\013\007QCA\001C!\r\0215IP\007\002\001%\021!(\r\005\006ie\"\t!\016\005\006\rf2\taR\001\007Y\026tw\r\0365\026\003!\003\"AC%\n\005)#!aA%oi\")A*\017D\001\033\006)\021\r\0359msR\021aH\024\005\006\037.\003\r\001S\001\004S\022D\b\"B):\t\003\022\026\001\003;p'R\024\030N\\4\025\003M\003\"\001V-\016\003US!AV,\002\t1\fgn\032\006\0021\006!!.\031<b\023\tQVK\001\004TiJLgn\032\004\b9\002\001\n1!\001^\005%)U\016\035;z-&,wo\005\003\\\023y{\006c\001\":-A\021!\tY\005\0039FBQ\001N.\005\002UBQAR.\005F\035CQ\001T.\005F\021$\"AF3\t\013\031\034\007\031\001%\002\00394q\001\033\001\021\002\007\005\021N\001\004G_J\034W\rZ\013\003U:\034BaZ\005l_B\031!\t\\7\n\005!\f\004C\001\no\t\025\001uM1\001\026!\r\021\025(\034\005\006i\035$\t!\016\005\006\r\036$\ta\022\005\006\031\036$\ta\035\013\003[RDQa\024:A\002!3qA\036\001\021\002\007\005qO\001\004TY&\034W\rZ\n\005k&A(\020\005\002Cs&\021a/\r\t\004\005f\n\002\"\002\033v\t\003)\004\"\002$v\t\0039\005\"\002'v\t\003qHCA\t\000\021\025yU\0201\001I\021\035\t\031!\036C!\003\013\tqAZ8sK\006\034\007.\006\003\002\b\005UAc\001\034\002\n!A\0211BA\001\001\004\ti!A\001g!\031Q\021qB\t\002\024%\031\021\021\003\003\003\023\031+hn\031;j_:\f\004c\001\n\002\026\0219\021qCA\001\005\004)\"!A+\t\017\005mQ\017\"\021\002\036\005A\021\016^3sCR|'/\006\002\002 A!a\"!\t\022\023\r\t\031C\001\002\t\023R,'/\031;pe\032I\021q\005\001\021\002\007\005\021\021\006\002\007\033\006\004\b/\0323\026\t\005-\0221G\n\b\003KI\021QFA\033!\025\021\025qFA\031\023\r\t9#\r\t\004%\005MBA\002!\002&\t\007Q\003\005\003Cs\005E\002B\002\033\002&\021\005Q\007\003\004G\003K!\ta\022\005\b\031\006\025B\021AA\037)\021\t\t$a\020\t\r=\013Y\0041\001I\r%\t\031\005\001I\001\004\003\t)E\001\006GY\006$X*\0319qK\022,B!a\022\002PM9\021\021I\005\002J\005E\003#\002\"\002L\0055\023bAA\"cA\031!#a\024\005\r\001\013\tE1\001\026!\021\021\025(!\024\t\rQ\n\t\005\"\0016\021-\t9&!\021\t\006\004&\t\"!\027\002\013%tG-\032=\026\005\005m\003\003\002\006\002^!K1!a\030\005\005\025\t%O]1z\021-\t\031'!\021\t\002\003\006K!a\027\002\r%tG-\032=!\021%\t9'!\021!\n#\tI'A\004gS:$'k\\<\025\017!\013Y'!\034\002r!1q*!\032A\002!Cq!a\034\002f\001\007\001*\001\002m_\"9\0211OA3\001\004A\025A\0015j\021\0311\025\021\tC\001\017\"9A*!\021\005\002\005eD\003BA'\003wBaaTA<\001\004Ae!CA@\001A\005\031\021AAA\005!\t\005\017]3oI\026$W\003BAB\003\027\033r!! \n\003\013\013y\tE\003C\003\017\013I)C\002\002\000E\0022AEAF\t\035\001\025Q\020b\001\003\033\013\"!E\r\021\t\tK\024\021\022\005\007i\005uD\021A\033\t\027\005U\025Q\020ECB\023E\021qS\001\be\026\034HoU3r+\t\tI\n\005\003\017\037\005%\005bCAO\003{B\t\021)Q\005\0033\013\001B]3tiN+\027\017\t\005\007\r\006uD\021A$\t\0171\013i\b\"\001\002$R!\021\021RAS\021\031y\025\021\025a\001\021\032I\021\021\026\001\021\002\007\005\0211\026\002\t\r&dG/\032:fIN1\021qU\005\002.j\0042AQAX\023\r\tI+\r\005\007i\005\035F\021A\033\t\027\005]\023q\025ECB\023E\021\021\f\005\f\003G\n9\013#A!B\023\tY\006\003\004G\003O#\ta\022\005\b\031\006\035F\021AA^)\r\t\022Q\030\005\007\037\006e\006\031\001%\007\023\005\005\007\001%A\002\002\005\r'A\003+bW\026tw\013[5mKN1\021qX\005\002Fj\0042AQAd\023\r\t\t-\r\005\007i\005}F\021A\033\t\025\0055\027q\030ECB\023Eq)A\002mK:D!\"!5\002@\"\005\t\025)\003I\003\021aWM\034\021\t\r\031\013y\f\"\001H\021\035a\025q\030C\001\003/$2!EAm\021\031y\025Q\033a\001\021\032I\021Q\034\001\021\002\007\005\021q\034\002\r\tJ|\007\017]3e/\"LG.Z\n\007\0037L\021\021\035>\021\007\t\013\031/C\002\002^FBa\001NAn\t\003)\004BCAu\0037D)\031)C\t\017\006)1\017^1si\"Q\021Q^An\021\003\005\013\025\002%\002\rM$\030M\035;!\021\0311\0251\034C\001\017\"9A*a7\005\002\005MHcA\t\002v\"1q*!=A\002!3\021\"!?\001!\003\r\t!a?\003\riK\007\017]3e+\021\tiP!\002\024\017\005]\030\"a@\003\bA)!I!\001\003\004%\031\021\021`\031\021\007I\021)\001\002\004A\003o\024\r!\006\t\005\005f\022I\001\005\004\013\005\027\t\"1A\005\004\005\033!!A\002+va2,'\007\003\0045\003o$\t!\016\005\f\005'\t9\020#b!\n#\021)\"A\004uQ\006$8+Z9\026\005\t]\001#\002\b\003\032\t\r\021b\001B\016\005\t\0311+Z9\t\027\t}\021q\037E\001B\003&!qC\001\ti\"\fGoU3rA!1a)a>\005\002\035Cq\001TA|\t\003\021)\003\006\003\003\n\t\035\002BB(\003$\001\007\001JB\005\003,\001\001\n1!\001\003.\tI!,\0339qK\022\fE\016\\\013\007\005_\0219D!\020\024\017\t%\022B!\r\003@A9!Ia\r\0036\tm\022b\001B\026cA\031!Ca\016\005\021\te\"\021\006b\001\003\033\023!!Q\031\021\007I\021i\004\002\004A\005S\021\r!\006\t\005\005f\022\t\005E\004\013\005\027\021)Da\017\t\rQ\022I\003\"\0016\021-\021\031B!\013\t\006\004&\tBa\022\026\005\t%\003#\002\b\003\032\tm\002b\003B\020\005SA\t\021)Q\005\005\023BaA\022B\025\t\0039\005b\002'\003*\021\005!\021\013\013\005\005\003\022\031\006\003\004P\005\037\002\r\001\023\004\n\005/\002\001\023aA\001\0053\022\001BU3wKJ\034X\rZ\n\005\005+J!\020\003\0045\005+\"\t!\016\005\t\0037\021)\006\"\021\002\036!1aI!\026\005\002\035Cq\001\024B+\t\003\021\031\007F\002\022\005KBaa\024B1\001\004A\005\"\003B5\005+\002KQ\013B6\00391\030.Z<JI\026tG/\0334jKJ,\022a\025\005\t\005_\022)\006\"\003\002\036\00512M]3bi\026\024VM^3sg\026$\027\n^3sCR|'OB\005\003t\001\001\n1!\001\003v\t9\001+\031;dQ\026$W\003\002B<\005{\032RA!\035\n\005s\002BAQ\035\003|A\031!C! \005\017\001\023\tH1\001\002\016\"1AG!\035\005\002UB\021Ba!\003r\t\007k\021C$\002\t\031\024x.\034\005\013\005\017\023\tH1Q\007\022\t%\025!\0029bi\016DWC\001BF!\021qqBa\037\t\023\t=%\021\017b!\016#9\025\001\003:fa2\f7-\0323\t\025\tM%\021\017EC\002\023%q)\001\003qY\026t\007B\003BL\005cB\t\021)Q\005\021\006)\001\017\\3oA!A\0211\004B9\t\003\022Y*\006\002\003\036B)a\"!\t\003|!1aI!\035\005\002\035Cq\001\024B9\t\003\021\031\013\006\003\003|\t\025\006BB(\003\"\002\007\001\nC\005\003j\tE\004\025\"\026\003l\031I!1\026\001\021\002\007\005!Q\026\002\n!J,\007/\0328eK\022,BAa,\0036N)!\021V\005\0032B!!)\017BZ!\r\021\"Q\027\003\b\001\n%&\031AAG\021\031!$\021\026C\001k!Q!1\030BU\005\0046\tB!0\002\007\031\034H/\006\002\0034\"A\0211\004BU\t\003\022\t-\006\002\003DB)a\"!\t\0034\"1aI!+\005\002\035Cq\001\024BU\t\003\021I\r\006\003\0034\n-\007BB(\003H\002\007\001\nC\005\003j\t%\006\025\"\026\003l\001")
/*     */ public interface GenSeqViewLike<A, Coll, This extends GenSeqView<A, Coll> & GenSeqViewLike<A, Coll, This>> extends GenSeq<A>, GenSeqLike<A, This>, GenIterableView<A, Coll>, GenIterableViewLike<A, Coll, This> {
/*     */   public abstract class Transformed$class {
/*     */     public static void $init$(GenSeqViewLike.Transformed $this) {}
/*     */     
/*     */     public static String toString(GenSeqViewLike.Transformed $this) {
/*  23 */       return $this.viewToString();
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class EmptyView$class {
/*     */     public static void $init$(GenSeqViewLike.EmptyView $this) {}
/*     */     
/*     */     public static final int length(GenSeqViewLike.EmptyView $this) {
/*  27 */       return 0;
/*     */     }
/*     */     
/*     */     public static final Nothing$ apply(GenSeqViewLike.EmptyView $this, int n) {
/*  28 */       return (Nothing$)Nil$.MODULE$.apply(n);
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class Forced$class {
/*     */     public static void $init$(GenSeqViewLike.Forced $this) {}
/*     */     
/*     */     public static int length(GenSeqViewLike.Forced $this) {
/*  32 */       return $this.forced().length();
/*     */     }
/*     */     
/*     */     public static Object apply(GenSeqViewLike.Forced $this, int idx) {
/*  33 */       return $this.forced().apply(idx);
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class Sliced$class {
/*     */     public static void $init$(GenSeqViewLike.Sliced $this) {}
/*     */     
/*     */     public static int length(GenSeqViewLike.Sliced $this) {
/*  37 */       return $this.iterator().size();
/*     */     }
/*     */     
/*     */     public static Object apply(GenSeqViewLike.Sliced $this, int idx) {
/*  39 */       if (idx + $this.from() < $this.until())
/*  39 */         return $this.scala$collection$GenSeqViewLike$Sliced$$$outer().apply(idx + $this.from()); 
/*  40 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(idx).toString());
/*     */     }
/*     */     
/*     */     public static void foreach(GenSeqViewLike.Sliced $this, Function1<A, ?> f) {
/*  42 */       $this.iterator().foreach(f);
/*     */     }
/*     */     
/*     */     public static Iterator iterator(GenSeqViewLike.Sliced $this) {
/*  43 */       return $this.scala$collection$GenSeqViewLike$Sliced$$$outer().iterator().drop($this.from()).take($this.endpoints().width());
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class Mapped$class {
/*     */     public static void $init$(GenSeqViewLike.Mapped $this) {}
/*     */     
/*     */     public static int length(GenSeqViewLike.Mapped $this) {
/*  47 */       return $this.scala$collection$GenSeqViewLike$Mapped$$$outer().length();
/*     */     }
/*     */     
/*     */     public static Object apply(GenSeqViewLike.Mapped $this, int idx) {
/*  48 */       return $this.mapping().apply($this.scala$collection$GenSeqViewLike$Mapped$$$outer().apply(idx));
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class FlatMapped$class {
/*     */     public static void $init$(GenSeqViewLike.FlatMapped $this) {}
/*     */     
/*     */     public static int[] index(GenSeqViewLike.FlatMapped $this) {
/*  53 */       int[] index = new int[$this.scala$collection$GenSeqViewLike$FlatMapped$$$outer().length() + 1];
/*  54 */       index[0] = 0;
/*  55 */       Predef$ predef$ = Predef$.MODULE$;
/*  55 */       int i = $this.scala$collection$GenSeqViewLike$FlatMapped$$$outer().length();
/*  55 */       Range$ range$ = Range$.MODULE$;
/*  55 */       GenSeqViewLike$FlatMapped$$anonfun$index$1 genSeqViewLike$FlatMapped$$anonfun$index$1 = new GenSeqViewLike$FlatMapped$$anonfun$index$1($this, (GenSeqViewLike<A, Coll, This>.FlatMapped<B>)index);
/*     */       Range range;
/*  55 */       if ((range = new Range(0, i, 1)).validateRangeBoundaries((Function1)genSeqViewLike$FlatMapped$$anonfun$index$1)) {
/*     */         int terminal1;
/*     */         int step1;
/*     */         int i1;
/*  55 */         for (i1 = range.start(), terminal1 = range.terminalElement(), step1 = range.step(); i1 != terminal1; ) {
/*  55 */           index[i1 + 1] = index[i1] + ((GenTraversableOnce)$this.mapping().apply($this.scala$collection$GenSeqViewLike$FlatMapped$$$outer().apply(i1))).seq().size();
/*  55 */           i1 += step1;
/*     */         } 
/*     */       } 
/*  57 */       return index;
/*     */     }
/*     */     
/*     */     public static int findRow(GenSeqViewLike.FlatMapped $this, int idx, int lo, int hi) {
/*  60 */       int mid = (lo + hi) / 2;
/*  61 */       return (idx < $this.index()[mid]) ? $this.findRow(idx, lo, mid - 1) : (
/*  62 */         (idx >= $this.index()[mid + 1]) ? $this.findRow(idx, mid + 1, hi) : 
/*  63 */         mid);
/*     */     }
/*     */     
/*     */     public static int length(GenSeqViewLike.FlatMapped $this) {
/*  65 */       return $this.index()[$this.scala$collection$GenSeqViewLike$FlatMapped$$$outer().length()];
/*     */     }
/*     */     
/*     */     public static Object apply(GenSeqViewLike.FlatMapped $this, int idx) {
/*  67 */       int row = $this.findRow(idx, 0, $this.scala$collection$GenSeqViewLike$FlatMapped$$$outer().length() - 1);
/*  68 */       return ((GenTraversableOnce)$this.mapping().apply($this.scala$collection$GenSeqViewLike$FlatMapped$$$outer().apply(row))).seq().toSeq().apply(idx - $this.index()[row]);
/*     */     }
/*     */   }
/*     */   
/*     */   public interface FlatMapped<B> extends GenIterableViewLike<A, Coll, This>.FlatMapped<B>, Transformed<B> {
/*     */     int[] index();
/*     */     
/*     */     int findRow(int param1Int1, int param1Int2, int param1Int3);
/*     */     
/*     */     int length();
/*     */     
/*     */     B apply(int param1Int);
/*     */     
/*     */     public class GenSeqViewLike$FlatMapped$$anonfun$index$1 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final int[] index$1;
/*     */       
/*     */       public final void apply(int i) {
/*     */         apply$mcVI$sp(i);
/*     */       }
/*     */       
/*     */       public GenSeqViewLike$FlatMapped$$anonfun$index$1(GenSeqViewLike.FlatMapped $outer, int[] index$1) {}
/*     */       
/*     */       public void apply$mcVI$sp(int i) {
/*     */         this.index$1[i + 1] = this.index$1[i] + ((GenTraversableOnce)this.$outer.mapping().apply(this.$outer.scala$collection$GenSeqViewLike$FlatMapped$$$outer().apply(i))).seq().size();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class Appended$class {
/*     */     public static void $init$(GenSeqViewLike.Appended $this) {}
/*     */     
/*     */     public static GenSeq restSeq(GenSeqViewLike.Appended $this) {
/*  73 */       return $this.rest().toSeq();
/*     */     }
/*     */     
/*     */     public static int length(GenSeqViewLike.Appended $this) {
/*  74 */       return $this.scala$collection$GenSeqViewLike$Appended$$$outer().length() + $this.restSeq().length();
/*     */     }
/*     */     
/*     */     public static Object apply(GenSeqViewLike.Appended $this, int idx) {
/*  76 */       return (idx < $this.scala$collection$GenSeqViewLike$Appended$$$outer().length()) ? $this.scala$collection$GenSeqViewLike$Appended$$$outer().apply(idx) : $this.restSeq().apply(idx - $this.scala$collection$GenSeqViewLike$Appended$$$outer().length());
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class Filtered$class {
/*     */     public static void $init$(GenSeqViewLike.Filtered $this) {}
/*     */     
/*     */     public static int[] index(GenSeqViewLike.Filtered $this) {
/*  81 */       IntRef len = new IntRef(0);
/*  82 */       int[] arr = new int[$this.scala$collection$GenSeqViewLike$Filtered$$$outer().length()];
/*  83 */       Predef$ predef$1 = Predef$.MODULE$;
/*  83 */       int i = $this.scala$collection$GenSeqViewLike$Filtered$$$outer().length();
/*  83 */       Range$ range$ = Range$.MODULE$;
/*  83 */       GenSeqViewLike$Filtered$$anonfun$index$2 genSeqViewLike$Filtered$$anonfun$index$2 = new GenSeqViewLike$Filtered$$anonfun$index$2($this, len, (GenSeqViewLike<A, Coll, This>.Filtered)arr);
/*     */       Range range;
/*  83 */       if ((range = new Range(0, i, 1)).validateRangeBoundaries((Function1)genSeqViewLike$Filtered$$anonfun$index$2)) {
/*     */         int terminal1;
/*     */         int step1;
/*     */         int i1;
/*  83 */         for (i1 = range.start(), terminal1 = range.terminalElement(), step1 = range.step(); i1 != terminal1; ) {
/*  83 */           if (BoxesRunTime.unboxToBoolean($this.pred().apply($this.scala$collection$GenSeqViewLike$Filtered$$$outer().apply(i1)))) {
/*  83 */             arr[len.elem] = i1;
/*  83 */             len.elem++;
/*     */           } 
/*  83 */           i1 += step1;
/*     */         } 
/*     */       } 
/*  88 */       Predef$ predef$2 = Predef$.MODULE$;
/*  88 */       return (int[])IndexedSeqOptimized$class.take((IndexedSeqOptimized)new ArrayOps.ofInt(arr), len.elem);
/*     */     }
/*     */     
/*     */     public static int length(GenSeqViewLike.Filtered $this) {
/*  90 */       return ($this.index()).length;
/*     */     }
/*     */     
/*     */     public static Object apply(GenSeqViewLike.Filtered $this, int idx) {
/*  91 */       return $this.scala$collection$GenSeqViewLike$Filtered$$$outer().apply($this.index()[idx]);
/*     */     }
/*     */   }
/*     */   
/*     */   public interface Filtered extends GenIterableViewLike<A, Coll, This>.Filtered, Transformed<A> {
/*     */     int[] index();
/*     */     
/*     */     int length();
/*     */     
/*     */     A apply(int param1Int);
/*     */     
/*     */     public class GenSeqViewLike$Filtered$$anonfun$index$2 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final IntRef len$1;
/*     */       
/*     */       public final int[] arr$1;
/*     */       
/*     */       public final void apply(int i) {
/*     */         apply$mcVI$sp(i);
/*     */       }
/*     */       
/*     */       public GenSeqViewLike$Filtered$$anonfun$index$2(GenSeqViewLike.Filtered $outer, IntRef len$1, int[] arr$1) {}
/*     */       
/*     */       public void apply$mcVI$sp(int i) {
/*     */         if (BoxesRunTime.unboxToBoolean(this.$outer.pred().apply(this.$outer.scala$collection$GenSeqViewLike$Filtered$$$outer().apply(i)))) {
/*     */           this.arr$1[this.len$1.elem] = i;
/*     */           this.len$1.elem++;
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class TakenWhile$class {
/*     */     public static void $init$(GenSeqViewLike.TakenWhile $this) {}
/*     */     
/*     */     public static int len(GenSeqViewLike.TakenWhile $this) {
/*  95 */       return $this.scala$collection$GenSeqViewLike$TakenWhile$$$outer().prefixLength($this.pred());
/*     */     }
/*     */     
/*     */     public static int length(GenSeqViewLike.TakenWhile $this) {
/*  96 */       return $this.len();
/*     */     }
/*     */     
/*     */     public static Object apply(GenSeqViewLike.TakenWhile $this, int idx) {
/*  98 */       if (idx < $this.len())
/*  98 */         return $this.scala$collection$GenSeqViewLike$TakenWhile$$$outer().apply(idx); 
/*  99 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(idx).toString());
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class DroppedWhile$class {
/*     */     public static void $init$(GenSeqViewLike.DroppedWhile $this) {}
/*     */     
/*     */     public static int start(GenSeqViewLike.DroppedWhile $this) {
/* 103 */       return $this.scala$collection$GenSeqViewLike$DroppedWhile$$$outer().prefixLength($this.pred());
/*     */     }
/*     */     
/*     */     public static int length(GenSeqViewLike.DroppedWhile $this) {
/* 104 */       return $this.scala$collection$GenSeqViewLike$DroppedWhile$$$outer().length() - $this.start();
/*     */     }
/*     */     
/*     */     public static Object apply(GenSeqViewLike.DroppedWhile $this, int idx) {
/* 106 */       if (idx >= 0)
/* 106 */         return $this.scala$collection$GenSeqViewLike$DroppedWhile$$$outer().apply(idx + $this.start()); 
/* 107 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(idx).toString());
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class Zipped$class {
/*     */     public static void $init$(GenSeqViewLike.Zipped $this) {}
/*     */     
/*     */     public static Seq thatSeq(GenSeqViewLike.Zipped $this) {
/* 111 */       return $this.other().seq().toSeq();
/*     */     }
/*     */     
/*     */     public static int length(GenSeqViewLike.Zipped $this) {
/* 113 */       return ($this.thatSeq().lengthCompare($this.scala$collection$GenSeqViewLike$Zipped$$$outer().length()) <= 0) ? $this.thatSeq().length() : $this.scala$collection$GenSeqViewLike$Zipped$$$outer().length();
/*     */     }
/*     */     
/*     */     public static Tuple2 apply(GenSeqViewLike.Zipped $this, int idx) {
/* 114 */       return new Tuple2($this.scala$collection$GenSeqViewLike$Zipped$$$outer().apply(idx), $this.thatSeq().apply(idx));
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class ZippedAll$class {
/*     */     public static void $init$(GenSeqViewLike.ZippedAll $this) {}
/*     */     
/*     */     public static Seq thatSeq(GenSeqViewLike.ZippedAll $this) {
/* 118 */       return $this.other().seq().toSeq();
/*     */     }
/*     */     
/*     */     public static int length(GenSeqViewLike.ZippedAll $this) {
/* 119 */       int i = $this.scala$collection$GenSeqViewLike$ZippedAll$$$outer().length();
/* 119 */       Predef$ predef$ = Predef$.MODULE$;
/* 119 */       return RichInt$.MODULE$.max$extension(i, $this.thatSeq().length());
/*     */     }
/*     */     
/*     */     public static Tuple2 apply(GenSeqViewLike.ZippedAll $this, int idx) {
/* 121 */       return new Tuple2((idx < $this.scala$collection$GenSeqViewLike$ZippedAll$$$outer().length()) ? $this.scala$collection$GenSeqViewLike$ZippedAll$$$outer().apply(idx) : $this.thisElem(), 
/* 122 */           (idx < $this.thatSeq().length()) ? $this.thatSeq().apply(idx) : $this.thatElem());
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class Reversed$class {
/*     */     public static void $init$(GenSeqViewLike.Reversed $this) {}
/*     */     
/*     */     public static Iterator iterator(GenSeqViewLike.Reversed $this) {
/* 126 */       return createReversedIterator($this);
/*     */     }
/*     */     
/*     */     public static int length(GenSeqViewLike.Reversed $this) {
/* 127 */       return $this.scala$collection$GenSeqViewLike$Reversed$$$outer().length();
/*     */     }
/*     */     
/*     */     public static Object apply(GenSeqViewLike.Reversed $this, int idx) {
/* 128 */       return $this.scala$collection$GenSeqViewLike$Reversed$$$outer().apply($this.length() - 1 - idx);
/*     */     }
/*     */     
/*     */     public static final String viewIdentifier(GenSeqViewLike.Reversed $this) {
/* 129 */       return "R";
/*     */     }
/*     */     
/*     */     private static Iterator createReversedIterator(GenSeqViewLike.Reversed $this) {
/* 132 */       ObjectRef lst = new ObjectRef(Nil$.MODULE$);
/* 133 */       $this.scala$collection$GenSeqViewLike$Reversed$$$outer().foreach((Function1)new GenSeqViewLike$Reversed$$anonfun$createReversedIterator$1($this, (GenSeqViewLike<A, Coll, This>.Reversed)lst));
/* 134 */       return ((List)lst.elem).iterator();
/*     */     }
/*     */   }
/*     */   
/*     */   public interface Reversed extends Transformed<A> {
/*     */     Iterator<A> iterator();
/*     */     
/*     */     int length();
/*     */     
/*     */     A apply(int param1Int);
/*     */     
/*     */     String viewIdentifier();
/*     */     
/*     */     public class GenSeqViewLike$Reversed$$anonfun$createReversedIterator$1 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final ObjectRef lst$1;
/*     */       
/*     */       public final void apply(Object elem) {
/*     */         this.lst$1.elem = ((List)this.lst$1.elem).$colon$colon(elem);
/*     */       }
/*     */       
/*     */       public GenSeqViewLike$Reversed$$anonfun$createReversedIterator$1(GenSeqViewLike.Reversed $outer, ObjectRef lst$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class Patched$class {
/*     */     public static void $init$(GenSeqViewLike.Patched $this) {}
/*     */     
/*     */     public static int scala$collection$GenSeqViewLike$Patched$$plen(GenSeqViewLike.Patched $this) {
/* 142 */       return $this.patch().length();
/*     */     }
/*     */     
/*     */     public static Iterator iterator(GenSeqViewLike.Patched $this) {
/* 143 */       return $this.scala$collection$GenSeqViewLike$Patched$$$outer().iterator().patch($this.from(), $this.patch().iterator(), $this.replaced());
/*     */     }
/*     */     
/*     */     public static int length(GenSeqViewLike.Patched $this) {
/* 144 */       return $this.scala$collection$GenSeqViewLike$Patched$$$outer().length() + $this.scala$collection$GenSeqViewLike$Patched$$plen() - $this.replaced();
/*     */     }
/*     */     
/*     */     public static Object apply(GenSeqViewLike.Patched $this, int idx) {
/* 146 */       return (idx < $this.from()) ? $this.scala$collection$GenSeqViewLike$Patched$$$outer().apply(idx) : (
/* 147 */         (idx < $this.from() + $this.scala$collection$GenSeqViewLike$Patched$$plen()) ? $this.patch().apply(idx - $this.from()) : 
/* 148 */         $this.scala$collection$GenSeqViewLike$Patched$$$outer().apply(idx - $this.scala$collection$GenSeqViewLike$Patched$$plen() + $this.replaced()));
/*     */     }
/*     */     
/*     */     public static final String viewIdentifier(GenSeqViewLike.Patched $this) {
/* 149 */       return "P";
/*     */     }
/*     */   }
/*     */   
/*     */   public interface Prepended<B> extends Transformed<B> {
/*     */     B fst();
/*     */     
/*     */     Iterator<B> iterator();
/*     */     
/*     */     int length();
/*     */     
/*     */     B apply(int param1Int);
/*     */     
/*     */     String viewIdentifier();
/*     */     
/*     */     public class GenSeqViewLike$Prepended$$anonfun$iterator$1 extends AbstractFunction0<Iterator<A>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Iterator<A> apply() {
/* 154 */         return this.$outer.scala$collection$GenSeqViewLike$Prepended$$$outer().iterator();
/*     */       }
/*     */       
/*     */       public GenSeqViewLike$Prepended$$anonfun$iterator$1(GenSeqViewLike.Prepended $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class Prepended$class {
/*     */     public static void $init$(GenSeqViewLike.Prepended $this) {}
/*     */     
/*     */     public static Iterator iterator(GenSeqViewLike<A, Coll, This>.Prepended<B> $this) {
/* 154 */       return Iterator$.MODULE$.single($this.fst()).$plus$plus((Function0)new GenSeqViewLike$Prepended$$anonfun$iterator$1($this));
/*     */     }
/*     */     
/*     */     public static int length(GenSeqViewLike.Prepended $this) {
/* 155 */       return 1 + $this.scala$collection$GenSeqViewLike$Prepended$$$outer().length();
/*     */     }
/*     */     
/*     */     public static Object apply(GenSeqViewLike.Prepended $this, int idx) {
/* 157 */       return (idx == 0) ? $this.fst() : 
/* 158 */         $this.scala$collection$GenSeqViewLike$Prepended$$$outer().apply(idx - 1);
/*     */     }
/*     */     
/*     */     public static final String viewIdentifier(GenSeqViewLike.Prepended $this) {
/* 159 */       return "A";
/*     */     }
/*     */   }
/*     */   
/*     */   public interface Forced<B> extends GenIterableViewLike<A, Coll, This>.Forced<B>, Transformed<B> {
/*     */     int length();
/*     */     
/*     */     B apply(int param1Int);
/*     */   }
/*     */   
/*     */   public interface Sliced extends GenIterableViewLike<A, Coll, This>.Sliced, Transformed<A> {
/*     */     int length();
/*     */     
/*     */     A apply(int param1Int);
/*     */     
/*     */     <U> void foreach(Function1<A, U> param1Function1);
/*     */     
/*     */     Iterator<A> iterator();
/*     */   }
/*     */   
/*     */   public interface Mapped<B> extends GenIterableViewLike<A, Coll, This>.Mapped<B>, Transformed<B> {
/*     */     int length();
/*     */     
/*     */     B apply(int param1Int);
/*     */   }
/*     */   
/*     */   public interface Zipped<B> extends GenIterableViewLike<A, Coll, This>.Zipped<B>, Transformed<Tuple2<A, B>> {
/*     */     Seq<B> thatSeq();
/*     */     
/*     */     int length();
/*     */     
/*     */     Tuple2<A, B> apply(int param1Int);
/*     */   }
/*     */   
/*     */   public interface Patched<B> extends Transformed<B> {
/*     */     int from();
/*     */     
/*     */     GenSeq<B> patch();
/*     */     
/*     */     int replaced();
/*     */     
/*     */     int scala$collection$GenSeqViewLike$Patched$$plen();
/*     */     
/*     */     Iterator<B> iterator();
/*     */     
/*     */     int length();
/*     */     
/*     */     B apply(int param1Int);
/*     */     
/*     */     String viewIdentifier();
/*     */   }
/*     */   
/*     */   public interface Appended<B> extends GenIterableViewLike<A, Coll, This>.Appended<B>, Transformed<B> {
/*     */     GenSeq<B> restSeq();
/*     */     
/*     */     int length();
/*     */     
/*     */     B apply(int param1Int);
/*     */   }
/*     */   
/*     */   public interface EmptyView extends Transformed<Nothing$>, GenIterableViewLike<A, Coll, This>.EmptyView {
/*     */     int length();
/*     */     
/*     */     Nothing$ apply(int param1Int);
/*     */   }
/*     */   
/*     */   public interface ZippedAll<A1, B> extends GenIterableViewLike<A, Coll, This>.ZippedAll<A1, B>, Transformed<Tuple2<A1, B>> {
/*     */     Seq<B> thatSeq();
/*     */     
/*     */     int length();
/*     */     
/*     */     Tuple2<A1, B> apply(int param1Int);
/*     */   }
/*     */   
/*     */   public interface TakenWhile extends GenIterableViewLike<A, Coll, This>.TakenWhile, Transformed<A> {
/*     */     int len();
/*     */     
/*     */     int length();
/*     */     
/*     */     A apply(int param1Int);
/*     */   }
/*     */   
/*     */   public interface Transformed<B> extends GenSeqView<B, Coll>, GenIterableViewLike<A, Coll, This>.Transformed<B> {
/*     */     int length();
/*     */     
/*     */     B apply(int param1Int);
/*     */     
/*     */     String toString();
/*     */   }
/*     */   
/*     */   public interface DroppedWhile extends GenIterableViewLike<A, Coll, This>.DroppedWhile, Transformed<A> {
/*     */     int start();
/*     */     
/*     */     int length();
/*     */     
/*     */     A apply(int param1Int);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\GenSeqViewLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */