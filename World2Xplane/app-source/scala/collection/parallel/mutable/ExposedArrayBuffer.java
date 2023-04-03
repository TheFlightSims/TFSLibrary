/*    */ package scala.collection.parallel.mutable;
/*    */ 
/*    */ import scala.Array$;
/*    */ import scala.collection.generic.Sizing;
/*    */ import scala.collection.mutable.ArrayBuffer;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\t3Q!\001\002\001\005)\021!#\022=q_N,G-\021:sCf\024UO\0324fe*\0211\001B\001\b[V$\030M\0317f\025\t)a!\001\005qCJ\fG\016\\3m\025\t9\001\"\001\006d_2dWm\031;j_:T\021!C\001\006g\016\fG.Y\013\003\027M\0312\001\001\007\037!\riq\"E\007\002\035)\0211AB\005\003!9\0211\"\021:sCf\024UO\0324feB\021!c\005\007\001\t\025!\002A1\001\027\005\005!6\001A\t\003/m\001\"\001G\r\016\003!I!A\007\005\003\0179{G\017[5oOB\021\001\004H\005\003;!\0211!\0218z!\ty\"%D\001!\025\t\tc!A\004hK:,'/[2\n\005\r\002#AB*ju&tw\rC\003&\001\021\005a%\001\004=S:LGO\020\013\002OA\031\001\006A\t\016\003\tAQA\013\001\005\002-\nQ\"\0338uKJt\027\r\\!se\006LX#\001\027\021\007ais&\003\002/\021\t)\021I\035:bsB\021\001\004M\005\003c!\021a!\0218z%\0264\007\"B\032\001\t\003!\024aD:fi&sG/\032:oC2\034\026N_3\025\005UB\004C\001\r7\023\t9\004B\001\003V]&$\b\"B\0353\001\004Q\024!A:\021\005aY\024B\001\037\t\005\rIe\016\036\005\006}\001!\teP\001\tg&TX\rS5oiR\021Q\007\021\005\006\003v\002\rAO\001\004Y\026t\007")
/*    */ public class ExposedArrayBuffer<T> extends ArrayBuffer<T> implements Sizing {
/*    */   public Object[] internalArray() {
/* 60 */     return array();
/*    */   }
/*    */   
/*    */   public void setInternalSize(int s) {
/* 61 */     size0_$eq(s);
/*    */   }
/*    */   
/*    */   public void sizeHint(int len) {
/* 63 */     if (len > size() && len >= 1) {
/* 64 */       Object[] newarray = new Object[len];
/* 65 */       Array$.MODULE$.copy(array(), 0, newarray, 0, size0());
/* 66 */       array_$eq(newarray);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\ExposedArrayBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */