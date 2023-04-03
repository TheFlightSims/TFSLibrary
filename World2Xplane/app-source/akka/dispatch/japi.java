/*     */ package akka.dispatch;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\035t!B\001\003\021\0039\021\001\0026ba&T!a\001\003\002\021\021L7\017]1uG\"T\021!B\001\005C.\\\027m\001\001\021\005!IQ\"\001\002\007\013)\021\001\022A\006\003\t)\f\007/[\n\003\0231\001\"!\004\t\016\0039Q\021aD\001\006g\016\fG.Y\005\003#9\021a!\0218z%\0264\007\"B\n\n\t\003!\022A\002\037j]&$h\bF\001\b\r\0211\022\002A\f\003\035\r\013G\016\0342bG.\024%/\0333hKV\021\001$I\n\003+e\001BAG\017 U5\t1D\003\002\035\035\0059!/\0368uS6,\027B\001\020\034\005]\t%m\035;sC\016$\b+\031:uS\006dg)\0368di&|g\016\005\002!C1\001AA\002\022\026\021\013\0071EA\001U#\t!s\005\005\002\016K%\021aE\004\002\b\035>$\b.\0338h!\ti\001&\003\002*\035\t\031\021I\\=\021\005iY\023B\001\027\034\005%\021u\016_3e+:LG\017C\003\024+\021\005a\006F\0010!\r\001TcH\007\002\023!)!'\006C#g\005Y\021n\035#fM&tW\rZ!u)\t!t\007\005\002\016k%\021aG\004\002\b\005>|G.Z1o\021\025A\024\0071\001 \003\005!\b\"\002\036\026\t\013Z\024!B1qa2LHC\001\026=\021\025A\024\b1\001 \021\025qT\003\"\005@\003!Ig\016^3s]\006dGC\001!D!\ti\021)\003\002C\035\t!QK\\5u\021\025!U\b1\001 \003\031\021Xm];mi\"\"QCR%L!\tiq)\003\002I\035\tQA-\0329sK\016\fG/\0323\"\003)\013\001\007R8!]>$\b%^:fAQD\027n\035\021eSJ,7\r\0367zY\001*8/\032\021tk\n\034G.Y:tKN\004sN\032\021uQ&\034\030%\001'\002\007Ir\003G\002\003O\023\001y%!\004*fG>4XM\035\"sS\022<W-\006\002Q?N\021Q*\025\t\0055u\021f\f\005\002T7:\021A+\027\b\003+bk\021A\026\006\003/\032\ta\001\020:p_Rt\024\"A\b\n\005is\021a\0029bG.\fw-Z\005\0039v\023\021\002\0265s_^\f'\r\\3\013\005is\001C\001\021`\t\031\021S\n\"b\001G!)1#\024C\001CR\t!\rE\0021\033zCQAM'\005F\021$\"\001N3\t\013a\032\007\031\001*\t\013ijEQI4\025\005yC\007\"\002\035g\001\004\021\006\"\002 N\t#QGC\0010l\021\025!\025\0161\001SQ\021ie)\\&\"\0039\fq\005R8!]>$\b%^:fAQD\027n\035\021eSJ,7\r\0367zY\001*8/\032\021(%\026\034wN^3sO\031!\001/\003\001r\005U\021un\0347fC:4UO\\2uS>t'I]5eO\026,\"A]<\024\007=d1\017\005\003\016iZ$\024BA;\017\005%1UO\\2uS>t\027\007\005\002!o\0221!e\034EC\002\rBQaE8\005\002e$\022A\037\t\004a=4\b\"\002\036p\t\013bHC\001\033~\021\025A4\0201\001w\021\025qt\016\"\005\000)\r!\024\021\001\005\006\tz\004\rA\036\025\005_\032K5J\002\004\002\b%\001\021\021\002\002\023+:LGOR;oGRLwN\034\"sS\022<W-\006\003\002\f\005E1#BA\003\031\0055\001#B\007u\003\037Q\003c\001\021\002\022\0219!%!\002\t\006\004\031\003bB\n\002\006\021\005\021Q\003\013\003\003/\001R\001MA\003\003\037A\001\"a\007\002\006\021\025\021QD\001\016CB\004H.\037\023nG2SEe\0359\025\007)\ny\002\003\005\002\"\005e\001\031AA\022\003\005a\007cA\007\002&%\031\021q\005\b\003\t1{gn\032\005\t\003W\t)\001\"\002\002.\005i\021\r\0359ms\022j7\rT%%gB$2AKA\030\021!\t\t$!\013A\002\005M\022!A5\021\0075\t)$C\002\00289\0211!\0238u\021!\tY$!\002\005\006\005u\022!D1qa2LH%\\2M\r\022\032\b\017F\002+\003A\001\"!\021\002:\001\007\0211I\001\002MB\031Q\"!\022\n\007\005\035cBA\003GY>\fG\017\003\005\002L\005\025AQAA'\0035\t\007\017\0357zI5\034G\n\022\023taR\031!&a\024\t\021\005E\023\021\na\001\003'\n\021\001\032\t\004\033\005U\023bAA,\035\t1Ai\\;cY\026DqAOA\003\t\013\nY\006F\002+\003;Bq\001OA-\001\004\ty\001C\004?\003\013!\t\"!\031\025\007\001\013\031\007C\004E\003?\002\r!a\004)\013\005\025a)S&")
/*     */ public final class japi {
/*     */   public static class CallbackBridge<T> extends AbstractPartialFunction<T, BoxedUnit> {
/*     */     public final boolean isDefinedAt(Object t) {
/* 172 */       return true;
/*     */     }
/*     */     
/*     */     public final BoxedUnit apply(Object t) {
/* 174 */       internal((T)t);
/* 175 */       return BoxedUnit.UNIT;
/*     */     }
/*     */     
/*     */     public void internal(Object result) {}
/*     */   }
/*     */   
/*     */   public static class RecoverBridge<T> extends AbstractPartialFunction<Throwable, T> {
/*     */     public final boolean isDefinedAt(Throwable t) {
/* 182 */       return true;
/*     */     }
/*     */     
/*     */     public final T apply(Throwable t) {
/* 183 */       return internal(t);
/*     */     }
/*     */     
/*     */     public T internal(Throwable result) {
/* 184 */       null;
/* 184 */       return null;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BooleanFunctionBridge<T> implements Function1<T, Object> {
/*     */     public boolean apply$mcZD$sp(double v1) {
/* 188 */       return Function1.class.apply$mcZD$sp(this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDD$sp(double v1) {
/* 188 */       return Function1.class.apply$mcDD$sp(this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFD$sp(double v1) {
/* 188 */       return Function1.class.apply$mcFD$sp(this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcID$sp(double v1) {
/* 188 */       return Function1.class.apply$mcID$sp(this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJD$sp(double v1) {
/* 188 */       return Function1.class.apply$mcJD$sp(this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVD$sp(double v1) {
/* 188 */       Function1.class.apply$mcVD$sp(this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZF$sp(float v1) {
/* 188 */       return Function1.class.apply$mcZF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDF$sp(float v1) {
/* 188 */       return Function1.class.apply$mcDF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFF$sp(float v1) {
/* 188 */       return Function1.class.apply$mcFF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcIF$sp(float v1) {
/* 188 */       return Function1.class.apply$mcIF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJF$sp(float v1) {
/* 188 */       return Function1.class.apply$mcJF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVF$sp(float v1) {
/* 188 */       Function1.class.apply$mcVF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZI$sp(int v1) {
/* 188 */       return Function1.class.apply$mcZI$sp(this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDI$sp(int v1) {
/* 188 */       return Function1.class.apply$mcDI$sp(this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFI$sp(int v1) {
/* 188 */       return Function1.class.apply$mcFI$sp(this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcII$sp(int v1) {
/* 188 */       return Function1.class.apply$mcII$sp(this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJI$sp(int v1) {
/* 188 */       return Function1.class.apply$mcJI$sp(this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVI$sp(int v1) {
/* 188 */       Function1.class.apply$mcVI$sp(this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZJ$sp(long v1) {
/* 188 */       return Function1.class.apply$mcZJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDJ$sp(long v1) {
/* 188 */       return Function1.class.apply$mcDJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFJ$sp(long v1) {
/* 188 */       return Function1.class.apply$mcFJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcIJ$sp(long v1) {
/* 188 */       return Function1.class.apply$mcIJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJJ$sp(long v1) {
/* 188 */       return Function1.class.apply$mcJJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVJ$sp(long v1) {
/* 188 */       Function1.class.apply$mcVJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose(Function1 g) {
/* 188 */       return Function1.class.compose(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/* 188 */       return Function1.class.compose$mcZD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/* 188 */       return Function1.class.compose$mcDD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/* 188 */       return Function1.class.compose$mcFD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/* 188 */       return Function1.class.compose$mcID$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/* 188 */       return Function1.class.compose$mcJD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/* 188 */       return Function1.class.compose$mcVD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/* 188 */       return Function1.class.compose$mcZF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/* 188 */       return Function1.class.compose$mcDF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/* 188 */       return Function1.class.compose$mcFF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/* 188 */       return Function1.class.compose$mcIF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/* 188 */       return Function1.class.compose$mcJF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/* 188 */       return Function1.class.compose$mcVF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/* 188 */       return Function1.class.compose$mcZI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/* 188 */       return Function1.class.compose$mcDI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/* 188 */       return Function1.class.compose$mcFI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/* 188 */       return Function1.class.compose$mcII$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/* 188 */       return Function1.class.compose$mcJI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/* 188 */       return Function1.class.compose$mcVI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/* 188 */       return Function1.class.compose$mcZJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/* 188 */       return Function1.class.compose$mcDJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/* 188 */       return Function1.class.compose$mcFJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/* 188 */       return Function1.class.compose$mcIJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/* 188 */       return Function1.class.compose$mcJJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/* 188 */       return Function1.class.compose$mcVJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<T, A> andThen(Function1 g) {
/* 188 */       return Function1.class.andThen(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/* 188 */       return Function1.class.andThen$mcZD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/* 188 */       return Function1.class.andThen$mcDD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/* 188 */       return Function1.class.andThen$mcFD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/* 188 */       return Function1.class.andThen$mcID$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/* 188 */       return Function1.class.andThen$mcJD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/* 188 */       return Function1.class.andThen$mcVD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/* 188 */       return Function1.class.andThen$mcZF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/* 188 */       return Function1.class.andThen$mcDF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/* 188 */       return Function1.class.andThen$mcFF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/* 188 */       return Function1.class.andThen$mcIF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/* 188 */       return Function1.class.andThen$mcJF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/* 188 */       return Function1.class.andThen$mcVF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/* 188 */       return Function1.class.andThen$mcZI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/* 188 */       return Function1.class.andThen$mcDI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/* 188 */       return Function1.class.andThen$mcFI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/* 188 */       return Function1.class.andThen$mcII$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/* 188 */       return Function1.class.andThen$mcJI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/* 188 */       return Function1.class.andThen$mcVI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/* 188 */       return Function1.class.andThen$mcZJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/* 188 */       return Function1.class.andThen$mcDJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/* 188 */       return Function1.class.andThen$mcFJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/* 188 */       return Function1.class.andThen$mcIJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/* 188 */       return Function1.class.andThen$mcJJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/* 188 */       return Function1.class.andThen$mcVJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 188 */       return Function1.class.toString(this);
/*     */     }
/*     */     
/*     */     public BooleanFunctionBridge() {
/* 188 */       Function1.class.$init$(this);
/*     */     }
/*     */     
/*     */     public final boolean apply(Object t) {
/* 189 */       return internal((T)t);
/*     */     }
/*     */     
/*     */     public boolean internal(Object result) {
/* 190 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class UnitFunctionBridge<T> implements Function1<T, BoxedUnit> {
/*     */     public boolean apply$mcZD$sp(double v1) {
/* 194 */       return Function1.class.apply$mcZD$sp(this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDD$sp(double v1) {
/* 194 */       return Function1.class.apply$mcDD$sp(this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFD$sp(double v1) {
/* 194 */       return Function1.class.apply$mcFD$sp(this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcID$sp(double v1) {
/* 194 */       return Function1.class.apply$mcID$sp(this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJD$sp(double v1) {
/* 194 */       return Function1.class.apply$mcJD$sp(this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVD$sp(double v1) {
/* 194 */       Function1.class.apply$mcVD$sp(this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZF$sp(float v1) {
/* 194 */       return Function1.class.apply$mcZF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDF$sp(float v1) {
/* 194 */       return Function1.class.apply$mcDF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFF$sp(float v1) {
/* 194 */       return Function1.class.apply$mcFF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcIF$sp(float v1) {
/* 194 */       return Function1.class.apply$mcIF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJF$sp(float v1) {
/* 194 */       return Function1.class.apply$mcJF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVF$sp(float v1) {
/* 194 */       Function1.class.apply$mcVF$sp(this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZI$sp(int v1) {
/* 194 */       return Function1.class.apply$mcZI$sp(this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDI$sp(int v1) {
/* 194 */       return Function1.class.apply$mcDI$sp(this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFI$sp(int v1) {
/* 194 */       return Function1.class.apply$mcFI$sp(this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcII$sp(int v1) {
/* 194 */       return Function1.class.apply$mcII$sp(this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJI$sp(int v1) {
/* 194 */       return Function1.class.apply$mcJI$sp(this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVI$sp(int v1) {
/* 194 */       Function1.class.apply$mcVI$sp(this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZJ$sp(long v1) {
/* 194 */       return Function1.class.apply$mcZJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDJ$sp(long v1) {
/* 194 */       return Function1.class.apply$mcDJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFJ$sp(long v1) {
/* 194 */       return Function1.class.apply$mcFJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcIJ$sp(long v1) {
/* 194 */       return Function1.class.apply$mcIJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJJ$sp(long v1) {
/* 194 */       return Function1.class.apply$mcJJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVJ$sp(long v1) {
/* 194 */       Function1.class.apply$mcVJ$sp(this, v1);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose(Function1 g) {
/* 194 */       return Function1.class.compose(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/* 194 */       return Function1.class.compose$mcZD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/* 194 */       return Function1.class.compose$mcDD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/* 194 */       return Function1.class.compose$mcFD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/* 194 */       return Function1.class.compose$mcID$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/* 194 */       return Function1.class.compose$mcJD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/* 194 */       return Function1.class.compose$mcVD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/* 194 */       return Function1.class.compose$mcZF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/* 194 */       return Function1.class.compose$mcDF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/* 194 */       return Function1.class.compose$mcFF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/* 194 */       return Function1.class.compose$mcIF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/* 194 */       return Function1.class.compose$mcJF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/* 194 */       return Function1.class.compose$mcVF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/* 194 */       return Function1.class.compose$mcZI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/* 194 */       return Function1.class.compose$mcDI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/* 194 */       return Function1.class.compose$mcFI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/* 194 */       return Function1.class.compose$mcII$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/* 194 */       return Function1.class.compose$mcJI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/* 194 */       return Function1.class.compose$mcVI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/* 194 */       return Function1.class.compose$mcZJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/* 194 */       return Function1.class.compose$mcDJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/* 194 */       return Function1.class.compose$mcFJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/* 194 */       return Function1.class.compose$mcIJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/* 194 */       return Function1.class.compose$mcJJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/* 194 */       return Function1.class.compose$mcVJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<T, A> andThen(Function1 g) {
/* 194 */       return Function1.class.andThen(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/* 194 */       return Function1.class.andThen$mcZD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/* 194 */       return Function1.class.andThen$mcDD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/* 194 */       return Function1.class.andThen$mcFD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/* 194 */       return Function1.class.andThen$mcID$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/* 194 */       return Function1.class.andThen$mcJD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/* 194 */       return Function1.class.andThen$mcVD$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/* 194 */       return Function1.class.andThen$mcZF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/* 194 */       return Function1.class.andThen$mcDF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/* 194 */       return Function1.class.andThen$mcFF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/* 194 */       return Function1.class.andThen$mcIF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/* 194 */       return Function1.class.andThen$mcJF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/* 194 */       return Function1.class.andThen$mcVF$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/* 194 */       return Function1.class.andThen$mcZI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/* 194 */       return Function1.class.andThen$mcDI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/* 194 */       return Function1.class.andThen$mcFI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/* 194 */       return Function1.class.andThen$mcII$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/* 194 */       return Function1.class.andThen$mcJI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/* 194 */       return Function1.class.andThen$mcVI$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/* 194 */       return Function1.class.andThen$mcZJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/* 194 */       return Function1.class.andThen$mcDJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/* 194 */       return Function1.class.andThen$mcFJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/* 194 */       return Function1.class.andThen$mcIJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/* 194 */       return Function1.class.andThen$mcJJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/* 194 */       return Function1.class.andThen$mcVJ$sp(this, g);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 194 */       return Function1.class.toString(this);
/*     */     }
/*     */     
/*     */     public UnitFunctionBridge() {
/* 194 */       Function1.class.$init$(this);
/*     */     }
/*     */     
/*     */     public final BoxedUnit apply$mcLJ$sp(long l) {
/* 195 */       internal((T)BoxesRunTime.boxToLong(l));
/* 195 */       return BoxedUnit.UNIT;
/*     */     }
/*     */     
/*     */     public final BoxedUnit apply$mcLI$sp(int i) {
/* 196 */       internal((T)BoxesRunTime.boxToInteger(i));
/* 196 */       return BoxedUnit.UNIT;
/*     */     }
/*     */     
/*     */     public final BoxedUnit apply$mcLF$sp(float f) {
/* 197 */       internal((T)BoxesRunTime.boxToFloat(f));
/* 197 */       return BoxedUnit.UNIT;
/*     */     }
/*     */     
/*     */     public final BoxedUnit apply$mcLD$sp(double d) {
/* 198 */       internal((T)BoxesRunTime.boxToDouble(d));
/* 198 */       return BoxedUnit.UNIT;
/*     */     }
/*     */     
/*     */     public final BoxedUnit apply(Object t) {
/* 199 */       internal((T)t);
/* 199 */       return BoxedUnit.UNIT;
/*     */     }
/*     */     
/*     */     public void internal(Object result) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\japi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */