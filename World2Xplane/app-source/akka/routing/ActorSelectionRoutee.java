/*    */ package akka.routing;
/*    */ 
/*    */ import akka.actor.ActorRef;
/*    */ import akka.actor.ActorSelection;
/*    */ import scala.Function1;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005Mb\001B\001\003\001\036\021A#Q2u_J\034V\r\\3di&|gNU8vi\026,'BA\002\005\003\035\021x.\036;j]\036T\021!B\001\005C.\\\027m\001\001\024\013\001AaBE\013\021\005%aQ\"\001\006\013\003-\tQa]2bY\006L!!\004\006\003\r\005s\027PU3g!\ty\001#D\001\003\023\t\t\"A\001\004S_V$X-\032\t\003\023MI!\001\006\006\003\017A\023x\016Z;diB\021\021BF\005\003/)\021AbU3sS\006d\027N_1cY\026D\001\"\007\001\003\026\004%\tAG\001\ng\026dWm\031;j_:,\022a\007\t\0039}i\021!\b\006\003=\021\tQ!Y2u_JL!\001I\017\003\035\005\033Go\034:TK2,7\r^5p]\"A!\005\001B\tB\003%1$\001\006tK2,7\r^5p]\002BQ\001\n\001\005\002\025\na\001P5oSRtDC\001\024(!\ty\001\001C\003\032G\001\0071\004C\003*\001\021\005#&\001\003tK:$GcA\026/gA\021\021\002L\005\003[)\021A!\0268ji\")q\006\013a\001a\0059Q.Z:tC\036,\007CA\0052\023\t\021$BA\002B]fDQ\001\016\025A\002U\naa]3oI\026\024\bC\001\0177\023\t9TD\001\005BGR|'OU3g\021\035I\004!!A\005\002i\nAaY8qsR\021ae\017\005\b3a\002\n\0211\001\034\021\035i\004!%A\005\002y\nabY8qs\022\"WMZ1vYR$\023'F\001@U\tY\002iK\001B!\t\021u)D\001D\025\t!U)A\005v]\016DWmY6fI*\021aIC\001\013C:tw\016^1uS>t\027B\001%D\005E)hn\0315fG.,GMV1sS\006t7-\032\005\b\025\002\t\t\021\"\021L\0035\001(o\0343vGR\004&/\0324jqV\tA\n\005\002N%6\taJ\003\002P!\006!A.\0318h\025\005\t\026\001\0026bm\006L!a\025(\003\rM#(/\0338h\021\035)\006!!A\005\002Y\013A\002\035:pIV\034G/\021:jif,\022a\026\t\003\023aK!!\027\006\003\007%sG\017C\004\\\001\005\005I\021\001/\002\035A\024x\016Z;di\026cW-\\3oiR\021\001'\030\005\b=j\013\t\0211\001X\003\rAH%\r\005\bA\002\t\t\021\"\021b\003=\001(o\0343vGRLE/\032:bi>\024X#\0012\021\007\r4\007'D\001e\025\t)'\"\001\006d_2dWm\031;j_:L!a\0323\003\021%#XM]1u_JDq!\033\001\002\002\023\005!.\001\005dC:,\025/^1m)\tYg\016\005\002\nY&\021QN\003\002\b\005>|G.Z1o\021\035q\006.!AA\002ABq\001\035\001\002\002\023\005\023/\001\005iCND7i\0343f)\0059\006bB:\001\003\003%\t\005^\001\ti>\034FO]5oOR\tA\nC\004w\001\005\005I\021I<\002\r\025\fX/\0317t)\tY\007\020C\004_k\006\005\t\031\001\031\b\017i\024\021\021!E\001w\006!\022i\031;peN+G.Z2uS>t'k\\;uK\026\004\"a\004?\007\017\005\021\021\021!E\001{N\031AP`\013\021\013}\f)a\007\024\016\005\005\005!bAA\002\025\0059!/\0368uS6,\027\002BA\004\003\003\021\021#\0212tiJ\f7\r\036$v]\016$\030n\03482\021\031!C\020\"\001\002\fQ\t1\020C\004ty\006\005IQ\t;\t\023\005EA0!A\005\002\006M\021!B1qa2LHc\001\024\002\026!1\021$a\004A\002mA\021\"!\007}\003\003%\t)a\007\002\017Ut\027\r\0359msR!\021QDA\022!\021I\021qD\016\n\007\005\005\"B\001\004PaRLwN\034\005\n\003K\t9\"!AA\002\031\n1\001\037\0231\021%\tI\003`A\001\n\023\tY#A\006sK\006$'+Z:pYZ,GCAA\027!\ri\025qF\005\004\003cq%AB(cU\026\034G\017")
/*    */ public class ActorSelectionRoutee implements Routee, Product, Serializable {
/*    */   private final ActorSelection selection;
/*    */   
/*    */   public ActorSelection selection() {
/* 51 */     return this.selection;
/*    */   }
/*    */   
/*    */   public ActorSelectionRoutee copy(ActorSelection selection) {
/* 51 */     return new ActorSelectionRoutee(selection);
/*    */   }
/*    */   
/*    */   public ActorSelection copy$default$1() {
/* 51 */     return selection();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 51 */     return "ActorSelectionRoutee";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 51 */     return 1;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 51 */     int i = x$1;
/* 51 */     switch (i) {
/*    */       default:
/* 51 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 51 */     return selection();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 51 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 51 */     return x$1 instanceof ActorSelectionRoutee;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 51 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 51 */     return ScalaRunTime$.MODULE$._toString(this);
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
/*    */     //   8: instanceof akka/routing/ActorSelectionRoutee
/*    */     //   11: ifeq -> 19
/*    */     //   14: iconst_1
/*    */     //   15: istore_3
/*    */     //   16: goto -> 21
/*    */     //   19: iconst_0
/*    */     //   20: istore_3
/*    */     //   21: iload_3
/*    */     //   22: ifeq -> 84
/*    */     //   25: aload_1
/*    */     //   26: checkcast akka/routing/ActorSelectionRoutee
/*    */     //   29: astore #4
/*    */     //   31: aload_0
/*    */     //   32: invokevirtual selection : ()Lakka/actor/ActorSelection;
/*    */     //   35: aload #4
/*    */     //   37: invokevirtual selection : ()Lakka/actor/ActorSelection;
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
/*    */     //   #51	-> 0
/*    */     //   #63	-> 14
/*    */     //   #51	-> 21
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	86	0	this	Lakka/routing/ActorSelectionRoutee;
/*    */     //   0	86	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public ActorSelectionRoutee(ActorSelection selection) {
/* 51 */     Product.class.$init$(this);
/*    */   }
/*    */   
/*    */   public void send(Object message, ActorRef sender) {
/* 53 */     selection().tell(message, sender);
/*    */   }
/*    */   
/*    */   public static <A> Function1<ActorSelection, A> andThen(Function1<ActorSelectionRoutee, A> paramFunction1) {
/*    */     return ActorSelectionRoutee$.MODULE$.andThen(paramFunction1);
/*    */   }
/*    */   
/*    */   public static <A> Function1<A, ActorSelectionRoutee> compose(Function1<A, ActorSelection> paramFunction1) {
/*    */     return ActorSelectionRoutee$.MODULE$.compose(paramFunction1);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\ActorSelectionRoutee.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */