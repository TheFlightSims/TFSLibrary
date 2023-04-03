/*     */ package scala.collection.parallel;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Set;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ 
/*     */ public final class CompositeThrowable$ extends AbstractFunction1<Set<Throwable>, CompositeThrowable> implements Serializable {
/*     */   public static final CompositeThrowable$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 138 */     return "CompositeThrowable";
/*     */   }
/*     */   
/*     */   public CompositeThrowable apply(Set<Throwable> throwables) {
/* 138 */     return new CompositeThrowable(throwables);
/*     */   }
/*     */   
/*     */   public Option<Set<Throwable>> unapply(CompositeThrowable x$0) {
/* 138 */     return (x$0 == null) ? (Option<Set<Throwable>>)scala.None$.MODULE$ : (Option<Set<Throwable>>)new Some(x$0.throwables());
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 138 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private CompositeThrowable$() {
/* 138 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public class CompositeThrowable$$anonfun$$init$$1 extends AbstractFunction1<Throwable, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(Throwable t) {
/* 142 */       scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 142 */       return (new StringBuilder()).append(scala.runtime.StringAdd$.MODULE$.$plus$extension(scala.Predef$.MODULE$.any2stringadd(t), "\n")).append(scala.Predef$.MODULE$.genericArrayOps(scala.Predef$.MODULE$.refArrayOps((Object[])scala.Predef$.MODULE$.refArrayOps((Object[])t.getStackTrace()).take(10)).$plus$plus((GenTraversableOnce)new StringOps("..."), scala.Array$.MODULE$.canBuildFrom(scala.reflect.ClassTag$.MODULE$.Any()))).mkString("\n")).toString();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\CompositeThrowable$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */