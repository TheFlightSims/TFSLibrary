/*     */ package akka.util;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.ParameterizedType;
/*     */ import java.lang.reflect.Type;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Serializable;
/*     */ import scala.StringContext;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.immutable.Seq;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class Reflect$ {
/*     */   public static final Reflect$ MODULE$;
/*     */   
/*     */   private final Option<Function1<Object, Class<?>>> getCallerClass;
/*     */   
/*     */   private Reflect$() {
/*  19 */     MODULE$ = this;
/*  29 */     this.getCallerClass = 
/*  30 */       liftedTree1$1();
/*     */   }
/*     */   
/*     */   public Option<Function1<Object, Class<?>>> getCallerClass() {
/*     */     return this.getCallerClass;
/*     */   }
/*     */   
/*     */   private final Option liftedTree1$1() {
/*     */     try {
/*  31 */       Class<?> c = Class.forName("sun.reflect.Reflection");
/*  32 */       (new Class[1])[0] = int.class;
/*  32 */       Method m = c.getMethod("getCallerClass", new Class[1]);
/*     */     } finally {
/*     */       scala.None$ none$;
/*     */       Exception exception1 = null, exception2 = exception1;
/*  35 */       Option option = scala.util.control.NonFatal$.MODULE$.unapply(exception2);
/*  35 */       if (option.isEmpty())
/*     */         throw exception1; 
/*     */     } 
/*     */     return (Option)none$;
/*     */   }
/*     */   
/*     */   public static class Reflect$$anonfun$liftedTree1$1$1 extends AbstractFunction1<Object, Class<?>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Method m$1;
/*     */     
/*     */     public final Class<?> apply(int i) {
/*     */       null;
/*     */       return (Class)this.m$1.invoke(null, new Object[] { BoxesRunTime.boxToInteger(i) });
/*     */     }
/*     */     
/*     */     public Reflect$$anonfun$liftedTree1$1$1(Method m$1) {}
/*     */   }
/*     */   
/*     */   public <T> T instantiate(Class clazz) {
/*     */     try {
/*     */     
/*  46 */     } catch (IllegalAccessException illegalAccessException) {
/*  47 */       Constructor<T> ctor = clazz.getDeclaredConstructor(new Class[0]);
/*  48 */       ctor.setAccessible(true);
/*     */     } 
/*  49 */     return ctor.newInstance(new Object[0]);
/*     */   }
/*     */   
/*     */   public <T> T instantiate(Class<T> clazz, Seq args) {
/*  57 */     return instantiate(findConstructor(clazz, args), args);
/*     */   }
/*     */   
/*     */   public <T> T instantiate(Constructor<T> constructor, Seq args) {
/*  65 */     constructor.setAccessible(true);
/*     */     try {
/*  66 */       return constructor.newInstance((Object[])args.toArray(scala.reflect.ClassTag$.MODULE$.AnyRef()));
/*  66 */     } catch (IllegalArgumentException illegalArgumentException) {
/*  69 */       String argString = ((TraversableOnce)args.map((Function1)new Reflect$$anonfun$2(), scala.collection.immutable.Seq$.MODULE$.canBuildFrom())).mkString("[", ", ", "]");
/*  70 */       (new String[3])[0] = "constructor ";
/*  70 */       (new String[3])[1] = " is incompatible with arguments ";
/*  70 */       (new String[3])[2] = "";
/*  70 */       throw new IllegalArgumentException((new StringContext(scala.Predef$.MODULE$.wrapRefArray((Object[])new String[3]))).s(scala.Predef$.MODULE$.genericWrapArray(new Object[] { constructor, argString }, )), illegalArgumentException);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static class Reflect$$anonfun$2 extends AbstractFunction1<Object, Class<?>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Class<?> apply(Object a) {
/*     */       return Reflect$.MODULE$.akka$util$Reflect$$safeGetClass(a);
/*     */     }
/*     */   }
/*     */   
/*     */   private final scala.runtime.Nothing$ error$1(String msg, Class clazz$1, Seq args$1) {
/*  81 */     String argClasses = ((TraversableOnce)args$1.map((Function1)new Reflect$$anonfun$3(), scala.collection.immutable.Seq$.MODULE$.canBuildFrom())).mkString(", ");
/*  82 */     (new String[4])[0] = "";
/*  82 */     (new String[4])[1] = " found on ";
/*  82 */     (new String[4])[2] = " for arguments [";
/*  82 */     (new String[4])[3] = "]";
/*  82 */     throw new IllegalArgumentException((new StringContext(scala.Predef$.MODULE$.wrapRefArray((Object[])new String[4]))).s(scala.Predef$.MODULE$.genericWrapArray(new Object[] { msg, clazz$1, argClasses })));
/*     */   }
/*     */   
/*     */   public static class Reflect$$anonfun$3 extends AbstractFunction1<Object, Class<?>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Class<?> apply(Object a) {
/*     */       return Reflect$.MODULE$.akka$util$Reflect$$safeGetClass(a);
/*     */     }
/*     */   }
/*     */   
/*     */   public <T> Constructor<T> findConstructor(Class clazz, Seq args) {
/*  88 */     int length = args.length();
/*  89 */     Iterator candidates = 
/*  90 */       scala.Predef$.MODULE$.refArrayOps((Object[])clazz.getDeclaredConstructors()).iterator().filter((Function1)new Reflect$$anonfun$6(args, length));
/* 100 */     Constructor cstrtr = (Constructor)candidates.next();
/* 101 */     if (candidates.hasNext())
/* 101 */       throw error$1("multiple matching constructors", clazz, args); 
/* 103 */     null;
/* 103 */     Constructor<T> constructor = args.isEmpty() ? (Constructor)scala.util.Try$.MODULE$.apply((Function0)new Reflect$$anonfun$4(clazz)).getOrElse((Function0)new Reflect$$anonfun$5()) : (candidates.hasNext() ? cstrtr : null);
/* 106 */     if (constructor == null)
/* 106 */       throw error$1("no matching constructor", clazz, args); 
/* 107 */     return constructor;
/*     */   }
/*     */   
/*     */   public static class Reflect$$anonfun$4 extends AbstractFunction0<Constructor<T>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Class clazz$1;
/*     */     
/*     */     public final Constructor<T> apply() {
/*     */       return this.clazz$1.getDeclaredConstructor(new Class[0]);
/*     */     }
/*     */     
/*     */     public Reflect$$anonfun$4(Class clazz$1) {}
/*     */   }
/*     */   
/*     */   public static class Reflect$$anonfun$5 extends AbstractFunction0<scala.runtime.Null$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final scala.runtime.Null$ apply() {
/*     */       return null;
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
/*     */       Class[] parameterTypes = c.getParameterTypes();
/*     */       return (parameterTypes.length == this.length$1 && scala.Predef$.MODULE$.refArrayOps((Object[])parameterTypes).iterator().zip(this.args$1.iterator()).forall((Function1)new Reflect$$anonfun$6$$anonfun$apply$1(this)));
/*     */     }
/*     */     
/*     */     public class Reflect$$anonfun$6$$anonfun$apply$1 extends AbstractFunction1<Tuple2<Class<?>, Object>, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final boolean apply(Tuple2 x0$1) {
/*     */         Tuple2 tuple2 = x0$1;
/*     */         if (tuple2 != null) {
/*     */           Class<?> found = (Class)tuple2._1();
/*     */           Object required = tuple2._2();
/*     */           return (found.isInstance(required) || BoxedType$.MODULE$.apply(found).isInstance(required) || (required == null && !found.isPrimitive()));
/*     */         } 
/*     */         throw new MatchError(tuple2);
/*     */       }
/*     */       
/*     */       public Reflect$$anonfun$6$$anonfun$apply$1(Reflect$$anonfun$6 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public Class<?> akka$util$Reflect$$safeGetClass(Object a) {
/* 111 */     return (a == null) ? Object.class : a.getClass();
/*     */   }
/*     */   
/*     */   public <T> Function0<T> instantiator(Class clazz) {
/* 119 */     return (Function0<T>)new Reflect$$anonfun$instantiator$1(clazz);
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
/*     */   private final Type rec$1(Class<?> curr, Class marker$1) {
/*     */     while (true) {
/* 123 */       if (curr.getSuperclass() != null && marker$1.isAssignableFrom(curr.getSuperclass())) {
/* 123 */         curr = curr.getSuperclass();
/*     */         continue;
/*     */       } 
/* 129 */       boolean bool = false;
/* 129 */       null;
/* 129 */       Object object = null;
/*     */       continue;
/*     */       option1 = scala.Predef$.MODULE$.refArrayOps((Object[])curr.getGenericInterfaces()).collectFirst((PartialFunction)new Reflect$$anonfun$1(marker$1));
/*     */       option2 = option1;
/*     */     } 
/* 131 */     throw scala.Predef$.MODULE$.$qmark$qmark$qmark();
/*     */   }
/*     */   
/*     */   public static class Reflect$$anonfun$1 extends AbstractPartialFunction<Type, Type> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Class marker$1;
/*     */     
/*     */     public final <A1 extends Type, B1> B1 applyOrElse(Type x1, Function1 default) {
/*     */       Type type = x1;
/*     */       if (type instanceof Class) {
/*     */         Class<?> clazz = (Class)type;
/*     */         if (this.marker$1.isAssignableFrom(clazz))
/*     */           return (B1)clazz; 
/*     */       } 
/*     */       if (type instanceof ParameterizedType) {
/*     */         ParameterizedType parameterizedType = (ParameterizedType)type;
/*     */         if (this.marker$1.isAssignableFrom((Class)parameterizedType.getRawType()))
/*     */           return (B1)parameterizedType; 
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
/*     */       if (type instanceof ParameterizedType) {
/*     */         ParameterizedType parameterizedType = (ParameterizedType)type;
/*     */         if (this.marker$1.isAssignableFrom((Class)parameterizedType.getRawType()))
/*     */           return true; 
/*     */       } 
/*     */       return false;
/*     */     }
/*     */     
/*     */     public Reflect$$anonfun$1(Class marker$1) {}
/*     */   }
/*     */   
/*     */   public Type findMarker(Class root, Class marker) {
/* 134 */     return rec$1(root, marker);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akk\\util\Reflect$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */