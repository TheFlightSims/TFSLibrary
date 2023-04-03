/*    */ package ch.qos.logback.core.joran.event;
/*    */ 
/*    */ import org.xml.sax.Locator;
/*    */ 
/*    */ public class EndEvent extends SaxEvent {
/*    */   EndEvent(String namespaceURI, String localName, String qName, Locator locator) {
/* 22 */     super(namespaceURI, localName, qName, locator);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 27 */     return "  EndEvent(" + getQName() + ")  [" + this.locator.getLineNumber() + "," + this.locator.getColumnNumber() + "]";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\joran\event\EndEvent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */