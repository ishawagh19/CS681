package edu.umb.cs681.hw15;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Directory extends FSElement {
	
	private ConcurrentLinkedQueue<FSElement> children = new ConcurrentLinkedQueue<FSElement>();
	private LinkedList<File> fileList = new LinkedList<File>();
	private LinkedList<Directory> directoryList = new LinkedList<Directory>();
	
	
	public Directory(Directory parent, String name, int size, LocalDateTime createdTime) {
		super(parent, name, size, createdTime);
		if (parent != null)
			parent.appendChild(this);
	}
	
	public ConcurrentLinkedQueue<FSElement> getChildren() {
		
		return this.children;

	}
	
	public void appendChild(FSElement child) {
		
		
    		this.children.add(child);
           

	}
	
	public int countChildren() {
		return getChildren().size();
		
		
	}
		
	public LinkedList<Directory> getSubDirectories() {
		
		lock.lock();
    	try {
    		for (FSElement e : getChildren()) {
    			if (e instanceof Directory)
    				directoryList.add((Directory) e);
    		}
    		return directoryList;
    	} finally {
    		lock.unlock();
    	}

	}
	
	public LinkedList<File> getFiles() {		
		
		LinkedList<File> files = new LinkedList<File>();
    	lock.lock();
    	try {
    		for (FSElement e : getChildren()) {
    			if (e instanceof File) {
    				files.add((File) e);
    			}
    		}
    		return files;
    	} finally {
    		lock.unlock();
    	}

	}

	public int getTotalSize() {
		int totalSize = 0;
		lock.lock();
    	try {
    		for (FSElement e : getChildren()) {
    			if (e instanceof Directory)
    				totalSize += ((Directory) e).getTotalSize();
    			else
    				totalSize += e.getSize();
    		}
    		return totalSize;
    	} finally {
    		lock.unlock();
    	}

		
	
	}

	public boolean isDirectory() {
		lock.lock();
    	try {
    		return true;
    	} finally {
    		lock.unlock();
    	}

	}
	
	
	


}