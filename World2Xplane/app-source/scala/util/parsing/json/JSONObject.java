/*    */ package scala.util.parsing.json;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.MatchError;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.immutable.Iterable$;
/*    */ import scala.collection.immutable.Map;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005Eb\001B\001\003\001.\021!BS*P\035>\023'.Z2u\025\t\031A!\001\003kg>t'BA\003\007\003\035\001\030M]:j]\036T!a\002\005\002\tU$\030\016\034\006\002\023\005)1oY1mC\016\0011\003\002\001\r!Q\001\"!\004\b\016\003\tI!a\004\002\003\021)\033vJ\024+za\026\004\"!\005\n\016\003!I!a\005\005\003\017A\023x\016Z;diB\021\021#F\005\003-!\021AbU3sS\006d\027N_1cY\026D\001\002\007\001\003\026\004%\t!G\001\004_\nTW#\001\016\021\tmq\022\005\n\b\003#qI!!\b\005\002\rA\023X\rZ3g\023\ty\002EA\002NCBT!!\b\005\021\005m\021\023BA\022!\005\031\031FO]5oOB\021\021#J\005\003M!\0211!\0218z\021!A\003A!E!\002\023Q\022\001B8cU\002BQA\013\001\005\002-\na\001P5oSRtDC\001\027.!\ti\001\001C\003\031S\001\007!\004C\0030\001\021\005\001'\001\005u_N#(/\0338h)\t\t\004\b\005\0023o5\t1G\003\0025k\005!A.\0318h\025\0051\024\001\0026bm\006L!aI\032\t\013er\003\031\001\036\002\023\031|'/\\1ui\026\024\bCA\036?\035\tiA(\003\002>\005\005Q!jU(O\r>\024X.\031;\n\005}\002%A\004,bYV,gi\034:nCR$XM\035\006\003{\tAqA\021\001\002\002\023\0051)\001\003d_BLHC\001\027E\021\035A\022\t%AA\002iAqA\022\001\022\002\023\005q)\001\bd_BLH\005Z3gCVdG\017J\031\026\003!S#AG%,\003)\003\"a\023)\016\0031S!!\024(\002\023Ut7\r[3dW\026$'BA(\t\003)\tgN\\8uCRLwN\\\005\003#2\023\021#\0368dQ\026\0347.\0323WCJL\027M\\2f\021\035\031\006!!A\005BQ\013Q\002\035:pIV\034G\017\025:fM&DX#A\031\t\017Y\003\021\021!C\001/\006a\001O]8ek\016$\030I]5usV\t\001\f\005\002\0223&\021!\f\003\002\004\023:$\bb\002/\001\003\003%\t!X\001\017aJ|G-^2u\0132,W.\0328u)\t!c\fC\004`7\006\005\t\031\001-\002\007a$\023\007C\004b\001\005\005I\021\t2\002\037A\024x\016Z;di&#XM]1u_J,\022a\031\t\004I\036$S\"A3\013\005\031D\021AC2pY2,7\r^5p]&\021\001.\032\002\t\023R,'/\031;pe\"9!\016AA\001\n\003Y\027\001C2b]\026\013X/\0317\025\0051|\007CA\tn\023\tq\007BA\004C_>dW-\0318\t\017}K\027\021!a\001I!9\021\017AA\001\n\003\022\030\001\0035bg\"\034u\016Z3\025\003aCq\001\036\001\002\002\023\005S/\001\004fcV\fGn\035\013\003YZDqaX:\002\002\003\007AeB\004y\005\005\005\t\022A=\002\025)\033vJT(cU\026\034G\017\005\002\016u\0329\021AAA\001\022\003Y8c\001>})A)Q0!\001\033Y5\taP\003\002\000\021\0059!/\0368uS6,\027bAA\002}\n\t\022IY:ue\006\034GOR;oGRLwN\\\031\t\r)RH\021AA\004)\005I\b\002C\030{\003\003%)%a\003\025\003EB\021\"a\004{\003\003%\t)!\005\002\013\005\004\b\017\\=\025\0071\n\031\002\003\004\031\003\033\001\rA\007\005\n\003/Q\030\021!CA\0033\tq!\0368baBd\027\020\006\003\002\034\005\005\002\003B\t\002\036iI1!a\b\t\005\031y\005\017^5p]\"I\0211EA\013\003\003\005\r\001L\001\004q\022\002\004\"CA\024u\006\005I\021BA\025\003-\021X-\0313SKN|GN^3\025\005\005-\002c\001\032\002.%\031\021qF\032\003\r=\023'.Z2u\001")
/*    */ public class JSONObject extends JSONType implements Product, Serializable {
/*    */   private final Map<String, Object> obj;
/*    */   
/*    */   public Map<String, Object> obj() {
/* 94 */     return this.obj;
/*    */   }
/*    */   
/*    */   public JSONObject copy(Map<String, Object> obj) {
/* 94 */     return new JSONObject(obj);
/*    */   }
/*    */   
/*    */   public Map<String, Object> copy$default$1() {
/* 94 */     return obj();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 94 */     return "JSONObject";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 94 */     return 1;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 94 */     switch (x$1) {
/*    */       default:
/* 94 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 94 */     return obj();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 94 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 94 */     return x$1 instanceof JSONObject;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 94 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 75
/*    */     //   5: aload_1
/*    */     //   6: instanceof scala/util/parsing/json/JSONObject
/*    */     //   9: ifeq -> 17
/*    */     //   12: iconst_1
/*    */     //   13: istore_2
/*    */     //   14: goto -> 19
/*    */     //   17: iconst_0
/*    */     //   18: istore_2
/*    */     //   19: iload_2
/*    */     //   20: ifeq -> 79
/*    */     //   23: aload_1
/*    */     //   24: checkcast scala/util/parsing/json/JSONObject
/*    */     //   27: astore #4
/*    */     //   29: aload_0
/*    */     //   30: invokevirtual obj : ()Lscala/collection/immutable/Map;
/*    */     //   33: aload #4
/*    */     //   35: invokevirtual obj : ()Lscala/collection/immutable/Map;
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
/*    */     //   #94	-> 0
/*    */     //   #236	-> 12
/*    */     //   #94	-> 19
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	81	0	this	Lscala/util/parsing/json/JSONObject;
/*    */     //   0	81	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public JSONObject(Map<String, Object> obj) {
/* 94 */     Product.class.$init$(this);
/*    */   }
/*    */   
/*    */   public String toString(Function1 formatter) {
/* 96 */     return (new StringBuilder()).append("{").append(((TraversableOnce)obj().map((Function1)new JSONObject$$anonfun$toString$1(this, formatter), Iterable$.MODULE$.canBuildFrom())).mkString(", ")).append("}").toString();
/*    */   }
/*    */   
/*    */   public static <A> Function1<Map<String, Object>, A> andThen(Function1<JSONObject, A> paramFunction1) {
/*    */     return JSONObject$.MODULE$.andThen(paramFunction1);
/*    */   }
/*    */   
/*    */   public static <A> Function1<A, JSONObject> compose(Function1<A, Map<String, Object>> paramFunction1) {
/*    */     return JSONObject$.MODULE$.compose(paramFunction1);
/*    */   }
/*    */   
/*    */   public class JSONObject$$anonfun$toString$1 extends AbstractFunction1<Tuple2<String, Object>, String> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Function1 formatter$1;
/*    */     
/*    */     public final String apply(Tuple2 x0$4) {
/* 96 */       if (x0$4 != null)
/* 96 */         return (new StringBuilder()).append(this.formatter$1.apply(((String)x0$4._1()).toString())).append(" : ").append(this.formatter$1.apply(x0$4._2())).toString(); 
/* 96 */       throw new MatchError(x0$4);
/*    */     }
/*    */     
/*    */     public JSONObject$$anonfun$toString$1(JSONObject $outer, Function1 formatter$1) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\json\JSONObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */