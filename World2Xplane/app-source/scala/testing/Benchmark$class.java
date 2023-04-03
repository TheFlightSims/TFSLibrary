/*     */ package scala.testing;
/*     */ 
/*     */ import java.io.OutputStreamWriter;
/*     */ import scala.Console$;
/*     */ import scala.Function1;
/*     */ import scala.Predef$;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.List$;
/*     */ import scala.collection.immutable.StringLike;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.compat.Platform$;
/*     */ import scala.math.Integral;
/*     */ import scala.math.Numeric$IntIsIntegral$;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public abstract class Benchmark$class {
/*     */   public static void $init$(Benchmark $this) {
/*  49 */     $this.multiplier_$eq(1);
/*     */   }
/*     */   
/*     */   public static List runBenchmark(Benchmark $this, int noTimes) {
/*  55 */     return (List)((TraversableLike)List$.MODULE$.range(BoxesRunTime.boxToInteger(1), BoxesRunTime.boxToInteger(noTimes + 1), (Integral)Numeric$IntIsIntegral$.MODULE$)).map((Function1)new Benchmark$$anonfun$runBenchmark$1($this), List$.MODULE$.canBuildFrom());
/*     */   }
/*     */   
/*     */   public static void setUp(Benchmark $this) {}
/*     */   
/*     */   public static void tearDown(Benchmark $this) {}
/*     */   
/*     */   public static String prefix(Benchmark $this) {
/*  86 */     return $this.getClass().getName();
/*     */   }
/*     */   
/*     */   public static void main(Benchmark $this, String[] args) {
/*  95 */     if (args.length > 0) {
/*  96 */       OutputStreamWriter logFile = new OutputStreamWriter(System.out);
/*  97 */       if (args.length > 1) {
/*  97 */         String str1 = args[1];
/*  97 */         Predef$ predef$1 = Predef$.MODULE$;
/*  97 */         $this.multiplier_$eq(StringLike.class.toInt((StringLike)new StringOps(str1)));
/*     */       } 
/*  98 */       logFile.write($this.prefix());
/*  99 */       String str = args[0];
/*  99 */       Predef$ predef$ = Predef$.MODULE$;
/*  99 */       List<Object> these1 = $this.runBenchmark(StringLike.class.toInt((StringLike)new StringOps(str)));
/*     */       while (true) {
/*  99 */         if (these1.isEmpty()) {
/* 102 */           logFile.write(Platform$.MODULE$.EOL());
/* 103 */           logFile.flush();
/*     */           break;
/*     */         } 
/*     */         long l = BoxesRunTime.unboxToLong(these1.head());
/*     */         logFile.write((new StringBuilder()).append("\t").append(BoxesRunTime.boxToLong(l)).toString());
/*     */         these1 = (List<Object>)these1.tail();
/*     */       } 
/*     */     } else {
/* 105 */       Predef$ predef$1 = Predef$.MODULE$;
/* 105 */       Console$.MODULE$.out().println("Usage: scala benchmarks.program <runs> ");
/* 106 */       Predef$ predef$2 = Predef$.MODULE$;
/* 106 */       Console$.MODULE$.out().println("   or: scala benchmarks.program <runs> <multiplier>");
/* 107 */       Predef$ predef$3 = Predef$.MODULE$;
/* 107 */       Console$.MODULE$.out().println("\n    The benchmark is run <runs> times, forcing a garbage collection between runs. The optional\n    <multiplier> causes the benchmark to be repeated <multiplier> times, each time for <runs>\n    executions.\n      ");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\testing\Benchmark$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */