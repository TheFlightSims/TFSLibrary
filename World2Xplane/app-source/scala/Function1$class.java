/*    */ package scala;
/*    */ 
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public abstract class Function1$class {
/*    */   public static void $init$(Function1 $this) {}
/*    */   
/*    */   public static boolean apply$mcZD$sp(Function1 $this, double v1) {
/* 39 */     return BoxesRunTime.unboxToBoolean($this.apply(BoxesRunTime.boxToDouble(v1)));
/*    */   }
/*    */   
/*    */   public static double apply$mcDD$sp(Function1 $this, double v1) {
/* 39 */     return BoxesRunTime.unboxToDouble($this.apply(BoxesRunTime.boxToDouble(v1)));
/*    */   }
/*    */   
/*    */   public static float apply$mcFD$sp(Function1 $this, double v1) {
/* 39 */     return BoxesRunTime.unboxToFloat($this.apply(BoxesRunTime.boxToDouble(v1)));
/*    */   }
/*    */   
/*    */   public static int apply$mcID$sp(Function1 $this, double v1) {
/* 39 */     return BoxesRunTime.unboxToInt($this.apply(BoxesRunTime.boxToDouble(v1)));
/*    */   }
/*    */   
/*    */   public static long apply$mcJD$sp(Function1 $this, double v1) {
/* 39 */     return BoxesRunTime.unboxToLong($this.apply(BoxesRunTime.boxToDouble(v1)));
/*    */   }
/*    */   
/*    */   public static void apply$mcVD$sp(Function1 $this, double v1) {
/* 39 */     $this.apply(BoxesRunTime.boxToDouble(v1));
/*    */   }
/*    */   
/*    */   public static boolean apply$mcZF$sp(Function1 $this, float v1) {
/* 39 */     return BoxesRunTime.unboxToBoolean($this.apply(BoxesRunTime.boxToFloat(v1)));
/*    */   }
/*    */   
/*    */   public static double apply$mcDF$sp(Function1 $this, float v1) {
/* 39 */     return BoxesRunTime.unboxToDouble($this.apply(BoxesRunTime.boxToFloat(v1)));
/*    */   }
/*    */   
/*    */   public static float apply$mcFF$sp(Function1 $this, float v1) {
/* 39 */     return BoxesRunTime.unboxToFloat($this.apply(BoxesRunTime.boxToFloat(v1)));
/*    */   }
/*    */   
/*    */   public static int apply$mcIF$sp(Function1 $this, float v1) {
/* 39 */     return BoxesRunTime.unboxToInt($this.apply(BoxesRunTime.boxToFloat(v1)));
/*    */   }
/*    */   
/*    */   public static long apply$mcJF$sp(Function1 $this, float v1) {
/* 39 */     return BoxesRunTime.unboxToLong($this.apply(BoxesRunTime.boxToFloat(v1)));
/*    */   }
/*    */   
/*    */   public static void apply$mcVF$sp(Function1 $this, float v1) {
/* 39 */     $this.apply(BoxesRunTime.boxToFloat(v1));
/*    */   }
/*    */   
/*    */   public static boolean apply$mcZI$sp(Function1 $this, int v1) {
/* 39 */     return BoxesRunTime.unboxToBoolean($this.apply(BoxesRunTime.boxToInteger(v1)));
/*    */   }
/*    */   
/*    */   public static double apply$mcDI$sp(Function1 $this, int v1) {
/* 39 */     return BoxesRunTime.unboxToDouble($this.apply(BoxesRunTime.boxToInteger(v1)));
/*    */   }
/*    */   
/*    */   public static float apply$mcFI$sp(Function1 $this, int v1) {
/* 39 */     return BoxesRunTime.unboxToFloat($this.apply(BoxesRunTime.boxToInteger(v1)));
/*    */   }
/*    */   
/*    */   public static int apply$mcII$sp(Function1 $this, int v1) {
/* 39 */     return BoxesRunTime.unboxToInt($this.apply(BoxesRunTime.boxToInteger(v1)));
/*    */   }
/*    */   
/*    */   public static long apply$mcJI$sp(Function1 $this, int v1) {
/* 39 */     return BoxesRunTime.unboxToLong($this.apply(BoxesRunTime.boxToInteger(v1)));
/*    */   }
/*    */   
/*    */   public static void apply$mcVI$sp(Function1 $this, int v1) {
/* 39 */     $this.apply(BoxesRunTime.boxToInteger(v1));
/*    */   }
/*    */   
/*    */   public static boolean apply$mcZJ$sp(Function1 $this, long v1) {
/* 39 */     return BoxesRunTime.unboxToBoolean($this.apply(BoxesRunTime.boxToLong(v1)));
/*    */   }
/*    */   
/*    */   public static double apply$mcDJ$sp(Function1 $this, long v1) {
/* 39 */     return BoxesRunTime.unboxToDouble($this.apply(BoxesRunTime.boxToLong(v1)));
/*    */   }
/*    */   
/*    */   public static float apply$mcFJ$sp(Function1 $this, long v1) {
/* 39 */     return BoxesRunTime.unboxToFloat($this.apply(BoxesRunTime.boxToLong(v1)));
/*    */   }
/*    */   
/*    */   public static int apply$mcIJ$sp(Function1 $this, long v1) {
/* 39 */     return BoxesRunTime.unboxToInt($this.apply(BoxesRunTime.boxToLong(v1)));
/*    */   }
/*    */   
/*    */   public static long apply$mcJJ$sp(Function1 $this, long v1) {
/* 39 */     return BoxesRunTime.unboxToLong($this.apply(BoxesRunTime.boxToLong(v1)));
/*    */   }
/*    */   
/*    */   public static void apply$mcVJ$sp(Function1 $this, long v1) {
/* 39 */     $this.apply(BoxesRunTime.boxToLong(v1));
/*    */   }
/*    */   
/*    */   public static Function1 compose(Function1 $this, Function1<T1, R> g) {
/* 47 */     return (Function1)new Function1$$anonfun$compose$1($this, g);
/*    */   }
/*    */   
/*    */   public static Function1 compose$mcZD$sp(Function1 $this, Function1 g) {
/* 47 */     return $this.compose(g);
/*    */   }
/*    */   
/*    */   public static Function1 compose$mcDD$sp(Function1 $this, Function1 g) {
/* 47 */     return $this.compose(g);
/*    */   }
/*    */   
/*    */   public static Function1 compose$mcFD$sp(Function1 $this, Function1 g) {
/* 47 */     return $this.compose(g);
/*    */   }
/*    */   
/*    */   public static Function1 compose$mcID$sp(Function1 $this, Function1 g) {
/* 47 */     return $this.compose(g);
/*    */   }
/*    */   
/*    */   public static Function1 compose$mcJD$sp(Function1 $this, Function1 g) {
/* 47 */     return $this.compose(g);
/*    */   }
/*    */   
/*    */   public static Function1 compose$mcVD$sp(Function1 $this, Function1 g) {
/* 47 */     return $this.compose(g);
/*    */   }
/*    */   
/*    */   public static Function1 compose$mcZF$sp(Function1 $this, Function1 g) {
/* 47 */     return $this.compose(g);
/*    */   }
/*    */   
/*    */   public static Function1 compose$mcDF$sp(Function1 $this, Function1 g) {
/* 47 */     return $this.compose(g);
/*    */   }
/*    */   
/*    */   public static Function1 compose$mcFF$sp(Function1 $this, Function1 g) {
/* 47 */     return $this.compose(g);
/*    */   }
/*    */   
/*    */   public static Function1 compose$mcIF$sp(Function1 $this, Function1 g) {
/* 47 */     return $this.compose(g);
/*    */   }
/*    */   
/*    */   public static Function1 compose$mcJF$sp(Function1 $this, Function1 g) {
/* 47 */     return $this.compose(g);
/*    */   }
/*    */   
/*    */   public static Function1 compose$mcVF$sp(Function1 $this, Function1 g) {
/* 47 */     return $this.compose(g);
/*    */   }
/*    */   
/*    */   public static Function1 compose$mcZI$sp(Function1 $this, Function1 g) {
/* 47 */     return $this.compose(g);
/*    */   }
/*    */   
/*    */   public static Function1 compose$mcDI$sp(Function1 $this, Function1 g) {
/* 47 */     return $this.compose(g);
/*    */   }
/*    */   
/*    */   public static Function1 compose$mcFI$sp(Function1 $this, Function1 g) {
/* 47 */     return $this.compose(g);
/*    */   }
/*    */   
/*    */   public static Function1 compose$mcII$sp(Function1 $this, Function1 g) {
/* 47 */     return $this.compose(g);
/*    */   }
/*    */   
/*    */   public static Function1 compose$mcJI$sp(Function1 $this, Function1 g) {
/* 47 */     return $this.compose(g);
/*    */   }
/*    */   
/*    */   public static Function1 compose$mcVI$sp(Function1 $this, Function1 g) {
/* 47 */     return $this.compose(g);
/*    */   }
/*    */   
/*    */   public static Function1 compose$mcZJ$sp(Function1 $this, Function1 g) {
/* 47 */     return $this.compose(g);
/*    */   }
/*    */   
/*    */   public static Function1 compose$mcDJ$sp(Function1 $this, Function1 g) {
/* 47 */     return $this.compose(g);
/*    */   }
/*    */   
/*    */   public static Function1 compose$mcFJ$sp(Function1 $this, Function1 g) {
/* 47 */     return $this.compose(g);
/*    */   }
/*    */   
/*    */   public static Function1 compose$mcIJ$sp(Function1 $this, Function1 g) {
/* 47 */     return $this.compose(g);
/*    */   }
/*    */   
/*    */   public static Function1 compose$mcJJ$sp(Function1 $this, Function1 g) {
/* 47 */     return $this.compose(g);
/*    */   }
/*    */   
/*    */   public static Function1 compose$mcVJ$sp(Function1 $this, Function1 g) {
/* 47 */     return $this.compose(g);
/*    */   }
/*    */   
/*    */   public static Function1 andThen(Function1 $this, Function1<T1, R> g) {
/* 55 */     return (Function1)new Function1$$anonfun$andThen$1($this, g);
/*    */   }
/*    */   
/*    */   public static Function1 andThen$mcZD$sp(Function1 $this, Function1 g) {
/* 55 */     return $this.andThen(g);
/*    */   }
/*    */   
/*    */   public static Function1 andThen$mcDD$sp(Function1 $this, Function1 g) {
/* 55 */     return $this.andThen(g);
/*    */   }
/*    */   
/*    */   public static Function1 andThen$mcFD$sp(Function1 $this, Function1 g) {
/* 55 */     return $this.andThen(g);
/*    */   }
/*    */   
/*    */   public static Function1 andThen$mcID$sp(Function1 $this, Function1 g) {
/* 55 */     return $this.andThen(g);
/*    */   }
/*    */   
/*    */   public static Function1 andThen$mcJD$sp(Function1 $this, Function1 g) {
/* 55 */     return $this.andThen(g);
/*    */   }
/*    */   
/*    */   public static Function1 andThen$mcVD$sp(Function1 $this, Function1 g) {
/* 55 */     return $this.andThen(g);
/*    */   }
/*    */   
/*    */   public static Function1 andThen$mcZF$sp(Function1 $this, Function1 g) {
/* 55 */     return $this.andThen(g);
/*    */   }
/*    */   
/*    */   public static Function1 andThen$mcDF$sp(Function1 $this, Function1 g) {
/* 55 */     return $this.andThen(g);
/*    */   }
/*    */   
/*    */   public static Function1 andThen$mcFF$sp(Function1 $this, Function1 g) {
/* 55 */     return $this.andThen(g);
/*    */   }
/*    */   
/*    */   public static Function1 andThen$mcIF$sp(Function1 $this, Function1 g) {
/* 55 */     return $this.andThen(g);
/*    */   }
/*    */   
/*    */   public static Function1 andThen$mcJF$sp(Function1 $this, Function1 g) {
/* 55 */     return $this.andThen(g);
/*    */   }
/*    */   
/*    */   public static Function1 andThen$mcVF$sp(Function1 $this, Function1 g) {
/* 55 */     return $this.andThen(g);
/*    */   }
/*    */   
/*    */   public static Function1 andThen$mcZI$sp(Function1 $this, Function1 g) {
/* 55 */     return $this.andThen(g);
/*    */   }
/*    */   
/*    */   public static Function1 andThen$mcDI$sp(Function1 $this, Function1 g) {
/* 55 */     return $this.andThen(g);
/*    */   }
/*    */   
/*    */   public static Function1 andThen$mcFI$sp(Function1 $this, Function1 g) {
/* 55 */     return $this.andThen(g);
/*    */   }
/*    */   
/*    */   public static Function1 andThen$mcII$sp(Function1 $this, Function1 g) {
/* 55 */     return $this.andThen(g);
/*    */   }
/*    */   
/*    */   public static Function1 andThen$mcJI$sp(Function1 $this, Function1 g) {
/* 55 */     return $this.andThen(g);
/*    */   }
/*    */   
/*    */   public static Function1 andThen$mcVI$sp(Function1 $this, Function1 g) {
/* 55 */     return $this.andThen(g);
/*    */   }
/*    */   
/*    */   public static Function1 andThen$mcZJ$sp(Function1 $this, Function1 g) {
/* 55 */     return $this.andThen(g);
/*    */   }
/*    */   
/*    */   public static Function1 andThen$mcDJ$sp(Function1 $this, Function1 g) {
/* 55 */     return $this.andThen(g);
/*    */   }
/*    */   
/*    */   public static Function1 andThen$mcFJ$sp(Function1 $this, Function1 g) {
/* 55 */     return $this.andThen(g);
/*    */   }
/*    */   
/*    */   public static Function1 andThen$mcIJ$sp(Function1 $this, Function1 g) {
/* 55 */     return $this.andThen(g);
/*    */   }
/*    */   
/*    */   public static Function1 andThen$mcJJ$sp(Function1 $this, Function1 g) {
/* 55 */     return $this.andThen(g);
/*    */   }
/*    */   
/*    */   public static Function1 andThen$mcVJ$sp(Function1 $this, Function1 g) {
/* 55 */     return $this.andThen(g);
/*    */   }
/*    */   
/*    */   public static String toString(Function1 $this) {
/* 57 */     return "<function1>";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Function1$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */