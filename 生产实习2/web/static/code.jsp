<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Random"%>
<%@ page import="java.io.OutputStream"%>
<%@ page import="java.awt.Color"%>
<%@ page import="java.awt.Font"%>
<%@ page import="java.awt.Graphics"%>
<%@ page import="java.awt.image.BufferedImage"%>
<%@ page import="javax.imageio.ImageIO"%>
<%@ page import="com.tledu.QRcode.QRCodeUtil" %>
<%
	int width = 60;
	int height = 32;
	//create the image
	BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	Graphics g = image.getGraphics();
	// set the background color
	g.setColor(new Color(0xDCDCDC));
	g.fillRect(0, 0, width, height);
	// draw the border
	g.setColor(Color.black);
	g.drawRect(0, 0, width - 1, height - 1);
	// create a random instance to generate the codes
	Random rdm = new Random();
	String hash1 = Integer.toHexString(rdm.nextInt());
//	System.out.print(hash1);
	// make some confusion
	for (int i = 0; i < 50; i++) {
		int x = rdm.nextInt(width);
		int y = rdm.nextInt(height);
		g.drawOval(x, y, 0, 0);
	}
	// generate a random code
	String capstr = hash1.substring(0, 4);
	
	session.setAttribute("key", capstr);//
	System.out.println(capstr);

	String text = (String) session.getAttribute("key");
	// 嵌入二维码的图片路径
	String imgPath = "D:\\idea\\生产实习2\\web\\static\\img\\heihei.jpg";
	// 生成的二维码的路径及名称
	String destPath = "D:\\idea\\生产实习2\\web\\static\\update\\heihei.jpg";
	//生成二维码
	QRCodeUtil.encode(text, imgPath, destPath, true);
	// 解析二维码
	String str = QRCodeUtil.decode(destPath);
	// 打印出解析出的内容
	System.out.println(str);

	g.setColor(new Color(0, 100, 0));
	g.setFont(new Font("Candara", Font.BOLD, 24));
	g.drawString(capstr, 8, 24);
	g.dispose();
	response.setContentType("image/jpeg");
	out = pageContext.pushBody();
	out.clear();
	OutputStream strm = response.getOutputStream();
	ImageIO.write(image, "jpeg", strm);
	strm.close();
%>