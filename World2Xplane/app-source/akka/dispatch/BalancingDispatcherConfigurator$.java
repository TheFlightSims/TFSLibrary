/*     */ package akka.dispatch;
/*     */ 
/*     */ import com.typesafe.config.Config;
/*     */ import com.typesafe.config.ConfigFactory;
/*     */ 
/*     */ public final class BalancingDispatcherConfigurator$ {
/*     */   public static final BalancingDispatcherConfigurator$ MODULE$;
/*     */   
/*     */   private final Config defaultRequirement;
/*     */   
/*     */   private BalancingDispatcherConfigurator$() {
/* 223 */     MODULE$ = this;
/* 224 */     this.defaultRequirement = 
/* 225 */       ConfigFactory.parseString("mailbox-requirement = akka.dispatch.MultipleConsumerSemantics");
/*     */   }
/*     */   
/*     */   private Config defaultRequirement() {
/*     */     return this.defaultRequirement;
/*     */   }
/*     */   
/*     */   public Config amendConfig(Config config) {
/* 227 */     String str = "";
/* 227 */     if (config.getString("mailbox-requirement") == null) {
/* 227 */       config.getString("mailbox-requirement");
/* 227 */       if (str != null);
/* 227 */     } else if (config.getString("mailbox-requirement").equals(str)) {
/*     */     
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\BalancingDispatcherConfigurator$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */