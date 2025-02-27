package org.tis.tools.asf.module.biz.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/4/12
 **/
public class PinyinTools {

    HanyuPinyinOutputFormat format = null;
    public static enum Type {
        UPPERCASE,              //全部大写
        LOWERCASE,              //全部小写
        FIRSTUPPER,              //首字母大写
        FIRSTLOWERABBR           //首字母小写缩写
    }

    public PinyinTools(){
        format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    }

    public String toPinYin(String str) throws BadHanyuPinyinOutputFormatCombination {
        return toPinYin(str, "", Type.UPPERCASE);
    }

    public String toPinYin(String str,String spera) throws BadHanyuPinyinOutputFormatCombination{
        return toPinYin(str, spera, Type.UPPERCASE);
    }

    public String toPinYinAbbr(String str) throws BadHanyuPinyinOutputFormatCombination{
        return toPinYin(str, "", Type.FIRSTLOWERABBR);
    }

    /**
     * 将str转换成拼音，如果不是汉字或者没有对应的拼音，则不作转换
     * 如： 明天 转换成 MINGTIAN
     * @param str：要转化的汉字
     * @param spera：转化结果的分割符
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    public String toPinYin(String str, String spera, Type type) throws BadHanyuPinyinOutputFormatCombination {
        if (str == null || str.trim().length()==0) {
            return "";
        }
        if (type == Type.UPPERCASE) {
            format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        } else {
            format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        }

        String py = "";
        String temp = "";
        String[] t;
        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            if ((int)c <= 128) {
                py += c;
            } else{
                t = PinyinHelper.toHanyuPinyinStringArray(c, format);
                if (t == null) {
                    py += c;
                } else {
                    temp = t[0];
                    if (type == Type.FIRSTLOWERABBR) {
                        py += temp.substring(0, 1);
                    } else {
                        if (type == Type.FIRSTUPPER) {
                            temp = t[0].toUpperCase().charAt(0) + temp.substring(1);
                        }
                        py += temp + (i == str.length() - 1 ? "" : spera);
                    }
                }
            }
        }
        return py.trim();
    }

    public static void main(String[] args) {
        try {
            String s = new PinyinTools().toPinYinAbbr("ss系统");
            System.out.println(s);
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            badHanyuPinyinOutputFormatCombination.printStackTrace();
        }
    }
}