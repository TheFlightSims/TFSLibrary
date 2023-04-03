/*     */ package scala.text;
/*     */ 
/*     */ public final class Document$ {
/*     */   public static final Document$ MODULE$;
/*     */   
/*     */   private Document$() {
/*  99 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public DocNil$ empty() {
/* 101 */     return DocNil$.MODULE$;
/*     */   }
/*     */   
/*     */   public DocBreak$ break() {
/* 104 */     return DocBreak$.MODULE$;
/*     */   }
/*     */   
/*     */   public Document text(String s) {
/* 107 */     return new DocText(s);
/*     */   }
/*     */   
/*     */   public Document group(Document d) {
/* 113 */     return new DocGroup(d);
/*     */   }
/*     */   
/*     */   public Document nest(int i, Document d) {
/* 116 */     return new DocNest(i, d);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\text\Document$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */