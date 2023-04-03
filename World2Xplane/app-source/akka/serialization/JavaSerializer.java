/*     */ package akka.serialization;
/*     */ 
/*     */ import akka.actor.ExtendedActorSystem;
/*     */ import akka.util.ClassLoaderObjectInputStream;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.util.concurrent.Callable;
/*     */ import scala.Function0;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.util.DynamicVariable;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\035q!B\001\003\021\0039\021A\004&bm\006\034VM]5bY&TXM\035\006\003\007\021\tQb]3sS\006d\027N_1uS>t'\"A\003\002\t\005\\7.Y\002\001!\tA\021\"D\001\003\r\025Q!\001#\001\f\0059Q\025M^1TKJL\027\r\\5{KJ\034\"!\003\007\021\0055\001R\"\001\b\013\003=\tQa]2bY\006L!!\005\b\003\r\005s\027PU3g\021\025\031\022\002\"\001\025\003\031a\024N\\5u}Q\tq\001C\004\027\023\t\007I\021A\f\002\033\r,(O]3oiNK8\017^3n+\005A\002CA\r\033\033\005Ia\001B\016\n\005q\021QbQ;se\026tGoU=ti\026l7C\001\016\036!\rq\022eI\007\002?)\021\001ED\001\005kRLG.\003\002#?\tyA)\0378b[&\034g+\031:jC\ndW\r\005\002%O5\tQE\003\002'\t\005)\021m\031;pe&\021\001&\n\002\024\013b$XM\0343fI\006\033Go\034:TsN$X-\034\005\006'i!\tA\013\013\0021!)AF\007C\001[\005Iq/\033;i-\006dW/Z\013\003]E\"2a\f\036=!\t\001\024\007\004\001\005\013IZ#\031A\032\003\003M\013\"\001N\034\021\0055)\024B\001\034\017\005\035qu\016\0365j]\036\004\"!\004\035\n\005er!aA!os\")1h\013a\001G\005)a/\0317vK\")Qh\013a\001}\005A1-\0317mC\ndW\rE\002@\013>j\021\001\021\006\003\003\n\013!bY8oGV\024(/\0328u\025\t\0013IC\001E\003\021Q\027M^1\n\005\031\003%\001C\"bY2\f'\r\\3\t\r!K\001\025!\003\031\0039\031WO\035:f]R\034\026p\035;f[\0022AA\003\002\001\025N\031\021\nD&\021\005!a\025BA'\003\005)\031VM]5bY&TXM\035\005\t\037&\023)\031!C\001!\00611/_:uK6,\022a\t\005\t%&\023\t\021)A\005G\00591/_:uK6\004\003\"B\nJ\t\003!FCA+W!\tA\021\nC\003P'\002\0071\005C\003Y\023\022\005\021,A\bj]\016dW\017Z3NC:Lg-Z:u+\005Q\006CA\007\\\023\tafBA\004C_>dW-\0318\t\013yKE\021A0\002\025%$WM\034;jM&,'/F\001a!\ti\021-\003\002c\035\t\031\021J\034;\t\013\021LE\021A3\002\021Q|')\0338bef$\"A\0327\021\00759\027.\003\002i\035\t)\021I\035:bsB\021QB[\005\003W:\021AAQ=uK\")Qn\031a\001\031\005\tq\016C\003p\023\022\005\001/\001\006ge>l')\0338bef$2\001D9t\021\025\021h\0161\001g\003\025\021\027\020^3t\021\025!h\0161\001v\003\025\031G.\031>{!\ria\017_\005\003o:\021aa\0249uS>t\007gA=\002\004A!!0`A\001\035\ti10\003\002}\035\0051\001K]3eK\032L!A`@\003\013\rc\027m]:\013\005qt\001c\001\031\002\004\021Q\021QA:\002\002\003\005)\021A\032\003\007}#S\007")
/*     */ public class JavaSerializer implements Serializer {
/*     */   private final ExtendedActorSystem system;
/*     */   
/*     */   public static class CurrentSystem extends DynamicVariable<ExtendedActorSystem> {
/*     */     public CurrentSystem() {
/* 104 */       super(null);
/*     */     }
/*     */     
/*     */     public <S> S withValue(ExtendedActorSystem value, Callable callable) {
/* 113 */       return (S)withValue(value, (Function0)new JavaSerializer$CurrentSystem$$anonfun$withValue$1(this, callable));
/*     */     }
/*     */     
/*     */     public class JavaSerializer$CurrentSystem$$anonfun$withValue$1 extends AbstractFunction0<S> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Callable callable$1;
/*     */       
/*     */       public final S apply() {
/* 113 */         return this.callable$1.call();
/*     */       }
/*     */       
/*     */       public JavaSerializer$CurrentSystem$$anonfun$withValue$1(JavaSerializer.CurrentSystem $outer, Callable callable$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public final Object fromBinary(byte[] bytes) {
/* 120 */     return Serializer$class.fromBinary(this, bytes);
/*     */   }
/*     */   
/*     */   public final Object fromBinary(byte[] bytes, Class clazz) {
/* 120 */     return Serializer$class.fromBinary(this, bytes, clazz);
/*     */   }
/*     */   
/*     */   public ExtendedActorSystem system() {
/* 120 */     return this.system;
/*     */   }
/*     */   
/*     */   public JavaSerializer(ExtendedActorSystem system) {
/* 120 */     Serializer$class.$init$(this);
/*     */   }
/*     */   
/*     */   public boolean includeManifest() {
/* 122 */     return false;
/*     */   }
/*     */   
/*     */   public int identifier() {
/* 124 */     return 1;
/*     */   }
/*     */   
/*     */   public byte[] toBinary(Object o) {
/* 127 */     ByteArrayOutputStream bos = new ByteArrayOutputStream();
/* 128 */     ObjectOutputStream out = new ObjectOutputStream(bos);
/* 129 */     JavaSerializer$.MODULE$.currentSystem().withValue(system(), (Function0)new JavaSerializer$$anonfun$toBinary$1(this, o, out));
/* 130 */     out.close();
/* 131 */     return bos.toByteArray();
/*     */   }
/*     */   
/*     */   public class JavaSerializer$$anonfun$toBinary$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Object o$1;
/*     */     
/*     */     private final ObjectOutputStream out$1;
/*     */     
/*     */     public final void apply() {
/*     */       apply$mcV$sp();
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/*     */       this.out$1.writeObject(this.o$1);
/*     */     }
/*     */     
/*     */     public JavaSerializer$$anonfun$toBinary$1(JavaSerializer $outer, Object o$1, ObjectOutputStream out$1) {}
/*     */   }
/*     */   
/*     */   public Object fromBinary(byte[] bytes, Option clazz) {
/* 135 */     ClassLoaderObjectInputStream in = new ClassLoaderObjectInputStream(system().dynamicAccess().classLoader(), new ByteArrayInputStream(bytes));
/* 136 */     Object obj = JavaSerializer$.MODULE$.currentSystem().withValue(system(), (Function0)new JavaSerializer$$anonfun$1(this, in));
/* 137 */     in.close();
/* 138 */     return obj;
/*     */   }
/*     */   
/*     */   public static CurrentSystem currentSystem() {
/*     */     return JavaSerializer$.MODULE$.currentSystem();
/*     */   }
/*     */   
/*     */   public class JavaSerializer$$anonfun$1 extends AbstractFunction0<Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ClassLoaderObjectInputStream in$1;
/*     */     
/*     */     public final Object apply() {
/*     */       return this.in$1.readObject();
/*     */     }
/*     */     
/*     */     public JavaSerializer$$anonfun$1(JavaSerializer $outer, ClassLoaderObjectInputStream in$1) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\serialization\JavaSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */