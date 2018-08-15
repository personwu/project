package com.oracle.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;


public class PictureCheckCode extends HttpServlet {

    public static final long serialVersionUID = 1L;
    public PictureCheckCode(){
        super();
    }
    public void desTroy(){
        super.destroy();
    }
    public void init() throws ServletException{
        super.init();
    }

    //该方法的主要作用是获得随机生成的颜色
    public Color getRandColor(int s, int e) {

        Random random = new Random();
        if (s > 255) s = 255;
        if (e > 255) e = 255;
        int r, g, b;
        r = s + random.nextInt(e - s);  //随机生成RGB颜色中的r值
        g = s + random.nextInt(e - s);  //随机生成RGB颜色中的g值
        b = s + random.nextInt(e - s);  //随机生成RGB颜色中的b值
        return new Color(r,g,b);
    }


    public void Service(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {



        //设置不缓存图片
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","No-Cache");
        response.setDateHeader("Expires",0);
        //指定生成的响应图片，一定不能缺少这句话
        response.setContentType("image/jpeg");
        int width = 86, height = 22;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB); //创建BuffedImage对象，相当于一张图片
        Graphics g=image.getGraphics(); //创建Graphics对象，其作用相当于画笔
        Graphics2D g2d = (Graphics2D)g;//创建Graphics对象
        Random random = new Random();
        Font mfont = new Font("楷体",Font.BOLD,16); //定义字体样式
        g.setColor(getRandColor(200,250));
        g.fillRect(0,0,width,height); //绘制背景
        g.setFont(mfont);
        g.setColor(getRandColor(180,200)); //设置字体

        //绘制100条颜色和位置全部为随机产生的线条，该线条为2f
        for(int i = 0;i < 100;i++){
            int x = random.nextInt(width - 1);
            int y = random.nextInt(height - 1);
            int x1 = random.nextInt(6) + 1;
            int y1 = random.nextInt(12) + 1;
            BasicStroke bs = new BasicStroke(2f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL);
            Line2D line=new Line2D.Double(x,y,x + x1,y + y1);
            g2d.setStroke(bs);
            g2d.draw(line); //绘制直线
        }

        //输出由英文，数字，和中文随机组成的验证文字，具体的组合方式根据生成随机数确定
        String sRand = "";
        String ctmp = "";
        int itmp = 0;
        //制定输出的验证码为四位
        for(int i = 0;i < 4;i++){

            switch (random.nextInt(3)){
                case 1:
                    itmp = random.nextInt(26) + 65;
                    ctmp = String.valueOf((char)itmp);
                    break;
                case 2:   //生成汉子
                    String[] rBase = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
                    //生成第一位区码
                    int r1 = random.nextInt(3) + 11;
                    String str_r1 = rBase[r1];
                    //生成第二位区码
                    int r2;
                    if(r1 == 13){
                        r2 = random.nextInt(7);
                    }else{
                        r2 = random.nextInt(16);
                    }
                    String str_r2 = rBase[r2];
                    //生成第一位位码
                    int r3 = random.nextInt(6) + 10;
                    String str_r3 = rBase[r3];
                    //生成第二位位码
                    int r4;
                    if(r3 == 10){
                        r4 = random.nextInt(15)+1;
                    }else if(r3 == 15){
                        r4 = random.nextInt(15);
                    }else{
                        r4 = random.nextInt(16);
                    }
                    String str_r4 = rBase[r4];
                    //将生成的随机内码转换成数字
                    byte[] bytes = new byte[2];
                    //将生成的区码保存到字节数组的第一个元素中
                    String str_12 = str_r1 + str_r2;
                    int templow = Integer .parseInt(str_12,16);
                    bytes[0] = (byte) templow;
                    //将生成的位码保存到字节数组的第二个元素中
                    String str_34 = str_r3 + str_r4;
                    int tempHign = Integer.parseInt(str_34,16);
                    bytes[1] = (byte)tempHign;
            }



        }
    }


}
