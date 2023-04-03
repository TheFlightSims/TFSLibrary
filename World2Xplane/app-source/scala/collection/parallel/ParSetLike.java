/*    */ package scala.collection.parallel;
/*    */ 
/*    */ import scala.Serializable;
/*    */ import scala.collection.GenSet;
/*    */ import scala.collection.GenSetLike;
/*    */ import scala.collection.Set;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\00193q!\001\002\021\002\007\005\021B\001\006QCJ\034V\r\036'jW\026T!a\001\003\002\021A\f'/\0317mK2T!!\002\004\002\025\r|G\016\\3di&|gNC\001\b\003\025\0318-\0317b\007\001)BAC\013 QM!\001aC\b7!\taQ\"D\001\007\023\tqaA\001\004B]f\024VM\032\t\005!E\031b$D\001\005\023\t\021BA\001\006HK:\034V\r\036'jW\026\004\"\001F\013\r\001\021)a\003\001b\001/\t\tA+\005\002\0317A\021A\"G\005\0035\031\021qAT8uQ&tw\r\005\002\r9%\021QD\002\002\004\003:L\bC\001\013 \t\031\001\003\001\"b\001C\t!!+\0329s#\tA\"EE\002$KM2A\001\n\001\001E\taAH]3gS:,W.\0328u}A)a\005A\n\037O5\t!\001\005\002\025Q\0211\021\006\001CC\002)\022!bU3rk\026tG/[1m#\tA2FE\002-[A2A\001\n\001\001WA\031\001CL\n\n\005=\"!aA*fiB!\001#M\n(\023\t\021DAA\004TKRd\025n[3\021\007\031\"4#\003\0026\005\t1\001+\031:TKR\004RAJ\034\024=\035J!\001\017\002\003\037A\013'/\023;fe\006\024G.\032'jW\026DQA\017\001\005\002m\na\001J5oSR$C#\001\037\021\0051i\024B\001 \007\005\021)f.\033;\t\013\001\003a\021A!\002\013\025l\007\017^=\026\003yAQa\021\001\005\002\021\013Q!\0368j_:$\"AH#\t\013\031\023\005\031A$\002\tQD\027\r\036\t\004!!\033\022BA%\005\005\0319UM\\*fi\")1\n\001C\001\031\006!A-\0334g)\tqR\nC\003G\025\002\007q\t")
/*    */ public interface ParSetLike<T, Repr extends ParSetLike<T, Repr, Sequential> & ParSet<T>, Sequential extends Set<T> & scala.collection.SetLike<T, Sequential>> extends GenSetLike<T, Repr>, ParIterableLike<T, Repr, Sequential> {
/*    */   Repr empty();
/*    */   
/*    */   Repr union(GenSet<T> paramGenSet);
/*    */   
/*    */   Repr diff(GenSet<T> paramGenSet);
/*    */   
/*    */   public class ParSetLike$$anonfun$union$1 extends AbstractFunction1<Sequential, Sequential> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final GenSet that$1;
/*    */     
/*    */     public final Sequential apply(Set x$1) {
/* 49 */       return (Sequential)x$1.union(this.that$1);
/*    */     }
/*    */     
/*    */     public ParSetLike$$anonfun$union$1(ParSetLike $outer, GenSet that$1) {}
/*    */   }
/*    */   
/*    */   public class ParSetLike$$anonfun$diff$1 extends AbstractFunction1<Sequential, Sequential> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final GenSet that$2;
/*    */     
/*    */     public final Sequential apply(Set x$2) {
/* 53 */       return (Sequential)x$2.diff(this.that$2);
/*    */     }
/*    */     
/*    */     public ParSetLike$$anonfun$diff$1(ParSetLike $outer, GenSet that$2) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\ParSetLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */