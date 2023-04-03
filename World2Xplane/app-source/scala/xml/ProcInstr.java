/*    */ package scala.xml;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Predef$;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0055c\001B\001\003\001\036\021\021\002\025:pG&s7\017\036:\013\005\r!\021a\001=nY*\tQ!A\003tG\006d\027m\001\001\024\t\001AA\002\005\t\003\023)i\021AA\005\003\027\t\0211b\0259fG&\fGNT8eKB\021QBD\007\002\t%\021q\002\002\002\b!J|G-^2u!\ti\021#\003\002\023\t\ta1+\032:jC2L'0\0312mK\"AA\003\001BK\002\023\005Q#\001\004uCJ<W\r^\013\002-A\021qC\007\b\003\033aI!!\007\003\002\rA\023X\rZ3g\023\tYBD\001\004TiJLgn\032\006\0033\021A\001B\b\001\003\022\003\006IAF\001\bi\006\024x-\032;!\021!\001\003A!f\001\n\003)\022\001\0039s_\016$X\r\037;\t\021\t\002!\021#Q\001\nY\t\021\002\035:pGR,\007\020\036\021\t\013\021\002A\021A\023\002\rqJg.\033;?)\r1s\005\013\t\003\023\001AQ\001F\022A\002YAQ\001I\022A\002YAQA\013\001\005F-\n1\003Z8D_2dWm\031;OC6,7\017]1dKN,\022\001\f\t\003\0335J!A\f\003\003\017\t{w\016\\3b]\")\001\007\001C#W\005YAm\034+sC:\034hm\034:n\021\025\021\004\001\"\0024\003\025a\027MY3m+\005!\004CA\033;\033\0051$BA\0349\003\021a\027M\\4\013\003e\nAA[1wC&\0211D\016\005\006y\001!\teM\001\005i\026DH\017C\003?\001\021\005s(A\006ck&dGm\025;sS:<GC\001!I!\t\te)D\001C\025\t\031E)A\004nkR\f'\r\\3\013\005\025#\021AC2pY2,7\r^5p]&\021qI\021\002\016'R\024\030N\\4Ck&dG-\032:\t\013%k\004\031\001&\002\005M\024\007CA&T\035\ta\025K\004\002N!6\taJ\003\002P\r\0051AH]8pizJ\021!B\005\003%\022\tq\001]1dW\006<W-\003\002H)*\021!\013\002\005\b-\002\t\t\021\"\001X\003\021\031w\016]=\025\007\031B\026\fC\004\025+B\005\t\031\001\f\t\017\001*\006\023!a\001-!91\fAI\001\n\003a\026AD2paf$C-\0324bk2$H%M\013\002;*\022aCX\026\002?B\021\001-Z\007\002C*\021!mY\001\nk:\034\007.Z2lK\022T!\001\032\003\002\025\005tgn\034;bi&|g.\003\002gC\n\tRO\\2iK\016\\W\r\032,be&\fgnY3\t\017!\004\021\023!C\0019\006q1m\0349zI\021,g-Y;mi\022\022\004b\0026\001\003\003%\teM\001\016aJ|G-^2u!J,g-\033=\t\0171\004\021\021!C\001[\006a\001O]8ek\016$\030I]5usV\ta\016\005\002\016_&\021\001\017\002\002\004\023:$\bb\002:\001\003\003%\ta]\001\017aJ|G-^2u\0132,W.\0328u)\t!x\017\005\002\016k&\021a\017\002\002\004\003:L\bb\002=r\003\003\005\rA\\\001\004q\022\n\004b\002>\001\003\003%\te_\001\020aJ|G-^2u\023R,'/\031;peV\tA\020E\002~}Rl\021\001R\005\003\022\023\001\"\023;fe\006$xN]\004\n\003\007\021\021\021!E\001\003\013\t\021\002\025:pG&s7\017\036:\021\007%\t9A\002\005\002\005\005\005\t\022AA\005'\025\t9!a\003\021!\035\ti!a\005\027-\031j!!a\004\013\007\005EA!A\004sk:$\030.\\3\n\t\005U\021q\002\002\022\003\n\034HO]1di\032+hn\031;j_:\024\004b\002\023\002\b\021\005\021\021\004\013\003\003\013A!\"!\b\002\b\005\005IQIA\020\003!!xn\025;sS:<G#\001\033\t\025\005\r\022qAA\001\n\003\013)#A\003baBd\027\020F\003'\003O\tI\003\003\004\025\003C\001\rA\006\005\007A\005\005\002\031\001\f\t\025\0055\022qAA\001\n\003\013y#A\004v]\006\004\b\017\\=\025\t\005E\022Q\b\t\006\033\005M\022qG\005\004\003k!!AB(qi&|g\016E\003\016\003s1b#C\002\002<\021\021a\001V;qY\026\024\004\"CA \003W\t\t\0211\001'\003\rAH\005\r\005\013\003\007\n9!!A\005\n\005\025\023a\003:fC\022\024Vm]8mm\026$\"!a\022\021\007U\nI%C\002\002LY\022aa\0242kK\016$\b")
/*    */ public class ProcInstr extends SpecialNode implements Product, Serializable {
/*    */   private final String target;
/*    */   
/*    */   private final String proctext;
/*    */   
/*    */   public String target() {
/* 18 */     return this.target;
/*    */   }
/*    */   
/*    */   public String proctext() {
/* 18 */     return this.proctext;
/*    */   }
/*    */   
/*    */   public ProcInstr copy(String target, String proctext) {
/* 18 */     return new ProcInstr(target, proctext);
/*    */   }
/*    */   
/*    */   public String copy$default$1() {
/* 18 */     return target();
/*    */   }
/*    */   
/*    */   public String copy$default$2() {
/* 18 */     return proctext();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 18 */     return "ProcInstr";
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
/* 18 */     return target();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 18 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public ProcInstr(String target, String proctext) {
/* 18 */     Product.class.$init$(this);
/* 20 */     if (Utility$.MODULE$.isName(target)) {
/* 22 */       if (proctext.contains("?>"))
/* 23 */         throw new IllegalArgumentException((new StringBuilder()).append(proctext).append(" may not contain \"?>\"").toString()); 
/* 24 */       if (target.toLowerCase() == null) {
/* 24 */         target.toLowerCase();
/* 24 */         if ("xml" != null)
/*    */           return; 
/* 24 */       } else if (!target.toLowerCase().equals("xml")) {
/*    */         return;
/*    */       } 
/* 25 */       throw new IllegalArgumentException((new StringBuilder()).append(target).append(" is reserved").toString());
/*    */     } 
/*    */     throw new IllegalArgumentException((new StringBuilder()).append(target).append(" must be an XML Name").toString());
/*    */   }
/*    */   
/*    */   public final boolean doCollectNamespaces() {
/* 27 */     return false;
/*    */   }
/*    */   
/*    */   public final boolean doTransform() {
/* 28 */     return false;
/*    */   }
/*    */   
/*    */   public final String label() {
/* 30 */     return "#PI";
/*    */   }
/*    */   
/*    */   public String text() {
/* 31 */     return "";
/*    */   }
/*    */   
/*    */   public StringBuilder buildString(StringBuilder sb) {
/* 37 */     Predef$ predef$ = Predef$.MODULE$;
/* 37 */     (new Object[2])[0] = target();
/* 37 */     if (proctext() == null) {
/* 37 */       proctext();
/* 37 */       if ("" != null);
/* 37 */     } else if (proctext().equals("")) {
/*    */     
/*    */     } 
/*    */   }
/*    */   
/*    */   public static Function1<Tuple2<String, String>, ProcInstr> tupled() {
/*    */     return ProcInstr$.MODULE$.tupled();
/*    */   }
/*    */   
/*    */   public static Function1<String, Function1<String, ProcInstr>> curried() {
/*    */     return ProcInstr$.MODULE$.curried();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\ProcInstr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */