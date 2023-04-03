/*    */ package akka.actor;
/*    */ 
/*    */ import java.net.MalformedURLException;
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.Iterable;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.immutable.Iterable;
/*    */ import scala.collection.immutable.StringOps;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.runtime.AbstractFunction2;
/*    */ import scala.util.matching.Regex;
/*    */ 
/*    */ public final class ActorPath$ implements Serializable {
/*    */   public static final ActorPath$ MODULE$;
/*    */   
/*    */   private final Regex ElementRegex;
/*    */   
/*    */   private final Iterable<String> emptyActorPath;
/*    */   
/*    */   private Object readResolve() {
/* 11 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private ActorPath$() {
/* 11 */     MODULE$ = this;
/* 25 */     this.ElementRegex = (new StringOps(scala.Predef$.MODULE$.augmentString("(?:[-\\w:@&=+,.!~*'_;]|%\\p{XDigit}{2})(?:[-\\w:@&=+,.!~*'$_;]|%\\p{XDigit}{2})*"))).r();
/* 27 */     (new String[1])[0] = "";
/* 27 */     this.emptyActorPath = (Iterable<String>)scala.collection.immutable.List$.MODULE$.apply((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new String[1]));
/*    */   }
/*    */   
/*    */   public ActorPath fromString(String s) {
/*    */     String str = s;
/*    */     Option<Tuple2<Address, Iterable<String>>> option = ActorPathExtractor$.MODULE$.unapply(str);
/*    */     if (option.isEmpty())
/*    */       throw new MalformedURLException((new StringBuilder()).append("cannot parse as ActorPath: ").append(s).toString()); 
/*    */     Address addr = (Address)((Tuple2)option.get())._1();
/*    */     Iterable elems = (Iterable)((Tuple2)option.get())._2();
/*    */     return (new RootActorPath(addr, RootActorPath$.MODULE$.apply$default$2())).$div((Iterable<String>)elems);
/*    */   }
/*    */   
/*    */   public Regex ElementRegex() {
/*    */     return this.ElementRegex;
/*    */   }
/*    */   
/*    */   public final Iterable<String> emptyActorPath() {
/* 27 */     return this.emptyActorPath;
/*    */   }
/*    */   
/*    */   public class ActorPath$$anonfun$$div$1 extends AbstractFunction2<ActorPath, String, ActorPath> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final ActorPath apply(ActorPath path, String elem) {
/* 78 */       return elem.isEmpty() ? path : path.$div(elem);
/*    */     }
/*    */     
/*    */     public ActorPath$$anonfun$$div$1(ActorPath $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ActorPath$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */