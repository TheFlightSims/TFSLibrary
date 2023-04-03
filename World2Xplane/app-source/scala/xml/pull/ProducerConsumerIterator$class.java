/*     */ package scala.xml.pull;
/*     */ 
/*     */ import java.nio.channels.ClosedChannelException;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.concurrent.LinkedBlockingQueue;
/*     */ import scala.Function0;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.Predef$;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public abstract class ProducerConsumerIterator$class {
/*     */   public static void $init$(ProducerConsumerIterator $this) {
/* 115 */     $this.scala$xml$pull$ProducerConsumerIterator$_setter_$MaxQueueSize_$eq(-1);
/*     */   }
/*     */   
/*     */   public static Option interruptibly(ProducerConsumerIterator $this, Function0 body) {
/*     */     try {
/*     */     
/* 117 */     } catch (InterruptedException interruptedException) {
/* 118 */       Thread.currentThread().interrupt();
/*     */     } catch (ClosedChannelException closedChannelException) {}
/* 119 */     return (Option)None$.MODULE$;
/*     */   }
/*     */   
/*     */   public static LinkedBlockingQueue scala$xml$pull$ProducerConsumerIterator$$queue(ProducerConsumerIterator $this) {
/* 123 */     return ($this.MaxQueueSize() < 0) ? new LinkedBlockingQueue() : 
/* 124 */       new LinkedBlockingQueue($this.MaxQueueSize());
/*     */   }
/*     */   
/*     */   private static boolean fillBuffer(ProducerConsumerIterator<T> $this) {
/*     */     Option option;
/* 127 */     $this.scala$xml$pull$ProducerConsumerIterator$$buffer_$eq((option = $this.interruptibly((Function0)new ProducerConsumerIterator$$anonfun$fillBuffer$1($this))).isEmpty() ? $this.EndOfStream() : option.get());
/* 128 */     return isElement($this, $this.scala$xml$pull$ProducerConsumerIterator$$buffer());
/*     */   }
/*     */   
/*     */   private static boolean isElement(ProducerConsumerIterator<Object> $this, Object x) {
/* 130 */     if (x != null) {
/* 130 */       Object object = $this.EndOfStream();
/* 130 */       if ((x == object) ? true : ((x == null) ? false : ((x instanceof Number) ? BoxesRunTime.equalsNumObject((Number)x, object) : ((x instanceof Character) ? BoxesRunTime.equalsCharObject((Character)x, object) : x.equals(object)))));
/* 130 */       return true;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static boolean eos(ProducerConsumerIterator<Object> $this) {
/* 131 */     Object object1 = $this.EndOfStream();
/*     */     Object object;
/* 131 */     return (((object = $this.scala$xml$pull$ProducerConsumerIterator$$buffer()) == object1) ? true : ((object == null) ? false : ((object instanceof Number) ? BoxesRunTime.equalsNumObject((Number)object, object1) : ((object instanceof Character) ? BoxesRunTime.equalsCharObject((Character)object, object1) : object.equals(object1)))));
/*     */   }
/*     */   
/*     */   public static void produce(ProducerConsumerIterator $this, Object x) {
/* 135 */     if (!eos($this))
/* 135 */       $this.interruptibly((Function0)new ProducerConsumerIterator$$anonfun$produce$1($this, (ProducerConsumerIterator<T>)x)); 
/*     */   }
/*     */   
/*     */   public static boolean hasNext(ProducerConsumerIterator $this) {
/* 139 */     return !(eos($this) || ($this.scala$xml$pull$ProducerConsumerIterator$$buffer() == null && !fillBuffer($this)));
/*     */   }
/*     */   
/*     */   public static Object next(ProducerConsumerIterator $this) {
/* 142 */     if (eos($this))
/* 142 */       throw new NoSuchElementException("ProducerConsumerIterator"); 
/* 143 */     if ($this.scala$xml$pull$ProducerConsumerIterator$$buffer() == null)
/* 143 */       fillBuffer($this); 
/* 145 */     return drainBuffer($this);
/*     */   }
/*     */   
/*     */   public static boolean available(ProducerConsumerIterator $this) {
/* 148 */     return (isElement($this, $this.scala$xml$pull$ProducerConsumerIterator$$buffer()) || isElement($this, $this.scala$xml$pull$ProducerConsumerIterator$$queue().peek()));
/*     */   }
/*     */   
/*     */   private static Object drainBuffer(ProducerConsumerIterator $this) {
/* 151 */     Predef$.MODULE$.assert(!eos($this));
/* 152 */     Object res = $this.scala$xml$pull$ProducerConsumerIterator$$buffer();
/* 153 */     $this.scala$xml$pull$ProducerConsumerIterator$$buffer_$eq(null);
/* 154 */     return res;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\pull\ProducerConsumerIterator$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */