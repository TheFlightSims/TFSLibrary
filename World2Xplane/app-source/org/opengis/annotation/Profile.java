package org.opengis.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
public @interface Profile {
  ComplianceLevel level();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\annotation\Profile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */