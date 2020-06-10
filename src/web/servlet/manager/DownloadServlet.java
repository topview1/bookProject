package web.servlet.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.ProductService;

/**
 * Servlet implementation class DownloadServlet
 */
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		//1.��ȡ��Ӧ����Ϣ
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		//3.����service 
		ProductService service = new ProductService();
		List<Object []> ps=service.download(year,month);
		//4.�����ļ�������1
		String filename = year+"��"+month+"�����۰�.csv";
		//5�����ļ�����Ӧͷ
		String fileType = this.getServletContext().getMimeType(filename);
		response.setContentType(fileType);
		//6�������ؿ����Ӧͷ
		response.setHeader("Content-Disposition","attachment;filename="+URLEncoder.encode(filename, "utf-8"));
		// 7.��ѯ������ʾ���ļ���
		
		out.println("��Ʒ����,��Ʒ����");
		for(int i = 0;i<ps.size();i++) {
			Object[] a = ps.get(i);
			out.println(a[0]+","+a[1]);
		}
		out.flush();
		out.close();
	}

}
