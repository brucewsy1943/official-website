package com.official.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
@Component
public class UploadUtils {
         
	private  Logger logger = LoggerFactory.getLogger(UploadUtils.class);
	
	
	@Value(value="${file.dir}")
	private   String  filespath;
	public   String uploadColumnPreview(MultipartFile file,String fileType) {
		if (file.isEmpty()) {
			return "文件为空,请重新上传";
		}
		logger.info("文件开始上传");
		String filename = file.getOriginalFilename();
		String filepath = filespath +fileType+filename;
		File files = new File(filepath);
		try {
			file.transferTo(files);
			logger.info("文件上传成功!");
			//return  fileType+filename;
			return  fileType+filename;
		} catch (Exception e) {
			logger.error(e.toString(),e);
		}
		return "";
	}

	public  String GenerateImage(String imgStr,String fileType)  
    {   //对字节数组字符串进行Base64解码并生成图片  
        if (imgStr == null) //图像数据为空  
            return "";  
        try   
        {  
            //Base64解码  
        	String imgStrs = imgStr.split(",")[1]; 
            @SuppressWarnings("restriction")
			byte[] b = new BASE64Decoder().decodeBuffer(imgStrs);
            for(int i=0;i<b.length;++i)  
            {  
            	
                if(b[i]<0)  
                {//调整异常数据  
                    b[i]+=256;  
                }  
            }  
            //生成jpeg图片   
            System.out.println(filespath);
            OutputStream out = new FileOutputStream(filespath+fileType);      
            out.write(b);  
            out.flush();  
            out.close();  
            return fileType;  
        }   
        catch (Exception e)   
        {  
            return "";  
        }  
    }  
}
