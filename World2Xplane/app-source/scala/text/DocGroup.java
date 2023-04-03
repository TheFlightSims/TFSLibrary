/*    */ package scala.text;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005\ra\001B\001\003\001\036\021\001\002R8d\017J|W\017\035\006\003\007\021\tA\001^3yi*\tQ!A\003tG\006d\027m\001\001\024\t\001AA\002\005\t\003\023)i\021AA\005\003\027\t\021\001\002R8dk6,g\016\036\t\003\0339i\021\001B\005\003\037\021\021q\001\025:pIV\034G\017\005\002\016#%\021!\003\002\002\r'\026\024\030.\0317ju\006\024G.\032\005\t)\001\021)\032!C\001+\005\031Am\\2\026\003!A\001b\006\001\003\022\003\006I\001C\001\005I>\034\007\005C\003\032\001\021\005!$\001\004=S:LGO\020\013\0037q\001\"!\003\001\t\013QA\002\031\001\005\t\017y\001\021\021!C\001?\005!1m\0349z)\tY\002\005C\004\025;A\005\t\031\001\005\t\017\t\002\021\023!C\001G\005q1m\0349zI\021,g-Y;mi\022\nT#\001\023+\005!)3&\001\024\021\005\035bS\"\001\025\013\005%R\023!C;oG\",7m[3e\025\tYC!\001\006b]:|G/\031;j_:L!!\f\025\003#Ut7\r[3dW\026$g+\031:jC:\034W\rC\0040\001\005\005I\021\t\031\002\033A\024x\016Z;diB\023XMZ5y+\005\t\004C\001\0328\033\005\031$B\001\0336\003\021a\027M\\4\013\003Y\nAA[1wC&\021\001h\r\002\007'R\024\030N\\4\t\017i\002\021\021!C\001w\005a\001O]8ek\016$\030I]5usV\tA\b\005\002\016{%\021a\b\002\002\004\023:$\bb\002!\001\003\003%\t!Q\001\017aJ|G-^2u\0132,W.\0328u)\t\021U\t\005\002\016\007&\021A\t\002\002\004\003:L\bb\002$@\003\003\005\r\001P\001\004q\022\n\004b\002%\001\003\003%\t%S\001\020aJ|G-^2u\023R,'/\031;peV\t!\nE\002L\035\nk\021\001\024\006\003\033\022\t!bY8mY\026\034G/[8o\023\tyEJ\001\005Ji\026\024\030\r^8s\021\035\t\006!!A\005\002I\013\001bY1o\013F,\030\r\034\013\003'Z\003\"!\004+\n\005U#!a\002\"p_2,\027M\034\005\b\rB\013\t\0211\001C\021\035A\006!!A\005Be\013\001\002[1tQ\016{G-\032\013\002y!91\fAA\001\n\003b\026\001\003;p'R\024\030N\\4\025\003EBqA\030\001\002\002\023\005s,\001\004fcV\fGn\035\013\003'\002DqAR/\002\002\003\007!iB\004c\005\005\005\t\022A2\002\021\021{7m\022:pkB\004\"!\0033\007\017\005\021\021\021!E\001KN\031AM\032\t\021\t\035T\007bG\007\002Q*\021\021\016B\001\beVtG/[7f\023\tY\007NA\tBEN$(/Y2u\rVt7\r^5p]FBQ!\0073\005\0025$\022a\031\005\b7\022\f\t\021\"\022]\021\035\001H-!A\005\002F\fQ!\0319qYf$\"a\007:\t\013Qy\007\031\001\005\t\017Q$\027\021!CAk\0069QO\\1qa2LHC\001<z!\riq\017C\005\003q\022\021aa\0249uS>t\007b\002>t\003\003\005\raG\001\004q\022\002\004b\002?e\003\003%I!`\001\fe\026\fGMU3t_24X\rF\001!\t\021t0C\002\002\002M\022aa\0242kK\016$\b")
/*    */ public class DocGroup extends Document implements Product, Serializable {
/*    */   private final Document doc;
/*    */   
/*    */   public static <A> Function1<Document, A> andThen(Function1<DocGroup, A> paramFunction1) {
/*    */     return DocGroup$.MODULE$.andThen(paramFunction1);
/*    */   }
/*    */   
/*    */   public static <A> Function1<A, DocGroup> compose(Function1<A, Document> paramFunction1) {
/*    */     return DocGroup$.MODULE$.compose(paramFunction1);
/*    */   }
/*    */   
/*    */   public Document doc() {
/* 16 */     return this.doc;
/*    */   }
/*    */   
/*    */   public DocGroup copy(Document doc) {
/* 16 */     return new DocGroup(doc);
/*    */   }
/*    */   
/*    */   public Document copy$default$1() {
/* 16 */     return doc();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 16 */     return "DocGroup";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 16 */     return 1;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 16 */     switch (x$1) {
/*    */       default:
/* 16 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 16 */     return doc();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 16 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 16 */     return x$1 instanceof DocGroup;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 16 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 16 */     return ScalaRunTime$.MODULE$._toString(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 75
/*    */     //   5: aload_1
/*    */     //   6: instanceof scala/text/DocGroup
/*    */     //   9: ifeq -> 17
/*    */     //   12: iconst_1
/*    */     //   13: istore_2
/*    */     //   14: goto -> 19
/*    */     //   17: iconst_0
/*    */     //   18: istore_2
/*    */     //   19: iload_2
/*    */     //   20: ifeq -> 79
/*    */     //   23: aload_1
/*    */     //   24: checkcast scala/text/DocGroup
/*    */     //   27: astore #4
/*    */     //   29: aload_0
/*    */     //   30: invokevirtual doc : ()Lscala/text/Document;
/*    */     //   33: aload #4
/*    */     //   35: invokevirtual doc : ()Lscala/text/Document;
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
/*    */     //   #16	-> 0
/*    */     //   #236	-> 12
/*    */     //   #16	-> 19
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	81	0	this	Lscala/text/DocGroup;
/*    */     //   0	81	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public DocGroup(Document doc) {
/* 16 */     Product.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\text\DocGroup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */