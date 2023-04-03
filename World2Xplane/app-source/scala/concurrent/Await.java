/*     */ package scala.concurrent;
/*     */ 
/*     */ import java.util.concurrent.TimeoutException;
/*     */ import scala.Serializable;
/*     */ import scala.concurrent.duration.Duration;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001y;Q!\001\002\t\002\035\tQ!Q<bSRT!a\001\003\002\025\r|gnY;se\026tGOC\001\006\003\025\0318-\0317b\007\001\001\"\001C\005\016\003\t1QA\003\002\t\002-\021Q!Q<bSR\034\"!\003\007\021\0055qQ\"\001\003\n\005=!!AB!osJ+g\rC\003\022\023\021\005!#\001\004=S:LGO\020\013\002\017!)A#\003C\001+\005)!/Z1esV\021ac\b\013\004/eAcB\001\r\032\031\001AQAG\nA\002m\t\021\"Y<bSR\f'\r\\3\021\007!ab$\003\002\036\005\tI\021i^1ji\006\024G.\032\t\0031}!Q\001I\nC\002\005\022\021\001V\t\003E\025\002\"!D\022\n\005\021\"!a\002(pi\"Lgn\032\t\003\033\031J!a\n\003\003\007\005s\027\020C\003*'\001\007!&\001\004bi6{7\017\036\t\003W9j\021\001\f\006\003[\t\t\001\002Z;sCRLwN\\\005\003_1\022\001\002R;sCRLwN\034\025\004'Er\004cA\0073i%\0211\007\002\002\007i\"\024xn^:\021\005a)D!\002\021\001\005\0041\024C\001\0228!\tA4H\004\002\016s%\021!\bB\001\ba\006\0347.Y4f\023\taTHA\005UQJ|w/\0312mK*\021!\bB\022\002A\021\001\bQ\005\003\003v\022A#\0238uKJ\024X\017\035;fI\026C8-\0329uS>t\007fA\nD\rB\031QB\r#\021\005a)E!\002\021\001\005\00414%A$\021\005!SeB\001\005J\023\tQ$!\003\002L\031\n\001B+[7f_V$X\t_2faRLwN\034\006\003u\tAQAT\005\005\002=\013aA]3tk2$XC\001)S)\r\t6+\026\t\0031I#Q\001I'C\002\005BQAG'A\002Q\0032\001\003\017R\021\025IS\n1\001+Q\riuK\027\t\004\033IB\006C\001\rZ\t\025\001\003A1\0017G\005Y\006C\001\035]\023\tiVHA\005Fq\016,\007\017^5p]\002")
/*     */ public final class Await {
/*     */   public static <T> T result(Awaitable<T> paramAwaitable, Duration paramDuration) throws Exception {
/*     */     return Await$.MODULE$.result(paramAwaitable, paramDuration);
/*     */   }
/*     */   
/*     */   public static <T> Awaitable<T> ready(Awaitable<T> paramAwaitable, Duration paramDuration) throws TimeoutException, InterruptedException {
/*     */     return Await$.MODULE$.ready(paramAwaitable, paramDuration);
/*     */   }
/*     */   
/*     */   public static class Await$$anonfun$ready$1 extends AbstractFunction0<Awaitable<T>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Awaitable awaitable$1;
/*     */     
/*     */     private final Duration atMost$1;
/*     */     
/*     */     public final Awaitable<T> apply() {
/*  86 */       return this.awaitable$1.ready(this.atMost$1, AwaitPermission$.MODULE$);
/*     */     }
/*     */     
/*     */     public Await$$anonfun$ready$1(Awaitable awaitable$1, Duration atMost$1) {}
/*     */   }
/*     */   
/*     */   public static class Await$$anonfun$result$1 extends AbstractFunction0<T> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Awaitable awaitable$2;
/*     */     
/*     */     private final Duration atMost$2;
/*     */     
/*     */     public final T apply() {
/* 107 */       return this.awaitable$2.result(this.atMost$2, AwaitPermission$.MODULE$);
/*     */     }
/*     */     
/*     */     public Await$$anonfun$result$1(Awaitable awaitable$2, Duration atMost$2) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\Await.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */