package web.servlet.manager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import web.entity.Product;
import web.exception.AddProductException;
import web.service.ProductService;
import web.utils.IdUtils;

/**
 * �����޸���Ϣ
 */
public class EditProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProductServlet() {
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
		//1.��װ���ݵ�product
		Product p = new Product();
//		map����Ϊ�˱�����Ʒ������ --- ��ֵ��
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", IdUtils.getUUID());
		// ��ȡ����ֵ����Ϊ���ǻ������String name = request.getParameter("name");
		DiskFileItemFactory dfif = new DiskFileItemFactory();
		String temp = this.getServletContext().getRealPath("/temp");
		//������ʱ��ſռ�
		dfif.setRepository(new File(temp));
		//���û���ռ��С
		dfif.setSizeThreshold(1024*1024*10);
		//2.�õ���������
		ServletFileUpload upload  = new ServletFileUpload(dfif);
		upload.setHeaderEncoding("utf-8");
		try {
			//����FileItem����
			List<FileItem> items = upload.parseRequest(request);
			for(FileItem item : items) {
				//�Ƿ�����ͨ�ֶ�
				if(item.isFormField()) {
					String fieldName  = item.getFieldName();
					String value = item.getString("utf-8");
					map.put(fieldName, value);
				}else { //�ϴ��ļ�
					String fileName = item.getName();
					if(fileName != null && ! fileName.trim().equals("")) {
						//Ϊ�˷�ֹ��ͬ�����·����ͬ������
						int index = fileName.lastIndexOf("\\");
						if(index != -1) {
							fileName = fileName.substring(index+1);
						}
						//�����ļ���,�滻��UUID����
						String ext = fileName.substring(fileName.lastIndexOf("."));
						fileName = IdUtils.getUUID()+ext;
						//�ļ��ϴ���λ�ã�ֱ�Ӵ���eclipse�µ���ĿWebContent
						String 	imgurl_path = "E:/jee-2019-06/workspace/bookproject/WebContent/productImg/"+fileName;
						File file = new File(imgurl_path);
						file.createNewFile();
						//�ϴ��ļ����Ӵ��̵���ʱ�ռ䵽��������
						InputStream in = item.getInputStream();
						FileOutputStream out = new  FileOutputStream(file);
						byte[]  buffer =  new byte[1024];  //ÿ�ζ�ȡ1�ֽ�
						int len;
						while((len = in.read(buffer)) >0)
							out.write(buffer,0,len);
						in.close();
						out.close();
						//ɾ�������е���ʱ�ļ�
						item.delete();
						
						map.put("imgurl", "/productImg/"+fileName);
					}
				}
			}
			//2.������������Map�����ֵ�Է�װ��Product����
			BeanUtils.populate(p, map);
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//3.����service
		ProductService service = new ProductService();
			service.editProduct(p);
			response.sendRedirect(request.getContextPath()+"/ListProductServlet");
		
	}

}
