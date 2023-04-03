/*    */ package org.codehaus.stax2.ri.evt;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.Writer;
/*    */ import javax.xml.stream.Location;
/*    */ import javax.xml.stream.XMLStreamException;
/*    */ import javax.xml.stream.events.EntityDeclaration;
/*    */ import javax.xml.stream.events.EntityReference;
/*    */ import org.codehaus.stax2.XMLStreamWriter2;
/*    */ 
/*    */ public class EntityReferenceEventImpl extends BaseEventImpl implements EntityReference {
/*    */   protected final EntityDeclaration mDecl;
/*    */   
/*    */   public EntityReferenceEventImpl(Location loc, EntityDeclaration decl) {
/* 20 */     super(loc);
/* 21 */     this.mDecl = decl;
/*    */   }
/*    */   
/*    */   public EntityReferenceEventImpl(Location loc, String name) {
/* 26 */     super(loc);
/* 28 */     this.mDecl = new EntityDeclarationEventImpl(loc, name);
/*    */   }
/*    */   
/*    */   public EntityDeclaration getDeclaration() {
/* 33 */     return this.mDecl;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 38 */     return this.mDecl.getName();
/*    */   }
/*    */   
/*    */   public int getEventType() {
/* 48 */     return 9;
/*    */   }
/*    */   
/*    */   public boolean isEntityReference() {
/* 52 */     return true;
/*    */   }
/*    */   
/*    */   public void writeAsEncodedUnicode(Writer w) throws XMLStreamException {
/*    */     try {
/* 59 */       w.write(38);
/* 60 */       w.write(getName());
/* 61 */       w.write(59);
/* 62 */     } catch (IOException ie) {
/* 63 */       throwFromIOE(ie);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void writeUsing(XMLStreamWriter2 w) throws XMLStreamException {
/* 69 */     w.writeEntityRef(getName());
/*    */   }
/*    */   
/*    */   public boolean equals(Object o) {
/* 80 */     if (o == this)
/* 80 */       return true; 
/* 81 */     if (o == null)
/* 81 */       return false; 
/* 83 */     if (!(o instanceof EntityReference))
/* 83 */       return false; 
/* 85 */     EntityReference other = (EntityReference)o;
/* 86 */     return getName().equals(other.getName());
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 91 */     return getName().hashCode();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\evt\EntityReferenceEventImpl.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */