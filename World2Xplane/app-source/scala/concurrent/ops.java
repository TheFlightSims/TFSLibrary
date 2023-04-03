/*    */ package scala.concurrent;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.Predef$;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.Nothing$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005=s!B\001\003\021\0039\021aA8qg*\0211\001B\001\013G>t7-\036:sK:$(\"A\003\002\013M\034\027\r\\1\004\001A\021\001\"C\007\002\005\031)!B\001E\001\027\t\031q\016]:\024\005%a\001CA\007\017\033\005!\021BA\b\005\005\031\te.\037*fM\")\021#\003C\001%\0051A(\0338jiz\"\022a\002\005\b)%\021\r\021\"\001\026\0035!WMZ1vYR\024VO\0348feV\ta\003\005\002\t/%\021\001D\001\002\021\rV$XO]3UCN\\'+\0368oKJDaAG\005!\002\0231\022A\0043fM\006,H\016\036*v]:,'\017\t\005\0069%!I!H\001\tiJL8)\031;dQV\021a\004\r\013\003?e\002B\001\t\025,]9\021\021E\n\b\003E\025j\021a\t\006\003I\031\ta\001\020:p_Rt\024\"A\003\n\005\035\"\021a\0029bG.\fw-Z\005\003S)\022a!R5uQ\026\024(BA\024\005!\t\001C&\003\002.U\tIA\013\033:po\006\024G.\032\t\003_Ab\001\001B\00327\t\007!GA\001B#\t\031d\007\005\002\016i%\021Q\007\002\002\b\035>$\b.\0338h!\tiq'\003\0029\t\t\031\021I\\=\t\riZB\0211\001<\003\021\021w\016Z=\021\0075ad&\003\002>\t\tAAHY=oC6,g\bC\003@\023\021%\001)\001\006hKR|%\017\0265s_^,2!\021%D)\t\021E\t\005\0020\007\022)\021G\020b\001e!)QI\020a\001\r\006\t\001\020\005\003!Q\035\023\005CA\030I\t\025IeH1\001K\005\005!\026CA\032,\021\025a\025\002\"\001N\003\025\031\b/Y<o)\tqu\013\006\002P%B\021Q\002U\005\003#\022\021A!\0268ji\"91k\023I\001\002\b!\026A\002:v]:,'\017\005\002\t+&\021aK\001\002\013)\006\0348NU;o]\026\024\bB\002-L\t\003\007\021,A\001q!\riAh\024\005\0067&!\t\001X\001\007MV$XO]3\026\005u\033GC\0010f)\tyF\rE\002\016A\nL!!\031\003\003\023\031+hn\031;j_:\004\004CA\030d\t\025\t$L1\0013\021\035\031&\f%AA\004YAa\001\027.\005\002\0041\007cA\007=E\")\001.\003C\001S\006\031\001/\031:\026\007)\004(\017F\002lkb$\"\001\034;\021\t5iw.]\005\003]\022\021a\001V;qY\026\024\004CA\030q\t\025\ttM1\0013!\ty#\017B\003tO\n\007!GA\001C\021\035\031v\r%AA\004QCaA^4\005\002\0049\030A\001=q!\riAh\034\005\007s\036$\t\031\001>\002\005e\004\bcA\007=c\"9A0CI\001\n\003i\030aD:qC^tG\005Z3gCVdG\017\n\032\025\007y\f\tB\013\002U.\022\021\021\001\t\005\003\007\ti!\004\002\002\006)!\021qAA\005\003%)hn\0315fG.,GMC\002\002\f\021\t!\"\0318o_R\fG/[8o\023\021\ty!!\002\003#Ut7\r[3dW\026$g+\031:jC:\034W\r\003\004Yw\022\005\r!\027\005\n\003+I\021\023!C\001\003/\t\001CZ;ukJ,G\005Z3gCVdG\017\n\032\026\t\005e\0211\005\013\005\0037\tiB\013\002\027\"A\001,a\005\005\002\004\ty\002\005\003\016y\005\005\002cA\030\002$\0211\021'a\005C\002IB\021\"a\n\n#\003%\t!!\013\002\033A\f'\017\n3fM\006,H\016\036\0234+\031\tY#a\r\002<Q)a0!\f\0026!Aa/!\n\005\002\004\ty\003\005\003\016y\005E\002cA\030\0024\0211\021'!\nC\002IB\001\"_A\023\t\003\007\021q\007\t\005\033q\nI\004E\0020\003w!aa]A\023\005\004\021\004fB\005\002@\005\025\023\021\n\t\004\033\005\005\023bAA\"\t\tQA-\0329sK\016\fG/\0323\"\005\005\035\023!F+tK\002\002g)\036;ve\026\004\007%\0338ti\026\fGML\021\003\003\027\naA\r\0302a9\002\004f\002\001\002@\005\025\023\021\n")
/*    */ public final class ops {
/*    */   public static <A, B> TaskRunner par$default$3(Function0<A> paramFunction0, Function0<B> paramFunction01) {
/*    */     return ops$.MODULE$.par$default$3(paramFunction0, paramFunction01);
/*    */   }
/*    */   
/*    */   public static <A> FutureTaskRunner future$default$2(Function0<A> paramFunction0) {
/*    */     return ops$.MODULE$.future$default$2(paramFunction0);
/*    */   }
/*    */   
/*    */   public static TaskRunner spawn$default$2(Function0<BoxedUnit> paramFunction0) {
/*    */     return ops$.MODULE$.spawn$default$2(paramFunction0);
/*    */   }
/*    */   
/*    */   public static <A, B> Tuple2<A, B> par(Function0<A> paramFunction0, Function0<B> paramFunction01, TaskRunner paramTaskRunner) {
/*    */     return ops$.MODULE$.par(paramFunction0, paramFunction01, paramTaskRunner);
/*    */   }
/*    */   
/*    */   public static <A> Function0<A> future(Function0<A> paramFunction0, FutureTaskRunner paramFutureTaskRunner) {
/*    */     return ops$.MODULE$.future(paramFunction0, paramFutureTaskRunner);
/*    */   }
/*    */   
/*    */   public static void spawn(Function0<BoxedUnit> paramFunction0, TaskRunner paramTaskRunner) {
/*    */     ops$.MODULE$.spawn(paramFunction0, paramTaskRunner);
/*    */   }
/*    */   
/*    */   public static FutureTaskRunner defaultRunner() {
/*    */     return ops$.MODULE$.defaultRunner();
/*    */   }
/*    */   
/*    */   public static class ops$$anonfun$getOrThrow$2 extends AbstractFunction1<A, A> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final A apply(Object x) {
/* 31 */       return (A)Predef$.MODULE$.identity(x);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class ops$$anonfun$getOrThrow$1 extends AbstractFunction1<T, Nothing$> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Nothing$ apply(Throwable x$1) {
/* 31 */       throw x$1;
/*    */     }
/*    */   }
/*    */   
/*    */   public static class ops$$anonfun$par$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Function0 yp$1;
/*    */     
/*    */     public final SyncVar y$1;
/*    */     
/*    */     public final void apply() {
/* 61 */       this.y$1.set(ops$.MODULE$.scala$concurrent$ops$$tryCatch(this.yp$1));
/*    */     }
/*    */     
/*    */     public void apply$mcV$sp() {
/* 61 */       this.y$1.set(ops$.MODULE$.scala$concurrent$ops$$tryCatch(this.yp$1));
/*    */     }
/*    */     
/*    */     public ops$$anonfun$par$1(Function0 yp$1, SyncVar y$1) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\ops.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */