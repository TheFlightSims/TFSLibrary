/*    */ package scala;
/*    */ 
/*    */ public final class Specializable$ {
/*    */   public static final Specializable$ MODULE$;
/*    */   
/*    */   private final Specializable.Group<Tuple9<Byte$, Short$, Int$, Long$, Char$, Float$, Double$, Boolean$, Unit$>> Primitives;
/*    */   
/*    */   private final Specializable.Group<Tuple10<Byte$, Short$, Int$, Long$, Char$, Float$, Double$, Boolean$, Unit$, Object>> Everything;
/*    */   
/*    */   private final Specializable.Group<Tuple4<Int$, Long$, Float$, Double$>> Bits32AndUp;
/*    */   
/*    */   private final Specializable.Group<Tuple5<Byte$, Short$, Int$, Long$, Char$>> Integral;
/*    */   
/*    */   private final Specializable.Group<Tuple7<Byte$, Short$, Int$, Long$, Char$, Float$, Double$>> AllNumeric;
/*    */   
/*    */   private final Specializable.Group<Tuple5<Int$, Double$, Boolean$, Unit$, Object>> BestOfBreed;
/*    */   
/*    */   private Specializable$() {
/* 16 */     MODULE$ = this;
/* 23 */     this.Primitives = new Specializable.Group<Tuple9<Byte$, Short$, Int$, Long$, Char$, Float$, Double$, Boolean$, Unit$>>(new Tuple9<Byte$, Short$, Int$, Long$, Char$, Float$, Double$, Boolean$, Unit$>(Byte$.MODULE$, Short$.MODULE$, Int$.MODULE$, Long$.MODULE$, Char$.MODULE$, Float$.MODULE$, Double$.MODULE$, Boolean$.MODULE$, Unit$.MODULE$));
/* 24 */     this.Everything = new Specializable.Group<Tuple10<Byte$, Short$, Int$, Long$, Char$, Float$, Double$, Boolean$, Unit$, Object>>(new Tuple10<Byte$, Short$, Int$, Long$, Char$, Float$, Double$, Boolean$, Unit$, Object>(Byte$.MODULE$, Short$.MODULE$, Int$.MODULE$, Long$.MODULE$, Char$.MODULE$, Float$.MODULE$, Double$.MODULE$, Boolean$.MODULE$, Unit$.MODULE$, package$.MODULE$.AnyRef()));
/* 25 */     this.Bits32AndUp = new Specializable.Group<Tuple4<Int$, Long$, Float$, Double$>>(new Tuple4<Int$, Long$, Float$, Double$>(Int$.MODULE$, Long$.MODULE$, Float$.MODULE$, Double$.MODULE$));
/* 26 */     this.Integral = new Specializable.Group<Tuple5<Byte$, Short$, Int$, Long$, Char$>>(new Tuple5<Byte$, Short$, Int$, Long$, Char$>(Byte$.MODULE$, Short$.MODULE$, Int$.MODULE$, Long$.MODULE$, Char$.MODULE$));
/* 27 */     this.AllNumeric = new Specializable.Group<Tuple7<Byte$, Short$, Int$, Long$, Char$, Float$, Double$>>(new Tuple7<Byte$, Short$, Int$, Long$, Char$, Float$, Double$>(Byte$.MODULE$, Short$.MODULE$, Int$.MODULE$, Long$.MODULE$, Char$.MODULE$, Float$.MODULE$, Double$.MODULE$));
/* 28 */     this.BestOfBreed = new Specializable.Group<Tuple5<Int$, Double$, Boolean$, Unit$, Object>>(new Tuple5<Int$, Double$, Boolean$, Unit$, Object>(Int$.MODULE$, Double$.MODULE$, Boolean$.MODULE$, Unit$.MODULE$, package$.MODULE$.AnyRef()));
/*    */   }
/*    */   
/*    */   public final Specializable.Group<Tuple9<Byte$, Short$, Int$, Long$, Char$, Float$, Double$, Boolean$, Unit$>> Primitives() {
/*    */     return this.Primitives;
/*    */   }
/*    */   
/*    */   public final Specializable.Group<Tuple10<Byte$, Short$, Int$, Long$, Char$, Float$, Double$, Boolean$, Unit$, Object>> Everything() {
/*    */     return this.Everything;
/*    */   }
/*    */   
/*    */   public final Specializable.Group<Tuple4<Int$, Long$, Float$, Double$>> Bits32AndUp() {
/*    */     return this.Bits32AndUp;
/*    */   }
/*    */   
/*    */   public final Specializable.Group<Tuple5<Byte$, Short$, Int$, Long$, Char$>> Integral() {
/*    */     return this.Integral;
/*    */   }
/*    */   
/*    */   public final Specializable.Group<Tuple7<Byte$, Short$, Int$, Long$, Char$, Float$, Double$>> AllNumeric() {
/*    */     return this.AllNumeric;
/*    */   }
/*    */   
/*    */   public final Specializable.Group<Tuple5<Int$, Double$, Boolean$, Unit$, Object>> BestOfBreed() {
/* 28 */     return this.BestOfBreed;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Specializable$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */