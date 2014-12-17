import ec.util.*;
import ec.*;
import ec.gp.*;
import ec.gp.koza.*;
import ec.simple.*;
import java.io.*;
import java.util.*;

public class Regression extends GPProblem implements SimpleProblemForm
{
  
  private static final long serialVersionUID = 1;

  public static final String P_SIZE = "size";
  public static final String P_FILE = "file";
  public static final String P_USE_FUNCTION = "use-function";

  public double currentValue;
  public int trainingSetSize;

  // if we have a file, should we use the function to compute the output values?  Or are they also contained?
  public boolean useFunction;
      
  public double inputs[];
  public double outputs[];

  public double func(double x)
  {
    return x*x*x*x + x*x*x + x*x + x;
  }

  public void setup(final EvolutionState state, final Parameter base)
  {
    super.setup(state, base);

    // verify our input is the right class (or subclasses from it)
    if (!(input instanceof RegressionData))
        state.output.fatal("GPData class must subclass from " + RegressionData.class,
            base.push(P_DATA), null);

    trainingSetSize = state.parameters.getInt(base.push(P_SIZE),null,1);

    if (trainingSetSize<1)
      state.output.fatal("Training Set Size must be an integer greater than 0", base.push(P_SIZE)); 

    // should we load our x parameters from a file, or generate them randomly?
    //file = state.parameters.getFile(base.push(P_FILE), null);
    InputStream inputfile = state.parameters.getResource(base.push(P_FILE), null);

    // *IF* we load from a file, should we generate the output through the function, or load the output as well?
    useFunction = state.parameters.getBoolean(base.push(P_USE_FUNCTION), null, true);

    // Compute our inputs so they can be copied with clone later
    inputs = new double[trainingSetSize];
    outputs = new double[trainingSetSize];

    //if (file != null)  // use the file
    if (inputfile != null) {
      try {
        Scanner scan = new Scanner(inputfile);
        for (int x = 0; x < trainingSetSize; x++) {
            if (scan.hasNextDouble())
                inputs[x] = scan.nextDouble();
            else
              state.output.fatal("Not enough data points in file: expected " + (trainingSetSize * (useFunction ? 1 : 2)));

            if (!useFunction) {
              if (scan.hasNextDouble())
                outputs[x] = scan.nextDouble();
              else
                state.output.fatal("Not enough data points in file: expected " + (trainingSetSize * (useFunction ? 1 : 2)));
            }
        }
      }
      catch (NumberFormatException e) {
        state.output.fatal("Some tokens in the file were not numbers.");
      }
    }
    else for(int x = 0; x < trainingSetSize; x++) {

       // On p. 242 of Koza-I, he claims that the points are chosen from the
       // fully-closed interval [-1, 1].  This is likely not true as Koza's lisp
       // code usually selected stuff from half-open intervals.  But just to be
       // absurdly exact here, we're allowing 1 as a valid number.
       inputs[x] = state.random[0].nextDouble(true, true) * 2.0 - 1.0;     // fully closed interval.

    }
                    
    for(int x = 0; x < trainingSetSize; x++) {
      if (useFunction)
        outputs[x] = func(inputs[x]);
      state.output.message("{" + inputs[x] + "," + outputs[x] + "},");
    }
  }

  public void evaluate(final EvolutionState state, 
      final Individual ind, 
      final int subpopulation,
      final int threadnum)
  {
    if (!ind.evaluated) {
      RegressionData input = (RegressionData)(this.input);

      int hits = 0;
      double sum = 0.0;
      double result;

      for (int y = 0; y < trainingSetSize; y++) {

        currentValue = inputs[y];
        ((GPIndividual)ind).trees[0].child.eval(
            state,threadnum,input,stack,((GPIndividual)ind),this);

        // It's possible to get NaN because cos(infinity) and
        // sin(infinity) are undefined (hence cos(exp(3000)) zings ya!)
        // So since NaN is NOT =,<,>,etc. any other number, including
        // NaN, we're CAREFULLY wording our cutoff to include NaN.
        // Interesting that this has never been reported before to
        // my knowledge.
        final double HIT_LEVEL = 0.01;
        final double PROBABLY_ZERO = 1.11E-15;
        final double BIG_NUMBER = 1.0e15;  // the same as lilgp uses

        result = Math.abs(outputs[y] - input.x);

        if (!(result < BIG_NUMBER ))
          result = BIG_NUMBER;

        // very slight math errors can creep in when evaluating
        // two equivalent by differently-ordered functions, like
        // x * (x*x*x + x*x)  vs. x*x*x*x + x*x
        else if (result < PROBABLY_ZERO)
            result = 0.0;
            
        if (result <= HIT_LEVEL)
          hits++;

        sum += result;              
     }
          
      // the fitness better be KozaFitness!
      KozaFitness f = ((KozaFitness) ind.fitness);
      f.setStandardizedFitness(state, sum);
      f.hits = hits;
      ind.evaluated = true;
    }
  }

}