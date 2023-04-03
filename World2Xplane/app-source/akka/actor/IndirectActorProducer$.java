/*     */ package akka.actor;
/*     */ 
/*     */ import scala.collection.IterableLike;
/*     */ import scala.collection.immutable.Seq;
/*     */ 
/*     */ public final class IndirectActorProducer$ {
/*     */   public static final IndirectActorProducer$ MODULE$;
/*     */   
/*     */   private final Class<UntypedActorFactoryConsumer> UntypedActorFactoryConsumerClass;
/*     */   
/*     */   private final Class<CreatorFunctionConsumer> CreatorFunctionConsumerClass;
/*     */   
/*     */   private final Class<CreatorConsumer> CreatorConsumerClass;
/*     */   
/*     */   private final Class<TypedCreatorFunctionConsumer> TypedCreatorFunctionConsumerClass;
/*     */   
/*     */   private IndirectActorProducer$() {
/* 280 */     MODULE$ = this;
/* 281 */     this.UntypedActorFactoryConsumerClass = UntypedActorFactoryConsumer.class;
/* 282 */     this.CreatorFunctionConsumerClass = CreatorFunctionConsumer.class;
/* 283 */     this.CreatorConsumerClass = CreatorConsumer.class;
/* 284 */     this.TypedCreatorFunctionConsumerClass = TypedCreatorFunctionConsumer.class;
/*     */   }
/*     */   
/*     */   public Class<UntypedActorFactoryConsumer> UntypedActorFactoryConsumerClass() {
/*     */     return this.UntypedActorFactoryConsumerClass;
/*     */   }
/*     */   
/*     */   public Class<CreatorFunctionConsumer> CreatorFunctionConsumerClass() {
/*     */     return this.CreatorFunctionConsumerClass;
/*     */   }
/*     */   
/*     */   public Class<CreatorConsumer> CreatorConsumerClass() {
/*     */     return this.CreatorConsumerClass;
/*     */   }
/*     */   
/*     */   public Class<TypedCreatorFunctionConsumer> TypedCreatorFunctionConsumerClass() {
/* 284 */     return this.TypedCreatorFunctionConsumerClass;
/*     */   }
/*     */   
/*     */   public IndirectActorProducer apply(Class clazz, Seq args) {
/*     */     // Byte code:
/*     */     //   0: ldc akka/actor/IndirectActorProducer
/*     */     //   2: aload_1
/*     */     //   3: invokevirtual isAssignableFrom : (Ljava/lang/Class;)Z
/*     */     //   6: ifeq -> 237
/*     */     //   9: aload_1
/*     */     //   10: astore_3
/*     */     //   11: aload_0
/*     */     //   12: invokevirtual TypedCreatorFunctionConsumerClass : ()Ljava/lang/Class;
/*     */     //   15: aload_3
/*     */     //   16: astore #4
/*     */     //   18: dup
/*     */     //   19: ifnonnull -> 31
/*     */     //   22: pop
/*     */     //   23: aload #4
/*     */     //   25: ifnull -> 39
/*     */     //   28: goto -> 67
/*     */     //   31: aload #4
/*     */     //   33: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   36: ifeq -> 67
/*     */     //   39: new akka/actor/TypedCreatorFunctionConsumer
/*     */     //   42: dup
/*     */     //   43: aload_0
/*     */     //   44: aload_2
/*     */     //   45: invokespecial get1stArg$1 : (Lscala/collection/immutable/Seq;)Ljava/lang/Object;
/*     */     //   48: checkcast java/lang/Class
/*     */     //   51: aload_0
/*     */     //   52: aload_2
/*     */     //   53: invokespecial get2ndArg$1 : (Lscala/collection/immutable/Seq;)Ljava/lang/Object;
/*     */     //   56: checkcast scala/Function0
/*     */     //   59: invokespecial <init> : (Ljava/lang/Class;Lscala/Function0;)V
/*     */     //   62: astore #5
/*     */     //   64: goto -> 232
/*     */     //   67: aload_0
/*     */     //   68: invokevirtual UntypedActorFactoryConsumerClass : ()Ljava/lang/Class;
/*     */     //   71: aload_3
/*     */     //   72: astore #6
/*     */     //   74: dup
/*     */     //   75: ifnonnull -> 87
/*     */     //   78: pop
/*     */     //   79: aload #6
/*     */     //   81: ifnull -> 95
/*     */     //   84: goto -> 115
/*     */     //   87: aload #6
/*     */     //   89: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   92: ifeq -> 115
/*     */     //   95: new akka/actor/UntypedActorFactoryConsumer
/*     */     //   98: dup
/*     */     //   99: aload_0
/*     */     //   100: aload_2
/*     */     //   101: invokespecial get1stArg$1 : (Lscala/collection/immutable/Seq;)Ljava/lang/Object;
/*     */     //   104: checkcast akka/actor/UntypedActorFactory
/*     */     //   107: invokespecial <init> : (Lakka/actor/UntypedActorFactory;)V
/*     */     //   110: astore #5
/*     */     //   112: goto -> 232
/*     */     //   115: aload_0
/*     */     //   116: invokevirtual CreatorFunctionConsumerClass : ()Ljava/lang/Class;
/*     */     //   119: aload_3
/*     */     //   120: astore #7
/*     */     //   122: dup
/*     */     //   123: ifnonnull -> 135
/*     */     //   126: pop
/*     */     //   127: aload #7
/*     */     //   129: ifnull -> 143
/*     */     //   132: goto -> 163
/*     */     //   135: aload #7
/*     */     //   137: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   140: ifeq -> 163
/*     */     //   143: new akka/actor/CreatorFunctionConsumer
/*     */     //   146: dup
/*     */     //   147: aload_0
/*     */     //   148: aload_2
/*     */     //   149: invokespecial get1stArg$1 : (Lscala/collection/immutable/Seq;)Ljava/lang/Object;
/*     */     //   152: checkcast scala/Function0
/*     */     //   155: invokespecial <init> : (Lscala/Function0;)V
/*     */     //   158: astore #5
/*     */     //   160: goto -> 232
/*     */     //   163: aload_0
/*     */     //   164: invokevirtual CreatorConsumerClass : ()Ljava/lang/Class;
/*     */     //   167: aload_3
/*     */     //   168: astore #8
/*     */     //   170: dup
/*     */     //   171: ifnonnull -> 183
/*     */     //   174: pop
/*     */     //   175: aload #8
/*     */     //   177: ifnull -> 191
/*     */     //   180: goto -> 219
/*     */     //   183: aload #8
/*     */     //   185: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   188: ifeq -> 219
/*     */     //   191: new akka/actor/CreatorConsumer
/*     */     //   194: dup
/*     */     //   195: aload_0
/*     */     //   196: aload_2
/*     */     //   197: invokespecial get1stArg$1 : (Lscala/collection/immutable/Seq;)Ljava/lang/Object;
/*     */     //   200: checkcast java/lang/Class
/*     */     //   203: aload_0
/*     */     //   204: aload_2
/*     */     //   205: invokespecial get2ndArg$1 : (Lscala/collection/immutable/Seq;)Ljava/lang/Object;
/*     */     //   208: checkcast akka/japi/Creator
/*     */     //   211: invokespecial <init> : (Ljava/lang/Class;Lakka/japi/Creator;)V
/*     */     //   214: astore #5
/*     */     //   216: goto -> 232
/*     */     //   219: getstatic akka/util/Reflect$.MODULE$ : Lakka/util/Reflect$;
/*     */     //   222: aload_1
/*     */     //   223: aload_2
/*     */     //   224: invokevirtual instantiate : (Ljava/lang/Class;Lscala/collection/immutable/Seq;)Ljava/lang/Object;
/*     */     //   227: checkcast akka/actor/IndirectActorProducer
/*     */     //   230: astore #5
/*     */     //   232: aload #5
/*     */     //   234: goto -> 275
/*     */     //   237: ldc akka/actor/Actor
/*     */     //   239: aload_1
/*     */     //   240: invokevirtual isAssignableFrom : (Ljava/lang/Class;)Z
/*     */     //   243: ifeq -> 276
/*     */     //   246: aload_2
/*     */     //   247: invokeinterface isEmpty : ()Z
/*     */     //   252: ifeq -> 266
/*     */     //   255: new akka/actor/NoArgsReflectConstructor
/*     */     //   258: dup
/*     */     //   259: aload_1
/*     */     //   260: invokespecial <init> : (Ljava/lang/Class;)V
/*     */     //   263: goto -> 275
/*     */     //   266: new akka/actor/ArgsReflectConstructor
/*     */     //   269: dup
/*     */     //   270: aload_1
/*     */     //   271: aload_2
/*     */     //   272: invokespecial <init> : (Ljava/lang/Class;Lscala/collection/immutable/Seq;)V
/*     */     //   275: areturn
/*     */     //   276: new java/lang/IllegalArgumentException
/*     */     //   279: dup
/*     */     //   280: new scala/StringContext
/*     */     //   283: dup
/*     */     //   284: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   287: iconst_2
/*     */     //   288: anewarray java/lang/String
/*     */     //   291: dup
/*     */     //   292: iconst_0
/*     */     //   293: ldc 'unknown actor creator ['
/*     */     //   295: aastore
/*     */     //   296: dup
/*     */     //   297: iconst_1
/*     */     //   298: ldc ']'
/*     */     //   300: aastore
/*     */     //   301: checkcast [Ljava/lang/Object;
/*     */     //   304: invokevirtual wrapRefArray : ([Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   307: invokespecial <init> : (Lscala/collection/Seq;)V
/*     */     //   310: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   313: iconst_1
/*     */     //   314: anewarray java/lang/Object
/*     */     //   317: dup
/*     */     //   318: iconst_0
/*     */     //   319: aload_1
/*     */     //   320: aastore
/*     */     //   321: invokevirtual genericWrapArray : (Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   324: invokevirtual s : (Lscala/collection/Seq;)Ljava/lang/String;
/*     */     //   327: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   330: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #287	-> 0
/*     */     //   #292	-> 9
/*     */     //   #293	-> 11
/*     */     //   #294	-> 39
/*     */     //   #295	-> 67
/*     */     //   #296	-> 95
/*     */     //   #297	-> 115
/*     */     //   #298	-> 143
/*     */     //   #299	-> 163
/*     */     //   #300	-> 191
/*     */     //   #302	-> 219
/*     */     //   #292	-> 232
/*     */     //   #304	-> 237
/*     */     //   #305	-> 246
/*     */     //   #306	-> 266
/*     */     //   #287	-> 275
/*     */     //   #307	-> 276
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	331	0	this	Lakka/actor/IndirectActorProducer$;
/*     */     //   0	331	1	clazz	Ljava/lang/Class;
/*     */     //   0	331	2	args	Lscala/collection/immutable/Seq;
/*     */   }
/*     */   
/*     */   private final Object get1stArg$1(Seq args$1) {
/* 288 */     return args$1.head();
/*     */   }
/*     */   
/*     */   private final Object get2ndArg$1(Seq args$1) {
/* 289 */     return ((IterableLike)args$1.tail()).head();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\IndirectActorProducer$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */