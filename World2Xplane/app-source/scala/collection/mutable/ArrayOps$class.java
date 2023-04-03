/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Array$;
/*    */ import scala.Function1;
/*    */ import scala.Predef$;
/*    */ import scala.collection.parallel.mutable.ParArray;
/*    */ import scala.collection.parallel.mutable.ParArray$;
/*    */ import scala.math.Numeric;
/*    */ import scala.math.Numeric$IntIsIntegral$;
/*    */ import scala.math.package$;
/*    */ import scala.reflect.ClassTag;
/*    */ import scala.reflect.ClassTag$;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.RichInt$;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ public abstract class ArrayOps$class {
/*    */   public static void $init$(ArrayOps $this) {}
/*    */   
/*    */   private static Class elementClass(ArrayOps $this) {
/* 39 */     return ScalaRunTime$.MODULE$.arrayElementClass($this.repr().getClass());
/*    */   }
/*    */   
/*    */   public static void copyToArray(ArrayOps $this, Object xs, int start, int len) {
/* 42 */     int l = package$.MODULE$.min(len, ScalaRunTime$.MODULE$.array_length($this.repr()));
/* 43 */     if (ScalaRunTime$.MODULE$.array_length(xs) - start < l) {
/* 43 */       int i = ScalaRunTime$.MODULE$.array_length(xs) - start;
/* 43 */       Predef$ predef$ = Predef$.MODULE$;
/* 43 */       l = RichInt$.MODULE$.max$extension(i, 0);
/*    */     } 
/* 44 */     Array$.MODULE$.copy($this.repr(), 0, xs, start, l);
/*    */   }
/*    */   
/*    */   public static Object toArray(ArrayOps $this, ClassTag evidence$1) {
/* 48 */     Predef$ predef$ = Predef$.MODULE$;
/* 48 */     Class thatElementClass = ScalaRunTime$.MODULE$.arrayElementClass(evidence$1);
/* 49 */     return (elementClass($this) == thatElementClass) ? 
/* 50 */       $this.repr() : 
/*    */       
/* 52 */       $this.scala$collection$mutable$ArrayOps$$super$toArray(evidence$1);
/*    */   }
/*    */   
/*    */   public static ParArray par(ArrayOps $this) {
/* 55 */     return ParArray$.MODULE$.handoff($this.repr());
/*    */   }
/*    */   
/*    */   public static Object flatten(ArrayOps<T> $this, Function1 asTrav, ClassTag m) {
/* 65 */     ArrayBuilder b = Array$.MODULE$.newBuilder(m);
/* 66 */     b.sizeHint(BoxesRunTime.unboxToInt(Predef$.MODULE$.intArrayOps((int[])$this.map((Function1)new ArrayOps$$anonfun$flatten$1($this), Array$.MODULE$.canBuildFrom(ClassTag$.MODULE$.Int()))).sum((Numeric)Numeric$IntIsIntegral$.MODULE$)));
/* 67 */     $this.foreach((Function1)new ArrayOps$$anonfun$flatten$2($this, b, (ArrayOps<T>)asTrav));
/* 69 */     return b.result();
/*    */   }
/*    */   
/*    */   public static Object[] transpose(ArrayOps<T> $this, Function1 asArray) {
/* 79 */     Builder bb = Array$.MODULE$.newBuilder(ClassTag$.MODULE$.apply(elementClass($this)));
/* 83 */     ArrayBuilder[] bs = (ArrayBuilder[])Predef$.MODULE$.genericArrayOps(asArray.apply($this.head())).map((Function1)new ArrayOps$$anonfun$1($this), Array$.MODULE$.canBuildFrom(ClassTag$.MODULE$.apply(ArrayBuilder.class)));
/* 84 */     $this.foreach((Function1)new ArrayOps$$anonfun$transpose$1($this, bs, (ArrayOps<T>)asArray));
/* 91 */     Predef$.MODULE$.refArrayOps((Object[])bs).foreach((Function1)new ArrayOps$$anonfun$transpose$2($this, (ArrayOps<T>)bb));
/* 92 */     return $this.isEmpty() ? (Object[])bb.result() : (Object[])bb.result();
/*    */   }
/*    */   
/*    */   public static final ArrayBuilder mkRowBuilder$1(ArrayOps $this) {
/*    */     return Array$.MODULE$.newBuilder(ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayElementClass(elementClass($this))));
/*    */   }
/*    */   
/*    */   public static IndexedSeq seq(ArrayOps $this) {
/* 96 */     return $this.thisCollection();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\ArrayOps$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */