/*     */ package scala.concurrent;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.concurrent.Executor;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.concurrent.impl.Promise;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.util.Failure;
/*     */ import scala.util.Success;
/*     */ import scala.util.Try;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\021McaB\001\003!\003\r\ta\002\002\007\rV$XO]3\013\005\r!\021AC2p]\016,(O]3oi*\tQ!A\003tG\006d\027m\001\001\026\005!\0312c\001\001\n\033A\021!bC\007\002\t%\021A\002\002\002\007\003:L(+\0324\021\0079y\021#D\001\003\023\t\001\"AA\005Bo\006LG/\0312mKB\021!c\005\007\001\t\031!\002\001\"b\001+\t\tA+\005\002\0273A\021!bF\005\0031\021\021qAT8uQ&tw\r\005\002\0135%\0211\004\002\002\004\003:L\b\"B\017\001\t\003q\022A\002\023j]&$H\005F\001 !\tQ\001%\003\002\"\t\t!QK\\5u\021\025\031\003\001\"\003%\003AIg\016^3s]\006dW\t_3dkR|'/F\001&\035\r1#q\005\b\003\035\035:Q\001\013\002\t\002%\naAR;ukJ,\007C\001\b+\r\025\t!\001#\001,'\tQ\023\002C\003.U\021\005a&\001\004=S:LGO\020\013\002S!A\001G\013b\001\n\003\021\021'A\004u_\n{\0070\0323\026\003I\002Ba\r\035;\0216\tAG\003\0026m\005I\021.\\7vi\006\024G.\032\006\003o\021\t!bY8mY\026\034G/[8o\023\tIDGA\002NCB\004$a\017#\021\007q\n5)D\001>\025\tqt(\001\003mC:<'\"\001!\002\t)\fg/Y\005\003\005v\022Qa\0217bgN\004\"A\005#\005\023\0253\025\021!A\001\006\003)\"aA0%c!1qI\013Q\001\nI\n\001\002^8C_b,G\r\t\031\003\023.\0032\001P!K!\t\0212\nB\005M\r\006\005\t\021!B\001+\t\031q\f\n\032\t\0139SC\021A(\002\r\031\f\027\016\\3e+\t\0016\013\006\002R)B\031a\002\001*\021\005I\031F!\002\013N\005\004)\002\"B+N\001\0041\026!C3yG\026\004H/[8o!\t9vL\004\002Y;:\021\021\fX\007\0025*\0211LB\001\007yI|w\016\036 \n\003\025I!A\030\003\002\017A\f7m[1hK&\021\001-\031\002\n)\"\024xn^1cY\026T!A\030\003\t\013\rTC\021\0013\002\025M,8mY3tg\032,H.\006\002fQR\021a-\033\t\004\035\0019\007C\001\ni\t\025!\"M1\001\026\021\025Q'\r1\001h\003\031\021Xm];mi\")AN\013C\001[\006)\021\r\0359msV\021aN\035\013\003_b$\"\001]:\021\0079\001\021\017\005\002\023e\022)Ac\033b\001+!)Ao\033a\002k\0069Q\r_3dGRD\bC\001\bw\023\t9(A\001\tFq\026\034W\017^5p]\016{g\016^3yi\"1\021p\033CA\002i\fAAY8esB\031!b_9\n\005q$!\001\003\037cs:\fW.\032 \t\013yTC\021A@\002\021M,\027/^3oG\026,b!!\001\002(\005%A\003BA\002\003\007\"b!!\002\002,\005}\002\003\002\b\001\003\017\001RAEA\005\003K!q!a\003~\005\004\tiAA\001N+\021\ty!!\t\022\007Y\t\t\002\r\003\002\024\005m\001#B,\002\026\005e\021bAA\fC\nyAK]1wKJ\034\030M\0317f\037:\034W\rE\002\023\0037!1\"!\b\002 \005\005\t\021!B\001+\t\031q\fJ\032\005\017\005-QP1\001\002\016\0219\0211EA\020\005\004)\"!A0\021\007I\t9\003\002\004\002*u\024\r!\006\002\002\003\"9\021QF?A\004\005=\022aA2cMBQ\021\021GA\034\003w\t)#a\002\016\005\005M\"bAA\033m\0059q-\0328fe&\034\027\002BA\035\003g\021AbQ1o\005VLG\016\032$s_6\004RAEA\005\003{\001BA\004\001\002&!1\021\021I?A\004U\f\001\"\032=fGV$xN\035\005\b\003\013j\b\031AA\036\003\tIg\016C\004\002J)\"\t!a\023\002!\031L'o\035;D_6\004H.\032;fI>3W\003BA'\003+\"B!a\024\002ZQ!\021\021KA,!\021q\001!a\025\021\007I\t)\006\002\004\025\003\017\022\r!\006\005\b\003\003\n9\005q\001v\021!\tY&a\022A\002\005u\023a\0024viV\024Xm\035\t\006/\006U\021\021\013\005\b\003CRC\021AA2\003\0211\027N\0343\026\t\005\025\024Q\017\013\005\003O\nI\t\006\003\002j\005eD\003BA6\003o\002BA\004\001\002nA)!\"a\034\002t%\031\021\021\017\003\003\r=\003H/[8o!\r\021\022Q\017\003\007)\005}#\031A\013\t\017\005\005\023q\fa\002k\"A\0211PA0\001\004\ti(A\005qe\026$\027nY1uKB9!\"a \002t\005\r\025bAAA\t\tIa)\0368di&|g.\r\t\004\025\005\025\025bAAD\t\t9!i\\8mK\006t\007\002CAF\003?\002\r!!$\002\037\031,H/\036:fgR\024\030M^8oG\026\004RaVA\013\003\037\003BA\004\001\002t!9\0211\023\026\005\002\005U\025\001\0024pY\022,b!a&\0026\006\rF\003BAM\003w#B!a'\0028R!\021QTAU)\021\ty*a*\021\t9\001\021\021\025\t\004%\005\rFaBAS\003#\023\r!\006\002\002%\"9\021\021IAI\001\b)\b\002CAV\003#\003\r!!,\002\017\031|G\016\032$v]BI!\"a,\002\"\006M\026\021U\005\004\003c#!!\003$v]\016$\030n\03483!\r\021\022Q\027\003\007)\005E%\031A\013\t\021\005e\026\021\023a\001\003C\013AA_3s_\"A\0211LAI\001\004\ti\fE\003X\003+\ty\f\005\003\017\001\005M\006bBAbU\021\005\021QY\001\007e\026$WoY3\026\r\005\035\027q[Ai)\021\tI-!9\025\t\005-\0271\034\013\005\003\033\fI\016\005\003\017\001\005=\007c\001\n\002R\022A\021QUAa\005\004\t\031.E\002\002Vf\0012AEAl\t\031!\022\021\031b\001+!9\021\021IAa\001\b)\b\002CAo\003\003\004\r!a8\002\005=\004\b#\003\006\0020\006=\027Q[Ah\021!\tY&!1A\002\005\r\b#B,\002\026\005\025\b\003\002\b\001\003+Dq!!;+\t\003\tY/\001\005ue\0064XM]:f+!\tiO!\007\003\016\005]H\003BAx\005K!B!!=\003\036Q1\0211\037B\t\0057\001BA\004\001\002vB)!#a>\003\f\021A\0211BAt\005\004\tI0\006\003\002|\n%\021c\001\f\002~B\"\021q B\002!\0259\026Q\003B\001!\r\021\"1\001\003\f\005\013\0219!!A\001\002\013\005QCA\002`IQ\"\001\"a\003\002h\n\007\021\021 \003\b\003G\0219A1\001\026!\r\021\"Q\002\003\b\005\037\t9O1\001\026\005\005\021\005\002CA\027\003O\004\035Aa\005\021\025\005E\022q\007B\013\005\027\t)\020E\003\023\003o\0249\002E\002\023\0053!q!!\013\002h\n\007Q\003C\004\002B\005\035\b9A;\t\021\t}\021q\035a\001\005C\t!A\0328\021\017)\tyHa\006\003$A!a\002\001B\006\021!\t)%a:A\002\tUq\001\003B\025U!\005!Aa\013\0021%sG/\032:oC2\034\025\r\0347cC\016\\W\t_3dkR|'\017\005\003\003.\t=R\"\001\026\007\021\tE\"\006#\001\003\005g\021\001$\0238uKJt\027\r\\\"bY2\024\027mY6Fq\026\034W\017^8s'\031\021y#C;\0036A!!q\007B \033\t\021IDC\002\004\005wQ1A!\020@\003\021)H/\0337\n\t\t\005#\021\b\002\t\013b,7-\036;pe\"9QFa\f\005\002\t\025CC\001B\026\021!\021IEa\f\005B\t-\023!\004:fa>\024HOR1jYV\024X\rF\002 \005\033BqAa\024\003H\001\007a+A\001u\021)\021\031Fa\fC\002\023%!QK\001\f?R\f7o[:M_\016\fG.\006\002\003XA)AH!\027\003^%\031!1L\037\003\027QC'/Z1e\031>\034\027\r\034\t\006/\n}#1M\005\004\005C\n'\001\002'jgR\0042\001\020B3\023\r\0219'\020\002\t%Vtg.\0312mK\"I!1\016B\030A\003%!qK\001\r?R\f7o[:M_\016\fG\016\t\004\b\005_\022y\003\002B9\005\025\021\025\r^2i'!\021iGa\035\003d\te\004c\001\037\003v%\031!qO\037\003\r=\023'.Z2u!\rq!1P\005\004\005{\022!\001\004\"m_\016\\7i\0348uKb$\bb\003BA\005[\022)\031!C\001\005\007\013q!\0338ji&\fG.\006\002\003^!Y!q\021B7\005\003\005\013\021\002B/\003!Ig.\033;jC2\004\003bB\027\003n\021\005!1\022\013\005\005\033\023\t\n\005\003\003\020\n5TB\001B\030\021!\021\tI!#A\002\tu\003\002\004BK\005[\002\r\021!Q!\n\te\024A\0059be\026tGO\0217pG.\034uN\034;fqRDqA!'\003n\021\005c$A\002sk:D\001B!(\003n\021\005#qT\001\bE2|7m[(o+\021\021\tKa*\025\t\t\r&1\027\013\005\005K\023I\013E\002\023\005O#a\001\006BN\005\004)\002\002\003BV\0057\003\035A!,\002\025A,'/\\5tg&|g\016E\002\017\005_K1A!-\003\005!\031\025M\\!xC&$\b\"\003B[\0057#\t\031\001B\\\003\025!\b.\0368l!\021Q1P!*\t\021\tm&q\006C!\005{\013q!\032=fGV$X\rF\002 \005C\001B!1\003:\002\007!1M\001\teVtg.\0312mK\"A!Q\031B\030\t\023\0219-\001\tv]\n\fGo\0315fI\026CXmY;uKR\031qD!3\t\021\t-'1\031a\001\005G\n\021A\035\005\b\005\037\004A\021\001Bi\003%ygnU;dG\026\0348/\006\003\003T\n\025H\003\002Bk\0053$2a\bBl\021\035\t\tE!4A\004UD\001Ba7\003N\002\007!Q\\\001\003a\032\004bA\003Bp#\t\r\030b\001Bq\t\ty\001+\031:uS\006dg)\0368di&|g\016E\002\023\005K$qAa:\003N\n\007QCA\001V\021\035\021Y\017\001C\001\005[\f\021b\0348GC&dWO]3\026\t\t=(Q \013\005\005c\024)\020F\002 \005gDq!!\021\003j\002\017Q\017\003\005\003x\n%\b\031\001B}\003!\031\027\r\0347cC\016\\\007C\002\006\003`Z\023Y\020E\002\023\005{$qAa:\003j\n\007Q\003C\004\004\002\0011\taa\001\002\025=t7i\\7qY\026$X-\006\003\004\006\ruA\003BB\004\007\027!2aHB\005\021\035\t\tEa@A\004UD\001b!\004\003\000\002\0071qB\001\005MVt7\rE\004\013\003\032\tba\007\021\013\rM1qC\t\016\005\rU!b\001B\037\t%!1\021DB\013\005\r!&/\037\t\004%\ruAa\002Bt\005\024\r!\006\005\b\007C\001a\021AB\022\003-I7oQ8na2,G/\0323\026\005\005\r\005bBB\024\001\031\0051\021F\001\006m\006dW/Z\013\003\007W\001RACA8\007#AaA\024\001\005\002\r=RCAB\031!\rq\001A\026\005\b\007k\001A\021AB\034\003\0351wN]3bG\",Ba!\017\004HQ!11HB )\ry2Q\b\005\b\003\003\032\031\004q\001v\021!\031\tea\rA\002\r\r\023!\0014\021\r)\ty(EB#!\r\0212q\t\003\b\005O\034\031D1\001\026\021\035\031Y\005\001C\001\007\033\n\021\002\036:b]N4wN]7\026\t\r=3q\013\013\007\007#\032ifa\031\025\t\rM31\f\t\005\035\001\031)\006E\002\023\007/\"qa!\027\004J\t\007QCA\001T\021\035\t\te!\023A\004UD\001ba\030\004J\001\0071\021M\001\002gB1!\"a \022\007+B\001b!\021\004J\001\0071Q\r\t\006\025\005}dK\026\005\b\007S\002A\021AB6\003\ri\027\r]\013\005\007[\032)\b\006\003\004p\reD\003BB9\007o\002BA\004\001\004tA\031!c!\036\005\017\re3q\rb\001+!9\021\021IB4\001\b)\b\002CB!\007O\002\raa\037\021\r)\ty(EB:\021\035\031y\b\001C\001\007\003\013qA\0327bi6\013\007/\006\003\004\004\016-E\003BBC\007\037#Baa\"\004\016B!a\002ABE!\r\02121\022\003\b\0073\032iH1\001\026\021\035\t\te! A\004UD\001b!\021\004~\001\0071\021\023\t\007\025\005}\024ca\"\t\017\rU\005\001\"\001\004\030\0061a-\0337uKJ$Ba!'\004 R!11TBO!\rq\001!\005\005\b\003\003\032\031\nq\001v\021!\031\tka%A\002\r\r\026\001\0029sK\022\004bACA@#\005\r\005bBBT\001\021\0251\021V\001\013o&$\bNR5mi\026\024H\003BBV\007_#Baa'\004.\"9\021\021IBS\001\b)\b\002CBY\007K\003\raa)\002\003ADqa!.\001\t\003\0319,A\004d_2dWm\031;\026\t\re6\021\031\013\005\007w\033)\r\006\003\004>\016\r\007\003\002\b\001\007\0032AEBa\t\035\031Ifa-C\002UAq!!\021\0044\002\017Q\017\003\005\003\\\016M\006\031ABd!\031Q!q\\\t\004@\"911\032\001\005\002\r5\027a\002:fG>4XM]\013\005\007\037\0349\016\006\003\004R\016uG\003BBj\0077\004BA\004\001\004VB\031!ca6\005\021\t\0358\021\032b\001\0073\f\"!E\r\t\017\005\0053\021\032a\002k\"A!1\\Be\001\004\031y\016\005\004\013\005?46Q\033\005\b\007G\004A\021ABs\003-\021XmY8wKJ<\026\016\0365\026\t\r\0358q\036\013\005\007S\034\031\020\006\003\004l\016E\b\003\002\b\001\007[\0042AEBx\t!\0219o!9C\002\re\007bBA!\007C\004\035!\036\005\t\0057\034\t\0171\001\004vB1!Ba8W\007WDqa!?\001\t\003\031Y0A\002{SB,Ba!@\005\nQ!1q C\006!\021q\001\001\"\001\021\r)!\031!\005C\004\023\r!)\001\002\002\007)V\004H.\032\032\021\007I!I\001B\004\003h\016](\031A\013\t\021\02151q\037a\001\t\037\tA\001\0365biB!a\002\001C\004\021\035!\031\002\001C\001\t+\t!BZ1mY\n\f7m\033+p+\021!9\002\"\b\025\t\021eAq\004\t\005\035\001!Y\002E\002\023\t;!\001Ba:\005\022\t\0071\021\034\005\t\t\033!\t\0021\001\005\032!9A1\005\001\005\002\021\025\022!B7baR{W\003\002C\024\t[!B\001\"\013\0050A!a\002\001C\026!\r\021BQ\006\003\b\0073\"\tC1\001\026\021!!\t\004\"\tA\004\021M\022a\001;bOB1AQ\007C\036\tWi!\001b\016\013\007\021eB!A\004sK\032dWm\031;\n\t\021uBq\007\002\t\0072\f7o\035+bO\"9A\021\t\001\005\002\021\r\023aB1oIRCWM\\\013\005\t\013\"\t\006\006\003\005H\021-C\003BBN\t\023Bq!!\021\005@\001\017Q\017\003\005\003\\\022}\002\031\001C'!\035Q!q\\B\t\t\037\0022A\005C)\t\035\0219\017b\020C\002U\001")
/*     */ public interface Future<T> extends Awaitable<T> {
/*     */   <U> void onSuccess(PartialFunction<T, U> paramPartialFunction, ExecutionContext paramExecutionContext);
/*     */   
/*     */   <U> void onFailure(PartialFunction<Throwable, U> paramPartialFunction, ExecutionContext paramExecutionContext);
/*     */   
/*     */   <U> void onComplete(Function1<Try<T>, U> paramFunction1, ExecutionContext paramExecutionContext);
/*     */   
/*     */   boolean isCompleted();
/*     */   
/*     */   Option<Try<T>> value();
/*     */   
/*     */   Future<Throwable> failed();
/*     */   
/*     */   <U> void foreach(Function1<T, U> paramFunction1, ExecutionContext paramExecutionContext);
/*     */   
/*     */   <S> Future<S> transform(Function1<T, S> paramFunction1, Function1<Throwable, Throwable> paramFunction11, ExecutionContext paramExecutionContext);
/*     */   
/*     */   <S> Future<S> map(Function1<T, S> paramFunction1, ExecutionContext paramExecutionContext);
/*     */   
/*     */   <S> Future<S> flatMap(Function1<T, Future<S>> paramFunction1, ExecutionContext paramExecutionContext);
/*     */   
/*     */   Future<T> filter(Function1<T, Object> paramFunction1, ExecutionContext paramExecutionContext);
/*     */   
/*     */   Future<T> withFilter(Function1<T, Object> paramFunction1, ExecutionContext paramExecutionContext);
/*     */   
/*     */   <S> Future<S> collect(PartialFunction<T, S> paramPartialFunction, ExecutionContext paramExecutionContext);
/*     */   
/*     */   <U> Future<U> recover(PartialFunction<Throwable, U> paramPartialFunction, ExecutionContext paramExecutionContext);
/*     */   
/*     */   <U> Future<U> recoverWith(PartialFunction<Throwable, Future<U>> paramPartialFunction, ExecutionContext paramExecutionContext);
/*     */   
/*     */   <U> Future<Tuple2<T, U>> zip(Future<U> paramFuture);
/*     */   
/*     */   <U> Future<U> fallbackTo(Future<U> paramFuture);
/*     */   
/*     */   <S> Future<S> mapTo(ClassTag<S> paramClassTag);
/*     */   
/*     */   <U> Future<T> andThen(PartialFunction<Try<T>, U> paramPartialFunction, ExecutionContext paramExecutionContext);
/*     */   
/*     */   public class Future$$anonfun$onSuccess$1 extends AbstractFunction1<Try<T>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final PartialFunction pf$1;
/*     */     
/*     */     public Future$$anonfun$onSuccess$1(Future $outer, PartialFunction pf$1) {}
/*     */     
/*     */     public final Object apply(Try x0$1) {
/*     */       Object object;
/* 115 */       if (x0$1 instanceof Success) {
/* 115 */         Success success = (Success)x0$1;
/* 117 */         object = this.pf$1.applyOrElse(success.value(), (Function1)scala.Predef$.MODULE$.conforms());
/*     */       } else {
/* 118 */         object = BoxedUnit.UNIT;
/*     */       } 
/*     */       return object;
/*     */     }
/*     */   }
/*     */   
/*     */   public class Future$$anonfun$onFailure$1 extends AbstractFunction1<Try<T>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final PartialFunction callback$1;
/*     */     
/*     */     public Future$$anonfun$onFailure$1(Future $outer, PartialFunction callback$1) {}
/*     */     
/*     */     public final Object apply(Try x0$2) {
/*     */       Object object;
/* 134 */       if (x0$2 instanceof Failure) {
/* 134 */         Failure failure = (Failure)x0$2;
/* 136 */         object = this.callback$1.applyOrElse(failure.exception(), (Function1)scala.Predef$.MODULE$.conforms());
/*     */       } else {
/* 137 */         object = BoxedUnit.UNIT;
/*     */       } 
/*     */       return object;
/*     */     }
/*     */   }
/*     */   
/*     */   public class Future$$anonfun$failed$1 extends AbstractFunction1<Try<T>, Promise<Throwable>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Promise p$1;
/*     */     
/*     */     public Future$$anonfun$failed$1(Future $outer, Promise p$1) {}
/*     */     
/*     */     public final Promise<Throwable> apply(Try x0$3) {
/* 190 */       if (x0$3 instanceof Failure) {
/* 190 */         Failure failure = (Failure)x0$3;
/* 191 */         Promise<Throwable> promise = this.p$1.success(failure.exception());
/*     */       } else {
/* 192 */         if (x0$3 instanceof Success)
/* 192 */           return this.p$1.failure(new NoSuchElementException("Future.failed not completed with a throwable.")); 
/*     */         throw new MatchError(x0$3);
/*     */       } 
/*     */       return (Promise<Throwable>)SYNTHETIC_LOCAL_VARIABLE_3;
/*     */     }
/*     */   }
/*     */   
/*     */   public class Future$$anonfun$foreach$1 extends AbstractFunction1<Try<T>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 f$1;
/*     */     
/*     */     public final void apply(Try x$1) {
/* 204 */       x$1.foreach(this.f$1);
/*     */     }
/*     */     
/*     */     public Future$$anonfun$foreach$1(Future $outer, Function1 f$1) {}
/*     */   }
/*     */   
/*     */   public class Future$$anonfun$transform$1 extends AbstractFunction1<Try<T>, Promise<S>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Promise p$2;
/*     */     
/*     */     public final Function1 s$1;
/*     */     
/*     */     public final Function1 f$2;
/*     */     
/*     */     public Future$$anonfun$transform$1(Future $outer, Promise p$2, Function1 s$1, Function1 f$2) {}
/*     */     
/*     */     public final Promise<S> apply(Try x0$4) {
/* 220 */       if (x0$4 instanceof Success) {
/* 220 */         Success success = (Success)x0$4;
/* 220 */         Promise promise = this.p$2.complete(scala.util.Try$.MODULE$.apply((Function0)new Future$$anonfun$transform$1$$anonfun$apply$1(this, ($anonfun$transform$1)success)));
/*     */       } else {
/* 222 */         if (x0$4 instanceof Failure) {
/* 222 */           Failure failure = (Failure)x0$4;
/* 222 */           return this.p$2.complete(scala.util.Try$.MODULE$.apply((Function0)new Future$$anonfun$transform$1$$anonfun$apply$2(this, ($anonfun$transform$1)failure)));
/*     */         } 
/*     */         throw new MatchError(x0$4);
/*     */       } 
/*     */       return (Promise<S>)SYNTHETIC_LOCAL_VARIABLE_4;
/*     */     }
/*     */     
/*     */     public class Future$$anonfun$transform$1$$anonfun$apply$1 extends AbstractFunction0<S> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Success x2$1;
/*     */       
/*     */       public final S apply() {
/*     */         return (S)this.$outer.s$1.apply(this.x2$1.value());
/*     */       }
/*     */       
/*     */       public Future$$anonfun$transform$1$$anonfun$apply$1(Future$$anonfun$transform$1 $outer, Success x2$1) {}
/*     */     }
/*     */     
/*     */     public class Future$$anonfun$transform$1$$anonfun$apply$2 extends AbstractFunction0<scala.runtime.Nothing$> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Failure x3$1;
/*     */       
/*     */       public final scala.runtime.Nothing$ apply() {
/* 222 */         throw (Throwable)this.$outer.f$2.apply(this.x3$1.exception());
/*     */       }
/*     */       
/*     */       public Future$$anonfun$transform$1$$anonfun$apply$2(Future$$anonfun$transform$1 $outer, Failure x3$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class Future$$anonfun$map$1 extends AbstractFunction1<Try<T>, Promise<S>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Promise p$3;
/*     */     
/*     */     private final Function1 f$3;
/*     */     
/*     */     public final Promise<S> apply(Try v) {
/* 235 */       return this.p$3.complete(v.map(this.f$3));
/*     */     }
/*     */     
/*     */     public Future$$anonfun$map$1(Future $outer, Promise p$3, Function1 f$3) {}
/*     */   }
/*     */   
/*     */   public class Future$$anonfun$flatMap$1 extends AbstractFunction1<Try<T>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Promise.DefaultPromise p$4;
/*     */     
/*     */     private final Function1 f$4;
/*     */     
/*     */     public Future$$anonfun$flatMap$1(Future $outer, Promise.DefaultPromise p$4, Function1 f$4) {}
/*     */     
/*     */     public final Object apply(Try x0$5) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: instanceof scala/util/Failure
/*     */       //   4: ifeq -> 25
/*     */       //   7: aload_1
/*     */       //   8: checkcast scala/util/Failure
/*     */       //   11: astore_2
/*     */       //   12: aload_0
/*     */       //   13: getfield p$4 : Lscala/concurrent/impl/Promise$DefaultPromise;
/*     */       //   16: aload_2
/*     */       //   17: invokevirtual complete : (Lscala/util/Try;)Lscala/concurrent/Promise;
/*     */       //   20: astore #8
/*     */       //   22: goto -> 159
/*     */       //   25: aload_1
/*     */       //   26: instanceof scala/util/Success
/*     */       //   29: ifeq -> 110
/*     */       //   32: aload_1
/*     */       //   33: checkcast scala/util/Success
/*     */       //   36: astore_3
/*     */       //   37: aload_0
/*     */       //   38: getfield f$4 : Lscala/Function1;
/*     */       //   41: aload_3
/*     */       //   42: invokevirtual value : ()Ljava/lang/Object;
/*     */       //   45: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */       //   50: checkcast scala/concurrent/Future
/*     */       //   53: astore #5
/*     */       //   55: aload #5
/*     */       //   57: instanceof scala/concurrent/impl/Promise$DefaultPromise
/*     */       //   60: ifeq -> 82
/*     */       //   63: aload #5
/*     */       //   65: checkcast scala/concurrent/impl/Promise$DefaultPromise
/*     */       //   68: astore #4
/*     */       //   70: aload #4
/*     */       //   72: aload_0
/*     */       //   73: getfield p$4 : Lscala/concurrent/impl/Promise$DefaultPromise;
/*     */       //   76: invokevirtual linkRootOf : (Lscala/concurrent/impl/Promise$DefaultPromise;)V
/*     */       //   79: goto -> 104
/*     */       //   82: aload #5
/*     */       //   84: new scala/concurrent/Future$$anonfun$flatMap$1$$anonfun$apply$3
/*     */       //   87: dup
/*     */       //   88: aload_0
/*     */       //   89: invokespecial <init> : (Lscala/concurrent/Future$$anonfun$flatMap$1;)V
/*     */       //   92: aload_0
/*     */       //   93: getfield $outer : Lscala/concurrent/Future;
/*     */       //   96: invokestatic scala$concurrent$Future$$internalExecutor : (Lscala/concurrent/Future;)Lscala/concurrent/Future$InternalCallbackExecutor$;
/*     */       //   99: invokeinterface onComplete : (Lscala/Function1;Lscala/concurrent/ExecutionContext;)V
/*     */       //   104: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   107: goto -> 157
/*     */       //   110: new scala/MatchError
/*     */       //   113: dup
/*     */       //   114: aload_1
/*     */       //   115: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   118: athrow
/*     */       //   119: astore #6
/*     */       //   121: getstatic scala/util/control/NonFatal$.MODULE$ : Lscala/util/control/NonFatal$;
/*     */       //   124: aload #6
/*     */       //   126: invokevirtual unapply : (Ljava/lang/Throwable;)Lscala/Option;
/*     */       //   129: astore #7
/*     */       //   131: aload #7
/*     */       //   133: invokevirtual isEmpty : ()Z
/*     */       //   136: ifeq -> 142
/*     */       //   139: aload #6
/*     */       //   141: athrow
/*     */       //   142: aload_0
/*     */       //   143: getfield p$4 : Lscala/concurrent/impl/Promise$DefaultPromise;
/*     */       //   146: aload #7
/*     */       //   148: invokevirtual get : ()Ljava/lang/Object;
/*     */       //   151: checkcast java/lang/Throwable
/*     */       //   154: invokevirtual failure : (Ljava/lang/Throwable;)Lscala/concurrent/Promise;
/*     */       //   157: astore #8
/*     */       //   159: aload #8
/*     */       //   161: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #250	-> 0
/*     */       //   #249	-> 0
/*     */       //   #251	-> 25
/*     */       //   #249	-> 41
/*     */       //   #251	-> 42
/*     */       //   #253	-> 55
/*     */       //   #254	-> 82
/*     */       //   #251	-> 104
/*     */       //   #249	-> 110
/*     */       //   #251	-> 119
/*     */       //   #255	-> 121
/*     */       //   #251	-> 139
/*     */       //   #255	-> 142
/*     */       //   #249	-> 146
/*     */       //   #255	-> 148
/*     */       //   #251	-> 157
/*     */       //   #249	-> 159
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	162	0	this	Lscala/concurrent/Future$$anonfun$flatMap$1;
/*     */       //   0	162	1	x0$5	Lscala/util/Try;
/*     */       // Exception table:
/*     */       //   from	to	target	type
/*     */       //   37	110	119	finally
/*     */     }
/*     */     
/*     */     public class Future$$anonfun$flatMap$1$$anonfun$apply$3 extends AbstractFunction1<Try<S>, Promise.DefaultPromise<S>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Promise.DefaultPromise<S> apply(Try result) {
/* 254 */         return (Promise.DefaultPromise<S>)this.$outer.p$4.complete(result);
/*     */       }
/*     */       
/*     */       public Future$$anonfun$flatMap$1$$anonfun$apply$3(Future$$anonfun$flatMap$1 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class Future$$anonfun$filter$1 extends AbstractFunction1<T, T> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 pred$1;
/*     */     
/*     */     public final T apply(Object r) {
/* 278 */       if (BoxesRunTime.unboxToBoolean(this.pred$1.apply(r)))
/* 278 */         return (T)r; 
/* 278 */       throw new NoSuchElementException("Future.filter predicate is not satisfied");
/*     */     }
/*     */     
/*     */     public Future$$anonfun$filter$1(Future $outer, Function1 pred$1) {}
/*     */   }
/*     */   
/*     */   public class Future$$anonfun$collect$1 extends AbstractFunction1<T, S> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final PartialFunction pf$2;
/*     */     
/*     */     public final S apply(Object r) {
/* 307 */       return (S)this.pf$2.applyOrElse(r, (Function1)new Future$$anonfun$collect$1$$anonfun$apply$4(this));
/*     */     }
/*     */     
/*     */     public Future$$anonfun$collect$1(Future $outer, PartialFunction pf$2) {}
/*     */     
/*     */     public class Future$$anonfun$collect$1$$anonfun$apply$4 extends AbstractFunction1<T, scala.runtime.Nothing$> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final scala.runtime.Nothing$ apply(Object t) {
/* 307 */         throw new NoSuchElementException((new StringBuilder()).append("Future.collect partial function is not defined at: ").append(t).toString());
/*     */       }
/*     */       
/*     */       public Future$$anonfun$collect$1$$anonfun$apply$4(Future$$anonfun$collect$1 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class Future$$anonfun$recover$1 extends AbstractFunction1<Try<T>, Promise<U>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Promise p$5;
/*     */     
/*     */     private final PartialFunction pf$3;
/*     */     
/*     */     public final Promise<U> apply(Try v) {
/* 324 */       return this.p$5.complete(v.recover(this.pf$3));
/*     */     }
/*     */     
/*     */     public Future$$anonfun$recover$1(Future $outer, Promise p$5, PartialFunction pf$3) {}
/*     */   }
/*     */   
/*     */   public class Future$$anonfun$recoverWith$1 extends AbstractFunction1<Try<T>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Promise p$6;
/*     */     
/*     */     private final PartialFunction pf$4;
/*     */     
/*     */     public Future$$anonfun$recoverWith$1(Future $outer, Promise p$6, PartialFunction pf$4) {}
/*     */     
/*     */     public final Object apply(Try x0$6) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: instanceof scala/util/Failure
/*     */       //   4: ifeq -> 62
/*     */       //   7: aload_1
/*     */       //   8: checkcast scala/util/Failure
/*     */       //   11: astore_2
/*     */       //   12: aload_0
/*     */       //   13: getfield pf$4 : Lscala/PartialFunction;
/*     */       //   16: aload_2
/*     */       //   17: invokevirtual exception : ()Ljava/lang/Throwable;
/*     */       //   20: new scala/concurrent/Future$$anonfun$recoverWith$1$$anonfun$apply$5
/*     */       //   23: dup
/*     */       //   24: aload_0
/*     */       //   25: invokespecial <init> : (Lscala/concurrent/Future$$anonfun$recoverWith$1;)V
/*     */       //   28: invokeinterface applyOrElse : (Ljava/lang/Object;Lscala/Function1;)Ljava/lang/Object;
/*     */       //   33: checkcast scala/concurrent/Future
/*     */       //   36: new scala/concurrent/Future$$anonfun$recoverWith$1$$anonfun$apply$6
/*     */       //   39: dup
/*     */       //   40: aload_0
/*     */       //   41: invokespecial <init> : (Lscala/concurrent/Future$$anonfun$recoverWith$1;)V
/*     */       //   44: aload_0
/*     */       //   45: getfield $outer : Lscala/concurrent/Future;
/*     */       //   48: invokestatic scala$concurrent$Future$$internalExecutor : (Lscala/concurrent/Future;)Lscala/concurrent/Future$InternalCallbackExecutor$;
/*     */       //   51: invokeinterface onComplete : (Lscala/Function1;Lscala/concurrent/ExecutionContext;)V
/*     */       //   56: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   59: goto -> 114
/*     */       //   62: aload_0
/*     */       //   63: getfield p$6 : Lscala/concurrent/Promise;
/*     */       //   66: aload_1
/*     */       //   67: invokeinterface complete : (Lscala/util/Try;)Lscala/concurrent/Promise;
/*     */       //   72: astore #5
/*     */       //   74: goto -> 116
/*     */       //   77: astore_3
/*     */       //   78: getstatic scala/util/control/NonFatal$.MODULE$ : Lscala/util/control/NonFatal$;
/*     */       //   81: aload_3
/*     */       //   82: invokevirtual unapply : (Ljava/lang/Throwable;)Lscala/Option;
/*     */       //   85: astore #4
/*     */       //   87: aload #4
/*     */       //   89: invokevirtual isEmpty : ()Z
/*     */       //   92: ifeq -> 97
/*     */       //   95: aload_3
/*     */       //   96: athrow
/*     */       //   97: aload_0
/*     */       //   98: getfield p$6 : Lscala/concurrent/Promise;
/*     */       //   101: aload #4
/*     */       //   103: invokevirtual get : ()Ljava/lang/Object;
/*     */       //   106: checkcast java/lang/Throwable
/*     */       //   109: invokeinterface failure : (Ljava/lang/Throwable;)Lscala/concurrent/Promise;
/*     */       //   114: astore #5
/*     */       //   116: aload #5
/*     */       //   118: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #344	-> 0
/*     */       //   #343	-> 0
/*     */       //   #343	-> 16
/*     */       //   #344	-> 17
/*     */       //   #345	-> 62
/*     */       //   #344	-> 77
/*     */       //   #343	-> 101
/*     */       //   #344	-> 103
/*     */       //   #343	-> 116
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	119	0	this	Lscala/concurrent/Future$$anonfun$recoverWith$1;
/*     */       //   0	119	1	x0$6	Lscala/util/Try;
/*     */       // Exception table:
/*     */       //   from	to	target	type
/*     */       //   12	62	77	finally
/*     */     }
/*     */     
/*     */     public class Future$$anonfun$recoverWith$1$$anonfun$apply$5 extends AbstractFunction1<Throwable, Future<T>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Future<T> apply(Throwable x$2) {
/* 344 */         return this.$outer.$outer;
/*     */       }
/*     */       
/*     */       public Future$$anonfun$recoverWith$1$$anonfun$apply$5(Future$$anonfun$recoverWith$1 $outer) {}
/*     */     }
/*     */     
/*     */     public class Future$$anonfun$recoverWith$1$$anonfun$apply$6 extends AbstractFunction1<Try<U>, Promise<U>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Promise<U> apply(Try<U> result) {
/* 344 */         return this.$outer.p$6.complete(result);
/*     */       }
/*     */       
/*     */       public Future$$anonfun$recoverWith$1$$anonfun$apply$6(Future$$anonfun$recoverWith$1 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class Future$$anonfun$zip$1 extends AbstractFunction1<Try<T>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Future.InternalCallbackExecutor$ ec$1;
/*     */     
/*     */     public final Promise p$7;
/*     */     
/*     */     private final Future that$1;
/*     */     
/*     */     public Future$$anonfun$zip$1(Future $outer, Future.InternalCallbackExecutor$ ec$1, Promise p$7, Future that$1) {}
/*     */     
/*     */     public final Object apply(Try x0$7) {
/* 361 */       if (x0$7 instanceof Failure) {
/* 361 */         Failure failure = (Failure)x0$7;
/* 361 */         Promise promise = this.p$7.complete((Try)failure);
/*     */       } else {
/* 363 */         if (x0$7 instanceof Success) {
/* 363 */           Success success = (Success)x0$7;
/* 363 */           this.that$1.onComplete((Function1)new Future$$anonfun$zip$1$$anonfun$apply$7(this, ($anonfun$zip$1)success), this.ec$1);
/* 363 */           return BoxedUnit.UNIT;
/*     */         } 
/*     */         throw new MatchError(x0$7);
/*     */       } 
/*     */       return SYNTHETIC_LOCAL_VARIABLE_4;
/*     */     }
/*     */     
/*     */     public class Future$$anonfun$zip$1$$anonfun$apply$7 extends AbstractFunction1<Try<U>, Promise<Tuple2<T, U>>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Success x2$2;
/*     */       
/*     */       public final Promise<Tuple2<T, U>> apply(Try c) {
/* 363 */         return this.$outer.p$7.complete(c.map((Function1)new Future$$anonfun$zip$1$$anonfun$apply$7$$anonfun$apply$8(this)));
/*     */       }
/*     */       
/*     */       public Future$$anonfun$zip$1$$anonfun$apply$7(Future$$anonfun$zip$1 $outer, Success x2$2) {}
/*     */       
/*     */       public class Future$$anonfun$zip$1$$anonfun$apply$7$$anonfun$apply$8 extends AbstractFunction1<U, Tuple2<T, U>> implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         public final Tuple2<T, U> apply(Object s2) {
/* 363 */           return new Tuple2(this.$outer.x2$2.value(), s2);
/*     */         }
/*     */         
/*     */         public Future$$anonfun$zip$1$$anonfun$apply$7$$anonfun$apply$8(Future$$anonfun$zip$1$$anonfun$apply$7 $outer) {}
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public class Future$$anonfun$fallbackTo$1 extends AbstractFunction1<Try<T>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Future.InternalCallbackExecutor$ ec$2;
/*     */     
/*     */     public final Promise p$8;
/*     */     
/*     */     private final Future that$2;
/*     */     
/*     */     public Future$$anonfun$fallbackTo$1(Future $outer, Future.InternalCallbackExecutor$ ec$2, Promise p$8, Future that$2) {}
/*     */     
/*     */     public final Object apply(Try x0$8) {
/* 385 */       if (x0$8 instanceof Success) {
/* 385 */         Success success = (Success)x0$8;
/* 385 */         Promise promise = this.p$8.complete((Try)success);
/*     */       } else {
/* 387 */         if (x0$8 instanceof Failure) {
/* 387 */           Failure failure = (Failure)x0$8;
/* 387 */           this.that$2.onComplete((Function1)new Future$$anonfun$fallbackTo$1$$anonfun$apply$9(this, ($anonfun$fallbackTo$1)failure), this.ec$2);
/* 387 */           return BoxedUnit.UNIT;
/*     */         } 
/*     */         throw new MatchError(x0$8);
/*     */       } 
/*     */       return SYNTHETIC_LOCAL_VARIABLE_4;
/*     */     }
/*     */     
/*     */     public class Future$$anonfun$fallbackTo$1$$anonfun$apply$9 extends AbstractFunction1<Try<U>, Promise<U>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Failure x5$1;
/*     */       
/*     */       public Future$$anonfun$fallbackTo$1$$anonfun$apply$9(Future$$anonfun$fallbackTo$1 $outer, Failure x5$1) {}
/*     */       
/*     */       public final Promise<U> apply(Try x0$9) {
/*     */         Promise<U> promise;
/* 387 */         if (x0$9 instanceof Success) {
/* 387 */           Success success = (Success)x0$9;
/* 387 */           promise = this.$outer.p$8.complete((Try)success);
/*     */         } else {
/* 389 */           promise = this.$outer.p$8.complete((Try)this.x5$1);
/*     */         } 
/*     */         return promise;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public class Future$$anonfun$mapTo$1 extends AbstractFunction1<T, S> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Class boxedClass$1;
/*     */     
/*     */     public final S apply(Object s) {
/* 405 */       return this.boxedClass$1.cast(s);
/*     */     }
/*     */     
/*     */     public Future$$anonfun$mapTo$1(Future $outer, Class boxedClass$1) {}
/*     */   }
/*     */   
/*     */   public class Future$$anonfun$andThen$1 extends AbstractFunction1<Try<T>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Promise p$9;
/*     */     
/*     */     private final PartialFunction pf$5;
/*     */     
/*     */     public final Object apply(Try x0$10) {
/* 433 */       Try try_ = x0$10;
/*     */       try {
/* 433 */         return 
/* 434 */           this.pf$5.applyOrElse(x0$10, (Function1)scala.Predef$.MODULE$.conforms());
/*     */       } finally {
/* 434 */         this.p$9.complete(try_);
/*     */       } 
/*     */     }
/*     */     
/*     */     public Future$$anonfun$andThen$1(Future $outer, Promise p$9, PartialFunction pf$5) {}
/*     */   }
/*     */   
/*     */   public static class Future$$anonfun$sequence$1 extends AbstractFunction2<Future<Builder<A, M>>, Object, Future<Builder<A, M>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final ExecutionContext executor$1;
/*     */     
/*     */     public final Future<Builder<A, M>> apply(Future fr, Object fa) {
/* 492 */       return fr.flatMap((Function1)new Future$$anonfun$sequence$1$$anonfun$apply$10(this, fa), this.executor$1);
/*     */     }
/*     */     
/*     */     public Future$$anonfun$sequence$1(ExecutionContext executor$1) {}
/*     */     
/*     */     public class Future$$anonfun$sequence$1$$anonfun$apply$10 extends AbstractFunction1<Builder<A, M>, Future<Builder<A, M>>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Object fa$1;
/*     */       
/*     */       public final Future<Builder<A, M>> apply(Builder r) {
/* 492 */         return ((Future)this.fa$1).map((Function1)new Future$$anonfun$sequence$1$$anonfun$apply$10$$anonfun$apply$11(this, r), this.$outer.executor$1);
/*     */       }
/*     */       
/*     */       public Future$$anonfun$sequence$1$$anonfun$apply$10(Future$$anonfun$sequence$1 $outer, Object fa$1) {}
/*     */       
/*     */       public class Future$$anonfun$sequence$1$$anonfun$apply$10$$anonfun$apply$11 extends AbstractFunction1<A, Builder<A, M>> implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         private final Builder r$1;
/*     */         
/*     */         public final Builder<A, M> apply(Object a) {
/* 492 */           return this.r$1.$plus$eq(a);
/*     */         }
/*     */         
/*     */         public Future$$anonfun$sequence$1$$anonfun$apply$10$$anonfun$apply$11(Future$$anonfun$sequence$1$$anonfun$apply$10 $outer, Builder r$1) {}
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Future$$anonfun$sequence$2 extends AbstractFunction1<Builder<A, M>, M> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final M apply(Builder x$3) {
/* 493 */       return (M)x$3.result();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Future$$anonfun$1 extends AbstractFunction1<Try<T>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Promise p$10;
/*     */     
/*     */     public final void apply(Try x$4) {
/* 500 */       this.p$10.tryComplete(x$4);
/*     */     }
/*     */     
/*     */     public Future$$anonfun$1(Promise p$10) {}
/*     */   }
/*     */   
/*     */   public static class Future$$anonfun$firstCompletedOf$1 extends AbstractFunction1<Future<T>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ExecutionContext executor$2;
/*     */     
/*     */     private final Function1 completeFirst$1;
/*     */     
/*     */     public final void apply(Future x$5) {
/* 501 */       x$5.onComplete(this.completeFirst$1, this.executor$2);
/*     */     }
/*     */     
/*     */     public Future$$anonfun$firstCompletedOf$1(ExecutionContext executor$2, Function1 completeFirst$1) {}
/*     */   }
/*     */   
/*     */   public static class Future$$anonfun$2 extends AbstractFunction1<Try<T>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 predicate$1;
/*     */     
/*     */     private final Promise result$1;
/*     */     
/*     */     private final AtomicInteger ref$1;
/*     */     
/*     */     public Future$$anonfun$2(Function1 predicate$1, Promise result$1, AtomicInteger ref$1) {}
/*     */     
/*     */     public final void apply(Try v) {
/*     */       // Byte code:
/*     */       //   0: nop
/*     */       //   1: aload_1
/*     */       //   2: instanceof scala/util/Success
/*     */       //   5: ifeq -> 60
/*     */       //   8: aload_1
/*     */       //   9: checkcast scala/util/Success
/*     */       //   12: astore_2
/*     */       //   13: aload_0
/*     */       //   14: getfield predicate$1 : Lscala/Function1;
/*     */       //   17: aload_2
/*     */       //   18: invokevirtual value : ()Ljava/lang/Object;
/*     */       //   21: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */       //   26: invokestatic unboxToBoolean : (Ljava/lang/Object;)Z
/*     */       //   29: ifeq -> 60
/*     */       //   32: aload_0
/*     */       //   33: getfield result$1 : Lscala/concurrent/Promise;
/*     */       //   36: new scala/util/Success
/*     */       //   39: dup
/*     */       //   40: new scala/Some
/*     */       //   43: dup
/*     */       //   44: aload_2
/*     */       //   45: invokevirtual value : ()Ljava/lang/Object;
/*     */       //   48: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   51: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   54: invokeinterface tryComplete : (Lscala/util/Try;)Z
/*     */       //   59: pop
/*     */       //   60: aload_0
/*     */       //   61: getfield ref$1 : Ljava/util/concurrent/atomic/AtomicInteger;
/*     */       //   64: invokevirtual decrementAndGet : ()I
/*     */       //   67: iconst_0
/*     */       //   68: if_icmpne -> 91
/*     */       //   71: aload_0
/*     */       //   72: getfield result$1 : Lscala/concurrent/Promise;
/*     */       //   75: new scala/util/Success
/*     */       //   78: dup
/*     */       //   79: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */       //   82: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   85: invokeinterface tryComplete : (Lscala/util/Try;)Z
/*     */       //   90: pop
/*     */       //   91: return
/*     */       //   92: astore_3
/*     */       //   93: aload_0
/*     */       //   94: getfield ref$1 : Ljava/util/concurrent/atomic/AtomicInteger;
/*     */       //   97: invokevirtual decrementAndGet : ()I
/*     */       //   100: iconst_0
/*     */       //   101: if_icmpne -> 124
/*     */       //   104: aload_0
/*     */       //   105: getfield result$1 : Lscala/concurrent/Promise;
/*     */       //   108: new scala/util/Success
/*     */       //   111: dup
/*     */       //   112: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */       //   115: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   118: invokeinterface tryComplete : (Lscala/util/Try;)Z
/*     */       //   123: pop
/*     */       //   124: aload_3
/*     */       //   125: athrow
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #514	-> 0
/*     */       //   #515	-> 1
/*     */       //   #514	-> 17
/*     */       //   #515	-> 18
/*     */       //   #514	-> 44
/*     */       //   #515	-> 45
/*     */       //   #519	-> 60
/*     */       //   #520	-> 71
/*     */       //   #513	-> 91
/*     */       //   #519	-> 92
/*     */       //   #520	-> 104
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	126	0	this	Lscala/concurrent/Future$$anonfun$2;
/*     */       //   0	126	1	v	Lscala/util/Try;
/*     */       // Exception table:
/*     */       //   from	to	target	type
/*     */       //   0	60	92	finally
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Future$$anonfun$find$1 extends AbstractFunction1<Future<T>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ExecutionContext executor$3;
/*     */     
/*     */     private final Function1 search$1;
/*     */     
/*     */     public final void apply(Future x$6) {
/* 524 */       x$6.onComplete(this.search$1, this.executor$3);
/*     */     }
/*     */     
/*     */     public Future$$anonfun$find$1(ExecutionContext executor$3, Function1 search$1) {}
/*     */   }
/*     */   
/*     */   public static class Future$$anonfun$fold$1 extends AbstractFunction1<TraversableOnce<T>, R> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Object zero$1;
/*     */     
/*     */     private final Function2 foldFun$1;
/*     */     
/*     */     public final R apply(TraversableOnce x$7) {
/* 542 */       return (R)x$7.foldLeft(this.zero$1, this.foldFun$1);
/*     */     }
/*     */     
/*     */     public Future$$anonfun$fold$1(Object zero$1, Function2 foldFun$1) {}
/*     */   }
/*     */   
/*     */   public static class Future$$anonfun$reduce$1 extends AbstractFunction1<TraversableOnce<T>, R> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function2 op$1;
/*     */     
/*     */     public final R apply(TraversableOnce x$8) {
/* 554 */       return (R)x$8.reduceLeft(this.op$1);
/*     */     }
/*     */     
/*     */     public Future$$anonfun$reduce$1(Function2 op$1) {}
/*     */   }
/*     */   
/*     */   public static class Future$$anonfun$traverse$1 extends AbstractFunction2<Future<Builder<B, M>>, Object, Future<Builder<B, M>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 fn$1;
/*     */     
/*     */     public final ExecutionContext executor$4;
/*     */     
/*     */     public Future$$anonfun$traverse$1(Function1 fn$1, ExecutionContext executor$4) {}
/*     */     
/*     */     public final Future<Builder<B, M>> apply(Future fr, Object a) {
/* 567 */       Future fb = (Future)this.fn$1.apply(a);
/* 568 */       return fr.flatMap((Function1)new Future$$anonfun$traverse$1$$anonfun$apply$12(this, fb), this.executor$4);
/*     */     }
/*     */     
/*     */     public class Future$$anonfun$traverse$1$$anonfun$apply$12 extends AbstractFunction1<Builder<B, M>, Future<Builder<B, M>>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Future fb$1;
/*     */       
/*     */       public final Future<Builder<B, M>> apply(Builder r) {
/* 568 */         return this.fb$1.map((Function1)new Future$$anonfun$traverse$1$$anonfun$apply$12$$anonfun$apply$13(this, r), this.$outer.executor$4);
/*     */       }
/*     */       
/*     */       public Future$$anonfun$traverse$1$$anonfun$apply$12(Future$$anonfun$traverse$1 $outer, Future fb$1) {}
/*     */       
/*     */       public class Future$$anonfun$traverse$1$$anonfun$apply$12$$anonfun$apply$13 extends AbstractFunction1<B, Builder<B, M>> implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         private final Builder r$2;
/*     */         
/*     */         public final Builder<B, M> apply(Object b) {
/* 568 */           return this.r$2.$plus$eq(b);
/*     */         }
/*     */         
/*     */         public Future$$anonfun$traverse$1$$anonfun$apply$12$$anonfun$apply$13(Future$$anonfun$traverse$1$$anonfun$apply$12 $outer, Builder r$2) {}
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Future$$anonfun$traverse$2 extends AbstractFunction1<Builder<B, M>, M> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final M apply(Builder x$9) {
/* 569 */       return (M)x$9.result();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class InternalCallbackExecutor$ implements ExecutionContext, Executor {
/*     */     public static final InternalCallbackExecutor$ MODULE$;
/*     */     
/*     */     private final ThreadLocal<List<Runnable>> scala$concurrent$Future$InternalCallbackExecutor$$_tasksLocal;
/*     */     
/*     */     public ExecutionContext prepare() {
/* 590 */       return ExecutionContext$class.prepare(this);
/*     */     }
/*     */     
/*     */     public InternalCallbackExecutor$() {
/* 590 */       MODULE$ = this;
/* 590 */       ExecutionContext$class.$init$(this);
/* 625 */       this.scala$concurrent$Future$InternalCallbackExecutor$$_tasksLocal = new ThreadLocal<List<Runnable>>();
/*     */     }
/*     */     
/*     */     public void reportFailure(Throwable t) {
/*     */       throw new IllegalStateException("problem in scala.concurrent internal callback", t);
/*     */     }
/*     */     
/*     */     public ThreadLocal<List<Runnable>> scala$concurrent$Future$InternalCallbackExecutor$$_tasksLocal() {
/* 625 */       return this.scala$concurrent$Future$InternalCallbackExecutor$$_tasksLocal;
/*     */     }
/*     */     
/*     */     public static class atch implements Runnable, BlockContext {
/*     */       private final List<Runnable> initial;
/*     */       
/*     */       public BlockContext scala$concurrent$Future$InternalCallbackExecutor$Batch$$parentBlockContext;
/*     */       
/*     */       public List<Runnable> initial() {
/* 627 */         return this.initial;
/*     */       }
/*     */       
/*     */       public atch(List<Runnable> initial) {}
/*     */       
/*     */       public void run() {
/* 631 */         scala.Predef$.MODULE$.require((Future.InternalCallbackExecutor$.MODULE$.scala$concurrent$Future$InternalCallbackExecutor$$_tasksLocal().get() == null));
/* 633 */         BlockContext prevBlockContext = BlockContext$.MODULE$.current();
/* 634 */         BlockContext$.MODULE$.withBlockContext(this, 
/* 635 */             (Function0<?>)new Future$InternalCallbackExecutor$Batch$$anonfun$run$1(this, prevBlockContext));
/*     */       }
/*     */       
/*     */       public class Future$InternalCallbackExecutor$Batch$$anonfun$run$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         private final BlockContext prevBlockContext$1;
/*     */         
/*     */         public final void apply() {
/* 635 */           apply$mcV$sp();
/*     */         }
/*     */         
/*     */         public Future$InternalCallbackExecutor$Batch$$anonfun$run$1(Future.InternalCallbackExecutor$.atch $outer, BlockContext prevBlockContext$1) {}
/*     */         
/*     */         public void apply$mcV$sp() {
/*     */           try {
/* 636 */             this.$outer.scala$concurrent$Future$InternalCallbackExecutor$Batch$$parentBlockContext = this.prevBlockContext$1;
/* 658 */             processBatch$1(this.$outer.initial());
/*     */             return;
/*     */           } finally {
/* 660 */             Future.InternalCallbackExecutor$.MODULE$.scala$concurrent$Future$InternalCallbackExecutor$$_tasksLocal().remove();
/* 661 */             this.$outer.scala$concurrent$Future$InternalCallbackExecutor$Batch$$parentBlockContext = null;
/*     */           } 
/*     */         }
/*     */         
/*     */         private final void processBatch$1(List batch) {
/*     */           // Byte code:
/*     */           //   0: getstatic scala/collection/immutable/Nil$.MODULE$ : Lscala/collection/immutable/Nil$;
/*     */           //   3: dup
/*     */           //   4: ifnonnull -> 15
/*     */           //   7: pop
/*     */           //   8: aload_1
/*     */           //   9: ifnull -> 22
/*     */           //   12: goto -> 23
/*     */           //   15: aload_1
/*     */           //   16: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */           //   19: ifeq -> 23
/*     */           //   22: return
/*     */           //   23: aload_1
/*     */           //   24: instanceof scala/collection/immutable/$colon$colon
/*     */           //   27: ifeq -> 76
/*     */           //   30: aload_1
/*     */           //   31: checkcast scala/collection/immutable/$colon$colon
/*     */           //   34: astore_2
/*     */           //   35: getstatic scala/concurrent/Future$InternalCallbackExecutor$.MODULE$ : Lscala/concurrent/Future$InternalCallbackExecutor$;
/*     */           //   38: invokevirtual scala$concurrent$Future$InternalCallbackExecutor$$_tasksLocal : ()Ljava/lang/ThreadLocal;
/*     */           //   41: aload_2
/*     */           //   42: invokevirtual tl$1 : ()Lscala/collection/immutable/List;
/*     */           //   45: invokevirtual set : (Ljava/lang/Object;)V
/*     */           //   48: aload_2
/*     */           //   49: invokevirtual hd$1 : ()Ljava/lang/Object;
/*     */           //   52: checkcast java/lang/Runnable
/*     */           //   55: invokeinterface run : ()V
/*     */           //   60: getstatic scala/concurrent/Future$InternalCallbackExecutor$.MODULE$ : Lscala/concurrent/Future$InternalCallbackExecutor$;
/*     */           //   63: invokevirtual scala$concurrent$Future$InternalCallbackExecutor$$_tasksLocal : ()Ljava/lang/ThreadLocal;
/*     */           //   66: invokevirtual get : ()Ljava/lang/Object;
/*     */           //   69: checkcast scala/collection/immutable/List
/*     */           //   72: astore_1
/*     */           //   73: goto -> 0
/*     */           //   76: new scala/MatchError
/*     */           //   79: dup
/*     */           //   80: aload_1
/*     */           //   81: invokespecial <init> : (Ljava/lang/Object;)V
/*     */           //   84: athrow
/*     */           //   85: astore #4
/*     */           //   87: getstatic scala/concurrent/Future$InternalCallbackExecutor$.MODULE$ : Lscala/concurrent/Future$InternalCallbackExecutor$;
/*     */           //   90: invokevirtual scala$concurrent$Future$InternalCallbackExecutor$$_tasksLocal : ()Ljava/lang/ThreadLocal;
/*     */           //   93: invokevirtual get : ()Ljava/lang/Object;
/*     */           //   96: checkcast scala/collection/immutable/List
/*     */           //   99: astore_3
/*     */           //   100: getstatic scala/concurrent/Future$InternalCallbackExecutor$.MODULE$ : Lscala/concurrent/Future$InternalCallbackExecutor$;
/*     */           //   103: invokevirtual scala$concurrent$Future$InternalCallbackExecutor$$_tasksLocal : ()Ljava/lang/ThreadLocal;
/*     */           //   106: getstatic scala/collection/immutable/Nil$.MODULE$ : Lscala/collection/immutable/Nil$;
/*     */           //   109: invokevirtual set : (Ljava/lang/Object;)V
/*     */           //   112: getstatic scala/concurrent/Future$InternalCallbackExecutor$.MODULE$ : Lscala/concurrent/Future$InternalCallbackExecutor$;
/*     */           //   115: new scala/concurrent/Future$InternalCallbackExecutor$Batch
/*     */           //   118: dup
/*     */           //   119: aload_3
/*     */           //   120: invokespecial <init> : (Lscala/collection/immutable/List;)V
/*     */           //   123: invokevirtual scala$concurrent$Future$InternalCallbackExecutor$$unbatchedExecute : (Ljava/lang/Runnable;)V
/*     */           //   126: aload #4
/*     */           //   128: athrow
/*     */           // Line number table:
/*     */           //   Java source line number -> byte code offset
/*     */           //   #639	-> 0
/*     */           //   #638	-> 0
/*     */           //   #638	-> 22
/*     */           //   #640	-> 23
/*     */           //   #641	-> 35
/*     */           //   #638	-> 41
/*     */           //   #641	-> 42
/*     */           //   #638	-> 48
/*     */           //   #643	-> 49
/*     */           //   #655	-> 60
/*     */           //   #638	-> 76
/*     */           //   #645	-> 85
/*     */           //   #642	-> 85
/*     */           //   #650	-> 87
/*     */           //   #651	-> 100
/*     */           //   #652	-> 112
/*     */           //   #653	-> 126
/*     */           // Local variable table:
/*     */           //   start	length	slot	name	descriptor
/*     */           //   0	129	0	this	Lscala/concurrent/Future$InternalCallbackExecutor$Batch$$anonfun$run$1;
/*     */           //   0	129	1	batch	Lscala/collection/immutable/List;
/*     */           //   100	29	3	remaining	Lscala/collection/immutable/List;
/*     */           // Exception table:
/*     */           //   from	to	target	type
/*     */           //   48	60	85	finally
/*     */         }
/*     */       }
/*     */       
/*     */       public <T> T blockOn(Function0<T> thunk, CanAwait permission) {
/* 669 */         List<Runnable> tasks = Future.InternalCallbackExecutor$.MODULE$.scala$concurrent$Future$InternalCallbackExecutor$$_tasksLocal().get();
/* 670 */         Future.InternalCallbackExecutor$.MODULE$.scala$concurrent$Future$InternalCallbackExecutor$$_tasksLocal().set(scala.collection.immutable.Nil$.MODULE$);
/* 671 */         if (tasks != null && tasks.nonEmpty())
/* 672 */           Future.InternalCallbackExecutor$.MODULE$.scala$concurrent$Future$InternalCallbackExecutor$$unbatchedExecute(new atch(tasks)); 
/* 676 */         scala.Predef$.MODULE$.require((this.scala$concurrent$Future$InternalCallbackExecutor$Batch$$parentBlockContext != null));
/* 677 */         return this.scala$concurrent$Future$InternalCallbackExecutor$Batch$$parentBlockContext.blockOn(thunk, permission);
/*     */       }
/*     */     }
/*     */     
/*     */     public void execute(Runnable runnable) {
/* 681 */       if (runnable instanceof OnCompleteRunnable) {
/* 684 */         List list = scala$concurrent$Future$InternalCallbackExecutor$$_tasksLocal().get();
/* 685 */         if (list == null) {
/* 685 */           (new Runnable[1])[0] = runnable;
/* 685 */           scala$concurrent$Future$InternalCallbackExecutor$$unbatchedExecute(new atch(scala.collection.immutable.List$.MODULE$.apply((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new Runnable[1]))));
/*     */         } else {
/* 686 */           scala$concurrent$Future$InternalCallbackExecutor$$_tasksLocal().set(list.$colon$colon(runnable));
/*     */         } 
/*     */       } else {
/* 691 */         scala$concurrent$Future$InternalCallbackExecutor$$unbatchedExecute(runnable);
/*     */       } 
/*     */     }
/*     */     
/*     */     public void scala$concurrent$Future$InternalCallbackExecutor$$unbatchedExecute(Runnable r) {
/* 694 */       r.run();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\Future.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */