/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ 
/*     */ public final class RootActorPath$ extends AbstractFunction2<Address, String, RootActorPath> implements Serializable {
/*     */   public static final RootActorPath$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 152 */     return "RootActorPath";
/*     */   }
/*     */   
/*     */   public RootActorPath apply(Address address, String name) {
/* 152 */     return new RootActorPath(address, name);
/*     */   }
/*     */   
/*     */   public Option<Tuple2<Address, String>> unapply(RootActorPath x$0) {
/* 152 */     return (x$0 == null) ? (Option<Tuple2<Address, String>>)scala.None$.MODULE$ : (Option<Tuple2<Address, String>>)new Some(new Tuple2(x$0.address(), x$0.name()));
/*     */   }
/*     */   
/*     */   public String apply$default$2() {
/* 152 */     return "/";
/*     */   }
/*     */   
/*     */   public String $lessinit$greater$default$2() {
/* 152 */     return "/";
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 152 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private RootActorPath$() {
/* 152 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\RootActorPath$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */