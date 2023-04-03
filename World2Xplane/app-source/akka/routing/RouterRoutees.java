/*    */ package akka.routing;
/*    */ 
/*    */ import akka.actor.ActorRef;
/*    */ import java.util.List;
/*    */ import scala.Function1;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.JavaConverters$;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.immutable.IndexedSeq;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0055c\001B\001\003\001\036\021QBU8vi\026\024(k\\;uK\026\034(BA\002\005\003\035\021x.\036;j]\036T\021!B\001\005C.\\\027m\001\001\024\t\001Aa\"\005\t\003\0231i\021A\003\006\002\027\005)1oY1mC&\021QB\003\002\007\003:L(+\0324\021\005%y\021B\001\t\013\005\035\001&o\0343vGR\004\"!\003\n\n\005MQ!\001D*fe&\fG.\033>bE2,\007\002C\013\001\005+\007I\021\001\f\002\017I|W\017^3fgV\tq\003E\002\031;}i\021!\007\006\0035m\t\021\"[7nkR\f'\r\\3\013\005qQ\021AC2pY2,7\r^5p]&\021a$\007\002\013\023:$W\r_3e'\026\f\bC\001\021$\033\005\t#B\001\022\005\003\025\t7\r^8s\023\t!\023E\001\005BGR|'OU3g\021!1\003A!E!\002\0239\022\001\003:pkR,Wm\035\021\t\013!\002A\021A\025\002\rqJg.\033;?)\tQC\006\005\002,\0015\t!\001C\003\026O\001\007q\003C\003/\001\021\005q&\001\006hKR\024v.\036;fKN,\022\001\r\t\004cYzR\"\001\032\013\005M\"\024\001B;uS2T\021!N\001\005U\0064\030-\003\0028e\t!A*[:u\021\035I\004!!A\005\002i\nAaY8qsR\021!f\017\005\b+a\002\n\0211\001\030\021\035i\004!%A\005\002y\nabY8qs\022\"WMZ1vYR$\023'F\001@U\t9\002iK\001B!\t\021u)D\001D\025\t!U)A\005v]\016DWmY6fI*\021aIC\001\013C:tw\016^1uS>t\027B\001%D\005E)hn\0315fG.,GMV1sS\006t7-\032\005\b\025\002\t\t\021\"\021L\0035\001(o\0343vGR\004&/\0324jqV\tA\n\005\002N!6\taJ\003\002Pi\005!A.\0318h\023\t\tfJ\001\004TiJLgn\032\005\b'\002\t\t\021\"\001U\0031\001(o\0343vGR\f%/\033;z+\005)\006CA\005W\023\t9&BA\002J]RDq!\027\001\002\002\023\005!,\001\bqe>$Wo\031;FY\026lWM\034;\025\005ms\006CA\005]\023\ti&BA\002B]fDqa\030-\002\002\003\007Q+A\002yIEBq!\031\001\002\002\023\005#-A\bqe>$Wo\031;Ji\026\024\030\r^8s+\005\031\007c\0013f76\t1$\003\002g7\tA\021\n^3sCR|'\017C\004i\001\005\005I\021A5\002\021\r\fg.R9vC2$\"A[7\021\005%Y\027B\0017\013\005\035\021un\0347fC:DqaX4\002\002\003\0071\fC\004p\001\005\005I\021\t9\002\021!\f7\017[\"pI\026$\022!\026\005\be\002\t\t\021\"\021t\003!!xn\025;sS:<G#\001'\t\017U\004\021\021!C!m\0061Q-];bYN$\"A[<\t\017}#\030\021!a\0017\"\032\001!\037?\021\005%Q\030BA>\013\005A\031VM]5bYZ+'o]5p]VKEIH\001\002Q\031\001a0a\001\002\bA\021\021b`\005\004\003\003Q!A\0033faJ,7-\031;fI\006\022\021QA\001\017+N,\007eR3u%>,H/Z3tC\t\tI!A\0023]M:\021\"!\004\003\003\003E\t!a\004\002\033I{W\017^3s%>,H/Z3t!\rY\023\021\003\004\t\003\t\t\t\021#\001\002\024M)\021\021CA\013#A1\021qCA\017/)j!!!\007\013\007\005m!\"A\004sk:$\030.\\3\n\t\005}\021\021\004\002\022\003\n\034HO]1di\032+hn\031;j_:\f\004b\002\025\002\022\021\005\0211\005\013\003\003\037A\001B]A\t\003\003%)e\035\005\013\003S\t\t\"!A\005\002\006-\022!B1qa2LHc\001\026\002.!1Q#a\nA\002]A!\"!\r\002\022\005\005I\021QA\032\003\035)h.\0319qYf$B!!\016\002<A!\021\"a\016\030\023\r\tID\003\002\007\037B$\030n\0348\t\023\005u\022qFA\001\002\004Q\023a\001=%a!Q\021\021IA\t\003\003%I!a\021\002\027I,\027\r\032*fg>dg/\032\013\003\003\013\0022!TA$\023\r\tIE\024\002\007\037\nTWm\031;)\017\005Ea0a\001\002\b\001")
/*    */ public class RouterRoutees implements Product, Serializable {
/*    */   public static final long serialVersionUID = 1L;
/*    */   
/*    */   private final IndexedSeq<ActorRef> routees;
/*    */   
/*    */   public IndexedSeq<ActorRef> routees() {
/* 45 */     return this.routees;
/*    */   }
/*    */   
/*    */   public RouterRoutees copy(IndexedSeq<ActorRef> routees) {
/* 45 */     return new RouterRoutees(routees);
/*    */   }
/*    */   
/*    */   public IndexedSeq<ActorRef> copy$default$1() {
/* 45 */     return routees();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 45 */     return "RouterRoutees";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 45 */     return 1;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 45 */     int i = x$1;
/* 45 */     switch (i) {
/*    */       default:
/* 45 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 45 */     return routees();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 45 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 45 */     return x$1 instanceof RouterRoutees;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 45 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 45 */     return ScalaRunTime$.MODULE$._toString(this);
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
/*    */     //   8: instanceof akka/routing/RouterRoutees
/*    */     //   11: ifeq -> 19
/*    */     //   14: iconst_1
/*    */     //   15: istore_3
/*    */     //   16: goto -> 21
/*    */     //   19: iconst_0
/*    */     //   20: istore_3
/*    */     //   21: iload_3
/*    */     //   22: ifeq -> 84
/*    */     //   25: aload_1
/*    */     //   26: checkcast akka/routing/RouterRoutees
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
/*    */     //   #45	-> 0
/*    */     //   #63	-> 14
/*    */     //   #45	-> 21
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	86	0	this	Lakka/routing/RouterRoutees;
/*    */     //   0	86	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public RouterRoutees(IndexedSeq<ActorRef> routees) {
/* 45 */     Product.class.$init$(this);
/*    */   }
/*    */   
/*    */   public List<ActorRef> getRoutees() {
/* 51 */     return (List<ActorRef>)JavaConverters$.MODULE$.seqAsJavaListConverter((Seq)routees()).asJava();
/*    */   }
/*    */   
/*    */   public static <A> Function1<IndexedSeq<ActorRef>, A> andThen(Function1<RouterRoutees, A> paramFunction1) {
/*    */     return RouterRoutees$.MODULE$.andThen(paramFunction1);
/*    */   }
/*    */   
/*    */   public static <A> Function1<A, RouterRoutees> compose(Function1<A, IndexedSeq<ActorRef>> paramFunction1) {
/*    */     return RouterRoutees$.MODULE$.compose(paramFunction1);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\RouterRoutees.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */