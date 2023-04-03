/*     */ package scala.collection;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.Nothing$;
/*     */ import scala.runtime.ObjectRef;
/*     */ import scala.runtime.StringAdd$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001M;a!\001\002\t\002\t1\021A\003#fEV<W\013^5mg*\0211\001B\001\013G>dG.Z2uS>t'\"A\003\002\013M\034\027\r\\1\021\005\035AQ\"\001\002\007\r%\021\001\022\001\002\013\005)!UMY;h+RLGn]\n\003\021-\001\"\001D\007\016\003\021I!A\004\003\003\r\005s\027PU3g\021\025\001\002\002\"\001\023\003\031a\024N\\5u}\r\001A#\001\004\t\013QAA\021A\013\002\027Ut7/\0369q_J$X\r\032\013\003-e\001\"\001D\f\n\005a!!a\002(pi\"Lgn\032\005\0065M\001\raG\001\004[N<\007C\001\017 \035\taQ$\003\002\037\t\0051\001K]3eK\032L!\001I\021\003\rM#(/\0338h\025\tqB\001C\003$\021\021\005A%A\007o_N+8\r[#mK6,g\016\036\013\003-\025BQA\007\022A\002mAQa\n\005\005\002!\n\001#\0338eKb|U\017^(g\005>,h\016Z:\025\005YI\003\"\002\026'\001\004Y\023!B5oI\026D\bC\001\007-\023\tiCAA\002J]RDQa\f\005\005\002A\nq\"\0337mK\036\fG.\021:hk6,g\016\036\013\003-EBQA\007\030A\002mAQa\r\005\005\002Q\n1BY;jY\022\034FO]5oOR\0211$\016\005\006mI\002\raN\001\bG2|7/\036:f!\021a\001H\017 \n\005e\"!!\003$v]\016$\030n\03482!\021a\001h\017 \021\0051a\024BA\037\005\005\r\te.\037\t\003\031}J!\001\021\003\003\tUs\027\016\036\005\006\005\"!\taQ\001\fCJ\024\030-_*ue&tw-\006\002E\031R!1$R(R\021\0251\025\t1\001H\003\025\t'O]1z!\ra\001JS\005\003\023\022\021Q!\021:sCf\004\"a\023'\r\001\021)Q*\021b\001\035\n\tA+\005\002\027w!)\001+\021a\001W\005!aM]8n\021\025\021\026\t1\001,\003\025)h\016^5m\001")
/*     */ public final class DebugUtils {
/*     */   public static <T> String arrayString(Object paramObject, int paramInt1, int paramInt2) {
/*     */     return DebugUtils$.MODULE$.arrayString(paramObject, paramInt1, paramInt2);
/*     */   }
/*     */   
/*     */   public static String buildString(Function1<Function1<Object, BoxedUnit>, BoxedUnit> paramFunction1) {
/*     */     return DebugUtils$.MODULE$.buildString(paramFunction1);
/*     */   }
/*     */   
/*     */   public static Nothing$ illegalArgument(String paramString) {
/*     */     return DebugUtils$.MODULE$.illegalArgument(paramString);
/*     */   }
/*     */   
/*     */   public static Nothing$ indexOutOfBounds(int paramInt) {
/*     */     return DebugUtils$.MODULE$.indexOutOfBounds(paramInt);
/*     */   }
/*     */   
/*     */   public static Nothing$ noSuchElement(String paramString) {
/*     */     return DebugUtils$.MODULE$.noSuchElement(paramString);
/*     */   }
/*     */   
/*     */   public static Nothing$ unsupported(String paramString) {
/*     */     return DebugUtils$.MODULE$.unsupported(paramString);
/*     */   }
/*     */   
/*     */   public static class DebugUtils$$anonfun$buildString$1 extends AbstractFunction1<Object, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ObjectRef output$1;
/*     */     
/*     */     public final void apply(Object x$1) {
/* 103 */       this.output$1.elem = (new StringBuilder()).append(this.output$1.elem).append(StringAdd$.MODULE$.$plus$extension(Predef$.MODULE$.any2stringadd(x$1), "\n")).toString();
/*     */     }
/*     */     
/*     */     public DebugUtils$$anonfun$buildString$1(ObjectRef output$1) {}
/*     */   }
/*     */   
/*     */   public static class DebugUtils$$anonfun$arrayString$1 extends AbstractFunction1<T, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(Object x0$1) {
/*     */       String str;
/* 109 */       if (x0$1 == null) {
/* 109 */         str = "n/a";
/*     */       } else {
/* 111 */         str = String.valueOf(x0$1);
/*     */       } 
/*     */       return str;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\DebugUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */