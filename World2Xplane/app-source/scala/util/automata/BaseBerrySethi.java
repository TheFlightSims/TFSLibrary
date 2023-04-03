/*    */ package scala.util.automata;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.Predef$;
/*    */ import scala.Serializable;
/*    */ import scala.collection.GenTraversableOnce;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.Seq$;
/*    */ import scala.collection.generic.GenericTraversableTemplate;
/*    */ import scala.collection.immutable.Map;
/*    */ import scala.collection.immutable.Nil$;
/*    */ import scala.collection.immutable.Set;
/*    */ import scala.collection.mutable.HashMap;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.AbstractFunction2;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.util.regexp.Base;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005Ub!B\001\003\003\003I!A\004\"bg\026\024UM\035:z'\026$\b.\033\006\003\007\021\t\001\"Y;u_6\fG/\031\006\003\013\031\tA!\036;jY*\tq!A\003tG\006d\027m\001\001\024\005\001Q\001CA\006\r\033\0051\021BA\007\007\005\031\te.\037*fM\")q\002\001C\001!\0051A(\0338jiz\"\022!\005\t\003%\001i\021A\001\005\b)\001\021\rQ\"\001\026\003\021a\027M\\4\026\003Y\001\"a\006\016\016\003aQ!!\007\003\002\rI,w-\032=q\023\tY\002D\001\003CCN,\007bB\017\001\001\004%\tBH\001\004a>\034X#A\020\021\005-\001\023BA\021\007\005\rIe\016\036\005\bG\001\001\r\021\"\005%\003\035\001xn]0%KF$\"!\n\025\021\005-1\023BA\024\007\005\021)f.\033;\t\017%\022\023\021!a\001?\005\031\001\020J\031\t\r-\002\001\025)\003 \003\021\001xn\035\021\t\0235\002\001\031!a\001\n#q\023A\0024pY2|w/F\0010!\021\001TgH\034\016\003ER!AM\032\002\0175,H/\0312mK*\021AGB\001\013G>dG.Z2uS>t\027B\001\0342\005\035A\025m\0355NCB\0042\001O\036 \035\tY\021(\003\002;\r\0051\001K]3eK\032L!\001P\037\003\007M+GO\003\002;\r!Iq\b\001a\001\002\004%\t\002Q\001\013M>dGn\\<`I\025\fHCA\023B\021\035Ic(!AA\002=Baa\021\001!B\023y\023a\0024pY2|w\017\t\005\n\013\002\001\r\0211A\005\022y\t\001BZ5oC2$\026m\032\005\n\017\002\001\r\0211A\005\022!\013ABZ5oC2$\026mZ0%KF$\"!J%\t\017%2\025\021!a\001?!11\n\001Q!\n}\t\021BZ5oC2$\026m\032\021\t\0235\003\001\031!a\001\n#q\025A\0024j]\006d7/F\001P!\021\0016kH\020\016\003ES!AU\032\002\023%lW.\036;bE2,\027B\001+R\005\ri\025\r\035\005\n-\002\001\r\0211A\005\022]\013!BZ5oC2\034x\fJ3r)\t)\003\fC\004*+\006\005\t\031A(\t\ri\003\001\025)\003P\003\0351\027N\\1mg\002Bq\001\030\001C\002\023\025Q,\001\005f[B$\030pU3u+\0059\004BB0\001A\0035q'A\005f[B$\030pU3uA!)\021\r\001C\005E\0061Am\\\"p[B$2aN2k\021\025!\007\r1\001f\003\005\021\bC\0014i\035\t97#D\001\001\023\tI'D\001\004SK\036,\005\020\035\005\006W\002\004\r\001\\\001\rG>l\007OR;oGRLwN\034\t\005\0275,w'\003\002o\r\tIa)\0368di&|g.\r\005\006a\002!\t\"]\001\nG>l\007OR5sgR$\"a\016:\t\013\021|\007\031A3\t\013Q\004A\021C;\002\021\r|W\016\035'bgR$\"a\016<\t\013\021\034\b\031A3\t\013a\004A\021C=\002\025\r|W\016\035$pY2|w\017\006\0028u\")1p\036a\001y\006\021!o\035\t\005{\006-QMD\002\003\017q1a`A\003\033\t\t\tAC\002\002\004!\ta\001\020:p_Rt\024\"A\004\n\007\005%a!A\004qC\016\\\027mZ3\n\t\0055\021q\002\002\004'\026\f(bAA\005\r!9\0211\003\001\005\022\005U\021aC2p[B4u\016\0347poF\"RaNA\f\0037Aq!!\007\002\022\001\007q'\001\003g_2\f\004B\0023\002\022\001\007Q\rC\004\002 \001!\t\"!\t\002\021Q\024\030M^3sg\026$2!JA\022\021\031!\027Q\004a\001K\":\001!a\n\002.\005E\002cA\006\002*%\031\0211\006\004\003\025\021,\007O]3dCR,G-\t\002\0020\005QB\013[5tA\rd\027m]:!o&dG\016\t2fAI,Wn\034<fI\006\022\0211G\001\007e9\n\004G\f\031")
/*    */ public abstract class BaseBerrySethi {
/* 25 */   private int pos = 0;
/*    */   
/*    */   private HashMap<Object, Set<Object>> follow;
/*    */   
/*    */   private int finalTag;
/*    */   
/*    */   private Map<Object, Object> finals;
/*    */   
/*    */   public abstract Base lang();
/*    */   
/*    */   public int pos() {
/* 25 */     return this.pos;
/*    */   }
/*    */   
/*    */   public void pos_$eq(int x$1) {
/* 25 */     this.pos = x$1;
/*    */   }
/*    */   
/*    */   public HashMap<Object, Set<Object>> follow() {
/* 28 */     return this.follow;
/*    */   }
/*    */   
/*    */   public void follow_$eq(HashMap<Object, Set<Object>> x$1) {
/* 28 */     this.follow = x$1;
/*    */   }
/*    */   
/*    */   public int finalTag() {
/* 30 */     return this.finalTag;
/*    */   }
/*    */   
/*    */   public void finalTag_$eq(int x$1) {
/* 30 */     this.finalTag = x$1;
/*    */   }
/*    */   
/*    */   public Map<Object, Object> finals() {
/* 32 */     return this.finals;
/*    */   }
/*    */   
/*    */   public void finals_$eq(Map<Object, Object> x$1) {
/* 32 */     this.finals = x$1;
/*    */   }
/*    */   
/* 36 */   private final Set<Object> emptySet = (Set<Object>)Predef$.MODULE$.Set().apply((Seq)Nil$.MODULE$);
/*    */   
/*    */   public final Set<Object> emptySet() {
/* 36 */     return this.emptySet;
/*    */   }
/*    */   
/*    */   private Set<Object> doComp(Base.RegExp r, Function1 compFunction) {
/*    */     // Byte code:
/*    */     //   0: aload_1
/*    */     //   1: instanceof scala/util/regexp/Base$Alt
/*    */     //   4: ifeq -> 63
/*    */     //   7: aload_1
/*    */     //   8: checkcast scala/util/regexp/Base$Alt
/*    */     //   11: astore_3
/*    */     //   12: aload_3
/*    */     //   13: invokevirtual rs : ()Lscala/collection/Seq;
/*    */     //   16: new scala/util/automata/BaseBerrySethi$$anonfun$doComp$1
/*    */     //   19: dup
/*    */     //   20: aload_0
/*    */     //   21: invokespecial <init> : (Lscala/util/automata/BaseBerrySethi;)V
/*    */     //   24: getstatic scala/collection/Seq$.MODULE$ : Lscala/collection/Seq$;
/*    */     //   27: invokevirtual canBuildFrom : ()Lscala/collection/generic/CanBuildFrom;
/*    */     //   30: invokeinterface map : (Lscala/Function1;Lscala/collection/generic/CanBuildFrom;)Ljava/lang/Object;
/*    */     //   35: checkcast scala/collection/TraversableOnce
/*    */     //   38: aload_0
/*    */     //   39: invokevirtual emptySet : ()Lscala/collection/immutable/Set;
/*    */     //   42: new scala/util/automata/BaseBerrySethi$$anonfun$doComp$2
/*    */     //   45: dup
/*    */     //   46: aload_0
/*    */     //   47: invokespecial <init> : (Lscala/util/automata/BaseBerrySethi;)V
/*    */     //   50: invokeinterface foldLeft : (Ljava/lang/Object;Lscala/Function2;)Ljava/lang/Object;
/*    */     //   55: checkcast scala/collection/immutable/Set
/*    */     //   58: astore #11
/*    */     //   60: goto -> 313
/*    */     //   63: aload_0
/*    */     //   64: invokevirtual lang : ()Lscala/util/regexp/Base;
/*    */     //   67: invokevirtual Eps : ()Lscala/util/regexp/Base$Eps$;
/*    */     //   70: dup
/*    */     //   71: ifnonnull -> 82
/*    */     //   74: pop
/*    */     //   75: aload_1
/*    */     //   76: ifnull -> 89
/*    */     //   79: goto -> 98
/*    */     //   82: aload_1
/*    */     //   83: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   86: ifeq -> 98
/*    */     //   89: aload_0
/*    */     //   90: invokevirtual emptySet : ()Lscala/collection/immutable/Set;
/*    */     //   93: astore #11
/*    */     //   95: goto -> 313
/*    */     //   98: aload_1
/*    */     //   99: instanceof scala/util/regexp/Base$Meta
/*    */     //   102: ifeq -> 130
/*    */     //   105: aload_1
/*    */     //   106: checkcast scala/util/regexp/Base$Meta
/*    */     //   109: astore #4
/*    */     //   111: aload_2
/*    */     //   112: aload #4
/*    */     //   114: invokevirtual r : ()Lscala/util/regexp/Base$RegExp;
/*    */     //   117: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*    */     //   122: checkcast scala/collection/immutable/Set
/*    */     //   125: astore #11
/*    */     //   127: goto -> 313
/*    */     //   130: aload_1
/*    */     //   131: instanceof scala/util/regexp/Base$Sequ
/*    */     //   134: ifeq -> 284
/*    */     //   137: aload_1
/*    */     //   138: checkcast scala/util/regexp/Base$Sequ
/*    */     //   141: astore #5
/*    */     //   143: aload #5
/*    */     //   145: invokevirtual rs : ()Lscala/collection/Seq;
/*    */     //   148: new scala/util/automata/BaseBerrySethi$$anonfun$1
/*    */     //   151: dup
/*    */     //   152: aload_0
/*    */     //   153: invokespecial <init> : (Lscala/util/automata/BaseBerrySethi;)V
/*    */     //   156: invokeinterface span : (Lscala/Function1;)Lscala/Tuple2;
/*    */     //   161: astore #9
/*    */     //   163: aload #9
/*    */     //   165: ifnull -> 274
/*    */     //   168: new scala/Tuple2
/*    */     //   171: dup
/*    */     //   172: aload #9
/*    */     //   174: invokevirtual _1 : ()Ljava/lang/Object;
/*    */     //   177: aload #9
/*    */     //   179: invokevirtual _2 : ()Ljava/lang/Object;
/*    */     //   182: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;)V
/*    */     //   185: astore #6
/*    */     //   187: aload #6
/*    */     //   189: invokevirtual _1 : ()Ljava/lang/Object;
/*    */     //   192: checkcast scala/collection/Seq
/*    */     //   195: astore #7
/*    */     //   197: aload #6
/*    */     //   199: invokevirtual _2 : ()Ljava/lang/Object;
/*    */     //   202: checkcast scala/collection/Seq
/*    */     //   205: astore #8
/*    */     //   207: aload #7
/*    */     //   209: aload #8
/*    */     //   211: iconst_1
/*    */     //   212: invokeinterface take : (I)Ljava/lang/Object;
/*    */     //   217: checkcast scala/collection/GenTraversableOnce
/*    */     //   220: getstatic scala/collection/Seq$.MODULE$ : Lscala/collection/Seq$;
/*    */     //   223: invokevirtual canBuildFrom : ()Lscala/collection/generic/CanBuildFrom;
/*    */     //   226: invokeinterface $plus$plus : (Lscala/collection/GenTraversableOnce;Lscala/collection/generic/CanBuildFrom;)Ljava/lang/Object;
/*    */     //   231: checkcast scala/collection/TraversableLike
/*    */     //   234: aload_2
/*    */     //   235: getstatic scala/collection/Seq$.MODULE$ : Lscala/collection/Seq$;
/*    */     //   238: invokevirtual canBuildFrom : ()Lscala/collection/generic/CanBuildFrom;
/*    */     //   241: invokeinterface map : (Lscala/Function1;Lscala/collection/generic/CanBuildFrom;)Ljava/lang/Object;
/*    */     //   246: checkcast scala/collection/TraversableOnce
/*    */     //   249: aload_0
/*    */     //   250: invokevirtual emptySet : ()Lscala/collection/immutable/Set;
/*    */     //   253: new scala/util/automata/BaseBerrySethi$$anonfun$doComp$3
/*    */     //   256: dup
/*    */     //   257: aload_0
/*    */     //   258: invokespecial <init> : (Lscala/util/automata/BaseBerrySethi;)V
/*    */     //   261: invokeinterface foldLeft : (Ljava/lang/Object;Lscala/Function2;)Ljava/lang/Object;
/*    */     //   266: checkcast scala/collection/immutable/Set
/*    */     //   269: astore #11
/*    */     //   271: goto -> 313
/*    */     //   274: new scala/MatchError
/*    */     //   277: dup
/*    */     //   278: aload #9
/*    */     //   280: invokespecial <init> : (Ljava/lang/Object;)V
/*    */     //   283: athrow
/*    */     //   284: aload_1
/*    */     //   285: instanceof scala/util/regexp/Base$Star
/*    */     //   288: ifeq -> 316
/*    */     //   291: aload_1
/*    */     //   292: checkcast scala/util/regexp/Base$Star
/*    */     //   295: astore #10
/*    */     //   297: aload_2
/*    */     //   298: aload #10
/*    */     //   300: invokevirtual r : ()Lscala/util/regexp/Base$RegExp;
/*    */     //   303: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*    */     //   308: checkcast scala/collection/immutable/Set
/*    */     //   311: astore #11
/*    */     //   313: aload #11
/*    */     //   315: areturn
/*    */     //   316: new java/lang/IllegalArgumentException
/*    */     //   319: dup
/*    */     //   320: new scala/collection/mutable/StringBuilder
/*    */     //   323: dup
/*    */     //   324: invokespecial <init> : ()V
/*    */     //   327: ldc 'unexpected pattern '
/*    */     //   329: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*    */     //   332: aload_1
/*    */     //   333: invokevirtual getClass : ()Ljava/lang/Class;
/*    */     //   336: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*    */     //   339: invokevirtual toString : ()Ljava/lang/String;
/*    */     //   342: invokespecial <init> : (Ljava/lang/String;)V
/*    */     //   345: athrow
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #39	-> 0
/*    */     //   #38	-> 0
/*    */     //   #40	-> 63
/*    */     //   #41	-> 98
/*    */     //   #42	-> 130
/*    */     //   #43	-> 143
/*    */     //   #44	-> 207
/*    */     //   #42	-> 269
/*    */     //   #43	-> 274
/*    */     //   #45	-> 284
/*    */     //   #38	-> 298
/*    */     //   #45	-> 300
/*    */     //   #38	-> 313
/*    */     //   #46	-> 316
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	346	0	this	Lscala/util/automata/BaseBerrySethi;
/*    */     //   0	346	1	r	Lscala/util/regexp/Base$RegExp;
/*    */     //   0	346	2	compFunction	Lscala/Function1;
/*    */     //   197	72	7	l1	Lscala/collection/Seq;
/*    */     //   207	62	8	l2	Lscala/collection/Seq;
/*    */   }
/*    */   
/*    */   public class BaseBerrySethi$$anonfun$doComp$1 extends AbstractFunction1<Base.RegExp, Set<Object>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Set<Object> apply(Base.RegExp r) {
/* 39 */       return this.$outer.compFirst(r);
/*    */     }
/*    */     
/*    */     public BaseBerrySethi$$anonfun$doComp$1(BaseBerrySethi $outer) {}
/*    */   }
/*    */   
/*    */   public class BaseBerrySethi$$anonfun$doComp$2 extends AbstractFunction2<Set<Object>, Set<Object>, Set<Object>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Set<Object> apply(Set x$1, Set x$2) {
/* 39 */       return (Set<Object>)x$1.$plus$plus((GenTraversableOnce)x$2);
/*    */     }
/*    */     
/*    */     public BaseBerrySethi$$anonfun$doComp$2(BaseBerrySethi $outer) {}
/*    */   }
/*    */   
/*    */   public class $anonfun$1 extends AbstractFunction1<Base.RegExp, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final boolean apply(Base.RegExp x$3) {
/* 43 */       return x$3.isNullable();
/*    */     }
/*    */     
/*    */     public $anonfun$1(BaseBerrySethi $outer) {}
/*    */   }
/*    */   
/*    */   public class BaseBerrySethi$$anonfun$doComp$3 extends AbstractFunction2<Set<Object>, Set<Object>, Set<Object>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Set<Object> apply(Set x$5, Set x$6) {
/* 44 */       return (Set<Object>)x$5.$plus$plus((GenTraversableOnce)x$6);
/*    */     }
/*    */     
/*    */     public BaseBerrySethi$$anonfun$doComp$3(BaseBerrySethi $outer) {}
/*    */   }
/*    */   
/*    */   public Set<Object> compFirst(Base.RegExp r) {
/* 50 */     return doComp(r, (Function1<Base.RegExp, Set<Object>>)new BaseBerrySethi$$anonfun$compFirst$1(this));
/*    */   }
/*    */   
/*    */   public class BaseBerrySethi$$anonfun$compFirst$1 extends AbstractFunction1<Base.RegExp, Set<Object>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Set<Object> apply(Base.RegExp r) {
/* 50 */       return this.$outer.compFirst(r);
/*    */     }
/*    */     
/*    */     public BaseBerrySethi$$anonfun$compFirst$1(BaseBerrySethi $outer) {}
/*    */   }
/*    */   
/*    */   public Set<Object> compLast(Base.RegExp r) {
/* 53 */     return doComp(r, (Function1<Base.RegExp, Set<Object>>)new BaseBerrySethi$$anonfun$compLast$1(this));
/*    */   }
/*    */   
/*    */   public class BaseBerrySethi$$anonfun$compLast$1 extends AbstractFunction1<Base.RegExp, Set<Object>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Set<Object> apply(Base.RegExp r) {
/* 53 */       return this.$outer.compLast(r);
/*    */     }
/*    */     
/*    */     public BaseBerrySethi$$anonfun$compLast$1(BaseBerrySethi $outer) {}
/*    */   }
/*    */   
/*    */   public Set<Object> compFollow(Seq rs) {
/* 60 */     follow().update(BoxesRunTime.boxToInteger(0), 
/* 61 */         rs.isEmpty() ? emptySet() : 
/* 62 */         rs.foldRight(Predef$.MODULE$.Set().apply((Seq)Predef$.MODULE$.wrapIntArray(new int[] { pos() }, )), (Function2)new BaseBerrySethi$$anonfun$compFollow$1(this)));
/* 69 */     return (Set<Object>)follow().apply(BoxesRunTime.boxToInteger(0));
/*    */   }
/*    */   
/*    */   public class BaseBerrySethi$$anonfun$compFollow$1 extends AbstractFunction2<Base.RegExp, Set<Object>, Set<Object>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public BaseBerrySethi$$anonfun$compFollow$1(BaseBerrySethi $outer) {}
/*    */     
/*    */     public final Set<Object> apply(Base.RegExp p, Set<Object> fol) {
/*    */       Set<Object> first = this.$outer.compFollow1(fol, p);
/*    */       return p.isNullable() ? (Set<Object>)fol.$plus$plus((GenTraversableOnce)first) : first;
/*    */     }
/*    */   }
/*    */   
/*    */   public Set<Object> compFollow1(Set<Object> fol1, Base.RegExp r) {
/* 74 */     if (r instanceof Base.Alt) {
/* 74 */       Base.Alt alt = (Base.Alt)r;
/* 74 */       Set set = (Set)Predef$.MODULE$.Set().apply((Seq)((GenericTraversableTemplate)alt.rs().reverseMap((Function1)new BaseBerrySethi$$anonfun$compFollow1$1(this, fol1), Seq$.MODULE$.canBuildFrom())).flatten((Function1)Predef$.MODULE$.conforms()));
/* 76 */     } else if (r instanceof Base.Meta) {
/* 76 */       Base.Meta meta = (Base.Meta)r;
/* 76 */       Set<Object> set = compFollow1(fol1, meta.r());
/* 77 */     } else if (r instanceof Base.Star) {
/* 77 */       Base.Star star = (Base.Star)r;
/* 77 */       Set<Object> set = compFollow1((Set<Object>)fol1.$plus$plus((GenTraversableOnce)compFirst(star.r())), star.r());
/*    */     } else {
/* 78 */       if (r instanceof Base.Sequ) {
/* 78 */         Base.Sequ sequ = (Base.Sequ)r;
/* 79 */         return (Set)sequ.rs().foldRight(fol1, (Function2)new BaseBerrySethi$$anonfun$compFollow1$2(this));
/*    */       } 
/* 85 */       throw new IllegalArgumentException((new StringBuilder()).append("unexpected pattern: ").append(r.getClass()).toString());
/*    */     } 
/*    */     return (Set<Object>)SYNTHETIC_LOCAL_VARIABLE_7;
/*    */   }
/*    */   
/*    */   public class BaseBerrySethi$$anonfun$compFollow1$1 extends AbstractFunction1<Base.RegExp, Set<Object>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Set fol1$1;
/*    */     
/*    */     public final Set<Object> apply(Base.RegExp x$7) {
/*    */       return this.$outer.compFollow1(this.fol1$1, x$7);
/*    */     }
/*    */     
/*    */     public BaseBerrySethi$$anonfun$compFollow1$1(BaseBerrySethi $outer, Set fol1$1) {}
/*    */   }
/*    */   
/*    */   public class BaseBerrySethi$$anonfun$compFollow1$2 extends AbstractFunction2<Base.RegExp, Set<Object>, Set<Object>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public BaseBerrySethi$$anonfun$compFollow1$2(BaseBerrySethi $outer) {}
/*    */     
/*    */     public final Set<Object> apply(Base.RegExp p, Set<Object> fol) {
/*    */       Set<Object> first = this.$outer.compFollow1(fol, p);
/*    */       return p.isNullable() ? (Set<Object>)fol.$plus$plus((GenTraversableOnce)first) : first;
/*    */     }
/*    */   }
/*    */   
/*    */   public void traverse(Base.RegExp r) {
/* 90 */     if (r instanceof Base.Alt) {
/* 90 */       Base.Alt alt = (Base.Alt)r;
/* 90 */       alt.rs().foreach((Function1)new BaseBerrySethi$$anonfun$traverse$1(this));
/* 93 */     } else if (r instanceof Base.Sequ) {
/* 93 */       Base.Sequ sequ = (Base.Sequ)r;
/* 93 */       sequ.rs().foreach((Function1)new BaseBerrySethi$$anonfun$traverse$2(this));
/* 94 */     } else if (r instanceof Base.Meta) {
/* 94 */       Base.Meta meta = (Base.Meta)r;
/* 94 */       traverse(meta.r());
/*    */     } else {
/* 95 */       if (r instanceof Base.Star) {
/* 95 */         Base.Star star = (Base.Star)r;
/* 95 */         traverse(star.r());
/*    */       } 
/* 96 */       throw new IllegalArgumentException((new StringBuilder()).append("unexp pattern ").append(r.getClass()).toString());
/*    */     } 
/*    */   }
/*    */   
/*    */   public class BaseBerrySethi$$anonfun$traverse$1 extends AbstractFunction1<Base.RegExp, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final void apply(Base.RegExp r) {
/*    */       this.$outer.traverse(r);
/*    */     }
/*    */     
/*    */     public BaseBerrySethi$$anonfun$traverse$1(BaseBerrySethi $outer) {}
/*    */   }
/*    */   
/*    */   public class BaseBerrySethi$$anonfun$traverse$2 extends AbstractFunction1<Base.RegExp, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final void apply(Base.RegExp r) {
/*    */       this.$outer.traverse(r);
/*    */     }
/*    */     
/*    */     public BaseBerrySethi$$anonfun$traverse$2(BaseBerrySethi $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\automata\BaseBerrySethi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */