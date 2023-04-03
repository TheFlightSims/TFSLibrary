/*    */ package akka.dispatch;
/*    */ 
/*    */ import com.typesafe.config.Config;
/*    */ import com.typesafe.config.ConfigFactory;
/*    */ import com.typesafe.config.ConfigValue;
/*    */ import scala.Serializable;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class CachingConfig$ {
/*    */   public static final CachingConfig$ MODULE$;
/*    */   
/*    */   private final Config emptyConfig;
/*    */   
/*    */   private final CachingConfig.ValuePathEntry invalidPathEntry;
/*    */   
/*    */   private final CachingConfig.ValuePathEntry nonExistingPathEntry;
/*    */   
/*    */   private final CachingConfig.ValuePathEntry emptyPathEntry;
/*    */   
/*    */   private CachingConfig$() {
/* 15 */     MODULE$ = this;
/* 16 */     this.emptyConfig = ConfigFactory.empty();
/* 26 */     this.invalidPathEntry = new CachingConfig.ValuePathEntry(false, true, CachingConfig.ValuePathEntry$.MODULE$.apply$default$3());
/* 27 */     this.nonExistingPathEntry = new CachingConfig.ValuePathEntry(true, false, CachingConfig.ValuePathEntry$.MODULE$.apply$default$3());
/* 28 */     this.emptyPathEntry = new CachingConfig.ValuePathEntry(true, true, CachingConfig.ValuePathEntry$.MODULE$.apply$default$3());
/*    */   }
/*    */   
/*    */   public Config emptyConfig() {
/*    */     return this.emptyConfig;
/*    */   }
/*    */   
/*    */   public CachingConfig.ValuePathEntry invalidPathEntry() {
/*    */     return this.invalidPathEntry;
/*    */   }
/*    */   
/*    */   public CachingConfig.ValuePathEntry nonExistingPathEntry() {
/*    */     return this.nonExistingPathEntry;
/*    */   }
/*    */   
/*    */   public CachingConfig.ValuePathEntry emptyPathEntry() {
/* 28 */     return this.emptyPathEntry;
/*    */   }
/*    */   
/*    */   public class $anonfun$1 extends AbstractFunction0.mcZ.sp implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final String path$1;
/*    */     
/*    */     public final boolean apply() {
/* 51 */       return apply$mcZ$sp();
/*    */     }
/*    */     
/*    */     public boolean apply$mcZ$sp() {
/* 51 */       return this.$outer.akka$dispatch$CachingConfig$$config().hasPath(this.path$1);
/*    */     }
/*    */     
/*    */     public $anonfun$1(CachingConfig $outer, String path$1) {}
/*    */   }
/*    */   
/*    */   public class $anonfun$2 extends AbstractFunction0<ConfigValue> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final String path$1;
/*    */     
/*    */     public final ConfigValue apply() {
/* 55 */       return this.$outer.akka$dispatch$CachingConfig$$config().getValue(this.path$1);
/*    */     }
/*    */     
/*    */     public $anonfun$2(CachingConfig $outer, String path$1) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\CachingConfig$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */