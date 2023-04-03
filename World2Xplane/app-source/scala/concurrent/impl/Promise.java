/*     */ package scala.concurrent.impl;
/*     */ 
/*     */ import java.util.concurrent.TimeoutException;
/*     */ import java.util.concurrent.locks.AbstractQueuedSynchronizer;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.concurrent.Awaitable;
/*     */ import scala.concurrent.CanAwait;
/*     */ import scala.concurrent.ExecutionContext;
/*     */ import scala.concurrent.Future;
/*     */ import scala.concurrent.Promise;
/*     */ import scala.concurrent.duration.Duration;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.util.Try;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t=e\001C\001\003!\003\r\t\001\002\005\003\017A\023x.\\5tK*\0211\001B\001\005S6\004HN\003\002\006\r\005Q1m\0348dkJ\024XM\034;\013\003\035\tQa]2bY\006,\"!C\n\024\t\001Qa\"\b\t\003\0271i\021AB\005\003\033\031\021a!\0218z%\0264\007cA\b\021#5\tA!\003\002\002\tA\021!c\005\007\001\t\025!\002A1\001\027\005\005!6\001A\t\003/i\001\"a\003\r\n\005e1!a\002(pi\"Lgn\032\t\003\027mI!\001\b\004\003\007\005s\027\020E\002\020=EI!a\b\003\003\r\031+H/\036:f\021\025\t\003\001\"\001#\003\031!\023N\\5uIQ\t1\005\005\002\fI%\021QE\002\002\005+:LG\017C\003(\001\021\005\001&\001\004gkR,(/Z\013\002S5\t\001a\002\004,\005!\005A\001L\001\b!J|W.[:f!\tic&D\001\003\r\031\t!\001#\001\005_M\021aF\003\005\006c9\"\tAM\001\007y%t\027\016\036 \025\0031BQ\001\016\030\005\nU\n!B]3t_24X\r\026:z+\t1d\b\006\0028A\031\001hO\037\016\003eR!A\017\004\002\tU$\030\016\\\005\003ye\0221\001\026:z!\t\021b\bB\003\025g\t\007a\003C\003Ag\001\007q'\001\004t_V\0248-\032\005\006\005:\"IaQ\001\te\026\034x\016\034<feV\021Ai\022\013\003\013\"\0032\001O\036G!\t\021r\tB\003\025\003\n\007a\003C\003J\003\002\007!*A\005uQJ|w/\0312mKB\0211j\025\b\003\031Fs!!\024)\016\0039S!aT\013\002\rq\022xn\034;?\023\0059\021B\001*\007\003\035\001\030mY6bO\026L!\001V+\003\023QC'o\\<bE2,'B\001*\007\r\0219fF\002-\003\037\r{W\016\0357fi&|g\016T1uG\",\"!W5\024\007YSF\r\005\002\\E6\tAL\003\002^=\006)An\\2lg*\021Qa\030\006\003u\001T\021!Y\001\005U\0064\030-\003\002d9\nQ\022IY:ue\006\034G/U;fk\026$7+\0378dQJ|g.\033>feB!1\"Z4$\023\t1gAA\005Gk:\034G/[8ocA\031\001h\0175\021\005IIG!\002\013W\005\0041\002\"B\031W\t\003YG#\0017\021\00754\006.D\001/\021\025yg\013\"\025q\003A!(/_!dcVL'/Z*iCJ,G\r\006\002riB\0211B]\005\003g\032\0211!\0238u\021\025)h\0161\001r\003\035IwM\\8sK\022DQa\036,\005Ra\f\001\003\036:z%\026dW-Y:f'\"\f'/\0323\025\005ed\bCA\006{\023\tYhAA\004C_>dW-\0318\t\013u4\b\031A9\002\r%<gn\034:f\021\031yh\013\"\021\002\002\005)\021\r\0359msR\0311%a\001\t\013Ut\b\031A4\007\r\005\035a\006AA\005\0059!UMZ1vYR\004&o\\7jg\026,B!a\003\002\030M1\021QAA\007\003'\0012!LA\b\023\r\t\tB\001\002\020\003\n\034HO]1diB\023x.\\5tKB!Q\006AA\013!\r\021\022q\003\003\007)\005\025!\031\001\f\t\017E\n)\001\"\001\002\034Q\021\021Q\004\t\006[\006\025\021Q\003\005\t\003C\t)\001\"\003\002\034\005q1m\\7qe\026\0348/\0323S_>$\b\006BA\020\003K\001B!a\n\002.5\021\021\021\006\006\004\003W1\021AC1o]>$\030\r^5p]&!\021qFA\025\005\035!\030-\0337sK\016D\001\"a\r\002\006\021%\021QG\001\005e>|G/\006\002\002\036!\"\021\021GA\023\021!\tY$!\002\005\026\005u\022\001\003;ss\006;\030-\033;\025\007e\fy\004\003\005\002B\005e\002\031AA\"\003\031\tG/T8tiB!\021QIA&\033\t\t9EC\002\002J\021\t\001\002Z;sCRLwN\\\005\005\003\033\n9E\001\005EkJ\fG/[8o\021!\t\t&!\002\005\002\005M\023!\002:fC\022LH\003BA+\003G\"B!a\026\002Z5\021\021Q\001\005\t\0037\ny\005q\001\002^\0051\001/\032:nSR\0042aDA0\023\r\t\t\007\002\002\t\007\006t\027i^1ji\"A\021\021IA(\001\004\t\031\005\013\004\002P\005\035\024q\017\t\006\027\005%\024QN\005\004\003W2!A\002;ie><8\017E\002\023\003_\"a\001\006\001C\002\005E\024cA\f\002tA\031\021QO*\017\005-\t6EAA=!\rY\0251P\005\004\003{*&\001F%oi\026\024(/\0369uK\022,\005pY3qi&|g\016\013\004\002P\005\005\025q\021\t\006\027\005%\0241\021\t\004%\005\025EA\002\013\001\005\004\t\th\t\002\002\nB!\0211RAJ\035\021\ti)!%\017\0071\013y)\003\002\006\r%\021!\013B\005\005\003+\0139J\001\tUS6,w.\036;Fq\016,\007\017^5p]*\021!\013\002\005\t\0037\013)\001\"\001\002\036\0061!/Z:vYR$B!a(\002$R!\021QCAQ\021!\tY&!'A\004\005u\003\002CA!\0033\003\r!a\021)\r\005e\025qUAW!\025Y\021\021NAU!\r\021\0221\026\003\007)\001\021\r!!\035$\005\005=\006cA&\0022&\031\0211W+\003\023\025C8-\0329uS>t\007\002CA\\\003\013!\t!!/\002\013Y\fG.^3\026\005\005m\006#B\006\002>\006\005\027bAA`\r\t1q\n\035;j_:\004B\001O\036\002\026!A\021QYA\003\t\023\tI,\001\004wC2,X\r\r\025\005\003\007\f)\003\003\005\002L\006\025A\021IAg\003-I7oQ8na2,G/\0323\026\003eD\001\"!5\002\006\021%\021QZ\001\rSN\034u.\0349mKR,G\r\r\025\005\003\037\f)\003\003\005\002X\006\025A\021AAm\003-!(/_\"p[BdW\r^3\025\007e\fY\016\003\005\0028\006U\007\031AAa\021!\ty.!\002\005\n\005\005\030A\007;ss\016{W\016\0357fi\026\fe\016Z$fi2K7\017^3oKJ\034H\003BAr\003_\004RaSAs\003SL1!a:V\005\021a\025n\035;\021\0135\nY/!\006\n\007\0055(A\001\tDC2d'-Y2l%Vtg.\0312mK\"A\021\021_Ao\001\004\t\t-A\001wQ\021\ti.!\n\t\021\005]\030Q\001C\001\003s\f!b\0348D_6\004H.\032;f+\021\tYP!\005\025\t\005u(\021\002\013\004G\005}\b\002\003B\001\003k\004\035Aa\001\002\021\025DXmY;u_J\0042a\004B\003\023\r\0219\001\002\002\021\013b,7-\036;j_:\034uN\034;fqRD\001Ba\003\002v\002\007!QB\001\005MVt7\r\005\004\fK\006\005'q\002\t\004%\tEAa\002B\n\003k\024\rA\006\002\002+\"A!qCA\003\t\023\021I\"A\013eSN\004\030\r^2i\037J\fE\rZ\"bY2\024\027mY6\025\007\r\022Y\002\003\005\003\036\tU\001\031AAu\003!\021XO\0348bE2,\007\006\002B\013\003KA\021Ba\t\002\006\021UAA!\n\002\0251Lgn\033*p_R|e\rF\002$\005OA\001B!\013\003\"\001\007\021QD\001\007i\006\024x-\032;\t\021\t5\022Q\001C\005\005_\tA\001\\5oWR\0311E!\r\t\021\t%\"1\006a\001\003;ACAa\013\002&\0311!q\007\030\003\005s\0211bS3qiB\023x.\\5tKV!!1\bB!'\025\021)D\003B\037!\021i\003Aa\020\021\007I\021\t\005\002\004\025\005k\021\rA\006\005\f\005\013\022)D!A!\002\023\0219%A\007tkB\004H.[3e-\006dW/\032\t\005qm\022y\004C\0042\005k!\tAa\023\025\t\t5#q\n\t\006[\nU\"q\b\005\t\005\013\022I\0051\001\003H!Q\021q\027B\033\005\004%\tAa\025\026\005\tU\003#B\006\003X\t\035\023b\001B-\r\t!1k\\7f\021%\021iF!\016!\002\023\021)&\001\004wC2,X\r\t\005\t\003\027\024)\004\"\021\002N\"A\021q\033B\033\t\003\021\031\007F\002z\005KB\001\"a.\003b\001\007!q\t\005\t\003o\024)\004\"\001\003jU!!1\016B<)\021\021iG!\035\025\007\r\022y\007\003\005\003\002\t\035\0049\001B\002\021!\021YAa\032A\002\tM\004CB\006f\005\017\022)\bE\002\023\005o\"qAa\005\003h\t\007a\003\003\005\002R\tUB\021\001B>)\021\021iHa!\025\t\t}$\021Q\007\003\005kA\001\"a\027\003z\001\017\021Q\f\005\t\003\003\022I\b1\001\002D!A\0211\024B\033\t\003\0219\t\006\003\003\n\n5E\003\002B \005\027C\001\"a\027\003\006\002\017\021Q\f\005\t\003\003\022)\t1\001\002D\001")
/*     */ public interface Promise<T> extends Promise<T>, Future<T> {
/*     */   Promise<T> future();
/*     */   
/*     */   public static class CompletionLatch<T> extends AbstractQueuedSynchronizer implements Function1<Try<T>, BoxedUnit> {
/*     */     public boolean apply$mcZD$sp(double v1) {
/*  67 */       return Function1.class.apply$mcZD$sp(this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDD$sp(double v1) {
/*  67 */       return Function1.class.apply$mcDD$sp(this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFD$sp(double v1) {
/*  67 */       return Function1.class.apply$mcFD$sp(this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcID$sp(double v1) {
/*  67 */       return Function1.class.apply$mcID$sp(this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJD$sp(double v1) {
/*  67 */       return Function1.class.apply$mcJD$sp(this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVD$sp(double v1) {
/*  67 */       Function1.class.apply$mcVD$sp(this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZF$sp(float v1) {
/*  67 */       return Function1.class.apply$mcZF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDF$sp(float v1) {
/*  67 */       return Function1.class.apply$mcDF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFF$sp(float v1) {
/*  67 */       return Function1.class.apply$mcFF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcIF$sp(float v1) {
/*  67 */       return Function1.class.apply$mcIF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJF$sp(float v1) {
/*  67 */       return Function1.class.apply$mcJF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVF$sp(float v1) {
/*  67 */       Function1.class.apply$mcVF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZI$sp(int v1) {
/*  67 */       return Function1.class.apply$mcZI$sp(this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDI$sp(int v1) {
/*  67 */       return Function1.class.apply$mcDI$sp(this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFI$sp(int v1) {
/*  67 */       return Function1.class.apply$mcFI$sp(this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcII$sp(int v1) {
/*  67 */       return Function1.class.apply$mcII$sp(this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJI$sp(int v1) {
/*  67 */       return Function1.class.apply$mcJI$sp(this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVI$sp(int v1) {
/*  67 */       Function1.class.apply$mcVI$sp(this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZJ$sp(long v1) {
/*  67 */       return Function1.class.apply$mcZJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDJ$sp(long v1) {
/*  67 */       return Function1.class.apply$mcDJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFJ$sp(long v1) {
/*  67 */       return Function1.class.apply$mcFJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcIJ$sp(long v1) {
/*  67 */       return Function1.class.apply$mcIJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJJ$sp(long v1) {
/*  67 */       return Function1.class.apply$mcJJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVJ$sp(long v1) {
/*  67 */       Function1.class.apply$mcVJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose(Function1 g) {
/*  67 */       return Function1.class.compose(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/*  67 */       return Function1.class.compose$mcZD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/*  67 */       return Function1.class.compose$mcDD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/*  67 */       return Function1.class.compose$mcFD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/*  67 */       return Function1.class.compose$mcID$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/*  67 */       return Function1.class.compose$mcJD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/*  67 */       return Function1.class.compose$mcVD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/*  67 */       return Function1.class.compose$mcZF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/*  67 */       return Function1.class.compose$mcDF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/*  67 */       return Function1.class.compose$mcFF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/*  67 */       return Function1.class.compose$mcIF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/*  67 */       return Function1.class.compose$mcJF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/*  67 */       return Function1.class.compose$mcVF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/*  67 */       return Function1.class.compose$mcZI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/*  67 */       return Function1.class.compose$mcDI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/*  67 */       return Function1.class.compose$mcFI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/*  67 */       return Function1.class.compose$mcII$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/*  67 */       return Function1.class.compose$mcJI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/*  67 */       return Function1.class.compose$mcVI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/*  67 */       return Function1.class.compose$mcZJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/*  67 */       return Function1.class.compose$mcDJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/*  67 */       return Function1.class.compose$mcFJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/*  67 */       return Function1.class.compose$mcIJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/*  67 */       return Function1.class.compose$mcJJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/*  67 */       return Function1.class.compose$mcVJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Try<T>, A> andThen(Function1 g) {
/*  67 */       return Function1.class.andThen(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/*  67 */       return Function1.class.andThen$mcZD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/*  67 */       return Function1.class.andThen$mcDD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/*  67 */       return Function1.class.andThen$mcFD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/*  67 */       return Function1.class.andThen$mcID$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/*  67 */       return Function1.class.andThen$mcJD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/*  67 */       return Function1.class.andThen$mcVD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/*  67 */       return Function1.class.andThen$mcZF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/*  67 */       return Function1.class.andThen$mcDF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/*  67 */       return Function1.class.andThen$mcFF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/*  67 */       return Function1.class.andThen$mcIF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/*  67 */       return Function1.class.andThen$mcJF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/*  67 */       return Function1.class.andThen$mcVF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/*  67 */       return Function1.class.andThen$mcZI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/*  67 */       return Function1.class.andThen$mcDI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/*  67 */       return Function1.class.andThen$mcFI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/*  67 */       return Function1.class.andThen$mcII$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/*  67 */       return Function1.class.andThen$mcJI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/*  67 */       return Function1.class.andThen$mcVI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/*  67 */       return Function1.class.andThen$mcZJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/*  67 */       return Function1.class.andThen$mcDJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/*  67 */       return Function1.class.andThen$mcFJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/*  67 */       return Function1.class.andThen$mcIJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/*  67 */       return Function1.class.andThen$mcJJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/*  67 */       return Function1.class.andThen$mcVJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public String toString() {
/*  67 */       return Function1.class.toString(this);
/*     */     }
/*     */     
/*     */     public CompletionLatch() {
/*  67 */       Function1.class.$init$(this);
/*     */     }
/*     */     
/*     */     public int tryAcquireShared(int ignored) {
/*  68 */       return (getState() != 0) ? 1 : -1;
/*     */     }
/*     */     
/*     */     public boolean tryReleaseShared(int ignore) {
/*  70 */       setState(1);
/*  71 */       return true;
/*     */     }
/*     */     
/*     */     public void apply(Try ignored) {
/*  73 */       releaseShared(1);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class DefaultPromise<T> extends AbstractPromise implements Promise<T> {
/*     */     public Promise<T> future() {
/* 153 */       return Promise$class.future(this);
/*     */     }
/*     */     
/*     */     public <U> void onSuccess(PartialFunction pf, ExecutionContext executor) {
/* 153 */       Future.class.onSuccess(this, pf, executor);
/*     */     }
/*     */     
/*     */     public <U> void onFailure(PartialFunction callback, ExecutionContext executor) {
/* 153 */       Future.class.onFailure(this, callback, executor);
/*     */     }
/*     */     
/*     */     public Future<Throwable> failed() {
/* 153 */       return Future.class.failed(this);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f, ExecutionContext executor) {
/* 153 */       Future.class.foreach(this, f, executor);
/*     */     }
/*     */     
/*     */     public <S> Future<S> transform(Function1 s, Function1 f, ExecutionContext executor) {
/* 153 */       return Future.class.transform(this, s, f, executor);
/*     */     }
/*     */     
/*     */     public <S> Future<S> map(Function1 f, ExecutionContext executor) {
/* 153 */       return Future.class.map(this, f, executor);
/*     */     }
/*     */     
/*     */     public <S> Future<S> flatMap(Function1 f, ExecutionContext executor) {
/* 153 */       return Future.class.flatMap(this, f, executor);
/*     */     }
/*     */     
/*     */     public Future<T> filter(Function1 pred, ExecutionContext executor) {
/* 153 */       return Future.class.filter(this, pred, executor);
/*     */     }
/*     */     
/*     */     public final Future<T> withFilter(Function1 p, ExecutionContext executor) {
/* 153 */       return Future.class.withFilter(this, p, executor);
/*     */     }
/*     */     
/*     */     public <S> Future<S> collect(PartialFunction pf, ExecutionContext executor) {
/* 153 */       return Future.class.collect(this, pf, executor);
/*     */     }
/*     */     
/*     */     public <U> Future<U> recover(PartialFunction pf, ExecutionContext executor) {
/* 153 */       return Future.class.recover(this, pf, executor);
/*     */     }
/*     */     
/*     */     public <U> Future<U> recoverWith(PartialFunction pf, ExecutionContext executor) {
/* 153 */       return Future.class.recoverWith(this, pf, executor);
/*     */     }
/*     */     
/*     */     public <U> Future<Tuple2<T, U>> zip(Future that) {
/* 153 */       return Future.class.zip(this, that);
/*     */     }
/*     */     
/*     */     public <U> Future<U> fallbackTo(Future that) {
/* 153 */       return Future.class.fallbackTo(this, that);
/*     */     }
/*     */     
/*     */     public <S> Future<S> mapTo(ClassTag tag) {
/* 153 */       return Future.class.mapTo(this, tag);
/*     */     }
/*     */     
/*     */     public <U> Future<T> andThen(PartialFunction pf, ExecutionContext executor) {
/* 153 */       return Future.class.andThen(this, pf, executor);
/*     */     }
/*     */     
/*     */     public Promise<T> complete(Try result) {
/* 153 */       return Promise.class.complete(this, result);
/*     */     }
/*     */     
/*     */     public final Promise<T> completeWith(Future other) {
/* 153 */       return Promise.class.completeWith(this, other);
/*     */     }
/*     */     
/*     */     public final Promise<T> tryCompleteWith(Future other) {
/* 153 */       return Promise.class.tryCompleteWith(this, other);
/*     */     }
/*     */     
/*     */     public Promise<T> success(Object v) {
/* 153 */       return Promise.class.success(this, v);
/*     */     }
/*     */     
/*     */     public boolean trySuccess(Object value) {
/* 153 */       return Promise.class.trySuccess(this, value);
/*     */     }
/*     */     
/*     */     public Promise<T> failure(Throwable t) {
/* 153 */       return Promise.class.failure(this, t);
/*     */     }
/*     */     
/*     */     public boolean tryFailure(Throwable t) {
/* 153 */       return Promise.class.tryFailure(this, t);
/*     */     }
/*     */     
/*     */     public DefaultPromise() {
/* 153 */       Promise.class.$init$(this);
/* 153 */       Future.class.$init$(this);
/* 153 */       Promise$class.$init$(this);
/* 154 */       updateState(null, Nil$.MODULE$);
/*     */     }
/*     */     
/*     */     private DefaultPromise<T> compressedRoot() {
/*     */       DefaultPromise<T> defaultPromise;
/*     */       while (true) {
/* 172 */         Object object = getState();
/* 173 */         if (object instanceof DefaultPromise) {
/* 173 */           DefaultPromise defaultPromise1 = (DefaultPromise)object;
/* 174 */           DefaultPromise target = defaultPromise1.root();
/* 175 */           if (updateState(defaultPromise1, target)) {
/*     */           
/*     */           } else {
/*     */             continue;
/*     */           } 
/* 175 */           DefaultPromise defaultPromise2 = (defaultPromise1 == target) ? target : target;
/*     */           break;
/*     */         } 
/* 176 */         defaultPromise = this;
/*     */         break;
/*     */       } 
/*     */       return defaultPromise;
/*     */     }
/*     */     
/*     */     private DefaultPromise<T> root() {
/*     */       while (true) {
/* 186 */         Object object = getState();
/* 187 */         if (object instanceof DefaultPromise) {
/* 187 */           DefaultPromise defaultPromise = (DefaultPromise)object;
/*     */           continue;
/*     */         } 
/*     */         return this;
/*     */       } 
/*     */     }
/*     */     
/*     */     public final boolean tryAwait(Duration atMost) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: invokevirtual isCompleted : ()Z
/*     */       //   4: ifeq -> 11
/*     */       //   7: iconst_1
/*     */       //   8: goto -> 163
/*     */       //   11: aload_1
/*     */       //   12: getstatic scala/concurrent/duration/Duration$.MODULE$ : Lscala/concurrent/duration/Duration$;
/*     */       //   15: invokevirtual Undefined : ()Lscala/concurrent/duration/Duration$Infinite;
/*     */       //   18: if_acmpne -> 31
/*     */       //   21: new java/lang/IllegalArgumentException
/*     */       //   24: dup
/*     */       //   25: ldc 'cannot wait for Undefined period'
/*     */       //   27: invokespecial <init> : (Ljava/lang/String;)V
/*     */       //   30: athrow
/*     */       //   31: getstatic scala/concurrent/duration/Duration$.MODULE$ : Lscala/concurrent/duration/Duration$;
/*     */       //   34: invokevirtual Inf : ()Lscala/concurrent/duration/Duration$Infinite;
/*     */       //   37: dup
/*     */       //   38: ifnonnull -> 49
/*     */       //   41: pop
/*     */       //   42: aload_1
/*     */       //   43: ifnull -> 56
/*     */       //   46: goto -> 80
/*     */       //   49: aload_1
/*     */       //   50: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   53: ifeq -> 80
/*     */       //   56: new scala/concurrent/impl/Promise$CompletionLatch
/*     */       //   59: dup
/*     */       //   60: invokespecial <init> : ()V
/*     */       //   63: astore_2
/*     */       //   64: aload_0
/*     */       //   65: aload_2
/*     */       //   66: getstatic scala/concurrent/Future$InternalCallbackExecutor$.MODULE$ : Lscala/concurrent/Future$InternalCallbackExecutor$;
/*     */       //   69: invokevirtual onComplete : (Lscala/Function1;Lscala/concurrent/ExecutionContext;)V
/*     */       //   72: aload_2
/*     */       //   73: iconst_1
/*     */       //   74: invokevirtual acquireSharedInterruptibly : (I)V
/*     */       //   77: goto -> 159
/*     */       //   80: getstatic scala/concurrent/duration/Duration$.MODULE$ : Lscala/concurrent/duration/Duration$;
/*     */       //   83: invokevirtual MinusInf : ()Lscala/concurrent/duration/Duration$Infinite;
/*     */       //   86: dup
/*     */       //   87: ifnonnull -> 98
/*     */       //   90: pop
/*     */       //   91: aload_1
/*     */       //   92: ifnull -> 159
/*     */       //   95: goto -> 105
/*     */       //   98: aload_1
/*     */       //   99: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   102: ifne -> 159
/*     */       //   105: aload_1
/*     */       //   106: instanceof scala/concurrent/duration/FiniteDuration
/*     */       //   109: ifeq -> 164
/*     */       //   112: aload_1
/*     */       //   113: checkcast scala/concurrent/duration/FiniteDuration
/*     */       //   116: astore #4
/*     */       //   118: aload #4
/*     */       //   120: getstatic scala/concurrent/duration/Duration$.MODULE$ : Lscala/concurrent/duration/Duration$;
/*     */       //   123: invokevirtual Zero : ()Lscala/concurrent/duration/FiniteDuration;
/*     */       //   126: invokevirtual $greater : (Ljava/lang/Object;)Z
/*     */       //   129: ifeq -> 159
/*     */       //   132: new scala/concurrent/impl/Promise$CompletionLatch
/*     */       //   135: dup
/*     */       //   136: invokespecial <init> : ()V
/*     */       //   139: astore_3
/*     */       //   140: aload_0
/*     */       //   141: aload_3
/*     */       //   142: getstatic scala/concurrent/Future$InternalCallbackExecutor$.MODULE$ : Lscala/concurrent/Future$InternalCallbackExecutor$;
/*     */       //   145: invokevirtual onComplete : (Lscala/Function1;Lscala/concurrent/ExecutionContext;)V
/*     */       //   148: aload_3
/*     */       //   149: iconst_1
/*     */       //   150: aload #4
/*     */       //   152: invokevirtual toNanos : ()J
/*     */       //   155: invokevirtual tryAcquireSharedNanos : (IJ)Z
/*     */       //   158: pop
/*     */       //   159: aload_0
/*     */       //   160: invokevirtual isCompleted : ()Z
/*     */       //   163: ireturn
/*     */       //   164: new scala/MatchError
/*     */       //   167: dup
/*     */       //   168: aload_1
/*     */       //   169: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   172: athrow
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #194	-> 0
/*     */       //   #213	-> 7
/*     */       //   #198	-> 11
/*     */       //   #197	-> 11
/*     */       //   #199	-> 31
/*     */       //   #200	-> 56
/*     */       //   #201	-> 64
/*     */       //   #202	-> 72
/*     */       //   #199	-> 77
/*     */       //   #203	-> 80
/*     */       //   #204	-> 105
/*     */       //   #205	-> 118
/*     */       //   #206	-> 132
/*     */       //   #207	-> 140
/*     */       //   #208	-> 148
/*     */       //   #212	-> 159
/*     */       //   #197	-> 159
/*     */       //   #194	-> 163
/*     */       //   #197	-> 164
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	173	0	this	Lscala/concurrent/impl/Promise$DefaultPromise;
/*     */       //   0	173	1	atMost	Lscala/concurrent/duration/Duration;
/*     */       //   64	13	2	l	Lscala/concurrent/impl/Promise$CompletionLatch;
/*     */       //   140	19	3	l	Lscala/concurrent/impl/Promise$CompletionLatch;
/*     */     }
/*     */     
/*     */     public DefaultPromise<T> ready(Duration atMost, CanAwait permit) throws TimeoutException, InterruptedException {
/* 218 */       if (tryAwait(atMost))
/* 218 */         return this; 
/* 219 */       throw new TimeoutException((new StringBuilder()).append("Futures timed out after [").append(atMost).append("]").toString());
/*     */     }
/*     */     
/*     */     public T result(Duration atMost, CanAwait permit) throws Exception {
/* 223 */       return (T)((Try)ready(atMost, permit).value().get()).get();
/*     */     }
/*     */     
/*     */     public Option<Try<T>> value() {
/* 225 */       return value0();
/*     */     }
/*     */     
/*     */     private Option<Try<T>> value0() {
/*     */       None$ none$;
/*     */       while (true) {
/* 228 */         Object object = getState();
/* 229 */         if (object instanceof Try) {
/* 229 */           Try try_ = (Try)object;
/* 229 */           Some some = new Some(try_);
/*     */           break;
/*     */         } 
/* 230 */         if (object instanceof DefaultPromise) {
/* 230 */           this = compressedRoot();
/*     */           continue;
/*     */         } 
/* 231 */         none$ = None$.MODULE$;
/*     */         break;
/*     */       } 
/*     */       return (Option<Try<T>>)none$;
/*     */     }
/*     */     
/*     */     public boolean isCompleted() {
/* 234 */       return isCompleted0();
/*     */     }
/*     */     
/*     */     private boolean isCompleted0() {
/*     */       boolean bool;
/*     */       while (true) {
/* 237 */         Object object = getState();
/* 238 */         if (object instanceof Try) {
/* 238 */           boolean bool1 = true;
/*     */           break;
/*     */         } 
/* 239 */         if (object instanceof DefaultPromise) {
/* 239 */           this = compressedRoot();
/*     */           continue;
/*     */         } 
/* 240 */         bool = false;
/*     */         break;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */     
/*     */     public boolean tryComplete(Try<?> value) {
/* 244 */       Try<?> resolved = Promise$.MODULE$.scala$concurrent$impl$Promise$$resolveTry(value);
/* 245 */       List<CallbackRunnable<T>> list = tryCompleteAndGetListeners((Try)resolved);
/* 246 */       if (list == null) {
/* 246 */         boolean bool = false;
/* 247 */       } else if (list.isEmpty()) {
/* 247 */         boolean bool = true;
/*     */       } else {
/* 248 */         List<CallbackRunnable<T>> these1 = list;
/*     */         while (true) {
/* 248 */           if (these1.isEmpty())
/*     */             return true; 
/* 248 */           ((CallbackRunnable)these1.head()).executeWithValue(resolved);
/* 248 */           these1 = (List<CallbackRunnable<T>>)these1.tail();
/*     */         } 
/*     */       } 
/*     */       return SYNTHETIC_LOCAL_VARIABLE_3;
/*     */     }
/*     */     
/*     */     private List<CallbackRunnable<T>> tryCompleteAndGetListeners(Try v) {
/*     */       List list;
/*     */       while (true) {
/* 257 */         Object object = getState();
/* 258 */         if (object instanceof List) {
/* 258 */           List list1 = (List)object;
/* 259 */           if (updateState(list1, v))
/* 259 */             List list2 = list1; 
/*     */           continue;
/*     */         } 
/* 261 */         if (object instanceof DefaultPromise) {
/* 262 */           this = compressedRoot();
/*     */           continue;
/*     */         } 
/* 263 */         list = null;
/*     */         break;
/*     */       } 
/*     */       return list;
/*     */     }
/*     */     
/*     */     public <U> void onComplete(Function1<Try<?>, Object> func, ExecutionContext executor) {
/* 268 */       ExecutionContext preparedEC = executor.prepare();
/* 269 */       CallbackRunnable<T> runnable = new CallbackRunnable(preparedEC, func);
/* 270 */       scala$concurrent$impl$Promise$DefaultPromise$$dispatchOrAddCallback(runnable);
/*     */     }
/*     */     
/*     */     public void scala$concurrent$impl$Promise$DefaultPromise$$dispatchOrAddCallback(CallbackRunnable runnable) {
/*     */       while (true) {
/* 279 */         Object object = getState();
/* 280 */         if (object instanceof Try) {
/* 280 */           Try try_ = (Try)object;
/* 280 */           runnable.executeWithValue(try_);
/*     */         } else {
/* 281 */           if (object instanceof DefaultPromise) {
/* 281 */             this = compressedRoot();
/*     */             continue;
/*     */           } 
/* 282 */           if (object instanceof List) {
/* 282 */             List list = (List)object;
/* 282 */             if (updateState(list, list.$colon$colon(runnable)))
/*     */               continue; 
/*     */             continue;
/*     */           } 
/*     */           throw new MatchError(object);
/*     */         } 
/*     */         return;
/*     */       } 
/*     */     }
/*     */     
/*     */     public final void linkRootOf(DefaultPromise<T> target) {
/* 289 */       link(target.compressedRoot());
/*     */     }
/*     */     
/*     */     private void link(DefaultPromise target) {
/* 301 */       while (this != target) {
/* 302 */         Object object = getState();
/* 303 */         if (object instanceof Try) {
/* 303 */           Try try_ = (Try)object;
/* 304 */           if (!target.tryComplete(try_))
/* 307 */             throw new IllegalStateException("Cannot link completed promises together"); 
/*     */           break;
/*     */         } 
/* 309 */         if (object instanceof DefaultPromise) {
/* 310 */           this = compressedRoot();
/*     */           continue;
/*     */         } 
/* 311 */         if (object instanceof List) {
/* 311 */           List list = (List)object;
/* 311 */           if (updateState(list, target)) {
/* 312 */             if (!list.isEmpty())
/* 312 */               for (List these1 = list; !these1.isEmpty(); ) {
/* 312 */                 CallbackRunnable callbackRunnable = (CallbackRunnable)these1.head();
/* 312 */                 target.scala$concurrent$impl$Promise$DefaultPromise$$dispatchOrAddCallback(callbackRunnable);
/* 312 */                 these1 = (List)these1.tail();
/*     */               }  
/*     */             break;
/*     */           } 
/*     */           continue;
/*     */         } 
/*     */         throw new MatchError(object);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static class KeptPromise<T> implements Promise<T> {
/*     */     private final Some<Try<T>> value;
/*     */     
/*     */     public Promise<T> future() {
/* 322 */       return Promise$class.future(this);
/*     */     }
/*     */     
/*     */     public <U> void onSuccess(PartialFunction pf, ExecutionContext executor) {
/* 322 */       Future.class.onSuccess(this, pf, executor);
/*     */     }
/*     */     
/*     */     public <U> void onFailure(PartialFunction callback, ExecutionContext executor) {
/* 322 */       Future.class.onFailure(this, callback, executor);
/*     */     }
/*     */     
/*     */     public Future<Throwable> failed() {
/* 322 */       return Future.class.failed(this);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f, ExecutionContext executor) {
/* 322 */       Future.class.foreach(this, f, executor);
/*     */     }
/*     */     
/*     */     public <S> Future<S> transform(Function1 s, Function1 f, ExecutionContext executor) {
/* 322 */       return Future.class.transform(this, s, f, executor);
/*     */     }
/*     */     
/*     */     public <S> Future<S> map(Function1 f, ExecutionContext executor) {
/* 322 */       return Future.class.map(this, f, executor);
/*     */     }
/*     */     
/*     */     public <S> Future<S> flatMap(Function1 f, ExecutionContext executor) {
/* 322 */       return Future.class.flatMap(this, f, executor);
/*     */     }
/*     */     
/*     */     public Future<T> filter(Function1 pred, ExecutionContext executor) {
/* 322 */       return Future.class.filter(this, pred, executor);
/*     */     }
/*     */     
/*     */     public final Future<T> withFilter(Function1 p, ExecutionContext executor) {
/* 322 */       return Future.class.withFilter(this, p, executor);
/*     */     }
/*     */     
/*     */     public <S> Future<S> collect(PartialFunction pf, ExecutionContext executor) {
/* 322 */       return Future.class.collect(this, pf, executor);
/*     */     }
/*     */     
/*     */     public <U> Future<U> recover(PartialFunction pf, ExecutionContext executor) {
/* 322 */       return Future.class.recover(this, pf, executor);
/*     */     }
/*     */     
/*     */     public <U> Future<U> recoverWith(PartialFunction pf, ExecutionContext executor) {
/* 322 */       return Future.class.recoverWith(this, pf, executor);
/*     */     }
/*     */     
/*     */     public <U> Future<Tuple2<T, U>> zip(Future that) {
/* 322 */       return Future.class.zip(this, that);
/*     */     }
/*     */     
/*     */     public <U> Future<U> fallbackTo(Future that) {
/* 322 */       return Future.class.fallbackTo(this, that);
/*     */     }
/*     */     
/*     */     public <S> Future<S> mapTo(ClassTag tag) {
/* 322 */       return Future.class.mapTo(this, tag);
/*     */     }
/*     */     
/*     */     public <U> Future<T> andThen(PartialFunction pf, ExecutionContext executor) {
/* 322 */       return Future.class.andThen(this, pf, executor);
/*     */     }
/*     */     
/*     */     public Promise<T> complete(Try result) {
/* 322 */       return Promise.class.complete(this, result);
/*     */     }
/*     */     
/*     */     public final Promise<T> completeWith(Future other) {
/* 322 */       return Promise.class.completeWith(this, other);
/*     */     }
/*     */     
/*     */     public final Promise<T> tryCompleteWith(Future other) {
/* 322 */       return Promise.class.tryCompleteWith(this, other);
/*     */     }
/*     */     
/*     */     public Promise<T> success(Object v) {
/* 322 */       return Promise.class.success(this, v);
/*     */     }
/*     */     
/*     */     public boolean trySuccess(Object value) {
/* 322 */       return Promise.class.trySuccess(this, value);
/*     */     }
/*     */     
/*     */     public Promise<T> failure(Throwable t) {
/* 322 */       return Promise.class.failure(this, t);
/*     */     }
/*     */     
/*     */     public boolean tryFailure(Throwable t) {
/* 322 */       return Promise.class.tryFailure(this, t);
/*     */     }
/*     */     
/*     */     public KeptPromise(Try<?> suppliedValue) {
/* 322 */       Promise.class.$init$(this);
/* 322 */       Future.class.$init$(this);
/* 322 */       Promise$class.$init$(this);
/* 324 */       this.value = new Some(Promise$.MODULE$.scala$concurrent$impl$Promise$$resolveTry(suppliedValue));
/*     */     }
/*     */     
/*     */     public Some<Try<T>> value() {
/* 324 */       return this.value;
/*     */     }
/*     */     
/*     */     public boolean isCompleted() {
/* 326 */       return true;
/*     */     }
/*     */     
/*     */     public boolean tryComplete(Try value) {
/* 328 */       return false;
/*     */     }
/*     */     
/*     */     public <U> void onComplete(Function1<Try<?>, Object> func, ExecutionContext executor) {
/* 331 */       Try<?> completedAs = (Try)value().get();
/* 332 */       ExecutionContext preparedEC = executor.prepare();
/* 333 */       (new CallbackRunnable(preparedEC, func)).executeWithValue(completedAs);
/*     */     }
/*     */     
/*     */     public KeptPromise<T> ready(Duration atMost, CanAwait permit) {
/* 336 */       return this;
/*     */     }
/*     */     
/*     */     public T result(Duration atMost, CanAwait permit) {
/* 338 */       return (T)((Try)value().get()).get();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\impl\Promise.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */