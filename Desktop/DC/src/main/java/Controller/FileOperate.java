package Controller;

import DbDao.Sql;
import DbDao.SqlConnect;
import DbDao.UserFileInfo;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@RequestMapping(path = "/FileOperate")
public class FileOperate {
    private SqlConnect sqlConnect;
    private  HttpServletRequest request;
    private final String uploadPath="D:"+File.separatorChar+"testUpload";

    @Autowired
    @Qualifier("sqlConnect")
    public void setSqlConnect(SqlConnect sqlConnect) {
        this.sqlConnect = sqlConnect;
    }
    public SqlConnect getSqlConnect() {
        return sqlConnect;
    }

    public HttpServletRequest getRequest() {
        return request;
    }
    @Autowired(required = false)
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @ResponseBody
    @RequestMapping(path = "/UploadFile")
     public String getUploadFile( HttpSession session){
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        DiskFileItemFactory factory=new DiskFileItemFactory();
        ServletFileUpload upload=new ServletFileUpload(factory);
        upload.setFileSizeMax(4194304);
        try {
            List<FileItem>files=upload.parseRequest(request);
            for(FileItem x:files){
                String name=x.getName();
                if(name!=null){
                    File savedFile=new File(uploadPath+File.separatorChar
                            +session.getAttribute("id"),name);
                    x.write(savedFile);
                }
                return "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fail";
    }

    @RequestMapping(path = "/download")
    public void downloadFile(@RequestParam("file_realName")String fileName, HttpServletResponse response, HttpSession session){
        File file=new File(uploadPath+File.separatorChar
                +session.getAttribute("id"),fileName);
        byte[]bytes=new byte[1024];
        try {
            BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
            OutputStream os=response.getOutputStream();
            int i= bis.read(bytes);
            while(i!=-1){
                os.write(bytes,0,i);
                i=bis.read(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping(path ="/delete")
    public Boolean deleteFile(@RequestParam("file_realName")String fileName,HttpSession session){
        File file=new File(uploadPath+File.separatorChar
                +session.getAttribute("id"),fileName);
        return file.delete();
    }

}
