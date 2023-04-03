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
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005ma\001B\001\003\001\036\021q\001R8d\007>t7O\003\002\004\t\005!A/\032=u\025\005)\021!B:dC2\f7\001A\n\005\001!a\001\003\005\002\n\0255\t!!\003\002\f\005\tAAi\\2v[\026tG\017\005\002\016\0355\tA!\003\002\020\t\t9\001K]8ek\016$\bCA\007\022\023\t\021BA\001\007TKJL\027\r\\5{C\ndW\r\003\005\025\001\tU\r\021\"\001\026\003\tAG-F\001\t\021!9\002A!E!\002\023A\021a\0015eA!A\021\004\001BK\002\023\005Q#\001\002uY\"A1\004\001B\tB\003%\001\"A\002uY\002BQ!\b\001\005\002y\ta\001P5oSRtDcA\020!CA\021\021\002\001\005\006)q\001\r\001\003\005\0063q\001\r\001\003\005\bG\001\t\t\021\"\001%\003\021\031w\016]=\025\007})c\005C\004\025EA\005\t\031\001\005\t\017e\021\003\023!a\001\021!9\001\006AI\001\n\003I\023AD2paf$C-\0324bk2$H%M\013\002U)\022\001bK\026\002YA\021QFM\007\002])\021q\006M\001\nk:\034\007.Z2lK\022T!!\r\003\002\025\005tgn\034;bi&|g.\003\0024]\t\tRO\\2iK\016\\W\r\032,be&\fgnY3\t\017U\002\021\023!C\001S\005q1m\0349zI\021,g-Y;mi\022\022\004bB\034\001\003\003%\t\005O\001\016aJ|G-^2u!J,g-\033=\026\003e\002\"AO \016\003mR!\001P\037\002\t1\fgn\032\006\002}\005!!.\031<b\023\t\0015H\001\004TiJLgn\032\005\b\005\002\t\t\021\"\001D\0031\001(o\0343vGR\f%/\033;z+\005!\005CA\007F\023\t1EAA\002J]RDq\001\023\001\002\002\023\005\021*\001\bqe>$Wo\031;FY\026lWM\034;\025\005)k\005CA\007L\023\taEAA\002B]fDqAT$\002\002\003\007A)A\002yIEBq\001\025\001\002\002\023\005\023+A\bqe>$Wo\031;Ji\026\024\030\r^8s+\005\021\006cA*W\0256\tAK\003\002V\t\005Q1m\0347mK\016$\030n\0348\n\005]#&\001C%uKJ\fGo\034:\t\017e\003\021\021!C\0015\006A1-\0318FcV\fG\016\006\002\\=B\021Q\002X\005\003;\022\021qAQ8pY\026\fg\016C\004O1\006\005\t\031\001&\t\017\001\004\021\021!C!C\006A\001.Y:i\007>$W\rF\001E\021\035\031\007!!A\005B\021\f\001\002^8TiJLgn\032\013\002s!9a\rAA\001\n\003:\027AB3rk\006d7\017\006\002\\Q\"9a*ZA\001\002\004Qua\0026\003\003\003E\ta[\001\b\t>\0347i\0348t!\tIANB\004\002\005\005\005\t\022A7\024\0071t\007\003E\003pe\"Aq$D\001q\025\t\tH!A\004sk:$\030.\\3\n\005M\004(!E!cgR\024\030m\031;Gk:\034G/[8oe!)Q\004\034C\001kR\t1\016C\004dY\006\005IQ\t3\t\017ad\027\021!CAs\006)\021\r\0359msR\031qD_>\t\013Q9\b\031\001\005\t\013e9\b\031\001\005\t\017ud\027\021!CA}\0069QO\\1qa2LHcA@\002\fA)Q\"!\001\002\006%\031\0211\001\003\003\r=\003H/[8o!\025i\021q\001\005\t\023\r\tI\001\002\002\007)V\004H.\032\032\t\021\0055A0!AA\002}\t1\001\037\0231\021%\t\t\002\\A\001\n\023\t\031\"A\006sK\006$'+Z:pYZ,GCAA\013!\rQ\024qC\005\004\0033Y$AB(cU\026\034G\017")
/*    */ public class DocCons extends Document implements Product, Serializable {
/*    */   private final Document hd;
/*    */   
/*    */   private final Document tl;
/*    */   
/*    */   public static Function1<Tuple2<Document, Document>, DocCons> tupled() {
/*    */     return DocCons$.MODULE$.tupled();
/*    */   }
/*    */   
/*    */   public static Function1<Document, Function1<Document, DocCons>> curried() {
/*    */     return DocCons$.MODULE$.curried();
/*    */   }
/*    */   
/*    */   public Document hd() {
/* 18 */     return this.hd;
/*    */   }
/*    */   
/*    */   public Document tl() {
/* 18 */     return this.tl;
/*    */   }
/*    */   
/*    */   public DocCons copy(Document hd, Document tl) {
/* 18 */     return new DocCons(hd, tl);
/*    */   }
/*    */   
/*    */   public Document copy$default$1() {
/* 18 */     return hd();
/*    */   }
/*    */   
/*    */   public Document copy$default$2() {
/* 18 */     return tl();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 18 */     return "DocCons";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 18 */     return 2;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 18 */     switch (x$1) {
/*    */       default:
/* 18 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 1:
/*    */       
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 18 */     return hd();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 18 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 18 */     return x$1 instanceof DocCons;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 18 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 18 */     return ScalaRunTime$.MODULE$._toString(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 107
/*    */     //   5: aload_1
/*    */     //   6: instanceof scala/text/DocCons
/*    */     //   9: ifeq -> 17
/*    */     //   12: iconst_1
/*    */     //   13: istore_2
/*    */     //   14: goto -> 19
/*    */     //   17: iconst_0
/*    */     //   18: istore_2
/*    */     //   19: iload_2
/*    */     //   20: ifeq -> 111
/*    */     //   23: aload_1
/*    */     //   24: checkcast scala/text/DocCons
/*    */     //   27: astore #5
/*    */     //   29: aload_0
/*    */     //   30: invokevirtual hd : ()Lscala/text/Document;
/*    */     //   33: aload #5
/*    */     //   35: invokevirtual hd : ()Lscala/text/Document;
/*    */     //   38: astore_3
/*    */     //   39: dup
/*    */     //   40: ifnonnull -> 51
/*    */     //   43: pop
/*    */     //   44: aload_3
/*    */     //   45: ifnull -> 58
/*    */     //   48: goto -> 103
/*    */     //   51: aload_3
/*    */     //   52: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   55: ifeq -> 103
/*    */     //   58: aload_0
/*    */     //   59: invokevirtual tl : ()Lscala/text/Document;
/*    */     //   62: aload #5
/*    */     //   64: invokevirtual tl : ()Lscala/text/Document;
/*    */     //   67: astore #4
/*    */     //   69: dup
/*    */     //   70: ifnonnull -> 82
/*    */     //   73: pop
/*    */     //   74: aload #4
/*    */     //   76: ifnull -> 90
/*    */     //   79: goto -> 103
/*    */     //   82: aload #4
/*    */     //   84: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   87: ifeq -> 103
/*    */     //   90: aload #5
/*    */     //   92: aload_0
/*    */     //   93: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*    */     //   96: ifeq -> 103
/*    */     //   99: iconst_1
/*    */     //   100: goto -> 104
/*    */     //   103: iconst_0
/*    */     //   104: ifeq -> 111
/*    */     //   107: iconst_1
/*    */     //   108: goto -> 112
/*    */     //   111: iconst_0
/*    */     //   112: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #18	-> 0
/*    */     //   #236	-> 12
/*    */     //   #18	-> 19
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	113	0	this	Lscala/text/DocCons;
/*    */     //   0	113	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public DocCons(Document hd, Document tl) {
/* 18 */     Product.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\text\DocCons.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */