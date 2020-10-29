package com.community.my.user;

import java.io.File;
import java.util.UUID;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.community.my.CommonUtils;
import com.community.my.Const;
import com.community.my.model.RestFile;
import com.community.my.user.model.UserDMI;
import com.community.my.user.model.UserParam;

@Service
public class UserService {
	
	@Autowired
	private JavaMailSenderImpl javaMailSenderImpl;
	
	@Autowired
	private UserMapper userMapper;
	
	
	//이메일 인증
	 public String sendAuthMail(String email) {
	        //6자리 난수 인증번호 생성
	        String authKey = CommonUtils.getAuthCode(); 
	        //인증메일 보내기
	        try {
         
	            MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();
	            mimeMessage.setFrom(new InternetAddress("gkrdnjsdyd123@gmail.com"));
	            mimeMessage.addRecipient(RecipientType.TO, new InternetAddress(email));
	            mimeMessage.setSubject("회원가입 이메일 인증");
	            mimeMessage.setText("인증키는 [" + authKey + "] 입니다" );
	            javaMailSenderImpl.send(mimeMessage);     
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        return authKey;
	 }
	 
	 
	 //가입
	 public int insJoin(UserParam param) {
		 int result = 0;
		 String beforePw = param.getUser_pw();
		 String salt = BCrypt.gensalt();
		 String afterPw = BCrypt.hashpw(beforePw, salt);
		 
		 param.setUser_pw(afterPw);
		 param.setSalt(salt);
		 
		 result = userMapper.insJoin(param);
		 
		 return result;
	 }
	 
	 //로그인(아이디없음(0), 비밀번호틀림(1), 로그인성공(2))
	 public int login(UserParam param, HttpSession hs) {
		 int result = Const.NO_PW; 
		 UserDMI userDb = userMapper.overlapChk(param);	 
		 if(userDb == null) {
			 result = Const.NO_ID; 
		 }else {
			 String beforePw = param.getUser_pw();
			 String salt = userDb.getSalt();
			 String afterPw = BCrypt.hashpw(beforePw, salt);
			 if(afterPw.equals(userDb.getUser_pw())) {
				 result = Const.SUCCESS;
				 userDb.setUser_pw(null);
				 
				 if(hs.getAttribute(Const.LOGIN_USER) != null) { //기존세션 제거
					 hs.removeAttribute(Const.LOGIN_USER);
				 }
				 hs.setAttribute(Const.LOGIN_USER, userDb);
			 }
		 }
		 return result;
	 }
	 
	 //프로필이미지 변경
	 public int updProfile(RestFile param, HttpServletRequest hsr) {
			
			String rPath =  hsr.getServletContext().getRealPath("");
			String path = rPath+"resources/img/user/"+param.getI_user()+"/profileIcon/";
			System.out.println(path);
			//경로에 폴더가 없다면 경로 생성
			File dir = new File(path);
			if(!dir.exists()) {
				dir.mkdirs();
			}
			
			//이미지파일 이름 변경
			MultipartFile mf = param.getProfile_img();
			String originNm = mf.getOriginalFilename();
			String ext = originNm.substring(originNm.lastIndexOf("."));
			String saveFileNm = UUID.randomUUID() + ext;
			
			UserParam uParam = new UserParam();
			uParam.setProfile_img(saveFileNm);
			uParam.setI_user(param.getI_user());
			
			int result = 0;
			result = userMapper.updProfile(uParam);
			
			//파일생성
			if(result == 1) {
				try {
					mf.transferTo(new File(path + saveFileNm));
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
			
			
			
			return result;
		}
}