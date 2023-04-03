/*    */ package scala;
/*    */ 
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.immutable.IndexedSeq;
/*    */ import scala.collection.immutable.IndexedSeq$;
/*    */ import scala.collection.immutable.WrappedString;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.collection.mutable.WrappedArray;
/*    */ import scala.collection.mutable.WrappedArray$;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.Null$;
/*    */ import scala.sys.package$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\t5b\001B\001\003\001\025\021A\003T8x!JLwN]5us&k\007\017\\5dSR\034(\"A\002\002\013M\034\027\r\\1\004\001M\021\001A\002\t\003\017!i\021AA\005\003\023\t\021a!\0218z%\0264\007\"B\006\001\t\003a\021A\002\037j]&$h\bF\001\016!\t9\001\001C\003\020\001\021\r\001#A\006csR,wK]1qa\026\024HCA\t\030!\t\021R#D\001\024\025\t!\"!A\004sk:$\030.\\3\n\005Y\031\"\001\003*jG\"\024\025\020^3\t\013aq\001\031A\r\002\003a\004\"a\002\016\n\005m\021!\001\002\"zi\026D#AD\017\021\005\035q\022BA\020\003\005\031Ig\016\\5oK\")\021\005\001C\002E\005a1\017[8si^\023\030\r\0359feR\0211E\n\t\003%\021J!!J\n\003\023IK7\r[*i_J$\b\"\002\r!\001\0049\003CA\004)\023\tI#AA\003TQ>\024H\017\013\002!;!)A\006\001C\002[\005Q\021N\034;Xe\006\004\b/\032:\025\0059\n\004C\001\n0\023\t\0014CA\004SS\016D\027J\034;\t\013aY\003\031\001\032\021\005\035\031\024B\001\033\003\005\rIe\016\036\025\003WuAQa\016\001\005\004a\n1b\0315be^\023\030\r\0359feR\021\021\b\020\t\003%iJ!aO\n\003\021IK7\r[\"iCJDQ!\020\034A\002y\n\021a\031\t\003\017}J!\001\021\002\003\t\rC\027M\035\025\003muAQa\021\001\005\004\021\0131\002\\8oO^\023\030\r\0359feR\021Q\t\023\t\003%\031K!aR\n\003\021IK7\r\033'p]\036DQ\001\007\"A\002%\003\"a\002&\n\005-\023!\001\002'p]\036D#AQ\017\t\0139\003A1A(\002\031\031dw.\031;Xe\006\004\b/\032:\025\005A\033\006C\001\nR\023\t\0216CA\005SS\016Dg\t\\8bi\")\001$\024a\001)B\021q!V\005\003-\n\021QA\0227pCRD#!T\017\t\013e\003A1\001.\002\033\021|WO\0317f/J\f\007\017]3s)\tYf\f\005\002\0239&\021Ql\005\002\013%&\034\007\016R8vE2,\007\"\002\rY\001\004y\006CA\004a\023\t\t'A\001\004E_V\024G.\032\025\0031vAQ\001\032\001\005\004\025\faBY8pY\026\fgn\026:baB,'\017\006\002gSB\021!cZ\005\003QN\0211BU5dQ\n{w\016\\3b]\")\001d\031a\001UB\021qa[\005\003Y\n\021qAQ8pY\026\fg\016\013\002d;!)q\016\001C\002a\006)\")\037;fe\tLH/\032(vY2\034uN\0344mS\016$HCA\rr\021\025Ab\0161\001s!\t91/\003\002u\005\t!a*\0367m\021\0251\b\001b\001x\003]\031\006n\034:ueMDwN\035;Ok2d7i\0348gY&\034G\017\006\002(q\")\001$\036a\001e\")!\020\001C\002w\006Q2\t[1sC\016$XM\035\032dQ\006\024h*\0367m\007>tg\r\\5diR\021a\b \005\0061e\004\rA\035\005\006}\002!\031a`\001\030\023:$XmZ3se%tGOT;mY\016{gN\0327jGR$2AMA\001\021\025AR\0201\001s\021\035\t)\001\001C\002\003\017\tQ\003T8oOJbwN\\4Ok2d7i\0348gY&\034G\017F\002J\003\023Aa\001GA\002\001\004\021\bbBA\007\001\021\r\021qB\001\030\r2|\027\r\036\032gY>\fGOT;mY\016{gN\0327jGR$2\001VA\t\021\031A\0221\002a\001e\"9\021Q\003\001\005\004\005]\021!\007#pk\ndWM\r3pk\ndWMT;mY\016{gN\0327jGR$2aXA\r\021\031A\0221\003a\001e\"9\021Q\004\001\005\004\005}\021a\007\"p_2,\027M\034\032c_>dW-\0318Ok2d7i\0348gY&\034G\017F\002k\003CAa\001GA\016\001\004\021\bbBA\023\001\021\r\021qE\001\021O\026tWM]5d/J\f\007/\021:sCf,B!!\013\002@Q!\0211FA)!\031\ti#a\016\002<5\021\021q\006\006\005\003c\t\031$A\004nkR\f'\r\\3\013\007\005U\"!\001\006d_2dWm\031;j_:LA!!\017\0020\taqK]1qa\026$\027I\035:bsB!\021QHA \031\001!\001\"!\021\002$\t\007\0211\t\002\002)F!\021QIA&!\r9\021qI\005\004\003\023\022!a\002(pi\"Lgn\032\t\004\017\0055\023bAA(\005\t\031\021I\\=\t\021\005M\0231\005a\001\003+\n!\001_:\021\013\035\t9&a\017\n\007\005e#AA\003BeJ\f\027\020C\004\002^\001!\031!a\030\002\031]\024\030\r\035*fM\006\023(/Y=\026\t\005\005\024q\r\013\005\003G\nY\007\005\004\002.\005]\022Q\r\t\005\003{\t9\007\002\005\002B\005m#\031AA5#\r\t)E\002\005\t\003'\nY\0061\001\002nA)q!a\026\002f!9\021\021\017\001\005\004\005M\024\001D<sCBLe\016^!se\006LH\003BA;\003o\002R!!\f\0028IB\001\"a\025\002p\001\007\021\021\020\t\005\017\005]#\007C\004\002~\001!\031!a \002\037]\024\030\r\035#pk\ndW-\021:sCf$B!!!\002\004B)\021QFA\034?\"A\0211KA>\001\004\t)\t\005\003\b\003/z\006bBAE\001\021\r\0211R\001\016oJ\f\007\017T8oO\006\023(/Y=\025\t\0055\025q\022\t\006\003[\t9$\023\005\t\003'\n9\t1\001\002\022B!q!a\026J\021\035\t)\n\001C\002\003/\013ab\036:ba\032cw.\031;BeJ\f\027\020\006\003\002\032\006m\005#BA\027\003o!\006\002CA*\003'\003\r!!(\021\t\035\t9\006\026\005\b\003C\003A1AAR\00359(/\0319DQ\006\024\030I\035:bsR!\021QUAT!\025\ti#a\016?\021!\t\031&a(A\002\005%\006\003B\004\002XyBq!!,\001\t\007\ty+A\007xe\006\004()\037;f\003J\024\030-\037\013\005\003c\013\031\fE\003\002.\005]\022\004\003\005\002T\005-\006\031AA[!\0219\021qK\r\t\017\005e\006\001b\001\002<\006qqO]1q'\"|'\017^!se\006LH\003BA_\003\003R!!\f\0028\035B\001\"a\025\0028\002\007\021\021\031\t\005\017\005]s\005C\004\002F\002!\031!a2\002!]\024\030\r\035\"p_2,\027M\\!se\006LH\003BAe\003\027\004R!!\f\0028)D\001\"a\025\002D\002\007\021Q\032\t\005\017\005]#\016C\004\002R\002!\031!a5\002\033]\024\030\r]+oSR\f%O]1z)\021\t).!8\021\r\0055\022qGAl!\r9\021\021\\\005\004\0037\024!\001B+oSRD\001\"a\025\002P\002\007\021q\034\t\006\017\005]\023q\033\005\b\003G\004A1AAs\003)9(/\0319TiJLgn\032\013\005\003O\f\031\020\005\003\002j\006=XBAAv\025\021\ti/a\r\002\023%lW.\036;bE2,\027\002BAy\003W\024Qb\026:baB,Gm\025;sS:<\007\002CA{\003C\004\r!a>\002\003M\004B!!?\002\000:\031q!a?\n\007\005u(!\001\004Qe\026$WMZ\005\005\005\003\021\031A\001\004TiJLgn\032\006\004\003{\024\001b\002B\004\001\021\r!\021B\001\rk:<(/\0319TiJLgn\032\013\005\003o\024Y\001\003\005\003\016\t\025\001\031AAt\003\t98\017C\004\003\022\001!\031Aa\005\0025\031\fG\016\0342bG.\034FO]5oO\016\013gNQ;jY\0224%o\\7\026\t\tU!QE\013\003\005/\001\"B!\007\003 \005](1\005B\024\033\t\021YB\003\003\003\036\005M\022aB4f]\026\024\030nY\005\005\005C\021YB\001\007DC:\024U/\0337e\rJ|W\016\005\003\002>\t\025B\001CA!\005\037\021\r!a\021\021\r\005%(\021\006B\022\023\021\021Y#a;\003\025%sG-\032=fIN+\027\017")
/*    */ public class LowPriorityImplicits {
/*    */   public byte byteWrapper(byte x) {
/* 36 */     return x;
/*    */   }
/*    */   
/*    */   public short shortWrapper(short x) {
/* 37 */     return x;
/*    */   }
/*    */   
/*    */   public int intWrapper(int x) {
/* 38 */     return x;
/*    */   }
/*    */   
/*    */   public char charWrapper(char c) {
/* 39 */     return c;
/*    */   }
/*    */   
/*    */   public long longWrapper(long x) {
/* 40 */     return x;
/*    */   }
/*    */   
/*    */   public float floatWrapper(float x) {
/* 41 */     return x;
/*    */   }
/*    */   
/*    */   public double doubleWrapper(double x) {
/* 42 */     return x;
/*    */   }
/*    */   
/*    */   public boolean booleanWrapper(boolean x) {
/* 43 */     return x;
/*    */   }
/*    */   
/*    */   public byte Byte2byteNullConflict(Null$ x) {
/* 54 */     throw package$.MODULE$.error("value error");
/*    */   }
/*    */   
/*    */   public short Short2shortNullConflict(Null$ x) {
/* 55 */     throw package$.MODULE$.error("value error");
/*    */   }
/*    */   
/*    */   public char Character2charNullConflict(Null$ x) {
/* 56 */     throw package$.MODULE$.error("value error");
/*    */   }
/*    */   
/*    */   public int Integer2intNullConflict(Null$ x) {
/* 57 */     throw package$.MODULE$.error("value error");
/*    */   }
/*    */   
/*    */   public long Long2longNullConflict(Null$ x) {
/* 58 */     throw package$.MODULE$.error("value error");
/*    */   }
/*    */   
/*    */   public float Float2floatNullConflict(Null$ x) {
/* 59 */     throw package$.MODULE$.error("value error");
/*    */   }
/*    */   
/*    */   public double Double2doubleNullConflict(Null$ x) {
/* 60 */     throw package$.MODULE$.error("value error");
/*    */   }
/*    */   
/*    */   public boolean Boolean2booleanNullConflict(Null$ x) {
/* 61 */     throw package$.MODULE$.error("value error");
/*    */   }
/*    */   
/*    */   public <T> WrappedArray<T> genericWrapArray(Object xs) {
/* 64 */     return (xs == null) ? null : 
/* 65 */       WrappedArray$.MODULE$.make(xs);
/*    */   }
/*    */   
/*    */   public <T> WrappedArray<T> wrapRefArray(Object[] xs) {
/* 71 */     return (xs == null) ? null : (
/* 72 */       (xs.length == 0) ? WrappedArray$.MODULE$.empty() : 
/* 73 */       (WrappedArray<T>)new WrappedArray.ofRef(xs));
/*    */   }
/*    */   
/*    */   public WrappedArray<Object> wrapIntArray(int[] xs) {
/* 76 */     return (xs != null) ? (WrappedArray<Object>)new WrappedArray.ofInt(xs) : null;
/*    */   }
/*    */   
/*    */   public WrappedArray<Object> wrapDoubleArray(double[] xs) {
/* 77 */     return (xs != null) ? (WrappedArray<Object>)new WrappedArray.ofDouble(xs) : null;
/*    */   }
/*    */   
/*    */   public WrappedArray<Object> wrapLongArray(long[] xs) {
/* 78 */     return (xs != null) ? (WrappedArray<Object>)new WrappedArray.ofLong(xs) : null;
/*    */   }
/*    */   
/*    */   public WrappedArray<Object> wrapFloatArray(float[] xs) {
/* 79 */     return (xs != null) ? (WrappedArray<Object>)new WrappedArray.ofFloat(xs) : null;
/*    */   }
/*    */   
/*    */   public WrappedArray<Object> wrapCharArray(char[] xs) {
/* 80 */     return (xs != null) ? (WrappedArray<Object>)new WrappedArray.ofChar(xs) : null;
/*    */   }
/*    */   
/*    */   public WrappedArray<Object> wrapByteArray(byte[] xs) {
/* 81 */     return (xs != null) ? (WrappedArray<Object>)new WrappedArray.ofByte(xs) : null;
/*    */   }
/*    */   
/*    */   public WrappedArray<Object> wrapShortArray(short[] xs) {
/* 82 */     return (xs != null) ? (WrappedArray<Object>)new WrappedArray.ofShort(xs) : null;
/*    */   }
/*    */   
/*    */   public WrappedArray<Object> wrapBooleanArray(boolean[] xs) {
/* 83 */     return (xs != null) ? (WrappedArray<Object>)new WrappedArray.ofBoolean(xs) : null;
/*    */   }
/*    */   
/*    */   public WrappedArray<BoxedUnit> wrapUnitArray(BoxedUnit[] xs) {
/* 84 */     return (xs != null) ? (WrappedArray<BoxedUnit>)new WrappedArray.ofUnit(xs) : null;
/*    */   }
/*    */   
/*    */   public WrappedString wrapString(String s) {
/* 86 */     return (s != null) ? new WrappedString(s) : null;
/*    */   }
/*    */   
/*    */   public String unwrapString(WrappedString ws) {
/* 87 */     return (ws != null) ? ws.self() : null;
/*    */   }
/*    */   
/*    */   public <T> CanBuildFrom<String, T, IndexedSeq<T>> fallbackStringCanBuildFrom() {
/* 90 */     return new LowPriorityImplicits$$anon$1(this);
/*    */   }
/*    */   
/*    */   public class LowPriorityImplicits$$anon$1 implements CanBuildFrom<String, T, IndexedSeq<T>> {
/*    */     public LowPriorityImplicits$$anon$1(LowPriorityImplicits $outer) {}
/*    */     
/*    */     public Builder<T, IndexedSeq<T>> apply(String from) {
/* 91 */       return IndexedSeq$.MODULE$.newBuilder();
/*    */     }
/*    */     
/*    */     public Builder<T, IndexedSeq<T>> apply() {
/* 92 */       return IndexedSeq$.MODULE$.newBuilder();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\LowPriorityImplicits.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */