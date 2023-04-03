/*     */ package scala.collection.generic;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.collection.GenTraversableLike;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ 
/*     */ public final class IsTraversableLike$ {
/*     */   public static final IsTraversableLike$ MODULE$;
/*     */   
/*     */   private final IsTraversableLike<String> stringRepr;
/*     */   
/*     */   private IsTraversableLike$() {
/* 115 */     MODULE$ = this;
/* 118 */     this.stringRepr = 
/* 119 */       new IsTraversableLike.$anon$1();
/*     */   }
/*     */   
/*     */   public IsTraversableLike<String> stringRepr() {
/*     */     return this.stringRepr;
/*     */   }
/*     */   
/*     */   public static class $anon$1 implements IsTraversableLike<String> {
/*     */     private final Function1<String, GenTraversableLike<Object, String>> conversion;
/*     */     
/*     */     public $anon$1() {
/* 121 */       $anonfun$1 $anonfun$1 = new $anonfun$1(this);
/* 121 */       scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 121 */       this.conversion = (Function1<String, GenTraversableLike<Object, String>>)$anonfun$1;
/*     */     }
/*     */     
/*     */     public Function1<String, GenTraversableLike<Object, String>> conversion() {
/* 121 */       return this.conversion;
/*     */     }
/*     */     
/*     */     public class $anonfun$1 extends AbstractFunction1<String, String> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final String apply(String x) {
/* 121 */         scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 121 */         return x;
/*     */       }
/*     */       
/*     */       public $anonfun$1(IsTraversableLike.$anon$1 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public <C, A0> IsTraversableLike<C> genTraversableLikeRepr(Function1 conv) {
/* 125 */     return new IsTraversableLike$$anon$2(conv);
/*     */   }
/*     */   
/*     */   public static class IsTraversableLike$$anon$2 implements IsTraversableLike<C> {
/*     */     private final Function1<C, GenTraversableLike<A0, C>> conversion;
/*     */     
/*     */     public IsTraversableLike$$anon$2(Function1<C, GenTraversableLike<A0, C>> conv$1) {
/* 127 */       this.conversion = conv$1;
/*     */     }
/*     */     
/*     */     public Function1<C, GenTraversableLike<A0, C>> conversion() {
/* 127 */       return this.conversion;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\IsTraversableLike$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */