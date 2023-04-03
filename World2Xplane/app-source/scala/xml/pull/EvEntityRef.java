/*    */ package scala.xml.pull;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005ea\001B\001\003\001&\0211\"\022<F]RLG/\037*fM*\0211\001B\001\005aVdGN\003\002\006\r\005\031\0010\0347\013\003\035\tQa]2bY\006\034\001aE\003\001\0259\021R\003\005\002\f\0315\ta!\003\002\016\r\t1\021I\\=SK\032\004\"a\004\t\016\003\tI!!\005\002\003\021akE*\022<f]R\004\"aC\n\n\005Q1!a\002)s_\022,8\r\036\t\003\027YI!a\006\004\003\031M+'/[1mSj\f'\r\\3\t\021e\001!Q3A\005\002i\ta!\0328uSRLX#A\016\021\005qybBA\006\036\023\tqb!\001\004Qe\026$WMZ\005\003A\005\022aa\025;sS:<'B\001\020\007\021!\031\003A!E!\002\023Y\022aB3oi&$\030\020\t\005\006K\001!\tAJ\001\007y%t\027\016\036 \025\005\035B\003CA\b\001\021\025IB\0051\001\034\021\035Q\003!!A\005\002-\nAaY8qsR\021q\005\f\005\b3%\002\n\0211\001\034\021\035q\003!%A\005\002=\nabY8qs\022\"WMZ1vYR$\023'F\0011U\tY\022gK\0013!\t\031\004(D\0015\025\t)d'A\005v]\016DWmY6fI*\021qGB\001\013C:tw\016^1uS>t\027BA\0355\005E)hn\0315fG.,GMV1sS\006t7-\032\005\bw\001\t\t\021\"\021=\0035\001(o\0343vGR\004&/\0324jqV\tQ\b\005\002?\0076\tqH\003\002A\003\006!A.\0318h\025\005\021\025\001\0026bm\006L!\001I \t\017\025\003\021\021!C\001\r\006a\001O]8ek\016$\030I]5usV\tq\t\005\002\f\021&\021\021J\002\002\004\023:$\bbB&\001\003\003%\t\001T\001\017aJ|G-^2u\0132,W.\0328u)\ti\005\013\005\002\f\035&\021qJ\002\002\004\003:L\bbB)K\003\003\005\raR\001\004q\022\n\004bB*\001\003\003%\t\005V\001\020aJ|G-^2u\023R,'/\031;peV\tQ\013E\002W36k\021a\026\006\0031\032\t!bY8mY\026\034G/[8o\023\tQvK\001\005Ji\026\024\030\r^8s\021\035a\006!!A\005\002u\013\001bY1o\013F,\030\r\034\013\003=\006\004\"aC0\n\005\0014!a\002\"p_2,\027M\034\005\b#n\013\t\0211\001N\021\035\031\007!!A\005B\021\f\001\002[1tQ\016{G-\032\013\002\017\"9a\rAA\001\n\003:\027\001\003;p'R\024\030N\\4\025\003uBq!\033\001\002\002\023\005#.\001\004fcV\fGn\035\013\003=.Dq!\0255\002\002\003\007QjB\004n\005\005\005\t\022\0018\002\027\0253XI\034;jif\024VM\032\t\003\037=4q!\001\002\002\002#\005\001oE\002pcV\001BA];\034O5\t1O\003\002u\r\0059!/\0368uS6,\027B\001<t\005E\t%m\035;sC\016$h)\0368di&|g.\r\005\006K=$\t\001\037\013\002]\"9am\\A\001\n\013:\007bB>p\003\003%\t\t`\001\006CB\004H.\037\013\003OuDQ!\007>A\002mA\001b`8\002\002\023\005\025\021A\001\bk:\f\007\017\0357z)\021\t\031!!\003\021\t-\t)aG\005\004\003\0171!AB(qi&|g\016\003\005\002\fy\f\t\0211\001(\003\rAH\005\r\005\n\003\037y\027\021!C\005\003#\t1B]3bIJ+7o\0347wKR\021\0211\003\t\004}\005U\021bAA\f\t1qJ\0316fGR\004")
/*    */ public class EvEntityRef implements XMLEvent, Product, Serializable {
/*    */   private final String entity;
/*    */   
/*    */   public static <A> Function1<String, A> andThen(Function1<EvEntityRef, A> paramFunction1) {
/*    */     return EvEntityRef$.MODULE$.andThen(paramFunction1);
/*    */   }
/*    */   
/*    */   public static <A> Function1<A, EvEntityRef> compose(Function1<A, String> paramFunction1) {
/*    */     return EvEntityRef$.MODULE$.compose(paramFunction1);
/*    */   }
/*    */   
/*    */   public String entity() {
/* 43 */     return this.entity;
/*    */   }
/*    */   
/*    */   public EvEntityRef copy(String entity) {
/* 43 */     return new EvEntityRef(entity);
/*    */   }
/*    */   
/*    */   public String copy$default$1() {
/* 43 */     return entity();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 43 */     return "EvEntityRef";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 43 */     return 1;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 43 */     switch (x$1) {
/*    */       default:
/* 43 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 43 */     return entity();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 43 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 43 */     return x$1 instanceof EvEntityRef;
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
/*    */     //   2: if_acmpeq -> 75
/*    */     //   5: aload_1
/*    */     //   6: instanceof scala/xml/pull/EvEntityRef
/*    */     //   9: ifeq -> 17
/*    */     //   12: iconst_1
/*    */     //   13: istore_2
/*    */     //   14: goto -> 19
/*    */     //   17: iconst_0
/*    */     //   18: istore_2
/*    */     //   19: iload_2
/*    */     //   20: ifeq -> 79
/*    */     //   23: aload_1
/*    */     //   24: checkcast scala/xml/pull/EvEntityRef
/*    */     //   27: astore #4
/*    */     //   29: aload_0
/*    */     //   30: invokevirtual entity : ()Ljava/lang/String;
/*    */     //   33: aload #4
/*    */     //   35: invokevirtual entity : ()Ljava/lang/String;
/*    */     //   38: astore_3
/*    */     //   39: dup
/*    */     //   40: ifnonnull -> 51
/*    */     //   43: pop
/*    */     //   44: aload_3
/*    */     //   45: ifnull -> 58
/*    */     //   48: goto -> 71
/*    */     //   51: aload_3
/*    */     //   52: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   55: ifeq -> 71
/*    */     //   58: aload #4
/*    */     //   60: aload_0
/*    */     //   61: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*    */     //   64: ifeq -> 71
/*    */     //   67: iconst_1
/*    */     //   68: goto -> 72
/*    */     //   71: iconst_0
/*    */     //   72: ifeq -> 79
/*    */     //   75: iconst_1
/*    */     //   76: goto -> 80
/*    */     //   79: iconst_0
/*    */     //   80: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #43	-> 0
/*    */     //   #236	-> 12
/*    */     //   #43	-> 19
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	81	0	this	Lscala/xml/pull/EvEntityRef;
/*    */     //   0	81	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public EvEntityRef(String entity) {
/* 43 */     Product.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\pull\EvEntityRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */