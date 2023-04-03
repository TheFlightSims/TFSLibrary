package org.opengis.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.PACKAGE})
@Retention(RetentionPolicy.RUNTIME)
public @interface XmlSchema {
  String URL();
  
  Specification specification();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\annotation\XmlSchema.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */