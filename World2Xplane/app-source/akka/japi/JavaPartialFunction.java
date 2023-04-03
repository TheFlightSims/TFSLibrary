/*     */ package akka.japi;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.util.control.NoStackTrace;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005]t!B\001\003\021\0039\021a\005&bm\006\004\026M\035;jC24UO\\2uS>t'BA\002\005\003\021Q\027\r]5\013\003\025\tA!Y6lC\016\001\001C\001\005\n\033\005\021a!\002\006\003\021\003Y!a\005&bm\006\004\026M\035;jC24UO\\2uS>t7CA\005\r!\ti\001#D\001\017\025\005y\021!B:dC2\f\027BA\t\017\005\031\te.\037*fM\")1#\003C\001)\0051A(\0338jiz\"\022a\002\004\006-%\t\tc\006\002\021\035>l\025\r^2i\013b\034W\r\035;j_:\0342!\006\r%!\tI\022E\004\002\033?9\0211DH\007\0029)\021QDB\001\007yI|w\016\036 \n\003=I!\001\t\b\002\017A\f7m[1hK&\021!e\t\002\021%VtG/[7f\013b\034W\r\035;j_:T!\001\t\b\021\005\025RS\"\001\024\013\005\035B\023aB2p]R\024x\016\034\006\003S9\tA!\036;jY&\0211F\n\002\r\035>\034F/Y2l)J\f7-\032\005\006'U!\t!\f\013\002]A\021q&F\007\002\023%\022Q#\r\004\006e%A\ti\r\002\b\035>l\025\r^2i'\021\td\006N\034\021\0055)\024B\001\034\017\005\035\001&o\0343vGR\004\"!\004\035\n\005er!\001D*fe&\fG.\033>bE2,\007\"B\n2\t\003YD#\001\037\021\005=\n\004b\002 2\003\003%\teP\001\016aJ|G-^2u!J,g-\033=\026\003\001\003\"!\021$\016\003\tS!a\021#\002\t1\fgn\032\006\002\013\006!!.\031<b\023\t9%I\001\004TiJLgn\032\005\b\023F\n\t\021\"\001K\0031\001(o\0343vGR\f%/\033;z+\005Y\005CA\007M\023\tieBA\002J]RDqaT\031\002\002\023\005\001+\001\bqe>$Wo\031;FY\026lWM\034;\025\005E#\006CA\007S\023\t\031fBA\002B]fDq!\026(\002\002\003\0071*A\002yIEBqaV\031\002\002\023\005\003,A\bqe>$Wo\031;Ji\026\024\030\r^8s+\005I\006c\001.^#6\t1L\003\002]\035\005Q1m\0347mK\016$\030n\0348\n\005y[&\001C%uKJ\fGo\034:\t\017\001\f\024\021!C\001C\006A1-\0318FcV\fG\016\006\002cKB\021QbY\005\003I:\021qAQ8pY\026\fg\016C\004V?\006\005\t\031A)\t\017\035\f\024\021!C!Q\006A\001.Y:i\007>$W\rF\001L\021\035Q\027'!A\005\n-\f1B]3bIJ+7o\0347wKR\tA\016\005\002B[&\021aN\021\002\007\037\nTWm\031;\b\013AL\001\022\021\037\002\0179{W*\031;dQ\")!/\003C\003g\0069an\\'bi\016DG#\001\r\007\013)\021\021\021A;\026\tY|\030QB\n\003i^\004R\001_>~\003\027i\021!\037\006\003u:\tqA];oi&lW-\003\002}s\n9\022IY:ue\006\034G\017U1si&\fGNR;oGRLwN\034\t\003}~d\001\001B\004\002\002Q\024\r!a\001\003\003\005\0132!!\002R!\ri\021qA\005\004\003\023q!a\002(pi\"Lgn\032\t\004}\0065AaBA\bi\n\007\0211\001\002\002\005\"11\003\036C\001\003'!\"!!\006\021\013!!X0a\003\t\017\005eAO\"\001\002\034\005)\021\r\0359msR1\0211BA\017\003CAq!a\b\002\030\001\007Q0A\001y\021\035\t\031#a\006A\002\t\fq![:DQ\026\0347\016\013\004\002\030\005\035\022Q\b\t\006\033\005%\022QF\005\004\003Wq!A\002;ie><8\017E\002\003_!q!!\r\001\005\004\t\031DA\001U#\021\t)!!\016\021\t\005]\022\021\b\b\003\033}I1!a\017$\005%!\006N]8xC\ndWm\t\002\002@A\031\021$!\021\n\007\005\r3EA\005Fq\016,\007\017^5p]\"9\021q\t;\005\006\005%\023aC5t\t\0264\027N\\3e\003R$2AYA&\021\035\ty\"!\022A\002uDq!!\007u\t\013\ny\005\006\003\002\f\005E\003bBA\020\003\033\002\r! \005\b\003+\"HQIA,\003-\t\007\017\0357z\037J,En]3\026\r\005e\023qMA/)\031\tY&a\031\002nA\031a0!\030\005\021\005}\0231\013b\001\003C\022!AQ\031\022\007\005-\021\013\003\005\002 \005M\003\031AA3!\rq\030q\r\003\t\003S\n\031F1\001\002l\t\021\021)M\t\004\003\013i\b\002CA8\003'\002\r!!\035\002\017\021,g-Y;miB9Q\"a\035\002f\005m\023bAA;\035\tIa)\0368di&|g.\r")
/*     */ public abstract class JavaPartialFunction<A, B> extends AbstractPartialFunction<A, B> {
/*     */   public static RuntimeException noMatch() {
/*     */     return JavaPartialFunction$.MODULE$.noMatch();
/*     */   }
/*     */   
/*     */   public abstract B apply(A paramA, boolean paramBoolean) throws Exception;
/*     */   
/*     */   public static abstract class NoMatchException extends RuntimeException implements NoStackTrace {
/*     */     public Throwable scala$util$control$NoStackTrace$$super$fillInStackTrace() {
/*  74 */       return super.fillInStackTrace();
/*     */     }
/*     */     
/*     */     public Throwable fillInStackTrace() {
/*  74 */       return NoStackTrace.class.fillInStackTrace(this);
/*     */     }
/*     */     
/*     */     public NoMatchException() {
/*  74 */       NoStackTrace.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class NoMatch$ extends NoMatchException implements Product, Serializable {
/*     */     public static final NoMatch$ MODULE$;
/*     */     
/*     */     public String productPrefix() {
/*  75 */       return "NoMatch";
/*     */     }
/*     */     
/*     */     public int productArity() {
/*  75 */       return 0;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/*  75 */       int i = x$1;
/*  75 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/*  75 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/*  75 */       return x$1 instanceof NoMatch$;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  75 */       return -537205660;
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*  75 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public NoMatch$() {
/*  75 */       MODULE$ = this;
/*  75 */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public final boolean isDefinedAt(Object x) {
/*     */     try {
/* 121 */       apply((A)x, true);
/*     */     } finally {
/*     */       boolean bool;
/* 121 */       Exception exception1 = null, exception2 = exception1, exception3 = exception2;
/* 121 */       if (NoMatch$.MODULE$ == null) {
/* 121 */         if (exception3 != null)
/* 121 */           throw exception1; 
/* 121 */       } else if (!NoMatch$.MODULE$.equals(exception3)) {
/* 121 */         throw exception1;
/*     */       } 
/*     */     } 
/* 121 */     return bool;
/*     */   }
/*     */   
/*     */   public final B apply(Object x) {
/*     */     try {
/* 122 */       return apply((A)x, false);
/*     */     } finally {
/* 122 */       Exception exception1 = null, exception2 = exception1, exception3 = exception2;
/* 122 */       if (NoMatch$.MODULE$ == null) {
/* 122 */         if (exception3 != null)
/* 122 */           throw exception1; 
/* 122 */       } else if (!NoMatch$.MODULE$.equals(exception3)) {
/* 122 */         throw exception1;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public final <A1 extends A, B1> B1 applyOrElse(Object x, Function1 default) {
/*     */     try {
/*     */     
/*     */     } finally {
/*     */       Object object;
/* 123 */       Exception exception1 = null, exception2 = exception1, exception3 = exception2;
/* 123 */       if (NoMatch$.MODULE$ == null) {
/* 123 */         if (exception3 != null)
/* 123 */           throw exception1; 
/* 123 */       } else if (!NoMatch$.MODULE$.equals(exception3)) {
/* 123 */         throw exception1;
/*     */       } 
/*     */     } 
/* 123 */     return (B1)object;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\japi\JavaPartialFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */