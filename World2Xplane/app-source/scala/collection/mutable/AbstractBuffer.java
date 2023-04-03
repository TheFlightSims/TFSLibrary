/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.collection.GenIterable;
/*    */ import scala.collection.GenMap;
/*    */ import scala.collection.GenSeq;
/*    */ import scala.collection.GenTraversable;
/*    */ import scala.collection.GenTraversableOnce;
/*    */ import scala.collection.Iterable;
/*    */ import scala.collection.IterableView;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.Traversable;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.TraversableView;
/*    */ import scala.collection.generic.GenericCompanion;
/*    */ import scala.collection.generic.Growable;
/*    */ import scala.collection.generic.Shrinkable;
/*    */ import scala.collection.generic.Subtractable;
/*    */ import scala.collection.script.Message;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\t2a!\001\002\002\002\031A!AD!cgR\024\030m\031;Ck\0324WM\035\006\003\007\021\tq!\\;uC\ndWM\003\002\006\r\005Q1m\0347mK\016$\030n\0348\013\003\035\tQa]2bY\006,\"!\003\t\024\007\001Q1\004E\002\f\0319i\021AA\005\003\033\t\0211\"\0212tiJ\f7\r^*fcB\021q\002\005\007\001\t\025\t\002A1\001\024\005\005\t5\001A\t\003)a\001\"!\006\f\016\003\031I!a\006\004\003\0179{G\017[5oOB\021Q#G\005\0035\031\0211!\0218z!\rYADD\005\003;\t\021aAQ;gM\026\024\b\"B\020\001\t\003\001\023A\002\037j]&$h\bF\001\"!\rY\001A\004")
/*    */ public abstract class AbstractBuffer<A> extends AbstractSeq<A> implements Buffer<A> {
/*    */   public GenericCompanion<Buffer> companion() {
/* 48 */     return Buffer$class.companion(this);
/*    */   }
/*    */   
/*    */   public void remove(int n, int count) {
/* 48 */     BufferLike$class.remove(this, n, count);
/*    */   }
/*    */   
/*    */   public BufferLike<A, Buffer<A>> $minus$eq(Object x) {
/* 48 */     return BufferLike$class.$minus$eq(this, x);
/*    */   }
/*    */   
/*    */   public BufferLike<A, Buffer<A>> $plus$plus$eq$colon(TraversableOnce xs) {
/* 48 */     return BufferLike$class.$plus$plus$eq$colon(this, xs);
/*    */   }
/*    */   
/*    */   public void append(Seq elems) {
/* 48 */     BufferLike$class.append(this, elems);
/*    */   }
/*    */   
/*    */   public void appendAll(TraversableOnce xs) {
/* 48 */     BufferLike$class.appendAll(this, xs);
/*    */   }
/*    */   
/*    */   public void prepend(Seq elems) {
/* 48 */     BufferLike$class.prepend(this, elems);
/*    */   }
/*    */   
/*    */   public void prependAll(TraversableOnce xs) {
/* 48 */     BufferLike$class.prependAll(this, xs);
/*    */   }
/*    */   
/*    */   public void insert(int n, Seq elems) {
/* 48 */     BufferLike$class.insert(this, n, elems);
/*    */   }
/*    */   
/*    */   public void trimStart(int n) {
/* 48 */     BufferLike$class.trimStart(this, n);
/*    */   }
/*    */   
/*    */   public void trimEnd(int n) {
/* 48 */     BufferLike$class.trimEnd(this, n);
/*    */   }
/*    */   
/*    */   public void $less$less(Message cmd) {
/* 48 */     BufferLike$class.$less$less(this, cmd);
/*    */   }
/*    */   
/*    */   public String stringPrefix() {
/* 48 */     return BufferLike$class.stringPrefix(this);
/*    */   }
/*    */   
/*    */   public Seq<A> readOnly() {
/* 48 */     return BufferLike$class.readOnly(this);
/*    */   }
/*    */   
/*    */   public Buffer<A> $plus$plus(GenTraversableOnce xs) {
/* 48 */     return BufferLike$class.$plus$plus(this, xs);
/*    */   }
/*    */   
/*    */   public Buffer<A> $minus(Object elem) {
/* 48 */     return BufferLike$class.$minus(this, elem);
/*    */   }
/*    */   
/*    */   public Buffer<A> $minus(Object elem1, Object elem2, Seq elems) {
/* 48 */     return BufferLike$class.$minus(this, elem1, elem2, elems);
/*    */   }
/*    */   
/*    */   public Buffer<A> $minus$minus(GenTraversableOnce xs) {
/* 48 */     return BufferLike$class.$minus$minus(this, xs);
/*    */   }
/*    */   
/*    */   public Buffer<A> clone() {
/* 48 */     return BufferLike$class.clone(this);
/*    */   }
/*    */   
/*    */   public Shrinkable<A> $minus$eq(Object elem1, Object elem2, Seq elems) {
/* 48 */     return Shrinkable.class.$minus$eq(this, elem1, elem2, elems);
/*    */   }
/*    */   
/*    */   public Shrinkable<A> $minus$minus$eq(TraversableOnce xs) {
/* 48 */     return Shrinkable.class.$minus$minus$eq(this, xs);
/*    */   }
/*    */   
/*    */   public Growable<A> $plus$eq(Object elem1, Object elem2, Seq elems) {
/* 48 */     return Growable.class.$plus$eq(this, elem1, elem2, elems);
/*    */   }
/*    */   
/*    */   public Growable<A> $plus$plus$eq(TraversableOnce xs) {
/* 48 */     return Growable.class.$plus$plus$eq(this, xs);
/*    */   }
/*    */   
/*    */   public AbstractBuffer() {
/* 48 */     Growable.class.$init$(this);
/* 48 */     Shrinkable.class.$init$(this);
/* 48 */     Subtractable.class.$init$(this);
/* 48 */     BufferLike$class.$init$(this);
/* 48 */     Buffer$class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\AbstractBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */