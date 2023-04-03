/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.Nothing$;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005UaaB\001\003!\003\r\t!\003\002\025\t>,(\r\\3MS:\\W\r\032'jgRd\025n[3\013\005\r!\021aB7vi\006\024G.\032\006\003\013\031\t!bY8mY\026\034G/[8o\025\0059\021!B:dC2\f7\001A\013\004\025Uy2\003\002\001\f\037%\002\"\001D\007\016\003\031I!A\004\004\003\r\005s\027PU3g!\021\001\022c\005\020\016\003\tI!A\005\002\003\017M+\027\017T5lKB\021A#\006\007\001\t\0251\002A1\001\030\005\005\t\025C\001\r\034!\ta\021$\003\002\033\r\t9aj\034;iS:<\007C\001\007\035\023\tibAA\002B]f\004\"\001F\020\005\013\001\002!\031A\021\003\tQC\027n]\t\0031\t\0222aI\023)\r\021!\003\001\001\022\003\031q\022XMZ5oK6,g\016\036 \021\007A13#\003\002(\005\t\0311+Z9\021\tA\0011C\b\t\005!)\032b$\003\002,\005\tqA*\0338lK\022d\025n\035;MS.,\007\"B\027\001\t\003q\023A\002\023j]&$H\005F\0010!\ta\001'\003\0022\r\t!QK\\5u\021%\031\004\0011AA\002\023\005A'\001\003qe\0264X#\001\020\t\023Y\002\001\031!a\001\n\0039\024\001\0039sKZ|F%Z9\025\005=B\004bB\0356\003\003\005\rAH\001\004q\022\n\004BB\036\001A\003&a$A\003qe\0264\b\005C\003>\001\021\005c(\001\004baB,g\016\032\013\003=}BQ\001\021\037A\002y\tA\001\0365bi\")!\t\001C!\007\0061\021N\\:feR$\"a\f#\t\013\001\013\005\031\001\020\t\013\031\003A\021\001\030\002\rI,Wn\034<fQ\021)\005J\024)\021\005%cU\"\001&\013\005-3\021AC1o]>$\030\r^5p]&\021QJ\023\002\n[&<'/\031;j_:\f\023aT\001?\t>,(\r\\3!Y&t7.\0323!Y&\034H\017\t8po\002\022X-\\8wKN\004C\017[3!GV\024(/\0328uA9|G-\032\021ge>l\007\005\0365fA1L7\017\036\030\"\003E\013QA\r\030:]ABQa\025\001\005\nQ\013!\"\031;M_\016\fG/[8o+\t)\026\f\006\002WKR\021q\013\031\013\0031n\003\"\001F-\005\013i\023&\031A\f\003\003QCa\001\030*\005\002\004i\026!D8o\037V$xJ\032\"pk:$7\017E\002\r=bK!a\030\004\003\021q\022\027P\\1nKzBQ!\031*A\002\t\f\021A\032\t\005\031\rt\002,\003\002e\r\tIa)\0368di&|g.\r\005\006MJ\003\raZ\001\002]B\021A\002[\005\003S\032\0211!\0238u\021\025Y\007\001\"\003m\003-yW\017^8gE>,h\016Z:\025\005ai\007\"\0024k\001\0049\007\"B8\001\t\003\002\030\001\0023s_B$\"AH9\t\013\031t\007\031A4\t\013M\004A\021\t\033\002\tQ\f\027\016\034\005\006k\002!\tE^\001\006CB\004H.\037\013\003']DQA\032;A\002\035DQ!\037\001\005Bi\fa!\0369eCR,GcA\030|y\")a\r\037a\001O\")Q\020\037a\001'\005\t\001\020\003\004\000\001\021\005\023\021A\001\004O\026$H\003BA\002\003\023\001B\001DA\003'%\031\021q\001\004\003\r=\003H/[8o\021\0251g\0201\001h\0211\ti\001AA\001\002\023%\021qBA\n\0031\031X\017]3sI%t7/\032:u)\ry\023\021\003\005\007\001\006-\001\031\001\020\n\005\tS\003")
/*     */ public interface DoubleLinkedListLike<A, This extends Seq<A> & DoubleLinkedListLike<A, This>> extends SeqLike<A, This>, LinkedListLike<A, This> {
/*     */   void scala$collection$mutable$DoubleLinkedListLike$$super$insert(This paramThis);
/*     */   
/*     */   This prev();
/*     */   
/*     */   @TraitSetter
/*     */   void prev_$eq(This paramThis);
/*     */   
/*     */   This append(This paramThis);
/*     */   
/*     */   void insert(This paramThis);
/*     */   
/*     */   void remove();
/*     */   
/*     */   This drop(int paramInt);
/*     */   
/*     */   This tail();
/*     */   
/*     */   A apply(int paramInt);
/*     */   
/*     */   void update(int paramInt, A paramA);
/*     */   
/*     */   Option<A> get(int paramInt);
/*     */   
/*     */   public class DoubleLinkedListLike$$anonfun$apply$1 extends AbstractFunction1<This, A> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final A apply(Seq x$1) {
/* 115 */       return (A)((LinkedListLike)x$1).elem();
/*     */     }
/*     */     
/*     */     public DoubleLinkedListLike$$anonfun$apply$1(DoubleLinkedListLike $outer) {}
/*     */   }
/*     */   
/*     */   public class DoubleLinkedListLike$$anonfun$apply$2 extends AbstractFunction0<Nothing$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int n$1;
/*     */     
/*     */     public final Nothing$ apply() {
/* 115 */       return DoubleLinkedListLike$class.scala$collection$mutable$DoubleLinkedListLike$$outofbounds(this.$outer, this.n$1);
/*     */     }
/*     */     
/*     */     public DoubleLinkedListLike$$anonfun$apply$2(DoubleLinkedListLike $outer, int n$1) {}
/*     */   }
/*     */   
/*     */   public class DoubleLinkedListLike$$anonfun$update$1 extends AbstractFunction1<This, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Object x$3;
/*     */     
/*     */     public final void apply(Seq x$2) {
/* 116 */       ((LinkedListLike)x$2).elem_$eq(this.x$3);
/*     */     }
/*     */     
/*     */     public DoubleLinkedListLike$$anonfun$update$1(DoubleLinkedListLike $outer, Object x$3) {}
/*     */   }
/*     */   
/*     */   public class DoubleLinkedListLike$$anonfun$update$2 extends AbstractFunction0<Nothing$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int n$2;
/*     */     
/*     */     public final Nothing$ apply() {
/* 116 */       return DoubleLinkedListLike$class.scala$collection$mutable$DoubleLinkedListLike$$outofbounds(this.$outer, this.n$2);
/*     */     }
/*     */     
/*     */     public DoubleLinkedListLike$$anonfun$update$2(DoubleLinkedListLike $outer, int n$2) {}
/*     */   }
/*     */   
/*     */   public class DoubleLinkedListLike$$anonfun$get$1 extends AbstractFunction1<This, Some<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Some<A> apply(Seq x) {
/* 117 */       return new Some(((LinkedListLike)x).elem());
/*     */     }
/*     */     
/*     */     public DoubleLinkedListLike$$anonfun$get$1(DoubleLinkedListLike $outer) {}
/*     */   }
/*     */   
/*     */   public class DoubleLinkedListLike$$anonfun$get$2 extends AbstractFunction0<None$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final None$ apply() {
/* 117 */       return None$.MODULE$;
/*     */     }
/*     */     
/*     */     public DoubleLinkedListLike$$anonfun$get$2(DoubleLinkedListLike $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\DoubleLinkedListLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */