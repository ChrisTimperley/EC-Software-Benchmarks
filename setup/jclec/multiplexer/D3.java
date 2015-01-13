import net.sf.jclec.exprtree.fun.Argument;

public class D3 extends Argument<Boolean>
{
  public D3()
  {
    super(Boolean.class, false);
  }

  public boolean equals(Object other)
  {
    return other instanceof D3;
  }

  public String toString()
  {
    return "D3";
  }
}
