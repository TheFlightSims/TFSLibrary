/*     */ package akka.actor;
/*     */ 
/*     */ import java.io.NotSerializableException;
/*     */ import java.io.ObjectOutputStream;
/*     */ import scala.PartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ public abstract class ActorContext$class {
/*     */   public static void $init$(ActorContext $this) {}
/*     */   
/*     */   public static void become(ActorContext $this, PartialFunction<Object, BoxedUnit> behavior) {
/*  77 */     $this.become(behavior, true);
/*     */   }
/*     */   
/*     */   public static final void writeObject(ActorContext $this, ObjectOutputStream o) {
/* 155 */     throw new NotSerializableException("ActorContext is not serializable!");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ActorContext$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */