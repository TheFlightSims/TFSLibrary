/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\00152Q!\001\002\001\t\031\021qc\021:fCR|'OR;oGRLwN\\\"p]N,X.\032:\013\005\r!\021!B1di>\024(\"A\003\002\t\005\\7.Y\n\004\001\035i\001C\001\005\f\033\005I!\"\001\006\002\013M\034\027\r\\1\n\0051I!AB!osJ+g\r\005\002\017\0375\t!!\003\002\021\005\t)\022J\0343je\026\034G/Q2u_J\004&o\0343vG\026\024\b\002\003\n\001\005\003\005\013\021\002\013\002\017\r\024X-\031;pe\016\001\001c\001\005\026/%\021a#\003\002\n\rVt7\r^5p]B\002\"A\004\r\n\005e\021!!B!di>\024\b\"B\016\001\t\003a\022A\002\037j]&$h\b\006\002\036=A\021a\002\001\005\006%i\001\r\001\006\005\006A\001!\t%I\001\013C\016$xN]\"mCN\034X#\001\022\021\007\rBs#D\001%\025\t)c%\001\003mC:<'\"A\024\002\t)\fg/Y\005\003S\021\022Qa\0217bgNDQa\013\001\005B1\nq\001\035:pIV\034W\rF\001\030\001")
/*     */ public class CreatorFunctionConsumer implements IndirectActorProducer {
/*     */   private final Function0<Actor> creator;
/*     */   
/*     */   public CreatorFunctionConsumer(Function0<Actor> creator) {}
/*     */   
/*     */   public Class<Actor> actorClass() {
/* 323 */     return Actor.class;
/*     */   }
/*     */   
/*     */   public Actor produce() {
/* 324 */     return (Actor)this.creator.apply();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\CreatorFunctionConsumer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */