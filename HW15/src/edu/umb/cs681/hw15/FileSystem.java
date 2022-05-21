package edu.umb.cs681.hw15;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class FileSystem implements Runnable {

    private LinkedList<Directory> directoryroot;
    private static FileSystem instance = null;

    void appendRootDir(Directory RootDirectory) {
        directoryroot = new LinkedList<Directory>();
        directoryroot.add(RootDirectory);
    }
    private FileSystem() {};

    public static FileSystem getFileSystem() {
        if(instance==null)
            instance = new FileSystem ();
        return instance;
    }

    public LinkedList<Directory> getRootDirs() {
        return this.directoryroot;
    }

    @Override
    public void run() {

        System.out.println("\nThread no. "+Thread.currentThread().getId()+" is running");

    }

    public static void main(String[] args) {

        LocalDateTime localTime = LocalDateTime.now();

        FileSystem fs = new FileSystem();

        Directory root = new Directory(null, "root", 0, localTime);

        Directory applications = new Directory(root, "applications", 0, localTime);
        File a = new File(applications, "a", 1300, localTime);
        File b = new File(applications, "b", 350, localTime);
        Thread t1 = new Thread(fs);
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        System.out.println("Directory "+root.getName()+" is added.");
        System.out.println("File "+a.getName()+" is added to the directory "+root.getName()+".");
        System.out.println("File "+b.getName()+" is added to the directory "+root.getName()+".");


        Directory home = new Directory(root, "home", 0, localTime);
        File c = new File(home, "c", 500, localTime);
        File d = new File(home, "d", 700, localTime);
        Thread t2 = new Thread(fs);
        t2.start();
        try {
            t2.join();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        System.out.println("Directory "+home.getName()+" is added.");
        System.out.println("File "+c.getName()+" is added to the directory "+home.getName()+".");
        System.out.println("File "+d.getName()+" is added to the directory "+home.getName()+".");

        Directory code = new Directory(home, "code", 0, localTime);
        File e = new File(code, "e", 70, localTime);
        File f = new File(code, "f", 130, localTime);
        Thread t3 = new Thread(fs);
        t3.start();
        try {
            t3.join();
        } catch (InterruptedException e3) {
            e3.printStackTrace();
        }
        System.out.println("Directory "+code.getName()+" is added.");
        System.out.println("File "+e.getName()+" is added to the directory "+code.getName()+".");
        System.out.println("File "+f.getName()+" is added to the directory "+code.getName()+".");

    }

}