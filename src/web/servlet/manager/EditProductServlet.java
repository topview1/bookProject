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
 * 用于修改信息
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
		//1.封装数据到product
		Product p = new Product();
//		map集合为了保存商品的数据 --- 键值对
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", IdUtils.getUUID());
		// 获取不到值，因为表单是混合数据String name = request.getParameter("name");
		DiskFileItemFactory dfif = new DiskFileItemFactory();
		String temp = this.getServletContext().getRealPath("/temp");
		//设置临时存放空间
		dfif.setRepository(new File(temp));
		//设置缓存空间大小
		dfif.setSizeThreshold(1024*1024*10);
		//2.得到解析对象
		ServletFileUpload upload  = new ServletFileUpload(dfif);
		upload.setHeaderEncoding("utf-8");
		try {
			//遍历FileItem集合
			List<FileItem> items = upload.parseRequest(request);
			for(FileItem item : items) {
				//是否是普通字段
				if(item.isFormField()) {
					String fieldName  = item.getFieldName();
					String value = item.getString("utf-8");
					map.put(fieldName, value);
				}else { //上传文件
					String fileName = item.getName();
					if(fileName != null && ! fileName.trim().equals("")) {
						//为了防止不同浏览器路径不同的问题
						int index = fileName.lastIndexOf("\\");
						if(index != -1) {
							fileName = fileName.substring(index+1);
						}
						//避免文件重,替换成UUID命名
						String ext = fileName.substring(fileName.lastIndexOf("."));
						fileName = IdUtils.getUUID()+ext;
						//文件上传的位置，直接存入eclipse下的项目WebContent
						String 	imgurl_path = "E:/jee-2019-06/workspace/bookproject/WebContent/productImg/"+fileName;
						File file = new File(imgurl_path);
						file.createNewFile();
						//上传文件（从磁盘的临时空间到服务器）
						InputStream in = item.getInputStream();
						FileOutputStream out = new  FileOutputStream(file);
						byte[]  buffer =  new byte[1024];  //每次读取1字节
						int len;
						while((len = in.read(buffer)) >0)
							out.write(buffer,0,len);
						in.close();
						out.close();
						//删除磁盘中的临时文件
						item.delete();
						
						map.put("imgurl", "/productImg/"+fileName);
					}
				}
			}
			//2.遍历结束，将Map里面键值对封装到Product里面
			BeanUtils.populate(p, map);
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//3.调用service
		ProductService service = new ProductService();
			service.editProduct(p);
			response.sendRedirect(request.getContextPath()+"/ListProductServlet");
		
	}

}
