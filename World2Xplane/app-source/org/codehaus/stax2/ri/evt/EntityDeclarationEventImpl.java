/*     */ package org.codehaus.stax2.ri.evt;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.StringWriter;
/*     */ import java.io.Writer;
/*     */ import javax.xml.stream.Location;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.events.EntityDeclaration;
/*     */ import org.codehaus.stax2.XMLStreamWriter2;
/*     */ 
/*     */ public class EntityDeclarationEventImpl extends BaseEventImpl implements EntityDeclaration {
/*     */   protected final String mName;
/*     */   
/*     */   public EntityDeclarationEventImpl(Location loc, String name) {
/*  24 */     super(loc);
/*  25 */     this.mName = name;
/*     */   }
/*     */   
/*     */   public String getBaseURI() {
/*  36 */     return "";
/*     */   }
/*     */   
/*     */   public String getName() {
/*  41 */     return this.mName;
/*     */   }
/*     */   
/*     */   public String getNotationName() {
/*  46 */     return null;
/*     */   }
/*     */   
/*     */   public String getPublicId() {
/*  51 */     return null;
/*     */   }
/*     */   
/*     */   public String getReplacementText() {
/*  56 */     return null;
/*     */   }
/*     */   
/*     */   public String getSystemId() {
/*  61 */     return null;
/*     */   }
/*     */   
/*     */   public int getEventType() {
/*  71 */     return 15;
/*     */   }
/*     */   
/*     */   public void writeAsEncodedUnicode(Writer w) throws XMLStreamException {
/*     */     try {
/*  78 */       w.write("<!ENTITY ");
/*  79 */       w.write(getName());
/*  80 */       w.write(" \"");
/*  82 */       String content = getReplacementText();
/*  83 */       if (content != null)
/*  84 */         w.write(content); 
/*  86 */       w.write("\">");
/*  87 */     } catch (IOException ie) {
/*  88 */       throwFromIOE(ie);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeUsing(XMLStreamWriter2 w) throws XMLStreamException {
/*  95 */     StringWriter strw = new StringWriter();
/*  96 */     writeAsEncodedUnicode(strw);
/*  97 */     w.writeRaw(strw.toString());
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 108 */     if (o == this)
/* 108 */       return true; 
/* 109 */     if (o == null)
/* 109 */       return false; 
/* 111 */     if (!(o instanceof EntityDeclaration))
/* 111 */       return false; 
/* 113 */     EntityDeclaration other = (EntityDeclaration)o;
/* 114 */     return (stringsWithNullsEqual(getName(), other.getName()) && stringsWithNullsEqual(getBaseURI(), other.getBaseURI()) && stringsWithNullsEqual(getNotationName(), other.getNotationName()) && stringsWithNullsEqual(getPublicId(), other.getPublicId()) && stringsWithNullsEqual(getReplacementText(), other.getReplacementText()) && stringsWithNullsEqual(getSystemId(), other.getSystemId()));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 126 */     return this.mName.hashCode();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\evt\EntityDeclarationEventImpl.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */