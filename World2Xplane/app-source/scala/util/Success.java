/*     */ package scala.util;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.util.control.NonFatal$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005Mh\001B\001\003\005\036\021qaU;dG\026\0348O\003\002\004\t\005!Q\017^5m\025\005)\021!B:dC2\f7\001A\013\003\021=\031B\001A\005\0329A\031!bC\007\016\003\tI!\001\004\002\003\007Q\023\030\020\005\002\017\0371\001AA\002\t\001\t\013\007\021CA\001U#\t\021b\003\005\002\024)5\tA!\003\002\026\t\t9aj\034;iS:<\007CA\n\030\023\tABAA\002B]f\004\"a\005\016\n\005m!!a\002)s_\022,8\r\036\t\003'uI!A\b\003\003\031M+'/[1mSj\f'\r\\3\t\021\001\002!Q3A\005\002\005\nQA^1mk\026,\022!\004\005\tG\001\021\t\022)A\005\033\0051a/\0317vK\002BQ!\n\001\005\002\031\na\001P5oSRtDCA\024)!\rQ\001!\004\005\006A\021\002\r!\004\005\006U\001!\taK\001\nSN4\025-\0337ve\026,\022\001\f\t\003'5J!A\f\003\003\017\t{w\016\\3b]\")\001\007\001C\001W\005I\021n]*vG\016,7o\035\005\006e\001!\taM\001\fe\026\034wN^3s/&$\b.\006\0025oQ\021QG\017\t\004\025-1\004C\001\b8\t\025A\024G1\001:\005\005)\026CA\007\027\021\025Y\024\0071\001=\003\0051\007\003B\n>UJ!A\020\003\003\037A\013'\017^5bY\032+hn\031;j_:\004\"\001\021%\017\005\0053eB\001\"F\033\005\031%B\001#\007\003\031a$o\\8u}%\tQ!\003\002H\t\0059\001/Y2lC\036,\027BA%K\005%!\006N]8xC\ndWM\003\002H\t!)A\n\001C\001C\005\031q-\032;\t\0139\003A\021A(\002\017\031d\027\r^'baV\021\001k\025\013\003#R\0032AC\006S!\tq1\013B\0039\033\n\007\021\003C\003<\033\002\007Q\013\005\003\024-6\t\026BA,\005\005%1UO\\2uS>t\027\007C\003Z\001\021\005!,A\004gY\006$H/\0328\026\005msFC\001/`!\rQ1\"\030\t\003\035y#Q\001\017-C\002EAQ\001\031-A\004\005\f!!\032<\021\t\t,W\002\030\b\003'\rL!\001\032\003\002\rA\023X\rZ3g\023\t1wM\001\t%Y\026\0348\017J2pY>tG\005\\3tg*\021A\r\002\005\006S\002!\tA[\001\bM>\024X-Y2i+\tY'\017\006\002m_B\0211#\\\005\003]\022\021A!\0268ji\")1\b\033a\001aB!1CV\007r!\tq!\017B\0039Q\n\007\021\003C\003u\001\021\005Q/A\002nCB,\"A^=\025\005]T\bc\001\006\fqB\021a\"\037\003\006qM\024\r!\005\005\006wM\004\ra\037\t\005'Yk\001\020C\003~\001\021\005a0\001\004gS2$XM\035\013\003\023}Dq!!\001}\001\004\t\031!A\001q!\021\031b+\004\027\t\017\005\035\001\001\"\001\002\n\0059!/Z2pm\026\024X\003BA\006\003#!B!!\004\002\024A!!bCA\b!\rq\021\021\003\003\007q\005\025!\031A\035\t\021\005U\021Q\001a\001\003/\tqB]3tGV,W\t_2faRLwN\034\t\006'uz\024q\002\005\b\0037\001A\021AA\017\003\0311\027-\0337fIV\021\021q\004\t\004\025-y\004\"CA\022\001\005\005I\021AA\023\003\021\031w\016]=\026\t\005\035\022Q\006\013\005\003S\ty\003\005\003\013\001\005-\002c\001\b\002.\0211\001#!\tC\002EA\021\002IA\021!\003\005\r!a\013\t\023\005M\002!%A\005\002\005U\022AD2paf$C-\0324bk2$H%M\013\005\003o\ti%\006\002\002:)\032Q\"a\017,\005\005u\002\003BA \003\023j!!!\021\013\t\005\r\023QI\001\nk:\034\007.Z2lK\022T1!a\022\005\003)\tgN\\8uCRLwN\\\005\005\003\027\n\tEA\tv]\016DWmY6fIZ\013'/[1oG\026$a\001EA\031\005\004\t\002\"CA)\001\005\005I\021IA*\0035\001(o\0343vGR\004&/\0324jqV\021\021Q\013\t\005\003/\n\t'\004\002\002Z)!\0211LA/\003\021a\027M\\4\013\005\005}\023\001\0026bm\006LA!a\031\002Z\t11\013\036:j]\036D\021\"a\032\001\003\003%\t!!\033\002\031A\024x\016Z;di\006\023\030\016^=\026\005\005-\004cA\n\002n%\031\021q\016\003\003\007%sG\017C\005\002t\001\t\t\021\"\001\002v\005q\001O]8ek\016$X\t\\3nK:$Hc\001\f\002x!Q\021\021PA9\003\003\005\r!a\033\002\007a$\023\007C\005\002~\001\t\t\021\"\021\002\000\005y\001O]8ek\016$\030\n^3sCR|'/\006\002\002\002B)\0211QAE-5\021\021Q\021\006\004\003\017#\021AC2pY2,7\r^5p]&!\0211RAC\005!IE/\032:bi>\024\b\"CAH\001\005\005I\021AAI\003!\031\027M\\#rk\006dGc\001\027\002\024\"I\021\021PAG\003\003\005\rA\006\005\n\003/\003\021\021!C!\0033\013\001\002[1tQ\016{G-\032\013\003\003WB\021\"!(\001\003\003%\t%a(\002\021Q|7\013\036:j]\036$\"!!\026\t\023\005\r\006!!A\005B\005\025\026AB3rk\006d7\017F\002-\003OC\021\"!\037\002\"\006\005\t\031\001\f\b\023\005-&!!A\t\002\0055\026aB*vG\016,7o\035\t\004\025\005=f\001C\001\003\003\003E\t!!-\024\013\005=\0261\027\017\021\007M\t),C\002\0028\022\021a!\0218z%\0264\007bB\023\0020\022\005\0211\030\013\003\003[C!\"!(\0020\006\005IQIAP\021)\t\t-a,\002\002\023\005\0251Y\001\006CB\004H._\013\005\003\013\fY\r\006\003\002H\0065\007\003\002\006\001\003\023\0042ADAf\t\031\001\022q\030b\001#!9\001%a0A\002\005%\007BCAi\003_\013\t\021\"!\002T\0069QO\\1qa2LX\003BAk\003?$B!a6\002bB)1#!7\002^&\031\0211\034\003\003\r=\003H/[8o!\rq\021q\034\003\007!\005='\031A\t\t\025\005\r\030qZA\001\002\004\t)/A\002yIA\002BA\003\001\002^\"Q\021\021^AX\003\003%I!a;\002\027I,\027\r\032*fg>dg/\032\013\003\003[\004B!a\026\002p&!\021\021_A-\005\031y%M[3di\002")
/*     */ public final class Success<T> extends Try<T> implements Product, Serializable {
/*     */   private final T value;
/*     */   
/*     */   public T value() {
/* 194 */     return this.value;
/*     */   }
/*     */   
/*     */   public <T> Success<T> copy(Object value) {
/* 194 */     return new Success((T)value);
/*     */   }
/*     */   
/*     */   public <T> T copy$default$1() {
/* 194 */     return value();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 194 */     return "Success";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 194 */     return 1;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 194 */     switch (x$1) {
/*     */       default:
/* 194 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 194 */     return value();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 194 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 194 */     return x$1 instanceof Success;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 194 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 194 */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/* 194 */     if (this != x$1) {
/*     */       boolean bool;
/* 194 */       if (x$1 instanceof Success) {
/* 236 */         bool = true;
/*     */       } else {
/* 236 */         bool = false;
/*     */       } 
/*     */       if (bool) {
/*     */         Success<Object> success = (Success)x$1;
/*     */         T t2 = (T)success.value();
/*     */         T t1;
/*     */         if ((((t1 = value()) == t2) ? true : ((t1 == null) ? false : ((t1 instanceof Number) ? BoxesRunTime.equalsNumObject((Number)t1, t2) : ((t1 instanceof Character) ? BoxesRunTime.equalsCharObject((Character)t1, t2) : t1.equals(t2))))));
/*     */       } 
/*     */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Success(Object value) {
/*     */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public boolean isFailure() {
/*     */     return false;
/*     */   }
/*     */   
/*     */   public boolean isSuccess() {
/*     */     return true;
/*     */   }
/*     */   
/*     */   public <U> Try<U> recoverWith(PartialFunction f) {
/*     */     return this;
/*     */   }
/*     */   
/*     */   public T get() {
/*     */     return value();
/*     */   }
/*     */   
/*     */   public <U> Try<U> flatMap(Function1 f) {
/*     */     try {
/*     */     
/*     */     } finally {
/*     */       Exception exception = null;
/*     */       Option option = NonFatal$.MODULE$.unapply(exception);
/*     */     } 
/*     */     return new Failure<U>((Throwable)option.get());
/*     */   }
/*     */   
/*     */   public <U> Try<U> flatten(Predef$.less.colon.less ev) {
/*     */     return (Try<U>)ev.apply(value());
/*     */   }
/*     */   
/*     */   public <U> void foreach(Function1 f) {
/*     */     f.apply(value());
/*     */   }
/*     */   
/*     */   public <U> Try<U> map(Function1 f) {
/*     */     return Try$.MODULE$.apply((Function0<U>)new Success$$anonfun$map$1(this, (Success<T>)f));
/*     */   }
/*     */   
/*     */   public class Success$$anonfun$map$1 extends AbstractFunction0<U> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 f$1;
/*     */     
/*     */     public final U apply() {
/*     */       return (U)this.f$1.apply(this.$outer.value());
/*     */     }
/*     */     
/*     */     public Success$$anonfun$map$1(Success $outer, Function1 f$1) {}
/*     */   }
/*     */   
/*     */   public Try<T> filter(Function1 p) {
/*     */     try {
/*     */     
/*     */     } finally {
/*     */       Exception exception = null;
/*     */       Option option = NonFatal$.MODULE$.unapply(exception);
/*     */     } 
/*     */     return new Failure<T>((Throwable)option.get());
/*     */   }
/*     */   
/*     */   public <U> Try<U> recover(PartialFunction rescueException) {
/*     */     return this;
/*     */   }
/*     */   
/*     */   public Try<Throwable> failed() {
/*     */     return new Failure<Throwable>(new UnsupportedOperationException("Success.failed"));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\Success.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */