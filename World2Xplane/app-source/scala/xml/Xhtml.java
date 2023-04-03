/*    */ package scala.xml;
/*    */ 
/*    */ import scala.Serializable;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005=r!B\001\003\021\0039\021!\002-ii6d'BA\002\005\003\rAX\016\034\006\002\013\005)1oY1mC\016\001\001C\001\005\n\033\005\021a!\002\006\003\021\003Y!!\002-ii6d7CA\005\r!\tia\"D\001\005\023\tyAA\001\004B]f\024VM\032\005\006#%!\tAE\001\007y%t\027\016\036 \025\003\035AQ\001F\005\005\002U\tq\001^8YQRlG\016\006\002\027;A\021qC\007\b\003\033aI!!\007\003\002\rA\023X\rZ3g\023\tYBD\001\004TiJLgn\032\006\0033\021AQAH\nA\002}\tAA\\8eKB\021\001\002I\005\003C\t\021AAT8eK\")A#\003C\001GQ\021a\003\n\005\006K\t\002\rAJ\001\b]>$WmU3r!\tAq%\003\002)\005\t9aj\0343f'\026\f\bb\002\026\n\005\004%IaK\001\024[&t\027.\\5{C\ndW-\0227f[\026tGo]\013\002YA\031QF\r\033\016\0039R!a\f\031\002\023%lW.\036;bE2,'BA\031\005\003)\031w\016\0347fGRLwN\\\005\003g9\022A\001T5tiB\021QGO\007\002m)\021q\007O\001\005Y\006twMC\001:\003\021Q\027M^1\n\005m1\004B\002\037\nA\003%A&\001\013nS:LW.\033>bE2,W\t\\3nK:$8\017\t\005\006)%!\tA\020\013\t\t#\025j\026/_AB\021Q\002Q\005\003\003\022\021A!\0268ji\")1)\020a\001?\005\t\001\020C\004F{A\005\t\031\001$\002\rA\0348m\0349f!\tAq)\003\002I\005\t\001b*Y7fgB\f7-\032\"j]\022Lgn\032\005\b\025v\002\n\0211\001L\003\t\031(\r\005\002M):\021QJ\025\b\003\035Fk\021a\024\006\003!\032\ta\001\020:p_Rt\024\"A\003\n\005M#\021a\0029bG.\fw-Z\005\003+Z\023Qb\025;sS:<')^5mI\026\024(BA*\005\021\035AV\b%AA\002e\013Qb\035;sSB\034u.\\7f]R\034\bCA\007[\023\tYFAA\004C_>dW-\0318\t\017uk\004\023!a\0013\006qA-Z2pI\026,e\016^5uS\026\034\bbB0>!\003\005\r!W\001\023aJ,7/\032:wK^C\027\016^3ta\006\034W\rC\004b{A\005\t\031A-\002\0315Lg.[7ju\026$\026mZ:\t\013\rLA\021\0013\002\033M,\027/^3oG\026$v\016W'M)!yTM[6m[:|\007\"\0024c\001\0049\027\001C2iS2$'/\0328\021\0071Cw$\003\002j-\n\0311+Z9\t\017\025\023\007\023!a\001\r\"9!J\031I\001\002\004Y\005b\002-c!\003\005\r!\027\005\b;\n\004\n\0211\001Z\021\035y&\r%AA\002eCq!\0312\021\002\003\007\021\fC\004r\023E\005I\021\001:\002#Q|\007\f\033;nY\022\"WMZ1vYR$#'F\001tU\t1EoK\001v!\t180D\001x\025\tA\0300A\005v]\016DWmY6fI*\021!\020B\001\013C:tw\016^1uS>t\027B\001?x\005E)hn\0315fG.,GMV1sS\006t7-\032\005\b}&\t\n\021\"\001\000\003E!x\016\0275u[2$C-\0324bk2$HeM\013\003\003\003Q#a\023;\t\023\005\025\021\"%A\005\002\005\035\021!\005;p1\"$X\016\034\023eK\032\fW\017\034;%iU\021\021\021\002\026\0033RD\021\"!\004\n#\003%\t!a\002\002#Q|\007\f\033;nY\022\"WMZ1vYR$S\007C\005\002\022%\t\n\021\"\001\002\b\005\tBo\034-ii6dG\005Z3gCVdG\017\n\034\t\023\005U\021\"%A\005\002\005\035\021!\005;p1\"$X\016\034\023eK\032\fW\017\034;%o!A\021\021D\005\022\002\023\005!/A\ftKF,XM\\2f)>DV\n\024\023eK\032\fW\017\034;%e!A\021QD\005\022\002\023\005q0A\ftKF,XM\\2f)>DV\n\024\023eK\032\fW\017\034;%g!I\021\021E\005\022\002\023\005\021qA\001\030g\026\fX/\0328dKR{\007,\024'%I\0264\027-\0367uIQB\021\"!\n\n#\003%\t!a\002\002/M,\027/^3oG\026$v\016W'MI\021,g-Y;mi\022*\004\"CA\025\023E\005I\021AA\004\003]\031X-];f]\016,Gk\034-N\031\022\"WMZ1vYR$c\007C\005\002.%\t\n\021\"\001\002\b\00592/Z9vK:\034W\rV8Y\0332#C-\0324bk2$He\016")
/*    */ public final class Xhtml {
/*    */   public static boolean sequenceToXML$default$7() {
/*    */     return Xhtml$.MODULE$.sequenceToXML$default$7();
/*    */   }
/*    */   
/*    */   public static boolean sequenceToXML$default$6() {
/*    */     return Xhtml$.MODULE$.sequenceToXML$default$6();
/*    */   }
/*    */   
/*    */   public static boolean sequenceToXML$default$5() {
/*    */     return Xhtml$.MODULE$.sequenceToXML$default$5();
/*    */   }
/*    */   
/*    */   public static boolean sequenceToXML$default$4() {
/*    */     return Xhtml$.MODULE$.sequenceToXML$default$4();
/*    */   }
/*    */   
/*    */   public static StringBuilder sequenceToXML$default$3() {
/*    */     return Xhtml$.MODULE$.sequenceToXML$default$3();
/*    */   }
/*    */   
/*    */   public static NamespaceBinding sequenceToXML$default$2() {
/*    */     return Xhtml$.MODULE$.sequenceToXML$default$2();
/*    */   }
/*    */   
/*    */   public static boolean toXhtml$default$7() {
/*    */     return Xhtml$.MODULE$.toXhtml$default$7();
/*    */   }
/*    */   
/*    */   public static boolean toXhtml$default$6() {
/*    */     return Xhtml$.MODULE$.toXhtml$default$6();
/*    */   }
/*    */   
/*    */   public static boolean toXhtml$default$5() {
/*    */     return Xhtml$.MODULE$.toXhtml$default$5();
/*    */   }
/*    */   
/*    */   public static boolean toXhtml$default$4() {
/*    */     return Xhtml$.MODULE$.toXhtml$default$4();
/*    */   }
/*    */   
/*    */   public static StringBuilder toXhtml$default$3() {
/*    */     return Xhtml$.MODULE$.toXhtml$default$3();
/*    */   }
/*    */   
/*    */   public static NamespaceBinding toXhtml$default$2() {
/*    */     return Xhtml$.MODULE$.toXhtml$default$2();
/*    */   }
/*    */   
/*    */   public static void sequenceToXML(Seq<Node> paramSeq, NamespaceBinding paramNamespaceBinding, StringBuilder paramStringBuilder, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4) {
/*    */     Xhtml$.MODULE$.sequenceToXML(paramSeq, paramNamespaceBinding, paramStringBuilder, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4);
/*    */   }
/*    */   
/*    */   public static void toXhtml(Node paramNode, NamespaceBinding paramNamespaceBinding, StringBuilder paramStringBuilder, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4) {
/*    */     Xhtml$.MODULE$.toXhtml(paramNode, paramNamespaceBinding, paramStringBuilder, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4);
/*    */   }
/*    */   
/*    */   public static String toXhtml(NodeSeq paramNodeSeq) {
/*    */     return Xhtml$.MODULE$.toXhtml(paramNodeSeq);
/*    */   }
/*    */   
/*    */   public static String toXhtml(Node paramNode) {
/*    */     return Xhtml$.MODULE$.toXhtml(paramNode);
/*    */   }
/*    */   
/*    */   public static class Xhtml$$anonfun$toXhtml$1 extends AbstractFunction1<StringBuilder, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Node node$1;
/*    */     
/*    */     public final void apply(StringBuilder sb) {
/* 16 */       Node x$2 = this.node$1;
/* 16 */       NamespaceBinding x$4 = Xhtml$.MODULE$.toXhtml$default$2();
/* 16 */       boolean x$5 = Xhtml$.MODULE$.toXhtml$default$4(), x$6 = Xhtml$.MODULE$.toXhtml$default$5(), x$7 = Xhtml$.MODULE$.toXhtml$default$6(), x$8 = Xhtml$.MODULE$.toXhtml$default$7();
/* 16 */       Xhtml$.MODULE$.toXhtml(x$2, x$4, sb, x$5, x$6, x$7, x$8);
/*    */     }
/*    */     
/*    */     public Xhtml$$anonfun$toXhtml$1(Node node$1) {}
/*    */   }
/*    */   
/*    */   public static class Xhtml$$anonfun$toXhtml$2 extends AbstractFunction1<StringBuilder, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final NodeSeq nodeSeq$1;
/*    */     
/*    */     public final void apply(StringBuilder sb) {
/* 24 */       NodeSeq nodeSeq = this.nodeSeq$1;
/* 24 */       NamespaceBinding x$11 = Xhtml$.MODULE$.sequenceToXML$default$2();
/* 24 */       boolean x$12 = Xhtml$.MODULE$.sequenceToXML$default$4(), x$13 = Xhtml$.MODULE$.sequenceToXML$default$5(), x$14 = Xhtml$.MODULE$.sequenceToXML$default$6(), x$15 = Xhtml$.MODULE$.sequenceToXML$default$7();
/* 24 */       Xhtml$.MODULE$.sequenceToXML((Seq<Node>)nodeSeq, x$11, sb, x$12, x$13, x$14, x$15);
/*    */     }
/*    */     
/*    */     public Xhtml$$anonfun$toXhtml$2(NodeSeq nodeSeq$1) {}
/*    */   }
/*    */   
/*    */   public static class Xhtml$$anonfun$toXhtml$3 extends AbstractFunction1<Node, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Node x$16;
/*    */     
/*    */     private final StringBuilder sb$1;
/*    */     
/*    */     private final boolean stripComments$1;
/*    */     
/*    */     private final boolean decodeEntities$1;
/*    */     
/*    */     private final boolean preserveWhitespace$1;
/*    */     
/*    */     private final boolean minimizeTags$1;
/*    */     
/*    */     public final void apply(Node x$1) {
/* 55 */       Xhtml$.MODULE$.toXhtml(x$1, this.x$16.scope(), this.sb$1, this.stripComments$1, this.decodeEntities$1, this.preserveWhitespace$1, this.minimizeTags$1);
/*    */     }
/*    */     
/*    */     public Xhtml$$anonfun$toXhtml$3(Node x$16, StringBuilder sb$1, boolean stripComments$1, boolean decodeEntities$1, boolean preserveWhitespace$1, boolean minimizeTags$1) {}
/*    */   }
/*    */   
/*    */   public static class Xhtml$$anonfun$1 extends AbstractFunction1<Node, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final boolean apply(Node x) {
/* 89 */       return Utility$.MODULE$.isAtomAndNotText(x);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Xhtml$$anonfun$sequenceToXML$1 extends AbstractFunction1<Node, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final NamespaceBinding pscope$1;
/*    */     
/*    */     private final StringBuilder sb$2;
/*    */     
/*    */     private final boolean stripComments$2;
/*    */     
/*    */     private final boolean decodeEntities$2;
/*    */     
/*    */     private final boolean preserveWhitespace$2;
/*    */     
/*    */     private final boolean minimizeTags$2;
/*    */     
/*    */     private final boolean doSpaces$1;
/*    */     
/*    */     public Xhtml$$anonfun$sequenceToXML$1(NamespaceBinding pscope$1, StringBuilder sb$2, boolean stripComments$2, boolean decodeEntities$2, boolean preserveWhitespace$2, boolean minimizeTags$2, boolean doSpaces$1) {}
/*    */     
/*    */     public final Object apply(Node c) {
/* 91 */       Xhtml$.MODULE$.toXhtml(c, this.pscope$1, this.sb$2, this.stripComments$2, this.decodeEntities$2, this.preserveWhitespace$2, this.minimizeTags$2);
/* 92 */       return this.doSpaces$1 ? this.sb$2.append(' ') : BoxedUnit.UNIT;
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\Xhtml.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */