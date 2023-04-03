/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.collection.AbstractSet;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenSet;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Parallelizable;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Set;
/*     */ import scala.collection.SetLike;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.TraversableView;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.generic.GenericSetTemplate;
/*     */ import scala.collection.generic.Subtractable;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.immutable.ParSet;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t5faB\001\003!\003\r\t!\003\002\004'\026$(BA\002\005\003%IW.\\;uC\ndWM\003\002\006\r\005Q1m\0347mK\016$\030n\0348\013\003\035\tQa]2bY\006\034\001!\006\002\013+M9\001aC\b\037C!b\003C\001\007\016\033\0051\021B\001\b\007\005\031\te.\037*fMB\031\001#E\n\016\003\tI!A\005\002\003\021%#XM]1cY\026\004\"\001F\013\r\001\021)a\003\001b\001/\t\t\021)\005\002\0317A\021A\"G\005\0035\031\021qAT8uQ&tw\r\005\002\r9%\021QD\002\002\004\003:L\bcA\020!'5\tA!\003\002\002\tA!!%J\n(\033\005\031#B\001\023\005\003\0359WM\\3sS\016L!AJ\022\003%\035+g.\032:jGN+G\017V3na2\fG/\032\t\003!\001\001BaH\025\024W%\021!\006\002\002\b'\026$H*[6f!\r\001\002a\005\t\005?5\032r&\003\002/\t\tq\001+\031:bY2,G.\033>bE2,\007c\001\0315'5\t\021G\003\002\004e)\0211\007B\001\ta\006\024\030\r\0347fY&\021Q'\r\002\007!\006\0248+\032;\t\013]\002A\021\001\035\002\r\021Jg.\033;%)\005I\004C\001\007;\023\tYdA\001\003V]&$\b\"B\037\001\t\003r\024!C2p[B\fg.[8o+\005y\004c\001\022AO%\021\021i\t\002\021\017\026tWM]5d\007>l\007/\0318j_:DQa\021\001\005B\021\013Q\001^8TKR,\"!\022%\026\003\031\0032\001\005\001H!\t!\002\nB\003J\005\n\007!JA\001C#\t\0312\004C\003M\001\021\005S*A\002tKF,\022a\013\005\006\037\002!\t\006U\001\fa\006\0248i\\7cS:,'/F\001R!\021\0216kE\030\016\003IJ!\001\026\032\003\021\r{WNY5oKJ<QA\026\002\t\002]\0131aU3u!\t\001\002LB\003\002\005!\005\021l\005\002Y5B\031!eW\024\n\005q\033#aE%n[V$\030M\0317f'\026$h)Y2u_JL\b\"\0020Y\t\003y\026A\002\037j]&$h\bF\001X\021\025\t\007\fb\001c\0031\031\027M\034\"vS2$gI]8n+\t\031G.F\001e!\025\021SmZ6n\023\t17E\001\007DC:\024U/\0337e\rJ|W\016\005\002iS6\t\001,\003\002k\001\n!1i\0347m!\t!B\016B\003\027A\n\007q\003E\002\021\001-DQa\034-\005BA\fQ!Z7qif,\"!\035;\026\003I\0042\001\005\001t!\t!B\017B\003\027]\n\007qcB\003w1\"%q/\001\005F[B$\030pU3u!\tA\007PB\003z1\"%!P\001\005F[B$\030pU3u'\021A8P`@\021\007}a8$\003\002~\t\tY\021IY:ue\006\034GoU3u!\r\001\002a\007\t\004\031\005\005\021bAA\002\r\ta1+\032:jC2L'0\0312mK\"1a\f\037C\001\003\017!\022a\036\005\b\003\027AH\021IA\007\003\021\031\030N_3\026\005\005=\001c\001\007\002\022%\031\0211\003\004\003\007%sG\017C\004\002\030a$\t!!\007\002\021\r|g\016^1j]N$B!a\007\002\"A\031A\"!\b\n\007\005}aAA\004C_>dW-\0318\t\017\005\r\022Q\003a\0017\005!Q\r\\3n\021\035\t9\003\037C\001\003S\tQ\001\n9mkN$2A`A\026\021\035\t\031#!\nA\002mAq!a\fy\t\003\t\t$\001\004%[&tWo\035\013\004}\006M\002bBA\022\003[\001\ra\007\005\b\003oAH\021AA\035\003!IG/\032:bi>\024XCAA\036!\021y\022QH\016\n\007\005}BA\001\005Ji\026\024\030\r^8s\021\035\t\031\005\037C!\003\013\nqAZ8sK\006\034\007.\006\003\002H\005UCcA\035\002J!A\0211JA!\001\004\ti%A\001g!\031a\021qJ\016\002T%\031\021\021\013\004\003\023\031+hn\031;j_:\f\004c\001\013\002V\0219\021qKA!\005\0049\"!A+\t\023\005m\0030!A\005\n\005u\023a\003:fC\022\024Vm]8mm\026$\"!a\030\021\t\005\005\0241N\007\003\003GRA!!\032\002h\005!A.\0318h\025\t\tI'\001\003kCZ\f\027\002BA7\003G\022aa\0242kK\016$hABA91\002\t\031H\001\003TKR\fT\003BA;\003w\032r!a\034\002x\005ut\020\005\003 y\006e\004c\001\013\002|\0211a#a\034C\002]\001B\001\005\001\002z!Y\021\021QA8\005\003\005\013\021BA=\003\025)G.Z72\021!q\026q\016C\001\t\005\025E\003BAD\003\023\003R\001[A8\003sB\001\"!!\002\004\002\007\021\021\020\005\t\003\027\ty\007\"\021\002\016!A\021qCA8\t\003\ty\t\006\003\002\034\005E\005\002CA\022\003\033\003\r!!\037\t\021\005\035\022q\016C\001\003+#B!! \002\030\"A\0211EAJ\001\004\tI\b\003\005\0020\005=D\021AAN)\021\ti(!(\t\021\005\r\022\021\024a\001\003sB\001\"a\016\002p\021\005\021\021U\013\003\003G\003RaHA\037\003sB\001\"a\021\002p\021\005\023qU\013\005\003S\013\t\fF\002:\003WC\001\"a\023\002&\002\007\021Q\026\t\b\031\005=\023\021PAX!\r!\022\021\027\003\b\003/\n)K1\001\030Q\031\ty'!.\002<B\031A\"a.\n\007\005efA\001\tTKJL\027\r\034,feNLwN\\+J\tzA\021#HoOf).>S\002\004\002@b\003\021\021\031\002\005'\026$('\006\003\002D\006%7cBA_\003\013\fYm \t\005?q\f9\rE\002\025\003\023$aAFA_\005\0049\002\003\002\t\001\003\017D1\"!!\002>\n\005\t\025!\003\002H\"Y\021\021[A_\005\003\005\013\021BAd\003\025)G.Z73\021!q\026Q\030C\001\t\005UGCBAl\0033\fY\016E\003i\003{\0139\r\003\005\002\002\006M\007\031AAd\021!\t\t.a5A\002\005\035\007\002CA\006\003{#\t%!\004\t\021\005]\021Q\030C\001\003C$B!a\007\002d\"A\0211EAp\001\004\t9\r\003\005\002(\005uF\021AAt)\021\tY-!;\t\021\005\r\022Q\035a\001\003\017D\001\"a\f\002>\022\005\021Q\036\013\005\003\027\fy\017\003\005\002$\005-\b\031AAd\021!\t9$!0\005\002\005MXCAA{!\025y\022QHAd\021!\t\031%!0\005B\005eX\003BA~\005\007!2!OA\021!\tY%a>A\002\005}\bc\002\007\002P\005\035'\021\001\t\004)\t\rAaBA,\003o\024\ra\006\025\007\003{\013)La\002\037\021\031.:{[\rDkQ4aAa\003Y\001\t5!\001B*fiN*BAa\004\003\026M9!\021\002B\t\005/y\b\003B\020}\005'\0012\001\006B\013\t\0311\"\021\002b\001/A!\001\003\001B\n\021-\t\tI!\003\003\002\003\006IAa\005\t\027\005E'\021\002B\001B\003%!1\003\005\f\005?\021IA!A!\002\023\021\031\"A\003fY\026l7\007\003\005_\005\023!\t\001\002B\022)!\021)Ca\n\003*\t-\002#\0025\003\n\tM\001\002CAA\005C\001\rAa\005\t\021\005E'\021\005a\001\005'A\001Ba\b\003\"\001\007!1\003\005\t\003\027\021I\001\"\021\002\016!A\021q\003B\005\t\003\021\t\004\006\003\002\034\tM\002\002CA\022\005_\001\rAa\005\t\021\005\035\"\021\002C\001\005o!BAa\006\003:!A\0211\005B\033\001\004\021\031\002\003\005\0020\t%A\021\001B\037)\021\0219Ba\020\t\021\005\r\"1\ba\001\005'A\001\"a\016\003\n\021\005!1I\013\003\005\013\002RaHA\037\005'A\001\"a\021\003\n\021\005#\021J\013\005\005\027\022\031\006F\002:\005\033B\001\"a\023\003H\001\007!q\n\t\b\031\005=#1\003B)!\r!\"1\013\003\b\003/\0229E1\001\030Q\031\021I!!.\003XyAa\032Le>s-(\005U\002\004\003\\a\003!Q\f\002\005'\026$H'\006\003\003`\t\0254c\002B-\005C\0229g \t\005?q\024\031\007E\002\025\005K\"aA\006B-\005\0049\002\003\002\t\001\005GB1\"!!\003Z\t\005\t\025!\003\003d!Y\021\021\033B-\005\003\005\013\021\002B2\021-\021yB!\027\003\002\003\006IAa\031\t\027\tE$\021\fB\001B\003%!1M\001\006K2,W\016\016\005\t=\neC\021\001\003\003vQQ!q\017B=\005w\022iHa \021\013!\024IFa\031\t\021\005\005%1\017a\001\005GB\001\"!5\003t\001\007!1\r\005\t\005?\021\031\b1\001\003d!A!\021\017B:\001\004\021\031\007\003\005\002\f\teC\021IA\007\021!\t9B!\027\005\002\t\025E\003BA\016\005\017C\001\"a\t\003\004\002\007!1\r\005\t\003O\021I\006\"\001\003\fR!!q\rBG\021!\t\031C!#A\002\t\r\004\002CA\030\0053\"\tA!%\025\t\t\035$1\023\005\t\003G\021y\t1\001\003d!A\021q\007B-\t\003\0219*\006\002\003\032B)q$!\020\003d!A\0211\tB-\t\003\022i*\006\003\003 \n\035FcA\035\003\"\"A\0211\nBN\001\004\021\031\013E\004\r\003\037\022\031G!*\021\007Q\0219\013B\004\002X\tm%\031A\f)\r\te\023Q\027BV=!i-X*cR[\"0\f")
/*     */ public interface Set<A> extends Iterable<A>, Set<A>, GenericSetTemplate<A, Set>, SetLike<A, Set<A>>, Parallelizable<A, ParSet<A>> {
/*     */   GenericCompanion<Set> companion();
/*     */   
/*     */   <B> Set<B> toSet();
/*     */   
/*     */   Set<A> seq();
/*     */   
/*     */   Combiner<A, ParSet<A>> parCombiner();
/*     */   
/*     */   public static class EmptySet$ extends AbstractSet<Object> implements Set<Object>, Serializable {
/*     */     public static final EmptySet$ MODULE$;
/*     */     
/*     */     public GenericCompanion<Set> companion() {
/*  50 */       return Set$class.companion(this);
/*     */     }
/*     */     
/*     */     public <B> Set<B> toSet() {
/*  50 */       return Set$class.toSet(this);
/*     */     }
/*     */     
/*     */     public Set<Object> seq() {
/*  50 */       return Set$class.seq(this);
/*     */     }
/*     */     
/*     */     public Combiner<Object, ParSet<Object>> parCombiner() {
/*  50 */       return Set$class.parCombiner(this);
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*  50 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public EmptySet$() {
/*  50 */       MODULE$ = this;
/*  50 */       Traversable$class.$init$(this);
/*  50 */       Iterable$class.$init$(this);
/*  50 */       Set$class.$init$(this);
/*     */     }
/*     */     
/*     */     public int size() {
/*  51 */       return 0;
/*     */     }
/*     */     
/*     */     public boolean contains(Object elem) {
/*  52 */       return false;
/*     */     }
/*     */     
/*     */     public Set<Object> $plus(Object elem) {
/*  53 */       return new Set.Set1(elem);
/*     */     }
/*     */     
/*     */     public Set<Object> $minus(Object elem) {
/*  54 */       return this;
/*     */     }
/*     */     
/*     */     public Iterator<Object> iterator() {
/*  55 */       return scala.collection.Iterator$.MODULE$.empty();
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {}
/*     */   }
/*     */   
/*     */   public static class Set1<A> extends AbstractSet<A> implements Set<A>, Serializable {
/*     */     public static final long serialVersionUID = 1233385750652442003L;
/*     */     
/*     */     private final A elem1;
/*     */     
/*     */     public GenericCompanion<Set> companion() {
/*  61 */       return Set$class.companion(this);
/*     */     }
/*     */     
/*     */     public <B> Set<B> toSet() {
/*  61 */       return Set$class.toSet(this);
/*     */     }
/*     */     
/*     */     public Set<A> seq() {
/*  61 */       return Set$class.seq(this);
/*     */     }
/*     */     
/*     */     public Combiner<A, ParSet<A>> parCombiner() {
/*  61 */       return Set$class.parCombiner(this);
/*     */     }
/*     */     
/*     */     public Set1(Object elem1) {
/*  61 */       Traversable$class.$init$(this);
/*  61 */       Iterable$class.$init$(this);
/*  61 */       Set$class.$init$(this);
/*     */     }
/*     */     
/*     */     public int size() {
/*  62 */       return 1;
/*     */     }
/*     */     
/*     */     public boolean contains(Object elem) {
/*  64 */       A a = this.elem1;
/*  64 */       return ((elem == a) ? true : ((elem == null) ? false : ((elem instanceof Number) ? BoxesRunTime.equalsNumObject((Number)elem, a) : ((elem instanceof Character) ? BoxesRunTime.equalsCharObject((Character)elem, a) : elem.equals(a)))));
/*     */     }
/*     */     
/*     */     public Set<A> $plus(Object elem) {
/*  66 */       return contains((A)elem) ? this : 
/*  67 */         new Set.Set2<A>(this.elem1, (A)elem);
/*     */     }
/*     */     
/*     */     public Set<A> $minus(Object elem) {
/*  69 */       A a = this.elem1;
/*  69 */       return ((elem == a) ? true : ((elem == null) ? false : ((elem instanceof Number) ? BoxesRunTime.equalsNumObject((Number)elem, a) : ((elem instanceof Character) ? BoxesRunTime.equalsCharObject((Character)elem, a) : elem.equals(a))))) ? Set$.MODULE$.<A>empty() : 
/*  70 */         this;
/*     */     }
/*     */     
/*     */     public Iterator<A> iterator() {
/*  72 */       return scala.collection.Iterator$.MODULE$.apply((Seq)scala.Predef$.MODULE$.genericWrapArray(new Object[] { this.elem1 }));
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/*  74 */       f.apply(this.elem1);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Set2<A> extends AbstractSet<A> implements Set<A>, Serializable {
/*     */     public static final long serialVersionUID = -6443011234944830092L;
/*     */     
/*     */     private final A elem1;
/*     */     
/*     */     private final A elem2;
/*     */     
/*     */     public GenericCompanion<Set> companion() {
/*  80 */       return Set$class.companion(this);
/*     */     }
/*     */     
/*     */     public <B> Set<B> toSet() {
/*  80 */       return Set$class.toSet(this);
/*     */     }
/*     */     
/*     */     public Set<A> seq() {
/*  80 */       return Set$class.seq(this);
/*     */     }
/*     */     
/*     */     public Combiner<A, ParSet<A>> parCombiner() {
/*  80 */       return Set$class.parCombiner(this);
/*     */     }
/*     */     
/*     */     public Set2(Object elem1, Object elem2) {
/*  80 */       Traversable$class.$init$(this);
/*  80 */       Iterable$class.$init$(this);
/*  80 */       Set$class.$init$(this);
/*     */     }
/*     */     
/*     */     public int size() {
/*  81 */       return 2;
/*     */     }
/*     */     
/*     */     public boolean contains(Object elem) {
/*  83 */       A a = this.elem1;
/*  83 */       if (!((elem == a) ? 1 : ((elem == null) ? 0 : ((elem instanceof Number) ? BoxesRunTime.equalsNumObject((Number)elem, a) : ((elem instanceof Character) ? BoxesRunTime.equalsCharObject((Character)elem, a) : elem.equals(a)))))) {
/*  83 */         A a1 = this.elem2;
/*  83 */         if ((elem == a1) ? true : ((elem == null) ? false : ((elem instanceof Number) ? BoxesRunTime.equalsNumObject((Number)elem, a1) : ((elem instanceof Character) ? BoxesRunTime.equalsCharObject((Character)elem, a1) : elem.equals(a1)))));
/*  83 */         return false;
/*     */       } 
/*     */     }
/*     */     
/*     */     public Set<A> $plus(Object elem) {
/*  85 */       return contains((A)elem) ? this : 
/*  86 */         new Set.Set3<A>(this.elem1, this.elem2, (A)elem);
/*     */     }
/*     */     
/*     */     public Set<A> $minus(Object elem) {
/*  88 */       A a1 = this.elem1;
/*  89 */       A a2 = this.elem2;
/*  89 */       return ((elem == a1) ? true : ((elem == null) ? false : ((elem instanceof Number) ? BoxesRunTime.equalsNumObject((Number)elem, a1) : ((elem instanceof Character) ? BoxesRunTime.equalsCharObject((Character)elem, a1) : elem.equals(a1))))) ? new Set.Set1<A>(this.elem2) : (((elem == a2) ? true : ((elem == null) ? false : ((elem instanceof Number) ? BoxesRunTime.equalsNumObject((Number)elem, a2) : ((elem instanceof Character) ? BoxesRunTime.equalsCharObject((Character)elem, a2) : elem.equals(a2))))) ? new Set.Set1<A>(this.elem1) : 
/*  90 */         this);
/*     */     }
/*     */     
/*     */     public Iterator<A> iterator() {
/*  92 */       return scala.collection.Iterator$.MODULE$.apply((Seq)scala.Predef$.MODULE$.genericWrapArray(new Object[] { this.elem1, this.elem2 }));
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/*  94 */       f.apply(this.elem1);
/*  94 */       f.apply(this.elem2);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Set3<A> extends AbstractSet<A> implements Set<A>, Serializable {
/*     */     public static final long serialVersionUID = -3590273538119220064L;
/*     */     
/*     */     private final A elem1;
/*     */     
/*     */     private final A elem2;
/*     */     
/*     */     private final A elem3;
/*     */     
/*     */     public GenericCompanion<Set> companion() {
/* 100 */       return Set$class.companion(this);
/*     */     }
/*     */     
/*     */     public <B> Set<B> toSet() {
/* 100 */       return Set$class.toSet(this);
/*     */     }
/*     */     
/*     */     public Set<A> seq() {
/* 100 */       return Set$class.seq(this);
/*     */     }
/*     */     
/*     */     public Combiner<A, ParSet<A>> parCombiner() {
/* 100 */       return Set$class.parCombiner(this);
/*     */     }
/*     */     
/*     */     public Set3(Object elem1, Object elem2, Object elem3) {
/* 100 */       Traversable$class.$init$(this);
/* 100 */       Iterable$class.$init$(this);
/* 100 */       Set$class.$init$(this);
/*     */     }
/*     */     
/*     */     public int size() {
/* 101 */       return 3;
/*     */     }
/*     */     
/*     */     public boolean contains(Object elem) {
/* 103 */       A a = this.elem1;
/* 103 */       if (!((elem == a) ? 1 : ((elem == null) ? 0 : ((elem instanceof Number) ? BoxesRunTime.equalsNumObject((Number)elem, a) : ((elem instanceof Character) ? BoxesRunTime.equalsCharObject((Character)elem, a) : elem.equals(a)))))) {
/* 103 */         A a1 = this.elem2;
/* 103 */         if (!((elem == a1) ? 1 : ((elem == null) ? 0 : ((elem instanceof Number) ? BoxesRunTime.equalsNumObject((Number)elem, a1) : ((elem instanceof Character) ? BoxesRunTime.equalsCharObject((Character)elem, a1) : elem.equals(a1)))))) {
/* 103 */           A a2 = this.elem3;
/* 103 */           if ((elem == a2) ? true : ((elem == null) ? false : ((elem instanceof Number) ? BoxesRunTime.equalsNumObject((Number)elem, a2) : ((elem instanceof Character) ? BoxesRunTime.equalsCharObject((Character)elem, a2) : elem.equals(a2)))));
/* 103 */           return false;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     public Set<A> $plus(Object elem) {
/* 105 */       return contains((A)elem) ? this : 
/* 106 */         new Set.Set4<A>(this.elem1, this.elem2, this.elem3, (A)elem);
/*     */     }
/*     */     
/*     */     public Set<A> $minus(Object elem) {
/* 108 */       A a1 = this.elem1;
/* 109 */       A a2 = this.elem2;
/* 110 */       A a3 = this.elem3;
/* 110 */       return ((elem == a1) ? true : ((elem == null) ? false : ((elem instanceof Number) ? BoxesRunTime.equalsNumObject((Number)elem, a1) : ((elem instanceof Character) ? BoxesRunTime.equalsCharObject((Character)elem, a1) : elem.equals(a1))))) ? new Set.Set2<A>(this.elem2, this.elem3) : (((elem == a2) ? true : ((elem == null) ? false : ((elem instanceof Number) ? BoxesRunTime.equalsNumObject((Number)elem, a2) : ((elem instanceof Character) ? BoxesRunTime.equalsCharObject((Character)elem, a2) : elem.equals(a2))))) ? new Set.Set2<A>(this.elem1, this.elem3) : (((elem == a3) ? true : ((elem == null) ? false : ((elem instanceof Number) ? BoxesRunTime.equalsNumObject((Number)elem, a3) : ((elem instanceof Character) ? BoxesRunTime.equalsCharObject((Character)elem, a3) : elem.equals(a3))))) ? new Set.Set2<A>(this.elem1, this.elem2) : 
/* 111 */         this));
/*     */     }
/*     */     
/*     */     public Iterator<A> iterator() {
/* 113 */       return scala.collection.Iterator$.MODULE$.apply((Seq)scala.Predef$.MODULE$.genericWrapArray(new Object[] { this.elem1, this.elem2, this.elem3 }));
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/* 115 */       f.apply(this.elem1);
/* 115 */       f.apply(this.elem2);
/* 115 */       f.apply(this.elem3);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Set4<A> extends AbstractSet<A> implements Set<A>, Serializable {
/*     */     public static final long serialVersionUID = -3622399588156184395L;
/*     */     
/*     */     private final A elem1;
/*     */     
/*     */     private final A elem2;
/*     */     
/*     */     private final A elem3;
/*     */     
/*     */     private final A elem4;
/*     */     
/*     */     public GenericCompanion<Set> companion() {
/* 121 */       return Set$class.companion(this);
/*     */     }
/*     */     
/*     */     public <B> Set<B> toSet() {
/* 121 */       return Set$class.toSet(this);
/*     */     }
/*     */     
/*     */     public Set<A> seq() {
/* 121 */       return Set$class.seq(this);
/*     */     }
/*     */     
/*     */     public Combiner<A, ParSet<A>> parCombiner() {
/* 121 */       return Set$class.parCombiner(this);
/*     */     }
/*     */     
/*     */     public Set4(Object elem1, Object elem2, Object elem3, Object elem4) {
/* 121 */       Traversable$class.$init$(this);
/* 121 */       Iterable$class.$init$(this);
/* 121 */       Set$class.$init$(this);
/*     */     }
/*     */     
/*     */     public int size() {
/* 122 */       return 4;
/*     */     }
/*     */     
/*     */     public boolean contains(Object elem) {
/* 124 */       A a = this.elem1;
/* 124 */       if (!((elem == a) ? 1 : ((elem == null) ? 0 : ((elem instanceof Number) ? BoxesRunTime.equalsNumObject((Number)elem, a) : ((elem instanceof Character) ? BoxesRunTime.equalsCharObject((Character)elem, a) : elem.equals(a)))))) {
/* 124 */         A a1 = this.elem2;
/* 124 */         if (!((elem == a1) ? 1 : ((elem == null) ? 0 : ((elem instanceof Number) ? BoxesRunTime.equalsNumObject((Number)elem, a1) : ((elem instanceof Character) ? BoxesRunTime.equalsCharObject((Character)elem, a1) : elem.equals(a1)))))) {
/* 124 */           A a2 = this.elem3;
/* 124 */           if (!((elem == a2) ? 1 : ((elem == null) ? 0 : ((elem instanceof Number) ? BoxesRunTime.equalsNumObject((Number)elem, a2) : ((elem instanceof Character) ? BoxesRunTime.equalsCharObject((Character)elem, a2) : elem.equals(a2)))))) {
/* 124 */             A a3 = this.elem4;
/* 124 */             if ((elem == a3) ? true : ((elem == null) ? false : ((elem instanceof Number) ? BoxesRunTime.equalsNumObject((Number)elem, a3) : ((elem instanceof Character) ? BoxesRunTime.equalsCharObject((Character)elem, a3) : elem.equals(a3)))));
/* 124 */             return false;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     public Set<A> $plus(Object elem) {
/* 126 */       return contains((A)elem) ? this : (
/* 127 */         new HashSet<A>()).$plus(this.elem1, this.elem2, (Seq<A>)scala.Predef$.MODULE$.genericWrapArray(new Object[] { this.elem3, this.elem4, elem }));
/*     */     }
/*     */     
/*     */     public Set<A> $minus(Object elem) {
/* 129 */       A a1 = this.elem1;
/* 130 */       A a2 = this.elem2;
/* 131 */       A a3 = this.elem3;
/* 132 */       A a4 = this.elem4;
/* 132 */       return ((elem == a1) ? true : ((elem == null) ? false : ((elem instanceof Number) ? BoxesRunTime.equalsNumObject((Number)elem, a1) : ((elem instanceof Character) ? BoxesRunTime.equalsCharObject((Character)elem, a1) : elem.equals(a1))))) ? new Set.Set3<A>(this.elem2, this.elem3, this.elem4) : (((elem == a2) ? true : ((elem == null) ? false : ((elem instanceof Number) ? BoxesRunTime.equalsNumObject((Number)elem, a2) : ((elem instanceof Character) ? BoxesRunTime.equalsCharObject((Character)elem, a2) : elem.equals(a2))))) ? new Set.Set3<A>(this.elem1, this.elem3, this.elem4) : (((elem == a3) ? true : ((elem == null) ? false : ((elem instanceof Number) ? BoxesRunTime.equalsNumObject((Number)elem, a3) : ((elem instanceof Character) ? BoxesRunTime.equalsCharObject((Character)elem, a3) : elem.equals(a3))))) ? new Set.Set3<A>(this.elem1, this.elem2, this.elem4) : (((elem == a4) ? true : ((elem == null) ? false : ((elem instanceof Number) ? BoxesRunTime.equalsNumObject((Number)elem, a4) : ((elem instanceof Character) ? BoxesRunTime.equalsCharObject((Character)elem, a4) : elem.equals(a4))))) ? new Set.Set3<A>(this.elem1, this.elem2, this.elem3) : 
/* 133 */         this)));
/*     */     }
/*     */     
/*     */     public Iterator<A> iterator() {
/* 135 */       return scala.collection.Iterator$.MODULE$.apply((Seq)scala.Predef$.MODULE$.genericWrapArray(new Object[] { this.elem1, this.elem2, this.elem3, this.elem4 }));
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/* 137 */       f.apply(this.elem1);
/* 137 */       f.apply(this.elem2);
/* 137 */       f.apply(this.elem3);
/* 137 */       f.apply(this.elem4);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\Set.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */