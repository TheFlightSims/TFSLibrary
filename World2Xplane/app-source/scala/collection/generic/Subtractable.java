/*    */ package scala.collection.generic;
/*    */ 
/*    */ import scala.Serializable;
/*    */ import scala.collection.GenTraversableOnce;
/*    */ import scala.collection.Seq;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction2;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\t3q!\001\002\021\002\007\005\021B\001\007Tk\n$(/Y2uC\ndWM\003\002\004\t\0059q-\0328fe&\034'BA\003\007\003)\031w\016\0347fGRLwN\034\006\002\017\005)1oY1mC\016\001Qc\001\006$5M\021\001a\003\t\003\0315i\021AB\005\003\035\031\021a!\0218z%\0264\007\"\002\t\001\t\003\t\022A\002\023j]&$H\005F\001\023!\ta1#\003\002\025\r\t!QK\\5u\021\0251\002A\"\005\030\003\021\021X\r\035:\026\003a\001\"!\007\016\r\001\02111\004\001CC\002q\021AAU3qeF\021Q\004\t\t\003\031yI!a\b\004\003\0179{G\017[5oOB!\021\005\001\022\031\033\005\021\001CA\r$\t\025!\003A1\001&\005\005\t\025CA\017'!\taq%\003\002)\r\t\031\021I\\=\t\013)\002a\021A\026\002\r\021j\027N\\;t)\tAB\006C\003.S\001\007!%\001\003fY\026l\007\"\002\026\001\t\003yC\003\002\r1eQBQ!\r\030A\002\t\nQ!\0327f[FBQa\r\030A\002\t\nQ!\0327f[JBQ!\016\030A\002Y\nQ!\0327f[N\0042\001D\034#\023\tAdA\001\006=e\026\004X-\031;fIzBQA\017\001\005\002m\nA\002J7j]V\034H%\\5okN$\"\001\007\037\t\013uJ\004\031\001 \002\005a\034\bcA AE5\tA!\003\002B\t\t\021r)\0328Ue\0064XM]:bE2,wJ\\2f\001")
/*    */ public interface Subtractable<A, Repr extends Subtractable<A, Repr>> {
/*    */   Repr repr();
/*    */   
/*    */   Repr $minus(A paramA);
/*    */   
/*    */   Repr $minus(A paramA1, A paramA2, Seq<A> paramSeq);
/*    */   
/*    */   Repr $minus$minus(GenTraversableOnce<A> paramGenTraversableOnce);
/*    */   
/*    */   public class Subtractable$$anonfun$$minus$minus$1 extends AbstractFunction2<Repr, A, Repr> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Repr apply(Subtractable<Object, Repr> x$2, Object x$3) {
/* 59 */       return x$2.$minus(x$3);
/*    */     }
/*    */     
/*    */     public Subtractable$$anonfun$$minus$minus$1(Subtractable $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\Subtractable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */