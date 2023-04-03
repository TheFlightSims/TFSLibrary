/*    */ package scala.xml.parsing;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.net.URL;
/*    */ import scala.Predef$;
/*    */ import scala.collection.immutable.StringOps;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.io.Codec$;
/*    */ import scala.io.Source;
/*    */ import scala.io.Source$;
/*    */ 
/*    */ public abstract class ExternalSources$class {
/*    */   public static void $init$(MarkupHandler $this) {}
/*    */   
/*    */   public static Source externalSource(MarkupHandler $this, String systemId) {
/*    */     String str2;
/* 27 */     if (systemId.startsWith("http:"))
/* 28 */       return (Source)Source$.MODULE$.fromURL(new URL(systemId), Codec$.MODULE$.fallbackSystemCodec()); 
/* 30 */     String str1 = ((MarkupParser)$this).input().descr();
/* 31 */     if (str1.startsWith("file:")) {
/* 31 */       Predef$ predef$ = Predef$.MODULE$;
/* 31 */       str2 = (String)(new StringOps(str1)).drop(5);
/*    */     } else {
/* 32 */       Predef$ predef$ = Predef$.MODULE$;
/* 32 */       str2 = (String)(new StringOps(str1)).take(str1.lastIndexOf(File.separator) + 1);
/*    */     } 
/*    */     return (Source)Source$.MODULE$.fromFile((new StringBuilder()).append(str2).append(systemId).toString(), Codec$.MODULE$.fallbackSystemCodec());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\parsing\ExternalSources$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */