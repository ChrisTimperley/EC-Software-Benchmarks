import net.sf.jclec.exprtree.fun.Argument;

public class D1 extends Argument<Boolean>
{
  public D1()
  {
    super(Boolean.class, false);
  }

  public boolean equals(Object other)
  {
    return other instanceof D1;
  }

  public String toString()
  {
    return "D1";
  }
}
