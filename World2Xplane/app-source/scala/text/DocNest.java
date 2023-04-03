/*    */ package scala.text;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.Iterator;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ import scala.runtime.Statics;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005}a\001B\001\003\001\036\021q\001R8d\035\026\034HO\003\002\004\t\005!A/\032=u\025\005)\021!B:dC2\f7\001A\n\005\001!a\001\003\005\002\n\0255\t!!\003\002\f\005\tAAi\\2v[\026tG\017\005\002\016\0355\tA!\003\002\020\t\t9\001K]8ek\016$\bCA\007\022\023\t\021BA\001\007TKJL\027\r\\5{C\ndW\r\003\005\025\001\tU\r\021\"\001\026\003\031Ig\016Z3oiV\ta\003\005\002\016/%\021\001\004\002\002\004\023:$\b\002\003\016\001\005#\005\013\021\002\f\002\017%tG-\0328uA!AA\004\001BK\002\023\005Q$A\002e_\016,\022\001\003\005\t?\001\021\t\022)A\005\021\005!Am\\2!\021\025\t\003\001\"\001#\003\031a\024N\\5u}Q\0311\005J\023\021\005%\001\001\"\002\013!\001\0041\002\"\002\017!\001\004A\001bB\024\001\003\003%\t\001K\001\005G>\004\030\020F\002$S)Bq\001\006\024\021\002\003\007a\003C\004\035MA\005\t\031\001\005\t\0171\002\021\023!C\001[\005q1m\0349zI\021,g-Y;mi\022\nT#\001\030+\005Yy3&\001\031\021\005E2T\"\001\032\013\005M\"\024!C;oG\",7m[3e\025\t)D!\001\006b]:|G/\031;j_:L!a\016\032\003#Ut7\r[3dW\026$g+\031:jC:\034W\rC\004:\001E\005I\021\001\036\002\035\r|\007/\037\023eK\032\fW\017\034;%eU\t1H\013\002\t_!9Q\bAA\001\n\003r\024!\0049s_\022,8\r\036)sK\032L\0070F\001@!\t\001U)D\001B\025\t\0215)\001\003mC:<'\"\001#\002\t)\fg/Y\005\003\r\006\023aa\025;sS:<\007b\002%\001\003\003%\t!F\001\raJ|G-^2u\003JLG/\037\005\b\025\002\t\t\021\"\001L\0039\001(o\0343vGR,E.Z7f]R$\"\001T(\021\0055i\025B\001(\005\005\r\te.\037\005\b!&\013\t\0211\001\027\003\rAH%\r\005\b%\002\t\t\021\"\021T\003=\001(o\0343vGRLE/\032:bi>\024X#\001+\021\007UCF*D\001W\025\t9F!\001\006d_2dWm\031;j_:L!!\027,\003\021%#XM]1u_JDqa\027\001\002\002\023\005A,\001\005dC:,\025/^1m)\ti\006\r\005\002\016=&\021q\f\002\002\b\005>|G.Z1o\021\035\001&,!AA\0021CqA\031\001\002\002\023\0053-\001\005iCND7i\0343f)\0051\002bB3\001\003\003%\tEZ\001\ti>\034FO]5oOR\tq\bC\004i\001\005\005I\021I5\002\r\025\fX/\0317t)\ti&\016C\004QO\006\005\t\031\001'\b\0171\024\021\021!E\001[\0069Ai\\2OKN$\bCA\005o\r\035\t!!!A\t\002=\0342A\0349\021!\025\tHO\006\005$\033\005\021(BA:\005\003\035\021XO\034;j[\026L!!\036:\003#\005\0237\017\036:bGR4UO\\2uS>t'\007C\003\"]\022\005q\017F\001n\021\035)g.!A\005F\031DqA\0378\002\002\023\00550A\003baBd\027\020F\002$yvDQ\001F=A\002YAQ\001H=A\002!A\001b 8\002\002\023\005\025\021A\001\bk:\f\007\017\0357z)\021\t\031!a\004\021\0135\t)!!\003\n\007\005\035AA\001\004PaRLwN\034\t\006\033\005-a\003C\005\004\003\033!!A\002+va2,'\007\003\005\002\022y\f\t\0211\001$\003\rAH\005\r\005\n\003+q\027\021!C\005\003/\t1B]3bIJ+7o\0347wKR\021\021\021\004\t\004\001\006m\021bAA\017\003\n1qJ\0316fGR\004")
/*    */ public class DocNest extends Document implements Product, Serializable {
/*    */   private final int indent;
/*    */   
/*    */   private final Document doc;
/*    */   
/*    */   public static Function1<Tuple2<Object, Document>, DocNest> tupled() {
/*    */     return DocNest$.MODULE$.tupled();
/*    */   }
/*    */   
/*    */   public static Function1<Object, Function1<Document, DocNest>> curried() {
/*    */     return DocNest$.MODULE$.curried();
/*    */   }
/*    */   
/*    */   public int indent() {
/* 17 */     return this.indent;
/*    */   }
/*    */   
/*    */   public Document doc() {
/* 17 */     return this.doc;
/*    */   }
/*    */   
/*    */   public DocNest copy(int indent, Document doc) {
/* 17 */     return new DocNest(indent, doc);
/*    */   }
/*    */   
/*    */   public int copy$default$1() {
/* 17 */     return indent();
/*    */   }
/*    */   
/*    */   public Document copy$default$2() {
/* 17 */     return doc();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 17 */     return "DocNest";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 17 */     return 2;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 17 */     switch (x$1) {
/*    */       default:
/* 17 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 1:
/*    */       
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 17 */     return BoxesRunTime.boxToInteger(indent());
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 17 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 17 */     return x$1 instanceof DocNest;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 17 */     return Statics.finalizeHash(Statics.mix(Statics.mix(-889275714, indent()), Statics.anyHash(doc())), 2);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 17 */     return ScalaRunTime$.MODULE$._toString(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 87
/*    */     //   5: aload_1
/*    */     //   6: instanceof scala/text/DocNest
/*    */     //   9: ifeq -> 17
/*    */     //   12: iconst_1
/*    */     //   13: istore_2
/*    */     //   14: goto -> 19
/*    */     //   17: iconst_0
/*    */     //   18: istore_2
/*    */     //   19: iload_2
/*    */     //   20: ifeq -> 91
/*    */     //   23: aload_1
/*    */     //   24: checkcast scala/text/DocNest
/*    */     //   27: astore #4
/*    */     //   29: aload_0
/*    */     //   30: invokevirtual indent : ()I
/*    */     //   33: aload #4
/*    */     //   35: invokevirtual indent : ()I
/*    */     //   38: if_icmpne -> 83
/*    */     //   41: aload_0
/*    */     //   42: invokevirtual doc : ()Lscala/text/Document;
/*    */     //   45: aload #4
/*    */     //   47: invokevirtual doc : ()Lscala/text/Document;
/*    */     //   50: astore_3
/*    */     //   51: dup
/*    */     //   52: ifnonnull -> 63
/*    */     //   55: pop
/*    */     //   56: aload_3
/*    */     //   57: ifnull -> 70
/*    */     //   60: goto -> 83
/*    */     //   63: aload_3
/*    */     //   64: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   67: ifeq -> 83
/*    */     //   70: aload #4
/*    */     //   72: aload_0
/*    */     //   73: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*    */     //   76: ifeq -> 83
/*    */     //   79: iconst_1
/*    */     //   80: goto -> 84
/*    */     //   83: iconst_0
/*    */     //   84: ifeq -> 91
/*    */     //   87: iconst_1
/*    */     //   88: goto -> 92
/*    */     //   91: iconst_0
/*    */     //   92: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #17	-> 0
/*    */     //   #236	-> 12
/*    */     //   #17	-> 19
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	93	0	this	Lscala/text/DocNest;
/*    */     //   0	93	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public DocNest(int indent, Document doc) {
/* 17 */     Product.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\text\DocNest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */