/*    */ package scala.xml.dtd;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Predef$;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple3;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.immutable.StringOps;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ import scala.xml.Utility$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005=d\001B\001\003\001&\021q\001R8d)f\004XM\003\002\004\t\005\031A\r\0363\013\005\0251\021a\001=nY*\tq!A\003tG\006d\027m\001\001\024\t\001Qa\"\005\t\003\0271i\021AB\005\003\033\031\021a!\0218z%\0264\007CA\006\020\023\t\001bAA\004Qe>$Wo\031;\021\005-\021\022BA\n\007\0051\031VM]5bY&T\030M\0317f\021!)\002A!f\001\n\0031\022\001\0028b[\026,\022a\006\t\0031mq!aC\r\n\005i1\021A\002)sK\022,g-\003\002\035;\t11\013\036:j]\036T!A\007\004\t\021}\001!\021#Q\001\n]\tQA\\1nK\002B\001\"\t\001\003\026\004%\tAI\001\006Kb$\030\nR\013\002GA\021A%J\007\002\005%\021aE\001\002\013\013b$XM\0358bY&#\005\002\003\025\001\005#\005\013\021B\022\002\r\025DH/\023#!\021!Q\003A!f\001\n\003Y\023!C5oiN+(m]3u+\005a\003cA\0276q9\021af\r\b\003_Ij\021\001\r\006\003c!\ta\001\020:p_Rt\024\"A\004\n\005Q2\021a\0029bG.\fw-Z\005\003m]\0221aU3r\025\t!d\001\005\002%s%\021!H\001\002\005\t\026\034G\016\003\005=\001\tE\t\025!\003-\003)Ig\016^*vEN,G\017\t\005\006}\001!\taP\001\007y%t\027\016\036 \025\t\001\013%i\021\t\003I\001AQ!F\037A\002]AQ!I\037A\002\rBQAK\037A\0021BQ!\022\001\005F\031\013\001\002^8TiJLgn\032\013\002/!9\001\nAA\001\n\003I\025\001B2paf$B\001\021&L\031\"9Qc\022I\001\002\0049\002bB\021H!\003\005\ra\t\005\bU\035\003\n\0211\001-\021\035q\005!%A\005\002=\013abY8qs\022\"WMZ1vYR$\023'F\001QU\t9\022kK\001S!\t\031\006,D\001U\025\t)f+A\005v]\016DWmY6fI*\021qKB\001\013C:tw\016^1uS>t\027BA-U\005E)hn\0315fG.,GMV1sS\006t7-\032\005\b7\002\t\n\021\"\001]\0039\031w\016]=%I\0264\027-\0367uII*\022!\030\026\003GECqa\030\001\022\002\023\005\001-\001\bd_BLH\005Z3gCVdG\017J\032\026\003\005T#\001L)\t\017\r\004\021\021!C!I\006i\001O]8ek\016$\bK]3gSb,\022!\032\t\003M.l\021a\032\006\003Q&\fA\001\\1oO*\t!.\001\003kCZ\f\027B\001\017h\021\035i\007!!A\005\0029\fA\002\035:pIV\034G/\021:jif,\022a\034\t\003\027AL!!\035\004\003\007%sG\017C\004t\001\005\005I\021\001;\002\035A\024x\016Z;di\026cW-\\3oiR\021Q\017\037\t\003\027YL!a\036\004\003\007\005s\027\020C\004ze\006\005\t\031A8\002\007a$\023\007C\004|\001\005\005I\021\t?\002\037A\024x\016Z;di&#XM]1u_J,\022! \t\005}\006\rQ/D\001\000\025\r\t\tAB\001\013G>dG.Z2uS>t\027bAA\003\nA\021\n^3sCR|'\017C\005\002\n\001\t\t\021\"\001\002\f\005A1-\0318FcV\fG\016\006\003\002\016\005M\001cA\006\002\020%\031\021\021\003\004\003\017\t{w\016\\3b]\"A\0210a\002\002\002\003\007Q\017C\005\002\030\001\t\t\021\"\021\002\032\005A\001.Y:i\007>$W\rF\001p\021%\ti\002AA\001\n\003\ny\"\001\004fcV\fGn\035\013\005\003\033\t\t\003\003\005z\0037\t\t\0211\001v\017%\t)CAA\001\022\003\t9#A\004E_\016$\026\020]3\021\007\021\nIC\002\005\002\005\005\005\t\022AA\026'\025\tI#!\f\022!!\ty#!\016\030G1\002UBAA\031\025\r\t\031DB\001\beVtG/[7f\023\021\t9$!\r\003#\005\0237\017\036:bGR4UO\\2uS>t7\007C\004?\003S!\t!a\017\025\005\005\035\002\"C#\002*\005\005IQIA )\005)\007BCA\"\003S\t\t\021\"!\002F\005)\021\r\0359msR9\001)a\022\002J\005-\003BB\013\002B\001\007q\003\003\004\"\003\003\002\ra\t\005\007U\005\005\003\031\001\027\t\025\005=\023\021FA\001\n\003\013\t&A\004v]\006\004\b\017\\=\025\t\005M\023q\f\t\006\027\005U\023\021L\005\004\003/2!AB(qi&|g\016\005\004\f\0037:2\005L\005\004\003;2!A\002+va2,7\007C\005\002b\0055\023\021!a\001\001\006\031\001\020\n\031\t\025\005\025\024\021FA\001\n\023\t9'A\006sK\006$'+Z:pYZ,GCAA5!\r1\0271N\005\004\003[:'AB(cU\026\034G\017")
/*    */ public class DocType implements Product, Serializable {
/*    */   private final String name;
/*    */   
/*    */   private final ExternalID extID;
/*    */   
/*    */   private final Seq<Decl> intSubset;
/*    */   
/*    */   public String name() {
/* 21 */     return this.name;
/*    */   }
/*    */   
/*    */   public ExternalID extID() {
/* 21 */     return this.extID;
/*    */   }
/*    */   
/*    */   public Seq<Decl> intSubset() {
/* 21 */     return this.intSubset;
/*    */   }
/*    */   
/*    */   public DocType copy(String name, ExternalID extID, Seq<Decl> intSubset) {
/* 21 */     return new DocType(name, extID, intSubset);
/*    */   }
/*    */   
/*    */   public String copy$default$1() {
/* 21 */     return name();
/*    */   }
/*    */   
/*    */   public ExternalID copy$default$2() {
/* 21 */     return extID();
/*    */   }
/*    */   
/*    */   public Seq<Decl> copy$default$3() {
/* 21 */     return intSubset();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 21 */     return "DocType";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 21 */     return 3;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 21 */     switch (x$1) {
/*    */       default:
/* 21 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 2:
/*    */       
/*    */       case 1:
/*    */       
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 21 */     return name();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 21 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 21 */     return x$1 instanceof DocType;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 21 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 139
/*    */     //   5: aload_1
/*    */     //   6: instanceof scala/xml/dtd/DocType
/*    */     //   9: ifeq -> 17
/*    */     //   12: iconst_1
/*    */     //   13: istore_2
/*    */     //   14: goto -> 19
/*    */     //   17: iconst_0
/*    */     //   18: istore_2
/*    */     //   19: iload_2
/*    */     //   20: ifeq -> 143
/*    */     //   23: aload_1
/*    */     //   24: checkcast scala/xml/dtd/DocType
/*    */     //   27: astore #6
/*    */     //   29: aload_0
/*    */     //   30: invokevirtual name : ()Ljava/lang/String;
/*    */     //   33: aload #6
/*    */     //   35: invokevirtual name : ()Ljava/lang/String;
/*    */     //   38: astore_3
/*    */     //   39: dup
/*    */     //   40: ifnonnull -> 51
/*    */     //   43: pop
/*    */     //   44: aload_3
/*    */     //   45: ifnull -> 58
/*    */     //   48: goto -> 135
/*    */     //   51: aload_3
/*    */     //   52: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   55: ifeq -> 135
/*    */     //   58: aload_0
/*    */     //   59: invokevirtual extID : ()Lscala/xml/dtd/ExternalID;
/*    */     //   62: aload #6
/*    */     //   64: invokevirtual extID : ()Lscala/xml/dtd/ExternalID;
/*    */     //   67: astore #4
/*    */     //   69: dup
/*    */     //   70: ifnonnull -> 82
/*    */     //   73: pop
/*    */     //   74: aload #4
/*    */     //   76: ifnull -> 90
/*    */     //   79: goto -> 135
/*    */     //   82: aload #4
/*    */     //   84: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   87: ifeq -> 135
/*    */     //   90: aload_0
/*    */     //   91: invokevirtual intSubset : ()Lscala/collection/Seq;
/*    */     //   94: aload #6
/*    */     //   96: invokevirtual intSubset : ()Lscala/collection/Seq;
/*    */     //   99: astore #5
/*    */     //   101: dup
/*    */     //   102: ifnonnull -> 114
/*    */     //   105: pop
/*    */     //   106: aload #5
/*    */     //   108: ifnull -> 122
/*    */     //   111: goto -> 135
/*    */     //   114: aload #5
/*    */     //   116: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   119: ifeq -> 135
/*    */     //   122: aload #6
/*    */     //   124: aload_0
/*    */     //   125: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*    */     //   128: ifeq -> 135
/*    */     //   131: iconst_1
/*    */     //   132: goto -> 136
/*    */     //   135: iconst_0
/*    */     //   136: ifeq -> 143
/*    */     //   139: iconst_1
/*    */     //   140: goto -> 144
/*    */     //   143: iconst_0
/*    */     //   144: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #21	-> 0
/*    */     //   #236	-> 12
/*    */     //   #21	-> 19
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	145	0	this	Lscala/xml/dtd/DocType;
/*    */     //   0	145	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public DocType(String name, ExternalID extID, Seq<Decl> intSubset) {
/* 21 */     Product.class.$init$(this);
/* 23 */     if (Utility$.MODULE$.isName(name))
/*    */       return; 
/* 24 */     throw new IllegalArgumentException((new StringBuilder()).append(name).append(" must be an XML Name").toString());
/*    */   }
/*    */   
/*    */   private final String intString$1() {
/* 29 */     return intSubset().isEmpty() ? "" : 
/* 30 */       intSubset().mkString("[", "", "]");
/*    */   }
/*    */   
/*    */   public final String toString() {
/* 32 */     Predef$ predef$ = Predef$.MODULE$;
/* 32 */     return (new StringOps("<!DOCTYPE %s %s%s>")).format((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { name(), extID().toString(), intString$1() }));
/*    */   }
/*    */   
/*    */   public static Function1<Tuple3<String, ExternalID, Seq<Decl>>, DocType> tupled() {
/*    */     return DocType$.MODULE$.tupled();
/*    */   }
/*    */   
/*    */   public static Function1<String, Function1<ExternalID, Function1<Seq<Decl>, DocType>>> curried() {
/*    */     return DocType$.MODULE$.curried();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\DocType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */