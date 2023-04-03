/*     */ package akka.dispatch;
/*     */ 
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001y2Q!\001\002\002\002\035\021\021b\0248GC&dWO]3\013\005\r!\021\001\0033jgB\fGo\0315\013\003\025\tA!Y6lC\016\0011C\001\001\t!\rIQ\002\005\b\003\025-i\021AA\005\003\031\t\tAA[1qS&\021ab\004\002\017\007\006dGNY1dW\n\023\030\016Z4f\025\ta!\001\005\002\02279\021!\003\007\b\003'Yi\021\001\006\006\003+\031\ta\001\020:p_Rt\024\"A\f\002\013M\034\027\r\\1\n\005eQ\022a\0029bG.\fw-\032\006\002/%\021A$\b\002\n)\"\024xn^1cY\026T!!\007\016\t\013}\001A\021\001\021\002\rqJg.\033;?)\005\t\003C\001\006\001\021\025\031\003\001\"\026%\003!Ig\016^3s]\006dGCA\023*!\t1s%D\001\033\023\tA#D\001\003V]&$\b\"\002\026#\001\004\001\022a\0024bS2,(/\032\005\006Y\0011\t!L\001\n_:4\025-\0337ve\026$\"!\n\030\t\013)Z\003\031\001\t)\007-\002T\bE\002'cMJ!A\r\016\003\rQD'o\\<t!\t!T\007\004\001\005\013Y\002!\031A\034\003\003Q\013\"\001O\036\021\005\031J\024B\001\036\033\005\035qu\016\0365j]\036\004\"\001P\016\017\005\031B2%\001\t")
/*     */ public abstract class OnFailure extends japi.CallbackBridge<Throwable> {
/*     */   public abstract void onFailure(Throwable paramThrowable) throws Throwable;
/*     */   
/*     */   public final void internal(Throwable failure) {
/* 228 */     onFailure(failure);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\OnFailure.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */