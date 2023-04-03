package javax.persistence;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface PersistenceProperty {
  String name();
  
  String value();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\persistence\PersistenceProperty.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */