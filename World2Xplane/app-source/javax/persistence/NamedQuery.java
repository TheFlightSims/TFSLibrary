package javax.persistence;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NamedQuery {
  String name();
  
  String query();
  
  QueryHint[] hints() default {};
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\persistence\NamedQuery.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */