package org.apache.commons.math3.ode;

import java.util.Collection;
import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
import org.apache.commons.math3.ode.events.EventHandler;
import org.apache.commons.math3.ode.sampling.StepHandler;

public interface ODEIntegrator {
  String getName();
  
  void addStepHandler(StepHandler paramStepHandler);
  
  Collection<StepHandler> getStepHandlers();
  
  void clearStepHandlers();
  
  void addEventHandler(EventHandler paramEventHandler, double paramDouble1, double paramDouble2, int paramInt);
  
  void addEventHandler(EventHandler paramEventHandler, double paramDouble1, double paramDouble2, int paramInt, UnivariateSolver paramUnivariateSolver);
  
  Collection<EventHandler> getEventHandlers();
  
  void clearEventHandlers();
  
  double getCurrentStepStart();
  
  double getCurrentSignedStepsize();
  
  void setMaxEvaluations(int paramInt);
  
  int getMaxEvaluations();
  
  int getEvaluations();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\ODEIntegrator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */