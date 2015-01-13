import java.util.Comparator;
import net.sf.jclec.IConfigure;
import net.sf.jclec.IFitness;
import net.sf.jclec.IIndividual;
import net.sf.jclec.base.AbstractEvaluator;
import net.sf.jclec.exprtree.ExprTree;
import net.sf.jclec.exprtree.ExprTreeIndividual;
import net.sf.jclec.exprtree.fun.ExprTreeFunction;
import net.sf.jclec.fitness.SimpleValueFitness;
import net.sf.jclec.fitness.ValueFitnessComparator;
import org.apache.commons.configuration.Configuration;

public class Regression extends AbstractEvaluator
  implements IConfigure
{
  private static final Comparator<IFitness> COMPARATOR = new ValueFitnessComparator(true);
  private double[] xvalues;
  private double[] yvalues;
  
  public void configure(Configuration settings)
  {
    String[] x = settings.getString("xvalues").split(",");
    String[] y = settings.getString("yvalues").split(",");
    
    int numberElements = x.length;
    
    this.xvalues = new double[numberElements];
    this.yvalues = new double[numberElements];
    
    for (int i = 0; i < numberElements; i++) {
     this.xvalues[i] = Double.parseDouble(x[i]);
     this.yvalues[i] = Double.parseDouble(y[i]);
    }
  }

  protected void evaluate(IIndividual ind)
  {
    ExprTree genotype = (ExprTree)((ExprTreeIndividual) ind).getGenotype();
    ExprTreeFunction function = new ExprTreeFunction(genotype);

    double[] y = new double[this.xvalues.length];

    for (int i = 0; i < this.xvalues.length; i++) {
      y[i] = ((Double) function.execute(new Object[] { Double.valueOf(this.xvalues[i]) }));
    }

    double rms = 0.0D;

    for (int i = 0; i < this.yvalues.length; i++) {
      double diff = y[i] - this.yvalues[i];
      rms += diff * diff;
    }

    rms = Math.sqrt(rms);

    ind.setFitness(new SimpleValueFitness(rms));
  }

  public Comparator<IFitness> getComparator()
  {
    return COMPARATOR;
  }
}
