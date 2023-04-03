/*     */ package scala.concurrent;
/*     */ 
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.Some;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\00114A!\001\002\001\017\t91+\0378d-\006\024(BA\002\005\003)\031wN\\2veJ,g\016\036\006\002\013\005)1oY1mC\016\001QC\001\005\025'\t\001\021\002\005\002\013\0275\tA!\003\002\r\t\t1\021I\\=SK\032DQA\004\001\005\002=\ta\001P5oSRtD#\001\t\021\007E\001!#D\001\003!\t\031B\003\004\001\005\013U\001!\031\001\f\003\003\005\013\"a\006\016\021\005)A\022BA\r\005\005\035qu\016\0365j]\036\004\"AC\016\n\005q!!aA!os\"9a\004\001a\001\n\023y\022!C5t\t\0264\027N\\3e+\005\001\003C\001\006\"\023\t\021CAA\004C_>dW-\0318\t\017\021\002\001\031!C\005K\005i\021n\035#fM&tW\rZ0%KF$\"AJ\025\021\005)9\023B\001\025\005\005\021)f.\033;\t\017)\032\023\021!a\001A\005\031\001\020J\031\t\r1\002\001\025)\003!\003)I7\017R3gS:,G\r\t\005\b]\001\001\r\021\"\0030\003\0251\030\r\\;f+\005\001\004c\001\0062%%\021!\007\002\002\007\037B$\030n\0348\t\017Q\002\001\031!C\005k\005Ia/\0317vK~#S-\035\013\003MYBqAK\032\002\002\003\007\001\007\003\0049\001\001\006K\001M\001\007m\006dW/\032\021\t\013i\002A\021A\036\002\007\035,G/F\001\023\021\025i\004\001\"\003?\003Q9\030-\033;NK\006\034XO]5oO\026c\027\r]:fIR\021qH\021\t\003\025\001K!!\021\003\003\t1{gn\032\005\006\007r\002\raP\001\bi&lWm\\;u\021\025Q\004\001\"\001F)\t\001d\tC\003D\t\002\007q\bC\003I\001\021\005\021*\001\003uC.,G#\001\n\t\013!\003A\021A&\025\005Ia\005\"B\"K\001\004y\004\"\002(\001\t\003y\025aA:fiR\021a\005\025\005\006#6\003\rAE\001\002q\"\"Qj\025,Y!\tQA+\003\002V\t\tQA-\0329sK\016\fG/\0323\"\003]\013\001(V:fA\001\004X\017\0361!S:\034H/Z1eY\001\n7\017\t1tKR\004\007%[:!a>$XM\034;j_:\fG\016\\=!KJ\024xN]\027qe>tW-I\001Z\003\031\021d&\r\031/a!)1\f\001C\0019\006\031\001/\036;\025\005\031j\006\"B)[\001\004\021\002\"B0\001\t\003y\022!B5t'\026$\b\"B1\001\t\003\021\027!B;og\026$H#\001\024)\t\001\034F\rW\021\002K\006YTk]3!AR\f7.\0321!S:\034H/Z1eY\001\n7\017\t1v]N,G\017\031\021jg\002\002x\016^3oi&|g.\0317ms\002*'O]8s[A\024xN\\3\t\013\035\004A\021\0025\002\rM,GOV1m)\t1\023\016C\003RM\002\007!\003C\003l\001\021%!-\001\005v]N,GOV1m\001")
/*     */ public class SyncVar<A> {
/*     */   private boolean isDefined = false;
/*     */   
/*     */   private boolean isDefined() {
/*  18 */     return this.isDefined;
/*     */   }
/*     */   
/*     */   private void isDefined_$eq(boolean x$1) {
/*  18 */     this.isDefined = x$1;
/*     */   }
/*     */   
/*  19 */   private Option<A> value = (Option<A>)None$.MODULE$;
/*     */   
/*     */   private Option<A> value() {
/*  19 */     return this.value;
/*     */   }
/*     */   
/*     */   private void value_$eq(Option<A> x$1) {
/*  19 */     this.value = x$1;
/*     */   }
/*     */   
/*     */   public synchronized A get() {
/*     */     while (true) {
/*  22 */       if (isDefined())
/*  23 */         return (A)value().get(); 
/*     */       wait();
/*     */     } 
/*     */   }
/*     */   
/*     */   private long waitMeasuringElapsed(long timeout) {
/*  30 */     long start = System.currentTimeMillis();
/*  31 */     wait(timeout);
/*  32 */     long elapsed = System.currentTimeMillis() - start;
/*  33 */     return (timeout <= 0L) ? 0L : ((elapsed < 0L) ? 0L : elapsed);
/*     */   }
/*     */   
/*     */   public synchronized Option<A> get(long timeout) {
/*  48 */     long rest = timeout;
/*  49 */     while (!isDefined() && rest > 0L) {
/*  50 */       long elapsed = waitMeasuringElapsed(rest);
/*  51 */       rest -= elapsed;
/*     */     } 
/*  53 */     return value();
/*     */   }
/*     */   
/*     */   public synchronized A take() {
/*     */     try {
/*  59 */       return get();
/*     */     } finally {
/*  60 */       unsetVal();
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized A take(long timeout) {
/*     */     try {
/*  73 */       return (A)get(timeout).get();
/*     */     } finally {
/*  74 */       unsetVal();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void set(Object x) {
/*  82 */     setVal((A)x);
/*     */   }
/*     */   
/*     */   public synchronized void put(Object x) {
/*  87 */     for (; isDefined(); wait());
/*  88 */     setVal((A)x);
/*     */   }
/*     */   
/*     */   public synchronized boolean isSet() {
/*  93 */     return isDefined();
/*     */   }
/*     */   
/*     */   public synchronized void unset() {
/* 102 */     isDefined_$eq(false);
/* 103 */     value_$eq((Option<A>)None$.MODULE$);
/* 104 */     notifyAll();
/*     */   }
/*     */   
/*     */   private synchronized void setVal(Object x) {
/* 111 */     isDefined_$eq(true);
/* 112 */     value_$eq((Option<A>)new Some(x));
/* 113 */     notifyAll();
/*     */   }
/*     */   
/*     */   private synchronized void unsetVal() {
/* 120 */     isDefined_$eq(false);
/* 121 */     value_$eq((Option<A>)None$.MODULE$);
/* 122 */     notifyAll();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\SyncVar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */