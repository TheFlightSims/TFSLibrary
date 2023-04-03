/*    */ package scala.testing;
/*    */ 
/*    */ import java.lang.reflect.Method;
/*    */ import scala.Predef$;
/*    */ import scala.Serializable;
/*    */ import scala.Symbol;
/*    */ import scala.collection.Seq;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001a3q!\001\002\021\002\007\005qA\001\003TQ><(BA\002\005\003\035!Xm\035;j]\036T\021!B\001\006g\016\fG.Y\002\001'\t\001\001\002\005\002\n\0255\tA!\003\002\f\t\t1\021I\\=SK\032DQ!\004\001\005\0029\ta\001J5oSR$C#A\b\021\005%\001\022BA\t\005\005\021)f.\033;\007\tM\001\021\001\006\002\t'fl\027\t\0359msN\021!\003\003\005\t-I\021\t\021)A\005/\005\ta\r\005\002\n1%\021\021\004\002\002\007'fl'm\0347\t\013m\021B\021\001\017\002\rqJg.\033;?)\tir\004\005\002\037%5\t\001\001C\003\0275\001\007q\003C\003\"%\021\005!%A\003baBd\0270\006\002$WQ\021q\002\n\005\006K\001\002\rAJ\001\005CJ<7\017E\002\nO%J!\001\013\003\003\025q\022X\r]3bi\026$g\b\005\002+W1\001A!\002\027!\005\004i#!A!\022\0059\n\004CA\0050\023\t\001DAA\004O_RD\027N\\4\021\005%\021\024BA\032\005\005\r\te.\037\005\bk\001\t\t\021b\0017\003!\031\0260\\!qa2LHCA\0178\021\0251B\0071\001\030\021\025I\004\001\"\001;\003!\031\0300\\!qa2LHCA\017<\021\025a\004\b1\001\030\003\r\031\0300\034\025\005qy\n5\t\005\002\n%\021\001\t\002\002\013I\026\004(/Z2bi\026$\027%\001\"\002)U\034X\rI*z[\006\003\b\017\\=!S:\034H/Z1eC\005!\025A\002\032/cAr\003\007C\003G\001\021\005q)\001\003uKN$XC\001%U)\rI\005+\025\t\003\0256s!!C&\n\0051#\021A\002)sK\022,g-\003\002O\037\n11\013\036:j]\036T!\001\024\003\t\013Y)\005\031A\f\t\013\025*\005\031\001*\021\007%93\013\005\002+)\022)A&\022b\001[!\"\001A\020,DC\0059\026a\007+iSN\0043\r\\1tg\002:\030\016\0347!E\026\004#/Z7pm\026$g\006")
/*    */ public interface Show {
/*    */   SymApply SymApply(Symbol paramSymbol);
/*    */   
/*    */   SymApply symApply(Symbol paramSymbol);
/*    */   
/*    */   <A> String test(Symbol paramSymbol, Seq<A> paramSeq);
/*    */   
/*    */   public class SymApply {
/*    */     private final Symbol f;
/*    */     
/*    */     public SymApply(Show $outer, Symbol f) {}
/*    */     
/*    */     public <A> void apply(Seq<?> args) {
/* 36 */       Predef$.MODULE$.println(scala$testing$Show$SymApply$$$outer().test(this.f, args));
/*    */     }
/*    */   }
/*    */   
/*    */   public class Show$$anonfun$1 extends AbstractFunction1<A, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Object apply(Object x$1) {
/* 47 */       return x$1;
/*    */     }
/*    */     
/*    */     public Show$$anonfun$1(Show $outer) {}
/*    */   }
/*    */   
/*    */   public class $anonfun$2 extends AbstractFunction1<Method, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Symbol f$1;
/*    */     
/*    */     public final boolean apply(Method x$2) {
/* 59 */       String str = this.f$1.name();
/* 59 */       if (x$2.getName() == null) {
/* 59 */         x$2.getName();
/* 59 */         if (str != null);
/* 59 */       } else if (x$2.getName().equals(str)) {
/*    */       
/*    */       } 
/*    */     }
/*    */     
/*    */     public $anonfun$2(Show $outer, Symbol f$1) {}
/*    */   }
/*    */   
/*    */   public class $anonfun$3 extends AbstractFunction1<Method, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Seq args$1;
/*    */     
/*    */     public final boolean apply(Method x$3) {
/* 65 */       return ((x$3.getParameterTypes()).length == this.args$1.length());
/*    */     }
/*    */     
/*    */     public $anonfun$3(Show $outer, Seq args$1) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\testing\Show.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */