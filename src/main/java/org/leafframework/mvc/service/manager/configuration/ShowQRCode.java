package org.leafframework.mvc.service.manager.configuration;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

@Controller
@RequestMapping("/qrcode")
public class ShowQRCode {

	@RequestMapping(value="/show")
	private void createQRCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String text = request.getParameter("url")+"&f="+request.getParameter("f")+"&uuid="+request.getParameter("uuid"); 
		int width = 122; 
		int height = 122; 
		//二维码的图片格式 
		Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>(); 
		//内容所使用编码 
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); 
		BitMatrix bitMatrix = new MultiFormatWriter().encode(text, 
		        BarcodeFormat.QR_CODE, width, height, hints); 
		//生成二维码 
		BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
		
		response.setContentType("image/jpeg");
		OutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
		out.close();
	}
}