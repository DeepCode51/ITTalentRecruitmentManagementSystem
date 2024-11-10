package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
 
/**
 * 模板提取
 * @author Administrator
 *
 */
public class CollaborativeDistill implements Callable<String>{
 
	public CollaborativeDistill(String source, String target, List<Future<String>> futures, ExecutorService pool, int startSourceIDX,int startTargetIDX, boolean lastIsPlaceholder,String templatePrefix,int variableNum) {
		super();
		this.source = source;
		this.target = target;
		this.futures = futures;
		this.pool = pool;
		this.startSourceIDX = startSourceIDX;
		this.startTargetIDX = startTargetIDX;
		this.lastIsPlaceholder = lastIsPlaceholder;
		this.templatePrefix = templatePrefix == null ? "":templatePrefix;
		this.variableNum = variableNum;
	}
	
	private String templatePrefix;
	
	private final int maxVariableNum = 3;
	
	private int variableNum;
	
	private int startSourceIDX;
	
	private int startTargetIDX;
	
	private boolean lastIsPlaceholder;
 
	private final String source;
	
	private final String target;
 
	private final List<Future<String>> futures;
 
	private final ExecutorService pool;
	
	@SuppressWarnings("unchecked")
	public String call() throws Exception {
		StringBuilder template = new StringBuilder(templatePrefix);
		while (true) {
			// 获取最大连击数
			Map<String, Object> result = carom(source, target, startSourceIDX, startTargetIDX);
			int maxCaromNum = (Integer) result.get("caromNum");
			if (maxCaromNum == 0) {
				if(!lastIsPlaceholder) {
					variableNum += 1;
					if (variableNum > maxVariableNum) {
						return null;
					}
					template.append("|");
					lastIsPlaceholder = true;
				}
				if (result.get("otherTargetIndexs") != null) {
					// 开启多线程找模板
					List<Integer> otherTargetIndexs = (List<Integer>) result.get("otherTargetIndexs");
					for (Integer otherTargetIndex : otherTargetIndexs) {
						futures.add(pool.submit(new CollaborativeDistill(source, target, futures, pool, startSourceIDX, otherTargetIndex, lastIsPlaceholder,template.toString(), variableNum)));
					}
				}
				startSourceIDX += 1;
			}else{
				template.append(source.substring(startSourceIDX, startSourceIDX + maxCaromNum));
				startSourceIDX += maxCaromNum;
				startTargetIDX += maxCaromNum;
				lastIsPlaceholder = false;;
			}
			if (source.length() <= startSourceIDX) {
				if (!lastIsPlaceholder && target.length() > startTargetIDX) {
					variableNum += 1;
					if (variableNum > maxVariableNum) {
						return null;
					}
					template.append("|");
				}
				break;
			}
			if (target.length() <= startTargetIDX) {
				if(!lastIsPlaceholder) {
					variableNum += 1;
					if (variableNum > maxVariableNum) {
						return null;
					}
					template.append("|");
				}
				break;
			}
		}
		return template.toString();
	}
 
	
	/**
	 * 获取连击数
	 * @param source 字符串s
	 * @param target 字符串t
	 * @param startSourceIDX 字符串s从什么下标开始匹配
	 * @param startTargetIDX 字符串t从什么下标开始匹配
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Map<String,Object> carom(String source,String target,Integer startSourceIDX,Integer startTargetIDX){
		// 连击次数
		int attemptCaromNum = 0;
		while(true) {
			// 每次累加1,最后减1
			attemptCaromNum += 1;
			// 本次匹配的子串的结束下标
			int endSourceIDX = startSourceIDX + attemptCaromNum;
			if (endSourceIDX > source.length()) {
				break;
			}
			// 本次匹配的子串
			String attemptStr = source.substring(startSourceIDX, endSourceIDX);
			int idx = target.indexOf(attemptStr, startTargetIDX);
			// 匹配失败
			if (idx != startTargetIDX) {
				break;
			}
		}
		attemptCaromNum -= 1;
		// 结果
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("caromNum", attemptCaromNum);
		// 匹配失败时
		if (attemptCaromNum == 0) {
			// 单纯的一个字符
			int endStrIDX = startSourceIDX + 1;
			if (endStrIDX <= source.length()) {
				// 匹配他失败后的字符
				List<Integer> otherTargetIndexs = new ArrayList();
				char c = source.charAt(startSourceIDX);
				char[] cs = target.substring(startTargetIDX+1).toCharArray();
				for (int i = 0; i < cs.length; i++) {
					if (c == cs[i]) {
						otherTargetIndexs.add(startTargetIDX+1+i);
					}
				}
				result.put("otherTargetIndexs", otherTargetIndexs.isEmpty() ? null : otherTargetIndexs);
			}
		}
		return result;
	}
	
}
