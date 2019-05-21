public class Main {
    public static void main(String[] args) {
        singleThread();
        multyThread();
    }

    public static void singleThread(){
        final int size = 10000000;
        float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 1f;
        }

        long a = System.currentTimeMillis(); //Начало замера времени
        for (int i = 0; i < size; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        //Завершение замера времени
        System.out.printf("Время выполнения операций в однопоточном режиме: %d ms. \n",(System.currentTimeMillis() - a));
    }

    public static void multyThread(){
        final int size = 10000000;
        float[] arr = new float[size];
        final int h = size / 2;
        float[] a1 = new float[h];
        float[] a2 = new float[h];
        for (int i = 0; i < size; i++) {
            arr[i] = 1f;
        }

        long a = System.currentTimeMillis(); //Начало замера времени

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < h; i++) {
                    a1[i] = (float)(a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = h; i < size; i++) {
                    a2[i-h] = (float)(a2[i-h] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        //Завершение замера времени
        System.out.printf("Время выполнения операций в многопоточном режиме: %d ms. \n",(System.currentTimeMillis() - a));
    }
}
