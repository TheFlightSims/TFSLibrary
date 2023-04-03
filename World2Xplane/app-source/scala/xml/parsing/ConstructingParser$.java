/*    */ package scala.xml.parsing;
/*    */ 
/*    */ import java.io.File;
/*    */ import scala.io.Source;
/*    */ 
/*    */ public final class ConstructingParser$ {
/*    */   public static final ConstructingParser$ MODULE$;
/*    */   
/*    */   private ConstructingParser$() {
/* 17 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public ConstructingParser fromFile(File inp, boolean preserveWS) {
/* 19 */     return (ConstructingParser)(new ConstructingParser((Source)scala.io.Source$.MODULE$.fromFile(inp, scala.io.Codec$.MODULE$.fallbackSystemCodec()), preserveWS)).initialize();
/*    */   }
/*    */   
/*    */   public ConstructingParser fromSource(Source inp, boolean preserveWS) {
/* 22 */     return (ConstructingParser)(new ConstructingParser(inp, preserveWS)).initialize();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\parsing\ConstructingParser$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */