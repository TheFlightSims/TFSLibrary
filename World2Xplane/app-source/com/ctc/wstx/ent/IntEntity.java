/*     */ package com.ctc.wstx.ent;
/*     */ 
/*     */ import com.ctc.wstx.api.ReaderConfig;
/*     */ import com.ctc.wstx.io.InputSourceFactory;
/*     */ import com.ctc.wstx.io.TextEscaper;
/*     */ import com.ctc.wstx.io.WstxInputLocation;
/*     */ import com.ctc.wstx.io.WstxInputSource;
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.net.URL;
/*     */ import javax.xml.stream.Location;
/*     */ import javax.xml.stream.XMLResolver;
/*     */ 
/*     */ public class IntEntity extends EntityDecl {
/*     */   protected final Location mContentLocation;
/*     */   
/*     */   final char[] mRepl;
/*     */   
/*  31 */   String mReplText = null;
/*     */   
/*     */   public IntEntity(Location loc, String name, URL ctxt, char[] repl, Location defLoc) {
/*  36 */     super(loc, name, ctxt);
/*  37 */     this.mRepl = repl;
/*  38 */     this.mContentLocation = defLoc;
/*     */   }
/*     */   
/*     */   public static IntEntity create(String id, String repl) {
/*  43 */     return create(id, repl.toCharArray());
/*     */   }
/*     */   
/*     */   public static IntEntity create(String id, char[] val) {
/*  48 */     WstxInputLocation loc = WstxInputLocation.getEmptyLocation();
/*  49 */     return new IntEntity((Location)loc, id, null, val, (Location)loc);
/*     */   }
/*     */   
/*     */   public String getNotationName() {
/*  53 */     return null;
/*     */   }
/*     */   
/*     */   public String getPublicId() {
/*  57 */     return null;
/*     */   }
/*     */   
/*     */   public String getReplacementText() {
/*  62 */     String repl = this.mReplText;
/*  63 */     if (repl == null) {
/*  64 */       repl = (this.mRepl.length == 0) ? "" : new String(this.mRepl);
/*  65 */       this.mReplText = repl;
/*     */     } 
/*  67 */     return this.mReplText;
/*     */   }
/*     */   
/*     */   public int getReplacementText(Writer w) throws IOException {
/*  73 */     w.write(this.mRepl);
/*  74 */     return this.mRepl.length;
/*     */   }
/*     */   
/*     */   public String getSystemId() {
/*  78 */     return null;
/*     */   }
/*     */   
/*     */   public void writeEnc(Writer w) throws IOException {
/*  89 */     w.write("<!ENTITY ");
/*  90 */     w.write(this.mName);
/*  91 */     w.write(" \"");
/*  92 */     TextEscaper.outputDTDText(w, this.mRepl, 0, this.mRepl.length);
/*  93 */     w.write("\">");
/*     */   }
/*     */   
/*     */   public char[] getReplacementChars() {
/* 112 */     return this.mRepl;
/*     */   }
/*     */   
/*     */   public boolean isExternal() {
/* 117 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isParsed() {
/* 119 */     return true;
/*     */   }
/*     */   
/*     */   public WstxInputSource expand(WstxInputSource parent, XMLResolver res, ReaderConfig cfg, int xmlVersion) {
/* 129 */     return InputSourceFactory.constructCharArraySource(parent, this.mName, this.mRepl, 0, this.mRepl.length, this.mContentLocation, null);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\ent\IntEntity.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */