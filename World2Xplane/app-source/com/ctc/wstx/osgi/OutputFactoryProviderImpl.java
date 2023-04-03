/*    */ package com.ctc.wstx.osgi;
/*    */ 
/*    */ import com.ctc.wstx.api.ReaderConfig;
/*    */ import com.ctc.wstx.stax.WstxOutputFactory;
/*    */ import java.util.Properties;
/*    */ import org.codehaus.stax2.XMLOutputFactory2;
/*    */ import org.codehaus.stax2.osgi.Stax2OutputFactoryProvider;
/*    */ 
/*    */ public class OutputFactoryProviderImpl implements Stax2OutputFactoryProvider {
/*    */   public XMLOutputFactory2 createOutputFactory() {
/* 15 */     return (XMLOutputFactory2)new WstxOutputFactory();
/*    */   }
/*    */   
/*    */   protected Properties getProperties() {
/* 20 */     Properties props = new Properties();
/* 21 */     props.setProperty("org.codehaus.stax2.implName", ReaderConfig.getImplName());
/* 22 */     props.setProperty("org.codehaus.stax2.implVersion", ReaderConfig.getImplVersion());
/* 23 */     return props;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\osgi\OutputFactoryProviderImpl.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */