/*    */ package akka.routing;
/*    */ 
/*    */ import akka.actor.ActorRef;
/*    */ import akka.japi.Util$;
/*    */ import java.util.List;
/*    */ import scala.Function1;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.JavaConverters$;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.immutable.IndexedSeq;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005ec\001B\001\003\001\036\021abU3wKJ\fGNU8vi\026,7O\003\002\004\t\0059!o\\;uS:<'\"A\003\002\t\005\\7.Y\002\001'\025\001\001B\004\n\026!\tIA\"D\001\013\025\005Y\021!B:dC2\f\027BA\007\013\005\031\te.\037*fMB\021q\002E\007\002\005%\021\021C\001\002\007%>,H/Z3\021\005%\031\022B\001\013\013\005\035\001&o\0343vGR\004\"!\003\f\n\005]Q!\001D*fe&\fG.\033>bE2,\007\002C\r\001\005+\007I\021\001\016\002\017I|W\017^3fgV\t1\004E\002\035C9i\021!\b\006\003=}\t\021\"[7nkR\f'\r\\3\013\005\001R\021AC2pY2,7\r^5p]&\021!%\b\002\013\023:$W\r_3e'\026\f\b\002\003\023\001\005#\005\013\021B\016\002\021I|W\017^3fg\002BQA\n\001\005\002\035\na\001P5oSRtDC\001\025*!\ty\001\001C\003\032K\001\0071\004C\003'\001\021\0051\006\006\002)Y!)QF\013a\001]\005\021!o\035\t\004_QrQ\"\001\031\013\005E\022\024\001\0027b]\036T\021aM\001\005U\0064\030-\003\0026a\tA\021\n^3sC\ndW\rC\0038\001\021\005\001(\001\006hKR\024v.\036;fKN$\022!\017\t\004uurQ\"A\036\013\005q\022\024\001B;uS2L!AP\036\003\t1K7\017\036\005\006\001\002!\t%Q\001\005g\026tG\rF\002C\013*\003\"!C\"\n\005\021S!\001B+oSRDQAR A\002\035\013q!\\3tg\006<W\r\005\002\n\021&\021\021J\003\002\004\003:L\b\"B&@\001\004a\025AB:f]\022,'\017\005\002N!6\taJ\003\002P\t\005)\021m\031;pe&\021\021K\024\002\t\003\016$xN\035*fM\"91\013AA\001\n\003!\026\001B2paf$\"\001K+\t\017e\021\006\023!a\0017!9q\013AI\001\n\003A\026AD2paf$C-\0324bk2$H%M\013\0023*\0221DW\026\0027B\021A,Y\007\002;*\021alX\001\nk:\034\007.Z2lK\022T!\001\031\006\002\025\005tgn\034;bi&|g.\003\002c;\n\tRO\\2iK\016\\W\r\032,be&\fgnY3\t\017\021\004\021\021!C!K\006i\001O]8ek\016$\bK]3gSb,\022A\032\t\003_\035L!\001\033\031\003\rM#(/\0338h\021\035Q\007!!A\005\002-\fA\002\035:pIV\034G/\021:jif,\022\001\034\t\003\0235L!A\034\006\003\007%sG\017C\004q\001\005\005I\021A9\002\035A\024x\016Z;di\026cW-\\3oiR\021qI\035\005\bg>\f\t\0211\001m\003\rAH%\r\005\bk\002\t\t\021\"\021w\003=\001(o\0343vGRLE/\032:bi>\024X#A<\021\007aLx)D\001 \023\tQxD\001\005Ji\026\024\030\r^8s\021\035a\b!!A\005\002u\f\001bY1o\013F,\030\r\034\013\004}\006\r\001CA\005\000\023\r\t\tA\003\002\b\005>|G.Z1o\021\035\03180!AA\002\035C\021\"a\002\001\003\003%\t%!\003\002\021!\f7\017[\"pI\026$\022\001\034\005\n\003\033\001\021\021!C!\003\037\t\001\002^8TiJLgn\032\013\002M\"I\0211\003\001\002\002\023\005\023QC\001\007KF,\030\r\\:\025\007y\f9\002\003\005t\003#\t\t\0211\001H\017%\tYBAA\001\022\003\ti\"\001\bTKZ,'/\0317S_V$X-Z:\021\007=\tyB\002\005\002\005\005\005\t\022AA\021'\025\ty\"a\t\026!\031\t)#a\013\034Q5\021\021q\005\006\004\003SQ\021a\002:v]RLW.Z\005\005\003[\t9CA\tBEN$(/Y2u\rVt7\r^5p]FBqAJA\020\t\003\t\t\004\006\002\002\036!Q\021QBA\020\003\003%)%a\004\t\025\005]\022qDA\001\n\003\013I$A\003baBd\027\020F\002)\003wAa!GA\033\001\004Y\002BCA \003?\t\t\021\"!\002B\0059QO\\1qa2LH\003BA\"\003\023\002B!CA#7%\031\021q\t\006\003\r=\003H/[8o\021%\tY%!\020\002\002\003\007\001&A\002yIAB!\"a\024\002 \005\005I\021BA)\003-\021X-\0313SKN|GN^3\025\005\005M\003cA\030\002V%\031\021q\013\031\003\r=\023'.Z2u\001")
/*    */ public class SeveralRoutees implements Routee, Product, Serializable {
/*    */   private final IndexedSeq<Routee> routees;
/*    */   
/*    */   public IndexedSeq<Routee> routees() {
/* 68 */     return this.routees;
/*    */   }
/*    */   
/*    */   public SeveralRoutees copy(IndexedSeq<Routee> routees) {
/* 68 */     return new SeveralRoutees(routees);
/*    */   }
/*    */   
/*    */   public IndexedSeq<Routee> copy$default$1() {
/* 68 */     return routees();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 68 */     return "SeveralRoutees";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 68 */     return 1;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 68 */     int i = x$1;
/* 68 */     switch (i) {
/*    */       default:
/* 68 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 68 */     return routees();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 68 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 68 */     return x$1 instanceof SeveralRoutees;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 68 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 68 */     return ScalaRunTime$.MODULE$._toString(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 80
/*    */     //   5: aload_1
/*    */     //   6: astore_2
/*    */     //   7: aload_2
/*    */     //   8: instanceof akka/routing/SeveralRoutees
/*    */     //   11: ifeq -> 19
/*    */     //   14: iconst_1
/*    */     //   15: istore_3
/*    */     //   16: goto -> 21
/*    */     //   19: iconst_0
/*    */     //   20: istore_3
/*    */     //   21: iload_3
/*    */     //   22: ifeq -> 84
/*    */     //   25: aload_1
/*    */     //   26: checkcast akka/routing/SeveralRoutees
/*    */     //   29: astore #4
/*    */     //   31: aload_0
/*    */     //   32: invokevirtual routees : ()Lscala/collection/immutable/IndexedSeq;
/*    */     //   35: aload #4
/*    */     //   37: invokevirtual routees : ()Lscala/collection/immutable/IndexedSeq;
/*    */     //   40: astore #5
/*    */     //   42: dup
/*    */     //   43: ifnonnull -> 55
/*    */     //   46: pop
/*    */     //   47: aload #5
/*    */     //   49: ifnull -> 63
/*    */     //   52: goto -> 76
/*    */     //   55: aload #5
/*    */     //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   60: ifeq -> 76
/*    */     //   63: aload #4
/*    */     //   65: aload_0
/*    */     //   66: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*    */     //   69: ifeq -> 76
/*    */     //   72: iconst_1
/*    */     //   73: goto -> 77
/*    */     //   76: iconst_0
/*    */     //   77: ifeq -> 84
/*    */     //   80: iconst_1
/*    */     //   81: goto -> 85
/*    */     //   84: iconst_0
/*    */     //   85: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #68	-> 0
/*    */     //   #63	-> 14
/*    */     //   #68	-> 21
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	86	0	this	Lakka/routing/SeveralRoutees;
/*    */     //   0	86	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public SeveralRoutees(IndexedSeq<Routee> routees) {
/* 68 */     Product.class.$init$(this);
/*    */   }
/*    */   
/*    */   public SeveralRoutees(Iterable rs) {
/* 73 */     this((IndexedSeq<Routee>)Util$.MODULE$.immutableSeq(rs).toVector());
/*    */   }
/*    */   
/*    */   public List<Routee> getRoutees() {
/* 80 */     return (List<Routee>)JavaConverters$.MODULE$.seqAsJavaListConverter((Seq)routees()).asJava();
/*    */   }
/*    */   
/*    */   public void send(Object message, ActorRef sender) {
/* 84 */     routees().foreach((Function1)new SeveralRoutees$$anonfun$send$1(this, message, sender));
/*    */   }
/*    */   
/*    */   public static <A> Function1<IndexedSeq<Routee>, A> andThen(Function1<SeveralRoutees, A> paramFunction1) {
/*    */     return SeveralRoutees$.MODULE$.andThen(paramFunction1);
/*    */   }
/*    */   
/*    */   public static <A> Function1<A, SeveralRoutees> compose(Function1<A, IndexedSeq<Routee>> paramFunction1) {
/*    */     return SeveralRoutees$.MODULE$.compose(paramFunction1);
/*    */   }
/*    */   
/*    */   public class SeveralRoutees$$anonfun$send$1 extends AbstractFunction1<Routee, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Object message$1;
/*    */     
/*    */     private final ActorRef sender$1;
/*    */     
/*    */     public final void apply(Routee x$1) {
/* 84 */       x$1.send(this.message$1, this.sender$1);
/*    */     }
/*    */     
/*    */     public SeveralRoutees$$anonfun$send$1(SeveralRoutees $outer, Object message$1, ActorRef sender$1) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\SeveralRoutees.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */