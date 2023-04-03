/*    */ package scala.collection.script;
/*    */ 
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.mutable.ArrayBuffer;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001=2A!\001\002\001\023\t11k\031:jaRT!a\001\003\002\rM\034'/\0339u\025\t)a!\001\006d_2dWm\031;j_:T\021aB\001\006g\016\fG.Y\002\001+\tQqcE\002\001\027E\0012\001D\b\022\033\005i!B\001\b\005\003\035iW\017^1cY\026L!\001E\007\003\027\005\023(/Y=Ck\0324WM\035\t\004%M)R\"\001\002\n\005Q\021!aB'fgN\fw-\032\t\003-]a\001\001B\003\031\001\t\007\021DA\001B#\tQb\004\005\002\03495\ta!\003\002\036\r\t9aj\034;iS:<\007CA\016 \023\t\001cAA\002B]fDQA\t\001\005\002\r\na\001P5oSRtD#\001\023\021\007I\001Q\003C\003'\001\021\005s%\001\005u_N#(/\0338h)\005A\003CA\025-\035\tY\"&\003\002,\r\0051\001K]3eK\032L!!\f\030\003\rM#(/\0338h\025\tYc\001")
/*    */ public class Script<A> extends ArrayBuffer<Message<A>> implements Message<A> {
/*    */   public String toString() {
/* 71 */     String res = "Script(";
/* 72 */     Iterator it = iterator();
/* 73 */     int i = 1;
/* 74 */     while (it.hasNext()) {
/* 75 */       if (i > 1)
/* 76 */         res = (new StringBuilder()).append(res).append(", ").toString(); 
/* 77 */       res = (new StringBuilder()).append(res).append("[").append(BoxesRunTime.boxToInteger(i)).append("] ").append(it.next()).toString();
/* 78 */       i++;
/*    */     } 
/* 80 */     return (new StringBuilder()).append(res).append(")").toString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\script\Script.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */