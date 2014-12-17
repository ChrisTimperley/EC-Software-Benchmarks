public class Sextic extends Regression
{
  public double func(double x)
  {
    return x*x*x*x*x*x - 2.0*x*x*x*x + x*x;
  }
}