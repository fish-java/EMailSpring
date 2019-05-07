package base;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

public class SenderTest extends BaseTest {
    private static String fromMail = "1251554146@qq.com";
    private static String targetMail = "bitfish666@outlook.com";

    @Autowired
    private JavaMailSender javaMailSender;

    @Test
    public void testSimpleTextMail(){   //发送普通文本邮件

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(fromMail);
        mailMessage.setTo(targetMail);//接受者
        mailMessage.setSubject("测试邮件");//主题
        mailMessage.setText("Test Email send by javaMailSender!");//邮件内容

        javaMailSender.send(mailMessage);
    }

    @Test
    public void testMimeMail() throws Exception {   //发送HTML格式的邮件

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(fromMail);
        helper.setTo(targetMail);
        helper.setSubject("主题：嵌入静态资源");
        helper.setText("<html><body><a href=\"http://www.baidu.com\" ></body></html>", true);

        javaMailSender.send(mimeMessage);
    }

    @Test
    public void testMimeInlineMail() throws Exception { //发送HTML格式含图片的邮件

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("FX_SKY");
        helper.setTo("ricky_feng@163.com");
        helper.setSubject("主题：嵌入静态资源");
        helper.setText("<html><body><img src=\"cid:logo\" ></body></html>", true);
        //FileSystemResource logoFile = new FileSystemResource(new File("logo.jpg"));
        //helper.addInline("logo", logoFile);

        javaMailSender.send(mimeMessage);
    }

    @Test
    public void testAttachmentMail() throws Exception { //发送含附件的邮件

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);// 第二个参数设置为true，表示允许添加附件
        helper.setFrom("FX_SKY");
        helper.setTo("ricky_feng@163.com");
        helper.setSubject("发送含图片附件的邮件");
        helper.setText("含有附件的邮件");

        //helper.addAttachment(MimeUtility.encodeText("附件-1.jpg"), new File("E:/attachment1.jpg"));
        //helper.addAttachment(MimeUtility.encodeText("附件-2.jpg"), new File("E:/attachment2.jpg"));

        javaMailSender.send(mimeMessage);
    }
}
