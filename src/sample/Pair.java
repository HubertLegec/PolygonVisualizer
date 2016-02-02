package sample;

/**
 * Created by hubert.legec on 2016-01-19.
 */
public class Pair
{
    private float key;
    private float value;

    public Pair(float aKey, float aValue)
    {
        key   = aKey;
        value = aValue;
    }

    public float key()   { return key; }
    public float value() { return value; }
}
