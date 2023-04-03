/*    */ package akka.japi;
/*    */ 
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005-d\001B\001\003\001\036\021A\001U1je*\0211\001B\001\005U\006\004\030NC\001\006\003\021\t7n[1\004\001U\031\001BG\025\024\t\001IqB\005\t\003\0255i\021a\003\006\002\031\005)1oY1mC&\021ab\003\002\007\003:L(+\0324\021\005)\001\022BA\t\f\005\035\001&o\0343vGR\004\"AC\n\n\005QY!\001D*fe&\fG.\033>bE2,\007\002\003\f\001\005+\007I\021A\f\002\013\031L'o\035;\026\003a\001\"!\007\016\r\001\021)1\004\001b\0019\t\t\021)\005\002\036AA\021!BH\005\003?-\021qAT8uQ&tw\r\005\002\013C%\021!e\003\002\004\003:L\b\002\003\023\001\005#\005\013\021\002\r\002\r\031L'o\035;!\021!1\003A!f\001\n\0039\023AB:fG>tG-F\001)!\tI\022\006B\003+\001\t\007ADA\001C\021!a\003A!E!\002\023A\023aB:fG>tG\r\t\005\006]\001!\taL\001\007y%t\027\016\036 \025\007A\0224\007\005\0032\001aAS\"\001\002\t\013Yi\003\031\001\r\t\013\031j\003\031\001\025\t\017U\002\021\021!C\001m\005!1m\0349z+\r9$\b\020\013\004qur\004\003B\031\001sm\002\"!\007\036\005\013m!$\031\001\017\021\005eaD!\002\0265\005\004a\002b\002\f5!\003\005\r!\017\005\bMQ\002\n\0211\001<\021\035\001\005!%A\005\002\005\013abY8qs\022\"WMZ1vYR$\023'F\002C\033:+\022a\021\026\0031\021[\023!\022\t\003\r.k\021a\022\006\003\021&\013\021\"\0368dQ\026\0347.\0323\013\005)[\021AC1o]>$\030\r^5p]&\021Aj\022\002\022k:\034\007.Z2lK\0224\026M]5b]\016,G!B\016@\005\004aB!\002\026@\005\004a\002b\002)\001#\003%\t!U\001\017G>\004\030\020\n3fM\006,H\016\036\0233+\r\021F+V\013\002'*\022\001\006\022\003\0067=\023\r\001\b\003\006U=\023\r\001\b\005\b/\002\t\t\021\"\021Y\0035\001(o\0343vGR\004&/\0324jqV\t\021\f\005\002[?6\t1L\003\002];\006!A.\0318h\025\005q\026\001\0026bm\006L!\001Y.\003\rM#(/\0338h\021\035\021\007!!A\005\002\r\fA\002\035:pIV\034G/\021:jif,\022\001\032\t\003\025\025L!AZ\006\003\007%sG\017C\004i\001\005\005I\021A5\002\035A\024x\016Z;di\026cW-\\3oiR\021\001E\033\005\bW\036\f\t\0211\001e\003\rAH%\r\005\b[\002\t\t\021\"\021o\003=\001(o\0343vGRLE/\032:bi>\024X#A8\021\007A\034\b%D\001r\025\t\0218\"\001\006d_2dWm\031;j_:L!\001^9\003\021%#XM]1u_JDqA\036\001\002\002\023\005q/\001\005dC:,\025/^1m)\tA8\020\005\002\013s&\021!p\003\002\b\005>|G.Z1o\021\035YW/!AA\002\001Bq! \001\002\002\023\005c0\001\005iCND7i\0343f)\005!\007\"CA\001\001\005\005I\021IA\002\003!!xn\025;sS:<G#A-\t\023\005\035\001!!A\005B\005%\021AB3rk\006d7\017F\002y\003\027A\001b[A\003\003\003\005\r\001\t\025\006\001\005=\021Q\003\t\004\025\005E\021bAA\n\027\t\0012+\032:jC24VM]:j_:,\026\n\022\020\002\003\035I\021\021\004\002\002\002#\005\0211D\001\005!\006L'\017E\0022\003;1\001\"\001\002\002\002#\005\021qD\n\005\003;I!\003C\004/\003;!\t!a\t\025\005\005m\001BCA\001\003;\t\t\021\"\022\002\004!Q\021\021FA\017\003\003%\t)a\013\002\013\005\004\b\017\\=\026\r\0055\0221GA\034)\031\ty#!\017\002<A1\021\007AA\031\003k\0012!GA\032\t\031Y\022q\005b\0019A\031\021$a\016\005\r)\n9C1\001\035\021\0351\022q\005a\001\003cAqAJA\024\001\004\t)\004\003\006\002@\005u\021\021!CA\003\003\nq!\0368baBd\0270\006\004\002D\005M\023q\013\013\005\003\013\nI\006E\003\013\003\017\nY%C\002\002J-\021aa\0249uS>t\007c\002\006\002N\005E\023QK\005\004\003\037Z!A\002+va2,'\007E\002\032\003'\"aaGA\037\005\004a\002cA\r\002X\0211!&!\020C\002qA!\"a\027\002>\005\005\t\031AA/\003\rAH\005\r\t\007c\001\t\t&!\026\t\025\005\005\024QDA\001\n\023\t\031'A\006sK\006$'+Z:pYZ,GCAA3!\rQ\026qM\005\004\003SZ&AB(cU\026\034G\017")
/*    */ public class Pair<A, B> implements Product, Serializable {
/*    */   public static final long serialVersionUID = 1L;
/*    */   
/*    */   private final A first;
/*    */   
/*    */   private final B second;
/*    */   
/*    */   public A first() {
/* 59 */     return this.first;
/*    */   }
/*    */   
/*    */   public B second() {
/* 59 */     return this.second;
/*    */   }
/*    */   
/*    */   public <A, B> Pair<A, B> copy(Object first, Object second) {
/* 59 */     return new Pair((A)first, (B)second);
/*    */   }
/*    */   
/*    */   public <A, B> A copy$default$1() {
/* 59 */     return first();
/*    */   }
/*    */   
/*    */   public <A, B> B copy$default$2() {
/* 59 */     return second();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 59 */     return "Pair";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 59 */     return 2;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 59 */     int i = x$1;
/* 59 */     switch (i) {
/*    */       default:
/* 59 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 1:
/*    */       
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 59 */     return first();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 59 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 59 */     return x$1 instanceof Pair;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 59 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 59 */     return ScalaRunTime$.MODULE$._toString(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/* 59 */     if (this != x$1) {
/*    */       boolean bool;
/* 59 */       Object object = x$1;
/* 59 */       if (object instanceof Pair) {
/* 63 */         bool = true;
/*    */       } else {
/* 63 */         bool = false;
/*    */       } 
/*    */       if (bool) {
/*    */         Pair pair = (Pair)x$1;
/*    */         if ((BoxesRunTime.equals(first(), pair.first()) && BoxesRunTime.equals(second(), pair.second()) && pair.canEqual(this)));
/*    */       } 
/*    */       return false;
/*    */     } 
/*    */   }
/*    */   
/*    */   public Pair(Object first, Object second) {
/*    */     Product.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\japi\Pair.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */