/*     */ package akka.actor;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.immutable.Seq;
/*     */ import scala.collection.immutable.Seq$;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ClassTag$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.StringAdd$;
/*     */ import scala.util.Try;
/*     */ import scala.util.Try$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\0055b\001B\001\003\001\035\021qCU3gY\026\034G/\033<f\tft\027-\\5d\003\016\034Wm]:\013\005\r!\021!B1di>\024(\"A\003\002\t\005\\7.Y\002\001'\t\001\001\002\005\002\n\0255\t!!\003\002\f\005\tiA)\0378b[&\034\027iY2fgND\001\"\004\001\003\006\004%\tAD\001\fG2\f7o\035'pC\022,'/F\001\020!\t\001R#D\001\022\025\t\0212#\001\003mC:<'\"\001\013\002\t)\fg/Y\005\003-E\0211b\0217bgNdu.\0313fe\"A\001\004\001B\001B\003%q\"\001\007dY\006\0348\017T8bI\026\024\b\005C\003\033\001\021\0051$\001\004=S:LGO\020\013\0039u\001\"!\003\001\t\0135I\002\031A\b\t\013}\001A\021\t\021\002\027\035,Go\0217bgN4uN]\013\003Cu\"\"AI&\025\005\r\032\005c\001\023*W5\tQE\003\002'O\005!Q\017^5m\025\005A\023!B:dC2\f\027B\001\026&\005\r!&/\037\031\003YY\0022!L\0315\035\tqs&D\001(\023\t\001t%\001\004Qe\026$WMZ\005\003eM\022Qa\0217bgNT!\001M\024\021\005U2D\002\001\003\noy\t\t\021!A\003\002a\0221a\030\0236#\tID\b\005\002/u%\0211h\n\002\b\035>$\b.\0338h!\t)T\bB\003?=\t\007qHA\001U#\tI\004\t\005\002/\003&\021!i\n\002\004\003:L\bb\002#\037\003\003\005\035!R\001\013KZLG-\0328dK\022*\004c\001$Jy5\tqI\003\002IO\0059!/\0324mK\016$\030B\001&H\005!\031E.Y:t)\006<\007\"\002'\037\001\004i\025\001\0024rG:\004\"!\f(\n\005=\033$AB*ue&tw\rC\003R\001\021\005#+A\tde\026\fG/Z%ogR\fgnY3G_J,\"aU,\025\007Q[&\r\006\002V1B\031A%\013,\021\005U:F!\002 Q\005\004y\004bB-Q\003\003\005\035AW\001\013KZLG-\0328dK\0222\004c\001$J-\")A\f\025a\001;\006)1\r\\1{uB\022a\f\031\t\004[Ez\006CA\033a\t%\t7,!A\001\002\013\005qHA\002`IaBQa\031)A\002\021\fA!\031:hgB\031QM\0337\016\003\031T!a\0325\002\023%lW.\036;bE2,'BA5(\003)\031w\016\0347fGRLwN\\\005\003W\032\0241aU3r!\021qSn\034;\n\0059<#A\002+va2,'\007\r\002qeB\031Q&M9\021\005U\022H!C:c\003\003\005\tQ!\001@\005\ryF%\017\t\003]UL!A^\024\003\r\005s\027PU3g\021\025\t\006\001\"\021y+\tIX\020F\003{\003\007\t)\001\006\002|}B\031A%\013?\021\005UjH!\002 x\005\004y\004\002C@x\003\003\005\035!!\001\002\025\0254\030\016Z3oG\026$s\007E\002G\023rDQ\001T<A\0025CaaY<A\002\005\035\001\003B3k\003\023\001RAL7\002\fQ\004D!!\004\002\022A!Q&MA\b!\r)\024\021\003\003\f\003'\t)!!A\001\002\013\005qH\001\003`IE\002\004bBA\f\001\021\005\023\021D\001\rO\026$xJ\0316fGR4uN]\013\005\0037\t\031\003\006\003\002\036\005-B\003BA\020\003K\001B\001J\025\002\"A\031Q'a\t\005\ry\n)B1\001@\021)\t9#!\006\002\002\003\017\021\021F\001\013KZLG-\0328dK\022B\004\003\002$J\003CAa\001TA\013\001\004i\005")
/*     */ public class ReflectiveDynamicAccess extends DynamicAccess {
/*     */   private final ClassLoader classLoader;
/*     */   
/*     */   public ClassLoader classLoader() {
/*  63 */     return this.classLoader;
/*     */   }
/*     */   
/*     */   public ReflectiveDynamicAccess(ClassLoader classLoader) {}
/*     */   
/*     */   public <T> Try<Class<? extends T>> getClassFor(String fqcn, ClassTag evidence$5) {
/*  66 */     return Try$.MODULE$.apply((Function0)new ReflectiveDynamicAccess$$anonfun$getClassFor$1(this, fqcn, evidence$5));
/*     */   }
/*     */   
/*     */   public class ReflectiveDynamicAccess$$anonfun$getClassFor$1 extends AbstractFunction0<Class<Object>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final String fqcn$1;
/*     */     
/*     */     private final ClassTag evidence$5$1;
/*     */     
/*     */     public ReflectiveDynamicAccess$$anonfun$getClassFor$1(ReflectiveDynamicAccess $outer, String fqcn$1, ClassTag evidence$5$1) {}
/*     */     
/*     */     public final Class<Object> apply() {
/*  67 */       Class<?> c = Class.forName(this.fqcn$1, false, this.$outer.classLoader());
/*  68 */       Class t = ((ClassTag)Predef$.MODULE$.implicitly(this.evidence$5$1)).runtimeClass();
/*  69 */       if (t.isAssignableFrom(c))
/*  69 */         return (Class)c; 
/*  69 */       throw new ClassCastException((new StringBuilder()).append(StringAdd$.MODULE$.$plus$extension(Predef$.MODULE$.any2stringadd(t), " is not assignable from ")).append(c).toString());
/*     */     }
/*     */   }
/*     */   
/*     */   public <T> Try<T> createInstanceFor(Class clazz, Seq args, ClassTag evidence$6) {
/*  81 */     return Try$.MODULE$.apply((Function0)new ReflectiveDynamicAccess$$anonfun$createInstanceFor$2(this, clazz, args, evidence$6)).recover((PartialFunction)new ReflectiveDynamicAccess$$anonfun$createInstanceFor$1(this));
/*     */   }
/*     */   
/*     */   public class ReflectiveDynamicAccess$$anonfun$createInstanceFor$2 extends AbstractFunction0<T> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Class clazz$1;
/*     */     
/*     */     private final Seq args$1;
/*     */     
/*     */     private final ClassTag evidence$6$1;
/*     */     
/*     */     public ReflectiveDynamicAccess$$anonfun$createInstanceFor$2(ReflectiveDynamicAccess $outer, Class clazz$1, Seq args$1, ClassTag evidence$6$1) {}
/*     */     
/*     */     public final T apply() {
/*     */       Class[] types = (Class[])((TraversableOnce)this.args$1.map((Function1)new ReflectiveDynamicAccess$$anonfun$createInstanceFor$2$$anonfun$2(this), Seq$.MODULE$.canBuildFrom())).toArray(ClassTag$.MODULE$.apply(Class.class));
/*     */       Object[] values = (Object[])((TraversableOnce)this.args$1.map((Function1)new ReflectiveDynamicAccess$$anonfun$createInstanceFor$2$$anonfun$3(this), Seq$.MODULE$.canBuildFrom())).toArray(ClassTag$.MODULE$.AnyRef());
/*     */       Constructor constructor = this.clazz$1.getDeclaredConstructor(types);
/*     */       constructor.setAccessible(true);
/*     */       Object obj = constructor.newInstance(values);
/*     */       Class t = ((ClassTag)Predef$.MODULE$.implicitly(this.evidence$6$1)).runtimeClass();
/*     */       if (t.isInstance(obj))
/*     */         return (T)obj; 
/*     */       throw new ClassCastException((new StringBuilder()).append(this.clazz$1.getName()).append(" is not a subtype of ").append(t).toString());
/*     */     }
/*     */     
/*     */     public class ReflectiveDynamicAccess$$anonfun$createInstanceFor$2$$anonfun$2 extends AbstractFunction1<Tuple2<Class<?>, Object>, Class<?>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Class<?> apply(Tuple2 x$1) {
/*     */         return (Class)x$1._1();
/*     */       }
/*     */       
/*     */       public ReflectiveDynamicAccess$$anonfun$createInstanceFor$2$$anonfun$2(ReflectiveDynamicAccess$$anonfun$createInstanceFor$2 $outer) {}
/*     */     }
/*     */     
/*     */     public class ReflectiveDynamicAccess$$anonfun$createInstanceFor$2$$anonfun$3 extends AbstractFunction1<Tuple2<Class<?>, Object>, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Object apply(Tuple2 x$2) {
/*     */         return x$2._2();
/*     */       }
/*     */       
/*     */       public ReflectiveDynamicAccess$$anonfun$createInstanceFor$2$$anonfun$3(ReflectiveDynamicAccess$$anonfun$createInstanceFor$2 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class ReflectiveDynamicAccess$$anonfun$createInstanceFor$1 extends AbstractPartialFunction<Throwable, T> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x1, Function1 default) {
/*  81 */       Throwable throwable = x1;
/*  81 */       if (throwable instanceof InvocationTargetException) {
/*  81 */         InvocationTargetException invocationTargetException = (InvocationTargetException)throwable;
/*  81 */         if (invocationTargetException.getTargetException() != null)
/*  81 */           throw invocationTargetException.getTargetException(); 
/*     */       } 
/*  81 */       return (B1)default.apply(x1);
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Throwable x1) {
/*  81 */       Throwable throwable = x1;
/*  81 */       if (throwable instanceof InvocationTargetException) {
/*  81 */         InvocationTargetException invocationTargetException = (InvocationTargetException)throwable;
/*  81 */         if (invocationTargetException.getTargetException() != null)
/*  81 */           return true; 
/*     */       } 
/*  81 */       return false;
/*     */     }
/*     */     
/*     */     public ReflectiveDynamicAccess$$anonfun$createInstanceFor$1(ReflectiveDynamicAccess $outer) {}
/*     */   }
/*     */   
/*     */   public <T> Try<T> createInstanceFor(String fqcn, Seq args, ClassTag<T> evidence$7) {
/*  84 */     return getClassFor(fqcn, evidence$7).flatMap((Function1)new ReflectiveDynamicAccess$$anonfun$createInstanceFor$3(this, args, evidence$7));
/*     */   }
/*     */   
/*     */   public class ReflectiveDynamicAccess$$anonfun$createInstanceFor$3 extends AbstractFunction1<Class<? extends T>, Try<T>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Seq args$2;
/*     */     
/*     */     private final ClassTag evidence$7$1;
/*     */     
/*     */     public final Try<T> apply(Class<?> c) {
/*  84 */       return this.$outer.createInstanceFor(c, this.args$2, this.evidence$7$1);
/*     */     }
/*     */     
/*     */     public ReflectiveDynamicAccess$$anonfun$createInstanceFor$3(ReflectiveDynamicAccess $outer, Seq args$2, ClassTag evidence$7$1) {}
/*     */   }
/*     */   
/*     */   public <T> Try<T> getObjectFor(String fqcn, ClassTag<?> evidence$8) {
/*  87 */     Try classTry = 
/*  88 */       fqcn.endsWith("$") ? getClassFor(fqcn, evidence$8) : 
/*  89 */       getClassFor((new StringBuilder()).append(fqcn).append("$").toString(), (ClassTag)evidence$8).recoverWith((PartialFunction)new ReflectiveDynamicAccess$$anonfun$1(this, fqcn, evidence$8));
/*  90 */     return classTry.flatMap((Function1)new ReflectiveDynamicAccess$$anonfun$getObjectFor$1(this, fqcn, evidence$8));
/*     */   }
/*     */   
/*     */   public class ReflectiveDynamicAccess$$anonfun$1 extends AbstractPartialFunction<Throwable, Try<Class<? extends T>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final String fqcn$2;
/*     */     
/*     */     private final ClassTag evidence$8$1;
/*     */     
/*     */     public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x2, Function1 default) {
/*     */       Throwable throwable = x2;
/*     */       return (B1)this.$outer.getClassFor(this.fqcn$2, this.evidence$8$1);
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Throwable x2) {
/*     */       Throwable throwable = x2;
/*     */       return true;
/*     */     }
/*     */     
/*     */     public ReflectiveDynamicAccess$$anonfun$1(ReflectiveDynamicAccess $outer, String fqcn$2, ClassTag evidence$8$1) {}
/*     */   }
/*     */   
/*     */   public class ReflectiveDynamicAccess$$anonfun$getObjectFor$1 extends AbstractFunction1<Class<? extends T>, Try<T>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String fqcn$2;
/*     */     
/*     */     public final ClassTag evidence$8$1;
/*     */     
/*     */     public ReflectiveDynamicAccess$$anonfun$getObjectFor$1(ReflectiveDynamicAccess $outer, String fqcn$2, ClassTag evidence$8$1) {}
/*     */     
/*     */     public final Try<T> apply(Class c) {
/* 100 */       return Try$.MODULE$.apply((Function0)new ReflectiveDynamicAccess$$anonfun$getObjectFor$1$$anonfun$apply$2(this, c)).recover((PartialFunction)new ReflectiveDynamicAccess$$anonfun$getObjectFor$1$$anonfun$apply$1(this));
/*     */     }
/*     */     
/*     */     public class ReflectiveDynamicAccess$$anonfun$getObjectFor$1$$anonfun$apply$2 extends AbstractFunction0<T> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Class c$1;
/*     */       
/*     */       public ReflectiveDynamicAccess$$anonfun$getObjectFor$1$$anonfun$apply$2(ReflectiveDynamicAccess$$anonfun$getObjectFor$1 $outer, Class c$1) {}
/*     */       
/*     */       public final T apply() {
/*     */         Field module = this.c$1.getDeclaredField("MODULE$");
/*     */         module.setAccessible(true);
/*     */         Class t = ((ClassTag)Predef$.MODULE$.implicitly(this.$outer.evidence$8$1)).runtimeClass();
/*     */         null;
/*     */         Object object = module.get(null);
/*     */         if (object == null)
/*     */           throw new NullPointerException(); 
/*     */         if (t.isInstance(object)) {
/*     */           Option option = this.$outer.evidence$8$1.unapply(object);
/*     */           if (!option.isEmpty() && option.get() instanceof Object)
/*     */             return (T)object; 
/*     */           throw new MatchError(object);
/*     */         } 
/*     */         throw new ClassCastException((new StringBuilder()).append(this.$outer.fqcn$2).append(" is not a subtype of ").append(t).toString());
/*     */       }
/*     */     }
/*     */     
/*     */     public class ReflectiveDynamicAccess$$anonfun$getObjectFor$1$$anonfun$apply$1 extends AbstractPartialFunction<Throwable, T> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x3, Function1 default) {
/* 100 */         Throwable throwable = x3;
/* 100 */         if (throwable instanceof InvocationTargetException) {
/* 100 */           InvocationTargetException invocationTargetException = (InvocationTargetException)throwable;
/* 100 */           if (invocationTargetException.getTargetException() != null)
/* 100 */             throw invocationTargetException.getTargetException(); 
/*     */         } 
/* 100 */         return (B1)default.apply(x3);
/*     */       }
/*     */       
/*     */       public final boolean isDefinedAt(Throwable x3) {
/* 100 */         Throwable throwable = x3;
/* 100 */         if (throwable instanceof InvocationTargetException) {
/* 100 */           InvocationTargetException invocationTargetException = (InvocationTargetException)throwable;
/* 100 */           if (invocationTargetException.getTargetException() != null)
/* 100 */             return true; 
/*     */         } 
/* 100 */         return false;
/*     */       }
/*     */       
/*     */       public ReflectiveDynamicAccess$$anonfun$getObjectFor$1$$anonfun$apply$1(ReflectiveDynamicAccess$$anonfun$getObjectFor$1 $outer) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ReflectiveDynamicAccess.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */