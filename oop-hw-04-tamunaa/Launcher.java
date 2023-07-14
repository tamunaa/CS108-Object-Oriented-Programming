import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Launcher extends Thread {
    private Semaphore sem;
    private ExecutorService exec;
    private DefaultTableModel model;
    private WebFrame frame;

    public Launcher(int numThreads, DefaultTableModel model, WebFrame frame) {
        exec = Executors.newFixedThreadPool(numThreads);
        sem = new Semaphore(numThreads);
        this.model = model;
        this.frame = frame;
    }

    @Override
    public void run() {
        ArrayList<WebWorker> workers = new ArrayList<>();

        IntStream.range(0, model.getRowCount())
                .forEach(i -> {
                    String url = (String) model.getValueAt(i, 0);
                    WebWorker worker = new WebWorker(url, sem, frame, i);
                    exec.execute(worker);
                    workers.add(worker);
                });

        exec.shutdown();

        try {
            exec.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            workers.forEach(WebWorker::interrupt);
        }

        long elapsedTime = System.currentTimeMillis() - frame.getStartTime();
        frame.updateElapsedLabel(elapsedTime);
        frame.changeButtons(true);
    }

    public void stopFetching() {
        exec.shutdownNow();
    }
}
