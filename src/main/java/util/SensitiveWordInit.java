package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @Description: 初始化敏感词库，将敏感词加入到HashMap中，构建DFA算法模型
 * @Project：test
 * @Author :
 * @Date ：
 * @version 1.0
 */
public class SensitiveWordInit {
	private String ENCODING = "utf-8";
	@SuppressWarnings("rawtypes")
	public HashMap sensitiveWordMap;

	public SensitiveWordInit(){
	}

	/**
	 * @author misskun
	 * @date 2014年4月20日 下午2:28:32
	 * @version 1.0
	 */
	@SuppressWarnings("rawtypes")
	public Map initKeyWord(){
		try {
			Set<String> keyWordSet = readSensitiveWordFile();
			addSensitiveWordToHashMap(keyWordSet);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sensitiveWordMap;
	}

	/**
	 * @author chenming
	 * @date 2014年4月20日 下午3:04:20
	 * @param keyWordSet  敏感词库
	 * @version 1.0
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void addSensitiveWordToHashMap(Set<String> keyWordSet) {
		sensitiveWordMap = new HashMap(keyWordSet.size());
		String key = null;
		Map nowMap = null;
		Map<String, String> newWorMap = null;
		//迭代keyWordSet
		Iterator<String> iterator = keyWordSet.iterator();
		while(iterator.hasNext()){
			//关键字
			key = iterator.next();
			nowMap = sensitiveWordMap;
			for(int i = 0 ; i < key.length() ; i++){
				//转换成char型
				char keyChar = key.charAt(i);
				Object wordMap = nowMap.get(keyChar);

				//如果存在该key，直接赋值
				if(wordMap != null){
					nowMap = (Map) wordMap;
				}
				else{
					//不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
					newWorMap = new HashMap<String,String>();
					//不是最后一个
					newWorMap.put("isEnd", "0");
					nowMap.put(keyChar, newWorMap);
					nowMap = newWorMap;
				}

				if(i == key.length() - 1){
					//最后一个
					nowMap.put("isEnd", "1");
				}
			}
		}
	}
	/**
	 * 读取敏感词库中的内容，将内容添加到set集合中
	 * @author chenming
	 * @date 2014年4月20日 下午2:31:18
	 * @return
	 * @version 1.0
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	private  Set<String> readSensitiveWordFile() throws Exception{
		Set<String> set = null;


		//File file = new File("resource\\filter.txt");
		String filePath =SensitiveWordInit.class.getClassLoader().getResource("filter.txt").getFile();
		filePath=filePath.replaceAll("%20"," ");
		System.out.println(filePath);
		File file = new File(filePath);
		InputStreamReader read = new InputStreamReader(new FileInputStream(file),ENCODING);
		try {
			//文件流是否存在
			if(file.isFile() && file.exists()){
				set = new HashSet<String>();
				BufferedReader bufferedReader = new BufferedReader(read);
				String txt = null;
				//读取文件，将文件内容放入到set中
				while((txt = bufferedReader.readLine()) != null){
					set.add(txt);
				}
			}
			else{
				throw new Exception("敏感词库文件不存在");
			}
		} catch (Exception e) {
			throw e;
		}finally{
			read.close();
		}
		return set;
	}
}
