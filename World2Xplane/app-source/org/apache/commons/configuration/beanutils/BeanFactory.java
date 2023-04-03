package org.apache.commons.configuration.beanutils;

public interface BeanFactory {
  Object createBean(Class paramClass, BeanDeclaration paramBeanDeclaration, Object paramObject) throws Exception;
  
  Class getDefaultBeanClass();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\beanutils\BeanFactory.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */