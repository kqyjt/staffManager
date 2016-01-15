package org.leafframework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.Calendar;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;
import org.leafframework.http.HttpClient;
import org.leafframework.mvc.controller.FileUploadController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class FtpUtil {
	
	private  final Logger logger = Logger.getLogger(FtpUtil.class); 
	private  ThreadLocal<FTPClient> ftpClientThreadLocal = new ThreadLocal<FTPClient>();
	private  String hostip="";
	private  int hostport=23;
	private  String loginuser="";
	private  String loginpwd="";
	private  String insideUploadPrefix="";
	private  String outsideUploadPrefix="";
	private  String getTokenUri="";
	
	public FtpUtil(){
		Properties proper = new Properties();
		try {
			proper.load(FileUploadController.class.getResourceAsStream("/org/leafframework/conf/leaf.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hostip = proper.getProperty("leaf.ftp.hostip");
		hostport = Integer.parseInt(proper.getProperty("leaf.ftp.hostport"));
		loginuser = proper.getProperty("leaf.ftp.loginuser");
		loginpwd = proper.getProperty("leaf.ftp.loginpwd");
		insideUploadPrefix = proper.getProperty("leaf.ftp.insideUploadPrefix");
		outsideUploadPrefix = proper.getProperty("leaf.ftp.outsideUploadPrefix");
		getTokenUri = proper.getProperty("leaf.ftp.getTokenUri");
	}
	
	
	public FtpUtil(String hostip, int hostport, String loginuser, String loginpwd,
			String insideUploadPrefix, String outsideUploadPrefix, String getTokenUri) {
		super();
		this.hostip = hostip;
		this.hostport = hostport;
		this.loginuser = loginuser;
		this.loginpwd = loginpwd;
		this.insideUploadPrefix = insideUploadPrefix;
		this.outsideUploadPrefix = outsideUploadPrefix;
		this.getTokenUri = getTokenUri;
	}


	/**
	 * 获得连接-FTP方式
	 * @param hostname FTP服务器地址
	 * @param port FTP服务器端口
	 * @param username FTP登录用户名
	 * @param password FTP登录密码
	 * @return FTPClient
	 */
	private  FTPClient getConnectionFTP() {
		
		 if (ftpClientThreadLocal.get() != null && ftpClientThreadLocal.get().isConnected()) {
	            return ftpClientThreadLocal.get();  
	        } else { 
				//创建FTPClient对象
				FTPClient ftp = new FTPClient();
				try {
					//连接FTP服务器
					ftp.connect(hostip, hostport);
					//下面三行代码必须要，而且不能改变编码格式，否则不能正确下载中文文件
					ftp.setControlEncoding("GBK");
					FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
					conf.setServerLanguageCode("zh");
					
					//登录ftp
					ftp.login(loginuser, loginpwd);
					
					//设置部分参数和缓冲
					//如果缺省该句 传输txt正常 但图片和其他格式的文件传输出现乱码
                    ftp.setFileType(FTP.BINARY_FILE_TYPE);
					
					ftp.setBufferSize(1024 * 1024 * 10);

					ftp.enterLocalPassiveMode();
					
					if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
						ftp.disconnect();
						logger.info("//%%%%%%%%% 连接 FTP 服务器失败！！！ %%%%%%%%%%%%//");
					}
					logger.info("//%%%%%%%%%  登录 FTP 服务器成功！ %%%%%%%%%%%%//");
				} catch (IOException e) {
					e.printStackTrace();
				}
				ftpClientThreadLocal.set(ftp);
				return ftp;
		
	        }
	}
	
	/**
	 * 关闭连接-FTP方式
	 * @param ftp FTPClient对象
	 * @return boolean
	 */
	private boolean closeFTP() {
		FTPClient ftp = getConnectionFTP();
		if (ftp.isConnected()) {
			try {
				ftp.logout();
				ftp.disconnect();
				logger.info("//%%%%%%%%%  断开 fTP 服务器成功！ %%%%%%%%%%%%//");
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	/**
	 * 上传文件-FTP方式
	 * @param ftp FTPClient对象
	 * @param path FTP服务器上传地址
	 * @param filename 本地文件路径
	 * @param inputStream 输入流
	 * @return boolean
	 */
	private boolean uploadFile( String path, String fileName, InputStream inputStream) {
		boolean success = false;
		try {
			FTPClient ftp=getConnectionFTP();
			//转移到指定FTP服务器目录
			ftp.changeWorkingDirectory(path);
			//得到目录的相应文件列表
			//FTPFile[] fs = ftp.listFiles();
			//防止重名！！！！！理论上不存在重名问题，以防万一
			//fileName = FtpUtil.changeName(fileName, fs);
			fileName = new String(fileName.getBytes("GBK"),"ISO-8859-1");
			path = new String(path.getBytes("GBK"), "ISO-8859-1");

			//将上传文件存储到指定目录
			ftp.storeFile(fileName, inputStream);
			//关闭输入流
			inputStream.close();
			
			//表示上传成功
			success = true;
			logger.info("//%%%%%%%%%  "+fileName+":上传文件成功！！！ %%%%%%%%%%%%//");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

	/**
	 * 删除文件-FTP方式
	 * @param ftp FTPClient对象
	 * @param path FTP服务器上传地址
	 * @param filename FTP服务器上要删除的文件名
	 * @return
	 */
	private boolean deleteFile( String path, String fileName) {
		boolean success = false;
		FTPClient ftp=getConnectionFTP();
		try {
			ftp.changeWorkingDirectory(path);//转移到指定FTP服务器目录
			fileName = new String(fileName.getBytes("GBK"),	"ISO-8859-1");
			//path = new String(path.getBytes("GBK"), "ISO-8859-1");
			ftp.deleteFile(fileName);
			success = true;
			logger.info("//%%%%%%%%%  delete file success! %%%%%%%%%%%%//");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

	/**
	 * 上传文件-FTP方式
	 * @param ftp FTPClient对象
	 * @param path FTP服务器上传地址
	 * @param fileName 本地文件路径
	 * @param localPath 本里存储路径
	 * @return boolean
	 */
	private boolean downFile(String path, String fileName, String localPath) {
		FTPClient ftp=getConnectionFTP();
		boolean success = false;
		try {
			ftp.changeWorkingDirectory(path);//转移到FTP服务器目录
			FTPFile[] fs = ftp.listFiles(); //得到目录的相应文件列表
			for (FTPFile ff : fs) {
				if (ff.getName().equals(fileName)) {
					File localFile = new File(localPath + "\\" + ff.getName());
					OutputStream outputStream = new FileOutputStream(localFile);
					//将文件保存到输出流outputStream中
					ftp.retrieveFile(new String(ff.getName().getBytes("GBK"), "ISO-8859-1"), outputStream);
					outputStream.flush();
					outputStream.close();
					logger.info("//%%%%%%%%%  下载文件成功！！！  %%%%%%%%%%%%//");
				}
			}

			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}
	
	/**
	 * 判断是否有重名文件
	 * @param fileName
	 * @param fs
	 * @return
	 */
	private  boolean isFileExist(String fileName, FTPFile[] fs) {
		for (int i = 0; i < fs.length; i++) {
			FTPFile ff = fs[i];
			if (ff.getName().equals(fileName)) {
				return true; //如果存在返回 正确信号
			}
		}
		return false; //如果不存在返回错误信号
	}

	/**
	 * 根据重名判断的结果 生成新的文件的名称
	 * @param fileName
	 * @param fs
	 * @return
	 */
	private  String changeName(String fileName, FTPFile[] fs) {
		int n = 0;
//		fileName = fileName.append(fileName);
		while (isFileExist(fileName.toString(), fs)) {
			n++;
			String a = "[" + n + "]";
			int b = fileName.lastIndexOf(".");//最后一出现小数点的位置
			int c = fileName.lastIndexOf("[");//最后一次"["出现的位置
			if (c < 0) {
				c = b;
			}
			StringBuffer name = new StringBuffer(fileName.substring(0, c));//文件的名字
			StringBuffer suffix = new StringBuffer(fileName.substring(b + 1));//后缀的名称
			fileName = name.append(a) + "." + suffix;
		}
		return fileName.toString();
	}
	
	
	private  String uploadlocalfile(String localfileName) throws Exception {
		
		//单个本地文件上传，返回保存路径
		
		File f1 = new File(localfileName);
		
		/*if (f1.exists() && f1.isFile()){  
	        logger.info(f1.length());  
	    }else{  
	        logger.info("file doesn't exist or is not a file");  
	    } */
		
		//上传目录设定为当天
		Calendar c = Calendar.getInstance();
	    int year = c.get(Calendar.YEAR); 
	    int month = c.get(Calendar.MONTH)+1; 
	    int date = c.get(Calendar.DATE);
	    String today=year+"-"+month+"-"+date;
	    String serverpath="/uploadpics/"+today+"/";
	    
	    String filename = f1.getName();
		//获取扩展名
		int pointpos=filename.lastIndexOf(".");
		String extname=filename.substring(pointpos);
		String newpicname=UUID.randomUUID().toString().replaceAll("-", "")+extname;
		
		//获取文件转为inputstream
		InputStream inputs = new FileInputStream(f1);

		boolean isSuccess = uploadFile(serverpath, newpicname, inputs);
		if(!isSuccess){
        	return null;
        }
		
		return serverpath+newpicname;
	}
	
	
	public  String uploadsteamfile(InputStream inputs,String realfilename) throws Exception {
		
		//单个本地文件上传，返回保存路径
		
		//上传目录设定为当天
		Calendar c = Calendar.getInstance();
	    int year = c.get(Calendar.YEAR); 
	    int month = c.get(Calendar.MONTH)+1; 
	    int date = c.get(Calendar.DATE);
	    String today=year+"-"+month+"-"+date;
	    String serverpath="/uploadpics/"+today+"/";
	    
		//获取扩展名
		int pointpos=realfilename.lastIndexOf(".");
		String extname=realfilename.substring(pointpos);
		String newpicname=UUID.randomUUID().toString().replaceAll("-", "")+extname;
		
        boolean isSuccess = uploadFile(serverpath, newpicname, inputs);
        if(!isSuccess){
        	return null;
        }
		
		return serverpath+newpicname;
	}
	
	/**
	 * 获取读取文件的token
	 * 
	 * @param tokenCount
	 * @return
	 * @date 2015年10月15日
	 *
	 */
	public String[] getFileToken(int tokenCount) {
		String tokens[] = new String[tokenCount];
		HttpClient httpClient = new HttpClient();
		String uri = getTokenUri + "&count=" + tokenCount;
		String resp = httpClient.sendRequest(uri);
		if (StringUtils.isNotEmpty(resp)) {
			resp = resp.trim();
			JSONObject jsonObj = JSONObject.parseObject(resp);
			if ("0000".equals(jsonObj.getString("errcode"))) {
				String randomcode = jsonObj.getString("randomcode");
				JSONArray codeArray = JSONObject.parseArray(randomcode);
				for (int i = 0; i < tokens.length; i++) {
					tokens[i] = codeArray.getString(i);
				}
			}
		}

		return tokens;
	}
	
	/**
	 * 获取图片展示路径
	 * @param imgUrl
	 * @param remoteIp
	 * @return
	 * @date 2015年10月16日
	 *
	 */
	public String genImgShowUrl(String imgUrl,String remoteIp) {
		String token = null;
		HttpClient httpClient = new HttpClient();
		String uri = getTokenUri + "&count=1";
		String resp = httpClient.sendRequest(uri);
		if (StringUtils.isNotEmpty(resp)) {
			resp = resp.trim();
			JSONObject jsonObj = JSONObject.parseObject(resp);
			if ("0000".equals(jsonObj.getString("errcode"))) {
				String randomcode = jsonObj.getString("randomcode");
				JSONArray codeArray = JSONObject.parseArray(randomcode);
				token = codeArray.getString(0);
			}
		}
		if(remoteIp != null && remoteIp.indexOf("134.32.32") != -1){
			// 内网访问
			return this.insideUploadPrefix + imgUrl + "?tkey=" + token+"&kt=1";
		}
		// 外网访问
		return this.outsideUploadPrefix + imgUrl + "?tkey=" + token+"&kt=1";
	}

	/**
	 * 
	 * @param args
	 * 
	 * @throws FileNotFoundException
	 * 
	 * 测试程序
	 * 
	 */
	public static void main(String[] args) {

	    //本地上传文件的名字
		String filename1="D:\\testdir\\201506181614183.jpg";
		String filename2="D:\\testdir\\20150618161418.jpg";
		
		try {
			//初始化连接
			FtpUtil myftpnew=new FtpUtil("134.32.32.144",9005,"vfly","kbs@[133]","http://134.32.32.144:18088/ftpserver/ftphome","http://61.156.3.144:18088/ftpserver/ftphome","http://134.32.32.144:18088/ftpserver/getrandom.jsp?token=87CEB49EB3810E304D6702882018394E");
			myftpnew.getFileToken(3);
			
			//直接上传本地文件方法
			//String fileresult=myftpnew.uploadlocalfile(filename1);
			//String fileresult2=myftpnew.uploadlocalfile(filename2);
			
			//测试框架中获取到inputstream的上传方法
			//获取文件转为inputstream
			File f1 = new File(filename1);
			File f2 = new File(filename2);
			InputStream inputs1 = new FileInputStream(f1);
			InputStream inputs2 = new FileInputStream(f2);
 
			if (f1.exists() && f1.isFile()){  
	            FileInputStream fis= new FileInputStream(f1);  
	            FileChannel fc= fis.getChannel();  
	            //logger.info(fc.size());
	        }else{  
	            //logger.info("file doesn't exist or is not a file");  
	        }  
			
			String fileresult=myftpnew.uploadsteamfile(inputs1, filename1);
			String fileresult2=myftpnew.uploadsteamfile(inputs2, filename2);
			
			
			//关闭
			myftpnew.closeFTP();
			
			System.out.println(fileresult);
			System.out.println(fileresult2);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}