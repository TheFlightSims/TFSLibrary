/*    */ package scala.concurrent.impl;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Option;
/*    */ import scala.Predef$;
/*    */ import scala.concurrent.ExecutionContext;
/*    */ import scala.concurrent.OnCompleteRunnable;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.util.Try;
/*    */ import scala.util.control.NonFatal$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001a3A!\001\002\005\023\t\0012)\0317mE\006\0347NU;o]\006\024G.\032\006\003\007\021\tA![7qY*\021QAB\001\013G>t7-\036:sK:$(\"A\004\002\013M\034\027\r\\1\004\001U\021!\"M\n\005\001-\031b\003\005\002\r#5\tQB\003\002\017\037\005!A.\0318h\025\005\001\022\001\0026bm\006L!AE\007\003\r=\023'.Z2u!\taA#\003\002\026\033\tA!+\0368oC\ndW\r\005\002\03015\tA!\003\002\032\t\t\021rJ\\\"p[BdW\r^3Sk:t\027M\0317f\021!Y\002A!b\001\n\003a\022\001C3yK\016,Ho\034:\026\003u\001\"a\006\020\n\005}!!\001E#yK\016,H/[8o\007>tG/\032=u\021!\t\003A!A!\002\023i\022!C3yK\016,Ho\034:!\021!\031\003A!b\001\n\003!\023AC8o\007>l\007\017\\3uKV\tQ\005\005\003'O%:T\"\001\004\n\005!2!!\003$v]\016$\030n\03482!\rQSfL\007\002W)\021AFB\001\005kRLG.\003\002/W\t\031AK]=\021\005A\nD\002\001\003\006e\001\021\ra\r\002\002)F\021Ag\016\t\003MUJ!A\016\004\003\0179{G\017[5oOB\021a\005O\005\003s\031\0211!\0218z\021!Y\004A!A!\002\023)\023aC8o\007>l\007\017\\3uK\002BQ!\020\001\005\002y\na\001P5oSRtDcA B\005B\031\001\tA\030\016\003\tAQa\007\037A\002uAQa\t\037A\002\025Bq\001\022\001A\002\023\005Q)A\003wC2,X-F\001*\021\0359\005\0011A\005\002!\013\021B^1mk\026|F%Z9\025\005%c\005C\001\024K\023\tYeA\001\003V]&$\bbB'G\003\003\005\r!K\001\004q\022\n\004BB(\001A\003&\021&\001\004wC2,X\r\t\005\006#\002!\tEU\001\004eVtG#A%\t\013Q\003A\021A+\002!\025DXmY;uK^KG\017\033,bYV,GCA%W\021\02596\0131\001*\003\0051\b")
/*    */ public class CallbackRunnable<T> implements Runnable, OnCompleteRunnable {
/*    */   private final ExecutionContext executor;
/*    */   
/*    */   private final Function1<Try<T>, Object> onComplete;
/*    */   
/*    */   public ExecutionContext executor() {
/* 26 */     return this.executor;
/*    */   }
/*    */   
/*    */   public Function1<Try<T>, Object> onComplete() {
/* 26 */     return this.onComplete;
/*    */   }
/*    */   
/* 28 */   private Try<T> value = null;
/*    */   
/*    */   public Try<T> value() {
/* 28 */     return this.value;
/*    */   }
/*    */   
/*    */   public void value_$eq(Try<T> x$1) {
/* 28 */     this.value = x$1;
/*    */   }
/*    */   
/*    */   public void run() {
/* 31 */     Predef$.MODULE$.require((value() != null));
/*    */     try {
/* 32 */       onComplete().apply(value());
/*    */     } finally {
/* 32 */       Exception exception = null;
/* 32 */       Option option = NonFatal$.MODULE$.unapply(exception);
/* 32 */       if (option.isEmpty())
/* 32 */         throw exception; 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void executeWithValue(Try<T> v) {
/* 36 */     Predef$.MODULE$.require((value() == null));
/*    */   }
/*    */   
/*    */   public CallbackRunnable(ExecutionContext executor, Function1<Try<T>, Object> onComplete) {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\impl\CallbackRunnable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */