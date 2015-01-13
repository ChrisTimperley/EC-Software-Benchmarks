import net.sf.jclec.exprtree.fun.Argument;

public class A1 extends Argument<Boolean>
{
  public A1()
  {
    super(Boolean.class, false);
  }

  public boolean equals(Object other)
  {
    return other instanceof A1;
  }

  public String toString()
  {
    return "A1";
  }
}
