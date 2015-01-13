import java.util.Comparator;
import net.sf.jclec.IFitness;
import net.sf.jclec.IIndividual;
import net.sf.jclec.base.AbstractEvaluator;
import net.sf.jclec.fitness.SimpleValueFitness;
import net.sf.jclec.fitness.ValueFitnessComparator;
import net.sf.jclec.realarray.RealArrayIndividual;

public class Rastrigin extends AbstractEvaluator
{
  protected boolean maximize = false;
  private Comparator<IFitness> COMPARATOR;

  protected void evaluate(IIndividual ind)
  {
    double[] genotype = (double[])((RealArrayIndividual)ind).getGenotype();
    double fitness = 0.0D;

    for (int i = 0; i < genotype.length; i++) {
      fitness += Math.pow(genotype[i], 2.0D) - 10.0D * Math.cos(6.2831853071D * genotype[i]) + 10.0D;
    }

    ind.setFitness(new SimpleValueFitness(fitness));
  }

  public Comparator<IFitness> getComparator()
  {
    if (this.COMPARATOR == null) {
      this.COMPARATOR = new ValueFitnessComparator(!this.maximize);
    }
    return this.COMPARATOR;
  }
}
