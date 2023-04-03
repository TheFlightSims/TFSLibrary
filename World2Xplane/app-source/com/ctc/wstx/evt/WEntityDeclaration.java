/*     */ package com.ctc.wstx.evt;
/*     */ 
/*     */ import com.ctc.wstx.exc.WstxIOException;
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import javax.xml.stream.Location;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.events.EntityDeclaration;
/*     */ import org.codehaus.stax2.XMLStreamWriter2;
/*     */ import org.codehaus.stax2.ri.evt.BaseEventImpl;
/*     */ 
/*     */ public abstract class WEntityDeclaration extends BaseEventImpl implements EntityDeclaration {
/*     */   public WEntityDeclaration(Location loc) {
/*  26 */     super(loc);
/*     */   }
/*     */   
/*     */   public abstract String getBaseURI();
/*     */   
/*     */   public abstract String getName();
/*     */   
/*     */   public abstract String getNotationName();
/*     */   
/*     */   public abstract String getPublicId();
/*     */   
/*     */   public abstract String getReplacementText();
/*     */   
/*     */   public abstract String getSystemId();
/*     */   
/*     */   public int getEventType() {
/*  48 */     return 15;
/*     */   }
/*     */   
/*     */   public abstract void writeEnc(Writer paramWriter) throws IOException;
/*     */   
/*     */   public void writeAsEncodedUnicode(Writer w) throws XMLStreamException {
/*     */     try {
/*  57 */       writeEnc(w);
/*  58 */     } catch (IOException ie) {
/*  59 */       throw new WstxIOException(ie);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeUsing(XMLStreamWriter2 w) throws XMLStreamException {
/*  75 */     throw new XMLStreamException("Can not write entity declarations using an XMLStreamWriter");
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/*  87 */     if (o == this)
/*  87 */       return true; 
/*  88 */     if (o == null)
/*  88 */       return false; 
/*  90 */     if (!(o instanceof EntityDeclaration))
/*  90 */       return false; 
/*  92 */     EntityDeclaration other = (EntityDeclaration)o;
/*  93 */     return (stringsWithNullsEqual(getName(), other.getName()) && stringsWithNullsEqual(getBaseURI(), other.getBaseURI()) && stringsWithNullsEqual(getNotationName(), other.getNotationName()) && stringsWithNullsEqual(getPublicId(), other.getPublicId()) && stringsWithNullsEqual(getReplacementText(), other.getReplacementText()) && stringsWithNullsEqual(getSystemId(), other.getSystemId()));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 107 */     return getName().hashCode();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\evt\WEntityDeclaration.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */