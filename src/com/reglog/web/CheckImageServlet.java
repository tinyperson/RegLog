package com.reglog.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 生成验证码
 * getWrite（）字符流
 * getOutputStream()字节流
 */
@WebServlet(name = "CheckImageServlet", urlPatterns = "/checkimage")
public class CheckImageServlet extends HttpServlet {
	private final int WIDTH = 120;
	private final int HEIGHT = 30;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BufferedImage bf = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);

		Graphics2D graphics = (Graphics2D) bf.getGraphics();

		graphics.setColor(Color.GRAY);
		graphics.fillRect(0, 0, WIDTH, HEIGHT);

		String base = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		graphics.setColor(Color.RED);
		graphics.setFont(new Font("黑体", Font.BOLD, 23));
		Random random = new Random();
		
		//存放产生的验证码
		StringBuilder sb = new StringBuilder();
		
		int m = 15;
		for (int i = 0; i < 4; i++) {
			int index = random.nextInt(base.length());
			char charAt = base.charAt(index);
			// 加旋转 -30~30
			// 角度变弧度 角度*PI/180

			int angle = random.nextInt(60) - 30;
			double theta = angle * Math.PI / 180;
			graphics.rotate(theta, m, 15);
			graphics.drawString(charAt + "", m, 20);
			graphics.rotate(-theta, m, 15);
			m += 20;
			
			sb.append(charAt);
		}
		
		request.getSession().setAttribute("checkcode_session", sb.toString());

		graphics.setColor(Color.BLUE);
		for (int i = 0; i < 8; i++) {
			int x1 = random.nextInt(WIDTH);
			int x2 = random.nextInt(WIDTH);
			int y1 = random.nextInt(HEIGHT);
			int y2 = random.nextInt(HEIGHT);
			graphics.drawLine(x1, y1, x2, y2);
		}

		ImageIO.write(bf, "png", response.getOutputStream());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
