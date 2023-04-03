/*     */ package scala.util;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.Some;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.util.control.NonFatal$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\005e!B\001\003\003C9!a\001+ss*\0211\001B\001\005kRLGNC\001\006\003\025\0318-\0317b\007\001)\"\001\003\013\024\005\001I\001C\001\006\f\033\005!\021B\001\007\005\005\031\te.\037*fM\")a\002\001C\001\037\0051A(\0338jiz\"\022\001\005\t\004#\001\021R\"\001\002\021\005M!B\002\001\003\007+\001!)\031\001\f\003\003Q\013\"a\006\016\021\005)A\022BA\r\005\005\035qu\016\0365j]\036\004\"AC\016\n\005q!!aA!os\")a\004\001D\001?\005I\021n\035$bS2,(/Z\013\002AA\021!\"I\005\003E\021\021qAQ8pY\026\fg\016C\003%\001\031\005q$A\005jgN+8mY3tg\")a\005\001C\001O\005Iq-\032;Pe\026c7/Z\013\003Q)\"\"!K\027\021\005MQC!B\026&\005\004a#!A+\022\005IQ\002B\002\030&\t\003\007q&A\004eK\032\fW\017\034;\021\007)\001\024&\003\0022\t\tAAHY=oC6,g\bC\0034\001\021\005A'\001\004pe\026c7/Z\013\003ka\"\"AN\035\021\007E\001q\007\005\002\024q\021)1F\rb\001Y!1aF\rCA\002i\0022A\003\0317\021\025a\004A\"\001>\003\r9W\r^\013\002%!)q\b\001D\001\001\0069am\034:fC\016DWCA!L)\t\021U\t\005\002\013\007&\021A\t\002\002\005+:LG\017C\003G}\001\007q)A\001g!\021Q\001J\005&\n\005%#!!\003$v]\016$\030n\03482!\t\0312\nB\003,}\t\007a\003C\003N\001\031\005a*A\004gY\006$X*\0319\026\005=\023FC\001)T!\r\t\002!\025\t\003'I#Qa\013'C\002YAQA\022'A\002Q\003BA\003%\023!\")a\013\001D\001/\006\031Q.\0319\026\005a[FCA-]!\r\t\002A\027\t\003'm#QaK+C\002YAQAR+A\002u\003BA\003%\0235\")q\f\001D\001A\0061a-\0337uKJ$\"\001E1\t\013\tt\006\031A2\002\003A\004BA\003%\023A!)Q\r\001D\001M\006Y!/Z2pm\026\024x+\033;i+\t9'\016\006\002iWB\031\021\003A5\021\005MQG!B\026e\005\004a\003\"\002$e\001\004a\007\003\002\006n_\"L!A\034\003\003\037A\013'\017^5bY\032+hn\031;j_:\004\"\001\035=\017\005E4hB\001:v\033\005\031(B\001;\007\003\031a$o\\8u}%\tQ!\003\002x\t\0059\001/Y2lC\036,\027BA={\005%!\006N]8xC\ndWM\003\002x\t!)A\020\001D\001{\0069!/Z2pm\026\024Xc\001@\002\004Q\031q0!\002\021\tE\001\021\021\001\t\004'\005\rA!B\026|\005\004a\003B\002$|\001\004\t9\001E\003\013[>\f\t\001C\004\002\f\001!\t!!\004\002\021Q|w\n\035;j_:,\"!a\004\021\t)\t\tBE\005\004\003'!!AB(qi&|g\016C\004\002\030\0011\t!!\007\002\017\031d\027\r\036;f]V!\0211DA\021)\021\ti\"a\t\021\tE\001\021q\004\t\004'\005\005BAB\026\002\026\t\007a\003\003\005\002&\005U\0019AA\024\003\t)g\017E\004\002*\005=\"#!\b\017\007)\tY#C\002\002.\021\ta\001\025:fI\0264\027\002BA\031\003g\021\001\003\n7fgN$3m\0347p]\022bWm]:\013\007\0055B\001C\004\0028\0011\t!!\017\002\r\031\f\027\016\\3e+\t\tY\004E\002\022\001=Dq!a\020\001\t\003\t\t%A\005ue\006t7OZ8s[V!\0211IA%)\031\t)%a\023\002RA!\021\003AA$!\r\031\022\021\n\003\007W\005u\"\031\001\f\t\021\0055\023Q\ba\001\003\037\n\021a\035\t\006\025!\023\022Q\t\005\b\r\006u\002\031AA*!\025Q\001j\\A#S\025\001\021qKA.\023\r\tIF\001\002\b\r\006LG.\036:f\023\r\tiF\001\002\b'V\0347-Z:t\017\035\t\tG\001E\001\003G\n1\001\026:z!\r\t\022Q\r\004\007\003\tA\t!a\032\024\007\005\025\024\002C\004\017\003K\"\t!a\033\025\005\005\r\004\002CA8\003K\"\t!!\035\002\013\005\004\b\017\\=\026\t\005M\024\021\020\013\005\003k\nY\b\005\003\022\001\005]\004cA\n\002z\0211Q#!\034C\002YA\021\"! \002n\021\005\r!a \002\003I\004BA\003\031\002x\001")
/*     */ public abstract class Try<T> {
/*     */   public static <T> Try<T> apply(Function0<T> paramFunction0) {
/*     */     return Try$.MODULE$.apply(paramFunction0);
/*     */   }
/*     */   
/*     */   public abstract boolean isFailure();
/*     */   
/*     */   public abstract boolean isSuccess();
/*     */   
/*     */   public <U> U getOrElse(Function0 default) {
/*  77 */     return isSuccess() ? (U)get() : (U)default.apply();
/*     */   }
/*     */   
/*     */   public <U> Try<U> orElse(Function0 default) {
/*     */     try {
/*     */     
/*     */     } finally {
/*  82 */       Exception exception = null;
/*  84 */       Option option = NonFatal$.MODULE$.unapply(exception);
/*     */     } 
/*  84 */     return new Failure<U>((Throwable)option.get());
/*     */   }
/*     */   
/*     */   public abstract T get();
/*     */   
/*     */   public abstract <U> void foreach(Function1<T, U> paramFunction1);
/*     */   
/*     */   public abstract <U> Try<U> flatMap(Function1<T, Try<U>> paramFunction1);
/*     */   
/*     */   public abstract <U> Try<U> map(Function1<T, U> paramFunction1);
/*     */   
/*     */   public abstract Try<T> filter(Function1<T, Object> paramFunction1);
/*     */   
/*     */   public abstract <U> Try<U> recoverWith(PartialFunction<Throwable, Try<U>> paramPartialFunction);
/*     */   
/*     */   public abstract <U> Try<U> recover(PartialFunction<Throwable, U> paramPartialFunction);
/*     */   
/*     */   public Option<T> toOption() {
/* 128 */     return isSuccess() ? (Option<T>)new Some(get()) : (Option<T>)None$.MODULE$;
/*     */   }
/*     */   
/*     */   public abstract <U> Try<U> flatten(Predef$.less.colon.less<T, Try<U>> paramless);
/*     */   
/*     */   public abstract Try<Throwable> failed();
/*     */   
/*     */   public <U> Try<U> transform(Function1 s, Function1 f) {
/*     */     try {
/*     */       Try try_;
/*     */     } finally {
/* 146 */       Exception exception = null;
/* 150 */       Option option = NonFatal$.MODULE$.unapply(exception);
/*     */     } 
/* 150 */     return new Failure<U>((Throwable)option.get());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\Try.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */