import net.sf.jclec.exprtree.fun.Argument;

public class A0 extends Argument<Boolean>
{
  public A0()
  {
    super(Boolean.class, false);
  }

  public boolean equals(Object other)
  {
    return other instanceof A0;
  }

  public String toString()
  {
    return "A0";
  }
}
