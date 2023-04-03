/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.MatchError;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001=2A!\001\002\001\023\t\t\"+\032<feRL'\r\\3ISN$xN]=\013\005\r!\021aB7vi\006\024G.\032\006\003\013\031\t!bY8mY\026\034G/[8o\025\0059\021!B:dC2\f7\001A\013\004\025Ea2\003\002\001\f1\t\002B\001D\007\02075\t!!\003\002\017\005\t9\001*[:u_JL\bC\001\t\022\031\001!QA\005\001C\002M\0211!\022<u#\t!\002\004\005\002\026-5\ta!\003\002\030\r\t9aj\034;iS:<\007C\001\007\032\023\tQ\"A\001\005V]\022|\027M\0317f!\t\001B\004B\003\036\001\t\007aDA\002Qk\n\f\"\001F\020\021\005U\001\023BA\021\007\005\r\te.\037\t\003+\rJ!\001\n\004\003\031M+'/[1mSj\f'\r\\3\t\013\031\002A\021A\024\002\rqJg.\033;?)\005A\003\003\002\007\001\037mAQA\013\001\005\002-\nA!\0368e_R\tA\006\005\002\026[%\021aF\002\002\005+:LG\017")
/*    */ public class RevertibleHistory<Evt extends Undoable, Pub> extends History<Evt, Pub> implements Undoable, Serializable {
/*    */   public void undo() {
/* 32 */     List old = log().toList().reverse();
/* 33 */     clear();
/* 34 */     List these1 = old;
/*    */     while (true) {
/* 34 */       if (these1.isEmpty())
/*    */         return; 
/* 34 */       Tuple2 tuple2 = (Tuple2)these1.head();
/* 34 */       if (tuple2 != null) {
/* 34 */         ((Undoable)tuple2._2()).undo();
/* 34 */         these1 = (List)these1.tail();
/*    */         continue;
/*    */       } 
/* 34 */       throw new MatchError(tuple2);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\RevertibleHistory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */