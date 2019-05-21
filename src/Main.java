public class Main {
    public static void main(String[] args) {
        singleThread();
        multyThread();
        System.out.println("END");
    }

    public static void singleThread(){
        final int size = 10;
        float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }

        long a = System.currentTimeMillis(); //Начало замера времени
        for (int i = 0; i < size; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        //Завершение замера времени
        System.out.printf("Время выполнения операций в однопоточном режиме: %d ms. \n",(System.currentTimeMillis() - a));
        System.out.println("Последний элемент массива: " + arr[size-1]);
    }

    public static void multyThread(){
        final int size = 10;
        float[] arr = new float[size];
        final int h = size / 2;
        float[] a1 = new float[h];
        float[] a2 = new float[h];
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }

        long a = System.currentTimeMillis(); //Начало замера времени

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);




        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        System.out.printf("Время выполнения операций в многопоточном режиме: %d ms. \n",(System.currentTimeMillis() - a));
        System.out.println("Последний элемент массива: " + arr[size-1]);

    };
}
