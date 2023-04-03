/*     */ package scala.util.matching;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.AbstractIterator;
/*     */ import scala.collection.BufferedIterator;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenSet;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.immutable.MapLike;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.collection.immutable.Stream;
/*     */ import scala.collection.immutable.Vector;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\r-c\001B\001\003\001%\021QAU3hKbT!a\001\003\002\0215\fGo\0315j]\036T!!\002\004\002\tU$\030\016\034\006\002\017\005)1oY1mC\016\0011c\001\001\013\035A\0211\002D\007\002\r%\021QB\002\002\007\003:L(+\0324\021\005-y\021B\001\t\007\0051\031VM]5bY&T\030M\0317f\021!\021\002A!A!\002\023\031\022!\002:fO\026D\bC\001\013\030\035\tYQ#\003\002\027\r\0051\001K]3eK\032L!\001G\r\003\rM#(/\0338h\025\t1b\001\003\005\034\001\t\005\t\025!\003\035\003)9'o\\;q\035\006lWm\035\t\004\027u\031\022B\001\020\007\005)a$/\0329fCR,GM\020\005\006A\001!\t!I\001\007y%t\027\016\036 \025\007\t\"S\005\005\002$\0015\t!\001C\003\023?\001\0071\003C\003\034?\001\007A\004C\004(\001\t\007I\021\001\025\002\017A\fG\017^3s]V\t\021\006\005\002+_5\t1F\003\002\023Y)\021Q!\f\006\002]\005!!.\031<b\023\t\0014FA\004QCR$XM\0358\t\rI\002\001\025!\003*\003!\001\030\r\036;fe:\004\003\"\002\033\001\t\003)\024AC;oCB\004H._*fcR\021a'\022\t\004\027]J\024B\001\035\007\005\031y\005\017^5p]B\031!HQ\n\017\005m\002eB\001\037@\033\005i$B\001 \t\003\031a$o\\8u}%\tq!\003\002B\r\0059\001/Y2lC\036,\027BA\"E\005\021a\025n\035;\013\005\0053\001\"\002$4\001\0049\025A\002;be\036,G\017\005\002\f\021&\021\021J\002\002\004\003:L\b\"B&\001\t#a\025A\003:v]6\013Go\0315feR\021Q\n\025\t\003\0279K!a\024\004\003\017\t{w\016\\3b]\")\021K\023a\001%\006\tQ\016\005\002+'&\021Ak\013\002\b\033\006$8\r[3s\021\0251\006\001\"\001X\003%1\027N\0343BY2Le\016F\002Y\005\0032!WAw\035\t\031#lB\003\\\005!\005A,A\003SK\036,\007\020\005\002$;\032)\021A\001E\001=N\031QL\003\b\t\013\001jF\021\0011\025\003q3qAY/\021\002\007\0051MA\005NCR\034\007\016R1uCN\021\021M\003\005\006K\006$\tAZ\001\007I%t\027\016\036\023\025\003\035\004\"a\0035\n\005%4!\001B+oSRDqa[1C\002\033\005A.\001\004t_V\0248-Z\013\002[B\021a.]\007\002_*\021\001/L\001\005Y\006tw-\003\002s_\na1\t[1s'\026\fX/\0328dK\"91$\031b\001\016\003!X#A;\021\007i28#\003\002x\t\n\0311+Z9\t\013e\fg\021\001>\002\025\035\024x.\0369D_VtG/F\001|!\tYA0\003\002~\r\t\031\021J\034;\t\013}\fg\021\001>\002\013M$\030M\035;\t\r}\fg\021AA\002)\rY\030Q\001\005\b\003\017\t\t\0011\001|\003\005I\007BBA\006C\032\005!0A\002f]\022Dq!a\003b\r\003\ty\001F\002|\003#Aq!a\002\002\016\001\0071\020C\004\002\026\005$\t!a\006\002\0175\fGo\0315fIV\t1\003C\004\002\034\005$\t!!\b\002\013\035\024x.\0369\025\007M\ty\002C\004\002\b\005e\001\031A>\t\017\005\r\022\r\"\001\002&\005I1/\0362he>,\bo]\013\002s!1\021\021F1\005\0021\faAY3g_J,\007bBA\025C\022\005\021Q\006\013\004[\006=\002bBA\004\003W\001\ra\037\005\007\003g\tG\021\0017\002\013\0054G/\032:\t\017\005M\022\r\"\001\0028Q\031Q.!\017\t\017\005\035\021Q\007a\001w\"Q\021QH1\t\006\004%I!a\020\002\0279\fW.\032+p\023:$W\r_\013\003\003\003\002R\001FA\"'mL1!!\022\032\005\ri\025\r\035\005\013\003\023\n\007\022!Q!\n\005\005\023\001\0048b[\026$v.\0238eKb\004\003bBA\016C\022\005\021Q\n\013\004'\005=\003bBA)\003\027\002\raE\001\003S\022Dq!!\026b\t\003\n9&\001\005u_N#(/\0338h)\005\031bABA.;\002\tiFA\003NCR\034\007nE\003\002Z)\ty\006E\002\002b\005l\021!\030\005\nW\006e#Q1A\005\0021D!\"a\032\002Z\t\005\t\025!\003n\003\035\031x.\036:dK\002B!\"a\033\002Z\t\005\t\025!\003S\003\035i\027\r^2iKJD\021bGA-\005\013\007I\021\001;\t\025\005E\024\021\fB\001B\003%Q/A\006he>,\bOT1nKN\004\003b\002\021\002Z\021\005\021Q\017\013\t\003o\nI(a\037\002~A!\021\021MA-\021\031Y\0271\017a\001[\"9\0211NA:\001\004\021\006BB\016\002t\001\007Q\017\003\005\000\0033\022\r\021\"\001{\021!\t\031)!\027!\002\023Y\030AB:uCJ$\b\005C\005\002\f\005e#\031!C\001u\"A\021\021RA-A\003%10\001\003f]\022\004\003BB=\002Z\021\005!\020C\006\002\020\006e\003R1A\005\n\005E\025AB:uCJ$8/\006\002\002\024B!1\"!&|\023\r\t9J\002\002\006\003J\024\030-\037\005\f\0037\013I\006#A!B\023\t\031*A\004ti\006\024Ho\035\021\t\027\005}\025\021\fEC\002\023%\021\021S\001\005K:$7\017C\006\002$\006e\003\022!Q!\n\005M\025!B3oIN\004\003bB@\002Z\021\005\021q\025\013\004w\006%\006bBA\004\003K\003\ra\037\005\t\003\027\tI\006\"\001\002.R\03110a,\t\017\005\035\0211\026a\001w\"A\0211WA-\t\003\t),A\003g_J\034W-\006\002\00286\021\021\021L\004\b\003wk\006\022AA_\003\025i\025\r^2i!\021\t\t'a0\007\017\005mS\f#\001\002BN\031\021q\030\006\t\017\001\ny\f\"\001\002FR\021\021Q\030\005\t\003\023\fy\f\"\001\002L\0069QO\\1qa2LH\003BAg\003'\004BaCAh'%\031\021\021\033\004\003\tM{W.\032\005\b#\006\035\007\031AA<\017\035\t9.\030E\001\0033\faa\022:pkB\034\b\003BA1\00374q!!8^\021\003\tyN\001\004He>,\bo]\n\004\0037T\001b\002\021\002\\\022\005\0211\035\013\003\0033Dq\001NAn\t\003\t9\017\006\003\002j\006-\bcA\0068k\"9\021+!:A\002\005]dABAx;\002\t\tPA\007NCR\034\007.\023;fe\006$xN]\n\t\003[\f\0310a@\002`A)\021Q_A~'5\021\021q\037\006\004\003s4\021AC2pY2,7\r^5p]&!\021Q`A|\005A\t%m\035;sC\016$\030\n^3sCR|'\017\005\003;\005\003\031\022b\001B\002\t\nA\021\n^3sCR|'\017C\005l\003[\024)\031!C\001Y\"Q\021qMAw\005\003\005\013\021B7\t\025I\tiO!b\001\n\003\021Y!F\001#\021)\021y!!<\003\002\003\006IAI\001\007e\026<W\r\037\021\t\023m\tiO!b\001\n\003!\bBCA9\003[\024\t\021)A\005k\"9\001%!<\005\002\t]A\003\003B\r\0057\021iBa\b\021\t\005\005\024Q\036\005\007W\nU\001\031A7\t\rI\021)\0021\001#\021\031Y\"Q\003a\001k\"Y\0211NAw\005\004%\t\"\030B\022+\005\021\006\002\003B\024\003[\004\013\021\002*\002\0215\fGo\0315fe\002B!Ba\013\002n\002\007I\021\002B\027\003!qW\r\037;TK\026tW#A'\t\025\tE\022Q\036a\001\n\023\021\031$\001\007oKb$8+Z3o?\022*\027\017F\002h\005kA\021Ba\016\0030\005\005\t\031A'\002\007a$\023\007\003\005\003<\0055\b\025)\003N\003%qW\r\037;TK\026t\007\005\003\005\003@\0055H\021\001B\027\003\035A\027m\035(fqRD\001Ba\021\002n\022\005\021qK\001\005]\026DH\017\003\005\002V\0055H\021\tB$)\t\021I\005E\002o\005\027J!\001G8\t\r}\fi\017\"\001{\021\035y\030Q\036C\001\005#\"2a\037B*\021\035\t9Aa\024A\002mDq!a\003\002n\022\005!\020\003\005\002\f\0055H\021\001B-)\rY(1\f\005\b\003\017\0219\0061\001|\021\031I\030Q\036C\001u\"A!\021MAw\t\003\021\031'A\005nCR\034\007\016R1uCV\021!Q\r\t\006u\t\005\021q\017\005\n\005S\ni\017\"\001\003\005W\nqB]3qY\006\034W-\\3oi\022\013G/Y\013\003\005[\022bAa\034\003t\tUda\002B9\005O\002!Q\016\002\ryI,g-\0338f[\026tGO\020\t\007\003k\fY0a\036\021\t\005\005$q\017\004\013\005sj\006\023aA\001\005\tm$a\003*fa2\f7-Z7f]R\0342Aa\036\013\021\031)'q\017C\001M\"A\0211\016B<\r#\021\031\003\003\006\003\004\n]\004\031!C\005\005\013\013!a\0352\026\005\t\035\005c\0018\003\n&\031!1R8\003\031M#(/\0338h\005V4g-\032:\t\025\t=%q\017a\001\n\023\021\t*\001\004tE~#S-\035\013\004O\nM\005B\003B\034\005\033\013\t\0211\001\003\b\"I!q\023B<A\003&!qQ\001\004g\n\004\003\002\003BN\005o\"\tA!(\002\021I,\007\017\\1dK\022,\"A!\023\t\021\t\005&q\017C\001\005G\013qA]3qY\006\034W\rF\002S\005KCqAa*\003 \002\0071#\001\002sg\"9!1V/\005\002\t5\026\001E9v_R,'+\0329mC\016,W.\0328u)\r\031\"q\026\005\b\005c\023I\0131\001\024\003\021!X\r\037;\t\023\tUV,!A\005\n\t]\026a\003:fC\022\024Vm]8mm\026$\"A!/\021\0079\024Y,C\002\003>>\024aa\0242kK\016$\b\"B6V\001\004i\007b\002Bb\001\021\005!QY\001\017M&tG-\0217m\033\006$8\r[%o)\021\0219Ma3\021\013i\022\tA!3\021\007e\013I\006\003\004l\005\003\004\r!\034\005\b\005\037\004A\021\001Bi\003-1\027N\0343GSJ\034H/\0238\025\t\tM'Q\033\t\004\027]\032\002BB6\003N\002\007Q\016C\004\003Z\002!\tAa7\002!\031Lg\016\032$jeN$X*\031;dQ&sG\003\002Bo\005?\004BaC\034\003J\"11Na6A\0025DqAa9\001\t\003\021)/\001\007gS:$\007K]3gSb|e\r\006\003\003T\n\035\bBB6\003b\002\007Q\016C\004\003l\002!\tA!<\002#\031Lg\016\032)sK\032L\0070T1uG\"|e\r\006\003\003^\n=\bBB6\003j\002\007Q\016C\004\003t\002!\tA!>\002\031I,\007\017\\1dK\006cG.\0238\025\013M\0219P!?\t\r\031\023\t\0201\001n\021\035\021YP!=A\002M\t1B]3qY\006\034W-\\3oi\"9!1\037\001\005\002\t}H#B\n\004\002\r\r\001B\002$\003~\002\007Q\016\003\005\004\006\tu\b\031AB\004\003!\021X\r\0357bG\026\024\bCB\006\004\n\t%7#C\002\004\f\031\021\021BR;oGRLwN\\\031\t\017\r=\001\001\"\001\004\022\005i!/\0329mC\016,7k\\7f\023:$RaEB\n\007+AaARB\007\001\004i\007\002CB\003\007\033\001\raa\006\021\017-\031IA!3\003T\"911\004\001\005\002\ru\021A\004:fa2\f7-\032$jeN$\030J\034\013\006'\r}1\021\005\005\007\r\016e\001\031A7\t\017\tm8\021\004a\001'!91Q\005\001\005\002\r\035\022!B:qY&$H\003BB\025\007W\001BaCAK'!91QFB\022\001\004i\027a\002;p'Bd\027\016\036\005\b\007c\001A\021AB\032\003))h.\0318dQ>\024X\rZ\013\003\007k\0012aIB\034\023\r\031ID\001\002\020+:\fgn\0315pe\026$'+Z4fq\"91Q\b\001\005\002\t-\021\001C1oG\"|'/\0323\t\017\005U\003\001\"\021\002X!*\001aa\021\004JA\0311b!\022\n\007\r\035cA\001\tTKJL\027\r\034,feNLwN\\+J\tzA!=|kk=C\020s\030")
/*     */ public class Regex implements Serializable {
/*     */   public static final long serialVersionUID = -2094783597747625537L;
/*     */   
/*     */   public final String scala$util$matching$Regex$$regex;
/*     */   
/*     */   public final Seq<String> scala$util$matching$Regex$$groupNames;
/*     */   
/*     */   private final Pattern pattern;
/*     */   
/*     */   public Regex(String regex, Seq<String> groupNames) {
/* 153 */     this.pattern = Pattern.compile(regex);
/*     */   }
/*     */   
/*     */   public Pattern pattern() {
/* 153 */     return this.pattern;
/*     */   }
/*     */   
/*     */   public Option<List<String>> unapplySeq(Object target) {
/*     */     scala.None$ none$;
/* 182 */     if (target instanceof CharSequence) {
/* 182 */       CharSequence charSequence = (CharSequence)target;
/* 184 */       Matcher m = pattern().matcher(charSequence);
/* 185 */       scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 185 */       none$ = (scala.None$)(runMatcher(m) ? new Some(scala.runtime.RichInt$.MODULE$.to$extension0(1, m.groupCount()).toList().map((Function1)new Regex$$anonfun$unapplySeq$1(this, m), scala.collection.immutable.List$.MODULE$.canBuildFrom())) : 
/* 186 */         scala.None$.MODULE$);
/* 187 */     } else if (target instanceof Match) {
/* 187 */       Match match = (Match)target;
/* 187 */       Option<List<String>> option = unapplySeq(match.matched());
/*     */     } else {
/* 188 */       none$ = scala.None$.MODULE$;
/*     */     } 
/*     */     return (Option<List<String>>)none$;
/*     */   }
/*     */   
/*     */   public class Regex$$anonfun$unapplySeq$1 extends AbstractFunction1<Object, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Matcher m$1;
/*     */     
/*     */     public final String apply(int x$1) {
/*     */       return this.m$1.group(x$1);
/*     */     }
/*     */     
/*     */     public Regex$$anonfun$unapplySeq$1(Regex $outer, Matcher m$1) {}
/*     */   }
/*     */   
/*     */   public boolean runMatcher(Matcher m) {
/* 190 */     return m.matches();
/*     */   }
/*     */   
/*     */   public MatchIterator findAllIn(CharSequence source) {
/* 203 */     return new MatchIterator(source, this, this.scala$util$matching$Regex$$groupNames);
/*     */   }
/*     */   
/*     */   public Iterator<Match> findAllMatchIn(CharSequence source) {
/* 214 */     MatchIterator matchIterator = findAllIn(source);
/* 215 */     return new Regex$$anon$4(this, matchIterator);
/*     */   }
/*     */   
/*     */   public class Regex$$anon$4 implements Iterator<Match> {
/*     */     private final Regex.MatchIterator matchIterator$1;
/*     */     
/*     */     public Iterator<Regex.Match> seq() {
/* 215 */       return Iterator.class.seq(this);
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 215 */       return Iterator.class.isEmpty(this);
/*     */     }
/*     */     
/*     */     public boolean isTraversableAgain() {
/* 215 */       return Iterator.class.isTraversableAgain(this);
/*     */     }
/*     */     
/*     */     public boolean hasDefiniteSize() {
/* 215 */       return Iterator.class.hasDefiniteSize(this);
/*     */     }
/*     */     
/*     */     public Iterator<Regex.Match> take(int n) {
/* 215 */       return Iterator.class.take(this, n);
/*     */     }
/*     */     
/*     */     public Iterator<Regex.Match> drop(int n) {
/* 215 */       return Iterator.class.drop(this, n);
/*     */     }
/*     */     
/*     */     public Iterator<Regex.Match> slice(int from, int until) {
/* 215 */       return Iterator.class.slice(this, from, until);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> map(Function1 f) {
/* 215 */       return Iterator.class.map(this, f);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> $plus$plus(Function0 that) {
/* 215 */       return Iterator.class.$plus$plus(this, that);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> flatMap(Function1 f) {
/* 215 */       return Iterator.class.flatMap(this, f);
/*     */     }
/*     */     
/*     */     public Iterator<Regex.Match> filter(Function1 p) {
/* 215 */       return Iterator.class.filter(this, p);
/*     */     }
/*     */     
/*     */     public <B> boolean corresponds(GenTraversableOnce that, Function2 p) {
/* 215 */       return Iterator.class.corresponds(this, that, p);
/*     */     }
/*     */     
/*     */     public Iterator<Regex.Match> withFilter(Function1 p) {
/* 215 */       return Iterator.class.withFilter(this, p);
/*     */     }
/*     */     
/*     */     public Iterator<Regex.Match> filterNot(Function1 p) {
/* 215 */       return Iterator.class.filterNot(this, p);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> collect(PartialFunction pf) {
/* 215 */       return Iterator.class.collect(this, pf);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> scanLeft(Object z, Function2 op) {
/* 215 */       return Iterator.class.scanLeft(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> scanRight(Object z, Function2 op) {
/* 215 */       return Iterator.class.scanRight(this, z, op);
/*     */     }
/*     */     
/*     */     public Iterator<Regex.Match> takeWhile(Function1 p) {
/* 215 */       return Iterator.class.takeWhile(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<Regex.Match>, Iterator<Regex.Match>> partition(Function1 p) {
/* 215 */       return Iterator.class.partition(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<Regex.Match>, Iterator<Regex.Match>> span(Function1 p) {
/* 215 */       return Iterator.class.span(this, p);
/*     */     }
/*     */     
/*     */     public Iterator<Regex.Match> dropWhile(Function1 p) {
/* 215 */       return Iterator.class.dropWhile(this, p);
/*     */     }
/*     */     
/*     */     public <B> Iterator<Tuple2<Regex.Match, B>> zip(Iterator that) {
/* 215 */       return Iterator.class.zip(this, that);
/*     */     }
/*     */     
/*     */     public <A1> Iterator<A1> padTo(int len, Object elem) {
/* 215 */       return Iterator.class.padTo(this, len, elem);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<Regex.Match, Object>> zipWithIndex() {
/* 215 */       return Iterator.class.zipWithIndex(this);
/*     */     }
/*     */     
/*     */     public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator that, Object thisElem, Object thatElem) {
/* 215 */       return Iterator.class.zipAll(this, that, thisElem, thatElem);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/* 215 */       Iterator.class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public boolean forall(Function1 p) {
/* 215 */       return Iterator.class.forall(this, p);
/*     */     }
/*     */     
/*     */     public boolean exists(Function1 p) {
/* 215 */       return Iterator.class.exists(this, p);
/*     */     }
/*     */     
/*     */     public boolean contains(Object elem) {
/* 215 */       return Iterator.class.contains(this, elem);
/*     */     }
/*     */     
/*     */     public Option<Regex.Match> find(Function1 p) {
/* 215 */       return Iterator.class.find(this, p);
/*     */     }
/*     */     
/*     */     public int indexWhere(Function1 p) {
/* 215 */       return Iterator.class.indexWhere(this, p);
/*     */     }
/*     */     
/*     */     public <B> int indexOf(Object elem) {
/* 215 */       return Iterator.class.indexOf(this, elem);
/*     */     }
/*     */     
/*     */     public BufferedIterator<Regex.Match> buffered() {
/* 215 */       return Iterator.class.buffered(this);
/*     */     }
/*     */     
/*     */     public <B> Iterator<Regex.Match>.GroupedIterator<B> grouped(int size) {
/* 215 */       return Iterator.class.grouped(this, size);
/*     */     }
/*     */     
/*     */     public <B> Iterator<Regex.Match>.GroupedIterator<B> sliding(int size, int step) {
/* 215 */       return Iterator.class.sliding(this, size, step);
/*     */     }
/*     */     
/*     */     public int length() {
/* 215 */       return Iterator.class.length(this);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<Regex.Match>, Iterator<Regex.Match>> duplicate() {
/* 215 */       return Iterator.class.duplicate(this);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> patch(int from, Iterator patchElems, int replaced) {
/* 215 */       return Iterator.class.patch(this, from, patchElems, replaced);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start, int len) {
/* 215 */       Iterator.class.copyToArray(this, xs, start, len);
/*     */     }
/*     */     
/*     */     public boolean sameElements(Iterator that) {
/* 215 */       return Iterator.class.sameElements(this, that);
/*     */     }
/*     */     
/*     */     public Traversable<Regex.Match> toTraversable() {
/* 215 */       return Iterator.class.toTraversable(this);
/*     */     }
/*     */     
/*     */     public Iterator<Regex.Match> toIterator() {
/* 215 */       return Iterator.class.toIterator(this);
/*     */     }
/*     */     
/*     */     public Stream<Regex.Match> toStream() {
/* 215 */       return Iterator.class.toStream(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 215 */       return Iterator.class.toString(this);
/*     */     }
/*     */     
/*     */     public <B> int sliding$default$2() {
/* 215 */       return Iterator.class.sliding$default$2(this);
/*     */     }
/*     */     
/*     */     public List<Regex.Match> reversed() {
/* 215 */       return TraversableOnce.class.reversed((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public int size() {
/* 215 */       return TraversableOnce.class.size((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public boolean nonEmpty() {
/* 215 */       return TraversableOnce.class.nonEmpty((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public int count(Function1 p) {
/* 215 */       return TraversableOnce.class.count((TraversableOnce)this, p);
/*     */     }
/*     */     
/*     */     public <B> Option<B> collectFirst(PartialFunction pf) {
/* 215 */       return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*     */     }
/*     */     
/*     */     public <B> B $div$colon(Object z, Function2 op) {
/* 215 */       return (B)TraversableOnce.class.$div$colon((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B $colon$bslash(Object z, Function2 op) {
/* 215 */       return (B)TraversableOnce.class.$colon$bslash((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldLeft(Object z, Function2 op) {
/* 215 */       return (B)TraversableOnce.class.foldLeft((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldRight(Object z, Function2 op) {
/* 215 */       return (B)TraversableOnce.class.foldRight((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceLeft(Function2 op) {
/* 215 */       return (B)TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceRight(Function2 op) {
/* 215 */       return (B)TraversableOnce.class.reduceRight((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceLeftOption(Function2 op) {
/* 215 */       return TraversableOnce.class.reduceLeftOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceRightOption(Function2 op) {
/* 215 */       return TraversableOnce.class.reduceRightOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <A1> A1 reduce(Function2 op) {
/* 215 */       return (A1)TraversableOnce.class.reduce((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <A1> Option<A1> reduceOption(Function2 op) {
/* 215 */       return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <A1> A1 fold(Object z, Function2 op) {
/* 215 */       return (A1)TraversableOnce.class.fold((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/* 215 */       return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*     */     }
/*     */     
/*     */     public <B> B sum(Numeric num) {
/* 215 */       return (B)TraversableOnce.class.sum((TraversableOnce)this, num);
/*     */     }
/*     */     
/*     */     public <B> B product(Numeric num) {
/* 215 */       return (B)TraversableOnce.class.product((TraversableOnce)this, num);
/*     */     }
/*     */     
/*     */     public <B> Regex.Match min(Ordering cmp) {
/* 215 */       return (Regex.Match)TraversableOnce.class.min((TraversableOnce)this, cmp);
/*     */     }
/*     */     
/*     */     public <B> Regex.Match max(Ordering cmp) {
/* 215 */       return (Regex.Match)TraversableOnce.class.max((TraversableOnce)this, cmp);
/*     */     }
/*     */     
/*     */     public <B> Regex.Match maxBy(Function1 f, Ordering cmp) {
/* 215 */       return (Regex.Match)TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> Regex.Match minBy(Function1 f, Ordering cmp) {
/* 215 */       return (Regex.Match)TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> void copyToBuffer(Buffer dest) {
/* 215 */       TraversableOnce.class.copyToBuffer((TraversableOnce)this, dest);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start) {
/* 215 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs, start);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs) {
/* 215 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs);
/*     */     }
/*     */     
/*     */     public <B> Object toArray(ClassTag evidence$1) {
/* 215 */       return TraversableOnce.class.toArray((TraversableOnce)this, evidence$1);
/*     */     }
/*     */     
/*     */     public List<Regex.Match> toList() {
/* 215 */       return TraversableOnce.class.toList((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Iterable<Regex.Match> toIterable() {
/* 215 */       return TraversableOnce.class.toIterable((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Seq<Regex.Match> toSeq() {
/* 215 */       return TraversableOnce.class.toSeq((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<Regex.Match> toIndexedSeq() {
/* 215 */       return TraversableOnce.class.toIndexedSeq((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Buffer<B> toBuffer() {
/* 215 */       return TraversableOnce.class.toBuffer((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Set<B> toSet() {
/* 215 */       return TraversableOnce.class.toSet((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Vector<Regex.Match> toVector() {
/* 215 */       return TraversableOnce.class.toVector((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <Col> Col to(CanBuildFrom cbf) {
/* 215 */       return (Col)TraversableOnce.class.to((TraversableOnce)this, cbf);
/*     */     }
/*     */     
/*     */     public <T, U> Map<T, U> toMap(scala.Predef$.less.colon.less ev) {
/* 215 */       return TraversableOnce.class.toMap((TraversableOnce)this, ev);
/*     */     }
/*     */     
/*     */     public String mkString(String start, String sep, String end) {
/* 215 */       return TraversableOnce.class.mkString((TraversableOnce)this, start, sep, end);
/*     */     }
/*     */     
/*     */     public String mkString(String sep) {
/* 215 */       return TraversableOnce.class.mkString((TraversableOnce)this, sep);
/*     */     }
/*     */     
/*     */     public String mkString() {
/* 215 */       return TraversableOnce.class.mkString((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/* 215 */       return TraversableOnce.class.addString((TraversableOnce)this, b, start, sep, end);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String sep) {
/* 215 */       return TraversableOnce.class.addString((TraversableOnce)this, b, sep);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b) {
/* 215 */       return TraversableOnce.class.addString((TraversableOnce)this, b);
/*     */     }
/*     */     
/*     */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/* 215 */       return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public Regex$$anon$4(Regex $outer, Regex.MatchIterator matchIterator$1) {
/* 215 */       GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/* 215 */       TraversableOnce.class.$init$((TraversableOnce)this);
/* 215 */       Iterator.class.$init$(this);
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 216 */       return this.matchIterator$1.hasNext();
/*     */     }
/*     */     
/*     */     public Regex.Match next() {
/* 218 */       this.matchIterator$1.next();
/* 219 */       return (new Regex.Match(this.matchIterator$1.source(), this.matchIterator$1.matcher(), this.matchIterator$1.groupNames())).force();
/*     */     }
/*     */   }
/*     */   
/*     */   public Option<String> findFirstIn(CharSequence source) {
/* 232 */     Matcher m = pattern().matcher(source);
/* 233 */     return m.find() ? (Option<String>)new Some(m.group()) : (Option<String>)scala.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public Option<Match> findFirstMatchIn(CharSequence source) {
/* 249 */     Matcher m = pattern().matcher(source);
/* 250 */     return m.find() ? (Option<Match>)new Some(new Match(source, m, this.scala$util$matching$Regex$$groupNames)) : (Option<Match>)scala.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public Option<String> findPrefixOf(CharSequence source) {
/* 266 */     Matcher m = pattern().matcher(source);
/* 267 */     return m.lookingAt() ? (Option<String>)new Some(m.group()) : (Option<String>)scala.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public Option<Match> findPrefixMatchOf(CharSequence source) {
/* 283 */     Matcher m = pattern().matcher(source);
/* 284 */     return m.lookingAt() ? (Option<Match>)new Some(new Match(source, m, this.scala$util$matching$Regex$$groupNames)) : (Option<Match>)scala.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public String replaceAllIn(CharSequence target, String replacement) {
/* 297 */     Matcher m = pattern().matcher(target);
/* 298 */     return m.replaceAll(replacement);
/*     */   }
/*     */   
/*     */   public String replaceAllIn(CharSequence target, Function1 replacer) {
/* 320 */     AbstractIterator<Match> it = (new MatchIterator(target, this, this.scala$util$matching$Regex$$groupNames)).replacementData();
/* 321 */     it.foreach((Function1)new Regex$$anonfun$replaceAllIn$1(this, replacer, it));
/* 322 */     return ((Replacement)it).replaced();
/*     */   }
/*     */   
/*     */   public class Regex$$anonfun$replaceAllIn$1 extends AbstractFunction1<Match, Matcher> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 replacer$1;
/*     */     
/*     */     private final AbstractIterator it$1;
/*     */     
/*     */     public final Matcher apply(Regex.Match md) {
/*     */       return ((Regex.Replacement)this.it$1).replace((String)this.replacer$1.apply(md));
/*     */     }
/*     */     
/*     */     public Regex$$anonfun$replaceAllIn$1(Regex $outer, Function1 replacer$1, AbstractIterator it$1) {}
/*     */   }
/*     */   
/*     */   public String replaceSomeIn(CharSequence target, Function1 replacer) {
/* 347 */     AbstractIterator<Match> it = (new MatchIterator(target, this, this.scala$util$matching$Regex$$groupNames)).replacementData();
/* 348 */     it.foreach((Function1)new Regex$$anonfun$replaceSomeIn$1(this, replacer, it));
/* 351 */     return ((Replacement)it).replaced();
/*     */   }
/*     */   
/*     */   public class Regex$$anonfun$replaceSomeIn$1 extends AbstractFunction1<Match, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 replacer$2;
/*     */     
/*     */     public final AbstractIterator it$2;
/*     */     
/*     */     public final void apply(Regex.Match matchdata) {
/*     */       Option option;
/*     */       if (!(option = (Option)this.replacer$2.apply(matchdata)).isEmpty()) {
/*     */         String str = (String)option.get();
/*     */         ((Regex.Replacement)this.it$2).replace(str);
/*     */       } 
/*     */     }
/*     */     
/*     */     public Regex$$anonfun$replaceSomeIn$1(Regex $outer, Function1 replacer$2, AbstractIterator it$2) {}
/*     */   }
/*     */   
/*     */   public String replaceFirstIn(CharSequence target, String replacement) {
/* 363 */     Matcher m = pattern().matcher(target);
/* 364 */     return m.replaceFirst(replacement);
/*     */   }
/*     */   
/*     */   public String[] split(CharSequence toSplit) {
/* 374 */     return pattern().split(toSplit);
/*     */   }
/*     */   
/*     */   public UnanchoredRegex unanchored() {
/* 393 */     return new Regex$$anon$2(this);
/*     */   }
/*     */   
/*     */   public class Regex$$anon$2 extends Regex implements UnanchoredRegex {
/*     */     public boolean runMatcher(Matcher m) {
/* 393 */       return UnanchoredRegex$class.runMatcher(this, m);
/*     */     }
/*     */     
/*     */     public UnanchoredRegex unanchored() {
/* 393 */       return UnanchoredRegex$class.unanchored(this);
/*     */     }
/*     */     
/*     */     public Regex anchored() {
/* 393 */       return this.$outer;
/*     */     }
/*     */     
/*     */     public Regex$$anon$2(Regex $outer) {
/* 393 */       super($outer.scala$util$matching$Regex$$regex, $outer.scala$util$matching$Regex$$groupNames);
/* 393 */       UnanchoredRegex$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public Regex anchored() {
/* 394 */     return this;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 397 */     return this.scala$util$matching$Regex$$regex;
/*     */   }
/*     */   
/*     */   public static String quoteReplacement(String paramString) {
/*     */     return Regex$.MODULE$.quoteReplacement(paramString);
/*     */   }
/*     */   
/*     */   public static abstract class MatchData$class {
/*     */     public static void $init$(Regex.MatchData $this) {}
/*     */     
/*     */     public static String matched(Regex.MatchData $this) {
/* 448 */       return ($this.start() >= 0) ? $this.source().subSequence($this.start(), $this.end()).toString() : null;
/*     */     }
/*     */     
/*     */     public static String group(Regex.MatchData $this, int i) {
/* 454 */       return ($this.start(i) >= 0) ? $this.source().subSequence($this.start(i), $this.end(i)).toString() : null;
/*     */     }
/*     */     
/*     */     public static List subgroups(Regex.MatchData $this) {
/* 458 */       scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 458 */       return (List)scala.runtime.RichInt$.MODULE$.to$extension0(1, $this.groupCount()).toList().map((Function1)new Regex$MatchData$$anonfun$subgroups$1($this), scala.collection.immutable.List$.MODULE$.canBuildFrom());
/*     */     }
/*     */     
/*     */     public static CharSequence before(Regex.MatchData $this) {
/* 463 */       return ($this.start() >= 0) ? $this.source().subSequence(0, $this.start()) : null;
/*     */     }
/*     */     
/*     */     public static CharSequence before(Regex.MatchData $this, int i) {
/* 469 */       return ($this.start(i) >= 0) ? $this.source().subSequence(0, $this.start(i)) : null;
/*     */     }
/*     */     
/*     */     public static CharSequence after(Regex.MatchData $this) {
/* 475 */       return ($this.end() >= 0) ? $this.source().subSequence($this.end(), $this.source().length()) : null;
/*     */     }
/*     */     
/*     */     public static CharSequence after(Regex.MatchData $this, int i) {
/* 481 */       return ($this.end(i) >= 0) ? $this.source().subSequence($this.end(i), $this.source().length()) : null;
/*     */     }
/*     */     
/*     */     public static Map scala$util$matching$Regex$MatchData$$nameToIndex(Regex.MatchData $this) {
/* 484 */       return ((MapLike)scala.Predef$.MODULE$.Map().apply((Seq)scala.collection.immutable.Nil$.MODULE$)).$plus$plus((GenTraversableOnce)$this.groupNames().toList().$colon$colon("").zipWithIndex(scala.collection.immutable.List$.MODULE$.canBuildFrom()));
/*     */     }
/*     */     
/*     */     public static String group(Regex.MatchData $this, String id) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: invokeinterface scala$util$matching$Regex$MatchData$$nameToIndex : ()Lscala/collection/immutable/Map;
/*     */       //   6: aload_1
/*     */       //   7: invokeinterface get : (Ljava/lang/Object;)Lscala/Option;
/*     */       //   12: astore_3
/*     */       //   13: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */       //   16: dup
/*     */       //   17: ifnonnull -> 28
/*     */       //   20: pop
/*     */       //   21: aload_3
/*     */       //   22: ifnull -> 35
/*     */       //   25: goto -> 67
/*     */       //   28: aload_3
/*     */       //   29: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   32: ifeq -> 67
/*     */       //   35: new java/util/NoSuchElementException
/*     */       //   38: dup
/*     */       //   39: new scala/collection/mutable/StringBuilder
/*     */       //   42: dup
/*     */       //   43: invokespecial <init> : ()V
/*     */       //   46: ldc 'group name '
/*     */       //   48: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */       //   51: aload_1
/*     */       //   52: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */       //   55: ldc ' not defined'
/*     */       //   57: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */       //   60: invokevirtual toString : ()Ljava/lang/String;
/*     */       //   63: invokespecial <init> : (Ljava/lang/String;)V
/*     */       //   66: athrow
/*     */       //   67: aload_3
/*     */       //   68: instanceof scala/Some
/*     */       //   71: ifeq -> 93
/*     */       //   74: aload_3
/*     */       //   75: checkcast scala/Some
/*     */       //   78: astore_2
/*     */       //   79: aload_0
/*     */       //   80: aload_2
/*     */       //   81: invokevirtual x : ()Ljava/lang/Object;
/*     */       //   84: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*     */       //   87: invokeinterface group : (I)Ljava/lang/String;
/*     */       //   92: areturn
/*     */       //   93: new scala/MatchError
/*     */       //   96: dup
/*     */       //   97: aload_3
/*     */       //   98: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   101: athrow
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #492	-> 0
/*     */       //   #493	-> 13
/*     */       //   #494	-> 67
/*     */       //   #492	-> 80
/*     */       //   #494	-> 81
/*     */       //   #492	-> 92
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	102	0	$this	Lscala/util/matching/Regex$MatchData;
/*     */       //   0	102	1	id	Ljava/lang/String;
/*     */     }
/*     */     
/*     */     public static String toString(Regex.MatchData $this) {
/* 498 */       return $this.matched();
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface MatchData {
/*     */     CharSequence source();
/*     */     
/*     */     Seq<String> groupNames();
/*     */     
/*     */     int groupCount();
/*     */     
/*     */     int start();
/*     */     
/*     */     int start(int param1Int);
/*     */     
/*     */     int end();
/*     */     
/*     */     int end(int param1Int);
/*     */     
/*     */     String matched();
/*     */     
/*     */     String group(int param1Int);
/*     */     
/*     */     List<String> subgroups();
/*     */     
/*     */     CharSequence before();
/*     */     
/*     */     CharSequence before(int param1Int);
/*     */     
/*     */     CharSequence after();
/*     */     
/*     */     CharSequence after(int param1Int);
/*     */     
/*     */     Map<String, Object> scala$util$matching$Regex$MatchData$$nameToIndex();
/*     */     
/*     */     String group(String param1String);
/*     */     
/*     */     String toString();
/*     */     
/*     */     public class Regex$MatchData$$anonfun$subgroups$1 extends AbstractFunction1<Object, String> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final String apply(int i) {
/*     */         return this.$outer.group(i);
/*     */       }
/*     */       
/*     */       public Regex$MatchData$$anonfun$subgroups$1(Regex.MatchData $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Match implements MatchData {
/*     */     private final CharSequence source;
/*     */     
/*     */     public final Matcher scala$util$matching$Regex$Match$$matcher;
/*     */     
/*     */     private final Seq<String> groupNames;
/*     */     
/*     */     private final int start;
/*     */     
/*     */     private final int end;
/*     */     
/*     */     private int[] starts;
/*     */     
/*     */     private int[] ends;
/*     */     
/*     */     private final Map<String, Object> scala$util$matching$Regex$MatchData$$nameToIndex;
/*     */     
/*     */     private volatile byte bitmap$0;
/*     */     
/*     */     private Map scala$util$matching$Regex$MatchData$$nameToIndex$lzycompute() {
/* 504 */       synchronized (this) {
/* 504 */         if ((byte)(this.bitmap$0 & 0x4) == 0) {
/* 504 */           this.scala$util$matching$Regex$MatchData$$nameToIndex = Regex.MatchData$class.scala$util$matching$Regex$MatchData$$nameToIndex(this);
/* 504 */           this.bitmap$0 = (byte)(this.bitmap$0 | 0x4);
/*     */         } 
/*     */         /* monitor exit ThisExpression{InnerObjectType{ObjectType{scala/util/matching/Regex}.Lscala/util/matching/Regex$Match;}} */
/* 504 */         return this.scala$util$matching$Regex$MatchData$$nameToIndex;
/*     */       } 
/*     */     }
/*     */     
/*     */     public Map<String, Object> scala$util$matching$Regex$MatchData$$nameToIndex() {
/* 504 */       return ((byte)(this.bitmap$0 & 0x4) == 0) ? scala$util$matching$Regex$MatchData$$nameToIndex$lzycompute() : this.scala$util$matching$Regex$MatchData$$nameToIndex;
/*     */     }
/*     */     
/*     */     public String matched() {
/* 504 */       return Regex.MatchData$class.matched(this);
/*     */     }
/*     */     
/*     */     public String group(int i) {
/* 504 */       return Regex.MatchData$class.group(this, i);
/*     */     }
/*     */     
/*     */     public List<String> subgroups() {
/* 504 */       return Regex.MatchData$class.subgroups(this);
/*     */     }
/*     */     
/*     */     public CharSequence before() {
/* 504 */       return Regex.MatchData$class.before(this);
/*     */     }
/*     */     
/*     */     public CharSequence before(int i) {
/* 504 */       return Regex.MatchData$class.before(this, i);
/*     */     }
/*     */     
/*     */     public CharSequence after() {
/* 504 */       return Regex.MatchData$class.after(this);
/*     */     }
/*     */     
/*     */     public CharSequence after(int i) {
/* 504 */       return Regex.MatchData$class.after(this, i);
/*     */     }
/*     */     
/*     */     public String group(String id) {
/* 504 */       return Regex.MatchData$class.group(this, id);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 504 */       return Regex.MatchData$class.toString(this);
/*     */     }
/*     */     
/*     */     public CharSequence source() {
/* 504 */       return this.source;
/*     */     }
/*     */     
/*     */     public Match(CharSequence source, Matcher matcher, Seq<String> groupNames) {
/* 504 */       Regex.MatchData$class.$init$(this);
/* 509 */       this.start = matcher.start();
/* 512 */       this.end = matcher.end();
/*     */     }
/*     */     
/*     */     public Seq<String> groupNames() {
/*     */       return this.groupNames;
/*     */     }
/*     */     
/*     */     public int start() {
/*     */       return this.start;
/*     */     }
/*     */     
/*     */     public int end() {
/* 512 */       return this.end;
/*     */     }
/*     */     
/*     */     public int groupCount() {
/* 515 */       return this.scala$util$matching$Regex$Match$$matcher.groupCount();
/*     */     }
/*     */     
/*     */     private int[] starts$lzycompute() {
/* 517 */       synchronized (this) {
/* 517 */         if ((byte)(this.bitmap$0 & 0x1) == 0) {
/* 518 */           scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 518 */           this.starts = (int[])((TraversableOnce)scala.runtime.RichInt$.MODULE$.to$extension0(0, groupCount()).map((Function1)new Regex$Match$$anonfun$starts$1(this), scala.collection.immutable.IndexedSeq$.MODULE$.canBuildFrom())).toArray(scala.reflect.ClassTag$.MODULE$.Int());
/*     */           this.bitmap$0 = (byte)(this.bitmap$0 | 0x1);
/*     */         } 
/*     */         /* monitor exit ThisExpression{InnerObjectType{ObjectType{scala/util/matching/Regex}.Lscala/util/matching/Regex$Match;}} */
/*     */         return this.starts;
/*     */       } 
/*     */     }
/*     */     
/*     */     private int[] starts() {
/*     */       return ((byte)(this.bitmap$0 & 0x1) == 0) ? starts$lzycompute() : this.starts;
/*     */     }
/*     */     
/*     */     public class Regex$Match$$anonfun$starts$1 extends AbstractFunction1.mcII.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final int apply(int x$1) {
/* 518 */         return this.$outer.scala$util$matching$Regex$Match$$matcher.start(x$1);
/*     */       }
/*     */       
/*     */       public int apply$mcII$sp(int x$1) {
/* 518 */         return this.$outer.scala$util$matching$Regex$Match$$matcher.start(x$1);
/*     */       }
/*     */       
/*     */       public Regex$Match$$anonfun$starts$1(Regex.Match $outer) {}
/*     */     }
/*     */     
/*     */     private int[] ends$lzycompute() {
/* 519 */       synchronized (this) {
/* 519 */         if ((byte)(this.bitmap$0 & 0x2) == 0) {
/* 520 */           scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 520 */           this.ends = (int[])((TraversableOnce)scala.runtime.RichInt$.MODULE$.to$extension0(0, groupCount()).map((Function1)new Regex$Match$$anonfun$ends$1(this), scala.collection.immutable.IndexedSeq$.MODULE$.canBuildFrom())).toArray(scala.reflect.ClassTag$.MODULE$.Int());
/*     */           this.bitmap$0 = (byte)(this.bitmap$0 | 0x2);
/*     */         } 
/*     */         /* monitor exit ThisExpression{InnerObjectType{ObjectType{scala/util/matching/Regex}.Lscala/util/matching/Regex$Match;}} */
/*     */         return this.ends;
/*     */       } 
/*     */     }
/*     */     
/*     */     private int[] ends() {
/*     */       return ((byte)(this.bitmap$0 & 0x2) == 0) ? ends$lzycompute() : this.ends;
/*     */     }
/*     */     
/*     */     public class Regex$Match$$anonfun$ends$1 extends AbstractFunction1.mcII.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final int apply(int x$1) {
/* 520 */         return this.$outer.scala$util$matching$Regex$Match$$matcher.end(x$1);
/*     */       }
/*     */       
/*     */       public int apply$mcII$sp(int x$1) {
/* 520 */         return this.$outer.scala$util$matching$Regex$Match$$matcher.end(x$1);
/*     */       }
/*     */       
/*     */       public Regex$Match$$anonfun$ends$1(Regex.Match $outer) {}
/*     */     }
/*     */     
/*     */     public int start(int i) {
/* 523 */       return starts()[i];
/*     */     }
/*     */     
/*     */     public int end(int i) {
/* 526 */       return ends()[i];
/*     */     }
/*     */     
/*     */     public Match force() {
/* 531 */       starts();
/* 531 */       ends();
/* 531 */       return this;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Match$ {
/*     */     public static final Match$ MODULE$;
/*     */     
/*     */     public class Regex$Match$$anonfun$starts$1 extends AbstractFunction1.mcII.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final int apply(int x$1) {
/*     */         return this.$outer.scala$util$matching$Regex$Match$$matcher.start(x$1);
/*     */       }
/*     */       
/*     */       public int apply$mcII$sp(int x$1) {
/*     */         return this.$outer.scala$util$matching$Regex$Match$$matcher.start(x$1);
/*     */       }
/*     */       
/*     */       public Regex$Match$$anonfun$starts$1(Regex.Match $outer) {}
/*     */     }
/*     */     
/*     */     public class Regex$Match$$anonfun$ends$1 extends AbstractFunction1.mcII.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final int apply(int x$1) {
/*     */         return this.$outer.scala$util$matching$Regex$Match$$matcher.end(x$1);
/*     */       }
/*     */       
/*     */       public int apply$mcII$sp(int x$1) {
/*     */         return this.$outer.scala$util$matching$Regex$Match$$matcher.end(x$1);
/*     */       }
/*     */       
/*     */       public Regex$Match$$anonfun$ends$1(Regex.Match $outer) {}
/*     */     }
/*     */     
/*     */     public Match$() {
/* 545 */       MODULE$ = this;
/*     */     }
/*     */     
/*     */     public Some<String> unapply(Regex.Match m) {
/* 546 */       return new Some(m.matched());
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Groups$ {
/*     */     public static final Groups$ MODULE$;
/*     */     
/*     */     public Groups$() {
/* 560 */       MODULE$ = this;
/*     */     }
/*     */     
/*     */     public Option<Seq<String>> unapplySeq(Regex.Match m) {
/* 561 */       scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 561 */       return (m.groupCount() > 0) ? (Option<Seq<String>>)new Some(scala.runtime.RichInt$.MODULE$.to$extension0(1, m.groupCount()).map((Function1)new Regex$Groups$$anonfun$unapplySeq$2(m), scala.collection.immutable.IndexedSeq$.MODULE$.canBuildFrom())) : (Option<Seq<String>>)scala.None$.MODULE$;
/*     */     }
/*     */     
/*     */     public static class Regex$Groups$$anonfun$unapplySeq$2 extends AbstractFunction1<Object, String> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Regex.Match m$2;
/*     */       
/*     */       public final String apply(int i) {
/* 561 */         return this.m$2.group(i);
/*     */       }
/*     */       
/*     */       public Regex$Groups$$anonfun$unapplySeq$2(Regex.Match m$2) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public static class MatchIterator extends AbstractIterator<String> implements Iterator<String>, MatchData {
/*     */     private final CharSequence source;
/*     */     
/*     */     private final Regex regex;
/*     */     
/*     */     private final Seq<String> groupNames;
/*     */     
/*     */     private final Matcher matcher;
/*     */     
/*     */     private boolean nextSeen;
/*     */     
/*     */     private final Map<String, Object> scala$util$matching$Regex$MatchData$$nameToIndex;
/*     */     
/*     */     private volatile boolean bitmap$0;
/*     */     
/*     */     private Map scala$util$matching$Regex$MatchData$$nameToIndex$lzycompute() {
/* 566 */       synchronized (this) {
/* 566 */         if (!this.bitmap$0) {
/* 566 */           this.scala$util$matching$Regex$MatchData$$nameToIndex = Regex.MatchData$class.scala$util$matching$Regex$MatchData$$nameToIndex(this);
/* 566 */           this.bitmap$0 = true;
/*     */         } 
/*     */         /* monitor exit ThisExpression{InnerObjectType{ObjectType{scala/util/matching/Regex}.Lscala/util/matching/Regex$MatchIterator;}} */
/* 566 */         return this.scala$util$matching$Regex$MatchData$$nameToIndex;
/*     */       } 
/*     */     }
/*     */     
/*     */     public Map<String, Object> scala$util$matching$Regex$MatchData$$nameToIndex() {
/* 566 */       return this.bitmap$0 ? this.scala$util$matching$Regex$MatchData$$nameToIndex : scala$util$matching$Regex$MatchData$$nameToIndex$lzycompute();
/*     */     }
/*     */     
/*     */     public String matched() {
/* 566 */       return Regex.MatchData$class.matched(this);
/*     */     }
/*     */     
/*     */     public String group(int i) {
/* 566 */       return Regex.MatchData$class.group(this, i);
/*     */     }
/*     */     
/*     */     public List<String> subgroups() {
/* 566 */       return Regex.MatchData$class.subgroups(this);
/*     */     }
/*     */     
/*     */     public CharSequence before() {
/* 566 */       return Regex.MatchData$class.before(this);
/*     */     }
/*     */     
/*     */     public CharSequence before(int i) {
/* 566 */       return Regex.MatchData$class.before(this, i);
/*     */     }
/*     */     
/*     */     public CharSequence after() {
/* 566 */       return Regex.MatchData$class.after(this);
/*     */     }
/*     */     
/*     */     public CharSequence after(int i) {
/* 566 */       return Regex.MatchData$class.after(this, i);
/*     */     }
/*     */     
/*     */     public String group(String id) {
/* 566 */       return Regex.MatchData$class.group(this, id);
/*     */     }
/*     */     
/*     */     public CharSequence source() {
/* 566 */       return this.source;
/*     */     }
/*     */     
/*     */     public Regex regex() {
/* 566 */       return this.regex;
/*     */     }
/*     */     
/*     */     public Seq<String> groupNames() {
/* 566 */       return this.groupNames;
/*     */     }
/*     */     
/*     */     public MatchIterator(CharSequence source, Regex regex, Seq<String> groupNames) {
/* 566 */       Regex.MatchData$class.$init$(this);
/* 569 */       this.matcher = regex.pattern().matcher(source);
/* 570 */       this.nextSeen = false;
/*     */     }
/*     */     
/*     */     public Matcher matcher() {
/*     */       return this.matcher;
/*     */     }
/*     */     
/*     */     private boolean nextSeen() {
/* 570 */       return this.nextSeen;
/*     */     }
/*     */     
/*     */     private void nextSeen_$eq(boolean x$1) {
/* 570 */       this.nextSeen = x$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 574 */       if (!nextSeen())
/* 574 */         nextSeen_$eq(matcher().find()); 
/* 575 */       return nextSeen();
/*     */     }
/*     */     
/*     */     public String next() {
/* 580 */       if (hasNext()) {
/* 581 */         nextSeen_$eq(false);
/* 582 */         return matcher().group();
/*     */       } 
/*     */       throw new NoSuchElementException();
/*     */     }
/*     */     
/*     */     public String toString() {
/* 585 */       return Iterator.class.toString(this);
/*     */     }
/*     */     
/*     */     public int start() {
/* 588 */       return matcher().start();
/*     */     }
/*     */     
/*     */     public int start(int i) {
/* 591 */       return matcher().start(i);
/*     */     }
/*     */     
/*     */     public int end() {
/* 594 */       return matcher().end();
/*     */     }
/*     */     
/*     */     public int end(int i) {
/* 597 */       return matcher().end(i);
/*     */     }
/*     */     
/*     */     public int groupCount() {
/* 600 */       return matcher().groupCount();
/*     */     }
/*     */     
/*     */     public Iterator<Regex.Match> matchData() {
/* 603 */       return (Iterator<Regex.Match>)new Regex$MatchIterator$$anon$3(this);
/*     */     }
/*     */     
/*     */     public class Regex$MatchIterator$$anon$3 extends AbstractIterator<Regex.Match> {
/*     */       public Regex$MatchIterator$$anon$3(Regex.MatchIterator $outer) {}
/*     */       
/*     */       public boolean hasNext() {
/* 604 */         return this.$outer.hasNext();
/*     */       }
/*     */       
/*     */       public Regex.Match next() {
/* 605 */         this.$outer.next();
/* 605 */         return (new Regex.Match(this.$outer.source(), this.$outer.matcher(), this.$outer.groupNames())).force();
/*     */       }
/*     */     }
/*     */     
/*     */     public AbstractIterator<Regex.Match> replacementData() {
/* 609 */       return new Regex$MatchIterator$$anon$1(this);
/*     */     }
/*     */     
/*     */     public class Regex$MatchIterator$$anon$1 extends AbstractIterator<Regex.Match> implements Regex.Replacement {
/*     */       private StringBuffer scala$util$matching$Regex$Replacement$$sb;
/*     */       
/*     */       public StringBuffer scala$util$matching$Regex$Replacement$$sb() {
/* 609 */         return this.scala$util$matching$Regex$Replacement$$sb;
/*     */       }
/*     */       
/*     */       public void scala$util$matching$Regex$Replacement$$sb_$eq(StringBuffer x$1) {
/* 609 */         this.scala$util$matching$Regex$Replacement$$sb = x$1;
/*     */       }
/*     */       
/*     */       public String replaced() {
/* 609 */         return Regex.Replacement$class.replaced(this);
/*     */       }
/*     */       
/*     */       public Matcher replace(String rs) {
/* 609 */         return Regex.Replacement$class.replace(this, rs);
/*     */       }
/*     */       
/*     */       public Regex$MatchIterator$$anon$1(Regex.MatchIterator $outer) {
/* 609 */         Regex.Replacement$class.$init$(this);
/*     */       }
/*     */       
/*     */       public Matcher matcher() {
/* 610 */         return this.$outer.matcher();
/*     */       }
/*     */       
/*     */       public boolean hasNext() {
/* 611 */         return this.$outer.hasNext();
/*     */       }
/*     */       
/*     */       public Regex.Match next() {
/* 612 */         this.$outer.next();
/* 612 */         return (new Regex.Match(this.$outer.source(), matcher(), this.$outer.groupNames())).force();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class Replacement$class {
/*     */     public static void $init$(Regex.Replacement $this) {
/* 623 */       $this.scala$util$matching$Regex$Replacement$$sb_$eq(new StringBuffer());
/*     */     }
/*     */     
/*     */     public static String replaced(Regex.Replacement $this) {
/* 626 */       StringBuffer newsb = new StringBuffer($this.scala$util$matching$Regex$Replacement$$sb());
/* 627 */       $this.matcher().appendTail(newsb);
/* 628 */       return newsb.toString();
/*     */     }
/*     */     
/*     */     public static Matcher replace(Regex.Replacement $this, String rs) {
/* 631 */       return $this.matcher().appendReplacement($this.scala$util$matching$Regex$Replacement$$sb(), rs);
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface Replacement {
/*     */     Matcher matcher();
/*     */     
/*     */     StringBuffer scala$util$matching$Regex$Replacement$$sb();
/*     */     
/*     */     @TraitSetter
/*     */     void scala$util$matching$Regex$Replacement$$sb_$eq(StringBuffer param1StringBuffer);
/*     */     
/*     */     String replaced();
/*     */     
/*     */     Matcher replace(String param1String);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\matching\Regex.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */