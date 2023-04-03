/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.LinearSeqOptimized;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005]e\001B\001\003\005&\021A\002J2pY>tGeY8m_:T!a\001\003\002\023%lW.\036;bE2,'BA\003\007\003)\031w\016\0347fGRLwN\034\006\002\017\005)1oY1mC\016\001QC\001\006\022'\021\0011b\007\020\021\0071iq\"D\001\003\023\tq!A\001\003MSN$\bC\001\t\022\031\001!QA\005\001C\002M\021\021AQ\t\003)a\001\"!\006\f\016\003\031I!a\006\004\003\0179{G\017[5oOB\021Q#G\005\0035\031\0211!\0218z!\t)B$\003\002\036\r\t9\001K]8ek\016$\bCA\013 \023\t\001cA\001\007TKJL\027\r\\5{C\ndW\r\003\005#\001\t\005\r\021\"\003$\003\tAG-F\001\020\021!)\003A!a\001\n\0231\023A\0025e?\022*\027\017\006\002(UA\021Q\003K\005\003S\031\021A!\0268ji\"91\006JA\001\002\004y\021a\001=%c!AQ\006\001B\tB\003&q\"A\002iI\002B\021b\f\001\003\002\004%\tA\002\031\002\005QdW#A\006\t\023I\002!\0211A\005\002\031\031\024A\002;m?\022*\027\017\006\002(i!91&MA\001\002\004Y\001\002\003\034\001\005#\005\013\025B\006\002\007Qd\007\005C\0039\001\021\005\021(\001\004=S:LGO\020\013\004umb\004c\001\007\001\037!)!e\016a\001\037!)qf\016a\001\027!)a\b\001C!G\005!\001.Z1e\021\025\001\005\001\"\0211\003\021!\030-\0337\t\013\t\003A\021I\"\002\017%\034X)\0349usV\tA\t\005\002\026\013&\021aI\002\002\b\005>|G.Z1o\021\025A\005\001\"\003J\003)\021X-\0313PE*,7\r\036\013\003O)CQaS$A\0021\013!!\0338\021\0055\023V\"\001(\013\005=\003\026AA5p\025\005\t\026\001\0026bm\006L!a\025(\003#=\023'.Z2u\023:\004X\017^*ue\026\fW\016C\003V\001\021%a+A\006xe&$Xm\0242kK\016$HCA\024X\021\025AF\0131\001Z\003\ryW\017\036\t\003\033jK!a\027(\003%=\023'.Z2u\037V$\b/\036;TiJ,\027-\034\005\b;\002\t\t\021\"\001_\003\021\031w\016]=\026\005}\023Gc\0011dIB\031A\002A1\021\005A\021G!\002\n]\005\004\031\002b\002\022]!\003\005\r!\031\005\b_q\003\n\0211\001f!\raQ\"\031\005\bO\002\t\n\021\"\001i\0039\031w\016]=%I\0264\027-\0367uIE*\"!\033;\026\003)T#aD6,\0031\004\"!\034:\016\0039T!a\0349\002\023Ut7\r[3dW\026$'BA9\007\003)\tgN\\8uCRLwN\\\005\003g:\024\021#\0368dQ\026\0347.\0323WCJL\027M\\2f\t\025\021bM1\001\024\021\0351\b!%A\005\002]\fabY8qs\022\"WMZ1vYR$#'\006\002yuV\t\021P\013\002\fW\022)!#\036b\001'!9A\020AE\001\n\003\031\023\001\0025eIEBqA \001\n\002\023\005\001'\001\003uY\022\n\004\"CA\001\001\005\005I\021IA\002\0035\001(o\0343vGR\004&/\0324jqV\021\021Q\001\t\005\003\017\ti!\004\002\002\n)\031\0211\002)\002\t1\fgnZ\005\005\003\037\tIA\001\004TiJLgn\032\005\n\003'\001\021\021!C\001\003+\tA\002\035:pIV\034G/\021:jif,\"!a\006\021\007U\tI\"C\002\002\034\031\0211!\0238u\021%\ty\002AA\001\n\003\t\t#\001\bqe>$Wo\031;FY\026lWM\034;\025\007a\t\031\003C\005,\003;\t\t\0211\001\002\030!I\021q\005\001\002\002\023\005\023\021F\001\020aJ|G-^2u\023R,'/\031;peV\021\0211\006\t\006\003[\ty\003G\007\002\t%\031\021\021\007\003\003\021%#XM]1u_JDS\001AA\033\003w\0012!FA\034\023\r\tID\002\002\021'\026\024\030.\0317WKJ\034\030n\0348V\023\022s\002B#/d7ZH\036@\\\004\n\003\021\021\021!E\001\003\003\nA\002J2pY>tGeY8m_:\0042\001DA\"\r!\t!!!A\t\002\005\0253#BA\"\003\017r\002cA\013\002J%\031\0211\n\004\003\r\005s\027PU3g\021\035A\0241\tC\001\003\037\"\"!!\021\t\025\005M\0231IA\001\n\013\n)&\001\005u_N#(/\0338h)\t\t)\001\003\006\002Z\005\r\023\021!CA\0037\nQ!\0319qYf,B!!\030\002dQ1\021qLA3\003O\002B\001\004\001\002bA\031\001#a\031\005\rI\t9F1\001\024\021\035\021\023q\013a\001\003CBqaLA,\001\004\tI\007\005\003\r\033\005\005\004BCA7\003\007\n\t\021\"!\002p\0059QO\\1qa2LX\003BA9\003\003#B!a\035\002\006B)Q#!\036\002z%\031\021q\017\004\003\r=\003H/[8o!\035)\0221PA@\003\007K1!! \007\005\031!V\017\0357feA\031\001#!!\005\rI\tYG1\001\024!\021aQ\"a \t\025\005\035\0251NA\001\002\004\tI)A\002yIA\002B\001\004\001\002\000!Q\021QRA\"\003\003%I!a$\002\027I,\027\r\032*fg>dg/\032\013\003\003#\003B!a\002\002\024&!\021QSA\005\005\031y%M[3di\002")
/*     */ public final class $colon$colon<B> extends List<B> implements Product, Serializable {
/*     */   public static final long serialVersionUID = -8476791151983527571L;
/*     */   
/*     */   private B scala$collection$immutable$$colon$colon$$hd;
/*     */   
/*     */   private List<B> tl;
/*     */   
/*     */   public B hd$1() {
/* 356 */     return this.scala$collection$immutable$$colon$colon$$hd;
/*     */   }
/*     */   
/*     */   public List<B> tl$1() {
/* 356 */     return this.tl;
/*     */   }
/*     */   
/*     */   public B scala$collection$immutable$$colon$colon$$hd() {
/* 356 */     return this.scala$collection$immutable$$colon$colon$$hd;
/*     */   }
/*     */   
/*     */   private void scala$collection$immutable$$colon$colon$$hd_$eq(Object x$1) {
/* 356 */     this.scala$collection$immutable$$colon$colon$$hd = (B)x$1;
/*     */   }
/*     */   
/*     */   public List<B> tl() {
/* 356 */     return this.tl;
/*     */   }
/*     */   
/*     */   public void tl_$eq(List<B> x$1) {
/* 356 */     this.tl = x$1;
/*     */   }
/*     */   
/*     */   public <B> $colon$colon<B> copy(Object hd, List<B> tl) {
/* 356 */     return new $colon$colon((B)hd, tl);
/*     */   }
/*     */   
/*     */   public <B> B copy$default$1() {
/* 356 */     return scala$collection$immutable$$colon$colon$$hd();
/*     */   }
/*     */   
/*     */   public <B> List<B> copy$default$2() {
/* 356 */     return tl();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 356 */     return "::";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 356 */     return 2;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 356 */     switch (x$1) {
/*     */       default:
/* 356 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 1:
/*     */       
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 356 */     return hd$1();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 356 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public $colon$colon(Object hd, List<B> tl) {}
/*     */   
/*     */   public B head() {
/* 357 */     return scala$collection$immutable$$colon$colon$$hd();
/*     */   }
/*     */   
/*     */   public List<B> tail() {
/* 358 */     return tl();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 359 */     return false;
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: invokevirtual readObject : ()Ljava/lang/Object;
/*     */     //   4: astore_2
/*     */     //   5: aload_0
/*     */     //   6: aload_2
/*     */     //   7: invokespecial scala$collection$immutable$$colon$colon$$hd_$eq : (Ljava/lang/Object;)V
/*     */     //   10: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   13: aload_0
/*     */     //   14: invokevirtual scala$collection$immutable$$colon$colon$$hd : ()Ljava/lang/Object;
/*     */     //   17: getstatic scala/collection/immutable/ListSerializeEnd$.MODULE$ : Lscala/collection/immutable/ListSerializeEnd$;
/*     */     //   20: astore_3
/*     */     //   21: dup
/*     */     //   22: ifnonnull -> 33
/*     */     //   25: pop
/*     */     //   26: aload_3
/*     */     //   27: ifnull -> 40
/*     */     //   30: goto -> 44
/*     */     //   33: aload_3
/*     */     //   34: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   37: ifeq -> 44
/*     */     //   40: iconst_0
/*     */     //   41: goto -> 45
/*     */     //   44: iconst_1
/*     */     //   45: invokevirtual assert : (Z)V
/*     */     //   48: aload_0
/*     */     //   49: astore #6
/*     */     //   51: aload_1
/*     */     //   52: invokevirtual readObject : ()Ljava/lang/Object;
/*     */     //   55: astore #4
/*     */     //   57: getstatic scala/collection/immutable/ListSerializeEnd$.MODULE$ : Lscala/collection/immutable/ListSerializeEnd$;
/*     */     //   60: dup
/*     */     //   61: ifnonnull -> 73
/*     */     //   64: pop
/*     */     //   65: aload #4
/*     */     //   67: ifnull -> 81
/*     */     //   70: goto -> 90
/*     */     //   73: aload #4
/*     */     //   75: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   78: ifeq -> 90
/*     */     //   81: aload #6
/*     */     //   83: getstatic scala/collection/immutable/Nil$.MODULE$ : Lscala/collection/immutable/Nil$;
/*     */     //   86: invokevirtual tl_$eq : (Lscala/collection/immutable/List;)V
/*     */     //   89: return
/*     */     //   90: new scala/collection/immutable/$colon$colon
/*     */     //   93: dup
/*     */     //   94: aload #4
/*     */     //   96: getstatic scala/collection/immutable/Nil$.MODULE$ : Lscala/collection/immutable/Nil$;
/*     */     //   99: invokespecial <init> : (Ljava/lang/Object;Lscala/collection/immutable/List;)V
/*     */     //   102: astore #5
/*     */     //   104: aload #6
/*     */     //   106: aload #5
/*     */     //   108: invokevirtual tl_$eq : (Lscala/collection/immutable/List;)V
/*     */     //   111: aload #5
/*     */     //   113: astore #6
/*     */     //   115: goto -> 51
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #362	-> 0
/*     */     //   #363	-> 5
/*     */     //   #364	-> 10
/*     */     //   #365	-> 48
/*     */     //   #366	-> 51
/*     */     //   #367	-> 57
/*     */     //   #368	-> 81
/*     */     //   #369	-> 89
/*     */     //   #371	-> 90
/*     */     //   #372	-> 104
/*     */     //   #373	-> 111
/*     */     //   #370	-> 115
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	118	0	this	Lscala/collection/immutable/$colon$colon;
/*     */     //   0	118	1	in	Ljava/io/ObjectInputStream;
/*     */     //   5	113	2	firstObject	Ljava/lang/Object;
/*     */     //   51	67	6	current	Lscala/collection/immutable/$colon$colon;
/*     */     //   104	11	5	list	Lscala/collection/immutable/$colon$colon;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) {
/* 378 */     List xs = this;
/*     */     while (true) {
/* 379 */       if (xs.isEmpty()) {
/* 380 */         out.writeObject(ListSerializeEnd$.MODULE$);
/*     */         return;
/*     */       } 
/*     */       out.writeObject(xs.head());
/*     */       xs = (List)xs.tail();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\$colon$colon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */