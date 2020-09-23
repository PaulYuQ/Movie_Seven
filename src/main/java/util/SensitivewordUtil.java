package util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @Description: 敏感词过滤
 * @Project：test
 * @Author : chenming
 * @Date ： 2014年4月20日 下午4:17:15
 * @version 1.0
 */
public class SensitivewordUtil {
	@SuppressWarnings("rawtypes")
	private Map sensitiveWordMap = null;
	/**
	 * 最小匹配规则
	 */
	public static int minMatchTYpe = 1;
	/**
	 * 最大匹配规则
	 */
	public static int maxMatchType = 2;

	/**
	 * 构造函数，初始化敏感词库
	 */
	public SensitivewordUtil(){
		sensitiveWordMap = new SensitiveWordInit().initKeyWord();
	}
	/**
	 * 判断文字是否包含敏感字符
	 * @author
	 * @date
	 * @param txt  文字
	 * @param matchType  匹配规则&nbsp;1：最小匹配规则，2：最大匹配规则
	 * @return 若包含返回true，否则返回false
	 * @version 1.0
	 */
	public boolean isContaintSensitiveWord(String txt,int matchType){
		boolean flag = false;
		for(int i = 0 ; i < txt.length() ; i++){
			//判断是否包含敏感字符
			int matchFlag = this.CheckSensitiveWord(txt, i, matchType);
			//大于0存在，返回true
			if(matchFlag > 0){
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 获取文字中的敏感词
	 * @author
	 * @date
	 * @param txt 文字
	 * @param matchType 匹配规则&nbsp;1：最小匹配规则，2：最大匹配规则
	 * @return
	 * @version 1.0
	 */
	public Set<String> getSensitiveWord(String txt , int matchType){
		Set<String> sensitiveWordList = new HashSet();
		for(int i = 0 ; i < txt.length(); i++){
			int length = CheckSensitiveWord(txt, i, matchType);
			if(length > 0){
				sensitiveWordList.add(txt.substring(i, i+length));
				i = i + length - 1;
			}
		}
		return sensitiveWordList;
	}

	/**
	 * 替换敏感字字符
	 * @author
	 * @date
	 * @param txt
	 * @param matchType
	 * @param replaceChar 替换字符，默认*
	 * @version 1.0
	 */
	public String replaceSensitiveWord(String txt,int matchType,String replaceChar){
		String resultTxt = txt;
		//获取所有的敏感词
		Set<String> set = getSensitiveWord(txt, matchType);
		Iterator<String> iterator = set.iterator();
		String word = null;
		String replaceString = null;
		while (iterator.hasNext()) {
			word = iterator.next();
			replaceString = getReplaceChars(replaceChar, word.length());
			resultTxt = resultTxt.replaceAll(word, replaceString);
		}

		return resultTxt;
	}

	/**
	 * 获取替换字符串
	 * @author
	 * @date
	 * @param replaceChar
	 * @param length
	 * @return
	 * @version 1.0
	 */
	private String getReplaceChars(String replaceChar,int length){
		String resultReplace = replaceChar;
		for(int i = 1 ; i < length ; i++){
			resultReplace += replaceChar;
		}
		return resultReplace;
	}

	/**
	 * 检查文字中是否包含敏感字符，检查规则如下
	 * @author
	 * @date
	 * @param txt
	 * @param beginIndex
	 * @param matchType
	 * @return，如果存在，则返回敏感词字符的长度，不存在返回0
	 * @version 1.0
	 */
	@SuppressWarnings({ "rawtypes"})
	public int CheckSensitiveWord(String txt,int beginIndex,int matchType){
		boolean  flag = false;
		int matchFlag = 0;
		char word = 0;
		Map nowMap = sensitiveWordMap;
		for(int i = beginIndex; i < txt.length() ; i++){
			word = txt.charAt(i);
			nowMap = (Map) nowMap.get(word);
			if(nowMap != null){
				matchFlag++;
				if("1".equals(nowMap.get("isEnd"))){
					//结束标志位为true
					flag = true;
					// 最小规则，直接返回,最大规则还需继续查找
					if(SensitivewordUtil.minMatchTYpe == matchType){
						break;
					}
				}
			}
			else{
				break;
			}
		}
		if(matchFlag < 2 || !flag){
			matchFlag = 0;
		}
		return matchFlag;
	}
}
