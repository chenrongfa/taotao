package com.taotao.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


public class Ftptest {

	@Test
	public void testFtp(){
		InputStream local=null;
		FTPClient ftp=new FTPClient();
		try {
			ftp.connect("192.168.123.133", 21);
			boolean login = ftp.login("imageserver", "18720979339");
			if(login){
				local=new FileInputStream(new File("G:\\images\\20170510165652_1.jpg"));
				ftp.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
				ftp.setFileType(FTP.BINARY_FILE_TYPE);
				/**
				 *  创建目录
				 */
			/*	boolean makeDirectory = ftp.makeDirectory("/webroot/images");*/
				int mkd = ftp.mkd("/webroot/nihao2");
				
				
				
					ftp.changeWorkingDirectory("/nihao");
				boolean file = ftp.appendFile("j3.jpg", local);
				if(file){
					local.close();
				}
				
			}
			ftp.logout();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
