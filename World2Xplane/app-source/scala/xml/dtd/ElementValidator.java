/*     */ package scala.xml.dtd;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.PartialFunction$;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.IterableLike;
/*     */ import scala.collection.LinearSeqOptimized;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Seq$;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.List$;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.NonLocalReturnControl;
/*     */ import scala.runtime.Nothing$;
/*     */ import scala.runtime.ObjectRef;
/*     */ import scala.util.automata.DetWordAutom;
/*     */ import scala.util.automata.NondetWordAutom;
/*     */ import scala.util.automata.SubsetConstruction;
/*     */ import scala.util.regexp.Base;
/*     */ import scala.xml.Atom;
/*     */ import scala.xml.MetaData;
/*     */ import scala.xml.Node;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\025b\001B\001\003\001%\021\001#\0227f[\026tGOV1mS\022\fGo\034:\013\005\r!\021a\0013uI*\021QAB\001\004q6d'\"A\004\002\013M\034\027\r\\1\004\001M\031\001A\003\b\021\005-aQ\"\001\004\n\00551!AB!osJ+g\r\005\003\f\037E)\022B\001\t\007\005%1UO\\2uS>t\027\007\005\002\023'5\tA!\003\002\025\t\t!aj\0343f!\tYa#\003\002\030\r\t9!i\\8mK\006t\007\"B\r\001\t\003Q\022A\002\037j]&$h\bF\001\034!\ta\002!D\001\003\021\035q\002\0011A\005\n}\t1!\032=d+\005\001\003cA\021*Y9\021!e\n\b\003G\031j\021\001\n\006\003K!\ta\001\020:p_Rt\024\"A\004\n\005!2\021a\0029bG.\fw-Z\005\003U-\022A\001T5ti*\021\001F\002\t\00395J!A\f\002\003'Y\013G.\0333bi&|g.\022=dKB$\030n\0348\t\017A\002\001\031!C\005c\0059Q\r_2`I\025\fHC\001\0326!\tY1'\003\0025\r\t!QK\\5u\021\0351t&!AA\002\001\n1\001\037\0232\021\031A\004\001)Q\005A\005!Q\r_2!\021%Q\004\0011AA\002\023E1(\001\007d_:$XM\034;N_\022,G.F\001=!\taR(\003\002?\005\ta1i\0348uK:$Xj\0343fY\"I\001\t\001a\001\002\004%\t\"Q\001\021G>tG/\0328u\033>$W\r\\0%KF$\"A\r\"\t\017Yz\024\021!a\001y!1A\t\001Q!\nq\nQbY8oi\026tG/T8eK2\004\003\"\003$\001\001\004\005\r\021\"\005H\003\r!g-Y\013\002\021B\031\021J\024)\016\003)S!a\023'\002\021\005,Ho\\7bi\006T!!\024\004\002\tU$\030\016\\\005\003\037*\023A\002R3u/>\024H-Q;u_6\004\"!\025+\017\005q\021\026BA*\003\0031\031uN\034;f]Rlu\016Z3m\023\t)fK\001\005FY\026lg*Y7f\025\t\031&\001C\005Y\001\001\007\t\031!C\t3\0069AMZ1`I\025\fHC\001\032[\021\0351t+!AA\002!Ca\001\030\001!B\023A\025\001\0023gC\002B\021B\030\001A\002\003\007I\021C0\002\r\005$Wm\0317t+\005\001\007cA\021*CB\021ADY\005\003G\n\021\001\"\021;ue\022+7\r\034\005\nK\002\001\r\0211A\005\022\031\f!\"\0313fG2\034x\fJ3r)\t\021t\rC\0047I\006\005\t\031\0011\t\r%\004\001\025)\003a\003\035\tG-Z2mg\002BQa\033\001\005\0021\fqb]3u\007>tG/\0328u\033>$W\r\034\013\003e5DQA\0346A\002q\n!aY7\t\013A\004A\021A\036\002\037\035,GoQ8oi\026tG/T8eK2DQA\035\001\005\002M\f1b]3u\033\026$\030\rR1uCR\021!\007\036\005\006=F\004\r\001\031\005\006m\002!\ta^\001\fO\026$\030\n^3sC\ndW\r\006\003yw\006\005\001cA\021z!&\021!p\013\002\t\023R,'/\0312mK\")A0\036a\001{\006)an\0343fgB\031\021E`\t\n\005}\\#aA*fc\"1\0211A;A\002U\t!b]6jaB\033E)\021+B\021\035\t9\001\001C\001\003\023\tQa\0315fG.$2!FA\006\021!\ti!!\002A\002\005=\021AA7e!\r\021\022\021C\005\004\003'!!\001C'fi\006$\025\r^1\t\017\005\035\001\001\"\001\002\030Q\031Q#!\007\t\rq\f)\0021\001~\021\035\ti\002\001C\001\003?\tQ!\0319qYf$2!FA\021\021\035\t\031#a\007A\002E\t\021A\034")
/*     */ public class ElementValidator implements Function1<Node, Object> {
/*     */   private List<ValidationException> scala$xml$dtd$ElementValidator$$exc;
/*     */   
/*     */   private ContentModel contentModel;
/*     */   
/*     */   private DetWordAutom<ContentModel.ElemName> dfa;
/*     */   
/*     */   private List<AttrDecl> adecls;
/*     */   
/*     */   public boolean apply$mcZD$sp(double v1) {
/*  23 */     return Function1.class.apply$mcZD$sp(this, v1);
/*     */   }
/*     */   
/*     */   public double apply$mcDD$sp(double v1) {
/*  23 */     return Function1.class.apply$mcDD$sp(this, v1);
/*     */   }
/*     */   
/*     */   public float apply$mcFD$sp(double v1) {
/*  23 */     return Function1.class.apply$mcFD$sp(this, v1);
/*     */   }
/*     */   
/*     */   public int apply$mcID$sp(double v1) {
/*  23 */     return Function1.class.apply$mcID$sp(this, v1);
/*     */   }
/*     */   
/*     */   public long apply$mcJD$sp(double v1) {
/*  23 */     return Function1.class.apply$mcJD$sp(this, v1);
/*     */   }
/*     */   
/*     */   public void apply$mcVD$sp(double v1) {
/*  23 */     Function1.class.apply$mcVD$sp(this, v1);
/*     */   }
/*     */   
/*     */   public boolean apply$mcZF$sp(float v1) {
/*  23 */     return Function1.class.apply$mcZF$sp(this, v1);
/*     */   }
/*     */   
/*     */   public double apply$mcDF$sp(float v1) {
/*  23 */     return Function1.class.apply$mcDF$sp(this, v1);
/*     */   }
/*     */   
/*     */   public float apply$mcFF$sp(float v1) {
/*  23 */     return Function1.class.apply$mcFF$sp(this, v1);
/*     */   }
/*     */   
/*     */   public int apply$mcIF$sp(float v1) {
/*  23 */     return Function1.class.apply$mcIF$sp(this, v1);
/*     */   }
/*     */   
/*     */   public long apply$mcJF$sp(float v1) {
/*  23 */     return Function1.class.apply$mcJF$sp(this, v1);
/*     */   }
/*     */   
/*     */   public void apply$mcVF$sp(float v1) {
/*  23 */     Function1.class.apply$mcVF$sp(this, v1);
/*     */   }
/*     */   
/*     */   public boolean apply$mcZI$sp(int v1) {
/*  23 */     return Function1.class.apply$mcZI$sp(this, v1);
/*     */   }
/*     */   
/*     */   public double apply$mcDI$sp(int v1) {
/*  23 */     return Function1.class.apply$mcDI$sp(this, v1);
/*     */   }
/*     */   
/*     */   public float apply$mcFI$sp(int v1) {
/*  23 */     return Function1.class.apply$mcFI$sp(this, v1);
/*     */   }
/*     */   
/*     */   public int apply$mcII$sp(int v1) {
/*  23 */     return Function1.class.apply$mcII$sp(this, v1);
/*     */   }
/*     */   
/*     */   public long apply$mcJI$sp(int v1) {
/*  23 */     return Function1.class.apply$mcJI$sp(this, v1);
/*     */   }
/*     */   
/*     */   public void apply$mcVI$sp(int v1) {
/*  23 */     Function1.class.apply$mcVI$sp(this, v1);
/*     */   }
/*     */   
/*     */   public boolean apply$mcZJ$sp(long v1) {
/*  23 */     return Function1.class.apply$mcZJ$sp(this, v1);
/*     */   }
/*     */   
/*     */   public double apply$mcDJ$sp(long v1) {
/*  23 */     return Function1.class.apply$mcDJ$sp(this, v1);
/*     */   }
/*     */   
/*     */   public float apply$mcFJ$sp(long v1) {
/*  23 */     return Function1.class.apply$mcFJ$sp(this, v1);
/*     */   }
/*     */   
/*     */   public int apply$mcIJ$sp(long v1) {
/*  23 */     return Function1.class.apply$mcIJ$sp(this, v1);
/*     */   }
/*     */   
/*     */   public long apply$mcJJ$sp(long v1) {
/*  23 */     return Function1.class.apply$mcJJ$sp(this, v1);
/*     */   }
/*     */   
/*     */   public void apply$mcVJ$sp(long v1) {
/*  23 */     Function1.class.apply$mcVJ$sp(this, v1);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose(Function1 g) {
/*  23 */     return Function1.class.compose(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/*  23 */     return Function1.class.compose$mcZD$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/*  23 */     return Function1.class.compose$mcDD$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/*  23 */     return Function1.class.compose$mcFD$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/*  23 */     return Function1.class.compose$mcID$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/*  23 */     return Function1.class.compose$mcJD$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/*  23 */     return Function1.class.compose$mcVD$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/*  23 */     return Function1.class.compose$mcZF$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/*  23 */     return Function1.class.compose$mcDF$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/*  23 */     return Function1.class.compose$mcFF$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/*  23 */     return Function1.class.compose$mcIF$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/*  23 */     return Function1.class.compose$mcJF$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/*  23 */     return Function1.class.compose$mcVF$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/*  23 */     return Function1.class.compose$mcZI$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/*  23 */     return Function1.class.compose$mcDI$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/*  23 */     return Function1.class.compose$mcFI$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/*  23 */     return Function1.class.compose$mcII$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/*  23 */     return Function1.class.compose$mcJI$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/*  23 */     return Function1.class.compose$mcVI$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/*  23 */     return Function1.class.compose$mcZJ$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/*  23 */     return Function1.class.compose$mcDJ$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/*  23 */     return Function1.class.compose$mcFJ$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/*  23 */     return Function1.class.compose$mcIJ$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/*  23 */     return Function1.class.compose$mcJJ$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/*  23 */     return Function1.class.compose$mcVJ$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Node, A> andThen(Function1 g) {
/*  23 */     return Function1.class.andThen(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/*  23 */     return Function1.class.andThen$mcZD$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/*  23 */     return Function1.class.andThen$mcDD$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/*  23 */     return Function1.class.andThen$mcFD$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/*  23 */     return Function1.class.andThen$mcID$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/*  23 */     return Function1.class.andThen$mcJD$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/*  23 */     return Function1.class.andThen$mcVD$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/*  23 */     return Function1.class.andThen$mcZF$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/*  23 */     return Function1.class.andThen$mcDF$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/*  23 */     return Function1.class.andThen$mcFF$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/*  23 */     return Function1.class.andThen$mcIF$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/*  23 */     return Function1.class.andThen$mcJF$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/*  23 */     return Function1.class.andThen$mcVF$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/*  23 */     return Function1.class.andThen$mcZI$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/*  23 */     return Function1.class.andThen$mcDI$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/*  23 */     return Function1.class.andThen$mcFI$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/*  23 */     return Function1.class.andThen$mcII$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/*  23 */     return Function1.class.andThen$mcJI$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/*  23 */     return Function1.class.andThen$mcVI$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/*  23 */     return Function1.class.andThen$mcZJ$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/*  23 */     return Function1.class.andThen$mcDJ$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/*  23 */     return Function1.class.andThen$mcFJ$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/*  23 */     return Function1.class.andThen$mcIJ$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/*  23 */     return Function1.class.andThen$mcJJ$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/*  23 */     return Function1.class.andThen$mcVJ$sp(this, g);
/*     */   }
/*     */   
/*     */   public String toString() {
/*  23 */     return Function1.class.toString(this);
/*     */   }
/*     */   
/*     */   public ElementValidator() {
/*  23 */     Function1.class.$init$(this);
/*  25 */     this.scala$xml$dtd$ElementValidator$$exc = (List<ValidationException>)Nil$.MODULE$;
/*     */   }
/*     */   
/*     */   public List<ValidationException> scala$xml$dtd$ElementValidator$$exc() {
/*  25 */     return this.scala$xml$dtd$ElementValidator$$exc;
/*     */   }
/*     */   
/*     */   public void scala$xml$dtd$ElementValidator$$exc_$eq(List<ValidationException> x$1) {
/*  25 */     this.scala$xml$dtd$ElementValidator$$exc = x$1;
/*     */   }
/*     */   
/*     */   public ContentModel contentModel() {
/*  27 */     return this.contentModel;
/*     */   }
/*     */   
/*     */   public void contentModel_$eq(ContentModel x$1) {
/*  27 */     this.contentModel = x$1;
/*     */   }
/*     */   
/*     */   public DetWordAutom<ContentModel.ElemName> dfa() {
/*  28 */     return this.dfa;
/*     */   }
/*     */   
/*     */   public void dfa_$eq(DetWordAutom<ContentModel.ElemName> x$1) {
/*  28 */     this.dfa = x$1;
/*     */   }
/*     */   
/*     */   public List<AttrDecl> adecls() {
/*  29 */     return this.adecls;
/*     */   }
/*     */   
/*     */   public void adecls_$eq(List<AttrDecl> x$1) {
/*  29 */     this.adecls = x$1;
/*     */   }
/*     */   
/*     */   public void setContentModel(ContentModel cm) {
/*  33 */     contentModel_$eq(cm);
/*  34 */     if (cm instanceof ELEMENTS) {
/*  34 */       ELEMENTS eLEMENTS = (ELEMENTS)cm;
/*  36 */       NondetWordAutom nfa = ContentModel.Translator$.MODULE$.automatonFrom(eLEMENTS.r(), 1);
/*  37 */       dfa_$eq((new SubsetConstruction(nfa)).determinize());
/*     */     } else {
/*  39 */       dfa_$eq(null);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ContentModel getContentModel() {
/*  43 */     return contentModel();
/*     */   }
/*     */   
/*     */   public void setMetaData(List<AttrDecl> adecls) {
/*  46 */     adecls_$eq(adecls);
/*     */   }
/*     */   
/*     */   public final boolean scala$xml$dtd$ElementValidator$$isAllWhitespace$1(Atom a) {
/*  49 */     return PartialFunction$.MODULE$.cond(a.data(), (PartialFunction)new ElementValidator$$anonfun$scala$xml$dtd$ElementValidator$$isAllWhitespace$1$1(this));
/*     */   }
/*     */   
/*     */   public class ElementValidator$$anonfun$scala$xml$dtd$ElementValidator$$isAllWhitespace$1$1 extends AbstractPartialFunction<Object, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final <A1, B1> B1 applyOrElse(Object x1, Function1 default) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: instanceof java/lang/String
/*     */       //   4: ifeq -> 46
/*     */       //   7: aload_1
/*     */       //   8: checkcast java/lang/String
/*     */       //   11: astore_3
/*     */       //   12: aload_3
/*     */       //   13: invokevirtual trim : ()Ljava/lang/String;
/*     */       //   16: dup
/*     */       //   17: ifnonnull -> 29
/*     */       //   20: pop
/*     */       //   21: ldc ''
/*     */       //   23: ifnull -> 37
/*     */       //   26: goto -> 46
/*     */       //   29: ldc ''
/*     */       //   31: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   34: ifeq -> 46
/*     */       //   37: iconst_1
/*     */       //   38: invokestatic boxToBoolean : (Z)Ljava/lang/Boolean;
/*     */       //   41: astore #4
/*     */       //   43: goto -> 55
/*     */       //   46: aload_2
/*     */       //   47: aload_1
/*     */       //   48: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */       //   53: astore #4
/*     */       //   55: aload #4
/*     */       //   57: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #49	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	58	0	this	Lscala/xml/dtd/ElementValidator$$anonfun$scala$xml$dtd$ElementValidator$$isAllWhitespace$1$1;
/*     */       //   0	58	1	x1	Ljava/lang/Object;
/*     */       //   0	58	2	default	Lscala/Function1;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Object x1) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: instanceof java/lang/String
/*     */       //   4: ifeq -> 42
/*     */       //   7: aload_1
/*     */       //   8: checkcast java/lang/String
/*     */       //   11: astore_2
/*     */       //   12: aload_2
/*     */       //   13: invokevirtual trim : ()Ljava/lang/String;
/*     */       //   16: dup
/*     */       //   17: ifnonnull -> 29
/*     */       //   20: pop
/*     */       //   21: ldc ''
/*     */       //   23: ifnull -> 37
/*     */       //   26: goto -> 42
/*     */       //   29: ldc ''
/*     */       //   31: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   34: ifeq -> 42
/*     */       //   37: iconst_1
/*     */       //   38: istore_3
/*     */       //   39: goto -> 44
/*     */       //   42: iconst_0
/*     */       //   43: istore_3
/*     */       //   44: iload_3
/*     */       //   45: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #49	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	46	0	this	Lscala/xml/dtd/ElementValidator$$anonfun$scala$xml$dtd$ElementValidator$$isAllWhitespace$1$1;
/*     */       //   0	46	1	x1	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public ElementValidator$$anonfun$scala$xml$dtd$ElementValidator$$isAllWhitespace$1$1(ElementValidator $outer) {}
/*     */   }
/*     */   
/*     */   public Iterable<ContentModel.ElemName> getIterable(Seq nodes, boolean skipPCDATA) {
/*  51 */     return (Iterable<ContentModel.ElemName>)((TraversableLike)nodes.filter((Function1)new ElementValidator$$anonfun$getIterable$1(this, skipPCDATA)))
/*     */       
/*  57 */       .map((Function1)new ElementValidator$$anonfun$getIterable$2(this), Seq$.MODULE$.canBuildFrom());
/*     */   }
/*     */   
/*     */   public class ElementValidator$$anonfun$getIterable$1 extends AbstractFunction1<Node, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final boolean skipPCDATA$1;
/*     */     
/*     */     public ElementValidator$$anonfun$getIterable$1(ElementValidator $outer, boolean skipPCDATA$1) {}
/*     */     
/*     */     public final boolean apply(Node x0$1) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: instanceof scala/xml/SpecialNode
/*     */       //   4: ifeq -> 62
/*     */       //   7: aload_1
/*     */       //   8: checkcast scala/xml/SpecialNode
/*     */       //   11: astore_2
/*     */       //   12: aload_2
/*     */       //   13: instanceof scala/xml/Atom
/*     */       //   16: ifeq -> 41
/*     */       //   19: aload_2
/*     */       //   20: checkcast scala/xml/Atom
/*     */       //   23: astore_3
/*     */       //   24: aload_0
/*     */       //   25: getfield $outer : Lscala/xml/dtd/ElementValidator;
/*     */       //   28: aload_3
/*     */       //   29: invokevirtual scala$xml$dtd$ElementValidator$$isAllWhitespace$1 : (Lscala/xml/Atom;)Z
/*     */       //   32: ifeq -> 41
/*     */       //   35: iconst_0
/*     */       //   36: istore #4
/*     */       //   38: goto -> 55
/*     */       //   41: aload_0
/*     */       //   42: getfield skipPCDATA$1 : Z
/*     */       //   45: ifeq -> 52
/*     */       //   48: iconst_0
/*     */       //   49: goto -> 53
/*     */       //   52: iconst_1
/*     */       //   53: istore #4
/*     */       //   55: iload #4
/*     */       //   57: istore #5
/*     */       //   59: goto -> 76
/*     */       //   62: aload_1
/*     */       //   63: invokevirtual namespace : ()Ljava/lang/String;
/*     */       //   66: ifnonnull -> 73
/*     */       //   69: iconst_1
/*     */       //   70: goto -> 74
/*     */       //   73: iconst_0
/*     */       //   74: istore #5
/*     */       //   76: iload #5
/*     */       //   78: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #52	-> 0
/*     */       //   #51	-> 0
/*     */       //   #53	-> 12
/*     */       //   #54	-> 41
/*     */       //   #52	-> 55
/*     */       //   #56	-> 62
/*     */       //   #51	-> 76
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	79	0	this	Lscala/xml/dtd/ElementValidator$$anonfun$getIterable$1;
/*     */       //   0	79	1	x0$1	Lscala/xml/Node;
/*     */     }
/*     */   }
/*     */   
/*     */   public class ElementValidator$$anonfun$getIterable$2 extends AbstractFunction1<Node, ContentModel.ElemName> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final ContentModel.ElemName apply(Node x) {
/*  57 */       return new ContentModel.ElemName(x.label());
/*     */     }
/*     */     
/*     */     public ElementValidator$$anonfun$getIterable$2(ElementValidator $outer) {}
/*     */   }
/*     */   
/*     */   public boolean check(MetaData md) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokevirtual scala$xml$dtd$ElementValidator$$exc : ()Lscala/collection/immutable/List;
/*     */     //   4: invokevirtual length : ()I
/*     */     //   7: istore_2
/*     */     //   8: new scala/runtime/ObjectRef
/*     */     //   11: dup
/*     */     //   12: new scala/collection/mutable/BitSet
/*     */     //   15: dup
/*     */     //   16: aload_0
/*     */     //   17: invokevirtual adecls : ()Lscala/collection/immutable/List;
/*     */     //   20: invokevirtual length : ()I
/*     */     //   23: invokespecial <init> : (I)V
/*     */     //   26: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   29: astore #5
/*     */     //   31: aload_1
/*     */     //   32: new scala/xml/dtd/ElementValidator$$anonfun$check$1
/*     */     //   35: dup
/*     */     //   36: aload_0
/*     */     //   37: aload #5
/*     */     //   39: invokespecial <init> : (Lscala/xml/dtd/ElementValidator;Lscala/runtime/ObjectRef;)V
/*     */     //   42: invokevirtual foreach : (Lscala/Function1;)V
/*     */     //   45: aload_0
/*     */     //   46: invokevirtual adecls : ()Lscala/collection/immutable/List;
/*     */     //   49: getstatic scala/collection/immutable/List$.MODULE$ : Lscala/collection/immutable/List$;
/*     */     //   52: invokevirtual ReusableCBF : ()Lscala/collection/generic/GenTraversableFactory$GenericCanBuildFrom;
/*     */     //   55: invokevirtual zipWithIndex : (Lscala/collection/generic/CanBuildFrom;)Ljava/lang/Object;
/*     */     //   58: checkcast scala/collection/immutable/List
/*     */     //   61: astore #7
/*     */     //   63: aload #7
/*     */     //   65: invokevirtual isEmpty : ()Z
/*     */     //   68: ifeq -> 88
/*     */     //   71: aload_0
/*     */     //   72: invokevirtual scala$xml$dtd$ElementValidator$$exc : ()Lscala/collection/immutable/List;
/*     */     //   75: invokevirtual length : ()I
/*     */     //   78: iload_2
/*     */     //   79: if_icmpne -> 86
/*     */     //   82: iconst_1
/*     */     //   83: goto -> 87
/*     */     //   86: iconst_0
/*     */     //   87: ireturn
/*     */     //   88: aload #7
/*     */     //   90: invokevirtual head : ()Ljava/lang/Object;
/*     */     //   93: checkcast scala/Tuple2
/*     */     //   96: astore #6
/*     */     //   98: aload #6
/*     */     //   100: ifnull -> 209
/*     */     //   103: aload #6
/*     */     //   105: invokevirtual _1 : ()Ljava/lang/Object;
/*     */     //   108: ifnull -> 209
/*     */     //   111: getstatic scala/xml/dtd/REQUIRED$.MODULE$ : Lscala/xml/dtd/REQUIRED$;
/*     */     //   114: aload #6
/*     */     //   116: invokevirtual _1 : ()Ljava/lang/Object;
/*     */     //   119: checkcast scala/xml/dtd/AttrDecl
/*     */     //   122: invokevirtual default : ()Lscala/xml/dtd/DefaultDecl;
/*     */     //   125: astore #4
/*     */     //   127: dup
/*     */     //   128: ifnonnull -> 140
/*     */     //   131: pop
/*     */     //   132: aload #4
/*     */     //   134: ifnull -> 148
/*     */     //   137: goto -> 209
/*     */     //   140: aload #4
/*     */     //   142: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   145: ifeq -> 209
/*     */     //   148: aload #5
/*     */     //   150: getfield elem : Ljava/lang/Object;
/*     */     //   153: checkcast scala/collection/mutable/BitSet
/*     */     //   156: aload #6
/*     */     //   158: invokevirtual _2$mcI$sp : ()I
/*     */     //   161: invokestatic boxToInteger : (I)Ljava/lang/Integer;
/*     */     //   164: invokevirtual apply : (Ljava/lang/Object;)Z
/*     */     //   167: ifne -> 209
/*     */     //   170: aload_0
/*     */     //   171: aload_0
/*     */     //   172: invokevirtual scala$xml$dtd$ElementValidator$$exc : ()Lscala/collection/immutable/List;
/*     */     //   175: getstatic scala/xml/dtd/MakeValidationException$.MODULE$ : Lscala/xml/dtd/MakeValidationException$;
/*     */     //   178: aload #6
/*     */     //   180: invokevirtual _1 : ()Ljava/lang/Object;
/*     */     //   183: checkcast scala/xml/dtd/AttrDecl
/*     */     //   186: invokevirtual name : ()Ljava/lang/String;
/*     */     //   189: aload #6
/*     */     //   191: invokevirtual _1 : ()Ljava/lang/Object;
/*     */     //   194: checkcast scala/xml/dtd/AttrDecl
/*     */     //   197: invokevirtual tpe : ()Ljava/lang/String;
/*     */     //   200: invokevirtual fromMissingAttribute : (Ljava/lang/String;Ljava/lang/String;)Lscala/xml/dtd/ValidationException;
/*     */     //   203: invokevirtual $colon$colon : (Ljava/lang/Object;)Lscala/collection/immutable/List;
/*     */     //   206: invokevirtual scala$xml$dtd$ElementValidator$$exc_$eq : (Lscala/collection/immutable/List;)V
/*     */     //   209: aload #7
/*     */     //   211: invokevirtual tail : ()Ljava/lang/Object;
/*     */     //   214: checkcast scala/collection/immutable/List
/*     */     //   217: astore #7
/*     */     //   219: goto -> 63
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #63	-> 0
/*     */     //   #64	-> 8
/*     */     //   #66	-> 31
/*     */     //   #87	-> 45
/*     */     //   #92	-> 71
/*     */     //   #62	-> 87
/*     */     //   #87	-> 88
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	222	0	this	Lscala/xml/dtd/ElementValidator;
/*     */     //   0	222	1	md	Lscala/xml/MetaData;
/*     */     //   8	214	2	len	I
/*     */     //   31	191	5	ok	Lscala/runtime/ObjectRef;
/*     */     //   63	159	7	these1	Lscala/collection/immutable/List;
/*     */   }
/*     */   
/*     */   public class ElementValidator$$anonfun$check$1 extends AbstractFunction1<MetaData, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final ObjectRef ok$1;
/*     */     
/*     */     public ElementValidator$$anonfun$check$1(ElementValidator $outer, ObjectRef ok$1) {}
/*     */     
/*     */     private final String attrStr$1(MetaData attr$1) {
/*  67 */       return attr$1.value().toString();
/*     */     }
/*     */     
/*     */     private final Option find$1(String Key) {
/*  68 */       Object object = new Object();
/*     */       try {
/*  69 */         ((LinearSeqOptimized)this.$outer.adecls().zipWithIndex(List$.MODULE$.canBuildFrom())).find((Function1)new ElementValidator$$anonfun$check$1$$anonfun$find$1$1(this, Key, object));
/*     */       } catch (NonLocalReturnControl nonLocalReturnControl2) {
/*     */         NonLocalReturnControl nonLocalReturnControl1;
/*     */         if ((nonLocalReturnControl1 = null).key() == object)
/*     */           return (Option)nonLocalReturnControl1.value(); 
/*     */       } 
/*  73 */       return (Option)None$.MODULE$;
/*     */     }
/*     */     
/*     */     public class ElementValidator$$anonfun$check$1$$anonfun$find$1$1 extends AbstractFunction1<Tuple2<AttrDecl, Object>, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final String Key$1;
/*     */       
/*     */       private final Object nonLocalReturnKey1$1;
/*     */       
/*     */       public final boolean apply(Tuple2 x0$2) {
/*     */         // Byte code:
/*     */         //   0: aload_1
/*     */         //   1: ifnull -> 89
/*     */         //   4: aload_1
/*     */         //   5: invokevirtual _1 : ()Ljava/lang/Object;
/*     */         //   8: ifnull -> 89
/*     */         //   11: aload_0
/*     */         //   12: getfield Key$1 : Ljava/lang/String;
/*     */         //   15: aload_1
/*     */         //   16: invokevirtual _1 : ()Ljava/lang/Object;
/*     */         //   19: checkcast scala/xml/dtd/AttrDecl
/*     */         //   22: invokevirtual name : ()Ljava/lang/String;
/*     */         //   25: astore_2
/*     */         //   26: dup
/*     */         //   27: ifnonnull -> 38
/*     */         //   30: pop
/*     */         //   31: aload_2
/*     */         //   32: ifnull -> 45
/*     */         //   35: goto -> 89
/*     */         //   38: aload_2
/*     */         //   39: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */         //   42: ifeq -> 89
/*     */         //   45: aload_0
/*     */         //   46: getfield $outer : Lscala/xml/dtd/ElementValidator$$anonfun$check$1;
/*     */         //   49: getfield ok$1 : Lscala/runtime/ObjectRef;
/*     */         //   52: getfield elem : Ljava/lang/Object;
/*     */         //   55: checkcast scala/collection/mutable/BitSet
/*     */         //   58: aload_1
/*     */         //   59: invokevirtual _2$mcI$sp : ()I
/*     */         //   62: invokevirtual $plus$eq : (I)Lscala/collection/mutable/BitSet;
/*     */         //   65: pop
/*     */         //   66: new scala/runtime/NonLocalReturnControl
/*     */         //   69: dup
/*     */         //   70: aload_0
/*     */         //   71: getfield nonLocalReturnKey1$1 : Ljava/lang/Object;
/*     */         //   74: new scala/Some
/*     */         //   77: dup
/*     */         //   78: aload_1
/*     */         //   79: invokevirtual _1 : ()Ljava/lang/Object;
/*     */         //   82: invokespecial <init> : (Ljava/lang/Object;)V
/*     */         //   85: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;)V
/*     */         //   88: athrow
/*     */         //   89: iconst_0
/*     */         //   90: ireturn
/*     */         // Line number table:
/*     */         //   Java source line number -> byte code offset
/*     */         //   #69	-> 0
/*     */         //   #70	-> 11
/*     */         //   #69	-> 15
/*     */         //   #70	-> 22
/*     */         //   #69	-> 58
/*     */         //   #70	-> 59
/*     */         //   #69	-> 78
/*     */         //   #70	-> 79
/*     */         //   #69	-> 89
/*     */         // Local variable table:
/*     */         //   start	length	slot	name	descriptor
/*     */         //   0	91	0	this	Lscala/xml/dtd/ElementValidator$$anonfun$check$1$$anonfun$find$1$1;
/*     */         //   0	91	1	x0$2	Lscala/Tuple2;
/*     */       }
/*     */       
/*     */       public ElementValidator$$anonfun$check$1$$anonfun$find$1$1(ElementValidator$$anonfun$check$1 $outer, String Key$1, Object nonLocalReturnKey1$1) {}
/*     */     }
/*     */     
/*     */     public final void apply(MetaData attr) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: invokevirtual key : ()Ljava/lang/String;
/*     */       //   5: invokespecial find$1 : (Ljava/lang/String;)Lscala/Option;
/*     */       //   8: astore_2
/*     */       //   9: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */       //   12: dup
/*     */       //   13: ifnonnull -> 24
/*     */       //   16: pop
/*     */       //   17: aload_2
/*     */       //   18: ifnull -> 31
/*     */       //   21: goto -> 61
/*     */       //   24: aload_2
/*     */       //   25: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   28: ifeq -> 61
/*     */       //   31: aload_0
/*     */       //   32: getfield $outer : Lscala/xml/dtd/ElementValidator;
/*     */       //   35: aload_0
/*     */       //   36: getfield $outer : Lscala/xml/dtd/ElementValidator;
/*     */       //   39: invokevirtual scala$xml$dtd$ElementValidator$$exc : ()Lscala/collection/immutable/List;
/*     */       //   42: getstatic scala/xml/dtd/MakeValidationException$.MODULE$ : Lscala/xml/dtd/MakeValidationException$;
/*     */       //   45: aload_1
/*     */       //   46: invokevirtual key : ()Ljava/lang/String;
/*     */       //   49: invokevirtual fromUndefinedAttribute : (Ljava/lang/String;)Lscala/xml/dtd/ValidationException;
/*     */       //   52: invokevirtual $colon$colon : (Ljava/lang/Object;)Lscala/collection/immutable/List;
/*     */       //   55: invokevirtual scala$xml$dtd$ElementValidator$$exc_$eq : (Lscala/collection/immutable/List;)V
/*     */       //   58: goto -> 198
/*     */       //   61: aload_2
/*     */       //   62: instanceof scala/Some
/*     */       //   65: ifeq -> 198
/*     */       //   68: aload_2
/*     */       //   69: checkcast scala/Some
/*     */       //   72: astore_3
/*     */       //   73: aload_3
/*     */       //   74: invokevirtual x : ()Ljava/lang/Object;
/*     */       //   77: ifnull -> 198
/*     */       //   80: aload_3
/*     */       //   81: invokevirtual x : ()Ljava/lang/Object;
/*     */       //   84: checkcast scala/xml/dtd/AttrDecl
/*     */       //   87: invokevirtual default : ()Lscala/xml/dtd/DefaultDecl;
/*     */       //   90: instanceof scala/xml/dtd/DEFAULT
/*     */       //   93: ifeq -> 198
/*     */       //   96: aload_3
/*     */       //   97: invokevirtual x : ()Ljava/lang/Object;
/*     */       //   100: checkcast scala/xml/dtd/AttrDecl
/*     */       //   103: invokevirtual default : ()Lscala/xml/dtd/DefaultDecl;
/*     */       //   106: checkcast scala/xml/dtd/DEFAULT
/*     */       //   109: astore #5
/*     */       //   111: iconst_1
/*     */       //   112: aload #5
/*     */       //   114: invokevirtual fixed : ()Z
/*     */       //   117: if_icmpne -> 198
/*     */       //   120: aload_1
/*     */       //   121: invokevirtual value : ()Lscala/collection/Seq;
/*     */       //   124: invokeinterface toString : ()Ljava/lang/String;
/*     */       //   129: aload #5
/*     */       //   131: invokevirtual attValue : ()Ljava/lang/String;
/*     */       //   134: astore #4
/*     */       //   136: dup
/*     */       //   137: ifnonnull -> 149
/*     */       //   140: pop
/*     */       //   141: aload #4
/*     */       //   143: ifnull -> 198
/*     */       //   146: goto -> 157
/*     */       //   149: aload #4
/*     */       //   151: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   154: ifne -> 198
/*     */       //   157: aload_0
/*     */       //   158: getfield $outer : Lscala/xml/dtd/ElementValidator;
/*     */       //   161: aload_0
/*     */       //   162: getfield $outer : Lscala/xml/dtd/ElementValidator;
/*     */       //   165: invokevirtual scala$xml$dtd$ElementValidator$$exc : ()Lscala/collection/immutable/List;
/*     */       //   168: getstatic scala/xml/dtd/MakeValidationException$.MODULE$ : Lscala/xml/dtd/MakeValidationException$;
/*     */       //   171: aload_1
/*     */       //   172: invokevirtual key : ()Ljava/lang/String;
/*     */       //   175: aload #5
/*     */       //   177: invokevirtual attValue : ()Ljava/lang/String;
/*     */       //   180: aload_1
/*     */       //   181: invokevirtual value : ()Lscala/collection/Seq;
/*     */       //   184: invokeinterface toString : ()Ljava/lang/String;
/*     */       //   189: invokevirtual fromFixedAttribute : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lscala/xml/dtd/ValidationException;
/*     */       //   192: invokevirtual $colon$colon : (Ljava/lang/Object;)Lscala/collection/immutable/List;
/*     */       //   195: invokevirtual scala$xml$dtd$ElementValidator$$exc_$eq : (Lscala/collection/immutable/List;)V
/*     */       //   198: return
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #76	-> 0
/*     */       //   #77	-> 9
/*     */       //   #78	-> 31
/*     */       //   #80	-> 61
/*     */       //   #76	-> 81
/*     */       //   #80	-> 87
/*     */       //   #76	-> 97
/*     */       //   #80	-> 103
/*     */       //   #76	-> 112
/*     */       //   #80	-> 114
/*     */       //   #76	-> 129
/*     */       //   #80	-> 131
/*     */       //   #81	-> 157
/*     */       //   #76	-> 175
/*     */       //   #81	-> 177
/*     */       //   #76	-> 198
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	199	0	this	Lscala/xml/dtd/ElementValidator$$anonfun$check$1;
/*     */       //   0	199	1	attr	Lscala/xml/MetaData;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean check(Seq nodes) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokevirtual contentModel : ()Lscala/xml/dtd/ContentModel;
/*     */     //   4: astore #6
/*     */     //   6: getstatic scala/xml/dtd/ANY$.MODULE$ : Lscala/xml/dtd/ANY$;
/*     */     //   9: dup
/*     */     //   10: ifnonnull -> 22
/*     */     //   13: pop
/*     */     //   14: aload #6
/*     */     //   16: ifnull -> 30
/*     */     //   19: goto -> 36
/*     */     //   22: aload #6
/*     */     //   24: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   27: ifeq -> 36
/*     */     //   30: iconst_1
/*     */     //   31: istore #7
/*     */     //   33: goto -> 307
/*     */     //   36: getstatic scala/xml/dtd/EMPTY$.MODULE$ : Lscala/xml/dtd/EMPTY$;
/*     */     //   39: dup
/*     */     //   40: ifnonnull -> 52
/*     */     //   43: pop
/*     */     //   44: aload #6
/*     */     //   46: ifnull -> 60
/*     */     //   49: goto -> 76
/*     */     //   52: aload #6
/*     */     //   54: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   57: ifeq -> 76
/*     */     //   60: aload_0
/*     */     //   61: aload_1
/*     */     //   62: iconst_0
/*     */     //   63: invokevirtual getIterable : (Lscala/collection/Seq;Z)Lscala/collection/Iterable;
/*     */     //   66: invokeinterface isEmpty : ()Z
/*     */     //   71: istore #7
/*     */     //   73: goto -> 307
/*     */     //   76: getstatic scala/xml/dtd/PCDATA$.MODULE$ : Lscala/xml/dtd/PCDATA$;
/*     */     //   79: dup
/*     */     //   80: ifnonnull -> 92
/*     */     //   83: pop
/*     */     //   84: aload #6
/*     */     //   86: ifnull -> 100
/*     */     //   89: goto -> 116
/*     */     //   92: aload #6
/*     */     //   94: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   97: ifeq -> 116
/*     */     //   100: aload_0
/*     */     //   101: aload_1
/*     */     //   102: iconst_1
/*     */     //   103: invokevirtual getIterable : (Lscala/collection/Seq;Z)Lscala/collection/Iterable;
/*     */     //   106: invokeinterface isEmpty : ()Z
/*     */     //   111: istore #7
/*     */     //   113: goto -> 307
/*     */     //   116: aload #6
/*     */     //   118: instanceof scala/xml/dtd/MIXED
/*     */     //   121: ifeq -> 258
/*     */     //   124: aload #6
/*     */     //   126: checkcast scala/xml/dtd/MIXED
/*     */     //   129: astore_2
/*     */     //   130: aload_2
/*     */     //   131: invokevirtual r : ()Lscala/util/regexp/Base$RegExp;
/*     */     //   134: instanceof scala/util/regexp/Base$Alt
/*     */     //   137: ifeq -> 258
/*     */     //   140: aload_2
/*     */     //   141: invokevirtual r : ()Lscala/util/regexp/Base$RegExp;
/*     */     //   144: checkcast scala/util/regexp/Base$Alt
/*     */     //   147: astore_3
/*     */     //   148: getstatic scala/xml/dtd/ContentModel$.MODULE$ : Lscala/xml/dtd/ContentModel$;
/*     */     //   151: invokevirtual Alt : ()Lscala/util/regexp/Base$Alt$;
/*     */     //   154: aload_3
/*     */     //   155: invokevirtual unapplySeq : (Lscala/util/regexp/Base$Alt;)Lscala/Some;
/*     */     //   158: astore #4
/*     */     //   160: aload #4
/*     */     //   162: invokevirtual isEmpty : ()Z
/*     */     //   165: ifne -> 258
/*     */     //   168: aload_0
/*     */     //   169: invokevirtual scala$xml$dtd$ElementValidator$$exc : ()Lscala/collection/immutable/List;
/*     */     //   172: invokevirtual length : ()I
/*     */     //   175: istore #5
/*     */     //   177: aload_0
/*     */     //   178: aload_1
/*     */     //   179: iconst_1
/*     */     //   180: invokevirtual getIterable : (Lscala/collection/Seq;Z)Lscala/collection/Iterable;
/*     */     //   183: new scala/xml/dtd/ElementValidator$$anonfun$check$3
/*     */     //   186: dup
/*     */     //   187: aload_0
/*     */     //   188: invokespecial <init> : (Lscala/xml/dtd/ElementValidator;)V
/*     */     //   191: getstatic scala/collection/Iterable$.MODULE$ : Lscala/collection/Iterable$;
/*     */     //   194: invokevirtual canBuildFrom : ()Lscala/collection/generic/CanBuildFrom;
/*     */     //   197: invokeinterface map : (Lscala/Function1;Lscala/collection/generic/CanBuildFrom;)Ljava/lang/Object;
/*     */     //   202: checkcast scala/collection/TraversableLike
/*     */     //   205: new scala/xml/dtd/ElementValidator$$anonfun$check$4
/*     */     //   208: dup
/*     */     //   209: aload_0
/*     */     //   210: aload #4
/*     */     //   212: invokespecial <init> : (Lscala/xml/dtd/ElementValidator;Lscala/Option;)V
/*     */     //   215: invokeinterface filterNot : (Lscala/Function1;)Ljava/lang/Object;
/*     */     //   220: checkcast scala/collection/IterableLike
/*     */     //   223: new scala/xml/dtd/ElementValidator$$anonfun$check$5
/*     */     //   226: dup
/*     */     //   227: aload_0
/*     */     //   228: invokespecial <init> : (Lscala/xml/dtd/ElementValidator;)V
/*     */     //   231: invokeinterface foreach : (Lscala/Function1;)V
/*     */     //   236: aload_0
/*     */     //   237: invokevirtual scala$xml$dtd$ElementValidator$$exc : ()Lscala/collection/immutable/List;
/*     */     //   240: invokevirtual length : ()I
/*     */     //   243: iload #5
/*     */     //   245: if_icmpne -> 252
/*     */     //   248: iconst_1
/*     */     //   249: goto -> 253
/*     */     //   252: iconst_0
/*     */     //   253: istore #7
/*     */     //   255: goto -> 307
/*     */     //   258: aload #6
/*     */     //   260: instanceof scala/xml/dtd/ELEMENTS
/*     */     //   263: ifeq -> 304
/*     */     //   266: aload_0
/*     */     //   267: invokevirtual dfa : ()Lscala/util/automata/DetWordAutom;
/*     */     //   270: aload_0
/*     */     //   271: aload_1
/*     */     //   272: iconst_0
/*     */     //   273: invokevirtual getIterable : (Lscala/collection/Seq;Z)Lscala/collection/Iterable;
/*     */     //   276: iconst_0
/*     */     //   277: invokestatic boxToInteger : (I)Ljava/lang/Integer;
/*     */     //   280: new scala/xml/dtd/ElementValidator$$anonfun$check$6
/*     */     //   283: dup
/*     */     //   284: aload_0
/*     */     //   285: invokespecial <init> : (Lscala/xml/dtd/ElementValidator;)V
/*     */     //   288: invokeinterface foldLeft : (Ljava/lang/Object;Lscala/Function2;)Ljava/lang/Object;
/*     */     //   293: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*     */     //   296: invokevirtual isFinal : (I)Z
/*     */     //   299: istore #7
/*     */     //   301: goto -> 307
/*     */     //   304: iconst_0
/*     */     //   305: istore #7
/*     */     //   307: iload #7
/*     */     //   309: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #98	-> 0
/*     */     //   #99	-> 6
/*     */     //   #100	-> 36
/*     */     //   #101	-> 76
/*     */     //   #102	-> 116
/*     */     //   #98	-> 130
/*     */     //   #102	-> 131
/*     */     //   #98	-> 140
/*     */     //   #102	-> 141
/*     */     //   #103	-> 168
/*     */     //   #107	-> 177
/*     */     //   #108	-> 223
/*     */     //   #107	-> 231
/*     */     //   #110	-> 236
/*     */     //   #102	-> 253
/*     */     //   #112	-> 258
/*     */     //   #113	-> 266
/*     */     //   #114	-> 270
/*     */     //   #113	-> 296
/*     */     //   #118	-> 304
/*     */     //   #98	-> 307
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	310	0	this	Lscala/xml/dtd/ElementValidator;
/*     */     //   0	310	1	nodes	Lscala/collection/Seq;
/*     */     //   177	133	5	j	I
/*     */   }
/*     */   
/*     */   public final boolean scala$xml$dtd$ElementValidator$$find$2(String Key, Option o17$1) {
/* 105 */     return ((IterableLike)o17$1.get()).exists((Function1)new ElementValidator$$anonfun$scala$xml$dtd$ElementValidator$$find$2$1(this, Key));
/*     */   }
/*     */   
/*     */   public class ElementValidator$$anonfun$scala$xml$dtd$ElementValidator$$find$2$1 extends AbstractFunction1<Base.RegExp, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final String Key$2;
/*     */     
/*     */     public final boolean apply(Base.RegExp x0$4) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: instanceof scala/util/regexp/WordExp$Letter
/*     */       //   4: ifeq -> 59
/*     */       //   7: aload_1
/*     */       //   8: checkcast scala/util/regexp/WordExp$Letter
/*     */       //   11: astore_2
/*     */       //   12: aload_2
/*     */       //   13: invokevirtual a : ()Lscala/util/regexp/WordExp$Label;
/*     */       //   16: ifnull -> 59
/*     */       //   19: aload_0
/*     */       //   20: getfield Key$2 : Ljava/lang/String;
/*     */       //   23: aload_2
/*     */       //   24: invokevirtual a : ()Lscala/util/regexp/WordExp$Label;
/*     */       //   27: checkcast scala/xml/dtd/ContentModel$ElemName
/*     */       //   30: invokevirtual name : ()Ljava/lang/String;
/*     */       //   33: astore_3
/*     */       //   34: dup
/*     */       //   35: ifnonnull -> 46
/*     */       //   38: pop
/*     */       //   39: aload_3
/*     */       //   40: ifnull -> 53
/*     */       //   43: goto -> 59
/*     */       //   46: aload_3
/*     */       //   47: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   50: ifeq -> 59
/*     */       //   53: iconst_1
/*     */       //   54: istore #4
/*     */       //   56: goto -> 62
/*     */       //   59: iconst_0
/*     */       //   60: istore #4
/*     */       //   62: iload #4
/*     */       //   64: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #105	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	65	0	this	Lscala/xml/dtd/ElementValidator$$anonfun$scala$xml$dtd$ElementValidator$$find$2$1;
/*     */       //   0	65	1	x0$4	Lscala/util/regexp/Base$RegExp;
/*     */     }
/*     */     
/*     */     public ElementValidator$$anonfun$scala$xml$dtd$ElementValidator$$find$2$1(ElementValidator $outer, String Key$2) {}
/*     */   }
/*     */   
/*     */   public class ElementValidator$$anonfun$check$3 extends AbstractFunction1<ContentModel.ElemName, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(ContentModel.ElemName x$1) {
/* 107 */       return x$1.name();
/*     */     }
/*     */     
/*     */     public ElementValidator$$anonfun$check$3(ElementValidator $outer) {}
/*     */   }
/*     */   
/*     */   public class ElementValidator$$anonfun$check$4 extends AbstractFunction1<String, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Option o17$1;
/*     */     
/*     */     public final boolean apply(String Key) {
/* 107 */       return this.$outer.scala$xml$dtd$ElementValidator$$find$2(Key, this.o17$1);
/*     */     }
/*     */     
/*     */     public ElementValidator$$anonfun$check$4(ElementValidator $outer, Option o17$1) {}
/*     */   }
/*     */   
/*     */   public class ElementValidator$$anonfun$check$5 extends AbstractFunction1<String, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(String x$2) {
/* 108 */       this.$outer.scala$xml$dtd$ElementValidator$$exc_$eq(this.$outer.scala$xml$dtd$ElementValidator$$exc().$colon$colon(MakeValidationException$.MODULE$.fromUndefinedElement(x$2)));
/*     */     }
/*     */     
/*     */     public ElementValidator$$anonfun$check$5(ElementValidator $outer) {}
/*     */   }
/*     */   
/*     */   public class ElementValidator$$anonfun$check$6 extends AbstractFunction2<Object, ContentModel.ElemName, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public ElementValidator$$anonfun$check$6(ElementValidator $outer) {}
/*     */     
/*     */     public final int apply(int q, ContentModel.ElemName e) {
/* 115 */       return BoxesRunTime.unboxToInt(this.$outer.dfa().delta()[q].getOrElse(e, (Function0)new ElementValidator$$anonfun$check$6$$anonfun$apply$1(this, e)));
/*     */     }
/*     */     
/*     */     public class ElementValidator$$anonfun$check$6$$anonfun$apply$1 extends AbstractFunction0<Nothing$> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final ContentModel.ElemName e$1;
/*     */       
/*     */       public final Nothing$ apply() {
/* 115 */         Predef$ predef$ = Predef$.MODULE$;
/* 115 */         throw new ValidationException((new StringOps("element %s not allowed here")).format(Predef$.MODULE$.genericWrapArray(new Object[] { this.e$1 })));
/*     */       }
/*     */       
/*     */       public ElementValidator$$anonfun$check$6$$anonfun$apply$1(ElementValidator$$anonfun$check$6 $outer, ContentModel.ElemName e$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean apply(Node n) {
/* 126 */     if (contentModel() == null || check(n.child()))
/* 128 */       if (adecls() == null || check(n.attributes())); 
/*     */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\ElementValidator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */