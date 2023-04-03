/*     */ package scala.sys.process;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.net.URL;
/*     */ import scala.Function0;
/*     */ import scala.collection.immutable.Stream;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\005haB\001\003!\003\r\n!\003\002\017!J|7-Z:t\005VLG\016Z3s\025\t\031A!A\004qe>\034Wm]:\013\005\0251\021aA:zg*\tq!A\003tG\006d\027m\001\001\024\013\001Qa\"!\004\021\005-aQ\"\001\004\n\00551!AB!osJ+g\r\005\002\020C9\021\001#E\007\002\005\035)!C\001E\001'\005q\001K]8dKN\034()^5mI\026\024\bC\001\t\025\r\025\t!\001#\001\026'\r!\"B\006\t\003!]I!\001\007\002\003%A\023xnY3tg\n+\030\016\0343fe&k\007\017\034\005\0065Q!\taG\001\007y%t\027\016\036 \025\003M1q!\b\013\021\002G\005aD\001\006V%2\023U/\0337eKJ\0342\001\b\006 !\t\001\023%D\001\025\r\035\021C\003%A\002\002\r\022aaU8ve\016,7CA\021\013\021\025)\023\005\"\001'\003\031!\023N\\5uIQ\tq\005\005\002\fQ%\021\021F\002\002\005+:LG\017C\003,C\031EA&\001\005u_N{WO]2f+\005i\003C\001\t\001\021\025y\023\005\"\0011\0035!\003.Y:iI\035\024X-\031;feR\021Q&\r\005\006e9\002\raM\001\002MB\021Ag\016\b\003!UJ!A\016\002\002\037A\024xnY3tg&sG/\032:oC2L!\001O\035\003\t\031KG.\032\006\003m\tAQaO\021\005\002q\nQ\003\n5bg\"$sM]3bi\026\024He\032:fCR,'\017\006\002.{!)!G\017a\001g!)q&\tC\001Q\021Q\006\021\005\007\003z\"\t\031\001\"\002\007=,H\017E\002\f\007\026K!\001\022\004\003\021q\022\027P\\1nKz\002\"\001\016$\n\005\035K$\001D(viB,Ho\025;sK\006l\007\"B\030\"\t\003IECA\027K\021\025Y\005\n1\001.\003\005\021\007\"B'\"\t\003a\023aA2bi\")q*\tC\005!\0061Ao\034$jY\026$2!L)S\021\025\021d\n1\0014\021\025\031f\n1\001U\003\031\t\007\017]3oIB\0211\"V\005\003-\032\021qAQ8pY\026\fgNB\004Y)A\005\031\023A-\003\027\031KG.\032\"vS2$WM]\n\005/*Qv\004\005\002!7\0329A\f\006I\001\004\003i&\001B*j].\034\"a\027\006\t\013\025ZF\021\001\024\t\013\001\\f\021\003\027\002\rQ|7+\0338l\021\025\0217\f\"\001d\003)!\003.Y:iI1,7o\035\013\003[\021DQAM1A\002MBQAY.\005\002\031$\"!L4\t\013I*\007\031\0015\021\005QJ\027B\0016:\005\r)&\013\024\005\006En#\t\001\034\013\003[5DaA\\6\005\002\004y\027AA5o!\rY1\t\035\t\003iEL!A]\035\003\027%s\007/\036;TiJ,\027-\034\005\006En#\t\001\036\013\003[UDQaS:A\0025BQa^,\007\002a\fq\002\n5bg\"$C.Z:tI1,7o\035\013\003[eDQA\r<A\002MBQa^,\007\002m$\"!\f?\t\013uT\b\031\0015\002\003UDQa^,\007\002}$2!LA\001\021\035\t\031A CA\002=\f\021!\033\005\007o^3\t!a\002\025\0075\nI\001C\004\002\f\005\025\001\031A\027\002\003A\004\"aD.\t\017\005E\001A\"\001\002\024\005QAEY1oO\022\022\027M\\4\026\005\005U\001\003BA\f\003;q1aCA\r\023\r\tYBB\001\007!J,G-\0324\n\t\005}\021\021\005\002\007'R\024\030N\\4\013\007\005ma\001C\004\002\022\0011\t!!\n\025\t\005U\021q\005\005\t\003S\t\031\0031\001\002,\005\031An\\4\021\007A\ti#C\002\0020\t\021Q\002\025:pG\026\0348\017T8hO\026\024\bbBA\032\001\031\005\0211C\001\020I\t\fgn\032\023cC:<G\005\\3tg\"9\0211\007\001\007\002\005]B\003BA\013\003sA\001\"!\013\0026\001\007\0211\006\005\b\003{\001a\021AA \003\025a\027N\\3t+\t\t\t\005\005\004\002D\005M\023Q\003\b\005\003\013\nyE\004\003\002H\0055SBAA%\025\r\tY\005C\001\007yI|w\016\036 \n\003\035I1!!\025\007\003\035\001\030mY6bO\026LA!!\026\002X\t11\013\036:fC6T1!!\025\007\021\035\ti\004\001D\001\0037\"B!!\021\002^!A\021\021FA-\001\004\tY\003C\004\002b\0011\t!a\020\002\0271Lg.Z:`I\t\fgn\032\005\b\003C\002a\021AA3)\021\t\t%a\032\t\021\005%\0221\ra\001\003WAq!a\033\001\r\003\ti'A\003%E\006tw-\006\002\002pA\0311\"!\035\n\007\005MdAA\002J]RDq!a\033\001\r\003\t9\b\006\003\002p\005e\004\002CA\025\003k\002\r!a\013\t\017\005u\004A\"\001\002n\005QAEY1oO\022bWm]:\t\017\005u\004A\"\001\002\002R!\021qNAB\021!\tI#a A\002\005-\002bBAD\001\031\005\021\021R\001\004eVtGCAAF!\r\001\022QR\005\004\003\037\023!a\002)s_\016,7o\035\005\b\003\017\003a\021AAJ)\021\tY)!&\t\021\005%\022\021\023a\001\003WAq!a\"\001\r\003\tI\n\006\003\002\f\006m\005\002CAO\003/\003\r!a(\002\005%|\007c\001\t\002\"&\031\0211\025\002\003\023A\023xnY3tg&{\005bBAD\001\031\005\021q\025\013\005\003\027\013I\013C\004\002,\006\025\006\031\001+\002\031\r|gN\\3di&s\007/\036;\t\017\005\035\005A\"\001\0020R1\0211RAY\003gC\001\"!\013\002.\002\007\0211\006\005\b\003W\013i\0131\001U\021\035\t9\f\001D\001\003s\013Q\002\n5bg\"$\023-\0349%C6\004HcA\027\002<\"9\021QXA[\001\004i\023!B8uQ\026\024\bbBAa\001\031\005\0211Y\001\016I!\f7\017\033\023cCJ$#-\031:\025\0075\n)\rC\004\002>\006}\006\031A\027\t\017\005%\007A\"\001\002L\006IA\005[1tQ\022\022\027M\035\013\004[\0055\007bBA_\003\017\004\r!\f\005\b\003#\004a\021AAj\003=!\003.Y:iI!\f7\017\033\023iCNDGcA\027\002V\"9\021QXAh\001\004i\003bBAm\001\031\005\0211\\\001\nG\006t\007+\0339f)>,\022\001\026\005\b\003?\004a\021AAn\0031A\027m]#ySR4\026\r\\;f\001")
/*     */ public interface ProcessBuilder extends ProcessBuilder.Source, ProcessBuilder.Sink {
/*     */   String $bang$bang();
/*     */   
/*     */   String $bang$bang(ProcessLogger paramProcessLogger);
/*     */   
/*     */   String $bang$bang$less();
/*     */   
/*     */   String $bang$bang$less(ProcessLogger paramProcessLogger);
/*     */   
/*     */   Stream<String> lines();
/*     */   
/*     */   Stream<String> lines(ProcessLogger paramProcessLogger);
/*     */   
/*     */   Stream<String> lines_$bang();
/*     */   
/*     */   Stream<String> lines_$bang(ProcessLogger paramProcessLogger);
/*     */   
/*     */   int $bang();
/*     */   
/*     */   int $bang(ProcessLogger paramProcessLogger);
/*     */   
/*     */   int $bang$less();
/*     */   
/*     */   int $bang$less(ProcessLogger paramProcessLogger);
/*     */   
/*     */   Process run();
/*     */   
/*     */   Process run(ProcessLogger paramProcessLogger);
/*     */   
/*     */   Process run(ProcessIO paramProcessIO);
/*     */   
/*     */   Process run(boolean paramBoolean);
/*     */   
/*     */   Process run(ProcessLogger paramProcessLogger, boolean paramBoolean);
/*     */   
/*     */   ProcessBuilder $hash$amp$amp(ProcessBuilder paramProcessBuilder);
/*     */   
/*     */   ProcessBuilder $hash$bar$bar(ProcessBuilder paramProcessBuilder);
/*     */   
/*     */   ProcessBuilder $hash$bar(ProcessBuilder paramProcessBuilder);
/*     */   
/*     */   ProcessBuilder $hash$hash$hash(ProcessBuilder paramProcessBuilder);
/*     */   
/*     */   boolean canPipeTo();
/*     */   
/*     */   boolean hasExitValue();
/*     */   
/*     */   public static abstract class Source$class {
/*     */     public static void $init$(ProcessBuilder.Source $this) {}
/*     */     
/*     */     public static ProcessBuilder $hash$greater(ProcessBuilder.Source $this, File f) {
/* 308 */       return toFile($this, f, false);
/*     */     }
/*     */     
/*     */     public static ProcessBuilder $hash$greater$greater(ProcessBuilder.Source $this, File f) {
/* 311 */       return toFile($this, f, true);
/*     */     }
/*     */     
/*     */     public static ProcessBuilder $hash$greater(ProcessBuilder.Source $this, Function0<OutputStream> out) {
/* 317 */       return $this.$hash$greater(new ProcessBuilderImpl.OStreamBuilder(ProcessBuilder$.MODULE$, out, "<output stream>"));
/*     */     }
/*     */     
/*     */     public static ProcessBuilder $hash$greater(ProcessBuilder.Source $this, ProcessBuilder b) {
/* 320 */       return new ProcessBuilderImpl.PipedBuilder(ProcessBuilder$.MODULE$, $this.toSource(), b, false);
/*     */     }
/*     */     
/*     */     public static ProcessBuilder cat(ProcessBuilder.Source $this) {
/* 323 */       return $this.toSource();
/*     */     }
/*     */     
/*     */     private static ProcessBuilder toFile(ProcessBuilder.Source $this, File f, boolean append) {
/* 324 */       return $this.$hash$greater(new ProcessBuilderImpl.FileOutput(ProcessBuilder$.MODULE$, f, append));
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface FileBuilder extends Sink, Source {
/*     */     ProcessBuilder $hash$less$less(File param1File);
/*     */     
/*     */     ProcessBuilder $hash$less$less(URL param1URL);
/*     */     
/*     */     ProcessBuilder $hash$less$less(Function0<InputStream> param1Function0);
/*     */     
/*     */     ProcessBuilder $hash$less$less(ProcessBuilder param1ProcessBuilder);
/*     */   }
/*     */   
/*     */   public static abstract class Sink$class {
/*     */     public static void $init$(ProcessBuilder.Sink $this) {}
/*     */     
/*     */     public static ProcessBuilder $hash$less(ProcessBuilder.Sink $this, File f) {
/* 334 */       return $this.$hash$less(new ProcessBuilderImpl.FileInput(ProcessBuilder$.MODULE$, f));
/*     */     }
/*     */     
/*     */     public static ProcessBuilder $hash$less(ProcessBuilder.Sink $this, URL f) {
/* 337 */       return $this.$hash$less(new ProcessBuilderImpl.URLInput(ProcessBuilder$.MODULE$, f));
/*     */     }
/*     */     
/*     */     public static ProcessBuilder $hash$less(ProcessBuilder.Sink $this, Function0<InputStream> in) {
/* 343 */       return $this.$hash$less(new ProcessBuilderImpl.IStreamBuilder(ProcessBuilder$.MODULE$, in, "<input stream>"));
/*     */     }
/*     */     
/*     */     public static ProcessBuilder $hash$less(ProcessBuilder.Sink $this, ProcessBuilder b) {
/* 346 */       return new ProcessBuilderImpl.PipedBuilder(ProcessBuilder$.MODULE$, b, $this.toSink(), false);
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface URLBuilder extends Source {}
/*     */   
/*     */   public static interface Source {
/*     */     ProcessBuilder toSource();
/*     */     
/*     */     ProcessBuilder $hash$greater(File param1File);
/*     */     
/*     */     ProcessBuilder $hash$greater$greater(File param1File);
/*     */     
/*     */     ProcessBuilder $hash$greater(Function0<OutputStream> param1Function0);
/*     */     
/*     */     ProcessBuilder $hash$greater(ProcessBuilder param1ProcessBuilder);
/*     */     
/*     */     ProcessBuilder cat();
/*     */   }
/*     */   
/*     */   public static interface Sink {
/*     */     ProcessBuilder toSink();
/*     */     
/*     */     ProcessBuilder $hash$less(File param1File);
/*     */     
/*     */     ProcessBuilder $hash$less(URL param1URL);
/*     */     
/*     */     ProcessBuilder $hash$less(Function0<InputStream> param1Function0);
/*     */     
/*     */     ProcessBuilder $hash$less(ProcessBuilder param1ProcessBuilder);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\sys\process\ProcessBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */