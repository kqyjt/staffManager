package org.leafframework.mvc.service.manager.upload;

import java.io.File;
import java.util.Date;

import org.leafframework.mvc.service.Business;
import org.leafframework.util.DateUtil;
import org.leafframework.util.FileUtil;
import org.leafframework.util.RETURN;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service("FileUpload")
@Scope("prototype")
public class FileUpload extends Business {

	@Override
	public RETURN execute() throws Exception {
		// TODO Auto-generated method stub
		return RETURN.SUCCESS;
	}

	@Override
	public RETURN init() throws Exception {
		// TODO Auto-generated method stub
		MultipartFile[] uploadFile=(MultipartFile[]) this.getInParam().get("uploadFile");
		String[] fileName=(String[]) this.getInParam().get("fileName");
		for (int i = 0; i < uploadFile.length; i++) {
			MultipartFile file = uploadFile[i];
			if(file.isEmpty()){
				continue;
			}
			String name = fileName[i];
			try {
				File dstFile = new File(FileUtil.uploadPath + "staffId" + "."
						+ DateUtil.format(new Date()).replaceAll("[\\s-:]", "")
						+ ".TMMenus." + name);
				file.transferTo(dstFile);
				logger.info("Server File Location="+ dstFile.getAbsolutePath());

				logger.debug( "You successfully uploaded file=" + name);
			} catch (Exception e) {
				logger.debug( "You failed to upload " + name + " => " + e.getMessage());
			}
		}
		return RETURN.SUCCESS;
	}

	@Override
	public RETURN query() throws Exception {
		// TODO Auto-generated method stub
		return RETURN.SUCCESS;
	}

}
