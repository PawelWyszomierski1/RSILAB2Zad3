import java.io.Serializable;
import java.util.LinkedList;

public class ResultType implements Serializable {
    LinkedList<Integer> liczby;
    int randomValue;
    public ResultType(){}
    public ResultType(LinkedList<Integer> value)
    {
        liczby=value;
    }
}
