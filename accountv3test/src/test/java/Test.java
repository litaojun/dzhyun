
public class Test {
	static String[] arrayA = new String[]{"a1", "a2", "a3","3","%6"};
	static String[] arrayB = new String[]{"b1", "b2", "b3"};
	static String[] arrayC = new String[]{"c1", "c2", "c3"};

	public static void main(String[] arg){
		System.out.println(arrayA[GetRandomNum(0, 4)]);
	}

	public static String randomSum(){
		return arrayA[GetRandomNum(0, 2)] + arrayB[GetRandomNum(0, 2)] + arrayC[GetRandomNum(0, 2)];
	}

    public static int GetRandomNum(int Min, int Max)
    {
        int Range = Max - Min;
        double Rand = Math.random();
        return (int)(Min + Math.round(Rand * Range));
    }
}
