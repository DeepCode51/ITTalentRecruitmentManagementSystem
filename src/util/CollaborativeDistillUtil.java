package util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
  
 
public class CollaborativeDistillUtil {
	
	
	private static final ExecutorService executorPool = new ThreadPoolExecutor(10, 30, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(50),new ThreadPoolExecutor.CallerRunsPolicy());
	
	
	public static String distillTemplates(List<String> targets){
		if (targets.isEmpty()) {
			return null;
		}else if(targets.size() == 1) {
			return targets.get(0);
		}
		String source = targets.get(0);
		String template = null;
		HashSet<String> set = new HashSet<String>();
		for (int i = 1; i < targets.size(); i++) {
			if (i==1) {
				template = distillTemplates(source, targets.get(i));
				
			}else { 
				template = distillTemplates(source, targets.get(i));
			}
			if(template!=null)
			set.add(template); 
		}
		String s = "";
		for(String str:set)
		{
			str=str.trim().replaceAll("[|]", " ") ;
			if(str.length()>0)s+=str+" ";
		}
		if(s.length()>0)s=s.substring(0,s.length()-1);
		//return format(template == null ? "|": template);
		return s.trim();
	}
	
	public static String distillTemplates(String source, String target) {
		
		// 符合这两字符串的全部模板
		List<Future<String>> futures = new Vector<Future<String>>();
		
		futures.add(executorPool.submit(new CollaborativeDistill(source,target,futures,executorPool,0,0,false,null,0)));
		
		Set<String> templates = new HashSet<String>();
		
		while (true) {
			int y = 0;
			for (int i = 0; i < futures.size(); i++) {
				try {
					y += 1;
					String template = futures.get(i).get();
					if (template != null) {
						templates.add(futures.get(i).get());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (y == futures.size()) {
				break;
			}
		}
		float maxRatio = 0;
		String maxRatioTemplate = null;
		for (String template : templates) {
			float ratio = CollaborativeRatioUtils.getSimilarityRatio(source, template);
			if (ratio > maxRatio) {
				maxRatio = ratio;
				maxRatioTemplate = template;
			}
		}
		return maxRatioTemplate;
	}
	
	public static String format(String template) {
		if (template == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		String[] vs = template.split("\\|");
		for (int i = 1; i <= vs.length; i++) {
			sb.append(vs[i-1]);
			if (i != vs.length) {
				sb.append("{"+i+"}");
			}
		}
		if (template.lastIndexOf("|") == template.length()-1) {
			sb.append("{"+((vs.length-1)<0?0:(vs.length-1))+"}");
		}
		return sb.toString();
	}
	
	public static String getTempage(ArrayList<String> list)
	{ 
		String templates = distillTemplates(list);
		System.out.println(templates);
		String t = "";
		for(String str:templates.split("[{]"))
		{
			if(str.contains("}"))
			{
				str = str.split("}")[1];
			}
			t += str+" ";
		}
		if(t.length()>0)t = t.substring(0,t.length()-1);
		t = t.trim();
		System.out.println("模板为："+t);
		return t;
	}
	
	
	public static void main(String[] args) {
		
		
//		System.out.println(format("123|123|"));
		
		long start = System.currentTimeMillis();
		
		String[] strss = new String[] {
//				"廖兴攀-有线，在2017-10-01 11:53:22",
//				"鲍冬梅-有线，在2017-10-01 11:53:11"
//				"【兴业银行】尊敬的用户,于10月8日前点 https://rsp.mobi/N3eOs4 申请信用卡，可获批10万额度。退订回T",
//				"【兴业银行】尊敬的用户,于10月8日前点 https://rsp.mobi/A5jk6Z 申请信用卡，可获批10万额度。退订回T"
//				"【随你花】尊敬的王建辉，您的账单现在到期了，逾期将取消再借资格，请及时处理！需要链接找客服。回T退订",
//				"【随你花】尊敬的项俊，您的账单现在到期了，逾期将取消再借资格，请及时处理！需要链接找客服。回T退订",
//				"廖兴攀-有线，在2017-10-01 11:53:37触发了上线提醒，请留意。",
//				"鲍冬梅-000有线，在2017-10-01 11:53:45触发了上线提醒，请留意。",
				"123xxx12aaa",
				"123xxz12",
				"123xxz221211",
				
				
				
//				,"【银汉科技】尊贵的易美达分期客户:郭文娟，你总12期的第2期1866.67元，2017-12-31为本期还款日,到目前未还款，现已经逾期，请珍视个人信用，维护良好的账户记录和信誉。如有疑问,请致电4000-512-012,退订回T。"
		};
//		System.out.println(SimilarityRatioUtils.getSimilarityRatio(strs[0], strs[1]));
		ArrayList<String> strs = new ArrayList<String>();
		strs.add("我程序员a123");
		strs.add("bcd");
		strs.add("java程序员");
		strs.add("程序");
		strs.add("我程");
		strs.add("123");
		String templates = CollaborativeDistillUtil.getTempage(strs);
		System.out.println(templates);
//		float maxRatio = 0;
//		String maxRatioTemplate = null;
//		for (String template : templates) {
//			float ratio = SimilarityRatioUtils.getSimilarityRatio(strs[0], template);
//			if (ratio > maxRatio) {
//				maxRatio = ratio;
//				maxRatioTemplate = template;
//			}
//		}
		System.out.println("模板："+templates);
//		
//		Matcher matcher = Pattern.compile("\\{\\d+\\}").matcher(maxRatioTemplate);
//		String templete = matcher.replaceAll(".*");
//		String regex = String.format("^%s$", templete); // 构建新的pattern字符串
//		for (String string : strs) {
//			System.out.println(Pattern.compile(regex).matcher(string).find());
//		}
		System.out.println("共计耗时："+ (System.currentTimeMillis()-start) +"毫秒");
	}
 
}