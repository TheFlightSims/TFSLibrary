/*    */ package scala.concurrent.duration;
/*    */ 
/*    */ import java.util.concurrent.TimeUnit;
/*    */ 
/*    */ public abstract class DurationConversions$class {
/*    */   public static void $init$(DurationConversions $this) {}
/*    */   
/*    */   public static FiniteDuration nanoseconds(DurationConversions $this) {
/* 18 */     return $this.durationIn(TimeUnit.NANOSECONDS);
/*    */   }
/*    */   
/*    */   public static FiniteDuration nanos(DurationConversions $this) {
/* 19 */     return $this.nanoseconds();
/*    */   }
/*    */   
/*    */   public static FiniteDuration nanosecond(DurationConversions $this) {
/* 20 */     return $this.nanoseconds();
/*    */   }
/*    */   
/*    */   public static FiniteDuration nano(DurationConversions $this) {
/* 21 */     return $this.nanoseconds();
/*    */   }
/*    */   
/*    */   public static FiniteDuration microseconds(DurationConversions $this) {
/* 23 */     return $this.durationIn(TimeUnit.MICROSECONDS);
/*    */   }
/*    */   
/*    */   public static FiniteDuration micros(DurationConversions $this) {
/* 24 */     return $this.microseconds();
/*    */   }
/*    */   
/*    */   public static FiniteDuration microsecond(DurationConversions $this) {
/* 25 */     return $this.microseconds();
/*    */   }
/*    */   
/*    */   public static FiniteDuration micro(DurationConversions $this) {
/* 26 */     return $this.microseconds();
/*    */   }
/*    */   
/*    */   public static FiniteDuration milliseconds(DurationConversions $this) {
/* 28 */     return $this.durationIn(TimeUnit.MILLISECONDS);
/*    */   }
/*    */   
/*    */   public static FiniteDuration millis(DurationConversions $this) {
/* 29 */     return $this.milliseconds();
/*    */   }
/*    */   
/*    */   public static FiniteDuration millisecond(DurationConversions $this) {
/* 30 */     return $this.milliseconds();
/*    */   }
/*    */   
/*    */   public static FiniteDuration milli(DurationConversions $this) {
/* 31 */     return $this.milliseconds();
/*    */   }
/*    */   
/*    */   public static FiniteDuration seconds(DurationConversions $this) {
/* 33 */     return $this.durationIn(TimeUnit.SECONDS);
/*    */   }
/*    */   
/*    */   public static FiniteDuration second(DurationConversions $this) {
/* 34 */     return $this.seconds();
/*    */   }
/*    */   
/*    */   public static FiniteDuration minutes(DurationConversions $this) {
/* 36 */     return $this.durationIn(TimeUnit.MINUTES);
/*    */   }
/*    */   
/*    */   public static FiniteDuration minute(DurationConversions $this) {
/* 37 */     return $this.minutes();
/*    */   }
/*    */   
/*    */   public static FiniteDuration hours(DurationConversions $this) {
/* 39 */     return $this.durationIn(TimeUnit.HOURS);
/*    */   }
/*    */   
/*    */   public static FiniteDuration hour(DurationConversions $this) {
/* 40 */     return $this.hours();
/*    */   }
/*    */   
/*    */   public static FiniteDuration days(DurationConversions $this) {
/* 42 */     return $this.durationIn(TimeUnit.DAYS);
/*    */   }
/*    */   
/*    */   public static FiniteDuration day(DurationConversions $this) {
/* 43 */     return $this.days();
/*    */   }
/*    */   
/*    */   public static Object nanoseconds(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
/* 45 */     return ev.convert($this.nanoseconds());
/*    */   }
/*    */   
/*    */   public static Object nanos(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
/* 46 */     return $this.nanoseconds(c, ev);
/*    */   }
/*    */   
/*    */   public static Object nanosecond(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
/* 47 */     return $this.nanoseconds(c, ev);
/*    */   }
/*    */   
/*    */   public static Object nano(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
/* 48 */     return $this.nanoseconds(c, ev);
/*    */   }
/*    */   
/*    */   public static Object microseconds(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
/* 50 */     return ev.convert($this.microseconds());
/*    */   }
/*    */   
/*    */   public static Object micros(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
/* 51 */     return $this.microseconds(c, ev);
/*    */   }
/*    */   
/*    */   public static Object microsecond(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
/* 52 */     return $this.microseconds(c, ev);
/*    */   }
/*    */   
/*    */   public static Object micro(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
/* 53 */     return $this.microseconds(c, ev);
/*    */   }
/*    */   
/*    */   public static Object milliseconds(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
/* 55 */     return ev.convert($this.milliseconds());
/*    */   }
/*    */   
/*    */   public static Object millis(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
/* 56 */     return $this.milliseconds(c, ev);
/*    */   }
/*    */   
/*    */   public static Object millisecond(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
/* 57 */     return $this.milliseconds(c, ev);
/*    */   }
/*    */   
/*    */   public static Object milli(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
/* 58 */     return $this.milliseconds(c, ev);
/*    */   }
/*    */   
/*    */   public static Object seconds(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
/* 60 */     return ev.convert($this.seconds());
/*    */   }
/*    */   
/*    */   public static Object second(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
/* 61 */     return $this.seconds(c, ev);
/*    */   }
/*    */   
/*    */   public static Object minutes(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
/* 63 */     return ev.convert($this.minutes());
/*    */   }
/*    */   
/*    */   public static Object minute(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
/* 64 */     return $this.minutes(c, ev);
/*    */   }
/*    */   
/*    */   public static Object hours(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
/* 66 */     return ev.convert($this.hours());
/*    */   }
/*    */   
/*    */   public static Object hour(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
/* 67 */     return $this.hours(c, ev);
/*    */   }
/*    */   
/*    */   public static Object days(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
/* 69 */     return ev.convert($this.days());
/*    */   }
/*    */   
/*    */   public static Object day(DurationConversions $this, Object c, DurationConversions.Classifier ev) {
/* 70 */     return $this.days(c, ev);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\duration\DurationConversions$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */