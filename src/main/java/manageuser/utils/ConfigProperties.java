/**
 * Luvina Software JSC, 2022
 * ConfigProperies.java,30/9/2022 HuyNQ
 */
package manageuser.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Class này dùng để đọc các dữ liệu theo yêu cầu của thiết kế 
 * 
 * @author HuyNQ
 */
public class ConfigProperties {
	//khai bao bien Constant
		static final String PROPERTIES_DATABASE_PATH = "./manageuser/utils/config.properties";
		// lưu các cặp <key, value> trong file properties
		private static Map<String, String> mapDBProperties = new HashMap<String, String>();
		static {
			try {
				// tạo đối tượng kiểu Properties
				Properties properties = new Properties(); 
				// đọc file properties
				properties.load(new InputStreamReader(DatabaseProperties.class.getClassLoader()
						.getResourceAsStream(PROPERTIES_DATABASE_PATH), "UTF-8"));
				// lưu các giá trị key trong file properties					
				Enumeration<?> enumeration = properties.propertyNames(); 
				// true nếu vẫn còn phần tử, false nếu tất cả phần tử đã được lấy ra
				while (enumeration.hasMoreElements()) { 
					// key = key tiếp theo
					String key = (String) enumeration.nextElement(); 
					// lấy value tương ứng với key
					String value = properties.getProperty(key); 
					// thêm vào list
					mapDBProperties.put(key, value); 
				}
			} catch (IOException e) {
				System.out.println("DatabaseProperties " + e.getMessage());
//				throw e;
			}
		}

		/**
		 * lấy value tương ứng với key trong file properties
		 * 
		 * @param key tên key trong file properties
		 * @return trả về value tương ứng với key
		 */
		public static String getValueByKey(String key) {
			String value = mapDBProperties.get(key);
			return value;
		}
}
