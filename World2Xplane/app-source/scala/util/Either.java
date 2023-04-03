/*     */ package scala.util;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\021\025b!B\001\003\003C9!AB#ji\",'O\003\002\004\t\005!Q\017^5m\025\005)\021!B:dC2\f7\001A\013\004\021Qq2C\001\001\n!\tQ1\"D\001\005\023\taAA\001\004B]f\024VM\032\005\006\035\001!\taD\001\007y%t\027\016\036 \025\003A\001B!\005\001\023;5\t!\001\005\002\024)1\001AAB\013\001\t\013\007aCA\001B#\t9\"\004\005\002\0131%\021\021\004\002\002\b\035>$\b.\0338h!\tQ1$\003\002\035\t\t\031\021I\\=\021\005MqBAB\020\001\t\013\007aCA\001C\021\025\t\003\001\"\001#\003\021aWM\032;\026\003\r\002B\001J,\023;9\021\021#J\004\006M\tA\taJ\001\007\013&$\b.\032:\021\005EAc!B\001\003\021\003I3C\001\025\n\021\025q\001\006\"\001,)\0059c\001B\027)\0039\022q\"T3sO\026\f'\r\\3FSRDWM]\013\003_Q\032\"\001L\005\t\021Eb#\021!Q\001\nI\n\021\001\037\t\005#\001\0314\007\005\002\024i\021)Q\003\fb\001-!)a\002\fC\001mQ\021q'\017\t\004q1\032T\"\001\025\t\013E*\004\031\001\032\t\013mbC\021\001\037\002\0135,'oZ3\026\003MBqA\020\025\002\002\023\rq(A\bNKJ<W-\0312mK\026KG\017[3s+\t\0015\t\006\002B\tB\031\001\b\f\"\021\005M\031E!B\013>\005\0041\002\"B\031>\001\004)\005\003B\t\001\005\nCQa\022\025\005\002!\013\001#Z5uQ\026\024('\\3sO\026\f'\r\\3\026\005%cEC\001&N!\rADf\023\t\003'1#Q!\006$C\002YAQ!\r$A\0029\003B!\005\001L\027\"\"a\tU*V!\tQ\021+\003\002S\t\tQA-\0329sK\016\fG/\0323\"\003Q\0131$^:fA5+'oZ3bE2,W)\033;iKJ\004\023N\\:uK\006$\027%\001,\002\rIr\023\007\r\0301\r\021A\006FQ-\003\0351+g\r\036)s_*,7\r^5p]V\031!L\0325\024\t]K1L\030\t\003\025qK!!\030\003\003\017A\023x\016Z;diB\021!bX\005\003A\022\021AbU3sS\006d\027N_1cY\026D\001BY,\003\026\004%\taY\001\002KV\tA\r\005\003\022\001\025<\007CA\ng\t\031)r\013\"b\001-A\0211\003\033\003\007?]#)\031\001\f\t\021)<&\021#Q\001\n\021\f!!\032\021\t\01399F\021\0017\025\0055t\007\003\002\035XK\036DQAY6A\002\021DQ\001],\005\002E\f1aZ3u+\005)\007\"B:X\t\003!\030a\0024pe\026\f7\r[\013\003kr$\"A\007<\t\013]\024\b\031\001=\002\003\031\004BAC=fw&\021!\020\002\002\n\rVt7\r^5p]F\002\"a\005?\005\013u\024(\031\001\f\003\003UCaa`,\005\002\005\005\021!C4fi>\023X\t\\:f+\021\t\031!a\002\025\t\005\025\021Q\002\t\004'\005\035AaBA\005}\n\007\0211\002\002\003\003\006\013\"!\032\016\t\021\005=a\020\"a\001\003#\t!a\034:\021\013)\t\031\"!\002\n\007\005UAA\001\005=Eft\027-\\3?\021\035\tIb\026C\001\0037\taAZ8sC2dG\003BA\017\003G\0012ACA\020\023\r\t\t\003\002\002\b\005>|G.Z1o\021\0359\030q\003a\001\003K\001RAC=f\003;Aq!!\013X\t\003\tY#\001\004fq&\034Ho\035\013\005\003;\ti\003C\004x\003O\001\r!!\n\t\017\005Er\013\"\001\0024\0059a\r\\1u\033\006\004XCBA\033\003\003\nY\004\006\003\0028\005\035\003CB\t\001\003s\ty\004E\002\024\003w!q!!\020\0020\t\007aCA\001Y!\r\031\022\021\t\003\t\003\007\nyC1\001\002F\t\021!IQ\t\003OjAqa^A\030\001\004\tI\005E\003\013s\026\f9\004C\004\002N]#\t!a\024\002\0075\f\007/\006\003\002R\005uC\003BA*\003?\022b!!\026_7\006ecABA,\001\001\t\031F\001\007=e\0264\027N\\3nK:$h\bE\003\022\001\005ms\rE\002\024\003;\"q!!\020\002L\t\007a\003C\004x\003\027\002\r!!\031\021\013)IX-a\027\t\017\005\025t\013\"\001\002h\0051a-\0337uKJ,B!!\033\002vQ!\0211NA=!\025Q\021QNA9\023\r\ty\007\002\002\007\037B$\030n\0348\021\013E\001Q-a\035\021\007M\t)\bB\004\002x\005\r$\031\001\f\003\003eC\001\"a\037\002d\001\007\021QE\001\002a\"9\021qP,\005\002\005\005\025!\002;p'\026\fXCAAB!\025\t))a#f\033\t\t9IC\002\002\n\022\t!bY8mY\026\034G/[8o\023\021\ti)a\"\003\007M+\027\017C\004\002\022^#\t!a%\002\021Q|w\n\035;j_:,\"!!&\021\t)\ti'\032\005\n\0033;\026\021!C\001\0037\013AaY8qsV1\021QTAR\003O#B!a(\002*B1\001hVAQ\003K\0032aEAR\t\031)\022q\023b\001-A\0311#a*\005\r}\t9J1\001\027\021%\021\027q\023I\001\002\004\tY\013\005\004\022\001\005\005\026Q\025\005\n\003_;\026\023!C\001\003c\013abY8qs\022\"WMZ1vYR$\023'\006\004\0024\006%\0271Z\013\003\003kS3\001ZA\\W\t\tI\f\005\003\002<\006\025WBAA_\025\021\ty,!1\002\023Ut7\r[3dW\026$'bAAb\t\005Q\021M\0348pi\006$\030n\0348\n\t\005\035\027Q\030\002\022k:\034\007.Z2lK\0224\026M]5b]\016,GAB\013\002.\n\007a\003\002\004 \003[\023\rA\006\005\n\003\037<\026\021!C!\003#\fQ\002\035:pIV\034G\017\025:fM&DXCAAj!\021\t).a8\016\005\005]'\002BAm\0037\fA\001\\1oO*\021\021Q\\\001\005U\0064\030-\003\003\002b\006]'AB*ue&tw\rC\005\002f^\013\t\021\"\001\002h\006a\001O]8ek\016$\030I]5usV\021\021\021\036\t\004\025\005-\030bAAw\t\t\031\021J\034;\t\023\005Ex+!A\005\002\005M\030A\0049s_\022,8\r^#mK6,g\016\036\013\0045\005U\bBCA|\003_\f\t\0211\001\002j\006\031\001\020J\031\t\023\005mx+!A\005B\005u\030a\0049s_\022,8\r^%uKJ\fGo\034:\026\005\005}\b#BAC\005\003Q\022\002\002B\002\003\017\023\001\"\023;fe\006$xN\035\005\n\005\0179\026\021!C\001\005\023\t\001bY1o\013F,\030\r\034\013\005\003;\021Y\001C\005\002x\n\025\021\021!a\0015!I!qB,\002\002\023\005#\021C\001\tQ\006\034\bnQ8eKR\021\021\021\036\005\n\005+9\026\021!C!\005/\t\001\002^8TiJLgn\032\013\003\003'D\021Ba\007X\003\003%\tE!\b\002\r\025\fX/\0317t)\021\tiBa\b\t\023\005](\021DA\001\002\004Qr!\003B\022Q\005\005\t\022\001B\023\0039aUM\032;Qe>TWm\031;j_:\0042\001\017B\024\r!A\006&!A\t\002\t%2\003\002B\024\023yCqA\004B\024\t\003\021i\003\006\002\003&!Q!Q\003B\024\003\003%)Ea\006\t\025\tM\"qEA\001\n\003\023)$A\003baBd\0270\006\004\0038\tu\"\021\t\013\005\005s\021\031\005\005\0049/\nm\"q\b\t\004'\tuBAB\013\0032\t\007a\003E\002\024\005\003\"aa\bB\031\005\0041\002b\0022\0032\001\007!Q\t\t\007#\001\021YDa\020\t\025\t%#qEA\001\n\003\023Y%A\004v]\006\004\b\017\\=\026\r\t5#Q\013B-)\021\021yEa\027\021\013)\tiG!\025\021\rE\001!1\013B,!\r\031\"Q\013\003\007+\t\035#\031\001\f\021\007M\021I\006\002\004 \005\017\022\rA\006\005\013\005;\0229%!AA\002\t}\023a\001=%aA1\001h\026B*\005/B!Ba\031\003(\005\005I\021\002B3\003-\021X-\0313SKN|GN^3\025\005\t\035\004\003BAk\005SJAAa\033\002X\n1qJ\0316fGR4aAa\034)\005\nE$a\004*jO\"$\bK]8kK\016$\030n\0348\026\r\tM$Q\020BA'\025\021i'C._\021)\021'Q\016BK\002\023\005!qO\013\003\005s\002b!\005\001\003|\t}\004cA\n\003~\0219QC!\034\005\006\0041\002cA\n\003\002\0229qD!\034\005\006\0041\002B\0036\003n\tE\t\025!\003\003z!9aB!\034\005\002\t\035E\003\002BE\005\027\003r\001\017B7\005w\022y\bC\004c\005\013\003\rA!\037\t\017A\024i\007\"\001\003\020V\021!q\020\005\bg\n5D\021\001BJ+\021\021)J!(\025\007i\0219\nC\004x\005#\003\rA!'\021\r)I(q\020BN!\r\031\"Q\024\003\007{\nE%\031\001\f\t\017}\024i\007\"\001\003\"V!!1\025BT)\021\021)Ka+\021\007M\0219\013\002\005\002D\t}%\031\001BU#\r\021yH\007\005\n\003\037\021y\n\"a\001\005[\003RACA\n\005KC\001\"!\007\003n\021\005!\021\027\013\005\003;\021\031\fC\004x\005_\003\rA!.\021\r)I(qPA\017\021!\tIC!\034\005\002\teF\003BA\017\005wCqa\036B\\\001\004\021)\f\003\005\0022\t5D\021\001B`+\031\021\tMa2\003NR!!1\031Bh!\031\t\002A!2\003LB\0311Ca2\005\021\005%!Q\030b\001\005\023\f2Aa\037\033!\r\031\"Q\032\003\b\003o\022iL1\001\027\021\0359(Q\030a\001\005#\004bAC=\003\000\t\r\007\002CA'\005[\"\tA!6\026\t\t]'\021\035\013\005\0053\024\031O\005\004\003\\z[&Q\034\004\007\003/\002\001A!7\021\rE\001!1\020Bp!\r\031\"\021\035\003\b\003o\022\031N1\001\027\021\0359(1\033a\001\005K\004bAC=\003\000\t}\007\002CA3\005[\"\tA!;\026\t\t-(1\037\013\005\005[\024)\020E\003\013\003[\022y\017\005\004\022\001\tE(q\020\t\004'\tMHaBA\037\005O\024\rA\006\005\t\003w\0229\0171\001\0036\"A\021q\020B7\t\003\021I0\006\002\003|B1\021QQAF\005B\001\"!%\003n\021\005!q`\013\003\007\003\001RACA7\005B!\"!'\003n\005\005I\021AB\003+\031\0319a!\004\004\022Q!1\021BB\n!\035A$QNB\006\007\037\0012aEB\007\t\031)21\001b\001-A\0311c!\005\005\r}\031\031A1\001\027\021%\02171\001I\001\002\004\031)\002\005\004\022\001\r-1q\002\005\013\003_\023i'%A\005\002\reQCBB\016\007?\031\t#\006\002\004\036)\"!\021PA\\\t\031)2q\003b\001-\0211qda\006C\002YA!\"a4\003n\005\005I\021IAi\021)\t)O!\034\002\002\023\005\021q\035\005\013\003c\024i'!A\005\002\r%Bc\001\016\004,!Q\021q_B\024\003\003\005\r!!;\t\025\005m(QNA\001\n\003\ni\020\003\006\003\b\t5\024\021!C\001\007c!B!!\b\0044!I\021q_B\030\003\003\005\rA\007\005\013\005\037\021i'!A\005B\tE\001B\003B\013\005[\n\t\021\"\021\003\030!Q!1\004B7\003\003%\tea\017\025\t\005u1Q\b\005\n\003o\034I$!AA\002i9\021b!\021)\003\003E\taa\021\002\037IKw\r\033;Qe>TWm\031;j_:\0042\001OB#\r%\021y\007KA\001\022\003\0319e\005\003\004F%q\006b\002\b\004F\021\00511\n\013\003\007\007B!B!\006\004F\005\005IQ\tB\f\021)\021\031d!\022\002\002\023\0055\021K\013\007\007'\032If!\030\025\t\rU3q\f\t\bq\t54qKB.!\r\0312\021\f\003\007+\r=#\031\001\f\021\007M\031i\006\002\004 \007\037\022\rA\006\005\bE\016=\003\031AB1!\031\t\002aa\026\004\\!Q!\021JB#\003\003%\ti!\032\026\r\r\0354qNB:)\021\031Ig!\036\021\013)\tiga\033\021\rE\0011QNB9!\r\0312q\016\003\007+\r\r$\031\001\f\021\007M\031\031\b\002\004 \007G\022\rA\006\005\013\005;\032\031'!AA\002\r]\004c\002\035\003n\r54\021\017\005\013\005G\032)%!A\005\n\t\025\004bBB?Q\021\0051qP\001\005G>tG-\006\004\004\002\016\03551\022\013\t\007\007\033ii!%\004\030B1\021\003ABC\007\023\0032aEBD\t\031)21\020b\001-A\0311ca#\005\r}\031YH1\001\027\021!\031yia\037A\002\005u\021\001\002;fgRD\021ba%\004|\021\005\ra!&\002\013ILw\r\033;\021\013)\t\031b!#\t\021\005\032Y\b\"a\001\0073\003RACA\n\007\013Cqaa%\001\t\003\031i*\006\002\004 B)AE!\034\023;!911\025\001\005\002\r\025\026\001\0024pY\022,Baa*\004,R11\021VBW\007g\0032aEBV\t\035\tid!)C\002YA\001ba,\004\"\002\0071\021W\001\003M\006\004RAC=\023\007SC\001b!.\004\"\002\0071qW\001\003M\n\004RAC=\036\007SCqaa/\001\t\003\031i,\001\003to\006\004XCAB`%\031\031\tMX.\004D\0321\021q\013\001\001\007\003B!\005\001\036%!91q\031\001\005\002\r%\027!\0036pS:\024\026n\0325u+!\031Ym!5\004r\016eG\003BBg\007;\004b!\005\001\004P\016]\007cA\n\004R\022A11[Bc\005\004\031)N\001\002BcE\021!C\007\t\004'\reGaBBn\007\013\024\rA\006\002\002\007\"A1q\\Bc\001\b\031\t/\001\002fmBA11]Bu\007_\034iMD\002\013\007KL1aa:\005\003\031\001&/\0323fM&!11^Bw\005A!C.Z:tI\r|Gn\0348%Y\026\0348OC\002\004h\022\0012aEBy\t!\031\031p!2C\002\rU(A\001\"2#\ti\"\004C\004\004z\002!\taa?\002\021)|\027N\034'fMR,\002b!@\005\020\021\035A1\001\013\005\007$I\001\005\004\022\001\021\005AQ\001\t\004'\021\rAaBBn\007o\024\rA\006\t\004'\021\035A\001CBz\007o\024\ra!>\t\021\r}7q\037a\002\t\027\001\002ba9\004j\02251q \t\004'\021=A\001CBj\007o\024\ra!6\t\017\021M\001A\"\001\005\026\0051\021n\035'fMR,\"!!\b\t\017\021e\001A\"\001\005\026\0059\021n\035*jO\"$\030&\002\001\005\036\021\005\022b\001C\020\005\t!A*\0324u\023\r!\031C\001\002\006%&<\007\016\036")
/*     */ public abstract class Either<A, B> {
/*     */   public static <A, B> Either<A, B> cond(boolean paramBoolean, Function0<B> paramFunction0, Function0<A> paramFunction01) {
/*     */     return Either$.MODULE$.cond(paramBoolean, paramFunction0, paramFunction01);
/*     */   }
/*     */   
/*     */   public static <A> MergeableEither<A> either2mergeable(Either<A, A> paramEither) {
/*     */     return Either$.MODULE$.either2mergeable(paramEither);
/*     */   }
/*     */   
/*     */   public static <A> MergeableEither<A> MergeableEither(Either<A, A> paramEither) {
/*     */     return Either$.MODULE$.MergeableEither(paramEither);
/*     */   }
/*     */   
/*     */   public LeftProjection<A, B> left() {
/*  74 */     return new LeftProjection<A, B>(this);
/*     */   }
/*     */   
/*     */   public RightProjection<A, B> right() {
/*  79 */     return new RightProjection<A, B>(this);
/*     */   }
/*     */   
/*     */   public <X> X fold(Function1 fa, Function1 fb) {
/*     */     Object object;
/*  96 */     if (this instanceof Left) {
/*  96 */       Left left = (Left)this;
/*  97 */       object = fa.apply(left.a());
/*     */     } else {
/*  98 */       if (this instanceof Right) {
/*  98 */         Right right = (Right)this;
/*  98 */         object = fb.apply(right.b());
/*     */       } 
/*     */       throw new MatchError(this);
/*     */     } 
/*     */     return (X)object;
/*     */   }
/*     */   
/*     */   public Serializable swap() {
/*     */     Left<Object, Object> left;
/* 109 */     if (this instanceof Left) {
/* 109 */       Left left1 = (Left)this;
/* 110 */       Right<Object, Object> right = new Right<Object, Object>(left1.a());
/*     */     } else {
/* 111 */       if (this instanceof Right) {
/* 111 */         Right right = (Right)this;
/* 111 */         left = new Left<Object, Object>(right.b());
/*     */       } 
/*     */       throw new MatchError(this);
/*     */     } 
/*     */     return left;
/*     */   }
/*     */   
/*     */   public <A1, B1, C> Either<A1, C> joinRight(scala.Predef$.less.colon.less ev) {
/*     */     Either<A1, C> either;
/* 133 */     if (this instanceof Left) {
/* 133 */       Left left = (Left)this;
/* 134 */       either = (Either)new Left<Object, Object>(left.a());
/*     */     } else {
/* 135 */       if (this instanceof Right) {
/* 135 */         Right right = (Right)this;
/* 135 */         either = (Either)ev.apply(right.b());
/*     */       } 
/*     */       throw new MatchError(this);
/*     */     } 
/*     */     return either;
/*     */   }
/*     */   
/*     */   public <A1, B1, C> Either<C, B1> joinLeft(scala.Predef$.less.colon.less ev) {
/*     */     Either<C, B1> either;
/* 157 */     if (this instanceof Left) {
/* 157 */       Left left = (Left)this;
/* 158 */       either = (Either)ev.apply(left.a());
/*     */     } else {
/* 159 */       if (this instanceof Right) {
/* 159 */         Right right = (Right)this;
/* 159 */         either = (Either)new Right<Object, Object>(right.b());
/*     */       } 
/*     */       throw new MatchError(this);
/*     */     } 
/*     */     return either;
/*     */   }
/*     */   
/*     */   public abstract boolean isLeft();
/*     */   
/*     */   public abstract boolean isRight();
/*     */   
/*     */   public static class MergeableEither<A> {
/*     */     private final Either<A, A> x;
/*     */     
/*     */     public MergeableEither(Either<A, A> x) {}
/*     */     
/*     */     public A merge() {
/* 219 */       Either<A, A> either = this.x;
/* 220 */       if (either instanceof Left) {
/* 220 */         Left left = (Left)either;
/* 220 */         Object object = left.a();
/*     */       } 
/* 221 */       if (either instanceof Right) {
/* 221 */         Right right = (Right)either;
/* 221 */         Object object = right.b();
/*     */       } 
/*     */       throw new MatchError(either);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class LeftProjection<A, B> implements Product, Serializable {
/*     */     private final Either<A, B> e;
/*     */     
/*     */     public Either<A, B> e() {
/* 276 */       return this.e;
/*     */     }
/*     */     
/*     */     public <A, B> LeftProjection<A, B> copy(Either<A, B> e) {
/* 276 */       return new LeftProjection(e);
/*     */     }
/*     */     
/*     */     public <A, B> Either<A, B> copy$default$1() {
/* 276 */       return e();
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/* 276 */       return "LeftProjection";
/*     */     }
/*     */     
/*     */     public int productArity() {
/* 276 */       return 1;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/* 276 */       switch (x$1) {
/*     */         default:
/* 276 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */         case 0:
/*     */           break;
/*     */       } 
/* 276 */       return e();
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/* 276 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/* 276 */       return x$1 instanceof LeftProjection;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 276 */       return scala.runtime.ScalaRunTime$.MODULE$._hashCode(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 276 */       return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object x$1) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: if_acmpeq -> 67
/*     */       //   5: aload_1
/*     */       //   6: instanceof scala/util/Either$LeftProjection
/*     */       //   9: ifeq -> 17
/*     */       //   12: iconst_1
/*     */       //   13: istore_2
/*     */       //   14: goto -> 19
/*     */       //   17: iconst_0
/*     */       //   18: istore_2
/*     */       //   19: iload_2
/*     */       //   20: ifeq -> 71
/*     */       //   23: aload_1
/*     */       //   24: checkcast scala/util/Either$LeftProjection
/*     */       //   27: astore_3
/*     */       //   28: aload_0
/*     */       //   29: invokevirtual e : ()Lscala/util/Either;
/*     */       //   32: aload_3
/*     */       //   33: invokevirtual e : ()Lscala/util/Either;
/*     */       //   36: astore #4
/*     */       //   38: dup
/*     */       //   39: ifnonnull -> 51
/*     */       //   42: pop
/*     */       //   43: aload #4
/*     */       //   45: ifnull -> 59
/*     */       //   48: goto -> 63
/*     */       //   51: aload #4
/*     */       //   53: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   56: ifeq -> 63
/*     */       //   59: iconst_1
/*     */       //   60: goto -> 64
/*     */       //   63: iconst_0
/*     */       //   64: ifeq -> 71
/*     */       //   67: iconst_1
/*     */       //   68: goto -> 72
/*     */       //   71: iconst_0
/*     */       //   72: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #276	-> 0
/*     */       //   #236	-> 12
/*     */       //   #276	-> 19
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	73	0	this	Lscala/util/Either$LeftProjection;
/*     */       //   0	73	1	x$1	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public LeftProjection(Either<A, B> e) {
/* 276 */       Product.class.$init$(this);
/*     */     }
/*     */     
/*     */     public A get() {
/* 288 */       Either<A, B> either = e();
/* 289 */       if (either instanceof Left)
/* 289 */         Left left = (Left)either; 
/* 290 */       if (either instanceof Right)
/* 290 */         throw new NoSuchElementException("Either.left.value on Right"); 
/*     */       throw new MatchError(either);
/*     */     }
/*     */     
/*     */     public <U> Object foreach(Function1 f) {
/* 302 */       Either<A, B> either = e();
/* 303 */       if (either instanceof Left) {
/* 303 */         Left left = (Left)either;
/* 303 */         Object object = f.apply(left.a());
/*     */       } 
/* 304 */       if (either instanceof Right)
/* 304 */         return BoxedUnit.UNIT; 
/*     */       throw new MatchError(either);
/*     */     }
/*     */     
/*     */     public <AA> AA getOrElse(Function0 or) {
/* 317 */       Either<A, B> either = e();
/* 318 */       if (either instanceof Left) {
/* 318 */         Left left = (Left)either;
/* 318 */         Object object = left.a();
/*     */       } 
/* 319 */       if (either instanceof Right)
/* 319 */         return (AA)or.apply(); 
/*     */       throw new MatchError(either);
/*     */     }
/*     */     
/*     */     public boolean forall(Function1 f) {
/* 333 */       Either<A, B> either = e();
/* 334 */       if (either instanceof Left) {
/* 334 */         Left left = (Left)either;
/* 334 */         boolean bool = BoxesRunTime.unboxToBoolean(f.apply(left.a()));
/*     */       } 
/* 335 */       if (either instanceof Right)
/*     */         return true; 
/*     */       throw new MatchError(either);
/*     */     }
/*     */     
/*     */     public boolean exists(Function1 f) {
/* 349 */       Either<A, B> either = e();
/* 350 */       if (either instanceof Left) {
/* 350 */         Left left = (Left)either;
/* 350 */         boolean bool = BoxesRunTime.unboxToBoolean(f.apply(left.a()));
/*     */       } 
/* 351 */       if (either instanceof Right)
/*     */         return false; 
/*     */       throw new MatchError(either);
/*     */     }
/*     */     
/*     */     public <BB, X> Either<X, BB> flatMap(Function1 f) {
/* 363 */       Either<A, B> either = e();
/* 364 */       if (either instanceof Left) {
/* 364 */         Left left = (Left)either;
/* 364 */         Either either1 = (Either)f.apply(left.a());
/*     */       } 
/* 365 */       if (either instanceof Right) {
/* 365 */         Right right1 = (Right)either;
/* 365 */         Right<Object, Object> right = new Right<Object, Object>(right1.b());
/*     */       } 
/*     */       throw new MatchError(either);
/*     */     }
/*     */     
/*     */     public <X> Serializable map(Function1 f) {
/* 376 */       Either<A, B> either = e();
/* 377 */       if (either instanceof Left) {
/* 377 */         Left left = (Left)either;
/* 377 */         Left<Object, Object> left1 = new Left<Object, Object>(f.apply(left.a()));
/*     */       } 
/* 378 */       if (either instanceof Right) {
/* 378 */         Right right1 = (Right)either;
/* 378 */         Right<Object, Object> right = new Right<Object, Object>(right1.b());
/*     */       } 
/*     */       throw new MatchError(either);
/*     */     }
/*     */     
/*     */     public <Y> Option<Either<A, Y>> filter(Function1 p) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: invokevirtual e : ()Lscala/util/Either;
/*     */       //   4: astore_2
/*     */       //   5: aload_2
/*     */       //   6: instanceof scala/util/Left
/*     */       //   9: ifeq -> 62
/*     */       //   12: aload_2
/*     */       //   13: checkcast scala/util/Left
/*     */       //   16: astore_3
/*     */       //   17: aload_1
/*     */       //   18: aload_3
/*     */       //   19: invokevirtual a : ()Ljava/lang/Object;
/*     */       //   22: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */       //   27: invokestatic unboxToBoolean : (Ljava/lang/Object;)Z
/*     */       //   30: ifeq -> 54
/*     */       //   33: new scala/Some
/*     */       //   36: dup
/*     */       //   37: new scala/util/Left
/*     */       //   40: dup
/*     */       //   41: aload_3
/*     */       //   42: invokevirtual a : ()Ljava/lang/Object;
/*     */       //   45: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   48: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   51: goto -> 57
/*     */       //   54: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */       //   57: astore #4
/*     */       //   59: goto -> 74
/*     */       //   62: aload_2
/*     */       //   63: instanceof scala/util/Right
/*     */       //   66: ifeq -> 77
/*     */       //   69: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */       //   72: astore #4
/*     */       //   74: aload #4
/*     */       //   76: areturn
/*     */       //   77: new scala/MatchError
/*     */       //   80: dup
/*     */       //   81: aload_2
/*     */       //   82: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   85: athrow
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #391	-> 0
/*     */       //   #392	-> 5
/*     */       //   #391	-> 18
/*     */       //   #392	-> 19
/*     */       //   #391	-> 41
/*     */       //   #392	-> 42
/*     */       //   #393	-> 62
/*     */       //   #391	-> 74
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	86	0	this	Lscala/util/Either$LeftProjection;
/*     */       //   0	86	1	p	Lscala/Function1;
/*     */     }
/*     */     
/*     */     public Seq<A> toSeq() {
/* 405 */       Either<A, B> either = e();
/* 406 */       if (either instanceof Left) {
/* 406 */         Left left = (Left)either;
/* 406 */         Seq seq = (Seq)scala.collection.Seq$.MODULE$.apply((Seq)scala.Predef$.MODULE$.genericWrapArray(new Object[] { left.a() }));
/*     */       } 
/* 407 */       if (either instanceof Right)
/* 407 */         return (Seq)scala.collection.Seq$.MODULE$.empty(); 
/*     */       throw new MatchError(either);
/*     */     }
/*     */     
/*     */     public Option<A> toOption() {
/* 419 */       Either<A, B> either = e();
/* 420 */       if (either instanceof Left) {
/* 420 */         Left left = (Left)either;
/* 420 */         Some some = new Some(left.a());
/*     */       } 
/* 421 */       if (either instanceof Right)
/* 421 */         return (Option<A>)scala.None$.MODULE$; 
/*     */       throw new MatchError(either);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class LeftProjection$ implements Serializable {
/*     */     public static final LeftProjection$ MODULE$;
/*     */     
/*     */     public final String toString() {
/*     */       return "LeftProjection";
/*     */     }
/*     */     
/*     */     public <A, B> Either.LeftProjection<A, B> apply(Either<A, B> e) {
/*     */       return new Either.LeftProjection<A, B>(e);
/*     */     }
/*     */     
/*     */     public <A, B> Option<Either<A, B>> unapply(Either.LeftProjection x$0) {
/*     */       return (x$0 == null) ? (Option<Either<A, B>>)scala.None$.MODULE$ : (Option<Either<A, B>>)new Some(x$0.e());
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*     */       return MODULE$;
/*     */     }
/*     */     
/*     */     public LeftProjection$() {
/*     */       MODULE$ = this;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class RightProjection<A, B> implements Product, Serializable {
/*     */     private final Either<A, B> e;
/*     */     
/*     */     public Either<A, B> e() {
/* 440 */       return this.e;
/*     */     }
/*     */     
/*     */     public <A, B> RightProjection<A, B> copy(Either<A, B> e) {
/* 440 */       return new RightProjection(e);
/*     */     }
/*     */     
/*     */     public <A, B> Either<A, B> copy$default$1() {
/* 440 */       return e();
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/* 440 */       return "RightProjection";
/*     */     }
/*     */     
/*     */     public int productArity() {
/* 440 */       return 1;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/* 440 */       switch (x$1) {
/*     */         default:
/* 440 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */         case 0:
/*     */           break;
/*     */       } 
/* 440 */       return e();
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/* 440 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/* 440 */       return x$1 instanceof RightProjection;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 440 */       return scala.runtime.ScalaRunTime$.MODULE$._hashCode(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 440 */       return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object x$1) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: if_acmpeq -> 67
/*     */       //   5: aload_1
/*     */       //   6: instanceof scala/util/Either$RightProjection
/*     */       //   9: ifeq -> 17
/*     */       //   12: iconst_1
/*     */       //   13: istore_2
/*     */       //   14: goto -> 19
/*     */       //   17: iconst_0
/*     */       //   18: istore_2
/*     */       //   19: iload_2
/*     */       //   20: ifeq -> 71
/*     */       //   23: aload_1
/*     */       //   24: checkcast scala/util/Either$RightProjection
/*     */       //   27: astore_3
/*     */       //   28: aload_0
/*     */       //   29: invokevirtual e : ()Lscala/util/Either;
/*     */       //   32: aload_3
/*     */       //   33: invokevirtual e : ()Lscala/util/Either;
/*     */       //   36: astore #4
/*     */       //   38: dup
/*     */       //   39: ifnonnull -> 51
/*     */       //   42: pop
/*     */       //   43: aload #4
/*     */       //   45: ifnull -> 59
/*     */       //   48: goto -> 63
/*     */       //   51: aload #4
/*     */       //   53: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   56: ifeq -> 63
/*     */       //   59: iconst_1
/*     */       //   60: goto -> 64
/*     */       //   63: iconst_0
/*     */       //   64: ifeq -> 71
/*     */       //   67: iconst_1
/*     */       //   68: goto -> 72
/*     */       //   71: iconst_0
/*     */       //   72: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #440	-> 0
/*     */       //   #236	-> 12
/*     */       //   #440	-> 19
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	73	0	this	Lscala/util/Either$RightProjection;
/*     */       //   0	73	1	x$1	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public RightProjection(Either<A, B> e) {
/* 440 */       Product.class.$init$(this);
/*     */     }
/*     */     
/*     */     public B get() {
/* 453 */       Either<A, B> either = e();
/* 454 */       if (either instanceof Left)
/* 454 */         throw new NoSuchElementException("Either.right.value on Left"); 
/* 455 */       if (either instanceof Right)
/* 455 */         Right right = (Right)either; 
/*     */       throw new MatchError(either);
/*     */     }
/*     */     
/*     */     public <U> Object foreach(Function1 f) {
/*     */       Object object;
/* 467 */       Either<A, B> either = e();
/* 468 */       if (either instanceof Left) {
/* 468 */         object = BoxedUnit.UNIT;
/*     */       } else {
/* 469 */         if (either instanceof Right) {
/* 469 */           Right right = (Right)either;
/* 469 */           object = f.apply(right.b());
/*     */         } 
/*     */         throw new MatchError(either);
/*     */       } 
/*     */       return object;
/*     */     }
/*     */     
/*     */     public <BB> BB getOrElse(Function0 or) {
/*     */       Object object;
/* 481 */       Either<A, B> either = e();
/* 482 */       if (either instanceof Left) {
/* 482 */         object = or.apply();
/*     */       } else {
/* 483 */         if (either instanceof Right) {
/* 483 */           Right right = (Right)either;
/* 483 */           object = right.b();
/*     */         } 
/*     */         throw new MatchError(either);
/*     */       } 
/*     */       return (BB)object;
/*     */     }
/*     */     
/*     */     public boolean forall(Function1 f) {
/*     */       boolean bool;
/* 496 */       Either<A, B> either = e();
/* 497 */       if (either instanceof Left) {
/* 497 */         bool = true;
/*     */       } else {
/* 498 */         if (either instanceof Right) {
/* 498 */           Right right = (Right)either;
/* 498 */           bool = BoxesRunTime.unboxToBoolean(f.apply(right.b()));
/*     */         } 
/*     */         throw new MatchError(either);
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */     
/*     */     public boolean exists(Function1 f) {
/*     */       boolean bool;
/* 511 */       Either<A, B> either = e();
/* 512 */       if (either instanceof Left) {
/* 512 */         bool = false;
/*     */       } else {
/* 513 */         if (either instanceof Right) {
/* 513 */           Right right = (Right)either;
/* 513 */           bool = BoxesRunTime.unboxToBoolean(f.apply(right.b()));
/*     */         } 
/*     */         throw new MatchError(either);
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */     
/*     */     public <AA, Y> Either<AA, Y> flatMap(Function1 f) {
/* 521 */       Either<A, B> either = e();
/* 522 */       if (either instanceof Left) {
/* 522 */         Left left = (Left)either;
/* 522 */         Left<Object, Object> left1 = new Left<Object, Object>(left.a());
/*     */       } 
/* 523 */       if (either instanceof Right) {
/* 523 */         Right right = (Right)either;
/* 523 */         Either either1 = (Either)f.apply(right.b());
/*     */       } 
/*     */       throw new MatchError(either);
/*     */     }
/*     */     
/*     */     public <Y> Serializable map(Function1 f) {
/* 534 */       Either<A, B> either = e();
/* 535 */       if (either instanceof Left) {
/* 535 */         Left left = (Left)either;
/* 535 */         Left<Object, Object> left1 = new Left<Object, Object>(left.a());
/*     */       } 
/* 536 */       if (either instanceof Right) {
/* 536 */         Right right1 = (Right)either;
/* 536 */         Right<Object, Object> right = new Right<Object, Object>(f.apply(right1.b()));
/*     */       } 
/*     */       throw new MatchError(either);
/*     */     }
/*     */     
/*     */     public <X> Option<Either<X, B>> filter(Function1 p) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: invokevirtual e : ()Lscala/util/Either;
/*     */       //   4: astore_2
/*     */       //   5: aload_2
/*     */       //   6: instanceof scala/util/Left
/*     */       //   9: ifeq -> 19
/*     */       //   12: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */       //   15: astore_3
/*     */       //   16: goto -> 75
/*     */       //   19: aload_2
/*     */       //   20: instanceof scala/util/Right
/*     */       //   23: ifeq -> 77
/*     */       //   26: aload_2
/*     */       //   27: checkcast scala/util/Right
/*     */       //   30: astore #4
/*     */       //   32: aload_1
/*     */       //   33: aload #4
/*     */       //   35: invokevirtual b : ()Ljava/lang/Object;
/*     */       //   38: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */       //   43: invokestatic unboxToBoolean : (Ljava/lang/Object;)Z
/*     */       //   46: ifeq -> 71
/*     */       //   49: new scala/Some
/*     */       //   52: dup
/*     */       //   53: new scala/util/Right
/*     */       //   56: dup
/*     */       //   57: aload #4
/*     */       //   59: invokevirtual b : ()Ljava/lang/Object;
/*     */       //   62: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   65: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   68: goto -> 74
/*     */       //   71: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */       //   74: astore_3
/*     */       //   75: aload_3
/*     */       //   76: areturn
/*     */       //   77: new scala/MatchError
/*     */       //   80: dup
/*     */       //   81: aload_2
/*     */       //   82: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   85: athrow
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #549	-> 0
/*     */       //   #550	-> 5
/*     */       //   #551	-> 19
/*     */       //   #549	-> 33
/*     */       //   #551	-> 35
/*     */       //   #549	-> 57
/*     */       //   #551	-> 59
/*     */       //   #549	-> 75
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	86	0	this	Lscala/util/Either$RightProjection;
/*     */       //   0	86	1	p	Lscala/Function1;
/*     */     }
/*     */     
/*     */     public Seq<B> toSeq() {
/*     */       Seq<B> seq;
/* 562 */       Either<A, B> either = e();
/* 563 */       if (either instanceof Left) {
/* 563 */         seq = (Seq)scala.collection.Seq$.MODULE$.empty();
/*     */       } else {
/* 564 */         if (either instanceof Right) {
/* 564 */           Right right = (Right)either;
/* 564 */           seq = (Seq)scala.collection.Seq$.MODULE$.apply((Seq)scala.Predef$.MODULE$.genericWrapArray(new Object[] { right.b() }));
/*     */         } 
/*     */         throw new MatchError(either);
/*     */       } 
/*     */       return seq;
/*     */     }
/*     */     
/*     */     public Option<B> toOption() {
/*     */       Some some;
/* 575 */       Either<A, B> either = e();
/* 576 */       if (either instanceof Left) {
/* 576 */         scala.None$ none$ = scala.None$.MODULE$;
/*     */       } else {
/* 577 */         if (either instanceof Right) {
/* 577 */           Right right = (Right)either;
/* 577 */           some = new Some(right.b());
/*     */         } 
/*     */         throw new MatchError(either);
/*     */       } 
/*     */       return (Option<B>)some;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class RightProjection$ implements Serializable {
/*     */     public static final RightProjection$ MODULE$;
/*     */     
/*     */     public final String toString() {
/*     */       return "RightProjection";
/*     */     }
/*     */     
/*     */     public <A, B> Either.RightProjection<A, B> apply(Either<A, B> e) {
/*     */       return new Either.RightProjection<A, B>(e);
/*     */     }
/*     */     
/*     */     public <A, B> Option<Either<A, B>> unapply(Either.RightProjection x$0) {
/*     */       return (x$0 == null) ? (Option<Either<A, B>>)scala.None$.MODULE$ : (Option<Either<A, B>>)new Some(x$0.e());
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*     */       return MODULE$;
/*     */     }
/*     */     
/*     */     public RightProjection$() {
/*     */       MODULE$ = this;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\Either.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */