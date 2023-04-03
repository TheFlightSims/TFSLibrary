/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Serializable;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.TraitSetter;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\00153A!\001\002\003\023\tYA*\0338lK\022,e\016\036:z\025\t\031A!A\004nkR\f'\r\\3\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\026\007))\002e\005\003\001\027=\021\003C\001\007\016\033\0051\021B\001\b\007\005\031\te.\037*fMB!\001#E\n\037\033\005\021\021B\001\n\003\005%A\025m\0355F]R\024\030\020\005\002\025+1\001A!\002\f\001\005\0049\"!A!\022\005aY\002C\001\007\032\023\tQbAA\004O_RD\027N\\4\021\0051a\022BA\017\007\005\r\te.\037\t\005!\001\031r\004\005\002\025A\021)\021\005\001b\001/\t\t!\t\005\002\rG%\021AE\002\002\r'\026\024\030.\0317ju\006\024G.\032\005\tM\001\021)\031!C\001O\005\0311.Z=\026\003MA\001\"\013\001\003\002\003\006IaE\001\005W\026L\b\005\003\005,\001\t\005\r\021\"\001-\003\0251\030\r\\;f+\005y\002\002\003\030\001\005\003\007I\021A\030\002\023Y\fG.^3`I\025\fHC\001\0314!\ta\021'\003\0023\r\t!QK\\5u\021\035!T&!AA\002}\t1\001\037\0232\021!1\004A!A!B\023y\022A\002<bYV,\007\005C\0039\001\021\005\021(\001\004=S:LGO\020\013\004=iZ\004\"\002\0248\001\004\031\002\"B\0268\001\004y\002bB\037\001\001\004%\tAP\001\bK\006\024H.[3s+\005q\002b\002!\001\001\004%\t!Q\001\fK\006\024H.[3s?\022*\027\017\006\0021\005\"9AgPA\001\002\004q\002B\002#\001A\003&a$\001\005fCJd\027.\032:!\021\0351\005\0011A\005\002y\nQ\001\\1uKJDq\001\023\001A\002\023\005\021*A\005mCR,'o\030\023fcR\021\001G\023\005\bi\035\013\t\0211\001\037\021\031a\005\001)Q\005=\0051A.\031;fe\002\002")
/*    */ public final class LinkedEntry<A, B> implements HashEntry<A, LinkedEntry<A, B>>, Serializable {
/*    */   private final A key;
/*    */   
/*    */   private B value;
/*    */   
/*    */   private LinkedEntry<A, B> earlier;
/*    */   
/*    */   private LinkedEntry<A, B> later;
/*    */   
/*    */   private Object next;
/*    */   
/*    */   public LinkedEntry<A, B> next() {
/* 17 */     return (LinkedEntry<A, B>)this.next;
/*    */   }
/*    */   
/*    */   @TraitSetter
/*    */   public void next_$eq(Object x$1) {
/* 17 */     this.next = x$1;
/*    */   }
/*    */   
/*    */   public A key() {
/* 17 */     return this.key;
/*    */   }
/*    */   
/*    */   public B value() {
/* 17 */     return this.value;
/*    */   }
/*    */   
/*    */   public void value_$eq(Object x$1) {
/* 17 */     this.value = (B)x$1;
/*    */   }
/*    */   
/*    */   public LinkedEntry(Object key, Object value) {
/* 17 */     HashEntry$class.$init$(this);
/* 19 */     this.earlier = null;
/* 20 */     this.later = null;
/*    */   }
/*    */   
/*    */   public LinkedEntry<A, B> earlier() {
/*    */     return this.earlier;
/*    */   }
/*    */   
/*    */   public void earlier_$eq(LinkedEntry<A, B> x$1) {
/*    */     this.earlier = x$1;
/*    */   }
/*    */   
/*    */   public LinkedEntry<A, B> later() {
/* 20 */     return this.later;
/*    */   }
/*    */   
/*    */   public void later_$eq(LinkedEntry<A, B> x$1) {
/* 20 */     this.later = x$1;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\LinkedEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */