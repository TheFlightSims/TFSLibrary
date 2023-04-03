/*     */ package scala.reflect;
/*     */ 
/*     */ import scala.Equals;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.mutable.ArrayBuilder;
/*     */ import scala.collection.mutable.WrappedArray;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\tueaB\001\003!\003\r\ta\002\002\t\0072\f7o\035+bO*\0211\001B\001\be\0264G.Z2u\025\005)\021!B:dC2\f7\001A\013\003\021M\031R\001A\005\0169}\001\"AC\006\016\003\021I!\001\004\003\003\r\005s\027PU3g!\rqq\"E\007\002\005%\021\001C\001\002\034\0072\f7o]'b]&4Wm\035;EKB\024XmY1uK\022\f\005/[:\021\005I\031B\002\001\003\006)\001\021\r!\006\002\002)F\021a#\007\t\003\025]I!\001\007\003\003\0179{G\017[5oOB\021!BG\005\0037\021\0211!\0218z!\tQQ$\003\002\037\t\t1Q)];bYN\004\"A\003\021\n\005\005\"!\001D*fe&\fG.\033>bE2,\007\"B\022\001\t\003!\023A\002\023j]&$H\005F\001&!\tQa%\003\002(\t\t!QK\\5u\021\025I\003A\"\001+\0031\021XO\034;j[\026\034E.Y:t+\005Y\003G\001\0276!\ri#\007N\007\002])\021q\006M\001\005Y\006twMC\0012\003\021Q\027M^1\n\005Mr#!B\"mCN\034\bC\001\n6\t%1\004&!A\001\002\013\005QCA\002`IEBQ\001\017\001\005\002e\nAa\036:baV\t!\bE\002\017\001m\0022A\003\037\022\023\tiDAA\003BeJ\f\027\020C\003@\001\021\005\003)\001\005oK^\f%O]1z)\tY\024\tC\003C}\001\0071)A\002mK:\004\"A\003#\n\005\025#!aA%oi\")q\t\001C\001\021\0069QO\\1qa2LHCA%M!\rQ!*E\005\003\027\022\021aa\0249uS>t\007\"B'G\001\004I\022!\001=\t\013\035\003A\021A(\025\005%\003\006\"B'O\001\004\t\006C\001\006S\023\t\031FA\001\003CsR,\007\"B$\001\t\003)FCA%W\021\025iE\0131\001X!\tQ\001,\003\002Z\t\t)1\013[8si\")q\t\001C\0017R\021\021\n\030\005\006\033j\003\r!\030\t\003\025yK!a\030\003\003\t\rC\027M\035\005\006\017\002!\t!\031\013\003\023\nDQ!\0241A\002\rCQa\022\001\005\002\021$\"!S3\t\0135\033\007\031\0014\021\005)9\027B\0015\005\005\021auN\\4\t\013\035\003A\021\0016\025\005%[\007\"B'j\001\004a\007C\001\006n\023\tqGAA\003GY>\fG\017C\003H\001\021\005\001\017\006\002Jc\")Qj\034a\001eB\021!b]\005\003i\022\021a\001R8vE2,\007\"B$\001\t\0031HCA%x\021\025iU\0171\001y!\tQ\0210\003\002{\t\t9!i\\8mK\006t\007\"B$\001\t\003aHCA%~\021\025i5\0201\001&\021\031y\b\001\"\003\002\002\005aQO\\1qa2Lx,[7qYV!\0211AA\b)\021\t)!a\005\025\007%\0139\001C\005\002\ny\f\t\021q\001\002\f\005QQM^5eK:\034W\rJ\031\021\t9\001\021Q\002\t\004%\005=AABA\t}\n\007QCA\001V\021\031ie\0201\001\002\016!9\021q\003\001\005B\005e\021\001C2b]\026\013X/\0317\025\007a\fY\002\003\004N\003+\001\r!\007\005\b\003?\001A\021IA\021\003\031)\027/^1mgR\031\0010a\t\t\r5\013i\0021\001\032\021\035\t9\003\001C!\003S\t\001\002[1tQ\016{G-\032\013\002\007\"9\021Q\006\001\005B\005=\022\001\003;p'R\024\030N\\4\025\005\005E\002\003BA\032\003sq1ACA\033\023\r\t9\004B\001\007!J,G-\0324\n\t\005m\022Q\b\002\007'R\024\030N\\4\013\007\005]B\001K\003\001\003\003\ni\005\005\003\002D\005%SBAA#\025\r\t9\005B\001\013C:tw\016^1uS>t\027\002BA&\003\013\022\001#[7qY&\034\027\016\036(pi\032{WO\0343\"\005\005=\023A\b(pA\rc\027m]:UC\036\004\023M^1jY\006\024G.\032\021g_J\004Ce\037+~\017\035\t\031F\001E\001\003+\n\001b\0217bgN$\026m\032\t\004\035\005]cAB\001\003\021\003\tIf\005\003\002X%y\002\002CA/\003/\"\t!a\030\002\rqJg.\033;?)\t\t)\006\003\006\002d\005]#\031!C\005\003K\n!b\0242kK\016$H+\027)F+\t\t9\007\005\003.e\005%\004cA\027\002l%\031\021Q\016\030\003\r=\023'.Z2u\021%\t\t(a\026!\002\023\t9'A\006PE*,7\r\036+Z!\026\003\003BCA;\003/\022\r\021\"\003\002x\005Yaj\034;iS:<G+\027)F+\t\tI\b\005\003.e\005m\004\003BA?\003\007k!!a \013\007\005\005E!A\004sk:$\030.\\3\n\t\005\025\025q\020\002\t\035>$\b.\0338hI!I\021\021RA,A\003%\021\021P\001\r\035>$\b.\0338h)f\003V\t\t\005\013\003\033\0139F1A\005\n\005=\025\001\003(vY2$\026\fU#\026\005\005E\005\003B\0273\003'\003B!! \002\026&!\021qSA@\005\025qU\017\0347%\021%\tY*a\026!\002\023\t\t*A\005Ok2dG+\027)FA!Q\021qTA,\005\004%\t!!)\002\t\tKH/Z\013\003\003G\0032A\004\001R\021%\t9+a\026!\002\023\t\031+A\003CsR,\007\005\003\006\002,\006]#\031!C\001\003[\013Qa\0255peR,\"!a,\021\0079\001q\013C\005\0024\006]\003\025!\003\0020\00611\013[8si\002B!\"a.\002X\t\007I\021AA]\003\021\031\005.\031:\026\005\005m\006c\001\b\001;\"I\021qXA,A\003%\0211X\001\006\007\"\f'\017\t\005\013\003\007\f9F1A\005\002\005\025\027aA%oiV\021\021q\031\t\004\035\001\031\005\"CAf\003/\002\013\021BAd\003\021Ie\016\036\021\t\025\005=\027q\013b\001\n\003\t\t.\001\003M_:<WCAAj!\rq\001A\032\005\n\003/\f9\006)A\005\003'\fQ\001T8oO\002B!\"a7\002X\t\007I\021AAo\003\0251En\\1u+\t\ty\016E\002\017\0011D\021\"a9\002X\001\006I!a8\002\r\031cw.\031;!\021)\t9/a\026C\002\023\005\021\021^\001\007\t>,(\r\\3\026\005\005-\bc\001\b\001e\"I\021q^A,A\003%\0211^\001\b\t>,(\r\\3!\021)\t\0310a\026C\002\023\005\021Q_\001\b\005>|G.Z1o+\t\t9\020E\002\017\001aD\021\"a?\002X\001\006I!a>\002\021\t{w\016\\3b]\002B!\"a@\002X\t\007I\021\001B\001\003\021)f.\033;\026\005\t\r\001c\001\b\001K!I!qAA,A\003%!1A\001\006+:LG\017\t\005\013\005\027\t9F1A\005\002\t5\021aA!osV\021!q\002\t\004\035\001I\002\"\003B\n\003/\002\013\021\002B\b\003\021\te.\037\021\t\025\t]\021q\013b\001\n\003\021I\"\001\004PE*,7\r^\013\003\0057\001BA\004\001\002j!I!qDA,A\003%!1D\001\b\037\nTWm\031;!\021)\021\031#a\026C\002\023\005!QE\001\007\003:Lh+\0317\026\005\t\035\002\003\002\b\001\005S\0012A\003B\026\023\r\021i\003\002\002\007\003:Lh+\0317\t\023\tE\022q\013Q\001\n\t\035\022aB!osZ\013G\016\t\005\013\005k\t9F1A\005\002\t]\022AB!osJ+g-\006\002\003:A\031a\002A\005\t\023\tu\022q\013Q\001\n\te\022aB!osJ+g\r\t\005\013\005\003\n9F1A\005\002\t\r\023a\002(pi\"LgnZ\013\003\005\013\0022A\004\001\027\021%\021I%a\026!\002\023\021)%\001\005O_RD\027N\\4!\021)\021i%a\026C\002\023\005!qJ\001\005\035VdG.\006\002\003RA!a\002\001B*!\rQ!QK\005\004\005/\"!\001\002(vY2D\021Ba\027\002X\001\006IA!\025\002\0139+H\016\034\021\t\021\t}\023q\013C\001\005C\nQ!\0319qYf,BAa\031\003jQ!!Q\rB6!\021q\001Aa\032\021\007I\021I\007\002\004\025\005;\022\r!\006\005\t\005[\022i\0061\001\003p\005i!/\0368uS6,7\t\\1tgF\002DA!\035\003vA!QF\rB:!\r\021\"Q\017\003\f\005o\022Y'!A\001\002\013\005QCA\002`IYBqaRA,\t\003\021Y(\006\003\003~\tUE\003\002B@\005\033\003BA\003&\003\002B\"!1\021BE!\031\t\031D!\"\003\b&\0311'!\020\021\007I\021I\tB\006\003\f\ne\024\021!A\001\006\003)\"aA0%o!A!q\022B=\001\004\021\t*\001\003di\006<\007\003\002\b\001\005'\0032A\005BK\t\031!\"\021\020b\001+!Q!\021TA,\003\003%IAa'\002\027I,\027\r\032*fg>dg/\032\013\003\003S\002")
/*     */ public interface ClassTag<T> extends ClassManifestDeprecatedApis<T>, Equals, Serializable {
/*     */   Class<?> runtimeClass();
/*     */   
/*     */   ClassTag<Object> wrap();
/*     */   
/*     */   Object newArray(int paramInt);
/*     */   
/*     */   Option<T> unapply(Object paramObject);
/*     */   
/*     */   Option<T> unapply(byte paramByte);
/*     */   
/*     */   Option<T> unapply(short paramShort);
/*     */   
/*     */   Option<T> unapply(char paramChar);
/*     */   
/*     */   Option<T> unapply(int paramInt);
/*     */   
/*     */   Option<T> unapply(long paramLong);
/*     */   
/*     */   Option<T> unapply(float paramFloat);
/*     */   
/*     */   Option<T> unapply(double paramDouble);
/*     */   
/*     */   Option<T> unapply(boolean paramBoolean);
/*     */   
/*     */   Option<T> unapply(BoxedUnit paramBoxedUnit);
/*     */   
/*     */   boolean canEqual(Object paramObject);
/*     */   
/*     */   boolean equals(Object paramObject);
/*     */   
/*     */   int hashCode();
/*     */   
/*     */   String toString();
/*     */   
/*     */   public static class ClassTag$$anon$1 implements ClassTag<T> {
/*     */     private final Class runtimeClass1$1;
/*     */     
/*     */     public ClassTag<Object> wrap() {
/* 144 */       return ClassTag$class.wrap(this);
/*     */     }
/*     */     
/*     */     public Object newArray(int len) {
/* 144 */       return ClassTag$class.newArray(this, len);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(Object x) {
/* 144 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(byte x) {
/* 144 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(short x) {
/* 144 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(char x) {
/* 144 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(int x) {
/* 144 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(long x) {
/* 144 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(float x) {
/* 144 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(double x) {
/* 144 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(boolean x) {
/* 144 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(BoxedUnit x) {
/* 144 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x) {
/* 144 */       return ClassTag$class.canEqual(this, x);
/*     */     }
/*     */     
/*     */     public boolean equals(Object x) {
/* 144 */       return ClassTag$class.equals(this, x);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 144 */       return ClassTag$class.hashCode(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 144 */       return ClassTag$class.toString(this);
/*     */     }
/*     */     
/*     */     public Class<?> erasure() {
/* 144 */       return ClassManifestDeprecatedApis$class.erasure(this);
/*     */     }
/*     */     
/*     */     public boolean $less$colon$less(ClassTag that) {
/* 144 */       return ClassManifestDeprecatedApis$class.$less$colon$less(this, that);
/*     */     }
/*     */     
/*     */     public boolean $greater$colon$greater(ClassTag that) {
/* 144 */       return ClassManifestDeprecatedApis$class.$greater$colon$greater(this, that);
/*     */     }
/*     */     
/*     */     public <T> Class<Object> arrayClass(Class tp) {
/* 144 */       return ClassManifestDeprecatedApis$class.arrayClass(this, tp);
/*     */     }
/*     */     
/*     */     public ClassTag<Object> arrayManifest() {
/* 144 */       return ClassManifestDeprecatedApis$class.arrayManifest(this);
/*     */     }
/*     */     
/*     */     public Object[] newArray2(int len) {
/* 144 */       return ClassManifestDeprecatedApis$class.newArray2(this, len);
/*     */     }
/*     */     
/*     */     public Object[][] newArray3(int len) {
/* 144 */       return ClassManifestDeprecatedApis$class.newArray3(this, len);
/*     */     }
/*     */     
/*     */     public Object[][][] newArray4(int len) {
/* 144 */       return ClassManifestDeprecatedApis$class.newArray4(this, len);
/*     */     }
/*     */     
/*     */     public Object[][][][] newArray5(int len) {
/* 144 */       return ClassManifestDeprecatedApis$class.newArray5(this, len);
/*     */     }
/*     */     
/*     */     public WrappedArray<T> newWrappedArray(int len) {
/* 144 */       return ClassManifestDeprecatedApis$class.newWrappedArray(this, len);
/*     */     }
/*     */     
/*     */     public ArrayBuilder<T> newArrayBuilder() {
/* 144 */       return ClassManifestDeprecatedApis$class.newArrayBuilder(this);
/*     */     }
/*     */     
/*     */     public List<OptManifest<?>> typeArguments() {
/* 144 */       return ClassManifestDeprecatedApis$class.typeArguments(this);
/*     */     }
/*     */     
/*     */     public String argString() {
/* 144 */       return ClassManifestDeprecatedApis$class.argString(this);
/*     */     }
/*     */     
/*     */     public Class<?> runtimeClass() {
/* 144 */       return this.runtimeClass1$1;
/*     */     }
/*     */     
/*     */     public ClassTag$$anon$1(Class runtimeClass1$1) {
/* 144 */       ClassManifestDeprecatedApis$class.$init$(this);
/* 144 */       ClassTag$class.$init$(this);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\reflect\ClassTag.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */