package com.ruoyi.web.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.ruoyi.practiceScore.domain.SysPracticeScore;
import com.ruoyi.practiceScore.service.ISysPracticeScoreService;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.framework.config.ServerConfig;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 通用请求处理
 * 
 * @author ruoyi
 */
@RestController
public class CommonController
{
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private ServerConfig serverConfig;

    @Autowired
    private ISysPracticeScoreService sysPracticeScoreService;

    /**
     * 通用下载请求
     * 
     * @param fileName 文件名称
     * @param delete 是否删除
     */
    @GetMapping("common/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request)
    {
        try
        {
            if (!FileUtils.checkAllowDownload(fileName))
            {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }
            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = RuoYiConfig.getDownloadPath() + fileName;

            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, realFileName);
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete)
            {
                FileUtils.deleteFile(filePath);
            }
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 通用上传请求
     */
    @PostMapping("/common/upload")
    public AjaxResult uploadFile(MultipartFile file) throws Exception
    {
        try
        {
            // 上传文件路径
            String filePath = RuoYiConfig.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", fileName);
            ajax.put("url", url);
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 上传实习鉴定
     */
    @PostMapping("/common/uploadAppraisal")
    public AjaxResult uploadAppraisal(MultipartFile file,String nick_name,String user_id,String scoreId) throws Exception
    {
        try
        {
            //初始化腾讯云连接
            ConnectTencentCloud connectTencentCloud = new ConnectTencentCloud();
            File localFile = new File(file.getOriginalFilename());
            //将MultipartiFile转化为File
            org.apache.commons.io.FileUtils.copyInputStreamToFile(file.getInputStream(),localFile);
            //上传指定路径
            connectTencentCloud.uploadObject(localFile,"实习管理系统/实习鉴定/"+user_id+nick_name+"的实习鉴定.pdf");
            // 新文件名称
            String fileName = "实习管理系统/实习鉴定/"+user_id+nick_name+"的实习鉴定.pdf";
            //得到访问的URL
            String url = "https://shenwo-1302502474.cos.ap-nanjing.myqcloud.com/" + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", fileName);
            ajax.put("url", url);
            /**输入0表示用户从个人的日志页面上传*/
            if(scoreId.equals("0")){
                SysPracticeScore sysPracticeScore = new SysPracticeScore();
                sysPracticeScore.setUserId(Long.valueOf(user_id));
                List<SysPracticeScore> list = sysPracticeScoreService.selectSysPracticeScoreList(sysPracticeScore);
                if(list.size()==0)
                {
                    /**表中不存在会自动创建*/
                    sysPracticeScore.setAppraisal(url);
                    sysPracticeScoreService.insertSysPracticeScore(sysPracticeScore);
                    System.out.println("找不到，自己创建了");
                }
                else {
                    sysPracticeScore.setScoreId(list.get(0).getScoreId());
                    sysPracticeScore.setAppraisal(url);
                    sysPracticeScoreService.updateScoreAppraisal(sysPracticeScore);

                }
                scoreId = null;

            }
            if(scoreId!=null&&scoreId.equals("")==false){
                System.out.println("ID为 "+scoreId);
                SysPracticeScore sysPracticeScore = new SysPracticeScore();
                sysPracticeScore.setScoreId(Long.valueOf(scoreId));
                sysPracticeScore.setAppraisal(url);
                System.out.println();
                sysPracticeScoreService.updateScoreAppraisal(sysPracticeScore);
            }

            System.out.println("文件上传成功："+url);
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 上传实习总结
     */
    @PostMapping("/common/uploadSummary")
    public AjaxResult uploadSummary(MultipartFile file,String nick_name,String user_id,String scoreId) throws Exception
    {
        try
        {
            //初始化腾讯云连接
            ConnectTencentCloud connectTencentCloud = new ConnectTencentCloud();
            File localFile = new File(file.getOriginalFilename());
            //将MultipartiFile转化为File
            org.apache.commons.io.FileUtils.copyInputStreamToFile(file.getInputStream(),localFile);
            //上传指定路径
            connectTencentCloud.uploadObject(localFile,"实习管理系统/实习总结/"+user_id+nick_name+"的实习总结.pdf");
            // 新文件名称
            String fileName = "实习管理系统/实习总结/"+user_id+nick_name+"的实习总结.pdf";
            String url = "https://shenwo-1302502474.cos.ap-nanjing.myqcloud.com/" + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", fileName);
            ajax.put("url", url);
            /**输入0表示用户从个人的日志页面上传*/
            if(scoreId.equals("0")){
                SysPracticeScore sysPracticeScore = new SysPracticeScore();
                sysPracticeScore.setUserId(Long.valueOf(user_id));
                List<SysPracticeScore> list = sysPracticeScoreService.selectSysPracticeScoreList(sysPracticeScore);
                if(list.size()==0)
                {
                    /**表中不存在会自动创建*/
                    sysPracticeScore.setSummary(url);
                    sysPracticeScoreService.insertSysPracticeScore(sysPracticeScore);
                    System.out.println("找不到，自己创建了");
                }
                else {
                    sysPracticeScore.setScoreId(list.get(0).getScoreId());
                    sysPracticeScore.setSummary(url);
                    sysPracticeScoreService.updateScoreSummary(sysPracticeScore);
                }
                scoreId = null;
            }
            if(scoreId!=null&&scoreId.equals("")==false){
                System.out.println("ID为 "+scoreId);
                SysPracticeScore sysPracticeScore = new SysPracticeScore();
                sysPracticeScore.setScoreId(Long.valueOf(scoreId));
                sysPracticeScore.setSummary(url);
                sysPracticeScoreService.updateScoreSummary(sysPracticeScore);
            }
            System.out.println("文件上传成功："+url);
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 本地资源通用下载
     */
    @GetMapping("/common/download/resource")
    public void resourceDownload(String resource, HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        try
        {
            if (!FileUtils.checkAllowDownload(resource))
            {
                throw new Exception(StringUtils.format("资源文件({})非法，不允许下载。 ", resource));
            }
            // 本地资源路径
            String localPath = RuoYiConfig.getProfile();
            // 数据库资源地址
            String downloadPath = localPath + StringUtils.substringAfter(resource, Constants.RESOURCE_PREFIX);
            // 下载名称
            String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, downloadName);
            FileUtils.writeBytes(downloadPath, response.getOutputStream());
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }

    private File transferToFile(MultipartFile multipartFile) {
//        选择用缓冲区来实现这个转换即使用java 创建的临时文件 使用 MultipartFile.transferto()方法 。
        File file = null;
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            String[] filename = originalFilename.split(".");
            file=File.createTempFile(filename[0], filename[1]);
            multipartFile.transferTo(file);
            file.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

}
