/*    */ package ch.qos.logback.core.net.ssl;
/*    */ 
/*    */ import ch.qos.logback.core.joran.spi.DefaultNestedComponentRegistry;
/*    */ 
/*    */ public class SSLNestedComponentRegistryRules {
/*    */   public static void addDefaultNestedComponentRegistryRules(DefaultNestedComponentRegistry registry) {
/* 28 */     registry.add(SSLComponent.class, "ssl", SSLConfiguration.class);
/* 29 */     registry.add(SSLConfiguration.class, "parameters", SSLParametersConfiguration.class);
/* 31 */     registry.add(SSLConfiguration.class, "keyStore", KeyStoreFactoryBean.class);
/* 33 */     registry.add(SSLConfiguration.class, "trustStore", KeyStoreFactoryBean.class);
/* 35 */     registry.add(SSLConfiguration.class, "keyManagerFactory", KeyManagerFactoryFactoryBean.class);
/* 37 */     registry.add(SSLConfiguration.class, "trustManagerFactory", TrustManagerFactoryFactoryBean.class);
/* 39 */     registry.add(SSLConfiguration.class, "secureRandom", SecureRandomFactoryBean.class);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\net\ssl\SSLNestedComponentRegistryRules.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */