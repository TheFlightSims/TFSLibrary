/*     */ package scala.annotation;
/*     */ 
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class elidable$ {
/*     */   public static final elidable$ MODULE$;
/*     */   
/*     */   private final int ALL;
/*     */   
/*     */   private final int FINEST;
/*     */   
/*     */   private final int FINER;
/*     */   
/*     */   private final int FINE;
/*     */   
/*     */   private final int CONFIG;
/*     */   
/*     */   private final int INFO;
/*     */   
/*     */   private final int WARNING;
/*     */   
/*     */   private final int SEVERE;
/*     */   
/*     */   private final int OFF;
/*     */   
/*     */   private final int MAXIMUM;
/*     */   
/*     */   private final int MINIMUM;
/*     */   
/*     */   private final int ASSERTION;
/*     */   
/*     */   private final Map<String, Object> byName;
/*     */   
/*     */   private elidable$() {
/*  78 */     MODULE$ = this;
/* 108 */     scala.Predef$ predef$1 = scala.Predef$.MODULE$;
/* 108 */     Integer integer1 = BoxesRunTime.boxToInteger(300);
/* 108 */     scala.Predef$ArrowAssoc$ predef$ArrowAssoc$1 = scala.Predef$ArrowAssoc$.MODULE$;
/* 108 */     (new Tuple2[12])[0] = new Tuple2("FINEST", integer1);
/* 109 */     scala.Predef$ predef$2 = scala.Predef$.MODULE$;
/* 109 */     Integer integer2 = BoxesRunTime.boxToInteger(400);
/* 109 */     scala.Predef$ArrowAssoc$ predef$ArrowAssoc$2 = scala.Predef$ArrowAssoc$.MODULE$;
/* 109 */     (new Tuple2[12])[1] = new Tuple2("FINER", integer2);
/* 110 */     scala.Predef$ predef$3 = scala.Predef$.MODULE$;
/* 110 */     Integer integer3 = BoxesRunTime.boxToInteger(500);
/* 110 */     scala.Predef$ArrowAssoc$ predef$ArrowAssoc$3 = scala.Predef$ArrowAssoc$.MODULE$;
/* 110 */     (new Tuple2[12])[2] = new Tuple2("FINE", integer3);
/* 111 */     scala.Predef$ predef$4 = scala.Predef$.MODULE$;
/* 111 */     Integer integer4 = BoxesRunTime.boxToInteger(700);
/* 111 */     scala.Predef$ArrowAssoc$ predef$ArrowAssoc$4 = scala.Predef$ArrowAssoc$.MODULE$;
/* 111 */     (new Tuple2[12])[3] = new Tuple2("CONFIG", integer4);
/* 112 */     scala.Predef$ predef$5 = scala.Predef$.MODULE$;
/* 112 */     Integer integer5 = BoxesRunTime.boxToInteger(800);
/* 112 */     scala.Predef$ArrowAssoc$ predef$ArrowAssoc$5 = scala.Predef$ArrowAssoc$.MODULE$;
/* 112 */     (new Tuple2[12])[4] = new Tuple2("INFO", integer5);
/* 113 */     scala.Predef$ predef$6 = scala.Predef$.MODULE$;
/* 113 */     Integer integer6 = BoxesRunTime.boxToInteger(900);
/* 113 */     scala.Predef$ArrowAssoc$ predef$ArrowAssoc$6 = scala.Predef$ArrowAssoc$.MODULE$;
/* 113 */     (new Tuple2[12])[5] = new Tuple2("WARNING", integer6);
/* 114 */     scala.Predef$ predef$7 = scala.Predef$.MODULE$;
/* 114 */     Integer integer7 = BoxesRunTime.boxToInteger(1000);
/* 114 */     scala.Predef$ArrowAssoc$ predef$ArrowAssoc$7 = scala.Predef$ArrowAssoc$.MODULE$;
/* 114 */     (new Tuple2[12])[6] = new Tuple2("SEVERE", integer7);
/* 115 */     scala.Predef$ predef$8 = scala.Predef$.MODULE$;
/* 115 */     (new Tuple2[12])[7] = scala.Predef$ArrowAssoc$.MODULE$.$minus$greater$extension("ASSERTION", BoxesRunTime.boxToInteger(2000));
/* 116 */     (new Tuple2[12])[8] = scala.Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(scala.Predef$.MODULE$.any2ArrowAssoc("ALL"), BoxesRunTime.boxToInteger(-2147483648));
/* 117 */     (new Tuple2[12])[9] = scala.Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(scala.Predef$.MODULE$.any2ArrowAssoc("OFF"), BoxesRunTime.boxToInteger(2147483647));
/* 118 */     (new Tuple2[12])[10] = scala.Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(scala.Predef$.MODULE$.any2ArrowAssoc("MAXIMUM"), BoxesRunTime.boxToInteger(2147483647));
/* 119 */     (new Tuple2[12])[11] = scala.Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(scala.Predef$.MODULE$.any2ArrowAssoc("MINIMUM"), BoxesRunTime.boxToInteger(-2147483648));
/*     */     this.byName = (Map<String, Object>)scala.Predef$.MODULE$.Map().apply((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[12]));
/*     */   }
/*     */   
/*     */   public final int ALL() {
/*     */     return Integer.MIN_VALUE;
/*     */   }
/*     */   
/*     */   public final int FINEST() {
/*     */     return 300;
/*     */   }
/*     */   
/*     */   public final int FINER() {
/*     */     return 400;
/*     */   }
/*     */   
/*     */   public final int FINE() {
/*     */     return 500;
/*     */   }
/*     */   
/*     */   public final int CONFIG() {
/*     */     return 700;
/*     */   }
/*     */   
/*     */   public final int INFO() {
/*     */     return 800;
/*     */   }
/*     */   
/*     */   public final int WARNING() {
/*     */     return 900;
/*     */   }
/*     */   
/*     */   public final int SEVERE() {
/*     */     return 1000;
/*     */   }
/*     */   
/*     */   public final int OFF() {
/*     */     return Integer.MAX_VALUE;
/*     */   }
/*     */   
/*     */   public final int MAXIMUM() {
/*     */     return Integer.MAX_VALUE;
/*     */   }
/*     */   
/*     */   public final int MINIMUM() {
/*     */     return Integer.MIN_VALUE;
/*     */   }
/*     */   
/*     */   public final int ASSERTION() {
/*     */     return 2000;
/*     */   }
/*     */   
/*     */   public Map<String, Object> byName() {
/*     */     return this.byName;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\annotation\elidable$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */