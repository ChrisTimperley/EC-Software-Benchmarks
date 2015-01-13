import net.sf.jclec.exprtree.fun.Argument;

public class D5 extends Argument<Boolean>
{
  public D5()
  {
    super(Boolean.class, false);
  }

  public boolean equals(Object other)
  {
    return other instanceof D5;
  }

  public String toString()
  {
    return "D5";
  }
}
