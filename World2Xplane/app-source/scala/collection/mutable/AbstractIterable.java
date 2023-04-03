/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.collection.AbstractIterable;
/*    */ import scala.collection.GenIterable;
/*    */ import scala.collection.GenMap;
/*    */ import scala.collection.GenTraversable;
/*    */ import scala.collection.Iterable;
/*    */ import scala.collection.Traversable;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.TraversableView;
/*    */ import scala.collection.generic.GenericCompanion;
/*    */ import scala.collection.parallel.Combiner;
/*    */ import scala.collection.parallel.mutable.ParIterable;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\t2a!\001\002\002\002\031A!\001E!cgR\024\030m\031;Ji\026\024\030M\0317f\025\t\031A!A\004nkR\f'\r\\3\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027-\006\002\n\037M\031\001A\003\016\021\007-aQ\"D\001\005\023\t\tA\001\005\002\017\0371\001A!\002\t\001\005\004\021\"!A!\004\001E\0211c\006\t\003)Ui\021AB\005\003-\031\021qAT8uQ&tw\r\005\002\0251%\021\021D\002\002\004\003:L\bcA\016\035\0335\t!!\003\002\036\005\tA\021\n^3sC\ndW\rC\003 \001\021\005\001%\001\004=S:LGO\020\013\002CA\0311\004A\007")
/*    */ public abstract class AbstractIterable<A> extends AbstractIterable<A> implements Iterable<A> {
/*    */   public GenericCompanion<Iterable> companion() {
/* 40 */     return Iterable$class.companion(this);
/*    */   }
/*    */   
/*    */   public Combiner<A, ParIterable<A>> parCombiner() {
/* 40 */     return Iterable$class.parCombiner(this);
/*    */   }
/*    */   
/*    */   public Iterable<A> seq() {
/* 40 */     return Iterable$class.seq(this);
/*    */   }
/*    */   
/*    */   public AbstractIterable() {
/* 40 */     Traversable$class.$init$(this);
/* 40 */     Iterable$class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\AbstractIterable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */