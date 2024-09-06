package ir.freeland.spring.initialspring.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CalculationSetterInjection {
  private Calculator calculator;
  private Display display;
  private Print print;

  public void complexCalculation() {
    int result = calculator.plus(2, 3);

    display.output(String.format("2 + 3 = %d", result));
    
    print.printing();
  }
  

  @Autowired
  public void setCalculator(Calculator calculator) {
    this.calculator = calculator;
  }

  @Autowired
  public void setDisplay(Display display) {
    this.display = display;
  }
  
  @Autowired
  public void setPrint(Print print) {
	    this.print = print;
	  }
}
