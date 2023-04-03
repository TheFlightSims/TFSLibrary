/*     */ package akka.dispatch;
/*     */ 
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\0253Q!\001\002\002\002\035\021\021b\0248Tk\016\034Wm]:\013\005\r!\021\001\0033jgB\fGo\0315\013\003\025\tA!Y6lC\016\001QC\001\005\024'\t\001\021\002E\002\013\035Eq!a\003\007\016\003\tI!!\004\002\002\t)\f\007/[\005\003\037A\021abQ1mY\n\f7m\033\"sS\022<WM\003\002\016\005A\021!c\005\007\001\t\031!\002\001#b\001+\t\tA+\005\002\0279A\021qCG\007\0021)\t\021$A\003tG\006d\027-\003\002\0341\t9aj\034;iS:<\007CA\f\036\023\tq\002DA\002B]fDQ\001\t\001\005\002\005\na\001P5oSRtD#\001\022\021\007-\001\021\003C\003%\001\021US%\001\005j]R,'O\\1m)\t1\023\006\005\002\030O%\021\001\006\007\002\005+:LG\017C\003+G\001\007\021#\001\004sKN,H\016\036\005\006Y\0011\t!L\001\n_:\034VoY2fgN$\"A\n\030\t\013)Z\003\031A\t)\007-\002T\bE\002\030cMJ!A\r\r\003\rQD'o\\<t!\t\021B\007B\003\025\001\t\007Q'\005\002\027mA\021qG\017\b\003/aJ!!\017\r\002\017A\f7m[1hK&\0211\b\020\002\n)\"\024xn^1cY\026T!!\017\r$\003y\002\"a\020\036\017\005\001CdBA!E\033\005\021%BA\"\007\003\031a$o\\8u}%\t\021\004")
/*     */ public abstract class OnSuccess<T> extends japi.CallbackBridge<T> {
/*     */   public final void internal(Object result) {
/* 211 */     onSuccess((T)result);
/*     */   }
/*     */   
/*     */   public abstract void onSuccess(T paramT) throws Throwable;
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\OnSuccess.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */