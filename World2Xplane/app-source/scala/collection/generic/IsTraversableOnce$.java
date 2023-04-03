/*    */ package scala.collection.generic;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Serializable;
/*    */ import scala.collection.GenTraversableOnce;
/*    */ import scala.collection.immutable.StringOps;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ public final class IsTraversableOnce$ {
/*    */   public static final IsTraversableOnce$ MODULE$;
/*    */   
/*    */   private final IsTraversableOnce<String> stringRepr;
/*    */   
/*    */   private IsTraversableOnce$() {
/* 47 */     MODULE$ = this;
/* 50 */     this.stringRepr = 
/* 51 */       new IsTraversableOnce.$anon$1();
/*    */   }
/*    */   
/*    */   public IsTraversableOnce<String> stringRepr() {
/*    */     return this.stringRepr;
/*    */   }
/*    */   
/*    */   public static class $anon$1 implements IsTraversableOnce<String> {
/*    */     private final Function1<String, GenTraversableOnce<Object>> conversion;
/*    */     
/*    */     public $anon$1() {
/* 53 */       $anonfun$1 $anonfun$1 = new $anonfun$1(this);
/* 53 */       scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 53 */       this.conversion = (Function1<String, GenTraversableOnce<Object>>)$anonfun$1;
/*    */     }
/*    */     
/*    */     public Function1<String, GenTraversableOnce<Object>> conversion() {
/* 53 */       return this.conversion;
/*    */     }
/*    */     
/*    */     public class $anonfun$1 extends AbstractFunction1<String, String> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final String apply(String x) {
/* 53 */         scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 53 */         return x;
/*    */       }
/*    */       
/*    */       public $anonfun$1(IsTraversableOnce.$anon$1 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public <C, A0> IsTraversableOnce<C> genTraversableLikeRepr(Function1 conv) {
/* 57 */     return new IsTraversableOnce$$anon$2(conv);
/*    */   }
/*    */   
/*    */   public static class IsTraversableOnce$$anon$2 implements IsTraversableOnce<C> {
/*    */     private final Function1<C, GenTraversableOnce<A0>> conversion;
/*    */     
/*    */     public IsTraversableOnce$$anon$2(Function1<C, GenTraversableOnce<A0>> conv$1) {
/* 59 */       this.conversion = conv$1;
/*    */     }
/*    */     
/*    */     public Function1<C, GenTraversableOnce<A0>> conversion() {
/* 59 */       return this.conversion;
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\IsTraversableOnce$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */