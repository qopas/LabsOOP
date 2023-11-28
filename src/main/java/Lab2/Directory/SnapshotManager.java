package Lab2.Directory;
import Lab2.Files.FileFactory;

import java.io.File;
import java.nio.file.*;
import java.nio.file.Path;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SnapshotManager {
    private Directory directory;
    private final Lock lock = new ReentrantLock();
    private Date lastSnapshotTime;

    public SnapshotManager(Directory directory) {
        this.directory = directory;
        scheduleSnapshotTask();
    }

    private void scheduleSnapshotTask() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            lock.lock();
            try {
                createSnapshot();
            } finally {
                lock.unlock();
            }
        }, 0, 5, TimeUnit.SECONDS);
    }

    public void processEvents(List<WatchEvent<?>> events, Path directoryPath) {
        Date snapshotTime = new Date();
        System.out.println("Created Snapshot at: " + snapshotTime);
        try {
            for (WatchEvent<?> event : events) {
                if (event.kind() == StandardWatchEventKinds.OVERFLOW) {
                    System.out.println("Event overflow occurred.");
                    continue;
                }
                @SuppressWarnings("unchecked")
                WatchEvent<Path> pathEvent = (WatchEvent<Path>) event;
                Path p = directoryPath.resolve(pathEvent.context());
                if(event.kind() == StandardWatchEventKinds.ENTRY_CREATE || event.kind() == StandardWatchEventKinds.ENTRY_MODIFY){
                    directory.getFileChanges().put(p, true);
                }
                if(event.kind() == StandardWatchEventKinds.ENTRY_DELETE){
                    directory.getFileChanges().put(p, null);
                    directory.getFiles().remove(directory.getFile(p.getFileName().toString()));
                }
                if(event.kind() == StandardWatchEventKinds.ENTRY_MODIFY){
                    FileFactory.updateFile(p,directory.getFile(p.getFileName().toString()));
                }
                System.out.println("Event: " + event.kind() + ", File: " + pathEvent.context().toString());
            }
        } finally {
        }
    }

    public void createSnapshot() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("snapshot.txt", true))) {
            Date snapshotTime = new Date();
            writer.write("Created Snapshot at: " + snapshotTime);
            writer.newLine();

            Map<Path, Boolean> fileChanges = directory.getFileChanges();

            for (Path file : fileChanges.keySet()) {
                if (fileChanges.get(file) != null && fileChanges.get(file)) {
                    if(directory.getFile(file.getFileName().toString()) == null){
                        writer.write(file.getFileName().toString() + " - NEW");
                        FileFactory.createFile(file,directory);
                    }
                    else{
                        writer.write(file.getFileName().toString() + " - Changed");
                        fileChanges.put(file, false);
                    }
                    writer.newLine();
                } else if(fileChanges.get(file) == null){
                    writer.write(file.getFileName().toString() + " - Deleted");
                    writer.newLine();
                    fileChanges.remove(file);
                }
                else {
                    writer.write(file.getFileName().toString() + " - No Changes");
                    writer.newLine();
                }

            }

            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error creating snapshot: " + e.getMessage());
        }
        lastSnapshotTime = new Date();
    }
    public Date getLastSnapshotTime() {
        return lastSnapshotTime;
    }
}
