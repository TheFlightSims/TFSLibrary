/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.TraversableLike;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.generic.Growable;
/*    */ import scala.reflect.ClassTag;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001a4A!\001\002\001\023\t\031rK]1qa\026$\027I\035:bs\n+\030\016\0343fe*\0211\001B\001\b[V$\030M\0317f\025\t)a!\001\006d_2dWm\031;j_:T\021aB\001\006g\016\fG.Y\002\001+\tQQcE\002\001\027=\001\"\001D\007\016\003\031I!A\004\004\003\r\005s\027PU3g!\021\001\022c\005\020\016\003\tI!A\005\002\003\017\t+\030\016\0343feB\021A#\006\007\001\t\0251\002A1\001\030\005\005\t\025C\001\r\034!\ta\021$\003\002\033\r\t9aj\034;iS:<\007C\001\007\035\023\tibAA\002B]f\0042\001E\020\024\023\t\001#A\001\007Xe\006\004\b/\0323BeJ\f\027\020\003\005#\001\t\005\t\025!\003$\003\r!\030m\032\t\004I\035\032R\"A\023\013\005\0312\021a\002:fM2,7\r^\005\003Q\025\022\001b\0217bgN$\026m\032\005\006U\001!\taK\001\007y%t\027\016\036 \025\0051j\003c\001\t\001'!)!%\013a\001G!9q\006\001b\001\n\003\001\024\001C7b]&4Wm\035;\026\003\rBCA\f\0326oA\021AbM\005\003i\031\021!\002Z3qe\026\034\027\r^3eC\0051\024aD;tK\002\"\030m\032\021j]N$X-\0313\"\003a\naA\r\0302a9\002\004B\002\036\001A\003%1%A\005nC:Lg-Z:uA!IA\b\001a\001\002\004%I!P\001\006K2,Wn]\013\002=!Iq\b\001a\001\002\004%I\001Q\001\nK2,Wn]0%KF$\"!\021#\021\0051\021\025BA\"\007\005\021)f.\033;\t\017\025s\024\021!a\001=\005\031\001\020J\031\t\r\035\003\001\025)\003\037\003\031)G.Z7tA!9\021\n\001a\001\n\023Q\025\001C2ba\006\034\027\016^=\026\003-\003\"\001\004'\n\00553!aA%oi\"9q\n\001a\001\n\023\001\026\001D2ba\006\034\027\016^=`I\025\fHCA!R\021\035)e*!AA\002-Caa\025\001!B\023Y\025!C2ba\006\034\027\016^=!\021\035)\006\0011A\005\n)\013Aa]5{K\"9q\013\001a\001\n\023A\026\001C:ju\026|F%Z9\025\005\005K\006bB#W\003\003\005\ra\023\005\0077\002\001\013\025B&\002\013ML'0\032\021\t\013u\003A\021\0020\002\0175\\\027I\035:bsR\021ad\030\005\006+r\003\ra\023\005\006C\002!IAY\001\007e\026\034\030N_3\025\005\005\033\007\"B+a\001\004Y\005\"B3\001\t\0032\027\001C:ju\026D\025N\034;\025\005\005;\007\"B+e\001\004Y\005\"B5\001\t\023Q\027AC3ogV\024XmU5{KR\021\021i\033\005\006+\"\004\ra\023\005\006[\002!\tA\\\001\tIAdWo\035\023fcR\021q\016]\007\002\001!)\021\017\034a\001'\005!Q\r\\3n\021\025\031\b\001\"\001u\003\025\031G.Z1s)\005\t\005\"\002<\001\t\0039\030A\002:fgVdG\017F\001\037\001")
/*    */ public class WrappedArrayBuilder<A> implements Builder<A, WrappedArray<A>> {
/*    */   private final ClassTag<A> tag;
/*    */   
/*    */   private final ClassTag<A> manifest;
/*    */   
/*    */   private WrappedArray<A> elems;
/*    */   
/*    */   private int capacity;
/*    */   
/*    */   private int size;
/*    */   
/*    */   public void sizeHint(TraversableLike coll) {
/* 25 */     Builder$class.sizeHint(this, coll);
/*    */   }
/*    */   
/*    */   public void sizeHint(TraversableLike coll, int delta) {
/* 25 */     Builder$class.sizeHint(this, coll, delta);
/*    */   }
/*    */   
/*    */   public void sizeHintBounded(int size, TraversableLike boundingColl) {
/* 25 */     Builder$class.sizeHintBounded(this, size, boundingColl);
/*    */   }
/*    */   
/*    */   public <NewTo> Builder<A, NewTo> mapResult(Function1 f) {
/* 25 */     return Builder$class.mapResult(this, f);
/*    */   }
/*    */   
/*    */   public Growable<A> $plus$eq(Object elem1, Object elem2, Seq elems) {
/* 25 */     return Growable.class.$plus$eq(this, elem1, elem2, elems);
/*    */   }
/*    */   
/*    */   public Growable<A> $plus$plus$eq(TraversableOnce xs) {
/* 25 */     return Growable.class.$plus$plus$eq(this, xs);
/*    */   }
/*    */   
/*    */   public WrappedArrayBuilder(ClassTag<A> tag) {
/* 25 */     Growable.class.$init$(this);
/* 25 */     Builder$class.$init$(this);
/* 28 */     this.manifest = tag;
/* 31 */     this.capacity = 0;
/* 32 */     this.size = 0;
/*    */   }
/*    */   
/*    */   public ClassTag<A> manifest() {
/*    */     return this.manifest;
/*    */   }
/*    */   
/*    */   private WrappedArray<A> elems() {
/*    */     return this.elems;
/*    */   }
/*    */   
/*    */   private void elems_$eq(WrappedArray<A> x$1) {
/*    */     this.elems = x$1;
/*    */   }
/*    */   
/*    */   private int capacity() {
/*    */     return this.capacity;
/*    */   }
/*    */   
/*    */   private void capacity_$eq(int x$1) {
/*    */     this.capacity = x$1;
/*    */   }
/*    */   
/*    */   private int size() {
/* 32 */     return this.size;
/*    */   }
/*    */   
/*    */   private void size_$eq(int x$1) {
/* 32 */     this.size = x$1;
/*    */   }
/*    */   
/*    */   private WrappedArray<A> mkArray(int size) {
/*    */     // Byte code:
/*    */     //   0: getstatic scala/runtime/ScalaRunTime$.MODULE$ : Lscala/runtime/ScalaRunTime$;
/*    */     //   3: aload_0
/*    */     //   4: getfield tag : Lscala/reflect/ClassTag;
/*    */     //   7: invokevirtual arrayElementClass : (Ljava/lang/Object;)Ljava/lang/Class;
/*    */     //   10: astore_2
/*    */     //   11: getstatic java/lang/Byte.TYPE : Ljava/lang/Class;
/*    */     //   14: dup
/*    */     //   15: ifnonnull -> 26
/*    */     //   18: pop
/*    */     //   19: aload_2
/*    */     //   20: ifnull -> 33
/*    */     //   23: goto -> 47
/*    */     //   26: aload_2
/*    */     //   27: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   30: ifeq -> 47
/*    */     //   33: new scala/collection/mutable/WrappedArray$ofByte
/*    */     //   36: dup
/*    */     //   37: iload_1
/*    */     //   38: newarray byte
/*    */     //   40: invokespecial <init> : ([B)V
/*    */     //   43: astore_3
/*    */     //   44: goto -> 357
/*    */     //   47: getstatic java/lang/Short.TYPE : Ljava/lang/Class;
/*    */     //   50: dup
/*    */     //   51: ifnonnull -> 62
/*    */     //   54: pop
/*    */     //   55: aload_2
/*    */     //   56: ifnull -> 69
/*    */     //   59: goto -> 83
/*    */     //   62: aload_2
/*    */     //   63: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   66: ifeq -> 83
/*    */     //   69: new scala/collection/mutable/WrappedArray$ofShort
/*    */     //   72: dup
/*    */     //   73: iload_1
/*    */     //   74: newarray short
/*    */     //   76: invokespecial <init> : ([S)V
/*    */     //   79: astore_3
/*    */     //   80: goto -> 357
/*    */     //   83: getstatic java/lang/Character.TYPE : Ljava/lang/Class;
/*    */     //   86: dup
/*    */     //   87: ifnonnull -> 98
/*    */     //   90: pop
/*    */     //   91: aload_2
/*    */     //   92: ifnull -> 105
/*    */     //   95: goto -> 119
/*    */     //   98: aload_2
/*    */     //   99: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   102: ifeq -> 119
/*    */     //   105: new scala/collection/mutable/WrappedArray$ofChar
/*    */     //   108: dup
/*    */     //   109: iload_1
/*    */     //   110: newarray char
/*    */     //   112: invokespecial <init> : ([C)V
/*    */     //   115: astore_3
/*    */     //   116: goto -> 357
/*    */     //   119: getstatic java/lang/Integer.TYPE : Ljava/lang/Class;
/*    */     //   122: dup
/*    */     //   123: ifnonnull -> 134
/*    */     //   126: pop
/*    */     //   127: aload_2
/*    */     //   128: ifnull -> 141
/*    */     //   131: goto -> 155
/*    */     //   134: aload_2
/*    */     //   135: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   138: ifeq -> 155
/*    */     //   141: new scala/collection/mutable/WrappedArray$ofInt
/*    */     //   144: dup
/*    */     //   145: iload_1
/*    */     //   146: newarray int
/*    */     //   148: invokespecial <init> : ([I)V
/*    */     //   151: astore_3
/*    */     //   152: goto -> 357
/*    */     //   155: getstatic java/lang/Long.TYPE : Ljava/lang/Class;
/*    */     //   158: dup
/*    */     //   159: ifnonnull -> 170
/*    */     //   162: pop
/*    */     //   163: aload_2
/*    */     //   164: ifnull -> 177
/*    */     //   167: goto -> 191
/*    */     //   170: aload_2
/*    */     //   171: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   174: ifeq -> 191
/*    */     //   177: new scala/collection/mutable/WrappedArray$ofLong
/*    */     //   180: dup
/*    */     //   181: iload_1
/*    */     //   182: newarray long
/*    */     //   184: invokespecial <init> : ([J)V
/*    */     //   187: astore_3
/*    */     //   188: goto -> 357
/*    */     //   191: getstatic java/lang/Float.TYPE : Ljava/lang/Class;
/*    */     //   194: dup
/*    */     //   195: ifnonnull -> 206
/*    */     //   198: pop
/*    */     //   199: aload_2
/*    */     //   200: ifnull -> 213
/*    */     //   203: goto -> 227
/*    */     //   206: aload_2
/*    */     //   207: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   210: ifeq -> 227
/*    */     //   213: new scala/collection/mutable/WrappedArray$ofFloat
/*    */     //   216: dup
/*    */     //   217: iload_1
/*    */     //   218: newarray float
/*    */     //   220: invokespecial <init> : ([F)V
/*    */     //   223: astore_3
/*    */     //   224: goto -> 357
/*    */     //   227: getstatic java/lang/Double.TYPE : Ljava/lang/Class;
/*    */     //   230: dup
/*    */     //   231: ifnonnull -> 242
/*    */     //   234: pop
/*    */     //   235: aload_2
/*    */     //   236: ifnull -> 249
/*    */     //   239: goto -> 263
/*    */     //   242: aload_2
/*    */     //   243: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   246: ifeq -> 263
/*    */     //   249: new scala/collection/mutable/WrappedArray$ofDouble
/*    */     //   252: dup
/*    */     //   253: iload_1
/*    */     //   254: newarray double
/*    */     //   256: invokespecial <init> : ([D)V
/*    */     //   259: astore_3
/*    */     //   260: goto -> 357
/*    */     //   263: getstatic java/lang/Boolean.TYPE : Ljava/lang/Class;
/*    */     //   266: dup
/*    */     //   267: ifnonnull -> 278
/*    */     //   270: pop
/*    */     //   271: aload_2
/*    */     //   272: ifnull -> 285
/*    */     //   275: goto -> 299
/*    */     //   278: aload_2
/*    */     //   279: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   282: ifeq -> 299
/*    */     //   285: new scala/collection/mutable/WrappedArray$ofBoolean
/*    */     //   288: dup
/*    */     //   289: iload_1
/*    */     //   290: newarray boolean
/*    */     //   292: invokespecial <init> : ([Z)V
/*    */     //   295: astore_3
/*    */     //   296: goto -> 357
/*    */     //   299: getstatic java/lang/Void.TYPE : Ljava/lang/Class;
/*    */     //   302: dup
/*    */     //   303: ifnonnull -> 314
/*    */     //   306: pop
/*    */     //   307: aload_2
/*    */     //   308: ifnull -> 321
/*    */     //   311: goto -> 336
/*    */     //   314: aload_2
/*    */     //   315: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   318: ifeq -> 336
/*    */     //   321: new scala/collection/mutable/WrappedArray$ofUnit
/*    */     //   324: dup
/*    */     //   325: iload_1
/*    */     //   326: anewarray scala/runtime/BoxedUnit
/*    */     //   329: invokespecial <init> : ([Lscala/runtime/BoxedUnit;)V
/*    */     //   332: astore_3
/*    */     //   333: goto -> 357
/*    */     //   336: new scala/collection/mutable/WrappedArray$ofRef
/*    */     //   339: dup
/*    */     //   340: aload_0
/*    */     //   341: getfield tag : Lscala/reflect/ClassTag;
/*    */     //   344: iload_1
/*    */     //   345: invokeinterface newArray : (I)Ljava/lang/Object;
/*    */     //   350: checkcast [Ljava/lang/Object;
/*    */     //   353: invokespecial <init> : ([Ljava/lang/Object;)V
/*    */     //   356: astore_3
/*    */     //   357: aload_0
/*    */     //   358: invokespecial size : ()I
/*    */     //   361: iconst_0
/*    */     //   362: if_icmple -> 388
/*    */     //   365: getstatic scala/Array$.MODULE$ : Lscala/Array$;
/*    */     //   368: aload_0
/*    */     //   369: invokespecial elems : ()Lscala/collection/mutable/WrappedArray;
/*    */     //   372: invokevirtual array : ()Ljava/lang/Object;
/*    */     //   375: iconst_0
/*    */     //   376: aload_3
/*    */     //   377: invokevirtual array : ()Ljava/lang/Object;
/*    */     //   380: iconst_0
/*    */     //   381: aload_0
/*    */     //   382: invokespecial size : ()I
/*    */     //   385: invokevirtual copy : (Ljava/lang/Object;ILjava/lang/Object;II)V
/*    */     //   388: aload_3
/*    */     //   389: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #35	-> 0
/*    */     //   #37	-> 11
/*    */     //   #36	-> 11
/*    */     //   #38	-> 47
/*    */     //   #39	-> 83
/*    */     //   #40	-> 119
/*    */     //   #41	-> 155
/*    */     //   #42	-> 191
/*    */     //   #43	-> 227
/*    */     //   #44	-> 263
/*    */     //   #45	-> 299
/*    */     //   #46	-> 336
/*    */     //   #48	-> 357
/*    */     //   #36	-> 357
/*    */     //   #49	-> 388
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	390	0	this	Lscala/collection/mutable/WrappedArrayBuilder;
/*    */     //   0	390	1	size	I
/*    */     //   11	378	2	runtimeClass	Ljava/lang/Class;
/*    */   }
/*    */   
/*    */   private void resize(int size) {
/* 53 */     elems_$eq(mkArray(size));
/* 54 */     capacity_$eq(size);
/*    */   }
/*    */   
/*    */   public void sizeHint(int size) {
/* 58 */     if (capacity() < size)
/* 58 */       resize(size); 
/*    */   }
/*    */   
/*    */   private void ensureSize(int size) {
/* 62 */     if (capacity() < size) {
/* 63 */       int newsize = (capacity() == 0) ? 16 : (capacity() * 2);
/* 64 */       for (; newsize < size; newsize *= 2);
/* 65 */       resize(newsize);
/*    */     } 
/*    */   }
/*    */   
/*    */   public WrappedArrayBuilder<A> $plus$eq(Object elem) {
/* 70 */     ensureSize(size() + 1);
/* 71 */     elems().update(size(), (A)elem);
/* 72 */     size_$eq(size() + 1);
/* 73 */     return this;
/*    */   }
/*    */   
/*    */   public void clear() {
/* 77 */     size_$eq(0);
/*    */   }
/*    */   
/*    */   public WrappedArray<A> result() {
/* 81 */     return (capacity() != 0 && capacity() == size()) ? elems() : 
/* 82 */       mkArray(size());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\WrappedArrayBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */