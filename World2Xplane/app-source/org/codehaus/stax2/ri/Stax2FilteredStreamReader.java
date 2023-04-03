/*    */ package org.codehaus.stax2.ri;
/*    */ 
/*    */ import javax.xml.stream.StreamFilter;
/*    */ import javax.xml.stream.XMLStreamConstants;
/*    */ import javax.xml.stream.XMLStreamException;
/*    */ import javax.xml.stream.XMLStreamReader;
/*    */ import org.codehaus.stax2.util.StreamReader2Delegate;
/*    */ 
/*    */ public class Stax2FilteredStreamReader extends StreamReader2Delegate implements XMLStreamConstants {
/*    */   final StreamFilter mFilter;
/*    */   
/*    */   public Stax2FilteredStreamReader(XMLStreamReader r, StreamFilter f) {
/* 21 */     super(Stax2ReaderAdapter.wrapIfNecessary(r));
/* 22 */     this.mFilter = f;
/*    */   }
/*    */   
/*    */   public int next() throws XMLStreamException {
/*    */     int type;
/*    */     do {
/* 36 */       type = this.mDelegate2.next();
/* 37 */       if (this.mFilter.accept((XMLStreamReader)this))
/*    */         break; 
/* 40 */     } while (type != 8);
/* 42 */     return type;
/*    */   }
/*    */   
/*    */   public int nextTag() throws XMLStreamException {
/*    */     int type;
/*    */     do {
/* 51 */       type = this.mDelegate2.nextTag();
/* 52 */     } while (!this.mFilter.accept((XMLStreamReader)this));
/* 56 */     return type;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\Stax2FilteredStreamReader.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */