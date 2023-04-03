/*    */ package akka.routing;
/*    */ 
/*    */ import akka.actor.ActorRef;
/*    */ import scala.Function1;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0055b\001B\001\003\001\036\021a\"Q2u_J\024VM\032*pkR,WM\003\002\004\t\0059!o\\;uS:<'\"A\003\002\t\005\\7.Y\002\001'\025\001\001B\004\n\026!\tIA\"D\001\013\025\005Y\021!B:dC2\f\027BA\007\013\005\031\te.\037*fMB\021q\002E\007\002\005%\021\021C\001\002\007%>,H/Z3\021\005%\031\022B\001\013\013\005\035\001&o\0343vGR\004\"!\003\f\n\005]Q!\001D*fe&\fG.\033>bE2,\007\002C\r\001\005+\007I\021\001\016\002\007I,g-F\001\034!\tar$D\001\036\025\tqB!A\003bGR|'/\003\002!;\tA\021i\031;peJ+g\r\003\005#\001\tE\t\025!\003\034\003\021\021XM\032\021\t\013\021\002A\021A\023\002\rqJg.\033;?)\t1s\005\005\002\020\001!)\021d\ta\0017!)\021\006\001C!U\005!1/\0328e)\rYcf\r\t\003\0231J!!\f\006\003\tUs\027\016\036\005\006_!\002\r\001M\001\b[\026\0348/Y4f!\tI\021'\003\0023\025\t\031\021I\\=\t\013QB\003\031A\016\002\rM,g\016Z3s\021\0351\004!!A\005\002]\nAaY8qsR\021a\005\017\005\b3U\002\n\0211\001\034\021\035Q\004!%A\005\002m\nabY8qs\022\"WMZ1vYR$\023'F\001=U\tYRhK\001?!\tyD)D\001A\025\t\t%)A\005v]\016DWmY6fI*\0211IC\001\013C:tw\016^1uS>t\027BA#A\005E)hn\0315fG.,GMV1sS\006t7-\032\005\b\017\002\t\t\021\"\021I\0035\001(o\0343vGR\004&/\0324jqV\t\021\n\005\002K\0376\t1J\003\002M\033\006!A.\0318h\025\005q\025\001\0026bm\006L!\001U&\003\rM#(/\0338h\021\035\021\006!!A\005\002M\013A\002\035:pIV\034G/\021:jif,\022\001\026\t\003\023UK!A\026\006\003\007%sG\017C\004Y\001\005\005I\021A-\002\035A\024x\016Z;di\026cW-\\3oiR\021\001G\027\005\b7^\013\t\0211\001U\003\rAH%\r\005\b;\002\t\t\021\"\021_\003=\001(o\0343vGRLE/\032:bi>\024X#A0\021\007\001\034\007'D\001b\025\t\021'\"\001\006d_2dWm\031;j_:L!\001Z1\003\021%#XM]1u_JDqA\032\001\002\002\023\005q-\001\005dC:,\025/^1m)\tA7\016\005\002\nS&\021!N\003\002\b\005>|G.Z1o\021\035YV-!AA\002ABq!\034\001\002\002\023\005c.\001\005iCND7i\0343f)\005!\006b\0029\001\003\003%\t%]\001\ti>\034FO]5oOR\t\021\nC\004t\001\005\005I\021\t;\002\r\025\fX/\0317t)\tAW\017C\004\\e\006\005\t\031\001\031\b\017]\024\021\021!E\001q\006q\021i\031;peJ+gMU8vi\026,\007CA\bz\r\035\t!!!A\t\002i\0342!_>\026!\021axp\007\024\016\003uT!A \006\002\017I,h\016^5nK&\031\021\021A?\003#\005\0237\017\036:bGR4UO\\2uS>t\027\007\003\004%s\022\005\021Q\001\013\002q\"9\001/_A\001\n\013\n\b\"CA\006s\006\005I\021QA\007\003\025\t\007\017\0357z)\r1\023q\002\005\0073\005%\001\031A\016\t\023\005M\0210!A\005\002\006U\021aB;oCB\004H.\037\013\005\003/\ti\002\005\003\n\0033Y\022bAA\016\025\t1q\n\035;j_:D\021\"a\b\002\022\005\005\t\031\001\024\002\007a$\003\007C\005\002$e\f\t\021\"\003\002&\005Y!/Z1e%\026\034x\016\034<f)\t\t9\003E\002K\003SI1!a\013L\005\031y%M[3di\002")
/*    */ public class ActorRefRoutee implements Routee, Product, Serializable {
/*    */   private final ActorRef ref;
/*    */   
/*    */   public ActorRef ref() {
/* 43 */     return this.ref;
/*    */   }
/*    */   
/*    */   public ActorRefRoutee copy(ActorRef ref) {
/* 43 */     return new ActorRefRoutee(ref);
/*    */   }
/*    */   
/*    */   public ActorRef copy$default$1() {
/* 43 */     return ref();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 43 */     return "ActorRefRoutee";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 43 */     return 1;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 43 */     int i = x$1;
/* 43 */     switch (i) {
/*    */       default:
/* 43 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 43 */     return ref();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 43 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 43 */     return x$1 instanceof ActorRefRoutee;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 43 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 43 */     return ScalaRunTime$.MODULE$._toString(this);
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
/*    */     //   8: instanceof akka/routing/ActorRefRoutee
/*    */     //   11: ifeq -> 19
/*    */     //   14: iconst_1
/*    */     //   15: istore_3
/*    */     //   16: goto -> 21
/*    */     //   19: iconst_0
/*    */     //   20: istore_3
/*    */     //   21: iload_3
/*    */     //   22: ifeq -> 84
/*    */     //   25: aload_1
/*    */     //   26: checkcast akka/routing/ActorRefRoutee
/*    */     //   29: astore #4
/*    */     //   31: aload_0
/*    */     //   32: invokevirtual ref : ()Lakka/actor/ActorRef;
/*    */     //   35: aload #4
/*    */     //   37: invokevirtual ref : ()Lakka/actor/ActorRef;
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
/*    */     //   #43	-> 0
/*    */     //   #63	-> 14
/*    */     //   #43	-> 21
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	86	0	this	Lakka/routing/ActorRefRoutee;
/*    */     //   0	86	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public ActorRefRoutee(ActorRef ref) {
/* 43 */     Product.class.$init$(this);
/*    */   }
/*    */   
/*    */   public void send(Object message, ActorRef sender) {
/* 45 */     ref().tell(message, sender);
/*    */   }
/*    */   
/*    */   public static <A> Function1<ActorRef, A> andThen(Function1<ActorRefRoutee, A> paramFunction1) {
/*    */     return ActorRefRoutee$.MODULE$.andThen(paramFunction1);
/*    */   }
/*    */   
/*    */   public static <A> Function1<A, ActorRefRoutee> compose(Function1<A, ActorRef> paramFunction1) {
/*    */     return ActorRefRoutee$.MODULE$.compose(paramFunction1);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\ActorRefRoutee.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */