package com.saha.slnarch.report;

import com.saha.slnarch.common.helper.ZipHelper;
import com.saha.slnarch.common.mail.MailSendType;
import com.saha.slnarch.common.mail.MailSenderCreator;
import com.saha.slnarch.junit.InjectableJunitPageTestImpl;
import javax.mail.MessagingException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;

public abstract class ExtentReportTest extends InjectableJunitPageTestImpl {

  @Rule
  public ExtentJunitListener extentJunitListener = new ExtentJunitListener(getDriver());

  @BeforeClass
  public static void setUp() {
    ReportManager.getInstance();
  }


  @Override
  public void afterTest() {

  }



  @AfterClass
  public static void after() {
    ReportManager.getInstance().closeReport();
    ZipHelper.zipDirectory(ReportManager.getInstance().getReportDir(), "zipReport.zip");
    try {
      MailSenderCreator
          .createMailSender(MailSendType.SSL, "smtp.gmail.com", 465, "ali.aktolun@sahabt.com",
              "161992Oz5", true)
          .createMail("ali.aktolun@sahabt.com", "nurhan.colakoglu@sahabt.com", null, "Test Result",
              null)
          .setMessage("Test Result")
          .addAttachment("zipReport.zip")
          .send();
    } catch (MessagingException e) {
      e.printStackTrace();
    }
  }

}
