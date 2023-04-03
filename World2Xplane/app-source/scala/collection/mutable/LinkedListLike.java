/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.collection.AbstractIterator;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005}baB\001\003!\003\r\t!\003\002\017\031&t7.\0323MSN$H*[6f\025\t\031A!A\004nkR\f'\r\\3\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\026\007))rdE\002\001\027=\001\"\001D\007\016\003\031I!A\004\004\003\r\005s\027PU3g!\021\001\022c\005\020\016\003\tI!A\005\002\003\017M+\027\017T5lKB\021A#\006\007\001\t\0251\002A1\001\030\005\005\t\025C\001\r\034!\ta\021$\003\002\033\r\t9aj\034;iS:<\007C\001\007\035\023\tibAA\002B]f\004\"\001F\020\005\013\001\002!\031A\021\003\tQC\027n]\t\0031\t\0222aI\023)\r\021!\003\001\001\022\003\031q\022XMZ5oK6,g\016\036 \021\007A13#\003\002(\005\t\0311+Z9\021\tA\0011C\b\005\006U\001!\taK\001\007I%t\027\016\036\023\025\0031\002\"\001D\027\n\00592!\001B+oSRD\021\002\r\001A\002\003\007I\021A\031\002\t\025dW-\\\013\002'!I1\007\001a\001\002\004%\t\001N\001\tK2,Wn\030\023fcR\021A&\016\005\bmI\n\t\0211\001\024\003\rAH%\r\005\007q\001\001\013\025B\n\002\013\025dW-\034\021\t\023i\002\001\031!a\001\n\003Y\024\001\0028fqR,\022A\b\005\n{\001\001\r\0211A\005\002y\n\001B\\3yi~#S-\035\013\003Y}BqA\016\037\002\002\003\007a\004\003\004B\001\001\006KAH\001\006]\026DH\017\t\005\006\007\002!\t\005R\001\bSN,U\016\035;z+\005)\005C\001\007G\023\t9eAA\004C_>dW-\0318\t\013%\003A\021\t&\002\r1,gn\032;i+\005Y\005C\001\007M\023\tieAA\002J]RDQa\024\001\005\nA\013q\001\\3oORD\007\007F\002L#JCQ\001\r(A\002yAQa\025(A\002-\0131!Y2dQ\tqU\013\005\002W36\tqK\003\002Y\r\005Q\021M\0348pi\006$\030n\0348\n\005i;&a\002;bS2\024Xm\031\005\0069\002!\t%M\001\005Q\026\fG\rC\003_\001\021\0053(\001\003uC&d\007\"\0021\001\t\003\t\027AB1qa\026tG\r\006\002\037E\")1m\030a\001=\005!A\017[1u\021\025)\007\001\"\001g\003\031Ign]3siR\021Af\032\005\006G\022\004\rA\b\005\006S\002!\tE[\001\005IJ|\007\017\006\002\037W\")A\016\033a\001\027\006\ta\016C\003o\001\021%q.\001\006bi2{7-\031;j_:,\"\001]:\025\005ETHC\001:v!\t!2\017B\003u[\n\007qCA\001U\021\0251X\0161\001x\003\0051\007\003\002\007y=IL!!\037\004\003\023\031+hn\031;j_:\f\004\"\0027n\001\004Y\005\"\002?\001\t\003j\030!B1qa2LHCA\n\021\025a7\0201\001L\021\035\t\t\001\001C\001\003\007\ta!\0369eCR,G#\002\027\002\006\005\035\001\"\0027\000\001\004Y\005BBA\005\002\0071#A\001y\021\035\ti\001\001C\001\003\037\t1aZ3u)\021\t\t\"a\006\021\t1\t\031bE\005\004\003+1!AB(qi&|g\016\003\004m\003\027\001\ra\023\005\b\0037\001A\021IA\017\003!IG/\032:bi>\024XCAA\020!\025\t\t#a\t\024\033\005!\021bAA\023\t\tA\021\n^3sCR|'\017C\004\002*\001!\t%a\013\002\017\031|'/Z1dQV!\021QFA\033)\ra\023q\006\005\bm\006\035\002\031AA\031!\025a\001pEA\032!\r!\022Q\007\003\b\003o\t9C1\001\030\005\005\021\005bBA\036\001\021\005\023QH\001\006G2|g.\032\013\002=\001")
/*     */ public interface LinkedListLike<A, This extends Seq<A> & LinkedListLike<A, This>> extends SeqLike<A, This> {
/*     */   A elem();
/*     */   
/*     */   @TraitSetter
/*     */   void elem_$eq(A paramA);
/*     */   
/*     */   This next();
/*     */   
/*     */   @TraitSetter
/*     */   void next_$eq(This paramThis);
/*     */   
/*     */   boolean isEmpty();
/*     */   
/*     */   int length();
/*     */   
/*     */   A head();
/*     */   
/*     */   This tail();
/*     */   
/*     */   This append(This paramThis);
/*     */   
/*     */   void insert(This paramThis);
/*     */   
/*     */   This drop(int paramInt);
/*     */   
/*     */   A apply(int paramInt);
/*     */   
/*     */   void update(int paramInt, A paramA);
/*     */   
/*     */   Option<A> get(int paramInt);
/*     */   
/*     */   Iterator<A> iterator();
/*     */   
/*     */   <B> void foreach(Function1<A, B> paramFunction1);
/*     */   
/*     */   This clone();
/*     */   
/*     */   public class LinkedListLike$$anonfun$apply$1 extends AbstractFunction1<This, A> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final A apply(Seq x$1) {
/* 157 */       return (A)((LinkedListLike)x$1).elem();
/*     */     }
/*     */     
/*     */     public LinkedListLike$$anonfun$apply$1(LinkedListLike $outer) {}
/*     */   }
/*     */   
/*     */   public class LinkedListLike$$anonfun$update$1 extends AbstractFunction1<This, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Object x$3;
/*     */     
/*     */     public final void apply(Seq x$2) {
/* 158 */       ((LinkedListLike)x$2).elem_$eq(this.x$3);
/*     */     }
/*     */     
/*     */     public LinkedListLike$$anonfun$update$1(LinkedListLike $outer, Object x$3) {}
/*     */   }
/*     */   
/*     */   public class LinkedListLike$$anon$1 extends AbstractIterator<A> {
/*     */     private LinkedListLike<A, This> elems;
/*     */     
/*     */     public LinkedListLike$$anon$1(LinkedListLike<A, This> $outer) {
/* 167 */       this.elems = $outer;
/*     */     }
/*     */     
/*     */     private LinkedListLike<A, This> elems() {
/* 167 */       return this.elems;
/*     */     }
/*     */     
/*     */     private void elems_$eq(LinkedListLike<A, This> x$1) {
/* 167 */       this.elems = x$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 168 */       return elems().nonEmpty();
/*     */     }
/*     */     
/*     */     public A next() {
/* 170 */       Object res = elems().elem();
/* 171 */       elems_$eq((LinkedListLike<A, This>)elems().next());
/* 172 */       return (A)res;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\LinkedListLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */