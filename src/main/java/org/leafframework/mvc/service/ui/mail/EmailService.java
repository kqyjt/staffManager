package org.leafframework.mvc.service.ui.mail;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import freemarker.template.Template;

@Service
@Scope("prototype")
public class EmailService {
	protected Logger logger = Logger.getLogger(this.getClass());

	@Resource(name = "mailSender")
	private JavaMailSender mailSender;

	@Resource(name = "freeMarker")
	private FreeMarkerConfigurer freeMarkerConfigurer;

	private String from;
	private String to;
	private String cc;
	private String title;
	private List<String> attachment = new ArrayList<String>();
	private String template;
	private Map<String, Object> param;

	public String getTemplate() {
		return template;
	}

	public Map<String, Object> getParam() {
		return param;
	}

	public List<String> getAttachment() {
		return attachment;
	}

	public void setTemplate(Map<String, Object> param, String template) {
		this.template = template;
		this.param = param;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void addAttachment(String attachment) {
		this.attachment.add(attachment);
	}

	public void sendEmail() {
		MimeMessage msg = null;
		try {
			msg = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, "utf-8");
			helper.setFrom(this.getFrom());
			helper.setTo(this.getTo());
			helper.setSubject(MimeUtility.encodeText(this.getTitle(),
					helper.getEncoding(), "B"));

			// true表示text的内容为html
			helper.setText(getMailText(), true);

			// 添加内嵌图片文件
			Map<String, Object> map = this.getParam();
			Iterator<Entry<String, Object>> iter = map.entrySet().iterator();
			while (iter.hasNext()) {
				Entry<String, Object> entry = iter.next();
				Object key = entry.getKey();
				Object val = entry.getValue();
				if (key.toString().startsWith("cid:")) {
					helper.addInline(key.toString().substring(4), new File(
							(String) val)); // 附件内容
				}
			}

			// 附件处理， 这里的方法调用和插入图片是不同的，解决附件名称的中文问题
			for (int i = 0; i < this.getAttachment().size(); i++) {
				String attachment = (String) this.getAttachment().get(i);
				File file = new File(attachment);
				helper.addAttachment(MimeUtility.encodeWord(file.getName()),
						file);
			}
			mailSender.send(msg);
		} catch (Exception e) {
			throw new RuntimeException("Mail Send failed", e);
		}
		logger.debug("Mail to :"+this.getTo()+" 邮件发送成功");
	}
	private String getMailText() throws Exception {
		
		Template template = freeMarkerConfigurer.getConfiguration()
				.getTemplate(this.getTemplate());

		String htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(
				template, this.getParam());
		return htmlText;
	}
}
