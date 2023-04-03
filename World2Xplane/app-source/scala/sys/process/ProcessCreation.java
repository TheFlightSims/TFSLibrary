/*     */ package scala.sys.process;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.net.URL;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Seq;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.xml.Elem;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005edaB\001\003!\003\r\t!\003\002\020!J|7-Z:t\007J,\027\r^5p]*\0211\001B\001\baJ|7-Z:t\025\t)a!A\002tsNT\021aB\001\006g\016\fG.Y\002\001'\t\001!\002\005\002\f\0315\ta!\003\002\016\r\t1\021I\\=SK\032DQa\004\001\005\002A\ta\001J5oSR$C#A\t\021\005-\021\022BA\n\007\005\021)f.\033;\t\013U\001A\021\001\f\002\013\005\004\b\017\\=\025\005]Y\002C\001\r\032\033\005\021\021B\001\016\003\0059\001&o\\2fgN\024U/\0337eKJDQ\001\b\013A\002u\tqaY8n[\006tG\r\005\002\037C9\0211bH\005\003A\031\ta\001\025:fI\0264\027B\001\022$\005\031\031FO]5oO*\021\001E\002\005\006+\001!\t!\n\013\003/\031BQ\001\b\023A\002\035\0022\001\013\031\036\035\tIcF\004\002+[5\t1F\003\002-\021\0051AH]8pizJ\021aB\005\003_\031\tq\001]1dW\006<W-\003\0022e\t\0311+Z9\013\005=2\001\"B\013\001\t\003!DcA\f6m!)Ad\ra\001;!)qg\ra\001O\005I\021M]4v[\026tGo\035\005\006+\001!\t!\017\013\005/iZD\tC\003\035q\001\007Q\004C\003=q\001\007Q(A\002do\022\004\"AP!\017\005ay\024B\001!\003\003=\001(o\\2fgNLe\016^3s]\006d\027B\001\"D\005\0211\025\016\\3\013\005\001\023\001\"B#9\001\0041\025\001C3yiJ\fWI\034<\021\007-9\025*\003\002I\r\tQAH]3qK\006$X\r\032 \021\t-QU$H\005\003\027\032\021a\001V;qY\026\024\004\"B\013\001\t\003iE\003B\fO\037BCQ\001\b'A\002\035BQ\001\020'A\002uBQ!\022'A\002\031CQ!\006\001\005\002I#BaF*U1\")A$\025a\001;!)A(\025a\001+B\0311BV\037\n\005]3!AB(qi&|g\016C\003F#\002\007a\tC\003\026\001\021\005!\f\006\003\0307rk\006\"\002\017Z\001\0049\003\"\002\037Z\001\004)\006\"B#Z\001\0041\005\"B\013\001\t\003yFCA\fa\021\025\tg\f1\001c\003\035\021W/\0337eKJ\004\"AP2\n\005\021\034%a\004&Qe>\034Wm]:Ck&dG-\032:\t\013U\001A\021\0014\025\005\035t\007C\0015l\035\tA\022.\003\002k\005\005q\001K]8dKN\034()^5mI\026\024\030B\0017n\005-1\025\016\\3Ck&dG-\032:\013\005)\024\001\"B8f\001\004i\024\001\0024jY\026DQ!\006\001\005\002E$\"A];\021\005!\034\030B\001;n\005))&\013\024\"vS2$WM\035\005\006mB\004\ra^\001\004kJd\007C\001 y\023\tI8IA\002V%2CQ!\006\001\005\002m$\"a\006?\t\013qQ\b\031A?\021\007y\f\031!D\001\000\025\r\t\tAB\001\004q6d\027bAA\003\n!Q\t\\3n\021\031)\002\001\"\001\002\nQ\031q#a\003\t\021\0055\021q\001a\001\003\037\tQA^1mk\026\0042aCA\t\023\r\t\031B\002\002\b\005>|G.Z1o\021\031)\002\001\"\001\002\030Q)q#!\007\002\036!9\0211DA\013\001\004i\022\001\0028b[\026D\021\"a\b\002\026\021\005\r!!\t\002\023\025D\030\016\036,bYV,\007#B\006\002$\005\035\022bAA\023\r\tAAHY=oC6,g\bE\002\f\003SI1!a\013\007\005\rIe\016\036\005\b\003_\001A\021AA\031\003!\t\007\017\0357z'\026\fX\003BA\032\003\033\"B!!\016\002`Q!\021qGA !\021A\003'!\017\021\007!\fY$C\002\002>5\024aaU8ve\016,\007\002CA!\003[\001\035!a\021\002\017\r|gN^3siB91\"!\022\002J\005e\022bAA$\r\tIa)\0368di&|g.\r\t\005\003\027\ni\005\004\001\005\021\005=\023Q\006b\001\003#\022\021\001V\t\005\003'\nI\006E\002\f\003+J1!a\026\007\005\035qu\016\0365j]\036\0042aCA.\023\r\tiF\002\002\004\003:L\b\002CA1\003[\001\r!a\031\002\021\t,\030\016\0343feN\004B\001\013\031\002J!9\021q\r\001\005\002\005%\024aA2biR)q#a\033\002n!9q.!\032A\002\005e\002\002CA8\003K\002\r!!\035\002\013\031LG.Z:\021\t-9\025\021\b\005\b\003O\002A\021AA;)\r9\022q\017\005\t\003_\n\031\b1\001\0028\001")
/*     */ public interface ProcessCreation {
/*     */   ProcessBuilder apply(String paramString);
/*     */   
/*     */   ProcessBuilder apply(Seq<String> paramSeq);
/*     */   
/*     */   ProcessBuilder apply(String paramString, Seq<String> paramSeq);
/*     */   
/*     */   ProcessBuilder apply(String paramString, File paramFile, Seq<Tuple2<String, String>> paramSeq);
/*     */   
/*     */   ProcessBuilder apply(Seq<String> paramSeq, File paramFile, Seq<Tuple2<String, String>> paramSeq1);
/*     */   
/*     */   ProcessBuilder apply(String paramString, Option<File> paramOption, Seq<Tuple2<String, String>> paramSeq);
/*     */   
/*     */   ProcessBuilder apply(Seq<String> paramSeq, Option<File> paramOption, Seq<Tuple2<String, String>> paramSeq1);
/*     */   
/*     */   ProcessBuilder apply(java.lang.ProcessBuilder paramProcessBuilder);
/*     */   
/*     */   ProcessBuilder.FileBuilder apply(File paramFile);
/*     */   
/*     */   ProcessBuilder.URLBuilder apply(URL paramURL);
/*     */   
/*     */   ProcessBuilder apply(Elem paramElem);
/*     */   
/*     */   ProcessBuilder apply(boolean paramBoolean);
/*     */   
/*     */   ProcessBuilder apply(String paramString, Function0<Object> paramFunction0);
/*     */   
/*     */   <T> Seq<ProcessBuilder.Source> applySeq(Seq<T> paramSeq, Function1<T, ProcessBuilder.Source> paramFunction1);
/*     */   
/*     */   ProcessBuilder cat(ProcessBuilder.Source paramSource, Seq<ProcessBuilder.Source> paramSeq);
/*     */   
/*     */   ProcessBuilder cat(Seq<ProcessBuilder.Source> paramSeq);
/*     */   
/*     */   public class ProcessCreation$$anonfun$apply$3 extends AbstractFunction1<Tuple2<String, String>, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final java.lang.ProcessBuilder jpb$1;
/*     */     
/*     */     public final String apply(Tuple2 x0$1) {
/* 105 */       if (x0$1 != null)
/* 105 */         return (String)this.jpb$1.environment().put(x0$1._1(), x0$1._2()); 
/* 105 */       throw new MatchError(x0$1);
/*     */     }
/*     */     
/*     */     public ProcessCreation$$anonfun$apply$3(ProcessCreation $outer, java.lang.ProcessBuilder jpb$1) {}
/*     */   }
/*     */   
/*     */   public class ProcessCreation$$anonfun$apply$1 extends AbstractFunction0.mcI.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final boolean value$1;
/*     */     
/*     */     public final int apply() {
/* 141 */       return apply$mcI$sp();
/*     */     }
/*     */     
/*     */     public int apply$mcI$sp() {
/* 141 */       return this.value$1 ? 0 : 1;
/*     */     }
/*     */     
/*     */     public ProcessCreation$$anonfun$apply$1(ProcessCreation $outer, boolean value$1) {}
/*     */   }
/*     */   
/*     */   public class ProcessCreation$$anonfun$cat$1 extends AbstractFunction1<ProcessBuilder.Source, ProcessBuilder> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final ProcessBuilder apply(ProcessBuilder.Source x$4) {
/* 182 */       return x$4.cat();
/*     */     }
/*     */     
/*     */     public ProcessCreation$$anonfun$cat$1(ProcessCreation $outer) {}
/*     */   }
/*     */   
/*     */   public class ProcessCreation$$anonfun$cat$2 extends AbstractFunction2<ProcessBuilder, ProcessBuilder, ProcessBuilder> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final ProcessBuilder apply(ProcessBuilder x$5, ProcessBuilder x$6) {
/* 182 */       return x$5.$hash$amp$amp(x$6);
/*     */     }
/*     */     
/*     */     public ProcessCreation$$anonfun$cat$2(ProcessCreation $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\sys\process\ProcessCreation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */