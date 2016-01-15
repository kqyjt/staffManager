package org.leafframework.mvc.controller;

import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.leafframework.util.FtpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

/**
 * 文件上传
 * 
 * @author zhangyy 2015年7月26日
 */
@Controller
@Scope("prototype")
public class FileUploadController {

	private FileUploadResp fileUploadResp = new FileUploadResp();
	@Autowired
	ApplicationContext applicationContext;
	private Logger logger = Logger.getLogger(FileUploadController.class);

	@RequestMapping(value = "/file/fileUpload")
	public ModelAndView fileUpload(@RequestParam(value = "uploadFile", required = false) MultipartFile[] uploadFiles,
			HttpServletResponse response) {

		PrintWriter out = null;
		String filePath = "";

		try {
			out = response.getWriter();

			if (uploadFiles != null) {
				for (int k = 0; k < uploadFiles.length; k++) {
					MultipartFile idcardFile = uploadFiles[k];
					if (idcardFile.getSize() == 0) {
						continue;
					}
					// 调用图片上传模块
					FtpUtil myftpnew=new FtpUtil();
					String fileresult=myftpnew.uploadsteamfile(idcardFile.getInputStream(), idcardFile.getOriginalFilename());

					if (StringUtils.isNotEmpty(fileresult)) {
						filePath += fileresult + "^";
					} else {
						fileUploadResp.setRespCode("fail");
						fileUploadResp.setRespMsg(idcardFile.getName() + "文件上传失败！");
						return null;
					}
				}
				fileUploadResp.setRespCode("success");
				fileUploadResp.setRespMsg("文件上传成功");
				fileUploadResp.setRespBody(filePath.substring(0, filePath.length() - 1));
			} else {
				fileUploadResp.setRespCode("fail");
				fileUploadResp.setRespMsg("未检测到需要上传的文件！");
				return null;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fileUploadResp.setRespCode("fail");
			fileUploadResp.setRespMsg("文件上传发生未知异常" + e.getMessage());
		} finally {
			logger.info("文件上传返回信息：" + JSONObject.toJSONString(fileUploadResp));
			out.print(JSONObject.toJSONString(fileUploadResp));
			if (out != null) {
				out.close();
			}
		}

		return null;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public FileUploadResp getFileUploadResp() {
		return fileUploadResp;
	}

	public void setFileUploadResp(FileUploadResp fileUploadResp) {
		this.fileUploadResp = fileUploadResp;
	}

}
