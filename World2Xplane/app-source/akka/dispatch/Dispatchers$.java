/*     */ package akka.dispatch;
/*     */ 
/*     */ import akka.ConfigurationException;
/*     */ import com.typesafe.config.Config;
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ 
/*     */ public final class Dispatchers$ {
/*     */   public static final Dispatchers$ MODULE$;
/*     */   
/*     */   private final String DefaultDispatcherId;
/*     */   
/*     */   private Dispatchers$() {
/*  43 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public final String DefaultDispatcherId() {
/*  48 */     return "akka.actor.default-dispatcher";
/*     */   }
/*     */   
/*     */   public class Dispatchers$$anonfun$configuratorFrom$1 extends AbstractPartialFunction<Throwable, scala.runtime.Nothing$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Config cfg$1;
/*     */     
/*     */     private final String x1$1;
/*     */     
/*     */     public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x1, Function1 default) {
/* 186 */       Throwable throwable = x1;
/* 188 */       throw new ConfigurationException((
/*     */           
/* 190 */           new StringOps(scala.Predef$.MODULE$.augmentString("Cannot instantiate MessageDispatcherConfigurator type [%s], defined in [%s], make sure it has constructor with [com.typesafe.config.Config] and [akka.dispatch.DispatcherPrerequisites] parameters")))
/*     */           
/* 192 */           .format(scala.Predef$.MODULE$.genericWrapArray(new Object[] { this.x1$1, this.cfg$1.getString("id") }, )), throwable);
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Throwable x1) {
/*     */       Throwable throwable = x1;
/*     */       return true;
/*     */     }
/*     */     
/*     */     public Dispatchers$$anonfun$configuratorFrom$1(Dispatchers $outer, Config cfg$1, String x1$1) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\Dispatchers$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */