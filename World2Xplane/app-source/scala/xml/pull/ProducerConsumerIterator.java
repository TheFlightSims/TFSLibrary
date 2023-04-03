/*     */ package scala.xml.pull;
/*     */ 
/*     */ import java.util.concurrent.LinkedBlockingQueue;
/*     */ import scala.Function0;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001U4q!\001\002\021\002\007\005\021B\001\rQe>$WoY3s\007>t7/^7fe&#XM]1u_JT!a\001\003\002\tA,H\016\034\006\003\013\031\t1\001_7m\025\0059\021!B:dC2\f7\001A\013\003\025u\0312\001A\006\020!\taQ\"D\001\007\023\tqaA\001\004B]f\024VM\032\t\004!aYbBA\t\027\035\t\021R#D\001\024\025\t!\002\"\001\004=e>|GOP\005\002\017%\021qCB\001\ba\006\0347.Y4f\023\tI\"D\001\005Ji\026\024\030\r^8s\025\t9b\001\005\002\035;1\001A!\002\020\001\005\004y\"!\001+\022\005\001\032\003C\001\007\"\023\t\021cA\001\003Ok2d\007C\001\007%\023\t)cAA\002B]fDQa\n\001\005\002!\na\001J5oSR$C#A\025\021\0051Q\023BA\026\007\005\021)f.\033;\t\0175\002!\031!D\001]\005YQI\0343PMN#(/Z1n+\005Y\002b\002\031\001\005\004%\t!M\001\r\033\006D\030+^3vKNK'0Z\013\002eA\021AbM\005\003i\031\0211!\0238u\021\0311\004\001)A\005e\005iQ*\031=Rk\026,XmU5{K\002BQ\001\017\001\005\002e\nQ\"\0338uKJ\024X\017\035;jE2LXC\001\036@)\tYD\tE\002\ryyJ!!\020\004\003\r=\003H/[8o!\tar\bB\003\037o\t\007\001)\005\002BGA\021ABQ\005\003\007\032\021qAT8uQ&tw\r\003\004Fo\021\005\rAR\001\005E>$\027\020E\002\r\017zJ!\001\023\004\003\021q\022\027P\\1nKzB\001B\023\001\t\006\004&IaS\001\006cV,W/Z\013\002\031B\031Q\nV\016\016\0039S!a\024)\002\025\r|gnY;se\026tGO\003\002R%\006!Q\017^5m\025\005\031\026\001\0026bm\006L!!\026(\003'1Kgn[3e\0052|7m[5oOF+X-^3\t\021]\003\001\022!Q!\n1\013a!];fk\026\004\003\"C-\001\001\004\005\t\025)\003\034\003\031\021WO\0324fe\")1\f\001C\0059\006Qa-\0337m\005V4g-\032:\025\003u\003\"\001\0040\n\005}3!a\002\"p_2,\027M\034\005\006C\002!IAY\001\nSN,E.Z7f]R$\"!X2\t\013\021\004\007\031A\016\002\003aDQA\032\001\005\nq\0131!Z8t\021\025A\007\001\"\001j\003\035\001(o\0343vG\026$\"!\0136\t\013\021<\007\031A\016\t\0131\004A\021A7\002\017!\f7OT3yiV\tQ\fC\003p\001\021\005\001/\001\003oKb$H#A\016\t\013I\004A\021\001/\002\023\0054\030-\0337bE2,\007\"\002;\001\t\023\001\030a\0033sC&t')\0364gKJ\004")
/*     */ public interface ProducerConsumerIterator<T> extends Iterator<T> {
/*     */   void scala$xml$pull$ProducerConsumerIterator$_setter_$MaxQueueSize_$eq(int paramInt);
/*     */   
/*     */   Object scala$xml$pull$ProducerConsumerIterator$$buffer();
/*     */   
/*     */   void scala$xml$pull$ProducerConsumerIterator$$buffer_$eq(Object paramObject);
/*     */   
/*     */   T EndOfStream();
/*     */   
/*     */   int MaxQueueSize();
/*     */   
/*     */   <T> Option<T> interruptibly(Function0<T> paramFunction0);
/*     */   
/*     */   LinkedBlockingQueue<T> scala$xml$pull$ProducerConsumerIterator$$queue();
/*     */   
/*     */   void produce(T paramT);
/*     */   
/*     */   boolean hasNext();
/*     */   
/*     */   T next();
/*     */   
/*     */   boolean available();
/*     */   
/*     */   public class ProducerConsumerIterator$$anonfun$fillBuffer$1 extends AbstractFunction0<T> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final T apply() {
/* 127 */       return this.$outer.scala$xml$pull$ProducerConsumerIterator$$queue().take();
/*     */     }
/*     */     
/*     */     public ProducerConsumerIterator$$anonfun$fillBuffer$1(ProducerConsumerIterator $outer) {}
/*     */   }
/*     */   
/*     */   public class ProducerConsumerIterator$$anonfun$produce$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Object x$1;
/*     */     
/*     */     public final void apply() {
/* 135 */       this.$outer.scala$xml$pull$ProducerConsumerIterator$$queue().put(this.x$1);
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/* 135 */       this.$outer.scala$xml$pull$ProducerConsumerIterator$$queue().put(this.x$1);
/*     */     }
/*     */     
/*     */     public ProducerConsumerIterator$$anonfun$produce$1(ProducerConsumerIterator $outer, Object x$1) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\pull\ProducerConsumerIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */