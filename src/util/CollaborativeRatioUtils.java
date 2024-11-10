package util;

public class CollaborativeRatioUtils {
	 
	/**
	 * 莱茵斯坦距离 
	 * @param str 
	 * @param target
	 * @return   
	 */
	public static int levenshiteinDistance(String str, String target) {
		int s = str.length();
		int t = target.length();
		if (s == 0) {
			return t;
		}
		if (t == 0) {
			return s;
		}
		int d[][];
		int si;
		int tj;
		char strChar;
		char tagetChar;
		int temp; // 记录相同字符,在某个矩阵位置值的增量,不是0就是1
		d = new int[s + 1][t + 1];
		for (si = 0; si <= s; si++) {// 初始化第一列
			d[si][0] = si;
		}
		for (si = 1; si <= s; si++) {// 遍历str
			strChar = str.charAt(si - 1);
			for (tj = 1; tj <= t; tj++) {
				tagetChar = target.charAt(tj - 1);
				/*
				 * 忽略大小写 if (strChar == tagetChar || strChar == tagetChar + 32 || strChar + 32
				 * == tagetChar) {
				 */
				if (strChar == tagetChar) {
					temp = 0;
				} else {
					temp = 1;
				}
				d[si][tj] = min(d[si - 1][tj] + 1, d[si][tj - 1] + 1, d[si - 1][tj - 1] + temp);
			}
		}
		return d[s][t];
	}
 
	private static int min(int one, int two, int three) {
		return (one = one < two ? one : two) < three ? one : three;
	}
 
	/**
	 * 获取两字符串的相似度
	 */
	public static float getSimilarityRatio(String str, String target) {
		// 1-莱茵斯坦距离/最大字符串长度
		return 1 - (float) levenshiteinDistance(str, target) / Math.max(str.length(), target.length());
	}
}