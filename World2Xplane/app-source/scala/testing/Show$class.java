/*     */ package scala.testing;
/*     */ 
/*     */ import java.lang.reflect.Method;
/*     */ import scala.Function1;
/*     */ import scala.Predef$;
/*     */ import scala.Some;
/*     */ import scala.Symbol;
/*     */ import scala.collection.LinearSeqOptimized;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Seq$;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.List$;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ 
/*     */ public abstract class Show$class {
/*     */   public static void $init$(Show $this) {}
/*     */   
/*     */   public static Show.SymApply SymApply(Show $this, Symbol f) {
/*  34 */     return new Show.SymApply($this, f);
/*     */   }
/*     */   
/*     */   public static Show.SymApply symApply(Show $this, Symbol sym) {
/*  41 */     return new Show.SymApply($this, sym);
/*     */   }
/*     */   
/*     */   public static String test(Show $this, Symbol f, Seq args) {
/*     */     String str;
/*  47 */     Seq args1 = (Seq)args.map((Function1)new Show$$anonfun$1($this), Seq$.MODULE$.canBuildFrom());
/*  59 */     List list = (List)Predef$.MODULE$.refArrayOps((Object[])$this.getClass().getMethods()).toList().filter((Function1)new Show.$anonfun$2($this, f));
/*  60 */     Some some = List$.MODULE$.unapplySeq((Seq)list);
/*  60 */     if (!some.isEmpty() && some.get() != null && ((LinearSeqOptimized)some.get()).lengthCompare(0) == 
/*     */       
/* 263 */       0) {
/*     */       str = (new StringBuilder()).append(f.name()).append(" is not defined").toString();
/*     */     } else {
/*     */       String str1;
/*     */       Some some1 = List$.MODULE$.unapplySeq((Seq)list);
/* 263 */       if (!some1.isEmpty() && some1.get() != null && ((LinearSeqOptimized)some1.get()).lengthCompare(1) == 0)
/*     */         String str2 = testMethod$1($this, (Method)((LinearSeqOptimized)some1.get()).apply(0), args1, f, args); 
/*     */       List list1 = (List)list.filter((Function1)new Show.$anonfun$3($this, args));
/*     */       Some some2 = List$.MODULE$.unapplySeq((Seq)list1);
/* 263 */       if (!some2.isEmpty() && some2.get() != null && ((LinearSeqOptimized)some2.get()).lengthCompare(0) == 0) {
/*     */         str1 = testMethod$1($this, (Method)list.head(), args1, f, args);
/*     */       } else {
/*     */         Some some3 = List$.MODULE$.unapplySeq((Seq)list1);
/* 263 */         if (!some3.isEmpty() && some3.get() != null && ((LinearSeqOptimized)some3.get()).lengthCompare(1) == 0)
/*     */           String str2 = testMethod$1($this, (Method)((LinearSeqOptimized)some3.get()).apply(0), args1, f, args); 
/*     */         str1 = (new StringBuilder()).append("cannot disambiguate between multiple implementations of ").append(f.name()).toString();
/*     */       } 
/*     */       str = str1;
/*     */     } 
/*     */     return str;
/*     */   }
/*     */   
/*     */   private static final String testMethod$1(Show $this, Method meth, Seq args1$1, Symbol f$1, Seq args$1) {
/*     */     return (new StringBuilder()).append(f$1.name()).append("(").append(args$1.mkString(",")).append(")  gives  ").append(liftedTree1$1($this, args1$1, meth)).toString();
/*     */   }
/*     */   
/*     */   private static final Object liftedTree1$1(Show $this, Seq args1$1, Method meth$1) {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\testing\Show$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */