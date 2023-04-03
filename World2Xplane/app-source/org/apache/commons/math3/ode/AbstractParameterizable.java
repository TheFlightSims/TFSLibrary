/*    */ package org.apache.commons.math3.ode;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*    */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*    */ 
/*    */ public abstract class AbstractParameterizable implements Parameterizable {
/*    */   private final Collection<String> parametersNames;
/*    */   
/*    */   protected AbstractParameterizable(String... names) {
/* 40 */     this.parametersNames = new ArrayList<String>();
/* 41 */     for (String name : names)
/* 42 */       this.parametersNames.add(name); 
/*    */   }
/*    */   
/*    */   protected AbstractParameterizable(Collection<String> names) {
/* 50 */     this.parametersNames = new ArrayList<String>();
/* 51 */     this.parametersNames.addAll(names);
/*    */   }
/*    */   
/*    */   public Collection<String> getParametersNames() {
/* 56 */     return this.parametersNames;
/*    */   }
/*    */   
/*    */   public boolean isSupported(String name) {
/* 61 */     for (String supportedName : this.parametersNames) {
/* 62 */       if (supportedName.equals(name))
/* 63 */         return true; 
/*    */     } 
/* 66 */     return false;
/*    */   }
/*    */   
/*    */   public void complainIfNotSupported(String name) throws MathIllegalArgumentException {
/* 76 */     if (!isSupported(name))
/* 77 */       throw new MathIllegalArgumentException(LocalizedFormats.UNKNOWN_PARAMETER, new Object[] { name }); 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\AbstractParameterizable.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */