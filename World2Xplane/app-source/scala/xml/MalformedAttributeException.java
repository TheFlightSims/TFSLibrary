/*    */ package scala.xml;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005}a\001B\001\003\001\036\0211$T1mM>\024X.\0323BiR\024\030NY;uK\026C8-\0329uS>t'BA\002\005\003\rAX\016\034\006\002\013\005)1oY1mC\016\0011\003\002\001\t)a\001\"!C\t\017\005)yaBA\006\017\033\005a!BA\007\007\003\031a$o\\8u}%\tQ!\003\002\021\t\0059\001/Y2lC\036,\027B\001\n\024\005A\021VO\034;j[\026,\005pY3qi&|gN\003\002\021\tA\021QCF\007\002\t%\021q\003\002\002\b!J|G-^2u!\t)\022$\003\002\033\t\ta1+\032:jC2L'0\0312mK\"AA\004\001BK\002\023\005Q$A\002ng\036,\022A\b\t\003?\tr!!\006\021\n\005\005\"\021A\002)sK\022,g-\003\002$I\t11\013\036:j]\036T!!\t\003\t\021\031\002!\021#Q\001\ny\tA!\\:hA!)\001\006\001C\001S\0051A(\0338jiz\"\"A\013\027\021\005-\002Q\"\001\002\t\013q9\003\031\001\020\t\0179\002\021\021!C\001_\005!1m\0349z)\tQ\003\007C\004\035[A\005\t\031\001\020\t\017I\002\021\023!C\001g\005q1m\0349zI\021,g-Y;mi\022\nT#\001\033+\005y)4&\001\034\021\005]bT\"\001\035\013\005eR\024!C;oG\",7m[3e\025\tYD!\001\006b]:|G/\031;j_:L!!\020\035\003#Ut7\r[3dW\026$g+\031:jC:\034W\rC\004@\001\005\005I\021\t!\002\033A\024x\016Z;diB\023XMZ5y+\005\t\005C\001\"H\033\005\031%B\001#F\003\021a\027M\\4\013\003\031\013AA[1wC&\0211e\021\005\b\023\002\t\t\021\"\001K\0031\001(o\0343vGR\f%/\033;z+\005Y\005CA\013M\023\tiEAA\002J]RDqa\024\001\002\002\023\005\001+\001\bqe>$Wo\031;FY\026lWM\034;\025\005E#\006CA\013S\023\t\031FAA\002B]fDq!\026(\002\002\003\0071*A\002yIEBqa\026\001\002\002\023\005\003,A\bqe>$Wo\031;Ji\026\024\030\r^8s+\005I\006c\001.^#6\t1L\003\002]\t\005Q1m\0347mK\016$\030n\0348\n\005y[&\001C%uKJ\fGo\034:\t\017\001\004\021\021!C\001C\006A1-\0318FcV\fG\016\006\002cKB\021QcY\005\003I\022\021qAQ8pY\026\fg\016C\004V?\006\005\t\031A)\t\017\035\004\021\021!C!Q\006A\001.Y:i\007>$W\rF\001L\021\035Q\007!!A\005B-\fa!Z9vC2\034HC\0012m\021\035)\026.!AA\002E;qA\034\002\002\002#\005q.A\016NC24wN]7fI\006#HO]5ckR,W\t_2faRLwN\034\t\003WA4q!\001\002\002\002#\005\021oE\002qeb\001Ba\035<\037U5\tAO\003\002v\t\0059!/\0368uS6,\027BA<u\005E\t%m\035;sC\016$h)\0368di&|g.\r\005\006QA$\t!\037\013\002_\"91\020]A\001\n\013b\030\001\003;p'R\024\030N\\4\025\003\005CqA 9\002\002\023\005u0A\003baBd\027\020F\002+\003\003AQ\001H?A\002yA\021\"!\002q\003\003%\t)a\002\002\017Ut\027\r\0359msR!\021\021BA\b!\021)\0221\002\020\n\007\0055AA\001\004PaRLwN\034\005\n\003#\t\031!!AA\002)\n1\001\037\0231\021%\t)\002]A\001\n\023\t9\"A\006sK\006$'+Z:pYZ,GCAA\r!\r\021\0251D\005\004\003;\031%AB(cU\026\034G\017")
/*    */ public class MalformedAttributeException extends RuntimeException implements Product, Serializable {
/*    */   private final String msg;
/*    */   
/*    */   public static <A> Function1<String, A> andThen(Function1<MalformedAttributeException, A> paramFunction1) {
/*    */     return MalformedAttributeException$.MODULE$.andThen(paramFunction1);
/*    */   }
/*    */   
/*    */   public static <A> Function1<A, MalformedAttributeException> compose(Function1<A, String> paramFunction1) {
/*    */     return MalformedAttributeException$.MODULE$.compose(paramFunction1);
/*    */   }
/*    */   
/*    */   public String msg() {
/* 14 */     return this.msg;
/*    */   }
/*    */   
/*    */   public MalformedAttributeException copy(String msg) {
/* 14 */     return new MalformedAttributeException(msg);
/*    */   }
/*    */   
/*    */   public String copy$default$1() {
/* 14 */     return msg();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 14 */     return "MalformedAttributeException";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 14 */     return 1;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 14 */     switch (x$1) {
/*    */       default:
/* 14 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 14 */     return msg();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 14 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 14 */     return x$1 instanceof MalformedAttributeException;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 14 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 75
/*    */     //   5: aload_1
/*    */     //   6: instanceof scala/xml/MalformedAttributeException
/*    */     //   9: ifeq -> 17
/*    */     //   12: iconst_1
/*    */     //   13: istore_2
/*    */     //   14: goto -> 19
/*    */     //   17: iconst_0
/*    */     //   18: istore_2
/*    */     //   19: iload_2
/*    */     //   20: ifeq -> 79
/*    */     //   23: aload_1
/*    */     //   24: checkcast scala/xml/MalformedAttributeException
/*    */     //   27: astore #4
/*    */     //   29: aload_0
/*    */     //   30: invokevirtual msg : ()Ljava/lang/String;
/*    */     //   33: aload #4
/*    */     //   35: invokevirtual msg : ()Ljava/lang/String;
/*    */     //   38: astore_3
/*    */     //   39: dup
/*    */     //   40: ifnonnull -> 51
/*    */     //   43: pop
/*    */     //   44: aload_3
/*    */     //   45: ifnull -> 58
/*    */     //   48: goto -> 71
/*    */     //   51: aload_3
/*    */     //   52: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   55: ifeq -> 71
/*    */     //   58: aload #4
/*    */     //   60: aload_0
/*    */     //   61: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*    */     //   64: ifeq -> 71
/*    */     //   67: iconst_1
/*    */     //   68: goto -> 72
/*    */     //   71: iconst_0
/*    */     //   72: ifeq -> 79
/*    */     //   75: iconst_1
/*    */     //   76: goto -> 80
/*    */     //   79: iconst_0
/*    */     //   80: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #14	-> 0
/*    */     //   #236	-> 12
/*    */     //   #14	-> 19
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	81	0	this	Lscala/xml/MalformedAttributeException;
/*    */     //   0	81	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public MalformedAttributeException(String msg) {
/* 14 */     super(msg);
/* 14 */     Product.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\MalformedAttributeException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */