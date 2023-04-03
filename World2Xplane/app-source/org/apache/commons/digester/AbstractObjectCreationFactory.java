/*    */ package org.apache.commons.digester;
/*    */ 
/*    */ import org.xml.sax.Attributes;
/*    */ 
/*    */ public abstract class AbstractObjectCreationFactory implements ObjectCreationFactory {
/* 38 */   protected Digester digester = null;
/*    */   
/*    */   public abstract Object createObject(Attributes paramAttributes) throws Exception;
/*    */   
/*    */   public Digester getDigester() {
/* 61 */     return this.digester;
/*    */   }
/*    */   
/*    */   public void setDigester(Digester digester) {
/* 74 */     this.digester = digester;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\AbstractObjectCreationFactory.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */