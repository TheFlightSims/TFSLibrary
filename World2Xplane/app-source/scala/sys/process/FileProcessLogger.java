/*    */ package scala.sys.process;
/*    */ 
/*    */ import java.io.BufferedWriter;
/*    */ import java.io.Closeable;
/*    */ import java.io.File;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.Flushable;
/*    */ import java.io.OutputStreamWriter;
/*    */ import java.io.PrintWriter;
/*    */ import scala.Function0;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001q3A!\001\002\001\023\t\tb)\0337f!J|7-Z:t\031><w-\032:\013\005\r!\021a\0029s_\016,7o\035\006\003\013\031\t1a]=t\025\0059\021!B:dC2\f7\001A\n\006\001)q!C\007\t\003\0271i\021AB\005\003\033\031\021a!\0218z%\0264\007CA\b\021\033\005\021\021BA\t\003\0055\001&o\\2fgNdunZ4feB\0211\003G\007\002))\021QCF\001\003S>T\021aF\001\005U\0064\030-\003\002\032)\tI1\t\\8tK\006\024G.\032\t\003'mI!\001\b\013\003\023\031cWo\0355bE2,\007\002\003\020\001\005\003\005\013\021B\020\002\t\031LG.\032\t\003'\001J!!\t\013\003\t\031KG.\032\005\006G\001!\t\001J\001\007y%t\027\016\036 \025\005\0252\003CA\b\001\021\025q\"\0051\001 \021\035A\003A1A\005\n%\naa\036:ji\026\024X#\001\026\021\005MY\023B\001\027\025\005-\001&/\0338u/JLG/\032:\t\r9\002\001\025!\003+\003\0359(/\033;fe\002BQ\001\r\001\005\002E\n1a\\;u)\t\021T\007\005\002\fg%\021AG\002\002\005+:LG\017\003\0047_\021\005\raN\001\002gB\0311\002\017\036\n\005e2!\001\003\037cs:\fW.\032 \021\005mrdBA\006=\023\tid!\001\004Qe\026$WMZ\005\003\001\023aa\025;sS:<'BA\037\007\021\025\021\005\001\"\001D\003\r)'O\035\013\003e\021CaAN!\005\002\0049\004\"\002$\001\t\0039\025A\0022vM\032,'/\006\002I\027R\021\021\n\026\t\003\025.c\001\001B\003M\013\n\007QJA\001U#\tq\025\013\005\002\f\037&\021\001K\002\002\b\035>$\b.\0338h!\tY!+\003\002T\r\t\031\021I\\=\t\rU+E\0211\001W\003\0051\007cA\0069\023\")\001\f\001C\0013\006)1\r\\8tKR\t!\007C\003\\\001\021\005\021,A\003gYV\034\b\016")
/*    */ public class FileProcessLogger implements ProcessLogger, Closeable, Flushable {
/*    */   private final PrintWriter writer;
/*    */   
/*    */   public FileProcessLogger(File file) {
/* 58 */     this.writer = 
/* 59 */       new PrintWriter(
/* 60 */         new BufferedWriter(
/* 61 */           new OutputStreamWriter(
/* 62 */             new FileOutputStream(file, true))));
/*    */   }
/*    */   
/*    */   private PrintWriter writer() {
/*    */     return this.writer;
/*    */   }
/*    */   
/*    */   public void out(Function0 s) {
/* 67 */     writer().println((String)s.apply());
/*    */   }
/*    */   
/*    */   public void err(Function0 s) {
/* 68 */     writer().println((String)s.apply());
/*    */   }
/*    */   
/*    */   public <T> T buffer(Function0 f) {
/* 69 */     return (T)f.apply();
/*    */   }
/*    */   
/*    */   public void close() {
/* 70 */     writer().close();
/*    */   }
/*    */   
/*    */   public void flush() {
/* 71 */     writer().flush();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\sys\process\FileProcessLogger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */