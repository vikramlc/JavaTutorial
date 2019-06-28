public class Main {

    public static void main(String[] args) {
        System.out.println(ThreadColor.ANSI_PURPLE + "Hello from the main thread!");

        Thread anotherThread = new AnotherThread();
        anotherThread.start();

        Thread testThread = new Thread() {
            public void run() {
                System.out.println(ThreadColor.ANSI_GREEN + "Hello from the another anonymous");
            }
        };

        new Thread() {
            @Override
            public void run() {
                System.out.println(ThreadColor.ANSI_GREEN + "Hello from the anonymous");
            }
        }.start();

        testThread.start();

        System.out.println(ThreadColor.ANSI_PURPLE + "Hello again from the main thread.");
        //anotherThread.start(); // Gives illegal state exception
    }
}
