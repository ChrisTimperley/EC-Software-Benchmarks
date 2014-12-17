import ec.gp.*;

public class RegressionData extends GPData
{
  public double x;

  public void copyTo(final GPData gpd) 
  {
    ((RegressionData)gpd).x = x;
  }
}