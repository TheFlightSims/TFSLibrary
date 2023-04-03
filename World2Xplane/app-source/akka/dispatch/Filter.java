/*     */ package akka.dispatch;
/*     */ 
/*     */ import akka.japi.Function;
/*     */ import scala.Function1;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001e:Q!\001\002\t\002\035\taAR5mi\026\024(BA\002\005\003!!\027n\0359bi\016D'\"A\003\002\t\005\\7.Y\002\001!\tA\021\"D\001\003\r\025Q!\001#\001\f\005\0311\025\016\034;feN\021\021\002\004\t\003\033Ai\021A\004\006\002\037\005)1oY1mC&\021\021C\004\002\007\003:L(+\0324\t\013MIA\021\001\013\002\rqJg.\033;?)\0059\001\"\002\f\n\t\0039\022\001\0034jYR,'o\0244\026\005aqBCA\r+!\021i!\004H\024\n\005mq!!\003$v]\016$\030n\03482!\tib\004\004\001\005\013})\"\031\001\021\003\003Q\013\"!\t\023\021\0055\021\023BA\022\017\005\035qu\016\0365j]\036\004\"!D\023\n\005\031r!aA!osB\021Q\002K\005\003S9\021qAQ8pY\026\fg\016C\003,+\001\007A&A\001g!\021i\003\007\b\032\016\0039R!a\f\003\002\t)\f\007/[\005\003c9\022\001BR;oGRLwN\034\t\003gaj\021\001\016\006\003kY\nA\001\\1oO*\tq'\001\003kCZ\f\027BA\0255\001")
/*     */ public final class Filter {
/*     */   public static <T> Function1<T, Object> filterOf(Function<T, Boolean> paramFunction) {
/*     */     return Filter$.MODULE$.filterOf(paramFunction);
/*     */   }
/*     */   
/*     */   public static class Filter$$anon$1 implements Function1<T, Object> {
/*     */     private final Function f$1;
/*     */     
/*     */     public boolean apply$mcZD$sp(double v1) {
/* 308 */       return Function1.class.apply$mcZD$sp(this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDD$sp(double v1) {
/* 308 */       return Function1.class.apply$mcDD$sp(this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFD$sp(double v1) {
/* 308 */       return Function1.class.apply$mcFD$sp(this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcID$sp(double v1) {
/* 308 */       return Function1.class.apply$mcID$sp(this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJD$sp(double v1) {
/* 308 */       return Function1.class.apply$mcJD$sp(this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVD$sp(double v1) {
/* 308 */       Function1.class.apply$mcVD$sp(this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZF$sp(float v1) {
/* 308 */       return Function1.class.apply$mcZF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDF$sp(float v1) {
/* 308 */       return Function1.class.apply$mcDF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFF$sp(float v1) {
/* 308 */       return Function1.class.apply$mcFF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcIF$sp(float v1) {
/* 308 */       return Function1.class.apply$mcIF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJF$sp(float v1) {
/* 308 */       return Function1.class.apply$mcJF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVF$sp(float v1) {
/* 308 */       Function1.class.apply$mcVF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZI$sp(int v1) {
/* 308 */       return Function1.class.apply$mcZI$sp(this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDI$sp(int v1) {
/* 308 */       return Function1.class.apply$mcDI$sp(this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFI$sp(int v1) {
/* 308 */       return Function1.class.apply$mcFI$sp(this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcII$sp(int v1) {
/* 308 */       return Function1.class.apply$mcII$sp(this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJI$sp(int v1) {
/* 308 */       return Function1.class.apply$mcJI$sp(this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVI$sp(int v1) {
/* 308 */       Function1.class.apply$mcVI$sp(this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZJ$sp(long v1) {
/* 308 */       return Function1.class.apply$mcZJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDJ$sp(long v1) {
/* 308 */       return Function1.class.apply$mcDJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFJ$sp(long v1) {
/* 308 */       return Function1.class.apply$mcFJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcIJ$sp(long v1) {
/* 308 */       return Function1.class.apply$mcIJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJJ$sp(long v1) {
/* 308 */       return Function1.class.apply$mcJJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVJ$sp(long v1) {
/* 308 */       Function1.class.apply$mcVJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose(Function1 g) {
/* 308 */       return Function1.class.compose(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/* 308 */       return Function1.class.compose$mcZD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/* 308 */       return Function1.class.compose$mcDD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/* 308 */       return Function1.class.compose$mcFD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/* 308 */       return Function1.class.compose$mcID$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/* 308 */       return Function1.class.compose$mcJD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/* 308 */       return Function1.class.compose$mcVD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/* 308 */       return Function1.class.compose$mcZF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/* 308 */       return Function1.class.compose$mcDF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/* 308 */       return Function1.class.compose$mcFF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/* 308 */       return Function1.class.compose$mcIF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/* 308 */       return Function1.class.compose$mcJF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/* 308 */       return Function1.class.compose$mcVF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/* 308 */       return Function1.class.compose$mcZI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/* 308 */       return Function1.class.compose$mcDI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/* 308 */       return Function1.class.compose$mcFI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/* 308 */       return Function1.class.compose$mcII$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/* 308 */       return Function1.class.compose$mcJI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/* 308 */       return Function1.class.compose$mcVI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/* 308 */       return Function1.class.compose$mcZJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/* 308 */       return Function1.class.compose$mcDJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/* 308 */       return Function1.class.compose$mcFJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/* 308 */       return Function1.class.compose$mcIJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/* 308 */       return Function1.class.compose$mcJJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/* 308 */       return Function1.class.compose$mcVJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<T, A> andThen(Function1 g) {
/* 308 */       return Function1.class.andThen(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/* 308 */       return Function1.class.andThen$mcZD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/* 308 */       return Function1.class.andThen$mcDD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/* 308 */       return Function1.class.andThen$mcFD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/* 308 */       return Function1.class.andThen$mcID$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/* 308 */       return Function1.class.andThen$mcJD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/* 308 */       return Function1.class.andThen$mcVD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/* 308 */       return Function1.class.andThen$mcZF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/* 308 */       return Function1.class.andThen$mcDF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/* 308 */       return Function1.class.andThen$mcFF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/* 308 */       return Function1.class.andThen$mcIF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/* 308 */       return Function1.class.andThen$mcJF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/* 308 */       return Function1.class.andThen$mcVF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/* 308 */       return Function1.class.andThen$mcZI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/* 308 */       return Function1.class.andThen$mcDI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/* 308 */       return Function1.class.andThen$mcFI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/* 308 */       return Function1.class.andThen$mcII$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/* 308 */       return Function1.class.andThen$mcJI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/* 308 */       return Function1.class.andThen$mcVI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/* 308 */       return Function1.class.andThen$mcZJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/* 308 */       return Function1.class.andThen$mcDJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/* 308 */       return Function1.class.andThen$mcFJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/* 308 */       return Function1.class.andThen$mcIJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/* 308 */       return Function1.class.andThen$mcJJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/* 308 */       return Function1.class.andThen$mcVJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 308 */       return Function1.class.toString(this);
/*     */     }
/*     */     
/*     */     public boolean apply(Object result) {
/* 308 */       return ((Boolean)this.f$1.apply(result)).booleanValue();
/*     */     }
/*     */     
/*     */     public Filter$$anon$1(Function f$1) {
/* 308 */       Function1.class.$init$(this);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\Filter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */