/*    */ package scala.collection.parallel;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.Predef$;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.GenTraversableOnce;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.Set;
/*    */ import scala.collection.Set$;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.CanCombineFrom;
/*    */ import scala.collection.parallel.mutable.ParArray;
/*    */ import scala.collection.parallel.mutable.ResizableParArrayCombiner;
/*    */ import scala.collection.parallel.mutable.package$;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005\rs!B\001\003\021\003I\021a\0029bG.\fw-\032\006\003\007\021\t\001\002]1sC2dW\r\034\006\003\013\031\t!bY8mY\026\034G/[8o\025\0059\021!B:dC2\f7\001\001\t\003\025-i\021A\001\004\006\031\tA\t!\004\002\ba\006\0347.Y4f'\tYa\002\005\002\020!5\ta!\003\002\022\r\t1\021I\\=SK\032DQaE\006\005\002Q\ta\001P5oSRtD#A\005\t\017YY!\031!C\001/\005aQ*\023(`\r>\023vlQ(Q3V\t\001\004\005\002\0203%\021!D\002\002\004\023:$\bB\002\017\fA\003%\001$A\007N\023:{fi\024*`\007>\003\026\f\t\005\b=-\021\r\021\"\001\030\003)\031\005*R\"L?J\013E+\022\005\007A-\001\013\021\002\r\002\027\rCUiQ&`%\006#V\t\t\005\bE-\021\r\021\"\001$\003\025\031\026K\025+3+\005!\003CA\b&\023\t1cA\001\004E_V\024G.\032\005\007Q-\001\013\021\002\023\002\rM\013&\013\026\032!\021\035Q3B1A\005\002]\t1#\031<bS2\f'\r\\3Qe>\034Wm]:peNDa\001L\006!\002\023A\022\001F1wC&d\027M\0317f!J|7-Z:t_J\034\b\005C\003/\027\021\005q&A\tuQJ,7\017[8mI\032\023x.\\*ju\026$2\001\007\0313\021\025\tT\0061\001\031\003\t\031(\020C\0034[\001\007\001$\001\tqCJ\fG\016\\3mSNlG*\032<fY\"1Qg\003C\001\005Y\n1\"\0368tkB\004xN\035;fIV\tq\007\005\002\020q%\021\021H\002\002\b\035>$\b.\0338h\021\031Y4\002\"\001\003y\005iQO\\:vaB|'\017^3e_B$\"aN\037\t\013yR\004\031A \002\0075\034x\r\005\002A\007:\021q\"Q\005\003\005\032\ta\001\025:fI\0264\027B\001#F\005\031\031FO]5oO*\021!I\002\005\007\017.!\tA\001%\002\027=,Ho\0344c_VtGm\035\013\003o%CQA\023$A\002a\t1!\0333y\021\031a5\002\"\001\003\033\006qq-\032;UCN\\7+\0369q_J$X#\001(\021\005)y\025B\001)\003\005-!\026m]6TkB\004xN\035;\t\017I[!\031!C\001\033\006\021B-\0324bk2$H+Y:l'V\004\bo\034:u\021\031!6\002)A\005\035\006\031B-\0324bk2$H+Y:l'V\004\bo\034:uA!)ak\003C\001/\006q1/\032;UCN\\7+\0369q_J$XC\001-\\)\rI\026m\031\t\0035nc\001\001B\003]+\n\007QL\001\003D_2d\027CA\034_!\tyq,\003\002a\r\t\031\021I\\=\t\013\t,\006\031A-\002\003\rDQ\001Z+A\0029\013\021\001\036\005\006M.!\031aZ\001\fM\006\034Go\034:ze=\0048/\006\003iaN4HCA5y%\rQg\002\034\004\005W\026\004\021N\001\007=e\0264\027N\\3nK:$h\bE\003\013[>\024X/\003\002o\005\tQa)Y2u_JLx\n]:\021\005i\003H!B9f\005\004i&\001\002$s_6\004\"AW:\005\013Q,'\031A/\003\t\025cW-\034\t\0035Z$Qa^3C\002u\023!\001V8\t\013e,\007\031\001>\002\005\t4\007#B>_J,X\"\001?\013\005u$\021aB4f]\026\024\030nY\005\003r\024AbQ1o\005VLG\016\032$s_6Dq!a\001\f\t\007\t)!A\bue\0064XM]:bE2,'g\0349t+\021\t9!!\006\025\t\005%\021\021\004\n\006\003\027q\021Q\002\004\007W\006\005\001!!\003\021\013)\ty!a\005\n\007\005E!A\001\bUe\0064XM]:bE2,w\n]:\021\007i\013)\002B\004\002\030\005\005!\031A/\003\003QCq\001ZA\001\001\004\tY\002\005\004\002\036\005}\0211C\007\002\t%\031\021\021\005\003\003%\035+g\016\026:bm\026\0248/\0312mK>s7-\032\005\b\003KYA1AA\024\0035!\bN]8xC\ndWMM8qgR!\021\021FA\032%\025\tYCDA\027\r\031Y\0271\005\001\002*A\031!\"a\f\n\007\005E\"A\001\007UQJ|w/\0312mK>\0038\017\003\005\0026\005\r\002\031AA\034\003\021\031X\r\0344\021\t\005e\022Q\b\b\004\037\005m\022BA\001\007\023\021\ty$!\021\003\023QC'o\\<bE2,'BA\001\007\001")
/*    */ public final class package {
/*    */   public static ThrowableOps throwable2ops(Throwable paramThrowable) {
/*    */     return package$.MODULE$.throwable2ops(paramThrowable);
/*    */   }
/*    */   
/*    */   public static <T> Object traversable2ops(GenTraversableOnce<T> paramGenTraversableOnce) {
/*    */     return package$.MODULE$.traversable2ops(paramGenTraversableOnce);
/*    */   }
/*    */   
/*    */   public static <From, Elem, To> Object factory2ops(CanBuildFrom<From, Elem, To> paramCanBuildFrom) {
/*    */     return package$.MODULE$.factory2ops(paramCanBuildFrom);
/*    */   }
/*    */   
/*    */   public static <Coll> Coll setTaskSupport(Coll paramColl, TaskSupport paramTaskSupport) {
/*    */     return package$.MODULE$.setTaskSupport(paramColl, paramTaskSupport);
/*    */   }
/*    */   
/*    */   public static TaskSupport defaultTaskSupport() {
/*    */     return package$.MODULE$.defaultTaskSupport();
/*    */   }
/*    */   
/*    */   public static int thresholdFromSize(int paramInt1, int paramInt2) {
/*    */     return package$.MODULE$.thresholdFromSize(paramInt1, paramInt2);
/*    */   }
/*    */   
/*    */   public static int availableProcessors() {
/*    */     return package$.MODULE$.availableProcessors();
/*    */   }
/*    */   
/*    */   public static double SQRT2() {
/*    */     return package$.MODULE$.SQRT2();
/*    */   }
/*    */   
/*    */   public static int CHECK_RATE() {
/*    */     return package$.MODULE$.CHECK_RATE();
/*    */   }
/*    */   
/*    */   public static int MIN_FOR_COPY() {
/*    */     return package$.MODULE$.MIN_FOR_COPY();
/*    */   }
/*    */   
/*    */   public static class package$$anon$4 implements FactoryOps<From, Elem, To> {
/*    */     private final CanBuildFrom bf$1;
/*    */     
/*    */     public package$$anon$4(CanBuildFrom bf$1) {
/* 60 */       FactoryOps$class.$init$(this);
/*    */     }
/*    */     
/*    */     public boolean isParallel() {
/* 61 */       return this.bf$1 instanceof scala.collection.Parallel;
/*    */     }
/*    */     
/*    */     public CanCombineFrom<From, Elem, To> asParallel() {
/* 62 */       return (CanCombineFrom<From, Elem, To>)this.bf$1;
/*    */     }
/*    */     
/*    */     public <R> Object ifParallel(Function1 isbody) {
/* 63 */       return new package$$anon$4$$anon$5(this, isbody);
/*    */     }
/*    */     
/*    */     public class package$$anon$4$$anon$5 implements FactoryOps<From, Elem, To>.Otherwise<R> {
/*    */       private final Function1 isbody$2;
/*    */       
/*    */       public package$$anon$4$$anon$5(package$$anon$4 $outer, Function1 isbody$2) {}
/*    */       
/*    */       public R otherwise(Function0 notbody) {
/* 64 */         return this.$outer.isParallel() ? (R)this.isbody$2.apply(this.$outer.asParallel()) : (R)notbody.apply();
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public static class package$$anon$2 implements TraversableOps<T> {
/*    */     private final GenTraversableOnce t$1;
/*    */     
/*    */     public package$$anon$2(GenTraversableOnce t$1) {
/* 67 */       TraversableOps$class.$init$(this);
/*    */     }
/*    */     
/*    */     public boolean isParallel() {
/* 68 */       return this.t$1 instanceof scala.collection.Parallel;
/*    */     }
/*    */     
/*    */     public boolean isParIterable() {
/* 69 */       return this.t$1 instanceof ParIterable;
/*    */     }
/*    */     
/*    */     public ParIterable<T> asParIterable() {
/* 70 */       return (ParIterable<T>)this.t$1;
/*    */     }
/*    */     
/*    */     public boolean isParSeq() {
/* 71 */       return this.t$1 instanceof ParSeq;
/*    */     }
/*    */     
/*    */     public ParSeq<T> asParSeq() {
/* 72 */       return (ParSeq<T>)this.t$1;
/*    */     }
/*    */     
/*    */     public <R> Object ifParSeq(Function1 isbody) {
/* 73 */       return new package$$anon$2$$anon$3(this, isbody);
/*    */     }
/*    */     
/*    */     public class package$$anon$2$$anon$3 implements TraversableOps<T>.Otherwise<R> {
/*    */       private final Function1 isbody$1;
/*    */       
/*    */       public package$$anon$2$$anon$3(package$$anon$2 $outer, Function1 isbody$1) {}
/*    */       
/*    */       public R otherwise(Function0 notbody) {
/* 74 */         return this.$outer.isParallel() ? (R)this.isbody$1.apply(this.$outer.asParSeq()) : (R)notbody.apply();
/*    */       }
/*    */     }
/*    */     
/*    */     public ParArray<T> toParArray() {
/* 77 */       Iterator it = this.t$1.toIterator();
/* 78 */       ResizableParArrayCombiner cb = package$.MODULE$.ParArrayCombiner().apply();
/* 79 */       for (; it.hasNext(); cb.$plus$eq(it.next()));
/* 80 */       return (this.t$1 instanceof ParArray) ? (ParArray<T>)this.t$1 : (ParArray<T>)cb.result();
/*    */     }
/*    */   }
/*    */   
/*    */   public static class package$$anon$1 implements ThrowableOps {
/*    */     private final Throwable self$1;
/*    */     
/*    */     public package$$anon$1(Throwable self$1) {}
/*    */     
/*    */     public Throwable alongWith(Throwable that) {
/*    */       CompositeThrowable compositeThrowable;
/* 84 */       Tuple2 tuple2 = new Tuple2(this.self$1, that);
/* 84 */       if (tuple2 != null && tuple2
/* 85 */         ._1() instanceof CompositeThrowable) {
/* 85 */         CompositeThrowable compositeThrowable1 = (CompositeThrowable)tuple2._1();
/* 85 */         if (tuple2._2() instanceof CompositeThrowable) {
/* 85 */           CompositeThrowable compositeThrowable2 = (CompositeThrowable)tuple2._2();
/* 85 */           return new CompositeThrowable(compositeThrowable1.throwables().$plus$plus((GenTraversableOnce)compositeThrowable2.throwables()));
/*    */         } 
/*    */       } 
/* 86 */       if (tuple2 != null && tuple2._1() instanceof CompositeThrowable) {
/* 86 */         CompositeThrowable compositeThrowable1 = (CompositeThrowable)tuple2._1();
/* 86 */         compositeThrowable = new CompositeThrowable(compositeThrowable1.throwables().$plus(that));
/* 87 */       } else if (tuple2 != null && tuple2._2() instanceof CompositeThrowable) {
/* 87 */         CompositeThrowable compositeThrowable1 = (CompositeThrowable)tuple2._2();
/* 87 */         compositeThrowable = new CompositeThrowable(compositeThrowable1.throwables().$plus(this.self$1));
/*    */       } else {
/* 88 */         (new Throwable[2])[0] = this.self$1;
/* 88 */         (new Throwable[2])[1] = that;
/* 88 */         compositeThrowable = new CompositeThrowable((Set<Throwable>)Set$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new Throwable[2])));
/*    */       } 
/*    */       return compositeThrowable;
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\package.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */