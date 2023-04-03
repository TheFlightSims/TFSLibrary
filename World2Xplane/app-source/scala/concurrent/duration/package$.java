/*    */ package scala.concurrent.duration;
/*    */ 
/*    */ import java.util.concurrent.TimeUnit;
/*    */ import scala.Tuple2;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class package$ {
/*    */   public static final package$ MODULE$;
/*    */   
/*    */   private final TimeUnit DAYS;
/*    */   
/*    */   private final TimeUnit HOURS;
/*    */   
/*    */   private final TimeUnit MICROSECONDS;
/*    */   
/*    */   private final TimeUnit MILLISECONDS;
/*    */   
/*    */   private final TimeUnit MINUTES;
/*    */   
/*    */   private final TimeUnit NANOSECONDS;
/*    */   
/*    */   private final TimeUnit SECONDS;
/*    */   
/*    */   private package$() {
/*  5 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public final TimeUnit DAYS() {
/* 31 */     return TimeUnit.DAYS;
/*    */   }
/*    */   
/*    */   public final TimeUnit HOURS() {
/* 32 */     return TimeUnit.HOURS;
/*    */   }
/*    */   
/*    */   public final TimeUnit MICROSECONDS() {
/* 33 */     return TimeUnit.MICROSECONDS;
/*    */   }
/*    */   
/*    */   public final TimeUnit MILLISECONDS() {
/* 34 */     return TimeUnit.MILLISECONDS;
/*    */   }
/*    */   
/*    */   public final TimeUnit MINUTES() {
/* 35 */     return TimeUnit.MINUTES;
/*    */   }
/*    */   
/*    */   public final TimeUnit NANOSECONDS() {
/* 36 */     return TimeUnit.NANOSECONDS;
/*    */   }
/*    */   
/*    */   public final TimeUnit SECONDS() {
/* 37 */     return TimeUnit.SECONDS;
/*    */   }
/*    */   
/*    */   public Duration pairIntToDuration(Tuple2 p) {
/* 39 */     return Duration$.MODULE$.apply(p._1$mcI$sp(), (TimeUnit)p._2());
/*    */   }
/*    */   
/*    */   public FiniteDuration pairLongToDuration(Tuple2 p) {
/* 40 */     return Duration$.MODULE$.apply(p._1$mcJ$sp(), (TimeUnit)p._2());
/*    */   }
/*    */   
/*    */   public Tuple2<Object, TimeUnit> durationToPair(Duration d) {
/* 41 */     return new Tuple2(BoxesRunTime.boxToLong(d.length()), d.unit());
/*    */   }
/*    */   
/*    */   public int DurationInt(int n) {
/* 43 */     return n;
/*    */   }
/*    */   
/*    */   public long DurationLong(long n) {
/* 47 */     return n;
/*    */   }
/*    */   
/*    */   public double DurationDouble(double d) {
/* 51 */     return d;
/*    */   }
/*    */   
/*    */   public int IntMult(int i) {
/* 62 */     return i;
/*    */   }
/*    */   
/*    */   public long LongMult(long i) {
/* 67 */     return i;
/*    */   }
/*    */   
/*    */   public double DoubleMult(double f) {
/* 72 */     return f;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\duration\package$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */