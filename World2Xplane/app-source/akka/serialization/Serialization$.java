/*     */ package akka.serialization;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ActorRefProvider;
/*     */ import akka.actor.Address;
/*     */ import java.io.NotSerializableException;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.StringContext;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.Seq;
/*     */ import scala.collection.mutable.ArrayBuffer;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.util.DynamicVariable;
/*     */ import scala.util.Try;
/*     */ 
/*     */ public final class Serialization$ {
/*     */   public static final Serialization$ MODULE$;
/*     */   
/*     */   private final DynamicVariable<Serialization.Information> currentTransportInformation;
/*     */   
/*     */   private Serialization$() {
/*  17 */     MODULE$ = this;
/*  29 */     null;
/*  29 */     this.currentTransportInformation = new DynamicVariable(null);
/*     */   }
/*     */   
/*     */   public DynamicVariable<Serialization.Information> currentTransportInformation() {
/*  29 */     return this.currentTransportInformation;
/*     */   }
/*     */   
/*     */   public String serializedActorPath(ActorRef actorRef) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: invokevirtual path : ()Lakka/actor/ActorPath;
/*     */     //   4: astore_2
/*     */     //   5: aload_1
/*     */     //   6: astore #4
/*     */     //   8: aload #4
/*     */     //   10: instanceof akka/actor/ActorRefWithCell
/*     */     //   13: ifeq -> 41
/*     */     //   16: aload #4
/*     */     //   18: checkcast akka/actor/ActorRefWithCell
/*     */     //   21: astore #5
/*     */     //   23: aload #5
/*     */     //   25: invokevirtual underlying : ()Lakka/actor/Cell;
/*     */     //   28: invokeinterface system : ()Lakka/actor/ActorSystem;
/*     */     //   33: checkcast akka/actor/ExtendedActorSystem
/*     */     //   36: astore #6
/*     */     //   38: goto -> 46
/*     */     //   41: aconst_null
/*     */     //   42: pop
/*     */     //   43: aconst_null
/*     */     //   44: astore #6
/*     */     //   46: aload #6
/*     */     //   48: astore_3
/*     */     //   49: aload_0
/*     */     //   50: invokevirtual currentTransportInformation : ()Lscala/util/DynamicVariable;
/*     */     //   53: invokevirtual value : ()Ljava/lang/Object;
/*     */     //   56: checkcast akka/serialization/Serialization$Information
/*     */     //   59: astore #7
/*     */     //   61: aload #7
/*     */     //   63: ifnonnull -> 104
/*     */     //   66: aload_3
/*     */     //   67: astore #9
/*     */     //   69: aload #9
/*     */     //   71: ifnonnull -> 85
/*     */     //   74: aload_2
/*     */     //   75: invokeinterface toSerializationFormat : ()Ljava/lang/String;
/*     */     //   80: astore #10
/*     */     //   82: goto -> 254
/*     */     //   85: aload_2
/*     */     //   86: aload #9
/*     */     //   88: invokevirtual provider : ()Lakka/actor/ActorRefProvider;
/*     */     //   91: invokeinterface getDefaultAddress : ()Lakka/actor/Address;
/*     */     //   96: invokeinterface toSerializationFormatWithAddress : (Lakka/actor/Address;)Ljava/lang/String;
/*     */     //   101: goto -> 252
/*     */     //   104: aload #7
/*     */     //   106: ifnull -> 205
/*     */     //   109: aload #7
/*     */     //   111: invokevirtual address : ()Lakka/actor/Address;
/*     */     //   114: astore #15
/*     */     //   116: aload #7
/*     */     //   118: invokevirtual system : ()Lakka/actor/ActorSystem;
/*     */     //   121: astore #16
/*     */     //   123: aload_3
/*     */     //   124: ifnull -> 153
/*     */     //   127: aload_3
/*     */     //   128: aload #16
/*     */     //   130: astore #17
/*     */     //   132: dup
/*     */     //   133: ifnonnull -> 145
/*     */     //   136: pop
/*     */     //   137: aload #17
/*     */     //   139: ifnull -> 153
/*     */     //   142: goto -> 164
/*     */     //   145: aload #17
/*     */     //   147: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   150: ifeq -> 164
/*     */     //   153: aload_2
/*     */     //   154: aload #15
/*     */     //   156: invokeinterface toSerializationFormatWithAddress : (Lakka/actor/Address;)Ljava/lang/String;
/*     */     //   161: goto -> 200
/*     */     //   164: aload_3
/*     */     //   165: invokevirtual provider : ()Lakka/actor/ActorRefProvider;
/*     */     //   168: astore #18
/*     */     //   170: aload_2
/*     */     //   171: aload #18
/*     */     //   173: aload #15
/*     */     //   175: invokeinterface getExternalAddressFor : (Lakka/actor/Address;)Lscala/Option;
/*     */     //   180: new akka/serialization/Serialization$$anonfun$serializedActorPath$1
/*     */     //   183: dup
/*     */     //   184: aload #18
/*     */     //   186: invokespecial <init> : (Lakka/actor/ActorRefProvider;)V
/*     */     //   189: invokevirtual getOrElse : (Lscala/Function0;)Ljava/lang/Object;
/*     */     //   192: checkcast akka/actor/Address
/*     */     //   195: invokeinterface toSerializationFormatWithAddress : (Lakka/actor/Address;)Ljava/lang/String;
/*     */     //   200: astore #8
/*     */     //   202: goto -> 258
/*     */     //   205: new scala/MatchError
/*     */     //   208: dup
/*     */     //   209: aload #7
/*     */     //   211: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   214: athrow
/*     */     //   215: astore #11
/*     */     //   217: aload #11
/*     */     //   219: astore #12
/*     */     //   221: getstatic scala/util/control/NonFatal$.MODULE$ : Lscala/util/control/NonFatal$;
/*     */     //   224: aload #12
/*     */     //   226: invokevirtual unapply : (Ljava/lang/Throwable;)Lscala/Option;
/*     */     //   229: astore #13
/*     */     //   231: aload #13
/*     */     //   233: invokevirtual isEmpty : ()Z
/*     */     //   236: ifeq -> 242
/*     */     //   239: aload #11
/*     */     //   241: athrow
/*     */     //   242: aload_2
/*     */     //   243: invokeinterface toSerializationFormat : ()Ljava/lang/String;
/*     */     //   248: astore #14
/*     */     //   250: aload #14
/*     */     //   252: astore #10
/*     */     //   254: aload #10
/*     */     //   256: astore #8
/*     */     //   258: aload #8
/*     */     //   260: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #53	-> 0
/*     */     //   #54	-> 5
/*     */     //   #55	-> 8
/*     */     //   #56	-> 41
/*     */     //   #54	-> 46
/*     */     //   #58	-> 49
/*     */     //   #59	-> 61
/*     */     //   #60	-> 69
/*     */     //   #62	-> 85
/*     */     //   #58	-> 104
/*     */     //   #65	-> 109
/*     */     //   #66	-> 123
/*     */     //   #67	-> 153
/*     */     //   #69	-> 164
/*     */     //   #70	-> 170
/*     */     //   #66	-> 200
/*     */     //   #58	-> 205
/*     */     //   #62	-> 215
/*     */     //   #63	-> 221
/*     */     //   #62	-> 239
/*     */     //   #63	-> 242
/*     */     //   #62	-> 250
/*     */     //   #59	-> 254
/*     */     //   #58	-> 258
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	261	0	this	Lakka/serialization/Serialization$;
/*     */     //   0	261	1	actorRef	Lakka/actor/ActorRef;
/*     */     //   5	255	2	path	Lakka/actor/ActorPath;
/*     */     //   49	211	3	originalSystem	Lakka/actor/ExtendedActorSystem;
/*     */     //   116	145	15	address	Lakka/actor/Address;
/*     */     //   123	138	16	system	Lakka/actor/ActorSystem;
/*     */     //   170	30	18	provider	Lakka/actor/ActorRefProvider;
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   85	104	215	finally
/*     */   }
/*     */   
/*     */   public static class Serialization$$anonfun$serializedActorPath$1 extends AbstractFunction0<Address> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ActorRefProvider provider$1;
/*     */     
/*     */     public final Address apply() {
/*  70 */       return this.provider$1.getDefaultAddress();
/*     */     }
/*     */     
/*     */     public Serialization$$anonfun$serializedActorPath$1(ActorRefProvider provider$1) {}
/*     */   }
/*     */   
/*     */   public class Serialization$$anonfun$serialize$1 extends AbstractFunction0<byte[]> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Object o$1;
/*     */     
/*     */     public final byte[] apply() {
/*  90 */       return this.$outer.findSerializerFor(this.o$1).toBinary(this.o$1);
/*     */     }
/*     */     
/*     */     public Serialization$$anonfun$serialize$1(Serialization $outer, Object o$1) {}
/*     */   }
/*     */   
/*     */   public class Serialization$$anonfun$deserialize$1 extends AbstractFunction0<T> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final byte[] bytes$2;
/*     */     
/*     */     private final int serializerId$1;
/*     */     
/*     */     private final Option clazz$2;
/*     */     
/*     */     public Serialization$$anonfun$deserialize$1(Serialization $outer, byte[] bytes$2, int serializerId$1, Option clazz$2) {}
/*     */     
/*     */     public final T apply() {
/*     */       try {
/*  99 */         Serializer serializer = (Serializer)this.$outer.serializerByIdentity().apply(BoxesRunTime.boxToInteger(this.serializerId$1));
/* 104 */         return (T)serializer.fromBinary(this.bytes$2, this.clazz$2);
/*     */       } catch (NoSuchElementException noSuchElementException) {
/*     */         (new String[2])[0] = "Cannot find serializer with id [";
/*     */         (new String[2])[1] = "]. The most probable reason is that the configuration entry ";
/*     */         throw new NotSerializableException((new StringBuilder()).append((new StringContext(scala.Predef$.MODULE$.wrapRefArray((Object[])new String[2]))).s(scala.Predef$.MODULE$.genericWrapArray(new Object[] { BoxesRunTime.boxToInteger(this.serializerId$1) }))).append("akka.actor.serializers is not in synch between the two systems.").toString());
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public class Serialization$$anonfun$deserialize$2 extends AbstractFunction0<T> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final byte[] bytes$1;
/*     */     
/*     */     private final Class clazz$1;
/*     */     
/*     */     public final T apply() {
/* 113 */       return (T)this.$outer.serializerFor(this.clazz$1).fromBinary(this.bytes$1, (Option<Class<?>>)new Some(this.clazz$1));
/*     */     }
/*     */     
/*     */     public Serialization$$anonfun$deserialize$2(Serialization $outer, byte[] bytes$1, Class clazz$1) {}
/*     */   }
/*     */   
/*     */   public class Serialization$$anonfun$unique$1$1 extends AbstractFunction1<Tuple2<Class<?>, Serializer>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Seq possibilities$1;
/*     */     
/*     */     public final boolean apply(Tuple2 x$1) {
/* 140 */       return ((Class)x$1._1()).isAssignableFrom((Class)((Tuple2)this.possibilities$1.apply(0))._1());
/*     */     }
/*     */     
/*     */     public Serialization$$anonfun$unique$1$1(Serialization $outer, Seq possibilities$1) {}
/*     */   }
/*     */   
/*     */   public class Serialization$$anonfun$unique$1$2 extends AbstractFunction1<Tuple2<Class<?>, Serializer>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Seq possibilities$1;
/*     */     
/*     */     public final boolean apply(Tuple2 x$2) {
/* 141 */       return BoxesRunTime.equals(x$2._2(), ((Tuple2)this.possibilities$1.apply(0))._2());
/*     */     }
/*     */     
/*     */     public Serialization$$anonfun$unique$1$2(Serialization $outer, Seq possibilities$1) {}
/*     */   }
/*     */   
/*     */   public class $anonfun$1 extends AbstractFunction1<Tuple2<Class<?>, Serializer>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Class clazz$3;
/*     */     
/*     */     public final boolean apply(Tuple2 x$3) {
/* 143 */       return ((Class)x$3._1()).isAssignableFrom(this.clazz$3);
/*     */     }
/*     */     
/*     */     public $anonfun$1(Serialization $outer, Class clazz$3) {}
/*     */   }
/*     */   
/*     */   public class Serialization$$anonfun$serializerOf$1 extends AbstractPartialFunction<Throwable, Try<Serializer>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final String serializerFQN$1;
/*     */     
/*     */     public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x1, Function1 default) {
/*     */       Object object;
/* 165 */       Throwable throwable = x1;
/* 166 */       if (throwable instanceof NoSuchMethodException) {
/* 166 */         object = this.$outer.system().dynamicAccess().createInstanceFor(this.serializerFQN$1, (Seq)scala.collection.immutable.Nil$.MODULE$, scala.reflect.ClassTag$.MODULE$.apply(Serializer.class));
/*     */       } else {
/*     */         object = default.apply(x1);
/*     */       } 
/*     */       return (B1)object;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Throwable x1) {
/*     */       boolean bool;
/*     */       Throwable throwable = x1;
/* 166 */       if (throwable instanceof NoSuchMethodException) {
/* 166 */         bool = true;
/*     */       } else {
/*     */         bool = false;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */     
/*     */     public Serialization$$anonfun$serializerOf$1(Serialization $outer, String serializerFQN$1) {}
/*     */   }
/*     */   
/*     */   public class $anonfun$2 extends AbstractFunction1<Tuple2<String, String>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Tuple2 check$ifrefutable$1) {
/* 174 */       Tuple2 tuple2 = check$ifrefutable$1;
/* 174 */       if (tuple2 != null) {
/* 174 */         String k = (String)tuple2._1(), v = (String)tuple2._2();
/* 174 */         if (k != null && v != null)
/* 174 */           return true; 
/*     */       } 
/* 174 */       return false;
/*     */     }
/*     */     
/*     */     public $anonfun$2(Serialization $outer) {}
/*     */   }
/*     */   
/*     */   public class $anonfun$3 extends AbstractFunction1<Tuple2<String, String>, Tuple2<String, Serializer>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Tuple2<String, Serializer> apply(Tuple2 x$4) {
/* 174 */       Tuple2 tuple2 = x$4;
/* 174 */       if (tuple2 != null) {
/* 174 */         String k = (String)tuple2._1(), v = (String)tuple2._2();
/* 174 */         if (k != null) {
/* 174 */           String str = k;
/* 174 */           if (v != null) {
/* 174 */             String str1 = v;
/* 174 */             return scala.Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(scala.Predef$.MODULE$.any2ArrowAssoc(str), this.$outer.serializerOf(str1).get());
/*     */           } 
/*     */         } 
/*     */       } 
/* 174 */       throw new MatchError(tuple2);
/*     */     }
/*     */     
/*     */     public $anonfun$3(Serialization $outer) {}
/*     */   }
/*     */   
/*     */   public class $anonfun$4 extends AbstractFunction1<Tuple2<String, String>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Tuple2 check$ifrefutable$2) {
/* 181 */       Tuple2 tuple2 = check$ifrefutable$2;
/* 181 */       if (tuple2 != null) {
/* 181 */         String k = (String)tuple2._1(), v = (String)tuple2._2();
/* 181 */         if (k != null && v != null)
/* 181 */           return true; 
/*     */       } 
/* 181 */       return false;
/*     */     }
/*     */     
/*     */     public $anonfun$4(Serialization $outer) {}
/*     */   }
/*     */   
/*     */   public class $anonfun$5 extends AbstractFunction1<Tuple2<String, String>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Tuple2 x$5) {
/* 181 */       Tuple2 tuple2 = x$5;
/* 181 */       if (tuple2 != null) {
/* 181 */         String k = (String)tuple2._1(), v = (String)tuple2._2();
/* 181 */         if (k != null && v != null) {
/* 181 */           String str1 = v, str2 = "none";
/* 181 */           if (str1 == null) {
/* 181 */             if (str2 != null);
/* 181 */           } else if (str1.equals(str2)) {
/*     */           
/*     */           } 
/*     */         } else {
/* 181 */           throw new MatchError(tuple2);
/*     */         } 
/*     */       } else {
/* 181 */         throw new MatchError(tuple2);
/*     */       } 
/*     */     }
/*     */     
/*     */     public $anonfun$5(Serialization $outer) {}
/*     */   }
/*     */   
/*     */   public class $anonfun$6 extends AbstractFunction1<Tuple2<String, String>, Tuple2<Class<Object>, Serializer>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Tuple2<Class<Object>, Serializer> apply(Tuple2 x$6) {
/* 181 */       Tuple2 tuple2 = x$6;
/* 181 */       if (tuple2 != null) {
/* 181 */         String k = (String)tuple2._1(), v = (String)tuple2._2();
/* 181 */         if (k != null) {
/* 181 */           String str = k;
/* 181 */           if (v != null) {
/* 181 */             String str1 = v;
/* 181 */             return new Tuple2(this.$outer.system().dynamicAccess().getClassFor(str, scala.reflect.ClassTag$.MODULE$.Any()).get(), this.$outer.akka$serialization$Serialization$$serializers().apply(str1));
/*     */           } 
/*     */         } 
/*     */       } 
/* 181 */       throw new MatchError(tuple2);
/*     */     }
/*     */     
/*     */     public $anonfun$6(Serialization $outer) {}
/*     */   }
/*     */   
/*     */   public class Serialization$$anonfun$sort$1 extends AbstractFunction2<ArrayBuffer<Tuple2<Class<?>, Serializer>>, Tuple2<Class<?>, Serializer>, ArrayBuffer<Tuple2<Class<?>, Serializer>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public Serialization$$anonfun$sort$1(Serialization $outer) {}
/*     */     
/*     */     public final ArrayBuffer<Tuple2<Class<?>, Serializer>> apply(ArrayBuffer<Tuple2<Class<?>, Serializer>> buf, Tuple2 ca) {
/* 189 */       int i = buf.indexWhere((Function1)new $anonfun$7(this, ca));
/* 189 */       switch (i) {
/*     */         default:
/* 191 */           (new Tuple2[1])[0] = ca;
/* 191 */           buf.insert(i, (Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[1]));
/* 193 */           return buf;
/*     */         case -1:
/*     */           break;
/*     */       } 
/*     */       (new Tuple2[1])[0] = ca;
/*     */       buf.append((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[1]));
/* 193 */       return buf;
/*     */     }
/*     */     
/*     */     public class $anonfun$7 extends AbstractFunction1<Tuple2<Class<?>, Serializer>, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Tuple2 ca$1;
/*     */       
/*     */       public final boolean apply(Tuple2 x$8) {
/*     */         return ((Class)x$8._1()).isAssignableFrom((Class)this.ca$1._1());
/*     */       }
/*     */       
/*     */       public $anonfun$7(Serialization$$anonfun$sort$1 $outer, Tuple2 ca$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class $anonfun$8 extends AbstractFunction2<ConcurrentHashMap<Class<?>, Serializer>, Tuple2<Class<?>, Serializer>, ConcurrentHashMap<Class<?>, Serializer>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final ConcurrentHashMap<Class<?>, Serializer> apply(ConcurrentHashMap x0$2, Tuple2 x1$1) {
/* 201 */       Tuple2 tuple2 = new Tuple2(x0$2, x1$1);
/* 201 */       if (tuple2 != null) {
/* 201 */         ConcurrentHashMap<Class<?>, Serializer> map = (ConcurrentHashMap)tuple2._1();
/* 201 */         Tuple2 tuple21 = (Tuple2)tuple2._2();
/* 201 */         if (tuple21 != null) {
/* 201 */           Class<?> c = (Class)tuple21._1();
/* 201 */           Serializer s = (Serializer)tuple21._2();
/* 201 */           map.put(c, s);
/* 201 */           return map;
/*     */         } 
/*     */       } 
/* 201 */       throw new MatchError(tuple2);
/*     */     }
/*     */     
/*     */     public $anonfun$8(Serialization $outer) {}
/*     */   }
/*     */   
/*     */   public class $anonfun$9 extends AbstractFunction1<Tuple2<Object, Serializer>, Tuple2<Object, Serializer>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Tuple2<Object, Serializer> apply(Tuple2 x0$3) {
/* 207 */       Tuple2 tuple2 = x0$3;
/* 207 */       if (tuple2 != null) {
/* 207 */         Serializer v = (Serializer)tuple2._2();
/* 207 */         return new Tuple2(BoxesRunTime.boxToInteger(v.identifier()), v);
/*     */       } 
/* 207 */       throw new MatchError(tuple2);
/*     */     }
/*     */     
/*     */     public $anonfun$9(Serialization $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\serialization\Serialization$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */