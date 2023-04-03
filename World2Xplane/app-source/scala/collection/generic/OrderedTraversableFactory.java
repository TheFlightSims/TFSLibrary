/*    */ package scala.collection.generic;
/*    */ 
/*    */ import scala.collection.Traversable;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.math.Ordering;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0354Q!\001\002\002\002%\021\021d\024:eKJ,G\r\026:bm\026\0248/\0312mK\032\0137\r^8ss*\0211\001B\001\bO\026tWM]5d\025\t)a!\001\006d_2dWm\031;j_:T\021aB\001\006g\016\fG.Y\002\001+\tQ\021c\005\002\001\027A\031A\"D\b\016\003\tI!A\004\002\003/\035+g.\032:jG>\023H-\032:fI\016{W\016]1oS>t\007C\001\t\022\031\001!QA\005\001C\002M\021!aQ\"\026\005Q\t\023CA\013\032!\t1r#D\001\007\023\tAbAA\004O_RD\027N\\4\023\007iarE\002\003\034\001\001I\"\001\004\037sK\032Lg.Z7f]Rt\004cA\017\037A5\tA!\003\002 \t\tYAK]1wKJ\034\030M\0317f!\t\001\022\005B\003##\t\0071EA\001Y#\t)B\005\005\002\027K%\021aE\002\002\004\003:L\b\003\002\007)A=I!!\013\002\003C\035+g.\032:jG>\023H-\032:fIR\023\030M^3sg\006\024G.\032+f[Bd\027\r^3\t\013-\002A\021\001\027\002\rqJg.\033;?)\005i\003c\001\007\001\037\031!q\006\001\0011\005M9UM\\3sS\016\034\025M\034\"vS2$gI]8n+\t\tdhE\002/eU\002\"AF\032\n\005Q2!AB!osJ+g\rE\003\rmaj\004)\003\0028\005\ta1)\0318Ck&dGM\022:p[B\022\021h\017\t\004!EQ\004C\001\t<\t%ad&!A\001\002\013\0051EA\002`IE\002\"\001\005 \005\013}r#\031A\022\003\003\005\0032\001E\t>\021!\021eF!A!\002\027\031\025aA8sIB\031A\tT\037\017\005\025SeB\001$J\033\0059%B\001%\t\003\031a$o\\8u}%\tq!\003\002L\r\0059\001/Y2lC\036,\027BA'O\005!y%\017Z3sS:<'BA&\007\021\025Yc\006\"\001Q)\005\tFC\001*U!\r\031f&P\007\002\001!)!i\024a\002\007\")aK\fC\001/\006)\021\r\0359msR\021\001L\030\t\0053rk\004)D\001[\025\tYF!A\004nkR\f'\r\\3\n\005uS&a\002\"vS2$WM\035\005\006?V\003\r\001Y\001\005MJ|W\016\r\002bGB\031\001#\0052\021\005A\031G!\0033_\003\003\005\tQ!\001$\005\ryFE\r\005\006-:\"\tA\032\013\0021\002")
/*    */ public abstract class OrderedTraversableFactory<CC extends Traversable<Object>> extends GenericOrderedCompanion<CC> {
/*    */   public class GenericCanBuildFrom<A> implements CanBuildFrom<CC, A, CC> {
/*    */     private final Ordering<A> ord;
/*    */     
/*    */     public GenericCanBuildFrom(OrderedTraversableFactory $outer, Ordering<A> ord) {}
/*    */     
/*    */     public Builder<A, CC> apply(Traversable from) {
/* 19 */       return ((GenericOrderedTraversableTemplate)from).genericOrderedBuilder(this.ord);
/*    */     }
/*    */     
/*    */     public Builder<A, CC> apply() {
/* 20 */       return scala$collection$generic$OrderedTraversableFactory$GenericCanBuildFrom$$$outer().newBuilder(this.ord);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\OrderedTraversableFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */