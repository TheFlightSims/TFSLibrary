/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.None$;
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.GenIterable;
/*    */ import scala.collection.GenMap;
/*    */ import scala.collection.GenSeq;
/*    */ import scala.collection.GenSet;
/*    */ import scala.collection.GenTraversable;
/*    */ import scala.collection.GenTraversableOnce;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Map;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.Traversable;
/*    */ import scala.collection.TraversableView;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.Growable;
/*    */ import scala.collection.generic.Shrinkable;
/*    */ import scala.collection.generic.Subtractable;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.collection.immutable.Nil$;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0055d\001B\001\003\001%\021q\001T5ti6\013\007O\003\002\004\t\0059Q.\036;bE2,'BA\003\007\003)\031w\016\0347fGRLwN\034\006\002\017\005)1oY1mC\016\001Qc\001\006\0229M)\001a\003\020\"KA!A\"D\b\034\033\005\021\021B\001\b\003\005-\t%m\035;sC\016$X*\0319\021\005A\tB\002\001\003\006%\001\021\ra\005\002\002\003F\021A\003\007\t\003+Yi\021AB\005\003/\031\021qAT8uQ&tw\r\005\002\0263%\021!D\002\002\004\003:L\bC\001\t\035\t\025i\002A1\001\024\005\005\021\005\003\002\007 \037mI!\001\t\002\003\0075\013\007\017E\003\rE=YB%\003\002$\005\t9Q*\0319MS.,\007\003\002\007\001\037m\001\"!\006\024\n\005\0352!\001D*fe&\fG.\033>bE2,\007\"B\025\001\t\003Q\023A\002\037j]&$h\bF\001%\021\025a\003\001\"\021.\003\025)W\016\035;z+\005!\003bB\030\001\001\004%I\001M\001\006K2,Wn]\013\002cA\031!GO\037\017\005MBdB\001\0338\033\005)$B\001\034\t\003\031a$o\\8u}%\tq!\003\002:\r\0059\001/Y2lC\036,\027BA\036=\005\021a\025n\035;\013\005e2\001\003B\013?\037mI!a\020\004\003\rQ+\b\017\\33\021\035\t\005\0011A\005\n\t\013\021\"\0327f[N|F%Z9\025\005\r3\005CA\013E\023\t)eA\001\003V]&$\bbB$A\003\003\005\r!M\001\004q\022\n\004BB%\001A\003&\021'\001\004fY\026l7\017\t\005\b\027\002\001\r\021\"\003M\003\r\031\030N_\013\002\033B\021QCT\005\003\037\032\0211!\0238u\021\035\t\006\0011A\005\nI\013qa]5{?\022*\027\017\006\002D'\"9q\tUA\001\002\004i\005BB+\001A\003&Q*\001\003tSj\004\003\"B,\001\t\003A\026aA4fiR\021\021\f\030\t\004+i[\022BA.\007\005\031y\005\017^5p]\")QL\026a\001\037\005\0311.Z=\t\013}\003A\021\0011\002\021%$XM]1u_J,\022!\031\t\004E\016lT\"\001\003\n\005\021$!\001C%uKJ\fGo\034:\t\013\031\004A\021A4\002\021\021\002H.^:%KF$\"\001[5\016\003\001AQA[3A\002u\n!a\033<\t\0131\004A\021A7\002\023\021j\027N\\;tI\025\fHC\0015o\021\025i6\0161\001\020\021\025\001\b\001\"\003r\003\031\021X-\\8wKR!\021G]:u\021\025iv\0161\001\020\021\025ys\0161\0012\021\025)x\0161\0012\003\r\t7m\031\025\003_^\004\"\001_>\016\003eT!A\037\004\002\025\005tgn\034;bi&|g.\003\002}s\n9A/Y5me\026\034\007\"\002@\001\t\003z\030!B2mK\006\024H#A\"\t\r\005\r\001\001\"\021M\003\021\031\030N_3\b\017\005\035!\001#\001\002\n\0059A*[:u\033\006\004\bc\001\007\002\f\0311\021A\001E\001\003\033\031R!a\003\002\020\025\002b!!\005\002\030\005mQBAA\n\025\r\t)\002B\001\bO\026tWM]5d\023\021\tI\"a\005\003#5+H/\0312mK6\013\007OR1di>\024\030\020\005\002\r\001!9\021&a\003\005\002\005}ACAA\005\021!\t\031#a\003\005\004\005\025\022\001D2b]\n+\030\016\0343Ge>lWCBA\024\003\t\031%\006\002\002*AQ\021\021CA\026\003_\tY$!\022\n\t\0055\0221\003\002\r\007\006t')^5mI\032\023x.\034\t\005\003c\t\031$\004\002\002\f%!\021QGA\034\005\021\031u\016\0347\n\t\005e\0221\003\002\016\017\026tW*\0319GC\016$xN]=\021\rUq\024QHA!!\r\001\022q\b\003\007%\005\005\"\031A\n\021\007A\t\031\005\002\004\036\003C\021\ra\005\t\007\031\001\ti$!\021\t\0171\nY\001\"\001\002JU1\0211JA)\003+*\"!!\024\021\r1\001\021qJA*!\r\001\022\021\013\003\007%\005\035#\031A\n\021\007A\t)\006\002\004\036\003\017\022\ra\005\005\013\0033\nY!!A\005\n\005m\023a\003:fC\022\024Vm]8mm\026$\"!!\030\021\t\005}\023\021N\007\003\003CRA!a\031\002f\005!A.\0318h\025\t\t9'\001\003kCZ\f\027\002BA6\003C\022aa\0242kK\016$\b")
/*    */ public class ListMap<A, B> extends AbstractMap<A, B> implements Map<A, B>, MapLike<A, B, ListMap<A, B>>, Serializable {
/*    */   private List<Tuple2<A, B>> elems;
/*    */   
/*    */   private int siz;
/*    */   
/*    */   public ListMap() {
/* 46 */     this.elems = (List<Tuple2<A, B>>)Nil$.MODULE$;
/* 47 */     this.siz = 0;
/*    */   }
/*    */   
/*    */   public ListMap<A, B> empty() {
/*    */     return ListMap$.MODULE$.empty();
/*    */   }
/*    */   
/*    */   private List<Tuple2<A, B>> elems() {
/*    */     return this.elems;
/*    */   }
/*    */   
/*    */   private void elems_$eq(List<Tuple2<A, B>> x$1) {
/*    */     this.elems = x$1;
/*    */   }
/*    */   
/*    */   private int siz() {
/* 47 */     return this.siz;
/*    */   }
/*    */   
/*    */   private void siz_$eq(int x$1) {
/* 47 */     this.siz = x$1;
/*    */   }
/*    */   
/*    */   public Option<B> get(Object key) {
/*    */     Option option;
/* 49 */     return (option = elems().find((Function1)new ListMap$$anonfun$get$1(this, (ListMap<A, B>)key))).isEmpty() ? (Option<B>)None$.MODULE$ : (Option<B>)new Some(((Tuple2)option.get())._2());
/*    */   }
/*    */   
/*    */   public class ListMap$$anonfun$get$1 extends AbstractFunction1<Tuple2<A, B>, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Object key$1;
/*    */     
/*    */     public final boolean apply(Tuple2 x$1) {
/* 49 */       Object object2 = this.key$1;
/*    */       Object object1;
/* 49 */       return (((object1 = x$1._1()) == object2) ? true : ((object1 == null) ? false : ((object1 instanceof Number) ? BoxesRunTime.equalsNumObject((Number)object1, object2) : ((object1 instanceof Character) ? BoxesRunTime.equalsCharObject((Character)object1, object2) : object1.equals(object2)))));
/*    */     }
/*    */     
/*    */     public ListMap$$anonfun$get$1(ListMap $outer, Object key$1) {}
/*    */   }
/*    */   
/*    */   public Iterator<Tuple2<A, B>> iterator() {
/* 50 */     return elems().iterator();
/*    */   }
/*    */   
/*    */   public ListMap<A, B> $plus$eq(Tuple2 kv) {
/* 52 */     elems_$eq(remove((A)kv._1(), elems(), (List<Tuple2<A, B>>)Nil$.MODULE$));
/* 52 */     elems_$eq(elems().$colon$colon(kv));
/* 52 */     siz_$eq(siz() + 1);
/* 52 */     return this;
/*    */   }
/*    */   
/*    */   public ListMap<A, B> $minus$eq(Object key) {
/* 53 */     elems_$eq(remove((A)key, elems(), (List<Tuple2<A, B>>)Nil$.MODULE$));
/* 53 */     return this;
/*    */   }
/*    */   
/*    */   private List<Tuple2<A, B>> remove(Object key, List elems, List acc) {
/*    */     // Byte code:
/*    */     //   0: aload_2
/*    */     //   1: invokevirtual isEmpty : ()Z
/*    */     //   4: ifeq -> 11
/*    */     //   7: aload_3
/*    */     //   8: goto -> 111
/*    */     //   11: aload_2
/*    */     //   12: invokevirtual head : ()Ljava/lang/Object;
/*    */     //   15: checkcast scala/Tuple2
/*    */     //   18: invokevirtual _1 : ()Ljava/lang/Object;
/*    */     //   21: dup
/*    */     //   22: astore #4
/*    */     //   24: aload_1
/*    */     //   25: if_acmpne -> 32
/*    */     //   28: iconst_1
/*    */     //   29: goto -> 87
/*    */     //   32: aload #4
/*    */     //   34: ifnonnull -> 41
/*    */     //   37: iconst_0
/*    */     //   38: goto -> 87
/*    */     //   41: aload #4
/*    */     //   43: instanceof java/lang/Number
/*    */     //   46: ifeq -> 61
/*    */     //   49: aload #4
/*    */     //   51: checkcast java/lang/Number
/*    */     //   54: aload_1
/*    */     //   55: invokestatic equalsNumObject : (Ljava/lang/Number;Ljava/lang/Object;)Z
/*    */     //   58: goto -> 87
/*    */     //   61: aload #4
/*    */     //   63: instanceof java/lang/Character
/*    */     //   66: ifeq -> 81
/*    */     //   69: aload #4
/*    */     //   71: checkcast java/lang/Character
/*    */     //   74: aload_1
/*    */     //   75: invokestatic equalsCharObject : (Ljava/lang/Character;Ljava/lang/Object;)Z
/*    */     //   78: goto -> 87
/*    */     //   81: aload #4
/*    */     //   83: aload_1
/*    */     //   84: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   87: ifeq -> 112
/*    */     //   90: aload_0
/*    */     //   91: aload_0
/*    */     //   92: invokespecial siz : ()I
/*    */     //   95: iconst_1
/*    */     //   96: isub
/*    */     //   97: invokespecial siz_$eq : (I)V
/*    */     //   100: aload_2
/*    */     //   101: invokevirtual tail : ()Ljava/lang/Object;
/*    */     //   104: checkcast scala/collection/immutable/List
/*    */     //   107: aload_3
/*    */     //   108: invokevirtual $colon$colon$colon : (Lscala/collection/immutable/List;)Lscala/collection/immutable/List;
/*    */     //   111: areturn
/*    */     //   112: aload_1
/*    */     //   113: aload_2
/*    */     //   114: invokevirtual tail : ()Ljava/lang/Object;
/*    */     //   117: checkcast scala/collection/immutable/List
/*    */     //   120: aload_2
/*    */     //   121: invokevirtual head : ()Ljava/lang/Object;
/*    */     //   124: checkcast scala/Tuple2
/*    */     //   127: astore #5
/*    */     //   129: aload_3
/*    */     //   130: aload #5
/*    */     //   132: invokevirtual $colon$colon : (Ljava/lang/Object;)Lscala/collection/immutable/List;
/*    */     //   135: astore_3
/*    */     //   136: astore_2
/*    */     //   137: astore_1
/*    */     //   138: goto -> 0
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #57	-> 0
/*    */     //   #58	-> 11
/*    */     //   #56	-> 111
/*    */     //   #59	-> 112
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	141	0	this	Lscala/collection/mutable/ListMap;
/*    */     //   0	141	1	key	Ljava/lang/Object;
/*    */     //   0	141	2	elems	Lscala/collection/immutable/List;
/*    */     //   0	141	3	acc	Lscala/collection/immutable/List;
/*    */   }
/*    */   
/*    */   public void clear() {
/* 63 */     elems_$eq((List<Tuple2<A, B>>)Nil$.MODULE$);
/* 63 */     siz_$eq(0);
/*    */   }
/*    */   
/*    */   public int size() {
/* 64 */     return siz();
/*    */   }
/*    */   
/*    */   public static <A, B> CanBuildFrom<ListMap<?, ?>, Tuple2<A, B>, ListMap<A, B>> canBuildFrom() {
/*    */     return ListMap$.MODULE$.canBuildFrom();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\ListMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */