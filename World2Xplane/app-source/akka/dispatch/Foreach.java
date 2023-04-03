/*     */ package akka.dispatch;
/*     */ 
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\0313Q!\001\002\002\002\035\021qAR8sK\006\034\007N\003\002\004\t\005AA-[:qCR\034\007NC\001\006\003\021\t7n[1\004\001U\021\001bE\n\003\001%\0012A\003\b\022\035\tYA\"D\001\003\023\ti!!\001\003kCBL\027BA\b\021\005I)f.\033;Gk:\034G/[8o\005JLGmZ3\013\0055\021\001C\001\n\024\031\001!a\001\006\001\t\006\004)\"!\001+\022\005Ya\002CA\f\033\033\005A\"\"A\r\002\013M\034\027\r\\1\n\005mA\"a\002(pi\"Lgn\032\t\003/uI!A\b\r\003\007\005s\027\020C\003!\001\021\005\021%\001\004=S:LGO\020\013\002EA\0311\002A\t\t\013\021\002AQI\023\002\021%tG/\032:oC2$\"AJ\025\021\005]9\023B\001\025\031\005\021)f.\033;\t\013)\032\003\031A\t\002\003QDQ\001\f\001\007\0025\nA!Z1dQR\021aE\f\005\006_-\002\r!E\001\007e\026\034X\017\034;)\007-\nd\bE\002\030eQJ!a\r\r\003\rQD'o\\<t!\t\021R\007B\003\025\001\t\007a'\005\002\027oA\021\001h\017\b\003/eJ!A\017\r\002\017A\f7m[1hK&\021A(\020\002\n)\"\024xn^1cY\026T!A\017\r$\003}\002\"\001Q\036\017\005\005KdB\001\"F\033\005\031%B\001#\007\003\031a$o\\8u}%\t\021\004")
/*     */ public abstract class Foreach<T> extends japi.UnitFunctionBridge<T> {
/*     */   public final void internal(Object t) {
/* 320 */     each((T)t);
/*     */   }
/*     */   
/*     */   public abstract void each(T paramT) throws Throwable;
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\Foreach.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */