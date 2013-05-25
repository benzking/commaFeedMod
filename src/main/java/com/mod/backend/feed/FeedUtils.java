package com.mod.backend.feed;

import org.apache.commons.lang.StringUtils;
import org.mozilla.universalchardet.UniversalDetector;


public class FeedUtils {

	public static String guessEncoding(byte[] bytes) {
		String DEFAULT_ENCODING = "UTF-8";
		UniversalDetector detector = new UniversalDetector(null);
		detector.handleData(bytes, 0, bytes.length);

		detector.dataEnd();
		String encoding = detector.getDetectedCharset();
		detector.reset();
		if (encoding == null) {
			encoding = DEFAULT_ENCODING;
		} else if (encoding.equalsIgnoreCase("ISO-8859-1")) {
			encoding = "windows-1252";
		}
		return encoding;
	}
//
//    /**
//     * 过滤可能导致出错的内容
//     * @param content   条目内容
//     * @return  条目内容
//     */
//	public static String handleContent(String content) {
//		if (StringUtils.isNotBlank(content)) {
//			Whitelist whitelist = Whitelist.relaxed();
//			whitelist.addEnforcedAttribute("a", "target", "_blank");
//
//			whitelist.addTags("iframe");
//			whitelist.addAttributes("iframe", "src", "height", "width",
//					"allowfullscreen", "frameborder");
//
//			content = Jsoup.clean(content, "", whitelist,
//					new OutputSettings().escapeMode(EscapeMode.base));
//		}
//		return content;
//	}

	public static String trimInvalidXmlCharacters(String xml) {
		if (StringUtils.isBlank(xml)) {
			return null;
		}
		StringBuilder sb = new StringBuilder();

		boolean firstTagFound = false;
		for (int i = 0; i < xml.length(); i++) {
			char c = xml.charAt(i);

			if (!firstTagFound) {
				if (c == '<') {
					firstTagFound = true;
				} else {
					continue;
				}
			}

			if (c >= 32 || c == 9 || c == 10 || c == 13) {
				if (!Character.isHighSurrogate(c)
						&& !Character.isLowSurrogate(c)) {
					sb.append(c);
				}
			}
		}
		return sb.toString();
	}
}
