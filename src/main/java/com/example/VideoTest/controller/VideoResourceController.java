package com.example.VideoTest.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.stream.FileImageInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping("/VideoResource")
public class VideoResourceController {
    @Value("${video.downloadDir}")
    private String downloadDir;

    @RequestMapping("/{videoFileName}")
    public String getVideo(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             @PathVariable String videoFileName){
        FileImageInputStream ins = null;
        OutputStream os = null;
        try {
            File downloadDirFile = new File(downloadDir);
            if(downloadDirFile.exists()){
                String PicFilePath = downloadDir + "/" + videoFileName;
                File PicFile = new File(PicFilePath);
                if(PicFile.exists()) {
                    httpServletResponse.setContentType("video/mpeg4");
                    ins = new FileImageInputStream(PicFile);
                    os = httpServletResponse.getOutputStream();
                    // img为图片的二进制流
                    int len = 0;
                    final int BUFFER_SIZE = 4096;
                    byte[] img = new byte[BUFFER_SIZE];
                    while ((len = ins.read(img,0,BUFFER_SIZE))!=-1){
                        os.write(img,0,len);
                        os.flush();
                    }
                }else{
                    return "404";
                }
            }else{
                return "404";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(ins != null){
                try {
                    ins.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return "success";
    }

}
