/*     */ package scala;
/*     */ 
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Iterator$;
/*     */ import scala.collection.immutable.;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t=u!B\001\003\021\003)\021AB(qi&|gNC\001\004\003\025\0318-\0317b\007\001\001\"AB\004\016\003\t1Q\001\003\002\t\002%\021aa\0249uS>t7cA\004\013\033A\021aaC\005\003\031\t\021a!\0218z%\0264\007C\001\004\017\023\ty!A\001\007TKJL\027\r\\5{C\ndW\rC\003\022\017\021\005!#\001\004=S:LGO\020\013\002\013!)Ac\002C\002+\005yq\016\035;j_:\024\024\n^3sC\ndW-\006\002\027AQ\021q#\013\t\0041mqbB\001\004\032\023\tQ\"!A\004qC\016\\\027mZ3\n\005qi\"\001C%uKJ\f'\r\\3\013\005i\021\001CA\020!\031\001!Q!I\nC\002\t\022\021!Q\t\003G\031\002\"A\002\023\n\005\025\022!a\002(pi\"Lgn\032\t\003\r\035J!\001\013\002\003\007\005s\027\020C\003+'\001\0071&\001\002y_B\031a\001\f\020\007\013!\021\021\021E\027\026\005924\003\002\027\013_5\001\"A\002\031\n\005E\022!a\002)s_\022,8\r\036\005\006#1\"\ta\r\013\002iA\031a\001L\033\021\005}1DAB\021-\t\013\007!\005C\0039Y\031\005\021(A\004jg\026k\007\017^=\026\003i\002\"AB\036\n\005q\022!a\002\"p_2,\027M\034\005\006}1\"\t!O\001\nSN$UMZ5oK\022DQ\001\021\027\007\002\005\0131aZ3u+\005)\004\"B\"-\t\013!\025!C4fi>\023X\t\\:f+\t)u\t\006\002G\025B\021qd\022\003\006\021\n\023\r!\023\002\002\005F\021QG\n\005\007\027\n#\t\031\001'\002\017\021,g-Y;miB\031a!\024$\n\0059\023!\001\003\037cs:\fW.\032 )\005\t\003\006C\001\004R\023\t\021&A\001\004j]2Lg.\032\005\006)2\")!V\001\007_JtU\017\0347\026\005YCFCA,[!\ty\002\fB\003Z'\n\007\021J\001\002Bc!)1l\025a\0029\006\021QM\036\t\005;\002\034wK\004\002\007=&\021qLA\001\007!J,G-\0324\n\005\005\024'\001\005\023mKN\034HeY8m_:$C.Z:t\025\ty&\001\005\002\007I&\021QM\001\002\005\035VdG\016\013\002T!\")\001\016\fC\003S\006\031Q.\0319\026\005)lGCA6o!\r1A\006\034\t\003?5$Q\001S4C\002\tBQa\\4A\002A\f\021A\032\t\005\rE,D.\003\002s\005\tIa)\0368di&|g.\r\025\003OBCQ!\036\027\005\006Y\fAAZ8mIV\021qO\037\013\003qv$\"!_>\021\005}QH!\002%u\005\004\021\003\"B8u\001\004a\b\003\002\004rkeDaA ;\005\002\004y\030aB5g\0136\004H/\037\t\004\r5K\bF\001;Q\021\035\t)\001\fC\003\003\017\tqA\0327bi6\013\007/\006\003\002\n\005=A\003BA\006\003#\001BA\002\027\002\016A\031q$a\004\005\r!\013\031A1\001#\021\035y\0271\001a\001\003'\001RAB96\003\027A3!a\001Q\021\035\tI\002\fC\001\0037\tqA\0327biR,g.\006\003\002\036\005\rB\003BA\020\003K\001BA\002\027\002\"A\031q$a\t\005\r!\0139B1\001#\021\035Y\026q\003a\002\003O\001R!\03016\003?Aq!a\013-\t\013\ti#\001\004gS2$XM\035\013\004i\005=\002\002CA\031\003S\001\r!a\r\002\003A\004BAB96u!\032\021\021\006)\t\017\005eB\006\"\002\002<\005Ia-\0337uKJtu\016\036\013\004i\005u\002\002CA\031\003o\001\r!a\r)\007\005]\002\013\003\004\002D1\")!O\001\t]>tW)\0349us\"9\021q\t\027\005\006\005%\023AC<ji\"4\025\016\034;feR!\0211JAO!\021\ti%a\024\016\00312a!!\025-\001\005M#AC,ji\"4\025\016\034;feN\031\021q\n\006\t\027\005E\022q\nB\001B\003%\0211\007\005\b#\005=C\021AA-)\021\tY%a\027\t\021\005E\022q\013a\001\003gAq\001[A(\t\003\ty&\006\003\002b\005\035D\003BA2\003S\002BA\002\027\002fA\031q$a\032\005\r!\013iF1\001#\021\035y\027Q\fa\001\003W\002RAB96\003KB\001\"!\002\002P\021\005\021qN\013\005\003c\n9\b\006\003\002t\005e\004\003\002\004-\003k\0022aHA<\t\031A\025Q\016b\001E!9q.!\034A\002\005m\004#\002\004rk\005M\004\002CA@\003\037\"\t!!!\002\017\031|'/Z1dQV!\0211QAI)\021\t))a#\021\007\031\t9)C\002\002\n\n\021A!\0268ji\"9q.! A\002\0055\005#\002\004rk\005=\005cA\020\002\022\0229\0211SA?\005\004\021#!A+\t\021\005\035\023q\nC\001\003/#B!a\023\002\032\"A\0211TAK\001\004\t\031$A\001r\021!\t\t$!\022A\002\005M\002fAA#!\"9\0211\025\027\005\006\005\025\026AB3ySN$8\017F\002;\003OC\001\"!\r\002\"\002\007\0211\007\025\004\003C\003\006bBAWY\021\025\021qV\001\007M>\024\030\r\0347\025\007i\n\t\f\003\005\0022\005-\006\031AA\032Q\r\tY\013\025\005\b\003bCQAA\\+\021\tI,!1\025\t\005\025\0251\030\005\b_\006U\006\031AA_!\0251\021/NA`!\ry\022\021\031\003\b\003'\013)L1\001#Q\r\t)\f\025\005\b\003\017dCQAAe\003\035\031w\016\0347fGR,B!a3\002RR!\021QZAj!\0211A&a4\021\007}\t\t\016\002\004I\003\013\024\rA\t\005\t\003+\f)\r1\001\002X\006\021\001O\032\t\007\r\005eW'a4\n\007\005m'AA\bQCJ$\030.\0317Gk:\034G/[8oQ\r\t)\r\025\005\b\003CdCQAAr\003\031y'/\0227tKV!\021Q]Av)\021\t9/!<\021\t\031a\023\021\036\t\004?\005-HA\002%\002`\n\007\021\nC\005\002p\006}G\0211\001\002r\006Y\021\r\034;fe:\fG/\033<f!\0211Q*a:)\007\005}\007\013C\004\002x2\"\t!!?\002\021%$XM]1u_J,\"!a?\021\ta\ti0N\005\004\003l\"\001C%uKJ\fGo\034:\t\017\t\rA\006\"\001\003\006\0051Ao\034'jgR,\"Aa\002\021\ta\021I!N\005\004\005\027i\"\001\002'jgRDqAa\004-\t\013\021\t\"A\004u_JKw\r\033;\026\t\tM!\021\006\013\005\005+\021iC\005\004\003\0305y#1\004\004\007\0053\001\001A!\006\003\031q\022XMZ5oK6,g\016\036 \021\017\tu!1\005B\024k5\021!q\004\006\004\005C\021\021\001B;uS2LAA!\n\003 \t1Q)\033;iKJ\0042a\bB\025\t\035\021YC!\004C\002\t\022\021\001\027\005\n\005_\021i\001\"a\001\005c\tA\001\\3giB!a!\024B\024Q\r\021i\001\025\005\b\005oaCQ\001B\035\003\031!x\016T3giV!!1\bB#)\021\021iDa\022\023\r\t}Rb\fB!\r\031\021I\002\001\001\003>A9!Q\004B\022k\t\r\003cA\020\003F\0219!1\006B\033\005\004\021\003\"\003B%\005k!\t\031\001B&\003\025\021\030n\0325u!\0211QJa\021)\007\tU\002+K\003-\005#\022)FC\002\003T\t\tAAT8oK&\031!q\013\002\003\tM{W.\032\005\b\0057:A\021\001B/\003\025\t\007\017\0357z+\021\021yF!\032\025\t\t\005$q\r\t\005\r1\022\031\007E\002 \005K\"a!\tB-\005\004\021\003\002\003B5\0053\002\rAa\031\002\003aDqA!\034\b\t\003\021y'A\003f[B$\0300\006\003\003r\t]TC\001B:!\0211AF!\036\021\007}\0219\b\002\004\"\005W\022\rA\t\005\n\005w:\021\021!C\005\005{\n1B]3bIJ+7o\0347wKR\021!q\020\t\005\005\003\023Y)\004\002\003\004*!!Q\021BD\003\021a\027M\\4\013\005\t%\025\001\0026bm\006LAA!$\003\004\n1qJ\0316fGR\004")
/*     */ public abstract class Option<A> implements Product, Serializable {
/*     */   public Iterator<Object> productIterator() {
/*  97 */     return Product$class.productIterator(this);
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/*  97 */     return Product$class.productPrefix(this);
/*     */   }
/*     */   
/*     */   public Option() {
/*  97 */     Product$class.$init$(this);
/*     */   }
/*     */   
/*     */   public boolean isDefined() {
/* 106 */     return !isEmpty();
/*     */   }
/*     */   
/*     */   public final <B> B getOrElse(Function0<B> default) {
/* 120 */     return isEmpty() ? default.apply() : (B)get();
/*     */   }
/*     */   
/*     */   public final <A1> A1 orNull(Predef.$less$colon$less ev) {
/* 131 */     return getOrElse((Function0<A1>)new Option$$anonfun$orNull$1(this, (Option<A>)ev));
/*     */   }
/*     */   
/*     */   public class Option$$anonfun$orNull$1 extends AbstractFunction0<A1> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Predef.$less$colon$less ev$1;
/*     */     
/*     */     public final A1 apply() {
/* 131 */       return (A1)this.ev$1.apply(null);
/*     */     }
/*     */     
/*     */     public Option$$anonfun$orNull$1(Option $outer, Predef.$less$colon$less ev$1) {}
/*     */   }
/*     */   
/*     */   public final <B> Option<B> map(Function1<A, B> f) {
/* 145 */     return isEmpty() ? None$.MODULE$ : new Some<B>(f.apply(get()));
/*     */   }
/*     */   
/*     */   public final <B> B fold(Function0<B> ifEmpty, Function1<A, B> f) {
/* 157 */     return isEmpty() ? ifEmpty.apply() : f.apply(get());
/*     */   }
/*     */   
/*     */   public final <B> Option<B> flatMap(Function1<A, Option<B>> f) {
/* 170 */     return isEmpty() ? None$.MODULE$ : f.apply(get());
/*     */   }
/*     */   
/*     */   public <B> Option<B> flatten(Predef.$less$colon$less<A, Option<B>> ev) {
/* 173 */     return isEmpty() ? None$.MODULE$ : ev.apply(get());
/*     */   }
/*     */   
/*     */   public final Option<A> filter(Function1 p) {
/* 181 */     return (isEmpty() || BoxesRunTime.unboxToBoolean(p.apply(get()))) ? this : None$.MODULE$;
/*     */   }
/*     */   
/*     */   public final Option<A> filterNot(Function1 p) {
/* 189 */     return (!isEmpty() && BoxesRunTime.unboxToBoolean(p.apply(get()))) ? None$.MODULE$ : this;
/*     */   }
/*     */   
/*     */   public final boolean nonEmpty() {
/* 194 */     return isDefined();
/*     */   }
/*     */   
/*     */   public final WithFilter withFilter(Function1<A, Object> p) {
/* 199 */     return new WithFilter(this, p);
/*     */   }
/*     */   
/*     */   public class WithFilter {
/*     */     public final Function1<A, Object> scala$Option$WithFilter$$p;
/*     */     
/*     */     public WithFilter(Option $outer, Function1<A, Object> p) {}
/*     */     
/*     */     public <B> Option<B> map(Function1 f) {
/* 206 */       Function1<A, Object> function1 = this.scala$Option$WithFilter$$p;
/*     */       Option<?> option1, option2;
/* 206 */       return (option2 = ((option1 = scala$Option$WithFilter$$$outer()).isEmpty() || BoxesRunTime.unboxToBoolean(function1.apply((A)option1.get()))) ? option1 : None$.MODULE$).isEmpty() ? None$.MODULE$ : new Some<B>((B)f.apply(option2.get()));
/*     */     }
/*     */     
/*     */     public <B> Option<B> flatMap(Function1 f) {
/* 207 */       Function1<A, Object> function1 = this.scala$Option$WithFilter$$p;
/*     */       Option<?> option1, option2;
/* 207 */       return (option2 = ((option1 = scala$Option$WithFilter$$$outer()).isEmpty() || BoxesRunTime.unboxToBoolean(function1.apply((A)option1.get()))) ? option1 : None$.MODULE$).isEmpty() ? None$.MODULE$ : (Option<B>)f.apply(option2.get());
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/* 208 */       Function1<A, Object> function1 = this.scala$Option$WithFilter$$p;
/*     */       Option<?> option1, option2;
/* 208 */       if (!(option2 = ((option1 = scala$Option$WithFilter$$$outer()).isEmpty() || BoxesRunTime.unboxToBoolean(function1.apply((A)option1.get()))) ? option1 : None$.MODULE$).isEmpty())
/* 208 */         f.apply(option2.get()); 
/*     */     }
/*     */     
/*     */     public WithFilter withFilter(Function1 q) {
/* 209 */       return new WithFilter(scala$Option$WithFilter$$$outer(), (Function1<A, Object>)new Option$WithFilter$$anonfun$withFilter$1(this, (WithFilter)q));
/*     */     }
/*     */     
/*     */     public class Option$WithFilter$$anonfun$withFilter$1 extends AbstractFunction1<A, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Function1 q$1;
/*     */       
/*     */       public final boolean apply(Object x) {
/* 209 */         return (BoxesRunTime.unboxToBoolean(this.$outer.scala$Option$WithFilter$$p.apply((A)x)) && BoxesRunTime.unboxToBoolean(this.q$1.apply(x)));
/*     */       }
/*     */       
/*     */       public Option$WithFilter$$anonfun$withFilter$1(Option.WithFilter $outer, Function1 q$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public final boolean exists(Function1 p) {
/* 219 */     return (!isEmpty() && BoxesRunTime.unboxToBoolean(p.apply(get())));
/*     */   }
/*     */   
/*     */   public final boolean forall(Function1 p) {
/* 226 */     return (isEmpty() || BoxesRunTime.unboxToBoolean(p.apply(get())));
/*     */   }
/*     */   
/*     */   public final <U> void foreach(Function1 f) {
/* 236 */     if (!isEmpty())
/* 236 */       f.apply(get()); 
/*     */   }
/*     */   
/*     */   public final <B> Option<B> collect(PartialFunction<A, B> pf) {
/* 250 */     return (!isEmpty() && pf.isDefinedAt(get())) ? new Some<B>(pf.apply(get())) : None$.MODULE$;
/*     */   }
/*     */   
/*     */   public final <B> Option<B> orElse(Function0<Option<B>> alternative) {
/* 257 */     return isEmpty() ? alternative.apply() : this;
/*     */   }
/*     */   
/*     */   public Iterator<A> iterator() {
/* 263 */     return isEmpty() ? Iterator$.MODULE$.empty() : Iterator$.MODULE$.single(get());
/*     */   }
/*     */   
/*     */   public List<A> toList() {
/* 269 */     return isEmpty() ? (List<A>)Nil$.MODULE$ : (List<A>)new .colon.colon(get(), (List)Nil$.MODULE$);
/*     */   }
/*     */   
/*     */   public final <X> Serializable toRight(Function0 left) {
/* 280 */     return isEmpty() ? (Serializable)package$.MODULE$.Left().apply(left.apply()) : (Serializable)package$.MODULE$.Right().apply(get());
/*     */   }
/*     */   
/*     */   public final <X> Serializable toLeft(Function0 right) {
/* 291 */     return isEmpty() ? (Serializable)package$.MODULE$.Right().apply(right.apply()) : (Serializable)package$.MODULE$.Left().apply(get());
/*     */   }
/*     */   
/*     */   public static <A> Option<A> empty() {
/*     */     return Option$.MODULE$.empty();
/*     */   }
/*     */   
/*     */   public static <A> Option<A> apply(A paramA) {
/*     */     return Option$.MODULE$.apply(paramA);
/*     */   }
/*     */   
/*     */   public static <A> Iterable<A> option2Iterable(Option<A> paramOption) {
/*     */     return Option$.MODULE$.option2Iterable(paramOption);
/*     */   }
/*     */   
/*     */   public abstract boolean isEmpty();
/*     */   
/*     */   public abstract A get();
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Option.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */