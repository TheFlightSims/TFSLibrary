/*     */ package scala.reflect;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.mutable.ArrayBuilder;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.collection.mutable.WrappedArray;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.Nothing$;
/*     */ import scala.runtime.Null$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\tuu!B\001\003\021\0039\021\001F\"mCN\034X*\0318jM\026\034HOR1di>\024\030P\003\002\004\t\0059!/\0324mK\016$(\"A\003\002\013M\034\027\r\\1\004\001A\021\001\"C\007\002\005\031)!B\001E\001\027\t!2\t\\1tg6\013g.\0334fgR4\025m\031;pef\034\"!\003\007\021\0055qQ\"\001\003\n\005=!!AB!osJ+g\rC\003\022\023\021\005!#\001\004=S:LGO\020\013\002\017!9A#\003b\001\n\003)\022\001\002\"zi\026,\022A\006\t\004\021]I\022B\001\r\003\0059\te.\037,bY6\013g.\0334fgR\004\"!\004\016\n\005m!!\001\002\"zi\026Da!H\005!\002\0231\022!\002\"zi\026\004\003bB\020\n\005\004%\t\001I\001\006'\"|'\017^\013\002CA\031\001b\006\022\021\0055\031\023B\001\023\005\005\025\031\006n\034:u\021\0311\023\002)A\005C\00511\013[8si\002Bq\001K\005C\002\023\005\021&\001\003DQ\006\024X#\001\026\021\007!92\006\005\002\016Y%\021Q\006\002\002\005\007\"\f'\017\003\0040\023\001\006IAK\001\006\007\"\f'\017\t\005\bc%\021\r\021\"\0013\003\rIe\016^\013\002gA\031\001b\006\033\021\0055)\024B\001\034\005\005\rIe\016\036\005\007q%\001\013\021B\032\002\t%sG\017\t\005\bu%\021\r\021\"\001<\003\021auN\\4\026\003q\0022\001C\f>!\tia(\003\002@\t\t!Aj\0348h\021\031\t\025\002)A\005y\005)Aj\0348hA!91)\003b\001\n\003!\025!\002$m_\006$X#A#\021\007!9b\t\005\002\016\017&\021\001\n\002\002\006\r2|\027\r\036\005\007\025&\001\013\021B#\002\r\031cw.\031;!\021\035a\025B1A\005\0025\013a\001R8vE2,W#\001(\021\007!9r\n\005\002\016!&\021\021\013\002\002\007\t>,(\r\\3\t\rMK\001\025!\003O\003\035!u.\0362mK\002Bq!V\005C\002\023\005a+A\004C_>dW-\0318\026\003]\0032\001C\fY!\ti\021,\003\002[\t\t9!i\\8mK\006t\007B\002/\nA\003%q+\001\005C_>dW-\0318!\021\035q\026B1A\005\002}\013A!\0268jiV\t\001\rE\002\t/\005\004\"!\0042\n\005\r$!\001B+oSRDa!Z\005!\002\023\001\027!B+oSR\004\003bB4\n\005\004%\t\001[\001\004\003:LX#A5\021\007!QG.\003\002l\005\tAQ*\0318jM\026\034H\017\005\002\016[&\021a\016\002\002\004\003:L\bB\0029\nA\003%\021.\001\003B]f\004\003b\002:\n\005\004%\ta]\001\007\037\nTWm\031;\026\003Q\0042\001\0036v!\t180D\001x\025\tA\0300\001\003mC:<'\"\001>\002\t)\fg/Y\005\003y^\024aa\0242kK\016$\bB\002@\nA\003%A/A\004PE*,7\r\036\021\t\023\005\005\021B1A\005\002\005\r\021AB!osZ\013G.\006\002\002\006A!\001B[A\004!\ri\021\021B\005\004\003\027!!AB!osZ\013G\016\003\005\002\020%\001\013\021BA\003\003\035\te.\037,bY\002B\021\"a\005\n\005\004%\t!!\006\002\0179{G\017[5oOV\021\021q\003\t\005\021)\fI\002E\002\016\0037I1!!\b\005\005\035qu\016\0365j]\036D\001\"!\t\nA\003%\021qC\001\t\035>$\b.\0338hA!I\021QE\005C\002\023\005\021qE\001\005\035VdG.\006\002\002*A!\001B[A\026!\ri\021QF\005\004\003_!!\001\002(vY2D\001\"a\r\nA\003%\021\021F\001\006\035VdG\016\t\005\b\003oIA\021AA\035\003%1'o\\7DY\006\0348/\006\003\002<\005=C\003BA\037\003+\002b!a\020\002F\005-cb\001\005\002B%\031\0211\t\002\002\017A\f7m[1hK&!\021qIA%\0055\031E.Y:t\033\006t\027NZ3ti*\031\0211\t\002\021\t\0055\023q\n\007\001\t!\t\t&!\016C\002\005M#!\001+\022\007\005eA\016\003\005\002X\005U\002\031AA-\003\025\031G.\031>{!\0251\0301LA&\023\r\tif\036\002\006\0072\f7o\035\005\b\003CJA\021AA2\003)\031\030N\\4mKRK\b/Z\013\005\003K\nY\007\006\003\002h\005=\004\003\002\005k\003S\002B!!\024\002l\021A\021\021KA0\005\004\ti'E\002\002\0321Aq!!\035\002`\001\007A\"A\003wC2,X\rC\004\002v%!\t!a\036\002\023\rd\027m]:UsB,W\003BA=\003\"B!a\037\002\002B1\021qHA#\003{\002B!!\024\002\000\021A\021\021KA:\005\004\t\031\006\003\005\002X\005M\004\031AABa\021\t))!#\021\013Y\fY&a\"\021\t\0055\023\021\022\003\r\003\027\013\t)!A\001\002\013\005\0211\013\002\005?\022\n4\007C\004\002v%!\t!a$\026\t\005E\025q\023\013\t\003'\013I*!*\0028B1\021qHA#\003+\003B!!\024\002\030\022A\021\021KAG\005\004\t\031\006\003\005\002X\0055\005\031AANa\021\ti*!)\021\013Y\fY&a(\021\t\0055\023\021\025\003\r\003G\013I*!A\001\002\013\005\0211\013\002\005?\022\nD\007\003\005\002(\0065\005\031AAU\003\021\t'oZ\0311\t\005-\0261\027\t\006\021\0055\026\021W\005\004\003_\023!aC(qi6\013g.\0334fgR\004B!!\024\0024\022a\021QWAS\003\003\005\tQ!\001\002T\t!q\fJ\0316\021!\tI,!$A\002\005m\026\001B1sON\004R!DA_\003\003L1!a0\005\005)a$/\0329fCR,GM\020\031\005\003\007\f9\rE\003\t\003[\013)\r\005\003\002N\005\035G\001DAe\003o\013\t\021!A\003\002\005M#\001B0%cYBq!!\036\n\t\003\ti-\006\003\002P\006UG\003CAi\003/\f)/!=\021\r\005}\022QIAj!\021\ti%!6\005\021\005E\0231\032b\001\003'B\001\"!7\002L\002\007\0211\\\001\007aJ,g-\033=1\t\005u\027\021\035\t\006\021\0055\026q\034\t\005\003\033\n\t\017\002\007\002d\006]\027\021!A\001\006\003\t\031F\001\003`IE:\004\002CA,\003\027\004\r!a:1\t\005%\030Q\036\t\006m\006m\0231\036\t\005\003\033\ni\017\002\007\002p\006\025\030\021!A\001\006\003\t\031F\001\003`IEB\004\002CA]\003\027\004\r!a=\021\0135\ti,!>1\t\005]\0301 \t\006\021\0055\026\021 \t\005\003\033\nY\020\002\007\002~\006E\030\021!A\001\006\003\t\031F\001\003`IEJ\004b\002B\001\023\021\005!1A\001\nCJ\024\030-\037+za\026,BA!\002\003\022Q!!q\001B\n!\031\ty$!\022\003\nA)QBa\003\003\020%\031!Q\002\003\003\013\005\023(/Y=\021\t\0055#\021\003\003\t\003#\nyP1\001\002T!A!QCA\000\001\004\0219\"A\002be\036\004DA!\007\003\036A)\001\"!,\003\034A!\021Q\nB\017\t1\021yBa\005\002\002\003\005)\021AA*\005\021yFE\r\031\t\017\t\r\022\002\"\001\003&\005a\021MY:ue\006\034G\017V=qKV!!q\005B\027))\021ICa\f\003<\t5#\021\f\t\007\003\t)Ea\013\021\t\0055#Q\006\003\t\003#\022\tC1\001\002T!A\021\021\034B\021\001\004\021\t\004\r\003\0034\t]\002#\002\005\002.\nU\002\003BA'\005o!AB!\017\0030\005\005\t\021!B\001\003'\022Aa\030\0233c!A!Q\bB\021\001\004\021y$\001\003oC6,\007\003\002B!\005\017r1!\004B\"\023\r\021)\005B\001\007!J,G-\0324\n\t\t%#1\n\002\007'R\024\030N\\4\013\007\t\025C\001\003\005\002X\t\005\002\031\001B(a\021\021\tF!\026\021\013Y\fYFa\025\021\t\0055#Q\013\003\r\005/\022i%!A\001\002\013\005\0211\013\002\005?\022\022$\007\003\005\002:\n\005\002\031\001B.!\025i\021Q\030B/a\021\021yFa\031\021\013!\tiK!\031\021\t\0055#1\r\003\r\005K\022I&!A\001\002\013\005\0211\013\002\005?\022\0224\007C\004\003$%!\tA!\033\026\t\t-$\021\017\013\013\005[\022\031Ha \003\002\n=\005CBA \003\013\022y\007\005\003\002N\tED\001CA)\005O\022\r!a\025\t\021\005e'q\ra\001\005k\002DAa\036\003|A)\001\"!,\003zA!\021Q\nB>\t1\021iHa\035\002\002\003\005)\021AA*\005\021yFE\r\033\t\021\tu\"q\ra\001\005A\001Ba!\003h\001\007!QQ\001\013kB\004XM\0352pk:$\007\007\002BD\005\027\003b!a\020\002F\t%\005\003BA'\005\027#AB!$\003\002\006\005\t\021!B\001\003'\022Aa\030\0233k!A\021\021\030B4\001\004\021\t\nE\003\016\003{\023\031\n\r\003\003\026\ne\005#\002\005\002.\n]\005\003BA'\0053#ABa'\003\020\006\005\t\021!B\001\003'\022Aa\030\0233m\001")
/*     */ public final class ClassManifestFactory {
/*     */   public static <T> ClassTag<T> abstractType(OptManifest<?> paramOptManifest, String paramString, ClassTag<?> paramClassTag, Seq<OptManifest<?>> paramSeq) {
/*     */     return ClassManifestFactory$.MODULE$.abstractType(paramOptManifest, paramString, paramClassTag, paramSeq);
/*     */   }
/*     */   
/*     */   public static <T> ClassTag<T> abstractType(OptManifest<?> paramOptManifest, String paramString, Class<?> paramClass, Seq<OptManifest<?>> paramSeq) {
/*     */     return ClassManifestFactory$.MODULE$.abstractType(paramOptManifest, paramString, paramClass, paramSeq);
/*     */   }
/*     */   
/*     */   public static <T> ClassTag<Object> arrayType(OptManifest<?> paramOptManifest) {
/*     */     return ClassManifestFactory$.MODULE$.arrayType(paramOptManifest);
/*     */   }
/*     */   
/*     */   public static <T> ClassTag<T> classType(OptManifest<?> paramOptManifest, Class<?> paramClass, Seq<OptManifest<?>> paramSeq) {
/*     */     return ClassManifestFactory$.MODULE$.classType(paramOptManifest, paramClass, paramSeq);
/*     */   }
/*     */   
/*     */   public static <T> ClassTag<T> classType(Class<?> paramClass, OptManifest<?> paramOptManifest, Seq<OptManifest<?>> paramSeq) {
/*     */     return ClassManifestFactory$.MODULE$.classType(paramClass, paramOptManifest, paramSeq);
/*     */   }
/*     */   
/*     */   public static <T> ClassTag<T> classType(Class<?> paramClass) {
/*     */     return ClassManifestFactory$.MODULE$.classType(paramClass);
/*     */   }
/*     */   
/*     */   public static <T> Manifest<T> singleType(Object paramObject) {
/*     */     return ClassManifestFactory$.MODULE$.singleType(paramObject);
/*     */   }
/*     */   
/*     */   public static <T> ClassTag<T> fromClass(Class<T> paramClass) {
/*     */     return ClassManifestFactory$.MODULE$.fromClass(paramClass);
/*     */   }
/*     */   
/*     */   public static Manifest<Null$> Null() {
/*     */     return ClassManifestFactory$.MODULE$.Null();
/*     */   }
/*     */   
/*     */   public static Manifest<Nothing$> Nothing() {
/*     */     return ClassManifestFactory$.MODULE$.Nothing();
/*     */   }
/*     */   
/*     */   public static Manifest<Object> AnyVal() {
/*     */     return ClassManifestFactory$.MODULE$.AnyVal();
/*     */   }
/*     */   
/*     */   public static Manifest<Object> Object() {
/*     */     return ClassManifestFactory$.MODULE$.Object();
/*     */   }
/*     */   
/*     */   public static Manifest<Object> Any() {
/*     */     return ClassManifestFactory$.MODULE$.Any();
/*     */   }
/*     */   
/*     */   public static AnyValManifest<BoxedUnit> Unit() {
/*     */     return ClassManifestFactory$.MODULE$.Unit();
/*     */   }
/*     */   
/*     */   public static AnyValManifest<Object> Boolean() {
/*     */     return ClassManifestFactory$.MODULE$.Boolean();
/*     */   }
/*     */   
/*     */   public static AnyValManifest<Object> Double() {
/*     */     return ClassManifestFactory$.MODULE$.Double();
/*     */   }
/*     */   
/*     */   public static AnyValManifest<Object> Float() {
/*     */     return ClassManifestFactory$.MODULE$.Float();
/*     */   }
/*     */   
/*     */   public static AnyValManifest<Object> Long() {
/*     */     return ClassManifestFactory$.MODULE$.Long();
/*     */   }
/*     */   
/*     */   public static AnyValManifest<Object> Int() {
/*     */     return ClassManifestFactory$.MODULE$.Int();
/*     */   }
/*     */   
/*     */   public static AnyValManifest<Object> Char() {
/*     */     return ClassManifestFactory$.MODULE$.Char();
/*     */   }
/*     */   
/*     */   public static AnyValManifest<Object> Short() {
/*     */     return ClassManifestFactory$.MODULE$.Short();
/*     */   }
/*     */   
/*     */   public static AnyValManifest<Object> Byte() {
/*     */     return ClassManifestFactory$.MODULE$.Byte();
/*     */   }
/*     */   
/*     */   public static class ClassManifestFactory$$anon$1 implements ClassTag<T> {
/*     */     private final List<OptManifest<?>> typeArguments;
/*     */     
/*     */     private final OptManifest prefix$1;
/*     */     
/*     */     private final String name$1;
/*     */     
/*     */     private final Class clazz$1;
/*     */     
/*     */     public ClassTag<Object> wrap() {
/* 210 */       return ClassTag$class.wrap(this);
/*     */     }
/*     */     
/*     */     public Object newArray(int len) {
/* 210 */       return ClassTag$class.newArray(this, len);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(Object x) {
/* 210 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(byte x) {
/* 210 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(short x) {
/* 210 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(char x) {
/* 210 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(int x) {
/* 210 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(long x) {
/* 210 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(float x) {
/* 210 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(double x) {
/* 210 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(boolean x) {
/* 210 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(BoxedUnit x) {
/* 210 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x) {
/* 210 */       return ClassTag$class.canEqual(this, x);
/*     */     }
/*     */     
/*     */     public boolean equals(Object x) {
/* 210 */       return ClassTag$class.equals(this, x);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 210 */       return ClassTag$class.hashCode(this);
/*     */     }
/*     */     
/*     */     public Class<?> erasure() {
/* 210 */       return ClassManifestDeprecatedApis$class.erasure(this);
/*     */     }
/*     */     
/*     */     public boolean $less$colon$less(ClassTag that) {
/* 210 */       return ClassManifestDeprecatedApis$class.$less$colon$less(this, that);
/*     */     }
/*     */     
/*     */     public boolean $greater$colon$greater(ClassTag that) {
/* 210 */       return ClassManifestDeprecatedApis$class.$greater$colon$greater(this, that);
/*     */     }
/*     */     
/*     */     public <T> Class<Object> arrayClass(Class tp) {
/* 210 */       return ClassManifestDeprecatedApis$class.arrayClass(this, tp);
/*     */     }
/*     */     
/*     */     public ClassTag<Object> arrayManifest() {
/* 210 */       return ClassManifestDeprecatedApis$class.arrayManifest(this);
/*     */     }
/*     */     
/*     */     public Object[] newArray2(int len) {
/* 210 */       return ClassManifestDeprecatedApis$class.newArray2(this, len);
/*     */     }
/*     */     
/*     */     public Object[][] newArray3(int len) {
/* 210 */       return ClassManifestDeprecatedApis$class.newArray3(this, len);
/*     */     }
/*     */     
/*     */     public Object[][][] newArray4(int len) {
/* 210 */       return ClassManifestDeprecatedApis$class.newArray4(this, len);
/*     */     }
/*     */     
/*     */     public Object[][][][] newArray5(int len) {
/* 210 */       return ClassManifestDeprecatedApis$class.newArray5(this, len);
/*     */     }
/*     */     
/*     */     public WrappedArray<T> newWrappedArray(int len) {
/* 210 */       return ClassManifestDeprecatedApis$class.newWrappedArray(this, len);
/*     */     }
/*     */     
/*     */     public ArrayBuilder<T> newArrayBuilder() {
/* 210 */       return ClassManifestDeprecatedApis$class.newArrayBuilder(this);
/*     */     }
/*     */     
/*     */     public String argString() {
/* 210 */       return ClassManifestDeprecatedApis$class.argString(this);
/*     */     }
/*     */     
/*     */     public ClassManifestFactory$$anon$1(OptManifest prefix$1, String name$1, Class clazz$1, Seq args$1) {
/* 210 */       ClassManifestDeprecatedApis$class.$init$(this);
/* 210 */       ClassTag$class.$init$(this);
/* 212 */       this.typeArguments = args$1.toList();
/*     */     }
/*     */     
/*     */     public Class<?> runtimeClass() {
/*     */       return this.clazz$1;
/*     */     }
/*     */     
/*     */     public List<OptManifest<?>> typeArguments() {
/* 212 */       return this.typeArguments;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 213 */       return (new StringBuilder()).append(this.prefix$1.toString()).append("#").append(this.name$1).append(argString()).toString();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ClassManifestFactory$$anon$2 implements ClassTag<T> {
/*     */     private final List<OptManifest<?>> typeArguments;
/*     */     
/*     */     private final OptManifest prefix$2;
/*     */     
/*     */     private final String name$2;
/*     */     
/*     */     private final ClassTag upperbound$1;
/*     */     
/*     */     public ClassTag<Object> wrap() {
/* 222 */       return ClassTag$class.wrap(this);
/*     */     }
/*     */     
/*     */     public Object newArray(int len) {
/* 222 */       return ClassTag$class.newArray(this, len);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(Object x) {
/* 222 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(byte x) {
/* 222 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(short x) {
/* 222 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(char x) {
/* 222 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(int x) {
/* 222 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(long x) {
/* 222 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(float x) {
/* 222 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(double x) {
/* 222 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(boolean x) {
/* 222 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(BoxedUnit x) {
/* 222 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x) {
/* 222 */       return ClassTag$class.canEqual(this, x);
/*     */     }
/*     */     
/*     */     public boolean equals(Object x) {
/* 222 */       return ClassTag$class.equals(this, x);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 222 */       return ClassTag$class.hashCode(this);
/*     */     }
/*     */     
/*     */     public Class<?> erasure() {
/* 222 */       return ClassManifestDeprecatedApis$class.erasure(this);
/*     */     }
/*     */     
/*     */     public boolean $less$colon$less(ClassTag that) {
/* 222 */       return ClassManifestDeprecatedApis$class.$less$colon$less(this, that);
/*     */     }
/*     */     
/*     */     public boolean $greater$colon$greater(ClassTag that) {
/* 222 */       return ClassManifestDeprecatedApis$class.$greater$colon$greater(this, that);
/*     */     }
/*     */     
/*     */     public <T> Class<Object> arrayClass(Class tp) {
/* 222 */       return ClassManifestDeprecatedApis$class.arrayClass(this, tp);
/*     */     }
/*     */     
/*     */     public ClassTag<Object> arrayManifest() {
/* 222 */       return ClassManifestDeprecatedApis$class.arrayManifest(this);
/*     */     }
/*     */     
/*     */     public Object[] newArray2(int len) {
/* 222 */       return ClassManifestDeprecatedApis$class.newArray2(this, len);
/*     */     }
/*     */     
/*     */     public Object[][] newArray3(int len) {
/* 222 */       return ClassManifestDeprecatedApis$class.newArray3(this, len);
/*     */     }
/*     */     
/*     */     public Object[][][] newArray4(int len) {
/* 222 */       return ClassManifestDeprecatedApis$class.newArray4(this, len);
/*     */     }
/*     */     
/*     */     public Object[][][][] newArray5(int len) {
/* 222 */       return ClassManifestDeprecatedApis$class.newArray5(this, len);
/*     */     }
/*     */     
/*     */     public WrappedArray<T> newWrappedArray(int len) {
/* 222 */       return ClassManifestDeprecatedApis$class.newWrappedArray(this, len);
/*     */     }
/*     */     
/*     */     public ArrayBuilder<T> newArrayBuilder() {
/* 222 */       return ClassManifestDeprecatedApis$class.newArrayBuilder(this);
/*     */     }
/*     */     
/*     */     public String argString() {
/* 222 */       return ClassManifestDeprecatedApis$class.argString(this);
/*     */     }
/*     */     
/*     */     public ClassManifestFactory$$anon$2(OptManifest prefix$2, String name$2, ClassTag upperbound$1, Seq args$2) {
/* 222 */       ClassManifestDeprecatedApis$class.$init$(this);
/* 222 */       ClassTag$class.$init$(this);
/* 224 */       this.typeArguments = args$2.toList();
/*     */     }
/*     */     
/*     */     public Class<?> runtimeClass() {
/*     */       return this.upperbound$1.erasure();
/*     */     }
/*     */     
/*     */     public List<OptManifest<?>> typeArguments() {
/* 224 */       return this.typeArguments;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 225 */       return (new StringBuilder()).append(this.prefix$2.toString()).append("#").append(this.name$2).append(argString()).toString();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\reflect\ClassManifestFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */