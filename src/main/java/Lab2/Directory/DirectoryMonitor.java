package Lab2.Directory;

import Lab2.Files.File;

import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DirectoryMonitor {
    private static final String directoryPath= "C:\\Users\\user\\IdeaProjects\\LabsOOP\\src\\main\\java\\Lab2\\labFiles";
    private static Lock lock = new ReentrantLock();

    public static void run() {
        Directory directory = new Directory(directoryPath);
        SnapshotManager snapshotManager = new SnapshotManager(directory);
        Thread snapshotThread = new Thread(() -> {
            try {
                WatchService watchService = FileSystems.getDefault().newWatchService();
                Path directoryPathObj = Paths.get(directoryPath);
                directoryPathObj.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);
                while (true) {
                    WatchKey key = watchService.poll(10, TimeUnit.SECONDS);
                    if (key != null) {
                        List<WatchEvent<?>> events = key.pollEvents();
                        snapshotManager.processEvents(events, Paths.get(directoryPath));
                        key.reset();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        snapshotThread.setDaemon(true);
        snapshotThread.start();
        appLoop(snapshotManager,directory);
    }
    private static void appLoop(SnapshotManager snapshotManager, Directory directory){
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("\nActions:");
            System.out.println("1. commit");
            System.out.println("2. info <filename>");
            System.out.println("3. status");
            System.out.println("4. exit");
            System.out.print("Enter action: ");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "commit":
                    snapshotManager.createSnapshot();
                    System.out.println("Snapshot committed.");
                    break;
                case "status":
                    displayStatus(directory, snapshotManager);
                    break;
                case "exit":
                    exit = true;
                    break;
                default:
                    if (input.startsWith("info")) {
                        String[] parts = input.split(" ");
                        if (parts.length == 2) {
                            String filename = parts[1];
                            displayFileInfo(directory, filename);
                        } else {
                            System.out.println("Invalid command. Usage: info <filename>");
                        }
                    } else {
                        System.out.println("Invalid command. Try again.");
                    }
                    break;
            }
        }
    }
    private static void displayFileInfo(Directory directory, String filename) {
        File file = directory.getFile(filename);
        if (file != null) {
            file.printInfo();
        } else {
            System.out.println("File not found: " + filename);
        }
    }

    private static void displayStatus(Directory directory, SnapshotManager snapshotManager) {
        Map<Path, Boolean> files = directory.getFileChanges();
        lock.lock();
        Date snapshotTime = snapshotManager.getLastSnapshotTime();
        boolean noChanges = true;
        for (Path path : files.keySet()) {
            if (files.get(path)) {
                noChanges = false;
                break;
            }
        }
        if (noChanges) {
            System.out.println("All files have experienced no change since the snapshot time of " + snapshotTime + ".");
        } else {
            System.out.print("Files that have changed since the snapshot time of " + snapshotTime + ": ");
            boolean firstChangedFile = true;
            for (Path path : files.keySet()) {
                if (files.get(path)){
                    if (!firstChangedFile) {
                        System.out.print(", ");
                    }
                    System.out.print(path.getFileName());
                    firstChangedFile = false;
                }
            }
            System.out.println(" did change since the snapshot.");
        }
        lock.unlock();
    }
}
