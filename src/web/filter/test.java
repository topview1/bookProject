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
//1. ��OutputStream
		//���Ǳ��ط������뱾�ؿͻ������־Ͳ���˵�ˣ�ֱ�ӿ���out.write(str.getBytes())�������û�����⡣��Ϊ����������str.getBytes()�ǲ���Ĭ�ϱ��صı���GBK���������Ҳ����ʱҲ�ñ���Ĭ�ϱ��룬������ͳһ�ģ�����û�����⡣
		String str = "���㵺���й���";
		//out.write(str.getBytes());
		//out.print("<br/>");
		//������Ĭ�ϱ�����GBKʱ(�������й�)����ô���������ʱ�ͻ����롣��Ϊ���������͹�������utf-8��1010���ݣ����ͻ������������gbk�����룬���߱��벻ͳһ���϶������롣
	//	out.write(str.getBytes("utf-8"));
		//����취
		//�ɷ����������Ӧͷ���ߣ�����������ֱ��������롣����Ҫ�ڷ�������servlet�У�����response.setHeader("content-type","text/html;charset=utf-8")��
		//��ȻҲ��ֱ���ü򵥵�response.setContentType("text/hmtl;charset=utf-8")�����ֵĲ�����һ��һ���ġ�
		
// 2.PrintWriter
		//���Ϊ��������
		//.getWriter()�õ����ַ��������Ĭ�϶��ַ�������ǲ���ISO-8859-1����ISO-8859-1�϶��ǲ�֧�����ĵ�,�������ַ������Ҳ����ַ�
		pw.print(str);
		//����취����Ҫ���ĵ�һ���£���Ҫ����������������ַ���֧�����ĵġ���η�������ͻ���д�ص���ӦͷҪ���߿ͻ������������ֱ������б���ġ�
		//��ʵ������������ֻ��Ҫresponse.setContentType("text/hmtl;charset=utf-8")���͸㶨�ˡ��ر�ע�⣺response.setContentType("text/html;charset=utf-8")Ҫ����PrintOut out = response.getWriter()�����ǰ�棬����ֻ���и��߿ͻ�����ʲô������Ĺ��ܣ����������˻�����ISO-8859-1�����ˡ����ر���ʾ�£���ͬһServlet�е�doGet��doPost�����У����ܼ���response.getOutputStream����response.getWriter����Ϊ������response����Ӧ����ֽ������ַ����ǳ�ͻ�ģ�ֻ������һ��
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
