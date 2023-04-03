package org.geotools.console;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Option {
  String name() default "";
  
  boolean mandatory() default false;
  
  String description() default "";
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\console\Option.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */