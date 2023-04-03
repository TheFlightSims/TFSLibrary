/*    */ package org.codehaus.stax2.ri.evt;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.Writer;
/*    */ import javax.xml.stream.Location;
/*    */ import javax.xml.stream.XMLStreamException;
/*    */ import javax.xml.stream.events.ProcessingInstruction;
/*    */ import org.codehaus.stax2.XMLStreamWriter2;
/*    */ 
/*    */ public class ProcInstrEventImpl extends BaseEventImpl implements ProcessingInstruction {
/*    */   final String mTarget;
/*    */   
/*    */   final String mData;
/*    */   
/*    */   public ProcInstrEventImpl(Location loc, String target, String data) {
/* 20 */     super(loc);
/* 21 */     this.mTarget = target;
/* 22 */     this.mData = data;
/*    */   }
/*    */   
/*    */   public String getData() {
/* 26 */     return this.mData;
/*    */   }
/*    */   
/*    */   public String getTarget() {
/* 30 */     return this.mTarget;
/*    */   }
/*    */   
/*    */   public int getEventType() {
/* 40 */     return 3;
/*    */   }
/*    */   
/*    */   public boolean isProcessingInstruction() {
/* 44 */     return true;
/*    */   }
/*    */   
/*    */   public void writeAsEncodedUnicode(Writer w) throws XMLStreamException {
/*    */     try {
/* 51 */       w.write("<?");
/* 52 */       w.write(this.mTarget);
/* 53 */       if (this.mData != null && this.mData.length() > 0)
/* 54 */         w.write(this.mData); 
/* 56 */       w.write("?>");
/* 57 */     } catch (IOException ie) {
/* 58 */       throwFromIOE(ie);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void writeUsing(XMLStreamWriter2 w) throws XMLStreamException {
/* 64 */     if (this.mData != null && this.mData.length() > 0) {
/* 65 */       w.writeProcessingInstruction(this.mTarget, this.mData);
/*    */     } else {
/* 67 */       w.writeProcessingInstruction(this.mTarget);
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean equals(Object o) {
/* 79 */     if (o == this)
/* 79 */       return true; 
/* 80 */     if (o == null)
/* 80 */       return false; 
/* 82 */     if (!(o instanceof ProcessingInstruction))
/* 82 */       return false; 
/* 84 */     ProcessingInstruction other = (ProcessingInstruction)o;
/* 85 */     return (this.mTarget.equals(other.getTarget()) && stringsWithNullsEqual(this.mData, other.getData()));
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 91 */     int hash = this.mTarget.hashCode();
/* 92 */     if (this.mData != null)
/* 93 */       hash ^= this.mData.hashCode(); 
/* 95 */     return hash;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\evt\ProcInstrEventImpl.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */