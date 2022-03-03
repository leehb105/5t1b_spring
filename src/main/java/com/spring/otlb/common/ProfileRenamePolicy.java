package com.otlb.semi.common;

import java.io.File;
import java.io.IOException;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class ProfileRenamePolicy implements FileRenamePolicy {
	
	private final String empNo;
	
	public ProfileRenamePolicy(String empNo) {
		super();
		this.empNo = empNo;

	}

	@Override
	public File rename(File originalFile) {
		File renamedFile = new File(originalFile.getParent(), this.empNo + ".png");
		return renamedFile;
//	File renamedFile = null;	
//		try {
//			renamedFile = new File(this.empNo + ".png");
//			System.out.println(renamedFile);
//
//			if(renamedFile.delete()) {
//				System.out.println("deleted" + renamedFile.getName());
//				if(renamedFile.createNewFile()) {
//					System.out.println("created" + renamedFile.getName());
//					
//				} else {
//					System.out.println("not created");
//				}
//			}
//			
//		} catch (IOException e) {
//			System.out.println("sdfsd");
//			e.printStackTrace();
//		}
//		try {
//			System.out.println(renamedFile.getCanonicalPath());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return renamedFile;
		
//		File renamedFile = null;
//		
//		do {
//			String originalName = originalFile.getName();
//			String ext = "";
//			int dot = originalName.lastIndexOf(".");
//			if(dot > -1)
//				ext = originalName.substring(dot);
//			String newName = empNo + ext;
//			renamedFile = new File(originalFile.getParent(), newName);
//
//		} while(!createNewFile(renamedFile));
//
//		return renamedFile;
//	}
//
//	private boolean createNewFile(File f) {
//		try {
//			return f.createNewFile();
//		} catch (IOException ignored) {
//			return true;
//		}
	}

}
