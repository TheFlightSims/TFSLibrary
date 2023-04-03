/*    */ package scala.reflect;
/*    */ 
/*    */ import scala.MatchError;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.collection.mutable.ArrayBuilder;
/*    */ import scala.collection.mutable.WrappedArray;
/*    */ import scala.runtime.AbstractFunction2;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005}h!C\001\003!\003\r\taBA|\005m\031E.Y:t\033\006t\027NZ3ti\022+\007O]3dCR,G-\0219jg*\0211\001B\001\be\0264G.Z2u\025\005)\021!B:dC2\f7\001A\013\003\021M\0312\001A\005\016!\tQ1\"D\001\005\023\taAA\001\004B]f\024VM\032\t\004\035=\tR\"\001\002\n\005A\021!aC(qi6\013g.\0334fgR\004\"AE\n\r\001\021)A\003\001b\001+\t\tA+\005\002\0273A\021!bF\005\0031\021\021qAT8uQ&tw\r\005\002\0135%\0211\004\002\002\004\003:L\b\"B\017\001\t\003q\022A\002\023j]&$H\005F\001 !\tQ\001%\003\002\"\t\t!QK\\5u\021\025\031\003\001\"\001%\003\035)'/Y:ve\026,\022!\n\031\003M=\0022a\n\027/\033\005A#BA\025+\003\021a\027M\\4\013\003-\nAA[1wC&\021Q\006\013\002\006\0072\f7o\035\t\003%=\"\021\002\r\022\002\002\003\005)\021A\013\003\007}#\023\007\013\003#eU:\004C\001\0064\023\t!DA\001\006eKB\024XmY1uK\022\f\023AN\001\031+N,\007E];oi&lWm\0217bgN\004\023N\\:uK\006$\027%\001\035\002\rIr\023\007\r\0301\021\025Q\004\001\"\003<\003\035\031XO\031;za\026$2\001P G!\tQQ(\003\002?\t\t9!i\\8mK\006t\007\"\002!:\001\004\t\025aA:vEB\022!\t\022\t\004O1\032\005C\001\nE\t%)u(!A\001\002\013\005QCA\002`IIBQaR\035A\002!\0131a];qa\tI5\nE\002(Y)\003\"AE&\005\02313\025\021!A\001\006\003)\"aA0%g!)a\n\001C\005\037\00691/\0362be\036\034Hc\001\037QG\")\021+\024a\001%\006)\021M]4tcA\0311k\0270\017\005QKfBA+Y\033\0051&BA,\007\003\031a$o\\8u}%\tQ!\003\002[\t\0059\001/Y2lC\036,\027B\001/^\005\021a\025n\035;\013\005i#\001GA0b!\rqq\002\031\t\003%\005$\021B\031)\002\002\003\005)\021A\013\003\007}#c\007C\003e\033\002\007Q-A\003be\036\034(\007E\002T7\032\004$aZ5\021\0079y\001\016\005\002\023S\022I!nYA\001\002\003\025\t!\006\002\004?\022:\004\"\0027\001\t\003i\027\001\005\023mKN\034HeY8m_:$C.Z:t)\tad\016C\003pW\002\007\001/\001\003uQ\006$\bGA9y!\r\021Ho\036\b\003\035ML!A\027\002\n\005U4(!D\"mCN\034X*\0318jM\026\034HO\003\002[\005A\021!\003\037\003\ns:\f\t\021!A\003\002U\0211a\030\0239Q\021Y'g_\034\"\003q\fq)V:fAM\034\027\r\\1/e\0264G.Z2u]I,h\016^5nK:*h.\033<feN,g\006V=qKR\013w\r\t4pe\002\032XO\031;za\026\0043\r[3dW&tw\rI5ogR,\027\r\032\005\006}\002!\ta`\001\027I\035\024X-\031;fe\022\032w\016\\8oI\035\024X-\031;feR\031A(!\001\t\r=l\b\031AA\002a\021\t)!!\003\021\tI$\030q\001\t\004%\005%AaCA\006\003\003\t\t\021!A\003\002U\021Aa\030\0232a!\"QPM>8\021\035\t\t\002\001C!\003'\t\001bY1o\013F,\030\r\034\013\004y\005U\001bBA\f\003\037\001\r!G\001\006_RDWM\035\005\b\0037\001A\021CA\017\003)\t'O]1z\0072\f7o]\013\005\003?\tY\003\006\003\002\"\0055\002\003B\024-\003G\001RACA\023\003SI1!a\n\005\005\025\t%O]1z!\r\021\0221\006\003\007)\005e!\031A\013\t\021\005=\022\021\004a\001\003c\t!\001\03691\t\005M\022q\007\t\005O1\n)\004E\002\023\003o!1\"!\017\002.\005\005\t\021!B\001+\t!q\fJ\0312\021\035\ti\004\001C\001\003\tQ\"\031:sCfl\025M\\5gKN$XCAA!!\021\021H/a\021\021\t)\t)#\005\025\007\003w\021\024qI\034\"\005\005%\023\001E+tK\002:(/\0319!S:\034H/Z1e\021\035\ti\005\001C!\003\037\n\001B\\3x\003J\024\030-\037\013\005\003\007\n\t\006\003\005\002T\005-\003\031AA+\003\raWM\034\t\004\025\005]\023bAA-\t\t\031\021J\034;\t\017\005u\003\001\"\001\002`\005Ia.Z<BeJ\f\027P\r\013\005\003C\n\031\007E\003\013\003K\t\031\005\003\005\002T\005m\003\031AA+Q\031\tYFMA4o\005\022\021\021N\001\032+N,\007e\036:ba:rWm^!se\006L\b%\0338ti\026\fG\rC\004\002n\001!\t!a\034\002\0239,w/\021:sCf\034D\003BA9\003g\002RACA\023\003CB\001\"a\025\002l\001\007\021Q\013\025\007\003W\022\024qO\034\"\005\005e\024AH+tK\002:(/\0319/oJ\f\007O\f8fo\006\023(/Y=!S:\034H/Z1e\021\035\ti\b\001C\001\003\n\021B\\3x\003J\024\030-\037\033\025\t\005\005\0251\021\t\006\025\005\025\022\021\017\005\t\003'\nY\b1\001\002V!2\0211\020\032\002\b^\n#!!#\002GU\033X\rI<sCBtsO]1q]]\024\030\r\035\030oK^\f%O]1zA%t7\017^3bI\"9\021Q\022\001\005\002\005=\025!\0038fo\006\023(/Y=6)\021\t\t*a%\021\013)\t)#!!\t\021\005M\0231\022a\001\003+Bc!a#3\003/;\024EAAM\003!*6/\032\021xe\006\004hf\036:ba::(/\0319/oJ\f\007O\f8fo\006\023(/Y=!S:\034H/Z1e\021\035\ti\n\001C\001\003?\013qB\\3x/J\f\007\017]3e\003J\024\030-\037\013\005\003C\013\t\fE\003\002$\0065\026#\004\002\002&*!\021qUAU\003\035iW\017^1cY\026T1!a+\005\003)\031w\016\0347fGRLwN\\\005\005\003_\013)K\001\007Xe\006\004\b/\0323BeJ\f\027\020\003\005\002T\005m\005\031AA+Q\031\tYJMA[o\005\022\021qW\001%\007J,\027\r^3!/J\f\007\017]3e\003J\024\030-\037\021eSJ,7\r\0367zA%t7\017^3bI\"9\0211\030\001\005\002\005u\026a\0048fo\006\023(/Y=Ck&dG-\032:\025\005\005}\006#BAR\003\003\f\022\002BAb\003K\023A\"\021:sCf\024U/\0337eKJDc!!/3\003\017<\024EAAe\003\r*6/\032\021BeJ\f\027PQ;jY\022,'OL7bW\026DC\017[5tS\001Jgn\035;fC\022Dq!!4\001\t\003\ty-A\007usB,\027I]4v[\026tGo]\013\003\003#\004BaU.\002TB\"\021Q[Am!\021qq\"a6\021\007I\tI\016B\006\002\\\006-\027\021!A\001\006\003)\"\001B0%cIBc!a33\003?<\024EAAq\0031+6/\032\021tG\006d\027M\f:fM2,7\r\036\030sk:$\030.\\3/k:Lg/\032:tK:\"\026\020]3UC\036\004Co\034\021dCB$XO]3!if\004X\rI:ueV\034G/\036:fA%t7\017^3bI\"9\021Q\035\001\005\022\005\035\030!C1sON#(/\0338h+\t\tI\017\005\003\002l\006Ehb\001\006\002n&\031\021q\036\003\002\rA\023X\rZ3g\023\021\t\0310!>\003\rM#(/\0338h\025\r\ty\017\002\t\004eR\f\002&\002\0013\003w<\024EAA\003\t*6/\032\021tG\006d\027M\f:fM2,7\r\036\030DY\006\0348\017V1hA%t7\017^3bI\002")
/*    */ public interface ClassManifestDeprecatedApis<T> extends OptManifest<T> {
/*    */   Class<?> erasure();
/*    */   
/*    */   boolean $less$colon$less(ClassTag<?> paramClassTag);
/*    */   
/*    */   boolean $greater$colon$greater(ClassTag<?> paramClassTag);
/*    */   
/*    */   boolean canEqual(Object paramObject);
/*    */   
/*    */   <T> Class<Object> arrayClass(Class<?> paramClass);
/*    */   
/*    */   ClassTag<Object> arrayManifest();
/*    */   
/*    */   Object newArray(int paramInt);
/*    */   
/*    */   Object[] newArray2(int paramInt);
/*    */   
/*    */   Object[][] newArray3(int paramInt);
/*    */   
/*    */   Object[][][] newArray4(int paramInt);
/*    */   
/*    */   Object[][][][] newArray5(int paramInt);
/*    */   
/*    */   WrappedArray<T> newWrappedArray(int paramInt);
/*    */   
/*    */   ArrayBuilder<T> newArrayBuilder();
/*    */   
/*    */   List<OptManifest<?>> typeArguments();
/*    */   
/*    */   String argString();
/*    */   
/*    */   public class ClassManifestDeprecatedApis$$anonfun$subargs$1 extends AbstractFunction2<OptManifest<?>, OptManifest<?>, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final boolean apply(OptManifest x0$1, OptManifest x1$1) {
/* 35 */       Tuple2 tuple2 = new Tuple2(x0$1, x1$1);
/* 35 */       if (tuple2 != null && tuple2
/*    */         
/* 37 */         ._1() instanceof ClassTag) {
/* 37 */         ClassTag classTag = (ClassTag)tuple2._1();
/* 37 */         if (tuple2._2() instanceof ClassTag) {
/* 37 */           ClassTag<?> classTag1 = (ClassTag)tuple2._2();
/* 37 */           return classTag.$less$colon$less(classTag1);
/*    */         } 
/*    */       } 
/*    */       if (tuple2 != null)
/* 38 */         return (tuple2._1() == NoManifest$.MODULE$ && tuple2._2() == NoManifest$.MODULE$); 
/*    */       throw new MatchError(tuple2);
/*    */     }
/*    */     
/*    */     public ClassManifestDeprecatedApis$$anonfun$subargs$1(ClassTag $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\reflect\ClassManifestDeprecatedApis.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */