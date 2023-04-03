/*     */ package akka.util;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.ParameterizedType;
/*     */ import java.lang.reflect.Type;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.immutable.Seq;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.Null$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005erAB\001\003\021\003!a!A\004SK\032dWm\031;\013\005\r!\021\001B;uS2T\021!B\001\005C.\\\027\r\005\002\b\0215\t!A\002\004\n\005!\005AA\003\002\b%\0264G.Z2u'\tA1\002\005\002\r\0375\tQBC\001\017\003\025\0318-\0317b\023\t\001RB\001\004B]f\024VM\032\005\006%!!\t\001F\001\007y%t\027\016\036 \004\001Q\ta\001C\004\027\021\t\007I\021A\f\002\035\035,GoQ1mY\026\0248\t\\1tgV\t\001\004E\002\r3mI!AG\007\003\r=\003H/[8o!\021aADH\021\n\005ui!!\003$v]\016$\030n\03482!\taq$\003\002!\033\t\031\021J\034;1\005\tZ\003cA\022'S9\021A\002J\005\003K5\ta\001\025:fI\0264\027BA\024)\005\025\031E.Y:t\025\t)S\002\005\002+W1\001A!\003\027.\003\003\005\tQ!\0016\005\ryF%\r\005\007]!\001\013\021B\030\002\037\035,GoQ1mY\026\0248\t\\1tg\002\0022\001D\r1!\021aADH\0311\005I\"\004cA\022'gA\021!\006\016\003\nY5\n\t\021!A\003\002U\n\"AN\035\021\00519\024B\001\035\016\005\035qu\016\0365j]\036\004\"\001\004\036\n\005mj!aA!os\"1Q\b\003C\001\ty\n1\"\0338ti\006tG/[1uKV\021q(\021\013\003\001\016\003\"AK!\005\013\tc$\031A\033\003\003QCQ\001\022\037A\002\025\013Qa\0317buj\0042a\t\024A\021\031i\004\002\"\001\005\017V\021\001J\023\013\004\023.k\005C\001\026K\t\025\021eI1\0016\021\025!e\t1\001M!\r\031c%\023\005\006\035\032\003\raT\001\005CJ<7\017E\002Q+fj\021!\025\006\003%N\013\021\"[7nkR\f'\r\\3\013\005Qk\021AC2pY2,7\r^5p]&\021a+\025\002\004'\026\f\bBB\037\t\t\003!\001,\006\002Z7R\031!\f\0305\021\005)ZF!\002\"X\005\004)\004\"B/X\001\004q\026aC2p]N$(/^2u_J\0042a\0304[\033\005\001'BA1c\003\035\021XM\0327fGRT!a\0313\002\t1\fgn\032\006\002K\006!!.\031<b\023\t9\007MA\006D_:\034HO];di>\024\b\"\002(X\001\004y\005B\0026\t\t\003!1.A\bgS:$7i\0348tiJ,8\r^8s+\taw\016F\002naJ\0042a\0304o!\tQs\016B\003CS\n\007Q\007C\003ES\002\007\021\017E\002$M9DQAT5A\002=CQ\001\036\005\005\nU\fAb]1gK\036+Go\0217bgN$\"A^>1\005]L\bcA\022'qB\021!&\037\003\nuN\f\t\021!A\003\002U\0221a\030\0234\021\025a8\0171\001:\003\005\t\007B\002@\t\t\003!q0\001\007j]N$\030M\034;jCR|'/\006\003\002\002\005-A\003BA\002\003\033\001R\001DA\003\003\023I1!a\002\016\005%1UO\\2uS>t\007\007E\002+\003\027!QAQ?C\002UBa\001R?A\002\005=\001\003B\022'\003\023Aq!a\005\t\t\003\t)\"\001\006gS:$W*\031:lKJ$b!a\006\002\036\005-\002cA0\002\032%\031\0211\0041\003\tQK\b/\032\005\t\003?\t\t\0021\001\002\"\005!!o\\8ua\021\t\031#a\n\021\t\r2\023Q\005\t\004U\005\035BaCA\025\003;\t\t\021!A\003\002U\0221a\030\0235\021!\ti#!\005A\002\005=\022AB7be.,'\017\r\003\0022\005U\002\003B\022'\003g\0012AKA\033\t-\t9$a\013\002\002\003\005)\021A\033\003\007}#S\007")
/*     */ public final class Reflect {
/*     */   public static Type findMarker(Class<?> paramClass1, Class<?> paramClass2) {
/*     */     return Reflect$.MODULE$.findMarker(paramClass1, paramClass2);
/*     */   }
/*     */   
/*     */   public static Option<Function1<Object, Class<?>>> getCallerClass() {
/*     */     return Reflect$.MODULE$.getCallerClass();
/*     */   }
/*     */   
/*     */   public static class Reflect$$anonfun$liftedTree1$1$1 extends AbstractFunction1<Object, Class<?>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Method m$1;
/*     */     
/*     */     public final Class<?> apply(int i) {
/*  33 */       null;
/*  33 */       return (Class)this.m$1.invoke(null, new Object[] { BoxesRunTime.boxToInteger(i) });
/*     */     }
/*     */     
/*     */     public Reflect$$anonfun$liftedTree1$1$1(Method m$1) {}
/*     */   }
/*     */   
/*     */   public static class Reflect$$anonfun$2 extends AbstractFunction1<Object, Class<?>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Class<?> apply(Object a) {
/*  69 */       return Reflect$.MODULE$.akka$util$Reflect$$safeGetClass(a);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Reflect$$anonfun$3 extends AbstractFunction1<Object, Class<?>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Class<?> apply(Object a) {
/*  81 */       return Reflect$.MODULE$.akka$util$Reflect$$safeGetClass(a);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Reflect$$anonfun$4 extends AbstractFunction0<Constructor<T>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Class clazz$1;
/*     */     
/*     */     public final Constructor<T> apply() {
/*  86 */       return this.clazz$1.getDeclaredConstructor(new Class[0]);
/*     */     }
/*     */     
/*     */     public Reflect$$anonfun$4(Class clazz$1) {}
/*     */   }
/*     */   
/*     */   public static class Reflect$$anonfun$5 extends AbstractFunction0<Null$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Null$ apply() {
/*  86 */       return null;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Reflect$$anonfun$6 extends AbstractFunction1<Constructor<T>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Seq args$1;
/*     */     
/*     */     private final int length$1;
/*     */     
/*     */     public Reflect$$anonfun$6(Seq args$1, int length$1) {}
/*     */     
/*     */     public final boolean apply(Constructor c) {
/*  91 */       Class[] parameterTypes = c.getParameterTypes();
/*  92 */       return (parameterTypes.length == this.length$1 && 
/*  93 */         Predef$.MODULE$.refArrayOps((Object[])parameterTypes).iterator().zip(this.args$1.iterator()).forall((Function1)new Reflect$$anonfun$6$$anonfun$apply$1(this)));
/*     */     }
/*     */     
/*     */     public class Reflect$$anonfun$6$$anonfun$apply$1 extends AbstractFunction1<Tuple2<Class<?>, Object>, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final boolean apply(Tuple2 x0$1) {
/*  93 */         Tuple2 tuple2 = x0$1;
/*  93 */         if (tuple2 != null) {
/*  94 */           Class<?> found = (Class)tuple2._1();
/*  94 */           Object required = tuple2._2();
/*  95 */           return (found.isInstance(required) || BoxedType$.MODULE$.apply(found).isInstance(required) || (
/*  96 */             required == null && !found.isPrimitive()));
/*     */         } 
/*     */         throw new MatchError(tuple2);
/*     */       }
/*     */       
/*     */       public Reflect$$anonfun$6$$anonfun$apply$1(Reflect$$anonfun$6 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Reflect$$anonfun$instantiator$1 extends AbstractFunction0<T> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Class clazz$2;
/*     */     
/*     */     public final T apply() {
/* 119 */       return Reflect$.MODULE$.instantiate(this.clazz$2);
/*     */     }
/*     */     
/*     */     public Reflect$$anonfun$instantiator$1(Class clazz$2) {}
/*     */   }
/*     */   
/*     */   public static class Reflect$$anonfun$1 extends AbstractPartialFunction<Type, Type> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Class marker$1;
/*     */     
/*     */     public final <A1 extends Type, B1> B1 applyOrElse(Type x1, Function1 default) {
/* 124 */       Type type = x1;
/* 125 */       if (type instanceof Class) {
/* 125 */         Class<?> clazz = (Class)type;
/* 125 */         if (this.marker$1.isAssignableFrom(clazz))
/* 125 */           return (B1)clazz; 
/*     */       } 
/* 126 */       if (type instanceof ParameterizedType) {
/* 126 */         ParameterizedType parameterizedType = (ParameterizedType)type;
/* 126 */         if (this.marker$1.isAssignableFrom((Class)parameterizedType.getRawType()))
/* 126 */           return (B1)parameterizedType; 
/*     */       } 
/*     */       return (B1)default.apply(x1);
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Type x1) {
/*     */       Type type = x1;
/*     */       if (type instanceof Class) {
/*     */         Class<?> clazz = (Class)type;
/*     */         if (this.marker$1.isAssignableFrom(clazz))
/*     */           return true; 
/*     */       } 
/* 126 */       if (type instanceof ParameterizedType) {
/* 126 */         ParameterizedType parameterizedType = (ParameterizedType)type;
/* 126 */         if (this.marker$1.isAssignableFrom((Class)parameterizedType.getRawType()))
/*     */           return true; 
/*     */       } 
/*     */       return false;
/*     */     }
/*     */     
/*     */     public Reflect$$anonfun$1(Class marker$1) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akk\\util\Reflect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */