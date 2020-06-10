package web.filter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class test
 */
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ServletOutputStream out =response.getOutputStream();
		PrintWriter   pw = response.getWriter();
		response.setContentType("text/html");
//1. 用OutputStream
		//若是本地服务器与本地客户端这种就不用说了，直接可以out.write(str.getBytes())可以输出没有问题。因为服务器中用str.getBytes()是采用默认本地的编码GBK。而浏览器也解析时也用本地默认编码，两者是统一的，所以没有问题。
		String str = "钓鱼岛是中国的";
		//out.write(str.getBytes());
		//out.print("<br/>");
		//而本地默认编码是GBK时(比例在中国)，那么用浏览器打开时就会乱码。因为服务器发送过来的是utf-8的1010数据，而客户端浏览器用了gbk来解码，两者编码不统一，肯定是乱码。
	//	out.write(str.getBytes("utf-8"));
		//解决办法
		//由服务器输出响应头告诉，浏览器用哪种编码来解码。所以要在服务器的servlet中，增加response.setHeader("content-type","text/html;charset=utf-8")，
		//当然也可直接用简单的response.setContentType("text/hmtl;charset=utf-8")。两种的操作是一样一样的。
		
// 2.PrintWriter
		//输出为？？？？
		//.getWriter()得到的字符输出流，默认对字符的输出是采用ISO-8859-1，而ISO-8859-1肯定是不支持中文的,所以在字符表中找不到字符
		pw.print(str);
		//解决办法首先要做的第一件事：是要将服务器对象输出字符能支持中文的。其次服务器向客户端写回的响应头要告诉客户端是用了哪种编码表进行编码的。
		//而实现这两个需求，只需要response.setContentType("text/hmtl;charset=utf-8")。就搞定了。特别注意：response.setContentType("text/html;charset=utf-8")要放在PrintOut out = response.getWriter()代码的前面，否则只是有告诉客户端用什么码表编码的功能，而服务器端还是用ISO-8859-1编码了。再特别提示下：在同一Servlet中的doGet或doPost方法中，不能既用response.getOutputStream又用response.getWriter，因为这两种response的响应输出字节流与字符流是冲突的，只能用其一。
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
