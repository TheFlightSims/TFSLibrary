/*    */ package akka.actor;
/*    */ 
/*    */ import akka.routing.RouterConfig;
/*    */ import com.typesafe.config.Config;
/*    */ import com.typesafe.config.ConfigFactory;
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple6;
/*    */ 
/*    */ public final class Deploy$ implements Serializable {
/*    */   public static final Deploy$ MODULE$;
/*    */   
/*    */   private final String NoDispatcherGiven;
/*    */   
/*    */   private final String NoMailboxGiven;
/*    */   
/*    */   private final Deploy local;
/*    */   
/*    */   private Object readResolve() {
/* 16 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Deploy$() {
/* 16 */     MODULE$ = this;
/* 19 */     LocalScope$ x$1 = LocalScope$.MODULE$;
/* 19 */     String x$2 = apply$default$1();
/* 19 */     Config x$3 = apply$default$2();
/* 19 */     RouterConfig x$4 = apply$default$3();
/* 19 */     String x$5 = apply$default$5(), x$6 = apply$default$6();
/* 19 */     this.local = new Deploy(x$2, x$3, x$4, x$1, x$5, x$6);
/*    */   }
/*    */   
/*    */   public final String NoDispatcherGiven() {
/*    */     return "";
/*    */   }
/*    */   
/*    */   public final String NoMailboxGiven() {
/*    */     return "";
/*    */   }
/*    */   
/*    */   public Deploy local() {
/* 19 */     return this.local;
/*    */   }
/*    */   
/*    */   public Deploy apply(String path, Config config, RouterConfig routerConfig, Scope scope, String dispatcher, String mailbox) {
/* 38 */     return new Deploy(path, config, routerConfig, scope, dispatcher, mailbox);
/*    */   }
/*    */   
/*    */   public Option<Tuple6<String, Config, RouterConfig, Scope, String, String>> unapply(Deploy x$0) {
/* 38 */     return (x$0 == null) ? (Option<Tuple6<String, Config, RouterConfig, Scope, String, String>>)scala.None$.MODULE$ : (Option<Tuple6<String, Config, RouterConfig, Scope, String, String>>)new Some(new Tuple6(x$0.path(), x$0.config(), x$0.routerConfig(), x$0.scope(), x$0.dispatcher(), x$0.mailbox()));
/*    */   }
/*    */   
/*    */   public String $lessinit$greater$default$1() {
/* 39 */     return "";
/*    */   }
/*    */   
/*    */   public String apply$default$1() {
/* 39 */     return "";
/*    */   }
/*    */   
/*    */   public Config $lessinit$greater$default$2() {
/* 40 */     return ConfigFactory.empty();
/*    */   }
/*    */   
/*    */   public Config apply$default$2() {
/* 40 */     return ConfigFactory.empty();
/*    */   }
/*    */   
/*    */   public RouterConfig $lessinit$greater$default$3() {
/* 41 */     return (RouterConfig)akka.routing.NoRouter$.MODULE$;
/*    */   }
/*    */   
/*    */   public RouterConfig apply$default$3() {
/* 41 */     return (RouterConfig)akka.routing.NoRouter$.MODULE$;
/*    */   }
/*    */   
/*    */   public Scope $lessinit$greater$default$4() {
/* 42 */     return NoScopeGiven$.MODULE$;
/*    */   }
/*    */   
/*    */   public Scope apply$default$4() {
/* 42 */     return NoScopeGiven$.MODULE$;
/*    */   }
/*    */   
/*    */   public String $lessinit$greater$default$5() {
/* 43 */     return "";
/*    */   }
/*    */   
/*    */   public String apply$default$5() {
/* 43 */     return "";
/*    */   }
/*    */   
/*    */   public String $lessinit$greater$default$6() {
/* 44 */     return "";
/*    */   }
/*    */   
/*    */   public String apply$default$6() {
/* 44 */     return "";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\Deploy$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */