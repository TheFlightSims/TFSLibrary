/*     */ package com.ctc.wstx.ent;
/*     */ 
/*     */ import com.ctc.wstx.api.ReaderConfig;
/*     */ import com.ctc.wstx.evt.WEntityDeclaration;
/*     */ import com.ctc.wstx.io.WstxInputSource;
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.net.URL;
/*     */ import javax.xml.stream.Location;
/*     */ import javax.xml.stream.XMLResolver;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ 
/*     */ public abstract class EntityDecl extends WEntityDeclaration {
/*     */   final String mName;
/*     */   
/*     */   final URL mContext;
/*     */   
/*     */   protected boolean mDeclaredExternally = false;
/*     */   
/*     */   public EntityDecl(Location loc, String name, URL ctxt) {
/*  56 */     super(loc);
/*  57 */     this.mName = name;
/*  58 */     this.mContext = ctxt;
/*     */   }
/*     */   
/*     */   public void markAsExternallyDeclared() {
/*  62 */     this.mDeclaredExternally = true;
/*     */   }
/*     */   
/*     */   public final String getBaseURI() {
/*  66 */     return this.mContext.toExternalForm();
/*     */   }
/*     */   
/*     */   public final String getName() {
/*  70 */     return this.mName;
/*     */   }
/*     */   
/*     */   public final Location getLocation() {
/*  74 */     return this.mLocation;
/*     */   }
/*     */   
/*     */   public abstract String getNotationName();
/*     */   
/*     */   public abstract String getPublicId();
/*     */   
/*     */   public abstract String getReplacementText();
/*     */   
/*     */   public abstract int getReplacementText(Writer paramWriter) throws IOException;
/*     */   
/*     */   public abstract String getSystemId();
/*     */   
/*     */   public boolean wasDeclaredExternally() {
/*  93 */     return this.mDeclaredExternally;
/*     */   }
/*     */   
/*     */   public abstract void writeEnc(Writer paramWriter) throws IOException;
/*     */   
/*     */   public abstract char[] getReplacementChars();
/*     */   
/*     */   public final int getReplacementTextLength() {
/* 115 */     String str = getReplacementText();
/* 116 */     return (str == null) ? 0 : str.length();
/*     */   }
/*     */   
/*     */   public abstract boolean isExternal();
/*     */   
/*     */   public abstract boolean isParsed();
/*     */   
/*     */   public abstract WstxInputSource expand(WstxInputSource paramWstxInputSource, XMLResolver paramXMLResolver, ReaderConfig paramReaderConfig, int paramInt) throws IOException, XMLStreamException;
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\ent\EntityDecl.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */