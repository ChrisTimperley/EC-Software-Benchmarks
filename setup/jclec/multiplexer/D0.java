import net.sf.jclec.exprtree.fun.Argument;

public class D0 extends Argument<Boolean>
{
  public D0()
  {
    super(Boolean.class, false);
  }

  public boolean equals(Object other)
  {
    return other instanceof D0;
  }

  public String toString()
  {
    return "D0";
  }
}
