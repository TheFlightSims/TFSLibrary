/*    */ package com.ctc.wstx.osgi;
/*    */ 
/*    */ import com.ctc.wstx.api.ValidatorConfig;
/*    */ import com.ctc.wstx.dtd.DTDSchemaFactory;
/*    */ import com.ctc.wstx.msv.RelaxNGSchemaFactory;
/*    */ import com.ctc.wstx.msv.W3CSchemaFactory;
/*    */ import java.util.Properties;
/*    */ import org.codehaus.stax2.osgi.Stax2ValidationSchemaFactoryProvider;
/*    */ import org.codehaus.stax2.validation.XMLValidationSchemaFactory;
/*    */ 
/*    */ public abstract class ValidationSchemaFactoryProviderImpl implements Stax2ValidationSchemaFactoryProvider {
/*    */   final String mSchemaType;
/*    */   
/*    */   protected ValidationSchemaFactoryProviderImpl(String st) {
/* 21 */     this.mSchemaType = st;
/*    */   }
/*    */   
/*    */   public static ValidationSchemaFactoryProviderImpl[] createAll() {
/* 26 */     return new ValidationSchemaFactoryProviderImpl[] { new DTD(), new RelaxNG(), new W3CSchema() };
/*    */   }
/*    */   
/*    */   public abstract XMLValidationSchemaFactory createValidationSchemaFactory();
/*    */   
/*    */   public String getSchemaType() {
/* 33 */     return this.mSchemaType;
/*    */   }
/*    */   
/*    */   public Properties getProperties() {
/* 37 */     Properties props = new Properties();
/* 38 */     props.setProperty("org.codehaus.stax2.implName", ValidatorConfig.getImplName());
/* 39 */     props.setProperty("org.codehaus.stax2.implVersion", ValidatorConfig.getImplVersion());
/* 40 */     props.setProperty("org.codehaus.stax2.validation.schemaType", this.mSchemaType);
/* 41 */     return props;
/*    */   }
/*    */   
/*    */   static final class DTD extends ValidationSchemaFactoryProviderImpl {
/*    */     DTD() {
/* 53 */       super("http://www.w3.org/XML/1998/namespace");
/*    */     }
/*    */     
/*    */     public XMLValidationSchemaFactory createValidationSchemaFactory() {
/* 56 */       return (XMLValidationSchemaFactory)new DTDSchemaFactory();
/*    */     }
/*    */   }
/*    */   
/*    */   static final class RelaxNG extends ValidationSchemaFactoryProviderImpl {
/*    */     RelaxNG() {
/* 63 */       super("http://relaxng.org/ns/structure/0.9");
/*    */     }
/*    */     
/*    */     public XMLValidationSchemaFactory createValidationSchemaFactory() {
/* 66 */       return (XMLValidationSchemaFactory)new RelaxNGSchemaFactory();
/*    */     }
/*    */   }
/*    */   
/*    */   static final class W3CSchema extends ValidationSchemaFactoryProviderImpl {
/*    */     W3CSchema() {
/* 73 */       super("http://www.w3.org/2001/XMLSchema");
/*    */     }
/*    */     
/*    */     public XMLValidationSchemaFactory createValidationSchemaFactory() {
/* 76 */       return (XMLValidationSchemaFactory)new W3CSchemaFactory();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\osgi\ValidationSchemaFactoryProviderImpl.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */