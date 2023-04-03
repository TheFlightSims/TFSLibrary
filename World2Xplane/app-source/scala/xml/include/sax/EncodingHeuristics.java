/*    */ package scala.xml.include.sax;
/*    */ 
/*    */ import java.io.InputStream;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001Y;Q!\001\002\t\002-\t!#\0228d_\022Lgn\032%fkJL7\017^5dg*\0211\001B\001\004g\006D(BA\003\007\003\035Ign\0317vI\026T!a\002\005\002\007alGNC\001\n\003\025\0318-\0317b\007\001\001\"\001D\007\016\003\t1QA\004\002\t\002=\021!#\0228d_\022Lgn\032%fkJL7\017^5dgN\021Q\002\005\t\003#Ii\021\001C\005\003'!\021a!\0218z%\0264\007\"B\013\016\t\0031\022A\002\037j]&$h\bF\001\f\017\025AR\002#\001\032\0035)enY8eS:<g*Y7fgB\021!dG\007\002\033\031)A$\004E\001;\tiQI\\2pI&twMT1nKN\034\"a\007\t\t\013UYB\021A\020\025\003eAq!I\016C\002\023\005!%A\004cS\036,6i\025\033\026\003\r\002\"\001J\025\016\003\025R!AJ\024\002\t1\fgn\032\006\002Q\005!!.\031<b\023\tQSE\001\004TiJLgn\032\005\007Ym\001\013\021B\022\002\021\tLw-V\"Ti\001BqAL\016C\002\023\005!%\001\006mSR$H.Z+D'RBa\001M\016!\002\023\031\023a\0037jiRdW-V\"Ti\001BqAM\016C\002\023\005!%A\006v]V\034X/\0317V\007N#\004B\002\033\034A\003%1%\001\007v]V\034X/\0317V\007N#\004\005C\00477\t\007I\021\001\022\002\021\tLw-\026+GcYBa\001O\016!\002\023\031\023!\0032jOV#f)\r\034!\021\035Q4D1A\005\002\t\n1\002\\5ui2,W\013\026$2m!1Ah\007Q\001\n\r\nA\002\\5ui2,W\013\026$2m\001BqAP\016C\002\023\005!%\001\003vi\032D\004B\002!\034A\003%1%A\003vi\032D\004\005C\004C7\t\007I\021\001\022\002\017\021,g-Y;mi\"1Ai\007Q\001\n\r\n\001\002Z3gCVdG\017\t\005\006\r6!\taR\001\027e\026\fG-\0228d_\022Lgn\032$s_6\034FO]3b[R\021\001J\024\t\003\0232s!!\005&\n\005-C\021A\002)sK\022,g-\003\002+\033*\0211\n\003\005\006\037\026\003\r\001U\001\003S:\004\"!\025+\016\003IS!aU\024\002\005%|\027BA+S\005-Ie\016];u'R\024X-Y7")
/*    */ public final class EncodingHeuristics {
/*    */   public static String readEncodingFromStream(InputStream paramInputStream) {
/*    */     return EncodingHeuristics$.MODULE$.readEncodingFromStream(paramInputStream);
/*    */   }
/*    */   
/*    */   public static class EncodingNames$ {
/*    */     public static final EncodingNames$ MODULE$;
/*    */     
/*    */     private final String bigUCS4;
/*    */     
/*    */     private final String littleUCS4;
/*    */     
/*    */     private final String unusualUCS4;
/*    */     
/*    */     private final String bigUTF16;
/*    */     
/*    */     private final String littleUTF16;
/*    */     
/*    */     private final String utf8;
/*    */     
/*    */     private final String default;
/*    */     
/*    */     public EncodingNames$() {
/* 28 */       MODULE$ = this;
/* 30 */       this.bigUCS4 = "UCS-4";
/* 31 */       this.littleUCS4 = "UCS-4";
/* 32 */       this.unusualUCS4 = "UCS-4";
/* 33 */       this.bigUTF16 = "UTF-16BE";
/* 34 */       this.littleUTF16 = "UTF-16LE";
/* 35 */       this.utf8 = "UTF-8";
/* 36 */       this.default = utf8();
/*    */     }
/*    */     
/*    */     public String bigUCS4() {
/*    */       return this.bigUCS4;
/*    */     }
/*    */     
/*    */     public String littleUCS4() {
/*    */       return this.littleUCS4;
/*    */     }
/*    */     
/*    */     public String unusualUCS4() {
/*    */       return this.unusualUCS4;
/*    */     }
/*    */     
/*    */     public String bigUTF16() {
/*    */       return this.bigUTF16;
/*    */     }
/*    */     
/*    */     public String littleUTF16() {
/*    */       return this.littleUTF16;
/*    */     }
/*    */     
/*    */     public String utf8() {
/*    */       return this.utf8;
/*    */     }
/*    */     
/*    */     public String default() {
/* 36 */       return this.default;
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\include\sax\EncodingHeuristics.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */