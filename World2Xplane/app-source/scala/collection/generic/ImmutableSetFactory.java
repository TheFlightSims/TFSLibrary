/*    */ package scala.collection.generic;
/*    */ 
/*    */ import scala.collection.Set;
/*    */ import scala.collection.immutable.Set;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.collection.mutable.SetBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0013Q!\001\002\002\002%\0211#S7nkR\f'\r\\3TKR4\025m\031;pefT!a\001\003\002\017\035,g.\032:jG*\021QAB\001\013G>dG.Z2uS>t'\"A\004\002\013M\034\027\r\\1\004\001U\021!\"E\n\003\001-\0012\001D\007\020\033\005\021\021B\001\b\003\005)\031V\r\036$bGR|'/\037\t\003!Ea\001\001B\003\023\001\t\0071C\001\002D\007V\021AcI\t\003+e\001\"AF\f\016\003\031I!\001\007\004\003\0179{G\017[5oOJ\031!\004H\025\007\tm\001\001!\007\002\ryI,g-\0338f[\026tGO\020\t\004;\001\022S\"\001\020\013\005}!\021!C5n[V$\030M\0317f\023\t\tcDA\002TKR\004\"\001E\022\005\013\021\n\"\031A\023\003\003a\013\"!\006\024\021\005Y9\023B\001\025\007\005\r\te.\037\t\005U-\022S&D\001\005\023\taCAA\004TKRd\025n[3\021\007A\t\"\005C\0030\001\021\005\001'\001\004=S:LGO\020\013\002cA\031A\002A\b\t\013M\002A\021\001\033\002\0259,wOQ;jY\022,'/\006\0026{U\ta\007\005\0038uqzT\"\001\035\013\005e\"\021aB7vi\006\024G.Z\005\003wa\022qAQ;jY\022,'\017\005\002\021{\021)aH\rb\001K\t\t\021\tE\002\021#q\002")
/*    */ public abstract class ImmutableSetFactory<CC extends Set<Object>> extends SetFactory<CC> {
/*    */   public <A> Builder<A, CC> newBuilder() {
/* 18 */     return (Builder<A, CC>)new SetBuilder((Set)empty());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\ImmutableSetFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */