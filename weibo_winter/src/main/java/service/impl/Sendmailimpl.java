package service.impl;


import been.User;
import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import  javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

public class Sendmailimpl implements Runnable {
    private final String SENDMAIL="15823316253@163.com";
    private final String PASSWORD="a134652879";
    private String myEmailSMTPHost = "smtp.163.com";
    private User user;
    private String path;
    public Sendmailimpl(User user,String path){
        this.user=user;
        this.path=path;
    }
    @Override
    public void run() {
        try {
            //这是协议
            Properties props = new Properties();                    // 参数配置
            props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
            props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
            props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
            props.setProperty("mail.smtp.port","994");
            props.setProperty("mail.smtp.socketFactory.port", "994");
            MailSSLSocketFactory sf = null;
            try {
                sf = new MailSSLSocketFactory();
                sf.setTrustAllHosts(true);
            } catch (GeneralSecurityException e1) {
                e1.printStackTrace();
            }
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.ssl.socketFactory", sf);
        //创建Session 会话
            Session session = Session.getDefaultInstance(props);
            session.setDebug(true);
            //创建邮件
            MimeMessage message = getMessage(session, SENDMAIL, user.getAccount());
        //然后运输出去发送
            Transport transport = session.getTransport();
        //链接你的邮箱
        transport.connect(SENDMAIL,PASSWORD);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MimeMessage getMessage(Session session, String sendMail, String receiveMail) throws Exception {
        MimeMessage mimeMessage=new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(sendMail,"","utf-8"));
        mimeMessage.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, user.getTrue_name(), "UTF-8"));
        mimeMessage.setSubject("欢迎"+user.getTrue_name()+"到此来激活","utf-8");
       // 这个线程不安全 管td 然后就是 发送code 的问题
        StringBuilder sbd = new StringBuilder();
        sbd.append("<br/>请确认此邮件地址以激活您的账号。<br/>");
        sbd.append("<font color='red'><a href="+path+"enroll/tool?flag="
                + user.getAcode()+ "&id="+user.getAccount()+ " target='_blank'");
        sbd.append(">立即激活</a></font><br/>");
        sbd.append("这是一封自动发送的邮件；如果您并未要求但收到这封信件，您不需要进行任何操作。");
        mimeMessage.setContent(sbd.toString(), "text/html;charset=utf-8");
        mimeMessage.setSentDate(new Date());
        mimeMessage.saveChanges();
        return mimeMessage;
    }
}
