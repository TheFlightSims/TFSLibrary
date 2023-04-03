/*     */ package akka.dispatch;
/*     */ 
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\r3Q!\001\002\002\002\035\021qAU3d_Z,'O\003\002\004\t\005AA-[:qCR\034\007NC\001\006\003\021\t7n[1\004\001U\021\001bE\n\003\001%\0012A\003\b\022\035\tYA\"D\001\003\023\ti!!\001\003kCBL\027BA\b\021\0055\021VmY8wKJ\024%/\0333hK*\021QB\001\t\003%Ma\001\001\002\004\025\001\021\025\r!\006\002\002)F\021a\003\b\t\003/ii\021\001\007\006\0023\005)1oY1mC&\0211\004\007\002\b\035>$\b.\0338h!\t9R$\003\002\0371\t\031\021I\\=\t\013\001\002A\021A\021\002\rqJg.\033;?)\005\021\003cA\006\001#!)A\005\001C+K\005A\021N\034;fe:\fG\016\006\002\022M!)qe\ta\001Q\0051!/Z:vYR\004\"!K\031\017\005)zcBA\026/\033\005a#BA\027\007\003\031a$o\\8u}%\t\021$\003\00211\0059\001/Y2lC\036,\027B\001\0324\005%!\006N]8xC\ndWM\003\00211!)Q\007\001D\001m\0059!/Z2pm\026\024HCA\t8\021\025AD\0071\001)\003\0351\027-\0337ve\026D3\001\016\036C!\r92(P\005\003ya\021a\001\0365s_^\034\bC\001\n?\t\025!\002A1\001@#\t1\002\t\005\002Bc9\021qcL\022\002Q\001")
/*     */ public abstract class Recover<T> extends japi.RecoverBridge<T> {
/*     */   public final T internal(Throwable result) {
/* 267 */     return recover(result);
/*     */   }
/*     */   
/*     */   public abstract T recover(Throwable paramThrowable) throws Throwable;
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\Recover.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */