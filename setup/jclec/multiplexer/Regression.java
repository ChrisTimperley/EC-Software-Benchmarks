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

public class Multiplexer extends AbstractEvaluator
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

    int score = 0
    //
    output[i] = (Boolean) function.execute(new Object[] {
      this.inputs[i][0],
      this.inputs[i][1],
      this.inputs[i][2],
      this.inputs[i][3],
      this.inputs[i][4],
      this.inputs[i][5],
      this.inputs[i][6],
      this.inputs[i][7],
      this.inputs[i][8],
      this.inputs[i][9],
      this.inputs[i][10]
    });

    rms = Math.sqrt(rms);

    ind.setFitness(new SimpleValueFitness(2048 - score));
  }

  public Comparator<IFitness> getComparator()
  {
    return COMPARATOR;
  }
}