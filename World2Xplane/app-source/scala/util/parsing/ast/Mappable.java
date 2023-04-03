/*    */ package scala.util.parsing.ast;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.None$;
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.collection.immutable.List$;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005=aaB\001\003!\003\r\ta\003\002\t\033\006\004\b/\0312mK*\0211\001B\001\004CN$(BA\003\007\003\035\001\030M]:j]\036T!a\002\005\002\tU$\030\016\034\006\002\023\005)1oY1mC\016\0011C\001\001\r!\tia\"D\001\t\023\ty\001B\001\004B]f\024VM\032\005\006#\001!\tAE\001\007I%t\027\016\036\023\025\003M\001\"!\004\013\n\005UA!\001B+oSR4qa\006\001\021\002G\005\001D\001\004NCB\004XM]\n\003-1AQA\007\f\007\002m\tQ!\0319qYf,\"\001\b\021\025\005u)EC\001\020*!\ty\002\005\004\001\005\013\005J\"\031\001\022\003\003Q\013\"a\t\024\021\0055!\023BA\023\t\005\035qu\016\0365j]\036\004\"!D\024\n\005!B!aA!os\"9!&GA\001\002\bY\023AC3wS\022,gnY3%cA!Q\002\f\020/\023\ti\003BA\005Gk:\034G/[8ocA\031q\006\r\020\016\003\0011q!\001\001\021\002\007\005\021'\006\0023qM\021\001\007\004\005\006#A\"\tA\005\005\006kA2\tAN\001\005O6\f\007\017\006\0028sA\021q\004\017\003\006CA\022\rA\t\005\006uQ\002\raO\001\002MB\021qF\006\005\006{A\"\tAP\001\013KZ,'/_<iKJ,GCA E)\t9\004\tC\003By\001\017!)A\001d!\021iAfN\"\021\007=\002t\007C\003;y\001\0071\bC\003G3\001\007a$A\001y\021\025A\005\001b\001J\003A\031FO]5oO&\033X*\0319qC\ndW\r\006\002K%B\031q\006M&\021\0051{eBA\007N\023\tq\005\"\001\004Qe\026$WMZ\005\003!F\023aa\025;sS:<'B\001(\t\021\025\031v\t1\001L\003\005\031\b\"B+\001\t\0071\026A\004'jgRL5/T1qa\006\024G.Z\013\003/\036$\"\001W7\025\005eK\007cA\03015B\0311l\0314\017\005q\013gBA/a\033\005q&BA0\013\003\031a$o\\8u}%\t\021\"\003\002c\021\0059\001/Y2lC\036,\027B\0013f\005\021a\025n\035;\013\005\tD\001CA\020h\t\025AGK1\001#\005\005!\bb\0026U\003\003\005\035a[\001\013KZLG-\0328dK\022\032\004\003B\007-M2\0042a\f\031g\021\025qG\0131\001[\003\tA8\017C\003q\001\021\r\021/\001\tPaRLwN\\%t\033\006\004\b/\0312mKV\021!/\037\013\003gz$\"\001\036>\021\007=\002T\017E\002\016mbL!a\036\005\003\r=\003H/[8o!\ty\022\020B\003i_\n\007!\005C\004|_\006\005\t9\001?\002\025\0254\030\016Z3oG\026$C\007\005\003\016Yal\bcA\0301q\")an\034a\001k\":\001!!\001\002\b\005-\001cA\007\002\004%\031\021Q\001\005\003\025\021,\007O]3dCR,G-\t\002\002\n\005QB\013[5tA\rd\027m]:!o&dG\016\t2fAI,Wn\034<fI\006\022\021QB\001\007e9\n\004G\f\031")
/*    */ public interface Mappable {
/*    */   Mappable<String> StringIsMappable(String paramString);
/*    */   
/*    */   <t> Mappable<List<t>> ListIsMappable(List<t> paramList, Function1<t, Mappable<t>> paramFunction1);
/*    */   
/*    */   <t> Mappable<Option<t>> OptionIsMappable(Option<t> paramOption, Function1<t, Mappable<t>> paramFunction1);
/*    */   
/*    */   public interface Mappable<T> {
/*    */     T gmap(Mappable.Mapper param1Mapper);
/*    */     
/*    */     T everywhere(Mappable.Mapper param1Mapper, Function1<T, Mappable<T>> param1Function1);
/*    */     
/*    */     public class Mappable$Mappable$$anon$1 implements Mappable.Mapper {
/*    */       private final Mappable.Mapper f$4;
/*    */       
/*    */       public <T> T apply(Object x, Function1<T, Mappable.Mappable<T>> evidence$2) {
/* 41 */         return ((Mappable.Mappable<T>)evidence$2.apply(x)).everywhere(this.f$4, evidence$2);
/*    */       }
/*    */       
/*    */       public Mappable$Mappable$$anon$1(Mappable.Mappable $outer, Mappable.Mapper f$4) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public abstract class Mappable$class {
/*    */     public static void $init$(Mappable.Mappable $this) {}
/*    */     
/*    */     public static Object everywhere(Mappable.Mappable $this, Mappable.Mapper f, Function1<?, Mappable.Mappable<?>> c) {
/* 41 */       return f.apply($this.gmap(new Mappable$Mappable$$anon$1($this, (Mappable.Mappable<T>)f)), c);
/*    */     }
/*    */   }
/*    */   
/*    */   public class Mappable$$anon$2 implements Mappable<String> {
/*    */     private final String s$1;
/*    */     
/*    */     public String everywhere(Mappable.Mapper f, Function1 c) {
/* 45 */       return (String)Mappable.Mappable$class.everywhere(this, f, c);
/*    */     }
/*    */     
/*    */     public Mappable$$anon$2(Mappable $outer, String s$1) {
/* 45 */       Mappable.Mappable$class.$init$(this);
/*    */     }
/*    */     
/*    */     public String gmap(Mappable.Mapper f) {
/* 46 */       return f.<String>apply(this.s$1, (Function1<String, Mappable.Mappable<String>>)new Mappable$$anon$2$$anonfun$gmap$1(this));
/*    */     }
/*    */     
/*    */     public class Mappable$$anon$2$$anonfun$gmap$1 extends AbstractFunction1<String, Mappable.Mappable<String>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Mappable.Mappable<String> apply(String s) {
/* 46 */         return this.$outer.scala$util$parsing$ast$Mappable$$anon$$$outer().StringIsMappable(s);
/*    */       }
/*    */       
/*    */       public Mappable$$anon$2$$anonfun$gmap$1(Mappable$$anon$2 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class Mappable$$anon$3 implements Mappable<List<t>> {
/*    */     private final List xs$1;
/*    */     
/*    */     public final Function1 evidence$3$1;
/*    */     
/*    */     public List<t> everywhere(Mappable.Mapper f, Function1 c) {
/* 50 */       return (List<t>)Mappable.Mappable$class.everywhere(this, f, c);
/*    */     }
/*    */     
/*    */     public Mappable$$anon$3(Mappable $outer, List xs$1, Function1 evidence$3$1) {
/* 50 */       Mappable.Mappable$class.$init$(this);
/*    */     }
/*    */     
/*    */     public List<t> gmap(Mappable.Mapper f) {
/* 51 */       return ((List)this.xs$1.map((Function1)new Mappable$$anon$3$$anonfun$gmap$2(this, f), List$.MODULE$.canBuildFrom())).toList();
/*    */     }
/*    */     
/*    */     public class Mappable$$anon$3$$anonfun$gmap$2 extends AbstractFunction1<t, t> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final Mappable.Mapper f$1;
/*    */       
/*    */       public final t apply(Object x) {
/* 51 */         return this.f$1.apply((t)x, this.$outer.evidence$3$1);
/*    */       }
/*    */       
/*    */       public Mappable$$anon$3$$anonfun$gmap$2(Mappable$$anon$3 $outer, Mappable.Mapper f$1) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class Mappable$$anon$4 implements Mappable<Option<t>> {
/*    */     private final Option xs$2;
/*    */     
/*    */     public final Function1 evidence$4$1;
/*    */     
/*    */     public Option<t> everywhere(Mappable.Mapper f, Function1 c) {
/* 55 */       return (Option<t>)Mappable.Mappable$class.everywhere(this, f, c);
/*    */     }
/*    */     
/*    */     public Mappable$$anon$4(Mappable $outer, Option xs$2, Function1 evidence$4$1) {
/* 55 */       Mappable.Mappable$class.$init$(this);
/*    */     }
/*    */     
/*    */     public Option<t> gmap(Mappable.Mapper f) {
/* 56 */       Object object = option.get();
/*    */       Option option;
/* 56 */       return (option = this.xs$2).isEmpty() ? (Option<t>)None$.MODULE$ : (Option<t>)new Some(f.apply(object, this.evidence$4$1));
/*    */     }
/*    */   }
/*    */   
/*    */   public interface Mapper {
/*    */     <T> T apply(T param1T, Function1<T, Mappable.Mappable<T>> param1Function1);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\ast\Mappable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */