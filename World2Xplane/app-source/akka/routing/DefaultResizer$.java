/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.ActorCell;
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ActorRefWithCell;
/*     */ import akka.actor.Cell;
/*     */ import com.typesafe.config.Config;
/*     */ import scala.Option;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple7;
/*     */ import scala.collection.Iterator;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class DefaultResizer$ implements Product, Serializable {
/*     */   public static final DefaultResizer$ MODULE$;
/*     */   
/*     */   public String productPrefix() {
/*  58 */     return "DefaultResizer";
/*     */   }
/*     */   
/*     */   public int productArity() {
/*  58 */     return 0;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/*  58 */     int i = x$1;
/*  58 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/*  58 */     return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/*  58 */     return x$1 instanceof DefaultResizer$;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  58 */     return 2099326173;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  58 */     return "DefaultResizer";
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/*  58 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private DefaultResizer$() {
/*  58 */     MODULE$ = this;
/*  58 */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public DefaultResizer apply(Config resizerConfig) {
/*  64 */     return new DefaultResizer(
/*  65 */         resizerConfig.getInt("lower-bound"), 
/*  66 */         resizerConfig.getInt("upper-bound"), 
/*  67 */         resizerConfig.getInt("pressure-threshold"), 
/*  68 */         resizerConfig.getDouble("rampup-rate"), 
/*  69 */         resizerConfig.getDouble("backoff-threshold"), 
/*  70 */         resizerConfig.getDouble("backoff-rate"), 
/*  71 */         resizerConfig.getInt("messages-per-resize"));
/*     */   }
/*     */   
/*     */   public Option<DefaultResizer> fromConfig(Config resizerConfig) {
/*  74 */     return resizerConfig.getBoolean("resizer.enabled") ? 
/*  75 */       (Option<DefaultResizer>)new Some(apply(resizerConfig.getConfig("resizer"))) : 
/*     */       
/*  77 */       (Option<DefaultResizer>)scala.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public DefaultResizer apply(int lowerBound, int upperBound, int pressureThreshold, double rampupRate, double backoffThreshold, double backoffRate, int messagesPerResize) {
/*  85 */     return new DefaultResizer(lowerBound, upperBound, pressureThreshold, rampupRate, backoffThreshold, backoffRate, messagesPerResize);
/*     */   }
/*     */   
/*     */   public Option<Tuple7<Object, Object, Object, Object, Object, Object, Object>> unapply(DefaultResizer x$0) {
/*  85 */     return (x$0 == null) ? (Option<Tuple7<Object, Object, Object, Object, Object, Object, Object>>)scala.None$.MODULE$ : (Option<Tuple7<Object, Object, Object, Object, Object, Object, Object>>)new Some(new Tuple7(BoxesRunTime.boxToInteger(x$0.lowerBound()), BoxesRunTime.boxToInteger(x$0.upperBound()), BoxesRunTime.boxToInteger(x$0.pressureThreshold()), BoxesRunTime.boxToDouble(x$0.rampupRate()), BoxesRunTime.boxToDouble(x$0.backoffThreshold()), BoxesRunTime.boxToDouble(x$0.backoffRate()), BoxesRunTime.boxToInteger(x$0.messagesPerResize())));
/*     */   }
/*     */   
/*     */   public int apply$default$1() {
/*  89 */     return 1;
/*     */   }
/*     */   
/*     */   public int $lessinit$greater$default$1() {
/*  89 */     return 1;
/*     */   }
/*     */   
/*     */   public int apply$default$2() {
/*  94 */     return 10;
/*     */   }
/*     */   
/*     */   public int $lessinit$greater$default$2() {
/*  94 */     return 10;
/*     */   }
/*     */   
/*     */   public int apply$default$3() {
/* 107 */     return 1;
/*     */   }
/*     */   
/*     */   public int $lessinit$greater$default$3() {
/* 107 */     return 1;
/*     */   }
/*     */   
/*     */   public double apply$default$4() {
/* 113 */     return 0.2D;
/*     */   }
/*     */   
/*     */   public double $lessinit$greater$default$4() {
/* 113 */     return 0.2D;
/*     */   }
/*     */   
/*     */   public double apply$default$5() {
/* 123 */     return 0.3D;
/*     */   }
/*     */   
/*     */   public double $lessinit$greater$default$5() {
/* 123 */     return 0.3D;
/*     */   }
/*     */   
/*     */   public double apply$default$6() {
/* 130 */     return 0.1D;
/*     */   }
/*     */   
/*     */   public double $lessinit$greater$default$6() {
/* 130 */     return 0.1D;
/*     */   }
/*     */   
/*     */   public int apply$default$7() {
/* 135 */     return 10;
/*     */   }
/*     */   
/*     */   public int $lessinit$greater$default$7() {
/* 135 */     return 10;
/*     */   }
/*     */   
/*     */   public class DefaultResizer$$anonfun$pressure$1 extends AbstractFunction1<Routee, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Routee x0$1) {
/* 191 */       Routee routee = x0$1;
/* 192 */       if (routee instanceof ActorRefRoutee) {
/* 192 */         ActorRefRoutee actorRefRoutee = (ActorRefRoutee)routee;
/* 192 */         ActorRef a = actorRefRoutee.ref();
/* 192 */         if (a instanceof ActorRefWithCell) {
/*     */           boolean bool;
/* 192 */           ActorRefWithCell actorRefWithCell = (ActorRefWithCell)a;
/* 193 */           Cell cell = actorRefWithCell.underlying();
/* 194 */           if (cell instanceof ActorCell) {
/* 194 */             ActorCell actorCell = (ActorCell)cell;
/* 195 */             int i = this.$outer.pressureThreshold();
/* 195 */             switch (i) {
/*     */               default:
/* 197 */                 if (i < 1)
/* 197 */                   if (actorCell.mailbox().isScheduled() && actorCell.currentMessage() != null); 
/* 198 */                 if (actorCell.mailbox().numberOfMessages() >= i);
/*     */               case 1:
/*     */                 if (actorCell.mailbox().isScheduled() && actorCell.mailbox().hasMessages());
/*     */                 break;
/*     */             } 
/*     */             bool = false;
/*     */           } else {
/* 201 */             int i = this.$outer.pressureThreshold();
/* 201 */             switch (i) {
/*     */               default:
/* 203 */                 if (i < 1);
/* 204 */                 if (cell.numberOfMessages() >= i);
/*     */               case 1:
/*     */                 break;
/*     */             } 
/*     */             bool = cell.hasMessages();
/*     */           } 
/*     */           return bool;
/*     */         } 
/*     */       } 
/*     */       return false;
/*     */     }
/*     */     
/*     */     public DefaultResizer$$anonfun$pressure$1(DefaultResizer $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\DefaultResizer$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */