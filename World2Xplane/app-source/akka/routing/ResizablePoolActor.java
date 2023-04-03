/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.ActorContext;
/*     */ import akka.actor.SupervisorStrategy;
/*     */ import scala.Function1;
/*     */ import scala.PartialFunction;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001}<a!\001\002\t\002\0211\021A\005*fg&T\030M\0317f!>|G.Q2u_JT!a\001\003\002\017I|W\017^5oO*\tQ!\001\003bW.\f\007CA\004\t\033\005\021aAB\005\003\021\003!!B\001\nSKNL'0\0312mKB{w\016\\!di>\0248C\001\005\f!\taq\"D\001\016\025\005q\021!B:dC2\f\027B\001\t\016\005\031\te.\037*fM\")!\003\003C\001)\0051A(\0338jiz\032\001\001F\001\007\017\0251\002\002#!\030\003\031\021Vm]5{KB\021\001$G\007\002\021\031)!\004\003EA7\t1!+Z:ju\026\034R!G\006\035?\t\002\"aB\017\n\005y\021!\001\007*pkR,'/T1oC\036,W.\0328u\033\026\0348o]1hKB\021A\002I\005\003C5\021q\001\025:pIV\034G\017\005\002\rG%\021A%\004\002\r'\026\024\030.\0317ju\006\024G.\032\005\006%e!\tA\n\013\002/!9\001&GA\001\n\003J\023!\0049s_\022,8\r\036)sK\032L\0070F\001+!\tY\003'D\001-\025\tic&\001\003mC:<'\"A\030\002\t)\fg/Y\005\003c1\022aa\025;sS:<\007bB\032\032\003\003%\t\001N\001\raJ|G-^2u\003JLG/_\013\002kA\021ABN\005\003o5\0211!\0238u\021\035I\024$!A\005\002i\na\002\035:pIV\034G/\0227f[\026tG\017\006\002<}A\021A\002P\005\003{5\0211!\0218z\021\035y\004(!AA\002U\n1\001\037\0232\021\035\t\025$!A\005B\t\013q\002\035:pIV\034G/\023;fe\006$xN]\013\002\007B\031AiR\036\016\003\025S!AR\007\002\025\r|G\016\\3di&|g.\003\002I\013\nA\021\n^3sCR|'\017C\004K3\005\005I\021A&\002\021\r\fg.R9vC2$\"\001T(\021\0051i\025B\001(\016\005\035\021un\0347fC:DqaP%\002\002\003\0071\bC\004R3\005\005I\021\t*\002\021!\f7\017[\"pI\026$\022!\016\005\b)f\t\t\021\"\021V\003!!xn\025;sS:<G#\001\026\t\017]K\022\021!C\0051\006Y!/Z1e%\026\034x\016\034<f)\005I\006CA\026[\023\tYFF\001\004PE*,7\r\036\004\006\023\t\001A!X\n\0039z\003\"aB0\n\005\001\024!a\004*pkR,'\017U8pY\006\033Go\034:\t\023\td&\021!Q\001\n\rL\027AE:va\026\024h/[:peN#(/\031;fOf\004\"\001Z4\016\003\025T!A\032\003\002\013\005\034Go\034:\n\005!,'AE*va\026\024h/[:peN#(/\031;fOfL!AY0\t\013IaF\021A6\025\0051l\007CA\004]\021\025\021'\0161\001d\021\035yGL1A\005\002A\f1B]3tSj,'oQ3mYV\t\021\017\005\002\be&\0211O\001\002\022%\026\034\030N_1cY\026\004vn\0347DK2d\007BB;]A\003%\021/\001\007sKNL'0\032:DK2d\007\005C\003x9\022\005\0030A\004sK\016,\027N^3\026\003e\004B\001\004><y&\02110\004\002\020!\006\024H/[1m\rVt7\r^5p]B\021A\"`\005\003}6\021A!\0268ji\002")
/*     */ public class ResizablePoolActor extends RouterPoolActor {
/*     */   private final ResizablePoolCell resizerCell;
/*     */   
/*     */   public static class Resize$ implements RouterManagementMesssage, Product, Serializable {
/*     */     public static final Resize$ MODULE$;
/*     */     
/*     */     public String productPrefix() {
/* 299 */       return "Resize";
/*     */     }
/*     */     
/*     */     public int productArity() {
/* 299 */       return 0;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/* 299 */       int i = x$1;
/* 299 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/* 299 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/* 299 */       return x$1 instanceof Resize$;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 299 */       return -1850570540;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 299 */       return "Resize";
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 299 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public Resize$() {
/* 299 */       MODULE$ = this;
/* 299 */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public ResizablePoolActor(SupervisorStrategy supervisorStrategy) {
/* 305 */     super(
/* 306 */         supervisorStrategy);
/* 309 */     ActorContext actorContext = context();
/* 310 */     if (actorContext instanceof ResizablePoolCell) {
/* 310 */       ResizablePoolCell resizablePoolCell1 = (ResizablePoolCell)actorContext, resizablePoolCell2 = resizablePoolCell1;
/*     */       this.resizerCell = resizablePoolCell2;
/*     */     } 
/* 312 */     throw akka.actor.ActorInitializationException$.MODULE$.apply((new StringBuilder()).append("Router actor can only be used in RoutedActorRef, not in ").append(context().getClass()).toString());
/*     */   }
/*     */   
/*     */   public ResizablePoolCell resizerCell() {
/*     */     return this.resizerCell;
/*     */   }
/*     */   
/*     */   public PartialFunction<Object, BoxedUnit> receive() {
/* 317 */     return (new ResizablePoolActor$$anonfun$receive$1(this)).orElse(super.receive());
/*     */   }
/*     */   
/*     */   public class ResizablePoolActor$$anonfun$receive$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final <A1, B1> B1 applyOrElse(Object x1, Function1 default) {
/*     */       Object object1 = x1;
/*     */       Object object2 = object1;
/*     */       if (ResizablePoolActor.Resize$.MODULE$ == null) {
/*     */         if (object2 != null)
/*     */           Object object = default.apply(x1); 
/*     */       } else {
/*     */         if (ResizablePoolActor.Resize$.MODULE$.equals(object2)) {
/*     */           this.$outer.resizerCell().resize(false);
/*     */           return (B1)BoxedUnit.UNIT;
/*     */         } 
/*     */         Object object = default.apply(x1);
/*     */       } 
/*     */       this.$outer.resizerCell().resize(false);
/*     */       return (B1)BoxedUnit.UNIT;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Object x1) {
/*     */       Object object1 = x1;
/*     */       Object object2 = object1;
/*     */       if (ResizablePoolActor.Resize$.MODULE$ == null) {
/*     */         if (object2 != null)
/*     */           boolean bool = false; 
/*     */       } else {
/*     */         if (ResizablePoolActor.Resize$.MODULE$.equals(object2))
/*     */           return true; 
/*     */         boolean bool = false;
/*     */       } 
/*     */       return true;
/*     */     }
/*     */     
/*     */     public ResizablePoolActor$$anonfun$receive$1(ResizablePoolActor $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\ResizablePoolActor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */