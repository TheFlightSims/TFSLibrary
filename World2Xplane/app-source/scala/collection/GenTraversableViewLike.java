/*     */ package scala.collection;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.SliceInterval;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BooleanRef;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.IntRef;
/*     */ import scala.runtime.NonLocalReturnControl;
/*     */ import scala.runtime.Nothing$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\tEcaB\001\003!\003\r\ta\002\002\027\017\026tGK]1wKJ\034\030M\0317f-&,w\017T5lK*\0211\001B\001\013G>dG.Z2uS>t'\"A\003\002\013M\034\027\r\\1\004\001U!\001b\005\026!'\021\001\021\"\004\017\021\005)YQ\"\001\003\n\0051!!AB!osJ+g\rE\002\017\037Ei\021AA\005\003!\t\021abR3o)J\fg/\032:tC\ndW\r\005\002\023'1\001AA\002\013\001\t\013\007QCA\001B#\t1\022\004\005\002\013/%\021\001\004\002\002\b\035>$\b.\0338h!\tQ!$\003\002\034\t\t\031\021I\\=\021\t9i\022cH\005\003=\t\021!cR3o)J\fg/\032:tC\ndW\rT5lKB\021!\003\t\003\007C\001!)\031\001\022\003\tQC\027n]\t\003-\r\0222\001\n\024-\r\021)\003\001A\022\003\031q\022XMZ5oK6,g\016\036 \021\t99\023#K\005\003Q\t\021!cR3o)J\fg/\032:tC\ndWMV5foB\021!C\013\003\007W\001!)\031A\013\003\t\r{G\016\034\t\006\035\001\t\022f\b\005\006]\001!\taL\001\007I%t\027\016\036\023\025\003A\002\"AC\031\n\005I\"!\001B+oSRDQ\001\016\001\007\002U\nQAZ8sG\026,2AN\"9)\t9$\b\005\002\023q\021)\021h\rb\001+\t!A\013[1u\021\025Y4\007q\001=\003\t\021g\rE\003>\001&\022u'D\001?\025\ty$!A\004hK:,'/[2\n\005\005s$\001D\"b]\n+\030\016\0343Ge>l\007C\001\nD\t\025!5G1\001F\005\005\021\025CA\t\032\021\0259\005A\"\005I\003))h\016Z3sYfLgnZ\013\002S!1!\n\001Q\007\022-\013aB^5fo&#WM\034;jM&,'/F\001M!\ti\005K\004\002\013\035&\021q\nB\001\007!J,G-\0324\n\005E\023&AB*ue&twM\003\002P\t!1A\013\001Q\007\022-\013AB^5fo&#7\013\036:j]\036DQA\026\001\005\002]\013AB^5foR{7\013\036:j]\036,\022\001\027\t\0033zk\021A\027\006\0037r\013A\001\\1oO*\tQ,\001\003kCZ\f\027BA)[\r\035\001\007\001%A\002\002\005\0241\002\026:b]N4wN]7fIV\021!-Z\n\004?&\031\007\003\002\b(I&\002\"AE3\005\r\021{FQ1\001\026\021\025qs\f\"\0010\021\025AwL\"\001j\003\0351wN]3bG\",\"A[9\025\005AZ\007\"\0027h\001\004i\027!\0014\021\t)qG\r]\005\003_\022\021\021BR;oGRLwN\\\031\021\005I\tH!\002:h\005\004)\"!A+\t\021\035{\006R1A\005\002!C\001\"^0\t\002\003\006K!K\001\fk:$WM\0357zS:<\007\005\003\004U?\002&)f\026\005\006q~#\teS\001\rgR\024\030N\\4Qe\0264\027\016\037\005\006u~#\te_\001\ti>\034FO]5oOR\t\001LB\004~\001A\005\031\021\001@\003\023\025k\007\017^=WS\026<8c\001?\nB!\021\021A0\027\033\005\001\001\"\002\030}\t\003y\003bBA\004y\022\025\023\021B\001\bSN,U\016\035;z+\t\tY\001E\002\013\003\033I1!a\004\005\005\035\021un\0347fC:Da\001\033?\005F\005MQ\003BA\013\003;!2\001MA\f\021\035a\027\021\003a\001\0033\001RA\0038\027\0037\0012AEA\017\t\031\021\030\021\003b\001+\031I\021\021\005\001\021\002\007\005\0211\005\002\007\r>\0248-\0323\026\t\005\025\0221F\n\006\003?I\021q\005\t\006\003\003y\026\021\006\t\004%\005-BA\002#\002 \t\007Q\003\003\004/\003?!\ta\f\005\013\003c\tyB1Q\007\022\005M\022A\0024pe\016,G-\006\002\0026A)a\"a\016\002*%\031\021\021\b\002\003\r\035+gnU3r\021\035A\027q\004C\001\003{)B!a\020\002HQ\031\001'!\021\t\0171\fY\0041\001\002DA1!B\\A\025\003\013\0022AEA$\t\031\021\0301\bb\001+!9!*a\b!\n+:f!CA'\001A\005\031\021AA(\005\031\031F.[2fIN)\0211J\005\002RA!\021\021A0\022\021\031q\0231\nC\001_!Q\021qKA&\005\0046\t\"!\027\002\023\025tG\r]8j]R\034XCAA.!\ri\024QL\005\004\003?r$!D*mS\016,\027J\034;feZ\fG\016C\005\002d\005-\003\025\"\005\002f\005!aM]8n+\t\t9\007E\002\013\003SJ1!a\033\005\005\rIe\016\036\005\n\003_\nY\005)C\t\003K\nQ!\0368uS2Dq\001[A&\t\003\t\031(\006\003\002v\005uDc\001\031\002x!9A.!\035A\002\005e\004#\002\006o#\005m\004c\001\n\002~\0211!/!\035C\002UAqASA&A\023UsKB\005\002\004\002\001\n1!\001\002\006\n1Q*\0319qK\022,B!a\"\002\016N)\021\021Q\005\002\nB)\021\021A0\002\fB\031!#!$\005\r\021\013\tI1\001\026\021\031q\023\021\021C\001_!Q\0211SAA\005\0046\t\"!&\002\0175\f\007\017]5oOV\021\021q\023\t\006\0259\f\0221\022\005\bQ\006\005E\021AAN+\021\ti*!*\025\007A\ny\nC\004m\0033\003\r!!)\021\r)q\0271RAR!\r\021\022Q\025\003\007e\006e%\031A\013\t\017)\013\t\t)C+/\032I\0211\026\001\021\002\007\005\021Q\026\002\013\r2\fG/T1qa\026$W\003BAX\003k\033R!!+\n\003c\003R!!\001`\003g\0032AEA[\t\031!\025\021\026b\001+!1a&!+\005\002=B!\"a%\002*\n\007k\021CA^+\t\ti\fE\003\013]F\ty\fE\003\017\003\003\f\031,C\002\002D\n\021!cR3o)J\fg/\032:tC\ndWm\0248dK\"9\001.!+\005\002\005\035W\003BAe\003#$2\001MAf\021\035a\027Q\031a\001\003\033\004bA\0038\0024\006=\007c\001\n\002R\0221!/!2C\002UAqASAUA\023UsKB\005\002X\002\001\n1!\001\002Z\nA\021\t\0359f]\022,G-\006\003\002\\\006\0058#BAk\023\005u\007#BA\001?\006}\007c\001\n\002b\0221A)!6C\002\025CaALAk\t\003y\003BCAt\003+\024\rU\"\005\002j\006!!/Z:u+\t\tY\017\005\003\017\037\005}\007b\0025\002V\022\005\021q^\013\005\003c\fI\020F\0021\003gDq\001\\Aw\001\004\t)\020\005\004\013]\006}\027q\037\t\004%\005eHA\002:\002n\n\007Q\003C\004K\003+\004KQK,\007\023\005}\b\001%A\002\002\t\005!\001\003$jYR,'/\0323\024\013\005u\030\"!\025\t\r9\ni\020\"\0010\021)\0219!!@CB\033E!\021B\001\005aJ,G-\006\002\003\fA)!B\\\t\002\f!9\001.!@\005\002\t=Q\003\002B\t\0053!2\001\rB\n\021\035a'Q\002a\001\005+\001RA\0038\022\005/\0012A\005B\r\t\031\021(Q\002b\001+!9!*!@!\n+:f!\003B\020\001A\005\031\021\001B\021\005)!\026m[3o/\"LG.Z\n\006\005;I\021\021\013\005\007]\tuA\021A\030\t\025\t\035!Q\004b!\016#\021I\001C\004i\005;!\tA!\013\026\t\t-\"1\007\013\004a\t5\002b\0027\003(\001\007!q\006\t\006\0259\f\"\021\007\t\004%\tMBA\002:\003(\t\007Q\003C\004K\005;\001KQK,\007\023\te\002\001%A\002\002\tm\"\001\004#s_B\004X\rZ,iS2,7#\002B\034\023\005E\003B\002\030\0038\021\005q\006\003\006\003\b\t]\"\031)D\t\005\023Aq\001\033B\034\t\003\021\031%\006\003\003F\t5Cc\001\031\003H!9AN!\021A\002\t%\003#\002\006o#\t-\003c\001\n\003N\0211!O!\021C\002UAqA\023B\034A\023Us\013")
/*     */ public interface GenTraversableViewLike<A, Coll, This extends GenTraversableView<A, Coll> & GenTraversableViewLike<A, Coll, This>> extends GenTraversable<A>, GenTraversableLike<A, This> {
/*     */   <B, That> That force(CanBuildFrom<Coll, B, That> paramCanBuildFrom);
/*     */   
/*     */   Coll underlying();
/*     */   
/*     */   String viewIdentifier();
/*     */   
/*     */   String viewIdString();
/*     */   
/*     */   String viewToString();
/*     */   
/*     */   public abstract class Transformed$class {
/*     */     public static void $init$(GenTraversableViewLike.Transformed $this) {}
/*     */     
/*     */     public static Object underlying(GenTraversableViewLike.Transformed $this) {
/*  37 */       return $this.scala$collection$GenTraversableViewLike$Transformed$$$outer().underlying();
/*     */     }
/*     */     
/*     */     public static final String viewIdString(GenTraversableViewLike.Transformed $this) {
/*  38 */       return (new StringBuilder()).append($this.scala$collection$GenTraversableViewLike$Transformed$$$outer().viewIdString()).append($this.viewIdentifier()).toString();
/*     */     }
/*     */     
/*     */     public static String stringPrefix(GenTraversableViewLike.Transformed $this) {
/*  39 */       return $this.scala$collection$GenTraversableViewLike$Transformed$$$outer().stringPrefix();
/*     */     }
/*     */     
/*     */     public static String toString(GenTraversableViewLike.Transformed $this) {
/*  40 */       return $this.viewToString();
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class EmptyView$class {
/*     */     public static void $init$(GenTraversableViewLike.EmptyView $this) {}
/*     */     
/*     */     public static final boolean isEmpty(GenTraversableViewLike.EmptyView $this) {
/*  44 */       return true;
/*     */     }
/*     */     
/*     */     public static final void foreach(GenTraversableViewLike.EmptyView $this, Function1 f) {}
/*     */   }
/*     */   
/*     */   public abstract class Forced$class {
/*     */     public static void $init$(GenTraversableViewLike.Forced $this) {}
/*     */     
/*     */     public static void foreach(GenTraversableViewLike.Forced $this, Function1 f) {
/*  53 */       $this.forced().foreach(f);
/*     */     }
/*     */     
/*     */     public static final String viewIdentifier(GenTraversableViewLike.Forced $this) {
/*  54 */       return "C";
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class Sliced$class {
/*     */     public static void $init$(GenTraversableViewLike.Sliced $this) {}
/*     */     
/*     */     public static int from(GenTraversableViewLike.Sliced $this) {
/*  59 */       return $this.endpoints().from();
/*     */     }
/*     */     
/*     */     public static int until(GenTraversableViewLike.Sliced $this) {
/*  60 */       return $this.endpoints().until();
/*     */     }
/*     */     
/*     */     public static void foreach(GenTraversableViewLike.Sliced $this, Function1 f) {
/*  64 */       Object object = new Object();
/*     */       try {
/*  65 */         IntRef index = new IntRef(0);
/*  66 */         $this.scala$collection$GenTraversableViewLike$Sliced$$$outer().foreach((Function1)new GenTraversableViewLike$Sliced$$anonfun$foreach$1($this, index, object, (GenTraversableViewLike<A, Coll, This>.Sliced)f));
/*     */       } catch (NonLocalReturnControl nonLocalReturnControl2) {
/*     */         NonLocalReturnControl nonLocalReturnControl1;
/*     */         if ((nonLocalReturnControl1 = null).key() == object) {
/*     */           nonLocalReturnControl1.value$mcV$sp();
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     public static final String viewIdentifier(GenTraversableViewLike.Sliced $this) {
/*  74 */       return "S";
/*     */     }
/*     */   }
/*     */   
/*     */   public interface Sliced extends Transformed<A> {
/*     */     SliceInterval endpoints();
/*     */     
/*     */     int from();
/*     */     
/*     */     int until();
/*     */     
/*     */     <U> void foreach(Function1<A, U> param1Function1);
/*     */     
/*     */     String viewIdentifier();
/*     */     
/*     */     public class GenTraversableViewLike$Sliced$$anonfun$foreach$1 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final IntRef index$1;
/*     */       
/*     */       private final Object nonLocalReturnKey1$1;
/*     */       
/*     */       private final Function1 f$1;
/*     */       
/*     */       public GenTraversableViewLike$Sliced$$anonfun$foreach$1(GenTraversableViewLike.Sliced $outer, IntRef index$1, Object nonLocalReturnKey1$1, Function1 f$1) {}
/*     */       
/*     */       public final void apply(Object x) {
/*     */         if (this.$outer.until() <= this.index$1.elem)
/*     */           throw new NonLocalReturnControl.mcV.sp(this.nonLocalReturnKey1$1, BoxedUnit.UNIT); 
/*     */         (this.$outer.from() <= this.index$1.elem) ? this.f$1.apply(x) : BoxedUnit.UNIT;
/*     */         this.index$1.elem++;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class Mapped$class {
/*     */     public static void $init$(GenTraversableViewLike.Mapped $this) {}
/*     */     
/*     */     public static void foreach(GenTraversableViewLike.Mapped $this, Function1 f) {
/*  80 */       $this.scala$collection$GenTraversableViewLike$Mapped$$$outer().foreach((Function1)new GenTraversableViewLike$Mapped$$anonfun$foreach$2($this, (GenTraversableViewLike<A, Coll, This>.Mapped<B>)f));
/*     */     }
/*     */     
/*     */     public static final String viewIdentifier(GenTraversableViewLike.Mapped $this) {
/*  83 */       return "M";
/*     */     }
/*     */   }
/*     */   
/*     */   public interface Mapped<B> extends Transformed<B> {
/*     */     Function1<A, B> mapping();
/*     */     
/*     */     <U> void foreach(Function1<B, U> param1Function1);
/*     */     
/*     */     String viewIdentifier();
/*     */     
/*     */     public class GenTraversableViewLike$Mapped$$anonfun$foreach$2 extends AbstractFunction1<A, U> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Function1 f$2;
/*     */       
/*     */       public GenTraversableViewLike$Mapped$$anonfun$foreach$2(GenTraversableViewLike.Mapped $outer, Function1 f$2) {}
/*     */       
/*     */       public final U apply(Object x) {
/*     */         return (U)this.f$2.apply(this.$outer.mapping().apply(x));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class FlatMapped$class {
/*     */     public static void $init$(GenTraversableViewLike.FlatMapped $this) {}
/*     */     
/*     */     public static void foreach(GenTraversableViewLike.FlatMapped $this, Function1 f) {
/*  89 */       $this.scala$collection$GenTraversableViewLike$FlatMapped$$$outer().foreach((Function1)new GenTraversableViewLike$FlatMapped$$anonfun$foreach$3($this, (GenTraversableViewLike<A, Coll, This>.FlatMapped<B>)f));
/*     */     }
/*     */     
/*     */     public static final String viewIdentifier(GenTraversableViewLike.FlatMapped $this) {
/*  93 */       return "N";
/*     */     }
/*     */   }
/*     */   
/*     */   public interface FlatMapped<B> extends Transformed<B> {
/*     */     Function1<A, GenTraversableOnce<B>> mapping();
/*     */     
/*     */     <U> void foreach(Function1<B, U> param1Function1);
/*     */     
/*     */     String viewIdentifier();
/*     */     
/*     */     public class GenTraversableViewLike$FlatMapped$$anonfun$foreach$3 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Function1 f$3;
/*     */       
/*     */       public GenTraversableViewLike$FlatMapped$$anonfun$foreach$3(GenTraversableViewLike.FlatMapped $outer, Function1 f$3) {}
/*     */       
/*     */       public final void apply(Object x) {
/*     */         ((GenTraversableOnce)this.$outer.mapping().apply(x)).seq().foreach((Function1)new GenTraversableViewLike$FlatMapped$$anonfun$foreach$3$$anonfun$apply$1(this));
/*     */       }
/*     */       
/*     */       public class GenTraversableViewLike$FlatMapped$$anonfun$foreach$3$$anonfun$apply$1 extends AbstractFunction1<B, U> implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         public GenTraversableViewLike$FlatMapped$$anonfun$foreach$3$$anonfun$apply$1(GenTraversableViewLike$FlatMapped$$anonfun$foreach$3 $outer) {}
/*     */         
/*     */         public final U apply(Object y) {
/*     */           return (U)this.$outer.f$3.apply(y);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class Appended$class {
/*     */     public static void $init$(GenTraversableViewLike.Appended $this) {}
/*     */     
/*     */     public static void foreach(GenTraversableViewLike.Appended $this, Function1 f) {
/*  99 */       $this.scala$collection$GenTraversableViewLike$Appended$$$outer().foreach(f);
/* 100 */       $this.rest().foreach(f);
/*     */     }
/*     */     
/*     */     public static final String viewIdentifier(GenTraversableViewLike.Appended $this) {
/* 102 */       return "A";
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class Filtered$class {
/*     */     public static void $init$(GenTraversableViewLike.Filtered $this) {}
/*     */     
/*     */     public static void foreach(GenTraversableViewLike.Filtered $this, Function1 f) {
/* 108 */       $this.scala$collection$GenTraversableViewLike$Filtered$$$outer().foreach((Function1)new GenTraversableViewLike$Filtered$$anonfun$foreach$4($this, (GenTraversableViewLike<A, Coll, This>.Filtered)f));
/*     */     }
/*     */     
/*     */     public static final String viewIdentifier(GenTraversableViewLike.Filtered $this) {
/* 111 */       return "F";
/*     */     }
/*     */   }
/*     */   
/*     */   public interface Filtered extends Transformed<A> {
/*     */     Function1<A, Object> pred();
/*     */     
/*     */     <U> void foreach(Function1<A, U> param1Function1);
/*     */     
/*     */     String viewIdentifier();
/*     */     
/*     */     public class GenTraversableViewLike$Filtered$$anonfun$foreach$4 extends AbstractFunction1<A, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Function1 f$4;
/*     */       
/*     */       public GenTraversableViewLike$Filtered$$anonfun$foreach$4(GenTraversableViewLike.Filtered $outer, Function1 f$4) {}
/*     */       
/*     */       public final Object apply(Object x) {
/*     */         return BoxesRunTime.unboxToBoolean(this.$outer.pred().apply(x)) ? this.f$4.apply(x) : BoxedUnit.UNIT;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class TakenWhile$class {
/*     */     public static void $init$(GenTraversableViewLike.TakenWhile $this) {}
/*     */     
/*     */     public static void foreach(GenTraversableViewLike.TakenWhile $this, Function1 f) {
/* 116 */       Object object = new Object();
/*     */       try {
/* 117 */         $this.scala$collection$GenTraversableViewLike$TakenWhile$$$outer().foreach((Function1)new GenTraversableViewLike$TakenWhile$$anonfun$foreach$5($this, object, (GenTraversableViewLike<A, Coll, This>.TakenWhile)f));
/*     */       } catch (NonLocalReturnControl nonLocalReturnControl2) {
/*     */         NonLocalReturnControl nonLocalReturnControl1;
/* 117 */         if ((nonLocalReturnControl1 = null).key() == object) {
/* 117 */           nonLocalReturnControl1.value$mcV$sp();
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     public static final String viewIdentifier(GenTraversableViewLike.TakenWhile $this) {
/* 122 */       return "T";
/*     */     }
/*     */   }
/*     */   
/*     */   public interface TakenWhile extends Transformed<A> {
/*     */     Function1<A, Object> pred();
/*     */     
/*     */     <U> void foreach(Function1<A, U> param1Function1);
/*     */     
/*     */     String viewIdentifier();
/*     */     
/*     */     public class GenTraversableViewLike$TakenWhile$$anonfun$foreach$5 extends AbstractFunction1<A, U> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Object nonLocalReturnKey2$1;
/*     */       
/*     */       private final Function1 f$5;
/*     */       
/*     */       public GenTraversableViewLike$TakenWhile$$anonfun$foreach$5(GenTraversableViewLike.TakenWhile $outer, Object nonLocalReturnKey2$1, Function1 f$5) {}
/*     */       
/*     */       public final U apply(Object x) {
/*     */         if (BoxesRunTime.unboxToBoolean(this.$outer.pred().apply(x)))
/*     */           return (U)this.f$5.apply(x); 
/*     */         throw new NonLocalReturnControl.mcV.sp(this.nonLocalReturnKey2$1, BoxedUnit.UNIT);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class DroppedWhile$class {
/*     */     public static void $init$(GenTraversableViewLike.DroppedWhile $this) {}
/*     */     
/*     */     public static void foreach(GenTraversableViewLike.DroppedWhile $this, Function1 f) {
/* 128 */       BooleanRef go = new BooleanRef(false);
/* 129 */       $this.scala$collection$GenTraversableViewLike$DroppedWhile$$$outer().foreach((Function1)new GenTraversableViewLike$DroppedWhile$$anonfun$foreach$6($this, go, (GenTraversableViewLike<A, Coll, This>.DroppedWhile)f));
/*     */     }
/*     */     
/*     */     public static final String viewIdentifier(GenTraversableViewLike.DroppedWhile $this) {
/* 134 */       return "D";
/*     */     }
/*     */   }
/*     */   
/*     */   public interface DroppedWhile extends Transformed<A> {
/*     */     Function1<A, Object> pred();
/*     */     
/*     */     <U> void foreach(Function1<A, U> param1Function1);
/*     */     
/*     */     String viewIdentifier();
/*     */     
/*     */     public class GenTraversableViewLike$DroppedWhile$$anonfun$foreach$6 extends AbstractFunction1<A, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final BooleanRef go$1;
/*     */       
/*     */       private final Function1 f$6;
/*     */       
/*     */       public GenTraversableViewLike$DroppedWhile$$anonfun$foreach$6(GenTraversableViewLike.DroppedWhile $outer, BooleanRef go$1, Function1 f$6) {}
/*     */       
/*     */       public final Object apply(Object x) {
/*     */         if (!this.go$1.elem && !BoxesRunTime.unboxToBoolean(this.$outer.pred().apply(x)))
/*     */           this.go$1.elem = true; 
/*     */         return this.go$1.elem ? this.f$6.apply(x) : BoxedUnit.UNIT;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public interface Forced<B> extends Transformed<B> {
/*     */     GenSeq<B> forced();
/*     */     
/*     */     <U> void foreach(Function1<B, U> param1Function1);
/*     */     
/*     */     String viewIdentifier();
/*     */   }
/*     */   
/*     */   public interface Appended<B> extends Transformed<B> {
/*     */     GenTraversable<B> rest();
/*     */     
/*     */     <U> void foreach(Function1<B, U> param1Function1);
/*     */     
/*     */     String viewIdentifier();
/*     */   }
/*     */   
/*     */   public interface EmptyView extends Transformed<Nothing$> {
/*     */     boolean isEmpty();
/*     */     
/*     */     <U> void foreach(Function1<Nothing$, U> param1Function1);
/*     */   }
/*     */   
/*     */   public interface Transformed<B> extends GenTraversableView<B, Coll> {
/*     */     <U> void foreach(Function1<B, U> param1Function1);
/*     */     
/*     */     Coll underlying();
/*     */     
/*     */     String viewIdString();
/*     */     
/*     */     String stringPrefix();
/*     */     
/*     */     String toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\GenTraversableViewLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */