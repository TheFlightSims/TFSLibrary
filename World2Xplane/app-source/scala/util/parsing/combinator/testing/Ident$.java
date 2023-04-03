/*   */ package scala.util.parsing.combinator.testing;
/*   */ 
/*   */ import scala.Option;
/*   */ import scala.Serializable;
/*   */ import scala.Some;
/*   */ import scala.runtime.AbstractFunction1;
/*   */ 
/*   */ public final class Ident$ extends AbstractFunction1<String, Ident> implements Serializable {
/*   */   public static final Ident$ MODULE$;
/*   */   
/*   */   public final String toString() {
/* 9 */     return "Ident";
/*   */   }
/*   */   
/*   */   public Ident apply(String s) {
/* 9 */     return new Ident(s);
/*   */   }
/*   */   
/*   */   public Option<String> unapply(Ident x$0) {
/* 9 */     return (x$0 == null) ? (Option<String>)scala.None$.MODULE$ : (Option<String>)new Some(x$0.s());
/*   */   }
/*   */   
/*   */   private Object readResolve() {
/* 9 */     return MODULE$;
/*   */   }
/*   */   
/*   */   private Ident$() {
/* 9 */     MODULE$ = this;
/*   */   }
/*   */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\combinator\testing\Ident$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */