import net.sf.jclec.exprtree.fun.Argument;

public class D6 extends Argument<Boolean>
{
  public D6()
  {
    super(Boolean.class, false);
  }

  public boolean equals(Object other)
  {
    return other instanceof D6;
  }

  public String toString()
  {
    return "D6";
  }
}
