/*    */ package scala.collection.generic;
/*    */ 
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.collection.mutable.GrowingBuilder;
/*    */ import scala.collection.mutable.SortedSet;
/*    */ import scala.math.Ordering;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001A3Q!\001\002\002\002%\021q#T;uC\ndWmU8si\026$7+\032;GC\016$xN]=\013\005\r!\021aB4f]\026\024\030n\031\006\003\013\031\t!bY8mY\026\034G/[8o\025\0059\021!B:dC2\f7\001A\013\003\025E\031\"\001A\006\021\0071iq\"D\001\003\023\tq!A\001\tT_J$X\rZ*fi\032\0137\r^8ssB\021\001#\005\007\001\t\025\021\002A1\001\024\005\t\0315)\006\002\025GE\021Q#\007\t\003-]i\021AB\005\0031\031\021qAT8uQ&twME\003\0339%r\023G\002\003\034\001\001I\"\001\004\037sK\032Lg.Z7f]Rt\004cA\017!E5\taD\003\002 \t\0059Q.\036;bE2,\027BA\021\037\005%\031vN\035;fIN+G\017\005\002\021G\021)A%\005b\001K\t\t\021)\005\002\026MA\021acJ\005\003Q\031\0211!\0218z!\021Q3FI\027\016\003\021I!\001\f\003\003\033M{'\017^3e'\026$H*[6f!\r\001\022C\t\t\004;=\022\023B\001\031\037\005\r\031V\r\036\t\005;I\022S&\003\0024=\t91+\032;MS.,\007\"B\033\001\t\0031\024A\002\037j]&$h\bF\0018!\ra\001a\004\005\006s\001!\tEO\001\013]\026<()^5mI\026\024XCA\036A)\ta$\t\005\003\036{}\n\025B\001 \037\005\035\021U/\0337eKJ\004\"\001\005!\005\013\021B$\031A\023\021\007A\tr\bC\003Dq\001\017A)A\002pe\022\0042!R'@\035\t15J\004\002H\0256\t\001J\003\002J\021\0051AH]8pizJ\021aB\005\003\031\032\tq\001]1dW\006<W-\003\002O\037\nAqJ\0353fe&twM\003\002M\r\001")
/*    */ public abstract class MutableSortedSetFactory<CC extends SortedSet<Object>> extends SortedSetFactory<CC> {
/*    */   public <A> Builder<A, CC> newBuilder(Ordering ord) {
/* 32 */     return (Builder<A, CC>)new GrowingBuilder((Growable)empty(ord));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\MutableSortedSetFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */