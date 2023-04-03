/*     */ package akka.dispatch;
/*     */ 
/*     */ import scala.MatchError;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.util.Failure;
/*     */ import scala.util.Success;
/*     */ import scala.util.Try;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\00193Q!\001\002\002\002\035\021!b\0248D_6\004H.\032;f\025\t\031A!\001\005eSN\004\030\r^2i\025\005)\021\001B1lW\006\034\001!\006\002\t7M\021\001!\003\t\004\0259\tbBA\006\r\033\005\021\021BA\007\003\003\021Q\027\r]5\n\005=\001\"AD\"bY2\024\027mY6Ce&$w-\032\006\003\033\t\0012AE\f\032\033\005\031\"B\001\013\026\003\021)H/\0337\013\003Y\tQa]2bY\006L!\001G\n\003\007Q\023\030\020\005\002\03371\001AA\002\017\001\021\013\007QDA\001U#\tq\"\005\005\002 A5\tQ#\003\002\"+\t9aj\034;iS:<\007CA\020$\023\t!SCA\002B]fDQA\n\001\005\002\035\na\001P5oSRtD#\001\025\021\007-\001\021\004C\003+\001\021U3&\001\005j]R,'O\\1m)\tas\006\005\002 [%\021a&\006\002\005+:LG\017C\0031S\001\007\021#A\003wC2,X\rC\0033\001\031\0051'\001\006p]\016{W\016\0357fi\026$2\001\f\033C\021\025)\024\0071\0017\003\0351\027-\0337ve\026\004\"aN \017\005ajdBA\035=\033\005Q$BA\036\007\003\031a$o\\8u}%\ta#\003\002?+\0059\001/Y2lC\036,\027B\001!B\005%!\006N]8xC\ndWM\003\002?+!)1)\ra\0013\00591/^2dKN\034\bfA\031F\033B\031qD\022%\n\005\035+\"A\002;ie><8\017\005\002\033\023\022)A\004\001b\001\025F\021ad\023\t\003\031~r!aH\037$\003Y\002")
/*     */ public abstract class OnComplete<T> extends japi.CallbackBridge<Try<T>> {
/*     */   public abstract void onComplete(Throwable paramThrowable, T paramT) throws Throwable;
/*     */   
/*     */   public final void internal(Try value) {
/* 245 */     Try try_ = value;
/* 246 */     if (try_ instanceof Failure) {
/* 246 */       Failure failure = (Failure)try_;
/* 246 */       Throwable t = failure.exception();
/* 246 */       null;
/* 246 */       onComplete(t, null);
/* 246 */       BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */     } else {
/* 247 */       if (try_ instanceof Success) {
/* 247 */         Success success = (Success)try_;
/* 247 */         Object r = success.value();
/* 247 */         null;
/* 247 */         onComplete(null, (T)r);
/* 247 */         BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */         return;
/*     */       } 
/*     */       throw new MatchError(try_);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\OnComplete.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */