/*    */ package scala.concurrent;
/*    */ 
/*    */ import scala.Serializable;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.util.Try;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005MaaB\001\003!\003\r\ta\002\002\b!J|W.[:f\025\t\031A!\001\006d_:\034WO\035:f]RT\021!B\001\006g\016\fG.Y\002\001+\tA!e\005\002\001\023A\021!bC\007\002\t%\021A\002\002\002\007\003:L(+\0324\t\0139\001A\021A\b\002\r\021Jg.\033;%)\005\001\002C\001\006\022\023\t\021BA\001\003V]&$\b\"\002\013\001\t\027)\022\001E5oi\026\024h.\0317Fq\026\034W\017^8s+\0051\002CA\f\031\033\005\021\021BA\r\003\005A)\0050Z2vi&|gnQ8oi\026DH\017C\003\034\001\031\005A$\001\004gkR,(/Z\013\002;A\031qC\b\021\n\005}\021!A\002$viV\024X\r\005\002\"E1\001A!B\022\001\005\004!#!\001+\022\005\025B\003C\001\006'\023\t9CAA\004O_RD\027N\\4\021\005)I\023B\001\026\005\005\r\te.\037\005\006Y\0011\t!L\001\fSN\034u.\0349mKR,G-F\001/!\tQq&\003\0021\t\t9!i\\8mK\006t\007\"\002\032\001\t\003\031\024\001C2p[BdW\r^3\025\005Q*T\"\001\001\t\013Y\n\004\031A\034\002\rI,7/\0367u!\rA4\bI\007\002s)\021!\bB\001\005kRLG.\003\002=s\t\031AK]=\t\013y\002a\021A \002\027Q\024\030pQ8na2,G/\032\013\003]\001CQAN\037A\002]BQA\021\001\005\006\r\013AbY8na2,G/Z,ji\"$\"\001\016#\t\013\025\013\005\031A\017\002\013=$\b.\032:\t\013\035\003AQ\001%\002\037Q\024\030pQ8na2,G/Z,ji\"$\"\001N%\t\013\0253\005\031A\017\t\013-\003A\021\001'\002\017M,8mY3tgR\021A'\024\005\006\035*\003\r\001I\001\002m\")\001\013\001C\001#\006QAO]=Tk\016\034Wm]:\025\0059\022\006\"B*P\001\004\001\023!\002<bYV,\007\"B+\001\t\0031\026a\0024bS2,(/\032\013\003i]CQ\001\027+A\002e\013\021\001\036\t\0035\nt!a\0271\017\005q{V\"A/\013\005y3\021A\002\037s_>$h(C\001\006\023\t\tG!A\004qC\016\\\027mZ3\n\005\r$'!\003+ie><\030M\0317f\025\t\tG\001C\003g\001\021\005q-\001\006uef4\025-\0337ve\026$\"A\f5\t\013a+\007\031A-\b\013)\024\001\022A6\002\017A\023x.\\5tKB\021q\003\034\004\006\003\tA\t!\\\n\003Y&AQa\0347\005\002A\fa\001P5oSRtD#A6\t\013IdG\021A:\002\013\005\004\b\017\\=\026\005Q<H#A;\021\007]\001a\017\005\002\"o\022)1%\035b\001I!)\021\020\034C\001u\0061a-Y5mK\022,\"a\037@\025\005q|\bcA\f\001{B\021\021E \003\006Ga\024\r\001\n\005\007\003\003A\b\031A-\002\023\025D8-\0329uS>t\007bBA\003Y\022\005\021qA\001\013gV\0347-Z:tMVdW\003BA\005\003\037!B!a\003\002\022A!q\003AA\007!\r\t\023q\002\003\007G\005\r!\031\001\023\t\017Y\n\031\0011\001\002\016\001")
/*    */ public interface Promise<T> {
/*    */   Future<T> future();
/*    */   
/*    */   boolean isCompleted();
/*    */   
/*    */   Promise<T> complete(Try<T> paramTry);
/*    */   
/*    */   boolean tryComplete(Try<T> paramTry);
/*    */   
/*    */   Promise<T> completeWith(Future<T> paramFuture);
/*    */   
/*    */   Promise<T> tryCompleteWith(Future<T> paramFuture);
/*    */   
/*    */   Promise<T> success(T paramT);
/*    */   
/*    */   boolean trySuccess(T paramT);
/*    */   
/*    */   Promise<T> failure(Throwable paramThrowable);
/*    */   
/*    */   boolean tryFailure(Throwable paramThrowable);
/*    */   
/*    */   public class Promise$$anonfun$completeWith$1 extends AbstractFunction1<Try<T>, Promise<T>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Promise<T> apply(Try<T> x$1) {
/* 70 */       return this.$outer.complete(x$1);
/*    */     }
/*    */     
/*    */     public Promise$$anonfun$completeWith$1(Promise $outer) {}
/*    */   }
/*    */   
/*    */   public class Promise$$anonfun$tryCompleteWith$1 extends AbstractFunction1<Try<T>, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final boolean apply(Try x$2) {
/* 79 */       return this.$outer.tryComplete(x$2);
/*    */     }
/*    */     
/*    */     public Promise$$anonfun$tryCompleteWith$1(Promise $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\Promise.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */