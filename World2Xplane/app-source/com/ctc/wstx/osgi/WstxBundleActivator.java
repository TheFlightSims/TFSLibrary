/*    */ package com.ctc.wstx.osgi;
/*    */ 
/*    */ import org.codehaus.stax2.osgi.Stax2InputFactoryProvider;
/*    */ import org.codehaus.stax2.osgi.Stax2OutputFactoryProvider;
/*    */ import org.codehaus.stax2.osgi.Stax2ValidationSchemaFactoryProvider;
/*    */ import org.osgi.framework.BundleActivator;
/*    */ import org.osgi.framework.BundleContext;
/*    */ 
/*    */ public class WstxBundleActivator implements BundleActivator {
/*    */   public void start(BundleContext ctxt) {
/* 28 */     InputFactoryProviderImpl inputP = new InputFactoryProviderImpl();
/* 29 */     ctxt.registerService(Stax2InputFactoryProvider.class.getName(), inputP, inputP.getProperties());
/* 30 */     OutputFactoryProviderImpl outputP = new OutputFactoryProviderImpl();
/* 31 */     ctxt.registerService(Stax2OutputFactoryProvider.class.getName(), outputP, outputP.getProperties());
/* 32 */     ValidationSchemaFactoryProviderImpl[] impls = ValidationSchemaFactoryProviderImpl.createAll();
/* 33 */     for (int i = 0, len = impls.length; i < len; i++) {
/* 34 */       ValidationSchemaFactoryProviderImpl impl = impls[i];
/* 35 */       ctxt.registerService(Stax2ValidationSchemaFactoryProvider.class.getName(), impl, impl.getProperties());
/*    */     } 
/*    */   }
/*    */   
/*    */   public void stop(BundleContext ctxt) {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\osgi\WstxBundleActivator.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */