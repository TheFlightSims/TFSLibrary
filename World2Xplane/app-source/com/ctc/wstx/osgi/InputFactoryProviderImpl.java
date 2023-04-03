/*    */ package com.ctc.wstx.osgi;
/*    */ 
/*    */ import com.ctc.wstx.api.ReaderConfig;
/*    */ import com.ctc.wstx.stax.WstxInputFactory;
/*    */ import java.util.Properties;
/*    */ import org.codehaus.stax2.XMLInputFactory2;
/*    */ import org.codehaus.stax2.osgi.Stax2InputFactoryProvider;
/*    */ 
/*    */ public class InputFactoryProviderImpl implements Stax2InputFactoryProvider {
/*    */   public XMLInputFactory2 createInputFactory() {
/* 15 */     return (XMLInputFactory2)new WstxInputFactory();
/*    */   }
/*    */   
/*    */   protected Properties getProperties() {
/* 20 */     Properties props = new Properties();
/* 21 */     props.setProperty("org.codehaus.stax2.implName", ReaderConfig.getImplName());
/* 22 */     props.setProperty("org.codehaus.stax2.implVersion", ReaderConfig.getImplVersion());
/* 23 */     return props;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\osgi\InputFactoryProviderImpl.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */